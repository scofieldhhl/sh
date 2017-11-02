package com.ex.ltech.onepiontfive.main.newscene;

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
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Scene;
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
        FtScene.this.loopSceneName(FtScene.this.regetStepsIndexsPosi);
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
  Handler handler = new Handler();
  private Home home;

  @Bind({2131559261})
  ImageView ivNoListIc;
  int loopGetSceneTime = 0;
  MyBusiness.MySendListener loopSceneNameListener = new MyBusiness.MySendListener()
  {
    public void onFail()
    {
    }

    public void onOk(byte[] paramArrayOfByte)
    {
      String str1 = StringUtils.btye2Str(paramArrayOfByte);
      String str2 = str1.substring(18, 20);
      int i = Integer.parseInt(str1.substring(28, 30), 16);
      String str3;
      if (str2.equalsIgnoreCase("AD"))
      {
        System.out.println("synSceneNum  = " + i);
        str3 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(str1.substring(30, 78)));
        int j = 0;
        for (int k = 0; k < FtScene.this.saveSceneOkNumList.size(); k++)
        {
          if (!((Integer)FtScene.this.saveSceneOkNumList.get(k)).equals(Integer.valueOf(i)))
            continue;
          j = 1;
        }
        if (j == 0);
      }
      else
      {
        return;
      }
      FtScene.this.scene.setmNum(i);
      FtScene.this.scene.setName(str3);
      FtScene.this.saveSceneOkNumList.add(Integer.valueOf(i));
      FtScene.this.business.getSmartScenes().smartScenes.add(FtScene.this.scene);
      FtScene.this.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          FtScene.this.ivNoListIc.setVisibility(8);
          FtScene.this.tvNoListIc.setVisibility(8);
          FtScene.this.adapter.notifyDataSetChanged();
        }
      });
      if (FtScene.this.loopGetSceneTime < FtScene.this.sceneNumlist.size())
      {
        FtScene.this.loopSceneName(FtScene.this.loopGetSceneTime);
        return;
      }
      FtScene.this.handler.removeCallbacks(FtScene.this.RegetStepRunnable);
      FtScene.this.handler.removeCallbacks(FtScene.this.dataRequestTimeoutRunnable);
      FtScene.this.handler.removeCallbacks(FtScene.this.PullRefreshRunnable);
      FtScene.this.mRefreshLayout.refreshFinish(0);
      if ((FtScene.this.dialog != null) && (FtScene.this.dialog.isShowing()))
        FtScene.this.dialog.dismiss();
      FtScene.this.sceneNumlist.clear();
      FtScene.this.business.setMySendListener(null);
      FtScene.this.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          FtScene.this.adapter.notifyDataSetChanged();
        }
      });
      FtScene.this.business.putData4ClassName(FtScene.this.mac, FtScene.this.business.smartScenes);
    }

    public void onTimeOut()
    {
    }
  };

  @Bind({2131558961})
  SwipeMenuListView lv;
  private PullToRefreshLayout mRefreshLayout;
  private String mac;
  int regetStepsIndexsPosi;
  int regetStepsTime;
  List<Integer> saveSceneOkNumList = new ArrayList();
  private Scene scene = new Scene();
  List<Integer> sceneNumlist = new ArrayList();

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
                FtScene.this.loopSceneName(FtScene.this.loopGetSceneTime);
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
          localIntent.putExtra("SceneNumExtraKey ", ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(paramInt1)).getmNum());
          localIntent.putExtra("SceneEditPosiKey", paramInt1);
          FtScene.this.startActivityForResult(localIntent, 200);
        }
        if (paramInt2 == 1)
        {
          FtScene.this.handler.removeCallbacks(FtScene.this.TimeoutRunnable);
          FtScene.this.handler.postDelayed(FtScene.this.TimeoutRunnable, 5000L);
          FtScene.this.delPosi = paramInt1;
          FtScene.this.business.sendDelScene(((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(paramInt1)).getmNum());
          FtScene.this.business.setMySendListener(new MyBusiness.MySendListener()
          {
            public void onFail()
            {
            }

            public void onOk(byte[] paramArrayOfByte)
            {
              if ((paramArrayOfByte.length == 19) && (StringUtils.btye2Str(paramArrayOfByte).substring(18, 20).equalsIgnoreCase("B0")))
              {
                FtScene.this.business.setMySendListener(null);
                FtScene.this.onDelSceneOk();
              }
              System.out.println("sendDelScene data3=" + StringUtils.btye2Str3(paramArrayOfByte));
            }

            public void onTimeOut()
            {
            }
          });
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
        localIntent.putExtra("SceneNumExtraKey ", -1);
        localIntent.putExtra("SceneEditPosiKey", -1);
        FtScene.this.startActivityForResult(localIntent, 200);
      }
    });
    this.tvTitleViewTitle.setText(2131100408);
  }

  private void loopSceneName(int paramInt)
  {
    this.regetStepsIndexsPosi = paramInt;
    synSceneName(((Integer)this.sceneNumlist.get(paramInt)).intValue());
    this.loopGetSceneTime = (1 + this.loopGetSceneTime);
    this.handler.removeCallbacks(this.RegetStepRunnable);
    this.handler.postDelayed(this.RegetStepRunnable, 10000L);
  }

  private void responseMessage(String paramString1, String paramString2)
  {
    this.business.responseMessage(paramString1, paramString2);
  }

  private void synSceneName(int paramInt)
  {
    this.scene = new Scene();
    this.exitsStepIndexList.clear();
    this.business.queryName(3, 0, paramInt, "1".getBytes(), false);
    this.business.setMySendListener(this.loopSceneNameListener);
    System.out.println("loopGetStep stepsIndexsPosi = " + paramInt);
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
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.FtScene
 * JD-Core Version:    0.6.0
 */