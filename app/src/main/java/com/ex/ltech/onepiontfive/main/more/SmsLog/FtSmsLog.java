package com.ex.ltech.onepiontfive.main.more.SmsLog;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.my_view.pullfresh.PullToRefreshLayout;
import com.ex.ltech.led.my_view.pullfresh.PullToRefreshLayout.OnRefreshListener;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.room.RoomsBusiness;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.vo.SensorVo;
import com.ex.ltech.onepiontfive.main.vo.SmsLogVo;
import com.ex.ltech.onepiontfive.main.vo.SmsLogsVo;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FtSmsLog extends MyBaseFt
{
  private FtSmsLogAdapter adapter;

  @Bind({2131558784})
  RippleView btnTitleViewEdit;
  private FtSmsLogBusiness business;
  private List<String> everySmsLogsLatest = new ArrayList();
  private Home home = null;
  private List<String> indexOfSensor = new ArrayList();
  private String lastReturnData = "";
  private String mac;

  @Bind({2131558584})
  PullToRefreshLayout refreshView;
  private List<Dvc> sensorDvcList = new ArrayList();
  List<SmsLogsVo> smsLogsVos = new ArrayList();

  @Bind({2131559207})
  SwipeMenuListView swipeContent;

  @Bind({2131558785})
  TextView tvTitleViewEdit;
  private List<Integer> useToQuerySmsLogs = new ArrayList();
  private View view;

  private void initEvent()
  {
    this.mac = UserFerences.getUserFerences(getActivity().getBaseContext()).getValue("GateWayMacIdKey");
    this.btnTitleViewEdit.setBackgroundResource(2130903706);
    this.btnTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        for (int i = 0; i < FtSmsLog.this.home.getRooms().size(); i++)
          for (int j = 0; j < ((Room)FtSmsLog.this.home.getRooms().get(i)).getDvcVos().size(); j++)
          {
            if (((Dvc)((Room)FtSmsLog.this.home.getRooms().get(i)).getDvcVos().get(j)).getSensorVo() == null)
              continue;
            ((Dvc)((Room)FtSmsLog.this.home.getRooms().get(i)).getDvcVos().get(j)).getSensorVo().setSmsLogs(null);
            FtSmsLog.this.business.putData4ClassName(FtSmsLog.this.mac, FtSmsLog.this.home);
          }
      }
    });
    this.home = new RoomsBusiness(getActivity()).getHome();
    this.business = new FtSmsLogBusiness(getActivity().getBaseContext(), this.home.getRooms());
    remoteOperateListener();
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < this.home.getRooms().size(); i++)
    {
      for (int j = 0; j < ((Room)this.home.getRooms().get(i)).getDvcVos().size(); j++)
      {
        if ((((Dvc)((Room)this.home.getRooms().get(i)).getDvcVos().get(j)).getType() != 14) && (((Dvc)((Room)this.home.getRooms().get(i)).getDvcVos().get(j)).getSensorVo() == null))
          continue;
        this.useToQuerySmsLogs.add(Integer.valueOf(((Dvc)((Room)this.home.getRooms().get(i)).getDvcVos().get(j)).getmIndex()));
        this.sensorDvcList.add(((Room)this.home.getRooms().get(i)).getDvcVos().get(j));
        this.indexOfSensor.add(i + "index" + j);
        if ((((Dvc)((Room)this.home.getRooms().get(i)).getDvcVos().get(j)).getSensorVo() == null) || (((Dvc)((Room)this.home.getRooms().get(i)).getDvcVos().get(j)).getSensorVo().getSmsLogs().size() <= 0))
          continue;
        Iterator localIterator = ((Dvc)((Room)this.home.getRooms().get(i)).getDvcVos().get(j)).getSensorVo().getSmsLogs().iterator();
        if (!localIterator.hasNext())
          continue;
        SmsLogVo localSmsLogVo = (SmsLogVo)localIterator.next();
        String str1 = localSmsLogVo.getTriggerHour() + ":" + localSmsLogVo.getTriggerMin();
        String str2 = "";
        switch (((Dvc)((Room)this.home.getRooms().get(i)).getDvcVos().get(j)).getRoomIndex())
        {
        default:
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        }
        while (true)
        {
          localArrayList.add("" + str2 + getActivity().getString(2131100181) + ((Dvc)((Room)this.home.getRooms().get(i)).getDvcVos().get(j)).getName() + getActivity().getString(2131100182) + str1 + getActivity().getString(2131100086));
          this.everySmsLogsLatest.add(i + "+" + j + "+" + String.valueOf(localSmsLogVo.getTriggerOrder()));
          break;
          str2 = getActivity().getString(2131100349);
          continue;
          str2 = getActivity().getString(2131100350);
          continue;
          str2 = getActivity().getString(2131100351);
          continue;
          str2 = getActivity().getString(2131100352);
          continue;
          str2 = getActivity().getString(2131100353);
          continue;
          str2 = getActivity().getString(2131100354);
          continue;
          str2 = getActivity().getString(2131100355);
          continue;
          str2 = getActivity().getString(2131100356);
        }
      }
      this.business.useToSaveList(this.useToQuerySmsLogs, this.sensorDvcList);
    }
    this.adapter = new FtSmsLogAdapter(getActivity(), localArrayList);
    this.swipeContent.setAdapter(this.adapter);
    this.refreshView.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener()
    {
      public void onLoadMore(PullToRefreshLayout paramPullToRefreshLayout)
      {
        new Thread()
        {
          public void run()
          {
            super.run();
            FtSmsLog.this.loadMoreLog();
          }
        }
        .start();
      }

      public void onRefresh(PullToRefreshLayout paramPullToRefreshLayout)
      {
        int i = FtSmsLog.this.sensorDvcList.size();
        if (FtSmsLog.this.useToQuerySmsLogs.size() != 0)
          for (int j = 0; j < i; j++)
            new Thread(Integer.valueOf(((Dvc)FtSmsLog.this.sensorDvcList.get(j)).getmIndex()), ((String)FtSmsLog.this.indexOfSensor.get(j)).split("index"))
            {
              public void run()
              {
                super.run();
                new FtSmsLogBusiness(FtSmsLog.this.getActivity().getBaseContext(), FtSmsLog.this.home.getRooms()).loadImagesByThread(this.val$integer.intValue(), this.val$sensorIndex);
              }
            }
            .start();
      }
    });
    this.swipeContent.setMenuCreator(new SwipeMenuCreator()
    {
      public void create(SwipeMenu paramSwipeMenu)
      {
      }
    });
    this.swipeContent.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2)
      {
        return false;
      }
    });
    this.swipeContent.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
      }
    });
  }

  private void loadImagesByThread(int paramInt)
  {
    Log.e("当前线程：", "" + Thread.currentThread().getName());
    FtSmsLogBusiness localFtSmsLogBusiness = new FtSmsLogBusiness(getActivity().getBaseContext(), this.home.getRooms());
    localFtSmsLogBusiness.querySmsLogInfo(new MyBusiness.MySendListener(localFtSmsLogBusiness, paramInt)
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        String str = StringUtils.btye2Str(paramArrayOfByte);
        int i = this.val$business2.compareWithReturnInfo(StringUtils.btye2Str(paramArrayOfByte), Integer.valueOf(this.val$useToQuerySmsLogsMIndex));
        if (i == -1)
          return;
        if (i == -2)
        {
          FtSmsLog.this.responseMessage(str.substring(4, 6), "25");
          return;
        }
        FtSmsLog.this.responseMessage(str.substring(4, 6), "25");
        FtSmsLog.this.syncSceneInfo(this.val$useToQuerySmsLogsMIndex, 50);
      }

      public void onTimeOut()
      {
      }
    }
    , paramInt);
  }

  private void responseMessage(String paramString1, String paramString2)
  {
    this.business.responseMessage(null, paramString1, paramString2);
  }

  private void updateLog(String paramString)
  {
    boolean bool = this.business.addCheckSumData(paramString);
    String str = paramString.substring(18, 20);
    int i = Integer.parseInt(paramString.substring(20, 22), 16);
    if ((str.equalsIgnoreCase("A0")) && (bool) && (i == 5))
    {
      this.business.responseMessage(null, paramString.substring(4, 6), "22");
      if (!this.lastReturnData.equalsIgnoreCase(paramString))
      {
        this.lastReturnData = paramString;
        Integer.parseInt(paramString.substring(18, 20), 16);
        Integer.parseInt(paramString.substring(22, 24), 16);
        Integer.parseInt(paramString.substring(24, 26), 16);
        Integer.parseInt(paramString.substring(26, 28), 16);
        Integer.parseInt(paramString.substring(28, 30), 16);
        Integer.parseInt(paramString.substring(30, 32), 16);
      }
    }
  }

  public void loadMoreLog()
  {
    for (int i = 0; i < this.useToQuerySmsLogs.size(); i++)
    {
      Integer localInteger = (Integer)this.useToQuerySmsLogs.get(i);
      this.business.querySmsLogInfo(new MyBusiness.MySendListener(localInteger)
      {
        public void onFail()
        {
        }

        public void onOk(byte[] paramArrayOfByte)
        {
          String str = StringUtils.btye2Str(paramArrayOfByte);
          int i = FtSmsLog.this.business.compareWithReturnInfo(StringUtils.btye2Str(paramArrayOfByte), this.val$serniorId);
          if (i == -1)
            return;
          if (i == -2)
          {
            FtSmsLog.this.responseMessage(str.substring(4, 6), "25");
            return;
          }
          FtSmsLog.this.responseMessage(str.substring(4, 6), "25");
          FtSmsLog.this.syncSceneInfo(this.val$serniorId.intValue(), i);
        }

        public void onTimeOut()
        {
        }
      }
      , ((Integer)this.useToQuerySmsLogs.get(i)).intValue());
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2130968764, null);
    ButterKnife.bind(this, this.view);
    initEvent();
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void refreshLog()
  {
    for (int i = 0; i < this.useToQuerySmsLogs.size(); i++)
      loadImagesByThread(((Integer)this.useToQuerySmsLogs.get(i)).intValue());
  }

  public void remoteOperateListener()
  {
  }

  public void syncSceneInfo(int paramInt1, int paramInt2)
  {
    SystemClock.sleep(200L);
    int i = paramInt2 % 50;
    int j = paramInt2 / 50;
    for (int k = 0; k < j + i; k++)
    {
      int m = this.useToQuerySmsLogs.indexOf(Integer.valueOf(paramInt1));
      for (int n = 0; n < this.sensorDvcList.size(); n++)
      {
        if (((Dvc)this.sensorDvcList.get(n)).getmIndex() != paramInt1)
          continue;
        m = n;
      }
      ((Dvc)this.sensorDvcList.get(m));
      this.business.syncSmsLogInfo(new MyBusiness.MySendListener()
      {
        public void onFail()
        {
          Log.i("", "");
        }

        public void onOk(byte[] paramArrayOfByte)
        {
          StringUtils.btye2Str(paramArrayOfByte);
        }

        public void onTimeOut()
        {
          Log.i("", "");
        }
      }
      , paramInt1, 1 + k * 50);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.SmsLog.FtSmsLog
 * JD-Core Version:    0.6.0
 */