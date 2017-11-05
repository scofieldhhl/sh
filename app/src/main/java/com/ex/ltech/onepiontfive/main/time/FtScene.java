package com.ex.ltech.onepiontfive.main.time;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.newscene.ExpandableLvSceneBusiness;
import com.ex.ltech.onepiontfive.main.vo.Scene;
import com.ex.ltech.onepiontfive.main.vo.Scenes;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.Iterator;

public class FtScene extends MyBaseFt
{
  BaseAdapter adapter;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  ExpandableLvSceneBusiness business;

  @Bind({2131558961})
  SwipeMenuListView lv;
  String seletedSceneName = "";
  int seletedSceneNum = 0;
  int seletedScenePosi = -1;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;

  private void initTitle()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtScene.this.setResult(209, new Request().putExtra("TimeScenePosiExtraKey ", FtScene.this.seletedScenePosi).putExtra("TimeSceneNumExtraKey ", FtScene.this.seletedSceneNum).putExtra("TimeSceneNameExtraKey ", FtScene.this.seletedSceneName));
        FtScene.this.finish();
      }
    });
    this.tvTitleViewTitle.setText(2131100391);
    this.business = new ExpandableLvSceneBusiness(getActivity());
    for (int i = 0; i < this.business.getSmartScenes().smartScenes.size(); i++)
      ((Scene)this.business.getSmartScenes().smartScenes.get(i)).setSwich(false);
    this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
      }
    });
    if (this.business.getSmartScenes().smartScenes.size() > 0)
      this.lv.setVisibility(View.VISIBLE);
    if (this.business.getSmartScenes().smartScenes.size() > 1 + getRequest().getIntExtra("sceneOrder", 0));
    try
    {
      this.seletedScenePosi = getRequest().getIntExtra("sceneOrder", 0);
      ((Scene)this.business.getSmartScenes().smartScenes.get(this.seletedScenePosi)).setSwich(true);
      this.seletedSceneNum = ((Scene)this.business.getSmartScenes().smartScenes.get(this.seletedScenePosi)).getmNum();
      this.seletedSceneName = ((Scene)this.business.getSmartScenes().smartScenes.get(this.seletedScenePosi)).getName();
      this.lv.setDividerHeight(0);
      SwipeMenuListView localSwipeMenuListView = this.lv;
      4 local4 = new BaseAdapter()
      {
        public int getCount()
        {
          return FtScene.this.business.getSmartScenes().smartScenes.size();
        }

        public Object getItem(int paramInt)
        {
          return null;
        }

        public long getItemId(int paramInt)
        {
          return 0L;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
        {
          ItemHolder localItemHolder;
          Scene localScene;
          if (paramView == null)
          {
            paramView = LayoutInflater.from(FtScene.this.getActivity()).inflate(2130968816, null);
            localItemHolder = new ItemHolder();
            localItemHolder.condition = ((TextView)paramView.findViewById(2131559111));
            localItemHolder.name = ((TextView)paramView.findViewById(2131559008));
            localItemHolder.icNext = ((TextView)paramView.findViewById(2131558971));
            localItemHolder.status = ((TextView)paramView.findViewById(2131559169));
            localItemHolder.swich = ((Button)paramView.findViewById(2131559047));
            localItemHolder.ic = ((ImageView)paramView.findViewById(2131558883));
            paramView.setTag(localItemHolder);
            localScene = (Scene)FtScene.this.business.getSmartScenes().smartScenes.get(paramInt);
            if ((localScene.getCondition() != null) && (!localScene.getCondition().equals(FtScene.this.getString(2131100083))))
              break label330;
            localItemHolder.ic.setBackgroundResource(2130903762);
            label181: localItemHolder.ic.setBackgroundResource(2130903560);
            localItemHolder.name.setText(localScene.getName());
            if (!localScene.isSwich())
              break label384;
            localItemHolder.swich.setBackgroundResource(2130903786);
          }
          String str1;
          while (true)
          {
            localItemHolder.swich.setOnClickListener(new View.OnClickListener(paramInt)
            {
              public void onClick(View paramView)
              {
                for (int i = 0; i < FtScene.this.business.getSmartScenes().smartScenes.size(); i++)
                  ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(i)).setSwich(false);
                ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(this.val$i)).setSwich(true);
                FtScene.this.seletedScenePosi = this.val$i;
                FtScene.this.seletedSceneNum = ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(this.val$i)).getmNum();
                FtScene.this.seletedSceneName = ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(this.val$i)).getName();
                FtScene.4.this.notifyDataSetChanged();
              }
            });
            paramView.setOnClickListener(new View.OnClickListener(paramInt)
            {
              public void onClick(View paramView)
              {
                for (int i = 0; i < FtScene.this.business.getSmartScenes().smartScenes.size(); i++)
                  ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(i)).setSwich(false);
                ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(this.val$i)).setSwich(true);
                FtScene.this.seletedScenePosi = this.val$i;
                FtScene.this.seletedSceneNum = ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(this.val$i)).getmNum();
                FtScene.this.seletedSceneName = ((Scene)FtScene.this.business.getSmartScenes().smartScenes.get(this.val$i)).getName();
                FtScene.4.this.notifyDataSetChanged();
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
            label330: if ((localScene.getCondition() != null) && (localScene.getCondition().equals(FtScene.this.getString(2131100045))))
            {
              localItemHolder.ic.setBackgroundResource(2130903088);
              break label181;
            }
            localItemHolder.ic.setBackgroundResource(2130903227);
            break label181;
            label384: localItemHolder.swich.setBackgroundResource(2130903784);
          }
          localItemHolder.status.setText(str1);
          return paramView;
        }

        class ItemHolder
        {
          public TextView condition;
          public ImageView ic;
          public TextView icNext;
          public TextView name;
          public TextView status;
          public Button swich;

          ItemHolder()
          {
          }
        }
      };
      this.adapter = local4;
      localSwipeMenuListView.setAdapter(local4);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
      this.view = paramLayoutInflater.inflate(2130968769, null);
    ButterKnife.bind(this, this.view);
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onResume()
  {
    super.onResume();
    initTitle();
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.lv.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramView)
      {
        return false;
      }
    });
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.time.FtScene
 * JD-Core Version:    0.6.0
 */