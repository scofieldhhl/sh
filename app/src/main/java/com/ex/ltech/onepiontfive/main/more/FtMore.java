package com.ex.ltech.onepiontfive.main.more;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.my_view.MyAlertDialog.MyOnClickListener;
import com.ex.ltech.led.my_view.MyAlertDialog14;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.AtFragmentMaster;
import com.ex.ltech.onepiontfive.main.AtMain;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.updataHardWareProgram.SynProgram2Device;
import com.ex.ltech.onepiontfive.main.updataHardWareProgram.SynProgram2Device.Crc32AllFileListener;
import com.ex.ltech.onepiontfive.main.updataHardWareProgram.SynProgram2Device.SynListener;
import com.indris.material.RippleView;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChanged;
import io.xlink.wifi.js.manage.DeviceManage;
import java.io.PrintStream;
import java.util.ArrayList;

public class FtMore extends MyBaseFt
  implements View.OnClickListener
{
  private final int M_MCU_VERSION = 14;

  @Bind({2131559188})
  ToggleButton all_sensor_switch;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  MyBusiness business;
  ArrayList<Integer> cmd = new ArrayList();
  AlertDialog dialog;
  boolean isUpdataIng = false;

  @Bind({2131559185})
  ImageView ivFirmwareUpgrade;

  @Bind({2131559179})
  ImageView ivGeoFencing;

  @Bind({2131559181})
  ImageView ivJournal;

  @Bind({2131559183})
  ImageView ivLnk;

  @Bind({2131559177})
  ImageView ivPower;

  @Bind({2131559186})
  ImageView ivSensor;

  @Bind({2131559175})
  ImageView ivTiming;
  BroadcastReceiver mShowFirmwareReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      FtMore.this.showFirmware();
    }
  };
  boolean onOffAllSensor;

  @Bind({2131559176})
  RelativeLayout oneKeyOnOff;
  byte[] resend;

  @Bind({2131559184})
  RelativeLayout rlFirmwareUpgrade;

  @Bind({2131559178})
  RelativeLayout rlGeoFencing;

  @Bind({2131559182})
  RelativeLayout rlShortcuts;

  @Bind({2131559180})
  RelativeLayout rlSmsLog;

  @Bind({2131559162})
  RelativeLayout rlTime;
  private RelativeLayout rl_load;
  SynProgram2Device synProgram2Device;
  private TextView tvPercent;

  @Bind({2131559187})
  TextView tvSensorTip;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  private View view = null;

  private void updataDialog(String paramString)
  {
    MyAlertDialog14 localMyAlertDialog14 = new MyAlertDialog14(getActivity());
    localMyAlertDialog14.show();
    localMyAlertDialog14.setCancelable(false);
    localMyAlertDialog14.setMsg(getString(2131100502) + paramString);
    localMyAlertDialog14.setMyOnClickListener(new MyAlertDialog.MyOnClickListener()
    {
      public void onClick(View paramView, boolean paramBoolean)
      {
        if (paramBoolean)
        {
          FtMore.this.isUpdataIng = true;
          FtMore.this.rl_load.setVisibility(0);
          FtMore localFtMore = FtMore.this;
          FragmentActivity localFragmentActivity = FtMore.this.getActivity();
          DeviceManage.getInstance();
          localFtMore.synProgram2Device = new SynProgram2Device(localFragmentActivity, DeviceManage.getxDevice(), SynProgram2Device.ONEPIONTFIVE_FILE_NAME);
          FtMore.this.synProgram2Device.setListener(new SynProgram2Device.SynListener()
          {
            public void failed()
            {
              FtMore.this.isUpdataIng = false;
              FtMore.this.rl_load.setVisibility(8);
              FtMore.this.synProgram2Device.close();
              FtMore.this.view.findViewById(2131558798).setVisibility(0);
              FtMore.this.view.findViewById(2131558799).setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramView)
                {
                  FtMore.this.view.findViewById(2131558798).setVisibility(8);
                  FtMore.this.getActivity().finish();
                }
              });
            }

            public void ok()
            {
              FtMore.this.synProgram2Device.allFileCrc32Check();
              FtMore.this.synProgram2Device.setCrc32AllFileListener(new SynProgram2Device.Crc32AllFileListener()
              {
                public void failed()
                {
                  FtMore.this.isUpdataIng = false;
                  FtMore.this.rl_load.setVisibility(8);
                  FtMore.this.view.findViewById(2131558798).setVisibility(0);
                  FtMore.this.synProgram2Device.close();
                  FtMore.this.view.findViewById(2131558798).setVisibility(0);
                  FtMore.this.view.findViewById(2131558799).setOnClickListener(new View.OnClickListener()
                  {
                    public void onClick(View paramView)
                    {
                      FtMore.this.view.findViewById(2131558798).setVisibility(8);
                      FtMore.this.getActivity().finish();
                    }
                  });
                }

                public void ok()
                {
                  FtMore.this.isUpdataIng = false;
                  FtMore.this.rl_load.setVisibility(8);
                  FtMore.this.view.findViewById(2131558802).setVisibility(0);
                  new Handler()
                  {
                  }
                  .postDelayed(new Runnable()
                  {
                    public void run()
                    {
                      FtMore.this.view.findViewById(2131558802).setVisibility(8);
                      FtMore.this.synProgram2Device.close();
                      FtMore.this.getActivity().finish();
                    }
                  }
                  , 1000L);
                }
              });
            }

            public void onPercent(int paramInt)
            {
              FtMore.this.tvPercent.setText("" + paramInt + "%");
            }
          });
          FtMore.this.synProgram2Device.syn();
          FtMore.this.tvPercent.setText("%");
        }
      }
    });
  }

  public void init()
  {
    this.tvTitleViewTitle.setText(2131100183);
    this.tvTitleViewTitle.setText(2131100183);
    this.business = new MyBusiness(getActivity());
    regUpataBroadcast(this.mShowFirmwareReceiver);
  }

  public void initView()
  {
    this.rl_load = ((RelativeLayout)this.view.findViewById(2131558800));
    this.tvPercent = ((TextView)this.view.findViewById(2131558801));
    this.rlTime.setOnClickListener(this);
    this.rlGeoFencing.setOnClickListener(this);
    this.rlSmsLog.setOnClickListener(this);
    this.rlFirmwareUpgrade.setOnClickListener(this);
    this.tvTitleViewTitle.setText(2131100183);
    this.onOffAllSensor = this.all_sensor_switch.isToggle();
    this.all_sensor_switch.setOnToggleChanged(new ToggleButton.OnToggleChanged()
    {
      public void onToggle(boolean paramBoolean)
      {
        FtMore.this.dialog = ProgressDialog.show(FtMore.this.getActivity(), "", FtMore.this.getString(2131100002), false);
        FtMore.this.dialog.show();
        FtMore.this.cmd.clear();
        FtMore.this.business.addNormalHeadData(FtMore.this.cmd);
        FtMore.this.cmd.add(Integer.valueOf(27));
        FtMore.this.cmd.add(Integer.valueOf(1));
        ArrayList localArrayList = FtMore.this.cmd;
        int i = 0;
        if (paramBoolean)
          i = 1;
        localArrayList.add(Integer.valueOf(i));
        FtMore.this.cmd.add(Integer.valueOf(1));
        FtMore.this.business.addCheckSumData(FtMore.this.cmd);
        FtMore.this.cmd.add(Integer.valueOf(22));
        FtMore.this.business.setMySendListener(new MyBusiness.MySendListener()
        {
          public void onFail()
          {
            Toast.makeText(FtMore.this.getActivity(), 2131100334, 0).show();
            FtMore.this.dialog.dismiss();
            if (FtMore.this.onOffAllSensor)
              FtMore.this.all_sensor_switch.setToggleOn();
            while (true)
            {
              FtMore.this.business.setMySendListener(null);
              return;
              FtMore.this.all_sensor_switch.setToggleOff();
            }
          }

          public void onOk(byte[] paramArrayOfByte)
          {
            Toast.makeText(FtMore.this.getActivity(), 2131100335, 0).show();
            FtMore.this.dialog.dismiss();
          }

          public void onTimeOut()
          {
            Toast.makeText(FtMore.this.getActivity(), 2131100336, 0).show();
            FtMore.this.dialog.dismiss();
            if (FtMore.this.onOffAllSensor)
              FtMore.this.all_sensor_switch.setToggleOn();
            while (true)
            {
              FtMore.this.business.setMySendListener(null);
              return;
              FtMore.this.all_sensor_switch.setToggleOff();
            }
          }
        });
        FtMore.this.business.sendCmd(FtMore.this.cmd);
      }
    });
  }

  public void onActivate()
  {
    super.onActivate();
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 127)
      AtMain.instance.finish();
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }

  public void onClick(View paramView)
  {
    Intent localIntent = new Intent(getActivity(), AtFragmentMaster.class);
    if (paramView != this.rlFirmwareUpgrade)
    {
      if (paramView == this.rlTime)
        localIntent.putExtra("AtTypeKey", "AtTypeTiming");
      if (paramView == this.oneKeyOnOff)
        localIntent.putExtra("AtTypeKey", "AtTypeOnKeyOnOff");
      if (paramView == this.rlGeoFencing)
        localIntent.putExtra("AtTypeKey", "AtTypeGeoFencing");
      if (paramView == this.rlSmsLog)
        localIntent.putExtra("AtTypeKey", "AtTypeSmsLog");
      if (paramView == this.rlShortcuts)
        localIntent.putExtra("AtTypeKey", "AtTypeShortcuts");
      startActivity(localIntent);
      return;
    }
    showFirmware();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968758, null);
      ButterKnife.bind(this, this.view);
      init();
      initView();
    }
    ButterKnife.bind(this, this.view);
    return this.view;
  }

  public void onDeactivate()
  {
    super.onDeactivate();
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
    if (this.synProgram2Device != null)
      this.synProgram2Device.setListener(null);
  }

  public void onDetach()
  {
    super.onDetach();
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }

  public void onStart()
  {
    super.onStart();
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }

  public void showFirmware()
  {
    this.business.setMySendListener(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        String str1 = StringUtils.btye2Str(paramArrayOfByte);
        if ((str1.length() < 80) || (!str1.substring(18, 20).equalsIgnoreCase("FE")));
        while (true)
        {
          return;
          FtMore.this.business.setMySendListener(null);
          String str2 = StringUtils.bytesStr2WordStr(str1.substring(50, 74)).trim();
          System.out.println(str2);
          String str3 = str2.replace(".", "");
          String str4 = str3.substring(4, str3.length()).replaceFirst("^0*", "");
          try
          {
            if (14 <= Integer.parseInt(str4))
              continue;
            FtMore.this.updataDialog(StringUtils.bytesStr2WordStr(str1.substring(50, 74)));
            return;
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localNumberFormatException.printStackTrace();
          }
        }
      }

      public void onTimeOut()
      {
      }
    });
    new Handler()
    {
    }
    .postDelayed(new Runnable()
    {
      public void run()
      {
        ArrayList localArrayList = new ArrayList();
        FtMore.this.business.addNormalHeadData(localArrayList);
        localArrayList.add(Integer.valueOf(126));
        localArrayList.add(Integer.valueOf(1));
        localArrayList.add(Integer.valueOf(1));
        localArrayList.add(Integer.valueOf(1));
        FtMore.this.business.addCheckSumData(localArrayList);
        localArrayList.add(Integer.valueOf(22));
        FtMore.this.business.sendCmd(localArrayList);
      }
    }
    , 50L);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.FtMore
 * JD-Core Version:    0.6.0
 */