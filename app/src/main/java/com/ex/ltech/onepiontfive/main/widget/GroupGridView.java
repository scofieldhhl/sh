package com.ex.ltech.onepiontfive.main.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.onepiontfive.main.room.GroupAdapter;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import java.io.PrintStream;
import java.util.List;

public class GroupGridView extends GridView
{
  private int _xDelta;
  private int _yDelta;
  private ImageView draftDevice;
  boolean isFirstTimeTouchMove = true;
  boolean isGvLongClick;
  public int mShowLeft;
  public int mShowTop;
  private OnDragOutGroupListener onDragInGroupListener;
  OnMyLongClickListener onMyLongClickListener;
  RelativeLayout root;
  private int top;

  public GroupGridView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public GroupGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public GroupGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
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

  public void init(Context paramContext)
  {
    paramContext.getResources().getDisplayMetrics();
    setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        GroupGridView.this.isGvLongClick = true;
        GroupAdapter localGroupAdapter = (GroupAdapter)GroupGridView.this.getAdapter();
        List localList = localGroupAdapter.dvcVos;
        for (int i = 0; i < localList.size(); i++)
        {
          ((Dvc)localList.get(i)).setIsShowDelBtn(true);
          ((Dvc)localList.get(i)).setIsClickSeleted(false);
        }
        ((Dvc)localList.get(paramInt)).setIsClickSeleted(true);
        if (GroupGridView.this.onDragInGroupListener != null)
          GroupGridView.this.onDragInGroupListener.onLongClick(paramInt);
        if (GroupGridView.this.onMyLongClickListener != null)
          GroupGridView.this.onMyLongClickListener.onLongClick(paramInt);
        localGroupAdapter.notifyDataSetChanged();
        switch (((Dvc)GroupGridView.this.getAdapter().getItem(paramInt)).getType())
        {
        default:
        case 8:
        case 9:
        case 10:
        case 11:
        }
        while (true)
        {
          if (GroupGridView.this.onDragInGroupListener != null)
            GroupGridView.this.onDragInGroupListener.onDragStart(paramInt);
          if (paramInt != 0);
          return true;
          GroupGridView.this.draftDevice.setBackgroundResource(2130903700);
          continue;
          GroupGridView.this.draftDevice.setBackgroundResource(2130903805);
          continue;
          GroupGridView.this.draftDevice.setBackgroundResource(2130903142);
          continue;
          GroupGridView.this.draftDevice.setBackgroundResource(2130903085);
        }
      }
    });
    setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        int i = (int)paramMotionEvent.getRawX();
        int j = (int)paramMotionEvent.getRawY();
        switch (paramMotionEvent.getAction())
        {
        default:
          if (!GroupGridView.this.isGvLongClick)
            break;
          if (GroupGridView.this.isFirstTimeTouchMove)
          {
            RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)GroupGridView.this.draftDevice.getLayoutParams();
            GroupGridView.access$202(GroupGridView.this, i - localLayoutParams2.leftMargin);
            GroupGridView.access$302(GroupGridView.this, j - localLayoutParams2.topMargin);
            GroupGridView.this.isFirstTimeTouchMove = false;
            System.out.println("GV       _xDelta = " + localLayoutParams2.leftMargin + "      _yDelta = " + localLayoutParams2.topMargin);
          }
          switch (0xFF & paramMotionEvent.getAction())
          {
          case 0:
          case 1:
          default:
          case 2:
          }
        case 1:
        }
        int k;
        int m;
        do
        {
          return false;
          if ((GroupGridView.this.isGvLongClick) && (GroupGridView.this.onDragInGroupListener != null))
            GroupGridView.this.onDragInGroupListener.onDragFinish();
          GroupGridView.this.isGvLongClick = false;
          GroupGridView.this.isFirstTimeTouchMove = true;
          GroupGridView.this.draftDevice.setVisibility(View.GONE);
          ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(GroupGridView.this.draftDevice, "alpha", new float[] { 1.0F, 0.0F });
          localObjectAnimator.setDuration(300L);
          localObjectAnimator.start();
          return false;
          k = (int)paramMotionEvent.getY();
          m = (int)paramMotionEvent.getX();
          RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)GroupGridView.this.draftDevice.getLayoutParams();
          localLayoutParams1.leftMargin = (i - GroupGridView.this._xDelta);
          localLayoutParams1.topMargin = (j - GroupGridView.this._yDelta);
          localLayoutParams1.rightMargin = (-GroupGridView.this.draftDevice.getWidth());
          localLayoutParams1.bottomMargin = (-GroupGridView.this.draftDevice.getHeight());
          GroupGridView.this.draftDevice.setLayoutParams(localLayoutParams1);
          GroupGridView.this.root.invalidate();
        }
        while (GroupGridView.this.onDragInGroupListener == null);
        GroupGridView.this.onDragInGroupListener.onDragMove(m + GroupGridView.this.getLeft(), k);
        return false;
      }
    });
    setSelector(new ColorDrawable(0));
  }

  public void setDraftDevice(ImageView paramImageView)
  {
    this.draftDevice = paramImageView;
  }

  public void setOnDragOutGroupListener(OnDragOutGroupListener paramOnDragOutGroupListener)
  {
  }

  public void setOnMyLongClickListener(OnMyLongClickListener paramOnMyLongClickListener)
  {
    this.onMyLongClickListener = paramOnMyLongClickListener;
  }

  public void setRoot(RelativeLayout paramRelativeLayout)
  {
    this.root = paramRelativeLayout;
  }

  public void setmTop(int paramInt)
  {
    this.top = paramInt;
  }

  public static abstract interface OnDragOutGroupListener
  {
    public abstract void onDragFinish();

    public abstract void onDragMove(int paramInt1, int paramInt2);

    public abstract void onDragStart(int paramInt);

    public abstract void onLongClick(int paramInt);
  }

  public static abstract interface OnMyLongClickListener
  {
    public abstract void onLongClick(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.widget.GroupGridView
 * JD-Core Version:    0.6.0
 */