package com.ex.ltech.led.my_view.swipemenugridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.PrintStream;

public class PullToRefreshView extends LinearLayout
{
  private static final int PULL_DOWN_STATE = 1;
  private static final int PULL_TO_REFRESH = 2;
  private static final int PULL_UP_STATE = 0;
  private static final int REFRESHING = 4;
  private static final int RELEASE_TO_REFRESH = 3;
  public static final int STOP = 1;
  private AdapterView<?> mAdapterView;
  private RotateAnimation mFlipAnimation;
  private ImageView mFooterImageView;
  private ProgressBar mFooterProgressBar;
  private int mFooterState;
  private TextView mFooterTextView;
  private View mFooterView;
  private int mFooterViewHeight;
  private ImageView mHeaderImageView;
  private ProgressBar mHeaderProgressBar;
  public int mHeaderState;
  private TextView mHeaderTextView;
  private TextView mHeaderUpdateTextView;
  private View mHeaderView;
  private int mHeaderViewHeight;
  private LayoutInflater mInflater;
  private int mLastMotionY;
  private OnFooterRefreshListener mOnFooterRefreshListener;
  private OnHeaderRefreshListener mOnHeaderRefreshListener;
  private int mPullState;
  private RotateAnimation mReverseFlipAnimation;
  private ScrollView mScrollView;
  float touchYStart;

  public PullToRefreshView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public PullToRefreshView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  private void addFooterView()
  {
    this.mFooterView = this.mInflater.inflate(2130968877, this, false);
    this.mFooterImageView = ((ImageView)this.mFooterView.findViewById(2131559457));
    this.mFooterTextView = ((TextView)this.mFooterView.findViewById(2131559458));
    this.mFooterProgressBar = ((ProgressBar)this.mFooterView.findViewById(2131559456));
    measureView(this.mFooterView);
    this.mFooterViewHeight = this.mFooterView.getMeasuredHeight();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, this.mFooterViewHeight);
    addView(this.mFooterView, localLayoutParams);
  }

  private void addHeaderView()
  {
    this.mHeaderView = this.mInflater.inflate(2130968879, this, false);
    this.mHeaderImageView = ((ImageView)this.mHeaderView.findViewById(2131559464));
    this.mHeaderTextView = ((TextView)this.mHeaderView.findViewById(2131559465));
    this.mHeaderUpdateTextView = ((TextView)this.mHeaderView.findViewById(2131559466));
    this.mHeaderProgressBar = ((ProgressBar)this.mHeaderView.findViewById(2131559463));
    measureView(this.mHeaderView);
    this.mHeaderViewHeight = this.mHeaderView.getMeasuredHeight();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, this.mHeaderViewHeight);
    localLayoutParams.topMargin = (-this.mHeaderViewHeight);
    addView(this.mHeaderView, localLayoutParams);
  }

  private int changingHeaderViewTopMargin(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mHeaderView.getLayoutParams();
    float f = localLayoutParams.topMargin + 0.3F * paramInt;
    if ((paramInt > 0) && (this.mPullState == 0) && (Math.abs(localLayoutParams.topMargin) <= this.mHeaderViewHeight))
      return localLayoutParams.topMargin;
    if ((paramInt < 0) && (this.mPullState == 1) && (Math.abs(localLayoutParams.topMargin) >= this.mHeaderViewHeight))
      return localLayoutParams.topMargin;
    localLayoutParams.topMargin = (int)f;
    this.mHeaderView.setLayoutParams(localLayoutParams);
    invalidate();
    return localLayoutParams.topMargin;
  }

  private void footerPrepareToRefresh(int paramInt)
  {
    int i = changingHeaderViewTopMargin(paramInt);
    if ((Math.abs(i) >= this.mHeaderViewHeight + this.mFooterViewHeight) && (this.mFooterState != 3))
    {
      this.mFooterTextView.setText(2131100325);
      this.mFooterImageView.clearAnimation();
      this.mFooterImageView.startAnimation(this.mFlipAnimation);
      this.mFooterState = 3;
    }
    do
      return;
    while (Math.abs(i) >= this.mHeaderViewHeight + this.mFooterViewHeight);
    this.mFooterImageView.clearAnimation();
    this.mFooterImageView.startAnimation(this.mFlipAnimation);
    this.mFooterTextView.setText(2131100325);
    this.mFooterState = 2;
  }

  private void footerRefreshing()
  {
    this.mFooterState = 4;
    setHeaderTopMargin(-(this.mHeaderViewHeight + this.mFooterViewHeight));
    this.mFooterImageView.setVisibility(8);
    this.mFooterImageView.clearAnimation();
    this.mFooterImageView.setImageDrawable(null);
    this.mFooterProgressBar.setVisibility(0);
    this.mFooterTextView.setText(2131100325);
    if (this.mOnFooterRefreshListener != null)
      this.mOnFooterRefreshListener.onFooterRefresh(this);
  }

  private int getHeaderTopMargin()
  {
    return ((LinearLayout.LayoutParams)this.mHeaderView.getLayoutParams()).topMargin;
  }

  private void headerRefreshing()
  {
    this.mHeaderState = 4;
    setHeaderTopMargin(0);
    this.mHeaderImageView.setVisibility(8);
    this.mHeaderImageView.clearAnimation();
    this.mHeaderImageView.setImageDrawable(null);
    this.mHeaderProgressBar.setVisibility(0);
    this.mHeaderTextView.setText(2131100325);
    if (this.mOnHeaderRefreshListener != null)
      this.mOnHeaderRefreshListener.onHeaderRefresh(this);
  }

  private void init()
  {
    setOrientation(1);
    this.mFlipAnimation = new RotateAnimation(0.0F, 180.0F, 1, 0.5F, 1, 0.5F);
    this.mFlipAnimation.setInterpolator(new LinearInterpolator());
    this.mFlipAnimation.setDuration(250L);
    this.mFlipAnimation.setFillAfter(true);
    this.mReverseFlipAnimation = new RotateAnimation(180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
    this.mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
    this.mReverseFlipAnimation.setDuration(250L);
    this.mReverseFlipAnimation.setFillAfter(true);
    this.mInflater = LayoutInflater.from(getContext());
    addHeaderView();
  }

  private void initContentAdapterView()
  {
    int i = getChildCount();
    if (i < 3)
      throw new IllegalArgumentException("This layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
    for (int j = 0; j < i - 1; j++)
    {
      View localView = getChildAt(j);
      if ((localView instanceof AdapterView))
        this.mAdapterView = ((AdapterView)localView);
      if (!(localView instanceof ScrollView))
        continue;
      this.mScrollView = ((ScrollView)localView);
    }
    if ((this.mAdapterView == null) && (this.mScrollView == null))
      throw new IllegalArgumentException("must contain a AdapterView or ScrollView in this layout!");
  }

  private boolean isRefreshViewScroll(int paramInt)
  {
    if ((this.mHeaderState == 4) || (this.mFooterState == 4))
      return false;
    if (this.mAdapterView != null)
      if (paramInt > 0)
      {
        View localView3 = this.mAdapterView.getChildAt(0);
        if ((localView3 != null) || ((this.mAdapterView.getFirstVisiblePosition() == 0) && ((localView3 == null) || (localView3.getTop() == 0))))
        {
          this.mPullState = 1;
          return true;
        }
        int i = localView3.getTop();
        int j = this.mAdapterView.getPaddingTop();
        if ((this.mAdapterView.getFirstVisiblePosition() == 0) && (Math.abs(i - j) <= 8))
        {
          this.mPullState = 1;
          return true;
        }
      }
      else if (paramInt < 0)
      {
        View localView2 = this.mAdapterView.getChildAt(-1 + this.mAdapterView.getChildCount());
        if (localView2 == null)
          return false;
        if ((localView2.getBottom() <= getHeight()) && (this.mAdapterView.getLastVisiblePosition() == -1 + this.mAdapterView.getCount()))
        {
          this.mPullState = 0;
          return true;
        }
      }
    if (this.mScrollView != null)
    {
      View localView1 = this.mScrollView.getChildAt(0);
      if ((paramInt > 0) && (this.mScrollView.getScrollY() == 0))
      {
        this.mPullState = 1;
        return true;
      }
      if ((paramInt < 0) && (localView1.getMeasuredHeight() <= getHeight() + this.mScrollView.getScrollY()))
      {
        this.mPullState = 0;
        return true;
      }
    }
    return false;
  }

  private void measureView(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    if (localLayoutParams == null)
      localLayoutParams = new ViewGroup.LayoutParams(-1, -2);
    int i = ViewGroup.getChildMeasureSpec(0, 0, localLayoutParams.width);
    int j = localLayoutParams.height;
    if (j > 0);
    for (int k = View.MeasureSpec.makeMeasureSpec(j, 1073741824); ; k = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(i, k);
      return;
    }
  }

  private void setHeaderTopMargin(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mHeaderView.getLayoutParams();
    localLayoutParams.topMargin = paramInt;
    this.mHeaderView.setLayoutParams(localLayoutParams);
    invalidate();
  }

  public void headerPrepareToRefresh(int paramInt)
  {
    int i = changingHeaderViewTopMargin(paramInt);
    if ((i >= 0) && (this.mHeaderState != 3))
    {
      this.mHeaderTextView.setText(2131100325);
      this.mHeaderUpdateTextView.setVisibility(0);
      this.mHeaderImageView.clearAnimation();
      this.mHeaderImageView.startAnimation(this.mFlipAnimation);
      this.mHeaderState = 3;
    }
    do
      return;
    while ((i >= 0) || (i <= -this.mHeaderViewHeight));
    this.mHeaderImageView.clearAnimation();
    this.mHeaderImageView.startAnimation(this.mFlipAnimation);
    this.mHeaderTextView.setText(2131100281);
    this.mHeaderState = 2;
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    addFooterView();
    initContentAdapterView();
  }

  public void onFooterRefreshComplete()
  {
    setHeaderTopMargin(-this.mHeaderViewHeight);
    this.mFooterImageView.setVisibility(0);
    this.mFooterImageView.setImageResource(2130903641);
    this.mFooterTextView.setText(2131100281);
    this.mFooterProgressBar.setVisibility(8);
    this.mFooterState = 2;
  }

  public void onHeaderRefreshComplete()
  {
    setHeaderTopMargin(-this.mHeaderViewHeight);
    this.mHeaderImageView.setVisibility(0);
    this.mHeaderImageView.setImageResource(2130903640);
    this.mHeaderTextView.setText(2131100281);
    this.mHeaderProgressBar.setVisibility(8);
    this.mHeaderState = 2;
  }

  public void onHeaderRefreshComplete(CharSequence paramCharSequence)
  {
    setLastUpdated(paramCharSequence);
    onHeaderRefreshComplete();
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getRawY();
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
    case 0:
    case 2:
    }
    do
      while (true)
      {
        return false;
        this.mLastMotionY = i;
        this.touchYStart = i;
      }
    while (!isRefreshViewScroll(i - this.mLastMotionY));
    return true;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = 1;
    int j = (int)paramMotionEvent.getRawY();
    System.out.println(paramMotionEvent.getY());
    switch (paramMotionEvent.getAction())
    {
    case 0:
    default:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      return super.onTouchEvent(paramMotionEvent);
      int m = j - this.mLastMotionY;
      int n;
      if (this.mPullState == i)
      {
        n = i;
        label77: if (j - this.touchYStart <= 50.0F)
          break label118;
        label91: if ((n & i) == 0)
          break label123;
        headerPrepareToRefresh(m);
      }
      while (true)
      {
        this.mLastMotionY = j;
        break;
        n = 0;
        break label77;
        label118: i = 0;
        break label91;
        label123: if (this.mPullState != 0)
          continue;
      }
      this.mHeaderState = i;
      int k = getHeaderTopMargin();
      if (this.mPullState == i)
      {
        if (k >= 0)
        {
          headerRefreshing();
          continue;
        }
        setHeaderTopMargin(-this.mHeaderViewHeight);
        continue;
      }
      if (this.mPullState != 0)
        continue;
      if (Math.abs(k) >= this.mHeaderViewHeight + this.mFooterViewHeight)
      {
        footerRefreshing();
        continue;
      }
      setHeaderTopMargin(-this.mHeaderViewHeight);
    }
  }

  public void setLastUpdated(CharSequence paramCharSequence)
  {
    if (paramCharSequence != null)
    {
      this.mHeaderUpdateTextView.setVisibility(0);
      this.mHeaderUpdateTextView.setText(paramCharSequence);
      return;
    }
    this.mHeaderUpdateTextView.setVisibility(8);
  }

  public void setOnFooterRefreshListener(OnFooterRefreshListener paramOnFooterRefreshListener)
  {
    this.mOnFooterRefreshListener = paramOnFooterRefreshListener;
  }

  public void setOnHeaderRefreshListener(OnHeaderRefreshListener paramOnHeaderRefreshListener)
  {
    this.mOnHeaderRefreshListener = paramOnHeaderRefreshListener;
  }

  public static abstract interface OnFooterRefreshListener
  {
    public abstract void onFooterRefresh(PullToRefreshView paramPullToRefreshView);
  }

  public static abstract interface OnHeaderRefreshListener
  {
    public abstract void onHeaderRefresh(PullToRefreshView paramPullToRefreshView);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.swipemenugridview.PullToRefreshView
 * JD-Core Version:    0.6.0
 */