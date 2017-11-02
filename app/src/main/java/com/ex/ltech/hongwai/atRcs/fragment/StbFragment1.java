package com.ex.ltech.hongwai.atRcs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.hongwai.atRcs.AtNewStb;
import com.ex.ltech.hongwai.view.ImageTextButton;
import java.io.PrintStream;

public class StbFragment1 extends Fragment
  implements View.OnClickListener, View.OnLongClickListener
{

  @Bind({2131558858})
  ImageTextButton back;

  @Bind({2131559116})
  ImageTextButton chAdd;

  @Bind({2131559117})
  ImageTextButton chSub;

  @Bind({2131558969})
  ImageTextButton down;

  @Bind({2131559212})
  ImageView iv_dirver;

  @Bind({2131558458})
  ImageTextButton left;
  public View mRootView = null;

  @Bind({2131558970})
  ImageTextButton menu;

  @Bind({2131558968})
  ImageTextButton ok;

  @Bind({2131559213})
  ImageTextButton onOff;
  AtNewStb pAt;

  @Bind({2131559217})
  ImageTextButton playBack;

  @Bind({2131558459})
  ImageTextButton right;

  @Bind({2131558967})
  RelativeLayout rlAtProjection1;

  @Bind({2131559214})
  ImageTextButton stbOnOff;
  private String type;

  @Bind({2131558410})
  ImageTextButton up;

  @Bind({2131559215})
  ImageTextButton volAdd;

  @Bind({2131559216})
  ImageTextButton volSub;

  private void initListener()
  {
    this.pAt = ((AtNewStb)getActivity());
    this.up.setOnClickListener(this);
    this.down.setOnClickListener(this);
    this.left.setOnClickListener(this);
    this.right.setOnClickListener(this);
    this.ok.setOnClickListener(this);
    this.menu.setOnClickListener(this);
    this.onOff.setOnClickListener(this);
    this.stbOnOff.setOnClickListener(this);
    this.volAdd.setOnClickListener(this);
    this.volSub.setOnClickListener(this);
    this.back.setOnClickListener(this);
    this.chAdd.setOnClickListener(this);
    this.chSub.setOnClickListener(this);
    this.playBack.setOnClickListener(this);
    this.up.setImageTextButtonLongClickListener(this.pAt);
    this.down.setImageTextButtonLongClickListener(this.pAt);
    this.left.setImageTextButtonLongClickListener(this.pAt);
    this.right.setImageTextButtonLongClickListener(this.pAt);
    this.ok.setImageTextButtonLongClickListener(this.pAt);
    this.menu.setImageTextButtonLongClickListener(this.pAt);
    this.onOff.setImageTextButtonLongClickListener(this.pAt);
    this.stbOnOff.setImageTextButtonLongClickListener(this.pAt);
    this.volAdd.setImageTextButtonLongClickListener(this.pAt);
    this.volSub.setImageTextButtonLongClickListener(this.pAt);
    this.back.setImageTextButtonLongClickListener(this.pAt);
    this.chAdd.setImageTextButtonLongClickListener(this.pAt);
    this.chSub.setImageTextButtonLongClickListener(this.pAt);
    this.playBack.setImageTextButtonLongClickListener(this.pAt);
    setDirverListener();
  }

  private void setDirverListener()
  {
    this.ok.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        int i = 1;
        int j;
        if (paramMotionEvent.getAction() == i)
        {
          j = i;
          if (paramMotionEvent.getAction() != 3)
            break label48;
        }
        while (true)
        {
          if ((j | i) == 0)
            break label53;
          StbFragment1.this.iv_dirver.setBackgroundResource(2130903575);
          return false;
          j = 0;
          break;
          label48: i = 0;
        }
        label53: StbFragment1.this.iv_dirver.setBackgroundResource(2130903578);
        return false;
      }
    });
    this.up.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        int i = 1;
        int j;
        if (paramMotionEvent.getAction() == i)
        {
          j = i;
          if (paramMotionEvent.getAction() != 3)
            break label48;
        }
        while (true)
        {
          if ((j | i) == 0)
            break label53;
          StbFragment1.this.iv_dirver.setBackgroundResource(2130903575);
          return false;
          j = 0;
          break;
          label48: i = 0;
        }
        label53: StbFragment1.this.iv_dirver.setBackgroundResource(2130903580);
        return false;
      }
    });
    this.down.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        int i = 1;
        int j;
        if (paramMotionEvent.getAction() == i)
        {
          j = i;
          if (paramMotionEvent.getAction() != 3)
            break label48;
        }
        while (true)
        {
          if ((j | i) == 0)
            break label53;
          StbFragment1.this.iv_dirver.setBackgroundResource(2130903575);
          return false;
          j = 0;
          break;
          label48: i = 0;
        }
        label53: StbFragment1.this.iv_dirver.setBackgroundResource(2130903576);
        return false;
      }
    });
    this.left.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        int i = 1;
        int j;
        if (paramMotionEvent.getAction() == i)
        {
          j = i;
          if (paramMotionEvent.getAction() != 3)
            break label48;
        }
        while (true)
        {
          if ((j | i) == 0)
            break label53;
          StbFragment1.this.iv_dirver.setBackgroundResource(2130903575);
          return false;
          j = 0;
          break;
          label48: i = 0;
        }
        label53: StbFragment1.this.iv_dirver.setBackgroundResource(2130903577);
        return false;
      }
    });
    this.right.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        int i = 1;
        int j;
        if (paramMotionEvent.getAction() == i)
        {
          j = i;
          if (paramMotionEvent.getAction() != 3)
            break label48;
        }
        while (true)
        {
          if ((j | i) == 0)
            break label53;
          StbFragment1.this.iv_dirver.setBackgroundResource(2130903575);
          return false;
          j = 0;
          break;
          label48: i = 0;
        }
        label53: StbFragment1.this.iv_dirver.setBackgroundResource(2130903579);
        return false;
      }
    });
  }

  public void onClick(View paramView)
  {
    if (paramView == this.up)
      this.pAt.up();
    if (paramView == this.down)
      this.pAt.down();
    if (paramView == this.left)
      this.pAt.left();
    if (paramView == this.right)
      this.pAt.right();
    if (paramView == this.ok)
      this.pAt.ok();
    if (paramView == this.onOff)
      this.pAt.onOff();
    if (paramView == this.stbOnOff)
      this.pAt.stbOnOff();
    if (paramView == this.back)
      this.pAt.tvback();
    if (paramView == this.volAdd)
      this.pAt.volAdd();
    if (paramView == this.volSub)
      this.pAt.volStub();
    if (paramView == this.menu)
      this.pAt.menu();
    if (paramView == this.chAdd)
      this.pAt.chAdd();
    if (paramView == this.chSub)
      this.pAt.chStub();
    if (paramView == this.playBack)
      this.pAt.playBack();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mRootView = paramLayoutInflater.inflate(2130968767, paramViewGroup, false);
    System.out.println("onCreateView");
    ButterKnife.bind(this, this.mRootView);
    initListener();
    return this.mRootView;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public boolean onLongClick(View paramView)
  {
    return false;
  }

  public void onResume()
  {
    super.onResume();
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.fragment.StbFragment1
 * JD-Core Version:    0.6.0
 */