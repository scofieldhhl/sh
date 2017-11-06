package com.ex.ltech.hongwai.atRcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ex.ltech.LogRegForget.Mail;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.vo.DiyKey;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.hongwai.yaokong.AtIrLightReset;
import com.ex.ltech.hongwai.yaokong.AtYkCfgHelpActivity;
import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.my_view.MyAlertDialog12;
import com.ex.ltech.led.my_view.MyEditAlertDialog;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.hzy.tvmao.KKACManagerV2;
import com.kookong.app.data.api.IrData;

import java.util.ArrayList;
import java.util.List;

public class AtRcSet extends MyBaseActivity
{
  public static String IR_LIGHT_D_ID_KEY = "IR_LIGHT_D_ID_KEY";
  private final String LIGHT_DEL_OK_REPS_CODE = "C1";
  private final String PANEL_DEL_OK_REPS_CODE = "D6";
  int existRcPosi;
  List<IrData> irDatas = new ArrayList();
  KKACManagerV2 kkACManager;
  private MyRcDevice rcDevice;
  private MyRcDevices rcDevices;
  private TextView tvRcName;

  private void finishDelRc()
  {
    this.rcDevices.myRcDevices.remove(this.existRcPosi);
    MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
    setResult(90000);
    finish();
  }

  public void addLight(View paramView)
  {
    startActivityForResult(new Intent(this, AtYkCfgHelpActivity.class).putExtra(IR_LIGHT_D_ID_KEY, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId), 0);
  }

  public void changeName(View paramView)
  {
    MyEditAlertDialog localMyEditAlertDialog = new MyEditAlertDialog(this);
    localMyEditAlertDialog.show();
    localMyEditAlertDialog.rv_my_edit_alertdialog_cancle.setTextColor(Color.parseColor("#FF41C5F7"));
    localMyEditAlertDialog.rv_my_edit_alertdialog_ok.setTextColor(Color.parseColor("#FF41C5F7"));
    localMyEditAlertDialog.getWindow().clearFlags(131080);
    localMyEditAlertDialog.getWindow().setSoftInputMode(4);
    localMyEditAlertDialog.et_my_edit_alertdialog.setText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName);
    localMyEditAlertDialog.setMyOnClickListener(new MyEditAlertDialog.MyOnClickListener()
    {
      public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
      {
        AtRcSet.this.tvRcName.setText(paramString);
        ((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).mName = paramString;
        MyDb.getInstance(AtRcSet.this.getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, AtRcSet.this.rcDevices);
        AtRcSet.this.setResult(100000, new Intent().putExtra("RC_NAME_KEY", paramString));
        AtRcSet.this.finish();
      }
    });
  }

  public void changeRc(View paramView)
  {
    setResult(80000);
    finish();
  }

  public void delRc(View paramView)
  {
    MyAlertDialog12 localMyAlertDialog12 = new MyAlertDialog12(this);
    localMyAlertDialog12.setMyOnClickListener(new MyAlertDialog12.MyOnClickListener()
    {
      public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
      {
        int i = 1;
        int j;
        int k;
        label70: int n;
        label111: MyBusiness localMyBusiness;
        int i1;
        if (((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).mType == 10)
        {
          j = i;
          if (((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).mType != 11)
            break label276;
          k = i;
          int m = j | k;
          if (((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).mType != 12)
            break label282;
          n = i;
          if ((n | m) == 0)
            break label381;
          localMyBusiness = new MyBusiness(AtRcSet.this);
          localMyBusiness.setMySendListener(new MyBusiness.MySendListener(localMyBusiness)
          {
            public void onFail()
            {
              Toast.makeText(AtRcSet.this, 2131100334, 0).show();
            }

            public void onOk(byte[] paramArrayOfByte)
            {
              String str1 = StringUtils.btye2Str(paramArrayOfByte);
              if (str1.length() == 18)
              {
                String str2 = str1.substring(14, 16);
                if ((str2.equals("D6") | str2.equals("C1")))
                {
                  this.val$myBusiness.setMySendListener(null);
                  AtRcSet.this.finishDelRc();
                }
              }
            }

            public void onTimeOut()
            {
              Toast.makeText(AtRcSet.this, 2131100336, 0).show();
            }
          });
          if (((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).mType != 10)
            break label288;
          i1 = i;
          label181: if (((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).mType != 11)
            break label294;
          label212: if ((i1 | i) == 0)
            break label299;
          localMyBusiness.sendCmd(new CmdDateBussiness(AtRcSet.this, "0000").delIrPanel(((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).nonIrDevice.nonIrDeviceId));
        }
        label276: label282: label288: label294: label299: 
        do
        {
          return;
          j = 0;
          break;
          k = 0;
          break label70;
          n = 0;
          break label111;
          i1 = 0;
          break label181;
          i = 0;
          break label212;
        }
        while (((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).mType != 12);
        localMyBusiness.sendCmd(new CmdDateBussiness(AtRcSet.this, "0000").delIrLight(((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).nonIrDevice.nonIrDeviceId));
        return;
        label381: AtRcSet.this.finishDelRc();
      }
    });
    localMyAlertDialog12.show();
  }

  public void delkey(View paramView)
  {
    setResult(120000);
    finish();
  }

  public void editBtnName(View paramView)
  {
    setResult(160000);
    finish();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 12)
    {
      setResult(12);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    int i = 1;
    super.onCreate(paramBundle);
    setContentView(2130968697);
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setTiTleTextRes(R.string.setting);
    this.tvRcName = ((TextView)findViewById(2131558907));
    this.existRcPosi = getIntent().getIntExtra("OP_AT_POSI_KEY", -1);
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
    this.tvRcName.setText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName);
    if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mType == 9)
    {
      findViewById(2131558906).setVisibility(View.GONE);
      findViewById(2131558999).setVisibility(View.VISIBLE);
      findViewById(2131559001).setVisibility(View.VISIBLE);
      findViewById(2131559003).setVisibility(View.VISIBLE);
      findViewById(2131559004).setVisibility(View.VISIBLE);
    }
    label232: label363: label369: label372: 
    while (true)
    {
      return;
      int j;
      int k;
      int m;
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mType == 10)
      {
        j = i;
        if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mType != 11)
          break label363;
        k = i;
        m = j | k;
        if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mType != 12)
          break label369;
      }
      while (true)
      {
        if ((m | i) == 0)
          break label372;
        findViewById(2131558906).setVisibility(View.GONE);
        if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mType != 12)
          break;
        findViewById(2131559005).setVisibility(View.VISIBLE);
        findViewById(2131559006).setVisibility(View.VISIBLE);
        return;
        j = 0;
        break label232;
        k = 0;
        break label260;
        i = 0;
      }
    }
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void reLearnBtn(View paramView)
  {
    setResult(170000);
    finish();
  }

  public void resetLight(View paramView)
  {
    startActivityForResult(new Intent(this, AtIrLightReset.class).putExtra(IR_LIGHT_D_ID_KEY, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId), 0);
  }

  public void upLoadError(View paramView)
  {
    ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(2131100491), false);
    localProgressDialog.setCancelable(true);
    new Thread(localProgressDialog)
    {
      public void run()
      {
        super.run();
        String str = "";
        int i = 0;
        if (i < ((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).diyKeys.size())
        {
          if (((DiyKey)((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).diyKeys.get(i)).getKeyCode() != null)
            if (str.length() <= 0)
              break label156;
          label156: for (str = str + "," + StringUtils.btye2Str(((DiyKey)((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).diyKeys.get(i)).getKeyCode()); ; str = StringUtils.btye2Str(((DiyKey)((MyRcDevice)AtRcSet.this.rcDevices.myRcDevices.get(AtRcSet.this.existRcPosi)).diyKeys.get(i)).getKeyCode()))
          {
            i++;
            break;
          }
        }
        Mail localMail = new Mail("L-Home@ltech.cn", "Lt201511");
        localMail.set_debuggable(false);
        localMail.set_to(new String[] { "2629312117@qq.com" });
        localMail.set_from("L-Home@ltech.cn");
        localMail.set_subject("无效红外码" + UserFerences.getUserFerences(AtRcSet.this).spFerences.getString("user", ""));
        localMail.setBody(str);
        try
        {
          if (localMail.send())
          {
            AtRcSet.this.runOnUiThread(new Runnable()
            {
              public void run()
              {
                AtRcSet.2.this.val$dialog.dismiss();
                AtRcSet.this.toast(2131100335);
              }
            });
            return;
          }
          AtRcSet.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              AtRcSet.2.this.val$dialog.dismiss();
              AtRcSet.this.toast(2131100334);
            }
          });
          return;
        }
        catch (Exception localException)
        {
        }
      }
    }
    .start();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.AtRcSet
 * JD-Core Version:    0.6.0
 */