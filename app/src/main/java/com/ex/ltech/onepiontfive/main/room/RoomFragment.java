package com.ex.ltech.onepiontfive.main.room;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.ex.ltech.ct.ColorBussiness;
import com.ex.ltech.led.T;
import com.ex.ltech.led.my_view.AlertDialog105DeviceEdit;
import com.ex.ltech.led.my_view.AlertDialog105DeviceEdit.MyOnClickListener;
import com.ex.ltech.led.my_view.DeviceNameEditDialog105;
import com.ex.ltech.led.my_view.DeviceNameEditDialog105.MyOnClickListener;
import com.ex.ltech.led.my_view.DiffrentDeviceDialog;
import com.ex.ltech.led.my_view.MyAlertDialog12;
import com.ex.ltech.led.my_view.MyAlertDialog12.MyOnClickListener;
import com.ex.ltech.led.my_view.MyAlertDialog15;
import com.ex.ltech.led.my_view.MyAlertDialog15.MyOnClickListener;
import com.ex.ltech.led.my_view.MyAlertDialog16;
import com.ex.ltech.led.my_view.MyAlertDialog16.MyOnClickListener;
import com.ex.ltech.led.my_view.MyAlertDialog19;
import com.ex.ltech.led.my_view.MyAlertDialog19.MyOnClickListener;
import com.ex.ltech.led.my_view.MyAlertDialog7;
import com.ex.ltech.led.my_view.MyAlertDialog7.MyOnClickListener;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.AtFragmentMaster;
import com.ex.ltech.onepiontfive.main.AtMain;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.DvcStatusListener;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.config.AtCfgStepTwoActivity;
import com.ex.ltech.onepiontfive.main.vo.DeviceStatusVo;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.widget.ColorPanel;
import com.ex.ltech.onepiontfive.main.widget.ColorPanel.OnPikerMoveListener;
import com.ex.ltech.onepiontfive.main.widget.GroupGridView;
import com.ex.ltech.onepiontfive.main.widget.GroupGridView.OnDragOutGroupListener;
import com.ex.ltech.onepiontfive.main.widget.GroupGridView.OnMyLongClickListener;
import com.ex.ltech.onepiontfive.main.widget.PhotoDialog;
import com.ex.ltech.onepiontfive.main.widget.PhotoDialog.MyOnClickListener;
import com.ex.ltech.onepiontfive.main.widget.RoomGridView;
import com.ex.ltech.onepiontfive.main.widget.RoomGridView.OnDragInGroupListener;
import com.ex.ltech.onepiontfive.main.widget.pulltorefresh.PullToRefreshLayout;
import com.ex.ltech.onepiontfive.main.widget.pulltorefresh.PullToRefreshLayout.OnRefreshListener;
import com.ex.ltech.remote.control.yaokong.AtYaokongActivity;
import com.fragmentmaster.app.Request;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class RoomFragment extends MyBaseFt
  implements DevicGridAdapter.OnMyDelBtnListener, View.OnClickListener, ColorPanel.OnPikerMoveListener, SeekBar.OnSeekBarChangeListener, ModeColorAdapter.CallBack, DeviceListAdapter.CallBack
{
  static int b;
  static int brt;
  static int g;
  static int r = 255;
  public static int roomGridTop;
  Runnable AutoRefreshRunnable = new Runnable()
  {
    public void run()
    {
      System.out.println(RoomFragment.this.t + "     AutoRefreshRunnable    ");
      RoomFragment.this.loopCheckDeviceIsOnline();
      if (RoomFragment.this.roomBusiness != null)
      {
        System.out.println(RoomFragment.this.t + "     AutoRefreshRunnable    roomBusiness != null");
        if (RoomFragment.this.roomBusiness.dvcVos.size() == 0)
        {
          System.out.println(RoomFragment.this.t + "     AutoRefreshRunnable    roomBusiness.dvcVos.size() == 0");
          RoomFragment.this.mainPullRefreshView.autoRefresh();
        }
      }
    }
  };
  Runnable Heartbeat = new Runnable()
  {
    public void run()
    {
      System.out.println("Heartbeat  ");
      if (!RoomFragment.this.isFreshing)
      {
        System.out.println("Heartbeat Heartbeat Heartbeat ");
        RoomFragment.this.roomBusiness.setRoomIndex(RoomFragment.this.mPosi);
        RoomFragment.this.roomBusiness.setDvcStatusListener(new MyBusiness.DvcStatusListener()
        {
          public void onOk(byte[] paramArrayOfByte)
          {
            DeviceStatusVo localDeviceStatusVo = RoomFragment.this.roomBusiness.handleDeviceStatus(paramArrayOfByte);
            if (localDeviceStatusVo.isChanged)
            {
              if (!RoomFragment.this.listViewShow)
                break label209;
              RoomFragment.this.roomBusiness.resortListviewData();
              RoomFragment.this.deviceListAdapter.notifyDataSetChanged();
            }
            while (true)
            {
              if (localDeviceStatusVo.seletedDevice != null)
                RoomFragment.this.handleOffLineDevice(localDeviceStatusVo.seletedDevice);
              String str = StringUtils.btye2Str(paramArrayOfByte);
              if (str.length() == 56)
              {
                int i = Integer.parseInt(str.substring(18, 20), 16);
                int j = Integer.parseInt(str.substring(20, 22), 16);
                if ((RoomFragment.this.seletedDvc != null) && (j == 13) && (i == 161) && (RoomFragment.this.seletedDvc.getmIndex() == Integer.parseInt(str.substring(24, 26), 16)))
                {
                  RoomFragment.this.updateDvcInfo(str);
                  RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
                }
              }
              return;
              label209: RoomFragment.this.roomBusiness.checkGroupStatus();
              RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
              if ((localDeviceStatusVo.changedgroupPosi == -1) || (localDeviceStatusVo.changedgroupPosi != RoomFragment.this.dvcClickPosi))
                continue;
              RoomFragment.this.groupAdapter.setDvcVos(((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(localDeviceStatusVo.changedgroupPosi)).innerDvcVos);
              RoomFragment.this.groupAdapter.notifyDataSetChanged();
            }
          }
        });
        RoomFragment.this.roomBusiness.heartbeat();
      }
      RoomFragment.this.handler.postDelayed(RoomFragment.this.Heartbeat, 4000L);
    }
  };
  private Runnable LOAD_DATA = new Runnable()
  {
    public void run()
    {
      RoomFragment.this.isInit = true;
      RoomFragment.access$2802(RoomFragment.this, BitmapUtils.dp2px(AtMain.instance, 50.0F));
      RoomFragment.this.initRoomBusiness();
      RoomFragment.this.roomBusiness.initDvcsData(RoomFragment.this.mPosi);
      RoomFragment.access$902(RoomFragment.this, new DevicGridAdapter(AtMain.instance, RoomFragment.this.roomBusiness.dvcVos, RoomFragment.this));
      RoomFragment.this.initListener();
      RoomFragment.access$202(RoomFragment.this, new DeviceListAdapter(AtMain.instance, RoomFragment.this.roomBusiness.listviewDvcVos, RoomFragment.this));
      RoomFragment.access$1702(RoomFragment.this, new GroupAdapter(AtMain.instance, RoomFragment.this.roomBusiness.groupInnerDvcVos, new GroupAdapter.OnMyDelBtnListener()
      {
        public void onGroudInnerDeviceEdit(Dvc paramDvc, int paramInt)
        {
          RoomFragment.this.onDeivceEdit(paramDvc, paramInt);
        }
      }));
      try
      {
        RoomFragment.this.initView();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  };
  Runnable PullRefreshRunnable = new Runnable()
  {
    public void run()
    {
      try
      {
        RoomFragment.this.mainPullRefreshView.refreshFinish(1);
        if ((RoomFragment.this.dialog != null) && (RoomFragment.this.dialog.isShowing()))
          RoomFragment.this.dialog.dismiss();
        System.out.println("do...");
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        System.out.println("exception...");
      }
    }
  };
  private boolean SynListView = false;
  private SeekBar blackWhiteSb;
  private LocalBroadcastManager broadcastManager;
  private RelativeLayout brtBar;
  private LinearLayout btAddDevice;
  private Button btCloseGroup;
  int c = 0;
  private boolean canContinue = false;
  private Dvc chosenDvc;
  private int clickType = 0;
  final int colorPanelHeihtDP = 150;
  int count = 0;
  public int count4A2 = 0;
  private ModeColorAdapter ctModeAdapter;
  private ColorBussiness ctModeBussiness;
  int curGetDeviceType;
  Runnable dataRequestTimeoutRunnable = new Runnable()
  {
    public void run()
    {
      if (RoomFragment.this.loopGetDeviceCount < RoomFragment.this.mcDeviceIndexs.size())
      {
        RoomFragment.this.mcDeviceIndexs.clear();
        RoomFragment.this.loopGetDeviceCount = 0;
        RoomFragment.this.exitsDeviceIndexs.clear();
        RoomFragment.this.roomBusiness.setMySendListener(null);
        RoomFragment.this.shortToast(2131100336);
      }
    }
  };
  private DevicGridAdapter devicGridAdapter;
  private DeviceListAdapter deviceListAdapter;
  private RelativeLayout deviceTitleBar;
  AlertDialog dialog;
  private ImageView draftDevice;
  Runnable dragInGroupNoRespThread = new Runnable()
  {
    public void run()
    {
      RoomFragment.this.roomBusiness.onDragInGroupFailde();
      RoomFragment.this.roomBusiness.setMySendListener(null);
      RoomFragment.this.dialog.dismiss();
      RoomFragment.this.roomBusiness.checkGroupStatus();
      RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
    }
  };
  private int dvcClickPosi = 0;
  int dvcIndexResendTime;
  private ImageView edit;
  List<Integer> exitsDeviceIndexs = new ArrayList();
  private String gatewayId;
  MyBusiness.MySendListener getDeviceListener = new MyBusiness.MySendListener()
  {
    public void onFail()
    {
    }

    public void onOk(byte[] paramArrayOfByte)
    {
      String str = StringUtils.btye2Str(paramArrayOfByte);
      List localList = RoomFragment.this.roomBusiness.getMcDvcIndexs(str, 1 + RoomFragment.this.roomIndex, RoomFragment.this.curGetDeviceType);
      int j;
      label43: int m;
      if (localList != null)
      {
        int i = 0;
        j = 0;
        if (j < localList.size())
        {
          int k = ((Integer)localList.get(j)).intValue();
          m = 0;
          label73: if (m < RoomFragment.this.mcDeviceIndexs.size())
          {
            if (k != ((Integer)RoomFragment.this.mcDeviceIndexs.get(m)).intValue())
              break label301;
            i = 1;
          }
          if (i == 0)
            break label307;
        }
        if (i == 0)
        {
          RoomFragment.this.mcDeviceIndexs.addAll(localList);
          RoomFragment.this.handler.removeCallbacks(RoomFragment.this.resendGetDvcIndexRunable);
          if (RoomFragment.this.curGetDeviceType == 111)
          {
            if (RoomFragment.this.mcDeviceIndexs.size() <= 0)
              break label313;
            RoomFragment.this.exitsDeviceIndexs.clear();
            System.out.println(RoomFragment.this.testLoopDevice + "   mcDeviceIndexs.size() = " + RoomFragment.this.mcDeviceIndexs.size());
            RoomFragment.this.loopGetDeviceInfo();
          }
        }
      }
      while (true)
      {
        RoomFragment.this.curGetDeviceType = -1;
        switch (RoomFragment.this.curGetDeviceType)
        {
        default:
          return;
          label301: m++;
          break label73;
          label307: j++;
          break label43;
          label313: RoomFragment.this.isFreshing = false;
          if ((RoomFragment.this.dialog != null) && (RoomFragment.this.dialog.isShowing()))
            RoomFragment.this.dialog.dismiss();
          RoomFragment.this.mainPullRefreshView.refreshFinish(1);
          RoomFragment.this.roomBusiness.clearVosThisRoom(RoomFragment.this.mPosi);
          RoomFragment.this.roomBusiness.checkGroupStatus();
          RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
          RoomFragment.this.handler.postDelayed(RoomFragment.this.dataRequestTimeoutRunnable, 10L);
          RoomFragment.this.handler.postDelayed(RoomFragment.this.PullRefreshRunnable, 10L);
          RoomFragment.this.btAddDevice.setVisibility(View.VISIBLE);
        case 79:
        case 47:
        case 95:
        }
      }
      RoomFragment localRoomFragment3 = RoomFragment.this;
      RoomFragment.this.curGetDeviceType = 47;
      localRoomFragment3.getDvcIndex(47);
      return;
      RoomFragment localRoomFragment2 = RoomFragment.this;
      RoomFragment.this.curGetDeviceType = 95;
      localRoomFragment2.getDvcIndex(95);
      return;
      RoomFragment localRoomFragment1 = RoomFragment.this;
      RoomFragment.this.curGetDeviceType = 111;
      localRoomFragment1.getDvcIndex(111);
    }

    public void onTimeOut()
    {
    }
  };
  public SharedPreferences getter;
  private GridView gridColor;
  private GroupAdapter groupAdapter;
  private int groupInnerDvcWidHei;
  private TextView groupName;
  private TextView groupNameBg;
  private RoomGridView gvDevice;
  private GroupGridView gvGroup;
  Handler handler = new Handler();
  Handler handlerAnimaion = new Handler()
  {
  };
  private View hidepickerview;
  Home home;
  private HorizontalScrollView hsvColor;
  private int innerDvcClickPosi = -1;
  boolean isClickGroup;
  boolean isFreshing;
  boolean isGroupLayoutShow = false;
  boolean isGroupShow = false;
  boolean isInit;
  boolean isShowColorView = false;
  public boolean isSyncProgress = false;
  private boolean isToggle = false;
  private ImageView ivBg;
  private ImageView ivCamera;
  private ImageView ivDiy;
  private ImageView ivMode;
  private ImageView ivMusic;
  boolean lampOnOff = false;
  private String lastReturnData = "";
  boolean listViewShow;
  private RelativeLayout llRgbwBar;
  int loopGetDeviceCount = 0;
  private ListView lvDevice;
  Handler mHandler = new Handler();
  BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramIntent.getAction().equalsIgnoreCase("ShowListviewBroadCast")) && (RoomFragment.this.mPosi == FtRooms.tagPagePosi))
      {
        RoomFragment localRoomFragment2 = RoomFragment.this;
        localRoomFragment2.testtime = (1 + localRoomFragment2.testtime);
        System.out.println("接姐姐 time = " + RoomFragment.this.testtime);
        RoomFragment.this.roomBusiness.resortListviewData();
        RoomFragment.this.deviceListAdapter.notifyDataSetChanged();
        RoomFragment.this.showViewAnimaion(RoomFragment.this.lvDevice);
      }
      try
      {
        RoomFragment.access$502(RoomFragment.this, -1);
        RoomFragment.this.rlGvGroupParent.setVisibility(View.GONE);
        RoomFragment.this.isGroupLayoutShow = false;
        RoomFragment.this.mainPullRefreshView.setCanRefresh(true);
        RoomFragment.this.isGroupShow = false;
        if ((paramIntent.getAction().equalsIgnoreCase("HideListviewBroadCast")) && (RoomFragment.this.mPosi == FtRooms.tagPagePosi))
        {
          RoomFragment localRoomFragment1 = RoomFragment.this;
          localRoomFragment1.testtime = (1 + localRoomFragment1.testtime);
          System.out.println("接姐姐 time = " + RoomFragment.this.testtime);
          RoomFragment.this.hideViewAnimaion(RoomFragment.this.lvDevice);
        }
        if ((paramIntent.getAction() == "AddDeviceSenorBroadCast") && (RoomFragment.this.mPosi == FtRooms.tagPagePosi))
          RoomFragment.this.updateGridView();
        if ((paramIntent.getAction() == "AddDevicePanelBroadCast") && (RoomFragment.this.mPosi == FtRooms.tagPagePosi))
          RoomFragment.this.updateGridView();
        if ((paramIntent.getAction() == "AddDeviceLightBroadCast") && (RoomFragment.this.mPosi == FtRooms.tagPagePosi))
          RoomFragment.this.updateGridView();
        if ((paramIntent.getAction() == "PanelOnOffBroadCast") && (RoomFragment.this.mPosi == FtRooms.tagPagePosi))
        {
          int j = paramIntent.getIntExtra("DClickPosiKey", -1);
          boolean bool2 = paramIntent.getBooleanExtra("PanelOnOffKey", false);
          ((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(j)).setOnOff(bool2);
          RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
        }
        if ((paramIntent.getAction() == "SenserOnOffBroadCast") && (RoomFragment.this.mPosi == FtRooms.tagPagePosi))
        {
          int i = paramIntent.getIntExtra("DClickPosiKey", -1);
          boolean bool1 = paramIntent.getBooleanExtra("SenserOnOffKey", false);
          ((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(i)).setOnOff(bool1);
          RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
        }
        return;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  };
  private int mPosi;
  private String mac;
  private PullToRefreshLayout mainPullRefreshView;
  private List<Integer> mcDeviceIndexs = new ArrayList();
  boolean needToQueryDeviceInfoAgain = true;
  boolean needToSyncDeviceInfoAgain = true;
  private ColorPanel pickerview;
  Runnable reSendRunnable = new Runnable()
  {
    public void run()
    {
      System.out.println(RoomFragment.this.testLoopDevice + "     mcDeviceIndexs.size() = " + RoomFragment.this.mcDeviceIndexs.size());
      if (RoomFragment.this.mcDeviceIndexs.size() > 0)
      {
        if (RoomFragment.this.reSendTime > RoomFragment.this.resendMaxTime)
        {
          if (RoomFragment.this.mcDeviceIndexs.size() > 0)
          {
            RoomFragment.this.mcDeviceIndexs.remove(0);
            RoomFragment.this.loopGetDeviceInfo();
            System.out.println(RoomFragment.this.testLoopDevice + "        (reSendTime) = " + RoomFragment.this.reSendTime);
          }
          RoomFragment.access$4602(RoomFragment.this, 0);
        }
      }
      else
        return;
      RoomFragment.access$4608(RoomFragment.this);
      RoomFragment.this.roomBusiness.reSyncDeviceInfo(1, RoomFragment.this.roomIndex, ((Integer)RoomFragment.this.mcDeviceIndexs.get(0)).intValue());
      System.out.println(RoomFragment.this.testLoopDevice + "        (reSyncDeviceInfo) id= " + RoomFragment.this.mcDeviceIndexs.get(0));
      RoomFragment.this.handler.removeCallbacks(RoomFragment.this.reSendRunnable);
      RoomFragment.this.handler.postDelayed(RoomFragment.this.reSendRunnable, RoomFragment.this.researchDeviceInfoTime);
    }
  };
  private int reSendTime = 0;
  int researchDeviceInfoTime = 1100;
  Runnable resendGetDvcIndexRunable = new Runnable()
  {
    public void run()
    {
      if (RoomFragment.this.dvcIndexResendTime < 12)
      {
        RoomFragment.this.roomBusiness.getMcDeivceIndexs(RoomFragment.this.getDeviceListener, RoomFragment.this.roomIndex, RoomFragment.this.curGetDeviceType);
        RoomFragment localRoomFragment = RoomFragment.this;
        localRoomFragment.dvcIndexResendTime = (1 + localRoomFragment.dvcIndexResendTime);
      }
    }
  };
  private int resendMaxTime = 5;
  int rgbw$w = 0;
  private SeekBar rgbwBrtSb;
  private SeekBar rgbwWSb;
  SeekBar.OnSeekBarChangeListener rgbwWSbListener = new SeekBar.OnSeekBarChangeListener()
  {
    public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
    {
      RoomFragment.this.rgbw$w = (255 * paramSeekBar.getProgress() / 100);
      RoomFragment.this.seletedDvc.setOnOff(true);
      RoomFragment.this.seletedDvc.setR(RoomFragment.r);
      RoomFragment.this.seletedDvc.setG(RoomFragment.g);
      RoomFragment.this.seletedDvc.setB(RoomFragment.b);
      RoomFragment.this.seletedDvc.setBrt(RoomFragment.brt);
      RoomFragment.this.roomBusiness.sendColor(RoomFragment.this.seletedDvc.isGroup(), RoomFragment.this.seletedDvc.getGroupId(), RoomFragment.this.seletedDvc.getType(), RoomFragment.this.seletedDvc.getRoomIndex(), RoomFragment.this.seletedDvc.getmIndex(), 0, 0, 0, RoomFragment.this.rgbw$w, RoomFragment.brt, RoomFragment.this.touchXPercent, RoomFragment.this.touchYPercent);
    }

    public void onStartTrackingTouch(SeekBar paramSeekBar)
    {
      RoomFragment.this.isSyncProgress = false;
    }

    public void onStopTrackingTouch(SeekBar paramSeekBar)
    {
    }
  };
  private RelativeLayout rlGvGroupParent;
  private RelativeLayout rlHead;
  Room room;
  private RoomBusiness roomBusiness;
  private int roomIndex;
  private RelativeLayout root;
  RunnableAnimaion runnableAnimaion = new RunnableAnimaion();
  SearchBlubRunnable searchBlubRunnable = new SearchBlubRunnable();
  final int seekBarBgHeihtDP = 50;
  Dvc seletedDvc;
  private TextView smartDevice;
  String t = "testMyNullPiont";
  RoomBusiness tempRoomBusiness;
  String testLoopDevice = "testLoopDevice";
  int testtime = 0;
  private RelativeLayout threeBtnsLl;
  private LinearLayout threeBtnsLlMode;
  private LinearLayout threeBtnsLlMusic;
  private LinearLayout threeBtnsLlOnoff;
  final int topNavigationBarHeightDP = 50;
  float touchXPercent;
  float touchYPercent;
  private TextView tvMode;
  private TextView tvMusic;
  private RelativeLayout twoBtnsLl;
  private LinearLayout twoBtnsLlDiy;
  private LinearLayout twoBtnsLlOnoff;
  private View view;
  int w = 0;

  static
  {
    g = 255;
    b = 255;
    brt = 255;
  }

  private void add()
  {
    MyAlertDialog7 localMyAlertDialog7 = new MyAlertDialog7(AtMain.instance);
    localMyAlertDialog7.show();
    localMyAlertDialog7.setMyOnClickListener(new MyAlertDialog7.MyOnClickListener()
    {
      public void onAddDevice()
      {
        Intent localIntent = new Intent(AtMain.instance, AtFragmentMaster.class);
        localIntent.putExtra("AtTypeKey", "AtAddDevice");
        localIntent.putExtra("mPosition", RoomFragment.this.mPosi);
        RoomFragment.this.startActivity(localIntent);
      }

      public void onAddLight()
      {
        Intent localIntent = new Intent(AtMain.instance, AtCfgStepTwoActivity.class);
        localIntent.putExtra("cfgType", "lamp");
        localIntent.putExtra("mPosition", RoomFragment.this.mPosi);
        RoomFragment.this.startActivity(localIntent);
      }
    });
  }

  private void clickItem(View paramView, int paramInt, long paramLong, boolean paramBoolean)
  {
    if (paramInt < 0);
    int i;
    int i2;
    label133: int i3;
    label148: int i5;
    while (true)
    {
      return;
      if (this.dvcClickPosi != paramInt)
        this.isShowColorView = false;
      this.deviceTitleBar.setVisibility(View.VISIBLE);
      if (!paramBoolean)
        break label1650;
      if ((this.roomBusiness.isShowDelBtn()) || (paramInt > -1 + this.roomBusiness.dvcVos.size()))
        continue;
      this.gvGroup.setmTop(this.rlHead.getHeight());
      i = ((Dvc)this.roomBusiness.dvcVos.get(paramInt)).getType();
      this.seletedDvc = ((Dvc)this.roomBusiness.dvcVos.get(paramInt));
      if (!this.seletedDvc.isOnLine())
      {
        if (this.seletedDvc.getType() != 8)
          break;
        i2 = 1;
        if (this.seletedDvc.getType() != 11)
          break label378;
        i3 = 1;
        int i4 = i3 | i2;
        if (this.seletedDvc.getType() != 9)
          break label384;
        i5 = 1;
        label170: if ((i5 | i4) == 0);
      }
      this.lampOnOff = this.seletedDvc.isOnOff();
      FtRooms.seletedDvc = this.seletedDvc;
      if (i != 22)
        syncDeviceInfo(this.seletedDvc.getmIndex(), paramInt);
      if (handleOffLineDevice(this.seletedDvc))
        continue;
      this.roomBusiness.onLightSeleted(paramInt);
      if (((Dvc)this.roomBusiness.dvcVos.get(paramInt)).innerDvcVos.size() >= 2)
        break label1102;
      this.isClickGroup = false;
      this.edit.setVisibility(View.VISIBLE);
      this.roomBusiness.checkGroupStatus();
      this.devicGridAdapter.notifyDataSetChanged();
      switch (i)
      {
      case 10:
      case 19:
      case 20:
      default:
      case 11:
      case 8:
      case 12:
      case 9:
      case 14:
      case 13:
      case 21:
      case 15:
      case 16:
      case 17:
      case 18:
      case 22:
      }
    }
    while (true)
    {
      this.dvcClickPosi = paramInt;
      this.clickType = i;
      return;
      i2 = 0;
      break label133;
      label378: i3 = 0;
      break label148;
      label384: i5 = 0;
      break label170;
      showHidePickerView();
      this.twoBtnsLl.setVisibility(View.VISIBLE);
      this.threeBtnsLl.setVisibility(View.GONE);
      this.pickerview.setBgType("ColorPanelTypeBlackWhite");
      this.brtBar.setVisibility(View.GONE);
      this.hsvColor.setVisibility(View.GONE);
      continue;
      showHidePickerView();
      this.twoBtnsLl.setVisibility(View.GONE);
      this.hsvColor.setVisibility(View.GONE);
      this.pickerview.setBgType("ColorPanelType");
      this.ivMusic.setVisibility(View.VISIBLE);
      this.tvMusic.setVisibility(View.VISIBLE);
      this.ivMusic.setBackgroundResource(2130903494);
      this.tvMusic.setText(2131100194);
      this.ivMode.setBackgroundResource(2130903487);
      this.tvMode.setText(R.string.mode);
      continue;
      showHidePickerView();
      this.twoBtnsLl.setVisibility(View.GONE);
      this.hsvColor.setVisibility(View.GONE);
      this.pickerview.setBgType("ColorPanelType");
      this.ivMusic.setVisibility(View.VISIBLE);
      this.tvMusic.setVisibility(View.VISIBLE);
      this.ivMusic.setBackgroundResource(2130903494);
      this.tvMusic.setText(2131100194);
      this.ivMode.setBackgroundResource(2130903487);
      this.tvMode.setText(R.string.mode);
      continue;
      showHidePickerView();
      this.twoBtnsLl.setVisibility(View.VISIBLE);
      this.threeBtnsLl.setVisibility(View.GONE);
      this.hsvColor.setVisibility(View.GONE);
      this.pickerview.setBgType("ColorPanelTypeWarmWhite");
      this.ivMode.setBackgroundResource(2130903217);
      this.tvMode.setText(2131100041);
      this.ivMusic.setVisibility(View.GONE);
      this.tvMusic.setVisibility(View.GONE);
      continue;
      this.isShowColorView = true;
      showHidePickerView();
      this.brtBar.setVisibility(View.GONE);
      this.twoBtnsLl.setVisibility(View.GONE);
      this.threeBtnsLl.setVisibility(View.GONE);
      this.hsvColor.setVisibility(View.GONE);
      this.isShowColorView = true;
      Intent localIntent4 = new Intent(AtMain.instance, AtFragmentMaster.class);
      localIntent4.putExtra("AtTypeKey", "AtTypeSensor");
      localIntent4.putExtra("DIndexKey", this.seletedDvc.getmIndex());
      localIntent4.putExtra("DRoomNumKey", this.mPosi);
      localIntent4.putExtra("DClickPosiKey", paramInt);
      startActivity(localIntent4);
      continue;
      this.brtBar.setVisibility(View.GONE);
      this.twoBtnsLl.setVisibility(View.GONE);
      this.threeBtnsLl.setVisibility(View.GONE);
      this.hsvColor.setVisibility(View.GONE);
      continue;
      if (Build.CPU_ABI.indexOf("arm") == -1)
      {
        Toast.makeText(AtMain.instance, getString(2131100209), 0).show();
        return;
      }
      this.brtBar.setVisibility(View.GONE);
      this.twoBtnsLl.setVisibility(View.GONE);
      this.threeBtnsLl.setVisibility(View.GONE);
      this.hsvColor.setVisibility(View.GONE);
      this.roomBusiness.putData("dvcInRoom", this.seletedDvc);
      startActivity(new Intent(AtMain.instance, AtYaokongActivity.class));
      continue;
      this.isShowColorView = true;
      showHidePickerView();
      this.brtBar.setVisibility(View.GONE);
      this.twoBtnsLl.setVisibility(View.GONE);
      this.threeBtnsLl.setVisibility(View.GONE);
      this.hsvColor.setVisibility(View.GONE);
      this.isShowColorView = true;
      Intent localIntent3 = new Intent(AtMain.instance, AtFragmentMaster.class);
      localIntent3.putExtra("AtTypeKey", "AtTypePanel");
      localIntent3.putExtra("DIndexKey", this.seletedDvc.getmIndex());
      localIntent3.putExtra("DRoomNumKey", this.mPosi);
      localIntent3.putExtra("DClickPosiKey", paramInt);
      startActivity(localIntent3);
      continue;
      add();
      continue;
      label1102: this.isGroupLayoutShow = true;
      this.isClickGroup = true;
      this.roomBusiness.setGroupInnerDvcVos(((Dvc)this.roomBusiness.dvcVos.get(paramInt)).innerDvcVos);
      this.groupAdapter.setDvcVos(((Dvc)this.roomBusiness.dvcVos.get(paramInt)).innerDvcVos);
      this.roomBusiness.onInnerLightFinishLongClick();
      showViewAnimaion(this.rlGvGroupParent);
      this.isGroupShow = true;
      this.gvGroup.setAdapter(this.groupAdapter);
      this.groupName.setText(((Dvc)this.roomBusiness.dvcVos.get(paramInt)).getGroupName());
      this.groupName.setTag(Integer.valueOf(paramInt));
      int i1 = ((Dvc)this.roomBusiness.dvcVos.get(paramInt)).innerDvcVos.size();
      this.roomBusiness.setGroupPosi(paramInt);
      if (i1 % 2 == 0)
        (i1 / 2);
      while (true)
      {
        roomGridTop = this.gvDevice.getTop();
        this.gvGroup.mShowLeft = (this.gvGroup.getLeft() + (paramView.getWidth() / 2 - this.groupInnerDvcWidHei));
        this.gvGroup.mShowTop = (this.gvDevice.getTop() + (0 + paramView.getHeight() / 2));
        roomGridTop = this.gvDevice.getTop();
        switch (i)
        {
        default:
          break;
        case 8:
        case 12:
          this.twoBtnsLl.setVisibility(View.GONE);
          this.isShowColorView = false;
          showHidePickerView();
          this.pickerview.setBgType("ColorPanelType");
          this.ivMusic.setVisibility(View.VISIBLE);
          this.tvMusic.setVisibility(View.VISIBLE);
          this.ivMusic.setBackgroundResource(2130903494);
          this.tvMusic.setText(2131100194);
          this.ivMode.setBackgroundResource(2130903487);
          this.tvMode.setText(R.string.mode);
          break;
          (1 + i1 / 2);
        case 11:
        case 10:
        case 9:
        }
      }
      this.twoBtnsLl.setVisibility(View.VISIBLE);
      this.isShowColorView = false;
      showHidePickerView();
      this.pickerview.setBgType("ColorPanelTypeBlackWhite");
      continue;
      this.twoBtnsLl.setVisibility(View.GONE);
      this.isShowColorView = false;
      showHidePickerView();
      this.pickerview.setBgType("ColorPanelTypeCold");
      this.ivMusic.setBackgroundResource(2130903217);
      this.tvMusic.setText(2131100041);
      this.ivMode.setBackgroundResource(2130903136);
      this.tvMode.setText(2131099928);
      continue;
      this.twoBtnsLl.setVisibility(View.VISIBLE);
      this.threeBtnsLl.setVisibility(View.GONE);
      this.isShowColorView = false;
      showHidePickerView();
      this.pickerview.setBgType("ColorPanelTypeWarmWhite");
      this.ivMode.setBackgroundResource(2130903217);
      this.tvMode.setText(2131100041);
      this.ivMusic.setVisibility(View.GONE);
      this.tvMusic.setVisibility(View.GONE);
      continue;
      label1650: i = ((Dvc)this.roomBusiness.listviewDvcVos.get(paramInt)).getType();
      int j = ((Dvc)this.roomBusiness.listviewDvcVos.get(paramInt)).getmIndex();
      this.seletedDvc = ((Dvc)this.roomBusiness.listviewDvcVos.get(paramInt));
      FtRooms.seletedDvc = this.seletedDvc;
      if (!this.seletedDvc.isOnLine())
        break;
      System.out.println("deviceType = " + i + " mIndex = " + j);
      int n;
      for (int k = 0; ; k++)
      {
        int m = this.roomBusiness.dvcVos.size();
        n = 0;
        if (k < m)
        {
          if (j != ((Dvc)this.roomBusiness.dvcVos.get(k)).getmIndex())
            continue;
          n = k;
        }
        switch (i)
        {
        case 19:
        case 20:
        default:
          break;
        case 13:
          this.brtBar.setVisibility(View.GONE);
          this.twoBtnsLl.setVisibility(View.GONE);
          this.threeBtnsLl.setVisibility(View.GONE);
          this.hsvColor.setVisibility(View.GONE);
          break;
        case 14:
        case 21:
        case 15:
        case 16:
        case 17:
        case 18:
        }
      }
      this.brtBar.setVisibility(View.GONE);
      this.twoBtnsLl.setVisibility(View.GONE);
      this.threeBtnsLl.setVisibility(View.GONE);
      this.hsvColor.setVisibility(View.GONE);
      Intent localIntent2 = new Intent(AtMain.instance, AtFragmentMaster.class);
      localIntent2.putExtra("AtTypeKey", "AtTypeSensor");
      localIntent2.putExtra("DIndexKey", this.seletedDvc.getmIndex());
      localIntent2.putExtra("DRoomNumKey", this.mPosi);
      localIntent2.putExtra("DClickPosiKey", n);
      startActivity(localIntent2);
      continue;
      if (Build.CPU_ABI.indexOf("arm") == -1)
      {
        Toast.makeText(AtMain.instance, getString(2131100209), 0).show();
        return;
      }
      this.brtBar.setVisibility(View.GONE);
      this.twoBtnsLl.setVisibility(View.GONE);
      this.threeBtnsLl.setVisibility(View.GONE);
      this.hsvColor.setVisibility(View.GONE);
      this.roomBusiness.putData("dvcInRoom", this.seletedDvc);
      startActivity(new Intent(AtMain.instance, AtYaokongActivity.class));
      continue;
      this.brtBar.setVisibility(View.GONE);
      this.twoBtnsLl.setVisibility(View.GONE);
      this.threeBtnsLl.setVisibility(View.GONE);
      this.hsvColor.setVisibility(View.GONE);
      Intent localIntent1 = new Intent(AtMain.instance, AtFragmentMaster.class);
      localIntent1.putExtra("AtTypeKey", "AtTypePanel");
      localIntent1.putExtra("DIndexKey", this.seletedDvc.getmIndex());
      localIntent1.putExtra("DRoomNumKey", this.mPosi);
      localIntent1.putExtra("DClickPosiKey", n);
      startActivity(localIntent1);
    }
  }

  private void findMyViewById()
  {
    this.root = ((RelativeLayout)this.view.findViewById(2131559222));
    this.rlHead = ((RelativeLayout)this.view.findViewById(2131559223));
    this.view.findViewById(2131559224).setOnClickListener(this);
    this.ivCamera = ((ImageView)this.view.findViewById(2131559225));
    this.pickerview = ((ColorPanel)this.view.findViewById(2131559226));
    this.hidepickerview = this.view.findViewById(2131559241);
    this.hsvColor = ((HorizontalScrollView)this.view.findViewById(2131559227));
    this.gridColor = ((GridView)this.view.findViewById(2131559228));
    this.brtBar = ((RelativeLayout)this.view.findViewById(2131559229));
    this.blackWhiteSb = ((SeekBar)this.view.findViewById(2131558998));
    this.threeBtnsLl = ((RelativeLayout)this.view.findViewById(2131559230));
    this.threeBtnsLlOnoff = ((LinearLayout)this.view.findViewById(2131559231));
    this.threeBtnsLlMode = ((LinearLayout)this.view.findViewById(2131559232));
    this.ivMode = ((ImageView)this.view.findViewById(R.id.iv_mode));
    this.tvMode = ((TextView)this.view.findViewById(2131558842));
    this.threeBtnsLlMusic = ((LinearLayout)this.view.findViewById(2131559234));
    this.ivMusic = ((ImageView)this.view.findViewById(2131559235));
    this.ivDiy = ((ImageView)this.view.findViewById(2131559240));
    this.tvMusic = ((TextView)this.view.findViewById(2131559236));
    this.twoBtnsLl = ((RelativeLayout)this.view.findViewById(2131559237));
    this.twoBtnsLlOnoff = ((LinearLayout)this.view.findViewById(2131559238));
    this.twoBtnsLlDiy = ((LinearLayout)this.view.findViewById(2131559239));
    this.view.findViewById(2131559242).setOnClickListener(this);
    this.ivBg = ((ImageView)this.view.findViewById(2131559243));
    this.deviceTitleBar = ((RelativeLayout)this.view.findViewById(2131559244));
    this.smartDevice = ((TextView)this.view.findViewById(2131559245));
    this.edit = ((ImageView)this.view.findViewById(2131558953));
    this.mainPullRefreshView = ((PullToRefreshLayout)this.view.findViewById(2131559246));
    this.gvDevice = ((RoomGridView)this.view.findViewById(2131559247));
    this.btAddDevice = ((LinearLayout)this.view.findViewById(2131559248));
    this.lvDevice = ((ListView)this.view.findViewById(2131559249));
    this.rlGvGroupParent = ((RelativeLayout)this.view.findViewById(2131559250));
    this.groupNameBg = ((TextView)this.view.findViewById(2131559251));
    this.groupName = ((TextView)this.view.findViewById(2131559252));
    this.gvGroup = ((GroupGridView)this.view.findViewById(2131559253));
    this.draftDevice = ((ImageView)this.view.findViewById(2131559255));
    this.btCloseGroup = ((Button)this.view.findViewById(2131559254));
    this.llRgbwBar = ((RelativeLayout)this.view.findViewById(2131558804));
    this.rgbwBrtSb = ((SeekBar)this.view.findViewById(2131558805));
    this.rgbwWSb = ((SeekBar)this.view.findViewById(2131558806));
  }

  private void getDvcIndex(int paramInt)
  {
    this.roomBusiness.getMcDeivceIndexs(this.getDeviceListener, this.roomIndex, paramInt);
    this.handler.removeCallbacks(this.resendGetDvcIndexRunable);
    this.handler.postDelayed(this.resendGetDvcIndexRunable, this.researchDeviceInfoTime);
  }

  private void handOffLineDevice(Dvc paramDvc)
  {
    if (!paramDvc.isOnLine())
    {
      MyAlertDialog19 localMyAlertDialog19 = new MyAlertDialog19(AtMain.instance);
      localMyAlertDialog19.show();
      localMyAlertDialog19.setMyOnClickListener(new MyAlertDialog19.MyOnClickListener(localMyAlertDialog19)
      {
        public void onClick(int paramInt)
        {
          if (paramInt == 0)
            RoomFragment.this.refreshGrid();
          if (paramInt == -1)
            this.val$OffLineDeviceDialog.dismiss();
        }
      });
    }
  }

  private boolean handleOffLineDevice(Dvc paramDvc)
  {
    int i;
    int j;
    label29: int m;
    label46: int i1;
    if (!paramDvc.isOnLine())
      if (paramDvc.getType() == 8)
      {
        i = 1;
        if (paramDvc.getType() != 12)
          break label118;
        j = 1;
        int k = j | i;
        if (paramDvc.getType() != 11)
          break label123;
        m = 1;
        int n = k | m;
        if (paramDvc.getType() != 9)
          break label129;
        i1 = 1;
        label65: if ((i1 | n) == 0)
          break label135;
        this.hidepickerview.setVisibility(View.VISIBLE);
        this.ivMode.setImageResource(2130903488);
        this.ivMusic.setImageResource(2130903495);
        this.ivDiy.setImageResource(2130903218);
      }
    while (true)
    {
      return false;
      i = 0;
      break;
      label118: j = 0;
      break label29;
      label123: m = 0;
      break label46;
      label129: i1 = 0;
      break label65;
      label135: MyAlertDialog19 localMyAlertDialog19 = new MyAlertDialog19(AtMain.instance);
      localMyAlertDialog19.show();
      localMyAlertDialog19.setMyOnClickListener(new MyAlertDialog19.MyOnClickListener()
      {
        public void onClick(int paramInt)
        {
          RoomFragment.this.mainPullRefreshView.autoRefresh();
        }
      });
      return true;
      this.hidepickerview.setVisibility(View.GONE);
      this.ivMode.setImageResource(2130903487);
      this.ivMusic.setImageResource(2130903494);
      this.ivDiy.setImageResource(2130903217);
    }
  }

  private void hideViewAnimaion(View paramView)
  {
    paramView.setVisibility(View.GONE);
  }

  private void init()
  {
    this.home = new RoomsBusiness(AtMain.instance).getHome();
    initCtMode();
  }

  private void initCtMode()
  {
    this.ctModeBussiness = new ColorBussiness(AtMain.instance);
    this.ctModeBussiness.loadCtSceneVos();
    this.ctModeAdapter = new ModeColorAdapter(AtMain.instance, this.ctModeBussiness.vos, this);
    setGridView();
    this.gridColor.setAdapter(this.ctModeAdapter);
    this.ivBg.setImageResource(FtRooms.bgRes[this.mPosi]);
    this.gridColor.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        RoomFragment.this.ctModeBussiness.showEditBtn();
        RoomFragment.this.ctModeAdapter.notifyDataSetChanged();
        return false;
      }
    });
    this.gridColor.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        RoomFragment.this.ctModeBussiness.hideEditBtn();
        Log.i("", "");
      }
    });
  }

  private void initData()
  {
    this.isInit = true;
    this.groupInnerDvcWidHei = BitmapUtils.dp2px(AtMain.instance, 50.0F);
    initRoomBusiness();
    initCtMode();
    this.roomBusiness.initDvcsData(this.mPosi);
    this.devicGridAdapter = new DevicGridAdapter(AtMain.instance, this.roomBusiness.dvcVos, this);
    initListener();
    this.deviceListAdapter = new DeviceListAdapter(AtMain.instance, this.roomBusiness.listviewDvcVos, this);
    this.groupAdapter = new GroupAdapter(AtMain.instance, this.roomBusiness.groupInnerDvcVos, new GroupAdapter.OnMyDelBtnListener()
    {
      public void onGroudInnerDeviceEdit(Dvc paramDvc, int paramInt)
      {
        RoomFragment.this.onDeivceEdit(paramDvc, paramInt);
      }
    });
    try
    {
      initView();
      System.out.println(this.t + "     initData    " + this.mPosi);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private void initListener()
  {
    lampOperateListener();
  }

  private void initView()
  {
    this.blackWhiteSb.setOnSeekBarChangeListener(this);
    this.rgbwBrtSb.setOnSeekBarChangeListener(this);
    this.rgbwWSb.setOnSeekBarChangeListener(this.rgbwWSbListener);
    this.pickerview.setListener(this);
    this.threeBtnsLlOnoff.setOnClickListener(this);
    this.threeBtnsLlMode.setOnClickListener(this);
    this.threeBtnsLlMusic.setOnClickListener(this);
    this.twoBtnsLlOnoff.setOnClickListener(this);
    this.twoBtnsLlDiy.setOnClickListener(this);
    this.btCloseGroup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        RoomFragment.this.rlGvGroupParent.setVisibility(View.GONE);
        RoomFragment.this.isGroupLayoutShow = false;
        RoomFragment.this.mainPullRefreshView.setCanRefresh(true);
        RoomFragment.this.isGroupShow = false;
        RoomFragment.access$502(RoomFragment.this, -1);
        RoomFragment.this.roomBusiness.onInnerLightCancelSeleted();
        RoomFragment.this.showHidePickerView();
      }
    });
    this.mainPullRefreshView.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener()
    {
      public void onLoadMore(PullToRefreshLayout paramPullToRefreshLayout)
      {
      }

      public void onRefresh(PullToRefreshLayout paramPullToRefreshLayout)
      {
        RoomFragment.this.refreshGrid();
      }
    });
    if (this.roomBusiness.dvcVos.size() == 0)
    {
      this.btAddDevice.setVisibility(View.VISIBLE);
      this.deviceTitleBar.setVisibility(View.GONE);
    }
    while (true)
    {
      this.lvDevice.setAdapter(this.deviceListAdapter);
      this.gvDevice.setAdapter(this.devicGridAdapter);
      this.gvDevice.setDraftDevice(this.draftDevice);
      this.gvDevice.setRoot(this.root);
      this.gvDevice.setmTop(this.rlHead.getHeight());
      this.gvDevice.setOnDragInGroupListener(new RoomGridView.OnDragInGroupListener()
      {
        public void onDragFinish(int paramInt)
        {
          FtRooms.ftRoomsInstance.canScroll(RoomFragment.this.isShowColorView);
          Thread.currentThread().getId();
          RoomFragment.this.dialog = ProgressDialog.show(AtMain.instance, "", RoomFragment.this.getString(2131100002), false);
          RoomFragment.this.dialog.show();
          if (RoomFragment.this.roomBusiness.canDrag())
          {
            RoomFragment.this.handler.removeCallbacks(RoomFragment.this.dragInGroupNoRespThread);
            RoomFragment.this.handler.postDelayed(RoomFragment.this.dragInGroupNoRespThread, 5100L);
            RoomFragment.this.roomBusiness.dragToGroup(new MyBusiness.MySendListener()
            {
              public void onFail()
              {
                RoomFragment.this.handler.removeCallbacks(RoomFragment.this.dragInGroupNoRespThread);
                RoomFragment.this.roomBusiness.setMySendListener(null);
                RoomFragment.this.dialog.dismiss();
              }

              public void onOk(byte[] paramArrayOfByte)
              {
                String str = StringUtils.btye2Str(paramArrayOfByte);
                if ((str.length() >= 20) && (str.substring(18, 20).equals("92")))
                {
                  int i = Integer.parseInt(StringUtils.btye2Str(paramArrayOfByte).substring(40, 42), 16);
                  if (i == 255)
                    break label190;
                  RoomFragment.this.roomBusiness.onDragInGroup(RoomFragment.this.getString(2131100124), RoomFragment.this.mPosi, i);
                  RoomFragment.this.roomBusiness.checkGroupStatus();
                  RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
                  RoomFragment.this.roomBusiness.setMySendListener(null);
                  RoomFragment.this.roomBusiness.saveDeivceInRoom(RoomFragment.this.mPosi);
                }
                while (true)
                {
                  RoomFragment.this.dialog.dismiss();
                  RoomFragment.this.handler.removeCallbacks(RoomFragment.this.dragInGroupNoRespThread);
                  return;
                  label190: RoomFragment.this.shortToast(2131100184);
                }
              }

              public void onTimeOut()
              {
                RoomFragment.this.handler.removeCallbacks(RoomFragment.this.dragInGroupNoRespThread);
                RoomFragment.this.handler.post(RoomFragment.this.dragInGroupNoRespThread);
              }
            });
          }
          while (true)
          {
            RoomFragment.this.roomBusiness.checkGroupStatus();
            RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
            return;
            if (RoomFragment.this.roomBusiness.checkIsDiffrentType())
              new DiffrentDeviceDialog(AtMain.instance).show();
            RoomFragment.this.dialog.dismiss();
          }
        }

        public void onDragMove(int paramInt)
        {
          RoomFragment.this.roomBusiness.onDragMove(paramInt);
        }

        public void onDragStart(int paramInt)
        {
          RoomFragment.this.roomBusiness.onDragStart(paramInt);
          RoomFragment.this.roomBusiness.checkGroupStatus();
          RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
        }

        public void onEventCancel(int paramInt)
        {
        }

        public void onLongClick(int paramInt)
        {
          FtRooms.ftRoomsInstance.onDeviceEditShow();
          RoomFragment.this.roomBusiness.onLightSeleted(paramInt);
          RoomFragment.this.roomBusiness.onDeviceEdit();
          RoomFragment.this.roomBusiness.checkGroupStatus();
          RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
          RoomFragment.this.gvDevice.setmTop(RoomFragment.this.rlHead.getHeight());
          RoomFragment.this.mainPullRefreshView.setCanRefresh(false);
          FtRooms.ftRoomsInstance.canScroll(false);
        }
      });
      this.lvDevice.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          RoomFragment.this.clickItem(paramView, paramInt, paramLong, false);
        }
      });
      this.gvDevice.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          RoomFragment.this.clickItem(paramView, paramInt, paramLong, true);
          System.out.println("clickItem");
        }
      });
      this.gvGroup.setAdapter(this.groupAdapter);
      this.gvGroup.setDraftDevice(this.draftDevice);
      this.gvGroup.setRoot(this.root);
      this.gvGroup.setmTop(this.rlHead.getHeight());
      this.gvGroup.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          RoomFragment.access$502(RoomFragment.this, paramInt);
          RoomFragment.this.roomBusiness.setGroupInnerDvcVos(((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(RoomFragment.this.dvcClickPosi)).innerDvcVos);
          RoomFragment.this.groupAdapter.setDvcVos(((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(RoomFragment.this.dvcClickPosi)).innerDvcVos);
          RoomFragment.this.seletedDvc = RoomFragment.this.roomBusiness.onInnerLightSeleted2(paramInt);
          RoomFragment.this.groupAdapter.notifyDataSetChanged();
          RoomFragment.this.isClickGroup = false;
          RoomFragment.this.roomBusiness.syncDeviceInfo(RoomFragment.this.roomIndex, ((Dvc)RoomFragment.this.roomBusiness.groupInnerDvcVos.get(paramInt)).getmIndex());
          if (RoomFragment.this.handleOffLineDevice(RoomFragment.this.seletedDvc))
            return;
          RoomFragment.this.syncDeviceInfo(RoomFragment.this.seletedDvc.getmIndex(), paramInt);
        }
      });
      this.gvGroup.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramView)
        {
          FtRooms.ftRoomsInstance.onDeviceEditShow();
          RoomFragment.this.roomBusiness.onInnerLightLongClick();
          RoomFragment.this.groupAdapter.notifyDataSetChanged();
          return false;
        }
      });
      this.gvGroup.setOnMyLongClickListener(new GroupGridView.OnMyLongClickListener()
      {
        public void onLongClick(int paramInt)
        {
          FtRooms.ftRoomsInstance.onDeviceEditShow();
        }
      });
      this.gvGroup.setOnDragOutGroupListener(new GroupGridView.OnDragOutGroupListener()
      {
        public void onDragFinish()
        {
          if (RoomFragment.this.isGroupLayoutShow)
            return;
          int i = RoomFragment.this.roomBusiness.getGroupInnerDvcCount();
          if (RoomFragment.this.roomBusiness.isGroupInnerDvcDragOk())
          {
            RoomFragment.access$502(RoomFragment.this, -1);
            RoomFragment.this.rlGvGroupParent.setVisibility(View.GONE);
            RoomFragment.this.isGroupLayoutShow = false;
            RoomFragment.this.mainPullRefreshView.setCanRefresh(true);
            RoomFragment.this.isGroupShow = false;
            RoomFragment.this.dialog = ProgressDialog.show(AtMain.instance, "", RoomFragment.this.getString(2131100002), false);
            if (i != 2)
              break label190;
            RoomFragment.this.roomBusiness.dragOutGroupRemoveAllSon(new MyBusiness.MySendListener()
            {
              public void onFail()
              {
                RoomFragment.this.roomBusiness.setMySendListener(null);
                RoomFragment.this.dialog.dismiss();
              }

              public void onOk(byte[] paramArrayOfByte)
              {
                String str = StringUtils.btye2Str(paramArrayOfByte);
                if ((str.length() == 50) && (str.substring(24, 32).equals(RoomFragment.this.roomBusiness.dragOutDvcId)))
                {
                  RoomFragment.this.roomBusiness.onGroupInnerDvcDragFinish();
                  RoomFragment.this.roomBusiness.hideDelBtn();
                  RoomFragment.this.roomBusiness.checkGroupStatus();
                  RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
                  RoomFragment.this.roomBusiness.setMySendListener(null);
                }
                if (RoomFragment.this.dialog.isShowing())
                  RoomFragment.this.dialog.dismiss();
              }

              public void onTimeOut()
              {
                RoomFragment.this.roomBusiness.setMySendListener(null);
                RoomFragment.this.dialog.dismiss();
              }
            });
          }
          while (true)
          {
            RoomFragment.this.roomBusiness.hideDelBtn();
            RoomFragment.this.roomBusiness.saveDeivceInRoom(RoomFragment.this.mPosi);
            RoomFragment.this.roomBusiness.checkGroupStatus();
            RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
            RoomFragment.this.groupAdapter.notifyDataSetChanged();
            return;
            label190: RoomFragment.this.roomBusiness.dragOutGroup(new MyBusiness.MySendListener()
            {
              public void onFail()
              {
                RoomFragment.this.roomBusiness.setMySendListener(null);
                RoomFragment.this.dialog.dismiss();
              }

              public void onOk(byte[] paramArrayOfByte)
              {
                String str = StringUtils.btye2Str(paramArrayOfByte);
                if ((str.length() == 42) && (str.substring(24, 32).equals(RoomFragment.this.roomBusiness.dragOutDvcId)))
                {
                  RoomFragment.this.roomBusiness.onGroupInnerDvcDragFinish();
                  RoomFragment.this.roomBusiness.hideDelBtn();
                  RoomFragment.this.roomBusiness.checkGroupStatus();
                  RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
                  RoomFragment.this.roomBusiness.setMySendListener(null);
                }
                if (RoomFragment.this.dialog.isShowing())
                  RoomFragment.this.dialog.dismiss();
              }

              public void onTimeOut()
              {
                RoomFragment.this.roomBusiness.setMySendListener(null);
                RoomFragment.this.dialog.dismiss();
              }
            });
          }
        }

        public void onDragMove(int paramInt1, int paramInt2)
        {
          System.out.println("pointToPosition       moveX     " + RoomFragment.this.gvGroup.pointToPosition(paramInt1, paramInt2));
          System.out.println("pointToPosition       moveX     " + RoomFragment.this.gvGroup.pointToPosition(paramInt1, paramInt2));
          if (RoomFragment.this.roomBusiness.canHideGroupLayout(RoomFragment.this.gvGroup.pointToPosition(paramInt1, paramInt2)))
          {
            if (RoomFragment.this.isGroupLayoutShow)
            {
              if (paramInt2 < RoomFragment.this.rlGvGroupParent.getTop())
                RoomFragment.this.hideViewAnimaion(RoomFragment.this.rlGvGroupParent);
              RoomFragment.this.isGroupLayoutShow = false;
            }
            RoomFragment.this.roomBusiness.onGroupInnerDvcDragMove(RoomFragment.this.gvDevice.pointToPosition(paramInt1, paramInt2));
          }
        }

        public void onDragStart(int paramInt)
        {
          if (paramInt == 0)
          {
            Toast.makeText(AtMain.instance, 2131100014, 0).show();
            return;
          }
          RoomFragment.this.roomBusiness.onDragMove(paramInt);
          RoomFragment.this.isGroupLayoutShow = true;
          RoomFragment.this.roomBusiness.onGroupInnerDvcDragStart(paramInt);
        }

        public void onLongClick(int paramInt)
        {
          RoomFragment.this.roomBusiness.onInnerLightSeleted2(paramInt);
          FtRooms.ftRoomsInstance.onDeviceEditShow();
          RoomFragment.this.roomBusiness.onGroupInnerDeviceEdit(RoomFragment.this.seletedDvc, paramInt);
          RoomFragment.this.groupAdapter.notifyDataSetChanged();
        }
      });
      this.rlGvGroupParent.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          RoomFragment.access$502(RoomFragment.this, -1);
          RoomFragment.this.rlGvGroupParent.setVisibility(View.GONE);
          RoomFragment.this.isGroupLayoutShow = false;
          RoomFragment.this.mainPullRefreshView.setCanRefresh(true);
          RoomFragment.this.isGroupShow = false;
          RoomFragment.this.roomBusiness.onInnerLightCancelSeleted();
          RoomFragment.this.showHidePickerView();
        }
      });
      this.ivCamera.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          PhotoDialog localPhotoDialog = new PhotoDialog(AtMain.instance);
          localPhotoDialog.show();
          localPhotoDialog.setMyOnClickListener(new PhotoDialog.MyOnClickListener()
          {
            public void onClick(int paramInt)
            {
              if (paramInt == 1)
                RoomFragment.this.roomBusiness.goCarmare(RoomFragment.this);
              if (paramInt == 2)
                RoomFragment.this.roomBusiness.pickImage(RoomFragment.this);
              if (paramInt == 3)
                RoomFragment.this.roomBusiness.resetBg(RoomFragment.this.ivBg);
            }
          });
        }
      });
      this.btAddDevice.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          RoomFragment.this.add();
        }
      });
      this.groupName.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          DeviceNameEditDialog105 localDeviceNameEditDialog105 = new DeviceNameEditDialog105(AtMain.instance);
          localDeviceNameEditDialog105.show();
          localDeviceNameEditDialog105.setContent(RoomFragment.this.groupName.getText().toString());
          localDeviceNameEditDialog105.getWindow().clearFlags(131080);
          localDeviceNameEditDialog105.getWindow().setSoftInputMode(4);
          localDeviceNameEditDialog105.setMyOnClickListener(new DeviceNameEditDialog105.MyOnClickListener()
          {
            public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
            {
              RoomFragment.this.dialog = ProgressDialog.show(AtMain.instance, "", RoomFragment.this.getString(2131100002), false);
              RoomFragment.this.dialog.show();
              int i = 67;
              switch (RoomFragment.this.seletedDvc.getType())
              {
              case 10:
              default:
              case 11:
              case 9:
              case 8:
              case 12:
              }
              while (paramString.getBytes().length > 24)
              {
                Toast.makeText(AtMain.instance, R.string.rename_count, 0).show();
                RoomFragment.this.dialog.dismiss();
                return;
                i = 69;
                continue;
                i = 70;
                continue;
                i = 67;
                continue;
                i = 68;
              }
              RoomFragment.this.roomBusiness.changeName(new MyBusiness.MySendListener(paramString)
              {
                public void onFail()
                {
                  RoomFragment.this.roomBusiness.setMySendListener(null);
                  RoomFragment.this.dialog.dismiss();
                  RoomFragment.this.shortToast(2131100334);
                }

                public void onOk(byte[] paramArrayOfByte)
                {
                  String str = StringUtils.btye2Str(paramArrayOfByte);
                  if ((str.length() >= 30) && (str.substring(18, 20).equals("AD")))
                  {
                    ((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(((Integer)RoomFragment.this.groupName.getTag()).intValue())).setGroupName(this.val$content);
                    RoomFragment.this.groupName.setText(this.val$content);
                    RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
                    RoomFragment.this.roomBusiness.setMySendListener(null);
                  }
                  RoomFragment.this.dialog.dismiss();
                }

                public void onTimeOut()
                {
                  RoomFragment.this.roomBusiness.setMySendListener(null);
                  RoomFragment.this.dialog.dismiss();
                  RoomFragment.this.shortToast(2131100336);
                }
              }
              , 1, i, RoomFragment.this.seletedDvc.getGroupId(), paramString.getBytes(), true);
            }
          });
        }
      });
      return;
      this.deviceTitleBar.setVisibility(View.VISIBLE);
    }
  }

  private void lampOperateListener()
  {
    System.out.println("lampOperateListener = ");
    this.roomBusiness.setMySendListener(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        String str = StringUtils.btye2Str(paramArrayOfByte);
        System.out.println("lampOperateListener = " + StringUtils.btye2Str3(paramArrayOfByte) + "len= " + str.length());
        if (str.length() == 52)
        {
          int i = Integer.parseInt(str.substring(18, 20), 16);
          int j = Integer.parseInt(str.substring(20, 22), 16);
          if ((RoomFragment.this.seletedDvc != null) && (j == 11) && (i == 161) && (RoomFragment.this.seletedDvc.getmIndex() == Integer.parseInt(str.substring(24, 26), 16)))
            RoomFragment.this.updateDvcInfo(str);
        }
      }

      public void onTimeOut()
      {
      }
    });
  }

  private void loopGetDeviceInfo()
  {
    this.reSendTime = 0;
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        if (RoomFragment.this.mcDeviceIndexs.size() > 0)
          RoomFragment.this.roomBusiness.syncDeviceInfo(new MyBusiness.MySendListener()
          {
            public void onFail()
            {
            }

            public void onOk(byte[] paramArrayOfByte)
            {
              String str = StringUtils.btye2Str(paramArrayOfByte);
              while (true)
              {
                int j;
                try
                {
                  int i = Integer.parseInt(str.substring(26, 28), 16);
                  j = 0;
                  int k = RoomFragment.this.exitsDeviceIndexs.size();
                  int m = 0;
                  if (j >= k)
                    continue;
                  if (((Integer)RoomFragment.this.exitsDeviceIndexs.get(j)).equals(Integer.valueOf(i)))
                  {
                    m = 1;
                    if (m != 0)
                      continue;
                    Dvc localDvc = RoomFragment.this.roomBusiness.checkSumDeviceInfo(str, 1 + RoomFragment.this.roomIndex, RoomFragment.this.curGetDeviceType);
                    if (localDvc == null)
                      continue;
                    RoomFragment.this.btAddDevice.setVisibility(View.GONE);
                    System.out.println(RoomFragment.this.testLoopDevice + "   Dvc   ok     (mIndex) = " + i);
                    RoomFragment.this.exitsDeviceIndexs.add(Integer.valueOf(i));
                    RoomFragment.this.roomBusiness.responseMessage(str.substring(4, 6), "23");
                    RoomFragment.this.roomBusiness.saveDevice(localDvc);
                    RoomFragment.this.groupAdapter.notifyDataSetChanged();
                    RoomFragment.this.roomBusiness.checkGroupStatus();
                    RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
                    RoomFragment.this.view.findViewById(2131559248).setVisibility(View.GONE);
                    RoomFragment.this.mcDeviceIndexs.remove(0);
                    RoomFragment.this.loopGetDeviceInfo();
                    if (RoomFragment.this.mcDeviceIndexs.size() != 0)
                      continue;
                    T.e();
                    RoomFragment.this.roomBusiness.udataDvcs(RoomFragment.this.exitsDeviceIndexs);
                    RoomFragment.this.roomBusiness.saveDeivceInRoom(RoomFragment.this.roomIndex);
                    RoomFragment.this.roomBusiness.setMySendListener(null);
                    RoomFragment.this.exitsDeviceIndexs.clear();
                    if (RoomFragment.this.mainPullRefreshView == null)
                      continue;
                    RoomFragment.this.mainPullRefreshView.refreshFinish(0);
                    RoomFragment.this.isFreshing = false;
                    RoomFragment.this.handler.removeCallbacks(RoomFragment.this.dataRequestTimeoutRunnable);
                    RoomFragment.this.handler.removeCallbacks(RoomFragment.this.PullRefreshRunnable);
                    if ((RoomFragment.this.dialog == null) || (!RoomFragment.this.dialog.isShowing()))
                      continue;
                    RoomFragment.this.dialog.dismiss();
                    return;
                  }
                }
                catch (Exception localException)
                {
                  localException.printStackTrace();
                  return;
                }
                j++;
              }
            }

            public void onTimeOut()
            {
            }
          }
          , 1, RoomFragment.this.roomIndex, ((Integer)RoomFragment.this.mcDeviceIndexs.get(0)).intValue());
      }
    }
    , 10L);
    this.handler.removeCallbacks(this.reSendRunnable);
    this.handler.postDelayed(this.reSendRunnable, this.researchDeviceInfoTime);
    this.handler.removeCallbacks(this.dataRequestTimeoutRunnable);
    this.handler.postDelayed(this.dataRequestTimeoutRunnable, this.mcDeviceIndexs.size() * this.resendMaxTime * this.researchDeviceInfoTime);
    this.handler.removeCallbacks(this.PullRefreshRunnable);
    this.handler.postDelayed(this.PullRefreshRunnable, this.mcDeviceIndexs.size() * this.resendMaxTime * this.researchDeviceInfoTime);
  }

  private void refreshGrid()
  {
    this.isShowColorView = true;
    showHidePickerView();
    System.out.println(this.t + "     refreshGrid");
    if (this.devicGridAdapter == null)
      return;
    this.curGetDeviceType = 79;
    getDvcIndex(79);
    this.dvcIndexResendTime = 0;
    this.isFreshing = true;
    this.roomBusiness.dvcVos.clear();
    this.devicGridAdapter.notifyDataSetChanged();
    this.mcDeviceIndexs.clear();
    this.loopGetDeviceCount = 0;
    this.exitsDeviceIndexs.clear();
    this.handler.removeCallbacks(this.dataRequestTimeoutRunnable);
    this.handler.postDelayed(this.dataRequestTimeoutRunnable, 10000L);
    this.handler.removeCallbacks(this.PullRefreshRunnable);
    this.handler.postDelayed(this.PullRefreshRunnable, 10000L);
  }

  private void removeAllRunnable()
  {
    this.handler.removeCallbacks(this.resendGetDvcIndexRunable);
    this.handler.removeCallbacks(this.PullRefreshRunnable);
    this.handler.removeCallbacks(this.AutoRefreshRunnable);
    this.handler.removeCallbacks(this.dataRequestTimeoutRunnable);
    this.handler.removeCallbacks(this.dragInGroupNoRespThread);
    this.handler.removeCallbacks(this.Heartbeat);
    this.handler.removeCallbacks(this.reSendRunnable);
    this.handler.removeCallbacks(this.resendGetDvcIndexRunable);
  }

  private void searchDevice(int paramInt)
  {
    this.roomBusiness.setRoomIndex(this.mPosi);
    this.roomBusiness.searchDevice(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        System.out.println("searchDevice onOk" + StringUtils.btye2Str2(paramArrayOfByte));
        RoomFragment.this.roomBusiness.resolveDvcData(StringUtils.btye2Str(paramArrayOfByte));
        RoomFragment.this.groupAdapter.notifyDataSetChanged();
        RoomFragment.this.roomBusiness.checkGroupStatus();
        RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
        RoomFragment.this.btAddDevice.setVisibility(View.GONE);
      }

      public void onTimeOut()
      {
      }
    }
    , this.mPosi, paramInt);
  }

  private void sendActionUpCtADimValue(float paramFloat, int paramInt)
  {
    this.w = (int)(255.0F * ((100.0F - paramFloat) / 100.0F));
    if (this.w < 1)
      this.w = 0;
    if (this.w > 254)
      this.w = 255;
    this.c = (255 - this.w);
    this.seletedDvc.setOnOff(true);
    if (this.blackWhiteSb.getProgress() != 100)
    {
      this.blackWhiteSb.setOnSeekBarChangeListener(null);
      this.blackWhiteSb.setProgress(100);
      this.blackWhiteSb.setOnSeekBarChangeListener(this);
    }
    System.out.println(this.c + ":" + this.w);
    this.roomBusiness.cracySendDIMnCTnRGB(this.seletedDvc, this.c, this.w, paramInt, this.clickType, this.touchXPercent, this.touchYPercent);
  }

  private void sendCtADimValue(float paramFloat, int paramInt)
  {
    this.w = (int)(255.0F * ((100.0F - paramFloat) / 100.0F));
    if (this.w < 1)
      this.w = 0;
    if (this.w > 254)
      this.w = 255;
    this.c = (255 - this.w);
    this.seletedDvc.setOnOff(true);
    if (this.blackWhiteSb.getProgress() != 100)
    {
      this.blackWhiteSb.setOnSeekBarChangeListener(null);
      this.blackWhiteSb.setProgress(100);
      this.blackWhiteSb.setOnSeekBarChangeListener(this);
    }
    System.out.println(this.c + ":" + this.w);
    this.roomBusiness.sendDIMnCTnRGB(this.seletedDvc, this.c, this.w, paramInt, this.clickType, this.touchXPercent, this.touchYPercent);
  }

  private void setAdtView()
  {
    if ((this.groupAdapter != null) && (this.devicGridAdapter != null))
    {
      this.home = new RoomsBusiness(AtMain.instance).getHome();
      this.roomBusiness = new RoomBusiness(AtMain.instance, ((Room)this.home.getRooms().get(this.roomIndex)).getDvcVos());
      this.devicGridAdapter = new DevicGridAdapter(AtMain.instance, this.roomBusiness.dvcVos, this);
      this.roomBusiness.resortListviewData();
      this.deviceListAdapter = new DeviceListAdapter(AtMain.instance, this.roomBusiness.listviewDvcVos, this);
      this.lvDevice.setAdapter(this.deviceListAdapter);
      this.gvDevice.setAdapter(this.devicGridAdapter);
    }
  }

  public static void setDialogWindowAttr(Dialog paramDialog, Context paramContext)
  {
    Window localWindow = paramDialog.getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    localLayoutParams.width = -1;
    localLayoutParams.height = -2;
    localLayoutParams.gravity = 80;
    localWindow.setAttributes(localLayoutParams);
  }

  private void setGridView()
  {
    float f = getResources().getDisplayMetrics().density;
    int i = (int)(f * 832);
    int j = (int)(f * 100);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(i, -1);
    this.gridColor.setLayoutParams(localLayoutParams);
    this.gridColor.setColumnWidth(j);
    this.gridColor.setHorizontalSpacing(5);
    this.gridColor.setStretchMode(0);
    this.gridColor.setNumColumns(8);
  }

  private void showHidePickerView()
  {
    boolean bool1 = true;
    int i = 8;
    boolean bool2;
    int j;
    label35: int k;
    label58: boolean bool3;
    label86: boolean bool4;
    label101: FtRooms localFtRooms;
    if (!this.isShowColorView)
    {
      bool2 = bool1;
      this.isShowColorView = bool2;
      ImageView localImageView = this.ivBg;
      if (!this.isShowColorView)
        break label198;
      j = i;
      localImageView.setVisibility(j);
      ColorPanel localColorPanel = this.pickerview;
      if (!this.isShowColorView)
        break label204;
      k = 0;
      localColorPanel.setVisibility(k);
      if (!this.isShowColorView)
        break label222;
      if (this.seletedDvc.getType() != i)
        break label210;
      bool3 = bool1;
      if (this.seletedDvc.getType() != 9)
        break label216;
      bool4 = bool1;
      if ((bool3 | bool4))
      {
        this.brtBar.setVisibility(View.VISIBLE);
        this.llRgbwBar.setVisibility(i);
      }
      if (this.seletedDvc.getType() == 12)
      {
        this.brtBar.setVisibility(i);
        this.llRgbwBar.setVisibility(View.VISIBLE);
      }
      label153: RelativeLayout localRelativeLayout = this.threeBtnsLl;
      if (this.isShowColorView)
        i = 0;
      localRelativeLayout.setVisibility(i);
      localFtRooms = FtRooms.ftRoomsInstance;
      if (this.isShowColorView)
        break label241;
    }
    while (true)
    {
      localFtRooms.canScroll(bool1);
      return;
      bool2 = false;
      break;
      label198: j = 0;
      break label35;
      label204: k = i;
      break label58;
      label210: bool3 = false;
      break label86;
      label216: bool4 = false;
      break label101;
      label222: this.brtBar.setVisibility(i);
      this.llRgbwBar.setVisibility(i);
      break label153;
      label241: bool1 = false;
    }
  }

  private void showViewAnimaion(View paramView)
  {
    paramView.setVisibility(View.VISIBLE);
  }

  private void syncDeviceInfo(int paramInt1, int paramInt2)
  {
    this.roomBusiness.syncDeviceInfo(new MyBusiness.MySendListener(paramInt1)
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        String str = StringUtils.btye2Str(paramArrayOfByte);
        RoomFragment.this.roomBusiness.responseMessage(str.substring(4, 6), "23");
        int i;
        int j;
        if (!str.substring(18, 20).equalsIgnoreCase("A3"))
        {
          i = 1;
          if (str.length() >= 68)
            break label67;
          j = 1;
          label54: if ((i | j) == 0)
            break label73;
        }
        label67: label73: 
        do
        {
          return;
          i = 0;
          break;
          j = 0;
          break label54;
          try
          {
            float f1 = Float.parseFloat(Integer.parseInt(str.substring(50, 52), 16) + "." + Integer.parseInt(str.substring(52, 54), 16) + "");
            float f2 = Float.parseFloat(Integer.parseInt(str.substring(54, 56), 16) + "." + Integer.parseInt(str.substring(56, 58), 16) + "");
            int k = Integer.parseInt(str.substring(46, 48), 16);
            int m = Integer.parseInt(str.substring(48, 50), 16);
            System.out.println("brt = " + m);
            RoomFragment.this.blackWhiteSb.setOnSeekBarChangeListener(null);
            RoomFragment.this.blackWhiteSb.setProgress(m * 100 / 255);
            RoomFragment.this.blackWhiteSb.setOnSeekBarChangeListener(RoomFragment.this);
            RoomFragment.this.rgbwBrtSb.setOnSeekBarChangeListener(null);
            RoomFragment.this.rgbwBrtSb.setProgress(m * 100 / 255);
            RoomFragment.this.rgbwBrtSb.setOnSeekBarChangeListener(RoomFragment.this);
            RoomFragment.this.rgbwWSb.setOnSeekBarChangeListener(null);
            RoomFragment.this.rgbwWSb.setProgress(k * 100 / 255);
            RoomFragment.this.rgbwWSb.setOnSeekBarChangeListener(RoomFragment.this.rgbwWSbListener);
            if (RoomFragment.this.pickerview != null)
            {
              int n = (int)(RoomFragment.this.pickerview.getMeasuredWidth() * (f1 / 100.0F));
              int i1 = (int)(RoomFragment.this.pickerview.getMeasuredHeight() * (f2 / 100.0F));
              RoomFragment.this.pickerview.setPikerXy(n, i1);
            }
            RoomFragment.this.roomBusiness.setMySendListener(null);
            if (RoomFragment.this.seletedDvc.getType() != 8)
              continue;
            RoomFragment.this.roomBusiness.updataDeviceBrt(this.val$mIndex, RoomFragment.this.blackWhiteSb.getProgress());
            return;
          }
          catch (Exception localException)
          {
            while (true)
              localException.printStackTrace();
          }
        }
        while (RoomFragment.this.seletedDvc.getType() != 12);
        RoomFragment.this.roomBusiness.updataDeviceBrt(this.val$mIndex, RoomFragment.this.rgbwBrtSb.getProgress());
      }

      public void onTimeOut()
      {
        if ((RoomFragment.this.dialog != null) && (RoomFragment.this.dialog.isShowing()))
          RoomFragment.this.dialog.dismiss();
      }
    }
    , 1, this.roomIndex, paramInt1);
  }

  private void updateDvcInfo(String paramString)
  {
    int i = Integer.parseInt(paramString.substring(22, 24), 16);
    int j = Integer.parseInt(paramString.substring(24, 26), 16);
    int k = Integer.parseInt(paramString.substring(26, 28), 16);
    int m = Integer.parseInt(paramString.substring(28, 30), 16);
    int n = Integer.parseInt(paramString.substring(30, 32), 16);
    int i1 = Integer.parseInt(paramString.substring(32, 34), 16);
    int i2 = Integer.parseInt(paramString.substring(34, 36), 16);
    int i3 = Integer.parseInt(paramString.substring(36, 38), 16);
    int i4 = Integer.parseInt(paramString.substring(38, 40), 16);
    float f1 = Float.parseFloat(Integer.parseInt(paramString.substring(40, 42), 16) + "." + Integer.parseInt(paramString.substring(42, 44), 16) + "");
    float f2 = Float.parseFloat(Integer.parseInt(paramString.substring(44, 46), 16) + "." + Integer.parseInt(paramString.substring(46, 48), 16) + "");
    if (this.pickerview != null)
    {
      int i10 = (int)(this.pickerview.getMeasuredWidth() * (f1 / 100.0F));
      int i11 = (int)(this.pickerview.getMeasuredHeight() * (f2 / 100.0F));
      this.pickerview.setPikerXy(i10, i11);
    }
    ArrayList localArrayList;
    int i5;
    if ((k >= 65) && (k <= 67))
    {
      localArrayList = ((Room)this.home.getRooms().get(i - 1)).getDvcVos();
      i5 = 0;
      int i6 = localArrayList.size();
      int i7 = 0;
      if (i5 < i6)
      {
        if (((Dvc)localArrayList.get(i5)).getmIndex() != j)
          break label753;
        i7 = 1;
      }
      if (i7 != 0)
      {
        if (m != 0)
          break label759;
        ((Dvc)localArrayList.get(i5)).setOnOff(false);
      }
    }
    while (true)
    {
      ((Dvc)localArrayList.get(i5)).setR(n);
      ((Dvc)localArrayList.get(i5)).setG(i1);
      ((Dvc)localArrayList.get(i5)).setB(i2);
      ((Dvc)localArrayList.get(i5)).setW(i3);
      ((Dvc)localArrayList.get(i5)).setBrt(i4);
      ((Dvc)localArrayList.get(i5)).setxPercent((int)f1);
      ((Dvc)localArrayList.get(i5)).setyPercent((int)f2);
      if ((this.blackWhiteSb != null) && (k == 67))
      {
        this.isSyncProgress = true;
        this.blackWhiteSb.setOnSeekBarChangeListener(null);
        this.blackWhiteSb.setProgress(i4 * 100 / 255);
        this.blackWhiteSb.setOnSeekBarChangeListener(this);
      }
      if ((this.rgbwBrtSb != null) && (k == 68))
      {
        this.isSyncProgress = true;
        this.rgbwBrtSb.setOnSeekBarChangeListener(null);
        this.rgbwBrtSb.setProgress(i4 * 100 / 255);
        this.rgbwBrtSb.setOnSeekBarChangeListener(this);
      }
      if ((this.rgbwWSb != null) && (k == 68))
      {
        this.isSyncProgress = true;
        this.rgbwWSb.setOnSeekBarChangeListener(null);
        this.rgbwWSb.setProgress(i3 * 100 / 255);
        this.rgbwWSb.setOnSeekBarChangeListener(this.rgbwWSbListener);
      }
      if (this.pickerview != null)
      {
        int i8 = (int)(this.pickerview.getWidth() * (f1 / 100.0F));
        int i9 = (int)(this.pickerview.getHeight() * (f2 / 100.0F));
        this.pickerview.setPikerXy(i8, i9);
      }
      this.roomBusiness.saveHome(this.home);
      return;
      label753: i5++;
      break;
      label759: ((Dvc)localArrayList.get(i5)).setOnOff(true);
    }
  }

  public void goToCtModeColorEdit(int paramInt, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(AtMain.instance, AtFragmentMaster.class).putExtra("AtTypeKey", "ModeColorEdit");
    localIntent.putExtra("ctScenePosi", paramInt);
    localIntent.putExtra("ctSceneName", paramString1);
    localIntent.putExtra("sceneDataStr", paramString2);
    localIntent.putExtra("type", this.seletedDvc.getType());
    localIntent.putExtra("roomIndex", this.seletedDvc.getRoomIndex());
    if (this.seletedDvc.isGroup());
    for (int i = this.seletedDvc.getGroupId(); ; i = this.seletedDvc.getmIndex())
    {
      localIntent.putExtra("groupIndex", i);
      startActivityForResult(localIntent, 0);
      return;
    }
  }

  public void hideDelBtn()
  {
    if (this.mainPullRefreshView == null)
      this.mainPullRefreshView = ((PullToRefreshLayout)this.view.findViewById(2131559246));
    if (this.mainPullRefreshView != null)
      this.mainPullRefreshView.setCanRefresh(true);
    initRoomBusiness();
    this.roomBusiness.hideDelBtn();
    this.roomBusiness.saveDeivceInRoom(this.mPosi);
    this.roomBusiness.onInnerLightFinishLongClick();
    this.groupAdapter.notifyDataSetChanged();
    this.roomBusiness.checkGroupStatus();
    this.devicGridAdapter.notifyDataSetChanged();
  }

  void initRoomBusiness()
  {
    if (this.roomBusiness == null);
    try
    {
      this.roomBusiness = new RoomBusiness(AtMain.instance, this.room.getDvcVos());
      this.isInit = true;
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        this.roomBusiness = new RoomBusiness(AtMain.instance, ((Room)new RoomsBusiness(AtMain.instance).getHome().getRooms().get(this.mPosi)).getDvcVos());
        localException.printStackTrace();
      }
    }
  }

  public void loopCheckDeviceIsOnline()
  {
    if ((this.handler != null) && (this.roomBusiness != null))
    {
      this.handler.removeCallbacks(this.Heartbeat);
      this.handler.postDelayed(this.Heartbeat, 4000L);
    }
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1)
      this.roomBusiness.setBgView(AtMain.instance, this.ivBg);
    if (paramInt1 == 9162)
      this.roomBusiness.beginCrop(this, AtMain.instance, paramIntent.getData());
    do
    {
      return;
      if (paramInt1 != 6709)
        continue;
      this.roomBusiness.handleCrop(AtMain.instance, paramIntent, this.ivBg);
      return;
    }
    while (paramInt1 != 202);
    this.groupAdapter.notifyDataSetChanged();
    this.roomBusiness.checkGroupStatus();
    this.devicGridAdapter.notifyDataSetChanged();
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }

  public void onClick(View paramView)
  {
    this.SynListView = true;
    int i;
    int j;
    label25: Dvc localDvc3;
    if (paramView == this.threeBtnsLlOnoff)
    {
      i = 1;
      if (paramView != this.twoBtnsLlOnoff)
        break label176;
      j = 1;
      if ((i | j) == 0)
        break label243;
      if (!this.seletedDvc.isGroup())
        break label621;
      localDvc3 = (Dvc)this.roomBusiness.dvcVos.get(this.dvcClickPosi);
      if (((Dvc)this.roomBusiness.dvcVos.get(this.dvcClickPosi)).isOnOff())
        break label181;
    }
    label176: label181: for (boolean bool3 = true; ; bool3 = false)
    {
      this.lampOnOff = bool3;
      localDvc3.setOnOff(bool3);
      for (int k = 0; k < ((Dvc)this.roomBusiness.dvcVos.get(this.dvcClickPosi)).innerDvcVos.size(); k++)
        ((Dvc)((Dvc)this.roomBusiness.dvcVos.get(this.dvcClickPosi)).innerDvcVos.get(k)).setOnOff(this.lampOnOff);
      i = 0;
      break;
      j = 0;
      break label25;
    }
    this.roomBusiness.deviceGroupOnOff(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
        RoomFragment.access$3702(RoomFragment.this, false);
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        if (RoomFragment.this.SynListView);
        RoomFragment.access$3702(RoomFragment.this, false);
      }

      public void onTimeOut()
      {
        RoomFragment.access$3702(RoomFragment.this, false);
      }
    }
    , this.mPosi, this.seletedDvc.getType(), this.lampOnOff, this.seletedDvc.getGroupId());
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        RoomFragment.this.roomBusiness.syncDeviceInfo(RoomFragment.this.roomIndex, RoomFragment.this.seletedDvc.getmIndex());
      }
    }
    , 50L);
    label243: if (paramView == this.threeBtnsLlMode)
    {
      if ((this.tvMode.getText().toString().equals(getString(R.string.mode))) && (this.seletedDvc.isOnLine()))
      {
        Intent localIntent2 = new Intent(AtMain.instance, AtFragmentMaster.class);
        localIntent2.putExtra("DIndexKey", this.seletedDvc.getmIndex());
        localIntent2.putExtra("DRoomNumKey", 1 + this.mPosi);
        localIntent2.putExtra("AtTypeKey", "AtTypeMode");
        localIntent2.putExtra("ModeIsGroupKey", this.isClickGroup);
        localIntent2.putExtra("ModeGroupIdKey", this.seletedDvc.getGroupId());
        localIntent2.putExtra("ModeDvcOderIdKey", this.seletedDvc.getmIndex());
        startActivity(localIntent2);
      }
      if ((!this.tvMode.getText().toString().equals(getString(2131100041))) || (!this.seletedDvc.isOnLine()));
    }
    Intent localIntent1;
    if ((paramView == this.threeBtnsLlMusic) && (this.seletedDvc.isOnLine()) && (this.tvMusic.getText().toString().equals(getString(2131100194))))
    {
      localIntent1 = new Intent(AtMain.instance, AtFragmentMaster.class);
      localIntent1.putExtra("AtTypeKey", "AtTypeMusic");
      localIntent1.putExtra("DTypeKey", 67);
      localIntent1.putExtra("DRoomNumKey", 1 + this.mPosi);
      if (!this.seletedDvc.isGroup())
        break label840;
      localIntent1.putExtra("DIndexKey", this.seletedDvc.getGroupId());
      localIntent1.putExtra("MusicIsGroupKey", true);
    }
    while (true)
    {
      startActivity(localIntent1);
      if ((paramView == this.twoBtnsLlDiy) && (this.seletedDvc.isOnLine()));
      switch (this.seletedDvc.getType())
      {
      case 10:
      default:
        return;
        label621: if (this.innerDvcClickPosi == -1)
        {
          Dvc localDvc2 = (Dvc)this.roomBusiness.dvcVos.get(this.dvcClickPosi);
          if (!((Dvc)this.roomBusiness.dvcVos.get(this.dvcClickPosi)).isOnOff());
          for (boolean bool2 = true; ; bool2 = false)
          {
            this.lampOnOff = bool2;
            localDvc2.setOnOff(bool2);
            this.roomBusiness.deviceOff(new MyBusiness.MySendListener()
            {
              public void onFail()
              {
                RoomFragment.access$3702(RoomFragment.this, false);
              }

              public void onOk(byte[] paramArrayOfByte)
              {
                if (RoomFragment.this.SynListView);
                RoomFragment.access$3702(RoomFragment.this, false);
              }

              public void onTimeOut()
              {
                RoomFragment.access$3702(RoomFragment.this, false);
              }
            }
            , this.mPosi, this.seletedDvc.getmIndex(), this.lampOnOff, this.seletedDvc.getType(), this.seletedDvc.isGroup(), this.seletedDvc.getGroupId());
            break;
          }
        }
        Dvc localDvc1 = (Dvc)((Dvc)this.roomBusiness.dvcVos.get(this.dvcClickPosi)).innerDvcVos.get(this.innerDvcClickPosi);
        if (!((Dvc)((Dvc)this.roomBusiness.dvcVos.get(this.dvcClickPosi)).innerDvcVos.get(this.innerDvcClickPosi)).isOnOff());
        for (boolean bool1 = true; ; bool1 = false)
        {
          this.lampOnOff = bool1;
          localDvc1.setOnOff(bool1);
          break;
        }
        label840: localIntent1.putExtra("DIndexKey", this.seletedDvc.getmIndex());
        localIntent1.putExtra("MusicIsGroupKey", false);
      case 11:
      case 9:
      }
    }
    MyAlertDialog16 localMyAlertDialog16 = new MyAlertDialog16(AtMain.instance);
    localMyAlertDialog16.show();
    setDialogWindowAttr(localMyAlertDialog16, AtMain.instance);
    localMyAlertDialog16.setMyOnClickListener(new MyAlertDialog16.MyOnClickListener()
    {
      public void onClick(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return;
        case 0:
          RoomFragment.this.sendCtADimValue(100.0F, 25);
          return;
        case 1:
          RoomFragment.this.sendCtADimValue(80.0F, 50);
          return;
        case 2:
          RoomFragment.this.sendCtADimValue(60.0F, 75);
          return;
        case 3:
          RoomFragment.this.sendCtADimValue(40.0F, 100);
          return;
        case 4:
        }
        RoomFragment.this.sendCtADimValue(20.0F, 255);
      }
    });
    return;
    MyAlertDialog15 localMyAlertDialog15 = new MyAlertDialog15(AtMain.instance);
    localMyAlertDialog15.show();
    localMyAlertDialog15.setMyOnClickListener(new MyAlertDialog15.MyOnClickListener()
    {
      public void onClick(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return;
        case 0:
          RoomFragment.this.sendCtADimValue(100.0F, 255);
          return;
        case 1:
          RoomFragment.this.sendCtADimValue(68.620003F, 255);
          return;
        case 2:
          RoomFragment.this.sendCtADimValue(52.939999F, 255);
          return;
        case 3:
          RoomFragment.this.sendCtADimValue(47.049999F, 204);
          return;
        case 4:
          RoomFragment.this.sendCtADimValue(21.559999F, 204);
          return;
        case 5:
          RoomFragment.this.sendCtADimValue(11.76F, 178);
          return;
        case 6:
          RoomFragment.this.sendCtADimValue(1.0E-005F, 26);
          return;
        case 7:
        }
        RoomFragment.this.sendCtADimValue(1.0E-005F, 8);
      }
    });
    setDialogWindowAttr(localMyAlertDialog15, AtMain.instance);
  }

  public void onColorChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean)
  {
    r = paramInt2;
    g = paramInt3;
    b = paramInt4;
    if (this.seletedDvc != null)
    {
      if (this.seletedDvc.getType() != 8)
        break label171;
      if (this.blackWhiteSb.getProgress() != 100)
      {
        this.blackWhiteSb.setOnSeekBarChangeListener(null);
        this.blackWhiteSb.setProgress(100);
        this.blackWhiteSb.setOnSeekBarChangeListener(this);
      }
    }
    while (true)
    {
      this.touchXPercent = (paramInt5 * 100 / this.pickerview.getWidth());
      this.touchYPercent = (paramInt6 * 100 / this.pickerview.getHeight());
      if (!paramBoolean)
        break;
      this.roomBusiness.sendActionUpColor(this.seletedDvc.isGroup(), this.seletedDvc.getGroupId(), this.seletedDvc.getType(), this.seletedDvc.getRoomIndex(), this.seletedDvc.getmIndex(), paramInt2, paramInt3, paramInt4, 0, 255, this.touchXPercent, this.touchYPercent);
      return;
      label171: if ((this.seletedDvc.getType() != 12) || (this.rgbwBrtSb.getProgress() == 100))
        continue;
      this.rgbwBrtSb.setOnSeekBarChangeListener(null);
      this.rgbwBrtSb.setProgress(100);
      this.rgbwBrtSb.setOnSeekBarChangeListener(this);
    }
    this.roomBusiness.sendColor(this.seletedDvc.isGroup(), this.seletedDvc.getGroupId(), this.seletedDvc.getType(), this.seletedDvc.getRoomIndex(), this.seletedDvc.getmIndex(), paramInt2, paramInt3, paramInt4, 0, 255, this.touchXPercent, this.touchYPercent);
  }

  @Nullable
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968774, null);
      findMyViewById();
    }
    try
    {
      initView();
      if (this.mPosi == 0)
        onMimeSeleted();
      init();
      return this.view;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void onDeivceEdit(Dvc paramDvc, int paramInt)
  {
    this.seletedDvc = paramDvc;
    AlertDialog105DeviceEdit localAlertDialog105DeviceEdit = new AlertDialog105DeviceEdit(AtMain.instance);
    localAlertDialog105DeviceEdit.show();
    if (this.isGroupShow)
      localAlertDialog105DeviceEdit.setRemoveOutGroupTextVisiable();
    while (true)
    {
      if ((this.seletedDvc.isGroup()) && (this.seletedDvc.innerDvcVos.size() > 1))
        localAlertDialog105DeviceEdit.setDelDeviceTextGone();
      Window localWindow = localAlertDialog105DeviceEdit.getWindow();
      localWindow.getAttributes();
      localWindow.setGravity(80);
      localAlertDialog105DeviceEdit.setMyOnClickListener(new AlertDialog105DeviceEdit.MyOnClickListener(paramInt)
      {
        public void onClick(int paramInt)
        {
          switch (paramInt)
          {
          default:
            return;
          case 0:
            RoomFragment.this.roomBusiness.onGroupInnerDvcDragStart(this.val$posi);
            RoomFragment.this.dialog = ProgressDialog.show(AtMain.instance, "", RoomFragment.this.getString(2131100002), false);
            if (RoomFragment.this.roomBusiness.getGroupInnerDvcCount() != 2)
            {
              RoomFragment.this.roomBusiness.dragOutGroup(new MyBusiness.MySendListener()
              {
                public void onFail()
                {
                  RoomFragment.this.roomBusiness.setMySendListener(null);
                  RoomFragment.this.dialog.dismiss();
                }

                public void onOk(byte[] paramArrayOfByte)
                {
                  String str = StringUtils.btye2Str(paramArrayOfByte);
                  if ((str.length() == 42) && (str.substring(24, 32).equals(RoomFragment.this.roomBusiness.dragOutDvcId)))
                  {
                    RoomFragment.this.roomBusiness.onGroupInnerDvcDragFinish();
                    RoomFragment.this.groupAdapter.setDvcVos(((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(RoomFragment.this.dvcClickPosi)).innerDvcVos);
                    if (((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(RoomFragment.this.dvcClickPosi)).innerDvcVos.size() == 0)
                    {
                      RoomFragment.this.rlGvGroupParent.setVisibility(View.GONE);
                      RoomFragment.this.isGroupLayoutShow = false;
                      RoomFragment.access$502(RoomFragment.this, -1);
                      RoomFragment.this.mainPullRefreshView.setCanRefresh(true);
                      RoomFragment.this.isGroupShow = false;
                    }
                    RoomFragment.this.groupAdapter.notifyDataSetChanged();
                    RoomFragment.this.roomBusiness.hideDelBtn();
                    RoomFragment.this.roomBusiness.checkGroupStatus();
                    RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
                    RoomFragment.this.roomBusiness.setMySendListener(null);
                  }
                  if (RoomFragment.this.dialog.isShowing())
                    RoomFragment.this.dialog.dismiss();
                }

                public void onTimeOut()
                {
                  RoomFragment.this.roomBusiness.setMySendListener(null);
                  RoomFragment.this.dialog.dismiss();
                }
              });
              return;
            }
            RoomFragment.this.roomBusiness.dragOutGroupRemoveAllSon(new MyBusiness.MySendListener()
            {
              public void onFail()
              {
                RoomFragment.this.roomBusiness.setMySendListener(null);
                RoomFragment.this.dialog.dismiss();
              }

              public void onOk(byte[] paramArrayOfByte)
              {
                String str = StringUtils.btye2Str(paramArrayOfByte);
                if ((str.length() == 50) && (str.substring(24, 32).equals(RoomFragment.this.roomBusiness.dragOutDvcId)))
                {
                  RoomFragment.this.roomBusiness.onGroupInnerDvcDragFinish();
                  RoomFragment.this.groupAdapter.setDvcVos(((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(RoomFragment.this.dvcClickPosi)).innerDvcVos);
                  if (((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(RoomFragment.this.dvcClickPosi)).innerDvcVos.size() == 0)
                  {
                    RoomFragment.this.rlGvGroupParent.setVisibility(View.GONE);
                    RoomFragment.this.isGroupLayoutShow = false;
                    RoomFragment.access$502(RoomFragment.this, -1);
                    RoomFragment.this.mainPullRefreshView.setCanRefresh(true);
                    RoomFragment.this.isGroupShow = false;
                  }
                  RoomFragment.this.groupAdapter.notifyDataSetChanged();
                  RoomFragment.this.roomBusiness.hideDelBtn();
                  RoomFragment.this.roomBusiness.checkGroupStatus();
                  RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
                  RoomFragment.this.roomBusiness.setMySendListener(null);
                }
                if (RoomFragment.this.dialog.isShowing())
                  RoomFragment.this.dialog.dismiss();
              }

              public void onTimeOut()
              {
                RoomFragment.this.roomBusiness.setMySendListener(null);
                RoomFragment.this.dialog.dismiss();
              }
            });
            return;
          case 1:
            MyAlertDialog12 localMyAlertDialog12 = new MyAlertDialog12(AtMain.instance);
            localMyAlertDialog12.setMyOnClickListener(new MyAlertDialog12.MyOnClickListener()
            {
              public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
              {
                RoomFragment.this.dialog = ProgressDialog.show(AtMain.instance, "", RoomFragment.this.getString(2131100002), false);
                RoomFragment.this.dialog.show();
                RoomFragment.this.roomBusiness.deleteDvc(new MyBusiness.MySendListener()
                {
                  public void onFail()
                  {
                    RoomFragment.this.roomBusiness.setMySendListener(null);
                    RoomFragment.this.dialog.dismiss();
                    RoomFragment.this.shortToast(2131100334);
                  }

                  public void onOk(byte[] paramArrayOfByte)
                  {
                    String str = StringUtils.btye2Str(paramArrayOfByte);
                    if ((str.length() >= 30) && (str.substring(18, 20).equals("91")))
                    {
                      if (!RoomFragment.this.isGroupLayoutShow)
                        break label167;
                      RoomFragment.this.roomBusiness.delGroupInnerDevice(RoomFragment.25.this.val$posi);
                      RoomFragment.this.roomBusiness.updataGroupDvc(RoomFragment.this.dvcClickPosi, RoomFragment.this.roomBusiness.groupInnerDvcVos);
                      RoomFragment.this.groupAdapter.notifyDataSetChanged();
                    }
                    while (true)
                    {
                      RoomFragment.this.roomBusiness.setMySendListener(null);
                      RoomFragment.this.dialog.dismiss();
                      return;
                      label167: RoomFragment.this.roomBusiness.delDeivceInRoom(RoomFragment.this.mPosi, RoomFragment.25.this.val$posi);
                      RoomFragment.this.roomBusiness.checkGroupStatus();
                      RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
                    }
                  }

                  public void onTimeOut()
                  {
                    RoomFragment.this.roomBusiness.setMySendListener(null);
                    RoomFragment.this.dialog.dismiss();
                    RoomFragment.this.shortToast(2131100336);
                  }
                }
                , RoomFragment.this.seletedDvc);
              }
            });
            localMyAlertDialog12.show();
            localMyAlertDialog12.setMsg(2131100023);
            return;
          case 2:
          }
          DeviceNameEditDialog105 localDeviceNameEditDialog105 = new DeviceNameEditDialog105(AtMain.instance);
          localDeviceNameEditDialog105.show();
          if ((RoomFragment.this.seletedDvc.isGroup()) && (RoomFragment.this.seletedDvc.innerDvcVos.size() > 1))
            localDeviceNameEditDialog105.setContent(RoomFragment.this.seletedDvc.getGroupName());
          while (true)
          {
            localDeviceNameEditDialog105.getWindow().clearFlags(131080);
            localDeviceNameEditDialog105.getWindow().setSoftInputMode(4);
            localDeviceNameEditDialog105.setMyOnClickListener(new DeviceNameEditDialog105.MyOnClickListener()
            {
              public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
              {
                int i = 1;
                RoomFragment.this.dialog = ProgressDialog.show(AtMain.instance, "", RoomFragment.this.getString(2131100002), false);
                RoomFragment.this.dialog.show();
                int j = 67;
                switch (RoomFragment.this.seletedDvc.getType())
                {
                case 10:
                case 12:
                case 13:
                default:
                  if (RoomFragment.this.seletedDvc.innerDvcVos.size() <= i)
                    break;
                case 11:
                case 9:
                case 8:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                }
                for (int k = i; ; k = 0)
                {
                  if (paramString.getBytes().length <= 24)
                    break label241;
                  Toast.makeText(AtMain.instance, R.string.rename_count, 0).show();
                  RoomFragment.this.dialog.dismiss();
                  return;
                  j = 69;
                  break;
                  j = 70;
                  break;
                  j = 67;
                  break;
                  j = 81;
                  break;
                  j = 34;
                  break;
                  j = 35;
                  break;
                  j = 36;
                  break;
                  j = 37;
                  break;
                }
                label241: RoomBusiness localRoomBusiness = RoomFragment.this.roomBusiness;
                1 local1 = new MyBusiness.MySendListener(paramString, k)
                {
                  public void onFail()
                  {
                    RoomFragment.this.roomBusiness.setMySendListener(null);
                    RoomFragment.this.dialog.dismiss();
                    RoomFragment.this.shortToast(2131100334);
                  }

                  public void onOk(byte[] paramArrayOfByte)
                  {
                    String str = StringUtils.btye2Str(paramArrayOfByte);
                    if ((str.length() >= 30) && (str.substring(18, 20).equals("AD")))
                    {
                      if (RoomFragment.this.isGroupShow)
                      {
                        RoomFragment.this.roomBusiness.renameGroupInnerDevice(RoomFragment.25.this.val$posi, this.val$content);
                        RoomFragment.access$1702(RoomFragment.this, new GroupAdapter(AtMain.instance, RoomFragment.this.roomBusiness.groupInnerDvcVos, new GroupAdapter.OnMyDelBtnListener()
                        {
                          public void onGroudInnerDeviceEdit(Dvc paramDvc, int paramInt)
                          {
                            RoomFragment.this.onDeivceEdit(paramDvc, paramInt);
                          }
                        }));
                        RoomFragment.this.gvGroup.setAdapter(RoomFragment.this.groupAdapter);
                        RoomFragment.this.roomBusiness.updataGroupDvc(RoomFragment.this.dvcClickPosi, RoomFragment.this.roomBusiness.groupInnerDvcVos);
                        RoomFragment.this.roomBusiness.setMySendListener(null);
                      }
                    }
                    else
                    {
                      RoomFragment.this.dialog.dismiss();
                      return;
                    }
                    if (this.val$isGroup)
                      ((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(RoomFragment.25.this.val$posi)).setGroupName(this.val$content);
                    while (true)
                    {
                      RoomFragment.this.roomBusiness.checkGroupStatus();
                      RoomFragment.this.devicGridAdapter.notifyDataSetChanged();
                      System.out.println(RoomFragment.this.isGroupShow + this.val$content);
                      break;
                      ((Dvc)RoomFragment.this.roomBusiness.dvcVos.get(RoomFragment.25.this.val$posi)).setName(this.val$content);
                    }
                  }

                  public void onTimeOut()
                  {
                    RoomFragment.this.roomBusiness.setMySendListener(null);
                    RoomFragment.this.dialog.dismiss();
                    RoomFragment.this.shortToast(2131100336);
                  }
                };
                if (k != 0)
                  if (k == 0)
                    break label315;
                label315: for (int m = RoomFragment.this.seletedDvc.getGroupId(); ; m = RoomFragment.this.seletedDvc.getmIndex())
                {
                  localRoomBusiness.changeName(local1, i, j, m, paramString.getBytes(), k);
                  return;
                  i = 0;
                  break;
                }
              }
            });
            return;
            localDeviceNameEditDialog105.setContent(RoomFragment.this.seletedDvc.getName());
          }
        }
      });
      return;
      localAlertDialog105DeviceEdit.setDelDeviceTextVisiable();
    }
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    this.handler.post(this.PullRefreshRunnable);
    unRegBroadcast();
    removeAllRunnable();
    ButterKnife.unbind(this);
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 1000)
    {
      this.ctModeBussiness.updateData();
      this.ctModeAdapter.notifyDataSetChanged();
    }
    if (paramInt2 == 1205)
      setAdtView();
  }

  public void onLvSbChange(int paramInt1, int paramInt2)
  {
    this.clickType = ((Dvc)this.roomBusiness.listviewDvcVos.get(paramInt2)).getType();
    this.seletedDvc = ((Dvc)this.roomBusiness.listviewDvcVos.get(paramInt2));
    brt = paramInt1 * 255 / 100;
    this.seletedDvc.setOnOff(true);
    PrintStream localPrintStream = System.out;
    if ("onLvSbChange" + this.seletedDvc == null);
    for (boolean bool = true; ; bool = false)
    {
      localPrintStream.println(bool);
      if ((this.seletedDvc != null) && ((this.clickType == 11) || (this.clickType == 9)))
      {
        this.seletedDvc.setOnOff(true);
        this.roomBusiness.sendDIMnCTnRGB(this.seletedDvc, 255 - this.seletedDvc.getW(), this.seletedDvc.getW(), brt, this.clickType, this.touchXPercent, this.touchYPercent);
      }
      if ((this.seletedDvc != null) && (this.clickType == 8))
      {
        this.seletedDvc.setOnOff(true);
        this.roomBusiness.sendBright(this.seletedDvc.isGroup(), this.seletedDvc.getGroupId(), this.seletedDvc.getType(), this.seletedDvc.getRoomIndex(), this.seletedDvc.getmIndex(), r, g, b, 255, brt, this.touchXPercent, this.touchYPercent);
      }
      return;
    }
  }

  public void onLvSbChange(SeekBar paramSeekBar, int paramInt)
  {
  }

  public void onLvSbChanged(int paramInt)
  {
    ((Dvc)this.roomBusiness.listviewDvcVos.get(paramInt)).setOnOff(true);
    this.deviceListAdapter.notifyDataSetChanged();
  }

  public void onMimeInvisiable()
  {
    initRoomBusiness();
    this.roomBusiness.hideDelBtn();
    this.roomBusiness.setMySendListener(null);
    this.roomBusiness.setDvcStatusListener(null);
    this.roomBusiness.saveDeivceInRoom(this.mPosi);
    this.handler.removeCallbacks(this.Heartbeat);
  }

  public void onMimeSeleted()
  {
    System.out.println(this.t + "     onMimeSeleted    " + this.mPosi);
    if (!this.isInit)
      initData();
    this.mHandler.postDelayed(this.AutoRefreshRunnable, 300L);
    setAdtView();
  }

  public void onPause()
  {
    super.onPause();
    if (this.roomBusiness != null)
    {
      this.roomBusiness.setMySendListener(null);
      this.roomBusiness.hideDelBtn();
      this.roomBusiness.saveDeivceInRoom(this.mPosi);
      this.handler.removeCallbacks(this.Heartbeat);
      this.roomBusiness.setDvcStatusListener(null);
    }
    this.mainPullRefreshView.refreshFinish(1);
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    this.seletedDvc.setOnOff(true);
    if ((this.c == 0) && (this.w == 0))
    {
      this.c = (255 - this.seletedDvc.getW());
      this.w = this.seletedDvc.getW();
    }
    brt = 255 * paramSeekBar.getProgress() / 100;
    if (brt < 12)
      brt = 12;
    if ((this.seletedDvc != null) && ((this.clickType == 8) || (this.clickType == 12)))
    {
      this.seletedDvc.setR(r);
      this.seletedDvc.setG(g);
      this.seletedDvc.setB(b);
      this.seletedDvc.setBrt(brt);
      this.roomBusiness.sendColor(this.seletedDvc.isGroup(), this.seletedDvc.getGroupId(), this.seletedDvc.getType(), this.seletedDvc.getRoomIndex(), this.seletedDvc.getmIndex(), r, g, b, this.rgbw$w, brt, this.touchXPercent, this.touchYPercent);
    }
    while (true)
    {
      this.roomBusiness.updataDeviceBrt(this.seletedDvc.getmIndex(), paramSeekBar.getProgress());
      return;
      if ((this.seletedDvc == null) || ((this.clickType != 11) && (this.clickType != 9)))
        continue;
      this.seletedDvc.setW(this.w);
      this.seletedDvc.setBrt(brt);
      this.roomBusiness.sendDIMnCTnRGB(this.seletedDvc, this.c, this.w, brt, this.clickType, this.touchXPercent, this.touchYPercent);
    }
  }

  public void onResume()
  {
    super.onResume();
    regBroadcast(this.mItemViewListClickReceiver);
    loopCheckDeviceIsOnline();
    if (this.gvDevice != null)
      this.gvDevice.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          RoomFragment.this.clickItem(paramView, paramInt, paramLong, true);
          System.out.println("clickItem");
        }
      });
    if (this.mainPullRefreshView != null);
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    Log.i("", "");
    this.isSyncProgress = false;
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void onXChange(float paramFloat, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.touchXPercent = (paramInt1 * 100 / this.pickerview.getWidth());
    this.touchYPercent = (paramInt2 * 100 / this.pickerview.getHeight());
    if (paramBoolean)
    {
      brt = 255;
      sendActionUpCtADimValue(paramFloat, 255);
      return;
    }
    brt = 255;
    sendCtADimValue(paramFloat, 255);
  }

  public void roomIndex(int paramInt)
  {
    this.roomIndex = paramInt;
  }

  public void seekBar()
  {
  }

  public void setRoom(Room paramRoom)
  {
    this.room = paramRoom;
  }

  public void setmPosi(int paramInt)
  {
    this.mPosi = paramInt;
  }

  public void switchOnOff(int paramInt, boolean paramBoolean)
  {
    this.seletedDvc = ((Dvc)this.roomBusiness.listviewDvcVos.get(paramInt));
    ((Dvc)this.roomBusiness.listviewDvcVos.get(paramInt)).setOnOff(paramBoolean);
    this.roomBusiness.updataDeviceGridData(this.seletedDvc.getmIndex(), paramBoolean);
    for (int i = 0; i < this.roomBusiness.dvcVos.size(); i++);
    this.roomBusiness.deviceOff(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
        RoomFragment.this.shortToast(2131100334);
      }

      public void onOk(byte[] paramArrayOfByte)
      {
      }

      public void onTimeOut()
      {
        RoomFragment.this.shortToast(2131100336);
      }
    }
    , this.mPosi, this.seletedDvc.getmIndex(), paramBoolean, this.seletedDvc.getType(), this.seletedDvc.isGroup(), this.seletedDvc.getGroupId());
  }

  public void updateGridView()
  {
    this.roomBusiness.dvcVos.clear();
    this.home = new RoomsBusiness(AtMain.instance).getHome();
    this.roomBusiness.dvcVos.addAll(((Room)this.home.getRooms().get(this.roomIndex)).getDvcVos());
    try
    {
      if (this.roomBusiness.dvcVos.size() > 0)
        this.btAddDevice.setVisibility(View.GONE);
      this.roomBusiness.checkGroupStatus();
      this.devicGridAdapter.notifyDataSetChanged();
      this.deviceListAdapter.notifyDataSetChanged();
      return;
    }
    catch (Exception localException1)
    {
      while (true)
        try
        {
          this.btAddDevice.setVisibility(View.GONE);
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
        }
    }
  }

  class RunnableAnimaion
    implements Runnable
  {
    View view;

    RunnableAnimaion()
    {
    }

    public void run()
    {
      this.view.setVisibility(View.GONE);
    }
  }

  class SearchBlubRunnable
    implements Runnable
  {
    SearchBlubRunnable()
    {
    }

    public void run()
    {
      RoomFragment.this.searchDevice(79);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.RoomFragment
 * JD-Core Version:    0.6.0
 */