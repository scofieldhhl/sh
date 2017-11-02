package com.ex.ltech.led.my_view.gallery_view;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.v4.util.LruCache;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.Scroller;
import com.ex.ltech.led.R.styleable;
import java.io.PrintStream;
import java.util.ArrayList;

public class CoverFlowView<T extends CoverFlowAdapter> extends View
{
  private static final float CARD_SCALE = 0.15F;
  private static final int DURATION = 200;
  private static final float FRICTION = 10.0F;
  private static final int LONG_CLICK_DELAY = 0;
  private static final float MAX_SPEED = 6.0F;
  private static float MOVE_POS_MULTIPLE = 0.0F;
  private static final float MOVE_SPEED_MULTIPLE = 1.0F;
  static final int NO_POSITION = -1;
  private static final int TOUCH_MINIMUM_MOVE = 5;
  private final int ALPHA_DATUM = 76;
  protected final int CHILD_SPACING = -200;
  protected final int INVALID_POSITION = -1;
  private int STANDARD_ALPHA;
  protected int VISIBLE_VIEWS = 3;
  private T mAdapter;
  private Runnable mAnimationRunnable;
  private int mChildHeight;
  private Matrix mChildTransfromer;
  private int mChildTranslateY;
  protected int mCoverFlowCenter;
  private CoverFlowListener<T> mCoverFlowListener;
  private Rect mCoverFlowPadding;
  boolean mDataChanged;
  private Paint mDrawChildPaint;
  private PaintFlagsDrawFilter mDrawFilter;
  private boolean mDrawing;
  private float mDuration;
  protected CoverFlowGravity mGravity;
  private SparseArray<int[]> mImageRecorder;
  private int mItemCount;
  private int mLastOffset;
  protected CoverFlowLayoutMode mLayoutMode;
  private TopImageLongClickListener mLongClickListener;
  private boolean mLongClickPosted;
  private CoverFlowView<T>.LongClickRunnable mLongClickRunnable;
  private boolean mLongClickTriggled;
  private float mOffset;
  private CoverFlowView<T>.RecycleBin mRecycler;
  private Matrix mReflectionTransfromer;
  private int mReflectionTranslateY;
  private ArrayList<Integer> mRemoveReflectionPendingArray;
  private Scroller mScroller;
  private float mStartOffset;
  private float mStartSpeed;
  private long mStartTime;
  private int mTopImageIndex;
  private boolean mTouchMoved;
  private RectF mTouchRect;
  private float mTouchStartPos;
  private float mTouchStartX;
  private float mTouchStartY;
  private VelocityTracker mVelocity;
  private int mVisibleChildCount;
  private int mWidth;
  OnCoverFlowViewTouchEvent onCoverFlowViewTouchEvent;
  private int reflectGap;
  private float reflectHeightFraction;
  private boolean topImageClickEnable = true;

  static
  {
    LONG_CLICK_DELAY = ViewConfiguration.getLongPressTimeout();
  }

  public CoverFlowView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public CoverFlowView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initAttributes(paramContext, paramAttributeSet);
    init();
  }

  public CoverFlowView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initAttributes(paramContext, paramAttributeSet);
    init();
  }

  private void driveAnimation()
  {
    float f = (float)(AnimationUtils.currentAnimationTimeMillis() - this.mStartTime) / 1000.0F;
    if (f >= this.mDuration)
    {
      endAnimation();
      return;
    }
    updateAnimationAtElapsed(f);
    post(this.mAnimationRunnable);
  }

  private void endAnimation()
  {
    if (this.mAnimationRunnable != null)
    {
      this.mOffset = (float)Math.floor(0.5D + this.mOffset);
      invalidate();
      removeCallbacks(this.mAnimationRunnable);
      this.mAnimationRunnable = null;
    }
  }

  private int getActuallyPosition(int paramInt)
  {
    if (this.mAdapter == null)
      return -1;
    int i = this.mAdapter.getCount();
    int j = paramInt + this.VISIBLE_VIEWS;
    while ((j < 0) || (j >= i))
    {
      if (j < 0)
      {
        j += i;
        continue;
      }
      if (j < i)
        continue;
      j -= i;
    }
    return j;
  }

  private void imageOnTop(int paramInt)
  {
    this.mTopImageIndex = paramInt;
    int[] arrayOfInt = (int[])this.mImageRecorder.get(paramInt);
    (int)((int)(this.mChildHeight - this.mChildHeight * this.reflectHeightFraction - this.reflectGap) / arrayOfInt[1] * arrayOfInt[0]);
    this.mTouchRect.left = (-270 + (this.mWidth >> 1));
    this.mTouchRect.top = this.mChildTranslateY;
    this.mTouchRect.right = (this.mTouchRect.left + 540);
    this.mTouchRect.bottom = (this.mTouchRect.top + 652);
    if (this.mCoverFlowListener != null)
      this.mCoverFlowListener.imageOnTop(this, paramInt, this.mTouchRect.left, this.mTouchRect.top, this.mTouchRect.right, this.mTouchRect.bottom);
  }

  private void init()
  {
    setWillNotDraw(false);
    setClickable(true);
    this.mChildTransfromer = new Matrix();
    this.mReflectionTransfromer = new Matrix();
    this.mTouchRect = new RectF();
    this.mImageRecorder = new SparseArray();
    this.mDrawChildPaint = new Paint();
    this.mDrawChildPaint.setAntiAlias(true);
    this.mDrawChildPaint.setFlags(1);
    this.mCoverFlowPadding = new Rect();
    this.mDrawFilter = new PaintFlagsDrawFilter(0, 3);
    this.mScroller = new Scroller(getContext(), new AccelerateDecelerateInterpolator());
    this.mRemoveReflectionPendingArray = new ArrayList();
  }

  private void initAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ImageCoverFlowView);
    int i = localTypedArray.getInt(0, 2);
    if (i % 2 == 0)
      throw new IllegalArgumentException("visible image must be an odd number");
    this.VISIBLE_VIEWS = (i >> 1);
    this.reflectHeightFraction = localTypedArray.getFraction(1, 100, 0, 0.0F);
    if (this.reflectHeightFraction > 100.0F)
      this.reflectHeightFraction = 100.0F;
    this.reflectHeightFraction /= 100.0F;
    this.reflectGap = localTypedArray.getDimensionPixelSize(2, 0);
    this.mGravity = CoverFlowGravity.values()[localTypedArray.getInt(4, CoverFlowGravity.CENTER_VERTICAL.ordinal())];
    this.mLayoutMode = CoverFlowLayoutMode.values()[localTypedArray.getInt(5, CoverFlowLayoutMode.WRAP_CONTENT.ordinal())];
    localTypedArray.recycle();
  }

  private void makeChildTransfromer(Bitmap paramBitmap, int paramInt, float paramFloat)
  {
    this.mChildTransfromer.reset();
    this.mReflectionTransfromer.reset();
    float f1;
    int j;
    float f3;
    int k;
    int i1;
    float f4;
    if (paramInt != 0)
    {
      f1 = 1.0F - 0.25F * Math.abs(paramFloat);
      int i = (int)(this.mChildHeight - this.mChildHeight * this.reflectHeightFraction - this.reflectGap);
      j = (int)(paramBitmap.getHeight() + paramBitmap.getHeight() * this.reflectHeightFraction + this.reflectGap);
      float f2 = i / paramBitmap.getHeight();
      f3 = f2 * f1;
      k = (int)(f3 * paramBitmap.getWidth());
      int m = (int)(f2 * paramBitmap.getWidth());
      int n = (this.mWidth >> 1) - this.mCoverFlowPadding.left - (m >> 1);
      i1 = (this.mWidth >> 1) - this.mCoverFlowPadding.right - (m >> 1);
      if (paramFloat > 0.0F)
        break label404;
      f4 = n / this.VISIBLE_VIEWS * (paramFloat + this.VISIBLE_VIEWS) + this.mCoverFlowPadding.left;
    }
    while (true)
    {
      this.mChildTransfromer.preTranslate(0.0F, -(j >> 1));
      this.mChildTransfromer.postScale(f3, f3);
      boolean bool = f3 < 1.0F;
      float f5 = 0.0F;
      if (bool)
        f5 = this.mChildHeight - j >> 1;
      Matrix localMatrix1 = this.mChildTransfromer;
      float f6 = f5 + this.mChildTranslateY;
      localMatrix1.postTranslate(f4, f6);
      getCustomTransformMatrix(this.mChildTransfromer, this.mDrawChildPaint, paramBitmap, paramInt, paramFloat);
      this.mChildTransfromer.postTranslate(0.0F, j >> 1);
      this.mReflectionTransfromer.preTranslate(0.0F, -(j >> 1));
      this.mReflectionTransfromer.postScale(f3, f3);
      Matrix localMatrix2 = this.mReflectionTransfromer;
      float f7 = f5 + f1 * this.mReflectionTranslateY;
      localMatrix2.postTranslate(f4, f7);
      getCustomTransformMatrix(this.mReflectionTransfromer, this.mDrawChildPaint, paramBitmap, paramInt, paramFloat);
      this.mReflectionTransfromer.postTranslate(0.0F, j >> 1);
      return;
      f1 = 1.0F - 0.15F * Math.abs(paramFloat);
      break;
      label404: f4 = this.mWidth - i1 / this.VISIBLE_VIEWS * (this.VISIBLE_VIEWS - paramFloat) - k - this.mCoverFlowPadding.right;
    }
  }

  private Bitmap obtainReflection(int paramInt, Bitmap paramBitmap)
  {
    Bitmap localBitmap;
    if (this.reflectHeightFraction <= 0.0F)
      localBitmap = null;
    do
    {
      do
      {
        return localBitmap;
        localBitmap = this.mRecycler.getCachedBitmap(paramInt);
      }
      while ((localBitmap != null) && (!localBitmap.isRecycled()));
      this.mRecycler.removeCachedBitmap(paramInt);
      localBitmap = BitmapUtils.createReflectedBitmap(paramBitmap, this.reflectHeightFraction);
    }
    while (localBitmap == null);
    this.mRecycler.addBitmap2Cache(paramInt, localBitmap);
    return localBitmap;
  }

  private void resetList()
  {
    if (this.mRecycler != null)
      this.mRecycler.clear();
    this.mChildHeight = 0;
    this.mOffset = 0.0F;
    this.mLastOffset = -1;
    this.STANDARD_ALPHA = (179 / this.VISIBLE_VIEWS);
    if (this.mGravity == null)
      this.mGravity = CoverFlowGravity.CENTER_VERTICAL;
    if (this.mLayoutMode == null)
      this.mLayoutMode = CoverFlowLayoutMode.WRAP_CONTENT;
    this.mImageRecorder.clear();
  }

  private void startAnimation(double paramDouble)
  {
    if (this.mAnimationRunnable != null)
      return;
    double d1 = paramDouble * paramDouble / 20.0D;
    if (paramDouble < 0.0D)
      d1 = -d1;
    double d2 = Math.floor(0.5D + (d1 + this.mStartOffset));
    this.mStartSpeed = (float)Math.sqrt(2.0D * (10.0D * Math.abs(d2 - this.mStartOffset)));
    if (d2 < this.mStartOffset)
      this.mStartSpeed = (-this.mStartSpeed);
    this.mDuration = Math.abs(this.mStartSpeed / 10.0F);
    this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
    this.mAnimationRunnable = new Runnable()
    {
      public void run()
      {
        CoverFlowView.this.driveAnimation();
      }
    };
    post(this.mAnimationRunnable);
  }

  private void stopLongClick()
  {
    if (this.mLongClickRunnable != null)
    {
      removeCallbacks(this.mLongClickRunnable);
      this.mLongClickPosted = false;
      this.mLongClickTriggled = false;
    }
  }

  private void touchBegan(MotionEvent paramMotionEvent)
  {
    endAnimation();
    float f = paramMotionEvent.getX();
    this.mTouchStartX = f;
    this.mTouchStartY = paramMotionEvent.getY();
    this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
    this.mStartOffset = this.mOffset;
    this.mTouchMoved = false;
    this.mTouchStartPos = (f / this.mWidth * MOVE_POS_MULTIPLE - 5.0F);
    this.mTouchStartPos /= 2.0F;
    this.mVelocity = VelocityTracker.obtain();
    this.mVelocity.addMovement(paramMotionEvent);
  }

  private void touchEnded(MotionEvent paramMotionEvent)
  {
    float f = (paramMotionEvent.getX() / this.mWidth * MOVE_POS_MULTIPLE - 5.0F) / 2.0F;
    double d;
    if ((this.mTouchMoved) || (this.mOffset - Math.floor(this.mOffset) != 0.0D))
    {
      this.mStartOffset += this.mTouchStartPos - f;
      this.mOffset = this.mStartOffset;
      this.mVelocity.addMovement(paramMotionEvent);
      this.mVelocity.computeCurrentVelocity(1000);
      d = 1.0D * (this.mVelocity.getXVelocity() / this.mWidth);
      if (d > 6.0D)
      {
        d = 6.0D;
        startAnimation(-d);
      }
    }
    while (true)
    {
      this.mVelocity.clear();
      this.mVelocity.recycle();
      return;
      if (d >= -6.0D)
        break;
      d = -6.0D;
      break;
      if ((this.mTouchRect == null) || (!this.mTouchRect.contains(paramMotionEvent.getX(), paramMotionEvent.getY())) || (this.mCoverFlowListener == null) || (!this.topImageClickEnable) || (this.mLongClickTriggled))
        continue;
      int i = this.mTopImageIndex;
      this.mCoverFlowListener.topImageClicked(this, i);
    }
  }

  private void touchMoved(MotionEvent paramMotionEvent)
  {
    float f1 = (paramMotionEvent.getX() / this.mWidth * MOVE_POS_MULTIPLE - 5.0F) / 2.0F;
    if (!this.mTouchMoved)
    {
      float f2 = Math.abs(paramMotionEvent.getX() - this.mTouchStartX);
      float f3 = Math.abs(paramMotionEvent.getY() - this.mTouchStartY);
      if ((f2 < 5.0F) && (f3 < 5.0F))
        return;
      this.mTouchMoved = true;
      stopLongClick();
    }
    this.mOffset = (this.mStartOffset + this.mTouchStartPos - f1);
    invalidate();
    this.mVelocity.addMovement(paramMotionEvent);
  }

  private void triggleLongClick(float paramFloat1, float paramFloat2)
  {
    if ((this.mTouchRect.contains(paramFloat1, paramFloat2)) && (this.mLongClickListener != null) && (this.topImageClickEnable) && (!this.mLongClickPosted))
    {
      int i = this.mTopImageIndex;
      this.mLongClickRunnable.setPosition(i);
      postDelayed(this.mLongClickRunnable, LONG_CLICK_DELAY);
    }
  }

  private void updateAnimationAtElapsed(float paramFloat)
  {
    if (paramFloat > this.mDuration)
      paramFloat = this.mDuration;
    float f = paramFloat * Math.abs(this.mStartSpeed) - paramFloat * (10.0F * paramFloat) / 2.0F;
    if (this.mStartSpeed < 0.0F)
      f = -f;
    this.mOffset = (f + this.mStartOffset);
    invalidate();
  }

  public void computeScroll()
  {
    super.computeScroll();
    if (this.mScroller.computeScrollOffset())
    {
      this.mOffset = (this.mScroller.getCurrX() / 100.0F);
      invalidate();
    }
  }

  float convert(float paramFloat)
  {
    return Math.abs(paramFloat) - (float)Math.floor(Math.abs(paramFloat));
  }

  public void disableTopImageClick()
  {
    this.topImageClickEnable = false;
  }

  protected final void drawChild(Canvas paramCanvas, int paramInt, float paramFloat)
  {
    int i = getActuallyPosition(paramInt);
    System.out.println("        mOffset " + convert(paramFloat));
    Bitmap localBitmap = this.mAdapter.getImage(i);
    int[] arrayOfInt1 = (int[])this.mImageRecorder.get(i);
    if (arrayOfInt1 == null)
    {
      int[] arrayOfInt2 = new int[2];
      arrayOfInt2[0] = localBitmap.getWidth();
      arrayOfInt2[1] = localBitmap.getHeight();
      this.mImageRecorder.put(i, arrayOfInt2);
    }
    while (true)
    {
      if ((localBitmap != null) && (!localBitmap.isRecycled()) && (paramCanvas != null))
      {
        makeChildTransfromer(localBitmap, paramInt, paramFloat);
        paramCanvas.drawBitmap(localBitmap, this.mChildTransfromer, this.mDrawChildPaint);
      }
      return;
      arrayOfInt1[0] = localBitmap.getWidth();
      arrayOfInt1[1] = localBitmap.getHeight();
    }
  }

  public void enableTopImageClick()
  {
    this.topImageClickEnable = true;
  }

  public T getAdapter()
  {
    return this.mAdapter;
  }

  protected void getCustomTransformMatrix(Matrix paramMatrix, Paint paramPaint, Bitmap paramBitmap, int paramInt, float paramFloat)
  {
  }

  public void invalidatePosition(int paramInt)
  {
    if ((this.mAdapter == null) || (paramInt < 0));
    while (true)
    {
      return;
      if (paramInt >= this.mAdapter.getCount())
        continue;
      if (!this.mDrawing)
        this.mRecycler.removeCachedBitmap(paramInt);
      while ((paramInt >= this.mTopImageIndex - this.VISIBLE_VIEWS) && (paramInt <= this.mTopImageIndex + this.VISIBLE_VIEWS))
      {
        invalidate();
        return;
        if (this.mRemoveReflectionPendingArray.contains(Integer.valueOf(paramInt)))
          continue;
        this.mRemoveReflectionPendingArray.add(Integer.valueOf(paramInt));
      }
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.mAdapter == null)
    {
      super.onDraw(paramCanvas);
      return;
    }
    this.mDrawing = true;
    paramCanvas.setDrawFilter(this.mDrawFilter);
    float f = this.mOffset;
    int i = (int)Math.floor(0.5D + f);
    if (this.mVisibleChildCount % 2 == 0);
    for (int j = -1 + (this.mVisibleChildCount >> 1); ; j = this.mVisibleChildCount >> 1)
      for (int k = i - (this.mVisibleChildCount >> 1); k < i; k++)
        drawChild(paramCanvas, k, k - f);
    for (int m = i + j; m >= i; m--)
      drawChild(paramCanvas, m, m - f);
    if (this.mLastOffset != (int)f)
    {
      imageOnTop(getActuallyPosition((int)f));
      this.mLastOffset = (int)f;
    }
    this.mDrawing = false;
    int n = this.mRemoveReflectionPendingArray.size();
    for (int i1 = 0; i1 < n; i1++)
    {
      int i2 = ((Integer)this.mRemoveReflectionPendingArray.get(i1)).intValue();
      this.mRecycler.removeCachedBitmap(i2);
    }
    this.mRemoveReflectionPendingArray.clear();
    super.onDraw(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mAdapter == null)
      return;
    this.mCoverFlowPadding.left = getPaddingLeft();
    this.mCoverFlowPadding.right = getPaddingRight();
    this.mCoverFlowPadding.top = getPaddingTop();
    this.mCoverFlowPadding.bottom = getPaddingBottom();
    int i = View.MeasureSpec.getMode(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    int k = View.MeasureSpec.getSize(paramInt2);
    int m = 1 + (this.VISIBLE_VIEWS << 1);
    int n = k - this.mCoverFlowPadding.top - this.mCoverFlowPadding.bottom;
    int i1 = 0;
    for (int i2 = 0; i2 < m; i2++)
    {
      int i3 = this.mAdapter.getImage(i2).getHeight();
      int i4 = (int)(i3 + i3 * this.reflectHeightFraction + this.reflectGap);
      if (i1 >= i4)
        continue;
      i1 = i4;
    }
    if ((i == 1073741824) || (i == -2147483648))
      if (n < i1)
      {
        this.mChildHeight = n;
        if (this.mGravity != CoverFlowGravity.CENTER_VERTICAL)
          break label396;
        this.mChildTranslateY = ((k >> 1) - (this.mChildHeight >> 1));
      }
    while (true)
    {
      this.mReflectionTranslateY = (int)(this.mChildTranslateY + this.mChildHeight - this.mChildHeight * this.reflectHeightFraction);
      setMeasuredDimension(j, k);
      this.mVisibleChildCount = m;
      this.mWidth = j;
      return;
      if (this.mLayoutMode == CoverFlowLayoutMode.MATCH_PARENT)
      {
        this.mChildHeight = n;
        break;
      }
      if (this.mLayoutMode != CoverFlowLayoutMode.WRAP_CONTENT)
        break;
      this.mChildHeight = i1;
      if (i != -2147483648)
        break;
      k = this.mChildHeight + this.mCoverFlowPadding.top + this.mCoverFlowPadding.bottom;
      break;
      if (this.mLayoutMode == CoverFlowLayoutMode.MATCH_PARENT)
      {
        this.mChildHeight = n;
        break;
      }
      if (this.mLayoutMode != CoverFlowLayoutMode.WRAP_CONTENT)
        break;
      this.mChildHeight = i1;
      k = this.mChildHeight + this.mCoverFlowPadding.top + this.mCoverFlowPadding.bottom;
      break;
      label396: if (this.mGravity == CoverFlowGravity.TOP)
      {
        this.mChildTranslateY = this.mCoverFlowPadding.top;
        continue;
      }
      if (this.mGravity != CoverFlowGravity.BOTTOM)
        continue;
      this.mChildTranslateY = (k - this.mCoverFlowPadding.bottom - this.mChildHeight);
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (getParent() != null)
      getParent().requestDisallowInterceptTouchEvent(true);
    switch (paramMotionEvent.getAction())
    {
    default:
      return false;
    case 0:
      if (this.onCoverFlowViewTouchEvent != null)
        this.onCoverFlowViewTouchEvent.actionDown();
      if (this.mScroller.computeScrollOffset())
      {
        this.mScroller.abortAnimation();
        invalidate();
      }
      stopLongClick();
      triggleLongClick(paramMotionEvent.getX(), paramMotionEvent.getY());
      touchBegan(paramMotionEvent);
      return true;
    case 2:
      touchMoved(paramMotionEvent);
      return true;
    case 1:
    }
    if (this.onCoverFlowViewTouchEvent != null)
      this.onCoverFlowViewTouchEvent.actionUp();
    touchEnded(paramMotionEvent);
    stopLongClick();
    return true;
  }

  public void setAdapter(T paramT)
  {
    this.mAdapter = paramT;
    if (this.mAdapter != null)
    {
      this.mItemCount = this.mAdapter.getCount();
      if (this.mItemCount < 1 + (this.VISIBLE_VIEWS << 1))
        throw new IllegalArgumentException("total count in adapter must larger than visible images!");
      this.mRecycler = new RecycleBin();
    }
    resetList();
    requestLayout();
  }

  public void setCoverFlowGravity(CoverFlowGravity paramCoverFlowGravity)
  {
    this.mGravity = paramCoverFlowGravity;
  }

  public void setCoverFlowLayoutMode(CoverFlowLayoutMode paramCoverFlowLayoutMode)
  {
    this.mLayoutMode = paramCoverFlowLayoutMode;
  }

  public void setCoverFlowListener(CoverFlowListener<T> paramCoverFlowListener)
  {
    this.mCoverFlowListener = paramCoverFlowListener;
  }

  public void setOnCoverFlowViewTouchEvent(OnCoverFlowViewTouchEvent paramOnCoverFlowViewTouchEvent)
  {
    this.onCoverFlowViewTouchEvent = paramOnCoverFlowViewTouchEvent;
  }

  public void setReflectionGap(int paramInt)
  {
    if (paramInt < 0)
      paramInt = 0;
    this.reflectGap = paramInt;
  }

  public void setReflectionHeight(int paramInt)
  {
    if (paramInt < 0)
      paramInt = 0;
    while (true)
    {
      this.reflectHeightFraction = paramInt;
      return;
      if (paramInt <= 100)
        continue;
      paramInt = 100;
    }
  }

  public void setSelection(int paramInt)
  {
    int i = this.mAdapter.getCount();
    if ((paramInt < 0) || (paramInt >= i))
      throw new IllegalArgumentException("Position want to select can not less than 0 or larger than max of adapter provide!");
    if (this.mTopImageIndex != paramInt)
    {
      if (this.mScroller.computeScrollOffset())
        this.mScroller.abortAnimation();
      int j = (int)(100.0F * this.mOffset);
      int k = 100 * (paramInt - this.VISIBLE_VIEWS) - j;
      this.mScroller.startScroll(j, 0, k, 0, 200 * Math.min(Math.abs(paramInt + i - this.mTopImageIndex), Math.abs(paramInt - this.mTopImageIndex)));
      invalidate();
    }
  }

  public void setTopImageLongClickListener(TopImageLongClickListener paramTopImageLongClickListener)
  {
    this.mLongClickListener = paramTopImageLongClickListener;
    if (paramTopImageLongClickListener == null)
      this.mLongClickRunnable = null;
    do
      return;
    while (this.mLongClickRunnable != null);
    this.mLongClickRunnable = new LongClickRunnable(null);
  }

  public void setVisibleImage(int paramInt)
  {
    if (paramInt % 2 == 0)
      throw new IllegalArgumentException("visible image must be an odd number");
    this.VISIBLE_VIEWS = (paramInt / 2);
    this.STANDARD_ALPHA = (179 / this.VISIBLE_VIEWS);
  }

  public static enum CoverFlowGravity
  {
    static
    {
      BOTTOM = new CoverFlowGravity("BOTTOM", 1);
      CENTER_VERTICAL = new CoverFlowGravity("CENTER_VERTICAL", 2);
      CoverFlowGravity[] arrayOfCoverFlowGravity = new CoverFlowGravity[3];
      arrayOfCoverFlowGravity[0] = TOP;
      arrayOfCoverFlowGravity[1] = BOTTOM;
      arrayOfCoverFlowGravity[2] = CENTER_VERTICAL;
      $VALUES = arrayOfCoverFlowGravity;
    }
  }

  public static enum CoverFlowLayoutMode
  {
    static
    {
      CoverFlowLayoutMode[] arrayOfCoverFlowLayoutMode = new CoverFlowLayoutMode[2];
      arrayOfCoverFlowLayoutMode[0] = MATCH_PARENT;
      arrayOfCoverFlowLayoutMode[1] = WRAP_CONTENT;
      $VALUES = arrayOfCoverFlowLayoutMode;
    }
  }

  public static abstract interface CoverFlowListener<V extends CoverFlowAdapter>
  {
    public abstract void imageOnTop(CoverFlowView<V> paramCoverFlowView, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);

    public abstract void invalidationCompleted();

    public abstract void topImageClicked(CoverFlowView<V> paramCoverFlowView, int paramInt);
  }

  private class LongClickRunnable
    implements Runnable
  {
    private int position;

    private LongClickRunnable()
    {
    }

    public void run()
    {
      if (CoverFlowView.this.mLongClickListener != null)
      {
        CoverFlowView.this.mLongClickListener.onLongClick(this.position);
        CoverFlowView.access$302(CoverFlowView.this, true);
      }
    }

    public void setPosition(int paramInt)
    {
      this.position = paramInt;
    }
  }

  public static abstract interface OnCoverFlowViewTouchEvent
  {
    public abstract void actionDown();

    public abstract void actionUp();
  }

  class RecycleBin
  {

    @SuppressLint({"NewApi"})
    final LruCache<Integer, Bitmap> bitmapCache = new LruCache(getCacheSize(CoverFlowView.this.getContext()))
    {
      protected void entryRemoved(boolean paramBoolean, Integer paramInteger, Bitmap paramBitmap1, Bitmap paramBitmap2)
      {
        if ((paramBoolean) && (paramBitmap1 != null) && (!paramBitmap1.isRecycled()))
          paramBitmap1.recycle();
      }

      protected int sizeOf(Integer paramInteger, Bitmap paramBitmap)
      {
        if (Build.VERSION.SDK_INT < 12)
          return paramBitmap.getRowBytes() * paramBitmap.getHeight();
        return paramBitmap.getByteCount();
      }
    };

    RecycleBin()
    {
    }

    private int getCacheSize(Context paramContext)
    {
      return 1048576 * ((ActivityManager)paramContext.getSystemService("activity")).getMemoryClass() / 21;
    }

    public void addBitmap2Cache(int paramInt, Bitmap paramBitmap)
    {
      this.bitmapCache.put(Integer.valueOf(paramInt), paramBitmap);
      Runtime.getRuntime().gc();
    }

    public void clear()
    {
      this.bitmapCache.evictAll();
    }

    public Bitmap getCachedBitmap(int paramInt)
    {
      return (Bitmap)this.bitmapCache.get(Integer.valueOf(paramInt));
    }

    public Bitmap removeCachedBitmap(int paramInt)
    {
      if ((paramInt < 0) || (paramInt >= this.bitmapCache.size()))
        return null;
      return (Bitmap)this.bitmapCache.remove(Integer.valueOf(paramInt));
    }
  }

  public static abstract interface TopImageLongClickListener
  {
    public abstract void onLongClick(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.gallery_view.CoverFlowView
 * JD-Core Version:    0.6.0
 */