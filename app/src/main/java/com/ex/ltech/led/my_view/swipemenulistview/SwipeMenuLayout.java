package com.ex.ltech.led.my_view.swipemenulistview;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;

public class SwipeMenuLayout extends FrameLayout
{
  private static final int CONTENT_VIEW_ID = 1;
  private static final int MENU_VIEW_ID = 2;
  private static final int STATE_CLOSE = 0;
  private static final int STATE_OPEN = 1;
  private int MAX_VELOCITYX = -dp2px(500);
  private int MIN_FLING = dp2px(15);
  private boolean isFling;
  private int mBaseX;
  private Interpolator mCloseInterpolator;
  private ScrollerCompat mCloseScroller;
  private View mContentView;
  private int mDownX;
  private GestureDetectorCompat mGestureDetector;
  private GestureDetector.OnGestureListener mGestureListener;
  private SwipeMenuView mMenuView;
  private Interpolator mOpenInterpolator;
  private ScrollerCompat mOpenScroller;
  private int position;
  private int state = 0;

  private SwipeMenuLayout(Context paramContext)
  {
    super(paramContext);
  }

  private SwipeMenuLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public SwipeMenuLayout(View paramView, SwipeMenuView paramSwipeMenuView)
  {
    this(paramView, paramSwipeMenuView, null, null);
  }

  public SwipeMenuLayout(View paramView, SwipeMenuView paramSwipeMenuView, Interpolator paramInterpolator1, Interpolator paramInterpolator2)
  {
    super(paramView.getContext());
    this.mCloseInterpolator = paramInterpolator1;
    this.mOpenInterpolator = paramInterpolator2;
    this.mContentView = paramView;
    this.mMenuView = paramSwipeMenuView;
    this.mMenuView.setLayout(this);
    init();
  }

  private int dp2px(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, getContext().getResources().getDisplayMetrics());
  }

  private void init()
  {
    setLayoutParams(new AbsListView.LayoutParams(-1, -2));
    this.mGestureListener = new GestureDetector.SimpleOnGestureListener()
    {
      public boolean onDown(MotionEvent paramMotionEvent)
      {
//        SwipeMenuLayout.access$002(SwipeMenuLayout.this, false);
        return true;
      }

      public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
      {
//        if ((paramMotionEvent1.getX() - paramMotionEvent2.getX() > SwipeMenuLayout.this.MIN_FLING) && (paramFloat1 < SwipeMenuLayout.this.MAX_VELOCITYX))
//          SwipeMenuLayout.access$002(SwipeMenuLayout.this, true);
        return super.onFling(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2);
      }
    };
    this.mGestureDetector = new GestureDetectorCompat(getContext(), this.mGestureListener);
    /*if (this.mCloseInterpolator != null)
    {
      this.mCloseScroller = ScrollerCompat.create(getContext(), this.mCloseInterpolator);
      if (this.mOpenInterpolator == null)
        break label184;
    }
    label184: for (this.mOpenScroller = ScrollerCompat.create(getContext(), this.mOpenInterpolator); ; this.mOpenScroller = ScrollerCompat.create(getContext()))
    {
      LayoutParams localLayoutParams = new LayoutParams(-1, -2);
      this.mContentView.setLayoutParams(localLayoutParams);
      if (this.mContentView.getId() < 1)
        this.mContentView.setId(1);
      this.mMenuView.setId(2);
      this.mMenuView.setLayoutParams(new LayoutParams(-2, -2));
      addView(this.mContentView);
      addView(this.mMenuView);
      return;
      this.mCloseScroller = ScrollerCompat.create(getContext());
      break;
    }*/
  }

  private void swipe(int paramInt)
  {
    if (paramInt > this.mMenuView.getWidth())
      paramInt = this.mMenuView.getWidth();
    if (paramInt < 0)
      paramInt = 0;
    this.mContentView.layout(-paramInt, this.mContentView.getTop(), this.mContentView.getWidth() - paramInt, getMeasuredHeight());
    this.mMenuView.layout(this.mContentView.getWidth() - paramInt, this.mMenuView.getTop(), this.mContentView.getWidth() + this.mMenuView.getWidth() - paramInt, this.mMenuView.getBottom());
  }

  public void closeMenu()
  {
    if (this.mCloseScroller.computeScrollOffset())
      this.mCloseScroller.abortAnimation();
    if (this.state == 1)
    {
      this.state = 0;
      swipe(0);
    }
  }

  public void computeScroll()
  {
    /*if (this.state == 1)
      if (this.mOpenScroller.computeScrollOffset())
      {
        swipe(this.mOpenScroller.getCurrX());
        postInvalidate();
      }
    do
      return;
    while (!this.mCloseScroller.computeScrollOffset());
    swipe(this.mBaseX - this.mCloseScroller.getCurrX());
    postInvalidate();*/
  }

  public View getContentView()
  {
    return this.mContentView;
  }

  public SwipeMenuView getMenuView()
  {
    return this.mMenuView;
  }

  public int getPosition()
  {
    return this.position;
  }

  public boolean isOpen()
  {
    return this.state == 1;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mContentView.layout(0, 0, getMeasuredWidth(), this.mContentView.getMeasuredHeight());
    this.mMenuView.layout(getMeasuredWidth(), 0, getMeasuredWidth() + this.mMenuView.getMeasuredWidth(), this.mContentView.getMeasuredHeight());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
//    this.mMenuView.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
    this.mMenuView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public boolean onSwipe(MotionEvent paramMotionEvent)
  {
    /*this.mGestureDetector.onTouchEvent(paramMotionEvent);
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 1:
    }
    while (true)
    {
      return true;
      this.mDownX = (int)paramMotionEvent.getX();
      this.isFling = false;
      continue;
      int i = (int)(this.mDownX - paramMotionEvent.getX());
      if (this.state == 1)
        i += this.mMenuView.getWidth();
      swipe(i);
      continue;
      if ((!this.isFling) && (this.mDownX - paramMotionEvent.getX() <= this.mMenuView.getWidth() / 2))
        break;
      smoothOpenMenu();
    }
    smoothCloseMenu();
    return false;*/
    return false;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.onTouchEvent(paramMotionEvent);
  }

  public void openMenu()
  {
    if (this.state == 0)
    {
      this.state = 1;
      swipe(this.mMenuView.getWidth());
    }
  }

  public void setMenuHeight(int paramInt)
  {
    LayoutParams localLayoutParams = (LayoutParams)this.mMenuView.getLayoutParams();
    if (localLayoutParams.height != paramInt)
    {
      localLayoutParams.height = paramInt;
      this.mMenuView.setLayoutParams(this.mMenuView.getLayoutParams());
    }
  }

  public void setPosition(int paramInt)
  {
    this.position = paramInt;
    this.mMenuView.setPosition(paramInt);
  }

  public void smoothCloseMenu()
  {
    this.state = 0;
    this.mBaseX = (-this.mContentView.getLeft());
    this.mCloseScroller.startScroll(0, 0, this.mBaseX, 0, 350);
    postInvalidate();
  }

  public void smoothOpenMenu()
  {
    this.state = 1;
    this.mOpenScroller.startScroll(-this.mContentView.getLeft(), 0, this.mMenuView.getWidth(), 0, 350);
    postInvalidate();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuLayout
 * JD-Core Version:    0.6.0
 */