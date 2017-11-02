package com.ex.ltech.hongwai.atRcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.AtBaseYk;
import com.ex.ltech.hongwai.atRcs.fragment.StbFragment1;
import com.ex.ltech.hongwai.atRcs.fragment.StbFragment2;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.hongwai.yaokong.AtSaveYongkongList;
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
import et.song.etclass.ETDevice;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AtNewStb extends AtBaseYk
{
  int areaId;
  int brandId;
  public int existRcPosi;
  ProgressDialog getTimeDialog;
  Handler handler = new Handler()
  {
  };
  int index;
  List<IrData> irDatas = new ArrayList();
  int key;
  KKNonACManager kkNonACManager;
  private LayoutInflater layoutInflater;
  private ETDevice mDevice = null;
  private List<Fragment> mFragments = new ArrayList();
  private NoScrollViewPager mVPFragments;
  private StbFragment2 numFragment;
  private String opRcType;
  private ImageView pageIndex;
  private int pagerCurrent = 0;
  public MyRcDevices rcDevices;
  List<Integer> rids;

  @Bind({2131558559})
  PercentRelativeLayout rl_parent;
  private StbFragment1 tvFragment;
  private String type;

  private void getRcCode(int paramInt)
  {
    ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(2131100078), false);
    localProgressDialog.setCancelable(true);
    KookongSDK.getIRDataById(this.rids.get(paramInt) + "", 1, new IRequestResult(localProgressDialog)
    {
      public void onFail(Integer paramInteger, String paramString)
      {
        this.val$dialog.dismiss();
        AtNewStb.this.showFaildToast(paramString);
      }

      public void onSuccess(String paramString, IrDataList paramIrDataList)
      {
        this.val$dialog.dismiss();
        AtNewStb.this.irDatas.clear();
        AtNewStb.this.irDatas.addAll(paramIrDataList.getIrDataList());
        if (AtNewStb.this.irDatas.size() <= 0)
          return;
        AtNewStb.this.kkNonACManager = new KKNonACManager((IrData)AtNewStb.this.irDatas.get(0));
        AtNewStb.this.sendRcParmas(AtNewStb.this.kkNonACManager.getParams());
        if (AtNewStb.this.action == 1)
          AtNewStb.this.getWindow().getDecorView().findViewById(16908290).setAnimation(AnimationUtils.loadAnimation(AtNewStb.this.getApplicationContext(), 2131034131));
        while (true)
        {
          for (int i = 0; i < AtNewStb.this.kkNonACManager.getAllKeys().size(); i++)
          {
            System.out.print("   ");
            System.out.print(((IrData.IrKey)AtNewStb.this.kkNonACManager.getAllKeys().get(i)).fname);
          }
          if (AtNewStb.this.action != 2)
            continue;
          AtNewStb.this.getWindow().getDecorView().findViewById(16908290).setAnimation(AnimationUtils.loadAnimation(AtNewStb.this.getApplicationContext(), 2131034132));
        }
        if (AtNewStb.this.isShowSaveYkDialogAlert)
          AtNewStb.this.showSeletedRcLib();
        ((MyRcDevice)AtNewStb.this.rcDevices.myRcDevices.get(AtNewStb.this.existRcPosi)).irDatas.clear();
        ((MyRcDevice)AtNewStb.this.rcDevices.myRcDevices.get(AtNewStb.this.existRcPosi)).irDatas.addAll(AtNewStb.this.irDatas);
        AtNewStb.this.numFragment.notifyDataSetChangedAdt();
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
        if (paramInt1 == 0)
        {
          AtNewStb.this.pageIndex.setBackgroundResource(2130903798);
          return;
        }
        AtNewStb.this.pageIndex.setBackgroundResource(2130903799);
      }

      public void onPageSelected(int paramInt)
      {
      }
    });
    this.mVPFragments.setOffscreenPageLimit(3);
    this.layoutInflater = LayoutInflater.from(this);
    this.tvFragment = new StbFragment1();
    this.numFragment = new StbFragment2();
    this.tvFragment.setType(this.type);
    this.mFragments.add(this.tvFragment);
    this.mFragments.add(this.numFragment);
    this.mVPFragments.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(), this.mFragments));
  }

  private void sendMyCmd(int paramInt)
  {
    try
    {
      sendCmd(this.mDevice.GetKeyValue(paramInt));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void back()
  {
    if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).irDatas.size() > 0)
    {
      if (!this.opRcType.equals("OP_AT_TYPE_CREATE"))
        break label105;
      if (!this.isSeleted)
        finish();
    }
    else
    {
      return;
    }
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = getTitleTest();
    MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
    setResult(10000);
    finish();
    return;
    label105: ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = getTitleTest();
    MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
    setResult(110000);
    finish();
  }

  public void chAdd()
  {
    sendRcCodeByChineseName("频道+");
  }

  public void chStub()
  {
    sendRcCodeByChineseName("频道-");
  }

  public void changeRcCode()
  {
    this.clickTime = 0;
    this.opType = "OP_AT_TYPE_CREATE";
    KookongSDK.getAllRemoteIds(1, 0, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).brandId, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).areaId, new IRequestResult()
    {
      public void onFail(Integer paramInteger, String paramString)
      {
        Toast.makeText(AtNewStb.this, 2131100076, 0).show();
      }

      public void onSuccess(String paramString, RemoteList paramRemoteList)
      {
        AtNewStb.this.rids = paramRemoteList.rids;
        AtNewStb.this.setRcLibCount(AtNewStb.this.rids.size());
        AtNewStb.this.getRcCode(0);
      }
    });
  }

  public void down()
  {
    sendRcCodeByChineseName("下");
  }

  public void fragmentCreateOk()
  {
    if ((this.opRcType.equals("OP_AT_TYPE_EXIST") | this.opRcType.equals("OP_RC_TYPE_TIME")))
      this.numFragment.notifyDataSetChangedAdt();
  }

  public void k1()
  {
    sendRcCodeByChineseName("1");
  }

  public void k10()
  {
    sendRcCodeByChineseName("0");
  }

  public void k11()
  {
    sendRcCodeByChineseName("-/--");
  }

  public void k12()
  {
    sendRcCodeByChineseName("回放");
  }

  public void k13()
  {
    sendRcCodeByChineseName("主页");
  }

  public void k14()
  {
    sendRcCodeByChineseName("播放");
  }

  public void k15()
  {
    sendRcCodeByChineseName("暂停");
  }

  public void k16()
  {
    sendRcCodeByChineseName("停止");
  }

  public void k17()
  {
    sendRcCodeByChineseName("信息");
  }

  public void k18()
  {
    sendRcCodeByChineseName("声道");
  }

  public void k19()
  {
    sendRcCodeByChineseName("设置");
  }

  public void k2()
  {
    sendRcCodeByChineseName("2");
  }

  public void k20()
  {
    sendRcCodeByChineseName("屏显");
  }

  public void k21()
  {
    sendRcCodeByChineseName("上页");
  }

  public void k22()
  {
    sendRcCodeByChineseName("下页");
  }

  public void k23()
  {
    sendRcCodeByChineseName("快进");
  }

  public void k24()
  {
    sendRcCodeByChineseName("快退");
  }

  public void k3()
  {
    sendRcCodeByChineseName("3");
  }

  public void k4()
  {
    sendRcCodeByChineseName("4");
  }

  public void k5()
  {
    sendRcCodeByChineseName("5");
  }

  public void k6()
  {
    sendRcCodeByChineseName("6");
  }

  public void k7()
  {
    sendRcCodeByChineseName("7");
  }

  public void k8()
  {
    sendRcCodeByChineseName("8");
  }

  public void k9()
  {
    sendRcCodeByChineseName("9");
  }

  public void kDouble()
  {
  }

  public void last(int paramInt)
  {
    getRcCode(paramInt);
  }

  public void left()
  {
    sendRcCodeByChineseName("左");
  }

  public void menu()
  {
    sendRcCodeByChineseName("菜单");
  }

  public void next(int paramInt)
  {
    getRcCode(paramInt);
  }

  public void ok()
  {
    sendRcCodeByChineseName("确认");
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 120000)
    {
      byte[] arrayOfByte = null;
      KKNonACManager localKKNonACManager = new KKNonACManager((IrData)((MyRcDevice)this.rcDevices.myRcDevices.get(paramIntent.getIntExtra("BIND_RC_POSI", 0))).irDatas.get(0));
      for (int i = 0; i < localKKNonACManager.getAllKeys().size(); i++)
      {
        if (!((IrData.IrKey)localKKNonACManager.getAllKeys().get(i)).fname.equals("电源"))
          continue;
        arrayOfByte = localKKNonACManager.getKeyIr(((IrData.IrKey)localKKNonACManager.getAllKeys().get(i)).fkey);
      }
      if (arrayOfByte != null)
      {
        onLearnOk(getString(2131100462), arrayOfByte);
        onLearnOk(getString(2131100463), localKKNonACManager.getParams());
        Toast.makeText(this, 2131099882, 0).show();
      }
    }
  }

  public void onBottomViewShow()
  {
    this.rl_parent.setLayoutParams(getMarginBottomScreenParams());
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968691);
    ButterKnife.bind(this);
    this.pageIndex = ((ImageView)findViewById(2131558908));
    setTitleView();
    initViewPager();
    setTitleView();
    ButterKnife.bind(this);
    this.opRcType = getIntent().getStringExtra("OP_AT_TYPE_KEY");
    this.brandId = getIntent().getIntExtra("BRAND_ID", -1);
    this.areaId = getIntent().getIntExtra("AREA_Id_KEY", -1);
    this.existRcPosi = getIntent().getIntExtra("OP_AT_POSI_KEY", -1);
    this.mRcPosi = this.existRcPosi;
    this.opType = this.opRcType;
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
      label222: switch (i)
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
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mType = 1;
      setTitleText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName, -16777216);
      return;
      if (!str.equals("OP_AT_TYPE_CREATE"))
        break;
      i = 0;
      break label222;
      if (!str.equals("OP_AT_TYPE_EXIST"))
        break;
      i = 1;
      break label222;
      if (!str.equals("OP_RC_TYPE_TIME"))
        break;
      i = 2;
      break label222;
      hideSetting();
      showTips();
      this.rcDevices.myRcDevices.add(new MyRcDevice());
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = (getIntent().getStringExtra("RC_NAME_KEY") + getResources().getString(2131100459));
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).brandId = this.brandId;
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).areaId = this.areaId;
      KookongSDK.getAllRemoteIds(1, 0, this.brandId, this.areaId, new IRequestResult()
      {
        public void onFail(Integer paramInteger, String paramString)
        {
          Toast.makeText(AtNewStb.this, 2131100076, 0).show();
        }

        public void onSuccess(String paramString, RemoteList paramRemoteList)
        {
          AtNewStb.this.rids = paramRemoteList.rids;
          AtNewStb.this.setRcLibCount(AtNewStb.this.rids.size());
          AtNewStb.this.getRcCode(0);
        }
      });
      continue;
      this.rl_parent.setLayoutParams(getFullScreenParams(0));
      this.kkNonACManager = new KKNonACManager((IrData)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).irDatas.get(0));
      sendRcParmas(this.kkNonACManager.getParams());
      continue;
      this.kkNonACManager = new KKNonACManager((IrData)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).irDatas.get(0));
      sendRcParmas(this.kkNonACManager.getParams());
    }
  }

  public void onLearnOk(String paramString, byte[] paramArrayOfByte)
  {
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.put(paramString, paramArrayOfByte);
    MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
  }

  public void onOff()
  {
    if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131100462)) == null)
    {
      int i = 0;
      for (int j = 0; j < this.rcDevices.myRcDevices.size(); j++)
      {
        if (((MyRcDevice)this.rcDevices.myRcDevices.get(j)).mType != 2)
          continue;
        i = 1;
      }
      if (i == 0)
        Toast.makeText(this, 2131100221, 0).show();
    }
    do
    {
      return;
      startActivityForResult(new Intent(this, AtSaveYongkongList.class).putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_BIND"), 0);
      return;
      this.getTimeDialog = ProgressDialog.show(this, "", getString(2131100432), false);
      this.getTimeDialog.setCancelable(false);
      this.getTimeDialog.show();
    }
    while (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131100463)) == null);
    sendRcParmas((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131100463)));
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        AtNewStb.this.sendRcCodeByChineseName(AtNewStb.this.getString(2131100462));
      }
    }
    , 1000L);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        AtNewStb.this.sendRcParmas(AtNewStb.this.kkNonACManager.getParams());
        AtNewStb.this.getTimeDialog.dismiss();
      }
    }
    , 2000L);
  }

  public void playBack()
  {
    sendRcCodeByChineseName("静音");
  }

  public void right()
  {
    sendRcCodeByChineseName("右");
  }

  public void seleted(int paramInt)
  {
    ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(2131100078), false);
    localProgressDialog.setCancelable(false);
    KookongSDK.getIRDataById(this.rids.get(paramInt) + "", 1, new IRequestResult(localProgressDialog)
    {
      public void onFail(Integer paramInteger, String paramString)
      {
        this.val$dialog.dismiss();
        AtNewStb.this.showFaildToast(paramString);
      }

      public void onSuccess(String paramString, IrDataList paramIrDataList)
      {
        this.val$dialog.dismiss();
        AtNewStb.this.irDatas.clear();
        AtNewStb.this.irDatas.addAll(paramIrDataList.getIrDataList());
        AtNewStb.this.kkNonACManager = new KKNonACManager((IrData)AtNewStb.this.irDatas.get(0));
        AtNewStb.this.sendRcParmas(AtNewStb.this.kkNonACManager.getParams());
        AtNewStb.this.rl_parent.setLayoutParams(AtNewStb.this.getFullScreenParams(0));
        ((MyRcDevice)AtNewStb.this.rcDevices.myRcDevices.get(AtNewStb.this.existRcPosi)).irDatas.clear();
        ((MyRcDevice)AtNewStb.this.rcDevices.myRcDevices.get(AtNewStb.this.existRcPosi)).irDatas.addAll(AtNewStb.this.irDatas);
        AtNewStb.this.numFragment.notifyDataSetChangedAdt();
        MyDb.getInstance(AtNewStb.this.getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, AtNewStb.this.rcDevices);
      }
    });
  }

  public void sendRcCodeByChineseName(String paramString)
  {
    this.clickTime = (1 + this.clickTime);
    if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(paramString) != null)
      sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(paramString));
    while (true)
    {
      return;
      if (this.kkNonACManager == null)
        continue;
      for (int i = 0; i < this.kkNonACManager.getAllKeys().size(); i++)
      {
        if (!((IrData.IrKey)this.kkNonACManager.getAllKeys().get(i)).fname.equals(paramString))
          continue;
        System.out.println(paramString);
        sendCmd(this.kkNonACManager.getKeyIr(((IrData.IrKey)this.kkNonACManager.getAllKeys().get(i)).fkey));
      }
    }
  }

  public void setting()
  {
  }

  public void stbOnOff()
  {
    sendRcCodeByChineseName("电源");
  }

  public void tvback()
  {
    sendRcCodeByChineseName("返回");
  }

  public void up()
  {
    sendRcCodeByChineseName("上");
  }

  public void volAdd()
  {
    sendRcCodeByChineseName("音量+");
  }

  public void volStub()
  {
    sendRcCodeByChineseName("音量-");
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.AtNewStb
 * JD-Core Version:    0.6.0
 */