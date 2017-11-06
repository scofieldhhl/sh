package com.ex.ltech.onepiontfive.main.time;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.ListUtils;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.newscene.ExpandableLvSceneBusiness;
import com.ex.ltech.onepiontfive.main.vo.Scene;
import com.ex.ltech.onepiontfive.main.vo.Scenes;
import com.ex.ltech.onepiontfive.main.vo.Timing;
import com.ex.ltech.onepiontfive.main.vo.Timings;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FtTime extends MyBaseFt
{
  TimingListAdapter adapter;

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  TimingBusiness business;
  boolean checkJustOnce;
  Runnable checkJustOnceThread = new Runnable()
  {
    public void run()
    {
      FtTime.this.openCheckJustOnce();
    }
  };
  int count4A2 = 0;
  AlertDialog dialog;
  MyBusiness.MySendListener getDeviceInfoListener = new MyBusiness.MySendListener()
  {
    public void onFail()
    {
    }

    public void onOk(byte[] paramArrayOfByte)
    {
      String str = StringUtils.btye2Str(paramArrayOfByte);
      do
      {
        int i;
        do
        {
          int j;
          int k;
          do
            try
            {
              i = Integer.parseInt(str.substring(34, 36), 16);
              j = 0;
              k = 0;
              if (k >= FtTime.this.savedTimings.size())
                continue;
              if (((Integer)FtTime.this.savedTimings.get(k)).equals(Integer.valueOf(i)))
                j = 1;
              k++;
              break;
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
              return;
            }
          while (j != 0);
          System.out.println("getDeviceInfoListener = " + i + "    mTimingNums.size()=" + FtTime.this.mTimingNums.size());
        }
        while (!FtTime.this.business.saveTimeData(str));
        FtTime.this.savedTimings.add(Integer.valueOf(i));
        FtTime.this.business.responseMessage(str.substring(4, 6), "26");
        FtTime.this.adapter.notifyDataSetChanged();
        FtTime.this.mTimingNums.remove(0);
        if (FtTime.this.mTimingNums.size() != 0)
          break;
        FtTime.this.business.setMySendListener(null);
        FtTime.this.savedTimings.clear();
        FtTime.this.refreshView.refreshFinish(0);
        FtTime.this.handler.removeCallbacks(FtTime.this.runnable);
      }
      while ((FtTime.this.dialog == null) || (!FtTime.this.dialog.isShowing()));
      FtTime.this.dialog.dismiss();
      return;
      FtTime.this.loopGetDeviceInfo();
    }

    public void onTimeOut()
    {
    }
  };
  Handler handler = new Handler(Looper.getMainLooper());
  boolean isRecTimeNumOk;
  List<Timing> justOnceVos = new ArrayList();
  Runnable loopCheckJustOnceTime = new Runnable()
  {
    public void run()
    {
      FtTime.this.checkJustOnce();
    }
  };
  int loopGetDeviceCount = 0;

  @Bind({R.id.lv_act_timing})
  SwipeMenuListView lvActTiming;
  private List<Integer> mTimingNums = new ArrayList();
  private String mac;
  Runnable reSendRunnable = new Runnable()
  {
    public void run()
    {
      if (FtTime.this.mTimingNums.size() > 0)
      {
        if (FtTime.this.reSendTime > 3)
        {
          if (FtTime.this.mTimingNums.size() > 0)
          {
            FtTime.this.mTimingNums.remove(0);
            FtTime.this.loopGetDeviceInfo();
          }
          FtTime.access$502(FtTime.this, 0);
          FtTime.this.handler.removeCallbacks(FtTime.this.reSendRunnable);
        }
      }
      else
        return;
      FtTime.access$508(FtTime.this);
      FtTime.this.business.reSyncDeviceInfo(((Integer)FtTime.this.mTimingNums.get(0)).intValue());
      FtTime.this.handler.removeCallbacks(FtTime.this.reSendRunnable);
      FtTime.this.handler.postDelayed(FtTime.this.reSendRunnable, 2000L);
    }
  };
  private int reSendTime = 0;

  @Bind({2131558584})
  PullToRefreshLayout refreshView;
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      try
      {
        FtTime.this.refreshView.refreshFinish(0);
        if ((FtTime.this.dialog != null) && (FtTime.this.dialog.isShowing()))
          FtTime.this.dialog.dismiss();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        System.out.println("exception...");
      }
    }
  };
  private List<Integer> savedTimings = new ArrayList();
  ExpandableLvSceneBusiness sceneBusiness;
  List<Integer> tempPosi = new ArrayList();
  List<Timing> timingVos;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;

  private void checkJustOnce()
  {
    this.handler.postDelayed(this.loopCheckJustOnceTime, 60000L);
    for (int i = 0; i < this.business.timings.timingList.size(); i++)
    {
      if ((!((Timing)this.business.timings.timingList.get(i)).isJustOnce()) || (!((Timing)this.business.timings.timingList.get(i)).isSwich()) || (((Timing)this.business.timings.timingList.get(i)).getJustOnceCurrentTime() >= System.currentTimeMillis()))
        continue;
      ((Timing)this.business.timings.timingList.get(i)).setSwich(false);
      this.business.putData4ClassName(this.mac, this.business.timings);
      this.adapter.notifyDataSetChanged();
    }
  }

  private void freshTiming()
  {
    this.dialog = ProgressDialog.show(getActivity(), "", getString(2131100002), false);
    this.dialog.setCancelable(true);
    this.dialog.show();
    this.loopGetDeviceCount = 0;
    this.isRecTimeNumOk = false;
    this.mTimingNums.clear();
    this.business.timings.timingList.clear();
    this.adapter.notifyDataSetChanged();
    this.handler.removeCallbacks(this.runnable);
    this.handler.postDelayed(this.runnable, 20000L);
    this.business.queryTimeInfo(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        String str = StringUtils.btye2Str(paramArrayOfByte);
        System.out.println("queryTimeInfo  " + str);
        List localList = FtTime.this.business.compareWithReturnInfo(StringUtils.btye2Str(paramArrayOfByte));
        if (!FtTime.this.isRecTimeNumOk)
        {
          if ((localList != null) && (localList.size() != 0))
          {
            System.out.println("compareWithReturnInfo   list.size() list.size() list.size()    " + localList.size());
            FtTime.this.mTimingNums.addAll(localList);
            FtTime.this.responseMessage(str.substring(4, 6), "22");
            FtTime.this.business.setMySendListener(null);
            FtTime.this.handler.postDelayed(new Runnable()
            {
              public void run()
              {
                FtTime.this.loopGetDeviceInfo();
              }
            }
            , 1000L);
            FtTime.this.isRecTimeNumOk = true;
          }
        }
        else
          return;
        FtTime.this.refreshView.refreshFinish(0);
        FtTime.this.handler.removeCallbacks(FtTime.this.loopCheckJustOnceTime);
        FtTime.this.handler.post(FtTime.this.loopCheckJustOnceTime);
        FtTime.this.handler.removeCallbacks(FtTime.this.runnable);
        if ((FtTime.this.dialog != null) && (FtTime.this.dialog.isShowing()))
          FtTime.this.dialog.dismiss();
        FtTime.this.business.timings.timingList.clear();
        FtTime.this.business.putData4ClassName(FtTime.this.mac, FtTime.this.business.timings);
        FtTime.this.adapter.notifyDataSetChanged();
      }

      public void onTimeOut()
      {
        FtTime.this.refreshView.refreshFinish(0);
      }
    });
  }

  private void initTitle()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtTime.this.finish();
      }
    });
    this.tvTitleViewTitle.setText(R.string.timing);
    this.btnTitleViewEdit.setBackgroundResource(2130903273);
    this.btnTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtTime.this.business.sysTime();
        FtTime.this.startFragmentForResult(new Request(FtAddTime.class).putExtra("TimingOperationKey", "AtTypeAddTiming"), 200);
      }
    });
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtTime.this.business.saveRoomListCacheData();
        FtTime.this.setResult(204);
        FtTime.this.finish();
      }
    });
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
      }
    }
    , 1000L);
  }

  private void loopGetDeviceInfo()
  {
    this.reSendTime = 0;
    if (this.mTimingNums.size() > 0)
    {
      this.business.syncDeviceInfo(this.getDeviceInfoListener, ((Integer)this.mTimingNums.get(0)).intValue());
      this.loopGetDeviceCount = (1 + this.loopGetDeviceCount);
      this.handler.removeCallbacks(this.reSendRunnable);
      this.handler.postDelayed(this.reSendRunnable, 900L);
    }
  }

  private void openCheckJustOnce()
  {
    this.handler.removeCallbacks(this.checkJustOnceThread);
    this.timingVos = this.business.timings.timingList;
    this.justOnceVos.clear();
    this.tempPosi.clear();
    this.checkJustOnce = true;
    for (int i = 0; i < this.timingVos.size(); i++)
    {
      if ((!((Timing)this.timingVos.get(i)).isJustOnce()) || (!((Timing)this.timingVos.get(i)).isSwich()))
        continue;
      this.justOnceVos.add(this.timingVos.get(i));
      this.tempPosi.add(Integer.valueOf(i));
    }
    if (this.justOnceVos.size() == 0)
      return;
    new Thread()
    {
      public void run()
      {
        for (int i = 0; i < FtTime.this.justOnceVos.size(); i++)
        {
          int j = ((Integer)FtTime.this.tempPosi.get(i)).intValue();
          if (System.currentTimeMillis() <= ((Timing)FtTime.this.justOnceVos.get(i)).getJustOnceCurrentTime())
            continue;
          FtTime.this.timingVos.remove(j);
          Timing localTiming = (Timing)FtTime.this.justOnceVos.get(i);
          localTiming.setSwich(false);
          FtTime.this.timingVos.add(j, localTiming);
          FtTime.this.business.timings.timingList.clear();
          FtTime.this.business.timings.timingList.addAll(FtTime.this.timingVos);
          if (FtTime.this.justOnceVos.size() == 1)
            FtTime.this.checkJustOnce = false;
          FtTime.this.justOnceVos.remove(i);
        }
        FtTime.this.getActivity().runOnUiThread(new Runnable()
        {
          public void run()
          {
            FtTime.this.adapter.notifyDataSetChanged();
          }
        });
      }
    }
    .start();
    this.handler.postDelayed(this.checkJustOnceThread, 10000L);
  }

  private void responseMessage(String paramString1, String paramString2)
  {
    this.business.responseMessage(paramString1, paramString2);
  }

  public void onActivate()
  {
    super.onActivate();
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968784, null);
      ButterKnife.bind(this, this.view);
      this.mac = UserFerences.getUserFerences(getActivity().getApplicationContext()).getValue("GateWayMacIdKey");
      setSlideable(false);
      this.business = new TimingBusiness(getActivity());
      this.sceneBusiness = new ExpandableLvSceneBusiness(getActivity());
      this.business.getTimings();
      this.adapter = new TimingListAdapter(getActivity(), this.business.timings.timingList);
      this.adapter.setOnListVSwichChangeListener(new TimingListAdapter.OnListVSwichChangeListener()
      {
        public void onListVSwichChange(boolean paramBoolean, int paramInt)
        {
          FtTime.this.dialog = ProgressDialog.show(FtTime.this.getActivity(), "", FtTime.this.getString(2131100002), false);
          FtTime.this.dialog.setCancelable(true);
          FtTime.this.dialog.show();
          if (paramBoolean)
            FtTime.this.business.sendNewCreateTiming((Timing)FtTime.this.business.timings.timingList.get(paramInt), 34);
          while (true)
          {
            FtTime.this.business.setMySendListener(new MyBusiness.MySendListener()
            {
              public void onFail()
              {
              }

              public void onOk(byte[] paramArrayOfByte)
              {
                if (FtTime.this.dialog.isShowing())
                  FtTime.this.dialog.dismiss();
                FtTime.this.business.setMySendListener(null);
              }

              public void onTimeOut()
              {
              }
            });
            return;
            FtTime.this.business.sendNewCreateTiming((Timing)FtTime.this.business.timings.timingList.get(paramInt), 51);
          }
        }
      });
      this.lvActTiming.setAdapter(this.adapter);
      this.lvActTiming.setMenuCreator(new SwipeMenuCreator()
      {
        public void create(SwipeMenu paramSwipeMenu)
        {
          SwipeMenuItem localSwipeMenuItem1 = new SwipeMenuItem(FtTime.this.getActivity());
          localSwipeMenuItem1.setBackground(new ColorDrawable(FtTime.this.getResources().getColor(R.color.progress_gray)));
          localSwipeMenuItem1.setWidth(BitmapUtils.dp2px(FtTime.this.getActivity(), 45.0F));
          localSwipeMenuItem1.setIcon(2130903240);
          localSwipeMenuItem1.setTitleColor(-1);
          paramSwipeMenu.addMenuItem(localSwipeMenuItem1);
          SwipeMenuItem localSwipeMenuItem2 = new SwipeMenuItem(FtTime.this.getActivity());
          localSwipeMenuItem2.setBackground(new ColorDrawable(FtTime.this.getResources().getColor(2131492897)));
          localSwipeMenuItem2.setWidth(BitmapUtils.dp2px(FtTime.this.getActivity(), 50.0F));
          localSwipeMenuItem2.setIcon(2130903194);
          paramSwipeMenu.addMenuItem(localSwipeMenuItem2);
        }
      });
      this.lvActTiming.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          System.out.println("");
        }
      });
      this.lvActTiming.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
      {
        public boolean onMenuItemClick(int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2)
        {
          if (paramInt2 == 0)
          {
            Timing localTiming = (Timing)FtTime.this.business.timings.timingList.get(paramInt1);
            int i = localTiming.getSeletedScenePosi();
            Scenes localScenes = FtTime.this.sceneBusiness.getSmartScenes();
            int k;
            if ((localScenes.smartScenes != null) && (localScenes.smartScenes.size() > 0))
              k = 0;
            while (k < localScenes.smartScenes.size())
            {
              if (((Scene)localScenes.smartScenes.get(k)).getmNum() == i);
              k++;
              continue;
              ArrayList localArrayList = localTiming.getSelectedDevicesmIndex();
              if ((localArrayList == null) || (localArrayList.size() <= 0))
                break;
              for (int j = 0; j < localArrayList.size(); j++)
                if (!ListUtils.compare(localArrayList, ((Timing)FtTime.this.business.timings.timingList.get(paramInt1)).getSelectedDevicesmIndex()))
                  continue;
            }
            FtTime.this.business.saveTagTimingCacheData(paramInt1);
            Bundle localBundle = new Bundle();
            localBundle.putSerializable("selectDvc", (Serializable)FtTime.this.business.timings.timingList.get(paramInt1));
            FtTime.this.startFragmentForResult(new Request(FtAddTime.class).putExtra("TimingOperationKey", "EditTiming").putExtra("position", paramInt1).putExtra("selectDvc", localBundle), 200);
          }
          if (paramInt2 == 1)
          {
            FtTime.this.dialog = ProgressDialog.show(FtTime.this.getActivity(), "", FtTime.this.getString(2131100002), false);
            FtTime.this.dialog.setCancelable(true);
            FtTime.this.dialog.show();
            FtTime.this.business.sendNewCreateTiming((Timing)FtTime.this.business.timings.timingList.get(paramInt1), 255);
            FtTime.this.business.setMySendListener(new MyBusiness.MySendListener(paramInt1)
            {
              public void onFail()
              {
              }

              public void onOk(byte[] paramArrayOfByte)
              {
                if (FtTime.this.dialog.isShowing())
                  FtTime.this.dialog.dismiss();
                FtTime.this.business.timings.timingList.remove(this.val$position);
                FtTime.this.business.putData4ClassName(FtTime.this.mac, FtTime.this.business.timings);
                FtTime.this.adapter.notifyDataSetChanged();
                FtTime.this.business.setMySendListener(null);
              }

              public void onTimeOut()
              {
                if (FtTime.this.dialog.isShowing())
                  FtTime.this.dialog.dismiss();
                FtTime.this.business.timings.timingList.remove(this.val$position);
                FtTime.this.business.putData4ClassName(FtTime.this.mac, FtTime.this.business.timings);
                FtTime.this.adapter.notifyDataSetChanged();
                FtTime.this.business.setMySendListener(null);
              }
            });
          }
          return false;
        }
      });
      this.refreshView.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener()
      {
        public void onLoadMore(PullToRefreshLayout paramPullToRefreshLayout)
        {
        }

        public void onRefresh(PullToRefreshLayout paramPullToRefreshLayout)
        {
          FtTime.this.freshTiming();
        }
      });
      initTitle();
      freshTiming();
    }
    ButterKnife.bind(this, this.view);
    return this.view;
  }

  public void onDeactivate()
  {
    super.onDeactivate();
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    System.out.println("fttime destroy ");
    this.handler.removeCallbacks(this.reSendRunnable);
    this.handler.removeCallbacks(this.loopCheckJustOnceTime);
    this.handler.removeCallbacks(this.runnable);
    this.handler.removeCallbacks(this.checkJustOnceThread);
    if (this.business != null)
      this.business.setMySendListener(null);
    ButterKnife.unbind(this);
  }

  public void onDetach()
  {
    super.onDetach();
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 202)
    {
      this.adapter.setItemVos(this.business.getTimings4DB().timingList);
      this.adapter.notifyDataSetChanged();
    }
  }

  public void onPause()
  {
    super.onPause();
    this.handler.removeCallbacks(this.loopCheckJustOnceTime);
  }

  public void onResume()
  {
    super.onResume();
    this.handler.post(this.loopCheckJustOnceTime);
  }

  public void onStart()
  {
    super.onStart();
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }

  public void syncTimeInfo(List<Integer> paramList, int paramInt)
  {
    SystemClock.sleep(50L);
    if ((paramList.size() > 0) && (-1 + paramList.size() >= paramInt))
      this.business.syncDeviceInfo(new MyBusiness.MySendListener(paramInt, paramList)
      {
        public void onFail()
        {
          Log.i("", "");
        }

        public void onOk(byte[] paramArrayOfByte)
        {
          String str = StringUtils.btye2Str(paramArrayOfByte);
          boolean bool = FtTime.this.business.saveTimeData(str);
          if (bool)
          {
            FtTime.this.responseMessage(str.substring(4, 6), "26");
            int i = 1 + this.val$i;
            if (this.val$i >= -1 + this.val$list.size())
              break label151;
            FtTime.this.syncTimeInfo(this.val$list, i);
          }
          while (true)
          {
            if (str.substring(18, 20).equalsIgnoreCase("a7"))
            {
              FtTime localFtTime = FtTime.this;
              localFtTime.count4A2 = (1 + localFtTime.count4A2);
            }
            if ((FtTime.this.count4A2 == 4) || (bool))
            {
              FtTime.this.syncTimeInfo(this.val$list, 1 + this.val$i);
              FtTime.this.count4A2 = 0;
            }
            return;
            label151: FtTime.this.refreshView.refreshFinish(0);
            FtTime.this.adapter = new TimingListAdapter(FtTime.this.getActivity(), FtTime.this.business.timings.timingList);
            FtTime.this.lvActTiming.setAdapter(FtTime.this.adapter);
            FtTime.this.adapter.notifyDataSetChanged();
          }
        }

        public void onTimeOut()
        {
          Log.i("", "");
        }
      }
      , ((Integer)paramList.get(paramInt)).intValue());
    if ((paramList.size() > 0) && (paramList.size() >= paramInt));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.time.FtTime
 * JD-Core Version:    0.6.0
 */