package com.ex.ltech.led.my_view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import java.util.Formatter;
import java.util.Locale;
import org.adw.library.widgets.discreteseekbar.R.styleable;
import org.adw.library.widgets.discreteseekbar.internal.PopupIndicator;
import org.adw.library.widgets.discreteseekbar.internal.compat.AnimatorCompat;
import org.adw.library.widgets.discreteseekbar.internal.compat.AnimatorCompat.AnimationFrameUpdateListener;
import org.adw.library.widgets.discreteseekbar.internal.compat.SeekBarCompat;
import org.adw.library.widgets.discreteseekbar.internal.drawable.MarkerDrawable.MarkerAnimationListener;
import org.adw.library.widgets.discreteseekbar.internal.drawable.ThumbDrawable;
import org.adw.library.widgets.discreteseekbar.internal.drawable.TrackRectDrawable;

public class DiscreteSeekBar extends View
{
  private static final String DEFAULT_FORMATTER = "%d";
  private static final int DEFAULT_THUMB_COLOR = -16738680;
  private static final int FOCUSED_STATE = 16842908;
  private static final int INDICATOR_DELAY_FOR_TAPS = 150;
  private static final int PRESSED_STATE = 16842919;
  private static final int PROGRESS_ANIMATION_DURATION = 250;
  private static final int SEPARATION_DP = 5;
  private static final boolean isLollipopOrGreater;
  private int mAddedTouchBounds;
  private boolean mAllowTrackClick = true;
  private float mAnimationPosition;
  private int mAnimationTarget;
  private float mDownX;
  private int mDragOffset;
  private MarkerDrawable.MarkerAnimationListener mFloaterListener = new MarkerDrawable.MarkerAnimationListener()
  {
    public void onClosingComplete()
    {
      DiscreteSeekBar.this.mThumb.animateToNormal();
    }

    public void onOpeningComplete()
    {
    }
  };
  private StringBuilder mFormatBuilder;
  Formatter mFormatter;
  private PopupIndicator mIndicator;
  private String mIndicatorFormatter;
  private boolean mIndicatorPopupEnabled = false;
  private Rect mInvalidateRect = new Rect();
  private boolean mIsDragging;
  private int mKeyProgressIncrement = 1;
  private int mMax;
  private int mMin;
  private boolean mMirrorForRtl = false;
  private NumericTransformer mNumericTransformer;
  private AnimatorCompat mPositionAnimator;
  private OnProgressChangeListener mPublicChangeListener;
  private Drawable mRipple;
  private TrackRectDrawable mScrubber;
  private int mScrubberHeight;
  private Runnable mShowIndicatorRunnable = new Runnable()
  {
    public void run()
    {
      DiscreteSeekBar.this.showFloater();
    }
  };
  private Rect mTempRect = new Rect();
  private ThumbDrawable mThumb;
  private float mTouchSlop;
  private TrackRectDrawable mTrack;
  private int mTrackHeight;
  private int mValue;

  static
  {
    if (Build.VERSION.SDK_INT >= 21);
    for (boolean bool = true; ; bool = false)
    {
      isLollipopOrGreater = bool;
      return;
    }
  }

  public DiscreteSeekBar(Context paramContext)
  {
    this(paramContext, null);
  }

  public DiscreteSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 2130772176);
  }

  public DiscreteSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setFocusable(true);
    setWillNotDraw(false);
    this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    float f = paramContext.getResources().getDisplayMetrics().density;
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.DiscreteSeekBar, paramInt, 2131296560);
    int i = 100;
    this.mMirrorForRtl = localTypedArray.getBoolean(3, this.mMirrorForRtl);
    this.mAllowTrackClick = localTypedArray.getBoolean(4, this.mAllowTrackClick);
    this.mIndicatorPopupEnabled = localTypedArray.getBoolean(12, this.mIndicatorPopupEnabled);
    this.mTrackHeight = localTypedArray.getDimensionPixelSize(13, (int)(1.0F * f));
    this.mScrubberHeight = localTypedArray.getDimensionPixelSize(14, (int)(4.0F * f));
    int j = localTypedArray.getDimensionPixelSize(15, (int)(12.0F * f));
    int k = localTypedArray.getDimensionPixelSize(16, (int)(5.0F * f));
    this.mAddedTouchBounds = Math.max(0, ((int)(32.0F * f) - j) / 2);
    TypedValue localTypedValue = new TypedValue();
    int m;
    label324: int n;
    label360: ColorStateList localColorStateList1;
    ColorStateList localColorStateList2;
    boolean bool3;
    if (localTypedArray.getValue(1, localTypedValue))
    {
      if (localTypedValue.type == 5)
        i = localTypedArray.getDimensionPixelSize(1, i);
    }
    else
    {
      boolean bool1 = localTypedArray.getValue(0, localTypedValue);
      m = 0;
      if (bool1)
      {
        if (localTypedValue.type != 5)
          break label781;
        m = localTypedArray.getDimensionPixelSize(0, 0);
      }
      boolean bool2 = localTypedArray.getValue(2, localTypedValue);
      n = 0;
      if (bool2)
      {
        if (localTypedValue.type != 5)
          break label793;
        n = localTypedArray.getDimensionPixelSize(2, 0);
      }
      this.mMin = m;
      this.mMax = Math.max(m + 1, i);
      int i1 = Math.min(i, n);
      this.mValue = Math.max(m, i1);
      updateKeyboardRange();
      this.mIndicatorFormatter = localTypedArray.getString(10);
      localColorStateList1 = localTypedArray.getColorStateList(6);
      localColorStateList2 = localTypedArray.getColorStateList(5);
      ColorStateList localColorStateList3 = localTypedArray.getColorStateList(11);
      bool3 = isInEditMode();
      if ((bool3) || (localColorStateList3 == null))
      {
        int[][] arrayOfInt1 = { new int[0] };
        int[] arrayOfInt2 = { -12303292 };
        localColorStateList3 = new ColorStateList(arrayOfInt1, arrayOfInt2);
      }
      if ((bool3) || (localColorStateList1 == null))
      {
        int[][] arrayOfInt3 = { new int[0] };
        int[] arrayOfInt4 = { -7829368 };
        localColorStateList1 = new ColorStateList(arrayOfInt3, arrayOfInt4);
      }
      if ((bool3) || (localColorStateList2 == null))
      {
        int[][] arrayOfInt5 = { new int[0] };
        int[] arrayOfInt6 = { -16738680 };
        localColorStateList2 = new ColorStateList(arrayOfInt5, arrayOfInt6);
      }
      this.mRipple = SeekBarCompat.getRipple(localColorStateList3);
      if (!isLollipopOrGreater)
        break label805;
      SeekBarCompat.setBackground(this, this.mRipple);
    }
    while (true)
    {
      TrackRectDrawable localTrackRectDrawable1 = new TrackRectDrawable(localColorStateList1);
      this.mTrack = localTrackRectDrawable1;
      this.mTrack.setCallback(this);
      TrackRectDrawable localTrackRectDrawable2 = new TrackRectDrawable(localColorStateList2);
      this.mScrubber = localTrackRectDrawable2;
      this.mScrubber.setCallback(this);
      this.mThumb = new ThumbDrawable(localColorStateList2, j);
      this.mThumb.setCallback(this);
      this.mThumb.setBounds(0, 0, this.mThumb.getIntrinsicWidth(), this.mThumb.getIntrinsicHeight());
      if (!bool3)
      {
        this.mIndicator = new PopupIndicator(paramContext, paramAttributeSet, paramInt, convertValueToMessage(this.mMax), j, k + (j + this.mAddedTouchBounds));
        this.mIndicator.setListener(this.mFloaterListener);
      }
      localTypedArray.recycle();
      setNumericTransformer(new DefaultNumericTransformer(null));
      return;
      i = localTypedArray.getInteger(1, i);
      break;
      label781: m = localTypedArray.getInteger(0, 0);
      break label324;
      label793: n = localTypedArray.getInteger(2, 0);
      break label360;
      label805: this.mRipple.setCallback(this);
    }
  }

  private void attemptClaimDrag()
  {
    ViewParent localViewParent = getParent();
    if (localViewParent != null)
      localViewParent.requestDisallowInterceptTouchEvent(true);
  }

  private String convertValueToMessage(int paramInt)
  {
    String str;
    int i;
    if (this.mIndicatorFormatter != null)
    {
      str = this.mIndicatorFormatter;
      if ((this.mFormatter != null) && (this.mFormatter.locale().equals(Locale.getDefault())))
        break label137;
      i = str.length() + String.valueOf(this.mMax).length();
      if (this.mFormatBuilder != null)
        break label126;
      this.mFormatBuilder = new StringBuilder(i);
      label70: this.mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());
    }
    while (true)
    {
      Formatter localFormatter = this.mFormatter;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      return localFormatter.format(str, arrayOfObject).toString();
      str = "%d";
      break;
      label126: this.mFormatBuilder.ensureCapacity(i);
      break label70;
      label137: this.mFormatBuilder.setLength(0);
    }
  }

  private int getAnimatedProgress()
  {
    if (isAnimationRunning())
      return getAnimationTarget();
    return this.mValue;
  }

  private int getAnimationTarget()
  {
    return this.mAnimationTarget;
  }

  private void hideFloater()
  {
    removeCallbacks(this.mShowIndicatorRunnable);
    if (!isInEditMode())
    {
      this.mIndicator.dismiss();
      notifyBubble(false);
    }
  }

  private boolean isDragging()
  {
    return this.mIsDragging;
  }

  private boolean isInScrollingContainer()
  {
    return SeekBarCompat.isInScrollingContainer(getParent());
  }

  private void notifyBubble(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      onShowBubble();
      return;
    }
    onHideBubble();
  }

  private void notifyProgress(int paramInt, boolean paramBoolean)
  {
    if (this.mPublicChangeListener != null)
      this.mPublicChangeListener.onProgressChanged(this, paramInt, paramBoolean);
    onValueChanged(paramInt);
  }

  private void setHotspot(float paramFloat1, float paramFloat2)
  {
    DrawableCompat.setHotspot(this.mRipple, paramFloat1, paramFloat2);
  }

  private void setProgress(int paramInt, boolean paramBoolean)
  {
    int i = Math.max(this.mMin, Math.min(this.mMax, paramInt));
    if (isAnimationRunning())
      this.mPositionAnimator.cancel();
    if (this.mValue != i)
    {
      this.mValue = i;
      notifyProgress(i, paramBoolean);
      updateProgressMessage(i);
      updateThumbPosFromCurrentProgress();
    }
  }

  private void showFloater()
  {
    if (!isInEditMode())
    {
      this.mThumb.animateToPressed();
      this.mIndicator.showIndicator(this, this.mThumb.getBounds());
      notifyBubble(true);
    }
  }

  private boolean startDragging(MotionEvent paramMotionEvent, boolean paramBoolean)
  {
    Rect localRect = this.mTempRect;
    this.mThumb.copyBounds(localRect);
    localRect.inset(-this.mAddedTouchBounds, -this.mAddedTouchBounds);
    this.mIsDragging = localRect.contains((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    if ((!this.mIsDragging) && (this.mAllowTrackClick) && (!paramBoolean))
    {
      this.mIsDragging = true;
      this.mDragOffset = (localRect.width() / 2 - this.mAddedTouchBounds);
      updateDragging(paramMotionEvent);
      this.mThumb.copyBounds(localRect);
      localRect.inset(-this.mAddedTouchBounds, -this.mAddedTouchBounds);
    }
    if (this.mIsDragging)
    {
      setPressed(true);
      attemptClaimDrag();
      setHotspot(paramMotionEvent.getX(), paramMotionEvent.getY());
      this.mDragOffset = (int)(paramMotionEvent.getX() - localRect.left - this.mAddedTouchBounds);
      if (this.mPublicChangeListener != null)
        this.mPublicChangeListener.onStartTrackingTouch(this);
    }
    return this.mIsDragging;
  }

  private void stopDragging()
  {
    if (this.mPublicChangeListener != null)
      this.mPublicChangeListener.onStopTrackingTouch(this);
    this.mIsDragging = false;
    setPressed(false);
  }

  private void updateDragging(MotionEvent paramMotionEvent)
  {
    setHotspot(paramMotionEvent.getX(), paramMotionEvent.getY());
    int i = (int)paramMotionEvent.getX();
    int j = this.mThumb.getBounds().width() / 2;
    int k = this.mAddedTouchBounds;
    int m = j + (i - this.mDragOffset);
    int n = k + (j + getPaddingLeft());
    int i1 = getWidth() - (k + (j + getPaddingRight()));
    if (m < n)
      m = n;
    while (true)
    {
      int i2 = i1 - n;
      float f = (m - n) / i2;
      if (isRtl())
        f = 1.0F - f;
      setProgress(Math.round(f * (this.mMax - this.mMin) + this.mMin), true);
      return;
      if (m <= i1)
        continue;
      m = i1;
    }
  }

  private void updateFromDrawableState()
  {
    int[] arrayOfInt = getDrawableState();
    int i = 0;
    int j = 0;
    int k = arrayOfInt.length;
    int m = 0;
    if (m < k)
    {
      int n = arrayOfInt[m];
      if (n == 16842908)
        i = 1;
      while (true)
      {
        m++;
        break;
        if (n != 16842919)
          continue;
        j = 1;
      }
    }
    if ((isEnabled()) && ((i != 0) || (j != 0)) && (this.mIndicatorPopupEnabled))
    {
      removeCallbacks(this.mShowIndicatorRunnable);
      postDelayed(this.mShowIndicatorRunnable, 150L);
    }
    while (true)
    {
      this.mThumb.setState(arrayOfInt);
      this.mTrack.setState(arrayOfInt);
      this.mScrubber.setState(arrayOfInt);
      this.mRipple.setState(arrayOfInt);
      return;
      hideFloater();
    }
  }

  private void updateIndicatorSizes()
  {
    if (!isInEditMode())
    {
      if (this.mNumericTransformer.useStringTransform())
        this.mIndicator.updateSizes(this.mNumericTransformer.transformToString(this.mMax));
    }
    else
      return;
    this.mIndicator.updateSizes(convertValueToMessage(this.mNumericTransformer.transform(this.mMax)));
  }

  private void updateKeyboardRange()
  {
    int i = this.mMax - this.mMin;
    if ((this.mKeyProgressIncrement == 0) || (i / this.mKeyProgressIncrement > 20))
      this.mKeyProgressIncrement = Math.max(1, Math.round(i / 20.0F));
  }

  private void updateProgressFromAnimation(float paramFloat)
  {
    int i = this.mThumb.getBounds().width() / 2;
    int j = this.mAddedTouchBounds;
    int k = j + (i + getPaddingLeft());
    int m = getWidth() - (j + (i + getPaddingRight())) - k;
    int n = Math.round(paramFloat * (this.mMax - this.mMin) + this.mMin);
    if (n != getProgress())
    {
      this.mValue = n;
      notifyProgress(this.mValue, true);
      updateProgressMessage(n);
    }
    updateThumbPos((int)(0.5F + paramFloat * m));
  }

  private void updateProgressMessage(int paramInt)
  {
    if (!isInEditMode())
    {
      if (this.mNumericTransformer.useStringTransform())
        this.mIndicator.setValue(this.mNumericTransformer.transformToString(paramInt));
    }
    else
      return;
    this.mIndicator.setValue(convertValueToMessage(this.mNumericTransformer.transform(paramInt)));
  }

  private void updateThumbPos(int paramInt)
  {
    int i = this.mThumb.getIntrinsicWidth();
    int j = i / 2;
    int k;
    int m;
    if (isRtl())
    {
      k = getWidth() - getPaddingRight() - this.mAddedTouchBounds;
      m = k - paramInt - i;
      this.mThumb.copyBounds(this.mInvalidateRect);
      this.mThumb.setBounds(m, this.mInvalidateRect.top, m + i, this.mInvalidateRect.bottom);
      if (!isRtl())
        break label247;
      this.mScrubber.getBounds().right = (k - j);
      this.mScrubber.getBounds().left = (m + j);
    }
    while (true)
    {
      Rect localRect = this.mTempRect;
      this.mThumb.copyBounds(localRect);
      if (!isInEditMode())
        this.mIndicator.move(localRect.centerX());
      this.mInvalidateRect.inset(-this.mAddedTouchBounds, -this.mAddedTouchBounds);
      localRect.inset(-this.mAddedTouchBounds, -this.mAddedTouchBounds);
      this.mInvalidateRect.union(localRect);
      SeekBarCompat.setHotspotBounds(this.mRipple, localRect.left, localRect.top, localRect.right, localRect.bottom);
      invalidate(this.mInvalidateRect);
      return;
      k = getPaddingLeft() + this.mAddedTouchBounds;
      m = paramInt + k;
      break;
      label247: this.mScrubber.getBounds().left = (k + j);
      this.mScrubber.getBounds().right = (m + j);
    }
  }

  private void updateThumbPosFromCurrentProgress()
  {
    int i = this.mThumb.getIntrinsicWidth();
    int j = this.mAddedTouchBounds;
    int k = i / 2;
    float f = (this.mValue - this.mMin) / (this.mMax - this.mMin);
    int m = j + (k + getPaddingLeft());
    updateThumbPos((int)(0.5F + f * (getWidth() - (j + (k + getPaddingRight())) - m)));
  }

  void animateSetProgress(int paramInt)
  {
    float f;
    if (isAnimationRunning())
    {
      f = getAnimationPosition();
      if (paramInt >= this.mMin)
        break label89;
      paramInt = this.mMin;
    }
    while (true)
    {
      if (this.mPositionAnimator != null)
        this.mPositionAnimator.cancel();
      this.mAnimationTarget = paramInt;
      this.mPositionAnimator = AnimatorCompat.create(f, paramInt, new AnimatorCompat.AnimationFrameUpdateListener()
      {
        public void onAnimationFrame(float paramFloat)
        {
          DiscreteSeekBar.this.setAnimationPosition(paramFloat);
        }
      });
      this.mPositionAnimator.setDuration(250);
      this.mPositionAnimator.start();
      return;
      f = getProgress();
      break;
      label89: if (paramInt <= this.mMax)
        continue;
      paramInt = this.mMax;
    }
  }

  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    updateFromDrawableState();
  }

  float getAnimationPosition()
  {
    return this.mAnimationPosition;
  }

  public int getMax()
  {
    return this.mMax;
  }

  public int getMin()
  {
    return this.mMin;
  }

  public NumericTransformer getNumericTransformer()
  {
    return this.mNumericTransformer;
  }

  public int getProgress()
  {
    return this.mValue;
  }

  boolean isAnimationRunning()
  {
    return (this.mPositionAnimator != null) && (this.mPositionAnimator.isRunning());
  }

  public boolean isRtl()
  {
    return (ViewCompat.getLayoutDirection(this) == 1) && (this.mMirrorForRtl);
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    removeCallbacks(this.mShowIndicatorRunnable);
    if (!isInEditMode())
      this.mIndicator.dismissComplete();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    monitorenter;
    try
    {
      if (!isLollipopOrGreater)
        this.mRipple.draw(paramCanvas);
      super.onDraw(paramCanvas);
      this.mTrack.draw(paramCanvas);
      this.mScrubber.draw(paramCanvas);
      this.mThumb.draw(paramCanvas);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected void onHideBubble()
  {
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = isEnabled();
    int i = 0;
    int j;
    if (bool)
    {
      j = getAnimatedProgress();
      i = 0;
      switch (paramInt)
      {
      default:
      case 21:
      case 22:
      }
    }
    while ((i != 0) || (super.onKeyDown(paramInt, paramKeyEvent)))
    {
      return true;
      i = 1;
      if (j <= this.mMin)
        continue;
      animateSetProgress(j - this.mKeyProgressIncrement);
      continue;
      i = 1;
      if (j >= this.mMax)
        continue;
      animateSetProgress(j + this.mKeyProgressIncrement);
    }
    return false;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramBoolean)
    {
      removeCallbacks(this.mShowIndicatorRunnable);
      if (!isInEditMode())
        this.mIndicator.dismissComplete();
      updateFromDrawableState();
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), this.mThumb.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom() + 2 * this.mAddedTouchBounds);
  }

  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable == null) || (!paramParcelable.getClass().equals(CustomState.class)))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    CustomState localCustomState = (CustomState)paramParcelable;
    setMin(localCustomState.min);
    setMax(localCustomState.max);
    setProgress(localCustomState.progress, false);
    super.onRestoreInstanceState(localCustomState.getSuperState());
  }

  protected Parcelable onSaveInstanceState()
  {
    CustomState localCustomState = new CustomState(super.onSaveInstanceState());
    CustomState.access$302(localCustomState, getProgress());
    CustomState.access$402(localCustomState, this.mMax);
    CustomState.access$502(localCustomState, this.mMin);
    return localCustomState;
  }

  protected void onShowBubble()
  {
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    int i = this.mThumb.getIntrinsicWidth();
    int j = this.mThumb.getIntrinsicHeight();
    int k = this.mAddedTouchBounds;
    int m = i / 2;
    int n = k + getPaddingLeft();
    int i1 = getPaddingRight();
    int i2 = getHeight() - getPaddingBottom() - k;
    this.mThumb.setBounds(n, i2 - j, n + i, i2);
    int i3 = Math.max(this.mTrackHeight / 2, 1);
    this.mTrack.setBounds(n + m, i2 - m - i3, getWidth() - m - i1 - k, i3 + (i2 - m));
    int i4 = Math.max(this.mScrubberHeight / 2, 2);
    this.mScrubber.setBounds(n + m, i2 - m - i4, n + m, i4 + (i2 - m));
    updateThumbPosFromCurrentProgress();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!isEnabled())
      return false;
    switch (MotionEventCompat.getActionMasked(paramMotionEvent))
    {
    default:
    case 0:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      return true;
      this.mDownX = paramMotionEvent.getX();
      startDragging(paramMotionEvent, isInScrollingContainer());
      continue;
      if (isDragging())
      {
        updateDragging(paramMotionEvent);
        continue;
      }
      if (Math.abs(paramMotionEvent.getX() - this.mDownX) <= this.mTouchSlop)
        continue;
      startDragging(paramMotionEvent, false);
      continue;
      if ((!isDragging()) && (this.mAllowTrackClick))
      {
        startDragging(paramMotionEvent, false);
        updateDragging(paramMotionEvent);
      }
      stopDragging();
      continue;
      stopDragging();
    }
  }

  protected void onValueChanged(int paramInt)
  {
  }

  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    super.scheduleDrawable(paramDrawable, paramRunnable, paramLong);
  }

  void setAnimationPosition(float paramFloat)
  {
    this.mAnimationPosition = paramFloat;
    updateProgressFromAnimation((paramFloat - this.mMin) / (this.mMax - this.mMin));
  }

  public void setIndicatorFormatter(@Nullable String paramString)
  {
    this.mIndicatorFormatter = paramString;
    updateProgressMessage(this.mValue);
  }

  public void setIndicatorPopupEnabled(boolean paramBoolean)
  {
    this.mIndicatorPopupEnabled = paramBoolean;
  }

  public void setMax(int paramInt)
  {
    this.mMax = paramInt;
    if (this.mMax < this.mMin)
      setMin(-1 + this.mMax);
    updateKeyboardRange();
    if ((this.mValue < this.mMin) || (this.mValue > this.mMax))
      setProgress(this.mMin);
    updateIndicatorSizes();
  }

  public void setMin(int paramInt)
  {
    this.mMin = paramInt;
    if (this.mMin > this.mMax)
      setMax(1 + this.mMin);
    updateKeyboardRange();
    if ((this.mValue < this.mMin) || (this.mValue > this.mMax))
      setProgress(this.mMin);
  }

  public void setNumericTransformer(@Nullable NumericTransformer paramNumericTransformer)
  {
    if (paramNumericTransformer != null);
    while (true)
    {
      this.mNumericTransformer = paramNumericTransformer;
      updateIndicatorSizes();
      updateProgressMessage(this.mValue);
      return;
      paramNumericTransformer = new DefaultNumericTransformer(null);
    }
  }

  public void setOnProgressChangeListener(@Nullable OnProgressChangeListener paramOnProgressChangeListener)
  {
    this.mPublicChangeListener = paramOnProgressChangeListener;
  }

  public void setProgress(int paramInt)
  {
    setProgress(paramInt, false);
  }

  public void setRippleColor(int paramInt)
  {
    setRippleColor(new ColorStateList(new int[][] { new int[0] }, new int[] { paramInt }));
  }

  public void setRippleColor(@NonNull ColorStateList paramColorStateList)
  {
    SeekBarCompat.setRippleColor(this.mRipple, paramColorStateList);
  }

  public void setScrubberColor(int paramInt)
  {
    this.mScrubber.setColorStateList(ColorStateList.valueOf(paramInt));
  }

  public void setScrubberColor(@NonNull ColorStateList paramColorStateList)
  {
    this.mScrubber.setColorStateList(paramColorStateList);
  }

  public void setThumbColor(int paramInt1, int paramInt2)
  {
    this.mThumb.setColorStateList(ColorStateList.valueOf(paramInt1));
    this.mIndicator.setColors(paramInt2, paramInt1);
  }

  public void setThumbColor(@NonNull ColorStateList paramColorStateList, int paramInt)
  {
    this.mThumb.setColorStateList(paramColorStateList);
    int i = paramColorStateList.getColorForState(new int[] { 16842919 }, paramColorStateList.getDefaultColor());
    this.mIndicator.setColors(paramInt, i);
  }

  public void setTrackColor(int paramInt)
  {
    this.mTrack.setColorStateList(ColorStateList.valueOf(paramInt));
  }

  public void setTrackColor(@NonNull ColorStateList paramColorStateList)
  {
    this.mTrack.setColorStateList(paramColorStateList);
  }

  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (paramDrawable == this.mThumb) || (paramDrawable == this.mTrack) || (paramDrawable == this.mScrubber) || (paramDrawable == this.mRipple) || (super.verifyDrawable(paramDrawable));
  }

  static class CustomState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<CustomState> CREATOR = new Parcelable.Creator()
    {
      public DiscreteSeekBar.CustomState createFromParcel(Parcel paramParcel)
      {
        return new DiscreteSeekBar.CustomState(paramParcel);
      }

      public DiscreteSeekBar.CustomState[] newArray(int paramInt)
      {
        return new DiscreteSeekBar.CustomState[paramInt];
      }
    };
    private int max;
    private int min;
    private int progress;

    public CustomState(Parcel paramParcel)
    {
      super();
      this.progress = paramParcel.readInt();
      this.max = paramParcel.readInt();
      this.min = paramParcel.readInt();
    }

    public CustomState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.progress);
      paramParcel.writeInt(this.max);
      paramParcel.writeInt(this.min);
    }
  }

  private static class DefaultNumericTransformer extends DiscreteSeekBar.NumericTransformer
  {
    public int transform(int paramInt)
    {
      return paramInt;
    }
  }

  public static abstract class NumericTransformer
  {
    public abstract int transform(int paramInt);

    public String transformToString(int paramInt)
    {
      return String.valueOf(paramInt);
    }

    public boolean useStringTransform()
    {
      return false;
    }
  }

  public static abstract interface OnProgressChangeListener
  {
    public abstract void onProgressChanged(DiscreteSeekBar paramDiscreteSeekBar, int paramInt, boolean paramBoolean);

    public abstract void onStartTrackingTouch(DiscreteSeekBar paramDiscreteSeekBar);

    public abstract void onStopTrackingTouch(DiscreteSeekBar paramDiscreteSeekBar);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.DiscreteSeekBar
 * JD-Core Version:    0.6.0
 */