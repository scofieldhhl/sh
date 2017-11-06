package com.ex.ltech.onepiontfive.main.room.panel;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.my_view.AlertDialog105DeviceEdit;
import com.ex.ltech.led.my_view.AlertDialog105DeviceEdit.MyOnClickListener;
import com.ex.ltech.led.my_view.MyAlertDialog;
import com.ex.ltech.led.my_view.MyAlertDialog12;
import com.ex.ltech.led.my_view.MyAlertDialog12.MyOnClickListener;
import com.ex.ltech.led.my_view.MyEditAlertDialog5;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.utils.UtilMath;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.room.FtRooms;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.PanelLampVO;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import com.soundcloud.android.crop.Crop;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FtPanel extends MyBaseFt
  implements View.OnClickListener, PanelListAdt.Callback
{
  Runnable Panel1NameRunnable;
  Runnable Panel2NameRunnable;
  Runnable Panel3NameRunnable;
  Runnable Panel4NameRunnable;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  private PanelBussiness bussiness;
  private int clickPosition;
  public MyEditAlertDialog5 dialog;
  private Map<Integer, String> dvcInMIndex;
  private List effectLights = new ArrayList();
  public SharedPreferences getter;
  Handler handler = new Handler(Looper.myLooper());
  private String imgPath = "";
  private int index;
  boolean isGetPanelInfoOk;
  PanelListAdt lampAdapter;
  private String lastReturnData = "";

  @Bind({2131558585})
  SwipeMenuListView lv;
  private String mac;
  List<PanelLampVO> panelLampVO;
  private int roomNum;
  public int selectItem = 0;
  private PanelBussiness tempPanelBussiness;
  private List<PanelLampVO> tempPnelLampVOs;
  Runnable timeout = new Runnable()
  {
    public void run()
    {
      if (FtPanel.this.dialog.isShowing())
        FtPanel.this.dialog.dismiss();
    }
  };

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view = null;

  private void init()
  {
    this.mac = UserFerences.getUserFerences(getActivity()).getValue("GateWayMacIdKey");
    this.roomNum = getRequest().getIntExtra("DRoomNumKey", 0);
    this.clickPosition = getRequest().getIntExtra("DClickPosiKey", 0);
    this.index = getRequest().getIntExtra("DIndexKey", 0);
    this.bussiness = new PanelBussiness(getActivity(), this.roomNum, this.clickPosition, this.index);
    this.panelLampVO = this.bussiness.initDataTemp();
    this.bussiness.initData();
    ArrayList localArrayList = ((Room)this.bussiness.home.getRooms().get(this.roomNum)).getDvcVos();
    this.dvcInMIndex = new HashMap();
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      Dvc localDvc = (Dvc)localIterator.next();
      this.dvcInMIndex.put(Integer.valueOf(localDvc.getmIndex()), localDvc.getName());
    }
    syncDeviceCurrentInfo();
    this.lampAdapter = new PanelListAdt(getActivity(), this.panelLampVO, this, this.bussiness, this.bussiness.getPanelWays());
  }

  private void initListener()
  {
    panelOperateListener();
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
      }
    }
    , 50L);
  }

  private void loopGetBranchName()
  {
    Handler localHandler1 = this.handler;
    19 local19 = new Runnable()
    {
      public void run()
      {
        FtPanel.this.bussiness.queryName(Integer.parseInt("80", 16), 34, FtPanel.this.index, "1".getBytes(), false);
      }
    };
    this.Panel1NameRunnable = local19;
    localHandler1.postDelayed(local19, 50L);
    Handler localHandler2 = this.handler;
    20 local20 = new Runnable()
    {
      public void run()
      {
        FtPanel.this.bussiness.queryName(Integer.parseInt("81", 16), 34, FtPanel.this.index, "1".getBytes(), false);
      }
    };
    this.Panel2NameRunnable = local20;
    localHandler2.postDelayed(local20, 100L);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        FtPanel.this.bussiness.queryName(Integer.parseInt("82", 16), 34, FtPanel.this.index, "1".getBytes(), false);
      }
    }
    , 150L);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        FtPanel.this.bussiness.queryName(Integer.parseInt("83", 16), 34, FtPanel.this.index, "1".getBytes(), false);
      }
    }
    , 200L);
  }

  private void resetData()
  {
    this.tempPanelBussiness = new PanelBussiness(getActivity(), getRequest().getIntExtra("DRoomNumKey", 0), getRequest().getIntExtra("DClickPosiKey", 0), getRequest().getIntExtra("DIndexKey", 0));
    this.tempPnelLampVOs = this.tempPanelBussiness.initDataTemp();
    this.bussiness.panelLampVos.clear();
    this.bussiness.panelLampVos.addAll(this.tempPnelLampVOs);
  }

  public void initTitleView()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131100407);
    this.tvTitleViewTitle.setText(FtRooms.seletedDvc.getName());
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent("PanelOnOffBroadCast");
        localIntent.putExtra("DClickPosiKey", FtPanel.this.clickPosition);
        boolean bool = false;
        for (int i = 0; i < FtPanel.this.bussiness.getPanelWays(); i++)
        {
          if (!((PanelLampVO)FtPanel.this.panelLampVO.get(i)).isOn())
            continue;
          bool = true;
        }
        localIntent.putExtra("PanelOnOffKey", bool);
        LocalBroadcastManager.getInstance(FtPanel.this.getActivity()).sendBroadcast(localIntent);
        FtPanel.this.finish();
      }
    });
    this.tvTitleViewEdit.setVisibility(View.GONE);
    this.tvTitleViewEdit.setText(2131100358);
    this.tvTitleViewEdit.setTextColor(getResources().getColor(2131492897));
    this.tvTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (FtPanel.this.tvTitleViewEdit.getText().toString().equals(FtPanel.this.getString(2131100358)))
        {
          FtPanel.this.bussiness.saveData(FtPanel.this.panelLampVO);
          FtPanel.this.finish();
        }
        if (FtPanel.this.tvTitleViewEdit.getText().toString().equals(FtPanel.this.getString(R.string.finish)))
        {
          FtPanel.this.bussiness.onHideRelationControl();
          FtPanel.this.bussiness.saveBug(313, FtPanel.this.lampAdapter.getCount());
          FtPanel.this.lampAdapter.notifyDataSetChanged();
          FtPanel.this.tvTitleViewEdit.setText(2131100358);
        }
      }
    });
  }

  public void initView()
  {
    this.lv.setAdapter(this.lampAdapter);
    this.lv.setDividerHeight(BitmapUtils.dp2px(getActivity(), 10.0F));
    this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        Toast.makeText(FtPanel.this.getActivity().getApplication(), "item click", 1).show();
      }
    });
    this.lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2)
      {
        MyAlertDialog localMyAlertDialog = new MyAlertDialog(FtPanel.this.getActivity());
        localMyAlertDialog.show();
        localMyAlertDialog.setTitle(R.string.tips);
        localMyAlertDialog.setMsg(2131100025);
        localMyAlertDialog.getWindow().clearFlags(131080);
        localMyAlertDialog.getWindow().setSoftInputMode(4);
        localMyAlertDialog.rv_my_alertdialog_cancle.setOnClickListener(new View.OnClickListener(localMyAlertDialog)
        {
          public void onClick(View paramView)
          {
            this.val$dialog.dismiss();
          }
        });
        localMyAlertDialog.rv_my_alertdialog_ok.setOnClickListener(new View.OnClickListener(localMyAlertDialog)
        {
          public void onClick(View paramView)
          {
            this.val$dialog.dismiss();
          }
        });
        return false;
      }
    });
  }

  @TargetApi(15)
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 1) && (paramInt2 == -1))
    {
      this.dialog.dismiss();
      this.bussiness.setBgView(getActivity(), this.dialog.ll_takephoto, this.selectItem, new PanelBussiness.OnPictrueListener()
      {
        public void onPictrueOk(String paramString)
        {
          FtPanel.this.bussiness.savePanelBranchImgPath(FtPanel.this.selectItem, paramString);
          FtPanel.this.lampAdapter.notifyDataSetChanged();
        }
      });
    }
    if ((paramInt1 == 9162) && (paramInt2 == -1))
      this.bussiness.beginCrop(this, getActivity(), paramIntent.getData());
    do
      return;
    while ((paramInt1 != 6709) || (paramInt2 != -1));
    this.dialog.dismiss();
    this.bussiness.handleCrop(getActivity(), paramIntent, this.dialog.ll_album, new PanelBussiness.OnPictrueListener()
    {
      public void onPictrueOk(String paramString)
      {
        FtPanel.this.bussiness.savePanelBranchImgPath(FtPanel.this.selectItem, paramString);
        FtPanel.this.lampAdapter.notifyDataSetChanged();
      }
    });
  }

  public void onClick(View paramView)
  {
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
      this.view = paramLayoutInflater.inflate(2130968753, null);
    ButterKnife.bind(this, this.view);
    setSlideable(false);
    init();
    initView();
    initTitleView();
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    System.out.println("ftpanel destroyView");
    this.bussiness.setMySendListener(null);
    this.handler.removeCallbacks(this.timeout);
    ButterKnife.unbind(this);
  }

  public void onEdit(int paramInt)
  {
    this.selectItem = paramInt;
    this.bussiness.putCacheData();
    this.dialog = new MyEditAlertDialog5(getActivity());
    this.dialog.show();
    this.dialog.getWindow().clearFlags(131080);
    this.dialog.getWindow().setSoftInputMode(4);
    this.dialog.rv_my_edit_alertdialog_cancle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtPanel.this.dialog.dismiss();
      }
    });
    this.dialog.rv_my_edit_alertdialog_ok.setOnClickListener(new View.OnClickListener(paramInt)
    {
      public void onClick(View paramView)
      {
        FtPanel.this.setResult(400);
        String str = FtPanel.this.dialog.et_my_edit_alertdialog.getText().toString();
        if ((!str.equals("")) || ((!FtPanel.this.imgPath.equals("")) && (((PanelLampVO)FtPanel.this.panelLampVO.get(this.val$posi)).getImgPath() != FtPanel.this.imgPath)))
          FtPanel.this.bussiness.changeName(new MyBusiness.MySendListener(str)
          {
            public void onFail()
            {
            }

            public void onOk(byte[] paramArrayOfByte)
            {
              String str = StringUtils.btye2Str(paramArrayOfByte);
              StringUtils.btye2Str3(paramArrayOfByte);
              if ((paramArrayOfByte.length >= 15) && (str.substring(18, 20).equals("AD")))
              {
                if (FtPanel.this.bussiness.onChangeNameOk(FtPanel.11.this.val$posi, this.val$name))
                  FtPanel.this.lampAdapter.notifyDataSetChanged();
                FtPanel.this.dialog.dismiss();
              }
            }

            public void onTimeOut()
            {
            }
          }
          , Integer.parseInt("8" + FtPanel.this.selectItem, 16), 34, FtPanel.this.index, str.getBytes(), false);
      }
    });
    this.dialog.ll_takephoto.setOnClickListener(new View.OnClickListener(paramInt)
    {
      public void onClick(View paramView)
      {
        FtPanel.this.bussiness.goCarmare(FtPanel.this, this.val$posi);
      }
    });
    this.dialog.ll_album.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Crop.pickImage(FtPanel.this);
      }
    });
    new Thread()
    {
      public void run()
      {
        FtPanel.this.getActivity().runOnUiThread(new Runnable()
        {
          public void run()
          {
          }
        });
      }
    }
    .start();
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 209)
    {
      int i = paramRequest.getIntExtra("iconId", -1);
      String str = paramRequest.getStringExtra("name");
      int j = paramRequest.getIntExtra("index", -1);
      int k = paramRequest.getIntExtra("chosenIndex", 0);
      paramRequest.getBooleanExtra("backSign", false);
      this.bussiness.relateDeviceInfo(new MyBusiness.MySendListener(i, j, str, k)
      {
        public void onFail()
        {
          FtPanel.this.shortToast(2131100334);
          FtPanel.this.bussiness.setMySendListener(null);
        }

        public void onOk(byte[] paramArrayOfByte)
        {
          if (StringUtils.btye2Str(paramArrayOfByte).length() == 30)
          {
            if ((this.val$type != -1) && (this.val$i != -1))
            {
              ((PanelLampVO)FtPanel.this.panelLampVO.get(this.val$i)).setName(this.val$name);
              System.out.println("panelLampVO.get(i).setName(s) = 735" + this.val$name);
              ((PanelLampVO)FtPanel.this.panelLampVO.get(this.val$i)).setType(this.val$type);
              ((PanelLampVO)FtPanel.this.panelLampVO.get(this.val$i)).setRealation(true);
              ((PanelLampVO)FtPanel.this.panelLampVO.get(this.val$i)).setChosenIndex(this.val$chosenIndex);
              ((PanelLampVO)FtPanel.this.panelLampVO.get(this.val$i)).setShowRelationBtn(true);
            }
            FtPanel.this.bussiness.saveBug(652, FtPanel.this.lampAdapter.getCount());
            FtPanel.this.lampAdapter.notifyDataSetChanged();
            FtPanel.this.bussiness.setMySendListener(null);
          }
        }

        public void onTimeOut()
        {
          FtPanel.this.shortToast(2131100336);
          FtPanel.this.bussiness.setMySendListener(null);
        }
      }
      , 5, 34, this.roomNum, this.index, j, k, true);
      Log.i("", "");
    }
  }

  public void onOnoff(boolean paramBoolean, int paramInt)
  {
    ProgressDialog localProgressDialog = ProgressDialog.show(getActivity(), "", getString(2131100432), false);
    ((PanelLampVO)this.panelLampVO.get(paramInt)).setOn(paramBoolean);
    this.effectLights.add(Integer.valueOf(paramInt));
    String[] arrayOfString = new String[4];
    int i = 3;
    if (i >= 0)
    {
      if (((PanelLampVO)this.panelLampVO.get(i)).isOn())
        arrayOfString[i] = "1";
      while (true)
      {
        i--;
        break;
        arrayOfString[i] = "0";
      }
    }
    this.handler.removeCallbacks(this.timeout);
    this.handler.postDelayed(this.timeout, 5000L);
    new Handler().postDelayed(new Runnable(localProgressDialog, paramInt, paramBoolean, arrayOfString)
    {
      public void run()
      {
        FtPanel.this.bussiness.ctrlSwitch(new MyBusiness.MySendListener()
        {
          public void onFail()
          {
          }

          public void onOk(byte[] paramArrayOfByte)
          {
            String str = StringUtils.btye2Str(paramArrayOfByte);
            System.out.println("ctrlSwitchOnOk = " + str);
            if ((str.length() >= 20) && ((str.substring(18, 20).equalsIgnoreCase("95")) || (str.substring(18, 20).equalsIgnoreCase("A1"))))
            {
              FtPanel.this.bussiness.saveData(FtPanel.this.panelLampVO);
              FtPanel.8.this.val$dialog.dismiss();
              FtPanel.this.handler.removeCallbacks(FtPanel.this.timeout);
            }
          }

          public void onTimeOut()
          {
            FtPanel.8.this.val$dialog.dismiss();
            PanelLampVO localPanelLampVO = (PanelLampVO)FtPanel.this.panelLampVO.get(FtPanel.8.this.val$posi);
            if (!FtPanel.8.this.val$onoff);
            for (boolean bool = true; ; bool = false)
            {
              localPanelLampVO.setOn(bool);
              FtPanel.this.bussiness.saveBug(525, FtPanel.this.lampAdapter.getCount());
              FtPanel.this.lampAdapter.notifyDataSetChanged();
              FtPanel.this.handler.removeCallbacks(FtPanel.this.timeout);
              return;
            }
          }
        }
        , (PanelLampVO)FtPanel.this.panelLampVO.get(this.val$posi), this.val$posi, this.val$lampPosi, FtPanel.this.getRequest().getIntExtra("DRoomNumKey", 0));
      }
    }
    , 1000L);
  }

  public void onRelation(int paramInt)
  {
    this.bussiness.onRelation(paramInt);
    this.bussiness.saveBug(461, this.lampAdapter.getCount());
    this.lampAdapter.notifyDataSetChanged();
    startFragmentForResult(new Request(FtPanelAssociated.class).putExtra("currentIndex", paramInt).putExtra("dIndex", getRequest().getIntExtra("DIndexKey", 0)).putExtra("isSelectedRelation", ((PanelLampVO)this.panelLampVO.get(paramInt)).isRealation()).putExtra("chosenIndex", ((PanelLampVO)this.panelLampVO.get(paramInt)).getChosenIndex()).putExtra("roomNum", this.roomNum), 206);
  }

  public void onShowRelationControl(int paramInt)
  {
    AlertDialog105DeviceEdit localAlertDialog105DeviceEdit = new AlertDialog105DeviceEdit(getActivity());
    localAlertDialog105DeviceEdit.show();
    localAlertDialog105DeviceEdit.setDelDeviceTextVisiable();
    localAlertDialog105DeviceEdit.setButtonText(getString(2131100326), getString(2131099942), getString(2131099891));
    Window localWindow = localAlertDialog105DeviceEdit.getWindow();
    localWindow.getAttributes();
    localWindow.setGravity(80);
    localAlertDialog105DeviceEdit.setMyOnClickListener(new AlertDialog105DeviceEdit.MyOnClickListener(paramInt, localAlertDialog105DeviceEdit)
    {
      public void onClick(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return;
        case 2:
          FtPanel.this.startFragmentForResult(new Request(FtPanelAssociated.class).putExtra("currentIndex", this.val$posi).putExtra("dIndex", FtPanel.this.getRequest().getIntExtra("DIndexKey", 0)).putExtra("isSelectedRelation", ((PanelLampVO)FtPanel.this.panelLampVO.get(this.val$posi)).isRealation()).putExtra("chosenIndex", ((PanelLampVO)FtPanel.this.panelLampVO.get(this.val$posi)).getChosenIndex()).putExtra("roomNum", FtPanel.this.roomNum), 206);
          return;
        case 1:
          MyAlertDialog12 localMyAlertDialog12 = new MyAlertDialog12(FtPanel.this.getActivity());
          localMyAlertDialog12.show();
          localMyAlertDialog12.setMsg(2131100119);
          localMyAlertDialog12.setMyOnClickListener(new MyAlertDialog12.MyOnClickListener()
          {
            public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
            {
              FtPanel.this.bussiness.relateDeviceInfo(new MyBusiness.MySendListener()
              {
                public void onFail()
                {
                  FtPanel.this.shortToast(2131100334);
                  FtPanel.this.bussiness.setMySendListener(null);
                }

                public void onOk(byte[] paramArrayOfByte)
                {
                  String str = StringUtils.btye2Str(paramArrayOfByte);
                  System.out.println(str.length());
                  if (str.length() == 30)
                  {
                    ((PanelLampVO)FtPanel.this.panelLampVO.get(FtPanel.7.this.val$posi)).setName("");
                    ((PanelLampVO)FtPanel.this.panelLampVO.get(FtPanel.7.this.val$posi)).setType(-1);
                    ((PanelLampVO)FtPanel.this.panelLampVO.get(FtPanel.7.this.val$posi)).setRealation(false);
                    ((PanelLampVO)FtPanel.this.panelLampVO.get(FtPanel.7.this.val$posi)).setShowRelationBtn(false);
                    ((PanelLampVO)FtPanel.this.panelLampVO.get(FtPanel.7.this.val$posi)).setChosenIndex(-1);
                    FtPanel.this.bussiness.saveBug(421, FtPanel.this.lampAdapter.getCount());
                    FtPanel.this.lampAdapter.notifyDataSetChanged();
                    FtPanel.this.bussiness.setMySendListener(null);
                  }
                }

                public void onTimeOut()
                {
                  FtPanel.this.shortToast(2131100336);
                  FtPanel.this.bussiness.setMySendListener(null);
                }
              }
              , 5, 34, FtPanel.this.roomNum, FtPanel.this.index, FtPanel.7.this.val$posi, ((PanelLampVO)FtPanel.this.panelLampVO.get(FtPanel.7.this.val$posi)).getChosenIndex(), false);
            }
          });
          return;
        case 3:
        }
        this.val$editDialog.dismiss();
      }
    });
  }

  public void panelOperateListener()
  {
    this.bussiness.startListener(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        int i4;
        label582: label632: int m;
        int n;
        do
        {
          String str1;
          Dvc localDvc;
          do
          {
            String str2;
            do
            {
              while (true)
              {
                int i1;
                try
                {
                  str1 = StringUtils.btye2Str(paramArrayOfByte);
                  boolean bool1 = FtPanel.this.bussiness.addCheckSumData(str1);
                  str2 = str1.substring(18, 20);
                  int i = Integer.parseInt(str1.substring(20, 22), 16);
                  int j = Integer.parseInt(str1.substring(26, 28), 16);
                  boolean bool2 = str2.equalsIgnoreCase("AD");
                  if (!bool2)
                    continue;
                  try
                  {
                    int i2 = Integer.parseInt(str1.substring(28, 30), 16);
                    int i3 = -1;
                    String str4 = str1.substring(24, 26);
                    i4 = -1;
                    switch (str4.hashCode())
                    {
                    case 1784:
                      String str5 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(str1.substring(30, 78)));
                      System.out.println("returnName = " + str5);
                      if ((i2 != FtPanel.this.index) || (i3 == -1) || (!FtPanel.this.bussiness.onChangeNameOk(i3, str5)))
                        continue;
                      FtPanel.this.lampAdapter.notifyDataSetChanged();
                      if ((!str2.equalsIgnoreCase("A1")) || (!bool1) || (i != 13) || (j != 34) || (FtPanel.this.lastReturnData.equalsIgnoreCase(str1)) || (FtPanel.this.getActivity() == null))
                        break label632;
                      UserFerences localUserFerences = UserFerences.getUserFerences(FtPanel.this.getFragment().getActivity().getBaseContext());
                      FtPanel.this.getter = localUserFerences.spFerences;
                      if (!FtPanel.this.getter.getString("GateWayIdKey" + FtPanel.this.getter.getString("GateWayMacIdKey", "11223344"), "00000000").equalsIgnoreCase(str1.substring(10, 18)))
                        break label632;
                      FtPanel.access$202(FtPanel.this, str1);
                      char[] arrayOfChar = UtilMath.hexString2binaryString(str1.substring(28, 30)).substring(4).toCharArray();
                      i1 = 3;
                      if (i1 < 0)
                        break label582;
                      if (arrayOfChar[i1] != '1')
                        break;
                      ((PanelLampVO)FtPanel.this.panelLampVO.get(3 - i1)).setOn(true);
                      i1--;
                      continue;
                      if (!str4.equals("80"))
                        break label1238;
                      i4 = 0;
                      break;
                    case 1785:
                      if (!str4.equals("81"))
                        break label1238;
                      i4 = 1;
                      break;
                    case 1786:
                      if (!str4.equals("82"))
                        break label1238;
                      i4 = 2;
                      break;
                    case 1787:
                      boolean bool3 = str4.equals("83");
                      if (!bool3)
                        break label1238;
                      i4 = 3;
                      break label1238;
                      i3 = 0;
                      continue;
                      i3 = 1;
                      continue;
                      i3 = 2;
                      continue;
                      i3 = 3;
                      continue;
                    }
                  }
                  catch (Exception localException2)
                  {
                    localException2.printStackTrace();
                    continue;
                  }
                }
                catch (Exception localException1)
                {
                  localException1.printStackTrace();
                  return;
                }
                ((PanelLampVO)FtPanel.this.panelLampVO.get(3 - i1)).setOn(false);
              }
              FtPanel.this.bussiness.saveData(FtPanel.this.panelLampVO);
              FtPanel.this.bussiness.saveBug(208, FtPanel.this.lampAdapter.getCount());
              FtPanel.this.lampAdapter.notifyDataSetChanged();
            }
            while ((!str2.equalsIgnoreCase("A3")) || (str1.length() != 56));
            localDvc = FtPanel.this.bussiness.saveDvcData(str1);
          }
          while (localDvc == null);
          int k = 0;
          m = 0;
          Iterator localIterator = localDvc.getPanelLampVO().iterator();
          if (!localIterator.hasNext())
            continue;
          ((PanelLampVO)localIterator.next());
          Integer.parseInt(str1.substring(28, 30), 16);
          m = 1;
          ((PanelLampVO)FtPanel.this.panelLampVO.get(k)).setShowRelationBtn(false);
          ((PanelLampVO)FtPanel.this.panelLampVO.get(k)).setChosenIndex(((PanelLampVO)localDvc.getPanelLampVO().get(k)).getChosenIndex());
          ((PanelLampVO)FtPanel.this.panelLampVO.get(k)).setOn(((PanelLampVO)localDvc.getPanelLampVO().get(k)).isOn());
          String str3 = "";
          if (((PanelLampVO)localDvc.getPanelLampVO().get(k)).getChosenIndex() > 0)
          {
            ((PanelLampVO)FtPanel.this.panelLampVO.get(k)).setRealation(true);
            if (FtPanel.this.dvcInMIndex.get(Integer.valueOf(((PanelLampVO)localDvc.getPanelLampVO().get(k)).getChosenIndex())) != null)
              str3 = (String)FtPanel.this.dvcInMIndex.get(Integer.valueOf(((PanelLampVO)localDvc.getPanelLampVO().get(k)).getChosenIndex()));
            n = -1;
          }
          switch (str3.hashCode())
          {
          case 81069:
            ((PanelLampVO)FtPanel.this.panelLampVO.get(k)).setType(11);
          case 2161:
            while (true)
            {
              ((PanelLampVO)FtPanel.this.panelLampVO.get(k)).setName(str3);
              System.out.println("panelLampVO.get(i).setName(s) = 285" + str3);
              k++;
              break;
              if (!str3.equals("RGB"))
                break label1272;
              n = 0;
              break label1272;
              if (!str3.equals("CT"))
                break label1272;
              n = 1;
              break label1272;
              ((PanelLampVO)FtPanel.this.panelLampVO.get(k)).setType(8);
              continue;
              ((PanelLampVO)FtPanel.this.panelLampVO.get(k)).setType(9);
              continue;
              ((PanelLampVO)FtPanel.this.panelLampVO.get(k)).setRealation(false);
              ((PanelLampVO)FtPanel.this.panelLampVO.get(k)).setType(-1);
            }
          }
        }
        while (m == 0);
        FtPanel.this.bussiness.saveData(FtPanel.this.panelLampVO);
        FtPanel.this.bussiness.saveBug(262, FtPanel.this.lampAdapter.getCount());
        FtPanel.this.lampAdapter.notifyDataSetChanged();
        return;
        label1238: switch (i4)
        {
        default:
        case 0:
        case 1:
        case 2:
        case 3:
        }
        label1272: switch (n)
        {
        default:
        case 0:
        case 1:
        }
      }

      public void onTimeOut()
      {
      }
    }
    , this.mac, this.bussiness.home);
  }

  public void syncDeviceCurrentInfo()
  {
    this.bussiness.syncDeviceInfo(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
        Log.i("", "");
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        String str1 = StringUtils.btye2Str(paramArrayOfByte);
        System.out.println(StringUtils.btye2Str3(paramArrayOfByte));
        Dvc localDvc = FtPanel.this.bussiness.saveDvcData(str1);
        if (localDvc != null)
        {
          int i = 0;
          Iterator localIterator = localDvc.getPanelLampVO().iterator();
          if (localIterator.hasNext())
          {
            PanelLampVO localPanelLampVO = (PanelLampVO)localIterator.next();
            Integer.parseInt(str1.substring(28, 30), 16);
            ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setShowRelationBtn(false);
            ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setChosenIndex(((PanelLampVO)localDvc.getPanelLampVO().get(i)).getChosenIndex());
            ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setOn(((PanelLampVO)localDvc.getPanelLampVO().get(i)).isOn());
            String str2 = "";
            label352: int j;
            if (((PanelLampVO)localDvc.getPanelLampVO().get(i)).getChosenIndex() > 0)
            {
              ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setRealation(true);
              if (FtPanel.this.dvcInMIndex.get(Integer.valueOf(((PanelLampVO)localDvc.getPanelLampVO().get(i)).getChosenIndex())) != null)
                str2 = (String)FtPanel.this.dvcInMIndex.get(Integer.valueOf(((PanelLampVO)localDvc.getPanelLampVO().get(i)).getChosenIndex()));
              if (str2.length() == 0)
                str2 = FtPanel.this.getString(2131100028);
              switch (str2.hashCode())
              {
              default:
                j = -1;
                switch (j)
                {
                default:
                  label355: ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setType(11);
                  label406: ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setType(localPanelLampVO.getType());
                case 0:
                case 1:
                case 2:
                }
              case 81069:
              case 2161:
              case 67688:
              }
            }
            while (true)
            {
              ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setName(localPanelLampVO.getName());
              i++;
              break;
              if (!str2.equals("RGB"))
                break label352;
              j = 0;
              break label355;
              if (!str2.equals("CT"))
                break label352;
              j = 1;
              break label355;
              if (!str2.equals("DIM"))
                break label352;
              j = 2;
              break label355;
              ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setType(8);
              break label406;
              ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setType(9);
              break label406;
              ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setType(11);
              break label406;
              ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setRealation(false);
              ((PanelLampVO)FtPanel.this.panelLampVO.get(i)).setType(-1);
            }
          }
          FtPanel.this.lampAdapter.notifyDataSetChanged();
          FtPanel.this.bussiness.setMySendListener(null);
          FtPanel.this.initListener();
          FtPanel.this.loopGetBranchName();
          FtPanel.this.isGetPanelInfoOk = true;
        }
      }

      public void onTimeOut()
      {
        Log.i("", "");
      }
    }
    , 1, this.roomNum, this.index);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.panel.FtPanel
 * JD-Core Version:    0.6.0
 */