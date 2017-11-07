package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

public class ImageTextButton2 extends LinearLayout
{
  TypedArray a;
  PercentLinearLayout bg;
  ImageTextButtonLongClickListener btLongClickListener;
  int defaultColor = Color.parseColor("#ffffff");
  private Drawable defaultDrawable;
  boolean isShowPressBg = true;
  boolean isShowText = true;
  public boolean isUnableBtn;
  private ImageView iv;
  public String mName = "";
  private Drawable pressDrawable;
  private TextView tv;

  public ImageTextButton2(Context paramContext)
  {
    super(paramContext);
  }

  public ImageTextButton2(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    LayoutInflater.from(paramContext).inflate(2130968789, this, true);
    this.iv = ((ImageView)findViewById(R.id.iv));
    this.tv = ((TextView)findViewById(2131559087));
    this.bg = ((PercentLinearLayout)findViewById(2131558840));
    setClickable(true);
    this.a = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ImageTextButton2);
    int i = this.a.getIndexCount();
    int j = 0;
    if (j < i)
    {
      int n = this.a.getIndex(j);
      switch (n)
      {
      default:
      case 0:
      case 1:
      case 2:
      }
      while (true)
      {
        j++;
        break;
        ImageView localImageView2 = this.iv;
        Drawable localDrawable2 = this.a.getDrawable(n);
        this.defaultDrawable = localDrawable2;
        localImageView2.setImageDrawable(localDrawable2);
        continue;
        ImageView localImageView1 = this.iv;
        Drawable localDrawable1 = this.a.getDrawable(n);
        this.pressDrawable = localDrawable1;
        localImageView1.setImageDrawable(localDrawable1);
        continue;
        this.tv.setText(this.a.getString(n));
        this.mName = this.tv.getText().toString();
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
        if (paramMotionEvent.getAction() == i)
        {
          j = i;
          if (paramMotionEvent.getAction() != 3)
            break label65;
        }
        while (true)
        {
          if ((j | i) == 0)
            break label70;
          ImageTextButton2.this.iv.setImageDrawable(ImageTextButton2.this.defaultDrawable);
          ImageTextButton2.this.tv.setTextColor(-16777216);
          return false;
          j = 0;
          break;
          label65: i = 0;
        }
        label70: ImageTextButton2.this.iv.setImageDrawable(ImageTextButton2.this.pressDrawable);
        ImageTextButton2.this.tv.setTextColor(-7829368);
        return false;
      }
    });
  }

  public void reset()
  {
    this.iv.setImageDrawable(this.defaultDrawable);
    this.tv.setTextColor(Color.parseColor("#000000"));
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
        ImageTextButton2.this.btLongClickListener.onImageTextButtonLongClick(ImageTextButton2.this.mName);
        return false;
      }
    });
  }

  public void setTextAndIc(int paramInt1, int paramInt2)
  {
    this.tv.setText(paramInt1);
    this.iv.setBackgroundResource(paramInt2);
    this.mName = this.tv.getText().toString();
  }

  public void setUnableBtn(int paramInt)
  {
    this.isUnableBtn = true;
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
 * Qualified Name:     com.ex.ltech.hongwai.view.ImageTextButton2
 * JD-Core Version:    0.6.0
 */