package com.ex.ltech.hongwai.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import java.lang.reflect.Field;

@SuppressLint({"NewApi"})
public class DragGridView extends GridView
{
  private static final int speed = 80;
  private long dragResponseMS = 1000L;
  private boolean isDrag = false;
  private int mDownScrollBorder;
  private int mDownX;
  private int mDownY;
  private Bitmap mDragBitmap;
  private ImageView mDragImageView;
  private int mDragPosition;
  private Handler mHandler = new Handler();
  private Runnable mLongClickRunnable = new Runnable()
  {
    public void run()
    {
      DragGridView.access$002(DragGridView.this, true);
      DragGridView.this.mVibrator.vibrate(50L);
      DragGridView.this.mStartDragItemView.setVisibility(4);
      DragGridView.this.createDragImage(DragGridView.this.mDragBitmap, DragGridView.this.mDownX, DragGridView.this.mDownY);
    }
  };
  private int mOffset2Left;
  private int mOffset2Top;
  private int mPoint2ItemLeft;
  private int mPoint2ItemTop;
  private Runnable mScrollRunnable = new Runnable()
  {
    public void run()
    {
      int i;
      if (DragGridView.this.moveY > DragGridView.this.mUpScrollBorder)
      {
        i = -80;
        DragGridView.this.mHandler.postDelayed(DragGridView.this.mScrollRunnable, 25L);
      }
      while (true)
      {
        DragGridView.this.onSwapItem(DragGridView.this.moveX, DragGridView.this.moveY);
        View localView = DragGridView.this.getChildAt(DragGridView.this.mDragPosition - DragGridView.this.getFirstVisiblePosition());
        DragGridView.this.smoothScrollToPositionFromTop(DragGridView.this.mDragPosition, i + localView.getTop());
        return;
        if (DragGridView.this.moveY < DragGridView.this.mDownScrollBorder)
        {
          i = 80;
          DragGridView.this.mHandler.postDelayed(DragGridView.this.mScrollRunnable, 25L);
          continue;
        }
        DragGridView.this.mHandler.removeCallbacks(DragGridView.this.mScrollRunnable);
        i = 0;
      }
    }
  };
  private View mStartDragItemView = null;
  private int mStatusHeight;
  private int mUpScrollBorder;
  private Vibrator mVibrator;
  private WindowManager.LayoutParams mWindowLayoutParams;
  private WindowManager mWindowManager;
  private int moveX;
  private int moveY;
  private OnChanageListener onChanageListener;

  public DragGridView(Context paramContext)
  {
    this(paramContext, null);
  }

  public DragGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public DragGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mVibrator = ((Vibrator)paramContext.getSystemService("vibrator"));
    this.mWindowManager = ((WindowManager)paramContext.getSystemService("window"));
    this.mStatusHeight = getStatusHeight(paramContext);
  }

  private void createDragImage(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    this.mWindowLayoutParams = new WindowManager.LayoutParams();
    this.mWindowLayoutParams.format = -3;
    this.mWindowLayoutParams.gravity = 51;
    this.mWindowLayoutParams.x = (paramInt1 - this.mPoint2ItemLeft + this.mOffset2Left);
    this.mWindowLayoutParams.y = (paramInt2 - this.mPoint2ItemTop + this.mOffset2Top - this.mStatusHeight);
    this.mWindowLayoutParams.alpha = 0.55F;
    this.mWindowLayoutParams.width = -2;
    this.mWindowLayoutParams.height = -2;
    this.mWindowLayoutParams.flags = 24;
    this.mDragImageView = new ImageView(getContext());
    this.mDragImageView.setImageBitmap(paramBitmap);
    this.mWindowManager.addView(this.mDragImageView, this.mWindowLayoutParams);
  }

  private static int getStatusHeight(Context paramContext)
  {
    Rect localRect = new Rect();
    ((Activity)paramContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    int i = localRect.top;
    if (i == 0);
    try
    {
      Class localClass = Class.forName("com.android.internal.R$dimen");
      Object localObject = localClass.newInstance();
      int j = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
      int k = paramContext.getResources().getDimensionPixelSize(j);
      i = k;
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return i;
  }

  private boolean isTouchInItem(View paramView, int paramInt1, int paramInt2)
  {
    int i = paramView.getLeft();
    int j = paramView.getTop();
    if ((paramInt1 < i) || (paramInt1 > i + paramView.getWidth()));
    do
      return false;
    while ((paramInt2 < j) || (paramInt2 > j + paramView.getHeight()));
    return true;
  }

  private void onDragItem(int paramInt1, int paramInt2)
  {
    this.mWindowLayoutParams.x = (paramInt1 - this.mPoint2ItemLeft + this.mOffset2Left);
    this.mWindowLayoutParams.y = (paramInt2 - this.mPoint2ItemTop + this.mOffset2Top - this.mStatusHeight);
    this.mWindowManager.updateViewLayout(this.mDragImageView, this.mWindowLayoutParams);
    onSwapItem(paramInt1, paramInt2);
    this.mHandler.post(this.mScrollRunnable);
  }

  private void onStopDrag()
  {
    getChildAt(this.mDragPosition - getFirstVisiblePosition()).setVisibility(View.VISIBLE);
    removeDragImage();
  }

  private void onSwapItem(int paramInt1, int paramInt2)
  {
    int i = pointToPosition(paramInt1, paramInt2);
    if ((i != this.mDragPosition) && (i != -1))
    {
      getChildAt(i - getFirstVisiblePosition()).setVisibility(4);
      getChildAt(this.mDragPosition - getFirstVisiblePosition()).setVisibility(View.VISIBLE);
      if (this.onChanageListener != null)
        this.onChanageListener.onChange(this.mDragPosition, i);
      this.mDragPosition = i;
    }
  }

  private void removeDragImage()
  {
    if (this.mDragImageView != null)
    {
      this.mWindowManager.removeView(this.mDragImageView);
      this.mDragImageView = null;
    }
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 1:
    }
    while (true)
    {
      return super.dispatchTouchEvent(paramMotionEvent);
      this.mHandler.postDelayed(this.mLongClickRunnable, this.dragResponseMS);
      this.mDownX = (int)paramMotionEvent.getX();
      this.mDownY = (int)paramMotionEvent.getY();
      this.mDragPosition = pointToPosition(this.mDownX, this.mDownY);
      if (this.mDragPosition == -1)
        return super.dispatchTouchEvent(paramMotionEvent);
      this.mStartDragItemView = getChildAt(this.mDragPosition - getFirstVisiblePosition());
      this.mPoint2ItemTop = (this.mDownY - this.mStartDragItemView.getTop());
      this.mPoint2ItemLeft = (this.mDownX - this.mStartDragItemView.getLeft());
      this.mOffset2Top = (int)(paramMotionEvent.getRawY() - this.mDownY);
      this.mOffset2Left = (int)(paramMotionEvent.getRawX() - this.mDownX);
      this.mDownScrollBorder = (getHeight() / 4);
      this.mUpScrollBorder = (3 * getHeight() / 4);
      this.mStartDragItemView.setDrawingCacheEnabled(true);
      this.mDragBitmap = Bitmap.createBitmap(this.mStartDragItemView.getDrawingCache());
      this.mStartDragItemView.destroyDrawingCache();
      continue;
      int i = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      if (isTouchInItem(this.mStartDragItemView, i, j))
        continue;
      this.mHandler.removeCallbacks(this.mLongClickRunnable);
      continue;
      this.mHandler.removeCallbacks(this.mLongClickRunnable);
      this.mHandler.removeCallbacks(this.mScrollRunnable);
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.isDrag) && (this.mDragImageView != null))
    {
      switch (paramMotionEvent.getAction())
      {
      default:
      case 2:
      case 1:
      }
      while (true)
      {
        return true;
        this.moveX = (int)paramMotionEvent.getX();
        this.moveY = (int)paramMotionEvent.getY();
        onDragItem(this.moveX, this.moveY);
        continue;
        onStopDrag();
        this.isDrag = false;
      }
    }
    return super.onTouchEvent(paramMotionEvent);
  }

  public void setDragResponseMS(long paramLong)
  {
    this.dragResponseMS = paramLong;
  }

  public void setOnChangeListener(OnChanageListener paramOnChanageListener)
  {
    this.onChanageListener = paramOnChanageListener;
  }

  public static abstract interface OnChanageListener
  {
    public abstract void onChange(int paramInt1, int paramInt2);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.DragGridView
 * JD-Core Version:    0.6.0
 */