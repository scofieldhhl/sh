package com.ex.ltech.hongwai.view.pickerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ex.ltech.hongwai.view.pickerview.view.BasePickerView;
import com.ex.ltech.hongwai.view.pickerview.view.WheelOptions;
import java.util.ArrayList;

public class OptionsPickerView<T> extends BasePickerView
  implements View.OnClickListener
{
  private static final String TAG_CANCEL = "cancel";
  private static final String TAG_SUBMIT = "submit";
  private View btnCancel;
  private View btnSubmit;
  private OnOptionsSelectListener optionsSelectListener;
  private TextView tvTitle;
  WheelOptions<T> wheelOptions;

  public OptionsPickerView(Context paramContext)
  {
    super(paramContext);
    LayoutInflater.from(paramContext).inflate(2130968875, this.contentContainer);
    this.btnSubmit = findViewById(2131559276);
    this.btnSubmit.setTag("submit");
    this.btnCancel = findViewById(2131559274);
    this.btnCancel.setTag("cancel");
    this.btnSubmit.setOnClickListener(this);
    this.btnCancel.setOnClickListener(this);
    this.tvTitle = ((TextView)findViewById(2131559275));
    this.wheelOptions = new WheelOptions(findViewById(2131559447));
  }

  public void onClick(View paramView)
  {
    if (((String)paramView.getTag()).equals("cancel"))
    {
      dismiss();
      return;
    }
    if (this.optionsSelectListener != null)
    {
      int[] arrayOfInt = this.wheelOptions.getCurrentItems();
      this.optionsSelectListener.onOptionsSelect(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2]);
    }
    dismiss();
  }

  public void setCyclic(boolean paramBoolean)
  {
    this.wheelOptions.setCyclic(paramBoolean);
  }

  public void setCyclic(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.wheelOptions.setCyclic(paramBoolean1, paramBoolean2, paramBoolean3);
  }

  public void setLabels(String paramString)
  {
    this.wheelOptions.setLabels(paramString, null, null);
  }

  public void setLabels(String paramString1, String paramString2)
  {
    this.wheelOptions.setLabels(paramString1, paramString2, null);
  }

  public void setLabels(String paramString1, String paramString2, String paramString3)
  {
    this.wheelOptions.setLabels(paramString1, paramString2, paramString3);
  }

  public void setOnoptionsSelectListener(OnOptionsSelectListener paramOnOptionsSelectListener)
  {
    this.optionsSelectListener = paramOnOptionsSelectListener;
  }

  public void setPicker(ArrayList<T> paramArrayList)
  {
    this.wheelOptions.setPicker(paramArrayList, null, null, false);
  }

  public void setPicker(ArrayList<T> paramArrayList, ArrayList<ArrayList<T>> paramArrayList1, ArrayList<ArrayList<ArrayList<T>>> paramArrayList2, boolean paramBoolean)
  {
    this.wheelOptions.setPicker(paramArrayList, paramArrayList1, paramArrayList2, paramBoolean);
  }

  public void setPicker(ArrayList<T> paramArrayList, ArrayList<ArrayList<T>> paramArrayList1, boolean paramBoolean)
  {
    this.wheelOptions.setPicker(paramArrayList, paramArrayList1, null, paramBoolean);
  }

  public void setSelectOptions(int paramInt)
  {
    this.wheelOptions.setCurrentItems(paramInt, 0, 0);
  }

  public void setSelectOptions(int paramInt1, int paramInt2)
  {
    this.wheelOptions.setCurrentItems(paramInt1, paramInt2, 0);
  }

  public void setSelectOptions(int paramInt1, int paramInt2, int paramInt3)
  {
    this.wheelOptions.setCurrentItems(paramInt1, paramInt2, paramInt3);
  }

  public void setTitle(String paramString)
  {
    this.tvTitle.setText(paramString);
  }

  public static abstract interface OnOptionsSelectListener
  {
    public abstract void onOptionsSelect(int paramInt1, int paramInt2, int paramInt3);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.OptionsPickerView
 * JD-Core Version:    0.6.0
 */