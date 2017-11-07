package com.ex.ltech.hongwai.yaokong;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.YkAt;
import com.ex.ltech.hongwai.atRcs.AtAirConActivity;
import com.ex.ltech.hongwai.atRcs.AtDiy;
import com.ex.ltech.hongwai.atRcs.AtFan;
import com.ex.ltech.hongwai.atRcs.AtNewBox;
import com.ex.ltech.hongwai.atRcs.AtNewStb;
import com.ex.ltech.hongwai.atRcs.AtNewTv;
import com.ex.ltech.hongwai.atRcs.AtProjecter;
import com.ex.ltech.hongwai.scene.MyBiz;
import com.ex.ltech.hongwai.view.dslv.DragSortController;
import com.ex.ltech.hongwai.view.dslv.DragSortListView;
import com.ex.ltech.hongwai.view.dslv.DragSortListView.DropListener;
import com.ex.ltech.hongwai.vo.IrSetting;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.hongwai.vo.NonIrDevice;
import com.ex.ltech.hongwai.vo.SceneVo;
import com.ex.ltech.hongwai.vo.SceneVos;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.my_view.DiscreteSeekBar;
import com.ex.ltech.led.my_view.DiscreteSeekBar.OnProgressChangeListener;
import com.ex.ltech.led.my_view.MLImageView;
import com.ex.ltech.led.my_view.MyAlertDialog20;
import com.ex.ltech.led.my_view.MyAlertDialog20.MyOnClickListener;
import com.ex.ltech.led.my_view.MyTimePickerView2;
import com.ex.ltech.led.my_view.MyTimePickerView2.onSelectListener;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.Action.Builder;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.appindexing.Thing.Builder;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.hzy.tvmao.KKACManagerV2;
import com.hzy.tvmao.KKNonACManager;
import com.hzy.tvmao.ir.ac.ACModelV2;
import com.hzy.tvmao.ir.ac.ACStateV2;
import com.hzy.tvmao.ir.ac.ACStateV2.UDWindDirectKey;
import com.hzy.tvmao.ir.ac.ACStateV2.UDWindDirectType;
import com.kookong.app.data.api.IrData;
import com.kookong.app.data.api.IrData.IrKey;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChanged;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AtYaokongActivity extends YkAt
{
  final int OfflineDeviceRespCode = 2;
  private ItRcLvAdapter adapter;
  Runnable checkDeviceStatusDelay = new Runnable()
  {
    public void run()
    {
      if ((AtYaokongActivity.this.dialog != null) && (AtYaokongActivity.this.dialog.isShowing()))
        AtYaokongActivity.this.dialog.dismiss();
      Toast.makeText(AtYaokongActivity.this, 2131099974, 0).show();
      MyAlertDialog20 localMyAlertDialog20 = new MyAlertDialog20(AtYaokongActivity.this);
      localMyAlertDialog20.show();
      localMyAlertDialog20.setMyOnClickListener(new MyAlertDialog20.MyOnClickListener()
      {
        public void onClick(int paramInt)
        {
          if (paramInt == 0)
            SocketManager.instance().sendData(AtYaokongActivity.this.cmd.queryIr$Devices());
          do
            return;
          while (1 != paramInt);
          SocketManager.instance().sendData(AtYaokongActivity.this.cmd.delIrLight(AtYaokongActivity.this.clickOfflineDeviceId));
          AtYaokongActivity.this.clickOfflineDeviceId = -1;
        }
      });
    }
  };
  int clickOfflineDeviceId = -1;
  int clickOfflineDevicePosi = -1;
  private GoogleApiClient client;
  CmdDateBussiness cmd;
  private ProgressDialog dialog;
  Handler handler = new Handler()
  {
  };
  private GridView horizontalGrid;
  private HorizontalScrollView horizontalScrollView;
  private boolean isEdit;
  boolean isLongClickNow;
  boolean isWenduSeleting;
  KKACManagerV2 kkACManager;
  KKNonACManager kkNonACManager;
  private DragSortListView lv;
  private ImageView mSceneArrow;

  @Bind({2131558963})
  RelativeLayout pop;
  private MyRcDevices rcDevices;
  RunnableAnimaion runnableAnimaion = new RunnableAnimaion();
  SceneAdapter sceneAdapter;
  private MyBiz sceneBusiness;
  SceneVos sceneVos;
  private TextView title;

  @Bind({2131558966})
  TextView version;

  @Bind({2131558965})
  ToggleButton zzzSwich;

  private void hideViewAnimaion(View paramView)
  {
    this.handler.removeCallbacks(this.runnableAnimaion);
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 1.0F, 0.0F });
    localObjectAnimator.setDuration(50L);
    localObjectAnimator.start();
    this.runnableAnimaion.view = paramView;
    this.handler.postDelayed(this.runnableAnimaion, 50L);
  }

  private void initAirView(MyTimePickerView2 paramMyTimePickerView2, Button paramButton1, Button paramButton2, Button paramButton3)
  {
    if (this.kkACManager.getCurrentACModel().isTempCanControl())
      paramMyTimePickerView2.setSelected(this.kkACManager.getCurrentACModel().getCurTmp() + "°");
    switch (this.kkACManager.getCurrentACModel().getModelType())
    {
    default:
      if (!this.kkACManager.getCurrentACModel().isWindSpeedCanControl())
        break;
      switch (this.kkACManager.getCurrentACModel().getCurWindSpeed())
      {
      default:
      case 0:
      case 3:
      case 1:
      case 2:
      }
    case 2:
    case 0:
    case 1:
    case 3:
    case 4:
    }
    while (true)
    {
      paramButton3.setBackgroundResource(2130903410);
      if (this.kkACManager.getPowerState() != 1)
        break label240;
      return;
      paramButton1.setBackgroundResource(2130903057);
      break;
      paramButton1.setBackgroundResource(2130903407);
      break;
      paramButton1.setBackgroundResource(2130903806);
      break;
      paramButton1.setBackgroundResource(2130903409);
      break;
      paramButton1.setBackgroundResource(2130903408);
      break;
      paramButton2.setBackgroundResource(2130903520);
      continue;
      paramButton2.setBackgroundResource(2130903519);
      continue;
      paramButton2.setBackgroundResource(2130903517);
      continue;
      paramButton2.setBackgroundResource(2130903518);
    }
    label240: ACStateV2.UDWindDirectType localUDWindDirectType = this.kkACManager.getAcStateV2().getCurUDDirectType();
    int i = this.kkACManager.getAcStateV2().getCurUDDirect();
    switch (16.$SwitchMap$com$hzy$tvmao$ir$ac$ACStateV2$UDWindDirectType[localUDWindDirectType.ordinal()])
    {
    default:
      return;
    case 1:
      paramButton3.setBackgroundResource(2130903410);
      return;
    case 2:
      paramButton3.setBackgroundResource(2130903094);
      return;
    case 3:
    }
    if (i == 0)
    {
      paramButton3.setBackgroundResource(2130903410);
      return;
    }
    paramButton3.setBackgroundResource(2130903094);
  }

  private void initFanView(Button paramButton1, Button paramButton2, Button paramButton3, Button paramButton4)
  {
    paramButton1.setBackgroundResource(2130903819);
    paramButton2.setBackgroundResource(2130903822);
    paramButton3.setBackgroundResource(2130903815);
    paramButton4.setBackgroundResource(2130903821);
  }

  private void initListViewData()
  {
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
  }

  private void initSceneData()
  {
    if (this.sceneBusiness == null)
      this.sceneBusiness = new MyBiz(this);
    this.sceneVos = this.sceneBusiness.getSceneVos();
    this.cmd = new CmdDateBussiness(this, "0000");
  }

  private void initTvView(Button paramButton1, Button paramButton2, Button paramButton3, Button paramButton4)
  {
    paramButton1.setBackgroundResource(2130903819);
    paramButton2.setBackgroundResource(2130903054);
    paramButton3.setBackgroundResource(2130903774);
    paramButton4.setBackgroundResource(2130903502);
  }

  private void sendRcCodeByChineseName(String paramString)
  {
    for (int i = 0; i < this.kkNonACManager.getAllKeys().size(); i++)
    {
      if (!((IrData.IrKey)this.kkNonACManager.getAllKeys().get(i)).fname.equals(paramString))
        continue;
      sendCmd(this.kkNonACManager.getKeyIr(((IrData.IrKey)this.kkNonACManager.getAllKeys().get(i)).fkey));
    }
  }

  private void sendRcKey(MyRcDevice paramMyRcDevice, String paramString)
  {
    int i = -1;
    if (paramMyRcDevice.mType == 5)
    {
      this.kkACManager = new KKACManagerV2((IrData)paramMyRcDevice.irDatas.get(0));
      this.kkACManager.setACStateV2FromString(paramMyRcDevice.acState);
      paramMyRcDevice.acState = this.kkACManager.getACStateV2InString();
      this.sceneBusiness.sendRcParmas(this.kkACManager.getAcParams());
      if (((IrData)paramMyRcDevice.irDatas.get(0)).type == 1)
      {
        this.kkNonACManager = new KKNonACManager((IrData)paramMyRcDevice.irDatas.get(0));
        switch (paramString.hashCode())
        {
        default:
        case 3551:
        case 3357091:
        case 109641799:
        case -1331586071:
        }
        while (true)
          switch (i)
          {
          default:
            this.kkACManager.getCurrentACModel().setTemperature(Integer.parseInt(paramString.substring(0, -1 + paramString.length())));
            return;
            if (!paramString.equals("on"))
              continue;
            i = 0;
            continue;
            if (!paramString.equals("mode"))
              continue;
            i = 1;
            continue;
            if (!paramString.equals("speed"))
              continue;
            i = 2;
            continue;
            if (!paramString.equals("direct"))
              continue;
            i = 3;
          case 0:
          case 1:
          case 2:
          case 3:
          }
        this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            AtYaokongActivity.this.sendRcCodeByChineseName("电源");
          }
        }
        , 400L);
        return;
        this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            AtYaokongActivity.this.sendRcCodeByChineseName("模式");
          }
        }
        , 400L);
        return;
        this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            AtYaokongActivity.this.sendRcCodeByChineseName("风量");
          }
        }
        , 400L);
        return;
        this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            AtYaokongActivity.this.sendRcCodeByChineseName("上下定风");
          }
        }
        , 400L);
        return;
      }
      int j;
      switch (paramString.hashCode())
      {
      default:
        j = i;
        switch (j)
        {
        default:
          label407: this.kkACManager.getCurrentACModel().setTemperature(Integer.parseInt(paramString.substring(0, -1 + paramString.length())));
        case 0:
        case 1:
        case 2:
        case 3:
        }
      case 3551:
      case 3357091:
      case 109641799:
      case -1331586071:
      }
      while (true)
      {
        paramMyRcDevice.acState = this.kkACManager.getACStateV2InString();
        paramMyRcDevice.acModeState = this.kkACManager.getCurrentACModel().getModelType();
        this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            AtYaokongActivity.this.sendCmd(AtYaokongActivity.this.kkACManager.getACKeyIr());
          }
        }
        , 400L);
        return;
        if (!paramString.equals("on"))
          break;
        j = 0;
        break label407;
        if (!paramString.equals("mode"))
          break;
        j = 1;
        break label407;
        if (!paramString.equals("speed"))
          break;
        j = 2;
        break label407;
        if (!paramString.equals("direct"))
          break;
        j = 3;
        break label407;
        this.kkACManager.changePowerState();
        continue;
        if (paramMyRcDevice.acModeState < 4)
        {
          this.kkACManager.getAcStateV2().changeToTargetModel(1 + paramMyRcDevice.acModeState);
          continue;
        }
        this.kkACManager.getAcStateV2().changeToTargetModel(0);
        continue;
        this.kkACManager.getCurrentACModel().changeWindSpeed();
        continue;
        this.kkACManager.getAcStateV2().changeUDWindDirect(ACStateV2.UDWindDirectKey.UDDIRECT_KEY_SWING);
      }
    }
    if (paramMyRcDevice.mType == 8)
    {
      this.kkNonACManager = new KKNonACManager((IrData)paramMyRcDevice.irDatas.get(0));
      this.sceneBusiness.sendRcParmas(this.kkNonACManager.getParams());
      this.handler.postDelayed(new Runnable(paramString)
      {
        public void run()
        {
          String str = this.val$operate;
          int i = -1;
          switch (str.hashCode())
          {
          default:
          case 3551:
          case 3357091:
          case 109641799:
          case -1331586071:
          }
          while (true)
            switch (i)
            {
            default:
              return;
              if (!str.equals("on"))
                continue;
              i = 0;
              continue;
              if (!str.equals("mode"))
                continue;
              i = 1;
              continue;
              if (!str.equals("speed"))
                continue;
              i = 2;
              continue;
              if (!str.equals("direct"))
                continue;
              i = 3;
            case 0:
            case 1:
            case 2:
            case 3:
            }
          AtYaokongActivity.this.sendRcCodeByChineseName("电源");
          return;
          AtYaokongActivity.this.sendRcCodeByChineseName("风类");
          return;
          AtYaokongActivity.this.sendRcCodeByChineseName("风速");
          return;
          AtYaokongActivity.this.sendRcCodeByChineseName("摆风");
        }
      }
      , 400L);
      return;
    }
    if (paramMyRcDevice.mType == 1)
    {
      this.kkNonACManager = new KKNonACManager((IrData)paramMyRcDevice.irDatas.get(0));
      this.sceneBusiness.sendRcParmas(this.kkNonACManager.getParams());
      this.handler.postDelayed(new Runnable(paramString)
      {
        public void run()
        {
          String str = this.val$operate;
          int i = -1;
          switch (str.hashCode())
          {
          default:
          case -1361708601:
          case 1374304278:
          case 631754549:
          case 1536977412:
          }
          while (true)
            switch (i)
            {
            default:
              return;
              if (!str.equals("ch_add"))
                continue;
              i = 0;
              continue;
              if (!str.equals("ch_minus"))
                continue;
              i = 1;
              continue;
              if (!str.equals("vol_add"))
                continue;
              i = 2;
              continue;
              if (!str.equals("vol_minus"))
                continue;
              i = 3;
            case 0:
            case 1:
            case 2:
            case 3:
            }
          AtYaokongActivity.this.sendRcCodeByChineseName("频道+");
          return;
          AtYaokongActivity.this.sendRcCodeByChineseName("频道-");
          return;
          AtYaokongActivity.this.sendRcCodeByChineseName("音量+");
          return;
          AtYaokongActivity.this.sendRcCodeByChineseName("音量-");
        }
      }
      , 400L);
      return;
    }
    this.kkNonACManager = new KKNonACManager((IrData)paramMyRcDevice.irDatas.get(0));
    this.sceneBusiness.sendRcParmas(this.kkNonACManager.getParams());
    this.handler.postDelayed(new Runnable(paramString)
    {
      public void run()
      {
        String str = this.val$operate;
        int i = -1;
        switch (str.hashCode())
        {
        default:
        case 3551:
        case 96417:
        case 3541166:
        case 3363353:
        }
        while (true)
          switch (i)
          {
          default:
            return;
            if (!str.equals("on"))
              continue;
            i = 0;
            continue;
            if (!str.equals("add"))
              continue;
            i = 1;
            continue;
            if (!str.equals("stub"))
              continue;
            i = 2;
            continue;
            if (!str.equals("mute"))
              continue;
            i = 3;
          case 0:
          case 1:
          case 2:
          case 3:
          }
        AtYaokongActivity.this.sendRcCodeByChineseName("电源");
        return;
        AtYaokongActivity.this.sendRcCodeByChineseName("音量+");
        return;
        AtYaokongActivity.this.sendRcCodeByChineseName("音量-");
        return;
        AtYaokongActivity.this.sendRcCodeByChineseName("静音");
      }
    }
    , 400L);
  }

  private void setHorizontalGridView()
  {
    int i = this.sceneAdapter.getCount();
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    float f = localDisplayMetrics.density;
    (int)(f * (i * 104));
    (int)(f * 100);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(i * (localDisplayMetrics.widthPixels / 4) - i * 5, -1);
    this.horizontalGrid.setLayoutParams(localLayoutParams);
    this.horizontalGrid.setColumnWidth(-10 + localDisplayMetrics.widthPixels / 4);
    this.horizontalGrid.setHorizontalSpacing(5);
    this.horizontalGrid.setStretchMode(0);
    this.horizontalGrid.setNumColumns(i);
  }

  private void setMoreView()
  {
    IrSetting localIrSetting = (IrSetting)MyDb.getInstance(this).getBean(DeviceListActivity.deviceMacAddress, IrSetting.class);
    if (localIrSetting == null)
      localIrSetting = new IrSetting();
    if (localIrSetting.isOn())
      this.zzzSwich.setToggleOn();
    while (true)
    {
      this.version.setText(getString(2131100157) + localIrSetting.getVersion());
      return;
      this.zzzSwich.setToggleOff();
    }
  }

  private void showSceneGrid()
  {
    if ((!this.sceneVos.isShowGrid) && (this.sceneVos.sceneVos.size() > 0))
    {
      this.horizontalScrollView.setVisibility(View.VISIBLE);
      this.sceneVos.isShowGrid = true;
      this.mSceneArrow.setBackgroundResource(2130903582);
      return;
    }
    this.horizontalScrollView.setVisibility(View.GONE);
    this.sceneVos.isShowGrid = false;
    this.mSceneArrow.setBackgroundResource(2130903552);
  }

  private void showViewAnimaion(View paramView)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setDuration(50L);
    localObjectAnimator.start();
    paramView.setVisibility(View.VISIBLE);
  }

  private void skip2YkAt(MyRcDevice paramMyRcDevice, int paramInt)
  {
    int i = paramMyRcDevice.mType;
    Intent localIntent = null;
    switch (i)
    {
    case 4:
    case 7:
    default:
    case 2:
    case 3:
    case 1:
    case 8:
    case 6:
    case 5:
    case 9:
    case 10:
    case 11:
    case 12:
    }
    while (true)
    {
      localIntent.putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_EXIST");
      localIntent.putExtra("OP_AT_POSI_KEY", paramInt);
      startActivityForResult(localIntent, 0);
      return;
      localIntent = new Intent(this, AtNewTv.class);
      continue;
      localIntent = new Intent(this, AtNewBox.class);
      continue;
      localIntent = new Intent(this, AtNewStb.class);
      continue;
      localIntent = new Intent(this, AtFan.class);
      continue;
      localIntent = new Intent(this, AtProjecter.class);
      continue;
      localIntent = new Intent(this, AtAirConActivity.class);
      continue;
      localIntent = new Intent(this, AtDiy.class);
      continue;
      localIntent = new Intent(this, AtYkSwitchRf1Activity.class);
      continue;
      localIntent = new Intent(this, AtYkSwitchRf2Activity.class);
      continue;
      localIntent = new Intent(this, AtYkLampActivity.class);
    }
  }

  public void add(View paramView)
  {
    Intent localIntent;
    if (DeviceListActivity.devicePid.equals("160fa2afd1a7f600160fa2afd1a7f601"))
      localIntent = new Intent(this, AtYkTypeList.class);
    while (true)
    {
      if (localIntent == null)
        localIntent = new Intent(this, AtYkTypeList.class);
      localIntent.putExtra("OP_AT_POSI_KEY", this.adapter.getCount());
      startActivityForResult(localIntent, 0);
      return;
      boolean bool = DeviceListActivity.devicePid.equals("160fa2b2db6403e9160fa2b2db646801");
      localIntent = null;
      if (!bool)
        continue;
      localIntent = new Intent(this, NewAtYkTypeList.class);
    }
  }

  public void back(View paramView)
  {
    finish();
  }

  public void edit(View paramView)
  {
    if (!this.isEdit);
    for (boolean bool = true; ; bool = false)
    {
      this.isEdit = bool;
      this.adapter.notifyDataSetChanged();
      return;
    }
  }

  public void findViewAndSetListener()
  {
    this.mSceneArrow = ((ImageView)findViewById(2131558957));
    findViewById(2131558958).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        AtYaokongActivity.this.showSceneGrid();
      }
    });
    this.horizontalScrollView = ((HorizontalScrollView)findViewById(2131558959));
    this.horizontalGrid = ((GridView)findViewById(2131558960));
    this.lv = ((DragSortListView)findViewById(2131558961));
    this.adapter = new ItRcLvAdapter(this);
    this.lv.setAdapter(this.adapter);
    this.lv.setDividerHeight(0);
    DragSortController localDragSortController = new DragSortController(this.lv);
    localDragSortController.setDragHandleId(2131559316);
    localDragSortController.setSortEnabled(true);
    localDragSortController.setDragInitMode(2);
    this.lv.setFloatViewManager(localDragSortController);
    this.lv.setOnTouchListener(localDragSortController);
    this.lv.setDragEnabled(true);
    this.title = ((TextView)findViewById(2131558469));
    this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        if (AtYaokongActivity.this.isLongClickNow)
          AtYaokongActivity.this.isLongClickNow = false;
        do
          return;
        while ((((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(paramInt)).mType == 12) && (!((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(paramInt)).nonIrDevice.irCt1Online));
        AtYaokongActivity.this.skip2YkAt((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(paramInt), paramInt);
      }
    });
    this.lv.setDropListener(new DragSortListView.DropListener()
    {
      public void drop(int paramInt1, int paramInt2)
      {
        if (paramInt1 != paramInt2)
        {
          AtYaokongActivity.this.rcDevices.myRcDevices.add(paramInt2, AtYaokongActivity.this.rcDevices.myRcDevices.remove(paramInt1));
          AtYaokongActivity.this.adapter.notifyDataSetChanged();
          MyDb.getInstance(AtYaokongActivity.this.getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, AtYaokongActivity.this.rcDevices);
        }
      }
    });
    this.sceneAdapter = new SceneAdapter(this);
    setHorizontalGridView();
    this.horizontalGrid.setAdapter(this.sceneAdapter);
    if ((this.sceneVos.isShowGrid) && (this.sceneVos.sceneVos.size() > 0))
    {
      this.horizontalScrollView.setVisibility(View.VISIBLE);
      this.sceneVos.isShowGrid = true;
      this.mSceneArrow.setBackgroundResource(2130903582);
      return;
    }
    this.horizontalScrollView.setVisibility(View.GONE);
    this.sceneVos.isShowGrid = false;
    this.mSceneArrow.setBackgroundResource(2130903552);
  }

  public Action getIndexApiAction()
  {
    Thing localThing = new Thing.Builder().setName("AtYaokong Page").setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]")).build();
    return new Action.Builder("http://schema.org/ViewAction").setObject(localThing).setActionStatus("http://schema.org/CompletedActionStatus").build();
  }

  public MyRcDevices getRcDevices()
  {
    return this.rcDevices;
  }

  public void hidePop(View paramView)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.pop, "alpha", new float[] { 1.0F, 0.0F });
    localObjectAnimator.setDuration(300L);
    localObjectAnimator.start();
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        AtYaokongActivity.this.pop.setVisibility(View.GONE);
      }
    }
    , 300L);
  }

  protected void init()
  {
    initListViewData();
    initSceneData();
    findViewAndSetListener();
    this.zzzSwich.setOnToggleChanged(new ToggleButton.OnToggleChanged()
    {
      public void onToggle(boolean paramBoolean)
      {
        IrSetting localIrSetting = (IrSetting)MyDb.getInstance(AtYaokongActivity.this).getBean(DeviceListActivity.deviceMacAddress, IrSetting.class);
        if (localIrSetting == null)
          localIrSetting = new IrSetting();
        localIrSetting.setOn(paramBoolean);
        MyDb.getInstance(AtYaokongActivity.this).putBean(DeviceListActivity.deviceMacAddress, localIrSetting);
      }
    });
  }

  public void more(View paramView)
  {
    setMoreView();
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.pop, "alpha", new float[] { 0.0F, 1.0F });
    localObjectAnimator.setDuration(300L);
    localObjectAnimator.start();
    this.pop.setVisibility(View.VISIBLE);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    int i = 1;
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    int j;
    int k;
    label32: int n;
    label50: int i2;
    label68: int i4;
    label85: int i6;
    label102: int i7;
    if (paramInt2 == 10000)
    {
      j = i;
      if (paramInt2 != 80000)
        break label160;
      k = i;
      int m = j | k;
      if (paramInt2 != 90000)
        break label166;
      n = i;
      int i1 = m | n;
      if (paramInt2 != 110000)
        break label172;
      i2 = i;
      int i3 = i1 | i2;
      if (paramInt2 != 10)
        break label178;
      i4 = i;
      int i5 = i3 | i4;
      if (paramInt2 != 11)
        break label184;
      i6 = i;
      i7 = i6 | i5;
      if (paramInt2 != 12)
        break label190;
    }
    while (true)
    {
      if ((i | i7) != 0)
      {
        this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
        this.adapter.notifyDataSetChanged();
      }
      return;
      j = 0;
      break;
      label160: k = 0;
      break label32;
      label166: n = 0;
      break label50;
      label172: i2 = 0;
      break label68;
      label178: i4 = 0;
      break label85;
      label184: i6 = 0;
      break label102;
      label190: i = 0;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968687);
    ButterKnife.bind(this);
    init();
    this.client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
  }

  public void onDelDeviceOk()
  {
    if (this.clickOfflineDevicePosi != -1)
    {
      this.rcDevices.myRcDevices.remove(this.clickOfflineDevicePosi);
      this.adapter.notifyDataSetChanged();
      this.clickOfflineDevicePosi = -1;
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.handler.removeCallbacks(this.checkDeviceStatusDelay);
  }

  protected void onPause()
  {
    super.onPause();
    this.sceneBusiness.saveSceneVos(this.sceneVos);
    MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
  }

  protected void onResume()
  {
    super.onResume();
    if (this.sceneAdapter != null)
    {
      initSceneData();
      this.sceneAdapter.notifyDataSetChanged();
      setHorizontalGridView();
    }
  }

  public void onStart()
  {
    super.onStart();
    this.client.connect();
    AppIndex.AppIndexApi.start(this.client, getIndexApiAction());
  }

  public void onStop()
  {
    super.onStop();
    AppIndex.AppIndexApi.end(this.client, getIndexApiAction());
    this.client.disconnect();
  }

  public void updataDeviceList()
  {
    initListViewData();
    this.adapter.notifyDataSetChanged();
  }

  public void updataDeviceOnlineStatus(int paramInt1, int paramInt2)
  {
    if (paramInt1 == this.clickOfflineDeviceId);
    for (int i = 0; ; i++)
    {
      NonIrDevice localNonIrDevice;
      if (i < this.rcDevices.myRcDevices.size())
      {
        if (((MyRcDevice)this.rcDevices.myRcDevices.get(i)).nonIrDevice.nonIrDeviceId != paramInt1)
          continue;
        localNonIrDevice = ((MyRcDevice)this.rcDevices.myRcDevices.get(i)).nonIrDevice;
        if (paramInt2 != 2)
          break label130;
      }
      label130: for (boolean bool = false; ; bool = true)
      {
        localNonIrDevice.irCt1Online = bool;
        if ((this.dialog != null) && (this.dialog.isShowing()))
          this.dialog.dismiss();
        this.adapter.notifyDataSetChanged();
        if (paramInt2 != 2)
          this.handler.removeCallbacks(this.checkDeviceStatusDelay);
        return;
      }
    }
  }

  public class ItRcLvAdapter extends BaseAdapter
  {
    int brtTime = 0;
    private Context context;
    private LayoutInflater layoutInflater;
    int sendTime;
    ArrayList<String> wendu = new ArrayList();

    public ItRcLvAdapter(Context arg2)
    {
      Context localContext;
      this.context = localContext;
      this.layoutInflater = LayoutInflater.from(localContext);
      this.wendu.add("16°");
      this.wendu.add("17°");
      this.wendu.add("18°");
      this.wendu.add("19°");
      this.wendu.add("20°");
      this.wendu.add("21°");
      this.wendu.add("22°");
      this.wendu.add("23°");
      this.wendu.add("24°");
      this.wendu.add("25°");
      this.wendu.add("26°");
      this.wendu.add("27°");
      this.wendu.add("28°");
      this.wendu.add("29°");
      this.wendu.add("30°");
      for (int i = 0; i < AtYaokongActivity.this.rcDevices.myRcDevices.size(); i++)
        System.out.println("mName " + ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(i)).mType);
    }

    private void cracySend(byte[] paramArrayOfByte, int paramInt)
    {
      for (int i = 0; i < paramInt; i++)
        AtYaokongActivity.this.handler.postDelayed(new Runnable(paramArrayOfByte)
        {
          public void run()
          {
            SocketManager.instance().sendData(this.val$data);
          }
        }
        , i * 100);
    }

    private void initializeViews(MyRcDevice paramMyRcDevice, ViewHolder paramViewHolder, int paramInt)
    {
      if (paramMyRcDevice.isShowListInfo)
      {
        paramViewHolder.rlSimple.setVisibility(View.GONE);
        switch (paramMyRcDevice.mType)
        {
        default:
          paramViewHolder.rlDoubleWayPanel.setVisibility(View.GONE);
          paramViewHolder.rlComplex.setVisibility(View.VISIBLE);
          paramViewHolder.rlIrCt1.setVisibility(View.GONE);
          paramViewHolder.rlStb.setVisibility(View.GONE);
          paramViewHolder.complexName.setText(paramMyRcDevice.mName);
          paramViewHolder.complexMode1.setOnClickListener(new View.OnClickListener(paramInt, paramMyRcDevice, paramViewHolder)
          {
            public void onClick(View paramView)
            {
              AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), "on");
              if (this.val$vo.mType == 5)
                AtYaokongActivity.this.initAirView(AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1300(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1400(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1500(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1600(this.val$h));
            }
          });
          paramViewHolder.complexMode2.setOnClickListener(new View.OnClickListener(paramMyRcDevice, paramInt, paramViewHolder)
          {
            public void onClick(View paramView)
            {
              if (this.val$vo.mType == 5)
              {
                AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), "mode");
                AtYaokongActivity.this.initAirView(AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1300(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1400(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1500(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1600(this.val$h));
              }
              if (this.val$vo.mType == 8)
                AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), "speed");
              if (this.val$vo.mType == 2)
                AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), "add");
            }
          });
          paramViewHolder.complexMode3.setOnClickListener(new View.OnClickListener(paramMyRcDevice, paramInt, paramViewHolder)
          {
            public void onClick(View paramView)
            {
              if (this.val$vo.mType == 5)
              {
                AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), "speed");
                AtYaokongActivity.this.initAirView(AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1300(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1400(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1500(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1600(this.val$h));
              }
              if (this.val$vo.mType == 8)
                AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), "mode");
              if (this.val$vo.mType == 2)
                AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), "stub");
            }
          });
          paramViewHolder.complexMode4.setOnClickListener(new View.OnClickListener(paramMyRcDevice, paramInt, paramViewHolder)
          {
            public void onClick(View paramView)
            {
              if (this.val$vo.mType == 5)
              {
                AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), "direct");
                AtYaokongActivity.this.initAirView(AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1300(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1400(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1500(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1600(this.val$h));
              }
              if (this.val$vo.mType == 8)
                AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), "direct");
              if (this.val$vo.mType == 2)
                AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), "mute");
            }
          });
          if (paramMyRcDevice.mType != 5)
            break;
          AtYaokongActivity.this.kkACManager = new KKACManagerV2((IrData)((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(paramInt)).irDatas.get(0));
          AtYaokongActivity.this.kkACManager.setACStateV2FromString(((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(paramInt)).acState);
          paramViewHolder.complexWendu.setVisibility(View.VISIBLE);
          paramViewHolder.complexWendu.setData(this.wendu);
          paramViewHolder.complexWendu.setTextCol(-1);
          paramViewHolder.complexWendu.setSelected(paramMyRcDevice.currentWenduPosi);
          AtYaokongActivity.this.initAirView(paramViewHolder.complexWendu, paramViewHolder.complexMode2, paramViewHolder.complexMode3, paramViewHolder.complexMode4);
          paramViewHolder.complexMode1.setBackgroundResource(2130903819);
          paramViewHolder.complexWendu.setOnSelectListener(new MyTimePickerView2.onSelectListener(paramInt, paramViewHolder)
          {
            public void onDown()
            {
              AtYaokongActivity.this.isWenduSeleting = true;
            }

            public void onSelect(String paramString)
            {
              AtYaokongActivity.this.isWenduSeleting = false;
              for (int i = 0; i < AtYaokongActivity.ItRcLvAdapter.this.wendu.size(); i++)
              {
                if (!((String)AtYaokongActivity.ItRcLvAdapter.this.wendu.get(i)).equalsIgnoreCase(paramString))
                  continue;
                ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).currentWenduPosi = i;
              }
              AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), paramString);
              AtYaokongActivity.this.initAirView(AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1300(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1400(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1500(this.val$h), AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$1600(this.val$h));
            }
          });
          if (paramMyRcDevice.mType == 2)
          {
            AtYaokongActivity.this.initTvView(paramViewHolder.complexMode1, paramViewHolder.complexMode2, paramViewHolder.complexMode3, paramViewHolder.complexMode4);
            paramViewHolder.complexWendu.setVisibility(View.GONE);
          }
          switch (paramMyRcDevice.mType)
          {
          case 4:
          case 7:
          default:
            label444: paramViewHolder.complexArrowClick.setOnClickListener(new View.OnClickListener(paramInt, paramViewHolder)
            {
              public void onClick(View paramView)
              {
                ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).isShowListInfo = false;
                AtYaokongActivity.this.hideViewAnimaion(AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$800(this.val$h));
                AtYaokongActivity.this.showViewAnimaion(AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$700(this.val$h));
                AtYaokongActivity.ItRcLvAdapter.this.initializeViews((MyRcDevice)AtYaokongActivity.ItRcLvAdapter.this.getItem(this.val$position), this.val$h, this.val$position);
              }
            });
            paramViewHolder.complexName.setOnClickListener(new View.OnClickListener(paramInt)
            {
              public void onClick(View paramView)
              {
                AtYaokongActivity.this.skip2YkAt((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), this.val$position);
              }
            });
            label477: if (paramMyRcDevice.mType == 5)
              paramViewHolder.iv_wendu_arrow.setVisibility(View.VISIBLE);
          case 2:
          case 3:
          case 1:
          case 8:
          case 6:
          case 5:
          case 9:
          }
        case 10:
        case 11:
        case 12:
        case 1:
        }
      }
      while (true)
      {
        paramViewHolder.tvRefreshOfflineItem1.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            AtYaokongActivity.this.clickOfflineDevicePosi = this.val$position;
            AtYaokongActivity.ItRcLvAdapter localItRcLvAdapter = AtYaokongActivity.ItRcLvAdapter.this;
            CmdDateBussiness localCmdDateBussiness = AtYaokongActivity.this.cmd;
            AtYaokongActivity localAtYaokongActivity = AtYaokongActivity.this;
            int i = ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.nonIrDeviceId;
            localAtYaokongActivity.clickOfflineDeviceId = i;
            localItRcLvAdapter.cracySend(localCmdDateBussiness.getIrCt1OnlineStatus(i), 3);
            AtYaokongActivity.access$2802(AtYaokongActivity.this, new ProgressDialog(AtYaokongActivity.this));
            AtYaokongActivity.this.dialog.setMessage(AtYaokongActivity.this.getString(R.string.get_d_info));
            AtYaokongActivity.this.dialog.setCancelable(false);
            AtYaokongActivity.this.dialog.show();
            AtYaokongActivity.this.handler.postDelayed(AtYaokongActivity.this.checkDeviceStatusDelay, 5500L);
          }
        });
        paramViewHolder.tvRefreshOfflineItem2.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            AtYaokongActivity.this.clickOfflineDevicePosi = this.val$position;
            AtYaokongActivity.ItRcLvAdapter localItRcLvAdapter = AtYaokongActivity.ItRcLvAdapter.this;
            CmdDateBussiness localCmdDateBussiness = AtYaokongActivity.this.cmd;
            AtYaokongActivity localAtYaokongActivity = AtYaokongActivity.this;
            int i = ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.nonIrDeviceId;
            localAtYaokongActivity.clickOfflineDeviceId = i;
            localItRcLvAdapter.cracySend(localCmdDateBussiness.getIrCt1OnlineStatus(i), 3);
            AtYaokongActivity.access$2802(AtYaokongActivity.this, new ProgressDialog(AtYaokongActivity.this));
            AtYaokongActivity.this.dialog.setMessage(AtYaokongActivity.this.getString(R.string.get_d_info));
            AtYaokongActivity.this.dialog.setCancelable(false);
            AtYaokongActivity.this.dialog.show();
            AtYaokongActivity.this.handler.postDelayed(AtYaokongActivity.this.checkDeviceStatusDelay, 5500L);
          }
        });
        return;
        paramViewHolder.rlDoubleWayPanel.setVisibility(View.VISIBLE);
        paramViewHolder.rlComplex.setVisibility(View.GONE);
        paramViewHolder.rlIrCt1.setVisibility(View.GONE);
        paramViewHolder.rlStb.setVisibility(View.GONE);
        paramViewHolder.doubleWayPanelTb.setVisibility(View.GONE);
        paramViewHolder.doubleWayPanelIvIc.setBackgroundResource(2130903411);
        paramViewHolder.doubleWayPanelTvOne.setVisibility(4);
        paramViewHolder.doubleWayPanelTvTwo.setVisibility(4);
        paramViewHolder.tvOnoffOn2.setVisibility(4);
        paramViewHolder.tvOnoffOff2.setVisibility(4);
        paramViewHolder.doubleWayPanelName.setText(paramMyRcDevice.mName);
        paramViewHolder.tvOnoffOn1.setOnTouchListener(new SwitchOnTouchListener(paramViewHolder.tvOnoffOn1, true, 1, paramInt));
        paramViewHolder.tvOnoffOff1.setOnTouchListener(new SwitchOnTouchListener(paramViewHolder.tvOnoffOff1, false, 2, paramInt));
        paramViewHolder.tvOnoffOn1.setTextColor(Color.parseColor("#FFFFFF"));
        paramViewHolder.tvOnoffOff1.setTextColor(Color.parseColor("#FFFFFF"));
        break label477;
        paramViewHolder.rlDoubleWayPanel.setVisibility(View.VISIBLE);
        paramViewHolder.rlComplex.setVisibility(View.GONE);
        paramViewHolder.rlStb.setVisibility(View.GONE);
        paramViewHolder.rlIrCt1.setVisibility(View.GONE);
        paramViewHolder.doubleWayPanelName.setText(paramMyRcDevice.mName);
        paramViewHolder.doubleWayPanelTb.setVisibility(View.GONE);
        paramViewHolder.doubleWayPanelIvIc.setBackgroundResource(2130903412);
        paramViewHolder.doubleWayPanelTvOne.setVisibility(View.VISIBLE);
        paramViewHolder.doubleWayPanelTvTwo.setVisibility(View.VISIBLE);
        paramViewHolder.tvOnoffOn2.setVisibility(View.VISIBLE);
        paramViewHolder.tvOnoffOff2.setVisibility(View.VISIBLE);
        paramViewHolder.tvOnoffOn1.setOnTouchListener(new SwitchOnTouchListener(paramViewHolder.tvOnoffOn1, true, 1, paramInt));
        paramViewHolder.tvOnoffOff1.setOnTouchListener(new SwitchOnTouchListener(paramViewHolder.tvOnoffOff1, false, 2, paramInt));
        paramViewHolder.tvOnoffOn2.setOnTouchListener(new SwitchOnTouchListener(paramViewHolder.tvOnoffOn2, true, 3, paramInt));
        paramViewHolder.tvOnoffOff2.setOnTouchListener(new SwitchOnTouchListener(paramViewHolder.tvOnoffOff2, false, 4, paramInt));
        paramViewHolder.tvOnoffOn1.setTextColor(Color.parseColor("#FFFFFF"));
        paramViewHolder.tvOnoffOff1.setTextColor(Color.parseColor("#FFFFFF"));
        paramViewHolder.tvOnoffOn2.setTextColor(Color.parseColor("#FFFFFF"));
        paramViewHolder.tvOnoffOff2.setTextColor(Color.parseColor("#FFFFFF"));
        break label477;
        TextView localTextView2 = paramViewHolder.tvRefreshOfflineItem1;
        int j;
        if (((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(paramInt)).nonIrDevice.irCt1Online)
        {
          j = 8;
          label972: localTextView2.setVisibility(j);
          paramViewHolder.rlStb.setVisibility(View.GONE);
          paramViewHolder.rlDoubleWayPanel.setVisibility(View.GONE);
          paramViewHolder.rlComplex.setVisibility(View.GONE);
          paramViewHolder.rlIrCt1.setVisibility(View.VISIBLE);
          paramViewHolder.rlIrCt1Name.setText(paramMyRcDevice.mName);
          if (!paramMyRcDevice.nonIrDevice.irCt1Onoff)
            break label1213;
          paramViewHolder.irCt1Tb.setToggleOn();
          paramViewHolder.irCt1Sb.setEnabled(true);
          paramViewHolder.irCt1Sb.setThumbColor(-1, -1);
          paramViewHolder.irCt1Sb.setScrubberColor(-1);
          paramViewHolder.tvBrt.setBackgroundResource(2130903362);
        }
        while (true)
        {
          paramViewHolder.irCt1Tb.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView(paramViewHolder, paramInt)
          {
            public void onToggleInListView(boolean paramBoolean, int paramInt)
            {
              this.val$h.irCt1Sb.setEnabled(paramBoolean);
              if (paramBoolean)
              {
                this.val$h.irCt1Sb.setThumbColor(-1, -1);
                this.val$h.irCt1Sb.setScrubberColor(-1);
                this.val$h.tvBrt.setBackgroundResource(2130903362);
              }
              while (true)
              {
                ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Onoff = paramBoolean;
                AtYaokongActivity.ItRcLvAdapter.this.cracySend(AtYaokongActivity.this.cmd.controlIrCtLight(((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.nonIrDeviceId, 1, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Onoff, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Brt, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1C, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1W), 3);
                MyDb.getInstance(AtYaokongActivity.this.getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, AtYaokongActivity.this.rcDevices);
                return;
                this.val$h.irCt1Sb.setThumbColor(Color.parseColor("#c8B6B6B6"), Color.parseColor("#c8B6B6B6"));
                this.val$h.irCt1Sb.setScrubberColor(Color.parseColor("#c8B6B6B6"));
                this.val$h.tvBrt.setBackgroundResource(2130903361);
              }
            }
          });
          paramViewHolder.rlIrCt1Arrow.setOnClickListener(new View.OnClickListener(paramInt, paramViewHolder)
          {
            public void onClick(View paramView)
            {
              ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).isShowListInfo = false;
              AtYaokongActivity.this.hideViewAnimaion(this.val$h.rlIrCt1);
              AtYaokongActivity.this.showViewAnimaion(AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$700(this.val$h));
              AtYaokongActivity.ItRcLvAdapter.this.initializeViews((MyRcDevice)AtYaokongActivity.ItRcLvAdapter.this.getItem(this.val$position), this.val$h, this.val$position);
            }
          });
          paramViewHolder.irCt1Sb.setOnProgressChangeListener(null);
          paramViewHolder.irCt1Sb.setProgress(paramMyRcDevice.nonIrDevice.irCt1BtrProgrees);
          paramViewHolder.tvBrtPrecent.setText(paramViewHolder.irCt1Sb.getProgress() + "%");
          if (paramViewHolder.irCt1Sb.getProgress() < 2)
            paramViewHolder.tvBrtPrecent.setText("2%");
          paramViewHolder.irCt1Sb.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener(paramInt, paramViewHolder)
          {
            public void onProgressChanged(DiscreteSeekBar paramDiscreteSeekBar, int paramInt, boolean paramBoolean)
            {
              ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Brt = (255 * paramDiscreteSeekBar.getProgress() / 100);
              this.val$h.tvBrtPrecent.setText(paramDiscreteSeekBar.getProgress() + "%");
              if (this.val$h.irCt1Sb.getProgress() < 2)
                this.val$h.tvBrtPrecent.setText("2%");
              if (((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Brt < 5)
                ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Brt = 5;
              ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1BtrProgrees = paramDiscreteSeekBar.getProgress();
              AtYaokongActivity.ItRcLvAdapter.this.fillterSend(AtYaokongActivity.this.cmd.controlIrCtLight(((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.nonIrDeviceId, 2, true, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Brt, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1C, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1W), 3);
            }

            public void onStartTrackingTouch(DiscreteSeekBar paramDiscreteSeekBar)
            {
            }

            public void onStopTrackingTouch(DiscreteSeekBar paramDiscreteSeekBar)
            {
              ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Brt = (255 * paramDiscreteSeekBar.getProgress() / 100);
              if (((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Brt < 5)
                ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Brt = 5;
              ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1BtrProgrees = paramDiscreteSeekBar.getProgress();
              AtYaokongActivity.ItRcLvAdapter.this.cracySend(AtYaokongActivity.this.cmd.controlIrCtLight(((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.nonIrDeviceId, 2, true, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Brt, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1C, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1W), 3);
              this.val$h.irCt1Tb.setToggleOn();
            }
          });
          break;
          j = 0;
          break label972;
          label1213: paramViewHolder.irCt1Tb.setToggleOff();
          paramViewHolder.irCt1Sb.setEnabled(false);
          paramViewHolder.irCt1Sb.setThumbColor(Color.parseColor("#c8B6B6B6"), Color.parseColor("#c8B6B6B6"));
          paramViewHolder.irCt1Sb.setScrubberColor(Color.parseColor("#c8B6B6B6"));
          paramViewHolder.tvBrt.setBackgroundResource(2130903361);
        }
        paramViewHolder.rlStb.setVisibility(View.VISIBLE);
        paramViewHolder.rlDoubleWayPanel.setVisibility(View.GONE);
        paramViewHolder.rlComplex.setVisibility(View.GONE);
        paramViewHolder.rlIrCt1.setVisibility(View.GONE);
        paramViewHolder.stbName.setText(paramMyRcDevice.mName);
        paramViewHolder.stbChAdd.setOnClickListener(new stbClickListener(paramInt));
        paramViewHolder.stbChMinus.setOnClickListener(new stbClickListener(paramInt));
        paramViewHolder.stbVolAdd.setOnClickListener(new stbClickListener(paramInt));
        paramViewHolder.stbVolMinus.setOnClickListener(new stbClickListener(paramInt));
        paramViewHolder.stbArrowClick.setOnClickListener(new View.OnClickListener(paramInt, paramViewHolder)
        {
          public void onClick(View paramView)
          {
            ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).isShowListInfo = false;
            AtYaokongActivity.this.hideViewAnimaion(this.val$h.rlStb);
            AtYaokongActivity.this.showViewAnimaion(AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$700(this.val$h));
            AtYaokongActivity.ItRcLvAdapter.this.initializeViews((MyRcDevice)AtYaokongActivity.ItRcLvAdapter.this.getItem(this.val$position), this.val$h, this.val$position);
          }
        });
        break label477;
        if (paramMyRcDevice.mType != 8)
          break;
        AtYaokongActivity.this.initFanView(paramViewHolder.complexMode1, paramViewHolder.complexMode2, paramViewHolder.complexMode3, paramViewHolder.complexMode4);
        paramViewHolder.complexWendu.setVisibility(View.GONE);
        break;
        paramViewHolder.complexIvIc.setBackgroundResource(2130903581);
        paramViewHolder.rlComplex.setBackgroundColor(Color.parseColor("#FFFD7A7F"));
        break label444;
        paramViewHolder.complexIvIc.setBackgroundResource(2130903526);
        break label444;
        paramViewHolder.complexIvIc.setBackgroundResource(2130903572);
        break label444;
        paramViewHolder.complexIvIc.setBackgroundResource(2130903556);
        paramViewHolder.rlComplex.setBackgroundColor(Color.parseColor("#FF25D498"));
        break label444;
        paramViewHolder.complexIvIc.setBackgroundResource(2130903564);
        break label444;
        paramViewHolder.complexIvIc.setBackgroundResource(2130903524);
        paramViewHolder.rlComplex.setBackgroundColor(Color.parseColor("#FF51C2FC"));
        break label444;
        paramViewHolder.complexIvIc.setBackgroundResource(2130903534);
        break label444;
        paramViewHolder.rlSimple.setVisibility(View.VISIBLE);
        paramViewHolder.rlDoubleWayPanel.setVisibility(View.GONE);
        paramViewHolder.rlComplex.setVisibility(View.GONE);
        paramViewHolder.rlIrCt1.setVisibility(View.GONE);
        paramViewHolder.rlStb.setVisibility(View.GONE);
        paramViewHolder.name.setText(paramMyRcDevice.mName);
        paramViewHolder.irCtTb.setVisibility(View.GONE);
        paramViewHolder.tvRefreshOfflineItem2.setVisibility(View.GONE);
        switch (paramMyRcDevice.mType)
        {
        case 4:
        case 7:
        case 10:
        case 11:
        default:
        case 2:
        case 3:
        case 1:
        case 8:
        case 6:
        case 5:
        case 9:
          while (true)
          {
            paramViewHolder.arrowClick.setOnClickListener(new View.OnClickListener(paramMyRcDevice, paramInt, paramViewHolder)
            {
              public void onClick(View paramView)
              {
                if ((this.val$vo.mType == 5) || (this.val$vo.mType == 2) || (this.val$vo.mType == 8))
                {
                  ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).isShowListInfo = true;
                  AtYaokongActivity.ItRcLvAdapter.this.initializeViews((MyRcDevice)AtYaokongActivity.ItRcLvAdapter.this.getItem(this.val$position), this.val$h, this.val$position);
                  AtYaokongActivity.this.showViewAnimaion(AtYaokongActivity.ItRcLvAdapter.ViewHolder.access$800(this.val$h));
                  return;
                }
                if (this.val$vo.mType == 12)
                {
                  ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).isShowListInfo = true;
                  AtYaokongActivity.ItRcLvAdapter.this.initializeViews((MyRcDevice)AtYaokongActivity.ItRcLvAdapter.this.getItem(this.val$position), this.val$h, this.val$position);
                  AtYaokongActivity.this.showViewAnimaion(this.val$h.rlIrCt1);
                  return;
                }
                if (this.val$vo.mType == 1)
                {
                  ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).isShowListInfo = true;
                  AtYaokongActivity.ItRcLvAdapter.this.initializeViews((MyRcDevice)AtYaokongActivity.ItRcLvAdapter.this.getItem(this.val$position), this.val$h, this.val$position);
                  AtYaokongActivity.this.showViewAnimaion(this.val$h.rlStb);
                  return;
                }
                AtYaokongActivity.this.skip2YkAt((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position), this.val$position);
              }
            });
            break;
            paramViewHolder.ivIc.setBackgroundResource(2130903574);
            paramViewHolder.arrow.setBackgroundResource(2130903552);
            continue;
            paramViewHolder.ivIc.setBackgroundResource(2130903526);
            paramViewHolder.arrow.setBackgroundResource(2130903565);
            continue;
            paramViewHolder.ivIc.setBackgroundResource(2130903572);
            paramViewHolder.arrow.setBackgroundResource(2130903552);
            continue;
            paramViewHolder.ivIc.setBackgroundResource(2130903555);
            paramViewHolder.arrow.setBackgroundResource(2130903552);
            continue;
            paramViewHolder.ivIc.setBackgroundResource(2130903564);
            paramViewHolder.arrow.setBackgroundResource(2130903565);
            continue;
            paramViewHolder.ivIc.setBackgroundResource(2130903523);
            paramViewHolder.arrow.setBackgroundResource(2130903552);
            continue;
            paramViewHolder.ivIc.setBackgroundResource(2130903534);
            paramViewHolder.arrow.setBackgroundResource(2130903565);
          }
        case 12:
        }
        TextView localTextView1 = paramViewHolder.tvRefreshOfflineItem2;
        int i;
        if (((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(paramInt)).nonIrDevice.irCt1Online)
        {
          i = 8;
          label1934: localTextView1.setVisibility(i);
          paramViewHolder.irCtTb.setVisibility(View.VISIBLE);
          paramViewHolder.ivIc.setBackgroundResource(2130903403);
          paramViewHolder.arrow.setBackgroundResource(2130903552);
          if (!paramMyRcDevice.nonIrDevice.irCt1Onoff)
            break label2011;
          paramViewHolder.irCtTb.setToggleOn();
        }
        while (true)
        {
          paramViewHolder.irCtTb.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView(paramInt)
          {
            public void onToggleInListView(boolean paramBoolean, int paramInt)
            {
              ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Onoff = paramBoolean;
              AtYaokongActivity.ItRcLvAdapter.this.cracySend(AtYaokongActivity.this.cmd.controlIrCtLight(((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.nonIrDeviceId, 1, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Onoff, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1Brt, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1C, ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.val$position)).nonIrDevice.irCt1W), 3);
            }
          });
          break;
          i = 0;
          break label1934;
          label2011: paramViewHolder.irCtTb.setToggleOff();
        }
        paramViewHolder.iv_wendu_arrow.setVisibility(View.GONE);
      }
    }

    private void onOffTextBgChange(TextView paramTextView, boolean paramBoolean)
    {
      if (paramBoolean);
      try
      {
        paramTextView.setTextColor(Color.parseColor("#4dbdff"));
        paramTextView.setBackgroundDrawable(AtYaokongActivity.this.getResources().getDrawable(2130837661));
        return;
        paramTextView.setTextColor(Color.parseColor("#FFFFFF"));
        paramTextView.setBackgroundDrawable(AtYaokongActivity.this.getResources().getDrawable(2130837662));
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }

    protected void fillterSend(byte[] paramArrayOfByte, int paramInt)
    {
      this.sendTime = (1 + this.sendTime);
      if (this.sendTime % paramInt == 0)
        SocketManager.instance().sendData(paramArrayOfByte);
    }

    public int getCount()
    {
      return AtYaokongActivity.this.rcDevices.myRcDevices.size();
    }

    public Object getItem(int paramInt)
    {
      return AtYaokongActivity.this.rcDevices.myRcDevices.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968809, null);
        paramView.setTag(new ViewHolder(paramView));
      }
      initializeViews((MyRcDevice)getItem(paramInt), (ViewHolder)paramView.getTag(), paramInt);
      return paramView;
    }

    class SwitchOnTouchListener
      implements View.OnTouchListener
    {
      int listItemPosi;
      boolean onOff;
      int onOffNum;
      TextView onoff;

      public SwitchOnTouchListener(TextView paramBoolean, boolean paramInt1, int paramInt2, int arg5)
      {
        this.onoff = paramBoolean;
        this.onOff = paramInt1;
        this.onOffNum = paramInt2;
        int i;
        this.listItemPosi = i;
      }

      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        switch (paramMotionEvent.getAction())
        {
        case 2:
        default:
          return true;
        case 0:
          AtYaokongActivity.ItRcLvAdapter.this.onOffTextBgChange(this.onoff, true);
          return true;
        case 1:
        case 3:
        }
        AtYaokongActivity.ItRcLvAdapter.this.onOffTextBgChange(this.onoff, false);
        AtYaokongActivity.this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            switch (AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.onOffNum)
            {
            default:
              return;
            case 1:
              ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.listItemPosi)).nonIrDevice.irPanelSwitch1 = AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.onOff;
              AtYaokongActivity.ItRcLvAdapter.this.cracySend(AtYaokongActivity.this.cmd.onOffK1RFAndK2RF(((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.listItemPosi)).nonIrDevice.nonIrDeviceId, 226), 3);
              return;
            case 2:
              ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.listItemPosi)).nonIrDevice.irPanelSwitch1 = AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.onOff;
              AtYaokongActivity.ItRcLvAdapter.this.cracySend(AtYaokongActivity.this.cmd.onOffK1RFAndK2RF(((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.listItemPosi)).nonIrDevice.nonIrDeviceId, 194), 3);
              return;
            case 3:
              ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.listItemPosi)).nonIrDevice.irPanelSwitch2 = AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.onOff;
              AtYaokongActivity.ItRcLvAdapter.this.cracySend(AtYaokongActivity.this.cmd.onOffK1RFAndK2RF(((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.listItemPosi)).nonIrDevice.nonIrDeviceId, 227), 3);
              return;
            case 4:
            }
            ((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.listItemPosi)).nonIrDevice.irPanelSwitch2 = AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.onOff;
            AtYaokongActivity.ItRcLvAdapter.this.cracySend(AtYaokongActivity.this.cmd.onOffK1RFAndK2RF(((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(AtYaokongActivity.ItRcLvAdapter.SwitchOnTouchListener.this.listItemPosi)).nonIrDevice.nonIrDeviceId, 195), 3);
          }
        }
        , 200L);
        return true;
      }
    }

    class ViewHolder
    {
      private ImageView arrow;
      private ImageView arrowClick;
      private ImageView complexArrow;
      private ImageView complexArrowClick;
      private ImageView complexIvIc;
      private Button complexMode1;
      private Button complexMode2;
      private Button complexMode3;
      private Button complexMode4;
      private TextView complexName;
      private MyTimePickerView2 complexWendu;

      @Bind({2131559326})
      ImageView doubleWayPanelIvIc;

      @Bind({2131559327})
      TextView doubleWayPanelName;

      @Bind({2131559328})
      ToggleButton doubleWayPanelTb;

      @Bind({2131559330})
      TextView doubleWayPanelTvOne;

      @Bind({2131559333})
      TextView doubleWayPanelTvTwo;

      @Bind({2131559341})
      DiscreteSeekBar irCt1Sb;

      @Bind({2131559339})
      ToggleButton irCt1Tb;

      @Bind({2131559318})
      ToggleButton irCtTb;
      private ImageView ivIc;
      private ImageView iv_wendu_arrow;
      private TextView name;
      private RelativeLayout rlComplex;

      @Bind({2131559325})
      RelativeLayout rlDoubleWayPanel;

      @Bind({2131559335})
      RelativeLayout rlIrCt1;

      @Bind({2131559337})
      ImageView rlIrCt1Arrow;

      @Bind({2131559338})
      ImageView rlIrCt1Click;

      @Bind({2131559336})
      TextView rlIrCt1Name;
      private RelativeLayout rlSimple;

      @Bind({2131559343})
      RelativeLayout rlStb;

      @Bind({2131559346})
      ImageView stbArrow;

      @Bind({2131559347})
      ImageView stbArrowClick;

      @Bind({2131559348})
      TextView stbChAdd;

      @Bind({2131559349})
      TextView stbChMinus;

      @Bind({2131559344})
      ImageView stbIvIc;

      @Bind({2131559345})
      TextView stbName;

      @Bind({2131559350})
      TextView stbVolAdd;

      @Bind({2131559351})
      TextView stbVolMinus;

      @Bind({2131559340})
      ImageView tvBrt;

      @Bind({2131558653})
      TextView tvBrtPrecent;

      @Bind({2131559331})
      TextView tvOnoffOff1;

      @Bind({2131559334})
      TextView tvOnoffOff2;

      @Bind({2131559329})
      TextView tvOnoffOn1;

      @Bind({2131559332})
      TextView tvOnoffOn2;

      @Bind({2131559342})
      TextView tvRefreshOfflineItem1;

      @Bind({2131559320})
      TextView tvRefreshOfflineItem2;

      public ViewHolder(View arg2)
      {
        View localView;
        this.rlSimple = ((RelativeLayout)localView.findViewById(2131559317));
        this.ivIc = ((ImageView)localView.findViewById(2131559290));
        this.name = ((TextView)localView.findViewById(2131559008));
        this.arrow = ((ImageView)localView.findViewById(2131559297));
        this.arrowClick = ((ImageView)localView.findViewById(2131559319));
        this.iv_wendu_arrow = ((ImageView)localView.findViewById(2131559283));
        this.rlComplex = ((RelativeLayout)localView.findViewById(2131559321));
        this.complexIvIc = ((ImageView)localView.findViewById(2131559322));
        this.complexName = ((TextView)localView.findViewById(2131559323));
        this.complexArrow = ((ImageView)localView.findViewById(2131559277));
        this.complexArrowClick = ((ImageView)localView.findViewById(2131559324));
        this.complexMode1 = ((Button)localView.findViewById(2131559278));
        this.complexMode2 = ((Button)localView.findViewById(2131559279));
        this.complexMode3 = ((Button)localView.findViewById(2131559280));
        this.complexMode4 = ((Button)localView.findViewById(2131559281));
        this.complexWendu = ((MyTimePickerView2)localView.findViewById(2131559282));
        ButterKnife.bind(this, localView);
      }
    }

    class stbClickListener
      implements View.OnClickListener
    {
      int listItemPosi;

      public stbClickListener(int arg2)
      {
        int i;
        this.listItemPosi = i;
      }

      public void onClick(View paramView)
      {
        if (paramView.getId() == 2131559348)
          AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.listItemPosi), "ch_add");
        if (paramView.getId() == 2131559349)
          AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.listItemPosi), "ch_minus");
        if (paramView.getId() == 2131559350)
        {
          AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.listItemPosi), "vol_add");
          return;
        }
        AtYaokongActivity.this.sendRcKey((MyRcDevice)AtYaokongActivity.this.rcDevices.myRcDevices.get(this.listItemPosi), "vol_minus");
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

  class SceneAdapter extends BaseAdapter
  {
    private Context context;
    private LayoutInflater layoutInflater;

    public SceneAdapter(Context arg2)
    {
      Context localContext;
      this.context = localContext;
      this.layoutInflater = LayoutInflater.from(localContext);
    }

    private void initializeViews(SceneVo paramSceneVo, ViewHolder paramViewHolder, int paramInt)
    {
      if (paramSceneVo.getSenceIcType().equals("outHome"))
        paramViewHolder.ivIc.setImageBitmap(BitmapFactory.decodeResource(AtYaokongActivity.this.getResources(), 2130903647));
      while (true)
      {
        paramViewHolder.tvName.setText(paramSceneVo.getName());
        paramViewHolder.ivIc.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            AtYaokongActivity.this.sceneBusiness.send(AtYaokongActivity.this.sceneVos, this.val$position);
          }
        });
        return;
        if (paramSceneVo.getSenceIcType().equals("goHome"))
        {
          paramViewHolder.ivIc.setImageBitmap(BitmapFactory.decodeResource(AtYaokongActivity.this.getResources(), 2130903560));
          continue;
        }
        if (paramSceneVo.getSenceIcType().equals("sleep"))
        {
          paramViewHolder.ivIc.setImageBitmap(BitmapFactory.decodeResource(AtYaokongActivity.this.getResources(), 2130903649));
          continue;
        }
        if (paramSceneVo.getSenceIcType().equals("wakeup"))
        {
          paramViewHolder.ivIc.setImageBitmap(BitmapFactory.decodeResource(AtYaokongActivity.this.getResources(), 2130903648));
          continue;
        }
        try
        {
          Bitmap localBitmap = BitmapFactory.decodeFile(paramSceneVo.getPicPath());
          if (localBitmap == null)
            break label197;
          paramViewHolder.ivIc.setImageBitmap(localBitmap);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        continue;
        label197: paramViewHolder.ivIc.setImageBitmap(BitmapFactory.decodeResource(AtYaokongActivity.this.getResources(), 2130903560));
      }
    }

    public int getCount()
    {
      return AtYaokongActivity.this.sceneVos.sceneVos.size();
    }

    public SceneVo getItem(int paramInt)
    {
      return (SceneVo)AtYaokongActivity.this.sceneVos.sceneVos.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968813, null);
        ViewHolder localViewHolder = new ViewHolder();
        ViewHolder.access$2902(localViewHolder, (MLImageView)paramView.findViewById(R.id.iv));
        ViewHolder.access$3002(localViewHolder, (TextView)paramView.findViewById(2131559008));
        paramView.setTag(localViewHolder);
      }
      if (paramView.getMeasuredHeight() > 0)
      {
        AtYaokongActivity.this.sceneBusiness.saveGvItWidthHeight(paramView.getMeasuredWidth(), paramView.getMeasuredHeight());
        System.out.println(paramView.getMeasuredWidth() + "$%^&*()*&()              " + paramInt + "           " + paramView.getMeasuredHeight());
      }
      initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag(), paramInt);
      return paramView;
    }

    protected class ViewHolder
    {
      private MLImageView ivIc;
      private TextView tvName;

      protected ViewHolder()
      {
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYaokongActivity
 * JD-Core Version:    0.6.0
 */