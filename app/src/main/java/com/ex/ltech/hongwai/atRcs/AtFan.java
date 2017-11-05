package com.ex.ltech.hongwai.atRcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.AtBaseYk;
import com.ex.ltech.hongwai.atRcs.fragment.FtFanRc2;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.fragment.FragmentPageAdapter;
import com.ex.ltech.led.my_view.NoScrollViewPager;
import com.hzy.tvmao.KKNonACManager;
import com.hzy.tvmao.KookongSDK;
import com.hzy.tvmao.interf.IRequestResult;
import com.kookong.app.data.RemoteList;
import com.kookong.app.data.api.IrData;
import com.kookong.app.data.api.IrData.IrKey;
import com.kookong.app.data.api.IrDataList;
import com.zhy.android.percent.support.PercentRelativeLayout;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AtFan extends AtBaseYk
{
  int brandId;
  public int existRcPosi;
  private FtFanRc2 ftFanRc2;
  List<IrData> irDatas = new ArrayList();
  public boolean isSeleted;
  byte[] keyCode = new byte[0];
  String keyName = "";
  KKNonACManager kkNonACManager;
  private List<Fragment> mFragments = new ArrayList();
  private NoScrollViewPager mVPFragments;
  private String opRcType;
  public MyRcDevices rcDevices;
  List<Integer> rids;

  @Bind({2131558559})
  PercentRelativeLayout rl_parent;

  private void getRcCode(int paramInt)
  {
    ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(2131100078), false);
    localProgressDialog.setCancelable(true);
    KookongSDK.getIRDataById(this.rids.get(paramInt) + "", 8, new IRequestResult(localProgressDialog)
    {
      public void onFail(Integer paramInteger, String paramString)
      {
        this.val$dialog.dismiss();
        AtFan.this.showFaildToast(paramString);
      }

      public void onSuccess(String paramString, IrDataList paramIrDataList)
      {
        this.val$dialog.dismiss();
        AtFan.this.irDatas.clear();
        AtFan.this.irDatas.addAll(paramIrDataList.getIrDataList());
        if (AtFan.this.irDatas.size() <= 0)
          return;
        AtFan.this.kkNonACManager = new KKNonACManager((IrData)AtFan.this.irDatas.get(0));
        AtFan.this.sendRcParmas(AtFan.this.kkNonACManager.getParams());
        if (AtFan.this.action == 1)
          AtFan.this.getWindow().getDecorView().findViewById(16908290).setAnimation(AnimationUtils.loadAnimation(AtFan.this.getApplicationContext(), 2131034131));
        while (true)
        {
          if (AtFan.this.isShowSaveYkDialogAlert)
            AtFan.this.showSeletedRcLib();
          ((MyRcDevice)AtFan.this.rcDevices.myRcDevices.get(AtFan.this.existRcPosi)).irDatas.clear();
          ((MyRcDevice)AtFan.this.rcDevices.myRcDevices.get(AtFan.this.existRcPosi)).irDatas.addAll(AtFan.this.irDatas);
          AtFan.this.ftFanRc2.notifyDataSetChangedAdt();
          return;
          if (AtFan.this.action != 2)
            continue;
          AtFan.this.getWindow().getDecorView().findViewById(16908290).setAnimation(AnimationUtils.loadAnimation(AtFan.this.getApplicationContext(), 2131034132));
        }
      }
    });
  }

  private void initViewPager()
  {
    this.mVPFragments = ((NoScrollViewPager)findViewById(2131558769));
    this.mVPFragments.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramInt)
      {
      }

      public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
      {
      }

      public void onPageSelected(int paramInt)
      {
      }
    });
    this.mVPFragments.setOffscreenPageLimit(0);
    this.ftFanRc2 = new FtFanRc2();
    this.mFragments.add(this.ftFanRc2);
    this.mVPFragments.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(), this.mFragments));
  }

  private void setListener()
  {
  }

  public void anion()
  {
    sendRcCodeByChineseName("负离子");
  }

  public void atomizer()
  {
    sendRcCodeByChineseName("雾化");
  }

  public void back()
  {
    if (this.opRcType.equals("OP_AT_TYPE_CREATE"))
      if (!this.isSeleted)
        finish();
    do
    {
      return;
      if ((((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).irDatas.size() > 0) && ((this.opRcType.equals("OP_AT_TYPE_CREATE") | this.opRcType.equals("OP_AT_TYPE_EXIST"))))
      {
        ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = getTitleTest();
        MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
      }
      setResult(10000);
      finish();
      return;
    }
    while (this.opRcType.equals("OP_RC_TYPE_TIME"));
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = getTitleTest();
    MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
    setResult(110000);
    finish();
  }

  public void changeRcCode()
  {
    this.clickTime = 0;
    this.opType = "OP_AT_TYPE_CREATE";
    KookongSDK.getAllRemoteIds(8, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).brandId, 0, 0, new IRequestResult()
    {
      public void onFail(Integer paramInteger, String paramString)
      {
        Toast.makeText(AtFan.this, 2131100076, 0).show();
      }

      public void onSuccess(String paramString, RemoteList paramRemoteList)
      {
        AtFan.this.rids = paramRemoteList.rids;
        AtFan.this.setRcLibCount(AtFan.this.rids.size());
        AtFan.this.getRcCode(0);
      }
    });
  }

  public void cloud()
  {
    sendRcCodeByChineseName("制冷");
  }

  public void fragmentCreateOk()
  {
    if ((this.opRcType.equals("OP_AT_TYPE_EXIST") | this.opRcType.equals("OP_RC_TYPE_TIME")))
      this.ftFanRc2.notifyDataSetChangedAdt();
  }

  public void last(int paramInt)
  {
    this.mVPFragments.setCurrentItem(0);
    getRcCode(paramInt);
  }

  public void light()
  {
    sendRcCodeByChineseName("灯光");
  }

  public void mute()
  {
    sendRcCodeByChineseName("静音");
  }

  public void next(int paramInt)
  {
    this.mVPFragments.setCurrentItem(0);
    getRcCode(paramInt);
  }

  public void on()
  {
    sendRcCodeByChineseName("电源");
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onBottomViewShow()
  {
    this.rl_parent.setLayoutParams(getMarginBottomScreenParams());
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968676);
    initViewPager();
    ButterKnife.bind(this);
    setTitleView();
    initSystemBar(this, Color.parseColor("#59cffa"));
    this.opRcType = getIntent().getStringExtra("OP_AT_TYPE_KEY");
    this.opType = this.opRcType;
    this.brandId = getIntent().getIntExtra("BRAND_ID", -1);
    this.existRcPosi = getIntent().getIntExtra("OP_AT_POSI_KEY", -1);
    this.mRcPosi = this.existRcPosi;
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
    this.mRcCount = this.rcDevices.myRcDevices.size();
    String str = this.opRcType;
    int i;
    switch (str.hashCode())
    {
    default:
      i = -1;
      label194: switch (i)
      {
      default:
      case 0:
      case 1:
      case 2:
      }
    case 1740066227:
    case -357480640:
    case -1351163454:
    }
    while (true)
    {
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mType = 8;
      setBackIc(2130903075);
      setSettingIc(2130903737);
      setTitleText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName, -1);
      setListener();
      return;
      if (!str.equals("OP_AT_TYPE_CREATE"))
        break;
      i = 0;
      break label194;
      if (!str.equals("OP_AT_TYPE_EXIST"))
        break;
      i = 1;
      break label194;
      if (!str.equals("OP_RC_TYPE_TIME"))
        break;
      i = 2;
      break label194;
      showTips();
      hideSetting();
      this.rcDevices.myRcDevices.add(new MyRcDevice());
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = (getIntent().getStringExtra("RC_NAME_KEY") + getResources().getString(2131100058));
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).brandId = this.brandId;
      KookongSDK.getAllRemoteIds(8, this.brandId, 0, 0, new IRequestResult()
      {
        public void onFail(Integer paramInteger, String paramString)
        {
          Toast.makeText(AtFan.this, 2131100076, 0).show();
        }

        public void onSuccess(String paramString, RemoteList paramRemoteList)
        {
          AtFan.this.rids = paramRemoteList.rids;
          AtFan.this.setRcLibCount(AtFan.this.rids.size());
          AtFan.this.getRcCode(0);
        }
      });
      continue;
      this.rl_parent.setLayoutParams(getFullScreenParams());
      this.kkNonACManager = new KKNonACManager((IrData)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).irDatas.get(0));
      sendRcParmas(this.kkNonACManager.getParams());
      continue;
      this.kkNonACManager = new KKNonACManager((IrData)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).irDatas.get(0));
      setSettingText(R.string.finish, -1);
      setCanLearn(false);
    }
  }

  public void onLearnOk(String paramString, byte[] paramArrayOfByte)
  {
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.put(paramString, paramArrayOfByte);
    MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
  }

  protected void onResume()
  {
    super.onResume();
  }

  public void onekey()
  {
    sendRcCodeByChineseName("一键通");
  }

  public void seleted(int paramInt)
  {
    ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(2131100078), false);
    localProgressDialog.setCancelable(false);
    KookongSDK.getIRDataById(this.rids.get(paramInt) + "", 8, new IRequestResult(localProgressDialog)
    {
      public void onFail(Integer paramInteger, String paramString)
      {
        this.val$dialog.dismiss();
        AtFan.this.showFaildToast(paramString);
      }

      public void onSuccess(String paramString, IrDataList paramIrDataList)
      {
        this.val$dialog.dismiss();
        AtFan.this.irDatas.clear();
        AtFan.this.irDatas.addAll(paramIrDataList.getIrDataList());
        AtFan.this.kkNonACManager = new KKNonACManager((IrData)AtFan.this.irDatas.get(0));
        AtFan.this.sendRcParmas(AtFan.this.kkNonACManager.getParams());
        AtFan.this.rl_parent.setLayoutParams(AtFan.this.getFullScreenParams());
        ((MyRcDevice)AtFan.this.rcDevices.myRcDevices.get(AtFan.this.existRcPosi)).irDatas.clear();
        ((MyRcDevice)AtFan.this.rcDevices.myRcDevices.get(AtFan.this.existRcPosi)).irDatas.addAll(AtFan.this.irDatas);
        for (int i = 0; i < AtFan.this.kkNonACManager.getAllKeys().size(); i++)
        {
          System.out.print("   ");
          System.out.print(((IrData.IrKey)AtFan.this.kkNonACManager.getAllKeys().get(i)).fname);
        }
        AtFan.this.ftFanRc2.notifyDataSetChangedAdt();
        MyDb.getInstance(AtFan.this.getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, AtFan.this.rcDevices);
      }
    });
  }

  public void sendRcCodeByChineseName(String paramString)
  {
    this.clickTime = (1 + this.clickTime);
    String str;
    if (paramString.equals("电源"))
    {
      str = "开关";
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(str) == null)
        break label116;
      if (this.opRcType.equals("OP_RC_TYPE_TIME"))
        break label104;
      sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(str));
    }
    label104: label116: 
    do
    {
      return;
      str = paramString;
      break;
      Toast.makeText(this, 2131100142, 0).show();
      return;
      this.keyName = paramString;
    }
    while (this.kkNonACManager == null);
    for (int i = 0; i < this.kkNonACManager.getAllKeys().size(); i++)
    {
      if (!((IrData.IrKey)this.kkNonACManager.getAllKeys().get(i)).fname.equals(paramString))
        continue;
      this.keyCode = this.kkNonACManager.getKeyIr(((IrData.IrKey)this.kkNonACManager.getAllKeys().get(i)).fkey);
      if (this.opRcType.equals("OP_RC_TYPE_TIME"))
        continue;
      sendCmd(this.keyCode);
    }
    this.isSeleted = true;
  }

  public void setting()
  {
    if (this.opRcType.equals("OP_RC_TYPE_TIME"))
    {
      if (this.keyName.length() == 0)
      {
        Toast.makeText(this, 2131100527, 0).show();
        return;
      }
      Intent localIntent = new Intent();
      InnerRcVo localInnerRcVo = new InnerRcVo();
      localInnerRcVo.setName(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName);
      localInnerRcVo.setmSaveRcListPosi(this.existRcPosi);
      localInnerRcVo.setmType(8);
      localInnerRcVo.setRcCodes(this.keyCode);
      localInnerRcVo.setStatus(this.keyName);
      localIntent.putExtra(InnerRcVo.class.getName(), localInnerRcVo);
      setResult(100000, localIntent);
    }
    finish();
  }

  public void shake()
  {
    sendRcCodeByChineseName("摆风");
  }

  public void sleep()
  {
    sendRcCodeByChineseName("睡眠");
  }

  public void speed()
  {
    sendRcCodeByChineseName("风速");
  }

  public void time()
  {
    sendRcCodeByChineseName("定时");
  }

  public void type()
  {
    sendRcCodeByChineseName("风类");
  }

  public void warn()
  {
    sendRcCodeByChineseName("制暖");
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.AtFan
 * JD-Core Version:    0.6.0
 */