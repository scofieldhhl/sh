package com.ex.ltech.hongwai.view.pickerview.view;

import android.view.View;
import com.ex.ltech.hongwai.view.pickerview.adapter.ArrayWheelAdapter;
import com.ex.ltech.hongwai.view.pickerview.lib.WheelView;
import com.ex.ltech.hongwai.view.pickerview.listener.OnItemSelectedListener;
import java.util.ArrayList;

public class WheelOptions<T>
{
  private boolean linkage = false;
  private ArrayList<T> mOptions1Items;
  private ArrayList<ArrayList<T>> mOptions2Items;
  private ArrayList<ArrayList<ArrayList<T>>> mOptions3Items;
  private View view;
  private OnItemSelectedListener wheelListener_option1;
  private OnItemSelectedListener wheelListener_option2;
  private WheelView wv_option1;
  private WheelView wv_option2;
  private WheelView wv_option3;

  public WheelOptions(View paramView)
  {
    this.view = paramView;
    setView(paramView);
  }

  private void itemSelected(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.mOptions2Items != null)
    {
      this.wv_option2.setAdapter(new ArrayWheelAdapter((ArrayList)this.mOptions2Items.get(paramInt1)));
      this.wv_option2.setCurrentItem(paramInt2);
    }
    if (this.mOptions3Items != null)
    {
      this.wv_option3.setAdapter(new ArrayWheelAdapter((ArrayList)((ArrayList)this.mOptions3Items.get(paramInt1)).get(paramInt2)));
      this.wv_option3.setCurrentItem(paramInt3);
    }
  }

  public int[] getCurrentItems()
  {
    int[] arrayOfInt = new int[3];
    arrayOfInt[0] = this.wv_option1.getCurrentItem();
    arrayOfInt[1] = this.wv_option2.getCurrentItem();
    arrayOfInt[2] = this.wv_option3.getCurrentItem();
    return arrayOfInt;
  }

  public View getView()
  {
    return this.view;
  }

  public void setCurrentItems(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.linkage)
      itemSelected(paramInt1, paramInt2, paramInt3);
    this.wv_option1.setCurrentItem(paramInt1);
    this.wv_option2.setCurrentItem(paramInt2);
    this.wv_option3.setCurrentItem(paramInt3);
  }

  public void setCyclic(boolean paramBoolean)
  {
    this.wv_option1.setCyclic(paramBoolean);
    this.wv_option2.setCyclic(paramBoolean);
    this.wv_option3.setCyclic(paramBoolean);
  }

  public void setCyclic(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.wv_option1.setCyclic(paramBoolean1);
    this.wv_option2.setCyclic(paramBoolean2);
    this.wv_option3.setCyclic(paramBoolean3);
  }

  public void setLabels(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 != null)
      this.wv_option1.setLabel(paramString1);
    if (paramString2 != null)
      this.wv_option2.setLabel(paramString2);
    if (paramString3 != null)
      this.wv_option3.setLabel(paramString3);
  }

  public void setOption2Cyclic(boolean paramBoolean)
  {
    this.wv_option2.setCyclic(paramBoolean);
  }

  public void setOption3Cyclic(boolean paramBoolean)
  {
    this.wv_option3.setCyclic(paramBoolean);
  }

  public void setPicker(ArrayList<T> paramArrayList)
  {
    setPicker(paramArrayList, null, null, false);
  }

  public void setPicker(ArrayList<T> paramArrayList, ArrayList<ArrayList<T>> paramArrayList1, ArrayList<ArrayList<ArrayList<T>>> paramArrayList2, boolean paramBoolean)
  {
    this.linkage = paramBoolean;
    this.mOptions1Items = paramArrayList;
    this.mOptions2Items = paramArrayList1;
    this.mOptions3Items = paramArrayList2;
    int i = 4;
    if (this.mOptions3Items == null)
      i = 8;
    if (this.mOptions2Items == null)
      i = 12;
    this.wv_option1 = ((WheelView)this.view.findViewById(2131559448));
    this.wv_option1.setAdapter(new ArrayWheelAdapter(this.mOptions1Items, i));
    this.wv_option1.setCurrentItem(0);
    this.wv_option2 = ((WheelView)this.view.findViewById(2131559449));
    if (this.mOptions2Items != null)
      this.wv_option2.setAdapter(new ArrayWheelAdapter((ArrayList)this.mOptions2Items.get(0)));
    this.wv_option2.setCurrentItem(this.wv_option1.getCurrentItem());
    this.wv_option3 = ((WheelView)this.view.findViewById(2131559450));
    if (this.mOptions3Items != null)
      this.wv_option3.setAdapter(new ArrayWheelAdapter((ArrayList)((ArrayList)this.mOptions3Items.get(0)).get(0)));
    this.wv_option3.setCurrentItem(this.wv_option3.getCurrentItem());
    this.wv_option1.setTextSize(20);
    this.wv_option2.setTextSize(20);
    this.wv_option3.setTextSize(20);
    if (this.mOptions2Items == null)
      this.wv_option2.setVisibility(8);
    if (this.mOptions3Items == null)
      this.wv_option3.setVisibility(8);
    this.wheelListener_option1 = new OnItemSelectedListener()
    {
      public void onItemSelected(int paramInt)
      {
        ArrayList localArrayList = WheelOptions.this.mOptions2Items;
        int i = 0;
        if (localArrayList != null)
        {
          i = WheelOptions.this.wv_option2.getCurrentItem();
          if (i >= -1 + ((ArrayList)WheelOptions.this.mOptions2Items.get(paramInt)).size())
            i = -1 + ((ArrayList)WheelOptions.this.mOptions2Items.get(paramInt)).size();
          WheelOptions.this.wv_option2.setAdapter(new ArrayWheelAdapter((ArrayList)WheelOptions.this.mOptions2Items.get(paramInt)));
          WheelOptions.this.wv_option2.setCurrentItem(i);
        }
        if (WheelOptions.this.mOptions3Items != null)
          WheelOptions.this.wheelListener_option2.onItemSelected(i);
      }
    };
    this.wheelListener_option2 = new OnItemSelectedListener()
    {
      public void onItemSelected(int paramInt)
      {
        if (WheelOptions.this.mOptions3Items != null)
        {
          int i = WheelOptions.this.wv_option1.getCurrentItem();
          if (i >= -1 + WheelOptions.this.mOptions3Items.size())
            i = -1 + WheelOptions.this.mOptions3Items.size();
          if (paramInt >= -1 + ((ArrayList)WheelOptions.this.mOptions2Items.get(i)).size())
            paramInt = -1 + ((ArrayList)WheelOptions.this.mOptions2Items.get(i)).size();
          int j = WheelOptions.this.wv_option3.getCurrentItem();
          if (j >= -1 + ((ArrayList)((ArrayList)WheelOptions.this.mOptions3Items.get(i)).get(paramInt)).size())
            j = -1 + ((ArrayList)((ArrayList)WheelOptions.this.mOptions3Items.get(i)).get(paramInt)).size();
          WheelOptions.this.wv_option3.setAdapter(new ArrayWheelAdapter((ArrayList)((ArrayList)WheelOptions.this.mOptions3Items.get(WheelOptions.this.wv_option1.getCurrentItem())).get(paramInt)));
          WheelOptions.this.wv_option3.setCurrentItem(j);
        }
      }
    };
    if ((paramArrayList1 != null) && (paramBoolean))
      this.wv_option1.setOnItemSelectedListener(this.wheelListener_option1);
    if ((paramArrayList2 != null) && (paramBoolean))
      this.wv_option2.setOnItemSelectedListener(this.wheelListener_option2);
  }

  public void setPicker(ArrayList<T> paramArrayList, ArrayList<ArrayList<T>> paramArrayList1, boolean paramBoolean)
  {
    setPicker(paramArrayList, paramArrayList1, null, paramBoolean);
  }

  public void setView(View paramView)
  {
    this.view = paramView;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.view.WheelOptions
 * JD-Core Version:    0.6.0
 */