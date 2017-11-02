package com.ex.ltech.hongwai.yaokong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.led.T;
import com.ex.ltech.led.my_view.MyTimePickerView2;
import java.util.ArrayList;

public class ItRcLvAdapter extends BaseAdapter
{
  private Context context;
  private LayoutInflater layoutInflater;
  private MyRcDevices rcDevices;
  ArrayList<String> wendu = new ArrayList();

  public ItRcLvAdapter(Context paramContext, MyRcDevices paramMyRcDevices)
  {
    this.context = paramContext;
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.rcDevices = paramMyRcDevices;
    this.wendu.add("16°");
    this.wendu.add("17°");
    this.wendu.add("18°");
    this.wendu.add("19°");
    this.wendu.add("20°");
    this.wendu.add("21°");
    this.wendu.add("22°");
    this.wendu.add("23°");
    this.wendu.add("24°");
    this.wendu.add("25°");
    this.wendu.add("26°");
    this.wendu.add("27°");
    this.wendu.add("28°");
    this.wendu.add("29°");
    this.wendu.add("30°");
  }

  private void initializeViews(T paramT, ViewHolder paramViewHolder)
  {
  }

  public int getCount()
  {
    return this.rcDevices.myRcDevices.size();
  }

  public Object getItem(int paramInt)
  {
    return this.rcDevices.myRcDevices.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.layoutInflater.inflate(2130968809, null);
      paramView.setTag(new ViewHolder(paramView));
    }
    initializeViews((T)getItem(paramInt), (ViewHolder)paramView.getTag());
    return paramView;
  }

  protected class ViewHolder
  {
    private ImageView arrow;
    private ImageView complexArrow;
    private ImageView complexIvIc;
    private Button complexMode1;
    private Button complexMode2;
    private Button complexMode3;
    private Button complexMode4;
    private TextView complexName;
    private MyTimePickerView2 complexWendu;
    private ImageView ivIc;
    private TextView name;
    private RelativeLayout rlComplex;
    private RelativeLayout rlSimple;

    public ViewHolder(View arg2)
    {
      Object localObject;
      this.rlSimple = ((RelativeLayout)localObject.findViewById(2131559317));
      this.ivIc = ((ImageView)localObject.findViewById(2131559290));
      this.name = ((TextView)localObject.findViewById(2131559008));
      this.arrow = ((ImageView)localObject.findViewById(2131559297));
      this.rlComplex = ((RelativeLayout)localObject.findViewById(2131559321));
      this.complexIvIc = ((ImageView)localObject.findViewById(2131559322));
      this.complexName = ((TextView)localObject.findViewById(2131559323));
      this.complexArrow = ((ImageView)localObject.findViewById(2131559277));
      this.complexMode1 = ((Button)localObject.findViewById(2131559278));
      this.complexMode2 = ((Button)localObject.findViewById(2131559279));
      this.complexMode3 = ((Button)localObject.findViewById(2131559280));
      this.complexMode4 = ((Button)localObject.findViewById(2131559281));
      this.complexWendu = ((MyTimePickerView2)localObject.findViewById(2131559282));
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.ItRcLvAdapter
 * JD-Core Version:    0.6.0
 */