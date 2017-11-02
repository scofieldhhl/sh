package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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

public class NoTextButton extends LinearLayout
{
  PercentLinearLayout bg;
  ImageTextButtonLongClickListener btLongClickListener;
  private ImageView iv;
  Drawable mDefaultDrawable;
  String mName = "";
  int pressedRes;
  private TextView tv;

  public NoTextButton(Context paramContext)
  {
    super(paramContext);
  }

  public NoTextButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    LayoutInflater.from(paramContext).inflate(2130968864, this, true);
    this.iv = ((ImageView)findViewById(2131558995));
    this.tv = ((TextView)findViewById(2131559087));
    this.bg = ((PercentLinearLayout)findViewById(2131558840));
    setClickable(true);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.NoTextButton);
    int i = localTypedArray.getIndexCount();
    int j = 0;
    if (j < i)
    {
      int k = localTypedArray.getIndex(j);
      switch (k)
      {
      case 2:
      default:
        label136: if (this.mName.length() != 0)
          break;
        switch (k)
        {
        default:
        case 2:
        }
      case 0:
      case 1:
      case 3:
      }
      while (true)
      {
        j++;
        break;
        ImageView localImageView = this.iv;
        Drawable localDrawable = localTypedArray.getDrawable(k);
        this.mDefaultDrawable = localDrawable;
        localImageView.setImageDrawable(localDrawable);
        break label136;
        this.tv.setText(localTypedArray.getString(k));
        this.mName = this.tv.getText().toString();
        break label136;
        this.tv.setTextColor(localTypedArray.getColor(k, 255));
        break label136;
        this.mName = localTypedArray.getString(k);
      }
    }
    localTypedArray.recycle();
    setOnTouchListener(new View.OnTouchListener(paramContext)
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        int i = 1;
        if (NoTextButton.this.pressedRes == 0)
        {
          int k;
          if (paramMotionEvent.getAction() == i)
          {
            k = i;
            if (paramMotionEvent.getAction() != 3)
              break label61;
          }
          while (true)
          {
            if ((k | i) == 0)
              break label66;
            NoTextButton.this.bg.setBackgroundColor(Color.parseColor("#00000000"));
            return false;
            k = 0;
            break;
            label61: i = 0;
          }
          label66: NoTextButton.this.bg.setBackgroundColor(Color.parseColor("#FFEAEAEA"));
          return false;
        }
        int j;
        if (paramMotionEvent.getAction() == i)
        {
          j = i;
          if (paramMotionEvent.getAction() != 3)
            break label134;
        }
        while (true)
        {
          if ((j | i) == 0)
            break label139;
          NoTextButton.this.iv.setImageDrawable(NoTextButton.this.mDefaultDrawable);
          return false;
          j = 0;
          break;
          label134: i = 0;
        }
        label139: NoTextButton.this.iv.setImageDrawable(new BitmapDrawable(BitmapFactory.decodeResource(this.val$context.getResources(), NoTextButton.this.pressedRes)));
        return false;
      }
    });
  }

  public int getPressedRes()
  {
    return this.pressedRes;
  }

  public void setImageTextButtonLongClickListener(ImageTextButtonLongClickListener paramImageTextButtonLongClickListener)
  {
    this.btLongClickListener = paramImageTextButtonLongClickListener;
    setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramView)
      {
        NoTextButton.this.btLongClickListener.onImageTextButtonLongClick(NoTextButton.this.mName);
        return false;
      }
    });
  }

  public void setPressedRes(int paramInt)
  {
    this.pressedRes = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.NoTextButton
 * JD-Core Version:    0.6.0
 */