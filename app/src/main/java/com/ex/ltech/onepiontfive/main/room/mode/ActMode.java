package com.ex.ltech.onepiontfive.main.room.mode;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.my_view.pullfresh.PullToRefreshLayout;
import com.ex.ltech.led.my_view.pullfresh.PullToRefreshLayout.OnRefreshListener;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.ModeVo;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.more.GeoFencing.FtGeoFencingHomeScene;
import com.fragmentmaster.app.Request;
import com.google.gson.Gson;
import com.indris.material.RippleView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActMode extends MyBaseFt
  implements AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener
{
  static int count = 0;
  static String name = "";
  static int position = 0;
  Runnable TimeoutRunnable = new Runnable()
  {
    public void run()
    {
      if ((ActMode.this.dialog != null) && (ActMode.this.dialog.isShowing()))
        ActMode.this.dialog.dismiss();
      ActMode.this.shortToast(2131100336);
      ActMode.this.business.setMySendListener(null);
    }
  };
  String[] aaa;

  @Bind({2131558716})
  Button btnActiModeAdd;

  @Bind({2131558709})
  RippleView btnActiModePlay;
  private Button btnRefresh;

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  private ModeBusiness business;
  int count4A2 = 0;
  int dIndex;
  private Button delMode;
  AlertDialog dialog;
  List<Integer> exitsDeviceIndexs = new ArrayList();
  MyBusiness.MySendListener getModeInfoListener = new MyBusiness.MySendListener()
  {
    public void onFail()
    {
    }

    public void onOk(byte[] paramArrayOfByte)
    {
      String str = StringUtils.btye2Str(paramArrayOfByte);
      StringUtils.btye2Str(paramArrayOfByte);
      do
      {
        int i;
        int j;
        do
        {
          try
          {
            i = Integer.parseInt(str.substring(24, 26), 16);
            if (!str.substring(18, 20).equalsIgnoreCase("a4"))
              return;
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
            return;
          }
          j = 0;
          for (int k = 0; k < ActMode.this.exitsDeviceIndexs.size(); k++)
          {
            if (!((Integer)ActMode.this.exitsDeviceIndexs.get(k)).equals(Integer.valueOf(i)))
              continue;
            j = 1;
          }
        }
        while (j != 0);
        ActMode.this.exitsDeviceIndexs.add(Integer.valueOf(i));
        try
        {
          boolean bool2 = ActMode.this.business.saveModeData(str);
          bool1 = bool2;
          if (bool1)
            ActMode.this.responseMessage(str.substring(4, 6), "24");
          if (ActMode.this.loopGetDeviceCount < ActMode.this.modeNum.size())
          {
            ActMode.this.loopGetModeInfo(ActMode.this.loopGetDeviceCount);
            return;
          }
        }
        catch (Exception localException2)
        {
          while (true)
          {
            localException2.printStackTrace();
            boolean bool1 = false;
          }
          ActMode.this.business.insertSynMode();
          ActMode.this.business.saveModesData2local(ActMode.this.business.modes);
          ActMode.this.setAdt();
          ActMode.this.handler.removeCallbacks(ActMode.this.TimeoutRunnable);
        }
      }
      while ((ActMode.this.dialog == null) || (!ActMode.this.dialog.isShowing()));
      ActMode.this.dialog.dismiss();
    }

    public void onTimeOut()
    {
    }
  };
  int groupId;

  @Bind({2131558714})
  GridView gvActMode;
  Handler handler = new Handler();
  boolean isAllSeleted;
  boolean isGroup;
  boolean isLongClick;
  private boolean isMultiSeleted;
  private boolean isPlay;
  private boolean isSingleSeleted;

  @Bind({2131558711})
  ImageView ivFragSysInsideAllSeleted;
  ImageView iv_frag_sys_inside_all_seleted;
  LinearLayout llChoseall;
  int loopGetDeviceCount = 0;
  private PullToRefreshLayout mRefreshLayout;
  private ModeGridViewAdapter madapter;
  List<Integer> modeNum;
  int roomNum;

  @Bind({2131558708})
  SeekBar sbActiMode;
  private boolean seekbarB = false;
  private int seekbarI = 0;
  private int singleSeletedIndex = -1;
  private int speed = 2;
  int tempSpeed;

  @Bind({2131558706})
  TextView tvActiModeProgress;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;

  private void init()
  {
    this.btnRefresh = ((Button)this.view.findViewById(2131558713));
    this.mRefreshLayout = ((PullToRefreshLayout)this.view.findViewById(2131558584));
    this.mRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener()
    {
      public void onLoadMore(PullToRefreshLayout paramPullToRefreshLayout)
      {
      }

      public void onRefresh(PullToRefreshLayout paramPullToRefreshLayout)
      {
      }
    });
    this.isGroup = getRequest().getBooleanExtra("ModeIsGroupKey", false);
    this.groupId = getRequest().getIntExtra("ModeGroupIdKey", 0);
    this.roomNum = getRequest().getIntExtra("DRoomNumKey", 0);
    this.dIndex = getRequest().getIntExtra("DIndexKey", 0);
    this.llChoseall = ((LinearLayout)this.view.findViewById(2131558710));
    this.llChoseall.setOnClickListener(this);
    this.business = new ModeBusiness(getActivity());
    this.business.setIsGroup(this.isGroup);
    this.business.setGroupNum(this.groupId);
    this.business.setDvcNum(getRequest().getIntExtra("ModeDvcOderIdKey", 0));
    this.business.initDefaultModes();
    this.business.appendAddBtnVo();
    this.business.saveModesData2local(this.business.modes);
    this.business.prepareLink();
    this.business.queryModeNum(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        String str = StringUtils.btye2Str(paramArrayOfByte);
        ActMode.this.modeNum = ActMode.this.business.compareWithReturnInfo(StringUtils.btye2Str(paramArrayOfByte));
        if ((ActMode.this.modeNum != null) && (ActMode.this.modeNum.size() != 0));
        try
        {
          ActMode.this.responseMessage(str.substring(4, 6), "22");
          ActMode.this.exitsDeviceIndexs.clear();
          ActMode.this.business.synModes.clear();
          ActMode.this.loopGetModeInfo(ActMode.this.loopGetDeviceCount);
          ActMode.this.dialog = ProgressDialog.show(ActMode.this.getActivity(), "", ActMode.this.getString(2131100002), false);
          if (!ActMode.this.dialog.isShowing())
            ActMode.this.dialog.show();
          ActMode.this.dialog.setCancelable(false);
          ActMode.this.handler.removeCallbacks(ActMode.this.TimeoutRunnable);
          ActMode.this.handler.postDelayed(ActMode.this.TimeoutRunnable, 10000L);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }

      public void onTimeOut()
      {
      }
    });
    setAdt();
  }

  private void initTitle()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131100163);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ActMode.this.business.setMySendListener(null);
        ActMode.this.finish();
      }
    });
  }

  private void loopGetModeInfo(int paramInt)
  {
    this.business.syncModeInfo(this.getModeInfoListener, ((Integer)this.modeNum.get(paramInt)).intValue());
    this.loopGetDeviceCount = (1 + this.loopGetDeviceCount);
  }

  private void responseMessage(String paramString1, String paramString2)
  {
    this.business.responseMessage(null, paramString1, paramString2);
  }

  private void setAdt()
  {
    this.madapter = new ModeGridViewAdapter(getActivity(), this.business.modes, this.business.getNewCreateModeBitmaps());
    this.gvActMode.setAdapter(this.madapter);
    this.madapter.setMoreSeletedListener(new MoreSeletedListener()
    {
      public void onMoreSeleted(int paramInt)
      {
        ActMode.this.business.onMoreSeleted(paramInt);
        ActMode.this.madapter.notifyDataSetChanged();
        if (ActMode.this.business.isMultiSeleted())
        {
          ActMode.this.sbActiMode.setVisibility(4);
          return;
        }
        ActMode.this.sbActiMode.setVisibility(0);
      }
    });
    this.madapter.setSingleSeletedListener(new SingleSeletedListener()
    {
      public void onLongClick(int paramInt)
      {
        ActMode.this.isLongClick = true;
        if ((paramInt > 7) && (!((ModeVo)ActMode.this.business.modes.get(paramInt)).isAddBtn()))
        {
          ActMode.this.business.saveModesData2local(ActMode.this.business.modes);
          ActMode.this.startFragmentForResult(new Request(ActNewMode.class).putExtra("modeCount", ActMode.count).putExtra("modesNames", ActMode.name).putExtra("DIndexKey", ActMode.this.getRequest().getIntExtra("DIndexKey", 0)).putExtra("DRoomNumKey", ActMode.this.getRequest().getIntExtra("DRoomNumKey", 0)).putExtra("modeDataStr", ActMode.this.business.gs.toJson(ActMode.this.business.modes.get(paramInt))).putExtra("isNewCreate", false).putExtra("modeCount", -1 + (-1 + ActMode.this.business.modes.size())).putExtra("modesNames", ActMode.this.business.modesNames).putExtra("modesPosi", paramInt).putExtra("modeOrder", ((ModeVo)ActMode.this.business.modes.get(paramInt)).getOrder()).putExtra("ModeIsGroupKey", ActMode.this.isGroup).putExtra("ModeGroupIdKey", ActMode.this.groupId).putExtra("ModeDvcOderIdKey", ActMode.this.getRequest().getIntExtra("ModeDvcOderIdKey", 0)), 206);
        }
      }

      public void onSingleSeleted(int paramInt)
      {
        if (ActMode.this.isLongClick)
          ActMode.this.isLongClick = false;
        do
        {
          ModeVo localModeVo;
          do
          {
            return;
            ActMode.access$502(ActMode.this, paramInt);
            if ((ActMode.this.business.onSingleSeleted(ActMode.this.roomNum, ActMode.this.dIndex, paramInt) == 1) && (paramInt < 16))
              ActMode.this.startFragmentForResult(new Request(ActNewMode.class).putExtra("modeCount", ActMode.count).putExtra("modesNames", ActMode.name).putExtra("DIndexKey", ActMode.this.getRequest().getIntExtra("DIndexKey", 0)).putExtra("isNewCreate", true).putExtra("DRoomNumKey", ActMode.this.getRequest().getIntExtra("DRoomNumKey", 0)).putExtra("ModeIsGroupKey", ActMode.this.isGroup).putExtra("ModeGroupIdKey", ActMode.this.groupId).putExtra("ModeDvcOderIdKey", ActMode.this.getRequest().getIntExtra("ModeDvcOderIdKey", 0)), 206);
            ActMode.this.madapter.notifyDataSetChanged();
            localModeVo = (ModeVo)ActMode.this.business.modes.get(paramInt);
          }
          while (localModeVo.isAddBtn());
          ActMode.this.sbActiMode.setProgress(12 * localModeVo.getSpeed());
          ActMode.this.sbActiMode.setVisibility(0);
          ActMode.this.ivFragSysInsideAllSeleted.setBackgroundResource(2130903315);
          ActMode.this.isAllSeleted = false;
          ActMode.this.sbActiMode.setVisibility(0);
        }
        while (!ActMode.this.isPlay);
        ActMode.this.btnActiModePlay.setBackgroundResource(2130903500);
        ActMode.access$302(ActMode.this, false);
      }
    });
  }

  private void setListener()
  {
    this.sbActiMode.setOnSeekBarChangeListener(this);
    this.btnActiModePlay.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ActMode localActMode = ActMode.this;
        if (!ActMode.this.isPlay);
        for (boolean bool = true; ; bool = false)
        {
          ActMode.access$302(localActMode, bool);
          if (ActMode.this.business.isMultiSeleted())
          {
            if (!ActMode.this.isPlay)
              break;
            ActMode.this.btnActiModePlay.setBackgroundResource(2130903501);
            ActMode.this.business.sendModes(ActMode.this.roomNum, ActMode.this.dIndex, 1);
          }
          return;
        }
        ActMode.this.business.sendModes(ActMode.this.roomNum, ActMode.this.dIndex, 2);
        ActMode.this.btnActiModePlay.setBackgroundResource(2130903500);
      }
    });
  }

  public static void toSettingValue(int paramInt1, String paramString, int paramInt2)
  {
    count = paramInt1;
    name = paramString;
    position = paramInt2;
  }

  public void goOut()
  {
    startFragmentForResult(new Request(FtGeoFencingHomeScene.class).putExtra("SceneInnerRemoteTyptKey", "SceneInnerRemoteTyptTv"), 205);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 1000)
    {
      this.business.initModes();
      setAdt();
    }
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131558712)
    {
      List localList = this.business.getItemVos();
      if (this.business.isMultiSeleted())
      {
        localList.size();
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          if (!((ModeVo)localIterator.next()).isSeleted())
            continue;
          localIterator.remove();
        }
        this.business.saveModesData2local(localList);
        setAdt();
        this.business.deleteModes(new MyBusiness.MySendListener()
        {
          public void onFail()
          {
          }

          public void onOk(byte[] paramArrayOfByte)
          {
          }

          public void onTimeOut()
          {
          }
        }
        , this.roomNum, this.dIndex, 1);
      }
      return;
    }
    this.singleSeletedIndex = -1;
    if (!this.isAllSeleted)
    {
      this.ivFragSysInsideAllSeleted.setBackgroundResource(2130903475);
      this.isAllSeleted = true;
      this.sbActiMode.setVisibility(4);
    }
    while (true)
    {
      this.business.seletedAll(this.isAllSeleted);
      this.madapter.notifyDataSetChanged();
      return;
      this.ivFragSysInsideAllSeleted.setBackgroundResource(2130903315);
      this.isAllSeleted = false;
      this.sbActiMode.setVisibility(0);
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2130968634, null);
    ButterKnife.bind(this, this.view);
    this.delMode = ((Button)this.view.findViewById(2131558712));
    this.delMode.setOnClickListener(this);
    initTitle();
    init();
    setListener();
    return this.view;
  }

  public void onDestroy()
  {
    super.onDestroy();
    System.out.println("ftMOde destroy ");
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    if (this.business != null)
      this.business.setSendListener(null);
    this.handler.removeCallbacks(this.TimeoutRunnable);
    ButterKnife.unbind(this);
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 1000)
    {
      this.business.initModes();
      this.business.saveModesData2local(this.business.modes);
      setAdt();
    }
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (this.business.onSingleSeleted(this.roomNum, this.dIndex, paramInt) == 1)
      startFragmentForResult(new Request(ActNewMode.class).putExtra("modeCount", count).putExtra("modesNames", name).putExtra("modesPosi", position), 206);
    this.madapter.notifyDataSetChanged();
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    this.speed = (paramSeekBar.getProgress() / 12);
    if (this.tempSpeed != this.speed)
      this.tempSpeed = this.speed;
  }

  public void onResume()
  {
    super.onResume();
    if (!Main.lastSendCmd.equals(Main.modeCmd))
    {
      this.isPlay = false;
      this.btnActiModePlay.setBackgroundResource(2130903500);
    }
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    this.isMultiSeleted = this.business.isMultiSeleted();
    this.isSingleSeleted = this.business.isSingleSeleted();
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    int i = 1;
    int j;
    ModeBusiness localModeBusiness1;
    if (this.isSingleSeleted)
    {
      ModeBusiness localModeBusiness2 = this.business;
      if (this.speed == 0)
      {
        j = i;
        localModeBusiness2.changeMoveSpeed(j);
        this.business.sendSingleMode(this.roomNum, this.dIndex, this.singleSeletedIndex, i);
      }
    }
    else if (this.isMultiSeleted)
    {
      localModeBusiness1 = this.business;
      if (this.speed != 0)
        break label93;
    }
    while (true)
    {
      localModeBusiness1.sendModesWithSameSpeed(i);
      this.business.saveMoveSpeed2Sd();
      return;
      j = this.speed;
      break;
      label93: i = this.speed;
    }
  }

  public void seletedAll(View paramView)
  {
  }

  public static abstract interface MoreSeletedListener
  {
    public abstract void onMoreSeleted(int paramInt);
  }

  public static abstract interface SingleSeletedListener
  {
    public abstract void onLongClick(int paramInt);

    public abstract void onSingleSeleted(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.mode.ActMode
 * JD-Core Version:    0.6.0
 */