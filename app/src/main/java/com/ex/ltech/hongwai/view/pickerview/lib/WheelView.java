package com.ex.ltech.hongwai.view.pickerview.lib;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.ex.ltech.hongwai.view.pickerview.adapter.WheelAdapter;
import com.ex.ltech.hongwai.view.pickerview.listener.OnItemSelectedListener;
import com.ex.ltech.hongwai.view.pickerview.model.IPickerViewData;
import com.ex.ltech.led.R.styleable;
import java.io.PrintStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WheelView extends View
{
  private static final float CENTERCONTENTOFFSET = 6.0F;
  private static final float SCALECONTENT = 0.8F;
  private static final int VELOCITYFLING = 5;
  static final float lineSpacingMultiplier = 1.4F;
  WheelAdapter adapter;
  float centerY;
  int change;
  Context context;
  boolean customTextSize = getResources().getBoolean(2131361799);
  int dividerColor = getResources().getColor(2131492968);
  private int drawCenterContentStart = 0;
  private int drawOutContentStart = 0;
  float firstLineY;
  private GestureDetector gestureDetector;
  int halfCircumference;
  Handler handler;
  int initPosition;
  boolean isLoop;
  float itemHeight;
  int itemsVisible = 11;
  private String label;
  ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();
  private ScheduledFuture<?> mFuture;
  private int mGravity = 17;
  private int mOffset = 0;
  int maxTextHeight;
  int maxTextWidth;
  int measuredHeight;
  int measuredWidth;
  OnItemSelectedListener onItemSelectedListener;
  Paint paintCenterText;
  Paint paintIndicator;
  Paint paintOuterText;
  int preCurrentIndex;
  private float previousY = 0.0F;
  int radius;
  float secondLineY;
  private int selectedItem;
  long startTime = 0L;
  int textColorCenter = getResources().getColor(2131492967);
  int textColorOut = getResources().getColor(2131492969);
  int textSize = getResources().getDimensionPixelSize(2131230791);
  int totalScrollY;
  int widthMeasureSpec;

  public WheelView(Context paramContext)
  {
    this(paramContext, null);
  }

  public WheelView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (paramAttributeSet != null)
    {
      TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.pickerview, 0, 0);
      this.mGravity = localTypedArray.getInt(0, 17);
      this.textColorOut = localTypedArray.getColor(2, this.textColorOut);
      this.textColorCenter = localTypedArray.getColor(3, this.textColorCenter);
      this.dividerColor = localTypedArray.getColor(4, this.dividerColor);
      this.textSize = localTypedArray.getDimensionPixelOffset(1, this.textSize);
    }
    initLoopView(paramContext);
  }

  private String getContentText(Object paramObject)
  {
    if (paramObject == null)
      return "";
    if ((paramObject instanceof IPickerViewData))
      return ((IPickerViewData)paramObject).getPickerViewText();
    return paramObject.toString();
  }

  private int getLoopMappingIndex(int paramInt)
  {
    if (paramInt < 0)
      paramInt = getLoopMappingIndex(paramInt + this.adapter.getItemsCount());
    do
      return paramInt;
    while (paramInt <= -1 + this.adapter.getItemsCount());
    return getLoopMappingIndex(paramInt - this.adapter.getItemsCount());
  }

  private void initLoopView(Context paramContext)
  {
    this.context = paramContext;
    this.handler = new MessageHandler(this);
    this.gestureDetector = new GestureDetector(paramContext, new LoopViewGestureListener(this));
    this.gestureDetector.setIsLongpressEnabled(false);
    this.isLoop = true;
    this.totalScrollY = 0;
    this.initPosition = -1;
    initPaints();
  }

  private void initPaints()
  {
    this.paintOuterText = new Paint();
    this.paintOuterText.setColor(this.textColorOut);
    this.paintOuterText.setAntiAlias(true);
    this.paintOuterText.setTypeface(Typeface.MONOSPACE);
    this.paintOuterText.setTextSize(this.textSize);
    this.paintCenterText = new Paint();
    this.paintCenterText.setColor(this.textColorCenter);
    this.paintCenterText.setAntiAlias(true);
    this.paintCenterText.setTextScaleX(1.1F);
    this.paintCenterText.setTypeface(Typeface.MONOSPACE);
    this.paintCenterText.setTextSize(this.textSize);
    this.paintIndicator = new Paint();
    this.paintIndicator.setColor(this.dividerColor);
    this.paintIndicator.setAntiAlias(true);
    if (Build.VERSION.SDK_INT >= 11)
      setLayerType(1, null);
  }

  private void measureTextWidthHeight()
  {
    Rect localRect = new Rect();
    for (int i = 0; i < this.adapter.getItemsCount(); i++)
    {
      String str = getContentText(this.adapter.getItem(i));
      this.paintCenterText.getTextBounds(str, 0, str.length(), localRect);
      int j = localRect.width();
      if (j > this.maxTextWidth)
        this.maxTextWidth = j;
      this.paintCenterText.getTextBounds("星期", 0, 2, localRect);
      int k = localRect.height();
      if (k <= this.maxTextHeight)
        continue;
      this.maxTextHeight = k;
    }
    this.itemHeight = (1.4F * this.maxTextHeight);
  }

  private void measuredCenterContentStart(String paramString)
  {
    Rect localRect = new Rect();
    this.paintCenterText.getTextBounds(paramString, 0, paramString.length(), localRect);
    switch (this.mGravity)
    {
    default:
      return;
    case 17:
      this.drawCenterContentStart = (int)(0.5D * (this.measuredWidth - localRect.width()));
      return;
    case 3:
      this.drawCenterContentStart = 0;
      return;
    case 5:
    }
    this.drawCenterContentStart = (this.measuredWidth - localRect.width());
  }

  private void measuredOutContentStart(String paramString)
  {
    Rect localRect = new Rect();
    this.paintOuterText.getTextBounds(paramString, 0, paramString.length(), localRect);
    switch (this.mGravity)
    {
    default:
      return;
    case 17:
      this.drawOutContentStart = (int)(0.5D * (this.measuredWidth - localRect.width()));
      return;
    case 3:
      this.drawOutContentStart = 0;
      return;
    case 5:
    }
    this.drawOutContentStart = (this.measuredWidth - localRect.width());
  }

  private void remeasure()
  {
    if (this.adapter == null)
      return;
    measureTextWidthHeight();
    this.halfCircumference = (int)(this.itemHeight * (-1 + this.itemsVisible));
    this.measuredHeight = (int)(2 * this.halfCircumference / 3.141592653589793D);
    this.radius = (int)(this.halfCircumference / 3.141592653589793D);
    this.measuredWidth = View.MeasureSpec.getSize(this.widthMeasureSpec);
    this.firstLineY = ((this.measuredHeight - this.itemHeight) / 2.0F);
    this.secondLineY = ((this.measuredHeight + this.itemHeight) / 2.0F);
    this.centerY = ((this.measuredHeight + this.maxTextHeight) / 2.0F - 6.0F);
    if (this.initPosition == -1)
      if (!this.isLoop)
        break label162;
    label162: for (this.initPosition = ((1 + this.adapter.getItemsCount()) / 2); ; this.initPosition = 0)
    {
      this.preCurrentIndex = this.initPosition;
      return;
    }
  }

  public void cancelFuture()
  {
    if ((this.mFuture != null) && (!this.mFuture.isCancelled()))
    {
      this.mFuture.cancel(true);
      this.mFuture = null;
    }
  }

  public final WheelAdapter getAdapter()
  {
    return this.adapter;
  }

  public final int getCurrentItem()
  {
    return this.selectedItem;
  }

  public int getItemsCount()
  {
    if (this.adapter != null)
      return this.adapter.getItemsCount();
    return 0;
  }

  public int getTextWidth(Paint paramPaint, String paramString)
  {
    int i = 0;
    if (paramString != null)
    {
      int j = paramString.length();
      i = 0;
      if (j > 0)
      {
        int k = paramString.length();
        float[] arrayOfFloat = new float[k];
        paramPaint.getTextWidths(paramString, arrayOfFloat);
        for (int m = 0; m < k; m++)
          i += (int)Math.ceil(arrayOfFloat[m]);
      }
    }
    return i;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.adapter == null);
    Object[] arrayOfObject;
    label317: int k;
    float f1;
    double d;
    while (true)
    {
      return;
      arrayOfObject = new Object[this.itemsVisible];
      this.change = (int)(this.totalScrollY / this.itemHeight);
      int i;
      try
      {
        this.preCurrentIndex = (this.initPosition + this.change % this.adapter.getItemsCount());
        if (!this.isLoop)
        {
          if (this.preCurrentIndex < 0)
            this.preCurrentIndex = 0;
          if (this.preCurrentIndex > -1 + this.adapter.getItemsCount())
            this.preCurrentIndex = (-1 + this.adapter.getItemsCount());
          i = (int)(this.totalScrollY % this.itemHeight);
          for (j = 0; ; j++)
          {
            if (j >= this.itemsVisible)
              break label317;
            i1 = this.preCurrentIndex - (this.itemsVisible / 2 - j);
            if (!this.isLoop)
              break;
            int i2 = getLoopMappingIndex(i1);
            arrayOfObject[j] = this.adapter.getItem(i2);
          }
        }
      }
      catch (ArithmeticException localArithmeticException)
      {
        while (true)
        {
          int j;
          int i1;
          System.out.println("出错了！adapter.getItemsCount() == 0，联动数据不匹配");
          continue;
          if (this.preCurrentIndex < 0)
            this.preCurrentIndex = (this.adapter.getItemsCount() + this.preCurrentIndex);
          if (this.preCurrentIndex <= -1 + this.adapter.getItemsCount())
            continue;
          this.preCurrentIndex -= this.adapter.getItemsCount();
          continue;
          if (i1 < 0)
          {
            arrayOfObject[j] = "";
            continue;
          }
          if (i1 > -1 + this.adapter.getItemsCount())
          {
            arrayOfObject[j] = "";
            continue;
          }
          arrayOfObject[j] = this.adapter.getItem(i1);
        }
        paramCanvas.drawLine(0.0F, this.firstLineY, this.measuredWidth, this.firstLineY, this.paintIndicator);
        paramCanvas.drawLine(0.0F, this.secondLineY, this.measuredWidth, this.secondLineY, this.paintIndicator);
        if (this.label != null)
        {
          int n = this.measuredWidth - getTextWidth(this.paintCenterText, this.label);
          paramCanvas.drawText(this.label, n - 6.0F, this.centerY, this.paintCenterText);
        }
        k = 0;
      }
      while (k < this.itemsVisible)
      {
        paramCanvas.save();
        f1 = 1.4F * this.maxTextHeight;
        d = 3.141592653589793D * (f1 * k - i) / this.halfCircumference;
        float f2 = (float)(90.0D - 180.0D * (d / 3.141592653589793D));
        if ((f2 < 90.0F) && (f2 > -90.0F))
          break label504;
        paramCanvas.restore();
        k++;
      }
    }
    label504: String str = getContentText(arrayOfObject[k]);
    measuredCenterContentStart(str);
    measuredOutContentStart(str);
    float f3 = (float)(this.radius - Math.cos(d) * this.radius - Math.sin(d) * this.maxTextHeight / 2.0D);
    paramCanvas.translate(0.0F, f3);
    paramCanvas.scale(1.0F, (float)Math.sin(d));
    if ((f3 <= this.firstLineY) && (f3 + this.maxTextHeight >= this.firstLineY))
    {
      paramCanvas.save();
      paramCanvas.clipRect(0.0F, 0.0F, this.measuredWidth, this.firstLineY - f3);
      paramCanvas.scale(1.0F, 0.8F * (float)Math.sin(d));
      paramCanvas.drawText(str, this.drawOutContentStart, this.maxTextHeight, this.paintOuterText);
      paramCanvas.restore();
      paramCanvas.save();
      paramCanvas.clipRect(0.0F, this.firstLineY - f3, this.measuredWidth, (int)f1);
      paramCanvas.scale(1.0F, 1.0F * (float)Math.sin(d));
      paramCanvas.drawText(str, this.drawCenterContentStart, this.maxTextHeight - 6.0F, this.paintCenterText);
      paramCanvas.restore();
    }
    while (true)
    {
      paramCanvas.restore();
      break;
      if ((f3 <= this.secondLineY) && (f3 + this.maxTextHeight >= this.secondLineY))
      {
        paramCanvas.save();
        paramCanvas.clipRect(0.0F, 0.0F, this.measuredWidth, this.secondLineY - f3);
        paramCanvas.scale(1.0F, 1.0F * (float)Math.sin(d));
        paramCanvas.drawText(str, this.drawCenterContentStart, this.maxTextHeight - 6.0F, this.paintCenterText);
        paramCanvas.restore();
        paramCanvas.save();
        paramCanvas.clipRect(0.0F, this.secondLineY - f3, this.measuredWidth, (int)f1);
        paramCanvas.scale(1.0F, 0.8F * (float)Math.sin(d));
        paramCanvas.drawText(str, this.drawOutContentStart, this.maxTextHeight, this.paintOuterText);
        paramCanvas.restore();
        continue;
      }
      if ((f3 >= this.firstLineY) && (f3 + this.maxTextHeight <= this.secondLineY))
      {
        paramCanvas.clipRect(0, 0, this.measuredWidth, (int)f1);
        paramCanvas.drawText(str, this.drawCenterContentStart, this.maxTextHeight - 6.0F, this.paintCenterText);
        int m = this.adapter.indexOf(arrayOfObject[k]);
        if (m == -1)
          continue;
        this.selectedItem = m;
        continue;
      }
      paramCanvas.save();
      paramCanvas.clipRect(0, 0, this.measuredWidth, (int)f1);
      paramCanvas.scale(1.0F, 0.8F * (float)Math.sin(d));
      paramCanvas.drawText(str, this.drawOutContentStart, this.maxTextHeight, this.paintOuterText);
      paramCanvas.restore();
    }
  }

  protected final void onItemSelected()
  {
    if (this.onItemSelectedListener != null)
      postDelayed(new OnItemSelectedRunnable(this), 200L);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.widthMeasureSpec = paramInt1;
    remeasure();
    setMeasuredDimension(this.measuredWidth, this.measuredHeight);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = this.gestureDetector.onTouchEvent(paramMotionEvent);
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
      if (bool)
        break;
      float f4 = paramMotionEvent.getY();
      int i = (int)((Math.acos((this.radius - f4) / this.radius) * this.radius + this.itemHeight / 2.0F) / this.itemHeight);
      float f5 = (this.totalScrollY % this.itemHeight + this.itemHeight) % this.itemHeight;
      this.mOffset = (int)((i - this.itemsVisible / 2) * this.itemHeight - f5);
      if (System.currentTimeMillis() - this.startTime > 120L)
        smoothScroll(ACTION.DAGGLE);
    case 0:
    case 2:
    }
    while (true)
    {
      invalidate();
      return true;
      this.startTime = System.currentTimeMillis();
      cancelFuture();
      this.previousY = paramMotionEvent.getRawY();
      continue;
      float f1 = this.previousY - paramMotionEvent.getRawY();
      this.previousY = paramMotionEvent.getRawY();
      this.totalScrollY = (int)(f1 + this.totalScrollY);
      if (this.isLoop)
        continue;
      float f2 = -this.initPosition * this.itemHeight;
      float f3 = (-1 + this.adapter.getItemsCount() - this.initPosition) * this.itemHeight;
      if (this.totalScrollY - 0.3D * this.itemHeight < f2)
        f2 = this.totalScrollY - f1;
      while (true)
      {
        if (this.totalScrollY >= f2)
          break label346;
        this.totalScrollY = (int)f2;
        break;
        if (this.totalScrollY + 0.3D * this.itemHeight <= f3)
          continue;
        f3 = this.totalScrollY - f1;
      }
      label346: if (this.totalScrollY <= f3)
        continue;
      this.totalScrollY = (int)f3;
      continue;
      smoothScroll(ACTION.CLICK);
    }
  }

  protected final void scrollBy(float paramFloat)
  {
    cancelFuture();
    this.mFuture = this.mExecutor.scheduleWithFixedDelay(new InertiaTimerTask(this, paramFloat), 0L, 5L, TimeUnit.MILLISECONDS);
  }

  public final void setAdapter(WheelAdapter paramWheelAdapter)
  {
    this.adapter = paramWheelAdapter;
    remeasure();
    invalidate();
  }

  public final void setCurrentItem(int paramInt)
  {
    this.initPosition = paramInt;
    this.totalScrollY = 0;
    invalidate();
  }

  public final void setCyclic(boolean paramBoolean)
  {
    this.isLoop = paramBoolean;
  }

  public void setGravity(int paramInt)
  {
    this.mGravity = paramInt;
  }

  public void setLabel(String paramString)
  {
    this.label = paramString;
  }

  public final void setOnItemSelectedListener(OnItemSelectedListener paramOnItemSelectedListener)
  {
    this.onItemSelectedListener = paramOnItemSelectedListener;
  }

  public final void setTextSize(float paramFloat)
  {
    if ((paramFloat > 0.0F) && (!this.customTextSize))
    {
      this.textSize = (int)(paramFloat * this.context.getResources().getDisplayMetrics().density);
      this.paintOuterText.setTextSize(this.textSize);
      this.paintCenterText.setTextSize(this.textSize);
    }
  }

  void smoothScroll(ACTION paramACTION)
  {
    cancelFuture();
    if ((paramACTION == ACTION.FLING) || (paramACTION == ACTION.DAGGLE))
    {
      this.mOffset = (int)((this.totalScrollY % this.itemHeight + this.itemHeight) % this.itemHeight);
      if (this.mOffset <= this.itemHeight / 2.0F)
        break label106;
    }
    label106: for (this.mOffset = (int)(this.itemHeight - this.mOffset); ; this.mOffset = (-this.mOffset))
    {
      this.mFuture = this.mExecutor.scheduleWithFixedDelay(new SmoothScrollTimerTask(this, this.mOffset), 0L, 10L, TimeUnit.MILLISECONDS);
      return;
    }
  }

  public static enum ACTION
  {
    static
    {
      DAGGLE = new ACTION("DAGGLE", 2);
      ACTION[] arrayOfACTION = new ACTION[3];
      arrayOfACTION[0] = CLICK;
      arrayOfACTION[1] = FLING;
      arrayOfACTION[2] = DAGGLE;
      $VALUES = arrayOfACTION;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.lib.WheelView
 * JD-Core Version:    0.6.0
 */