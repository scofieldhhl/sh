package com.ex.ltech.onepiontfive.main.config;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.DiscoverDeviceDialog;
import com.ex.ltech.led.my_view.MyAlertDialog.MyOnClickListener;
import com.ex.ltech.led.my_view.SecondArc;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.room.RoomBusiness;
import com.ex.ltech.onepiontfive.main.room.RoomsBusiness;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import java.util.ArrayList;

public class AtCfgStepFourActivity extends MyBaseActivity
  implements View.OnClickListener
{
  public static boolean successful = false;
  String cfgState = "cfging";
  ArrayList<Byte> dIndexs = new ArrayList();

  @Bind({2131558877})
  Button deviceConnet;
  DiscoverDeviceDialog discoverDeviceDialog;
  private Home home;

  @Bind({R.id.info})
  TextView info;
  private boolean isconncting = false;

  @Bind({2131558879})
  ImageView ivCfgNoOk;

  @Bind({2131558878})
  ImageView ivCfgOk;
  Handler mHandler = new Handler();
  private int mPosi;
  private Room room;
  private RoomBusiness roomBusiness;
  private RoomsBusiness roomsbusiness;
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      if (AtCfgStepFourActivity.this.timerTime > 1)
      {
        AtCfgStepFourActivity localAtCfgStepFourActivity = AtCfgStepFourActivity.this;
        localAtCfgStepFourActivity.timerTime = (-1 + localAtCfgStepFourActivity.timerTime);
        AtCfgStepFourActivity.this.timeHandler.postDelayed(AtCfgStepFourActivity.this.runnable, 1000L);
        AtCfgStepFourActivity.this.second.setText(AtCfgStepFourActivity.this.timerTime + "");
        AtCfgStepFourActivity.this.smallSsecond.setVisibility(View.VISIBLE);
        AtCfgStepFourActivity.this.sbActOutletLed.setProgressSweep(360 * (60 - AtCfgStepFourActivity.this.timerTime) / 60);
        return;
      }
      AtCfgStepFourActivity.this.timerTime = 60;
      AtCfgStepFourActivity.this.second.setText("");
      AtCfgStepFourActivity.successful = false;
      AtCfgStepFourActivity.this.info.setText(2131099921);
      if (AtCfgStepFourActivity.this.dIndexs.size() > 0)
      {
        AtCfgStepFourActivity.this.cfgState = "ok";
        AtCfgStepFourActivity.this.ivCfgOk.setVisibility(View.VISIBLE);
        AtCfgStepFourActivity.this.ivCfgNoOk.setVisibility(View.GONE);
        AtCfgStepFourActivity.this.deviceConnet.setBackgroundResource(2130903587);
      }
      while (true)
      {
        AtCfgStepFourActivity.this.smallSsecond.setVisibility(View.GONE);
        AtCfgStepFourActivity.this.second.setVisibility(View.VISIBLE);
        break;
        AtCfgStepFourActivity.this.cfgState = "research";
        AtCfgStepFourActivity.this.ivCfgOk.setVisibility(View.GONE);
        AtCfgStepFourActivity.this.ivCfgNoOk.setVisibility(View.VISIBLE);
        AtCfgStepFourActivity.this.deviceConnet.setBackgroundResource(2130903114);
      }
    }
  };
  private SecondArc sbActOutletLed;
  Runnable searchRunnable = new Runnable()
  {
    public void run()
    {
      AtCfgStepFourActivity.this.searchDevice(AtCfgStepFourActivity.this.type);
    }
  };
  private TextView second;
  private TextView smallSsecond;
  Handler timeHandler = new Handler()
  {
  };
  int timerTime = 60;
  private int type = 0;

  private void searchDevice(int paramInt)
  {
    this.roomBusiness.searchDevice(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
        Log.i("", "");
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        if ((StringUtils.btye2Str(paramArrayOfByte).length() != 46) || (Integer.parseInt(StringUtils.btye2Str(paramArrayOfByte).substring(20, StringUtils.btye2Str(paramArrayOfByte).length()).substring(2, 4), 16) != 85));
        int i;
        do
        {
          return;
          i = 0;
          for (int j = 0; j < AtCfgStepFourActivity.this.dIndexs.size(); j++)
          {
            if (paramArrayOfByte[17] != ((Byte)AtCfgStepFourActivity.this.dIndexs.get(j)).byteValue())
              continue;
            i = 1;
          }
        }
        while (i != 0);
        AtCfgStepFourActivity.this.roomBusiness.resolveDvcData(StringUtils.btye2Str(paramArrayOfByte));
        AtCfgStepFourActivity.this.dIndexs.add(Byte.valueOf(paramArrayOfByte[17]));
        if (AtCfgStepFourActivity.this.discoverDeviceDialog == null)
        {
          AtCfgStepFourActivity.this.discoverDeviceDialog = new DiscoverDeviceDialog(AtCfgStepFourActivity.this);
          AtCfgStepFourActivity.this.discoverDeviceDialog.show();
          AtCfgStepFourActivity.this.discoverDeviceDialog.setMyOnClickListener(new MyAlertDialog.MyOnClickListener()
          {
            public void onClick(View paramView, boolean paramBoolean)
            {
              int i = 1;
              Intent localIntent;
              if (AtCfgStepFourActivity.this.dIndexs.size() > 0)
                if (AtCfgStepFourActivity.this.type == 97)
                {
                  localIntent = new Intent("AddDeviceRcBroadCast");
                  AtCfgStepFourActivity.this.setResult(1205, localIntent);
                  LocalBroadcastManager.getInstance(AtCfgStepFourActivity.this).sendBroadcast(localIntent);
                  AtCfgStepFourActivity.this.finish();
                }
              while (true)
              {
                AtCfgStepFourActivity.this.mHandler.removeCallbacks(AtCfgStepFourActivity.this.searchRunnable);
                return;
                int j;
                label127: int k;
                label145: int n;
                label170: int i1;
                if (AtCfgStepFourActivity.this.type == 34)
                {
                  j = i;
                  if (AtCfgStepFourActivity.this.type != 35)
                    break label219;
                  k = i;
                  int m = k | j;
                  if (AtCfgStepFourActivity.this.type != 36)
                    break label225;
                  n = i;
                  i1 = n | m;
                  if (AtCfgStepFourActivity.this.type != 37)
                    break label231;
                }
                while (true)
                {
                  if ((i1 | i) == 0)
                    break label236;
                  localIntent = new Intent("AddDevicePanelBroadCast");
                  break;
                  j = 0;
                  break label127;
                  label219: k = 0;
                  break label145;
                  label225: n = 0;
                  break label170;
                  label231: i = 0;
                }
                label236: if (AtCfgStepFourActivity.this.type == 81)
                {
                  localIntent = new Intent("AddDeviceSenorBroadCast");
                  break;
                }
                if (AtCfgStepFourActivity.this.type == 98)
                {
                  localIntent = new Intent("AddDevicePlugBroadCast");
                  break;
                }
                int i2 = AtCfgStepFourActivity.this.type;
                localIntent = null;
                if (i2 != 79)
                  break;
                localIntent = new Intent("AddDeviceLightBroadCast");
                break;
                AtCfgStepFourActivity.this.finish();
              }
            }
          });
        }
        AtCfgStepFourActivity.this.discoverDeviceDialog.setCount(AtCfgStepFourActivity.this.dIndexs.size() + AtCfgStepFourActivity.this.getString(2131100073));
      }

      public void onTimeOut()
      {
        Log.i("", "");
        AtCfgStepFourActivity.this.timeoutFunction();
      }
    }
    , this.mPosi, paramInt);
  }

  private void timer()
  {
    this.timerTime = 60;
    this.timeHandler.removeCallbacks(this.runnable);
    this.timeHandler.postDelayed(this.runnable, 1000L);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131558877:
    }
    do
    {
      return;
      if (this.cfgState.equals("cfging"))
        finish();
      if (this.cfgState.equals("timeout"))
      {
        startSmartLink();
        this.cfgState = "cfging";
        this.deviceConnet.setBackgroundResource(2130903131);
        this.ivCfgOk.setVisibility(View.GONE);
        this.smallSsecond.setVisibility(View.VISIBLE);
        this.second.setVisibility(View.VISIBLE);
        this.ivCfgNoOk.setVisibility(View.GONE);
      }
      if (!this.cfgState.equals("ok"))
        continue;
      setResult(200);
      finish();
    }
    while (!this.cfgState.equals("research"));
    startSmartLink();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968665);
    ButterKnife.bind(this);
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleText("");
    this.sbActOutletLed = ((SecondArc)findViewById(2131558742));
    this.second = ((TextView)findViewById(2131558875));
    this.smallSsecond = ((TextView)findViewById(2131558876));
    findViewById(2131558877).setOnClickListener(this);
    this.mPosi = getIntent().getIntExtra("mPosition", this.mPosi);
    String str = getIntent().getStringExtra("cfgType");
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
    case -905948230:
    case 106433028:
    case -995543377:
    case -995543378:
    case -995543379:
    case 3314136:
    }
    while (true)
    {
      this.roomsbusiness = new RoomsBusiness(this);
      this.home = this.roomsbusiness.getHome();
      this.room = ((Room)this.home.getRooms().get(this.mPosi));
      this.roomBusiness = new RoomBusiness(this, this.room.getDvcVos());
      this.roomBusiness.setRoomIndex(this.mPosi);
      startSmartLink();
      return;
      if (!str.equals("sensor"))
        break;
      i = 0;
      break;
      if (!str.equals("panel"))
        break;
      i = 1;
      break;
      if (!str.equals("panel3"))
        break;
      i = 2;
      break;
      if (!str.equals("panel2"))
        break;
      i = 3;
      break;
      if (!str.equals("panel1"))
        break;
      i = 4;
      break;
      if (!str.equals("lamp"))
        break;
      i = 5;
      break;
      this.type = 81;
      continue;
      this.type = 34;
      continue;
      this.type = 35;
      continue;
      this.type = 36;
      continue;
      this.type = 37;
      continue;
      this.type = 79;
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.mHandler.removeCallbacks(this.searchRunnable);
    this.roomBusiness.setMySendListener(null);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onPause()
  {
    super.onPause();
    this.timeHandler.removeCallbacks(this.runnable);
  }

  public void startSmartLink()
  {
    this.info.setText(2131100084);
    this.ivCfgOk.setVisibility(View.GONE);
    this.ivCfgNoOk.setVisibility(View.GONE);
    timer();
    if (!this.isconncting)
    {
      this.mHandler.post(this.searchRunnable);
      this.mHandler.postDelayed(this.searchRunnable, 5000L);
      this.mHandler.postDelayed(this.searchRunnable, 10000L);
      this.mHandler.postDelayed(this.searchRunnable, 15000L);
      this.mHandler.postDelayed(this.searchRunnable, 20000L);
      this.mHandler.postDelayed(this.searchRunnable, 25000L);
      this.mHandler.postDelayed(this.searchRunnable, 30000L);
      this.mHandler.postDelayed(this.searchRunnable, 35000L);
      this.mHandler.postDelayed(this.searchRunnable, 40000L);
      this.mHandler.postDelayed(this.searchRunnable, 45000L);
      this.mHandler.postDelayed(this.searchRunnable, 50000L);
      this.mHandler.postDelayed(this.searchRunnable, 55000L);
    }
  }

  public void succesnessFunction()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        AtCfgStepFourActivity.this.info.setText(2131099922);
        AtCfgStepFourActivity.this.deviceConnet.setBackgroundResource(2130903587);
        AtCfgStepFourActivity.this.ivCfgOk.setVisibility(View.VISIBLE);
        AtCfgStepFourActivity.this.ivCfgNoOk.setVisibility(View.GONE);
        AtCfgStepFourActivity.this.smallSsecond.setVisibility(View.GONE);
        AtCfgStepFourActivity.this.second.setVisibility(View.GONE);
      }
    });
  }

  public void timeoutFunction()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
      }
    });
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.AtCfgStepFourActivity
 * JD-Core Version:    0.6.0
 */