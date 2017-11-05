package com.ex.ltech.hongwai.atRcs.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.ex.ltech.hongwai.atRcs.AtNewTv;
import com.ex.ltech.hongwai.view.ImageTextButton;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.kookong.app.data.api.IrData;
import com.kookong.app.data.api.IrData.IrKey;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewTvFragment2 extends Fragment
{
  ItFtFanRc2Adapter adapter;
  List<IrData.IrKey> fanNotNormalKey = new ArrayList();
  private GridView gvDiy;
  List<IrData.IrKey> one2nineKey = new ArrayList();
  AtNewTv pat;

  public void addone2nineToFrist(List<IrData.IrKey> paramList1, List<IrData.IrKey> paramList2, String paramString)
  {
    Iterator localIterator = paramList2.iterator();
    while (localIterator.hasNext())
    {
      IrData.IrKey localIrKey = (IrData.IrKey)localIterator.next();
      if (!localIrKey.fname.equals(paramString))
        continue;
      localIterator.remove();
      paramList1.add(localIrKey);
    }
  }

  public void notifyDataSetChangedAdt()
  {
    this.one2nineKey.clear();
    this.fanNotNormalKey.clear();
    for (int i = 0; i < ((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.size(); i++)
    {
      if ((((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("上")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("下")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("左")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("右")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("确认")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("电源")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("静音")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("返回")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("频道+")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("频道-")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("菜单")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("信号源")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("音量+")) || (((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("音量-")))
        continue;
      this.fanNotNormalKey.add(((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i));
      System.out.println(((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname);
    }
    addone2nineToFrist(this.one2nineKey, this.fanNotNormalKey, "1");
    addone2nineToFrist(this.one2nineKey, this.fanNotNormalKey, "2");
    addone2nineToFrist(this.one2nineKey, this.fanNotNormalKey, "3");
    addone2nineToFrist(this.one2nineKey, this.fanNotNormalKey, "4");
    addone2nineToFrist(this.one2nineKey, this.fanNotNormalKey, "5");
    addone2nineToFrist(this.one2nineKey, this.fanNotNormalKey, "6");
    addone2nineToFrist(this.one2nineKey, this.fanNotNormalKey, "7");
    addone2nineToFrist(this.one2nineKey, this.fanNotNormalKey, "8");
    addone2nineToFrist(this.one2nineKey, this.fanNotNormalKey, "9");
    addone2nineToFrist(this.one2nineKey, this.fanNotNormalKey, "0");
    addone2nineToFrist(this.one2nineKey, this.fanNotNormalKey, "-/--");
    this.fanNotNormalKey.addAll(0, this.one2nineKey);
    this.gvDiy.setAdapter(this.adapter);
    this.adapter.notifyDataSetChanged();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130968749, null);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.pat = ((AtNewTv)getActivity());
    this.gvDiy = ((GridView)paramView.findViewById(2131558819));
    this.adapter = new ItFtFanRc2Adapter(getActivity());
    this.pat.fragmentCreateOk();
  }

  public void setAdt()
  {
    this.gvDiy.setAdapter(this.adapter);
  }

  public class ItFtFanRc2Adapter extends BaseAdapter
  {
    private Context context;
    private LayoutInflater layoutInflater;

    public ItFtFanRc2Adapter(Context arg2)
    {
      Context localContext;
      this.context = localContext;
      this.layoutInflater = LayoutInflater.from(localContext);
    }

    private void initializeViews(IrData.IrKey paramIrKey, ViewHolder paramViewHolder, int paramInt, View paramView)
    {
      paramViewHolder.btDiy.setVisibility(View.VISIBLE);
      paramViewHolder.bt_image_text.setVisibility(View.GONE);
      paramViewHolder.btDiy.setText(paramIrKey.fname);
      paramViewHolder.btDiy.setOnTouchListener(new NewTvFragment2.MyOnTouchListener(NewTvFragment2.this, paramViewHolder.btDiy));
      paramViewHolder.btDiy.setOnClickListener(new View.OnClickListener(paramIrKey)
      {
        public void onClick(View paramView)
        {
          NewTvFragment2.this.pat.sendRcCodeByChineseName(this.val$irKey.fname);
        }
      });
      paramViewHolder.btDiy.setOnLongClickListener(new View.OnLongClickListener(paramIrKey)
      {
        public boolean onLongClick(View paramView)
        {
          NewTvFragment2.this.pat.showLearn(this.val$irKey.fname);
          return false;
        }
      });
    }

    public int getCount()
    {
      return NewTvFragment2.this.fanNotNormalKey.size();
    }

    public IrData.IrKey getItem(int paramInt)
    {
      return (IrData.IrKey)NewTvFragment2.this.fanNotNormalKey.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968795, null);
        paramView.setTag(new ViewHolder(paramView));
      }
      initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag(), paramInt, paramView);
      return paramView;
    }

    protected class ViewHolder
    {
      private TextView btDiy;
      private ImageTextButton bt_image_text;

      public ViewHolder(View arg2)
      {
        Object localObject;
        this.btDiy = ((TextView)localObject.findViewById(2131559292));
        this.bt_image_text = ((ImageTextButton)localObject.findViewById(2131559293));
      }
    }
  }

  class MyOnTouchListener
    implements View.OnTouchListener
  {
    View view;

    public MyOnTouchListener(View arg2)
    {
      Object localObject;
      this.view = localObject;
    }

    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      int i = 1;
      int j;
      if (paramMotionEvent.getAction() == i)
      {
        j = i;
        if (paramMotionEvent.getAction() != 3)
          break label45;
      }
      while (true)
      {
        if ((j | i) == 0)
          break label50;
        this.view.setBackgroundResource(2130903795);
        return false;
        j = 0;
        break;
        label45: i = 0;
      }
      label50: this.view.setBackgroundResource(2130903796);
      return false;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.fragment.NewTvFragment2
 * JD-Core Version:    0.6.0
 */