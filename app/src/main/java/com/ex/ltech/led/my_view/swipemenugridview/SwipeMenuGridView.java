package com.ex.ltech.led.my_view.swipemenugridview;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.ListAdapter;
import com.ex.ltech.led.my_view.pullfresh.PullableGridView;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;

public class SwipeMenuGridView extends PullableGridView
{
  private static final int TOUCH_STATE_NONE = 0;
  private static final int TOUCH_STATE_X = 1;
  private static final int TOUCH_STATE_Y = 2;
  private int MAX_X = 3;
  private int MAX_Y = 5;
  private Interpolator mCloseInterpolator;
  private float mDownX;
  private float mDownY;
  private SwipeMenuCreator mMenuCreator;
  private OnMenuItemClickListener mOnMenuItemClickListener;
  private OnSwipeListener mOnSwipeListener;
  private Interpolator mOpenInterpolator;
  private int mTouchPosition;
  private int mTouchState;
  private SwipeMenuLayout mTouchView;

  public SwipeMenuGridView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public SwipeMenuGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public SwipeMenuGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  private void init()
  {
    this.MAX_X = dp2px(this.MAX_X);
    this.MAX_Y = dp2px(this.MAX_Y);
    this.mTouchState = 0;
  }

  public int dp2px(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, getContext().getResources().getDisplayMetrics());
  }

  public Interpolator getCloseInterpolator()
  {
    return this.mCloseInterpolator;
  }

  public Interpolator getOpenInterpolator()
  {
    return this.mOpenInterpolator;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.onInterceptTouchEvent(paramMotionEvent);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.onTouchEvent(paramMotionEvent);
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    super.setAdapter(new SwipeMenuAdapter(getContext(), paramListAdapter)
    {
      public void createMenu(SwipeMenu paramSwipeMenu)
      {
        if (SwipeMenuGridView.this.mMenuCreator != null)
          SwipeMenuGridView.this.mMenuCreator.create(paramSwipeMenu);
      }

      public void onItemClick(SwipeMenuView paramSwipeMenuView, SwipeMenu paramSwipeMenu, int paramInt)
      {
        SwipeMenuGridView.OnMenuItemClickListener localOnMenuItemClickListener = SwipeMenuGridView.this.mOnMenuItemClickListener;
        boolean bool = false;
        if (localOnMenuItemClickListener != null)
          bool = SwipeMenuGridView.this.mOnMenuItemClickListener.onMenuItemClick(paramSwipeMenuView.getPosition(), paramSwipeMenu, paramInt);
        if ((SwipeMenuGridView.this.mTouchView != null) && (!bool))
          SwipeMenuGridView.this.mTouchView.smoothCloseMenu();
      }
    });
  }

  public void setCloseInterpolator(Interpolator paramInterpolator)
  {
    this.mCloseInterpolator = paramInterpolator;
  }

  public void setMenuCreator(SwipeMenuCreator paramSwipeMenuCreator)
  {
    this.mMenuCreator = paramSwipeMenuCreator;
  }

  public void setOnMenuItemClickListener(OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    this.mOnMenuItemClickListener = paramOnMenuItemClickListener;
  }

  public void setOnSwipeListener(OnSwipeListener paramOnSwipeListener)
  {
    this.mOnSwipeListener = paramOnSwipeListener;
  }

  public void setOpenInterpolator(Interpolator paramInterpolator)
  {
    this.mOpenInterpolator = paramInterpolator;
  }

  public static abstract interface OnMenuItemClickListener
  {
    public abstract boolean onMenuItemClick(int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2);
  }

  public static abstract interface OnSwipeListener
  {
    public abstract void onSwipeEnd(int paramInt);

    public abstract void onSwipeStart(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.swipemenugridview.SwipeMenuGridView
 * JD-Core Version:    0.6.0
 */