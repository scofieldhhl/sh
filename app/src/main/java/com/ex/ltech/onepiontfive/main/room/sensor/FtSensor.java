package com.ex.ltech.onepiontfive.main.room.sensor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
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
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.utils.UtilMath;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.newscene.ExpandableLvSceneBusiness;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.vo.RoomLvChildVo;
import com.ex.ltech.onepiontfive.main.vo.RoomLvData;
import com.ex.ltech.onepiontfive.main.vo.Scene;
import com.ex.ltech.onepiontfive.main.vo.Scenes;
import com.ex.ltech.onepiontfive.main.vo.SensorVo;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChanged;
import java.io.PrintStream;
import java.util.ArrayList;

public class FtSensor extends MyBaseFt
  implements View.OnClickListener
{
  Runnable TimeoutRunnable = new Runnable()
  {
    public void run()
    {
      if ((FtSensor.this.dialog != null) && (FtSensor.this.dialog.isShowing()))
        FtSensor.this.dialog.dismiss();
      FtSensor.this.shortToast(2131100336);
    }
  };
  SensorBiz biz;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;

  @Bind({2131559111})
  TextView condition;

  @Bind({2131559265})
  TextView delay;
  AlertDialog dialog;

  @Bind({2131559147})
  ImageView go1;

  @Bind({2131559150})
  ImageView go2;

  @Bind({2131559160})
  ImageView go3;
  Handler handler = new Handler();

  @Bind({2131559149})
  ImageView ic1;

  @Bind({2131559264})
  ImageView ic3;

  @Bind({2131559266})
  ImageView ic4;

  @Bind({2131559268})
  ToggleButton msgPushSwitch;

  @Bind({2131559263})
  ImageView onOff;

  @Bind({2131559145})
  RelativeLayout rl1;

  @Bind({2131559148})
  RelativeLayout rl2;

  @Bind({2131559159})
  RelativeLayout rl3;

  @Bind({2131559267})
  TextView rl4Info;

  @Bind({2131558993})
  RelativeLayout rlTitle;

  @Bind({2131558776})
  ImageView settingActiVersonUp;
  int[] shotDay = { 2131100240, 2131100240, 2131100467, 2131100430, 2131100071, 2131100066, 2131100404, 2131100394 };

  @Bind({2131559169})
  TextView status;

  @Bind({2131558981})
  TextView time;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;
  SensorVo vo;

  @Bind({2131559146})
  ImageView whiteBg;

  public void initTitleView()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtSensor.this.biz.saveCacheData(null);
        FtSensor.this.finish();
      }
    });
    this.tvTitleViewTitle.setText(2131100409);
    this.tvTitleViewEdit.setVisibility(0);
    this.tvTitleViewEdit.setText(2131100358);
    this.tvTitleViewEdit.setTextColor(getResources().getColor(2131492897));
    this.tvTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if ((!FtSensor.this.condition.getText().toString().equals("")) && (!FtSensor.this.time.getText().toString().equals("")) && (!FtSensor.this.delay.getText().toString().equals("")))
        {
          FtSensor.this.dialog = ProgressDialog.show(FtSensor.this.getActivity(), "", FtSensor.this.getString(2131100002), false);
          FtSensor.this.dialog.show();
          if (FtSensor.this.vo.getTouchType().equals("SensorTouchDeviceType"))
            FtSensor.this.biz.sendSensorTouchBlub(new MyBusiness.MySendListener()
            {
              public void onFail()
              {
                if ((FtSensor.this.dialog != null) && (FtSensor.this.dialog.isShowing()))
                  FtSensor.this.dialog.dismiss();
              }

              public void onOk(byte[] paramArrayOfByte)
              {
                if ((FtSensor.this.dialog != null) && (FtSensor.this.dialog.isShowing()))
                  FtSensor.this.dialog.dismiss();
                FtSensor.this.biz.saveCacheData(null);
                FtSensor.this.finish();
              }

              public void onTimeOut()
              {
                if ((FtSensor.this.dialog != null) && (FtSensor.this.dialog.isShowing()))
                  FtSensor.this.dialog.dismiss();
              }
            }
            , FtSensor.this.vo, FtSensor.this.getRequest().getIntExtra("DIndexKey", 0));
          if (FtSensor.this.vo.getTouchType().equals("SensorTouchSceneType"))
            FtSensor.this.biz.sendSensorTouchScene(new MyBusiness.MySendListener()
            {
              public void onFail()
              {
                if ((FtSensor.this.dialog != null) && (FtSensor.this.dialog.isShowing()))
                  FtSensor.this.dialog.dismiss();
              }

              public void onOk(byte[] paramArrayOfByte)
              {
                if ((FtSensor.this.dialog != null) && (FtSensor.this.dialog.isShowing()))
                  FtSensor.this.dialog.dismiss();
                FtSensor.this.finish();
              }

              public void onTimeOut()
              {
                if ((FtSensor.this.dialog != null) && (FtSensor.this.dialog.isShowing()))
                  FtSensor.this.dialog.dismiss();
              }
            }
            , FtSensor.this.vo, FtSensor.this.getRequest().getIntExtra("DIndexKey", 0));
        }
        Intent localIntent = new Intent("SenserOnOffBroadCast");
        localIntent.putExtra("DClickPosiKey", FtSensor.this.getRequest().getIntExtra("DClickPosiKey", 0));
        localIntent.putExtra("SenserOnOffKey", FtSensor.this.vo.isOpen());
        LocalBroadcastManager.getInstance(FtSensor.this.getActivity()).sendBroadcast(localIntent);
      }
    });
  }

  public void onClick(View paramView)
  {
    if (paramView == this.rl1)
      startFragmentForResult(new Request(FtSensorInfo.class), 0);
    if (paramView == this.rl2)
      startFragmentForResult(new Request(FtSensorTiming.class), 0);
    if (paramView == this.rl3)
      startFragmentForResult(new Request(FtSensorDelay.class), 0);
    boolean bool;
    int i;
    if (paramView == this.onOff)
    {
      SensorVo localSensorVo = this.vo;
      if (this.vo.isOpen())
        break label157;
      bool = true;
      localSensorVo.setOpen(bool);
      TextView localTextView = this.status;
      if (!this.vo.isOpen())
        break label162;
      i = 2131100248;
      label116: localTextView.setText(i);
      if (!this.condition.getText().toString().equals(""))
        break label169;
      Toast.makeText(getActivity(), 2131100388, 0).show();
    }
    label157: label162: label169: 
    do
    {
      return;
      bool = false;
      break;
      i = 2131099950;
      break label116;
      if (!this.time.getText().toString().equals(""))
        continue;
      Toast.makeText(getActivity(), 2131100455, 0).show();
      return;
    }
    while (!this.delay.getText().toString().equals(""));
    Toast.makeText(getActivity(), 2131100228, 0).show();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968778, null);
      this.biz = new SensorBiz(getActivity(), getRequest().getIntExtra("DRoomNumKey", 0), getRequest().getIntExtra("DClickPosiKey", 0));
      this.vo = new SensorVo();
      this.dialog = ProgressDialog.show(getActivity(), "", getString(2131100002), false);
      this.dialog.setCancelable(true);
      this.dialog.show();
    }
    try
    {
      this.handler.removeCallbacks(this.TimeoutRunnable);
      this.handler.postDelayed(this.TimeoutRunnable, 5000L);
      this.biz.syncDeviceInfo(new MyBusiness.MySendListener()
      {
        public void onFail()
        {
          if ((FtSensor.this.dialog != null) && (FtSensor.this.dialog.isShowing()))
            FtSensor.this.dialog.dismiss();
        }

        public void onOk(byte[] paramArrayOfByte)
        {
          String str1 = StringUtils.btye2Str(paramArrayOfByte);
          if (str1.length() < 114);
          int i;
          int j;
          label58: boolean bool3;
          label128: int i6;
          label252: boolean bool4;
          label547: ArrayList localArrayList4;
          int i7;
          label601: RepeatDayVo localRepeatDayVo2;
          while (true)
          {
            return;
            String str2 = str1.substring(18, 20);
            String str3 = str1.substring(20, 22);
            if (str2.equalsIgnoreCase("A3"))
              break;
            i = 1;
            if (str1.length() >= 64)
              break label716;
            j = 1;
            if ((i | j) != 0)
              continue;
            FtSensor.this.biz.responseMessage(str1.substring(4, 6), "23");
            if (!str3.equalsIgnoreCase("2A"))
              break label1041;
            int i5 = Integer.parseInt(str1.substring(38, 40), 16);
            SensorVo localSensorVo3 = FtSensor.this.vo;
            if (i5 != 1)
              break label722;
            bool3 = true;
            localSensorVo3.setOpen(bool3);
            FtSensor.this.vo.setTouchType("SensorTouchSceneType");
            FtSensor.this.vo.setTouchScenePosi(-1 + Integer.parseInt(str1.substring(42, 44), 16));
            i6 = Integer.parseInt(str1.substring(42, 44), 16);
            ExpandableLvSceneBusiness localExpandableLvSceneBusiness = new ExpandableLvSceneBusiness(FtSensor.this.getActivity());
            if (localExpandableLvSceneBusiness.getSmartScenes().smartScenes.size() <= i6 - 1)
              break label728;
            FtSensor.this.vo.setTouchSceneName(((Scene)localExpandableLvSceneBusiness.getSmartScenes().smartScenes.get(i6 - 1)).getName());
            if (i6 == 255)
              FtSensor.this.vo.setTouchSceneName(FtSensor.this.getString(2131100026));
            FtSensor.this.vo.setStartMin(String.valueOf(Integer.parseInt(str1.substring(44, 46), 16)));
            if (FtSensor.this.vo.getStartMin().length() == 1)
              FtSensor.this.vo.setStartMin("0" + String.valueOf(Integer.parseInt(str1.substring(44, 46), 16)));
            FtSensor.this.vo.setStartHour(String.valueOf(Integer.parseInt(str1.substring(46, 48), 16)));
            FtSensor.this.vo.setEndMin(String.valueOf(Integer.parseInt(str1.substring(48, 50), 16)));
            if (FtSensor.this.vo.getEndMin().length() == 1)
              FtSensor.this.vo.setEndMin("0" + String.valueOf(Integer.parseInt(str1.substring(48, 50), 16)));
            FtSensor.this.vo.setEndHout(String.valueOf(Integer.parseInt(str1.substring(50, 52), 16)));
            String str9 = UtilMath.hexString2binaryString(str1.substring(52, 54));
            SensorVo localSensorVo4 = FtSensor.this.vo;
            if (Integer.parseInt(str1.substring(54, 56), 16) != 1)
              break label768;
            bool4 = true;
            localSensorVo4.setPush(bool4);
            if (str9.equals("01111111"))
              str9 = "10000000";
            localArrayList4 = new ArrayList();
            StringBuffer localStringBuffer3 = new StringBuffer(str9);
            String str10 = localStringBuffer3.reverse().toString();
            i7 = 0;
            if (i7 >= str10.length())
              break label919;
            localRepeatDayVo2 = new RepeatDayVo();
            if (!str10.substring(i7, i7 + 1).equals("1"))
              break label774;
            localRepeatDayVo2.setSeleted(true);
            label645: switch (i7 + 1)
            {
            default:
            case 8:
            case 7:
            case 6:
            case 5:
            case 4:
            case 3:
            case 2:
            case 1:
            }
          }
          while (true)
          {
            localArrayList4.add(localRepeatDayVo2);
            i7++;
            break label601;
            i = 0;
            break;
            label716: j = 0;
            break label58;
            label722: bool3 = false;
            break label128;
            label728: FtSensor.this.vo.setTouchSceneName(FtSensor.this.getString(2131100408) + i6);
            break label252;
            label768: bool4 = false;
            break label547;
            label774: localRepeatDayVo2.setSeleted(false);
            break label645;
            localRepeatDayVo2.setDay(FtSensor.this.getString(2131100055));
            continue;
            localRepeatDayVo2.setDay(FtSensor.this.getString(2131100009));
            continue;
            localRepeatDayVo2.setDay(FtSensor.this.getString(2131100008));
            continue;
            localRepeatDayVo2.setDay(FtSensor.this.getString(2131100007));
            continue;
            localRepeatDayVo2.setDay(FtSensor.this.getString(2131100006));
            continue;
            localRepeatDayVo2.setDay(FtSensor.this.getString(2131100005));
            continue;
            localRepeatDayVo2.setDay(FtSensor.this.getString(2131100004));
            continue;
            localRepeatDayVo2.setDay(FtSensor.this.getString(2131100003));
          }
          label919: localArrayList4.add(0, localArrayList4.remove(-1 + localArrayList4.size()));
          FtSensor.this.vo.setRepeatDayVos(localArrayList4);
          FtSensor.this.vo.setDelayType(6);
          FtSensor.this.vo.setDelay(FtSensor.this.getActivity().getString(2131100022));
          FtSensor.this.biz.saveCacheData(FtSensor.this.vo);
          while (true)
          {
            FtSensor.this.setDataView();
            if ((FtSensor.this.dialog == null) || (!FtSensor.this.dialog.isShowing()))
              break;
            FtSensor.this.dialog.dismiss();
            return;
            label1041: int k = Integer.parseInt(str1.substring(38, 40), 16);
            SensorVo localSensorVo1 = FtSensor.this.vo;
            if (k == 1);
            ArrayList localArrayList1;
            for (boolean bool1 = true; ; bool1 = false)
            {
              localSensorVo1.setOpen(bool1);
              FtSensor.this.vo.setTouchType("SensorTouchDeviceType");
              String str4 = UtilMath.hexString2binaryString(str1.substring(42, 50));
              String str5 = str4.substring(24, 32) + str4.substring(16, 24) + str4.substring(8, 16) + str4.substring(0, 8);
              StringBuffer localStringBuffer1 = new StringBuffer(str5);
              String str6 = localStringBuffer1.reverse().toString();
              localArrayList1 = new ArrayList();
              for (int m = 0; m < str6.length(); m++)
              {
                if (!str6.substring(m, m + 1).equals("1"))
                  continue;
                localArrayList1.add(Integer.valueOf(m + 1));
              }
            }
            if (localArrayList1.size() > 0)
            {
              FtSensor.this.vo.setDeviceIndexs(localArrayList1);
              ArrayList localArrayList3 = new ExpandableLvSceneBusiness(FtSensor.this.getActivity()).getAddBlubListData().getRooms();
              if (localArrayList3 != null)
                for (int i2 = 0; i2 < localArrayList1.size(); i2++)
                  for (int i3 = 0; i3 < localArrayList3.size(); i3++)
                    for (int i4 = 0; i4 < ((Room)localArrayList3.get(i3)).getExpandableLvInnerItemVos().size(); i4++)
                    {
                      if (((RoomLvChildVo)((Room)localArrayList3.get(i3)).getExpandableLvInnerItemVos().get(i4)).getDvcIndex() != ((Integer)localArrayList1.get(i2)).intValue())
                        continue;
                      ((RoomLvChildVo)((Room)localArrayList3.get(i3)).getExpandableLvInnerItemVos().get(i4)).setSwich(true);
                    }
              FtSensor.this.vo.setRooms(localArrayList3);
            }
            FtSensor.this.vo.setStartMin(String.valueOf(Integer.parseInt(str1.substring(50, 52), 16)));
            if (FtSensor.this.vo.getStartMin().length() == 1)
              FtSensor.this.vo.setStartMin("0" + String.valueOf(Integer.parseInt(str1.substring(50, 52), 16)));
            FtSensor.this.vo.setStartHour(String.valueOf(Integer.parseInt(str1.substring(52, 54), 16)));
            FtSensor.this.vo.setEndMin(String.valueOf(Integer.parseInt(str1.substring(54, 56), 16)));
            if (FtSensor.this.vo.getEndMin().length() == 1)
              FtSensor.this.vo.setEndMin("0" + String.valueOf(Integer.parseInt(str1.substring(54, 56), 16)));
            FtSensor.this.vo.setEndHout(String.valueOf(Integer.parseInt(str1.substring(56, 58), 16)));
            int n = Integer.parseInt(str1.substring(58, 60), 16);
            FtSensor.this.vo.setDelayType(n);
            boolean bool2;
            label1778: ArrayList localArrayList2;
            int i1;
            label1832: RepeatDayVo localRepeatDayVo1;
            switch (n)
            {
            default:
              String str7 = UtilMath.hexString2binaryString(str1.substring(60, 62));
              SensorVo localSensorVo2 = FtSensor.this.vo;
              if (Integer.parseInt(str1.substring(62, 64), 16) != 1)
                break;
              bool2 = true;
              localSensorVo2.setPush(bool2);
              if (str7.equals("01111111"))
                str7 = "10000000";
              localArrayList2 = new ArrayList();
              StringBuffer localStringBuffer2 = new StringBuffer(str7);
              String str8 = localStringBuffer2.reverse().toString();
              i1 = 0;
              if (i1 >= str8.length())
                break label2248;
              localRepeatDayVo1 = new RepeatDayVo();
              if (str8.substring(i1, i1 + 1).equals("1"))
              {
                localRepeatDayVo1.setSeleted(true);
                label1876: switch (i1 + 1)
                {
                default:
                case 8:
                case 7:
                case 6:
                case 5:
                case 4:
                case 3:
                case 2:
                case 1:
                }
              }
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 0:
            }
            while (true)
            {
              localArrayList2.add(localRepeatDayVo1);
              i1++;
              break label1832;
              FtSensor.this.vo.setDelay(FtSensor.this.getActivity().getString(2131100017));
              break;
              FtSensor.this.vo.setDelay(FtSensor.this.getActivity().getString(2131100018));
              break;
              FtSensor.this.vo.setDelay(FtSensor.this.getActivity().getString(2131100019));
              break;
              FtSensor.this.vo.setDelay(FtSensor.this.getActivity().getString(2131100020));
              break;
              FtSensor.this.vo.setDelay(FtSensor.this.getActivity().getString(2131100021));
              break;
              FtSensor.this.vo.setDelay(FtSensor.this.getActivity().getString(2131100022));
              break;
              bool2 = false;
              break label1778;
              localRepeatDayVo1.setSeleted(false);
              break label1876;
              localRepeatDayVo1.setDay(FtSensor.this.getString(2131100055));
              continue;
              localRepeatDayVo1.setDay(FtSensor.this.getString(2131100009));
              continue;
              localRepeatDayVo1.setDay(FtSensor.this.getString(2131100008));
              continue;
              localRepeatDayVo1.setDay(FtSensor.this.getString(2131100007));
              continue;
              localRepeatDayVo1.setDay(FtSensor.this.getString(2131100006));
              continue;
              localRepeatDayVo1.setDay(FtSensor.this.getString(2131100005));
              continue;
              localRepeatDayVo1.setDay(FtSensor.this.getString(2131100004));
              continue;
              localRepeatDayVo1.setDay(FtSensor.this.getString(2131100003));
            }
            label2248: localArrayList2.add(0, localArrayList2.remove(-1 + localArrayList2.size()));
            FtSensor.this.vo.setRepeatDayVos(localArrayList2);
            FtSensor.this.biz.saveCacheData(FtSensor.this.vo);
          }
        }

        public void onTimeOut()
        {
          if ((FtSensor.this.dialog != null) && (FtSensor.this.dialog.isShowing()))
            FtSensor.this.dialog.dismiss();
        }
      }
      , 95, getRequest().getIntExtra("DRoomNumKey", 0), getRequest().getIntExtra("DIndexKey", 0));
      ButterKnife.bind(this, this.view);
      return this.view;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    System.out.println("ftsencor destroyView");
    this.handler.removeCallbacks(this.TimeoutRunnable);
    if (this.biz != null)
      this.biz.setMySendListener(null);
    ButterKnife.unbind(this);
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 10000)
    {
      this.vo = this.biz.getCacheSensorVo();
      setDataView();
    }
    if ((paramInt2 != 1000) || ((paramInt2 != 2000) || (paramInt2 == 3000)));
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    initTitleView();
    this.rl1.setOnClickListener(this);
    this.rl2.setOnClickListener(this);
    this.rl3.setOnClickListener(this);
    this.onOff.setOnClickListener(this);
    this.msgPushSwitch.setOnToggleChanged(new ToggleButton.OnToggleChanged()
    {
      public void onToggle(boolean paramBoolean)
      {
        FtSensor.this.vo.setPush(paramBoolean);
      }
    });
  }

  public void setDataView()
  {
    String str1 = "";
    if (this.vo.getTouchType().equals("SensorTouchDeviceType"))
    {
      ArrayList localArrayList = this.vo.getRooms();
      if (localArrayList != null)
        for (int k = 0; k < localArrayList.size(); k++)
          for (int m = 0; m < ((Room)localArrayList.get(k)).getExpandableLvInnerItemVos().size(); m++)
          {
            if (!((RoomLvChildVo)((Room)localArrayList.get(k)).getExpandableLvInnerItemVos().get(m)).isSwich())
              continue;
            str1 = str1 + "\t\t" + ((RoomLvChildVo)((Room)localArrayList.get(k)).getExpandableLvInnerItemVos().get(m)).getInnerDeviceName();
          }
      this.condition.setText(str1);
    }
    if (this.vo.getTouchType().equals("SensorTouchSceneType"))
      this.condition.setText(this.vo.getTouchSceneName());
    label303: String str3;
    String str4;
    if (this.vo.getRepeatDayVos() != null)
    {
      String str2 = "";
      int j = 0;
      if (j < this.vo.getRepeatDayVos().size())
      {
        if (((RepeatDayVo)this.vo.getRepeatDayVos().get(j)).isSeleted())
          if (j != 0)
            break label303;
        for (str2 = str2 + ((RepeatDayVo)this.vo.getRepeatDayVos().get(j)).getDay() + "\t\t"; ; str2 = str2 + getString(this.shotDay[j]) + "\t\t")
        {
          j++;
          break;
        }
      }
      if (!str2.equals(""))
      {
        TextView localTextView2 = this.time;
        StringBuilder localStringBuilder1 = new StringBuilder().append(str2);
        if (Integer.parseInt(this.vo.getStartHour()) >= 10)
          break label586;
        str3 = "0" + this.vo.getStartHour();
        StringBuilder localStringBuilder2 = localStringBuilder1.append(str3).append(":").append(this.vo.getStartMin()).append("-");
        if (Integer.parseInt(this.vo.getEndHout()) >= 10)
          break label598;
        str4 = "0" + this.vo.getEndHout();
        label490: localTextView2.setText(str4 + ":" + this.vo.getEndMin());
      }
    }
    if (this.vo.getDelay() != null)
      this.delay.setText(this.vo.getDelay());
    label562: TextView localTextView1;
    if (this.vo.isPush())
    {
      this.msgPushSwitch.setToggleOn();
      localTextView1 = this.status;
      if (!this.vo.isOpen())
        break label620;
    }
    label586: label598: label620: for (int i = 2131100248; ; i = 2131099950)
    {
      localTextView1.setText(i);
      return;
      str3 = this.vo.getStartHour();
      break;
      str4 = this.vo.getEndHout();
      break label490;
      this.msgPushSwitch.setToggleOff();
      break label562;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.sensor.FtSensor
 * JD-Core Version:    0.6.0
 */