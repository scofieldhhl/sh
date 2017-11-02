package com.ex.ltech.LogRegForget.demo;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.Locale;

public class CountryListView extends RelativeLayout
  implements View.OnTouchListener
{
  private CountryAdapter adapter;
  private LinearLayout llScroll;
  private GroupListView lvContries;
  private TextView tvScroll;

  public CountryListView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public CountryListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public CountryListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private int dipToPx(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }

  private void init(Context paramContext)
  {
    this.lvContries = new GroupListView(paramContext);
    this.lvContries.setDividerHeight(1);
    this.lvContries.setDivider(paramContext.getResources().getDrawable(2130837570));
    this.adapter = new CountryAdapter(paramContext, this.lvContries);
    this.lvContries.setAdapter(this.adapter);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
    int i = dipToPx(paramContext, 9.0F);
    localLayoutParams1.setMargins(i, 0, i, 0);
    addView(this.lvContries, localLayoutParams1);
    this.tvScroll = new TextView(paramContext);
    this.tvScroll.setTextColor(paramContext.getResources().getColor(2131492990));
    this.tvScroll.setBackgroundResource(2130837613);
    this.tvScroll.setTextSize(1, 48.0F);
    this.tvScroll.setTypeface(Typeface.DEFAULT);
    this.tvScroll.setVisibility(8);
    this.tvScroll.setGravity(17);
    int j = dipToPx(paramContext, 80.0F);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(j, j);
    localLayoutParams2.addRule(13);
    addView(this.tvScroll, localLayoutParams2);
    this.llScroll = new LinearLayout(paramContext);
    this.llScroll.setBackgroundResource(2130837614);
    this.llScroll.setOrientation(1);
    this.llScroll.setOnTouchListener(this);
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams3.addRule(11);
    localLayoutParams3.addRule(15);
    localLayoutParams3.rightMargin = dipToPx(paramContext, 5.0F);
    addView(this.llScroll, localLayoutParams3);
    initScroll(paramContext);
  }

  private void initScroll(Context paramContext)
  {
    this.llScroll.removeAllViews();
    int i = this.adapter.getGroupCount();
    int j = dipToPx(getContext(), 3.0F);
    int k = dipToPx(getContext(), 0.4F);
    for (int m = 0; m < i; m++)
    {
      TextView localTextView = new TextView(paramContext);
      localTextView.setTextColor(Color.parseColor("#fc3341"));
      localTextView.setText(this.adapter.getGroupTitle(m));
      localTextView.setGravity(17);
      localTextView.setPadding(j, k, j, k);
      this.llScroll.addView(localTextView);
    }
  }

  private boolean isZh()
  {
    Locale localLocale = getResources().getConfiguration().locale;
    String str1 = localLocale.getCountry();
    String str2 = localLocale.getLanguage();
    return (str2 + "_" + str1).equals("zh_CN");
  }

  public String[] getCountry(int paramInt1, int paramInt2)
  {
    return this.adapter.getItem(paramInt1, paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    getMeasuredHeight();
    getMeasuredHeight();
  }

  public void onScroll(ViewGroup paramViewGroup, float paramFloat1, float paramFloat2)
  {
    int i = 0;
    int j = paramViewGroup.getChildCount();
    while (true)
    {
      if (i < j)
      {
        TextView localTextView = (TextView)paramViewGroup.getChildAt(i);
        if ((paramFloat1 >= localTextView.getLeft()) && (paramFloat1 <= localTextView.getRight()) && (paramFloat2 >= localTextView.getTop()) && (paramFloat2 <= localTextView.getBottom()))
        {
          this.lvContries.setSelection(i);
          this.tvScroll.setVisibility(0);
          this.tvScroll.setText(localTextView.getText());
        }
      }
      else
      {
        return;
      }
      i++;
    }
  }

  public void onSearch(String paramString)
  {
    this.adapter.search(paramString);
    this.adapter.notifyDataSetChanged();
    if (this.adapter.getGroupCount() == 0)
      this.llScroll.setVisibility(8);
    while (true)
    {
      initScroll(getContext());
      return;
      this.llScroll.setVisibility(0);
    }
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      return true;
      float f3 = paramMotionEvent.getX();
      float f4 = paramMotionEvent.getY();
      onScroll((ViewGroup)paramView, f3, f4);
      continue;
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      onScroll((ViewGroup)paramView, f1, f2);
      continue;
      paramView.setBackgroundResource(2130837614);
      this.tvScroll.setVisibility(8);
    }
  }

  public void setOnItemClickListener(GroupListView.OnItemClickListener paramOnItemClickListener)
  {
    this.lvContries.setOnItemClickListener(paramOnItemClickListener);
  }

  public void setlocalItem2Frist(String paramString)
  {
    if (isZh())
      this.adapter.setlocalItem2Frist(paramString);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.demo.CountryListView
 * JD-Core Version:    0.6.0
 */