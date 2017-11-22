package com.ex.ltech.led.my_view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ex.ltech.led.R;

public class SecondArc extends View
{
  private static int INVALID_PROGRESS_VALUE;
  private static final String TAG = SecondArc.class.getSimpleName();
  private final int mAngleOffset = -90;
  private Paint mArcPaint;
  private int mArcRadius = 0;
  private RectF mArcRect = new RectF();
  private int mArcWidth = 2;
  private boolean mClockwise = true;
  private int mMax = 100;
  private OnSeekArcChangeListener mOnSeekArcChangeListener;
  private int mProgress = 0;
  private Paint mProgressPaint;
  private float mProgressSweep = 0.0F;
  private int mProgressWidth = 4;
  private int mRotation = 0;
  private boolean mRoundedEdges = false;
  private int mStartAngle = 0;
  private Paint mSunPaint;
  private RectF mSunRect = new RectF();
  private int mSweepAngle = 360;
  private int mThumbXPos;
  private int mThumbYPos;
  private double mTouchAngle;
  private float mTouchIgnoreRadius;
  private boolean mTouchInside = true;
  private int mTranslateX;
  private int mTranslateY;
  private int pColor = -1;

  static
  {
    INVALID_PROGRESS_VALUE = -1;
  }

  public SecondArc(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }

  public SecondArc(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }

  public SecondArc(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }

  private int getProgressForAngle(double paramDouble)
  {
    int i = (int)Math.round(paramDouble * valuePerDegree());
    if (i < 0)
      i = INVALID_PROGRESS_VALUE;
    if (i > this.mMax)
      i = INVALID_PROGRESS_VALUE;
    return i;
  }

  private double getTouchDegrees(float paramFloat1, float paramFloat2)
  {
    float f1 = paramFloat1 - this.mTranslateX;
    float f2 = paramFloat2 - this.mTranslateY;
    /*if (this.mClockwise);
    while (true)
    {
      double d = Math.toDegrees(1.570796326794897D + Math.atan2(f2, f1) - Math.toRadians(this.mRotation));
      if (d < 0.0D)
        d += 360.0D;
      return d - this.mStartAngle;
      f1 = -f1;
    }*/
    if (this.mClockwise);

    double d = Math.toDegrees(1.570796326794897D + Math.atan2(f2, f1) - Math.toRadians(this.mRotation));
      if (d < 0.0D)
        d += 360.0D;
    return d - this.mStartAngle;
  }

  private boolean ignoreTouch(float paramFloat1, float paramFloat2)
  {
    /*float f1 = paramFloat1 - this.mTranslateX;
    float f2 = paramFloat2 - this.mTranslateY;
    boolean bool = (float)Math.sqrt(f1 * f1 + f2 * f2) < this.mTouchIgnoreRadius;
    int i = 0;
    if (bool)
      i = 1;
    return i;*/
    return false;
  }

  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    Resources localResources = getResources();
    float f = paramContext.getResources().getDisplayMetrics().density;
    int i = localResources.getColor(R.color.progress_gray);
    int j = localResources.getColor(R.color.outlet_green);
    this.mProgressWidth = (int)(f * this.mProgressWidth);
    if (paramAttributeSet != null)
    {
      TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SeekArc);
      if (localTypedArray.getDrawable(0) != null);
      this.mMax = localTypedArray.getInteger(R.styleable.SeekArc_maxs, this.mMax);
      this.mProgress = localTypedArray.getInteger(R.styleable.SeekArc_progresss, this.mProgress);
      this.mProgressWidth = (int)localTypedArray.getDimension(R.styleable.SeekArc_progressWidth, this.mProgressWidth);
      this.mArcWidth = (int)localTypedArray.getDimension(R.styleable.SeekArc_arcWidth, this.mArcWidth);
      this.mStartAngle = localTypedArray.getInt(R.styleable.SeekArc_startAngle, this.mStartAngle);
      this.mSweepAngle = localTypedArray.getInt(R.styleable.SeekArc_sweepAngle, this.mSweepAngle);//8
      this.mRotation = localTypedArray.getInt(R.styleable.SeekArc_rotation, this.mRotation);
      this.mRoundedEdges = localTypedArray.getBoolean(R.styleable.SeekArc_roundEdges, this.mRoundedEdges);//11
      this.mTouchInside = localTypedArray.getBoolean(R.styleable.SeekArc_touchInside, this.mTouchInside);
      this.mClockwise = localTypedArray.getBoolean(R.styleable.SeekArc_clockwise, this.mClockwise);
      i = localTypedArray.getColor(R.styleable.SeekArc_arcColor, i);
      j = localTypedArray.getColor(R.styleable.SeekArc_progressColor, j);
      localTypedArray.recycle();
    }
    /*int k;
    int m;
    label284: int n;
    label305: int i1;
    label321: int i2;
    if (this.mProgress > this.mMax)
    {
      k = this.mMax;
      this.mProgress = k;
      if (this.mProgress >= 0)
        break label551;
      m = 0;
      this.mProgress = m;
      if (this.mSweepAngle <= 360)
        break label560;
      n = 360;
      this.mSweepAngle = n;
      if (this.mSweepAngle >= 0)
        break label569;
      i1 = 0;
      this.mSweepAngle = i1;
      if (this.mStartAngle <= 360)
        break label578;
      i2 = 0;
      label340: this.mStartAngle = i2;
      if (this.mStartAngle >= 0)
        break label587;
    }
    label551: label560: label569: label578: label587: for (int i3 = 0; ; i3 = this.mStartAngle)
    {
      this.mStartAngle = i3;
      this.mArcPaint = new Paint();
      this.mArcPaint.setColor(i);
      this.mArcPaint.setAntiAlias(true);
      this.mArcPaint.setStyle(Paint.Style.STROKE);
      this.mArcPaint.setStrokeWidth(this.mProgressWidth);
      this.mProgressPaint = new Paint();
      this.mProgressPaint.setColor(j);
      this.mProgressPaint.setAntiAlias(true);
      this.mProgressPaint.setStyle(Paint.Style.STROKE);
      this.mProgressPaint.setStrokeWidth(this.mProgressWidth);
      this.mSunPaint = new Paint();
      this.mSunPaint.setColor(j);
      this.mSunPaint.setAntiAlias(true);
      this.mSunPaint.setStyle(Paint.Style.STROKE);
      this.mSunPaint.setStrokeWidth(2 * this.mProgressWidth);
      if (this.mRoundedEdges)
      {
        this.mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
      }
      return;
      k = this.mProgress;
      break;
      m = this.mProgress;
      break label284;
      n = this.mSweepAngle;
      break label305;
      i1 = this.mSweepAngle;
      break label321;
      i2 = this.mStartAngle;
      break label340;
    }*/

    if (this.mProgress > this.mMax)
    {
      this.mProgress = this.mMax;
      if (this.mProgress >= 0)
        this.mProgress = 0;

      if (this.mSweepAngle <= 360)
        this.mSweepAngle = 360;

      if (this.mSweepAngle >= 0)
        this.mSweepAngle = 0;

      if (this.mStartAngle <= 360)
        this.mSweepAngle = 0;

      if (this.mStartAngle >= 0)
        this.mStartAngle = 0;
    }else {
      this.mStartAngle = 0;
      this.mArcPaint = new Paint();
      this.mArcPaint.setColor(i);
      this.mArcPaint.setAntiAlias(true);
      this.mArcPaint.setStyle(Paint.Style.STROKE);
      this.mArcPaint.setStrokeWidth(this.mProgressWidth);
      this.mProgressPaint = new Paint();
      this.mProgressPaint.setColor(j);
      this.mProgressPaint.setAntiAlias(true);
      this.mProgressPaint.setStyle(Paint.Style.STROKE);
      this.mProgressPaint.setStrokeWidth(this.mProgressWidth);
      this.mSunPaint = new Paint();
      this.mSunPaint.setColor(j);
      this.mSunPaint.setAntiAlias(true);
      this.mSunPaint.setStyle(Paint.Style.STROKE);
      this.mSunPaint.setStrokeWidth(2 * this.mProgressWidth);
      if (this.mRoundedEdges)
      {
        this.mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
      }
    }
  }

  private void onProgressRefresh(int paramInt, boolean paramBoolean)
  {
    updateProgress(paramInt, paramBoolean);
  }

  private void onStartTrackingTouch()
  {
    if (this.mOnSeekArcChangeListener != null)
      this.mOnSeekArcChangeListener.onStartTrackingTouch(this);
  }

  private void onStopTrackingTouch()
  {
    if (this.mOnSeekArcChangeListener != null)
      this.mOnSeekArcChangeListener.onStopTrackingTouch(this);
  }

  private void updateOnTouch(MotionEvent paramMotionEvent)
  {
    if (ignoreTouch(paramMotionEvent.getX(), paramMotionEvent.getY()))
      return;
    setPressed(true);
    this.mTouchAngle = getTouchDegrees(paramMotionEvent.getX(), paramMotionEvent.getY());
    onProgressRefresh(getProgressForAngle(this.mTouchAngle), true);
  }

  private void updateProgress(int paramInt, boolean paramBoolean)
  {
    if (paramInt == INVALID_PROGRESS_VALUE)
      return;
    if (this.mOnSeekArcChangeListener != null)
      this.mOnSeekArcChangeListener.onProgressChanged(this, paramInt, paramBoolean);
    if (paramInt > this.mMax)
      paramInt = this.mMax;
    if (this.mProgress < 0)
      paramInt = 0;
    this.mProgress = paramInt;
    this.mProgressSweep = (paramInt / this.mMax * this.mSweepAngle);
    updateThumbPosition();
    invalidate();
  }

  private void updateThumbPosition()
  {
    int i = (int)(90.0F + (this.mStartAngle + this.mProgressSweep + this.mRotation));
    this.mThumbXPos = (int)(this.mArcRadius * Math.cos(Math.toRadians(i)));
    this.mThumbYPos = (int)(this.mArcRadius * Math.sin(Math.toRadians(i)));
  }

  private float valuePerDegree()
  {
    return this.mMax / this.mSweepAngle;
  }

  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
  }

  public int getArcRotation()
  {
    return this.mRotation;
  }

  public int getArcWidth()
  {
    return this.mArcWidth;
  }

  public int getProgressWidth()
  {
    return this.mProgressWidth;
  }

  public int getStartAngle()
  {
    return this.mStartAngle;
  }

  public int getSweepAngle()
  {
    return this.mSweepAngle;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (!this.mClockwise)
      paramCanvas.scale(-1.0F, 1.0F, this.mArcRect.centerX(), this.mArcRect.centerY());
    int i = -90 + this.mStartAngle + this.mRotation;
    int j = this.mSweepAngle;
    paramCanvas.drawArc(this.mArcRect, i, j, false, this.mArcPaint);
    paramCanvas.drawArc(this.mArcRect, i, this.mProgressSweep, false, this.mProgressPaint);
    paramCanvas.translate(this.mTranslateX - this.mThumbXPos, this.mTranslateY - this.mThumbYPos);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getDefaultSize(getSuggestedMinimumHeight(), paramInt2);
    int j = getDefaultSize(getSuggestedMinimumWidth(), paramInt1);
    int k = Math.min(j, i);
    this.mTranslateX = (int)(0.5F * j);
    this.mTranslateY = (int)(0.5F * i);
    int m = k - getPaddingLeft();
    this.mArcRadius = (m / 2);
    float f1 = i / 2 - m / 2;
    float f2 = j / 2 - m / 2;
    this.mArcRect.set(f2, f1, f2 + m, f1 + m);
    this.mSunRect.set(f2 - 50.0F, f1 - 50.0F, 50.0F + (f2 + m), 50.0F + (f1 + m));
    int n = 90 + ((int)this.mProgressSweep + this.mStartAngle + this.mRotation);
    this.mThumbXPos = (int)(this.mArcRadius * Math.cos(Math.toRadians(n)));
    this.mThumbYPos = (int)(this.mArcRadius * Math.sin(Math.toRadians(n)));
    setTouchInSide(this.mTouchInside);
    super.onMeasure(paramInt1, paramInt2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    paramMotionEvent.getAction();
    return true;
  }

  public void setArcRotation(int paramInt)
  {
    this.mRotation = paramInt;
    updateThumbPosition();
  }

  public void setArcWidth(int paramInt)
  {
    this.mArcWidth = paramInt;
    this.mArcPaint.setStrokeWidth(paramInt);
  }

  public void setClockwise(boolean paramBoolean)
  {
    this.mClockwise = paramBoolean;
  }

  public void setOnSeekArcChangeListener(OnSeekArcChangeListener paramOnSeekArcChangeListener)
  {
    this.mOnSeekArcChangeListener = paramOnSeekArcChangeListener;
  }

  public void setProgessColor(int paramInt)
  {
    this.mProgressPaint.setColor(paramInt);
    this.mSunPaint.setColor(paramInt);
    invalidate();
  }

  public void setProgress(int paramInt)
  {
    updateProgress(paramInt, false);
  }

  public void setProgressSweep(int paramInt)
  {
    this.mProgressSweep = paramInt;
    invalidate();
  }

  public void setProgressWidth(int paramInt)
  {
    this.mProgressWidth = paramInt;
    this.mProgressPaint.setStrokeWidth(paramInt);
  }

  public void setRoundedEdges(boolean paramBoolean)
  {
    this.mRoundedEdges = paramBoolean;
    if (this.mRoundedEdges)
    {
      this.mArcPaint.setStrokeCap(Paint.Cap.ROUND);
      this.mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
      return;
    }
    this.mArcPaint.setStrokeCap(Paint.Cap.SQUARE);
    this.mProgressPaint.setStrokeCap(Paint.Cap.SQUARE);
  }

  public void setStartAngle(int paramInt)
  {
    this.mStartAngle = paramInt;
    updateThumbPosition();
  }

  public void setSweepAngle(int paramInt)
  {
    this.mSweepAngle = paramInt;
    updateThumbPosition();
  }

  public void setTouchInSide(boolean paramBoolean)
  {
    this.mTouchInside = paramBoolean;
    if (this.mTouchInside)
      this.mTouchIgnoreRadius = (this.mArcRadius / 4.0F);
  }

  public static abstract interface OnSeekArcChangeListener
  {
    public abstract void onProgressChanged(SecondArc paramSecondArc, int paramInt, boolean paramBoolean);

    public abstract void onStartTrackingTouch(SecondArc paramSecondArc);

    public abstract void onStopTrackingTouch(SecondArc paramSecondArc);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.SecondArc
 * JD-Core Version:    0.6.0
 */