package com.ex.ltech.onepiontfive.main.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ex.ltech.led.R.styleable;
import java.util.Locale;

public class EasySlidingTabs extends HorizontalScrollView
{
  private static final int[] ATTRS = { 16842901, 16842904 };
  private int currentPosition = 0;
  private float currentPositionOffset = 0.0F;
  private LinearLayout.LayoutParams defaultTabLayoutParams;
  public ViewPager.OnPageChangeListener delegatePageListener;
  private int dividerColor = 436207616;
  private int dividerPadding = 12;
  private Paint dividerPaint;
  private int dividerWidth = 1;
  private LinearLayout.LayoutParams expandedTabLayoutParams;
  private int indicatorColor = -49023;
  private Drawable indicatorDrawable;
  private int indicatorHeight = 10;
  private boolean isTabsAdded = false;
  private int lastScrollX = 0;
  private Locale locale;
  private int mMyUnderlinePadding = 12;
  MyPageChangeListener myPageChangeListener;
  private final PageListener pageListener = new PageListener(null);
  private ViewPager pager;
  private Paint rectPaint;
  private int scrollOffset = 52;
  private int selectedPosition = 0;
  private int selectedTabTextColor = -49023;
  private boolean shouldExpand = false;
  private int tabBackgroundResId = 2130837568;
  private int tabCount;
  private int tabPadding = 2;
  private int tabTextColor = -13615201;
  private int tabTextSize = 12;
  private Typeface tabTypeface = null;
  private int tabTypefaceStyle = 0;
  private int tabWidth;
  private LinearLayout tabsContainer;
  private boolean textAllCaps = true;
  private int underlineColor = -2236963;
  private int underlineHeight = 1;
  private int width;

  public EasySlidingTabs(Context paramContext)
  {
    this(paramContext, null);
  }

  public EasySlidingTabs(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }

  public EasySlidingTabs(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }

  private void addIconTab(int paramInt1, int paramInt2)
  {
    ImageButton localImageButton = new ImageButton(getContext());
    localImageButton.setImageResource(paramInt2);
    addTab(paramInt1, localImageButton);
  }

  private void addTab(int paramInt, View paramView)
  {
    int i = 5;
    paramView.setFocusable(true);
    paramView.setOnClickListener(new View.OnClickListener(paramInt)
    {
      public void onClick(View paramView)
      {
        EasySlidingTabs.this.pager.setCurrentItem(this.val$position);
      }
    });
    int j;
    if (this.defaultTabLayoutParams == null)
    {
      if (this.width == 0)
        this.width = getWidth();
      j = this.width;
      if (this.tabCount <= i)
        break label98;
    }
    while (true)
    {
      this.tabWidth = (j / i);
      this.defaultTabLayoutParams = new LinearLayout.LayoutParams(this.tabWidth, -1);
      paramView.setLayoutParams(this.defaultTabLayoutParams);
      this.tabsContainer.addView(paramView, paramInt);
      return;
      label98: i = this.tabCount;
    }
  }

  private void addTabs()
  {
    this.isTabsAdded = true;
    this.tabsContainer.removeAllViews();
    this.tabCount = this.pager.getAdapter().getCount();
    int i = 0;
    if (i < this.tabCount)
    {
      if ((this.pager.getAdapter() instanceof IconTabProvider))
        addIconTab(i, ((IconTabProvider)this.pager.getAdapter()).getPageIconResId(i));
      while (true)
      {
        i++;
        break;
        PagerAdapter localPagerAdapter = this.pager.getAdapter();
        if ((localPagerAdapter instanceof TabsTitleInterface))
        {
          TabsTitleInterface localTabsTitleInterface = (TabsTitleInterface)localPagerAdapter;
          addTextTab(i, localTabsTitleInterface.getTabTitle(i), localTabsTitleInterface.getTabDrawableLeft(i), localTabsTitleInterface.getTabDrawableTop(i), localTabsTitleInterface.getTabDrawableRight(i), localTabsTitleInterface.getTabDrawableBottom(i));
          continue;
        }
        addTextTab(i, localPagerAdapter.getPageTitle(i).toString());
      }
    }
    updateTabStyles();
    getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        EasySlidingTabs.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        EasySlidingTabs.access$102(EasySlidingTabs.this, EasySlidingTabs.this.pager.getCurrentItem());
        EasySlidingTabs.this.scrollToChild(EasySlidingTabs.this.currentPosition, 0);
      }
    });
  }

  private void addTextTab(int paramInt1, SpannableString paramSpannableString, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    TextView localTextView = new TextView(getContext());
    localTextView.setText(paramSpannableString);
    localTextView.setGravity(17);
    localTextView.setCompoundDrawablesWithIntrinsicBounds(paramInt2, paramInt3, paramInt4, paramInt5);
    localTextView.setLineSpacing(8.0F, 1.0F);
    addTab(paramInt1, localTextView);
  }

  private void addTextTab(int paramInt, String paramString)
  {
    TextView localTextView = new TextView(getContext());
    localTextView.setText(paramString);
    localTextView.setGravity(17);
    localTextView.setLineSpacing(5.0F, 1.0F);
    addTab(paramInt, localTextView);
  }

  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    setHorizontalScrollBarEnabled(false);
    setFillViewport(true);
    setWillNotDraw(false);
    this.tabsContainer = new LinearLayout(paramContext);
    this.tabsContainer.setOrientation(0);
    this.tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    addView(this.tabsContainer);
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    this.scrollOffset = (int)TypedValue.applyDimension(1, this.scrollOffset, localDisplayMetrics);
    this.underlineHeight = (int)TypedValue.applyDimension(1, this.underlineHeight, localDisplayMetrics);
    this.dividerPadding = (int)TypedValue.applyDimension(1, this.dividerPadding, localDisplayMetrics);
    this.tabPadding = (int)TypedValue.applyDimension(1, this.tabPadding, localDisplayMetrics);
    this.dividerWidth = (int)TypedValue.applyDimension(1, this.dividerWidth, localDisplayMetrics);
    this.tabTextSize = (int)TypedValue.applyDimension(2, this.tabTextSize, localDisplayMetrics);
    TypedArray localTypedArray1 = paramContext.obtainStyledAttributes(paramAttributeSet, ATTRS);
    this.tabTextSize = localTypedArray1.getDimensionPixelSize(0, this.tabTextSize);
    this.tabTextColor = localTypedArray1.getColor(1, this.tabTextColor);
    localTypedArray1.recycle();
    TypedArray localTypedArray2 = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.EasySlidingTabs);
    this.indicatorColor = localTypedArray2.getColor(0, this.indicatorColor);
    this.underlineColor = localTypedArray2.getColor(1, this.underlineColor);
    this.tabTextColor = localTypedArray2.getColor(2, this.tabTextColor);
    this.selectedTabTextColor = localTypedArray2.getColor(3, this.selectedTabTextColor);
    this.dividerColor = localTypedArray2.getColor(4, this.dividerColor);
    this.underlineHeight = localTypedArray2.getDimensionPixelSize(6, this.underlineHeight);
    this.dividerPadding = localTypedArray2.getDimensionPixelSize(7, this.dividerPadding);
    this.tabPadding = localTypedArray2.getDimensionPixelSize(8, this.tabPadding);
    this.tabBackgroundResId = localTypedArray2.getResourceId(9, this.tabBackgroundResId);
    this.scrollOffset = localTypedArray2.getDimensionPixelSize(10, this.scrollOffset);
    this.shouldExpand = localTypedArray2.getBoolean(11, this.shouldExpand);
    this.textAllCaps = localTypedArray2.getBoolean(12, this.textAllCaps);
    this.indicatorDrawable = localTypedArray2.getDrawable(13);
    this.mMyUnderlinePadding = (int)TypedValue.applyDimension(1, this.mMyUnderlinePadding, localDisplayMetrics);
    localTypedArray2.recycle();
    this.rectPaint = new Paint();
    this.rectPaint.setAntiAlias(true);
    this.rectPaint.setStyle(Paint.Style.FILL);
    this.dividerPaint = new Paint();
    this.dividerPaint.setAntiAlias(true);
    this.dividerPaint.setStrokeWidth(this.dividerWidth);
    this.expandedTabLayoutParams = new LinearLayout.LayoutParams(0, -1, 1.0F);
    if (this.locale == null)
      this.locale = getResources().getConfiguration().locale;
  }

  private void scrollToChild(int paramInt1, int paramInt2)
  {
    if (this.tabCount == 0);
    int i;
    do
    {
      return;
      this.scrollOffset = (getWidth() / 2 - this.tabWidth / 2);
      i = paramInt2 + this.tabsContainer.getChildAt(paramInt1).getLeft();
      if ((paramInt1 <= 0) && (paramInt2 <= 0))
        continue;
      i -= this.scrollOffset;
    }
    while (i == this.lastScrollX);
    this.lastScrollX = i;
    scrollTo(i, 0);
  }

  private void setTabsValue()
  {
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    setShouldExpand(true);
    setDividerColor(0);
    setUnderlineHeight((int)TypedValue.applyDimension(1, this.underlineHeight, localDisplayMetrics));
    setIndicatorHeight((int)TypedValue.applyDimension(1, this.indicatorHeight, localDisplayMetrics));
    setTextSize((int)TypedValue.applyDimension(2, 16.0F, localDisplayMetrics));
    setIndicatorColor(this.indicatorColor);
    setSelectedTextColor(this.selectedTabTextColor);
    setUnderlineColor(this.underlineColor);
    setTabBackground(0);
  }

  private void updateTabStyles()
  {
    PagerAdapter localPagerAdapter = this.pager.getAdapter();
    if ((localPagerAdapter instanceof TabsTitleInterface))
    {
      int j = 0;
      if (j < this.tabCount)
      {
        View localView2 = this.tabsContainer.getChildAt(j);
        localView2.setBackgroundResource(this.tabBackgroundResId);
        TextView localTextView2;
        SpannableString localSpannableString;
        if ((localView2 instanceof TextView))
        {
          localTextView2 = (TextView)localView2;
          localSpannableString = ((TabsTitleInterface)localPagerAdapter).getTabTitle(j);
          if (j != this.selectedPosition)
            break label121;
          localSpannableString.setSpan(new ForegroundColorSpan(this.selectedTabTextColor), 0, localSpannableString.length(), 17);
          localTextView2.setText(localSpannableString);
        }
        while (true)
        {
          j++;
          break;
          label121: localSpannableString.setSpan(new ForegroundColorSpan(this.tabTextColor), 0, localSpannableString.length(), 17);
          localTextView2.setText(localSpannableString);
        }
      }
    }
    else
    {
      int i = 0;
      if (i < this.tabCount)
      {
        View localView1 = this.tabsContainer.getChildAt(i);
        localView1.setBackgroundResource(this.tabBackgroundResId);
        TextView localTextView1;
        if ((localView1 instanceof TextView))
        {
          localTextView1 = (TextView)localView1;
          localTextView1.setTextSize(0, this.tabTextSize);
          localTextView1.setTypeface(this.tabTypeface, this.tabTypefaceStyle);
          localTextView1.setTextColor(this.tabTextColor);
          if (this.textAllCaps)
          {
            if (Build.VERSION.SDK_INT < 14)
              break label272;
            localTextView1.setAllCaps(true);
          }
          label249: if (i != this.selectedPosition)
            break label297;
          localTextView1.setTextColor(this.selectedTabTextColor);
        }
        while (true)
        {
          i++;
          break;
          label272: localTextView1.setText(localTextView1.getText().toString().toUpperCase(this.locale));
          break label249;
          label297: localTextView1.setTextColor(this.tabTextColor);
        }
      }
    }
  }

  public int getDividerColor()
  {
    return this.dividerColor;
  }

  public int getDividerPadding()
  {
    return this.dividerPadding;
  }

  public int getIndicatorColor()
  {
    return this.indicatorColor;
  }

  public int getIndicatorHeight()
  {
    return this.indicatorHeight;
  }

  public int getScrollOffset()
  {
    return this.scrollOffset;
  }

  public int getSelectedTextColor()
  {
    return this.selectedTabTextColor;
  }

  public boolean getShouldExpand()
  {
    return this.shouldExpand;
  }

  public int getTabBackground()
  {
    return this.tabBackgroundResId;
  }

  public int getTabPaddingLeftRight()
  {
    return this.tabPadding;
  }

  public int getTextColor()
  {
    return this.tabTextColor;
  }

  public int getTextSize()
  {
    return this.tabTextSize;
  }

  public int getUnderlineColor()
  {
    return this.underlineColor;
  }

  public int getUnderlineHeight()
  {
    return this.underlineHeight;
  }

  public boolean isTextAllCaps()
  {
    return this.textAllCaps;
  }

  public void notifyDataSetChanged()
  {
    if (this.width != 0)
      addTabs();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    int i;
    float f1;
    float f2;
    try
    {
      super.onDraw(paramCanvas);
      if ((isInEditMode()) || (this.tabCount == 0))
        return;
    }
    catch (NullPointerException localNullPointerException)
    {
      while (true)
        localNullPointerException.printStackTrace();
      i = getHeight();
      this.rectPaint.setColor(this.underlineColor);
      this.rectPaint.setColor(this.indicatorColor);
      View localView1 = this.tabsContainer.getChildAt(this.currentPosition);
      f1 = localView1.getLeft();
      f2 = localView1.getRight();
      if ((this.currentPositionOffset > 0.0F) && (this.currentPosition < -1 + this.tabCount))
      {
        View localView3 = this.tabsContainer.getChildAt(1 + this.currentPosition);
        float f3 = localView3.getLeft();
        float f4 = localView3.getRight();
        f1 = f3 * this.currentPositionOffset + f1 * (1.0F - this.currentPositionOffset);
        f2 = f4 * this.currentPositionOffset + f2 * (1.0F - this.currentPositionOffset);
      }
      if (this.indicatorDrawable == null)
        break label324;
    }
    int k = this.indicatorDrawable.getIntrinsicWidth();
    this.indicatorDrawable.setBounds((int)(f1 + this.tabWidth / 2 - k / 2), i - this.indicatorDrawable.getIntrinsicHeight(), (int)(f2 - this.tabWidth / 2 + k / 2), i);
    this.indicatorDrawable.draw(paramCanvas);
    while (true)
    {
      this.dividerPaint.setColor(this.dividerColor);
      for (int j = 0; j < -1 + this.tabCount; j++)
      {
        View localView2 = this.tabsContainer.getChildAt(j);
        paramCanvas.drawLine(localView2.getRight(), this.dividerPadding, localView2.getRight(), i - this.dividerPadding, this.dividerPaint);
      }
      label324: paramCanvas.drawRect(f1 + this.mMyUnderlinePadding, i - this.indicatorHeight, f2 - this.mMyUnderlinePadding, i, this.rectPaint);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = getMeasuredWidth();
    getMeasuredHeight();
    if (i > 0)
    {
      this.width = i;
      if ((!this.isTabsAdded) && (this.pager != null))
        addTabs();
    }
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    SavedState localSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    this.currentPosition = localSavedState.currentPosition;
    requestLayout();
  }

  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.currentPosition = this.currentPosition;
    return localSavedState;
  }

  public void setAllCaps(boolean paramBoolean)
  {
    this.textAllCaps = paramBoolean;
  }

  public void setDividerColor(int paramInt)
  {
    this.dividerColor = paramInt;
    invalidate();
  }

  public void setDividerColorResource(int paramInt)
  {
    this.dividerColor = getResources().getColor(paramInt);
    invalidate();
  }

  public void setDividerPadding(int paramInt)
  {
    this.dividerPadding = paramInt;
    invalidate();
  }

  public void setIndicatorColor(int paramInt)
  {
    this.indicatorColor = paramInt;
    invalidate();
  }

  public void setIndicatorColorResource(int paramInt)
  {
    this.indicatorColor = getResources().getColor(paramInt);
    invalidate();
  }

  public void setIndicatorHeight(int paramInt)
  {
    invalidate();
  }

  public void setMyPageChangeListener(MyPageChangeListener paramMyPageChangeListener)
  {
    this.myPageChangeListener = paramMyPageChangeListener;
  }

  public void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener)
  {
    this.delegatePageListener = paramOnPageChangeListener;
  }

  public void setScrollOffset(int paramInt)
  {
    this.scrollOffset = paramInt;
    invalidate();
  }

  public void setSelectedTextColor(int paramInt)
  {
    this.selectedTabTextColor = paramInt;
    updateTabStyles();
  }

  public void setSelectedTextColorResource(int paramInt)
  {
    this.selectedTabTextColor = getResources().getColor(paramInt);
    updateTabStyles();
  }

  public void setShouldExpand(boolean paramBoolean)
  {
    this.shouldExpand = paramBoolean;
    notifyDataSetChanged();
  }

  public void setTabBackground(int paramInt)
  {
    this.tabBackgroundResId = paramInt;
    updateTabStyles();
  }

  public void setTabPaddingLeftRight(int paramInt)
  {
    this.tabPadding = paramInt;
    updateTabStyles();
  }

  public void setTextColor(int paramInt)
  {
    this.tabTextColor = paramInt;
    updateTabStyles();
  }

  public void setTextColorResource(int paramInt)
  {
    this.tabTextColor = getResources().getColor(paramInt);
    updateTabStyles();
  }

  public void setTextSize(int paramInt)
  {
    this.tabTextSize = paramInt;
    updateTabStyles();
  }

  public void setTypeface(Typeface paramTypeface, int paramInt)
  {
    this.tabTypeface = paramTypeface;
    this.tabTypefaceStyle = paramInt;
    updateTabStyles();
  }

  public void setUnderlineColor(int paramInt)
  {
    this.underlineColor = paramInt;
    invalidate();
  }

  public void setUnderlineColorResource(int paramInt)
  {
    this.underlineColor = getResources().getColor(paramInt);
    invalidate();
  }

  public void setUnderlineHeight(int paramInt)
  {
    this.underlineHeight = paramInt;
    invalidate();
  }

  public void setUnderlinePadding0()
  {
    this.mMyUnderlinePadding = 0;
  }

  public void setViewPager(ViewPager paramViewPager)
  {
    if ((paramViewPager == null) || (paramViewPager.getAdapter() == null))
      return;
    this.pager = paramViewPager;
    this.selectedPosition = paramViewPager.getCurrentItem();
    if (paramViewPager.getAdapter() == null)
      throw new IllegalStateException("ViewPager does not have adapter instance.");
    paramViewPager.setOnPageChangeListener(this.pageListener);
    notifyDataSetChanged();
    setTabsValue();
  }

  public static abstract interface IconTabProvider
  {
    public abstract int getPageIconResId(int paramInt);
  }

  public static abstract interface MyPageChangeListener
  {
    public abstract void onPageChange(int paramInt);
  }

  private class PageListener
    implements ViewPager.OnPageChangeListener
  {
    private PageListener()
    {
    }

    public void onPageScrollStateChanged(int paramInt)
    {
      if (paramInt == 0)
        EasySlidingTabs.this.scrollToChild(EasySlidingTabs.this.pager.getCurrentItem(), 0);
      if (EasySlidingTabs.this.delegatePageListener != null)
        EasySlidingTabs.this.delegatePageListener.onPageScrollStateChanged(paramInt);
      if ((paramInt == 0) && (EasySlidingTabs.this.myPageChangeListener != null))
        EasySlidingTabs.this.myPageChangeListener.onPageChange(EasySlidingTabs.this.selectedPosition);
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      EasySlidingTabs.access$102(EasySlidingTabs.this, paramInt1);
      EasySlidingTabs.access$402(EasySlidingTabs.this, paramFloat);
      View localView = EasySlidingTabs.this.tabsContainer.getChildAt(paramInt1);
      if (localView == null);
      do
      {
        return;
        EasySlidingTabs.this.scrollToChild(paramInt1, (int)(paramFloat * localView.getWidth()));
        EasySlidingTabs.this.invalidate();
      }
      while (EasySlidingTabs.this.delegatePageListener == null);
      EasySlidingTabs.this.delegatePageListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }

    public void onPageSelected(int paramInt)
    {
      EasySlidingTabs.access$602(EasySlidingTabs.this, paramInt);
      com.ex.ltech.onepiontfive.main.room.FtRooms.tagPagePosi = paramInt;
      EasySlidingTabs.this.updateTabStyles();
      if (EasySlidingTabs.this.delegatePageListener != null)
        EasySlidingTabs.this.delegatePageListener.onPageSelected(paramInt);
    }
  }

  static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public EasySlidingTabs.SavedState createFromParcel(Parcel paramParcel)
      {
        return new EasySlidingTabs.SavedState(paramParcel, null);
      }

      public EasySlidingTabs.SavedState[] newArray(int paramInt)
      {
        return new EasySlidingTabs.SavedState[paramInt];
      }
    };
    int currentPosition;

    private SavedState(Parcel paramParcel)
    {
      super();
      this.currentPosition = paramParcel.readInt();
    }

    public SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.currentPosition);
    }
  }

  public static abstract interface TabsTitleInterface
  {
    public abstract int getTabDrawableBottom(int paramInt);

    public abstract int getTabDrawableLeft(int paramInt);

    public abstract int getTabDrawableRight(int paramInt);

    public abstract int getTabDrawableTop(int paramInt);

    public abstract SpannableString getTabTitle(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.widget.EasySlidingTabs
 * JD-Core Version:    0.6.0
 */