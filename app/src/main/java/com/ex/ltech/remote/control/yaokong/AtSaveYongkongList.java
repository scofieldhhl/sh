package com.ex.ltech.remote.control.yaokong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.MyAlertDialog6;
import com.ex.ltech.led.my_view.MyAlertDialog6.MyOnClickListener;
import com.ex.ltech.remote.control.scene.AtAirYKSetActivity;
import com.ex.ltech.remote.control.scene.AtSimpleYKSetActivity;
import et.song.etclass.ETDevice;
import et.song.etclass.ETGroup;
import et.song.etclass.ETPage;

public class AtSaveYongkongList extends MyBaseActivity
{
  private YaoKongAdapter adt;
  private SaveYongkongListBusiness business;
  private ListView lv;
  int ykPosi;
  int ykSceneVoPosi;

  private void findView()
  {
    this.lv = ((ListView)findViewById(R.id.lv_act_repeat_day));
  }

  private void init()
  {
    this.adt = new YaoKongAdapter(this);
    this.lv.setAdapter(this.adt);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 200)
    {
      setResult(200);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.act_repeat_day);
    this.business = new SaveYongkongListBusiness(this);
    findView();
    setTitleView();
    init();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void seletedOnOff(ETDevice paramETDevice, int paramInt)
  {
    if (paramETDevice.GetRes() == 7)
    {
      Intent localIntent1 = new Intent();
      localIntent1.putExtra("ykName", paramETDevice.GetName());
      localIntent1.putExtra("ykId", paramInt);
      localIntent1.putExtra("ykType", "air");
      setResult(200, localIntent1);
      finish();
      return;
    }
    Intent localIntent2 = new Intent();
    localIntent2.putExtra("ykName", paramETDevice.GetName());
    localIntent2.putExtra("ykId", paramInt);
    switch (paramETDevice.GetRes())
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      setResult(200, localIntent2);
      finish();
      return;
      localIntent2.putExtra("ykType", "tv");
      continue;
      localIntent2.putExtra("ykType", "iptv");
      continue;
      localIntent2.putExtra("ykType", "tvbox");
      continue;
      localIntent2.putExtra("ykType", "dvd");
      continue;
      localIntent2.putExtra("ykType", "fan");
      continue;
      localIntent2.putExtra("ykType", "projector");
      continue;
      localIntent2.putExtra("ykType", "projector");
    }
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setTiTleTextRes(2131100529);
  }

  class YaoKongAdapter extends BaseAdapter
  {
    int[] nameRes = { 2131099858, 2131100457, 2131100459, 2131100276, 2131100058, 2131100109, 2131100090 };
    private Activity pct;
    int[] picRes = { 2130903790, 2130903401, 2130903792, 2130903228, 2130903254, 2130903636, 2130903636, 2130903055, 2130903636, 2130903636, 2130903636, 2130903636 };

    public YaoKongAdapter(Activity arg2)
    {
      Object localObject;
      this.pct = localObject;
    }

    private void initializeViews(View paramView, ETDevice paramETDevice, Holder paramHolder, int paramInt)
    {
      paramHolder.ic.setImageResource(this.picRes[paramETDevice.GetRes()]);
      paramHolder.name.setText(paramETDevice.GetName());
      if (AtSaveYongkongList.this.getIntent().getBooleanExtra("isFromTimg", false))
      {
        paramView.setOnClickListener(new View.OnClickListener(paramETDevice, paramInt)
        {
          public void onClick(View paramView)
          {
            AtSaveYongkongList.this.seletedOnOff(this.val$device, this.val$position);
          }
        });
        return;
      }
      paramView.setOnClickListener(new View.OnClickListener(paramETDevice, paramInt)
      {
        public void onClick(View paramView)
        {
          if (this.val$device.GetRes() == 7)
          {
            Intent localIntent1 = new Intent(AtSaveYongkongList.this, AtAirYKSetActivity.class);
            localIntent1.putExtra("ykName", this.val$device.GetName());
            localIntent1.putExtra("ykId", this.val$position);
            localIntent1.putExtra("ykType", "air");
            AtSaveYongkongList.this.startActivityForResult(localIntent1, 0);
            return;
          }
          Intent localIntent2 = new Intent(AtSaveYongkongList.this, AtSimpleYKSetActivity.class);
          localIntent2.putExtra("ykName", this.val$device.GetName());
          localIntent2.putExtra("ykId", this.val$position);
          switch (this.val$device.GetRes())
          {
          default:
            return;
          case 0:
            MyAlertDialog6 localMyAlertDialog63 = new MyAlertDialog6(AtSaveYongkongList.this);
            localMyAlertDialog63.show();
            localMyAlertDialog63.setMyOnClickListener(new MyAlertDialog6.MyOnClickListener(localIntent2)
            {
              public void onChanel()
              {
                this.val$i.putExtra("ykType", "tv");
                this.val$i.putExtra("ykTvStatusType", "chanel");
                this.val$i.putExtra("icRes", AtSaveYongkongList.YaoKongAdapter.this.picRes[AtSaveYongkongList.YaoKongAdapter.2.this.val$device.GetRes()]);
                AtSaveYongkongList.this.startActivityForResult(this.val$i, 0);
              }

              public void onOnOff()
              {
                this.val$i.putExtra("ykType", "tv");
                this.val$i.putExtra("ykTvStatusType", "onOff");
                this.val$i.putExtra("icRes", AtSaveYongkongList.YaoKongAdapter.this.picRes[AtSaveYongkongList.YaoKongAdapter.2.this.val$device.GetRes()]);
                AtSaveYongkongList.this.startActivityForResult(this.val$i, 0);
              }
            });
            return;
          case 1:
            MyAlertDialog6 localMyAlertDialog62 = new MyAlertDialog6(AtSaveYongkongList.this);
            localMyAlertDialog62.show();
            localMyAlertDialog62.setMyOnClickListener(new MyAlertDialog6.MyOnClickListener(localIntent2)
            {
              public void onChanel()
              {
                this.val$i.putExtra("ykType", "iptv");
                this.val$i.putExtra("ykStatusType", "chanel");
                this.val$i.putExtra("icRes", AtSaveYongkongList.YaoKongAdapter.this.picRes[AtSaveYongkongList.YaoKongAdapter.2.this.val$device.GetRes()]);
                AtSaveYongkongList.this.startActivityForResult(this.val$i, 0);
              }

              public void onOnOff()
              {
                this.val$i.putExtra("ykType", "iptv");
                this.val$i.putExtra("ykTvStatusType", "onOff");
                this.val$i.putExtra("icRes", AtSaveYongkongList.YaoKongAdapter.this.picRes[AtSaveYongkongList.YaoKongAdapter.2.this.val$device.GetRes()]);
                AtSaveYongkongList.this.startActivityForResult(this.val$i, 0);
              }
            });
            return;
          case 2:
            MyAlertDialog6 localMyAlertDialog61 = new MyAlertDialog6(AtSaveYongkongList.this);
            localMyAlertDialog61.show();
            localMyAlertDialog61.setMyOnClickListener(new MyAlertDialog6.MyOnClickListener(localIntent2)
            {
              public void onChanel()
              {
                this.val$i.putExtra("ykType", "tvbox");
                this.val$i.putExtra("ykTvStatusType", "chanel");
                this.val$i.putExtra("icRes", AtSaveYongkongList.YaoKongAdapter.this.picRes[AtSaveYongkongList.YaoKongAdapter.2.this.val$device.GetRes()]);
                AtSaveYongkongList.this.startActivityForResult(this.val$i, 0);
              }

              public void onOnOff()
              {
                this.val$i.putExtra("ykType", "tvbox");
                this.val$i.putExtra("ykTvStatusType", "onOff");
                this.val$i.putExtra("icRes", AtSaveYongkongList.YaoKongAdapter.this.picRes[AtSaveYongkongList.YaoKongAdapter.2.this.val$device.GetRes()]);
                AtSaveYongkongList.this.startActivityForResult(this.val$i, 0);
              }
            });
            return;
          case 3:
            localIntent2.putExtra("ykType", "dvd");
            localIntent2.putExtra("icRes", AtSaveYongkongList.YaoKongAdapter.this.picRes[this.val$device.GetRes()]);
            AtSaveYongkongList.this.startActivityForResult(localIntent2, 0);
            return;
          case 4:
            localIntent2.putExtra("ykType", "fan");
            localIntent2.putExtra("icRes", AtSaveYongkongList.YaoKongAdapter.this.picRes[this.val$device.GetRes()]);
            AtSaveYongkongList.this.startActivityForResult(localIntent2, 0);
            return;
          case 5:
          }
          localIntent2.putExtra("ykType", "projector");
          localIntent2.putExtra("icRes", AtSaveYongkongList.YaoKongAdapter.this.picRes[this.val$device.GetRes()]);
          AtSaveYongkongList.this.startActivityForResult(localIntent2, 0);
        }
      });
    }

    public int getCount()
    {
      try
      {
        int i = ((ETGroup)ETPage.getInstance(AtSaveYongkongList.this).GetItem(0)).GetCount();
        return i - 1;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return 0;
    }

    public Object getItem(int paramInt)
    {
      return ((ETGroup)ETPage.getInstance(AtSaveYongkongList.this).GetItem(0)).GetItem(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        Holder localHolder = new Holder();
        this.pct.getLayoutInflater();
        paramView = LayoutInflater.from(this.pct).inflate(2130968825, null);
        localHolder.name = ((TextView)paramView.findViewById(2131559008));
        localHolder.ic = ((ImageView)paramView.findViewById(2131558883));
        paramView.setTag(localHolder);
      }
      while (true)
      {
        initializeViews(paramView, (ETDevice)getItem(paramInt), (Holder)paramView.getTag(), paramInt);
        return paramView;
        ((Holder)paramView.getTag());
      }
    }

    class Holder
    {
      ImageView ic;
      TextView name;

      Holder()
      {
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.AtSaveYongkongList
 * JD-Core Version:    0.6.0
 */