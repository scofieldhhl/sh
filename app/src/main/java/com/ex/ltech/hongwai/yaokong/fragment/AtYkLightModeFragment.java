package com.ex.ltech.hongwai.yaokong.fragment;

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
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.ct.ColorBussiness;
import com.ex.ltech.hongwai.yaokong.AtYkLightMode;
import com.ex.ltech.led.my_view.MLImageView;
import com.ex.ltech.led.vo.CtSceneVo;
import java.util.List;

public class AtYkLightModeFragment extends Fragment
  implements AdapterView.OnItemClickListener
{
  ColorBussiness colorBussiness;
  public List<CtSceneVo> ctSceneVos;

  @Bind({2131559138})
  ListView lv_f_timing_mode;
  private View mRootView = null;
  private MyAdapter madapter;
  public String modeName;

  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.colorBussiness = new ColorBussiness(getActivity());
    this.colorBussiness.loadCtSceneVos();
    this.madapter = new MyAdapter(getActivity());
    this.lv_f_timing_mode.setAdapter(this.madapter);
    this.lv_f_timing_mode.setOnItemClickListener(this);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mRootView = paramLayoutInflater.inflate(2130968741, paramViewGroup, false);
    ButterKnife.bind(this, this.mRootView);
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
    ((AtYkLightMode)getActivity()).ctTimingAction.scenePosi = paramInt;
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
      return AtYkLightModeFragment.this.colorBussiness.vos.size();
    }

    public Object getItem(int paramInt)
    {
      return AtYkLightModeFragment.this.colorBussiness.vos.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      ViewHolder localViewHolder;
      CtSceneVo localCtSceneVo;
      if (paramView == null)
      {
        this.pct.getLayoutInflater();
        paramView = LayoutInflater.from(this.pct).inflate(2130968832, paramViewGroup, false);
        localViewHolder = new ViewHolder(paramView);
        localViewHolder.ivActiTimingListItem1 = ((MLImageView)paramView.findViewById(2131559393));
        localViewHolder.tvActiTimingListItem2 = ((TextView)paramView.findViewById(2131559394));
        paramView.setTag(localViewHolder);
        localCtSceneVo = (CtSceneVo)AtYkLightModeFragment.this.colorBussiness.vos.get(paramInt);
        localViewHolder.tvActiTimingListItem2.setText(localCtSceneVo.getName());
        if (!localCtSceneVo.getIcPath().equals(""))
          break label175;
        localViewHolder.ivActiTimingListItem1.setBackgroundResource(this.reses[paramInt]);
        label133: if (!localCtSceneVo.isEdit())
          break label194;
      }
      label175: label194: for (int i = this.pct.getResources().getColor(R.color.gray); ; i = this.pct.getResources().getColor(R.color.white))
      {
        paramView.setBackgroundColor(i);
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
        localViewHolder.ivActiTimingListItem1.setImageBitmap(BitmapFactory.decodeFile(localCtSceneVo.getIcPath()));
        break label133;
      }
    }

    class ViewHolder
    {

      @Bind({2131559393})
      MLImageView ivActiTimingListItem1;

      @Bind({2131559394})
      TextView tvActiTimingListItem2;

      ViewHolder(View arg2)
      {
        View localView;
        ButterKnife.bind(this, localView);
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.fragment.AtYkLightModeFragment
 * JD-Core Version:    0.6.0
 */