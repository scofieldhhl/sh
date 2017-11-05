package com.ex.ltech.hongwai.atRcs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.AtBaseYk;
import com.ex.ltech.hongwai.view.ImageTextButton;
import com.ex.ltech.hongwai.view.SmallImageTextButton;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.hzy.tvmao.KKACManagerV2;
import com.hzy.tvmao.KKNonACManager;
import com.hzy.tvmao.KookongSDK;
import com.hzy.tvmao.interf.IRequestResult;
import com.hzy.tvmao.ir.ac.ACModelV2;
import com.hzy.tvmao.ir.ac.ACStateV2;
import com.hzy.tvmao.ir.ac.ACStateV2.UDWindDirectKey;
import com.hzy.tvmao.ir.ac.ACStateV2.UDWindDirectType;
import com.kookong.app.data.RemoteList;
import com.kookong.app.data.api.IrData;
import com.kookong.app.data.api.IrData.IrKey;
import com.kookong.app.data.api.IrDataList;
import com.zhy.android.percent.support.PercentRelativeLayout;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AtAirConActivity extends AtBaseYk
{

  @Bind({2131558840})
  ImageView bg;
  int brandId;

  @Bind({2131558851})
  SmallImageTextButton btAdd;

  @Bind({2131558853})
  ImageTextButton btHot;

  @Bind({2131558852})
  SmallImageTextButton btMinus;

  @Bind({2131558850})
  ImageTextButton btMode;

  @Bind({2131558849})
  ImageTextButton btMode2;

  @Bind({2131558847})
  ImageTextButton btOff;

  @Bind({2131558857})
  ImageTextButton btOn;

  @Bind({2131558855})
  ImageTextButton btOrientation;

  @Bind({2131558848})
  ImageTextButton btSpeed;

  @Bind({2131558854})
  ImageTextButton btSweep;
  int codeType;
  private int direction;
  int existRcPosi;
  List<IrData> irDatas = new ArrayList();

  @Bind({2131558838})
  ImageView ivSpeed;
  KKACManagerV2 kkACManager;

  @Bind({2131558835})
  ImageView mode;
  KKNonACManager oldACManager;
  private String opRcType;

  @Bind({2131558845})
  ImageView orientation;
  private MyRcDevices rcDevices;
  List<Integer> rids;

  @Bind({2131558856})
  PercentRelativeLayout rlOff;

  @Bind({2131558839})
  RelativeLayout rl_p_bg;

  @Bind({2131558559})
  PercentRelativeLayout rl_parent;

  @Bind({2131558843})
  ImageView sao_feng;

  @Bind({2131558842})
  TextView tvMode;

  @Bind({2131558844})
  TextView tvOrientation;

  @Bind({2131558846})
  TextView tvSpeed;

  @Bind({2131558841})
  TextView wendu;

  public static String byte2Hexstr(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      localStringBuilder.append(getHexChar(0xF & paramArrayOfByte[i] >> 4));
      localStringBuilder.append(getHexChar(0xF & paramArrayOfByte[i]));
    }
    return localStringBuilder.toString();
  }

  private static char getHexChar(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 10))
      return (char)(paramInt + 48);
    return (char)(65 + (paramInt - 10));
  }

  private String getModeStr()
  {
    switch (this.kkACManager.getCurrentACModel().getModelType())
    {
    default:
      return "";
    case 2:
      return getString(2131099862);
    case 0:
      return getString(2131099860);
    case 1:
      return getString(2131099861);
    case 3:
      return getString(2131099863);
    case 4:
    }
    return getString(2131099864);
  }

  private void getRcCode(int paramInt)
  {
    this.oldACManager = null;
    ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(2131100078), false);
    localProgressDialog.setCancelable(false);
    KookongSDK.getIRDataById(this.rids.get(paramInt) + "", 5, new IRequestResult(localProgressDialog)
    {
      public void onFail(Integer paramInteger, String paramString)
      {
        this.val$dialog.dismiss();
        AtAirConActivity.this.showFaildToast(paramString);
      }

      public void onSuccess(String paramString, IrDataList paramIrDataList)
      {
        this.val$dialog.dismiss();
        AtAirConActivity.this.irDatas.clear();
        AtAirConActivity.this.irDatas.addAll(paramIrDataList.getIrDataList());
        if (AtAirConActivity.this.irDatas.size() <= 0)
          return;
        AtAirConActivity.this.kkACManager = new KKACManagerV2((IrData)AtAirConActivity.this.irDatas.get(0));
        if (((IrData)AtAirConActivity.this.irDatas.get(0)).type == 1)
          System.out.println("irDatas.get(0):" + ((IrData)AtAirConActivity.this.irDatas.get(0)).type);
        AtAirConActivity.this.codeType = ((IrData)AtAirConActivity.this.irDatas.get(0)).type;
        AtAirConActivity.this.kkACManager.setACStateV2FromString("");
        AtAirConActivity.this.kkACManager.getCurrentACModel().setTemperature(25);
        AtAirConActivity.this.sendRcParmas(AtAirConActivity.this.kkACManager.getAcParams());
        if (AtAirConActivity.this.action == 1)
          AtAirConActivity.this.getWindow().getDecorView().findViewById(16908290).setAnimation(AnimationUtils.loadAnimation(AtAirConActivity.this.getApplicationContext(), 2131034131));
        while (true)
        {
          AtAirConActivity.this.initACPannel();
          if (AtAirConActivity.this.isShowSaveYkDialogAlert)
            AtAirConActivity.this.showSeletedRcLib();
          AtAirConActivity.this.initOldACManager(AtAirConActivity.this.irDatas);
          return;
          if (AtAirConActivity.this.action != 2)
            continue;
          AtAirConActivity.this.getWindow().getDecorView().findViewById(16908290).setAnimation(AnimationUtils.loadAnimation(AtAirConActivity.this.getApplicationContext(), 2131034132));
        }
      }
    });
  }

  private void init()
  {
    setBackIc(2130903075);
    setSettingIc(2130903737);
    this.opRcType = getIntent().getStringExtra("OP_AT_TYPE_KEY");
    this.brandId = getIntent().getIntExtra("BRAND_ID", -1);
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
      label170: switch (i)
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
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mType = 5;
      setTitleText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName, -1);
      setListener();
      return;
      if (!str.equals("OP_AT_TYPE_CREATE"))
        break;
      i = 0;
      break label170;
      if (!str.equals("OP_AT_TYPE_EXIST"))
        break;
      i = 1;
      break label170;
      if (!str.equals("OP_RC_TYPE_TIME"))
        break;
      i = 2;
      break label170;
      hideSetting();
      this.rcDevices.myRcDevices.add(new MyRcDevice());
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = (getIntent().getStringExtra("RC_NAME_KEY") + getResources().getString(2131099858));
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).brandId = this.brandId;
      KookongSDK.getAllRemoteIds(5, this.brandId, 0, 0, new IRequestResult()
      {
        public void onFail(Integer paramInteger, String paramString)
        {
          Toast.makeText(AtAirConActivity.this, 2131100076, 0).show();
        }

        public void onSuccess(String paramString, RemoteList paramRemoteList)
        {
          AtAirConActivity.this.rids = paramRemoteList.rids;
          if (AtAirConActivity.this.brandId == 97)
            AtAirConActivity.this.rids.add(0, AtAirConActivity.this.rids.remove(1));
          AtAirConActivity.this.setRcLibCount(AtAirConActivity.this.rids.size());
          AtAirConActivity.this.getRcCode(0);
        }
      });
      showTips();
      continue;
      this.rlOff.setLayoutParams(getFullScreenParams());
      this.rl_parent.setLayoutParams(getFullScreenParams());
      this.kkACManager = new KKACManagerV2((IrData)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).irDatas.get(0));
      this.kkACManager.setACStateV2FromString(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).acState);
      sendRcParmas(this.kkACManager.getAcParams());
      initACPannel();
      initOldACManager(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).irDatas);
      continue;
      this.kkACManager = new KKACManagerV2((IrData)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).irDatas.get(0));
      this.kkACManager.setACStateV2FromString(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).acState);
      setSettingText(R.string.finish, -1);
      initACPannel();
    }
  }

  private void initACPannel()
  {
    updatePowerScreen();
    updateModes();
    updateWindDirectBt();
  }

  private boolean initOldACManager(List<IrData> paramList)
  {
    if (((IrData)paramList.get(0)).type == 1)
    {
      this.oldACManager = new KKNonACManager((IrData)paramList.get(0));
      return true;
    }
    return false;
  }

  private void setListener()
  {
    this.btOff.setImageTextButtonLongClickListener(this);
    this.btSpeed.setImageTextButtonLongClickListener(this);
    this.btMode2.setImageTextButtonLongClickListener(this);
    this.btMode.setImageTextButtonLongClickListener(this);
    this.btAdd.setImageTextButtonLongClickListener(this);
    this.btMinus.setImageTextButtonLongClickListener(this);
    this.btHot.setImageTextButtonLongClickListener(this);
    this.btSweep.setImageTextButtonLongClickListener(this);
    this.btOrientation.setImageTextButtonLongClickListener(this);
    this.btOn.setImageTextButtonLongClickListener(this);
  }

  private void updateModes()
  {
    int i = this.kkACManager.getCurrentACModel().getModelType();
    this.kkACManager.getACStateV2InString();
    switch (i)
    {
    default:
      return;
    case 2:
      initSystemBar(this, Color.parseColor("#29BFF7"));
      this.rl_p_bg.setBackgroundResource(2130903646);
      this.mode.setBackgroundResource(2130903057);
      this.tvMode.setText(2131099862);
      return;
    case 0:
      initSystemBar(this, Color.parseColor("#29BFF7"));
      this.rl_p_bg.setBackgroundResource(2130903646);
      this.mode.setBackgroundResource(2130903407);
      this.tvMode.setText(2131099860);
      return;
    case 1:
      initSystemBar(this, Color.parseColor("#ffa474"));
      this.rl_p_bg.setBackgroundResource(2130903664);
      this.mode.setBackgroundResource(2130903806);
      TextView localTextView = this.tvMode;
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).isNorth);
      for (int j = 2131099871; ; j = 2131099861)
      {
        localTextView.setText(j);
        return;
      }
    case 3:
      initSystemBar(this, Color.parseColor("#29BFF7"));
      this.rl_p_bg.setBackgroundResource(2130903646);
      this.mode.setBackgroundResource(2130903409);
      this.tvMode.setText(2131099863);
      return;
    case 4:
    }
    initSystemBar(this, Color.parseColor("#29BFF7"));
    this.rl_p_bg.setBackgroundResource(2130903646);
    this.mode.setBackgroundResource(2130903408);
    this.tvMode.setText(2131099864);
  }

  private void updatePowerScreen()
  {
    if (this.kkACManager.getPowerState() == 1)
    {
      this.wendu.setText("NA");
      this.rlOff.setVisibility(View.VISIBLE);
      return;
    }
    this.rlOff.setVisibility(View.GONE);
    updateTemptures();
    updateWindSpeed();
    updateWindDirectType();
  }

  private void updateTemptures()
  {
    if (this.kkACManager.getCurrentACModel().isTempCanControl())
    {
      this.wendu.setText(this.kkACManager.getCurrentACModel().getCurTmp() + "");
      return;
    }
    this.wendu.setText("NA");
  }

  private void updateWindDirect()
  {
    ACStateV2.UDWindDirectType localUDWindDirectType = this.kkACManager.getAcStateV2().getCurUDDirectType();
    int i = this.kkACManager.getAcStateV2().getCurUDDirect();
    if ((ACStateV2.UDWindDirectType.UDDIRECT_ONLY_SWING != localUDWindDirectType) && (i == 0));
  }

  private void updateWindDirectBt()
  {
    ACStateV2.UDWindDirectType localUDWindDirectType = this.kkACManager.getAcStateV2().getCurUDDirectType();
    int i = this.kkACManager.getAcStateV2().getCurUDDirect();
    switch (5.$SwitchMap$com$hzy$tvmao$ir$ac$ACStateV2$UDWindDirectType[localUDWindDirectType.ordinal()])
    {
    default:
      return;
    case 1:
      this.sao_feng.setVisibility(View.VISIBLE);
      this.orientation.setVisibility(View.GONE);
      this.tvOrientation.setText(2131099865);
      return;
    case 2:
      this.sao_feng.setVisibility(View.GONE);
      this.orientation.setVisibility(View.VISIBLE);
      this.tvOrientation.setText(2131099859);
      return;
    case 3:
    }
    if (i == 0)
    {
      this.sao_feng.setVisibility(View.VISIBLE);
      this.orientation.setVisibility(View.GONE);
      this.tvOrientation.setText(2131099865);
      return;
    }
    this.sao_feng.setVisibility(View.GONE);
    this.orientation.setVisibility(View.VISIBLE);
    this.tvOrientation.setText(2131099859);
  }

  private void updateWindDirectType()
  {
    if (this.kkACManager.getPowerState() == 1);
    int i;
    do
    {
      return;
      ACStateV2.UDWindDirectType localUDWindDirectType = this.kkACManager.getAcStateV2().getCurUDDirectType();
      i = this.kkACManager.getAcStateV2().getCurUDDirect();
      switch (5.$SwitchMap$com$hzy$tvmao$ir$ac$ACStateV2$UDWindDirectType[localUDWindDirectType.ordinal()])
      {
      default:
        return;
      case 1:
        this.sao_feng.setVisibility(View.VISIBLE);
        this.orientation.setVisibility(View.GONE);
        return;
      case 2:
        updateWindDirect();
        this.sao_feng.setVisibility(View.GONE);
        this.orientation.setVisibility(View.VISIBLE);
        return;
      case 3:
      }
    }
    while (i == 0);
    updateWindDirect();
  }

  private void updateWindSpeed()
  {
    if (this.kkACManager.getCurrentACModel().isWindSpeedCanControl());
    switch (this.kkACManager.getCurrentACModel().getCurWindSpeed())
    {
    default:
      return;
    case 0:
      this.ivSpeed.setBackgroundResource(2130903520);
      this.tvSpeed.setText(2131099870);
      return;
    case 3:
      this.ivSpeed.setBackgroundResource(2130903519);
      this.tvSpeed.setText(2131099869);
      return;
    case 1:
      this.ivSpeed.setBackgroundResource(2130903517);
      this.tvSpeed.setText(2131099867);
      return;
    case 2:
    }
    this.ivSpeed.setBackgroundResource(2130903518);
    this.tvSpeed.setText(2131099868);
  }

  public void add(View paramView)
  {
    if (this.kkACManager == null);
    do
    {
      return;
      this.kkACManager.getCurrentACModel().increaseTmp();
      byte2Hexstr(this.kkACManager.getACKeyIr());
      initACPannel();
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131100520)) != null)
      {
        sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131100520)));
        return;
      }
      this.clickTime = (1 + this.clickTime);
    }
    while (this.opRcType.equals("OP_RC_TYPE_TIME"));
    if (this.oldACManager == null)
    {
      sendCmd(this.kkACManager.getACKeyIr());
      return;
    }
    sendRcCodeByChineseName("温度-");
  }

  public void back()
  {
    if (this.kkACManager.getACStateV2InString() == null)
      finish();
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).acState = this.kkACManager.getACStateV2InString();
    if (this.opRcType.equals("OP_AT_TYPE_CREATE"))
      if (!this.isSeleted)
        finish();
    do
    {
      return;
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = getTitleTest();
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).acState = ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).acState;
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).irDatas.size() > 0)
        MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
      setResult(10000);
      finish();
      return;
    }
    while (this.opRcType.equals("OP_RC_TYPE_TIME"));
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = getTitleTest();
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).acState = ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).acState;
    MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
    setResult(110000);
    finish();
  }

  public void changeRcCode()
  {
    this.clickTime = 0;
    this.opType = "OP_AT_TYPE_CREATE";
    KookongSDK.getAllRemoteIds(5, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).brandId, 0, 0, new IRequestResult()
    {
      public void onFail(Integer paramInteger, String paramString)
      {
        Toast.makeText(AtAirConActivity.this, 2131100076, 0).show();
      }

      public void onSuccess(String paramString, RemoteList paramRemoteList)
      {
        AtAirConActivity.this.rids = paramRemoteList.rids;
        if (AtAirConActivity.this.brandId == 97)
          AtAirConActivity.this.rids.add(0, AtAirConActivity.this.rids.remove(1));
        AtAirConActivity.this.setRcLibCount(AtAirConActivity.this.rids.size());
        AtAirConActivity.this.getRcCode(0);
      }
    });
  }

  public void cloudMode(View paramView)
  {
    if (this.kkACManager == null);
    do
    {
      return;
      if (this.kkACManager.getAcStateV2().changeToTargetModel(0) == 1)
      {
        updateModes();
        updateTemptures();
        updateWindSpeed();
      }
      initACPannel();
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131099857)) != null)
      {
        sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131099857)));
        return;
      }
      this.clickTime = (1 + this.clickTime);
    }
    while (this.opRcType.equals("OP_RC_TYPE_TIME"));
    sendCmd(this.kkACManager.getACKeyIr());
  }

  public void last(int paramInt)
  {
    this.kkACManager.onPause();
    getRcCode(paramInt);
  }

  public void mode(View paramView)
  {
    if (this.kkACManager == null);
    do
    {
      return;
      this.kkACManager.changeACModel();
      updateModes();
      updateTemptures();
      updateWindSpeed();
      initACPannel();
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(R.string.mode)) != null)
      {
        sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(R.string.mode)));
        return;
      }
      this.clickTime = (1 + this.clickTime);
    }
    while (this.opRcType.equals("OP_RC_TYPE_TIME"));
    if (this.oldACManager == null)
    {
      sendCmd(this.kkACManager.getACKeyIr());
      return;
    }
    sendRcCodeByChineseName("模式");
  }

  public void next(int paramInt)
  {
    this.kkACManager.onPause();
    getRcCode(paramInt);
  }

  public void off(View paramView)
  {
    if (this.kkACManager == null);
    do
    {
      return;
      this.kkACManager.changePowerState();
      initACPannel();
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131100234)) != null)
      {
        sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131100234)));
        return;
      }
      this.clickTime = (1 + this.clickTime);
    }
    while (this.opRcType.equals("OP_RC_TYPE_TIME"));
    if (this.oldACManager == null)
    {
      sendCmd(this.kkACManager.getACKeyIr());
      return;
    }
    sendRcCodeByChineseName("电源");
  }

  public void on(View paramView)
  {
    if (this.kkACManager == null);
    do
    {
      return;
      this.kkACManager.changePowerState();
      if (this.kkACManager.getPowerState() == 1);
      initACPannel();
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131100234)) != null)
      {
        sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131100234)));
        return;
      }
      this.clickTime = (1 + this.clickTime);
    }
    while (this.opRcType.equals("OP_RC_TYPE_TIME"));
    if (this.oldACManager == null)
    {
      sendCmd(this.kkACManager.getACKeyIr());
      return;
    }
    sendRcCodeByChineseName("电源");
  }

  public void onBottomViewShow()
  {
    this.rlOff.setLayoutParams(getMarginBottomScreenParams());
    this.rl_parent.setLayoutParams(getMarginBottomScreenParams());
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968657);
    ButterKnife.bind(this);
    setTitleView();
    initSystemBar(this, Color.parseColor("#29BFF7"));
    init();
  }

  public void onLearnOk(String paramString, byte[] paramArrayOfByte)
  {
    if (this.opRcType.equals("OP_AT_TYPE_CREATE"))
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.put(paramString, paramArrayOfByte);
    while (true)
    {
      MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
      return;
      if (this.opRcType.equals("OP_RC_TYPE_TIME"))
        continue;
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.put(paramString, paramArrayOfByte);
    }
  }

  protected void onStop()
  {
    if (this.kkACManager != null)
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).acState = this.kkACManager.getACStateV2InString();
    super.onStop();
  }

  public void orientation(View paramView)
  {
    if (this.kkACManager == null);
    do
    {
      return;
      this.kkACManager.changeUDWindDirect(ACStateV2.UDWindDirectKey.UDDIRECT_KEY_FIX);
      this.direction = (1 + this.direction);
      updateWindDirectBt();
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131099859)) != null)
      {
        sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131099859)));
        return;
      }
      this.clickTime = (1 + this.clickTime);
    }
    while (this.opRcType.equals("OP_RC_TYPE_TIME"));
    if (this.oldACManager == null)
    {
      sendCmd(this.kkACManager.getACKeyIr());
      return;
    }
    sendRcCodeByChineseName("上下定风");
  }

  public void seleted(int paramInt)
  {
    ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(2131100078), false);
    localProgressDialog.setCancelable(false);
    KookongSDK.getIRDataById(this.rids.get(paramInt) + "", 5, new IRequestResult(localProgressDialog)
    {
      public void onFail(Integer paramInteger, String paramString)
      {
        this.val$dialog.dismiss();
        AtAirConActivity.this.showFaildToast(paramString);
      }

      public void onSuccess(String paramString, IrDataList paramIrDataList)
      {
        this.val$dialog.dismiss();
        AtAirConActivity.this.irDatas.clear();
        AtAirConActivity.this.irDatas.addAll(paramIrDataList.getIrDataList());
        AtAirConActivity.this.rlOff.setLayoutParams(AtAirConActivity.this.getFullScreenParams());
        AtAirConActivity.this.rl_parent.setLayoutParams(AtAirConActivity.this.getFullScreenParams());
        ((MyRcDevice)AtAirConActivity.this.rcDevices.myRcDevices.get(AtAirConActivity.this.existRcPosi)).irDatas.clear();
        ((MyRcDevice)AtAirConActivity.this.rcDevices.myRcDevices.get(AtAirConActivity.this.existRcPosi)).irDatas.addAll(AtAirConActivity.this.irDatas);
        MyDb.getInstance(AtAirConActivity.this.getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, AtAirConActivity.this.rcDevices);
      }
    });
  }

  public void sendRcCodeByChineseName(String paramString)
  {
    if (this.oldACManager == null);
    while (true)
    {
      return;
      for (int i = 0; i < this.oldACManager.getAllKeys().size(); i++)
      {
        if (!((IrData.IrKey)this.oldACManager.getAllKeys().get(i)).fname.equals(paramString))
          continue;
        sendCmd(this.oldACManager.getKeyIr(((IrData.IrKey)this.oldACManager.getAllKeys().get(i)).fkey));
      }
    }
  }

  public void setting()
  {
    if (this.kkACManager.getACStateV2InString() == null)
      finish();
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).acState = this.kkACManager.getACStateV2InString();
    Intent localIntent;
    InnerRcVo localInnerRcVo;
    if (this.opRcType.equals("OP_RC_TYPE_TIME"))
    {
      localIntent = new Intent();
      localInnerRcVo = new InnerRcVo();
      localInnerRcVo.setName(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName);
      localInnerRcVo.setTypeStr("air");
      localInnerRcVo.setmSaveRcListPosi(this.existRcPosi);
      localInnerRcVo.setmType(5);
      if (this.kkACManager.getPowerState() != 0)
        break label197;
      localInnerRcVo.setStatus(getModeStr() + "," + this.wendu.getText().toString() + "℃");
    }
    while (true)
    {
      localIntent.putExtra(InnerRcVo.class.getName(), localInnerRcVo);
      setResult(50000, localIntent);
      finish();
      return;
      label197: localInnerRcVo.setStatus(getString(2131100226));
    }
  }

  public void speed(View paramView)
  {
    if (this.kkACManager == null);
    do
    {
      return;
      this.kkACManager.getCurrentACModel().changeWindSpeed();
      initACPannel();
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131099866)) != null)
      {
        sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131099866)));
        return;
      }
      this.clickTime = (1 + this.clickTime);
    }
    while (this.opRcType.equals("OP_RC_TYPE_TIME"));
    if (this.oldACManager == null)
    {
      sendCmd(this.kkACManager.getACKeyIr());
      return;
    }
    sendRcCodeByChineseName("风量");
  }

  public void stub(View paramView)
  {
    if (this.kkACManager == null);
    do
    {
      return;
      this.kkACManager.getCurrentACModel().decreaseTmp();
      initACPannel();
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131100519)) != null)
      {
        sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131100519)));
        return;
      }
      this.clickTime = (1 + this.clickTime);
    }
    while (this.opRcType.equals("OP_RC_TYPE_TIME"));
    if (this.oldACManager == null)
    {
      sendCmd(this.kkACManager.getACKeyIr());
      return;
    }
    sendRcCodeByChineseName("温度+");
  }

  public void sweep(View paramView)
  {
    if (this.kkACManager == null);
    do
    {
      return;
      this.kkACManager.getAcStateV2().changeUDWindDirect(ACStateV2.UDWindDirectKey.UDDIRECT_KEY_SWING);
      updateWindDirectBt();
      if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131099865)) != null)
      {
        sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131099865)));
        return;
      }
      this.clickTime = (1 + this.clickTime);
    }
    while (this.opRcType.equals("OP_RC_TYPE_TIME"));
    if (this.oldACManager == null)
    {
      sendCmd(this.kkACManager.getACKeyIr());
      return;
    }
    sendRcCodeByChineseName("上下摆风");
  }

  public void warmMode(View paramView)
  {
    if (this.kkACManager == null)
      return;
    if (this.kkACManager.getAcStateV2().changeToTargetModel(1) == 1)
    {
      updateModes();
      updateTemptures();
      updateWindSpeed();
    }
    initACPannel();
    if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131099871)) != null)
    {
      sendLearnedCmd((byte[])((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.get(getString(2131099871)));
      return;
    }
    this.clickTime = (1 + this.clickTime);
    if (!this.opRcType.equals("OP_RC_TYPE_TIME"))
      sendCmd(this.kkACManager.getACKeyIr());
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).isNorth = true;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.AtAirConActivity
 * JD-Core Version:    0.6.0
 */