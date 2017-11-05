package com.ex.ltech.hongwai;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.ToneGenerator;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.ex.ltech.SystemBarTintManager;
import com.ex.ltech.hongwai.atRcs.AtDiy;
import com.ex.ltech.hongwai.atRcs.AtRcSet;
import com.ex.ltech.hongwai.view.ImageTextButtonLongClickListener;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.my_view.MyAlertDialog11;
import com.ex.ltech.led.my_view.MyAlertDialog13;
import com.ex.ltech.led.my_view.MyAlertDialog8;
import com.ex.ltech.led.my_view.MyAlertDialog8.MyOnClickListener;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.room.RoomBusiness;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.indris.material.RippleView;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public abstract class AtBaseYk extends FragmentActivity
  implements View.OnClickListener, ImageTextButtonLongClickListener
{
  public int action;
  private RippleView back;
  boolean canLearn = true;
  public int clickTime;
  private Button close_tips;
  public CmdDateBussiness cmd;
  public boolean isSeleted;
  public boolean isShowSaveYkDialogAlert;
  MyAlertDialog11 learnDialog;
  String learnKey;
  MyAlertDialog13 learnOkDialog;
  public int mRcCount;
  public int mRcPosi;
  ToneGenerator mToneGenerator = new ToneGenerator(8, 80);
  MyLearnListener myLearnListener;
  public String opType = "";
  private int rcLibCount;
  private int rcLibPosi = 0;
  byte[] resendData;
  private RelativeLayout rlLast;
  private RelativeLayout rlNext;
  private RelativeLayout rlSeleted;
  RelativeLayout rl_bottom_view;
  private RelativeLayout rl_tips;
  private RippleView setting;
  private TextView title;
  private TextView tvLast;
  private TextView tvNext;
  private TextView tvSeleted;
  private TextView tvSetting;

  public static void initSystemBar(Activity paramActivity, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19)
      setTranslucentStatus(paramActivity, true);
    SystemBarTintManager localSystemBarTintManager = new SystemBarTintManager(paramActivity);
    localSystemBarTintManager.setStatusBarTintEnabled(true);
    localSystemBarTintManager.setStatusBarTintColor(paramInt);
  }

  private boolean isZh()
  {
    Locale localLocale = getResources().getConfiguration().locale;
    String str1 = localLocale.getCountry();
    String str2 = localLocale.getLanguage();
    return (str2 + "_" + str1).equals("zh_CN");
  }

  private void learn(String paramString)
  {
    this.learnKey = paramString;
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    XDevice localXDevice = DeviceManage.getxDevice();
    byte[] arrayOfByte = this.cmd.hongWaiLearn();
    this.resendData = arrayOfByte;
    localXlinkAgent.sendPipeData(localXDevice, arrayOfByte, new SendPipeListener()
    {
      public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
      {
      }
    });
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), AtBaseYk.this.resendData, new SendPipeListener()
        {
          public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
          {
          }
        });
      }
    }
    , 50L);
    XlinkAgent.getInstance().addXlinkListener(this.myLearnListener);
  }

  @TargetApi(19)
  private static void setTranslucentStatus(Activity paramActivity, boolean paramBoolean)
  {
    Window localWindow = paramActivity.getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    if (paramBoolean);
    for (localLayoutParams.flags = (0x4000000 | localLayoutParams.flags); ; localLayoutParams.flags = (0xFBFFFFFF & localLayoutParams.flags))
    {
      localWindow.setAttributes(localLayoutParams);
      return;
    }
  }

  public static String toHex(byte paramByte)
  {
    String str = Integer.toHexString(paramByte & 0xFF);
    if (str.length() == 1)
      str = '0' + str;
    return str;
  }

  public void addCheckSumData(List<Integer> paramList)
  {
    int i = 0;
    for (int j = 0; j < paramList.size(); j++)
      i += ((Integer)paramList.get(j)).intValue();
    String str1 = Integer.toHexString(i);
    if (str1.length() == 3)
      str1 = "0" + str1;
    String str2 = str1.substring(-2 + str1.length(), str1.length());
    String str3 = str1.substring(0, 2);
    paramList.add(Integer.valueOf(str2, 16));
    paramList.add(Integer.valueOf(str3, 16));
  }

  public abstract void back();

  public abstract void changeRcCode();

  public void checkClickTime()
  {
    if ((this.opType.equals("OP_AT_TYPE_CREATE")) && (this.clickTime == 1))
      this.rl_tips.setVisibility(View.VISIBLE);
    if ((this.opType.equals("OP_AT_TYPE_CREATE")) && (this.clickTime == 3))
      showSaveYkDialog();
  }

  public int dp2px(float paramFloat)
  {
    return (int)(0.5F + paramFloat * getResources().getDisplayMetrics().density);
  }

  public RelativeLayout.LayoutParams getFullScreenParams()
  {
    hideBottomView();
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams.setMargins(0, dp2px(50.0F), 0, 0);
    return localLayoutParams;
  }

  public RelativeLayout.LayoutParams getFullScreenParams(int paramInt)
  {
    hideBottomView();
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams.setMargins(0, dp2px(paramInt), 0, 0);
    return localLayoutParams;
  }

  public RelativeLayout.LayoutParams getMarginBottomScreenParams()
  {
    showBottomView();
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams.setMargins(0, dp2px(50.0F), 0, dp2px(50.0F));
    return localLayoutParams;
  }

  public String getTitleTest()
  {
    return this.title.getText().toString();
  }

  public void goSettingAt()
  {
    startActivityForResult(new Intent(this, AtRcSet.class).putExtra("OP_AT_POSI_KEY", this.mRcPosi), 0);
  }

  public void hideBottomView()
  {
    this.rl_bottom_view.setVisibility(View.GONE);
  }

  protected void hideSeletedRcLib()
  {
    this.rlLast.setVisibility(View.GONE);
    this.rlSeleted.setVisibility(View.GONE);
    this.rlNext.setVisibility(View.GONE);
  }

  public void hideSetting()
  {
    this.setting.setVisibility(View.GONE);
  }

  String int2Chinese(int paramInt)
  {
    if (isZh())
      return paramInt + "";
    String[] arrayOfString = { "", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
    StringBuffer localStringBuffer = new StringBuffer();
    int i = paramInt / 10;
    int j = paramInt - i * 10;
    if (i > 1)
      localStringBuffer.append(arrayOfString[i]);
    if (i > 0)
      localStringBuffer.append(arrayOfString[10]);
    localStringBuffer.append(arrayOfString[j]);
    return localStringBuffer.toString();
  }

  public abstract void last(int paramInt);

  public abstract void next(int paramInt);

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 90000)
    {
      setResult(90000);
      finish();
    }
    if (paramInt2 == 80000)
    {
      changeRcCode();
      this.isShowSaveYkDialogAlert = true;
    }
    if (paramInt2 == 100000)
    {
      this.title.setText(paramIntent.getStringExtra("RC_NAME_KEY"));
      setResult(100000);
    }
  }

  public abstract void onBottomViewShow();

  public void onClick(View paramView)
  {
    if (paramView == this.back)
      back();
    if (paramView == this.close_tips)
      this.rl_tips.setVisibility(View.GONE);
    if (paramView == this.tvSetting)
      setting();
    if (paramView == this.setting)
      goSettingAt();
    if (paramView == this.rlSeleted)
    {
      this.setting.setVisibility(View.VISIBLE);
      seleted(this.rcLibPosi);
      hideSeletedRcLib();
      this.isSeleted = true;
    }
    if (paramView == this.rlLast)
      if (this.rcLibPosi != 0);
    do
    {
      return;
      this.action = 1;
      this.rcLibPosi = (-1 + this.rcLibPosi);
      last(this.rcLibPosi);
    }
    while (paramView != this.rlNext);
    if (this.rcLibPosi == -1 + this.rcLibCount)
    {
      showGoDiyDialog();
      return;
    }
    this.action = 2;
    this.rcLibPosi = (1 + this.rcLibPosi);
    next(this.rcLibPosi);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    XlinkAgent.getInstance().removeListener(this.myLearnListener);
  }

  public void onImageTextButtonLongClick(String paramString)
  {
    if (this.canLearn)
      showLearn(paramString);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      back();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public abstract void onLearnOk(String paramString, byte[] paramArrayOfByte);

  public abstract void seleted(int paramInt);

  public void sendCmd(byte[] paramArrayOfByte)
  {
    checkClickTime();
    vibrate();
    try
    {
      if (DeviceListActivity.isOnePointFive)
      {
        Dvc localDvc = (Dvc)new RoomBusiness(this).getData("dvcInRoom", Dvc.class);
        localDvc.getId();
        ArrayList localArrayList = new ArrayList();
        MyBusiness localMyBusiness = new MyBusiness(this);
        localMyBusiness.addNormalHeadData(localArrayList);
        localArrayList.add(Integer.valueOf(20));
        localArrayList.add(Integer.valueOf(4 + paramArrayOfByte.length));
        localArrayList.add(Integer.valueOf(97));
        localArrayList.add(Integer.valueOf(1 + localDvc.getRoomIndex()));
        localArrayList.add(Integer.valueOf(localDvc.getmIndex()));
        localArrayList.add(Integer.valueOf(1));
        for (int i = 0; i < paramArrayOfByte.length; i++)
          localArrayList.add(Integer.valueOf(toHex(paramArrayOfByte[i]), 16));
        localArrayList.add(Integer.valueOf(1));
        addCheckSumData(localArrayList);
        localArrayList.add(Integer.valueOf(22));
        byte[] arrayOfByte2 = localMyBusiness.getCmdData(localArrayList);
        StringUtils.btye2Str(arrayOfByte2);
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), arrayOfByte2, new SendPipeListener()
        {
          public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
          {
          }
        });
        return;
      }
      SocketManager localSocketManager = SocketManager.instance();
      byte[] arrayOfByte1 = this.cmd.hongWaiTest(paramArrayOfByte);
      this.resendData = arrayOfByte1;
      localSocketManager.sendData(arrayOfByte1);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void sendLearnedCmd(byte[] paramArrayOfByte)
  {
    vibrate();
    SocketManager.instance().sendData(this.cmd.learnedRcCode(paramArrayOfByte));
  }

  public void sendRcParmas(byte[] paramArrayOfByte)
  {
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiId(paramArrayOfByte), new SendPipeListener()
    {
      public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
      {
      }
    });
  }

  protected void setBackIc(int paramInt)
  {
    this.back.setBackgroundResource(paramInt);
  }

  public void setCanLearn(boolean paramBoolean)
  {
    this.canLearn = paramBoolean;
  }

  public void setRcLibCount(int paramInt)
  {
    this.rcLibCount = paramInt;
  }

  protected void setSettingIc(int paramInt)
  {
    this.setting.setBackgroundResource(paramInt);
  }

  protected void setSettingText(int paramInt)
  {
    this.setting.setVisibility(View.GONE);
    this.tvSetting.setVisibility(View.VISIBLE);
    this.tvSetting.setText(paramInt);
  }

  protected void setSettingText(int paramInt1, int paramInt2)
  {
    this.setting.setVisibility(View.GONE);
    this.tvSetting.setVisibility(View.VISIBLE);
    this.tvSetting.setText(paramInt1);
    this.tvSetting.setTextColor(paramInt2);
  }

  protected void setSettingText(String paramString)
  {
    this.setting.setVisibility(View.GONE);
    this.tvSetting.setVisibility(View.VISIBLE);
    this.tvSetting.setText(paramString);
  }

  protected void setSettingVisiable(int paramInt)
  {
    this.setting.setVisibility(paramInt);
  }

  protected void setTitleText(int paramInt1, int paramInt2)
  {
    this.title.setText(paramInt1);
    this.title.setTextColor(paramInt2);
  }

  protected void setTitleText(String paramString, int paramInt)
  {
    this.title.setText(paramString);
    this.title.setTextColor(paramInt);
  }

  protected void setTitleView()
  {
    this.back = ((RippleView)findViewById(2131558858));
    this.title = ((TextView)findViewById(2131558469));
    this.setting = ((RippleView)findViewById(2131558859));
    this.rlLast = ((RelativeLayout)findViewById(2131558864));
    this.tvLast = ((TextView)findViewById(2131558865));
    this.rlSeleted = ((RelativeLayout)findViewById(2131558866));
    this.tvSeleted = ((TextView)findViewById(2131558867));
    this.rlNext = ((RelativeLayout)findViewById(2131558868));
    this.rl_bottom_view = ((RelativeLayout)findViewById(2131558863));
    this.tvNext = ((TextView)findViewById(2131558869));
    this.tvSetting = ((TextView)findViewById(2131558860));
    this.rl_tips = ((RelativeLayout)findViewById(2131558861));
    this.close_tips = ((Button)findViewById(2131558862));
    this.back.setOnClickListener(this);
    this.setting.setOnClickListener(this);
    this.rlNext.setOnClickListener(this);
    this.rlSeleted.setOnClickListener(this);
    this.rlLast.setOnClickListener(this);
    this.tvSetting.setOnClickListener(this);
    this.close_tips.setOnClickListener(this);
    this.cmd = new CmdDateBussiness(getApplicationContext(), "0000");
    hideSeletedRcLib();
    this.myLearnListener = new MyLearnListener();
    this.learnOkDialog = new MyAlertDialog13(this);
    this.learnDialog = new MyAlertDialog11(this);
  }

  @Deprecated
  public abstract void setting();

  public void showBottomView()
  {
    this.rl_bottom_view.setVisibility(View.VISIBLE);
  }

  public void showFaildToast(String paramString)
  {
    if (paramString.equals("code 6"))
      Toast.makeText(this, 2131100056, 0).show();
    if (paramString.equals("code 10"))
      Toast.makeText(this, 2131100057, 0).show();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        AtBaseYk.this.finish();
      }
    }
    , 500L);
  }

  public void showGoDiyDialog()
  {
    MyAlertDialog8 localMyAlertDialog8 = new MyAlertDialog8(this);
    localMyAlertDialog8.setCancelable(false);
    localMyAlertDialog8.showDiyStyle();
    localMyAlertDialog8.setMyOnClickListener(new MyAlertDialog8.MyOnClickListener(localMyAlertDialog8)
    {
      public void onClick(int paramInt)
      {
        if (paramInt == 0)
        {
          Intent localIntent = new Intent(AtBaseYk.this, AtDiy.class);
          localIntent.putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_CREATE");
          localIntent.putExtra("OP_AT_POSI_KEY", AtBaseYk.this.mRcCount);
          AtBaseYk.this.startActivityForResult(localIntent, 0);
          AtBaseYk.this.setResult(10000);
          AtBaseYk.this.finish();
          return;
        }
        this.val$dialog8.dismiss();
      }
    });
  }

  public void showLearn(String paramString)
  {
    this.learnDialog.show();
    this.learnDialog.timer();
    this.learnDialog.test(paramString);
    learn(paramString);
  }

  public void showSaveYkDialog()
  {
    this.isShowSaveYkDialogAlert = true;
    MyAlertDialog8 localMyAlertDialog8 = new MyAlertDialog8(this);
    localMyAlertDialog8.setCancelable(false);
    localMyAlertDialog8.show();
    localMyAlertDialog8.setMyOnClickListener(new MyAlertDialog8.MyOnClickListener()
    {
      public void onClick(int paramInt)
      {
        if (paramInt == 0)
        {
          AtBaseYk.this.setting.setVisibility(View.VISIBLE);
          AtBaseYk.this.seleted(AtBaseYk.this.rcLibPosi);
          AtBaseYk.this.hideSeletedRcLib();
          AtBaseYk.this.isSeleted = true;
          AtBaseYk.this.rl_tips.setVisibility(View.GONE);
          return;
        }
        AtBaseYk.this.showSeletedRcLib();
      }
    });
  }

  protected void showSeletedRcLib()
  {
    onBottomViewShow();
    this.rlLast.setVisibility(View.VISIBLE);
    this.rlSeleted.setVisibility(View.VISIBLE);
    this.rlNext.setVisibility(View.VISIBLE);
    if (this.rcLibPosi == 0)
      this.rlLast.setVisibility(View.GONE);
    this.tvLast.setText(getString(2131100035) + int2Chinese(this.rcLibPosi) + getString(2131100072));
    if (this.rcLibPosi == -1 + this.rcLibCount)
    {
      this.tvNext.setText(2131100468);
      return;
    }
    this.tvNext.setText(getString(2131100035) + int2Chinese(2 + this.rcLibPosi) + getString(2131100072));
  }

  public void showSetting()
  {
    this.setting.setVisibility(View.VISIBLE);
    this.tvSetting.setVisibility(View.GONE);
  }

  public void showSettingBtn()
  {
    this.setting.setVisibility(View.VISIBLE);
    this.tvSetting.setVisibility(View.GONE);
  }

  public void showTips()
  {
  }

  void vibrate()
  {
    this.mToneGenerator.startTone(27, 98);
  }

  class MyLearnListener
    implements XlinkNetListener
  {
    MyLearnListener()
    {
    }

    public void onDataPointUpdate(XDevice paramXDevice, List<DataPoint> paramList, int paramInt)
    {
    }

    public void onDeviceStateChanged(XDevice paramXDevice, int paramInt)
    {
    }

    public void onDisconnect(int paramInt)
    {
    }

    public void onEventNotify(EventNotify paramEventNotify)
    {
    }

    public void onLocalDisconnect(int paramInt)
    {
    }

    public void onLogin(int paramInt)
    {
    }

    public void onRecvPipeData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
      AtBaseYk.this.runOnUiThread(new Runnable(paramArrayOfByte)
      {
        public void run()
        {
          if (AtBaseYk.this.learnOkDialog.isShowing());
          String str;
          do
          {
            return;
            str = StringUtil.byte2Hexstr(this.val$bytes);
            System.out.println(str);
          }
          while ((!str.substring(14, 16).equals("B3")) || (this.val$bytes.length == 9));
          System.out.println(StringUtil.byte2Hexstr(Arrays.copyOfRange(this.val$bytes, 9, -1 + this.val$bytes.length)));
          AtBaseYk.this.onLearnOk(AtBaseYk.this.learnKey, Arrays.copyOfRange(this.val$bytes, 9, -1 + this.val$bytes.length));
          AtBaseYk.this.learnDialog.dismiss();
          AtBaseYk.this.learnOkDialog.show();
        }
      });
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
    }

    public void onStart(int paramInt)
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.AtBaseYk
 * JD-Core Version:    0.6.0
 */