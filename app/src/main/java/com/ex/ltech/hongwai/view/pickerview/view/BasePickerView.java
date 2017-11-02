package com.ex.ltech.hongwai.view.pickerview.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout.LayoutParams;
import com.ex.ltech.hongwai.view.pickerview.listener.OnDismissListener;
import com.ex.ltech.hongwai.view.pickerview.utils.PickerViewAnimateUtil;

public class BasePickerView
{
  protected ViewGroup contentContainer;
  private Context context;
  private ViewGroup decorView;
  private boolean dismissing;
  private int gravity = 80;
  private Animation inAnim;
  private boolean isShowing;
  private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener()
  {
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      if (paramMotionEvent.getAction() == 0)
        BasePickerView.this.dismiss();
      return false;
    }
  };
  private OnDismissListener onDismissListener;
  private Animation outAnim;
  private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -2, 80);
  private ViewGroup rootView;

  public BasePickerView(Context paramContext)
  {
    this.context = paramContext;
    initViews();
    init();
    initEvents();
  }

  private void onAttached(View paramView)
  {
    this.decorView.addView(paramView);
    this.contentContainer.startAnimation(this.inAnim);
  }

  public void dismiss()
  {
    if (this.dismissing)
      return;
    this.dismissing = true;
    this.outAnim.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnimation)
      {
        BasePickerView.this.decorView.post(new Runnable()
        {
          public void run()
          {
            BasePickerView.this.dismissImmediately();
          }
        });
      }

      public void onAnimationRepeat(Animation paramAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnimation)
      {
      }
    });
    this.contentContainer.startAnimation(this.outAnim);
  }

  public void dismissImmediately()
  {
    this.decorView.removeView(this.rootView);
    this.isShowing = false;
    this.dismissing = false;
    if (this.onDismissListener != null)
      this.onDismissListener.onDismiss(this);
  }

  public View findViewById(int paramInt)
  {
    return this.contentContainer.findViewById(paramInt);
  }

  public Animation getInAnimation()
  {
    int i = PickerViewAnimateUtil.getAnimationResource(this.gravity, true);
    return AnimationUtils.loadAnimation(this.context, i);
  }

  public Animation getOutAnimation()
  {
    int i = PickerViewAnimateUtil.getAnimationResource(this.gravity, false);
    return AnimationUtils.loadAnimation(this.context, i);
  }

  protected void init()
  {
    this.inAnim = getInAnimation();
    this.outAnim = getOutAnimation();
  }

  protected void initEvents()
  {
  }

  protected void initViews()
  {
    LayoutInflater localLayoutInflater = LayoutInflater.from(this.context);
    this.decorView = ((ViewGroup)((Activity)this.context).getWindow().getDecorView().findViewById(16908290));
    this.rootView = ((ViewGroup)localLayoutInflater.inflate(2130968822, this.decorView, false));
    this.rootView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    this.contentContainer = ((ViewGroup)this.rootView.findViewById(2131559370));
    this.contentContainer.setLayoutParams(this.params);
  }

  public boolean isShowing()
  {
    return (this.rootView.getParent() != null) || (this.isShowing);
  }

  public BasePickerView setCancelable(boolean paramBoolean)
  {
    View localView = this.rootView.findViewById(2131559369);
    if (paramBoolean)
    {
      localView.setOnTouchListener(this.onCancelableTouchListener);
      return this;
    }
    localView.setOnTouchListener(null);
    return this;
  }

  public BasePickerView setOnDismissListener(OnDismissListener paramOnDismissListener)
  {
    this.onDismissListener = paramOnDismissListener;
    return this;
  }

  public void show()
  {
    if (isShowing())
      return;
    this.isShowing = true;
    onAttached(this.rootView);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.view.BasePickerView
 * JD-Core Version:    0.6.0
 */