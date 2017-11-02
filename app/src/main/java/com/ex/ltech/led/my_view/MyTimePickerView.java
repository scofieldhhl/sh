package com.ex.ltech.led.my_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimePickerView extends View
{
  public static final float MARGIN_ALPHA = 2.8F;
  public static final float SPEED = 2.0F;
  public static final String TAG = "PickerView";
  private boolean isInit = false;
  private int mColorText = 16670515;
  private int mColorText2 = -6184797;
  private int mCurrentSelected;
  private List<String> mDataList;
  private float mLastDownY;
  private float mMaxTextAlpha = 255.0F;
  private float mMaxTextSize = 50.0F;
  private float mMinTextAlpha = 120.0F;
  private float mMinTextSize = 45.0F;
  private float mMoveLen = 0.0F;
  private Paint mPaint;
  private Paint mPaint2;
  private onSelectListener mSelectListener;
  private MyTimerTask mTask;
  private int mViewHeight;
  private int mViewWidth;
  private Timer timer;
  Handler updateHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      if (Math.abs(MyTimePickerView.this.mMoveLen) < 2.0F)
      {
        MyTimePickerView.access$002(MyTimePickerView.this, 0.0F);
        if (MyTimePickerView.this.mTask != null)
        {
          MyTimePickerView.this.mTask.cancel();
          MyTimePickerView.access$102(MyTimePickerView.this, null);
          MyTimePickerView.this.performSelect();
        }
      }
      while (true)
      {
        MyTimePickerView.this.invalidate();
        return;
        MyTimePickerView.access$002(MyTimePickerView.this, MyTimePickerView.this.mMoveLen - 2.0F * (MyTimePickerView.this.mMoveLen / Math.abs(MyTimePickerView.this.mMoveLen)));
      }
    }
  };
  private String value;

  public MyTimePickerView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public MyTimePickerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  private void doDown(MotionEvent paramMotionEvent)
  {
    if (this.mTask != null)
    {
      this.mTask.cancel();
      this.mTask = null;
    }
    this.mLastDownY = paramMotionEvent.getY();
  }

  private void doMove(MotionEvent paramMotionEvent)
  {
    this.mMoveLen += paramMotionEvent.getY() - this.mLastDownY;
    if (this.mMoveLen > 2.8F * this.mMinTextSize / 2.0F)
    {
      moveTailToHead();
      this.mMoveLen -= 2.8F * this.mMinTextSize;
    }
    while (true)
    {
      this.mLastDownY = paramMotionEvent.getY();
      invalidate();
      return;
      if (this.mMoveLen >= -2.8F * this.mMinTextSize / 2.0F)
        continue;
      moveHeadToTail();
      this.mMoveLen += 2.8F * this.mMinTextSize;
    }
  }

  private void doUp(MotionEvent paramMotionEvent)
  {
    if (Math.abs(this.mMoveLen) < 0.0001D)
    {
      this.mMoveLen = 0.0F;
      return;
    }
    if (this.mTask != null)
    {
      this.mTask.cancel();
      this.mTask = null;
    }
    this.mTask = new MyTimerTask(this.updateHandler);
    this.timer.schedule(this.mTask, 0L, 10L);
  }

  private void drawData(Canvas paramCanvas)
  {
    if (isInEditMode());
    while (true)
    {
      return;
      float f1 = parabola(this.mViewHeight / 4.0F, this.mMoveLen);
      float f2 = f1 * (this.mMaxTextSize - this.mMinTextSize) + this.mMinTextSize;
      this.mPaint.setTextSize(f2);
      this.mPaint.setAlpha((int)(f1 * (this.mMaxTextAlpha - this.mMinTextAlpha) + this.mMinTextAlpha));
      this.mPaint2.setTextSize(f2);
      this.mPaint2.setAlpha((int)(f1 * (this.mMaxTextAlpha - this.mMinTextAlpha) + this.mMinTextAlpha));
      float f3 = (float)(this.mViewWidth / 2.0D);
      float f4 = (float)(this.mViewHeight / 2.0D + this.mMoveLen);
      Paint.FontMetricsInt localFontMetricsInt = this.mPaint.getFontMetricsInt();
      float f5 = (float)(f4 - (localFontMetricsInt.bottom / 2.0D + localFontMetricsInt.top / 2.0D));
      this.value = ((String)this.mDataList.get(this.mCurrentSelected));
      paramCanvas.drawText((String)this.mDataList.get(this.mCurrentSelected), f3, f5, this.mPaint2);
      for (int i = 1; this.mCurrentSelected - i >= 0; i++)
        drawOtherText(paramCanvas, i, -1);
      for (int j = 1; j + this.mCurrentSelected < this.mDataList.size(); j++)
        drawOtherText(paramCanvas, j, 1);
    }
  }

  private void drawOtherText(Canvas paramCanvas, int paramInt1, int paramInt2)
  {
    float f1 = 2.8F * this.mMinTextSize * paramInt1 + paramInt2 * this.mMoveLen;
    float f2 = parabola(this.mViewHeight / 4.0F, f1);
    float f3 = f2 * (this.mMaxTextSize - this.mMinTextSize) + this.mMinTextSize;
    this.mPaint.setTextSize(f3);
    this.mPaint.setAlpha((int)(f2 * (this.mMaxTextAlpha - this.mMinTextAlpha) + this.mMinTextAlpha));
    float f4 = (float)(this.mViewHeight / 2.0D + f1 * paramInt2);
    Paint.FontMetricsInt localFontMetricsInt = this.mPaint.getFontMetricsInt();
    float f5 = (float)(f4 - (localFontMetricsInt.bottom / 2.0D + localFontMetricsInt.top / 2.0D));
    paramCanvas.drawText((String)this.mDataList.get(this.mCurrentSelected + paramInt2 * paramInt1), (float)(this.mViewWidth / 2.0D), f5, this.mPaint);
  }

  private void init()
  {
    this.timer = new Timer();
    this.mDataList = new ArrayList();
    this.mPaint = new Paint(1);
    this.mPaint.setStyle(Paint.Style.FILL);
    this.mPaint.setTextAlign(Paint.Align.CENTER);
    this.mPaint.setColor(this.mColorText2);
    this.mPaint2 = new Paint(1);
    this.mPaint2.setStyle(Paint.Style.FILL);
    this.mPaint2.setTextAlign(Paint.Align.CENTER);
    this.mPaint2.setColor(this.mColorText);
  }

  private void moveHeadToTail()
  {
    String str = (String)this.mDataList.get(0);
    this.mDataList.remove(0);
    this.mDataList.add(str);
  }

  private void moveTailToHead()
  {
    String str = (String)this.mDataList.get(-1 + this.mDataList.size());
    this.mDataList.remove(-1 + this.mDataList.size());
    this.mDataList.add(0, str);
  }

  private float parabola(float paramFloat1, float paramFloat2)
  {
    float f = (float)(1.0D - Math.pow(paramFloat2 / paramFloat1, 2.0D));
    if (f < 0.0F)
      f = 0.0F;
    return f;
  }

  private void performSelect()
  {
    if (this.mSelectListener != null)
      this.mSelectListener.onSelect((String)this.mDataList.get(this.mCurrentSelected));
  }

  public String getValue()
  {
    return this.value;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.isInit)
      drawData(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    this.mViewHeight = getMeasuredHeight();
    this.mViewWidth = getMeasuredWidth();
    this.mMaxTextSize = (this.mViewHeight / 10.0F);
    this.mMinTextSize = (this.mMaxTextSize / 2.0F);
    this.isInit = true;
    invalidate();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getActionMasked())
    {
    default:
    case 0:
    case 2:
    case 1:
    }
    while (true)
    {
      return true;
      doDown(paramMotionEvent);
      continue;
      doMove(paramMotionEvent);
      continue;
      doUp(paramMotionEvent);
    }
  }

  public void setData(List<String> paramList)
  {
    this.mDataList = paramList;
    this.mCurrentSelected = (paramList.size() / 2);
    invalidate();
  }

  public void setOnSelectListener(onSelectListener paramonSelectListener)
  {
    this.mSelectListener = paramonSelectListener;
  }

  public void setSelected(int paramInt)
  {
    this.mCurrentSelected = paramInt;
    int i = this.mDataList.size() / 2 - this.mCurrentSelected;
    if (i < 0)
      for (int k = 0; k < -i; k++)
      {
        moveHeadToTail();
        this.mCurrentSelected = (-1 + this.mCurrentSelected);
      }
    if (i > 0)
      for (int j = 0; j < i; j++)
      {
        moveTailToHead();
        this.mCurrentSelected = (1 + this.mCurrentSelected);
      }
    invalidate();
  }

  public void setSelected(String paramString)
  {
    System.out.println("mSelectItem = " + paramString);
    for (int i = 0; ; i++)
    {
      if (i < this.mDataList.size())
      {
        System.out.println("mDataList.get(i) = " + (String)this.mDataList.get(i));
        if (!((String)this.mDataList.get(i)).equals(paramString))
          continue;
        setSelected(i);
      }
      return;
    }
  }

  public void setTextCol(int paramInt)
  {
    this.mPaint.setColor(paramInt);
    this.mPaint2.setColor(paramInt);
    invalidate();
  }

  public void setmColorText(int paramInt)
  {
    this.mPaint2.setColor(paramInt);
    invalidate();
  }

  public void setmCurrentSelected(int paramInt)
  {
    this.mCurrentSelected = paramInt;
  }

  class MyTimerTask extends TimerTask
  {
    Handler handler;

    public MyTimerTask(Handler arg2)
    {
      Object localObject;
      this.handler = localObject;
    }

    public void run()
    {
      this.handler.sendMessage(this.handler.obtainMessage());
    }
  }

  public static abstract interface onSelectListener
  {
    public abstract void onSelect(String paramString);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyTimePickerView
 * JD-Core Version:    0.6.0
 */