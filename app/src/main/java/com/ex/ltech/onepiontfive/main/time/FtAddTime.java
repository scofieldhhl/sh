package com.ex.ltech.onepiontfive.main.time;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.my_view.MyTimePickerView.onSelectListener;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.room.RoomBusiness;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.vo.RoomLvChildVo;
import com.ex.ltech.onepiontfive.main.vo.Timing;
import com.ex.ltech.onepiontfive.main.vo.TimingInnerDeivces;
import com.fragmentmaster.app.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.indris.material.RippleView;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FtAddTime extends MyBaseFt
  implements View.OnClickListener
{

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  TimingBusiness business;
  AlertDialog dialog;
  Handler handler = new Handler();
  String hour;
  boolean isNewCreate;

  @Bind({2131558519})
  ImageView ivActAddTimingGo2;
  String min;
  boolean onOff;
  private int position = -1;

  @Bind({R.id.rl_act_add_timing_2})
  RelativeLayout rlSeletedDays;

  @Bind({R.id.rl_act_add_timing_1})
  RelativeLayout rlSeletedDevice;
  RoomBusiness roomBusiness;
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      Toast.makeText(FtAddTime.this.getActivity(), R.string.add_time_no_ok, 0).show();
    }
  };
  private int[] shotDay = { 2131100240, 2131100240, 2131100467, 2131100430, 2131100071, 2131100066, 2131100404, 2131100394 };
  Timing timing;

  @Bind({R.id.tp_act_add_timing_hour})
  MyTimePickerView tpActAddTimingHour;

  @Bind({R.id.tp_act_add_timing_min})
  MyTimePickerView tpActAddTimingMin;

  @Bind({2131558512})
  TextView tvActAddTimingMode;

  @Bind({R.id.tv_act_add_timing_mode_status})
  TextView tvActAddTimingModeStatus;

  @Bind({R.id.tv_act_add_timing_off})
  TextView tvActAddTimingOff;

  @Bind({R.id.tv_act_add_timing_on})
  TextView tvActAddTimingOn;

  @Bind({2131558518})
  TextView tvActAddTimingRepeat;

  @Bind({R.id.tv_act_add_timing_repeat_status})
  TextView tvActAddTimingRepeatStatus;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;

  private void closeOperation()
  {
    this.tvActAddTimingOn.setBackgroundResource(2130903788);
    this.tvActAddTimingOff.setBackgroundResource(2130903787);
    this.tvActAddTimingOn.setTextColor(-16777216);
    this.tvActAddTimingOff.setTextColor(-1);
    this.onOff = false;
    this.timing.setOnOff(this.onOff);
    this.tvActAddTimingMode.setText(2131099958);
  }

  private void closeTips()
  {
  }

  private void initTitle()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(R.mipmap.add_timing);
    this.btnTitleViewEdit.setBackgroundResource(2130903116);
  }

  private void openOperation()
  {
    this.onOff = true;
    this.tvActAddTimingOn.setBackgroundResource(2130903787);
    this.tvActAddTimingOff.setBackgroundResource(2130903788);
    this.tvActAddTimingOff.setTextColor(-16777216);
    this.tvActAddTimingOn.setTextColor(-1);
    this.timing.setOnOff(this.onOff);
    this.tvActAddTimingMode.setText(2131100250);
  }

  public void onClick(View paramView)
  {
    if (paramView == this.btnTitleViewMenu)
    {
      this.business.saveCacheData(null);
      finish();
    }
    if (paramView == this.btnTitleViewEdit)
      if (this.tvActAddTimingModeStatus.getText().toString().equals(""))
        Toast.makeText(getActivity(), 2131099946, 0).show();
    label397: 
    while (true)
    {
      return;
      this.dialog = ProgressDialog.show(getActivity(), "", getString(2131100002), false);
      this.dialog.show();
      this.timing.setTime(this.hour + ":" + this.min);
      if (this.isNewCreate)
      {
        this.business.sendNewCreateTiming(this.timing, 17);
        if (this.timing.getShotDaysStr().equalsIgnoreCase(getString(R.string.once)))
          this.timing.setIsJustOnce(true);
        this.handler.removeCallbacks(this.runnable);
        this.handler.postDelayed(this.runnable, 5000L);
        this.business.setMySendListener(new MyBusiness.MySendListener()
        {
          public void onFail()
          {
          }

          public void onOk(byte[] paramArrayOfByte)
          {
            if (FtAddTime.this.dialog.isShowing())
              FtAddTime.this.dialog.dismiss();
            String str1 = StringUtils.btye2Str(paramArrayOfByte);
            String str2 = StringUtils.btye2Str3(paramArrayOfByte);
            System.out.println("recseiptBytes = " + str2);
            Integer.parseInt(str1.substring(18, 20), 16);
            if (paramArrayOfByte.length < 17);
            do
              return;
            while (!StringUtils.btye2Str(paramArrayOfByte).substring(18, 20).equalsIgnoreCase("93"));
            FtAddTime.this.handler.removeCallbacks(FtAddTime.this.runnable);
            if (paramArrayOfByte[16] == -1)
            {
              Toast.makeText(FtAddTime.this.getActivity(), R.string.ten_timing, 0).show();
              return;
            }
            FtAddTime.this.timing.setOrder(paramArrayOfByte[16]);
            FtAddTime.this.timing.setJustOnceCurrentTime(System.currentTimeMillis());
            if (FtAddTime.this.isNewCreate)
            {
              FtAddTime.this.timing.setSwich(true);
              FtAddTime.this.business.saveTiming(FtAddTime.this.timing);
            }
            while (true)
            {
              FtAddTime.this.setResult(202);
              FtAddTime.this.business.saveCacheData(null);
              FtAddTime.this.finish();
              FtAddTime.this.business.setMySendListener(null);
              return;
              FtAddTime.this.timing.setSwich(true);
              FtAddTime.this.business.updateTiming(FtAddTime.this.timing, FtAddTime.this.position);
            }
          }

          public void onTimeOut()
          {
            FtAddTime.this.getActivity().runOnUiThread(new Runnable()
            {
              public void run()
              {
              }
            });
            FtAddTime.this.handler.removeCallbacks(FtAddTime.this.runnable);
            if (FtAddTime.this.dialog.isShowing())
              FtAddTime.this.dialog.dismiss();
          }
        });
        if (paramView == this.tvActAddTimingOn)
        {
          this.tvActAddTimingModeStatus.setText("");
          openOperation();
        }
        if (paramView == this.tvActAddTimingOff)
        {
          this.tvActAddTimingModeStatus.setText("");
          closeOperation();
        }
        if (paramView == this.rlSeletedDevice)
        {
          if (this.onOff)
            break label368;
          Bundle localBundle = new Bundle();
          localBundle.putSerializable("selectDvc", this.timing);
          startFragmentForResult(new Request(FtRoomList.class).putExtra("timingOrder", this.timing.getOrder()).putExtra("selectDvc", localBundle), 0);
        }
      }
      while (true)
      {
        if (paramView != this.rlSeletedDays)
          break label397;
        this.business.saveCacheData(this.timing);
        startFragmentForResult(FtRepeatDay.class, 0);
        return;
        this.business.sendNewCreateTiming(this.timing, 68);
        break;
        label368: startFragmentForResult(new Request(FtScene.class).putExtra("sceneOrder", this.timing.getSeletedScenePosi()), 0);
      }
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968744, null);
      ButterKnife.bind(this, this.view);
      Bundle localBundle = getRequest().getBundleExtra("selectDvc");
      this.business = new TimingBusiness(getActivity());
      this.roomBusiness = new RoomBusiness(getActivity());
      this.tpActAddTimingHour.setData(this.business.getHourDate());
      this.tpActAddTimingMin.setData(this.business.getMinDate());
      this.tpActAddTimingHour.setTextCol(-16777216);
      this.tpActAddTimingMin.setTextCol(-16777216);
      this.timing = new Timing();
      if (getRequest().getStringExtra("TimingOperationKey").equals("AtTypeAddTiming"))
      {
        this.isNewCreate = true;
        this.tpActAddTimingHour.setSelected(Integer.parseInt(new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis()))));
        this.tpActAddTimingMin.setSelected(Integer.parseInt(new SimpleDateFormat("mm").format(Long.valueOf(System.currentTimeMillis()))));
        Timing localTiming = this.timing;
        StringBuilder localStringBuilder1 = new StringBuilder();
        String str1 = new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis()));
        this.hour = str1;
        StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append(":");
        String str2 = new SimpleDateFormat("mm").format(Long.valueOf(System.currentTimeMillis()));
        this.min = str2;
        localTiming.setTime(str2);
        this.timing.setHour(Integer.parseInt(this.hour));
        this.timing.setMin(Integer.parseInt(this.min));
        openOperation();
        this.timing.setShotDaysStr(getString(R.string.once));
      }
      if (getRequest().getStringExtra("TimingOperationKey").equals("EditTiming"))
      {
        this.timing = ((Timing)localBundle.get("selectDvc"));
        this.isNewCreate = false;
        String[] arrayOfString = this.timing.getTime().split(":");
        this.hour = arrayOfString[0];
        this.min = arrayOfString[1];
        this.timing.setHour(Integer.parseInt(this.hour));
        this.timing.setMin(Integer.parseInt(this.min));
        if (!this.timing.isOnOff())
          break label580;
        openOperation();
      }
    }
    while (true)
    {
      this.tpActAddTimingHour.setSelected(Integer.parseInt(this.hour));
      this.tpActAddTimingMin.setSelected(Integer.parseInt(this.min));
      this.tvActAddTimingModeStatus.setText(this.timing.getSeletedInfo());
      this.tvActAddTimingRepeatStatus.setText(this.timing.getShotDaysStr());
      this.tpActAddTimingHour.setOnSelectListener(new MyTimePickerView.onSelectListener()
      {
        public void onSelect(String paramString)
        {
          FtAddTime.this.hour = paramString;
          FtAddTime.this.timing.setHour(Integer.parseInt(FtAddTime.this.hour));
        }
      });
      this.tpActAddTimingMin.setOnSelectListener(new MyTimePickerView.onSelectListener()
      {
        public void onSelect(String paramString)
        {
          FtAddTime.this.min = paramString;
          FtAddTime.this.timing.setMin(Integer.parseInt(FtAddTime.this.min));
        }
      });
      initTitle();
      this.position = getRequest().getIntExtra("position", 0);
      ButterKnife.bind(this, this.view);
      return this.view;
      label580: closeOperation();
    }
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    System.out.println("ftaddtime destroyV ");
    if (this.business != null)
      this.business.setMySendListener(null);
    ButterKnife.unbind(this);
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    String str2;
    if (paramInt2 == 203)
      str2 = paramRequest.getStringExtra("TimingRepeatDayResultKey");
    while (true)
    {
      int k;
      try
      {
        List localList = (List)new Gson().fromJson(str2, new TypeToken()
        {
        }
        .getType());
        this.timing.setRepeatDayVos(localList);
        String str3 = "";
        k = 0;
        if (k >= localList.size())
          continue;
        if (!((RepeatDayVo)localList.get(k)).isSeleted())
          break label491;
        if (k != 0)
          continue;
        str3 = str3 + ((RepeatDayVo)localList.get(k)).getDay() + "\t\t";
        break label491;
        str3 = str3 + getString(this.shotDay[k]) + "\t\t";
        break label491;
        if (str3.length() != 0)
          continue;
        str3 = getString(R.string.once);
        this.timing.setShotDaysStr(str3);
        this.tvActAddTimingRepeatStatus.setText(str3);
        if (paramInt2 != 209)
          continue;
        this.timing.setSeletedScenePosi(paramRequest.getIntExtra("TimeScenePosiExtraKey ", -1));
        this.tvActAddTimingModeStatus.setText(paramRequest.getStringExtra("TimeSceneNameExtraKey "));
        this.timing.setSeletedInfo(paramRequest.getStringExtra("TimeSceneNameExtraKey "));
        if (paramInt2 == 204)
        {
          this.timing.setSelectedDevicesmIndex(paramRequest.getIntegerArrayListExtra("SelectedDevicesmIndex"));
          this.timing.setTimingInnerDeivces((TimingInnerDeivces)this.business.getCacheBean(TimingInnerDeivces.class));
          str1 = "";
          TimingInnerDeivces localTimingInnerDeivces = this.timing.getTimingInnerDeivces();
          i = 0;
          if (i >= localTimingInnerDeivces.getRooms().size())
            continue;
          int j = 0;
          if (j >= ((Room)localTimingInnerDeivces.getRooms().get(i)).getExpandableLvInnerItemVos().size())
            continue;
          if (!((RoomLvChildVo)((Room)localTimingInnerDeivces.getRooms().get(i)).getExpandableLvInnerItemVos().get(j)).isSeted())
            continue;
          str1 = str1 + ((RoomLvChildVo)((Room)localTimingInnerDeivces.getRooms().get(i)).getExpandableLvInnerItemVos().get(j)).getInnerDeviceName() + "\t\t";
          j++;
          continue;
        }
      }
      catch (Exception localException)
      {
        String str1;
        int i;
        localException.printStackTrace();
        continue;
        i++;
        continue;
        this.tvActAddTimingModeStatus.setText(str1);
        this.timing.setSeletedInfo(str1);
      }
      return;
      label491: k++;
    }
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.btnTitleViewMenu.setOnClickListener(this);
    this.btnTitleViewEdit.setOnClickListener(this);
    this.tvActAddTimingOn.setOnClickListener(this);
    this.tvActAddTimingOff.setOnClickListener(this);
    this.rlSeletedDevice.setOnClickListener(this);
    this.rlSeletedDays.setOnClickListener(this);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.time.FtAddTime
 * JD-Core Version:    0.6.0
 */