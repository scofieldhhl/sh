package com.ex.ltech.onepiontfive.main.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.widget.pulltorefresh.Pullable;
import java.io.PrintStream;
import java.util.ArrayList;

public class RoomGridView extends GridView
  implements Pullable
{
  private final int SCROLL_STATE_BOTTOM = 1;
  private final int SCROLL_STATE_MIDDLE = 2;
  private final int SCROLL_STATE_TOP = 0;
  private int _xDelta;
  private int _yDelta;
  private ImageView draftDevice;
  boolean isFirstTimeTouchMove = true;
  public boolean isGvLongClick;
  private int mAutoScrollAreaMarginDistance;
  private int mScrollState = 0;
  private OnDragInGroupListener onDragInGroupListener;
  RelativeLayout root;
  private int top;

  public RoomGridView(Context paramContext)
  {
    super(paramContext);
  }

  public RoomGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public RoomGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void initDragDevice(View paramView, int paramInt)
  {
    int i = 1;
    Dvc localDvc = (Dvc)getAdapter().getItem(paramInt);
    int j;
    label42: int k;
    label55: int n;
    label75: int i1;
    if (localDvc.innerDvcVos.size() < 2)
    {
      if (localDvc.getType() != 8)
        break label201;
      j = i;
      if (localDvc.getType() != 9)
        break label207;
      k = i;
      int m = k | j;
      if (localDvc.getType() != 10)
        break label213;
      n = i;
      i1 = n | m;
      if (localDvc.getType() != 11)
        break label219;
    }
    while (true)
    {
      if ((i1 | i) != 0)
      {
        this.onDragInGroupListener.onDragStart(paramInt);
        setViewXy(this.draftDevice, paramView.getLeft() + (paramView.getWidth() / 2 - this.draftDevice.getWidth() / 2), this.top + paramView.getTop() - (this.draftDevice.getHeight() / 2 - paramView.getHeight() / 2));
      }
      switch (localDvc.getType())
      {
      default:
        return;
        label201: j = 0;
        break label42;
        label207: k = 0;
        break label55;
        label213: n = 0;
        break label75;
        label219: i = 0;
      case 8:
      case 9:
      case 10:
      case 11:
      }
    }
    this.draftDevice.setBackgroundResource(2130903700);
    return;
    this.draftDevice.setBackgroundResource(2130903805);
    return;
    this.draftDevice.setBackgroundResource(2130903142);
    return;
    this.draftDevice.setBackgroundResource(2130903085);
  }

  private void setViewXy(View paramView, int paramInt1, int paramInt2)
  {
    paramView.setVisibility(View.VISIBLE);
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setDuration(300L);
    localObjectAnimator.start();
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)paramView.getLayoutParams();
    localLayoutParams.leftMargin = paramInt1;
    localLayoutParams.topMargin = paramInt2;
    localLayoutParams.rightMargin = (-BitmapUtils.dp2px(getContext(), 75.0F));
    localLayoutParams.bottomMargin = (-BitmapUtils.dp2px(getContext(), 75.0F));
    paramView.setLayoutParams(localLayoutParams);
    this.root.invalidate();
  }

  public boolean canPullDown()
  {
    try
    {
      if (this.isGvLongClick)
        return false;
      if (getCount() == 0)
        return true;
      if (getFirstVisiblePosition() == 0)
      {
        int i = getChildAt(0).getTop();
        if (i >= 0)
          return true;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean canPullUp()
  {
    return false;
  }

  public void init(Context paramContext)
  {
    paramContext.getResources().getDisplayMetrics();
    setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
      {
        if (paramAbsListView.getLastVisiblePosition() == -1 + paramAbsListView.getCount())
        {
          View localView2 = paramAbsListView.getChildAt(-1 + paramAbsListView.getChildCount());
          System.out.println("onScrollStateChanged    getBottom   " + localView2.getBottom());
          if (localView2.getBottom() <= RoomGridView.this.getMeasuredHeight())
          {
            RoomGridView.access$002(RoomGridView.this, 1);
            System.out.println("onScrollStateChanged     底部");
          }
        }
        while (true)
        {
          return;
          if (paramAbsListView.getFirstVisiblePosition() != 0)
            break;
          View localView1 = paramAbsListView.getChildAt(0);
          System.out.println("onScrollStateChanged     getTop     " + localView1.getTop());
          if (localView1.getTop() < 0)
            continue;
          RoomGridView.access$002(RoomGridView.this, 0);
          System.out.println("onScrollStateChanged     顶部");
          return;
        }
        RoomGridView.access$002(RoomGridView.this, 2);
      }
    });
    setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        RoomGridView.this.onDragInGroupListener.onLongClick(paramInt);
        Dvc localDvc = (Dvc)RoomGridView.this.getAdapter().getItem(paramInt);
        int i;
        int j;
        label57: int m;
        label77: int i1;
        label97: int i3;
        if (localDvc.getType() == 15)
        {
          i = 1;
          if (localDvc.getType() != 14)
            break label133;
          j = 1;
          int k = j | i;
          if (localDvc.getType() != 16)
            break label139;
          m = 1;
          int n = k | m;
          if (localDvc.getType() != 17)
            break label145;
          i1 = 1;
          int i2 = n | i1;
          if (localDvc.getType() != 18)
            break label151;
          i3 = 1;
          label117: if ((i3 | i2) == 0)
            break label157;
        }
        label133: label139: label145: label151: label157: int i4;
        int i5;
        label203: int i7;
        label223: int i9;
        label243: int i10;
        int i12;
        do
        {
          return true;
          i = 0;
          break;
          j = 0;
          break label57;
          m = 0;
          break label77;
          i1 = 0;
          break label97;
          i3 = 0;
          break label117;
          RoomGridView.this.isGvLongClick = true;
          if (localDvc.innerDvcVos.size() >= 2)
            break label516;
          if (localDvc.getType() != 8)
            break label417;
          i4 = 1;
          if (localDvc.getType() != 12)
            break label423;
          i5 = 1;
          int i6 = i5 | i4;
          if (localDvc.getType() != 9)
            break label429;
          i7 = 1;
          int i8 = i6 | i7;
          if (localDvc.getType() != 10)
            break label435;
          i9 = 1;
          i10 = i9 | i8;
          int i11 = localDvc.getType();
          i12 = 0;
          if (i11 != 11)
            continue;
          i12 = 1;
        }
        while ((i10 | i12) == 0);
        RoomGridView.this.onDragInGroupListener.onDragStart(paramInt);
        RoomGridView.this.setViewXy(RoomGridView.this.draftDevice, paramView.getLeft() + (paramView.getWidth() / 2 - RoomGridView.this.draftDevice.getWidth() / 2), RoomGridView.this.top + paramView.getTop() + RoomGridView.this.draftDevice.getHeight() / 2);
        switch (localDvc.getType())
        {
        default:
        case 8:
        case 12:
        case 9:
        case 10:
        case 11:
        }
        while (true)
        {
          RoomGridView.this.setOnTouchListener(new View.OnTouchListener()
          {
            public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
            {
              System.out.println("GV       _xDelta = " + paramMotionEvent.getAction());
              int i = (int)paramMotionEvent.getRawX();
              int j = (int)paramMotionEvent.getRawY();
              int k = (int)paramMotionEvent.getY();
              int m = (int)paramMotionEvent.getX();
              switch (paramMotionEvent.getAction())
              {
              case 2:
              default:
                if (!RoomGridView.this.isGvLongClick)
                  break;
                if (RoomGridView.this.isFirstTimeTouchMove)
                {
                  RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)RoomGridView.this.draftDevice.getLayoutParams();
                  RoomGridView.access$502(RoomGridView.this, i - localLayoutParams2.leftMargin);
                  RoomGridView.access$602(RoomGridView.this, j - localLayoutParams2.topMargin);
                  RoomGridView.this.isFirstTimeTouchMove = false;
                  System.out.println("GV       _xDelta = " + localLayoutParams2.leftMargin + "      _yDelta = " + localLayoutParams2.topMargin);
                }
                switch (0xFF & paramMotionEvent.getAction())
                {
                case 0:
                case 1:
                default:
                case 2:
                }
              case 3:
              case 1:
              }
              while (true)
              {
                return false;
                RoomGridView.this.setOnTouchListener(null);
                RoomGridView.this.isGvLongClick = false;
                continue;
                if ((RoomGridView.this.isGvLongClick) && (RoomGridView.this.onDragInGroupListener != null))
                  RoomGridView.this.onDragInGroupListener.onDragFinish(RoomGridView.this.pointToPosition(m, k));
                RoomGridView.this.isGvLongClick = false;
                RoomGridView.this.isFirstTimeTouchMove = true;
                RoomGridView.this.draftDevice.setVisibility(View.GONE);
                ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(RoomGridView.this.draftDevice, "alpha", new float[] { 1.0F, 0.0F });
                localObjectAnimator.setDuration(300L);
                localObjectAnimator.start();
                RoomGridView.this.setOnTouchListener(null);
                continue;
                if ((k < RoomGridView.this.mAutoScrollAreaMarginDistance) && (RoomGridView.this.mScrollState != 0))
                  RoomGridView.this.smoothScrollBy(-20, 0);
                if ((k > RoomGridView.this.getMeasuredHeight() - RoomGridView.this.mAutoScrollAreaMarginDistance) && (RoomGridView.this.mScrollState != 1))
                  RoomGridView.this.smoothScrollBy(20, 0);
                RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)RoomGridView.this.draftDevice.getLayoutParams();
                localLayoutParams1.leftMargin = (i - RoomGridView.this._xDelta);
                localLayoutParams1.topMargin = (j - RoomGridView.this._yDelta);
                localLayoutParams1.rightMargin = (-RoomGridView.this.draftDevice.getWidth());
                localLayoutParams1.bottomMargin = (-RoomGridView.this.draftDevice.getHeight());
                RoomGridView.this.draftDevice.setLayoutParams(localLayoutParams1);
                RoomGridView.this.root.invalidate();
                int n = RoomGridView.this.pointToPosition(m, k);
                if (RoomGridView.this.onDragInGroupListener != null)
                  RoomGridView.this.onDragInGroupListener.onDragMove(n);
                System.out.println("pointToPosition    Room   moveX     " + m + "       moveY     " + k);
              }
            }
          });
          RoomGridView.this.requestDisallowInterceptTouchEvent(true);
          return true;
          label417: i4 = 0;
          break;
          label423: i5 = 0;
          break label203;
          label429: i7 = 0;
          break label223;
          label435: i9 = 0;
          break label243;
          RoomGridView.this.draftDevice.setBackgroundResource(2130903700);
          continue;
          RoomGridView.this.draftDevice.setBackgroundResource(2130903701);
          continue;
          RoomGridView.this.draftDevice.setBackgroundResource(2130903805);
          continue;
          RoomGridView.this.draftDevice.setBackgroundResource(2130903142);
          continue;
          RoomGridView.this.draftDevice.setBackgroundResource(2130903085);
        }
        label516: RoomGridView.this.isGvLongClick = false;
        return true;
      }
    });
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    this.mAutoScrollAreaMarginDistance = (getMeasuredHeight() / 4);
  }

  public void setDraftDevice(ImageView paramImageView)
  {
    this.draftDevice = paramImageView;
    try
    {
      init(getContext());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void setOnDragInGroupListener(OnDragInGroupListener paramOnDragInGroupListener)
  {
    this.onDragInGroupListener = paramOnDragInGroupListener;
  }

  public void setRoot(RelativeLayout paramRelativeLayout)
  {
    this.root = paramRelativeLayout;
  }

  public void setmTop(int paramInt)
  {
    this.top = paramInt;
  }

  public static abstract interface OnDragInGroupListener
  {
    public abstract void onDragFinish(int paramInt);

    public abstract void onDragMove(int paramInt);

    public abstract void onDragStart(int paramInt);

    public abstract void onEventCancel(int paramInt);

    public abstract void onLongClick(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.widget.RoomGridView
 * JD-Core Version:    0.6.0
 */