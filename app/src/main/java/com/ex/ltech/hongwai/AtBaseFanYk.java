package com.ex.ltech.hongwai;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ex.ltech.SystemBarTintManager;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
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
import io.xlink.wifi.sdk.listener.SendPipeListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class AtBaseFanYk extends Activity
  implements View.OnClickListener
{
  private RippleView back;
  public CmdDateBussiness cmd;
  public boolean isSeleted;
  private int rcLibCount;
  private int rcLibPosi = 0;
  private RelativeLayout rlLast;
  private RelativeLayout rlNext;
  private RelativeLayout rlSeleted;
  private RippleView setting;
  private TextView title;
  private TextView tvLast;
  private TextView tvNext;
  private TextView tvSeleted;

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

  protected void hideSeletedRcLib()
  {
    this.rlLast.setVisibility(View.GONE);
    this.rlSeleted.setVisibility(View.GONE);
    this.rlNext.setVisibility(View.GONE);
  }

  String int2Chinese(int paramInt)
  {
    if (!isZh())
      return paramInt + "";
    String[] arrayOfString1 = { "", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
    String[] arrayOfString2 = { "", "十", "百", "千", "万", "十", "百", "千", "亿" };
    String str = String.valueOf(paramInt);
    System.out.println(str);
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < str.length(); i++)
      localStringBuffer = localStringBuffer.append(arrayOfString1[Integer.parseInt(String.valueOf(str.charAt(i)))]);
    int j = String.valueOf(localStringBuffer).length();
    int m;
    for (int k = 0; j > 0; k = m)
    {
      m = k + 1;
      localStringBuffer = localStringBuffer.insert(j, arrayOfString2[k]);
      j--;
    }
    System.out.println(localStringBuffer);
    return localStringBuffer.toString();
  }

  public abstract void last(int paramInt);

  public abstract void next(int paramInt);

  public void onClick(View paramView)
  {
    if (paramView == this.back)
    {
      if (!this.isSeleted)
        finish();
      back();
    }
    if (paramView == this.setting)
      setting();
    if (paramView == this.rlSeleted)
      showSaveYkDialog();
    if (paramView == this.rlLast)
      if (this.rcLibPosi != 0);
    do
    {
      return;
      this.rcLibPosi = (-1 + this.rcLibPosi);
      last(this.rcLibPosi);
    }
    while ((paramView != this.rlNext) || (this.rcLibPosi == -1 + this.rcLibCount));
    this.rcLibPosi = (1 + this.rcLibPosi);
    next(this.rcLibPosi);
  }

  public abstract void seleted(int paramInt);

  public void sendCmd(byte[] paramArrayOfByte)
  {
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
        byte[] arrayOfByte = localMyBusiness.getCmdData(localArrayList);
        StringUtils.btye2Str(arrayOfByte);
        XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent2.sendPipeData(DeviceManage.getxDevice(), arrayOfByte, new SendPipeListener()
        {
          public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
          {
          }
        });
        return;
      }
      XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent1.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiTest(paramArrayOfByte), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
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

  public void setRcLibCount(int paramInt)
  {
    this.rcLibCount = paramInt;
  }

  protected void setSettingIc(int paramInt)
  {
    this.setting.setBackgroundResource(paramInt);
  }

  protected void setTitleText(int paramInt1, int paramInt2)
  {
    this.title.setText(paramInt1);
    this.title.setTextColor(paramInt2);
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
    this.tvNext = ((TextView)findViewById(2131558869));
    this.back.setOnClickListener(this);
    this.setting.setOnClickListener(this);
    this.rlNext.setOnClickListener(this);
    this.rlSeleted.setOnClickListener(this);
    this.rlLast.setOnClickListener(this);
    this.cmd = new CmdDateBussiness(getApplicationContext(), "0000");
    hideSeletedRcLib();
  }

  public abstract void setting();

  public void showSaveYkDialog()
  {
    MyAlertDialog8 localMyAlertDialog8 = new MyAlertDialog8(this);
    localMyAlertDialog8.show();
    localMyAlertDialog8.setMyOnClickListener(new MyAlertDialog8.MyOnClickListener()
    {
      public void onClick(int paramInt)
      {
        if (paramInt == 0)
        {
          AtBaseFanYk.this.seleted(AtBaseFanYk.this.rcLibPosi);
          AtBaseFanYk.this.hideSeletedRcLib();
          AtBaseFanYk.this.isSeleted = true;
        }
      }
    });
  }

  protected void showSeletedRcLib()
  {
    this.rlLast.setVisibility(View.VISIBLE);
    this.rlSeleted.setVisibility(View.VISIBLE);
    this.rlNext.setVisibility(View.VISIBLE);
    if (this.rcLibPosi == 0)
      this.rlLast.setVisibility(View.GONE);
    if (this.rcLibPosi == -1 + this.rcLibCount)
      this.rlNext.setVisibility(View.GONE);
    this.tvLast.setText(getString(2131100035) + int2Chinese(1 + this.rcLibPosi) + getString(2131100072));
    this.tvNext.setText(getString(2131100035) + int2Chinese(3 + this.rcLibPosi) + getString(2131100072));
  }

  public void vibrate()
  {
    ((Vibrator)getSystemService("vibrator")).vibrate(100L);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.AtBaseFanYk
 * JD-Core Version:    0.6.0
 */