package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ex.ltech.led.R.styleable;
import com.zhy.android.percent.support.PercentLinearLayout;

public class SmallImageTextButton extends LinearLayout
{
  TypedArray a;
  PercentLinearLayout bg;
  ImageTextButtonLongClickListener btLongClickListener;
  int defaultColor = Color.parseColor("#ffffff");
  boolean isShowPressBg = true;
  boolean isShowText = true;
  private ImageView iv;
  public String mName = "";
  private TextView tv;

  public SmallImageTextButton(Context paramContext)
  {
    super(paramContext);
  }

  public SmallImageTextButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    LayoutInflater.from(paramContext).inflate(2130968887, this, true);
    this.iv = ((ImageView)findViewById(R.id.iv));
    this.tv = ((TextView)findViewById(2131559087));
    this.bg = ((PercentLinearLayout)findViewById(2131558840));
    setClickable(true);
    this.a = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SmallImageTextButton);
    int i = this.a.getIndexCount();
    int j = 0;
    if (j < i)
    {
      int n = this.a.getIndex(j);
      switch (n)
      {
      case 2:
      default:
      case 0:
      case 1:
      case 3:
      case 4:
      case 5:
      }
      while (true)
      {
        j++;
        break;
        this.iv.setImageDrawable(this.a.getDrawable(n));
        continue;
        this.tv.setText(this.a.getString(n));
        this.mName = this.tv.getText().toString();
        continue;
        this.tv.setTextColor(this.a.getColor(n, 255));
        continue;
        this.isShowPressBg = this.a.getBoolean(n, true);
        continue;
        this.isShowText = this.a.getBoolean(n, true);
        if (this.isShowText)
          continue;
        this.tv.setVisibility(View.GONE);
      }
    }
    int k = 0;
    if (k < i)
    {
      int m = this.a.getIndex(k);
      switch (m)
      {
      default:
      case 2:
      }
      while (true)
      {
        k++;
        break;
        if (this.a.getString(m).length() <= 0)
          continue;
        this.mName = this.a.getString(m);
      }
    }
    this.a.recycle();
    setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        int i = 1;
        int j;
        if (SmallImageTextButton.this.isShowPressBg)
        {
          if (paramMotionEvent.getAction() != i)
            break label57;
          j = i;
          if (paramMotionEvent.getAction() != 3)
            break label63;
        }
        while (true)
        {
          if ((j | i) == 0)
            break label68;
          SmallImageTextButton.this.bg.setBackgroundColor(SmallImageTextButton.this.defaultColor);
          return false;
          label57: j = 0;
          break;
          label63: i = 0;
        }
        label68: SmallImageTextButton.this.bg.setBackgroundColor(Color.parseColor("#FFEAEAEA"));
        return false;
      }
    });
  }

  public void setGrayBg()
  {
    PercentLinearLayout localPercentLinearLayout = this.bg;
    int i = Color.parseColor("#7adedede");
    this.defaultColor = i;
    localPercentLinearLayout.setBackgroundColor(i);
  }

  public void setImageTextButtonLongClickListener(ImageTextButtonLongClickListener paramImageTextButtonLongClickListener)
  {
    this.btLongClickListener = paramImageTextButtonLongClickListener;
    setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramView)
      {
        SmallImageTextButton.this.btLongClickListener.onImageTextButtonLongClick(SmallImageTextButton.this.mName);
        return false;
      }
    });
  }

  public void setUnableBtn(int paramInt)
  {
    this.iv.setImageResource(paramInt);
    this.tv.setTextColor(Color.parseColor("#F0F0F0"));
  }

  public void setWhiteBg()
  {
    PercentLinearLayout localPercentLinearLayout = this.bg;
    int i = Color.parseColor("#ffffff");
    this.defaultColor = i;
    localPercentLinearLayout.setBackgroundColor(i);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.SmallImageTextButton
 * JD-Core Version:    0.6.0
 */