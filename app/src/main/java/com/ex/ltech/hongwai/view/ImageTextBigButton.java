package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ex.ltech.led.R;
import com.zhy.android.percent.support.PercentLinearLayout;

public class ImageTextBigButton extends LinearLayout
{
  PercentLinearLayout bg;
  ImageTextButtonLongClickListener btLongClickListener;
  private boolean isShowPressBg = true;
  private ImageView iv;
  Drawable mDefaultDrawable;
  public String mName = "";
  public int mRes;
  int pressedRes;
  private TextView tv;

  public ImageTextBigButton(Context paramContext)
  {
    super(paramContext);
  }

  public ImageTextBigButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    LayoutInflater.from(paramContext).inflate(2130968788, this, true);
    this.iv = ((ImageView)findViewById(R.id.iv));
    this.tv = ((TextView)findViewById(2131559087));
    this.bg = ((PercentLinearLayout)findViewById(2131558840));
    setClickable(true);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ImageTextBigButton);
    int i = localTypedArray.getIndexCount();
    int j = 0;
    if (j < i)
    {
      int k = localTypedArray.getIndex(j);
      switch (k)
      {
      case 2:
      default:
        label144: if (this.mName.length() != 0)
          break;
        switch (k)
        {
        default:
        case 2:
        }
      case 0:
      case 1:
      case 3:
      case 4:
      }
      while (true)
      {
        j++;
        break;
        ImageView localImageView = this.iv;
        Drawable localDrawable = localTypedArray.getDrawable(k);
        this.mDefaultDrawable = localDrawable;
        localImageView.setImageDrawable(localDrawable);
        break label144;
        this.tv.setText(localTypedArray.getString(k));
        this.mName = this.tv.getText().toString();
        break label144;
        this.tv.setTextColor(localTypedArray.getColor(k, 255));
        break label144;
        this.isShowPressBg = localTypedArray.getBoolean(k, true);
        break label144;
        this.mName = localTypedArray.getString(k);
      }
    }
    localTypedArray.recycle();
    setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        int i = 1;
        int k;
        if (ImageTextBigButton.this.isShowPressBg)
        {
          if (ImageTextBigButton.this.pressedRes != 0)
            break label93;
          if (paramMotionEvent.getAction() != i)
            break label65;
          k = i;
          if (paramMotionEvent.getAction() != 3)
            break label71;
        }
        while (true)
        {
          if ((k | i) == 0)
            break label76;
          ImageTextBigButton.this.bg.setBackgroundColor(Color.parseColor("#00000000"));
          return false;
          label65: k = 0;
          break;
          label71: i = 0;
        }
        label76: ImageTextBigButton.this.bg.setBackgroundColor(Color.parseColor("#FFEAEAEA"));
        return false;
        label93: int j;
        if (paramMotionEvent.getAction() == i)
        {
          j = i;
          if (paramMotionEvent.getAction() != 3)
            break label144;
        }
        while (true)
        {
          if ((j | i) == 0)
            break label149;
          ImageTextBigButton.this.iv.setImageDrawable(ImageTextBigButton.this.mDefaultDrawable);
          return false;
          j = 0;
          break;
          label144: i = 0;
        }
        label149: ImageTextBigButton.this.iv.setImageDrawable(new BitmapDrawable(BitmapFactory.decodeResource(ImageTextBigButton.this.getResources(), ImageTextBigButton.this.pressedRes)));
        return false;
      }
    });
  }

  public int getPressedRes()
  {
    return this.pressedRes;
  }

  public void setBtnBitmap(Bitmap paramBitmap)
  {
    this.iv.setImageBitmap(paramBitmap);
  }

  public void setBtnRes(int paramInt)
  {
    this.iv.setImageResource(paramInt);
  }

  public void setBtnText(String paramString)
  {
    this.tv.setText(paramString);
  }

  public void setBtnTextColor(int paramInt)
  {
    this.tv.setTextColor(paramInt);
  }

  public void setImageTextButtonLongClickListener(ImageTextButtonLongClickListener paramImageTextButtonLongClickListener)
  {
    this.btLongClickListener = paramImageTextButtonLongClickListener;
    setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramView)
      {
        ImageTextBigButton.this.btLongClickListener.onImageTextButtonLongClick(ImageTextBigButton.this.mName);
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
 * Qualified Name:     com.ex.ltech.hongwai.view.ImageTextBigButton
 * JD-Core Version:    0.6.0
 */