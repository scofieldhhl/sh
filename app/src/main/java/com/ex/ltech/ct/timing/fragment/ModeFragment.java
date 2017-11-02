package com.ex.ltech.ct.timing.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ex.ltech.ct.ColorBussiness;
import com.ex.ltech.led.acti.mode.ModeBusiness;
import com.ex.ltech.led.my_view.MLImageView;
import com.ex.ltech.led.vo.CtSceneVo;
import java.io.PrintStream;
import java.util.List;

public class ModeFragment extends Fragment
  implements AdapterView.OnItemClickListener
{
  ColorBussiness colorBussiness;
  public List<CtSceneVo> ctSceneVos;
  private ListView lv_f_timing_mode;
  private View mRootView = null;
  private MyAdapter madapter;
  private ModeBusiness modeBusiness;
  public String modeName;

  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.colorBussiness = new ColorBussiness(getActivity());
    this.colorBussiness.loadCtSceneVos();
    this.lv_f_timing_mode = ((ListView)this.mRootView.findViewById(2131559138));
    this.madapter = new MyAdapter(getActivity());
    this.lv_f_timing_mode.setAdapter(this.madapter);
    this.lv_f_timing_mode.setOnItemClickListener(this);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mRootView = paramLayoutInflater.inflate(2130968740, paramViewGroup, false);
    System.out.println("onCreateView");
    return this.mRootView;
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    for (int i = 0; i < 8; i++)
    {
      CtSceneVo localCtSceneVo2 = (CtSceneVo)this.colorBussiness.vos.get(i);
      localCtSceneVo2.setIsEdit(false);
      this.colorBussiness.vos.remove(i);
      this.colorBussiness.vos.add(i, localCtSceneVo2);
    }
    CtSceneVo localCtSceneVo1 = (CtSceneVo)this.colorBussiness.vos.get(paramInt);
    boolean bool1 = localCtSceneVo1.isEdit();
    boolean bool2 = false;
    if (!bool1)
      bool2 = true;
    localCtSceneVo1.setIsEdit(bool2);
    this.colorBussiness.vos.remove(paramInt);
    this.colorBussiness.vos.add(paramInt, localCtSceneVo1);
    this.ctSceneVos = this.colorBussiness.vos;
    this.modeName = ((CtSceneVo)this.ctSceneVos.get(paramInt)).getName();
    this.madapter.notifyDataSetChanged();
  }

  class MyAdapter extends BaseAdapter
  {
    private Activity pct;
    int[] reses = { 2130903158, 2130903159, 2130903160, 2130903161, 2130903162, 2130903163, 2130903164, 2130903165 };

    public MyAdapter(Activity arg2)
    {
      Object localObject;
      this.pct = localObject;
    }

    public int getCount()
    {
      return ModeFragment.this.colorBussiness.vos.size();
    }

    public Object getItem(int paramInt)
    {
      return ModeFragment.this.colorBussiness.vos.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Holder localHolder;
      CtSceneVo localCtSceneVo;
      label145: int i;
      if (paramView == null)
      {
        localHolder = new Holder();
        this.pct.getLayoutInflater();
        paramView = LayoutInflater.from(this.pct).inflate(2130968836, null);
        localHolder.iv_acti_timing_list_item_1 = ((MLImageView)paramView.findViewById(2131559393));
        localHolder.tv_acti_timing_list_item_2 = ((TextView)paramView.findViewById(2131559394));
        localHolder.iv_acti_timing_list_item_3 = ((ImageView)paramView.findViewById(2131559402));
        paramView.setTag(localHolder);
        localCtSceneVo = (CtSceneVo)ModeFragment.this.colorBussiness.vos.get(paramInt);
        localHolder.tv_acti_timing_list_item_2.setText(localCtSceneVo.getName());
        if (!localCtSceneVo.getIcPath().equals(""))
          break label213;
        localHolder.iv_acti_timing_list_item_1.setBackgroundResource(this.reses[paramInt]);
        ImageView localImageView = localHolder.iv_acti_timing_list_item_3;
        if (!localCtSceneVo.isEdit())
          break label232;
        i = 2130903276;
        label164: localImageView.setBackgroundResource(i);
        if (!localCtSceneVo.isEdit())
          break label239;
      }
      label213: label232: label239: for (int j = this.pct.getResources().getColor(2131492927); ; j = this.pct.getResources().getColor(2131492997))
      {
        paramView.setBackgroundColor(j);
        return paramView;
        localHolder = (Holder)paramView.getTag();
        break;
        localHolder.iv_acti_timing_list_item_1.setImageBitmap(BitmapFactory.decodeFile(localCtSceneVo.getIcPath()));
        break label145;
        i = 2130903277;
        break label164;
      }
    }

    class Holder
    {
      MLImageView iv_acti_timing_list_item_1;
      ImageView iv_acti_timing_list_item_3;
      TextView tv_acti_timing_list_item_2;

      Holder()
      {
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.ct.timing.fragment.ModeFragment
 * JD-Core Version:    0.6.0
 */