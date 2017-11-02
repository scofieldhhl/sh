package com.ex.ltech.hongwai.view.pickerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ex.ltech.hongwai.view.pickerview.view.BasePickerView;
import com.ex.ltech.hongwai.view.pickerview.view.WheelTime;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class TimePickerView extends BasePickerView
  implements View.OnClickListener
{
  private static final String TAG_CANCEL = "cancel";
  private static final String TAG_SUBMIT = "submit";
  private View btnCancel;
  private View btnSubmit;
  private OnTimeSelectListener timeSelectListener;
  private TextView tvTitle;
  WheelTime wheelTime;

  public TimePickerView(Context paramContext, Type paramType)
  {
    super(paramContext);
    LayoutInflater.from(paramContext).inflate(2130968876, this.contentContainer);
    this.btnSubmit = findViewById(2131559276);
    this.btnSubmit.setTag("submit");
    this.btnCancel = findViewById(2131559274);
    this.btnCancel.setTag("cancel");
    this.btnSubmit.setOnClickListener(this);
    this.btnCancel.setOnClickListener(this);
    this.tvTitle = ((TextView)findViewById(2131559275));
    this.wheelTime = new WheelTime(findViewById(2131559451), paramType);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(System.currentTimeMillis());
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    int k = localCalendar.get(5);
    int m = localCalendar.get(11);
    int n = localCalendar.get(12);
    this.wheelTime.setPicker(i, j, k, m, n);
  }

  public void onClick(View paramView)
  {
    if (((String)paramView.getTag()).equals("cancel"))
    {
      dismiss();
      return;
    }
    if (this.timeSelectListener != null);
    try
    {
      Date localDate = WheelTime.dateFormat.parse(this.wheelTime.getTime());
      this.timeSelectListener.onTimeSelect(localDate);
      dismiss();
      return;
    }
    catch (ParseException localParseException)
    {
      while (true)
        localParseException.printStackTrace();
    }
  }

  public void setCyclic(boolean paramBoolean)
  {
    this.wheelTime.setCyclic(paramBoolean);
  }

  public void setOnTimeSelectListener(OnTimeSelectListener paramOnTimeSelectListener)
  {
    this.timeSelectListener = paramOnTimeSelectListener;
  }

  public void setRange(int paramInt1, int paramInt2)
  {
    this.wheelTime.setStartYear(paramInt1);
    this.wheelTime.setEndYear(paramInt2);
  }

  public void setTime(Date paramDate)
  {
    Calendar localCalendar = Calendar.getInstance();
    if (paramDate == null)
      localCalendar.setTimeInMillis(System.currentTimeMillis());
    while (true)
    {
      int i = localCalendar.get(1);
      int j = localCalendar.get(2);
      int k = localCalendar.get(5);
      int m = localCalendar.get(11);
      int n = localCalendar.get(12);
      this.wheelTime.setPicker(i, j, k, m, n);
      return;
      localCalendar.setTime(paramDate);
    }
  }

  public void setTitle(String paramString)
  {
    this.tvTitle.setText(paramString);
  }

  public static abstract interface OnTimeSelectListener
  {
    public abstract void onTimeSelect(Date paramDate);
  }

  public static enum Type
  {
    static
    {
      HOURS_MINS = new Type("HOURS_MINS", 2);
      MONTH_DAY_HOUR_MIN = new Type("MONTH_DAY_HOUR_MIN", 3);
      YEAR_MONTH = new Type("YEAR_MONTH", 4);
      Type[] arrayOfType = new Type[5];
      arrayOfType[0] = ALL;
      arrayOfType[1] = YEAR_MONTH_DAY;
      arrayOfType[2] = HOURS_MINS;
      arrayOfType[3] = MONTH_DAY_HOUR_MIN;
      arrayOfType[4] = YEAR_MONTH;
      $VALUES = arrayOfType;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.TimePickerView
 * JD-Core Version:    0.6.0
 */