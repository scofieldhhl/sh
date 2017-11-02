package com.ex.ltech.hongwai.view.pickerview.view;

import android.content.Context;
import android.view.View;
import com.ex.ltech.hongwai.view.pickerview.TimePickerView.Type;
import com.ex.ltech.hongwai.view.pickerview.adapter.NumericWheelAdapter;
import com.ex.ltech.hongwai.view.pickerview.lib.WheelView;
import com.ex.ltech.hongwai.view.pickerview.listener.OnItemSelectedListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class WheelTime
{
  public static final int DEFULT_END_YEAR = 2100;
  public static final int DEFULT_START_YEAR = 1990;
  public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  private int endYear = 2100;
  private int startYear = 1990;
  private TimePickerView.Type type;
  private View view;
  private WheelView wv_day;
  private WheelView wv_hours;
  private WheelView wv_mins;
  private WheelView wv_month;
  private WheelView wv_year;

  public WheelTime(View paramView)
  {
    this.view = paramView;
    this.type = TimePickerView.Type.ALL;
    setView(paramView);
  }

  public WheelTime(View paramView, TimePickerView.Type paramType)
  {
    this.view = paramView;
    this.type = paramType;
    setView(paramView);
  }

  public int getEndYear()
  {
    return this.endYear;
  }

  public int getStartYear()
  {
    return this.startYear;
  }

  public String getTime()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this.wv_year.getCurrentItem() + this.startYear).append("-").append(1 + this.wv_month.getCurrentItem()).append("-").append(1 + this.wv_day.getCurrentItem()).append(" ").append(this.wv_hours.getCurrentItem()).append(":").append(this.wv_mins.getCurrentItem());
    return localStringBuffer.toString();
  }

  public View getView()
  {
    return this.view;
  }

  public void setCyclic(boolean paramBoolean)
  {
    this.wv_year.setCyclic(paramBoolean);
    this.wv_month.setCyclic(paramBoolean);
    this.wv_day.setCyclic(paramBoolean);
    this.wv_hours.setCyclic(paramBoolean);
    this.wv_mins.setCyclic(paramBoolean);
  }

  public void setEndYear(int paramInt)
  {
    this.endYear = paramInt;
  }

  public void setPicker(int paramInt1, int paramInt2, int paramInt3)
  {
    setPicker(paramInt1, paramInt2, paramInt3, 0, 0);
  }

  public void setPicker(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    String[] arrayOfString1 = { "1", "3", "5", "7", "8", "10", "12" };
    String[] arrayOfString2 = { "4", "6", "9", "11" };
    List localList1 = Arrays.asList(arrayOfString1);
    List localList2 = Arrays.asList(arrayOfString2);
    Context localContext = this.view.getContext();
    this.wv_year = ((WheelView)this.view.findViewById(2131559452));
    this.wv_year.setAdapter(new NumericWheelAdapter(this.startYear, this.endYear));
    this.wv_year.setLabel(localContext.getString(2131100271));
    this.wv_year.setCurrentItem(paramInt1 - this.startYear);
    this.wv_month = ((WheelView)this.view.findViewById(2131559453));
    this.wv_month.setAdapter(new NumericWheelAdapter(1, 12));
    this.wv_month.setLabel(localContext.getString(2131100269));
    this.wv_month.setCurrentItem(paramInt2);
    this.wv_day = ((WheelView)this.view.findViewById(2131559454));
    int i;
    if (localList1.contains(String.valueOf(paramInt2 + 1)))
    {
      this.wv_day.setAdapter(new NumericWheelAdapter(1, 31));
      this.wv_day.setLabel(localContext.getString(2131100266));
      this.wv_day.setCurrentItem(paramInt3 - 1);
      this.wv_hours = ((WheelView)this.view.findViewById(2131559455));
      this.wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
      this.wv_hours.setLabel(localContext.getString(2131100267));
      this.wv_hours.setCurrentItem(paramInt4);
      this.wv_mins = ((WheelView)this.view.findViewById(2131558903));
      this.wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
      this.wv_mins.setLabel(localContext.getString(2131100268));
      this.wv_mins.setCurrentItem(paramInt5);
      1 local1 = new OnItemSelectedListener(localList1, localList2)
      {
        public void onItemSelected(int paramInt)
        {
          int i = paramInt + WheelTime.this.startYear;
          int j;
          if (this.val$list_big.contains(String.valueOf(1 + WheelTime.this.wv_month.getCurrentItem())))
          {
            WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 31));
            j = 31;
          }
          while (true)
          {
            if (WheelTime.this.wv_day.getCurrentItem() > j - 1)
              WheelTime.this.wv_day.setCurrentItem(j - 1);
            return;
            if (this.val$list_little.contains(String.valueOf(1 + WheelTime.this.wv_month.getCurrentItem())))
            {
              WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 30));
              j = 30;
              continue;
            }
            if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0))
            {
              WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 29));
              j = 29;
              continue;
            }
            WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 28));
            j = 28;
          }
        }
      };
      2 local2 = new OnItemSelectedListener(localList1, localList2)
      {
        public void onItemSelected(int paramInt)
        {
          int i = paramInt + 1;
          int j;
          if (this.val$list_big.contains(String.valueOf(i)))
          {
            WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 31));
            j = 31;
          }
          while (true)
          {
            if (WheelTime.this.wv_day.getCurrentItem() > j - 1)
              WheelTime.this.wv_day.setCurrentItem(j - 1);
            return;
            if (this.val$list_little.contains(String.valueOf(i)))
            {
              WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 30));
              j = 30;
              continue;
            }
            if ((((WheelTime.this.wv_year.getCurrentItem() + WheelTime.this.startYear) % 4 == 0) && ((WheelTime.this.wv_year.getCurrentItem() + WheelTime.this.startYear) % 100 != 0)) || ((WheelTime.this.wv_year.getCurrentItem() + WheelTime.this.startYear) % 400 == 0))
            {
              WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 29));
              j = 29;
              continue;
            }
            WheelTime.this.wv_day.setAdapter(new NumericWheelAdapter(1, 28));
            j = 28;
          }
        }
      };
      this.wv_year.setOnItemSelectedListener(local1);
      this.wv_month.setOnItemSelectedListener(local2);
      i = 6;
      switch (3.$SwitchMap$com$ex$ltech$hongwai$view$pickerview$TimePickerView$Type[this.type.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
    }
    while (true)
    {
      this.wv_day.setTextSize(i);
      this.wv_month.setTextSize(i);
      this.wv_year.setTextSize(i);
      this.wv_hours.setTextSize(i);
      this.wv_mins.setTextSize(i);
      return;
      if (localList2.contains(String.valueOf(paramInt2 + 1)))
      {
        this.wv_day.setAdapter(new NumericWheelAdapter(1, 30));
        break;
      }
      if (((paramInt1 % 4 == 0) && (paramInt1 % 100 != 0)) || (paramInt1 % 400 == 0))
      {
        this.wv_day.setAdapter(new NumericWheelAdapter(1, 29));
        break;
      }
      this.wv_day.setAdapter(new NumericWheelAdapter(1, 28));
      break;
      int j;
      i *= 3;
      continue;
      j *= 4;
      this.wv_hours.setVisibility(8);
      this.wv_mins.setVisibility(8);
      continue;
      j *= 4;
      this.wv_year.setVisibility(8);
      this.wv_month.setVisibility(8);
      this.wv_day.setVisibility(8);
      continue;
      j *= 3;
      this.wv_year.setVisibility(8);
      continue;
      j *= 4;
      this.wv_day.setVisibility(8);
      this.wv_hours.setVisibility(8);
      this.wv_mins.setVisibility(8);
    }
  }

  public void setStartYear(int paramInt)
  {
    this.startYear = paramInt;
  }

  public void setView(View paramView)
  {
    this.view = paramView;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.view.WheelTime
 * JD-Core Version:    0.6.0
 */