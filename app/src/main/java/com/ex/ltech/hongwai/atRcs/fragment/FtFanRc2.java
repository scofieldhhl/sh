package com.ex.ltech.hongwai.atRcs.fragment;

import android.content.Context;
import android.graphics.Color;
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
import com.ex.ltech.hongwai.atRcs.AtFan;
import com.ex.ltech.hongwai.view.ImageTextButton;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.kookong.app.data.api.IrData;
import com.kookong.app.data.api.IrData.IrKey;
import java.util.ArrayList;
import java.util.List;

public class FtFanRc2 extends Fragment
{
  ItFtFanRc2Adapter adapter;
  private GridView gvDiy;
  AtFan pat;

  public void notifyDataSetChangedAdt()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int i = 0;
    if (i < ((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.size())
    {
      if ((!((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("电源")) && (!((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("风速")) && (!((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("风类")) && (!((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("定时")) && (!((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("静音")) && (!((IrData.IrKey)((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i)).fname.equals("摆风")))
        localArrayList2.add(((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i));
      while (true)
      {
        i++;
        break;
        localArrayList1.add(((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.get(i));
      }
    }
    ((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.clear();
    ((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.addAll(localArrayList1);
    ((IrData)((MyRcDevice)this.pat.rcDevices.myRcDevices.get(this.pat.existRcPosi)).irDatas.get(0)).keys.addAll(localArrayList2);
    this.gvDiy.setAdapter(this.adapter);
    this.adapter.notifyDataSetChanged();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130968748, null);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.pat = ((AtFan)getActivity());
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
      if ((!((IrData.IrKey)((IrData)((MyRcDevice)FtFanRc2.this.pat.rcDevices.myRcDevices.get(FtFanRc2.this.pat.existRcPosi)).irDatas.get(0)).keys.get(paramInt)).fname.equals("电源")) && (!((IrData.IrKey)((IrData)((MyRcDevice)FtFanRc2.this.pat.rcDevices.myRcDevices.get(FtFanRc2.this.pat.existRcPosi)).irDatas.get(0)).keys.get(paramInt)).fname.equals("风速")) && (!((IrData.IrKey)((IrData)((MyRcDevice)FtFanRc2.this.pat.rcDevices.myRcDevices.get(FtFanRc2.this.pat.existRcPosi)).irDatas.get(0)).keys.get(paramInt)).fname.equals("风类")) && (!((IrData.IrKey)((IrData)((MyRcDevice)FtFanRc2.this.pat.rcDevices.myRcDevices.get(FtFanRc2.this.pat.existRcPosi)).irDatas.get(0)).keys.get(paramInt)).fname.equals("定时")) && (!((IrData.IrKey)((IrData)((MyRcDevice)FtFanRc2.this.pat.rcDevices.myRcDevices.get(FtFanRc2.this.pat.existRcPosi)).irDatas.get(0)).keys.get(paramInt)).fname.equals("静音")) && (!((IrData.IrKey)((IrData)((MyRcDevice)FtFanRc2.this.pat.rcDevices.myRcDevices.get(FtFanRc2.this.pat.existRcPosi)).irDatas.get(0)).keys.get(paramInt)).fname.equals("摆风")))
      {
        paramViewHolder.btDiy.setVisibility(View.VISIBLE);
        paramViewHolder.bt_image_text.setVisibility(View.GONE);
        paramViewHolder.btDiy.setText(paramIrKey.fname);
        paramViewHolder.btDiy.setOnTouchListener(new FtFanRc2.MyOnTouchListener(FtFanRc2.this, paramView.findViewById(2131558559)));
        paramViewHolder.btDiy.setOnClickListener(new View.OnClickListener(paramIrKey)
        {
          public void onClick(View paramView)
          {
            FtFanRc2.this.pat.sendRcCodeByChineseName(this.val$irKey.fname);
          }
        });
        paramViewHolder.btDiy.setOnLongClickListener(new View.OnLongClickListener(paramIrKey)
        {
          public boolean onLongClick(View paramView)
          {
            FtFanRc2.this.pat.showLearn(this.val$irKey.fname);
            return false;
          }
        });
        return;
      }
      paramViewHolder.btDiy.setVisibility(View.GONE);
      paramViewHolder.bt_image_text.setVisibility(View.VISIBLE);
      paramViewHolder.bt_image_text.setImageTextButtonLongClickListener(FtFanRc2.this.pat);
      paramViewHolder.bt_image_text.setOnTouchListener(new FtFanRc2.MyOnTouchListener(FtFanRc2.this, paramView.findViewById(2131558559)));
      String str = ((IrData.IrKey)((IrData)((MyRcDevice)FtFanRc2.this.pat.rcDevices.myRcDevices.get(FtFanRc2.this.pat.existRcPosi)).irDatas.get(0)).keys.get(paramInt)).fname;
      int i = -1;
      switch (str.hashCode())
      {
      default:
        switch (i)
        {
        default:
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        }
      case 958459:
      case 1249553:
      case 1244525:
      case 753052:
      case 1239994:
      case 834888:
      }
      while (true)
      {
        paramViewHolder.bt_image_text.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            String str = ((IrData.IrKey)((IrData)((MyRcDevice)FtFanRc2.this.pat.rcDevices.myRcDevices.get(FtFanRc2.this.pat.existRcPosi)).irDatas.get(0)).keys.get(this.val$i)).fname;
            int i = -1;
            switch (str.hashCode())
            {
            default:
            case 958459:
            case 1249553:
            case 1244525:
            case 753052:
            case 1239994:
            case 834888:
            }
            while (true)
              switch (i)
              {
              default:
                return;
                if (!str.equals("电源"))
                  continue;
                i = 0;
                continue;
                if (!str.equals("风速"))
                  continue;
                i = 1;
                continue;
                if (!str.equals("风类"))
                  continue;
                i = 2;
                continue;
                if (!str.equals("定时"))
                  continue;
                i = 3;
                continue;
                if (!str.equals("静音"))
                  continue;
                i = 4;
                continue;
                if (!str.equals("摆风"))
                  continue;
                i = 5;
              case 0:
              case 1:
              case 2:
              case 3:
              case 4:
              case 5:
              }
            FtFanRc2.this.pat.on();
            return;
            FtFanRc2.this.pat.speed();
            return;
            FtFanRc2.this.pat.type();
            return;
            FtFanRc2.this.pat.time();
            return;
            FtFanRc2.this.pat.mute();
            return;
            FtFanRc2.this.pat.shake();
          }
        });
        return;
        if (!str.equals("电源"))
          break;
        i = 0;
        break;
        if (!str.equals("风速"))
          break;
        i = 1;
        break;
        if (!str.equals("风类"))
          break;
        i = 2;
        break;
        if (!str.equals("定时"))
          break;
        i = 3;
        break;
        if (!str.equals("静音"))
          break;
        i = 4;
        break;
        if (!str.equals("摆风"))
          break;
        i = 5;
        break;
        paramViewHolder.bt_image_text.setTextAndIc(2131100301, 2130903179);
        continue;
        paramViewHolder.bt_image_text.setTextAndIc(2131100288, 2130903510);
        continue;
        paramViewHolder.bt_image_text.setTextAndIc(2131100290, 2130903512);
        continue;
        paramViewHolder.bt_image_text.setTextAndIc(2131100289, 2130903511);
        continue;
        paramViewHolder.bt_image_text.setTextAndIc(2131100287, 2130903508);
        continue;
        paramViewHolder.bt_image_text.setTextAndIc(2131100286, 2130903509);
      }
    }

    public int getCount()
    {
      return ((IrData)((MyRcDevice)FtFanRc2.this.pat.rcDevices.myRcDevices.get(FtFanRc2.this.pat.existRcPosi)).irDatas.get(0)).keys.size();
    }

    public IrData.IrKey getItem(int paramInt)
    {
      return (IrData.IrKey)((IrData)((MyRcDevice)FtFanRc2.this.pat.rcDevices.myRcDevices.get(FtFanRc2.this.pat.existRcPosi)).irDatas.get(0)).keys.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968800, null);
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
          break label48;
      }
      while (true)
      {
        if ((j | i) == 0)
          break label53;
        this.view.setBackgroundColor(Color.parseColor("#ffffff"));
        return false;
        j = 0;
        break;
        label48: i = 0;
      }
      label53: this.view.setBackgroundColor(Color.parseColor("#cccccc"));
      return false;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.fragment.FtFanRc2
 * JD-Core Version:    0.6.0
 */