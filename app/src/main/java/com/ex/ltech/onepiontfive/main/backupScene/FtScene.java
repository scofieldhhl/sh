package com.ex.ltech.onepiontfive.main.backupScene;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.pullfresh.PullToRefreshLayout;
import com.ex.ltech.led.my_view.pullfresh.PullToRefreshLayout.OnRefreshListener;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.AtFragmentMaster;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.newscene.ExpandableLvSceneBusiness;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Scene;
import com.ex.ltech.onepiontfive.main.vo.SceneStep;
import com.ex.ltech.onepiontfive.main.vo.SceneSteps;
import com.ex.ltech.onepiontfive.main.vo.Scenes;
import com.indris.material.RippleView;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class FtScene extends MyBaseFt
{
  Runnable PullRefreshRunnable = new Runnable()
  {
    public void run()
    {
      try
      {
        FtScene.this.mRefreshLayout.refreshFinish(0);
        if ((FtScene.this.dialog != null) && (FtScene.this.dialog.isShowing()))
          FtScene.this.dialog.dismiss();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  };
  Runnable RegetStepRunnable = new Runnable()
  {
    public void run()
    {
      if (FtScene.this.sceneNumlist.size() > FtScene.this.regetStepsIndexsPosi)
      {
        if (FtScene.this.regetStepsTime >= 4)
          break label78;
        FtScene localFtScene2 = FtScene.this;
        localFtScene2.loopGetSceneTime = (-1 + localFtScene2.loopGetSceneTime);
      }
      while (true)
      {
        FtScene.this.loopGetStep(FtScene.this.regetStepsIndexsPosi);
        FtScene localFtScene1 = FtScene.this;
        localFtScene1.regetStepsTime = (1 + localFtScene1.regetStepsTime);
        return;
        label78: FtScene.this.regetStepsTime = 0;
      }
    }
  };
  Runnable TimeoutRunnable = new Runnable()
  {
    public void run()
    {
      if ((FtScene.this.dialog != null) && (FtScene.this.dialog.isShowing()))
        FtScene.this.dialog.dismiss();
      FtScene.this.shortToast(2131100336);
    }
  };
  BaseAdapter adapter;

  @Bind({2131558784})
  RippleView btnTitleViewMenu;
  ExpandableLvSceneBusiness business;
  public int count4A2 = 0;
  Runnable dataRequestTimeoutRunnable = new Runnable()
  {
    public void run()
    {
      if (FtScene.this.loopGetSceneTime < FtScene.this.sceneNumlist.size())
      {
        FtScene.this.sceneNumlist.clear();
        FtScene.this.loopGetSceneTime = 0;
        FtScene.this.business.setMySendListener(null);
        FtScene.this.shortToast(2131100336);
      }
    }
  };
  int delPosi;
  AlertDialog dialog;
  Runnable exceptionRunnable = new Runnable()
  {
    public void run()
    {
      FtScene.this.hideDataRequestDialog();
      FtScene.this.shortToast(2131100336);
    }
  };
  List<Integer> exitsStepIndexList = new ArrayList();
  MyBusiness.MySendListener getStepListener = new MyBusiness.MySendListener()
  {
    public void onFail()
    {
    }

    public void onOk(byte[] paramArrayOfByte)
    {
      String str1 = StringUtils.btye2Str(paramArrayOfByte);
      int i;
      int j;
      int k;
      int m;
      int n;
      String str2;
      Object localObject;
      if ((str1.length() >= 50) && (str1.substring(18, 20).equalsIgnoreCase("a5")))
      {
        i = Integer.parseInt(str1.substring(20, 22), 16);
        j = Integer.parseInt(str1.substring(24, 26), 16);
        k = Integer.parseInt(str1.substring(26, 28), 16);
        m = Integer.parseInt(str1.substring(28, 30), 16);
        n = Integer.parseInt(str1.substring(30, 32), 16);
        Integer.parseInt(str1.substring(32, 34), 16);
        str2 = str1.substring(34, 42);
        localObject = "";
        int i1;
        try
        {
          str1.substring(42, 50);
          String str3 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(str1.substring(50, 98)));
          localObject = str3;
          FtScene.this.responseMessage(str1.substring(4, 6), "25");
          i1 = 0;
          for (int i2 = 0; i2 < FtScene.this.saveSceneOkNumList.size(); i2++)
          {
            if (!((Integer)FtScene.this.saveSceneOkNumList.get(i2)).equals(Integer.valueOf(j)))
              continue;
            i1 = 1;
          }
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
        if (i1 == 0)
          break label253;
      }
      label253: int i5;
      label588: 
      while (true)
      {
        return;
        int i3 = 0;
        for (int i4 = 0; i4 < FtScene.this.exitsStepIndexList.size(); i4++)
        {
          if (!((Integer)FtScene.this.exitsStepIndexList.get(i4)).equals(Integer.valueOf(m)))
            continue;
          i3 = 1;
        }
        if (i3 != 0)
          continue;
        FtScene.this.exitsStepIndexList.add(Integer.valueOf(m));
        if (i == 38);
        for (SceneStep localSceneStep = FtScene.this.business.createSceneStep(str2); ; localSceneStep = FtScene.this.business.createSceneStep(str2))
        {
          if (localSceneStep == null)
            break label588;
          FtScene.this.responseMessage(str1.substring(4, 6), "25");
          if (FtScene.this.scene.getSceneSteps() == null)
            FtScene.this.scene.setSceneSteps(new SceneSteps());
          localSceneStep.setSpaceTime(n);
          FtScene.this.scene.getSceneSteps().steps.add(localSceneStep);
          FtScene.this.scene.setmNum(j);
          if (m == 1)
            FtScene.this.scene.setName((String)localObject);
          if (m != k)
            break;
          FtScene.this.saveSceneOkNumList.add(Integer.valueOf(j));
          i5 = -1;
          for (int i6 = 0; i6 < FtScene.this.business.getSmartScenes().smartScenes.size(); i6++)
          {
            if (j != ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(i6)).getmNum())
              continue;
            FtScene.this.business.getSmartScenes().smartScenes.remove(i6);
            i5 = i6;
          }
        }
      }
      if (i5 != -1)
        FtScene.this.business.getSmartScenes().smartScenes.add(i5, FtScene.this.scene);
      while (true)
      {
        for (int i7 = 0; i7 < FtScene.this.business.getSmartScenes().smartScenes.size(); i7++)
          System.out.println("getSmartScenes name:" + ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(i7)).getName());
        FtScene.this.business.getSmartScenes().smartScenes.add(FtScene.this.scene);
      }
      FragmentActivity localFragmentActivity1 = FtScene.this.getActivity();
      1 local1 = new Runnable()
      {
        public void run()
        {
          FtScene.this.ivNoListIc.setVisibility(8);
          FtScene.this.tvNoListIc.setVisibility(8);
          FtScene.this.adapter.notifyDataSetChanged();
        }
      };
      localFragmentActivity1.runOnUiThread(local1);
      if (FtScene.this.loopGetSceneTime < FtScene.this.sceneNumlist.size())
      {
        FtScene.this.loopGetStep(FtScene.this.loopGetSceneTime);
        return;
      }
      FtScene.this.handler.removeCallbacks(FtScene.this.RegetStepRunnable);
      FtScene.this.handler.removeCallbacks(FtScene.this.dataRequestTimeoutRunnable);
      FtScene.this.handler.removeCallbacks(FtScene.this.PullRefreshRunnable);
      FtScene.this.mRefreshLayout.refreshFinish(0);
      if ((FtScene.this.dialog != null) && (FtScene.this.dialog.isShowing()))
        FtScene.this.dialog.dismiss();
      ArrayList localArrayList = new ArrayList();
      for (int i8 = 0; i8 < FtScene.this.sceneNumlist.size(); i8++)
        for (int i9 = 0; i9 < FtScene.this.business.getSmartScenes().smartScenes.size(); i9++)
        {
          if (((Integer)FtScene.this.sceneNumlist.get(i8)).intValue() != ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(i9)).getmNum())
            continue;
          localArrayList.add(FtScene.this.business.getSmartScenes().smartScenes.get(i9));
        }
      FtScene.this.business.getSmartScenes().smartScenes.clear();
      FtScene.this.business.getSmartScenes().smartScenes.addAll(localArrayList);
      FtScene.this.sceneNumlist.clear();
      FtScene.this.business.setMySendListener(null);
      FragmentActivity localFragmentActivity2 = FtScene.this.getActivity();
      2 local2 = new Runnable()
      {
        public void run()
        {
          FtScene.this.adapter.notifyDataSetChanged();
        }
      };
      localFragmentActivity2.runOnUiThread(local2);
      FtScene.this.business.putData4ClassName(FtScene.this.mac, FtScene.this.business.smartScenes);
    }

    public void onTimeOut()
    {
    }
  };
  Handler handler = new Handler();
  private Home home;
  boolean isDone = false;

  @Bind({2131559261})
  ImageView ivNoListIc;
  private String lastReturnData = "";
  int loopGetSceneTime = 0;

  @Bind({2131558961})
  SwipeMenuListView lv;
  private PullToRefreshLayout mRefreshLayout;
  private String mac;
  int regetStepsIndexsPosi;
  int regetStepsTime;
  List<Integer> saveSceneOkNumList = new ArrayList();
  private Scene scene = new Scene();
  List<Integer> sceneNumlist = new ArrayList();
  int stepsNum = 0;

  @Bind({2131559262})
  TextView tvNoListIc;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;

  private void hideDataRequestDialog()
  {
    if ((this.dialog != null) && (this.dialog.isShowing()))
      this.dialog.dismiss();
  }

  private void initData()
  {
    this.business = new ExpandableLvSceneBusiness(getActivity());
    this.business.setFtScene(this);
    this.lv.setDividerHeight(0);
    this.adapter = new MyBaseAdapter();
    this.lv.setAdapter(this.adapter);
    this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
      }
    });
    if (this.business.getSmartScenes() != null)
    {
      if (this.business.getSmartScenes().smartScenes.size() > 0)
      {
        this.ivNoListIc.setVisibility(8);
        this.tvNoListIc.setVisibility(8);
      }
      this.business.reSortScenes();
      return;
    }
    this.lv.setVisibility(8);
  }

  private void initView()
  {
    this.mRefreshLayout = ((PullToRefreshLayout)this.view.findViewById(2131558584));
    this.mac = UserFerences.getUserFerences(getActivity().getApplicationContext()).getValue("GateWayMacIdKey");
    this.mRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener()
    {
      public void onLoadMore(PullToRefreshLayout paramPullToRefreshLayout)
      {
      }

      public void onRefresh(PullToRefreshLayout paramPullToRefreshLayout)
      {
        FtScene.this.business.getSmartScenes().smartScenes.clear();
        FtScene.this.adapter.notifyDataSetChanged();
        FtScene.this.saveSceneOkNumList.clear();
        FtScene.this.sceneNumlist.clear();
        FtScene.this.dialog = ProgressDialog.show(FtScene.this.getActivity(), "", FtScene.this.getString(2131100002), false);
        if (!FtScene.this.dialog.isShowing())
          FtScene.this.dialog.show();
        FtScene.this.dialog.setCancelable(true);
        FtScene.this.loopGetSceneTime = 0;
        FtScene.this.count4A2 = 0;
        FtScene.this.business.querySceneInfo(new MyBusiness.MySendListener()
        {
          public void onFail()
          {
          }

          public void onOk(byte[] paramArrayOfByte)
          {
            String str = StringUtils.btye2Str(paramArrayOfByte);
            try
            {
              if (!str.substring(18, 20).equalsIgnoreCase("a2"))
                return;
              FtScene.this.sceneNumlist.clear();
              List localList = FtScene.this.sceneNumlist;
              if (FtScene.this.business.compareWithReturnInfo(StringUtils.btye2Str(paramArrayOfByte)) != null);
              for (Object localObject = FtScene.this.business.compareWithReturnInfo(StringUtils.btye2Str(paramArrayOfByte)); ; localObject = new ArrayList())
              {
                localList.addAll((Collection)localObject);
                System.out.println(" querySceneInfo " + FtScene.this.sceneNumlist.size());
                if ((FtScene.this.sceneNumlist == null) || (FtScene.this.sceneNumlist.size() == 0))
                  break;
                FtScene.this.handler.removeCallbacks(FtScene.this.dataRequestTimeoutRunnable);
                FtScene.this.handler.removeCallbacks(FtScene.this.PullRefreshRunnable);
                FtScene.this.handler.postDelayed(FtScene.this.dataRequestTimeoutRunnable, 1000 * (10 * FtScene.this.sceneNumlist.size()));
                FtScene.this.handler.postDelayed(FtScene.this.PullRefreshRunnable, 1000 * (10 * FtScene.this.sceneNumlist.size()));
                FtScene.this.loopGetStep(FtScene.this.loopGetSceneTime);
                return;
              }
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
              return;
            }
            FtScene.this.business.setMySendListener(null);
            FtScene.this.handler.removeCallbacks(FtScene.this.dataRequestTimeoutRunnable);
            FtScene.this.handler.removeCallbacks(FtScene.this.PullRefreshRunnable);
            if ((FtScene.this.dialog != null) && (FtScene.this.dialog.isShowing()))
              FtScene.this.dialog.dismiss();
            FtScene.this.mRefreshLayout.refreshFinish(0);
            FtScene.this.business.getSmartScenes().smartScenes.clear();
            FtScene.this.adapter.notifyDataSetChanged();
            FtScene.this.business.putData4ClassName(DeviceListActivity.deviceMacAddress, new Scenes());
          }

          public void onTimeOut()
          {
          }
        });
      }
    });
    this.lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2)
      {
        if (paramInt2 == 0)
        {
          Intent localIntent = new Intent(FtScene.this.getActivity(), AtFragmentMaster.class);
          localIntent.putExtra("AtTypeKey", "AtTypeScene");
          localIntent.putExtra("SceneNameExtraKey ", ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(paramInt1)).getName());
          localIntent.putExtra("NewSceneCountKey", FtScene.this.business.getSmartScenes().smartScenes.size());
          localIntent.putExtra("SceneEditPosiKey", paramInt1);
          FtScene.this.startActivityForResult(localIntent, 200);
        }
        if (paramInt2 == 1)
        {
          FtScene.this.handler.removeCallbacks(FtScene.this.TimeoutRunnable);
          FtScene.this.handler.postDelayed(FtScene.this.TimeoutRunnable, 5000L);
          FtScene.this.delPosi = paramInt1;
          FtScene.this.business.delPreprea(paramInt1, (Scene)FtScene.this.business.getSmartScenes().smartScenes.get(paramInt1));
          FtScene.this.business.startLoopDelScene((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(paramInt1));
          FtScene.this.dialog = ProgressDialog.show(FtScene.this.getActivity(), "", FtScene.this.getString(2131100002), false);
          FtScene.this.dialog.show();
        }
        return false;
      }
    });
    this.lv.setMenuCreator(new SwipeMenuCreator()
    {
      public void create(SwipeMenu paramSwipeMenu)
      {
        SwipeMenuItem localSwipeMenuItem1 = new SwipeMenuItem(FtScene.this.getActivity());
        localSwipeMenuItem1.setBackground(new ColorDrawable(FtScene.this.getResources().getColor(2131492980)));
        localSwipeMenuItem1.setWidth(BitmapUtils.dp2px(FtScene.this.getActivity(), 40.0F));
        localSwipeMenuItem1.setIcon(2130903240);
        localSwipeMenuItem1.setTitleColor(-1);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem1);
        SwipeMenuItem localSwipeMenuItem2 = new SwipeMenuItem(FtScene.this.getActivity());
        localSwipeMenuItem2.setBackground(new ColorDrawable(FtScene.this.getResources().getColor(2131492897)));
        localSwipeMenuItem2.setWidth(BitmapUtils.dp2px(FtScene.this.getActivity(), 45.0F));
        localSwipeMenuItem2.setIcon(2130903194);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem2);
      }
    });
    this.btnTitleViewMenu.setBackgroundResource(2130903273);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (FtScene.this.adapter.getCount() > 7)
        {
          Toast.makeText(FtScene.this.getActivity(), 2131100365, 0).show();
          return;
        }
        Intent localIntent = new Intent(FtScene.this.getActivity(), AtFragmentMaster.class);
        localIntent.putExtra("AtTypeKey", "AtTypeScene");
        localIntent.putExtra("SceneNameExtraKey ", "");
        localIntent.putExtra("NewSceneCountKey", FtScene.this.business.getSmartScenes().smartScenes.size());
        localIntent.putExtra("SceneEditPosiKey", -1);
        FtScene.this.startActivityForResult(localIntent, 200);
      }
    });
    this.tvTitleViewTitle.setText(2131100408);
  }

  private void loopGetStep(int paramInt)
  {
    this.regetStepsIndexsPosi = paramInt;
    synSceneStep(((Integer)this.sceneNumlist.get(paramInt)).intValue());
    this.loopGetSceneTime = (1 + this.loopGetSceneTime);
    this.handler.removeCallbacks(this.RegetStepRunnable);
    this.handler.postDelayed(this.RegetStepRunnable, 10000L);
  }

  private void responseMessage(String paramString1, String paramString2)
  {
    this.business.responseMessage(paramString1, paramString2);
  }

  private void synSceneStep(int paramInt)
  {
    this.scene = new Scene();
    this.exitsStepIndexList.clear();
    this.business.syncSceneStep(this.getStepListener, paramInt);
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }

  public void onCreateSceneOk(int paramInt)
  {
    if (this.dialog.isShowing())
      this.dialog.dismiss();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
      this.view = paramLayoutInflater.inflate(2130968776, null);
    ButterKnife.bind(this, this.view);
    return this.view;
  }

  public void onDelSceneOk()
  {
    if (this.dialog.isShowing())
      this.dialog.dismiss();
    this.handler.removeCallbacks(this.TimeoutRunnable);
    this.business.delScene(this.delPosi);
    this.adapter.notifyDataSetChanged();
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    System.out.println("ftScene destroyView");
    this.handler.removeCallbacks(this.RegetStepRunnable);
    this.handler.removeCallbacks(this.exceptionRunnable);
    this.handler.removeCallbacks(this.dataRequestTimeoutRunnable);
    this.handler.removeCallbacks(this.PullRefreshRunnable);
    if (this.business != null)
      this.business.setMySendListener(null);
    ButterKnife.unbind(this);
  }

  public void onResume()
  {
    super.onResume();
    initData();
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    initView();
  }

  class MyBaseAdapter extends BaseAdapter
  {
    MyBaseAdapter()
    {
    }

    public int getCount()
    {
      return FtScene.this.business.getSmartScenes().smartScenes.size();
    }

    public Object getItem(int paramInt)
    {
      return FtScene.this.business.getSmartScenes().smartScenes.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      ItemHolder localItemHolder;
      Scene localScene;
      if (paramView == null)
      {
        paramView = LayoutInflater.from(FtScene.this.getActivity()).inflate(2130968815, null);
        localItemHolder = new ItemHolder();
        localItemHolder.ll_sensor_touch = ((RelativeLayout)paramView.findViewById(2131559356));
        localItemHolder.ll_hand_touch = ((LinearLayout)paramView.findViewById(2131559357));
        localItemHolder.ll_hand_touch_bottom = ((LinearLayout)paramView.findViewById(2131559359));
        localItemHolder.condition = ((TextView)paramView.findViewById(2131559111));
        localItemHolder.btn_hand_touch_run = ((Button)paramView.findViewById(2131559354));
        localItemHolder.name = ((TextView)paramView.findViewById(2131559008));
        localItemHolder.name_hand_touch = ((TextView)paramView.findViewById(2131559358));
        localItemHolder.icNext = ((TextView)paramView.findViewById(2131558971));
        localItemHolder.status = ((TextView)paramView.findViewById(2131559169));
        localItemHolder.swich = ((ToggleButton)paramView.findViewById(2131559047));
        localItemHolder.ic = ((ImageView)paramView.findViewById(2131558883));
        paramView.setTag(localItemHolder);
        localScene = (Scene)FtScene.this.business.getSmartScenes().smartScenes.get(paramInt);
        if ((localScene.getCondition() != null) && (!localScene.getCondition().equals(FtScene.this.getString(2131100083))))
          break label464;
        localItemHolder.ll_hand_touch.setVisibility(0);
        localItemHolder.ll_sensor_touch.setVisibility(8);
        localItemHolder.ic.setBackgroundResource(2130903762);
        if (-1 + getCount() != paramInt)
          break label451;
        localItemHolder.ll_hand_touch_bottom.setVisibility(0);
        label289: localItemHolder.swich.setListViewItemPosi(paramInt);
        localItemHolder.name.setText(localScene.getName());
        localItemHolder.name_hand_touch.setText(localScene.getName());
        if (!localScene.isSwich())
          break label568;
        localItemHolder.swich.setToggleOn();
      }
      String str1;
      while (true)
      {
        localItemHolder.btn_hand_touch_run.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            FtScene.this.business.isCreateNewScene = false;
            FtScene.this.dialog = ProgressDialog.show(FtScene.this.getActivity(), "", FtScene.this.getString(2131100002), false);
            FtScene.this.dialog.show();
            FtScene.this.handler.removeCallbacks(FtScene.this.exceptionRunnable);
            FtScene.this.handler.postDelayed(FtScene.this.exceptionRunnable, 2000L);
            FtScene.this.business.sendExitsScene(new MyBusiness.MySendListener()
            {
              public void onFail()
              {
                FtScene.this.hideDataRequestDialog();
                FtScene.this.handler.removeCallbacks(FtScene.this.exceptionRunnable);
              }

              public void onOk(byte[] paramArrayOfByte)
              {
                if (StringUtils.btye2Str(paramArrayOfByte).substring(18, 20).equalsIgnoreCase("B1"))
                {
                  FtScene.this.business.setMySendListener(null);
                  FtScene.this.hideDataRequestDialog();
                  FtScene.this.handler.removeCallbacks(FtScene.this.exceptionRunnable);
                }
              }

              public void onTimeOut()
              {
                FtScene.this.shortToast(2131100336);
                FtScene.this.hideDataRequestDialog();
                FtScene.this.handler.removeCallbacks(FtScene.this.exceptionRunnable);
              }
            }
            , ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(this.val$i)).getmNum());
          }
        });
        localItemHolder.swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
        {
          public void onToggleInListView(boolean paramBoolean, int paramInt)
          {
            if (paramBoolean)
            {
              FtScene.this.dialog = ProgressDialog.show(FtScene.this.getActivity(), "", FtScene.this.getString(2131100002), false);
              FtScene.this.dialog.show();
              FtScene.this.business.isCreateNewScene = false;
              FtScene.this.business.sendPreprea(paramInt, (Scene)FtScene.this.business.getSmartScenes().smartScenes.get(paramInt));
              FtScene.this.business.startLoopCreateScene((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(paramInt));
            }
          }
        });
        str1 = "";
        Iterator localIterator = localScene.getRoomNames().iterator();
        while (localIterator.hasNext())
        {
          String str2 = (String)localIterator.next();
          str1 = str1 + str2 + "\t\t";
        }
        localItemHolder = (ItemHolder)paramView.getTag();
        break;
        label451: localItemHolder.ll_hand_touch_bottom.setVisibility(8);
        break label289;
        label464: if (localScene.getCondition().equals(FtScene.this.getString(2131100045)))
        {
          localItemHolder.ll_hand_touch.setVisibility(8);
          localItemHolder.ll_sensor_touch.setVisibility(0);
          localItemHolder.ic.setBackgroundResource(2130903227);
          localItemHolder.ll_hand_touch_bottom.setVisibility(8);
          break label289;
        }
        localItemHolder.ll_hand_touch.setVisibility(8);
        localItemHolder.ll_sensor_touch.setVisibility(0);
        localItemHolder.ic.setBackgroundResource(2130903731);
        localItemHolder.ll_hand_touch_bottom.setVisibility(8);
        break label289;
        label568: localItemHolder.swich.setToggleOff();
      }
      localItemHolder.status.setText(str1);
      return paramView;
    }

    class ItemHolder
    {
      Button btn_hand_touch_run;
      public TextView condition;
      public ImageView ic;
      public TextView icNext;
      public LinearLayout ll_hand_touch;
      public LinearLayout ll_hand_touch_bottom;
      public RelativeLayout ll_sensor_touch;
      public TextView name;
      public TextView name_hand_touch;
      public TextView status;
      public ToggleButton swich;

      ItemHolder()
      {
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.backupScene.FtScene
 * JD-Core Version:    0.6.0
 */