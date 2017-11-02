package com.ex.ltech.onepiontfive.main.more.GeoFencing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.T;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.newscene.ExpandableLvSceneBusiness;
import com.ex.ltech.onepiontfive.main.vo.Scene;
import com.ex.ltech.onepiontfive.main.vo.Scenes;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.Iterator;

public class FtGeoFencingHomeScene extends MyBaseFt
{
  BaseAdapter baseAdapter;

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  ExpandableLvSceneBusiness business;

  @Bind({2131558961})
  ListView lv;
  String singleSeletedName = "";
  int singleSeletedPosi = -1;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  private View view;

  private void init()
  {
    this.business = new ExpandableLvSceneBusiness(getActivity());
    this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        ((TextView)paramView.findViewById(2131559008));
      }
    });
  }

  private void initView()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131099877);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtGeoFencingHomeScene.this.setResult(4000, FtGeoFencingHomeScene.this.getRequest().putExtra("GeoSceneStrResultKey", FtGeoFencingHomeScene.this.singleSeletedName).putExtra("GeoSceneIndexResultKey", FtGeoFencingHomeScene.this.singleSeletedPosi));
        FtGeoFencingHomeScene.this.finish();
      }
    });
    ListView localListView = this.lv;
    3 local3 = new BaseAdapter()
    {
      private void initializeViews(T paramT, ViewHolder paramViewHolder, int paramInt)
      {
        Scene localScene = (Scene)FtGeoFencingHomeScene.this.business.getSmartScenes().smartScenes.get(paramInt);
        paramViewHolder.name.setText(localScene.getName());
        if ((localScene.getCondition() == null) || (localScene.getCondition().equals(FtGeoFencingHomeScene.this.getString(2131100083))))
          paramViewHolder.ic.setBackgroundResource(2130903762);
        while (true)
        {
          paramViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener(localScene, paramInt)
          {
            public void onClick(View paramView)
            {
              FtGeoFencingHomeScene.this.setResult(4000, FtGeoFencingHomeScene.this.getRequest().putExtra("GeoSceneStrResultKey", this.val$vo.getName()).putExtra("GeoSceneIndexResultKey", this.val$i));
              FtGeoFencingHomeScene.this.finish();
            }
          });
          return;
          if (localScene.getCondition().equals(FtGeoFencingHomeScene.this.getString(2131100045)))
          {
            paramViewHolder.ic.setBackgroundResource(2130903088);
            continue;
          }
          paramViewHolder.ic.setBackgroundResource(2130903227);
        }
      }

      public int getCount()
      {
        return FtGeoFencingHomeScene.this.business.getSmartScenes().smartScenes.size();
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
          paramView = LayoutInflater.from(FtGeoFencingHomeScene.this.getActivity()).inflate(2130968816, null);
          localItemHolder = new ItemHolder();
          localItemHolder.condition = ((TextView)paramView.findViewById(2131559111));
          localItemHolder.name = ((TextView)paramView.findViewById(2131559008));
          localItemHolder.icNext = ((TextView)paramView.findViewById(2131558971));
          localItemHolder.status = ((TextView)paramView.findViewById(2131559169));
          localItemHolder.swich = ((Button)paramView.findViewById(2131559047));
          localItemHolder.ic = ((ImageView)paramView.findViewById(2131558883));
          paramView.setTag(localItemHolder);
          localScene = (Scene)FtGeoFencingHomeScene.this.business.getSmartScenes().smartScenes.get(paramInt);
          if ((localScene.getCondition() != null) && (!localScene.getCondition().equals(FtGeoFencingHomeScene.this.getString(2131100083))))
            break label334;
          localItemHolder.ic.setBackgroundResource(2130903762);
          label181: localItemHolder.ic.setBackgroundResource(2130903560);
          localItemHolder.name.setText(localScene.getName());
          if (!localScene.isSwich())
            break label388;
          localItemHolder.swich.setBackgroundResource(2130903786);
        }
        String str1;
        while (true)
        {
          paramView.setOnClickListener(new View.OnClickListener(paramInt, localScene)
          {
            public void onClick(View paramView)
            {
              for (int i = 0; i < FtGeoFencingHomeScene.this.business.getSmartScenes().smartScenes.size(); i++)
                ((Scene)FtGeoFencingHomeScene.this.business.getSmartScenes().smartScenes.get(i)).setSwich(false);
              ((Scene)FtGeoFencingHomeScene.this.business.getSmartScenes().smartScenes.get(this.val$position)).setSwich(true);
              FtGeoFencingHomeScene.3.this.notifyDataSetChanged();
              FtGeoFencingHomeScene.this.singleSeletedPosi = this.val$position;
              FtGeoFencingHomeScene.this.singleSeletedName = this.val$vo.getName();
            }
          });
          localItemHolder.swich.setOnClickListener(new View.OnClickListener(paramInt, localScene)
          {
            public void onClick(View paramView)
            {
              for (int i = 0; i < FtGeoFencingHomeScene.this.business.getSmartScenes().smartScenes.size(); i++)
                ((Scene)FtGeoFencingHomeScene.this.business.getSmartScenes().smartScenes.get(i)).setSwich(false);
              ((Scene)FtGeoFencingHomeScene.this.business.getSmartScenes().smartScenes.get(this.val$position)).setSwich(true);
              FtGeoFencingHomeScene.3.this.notifyDataSetChanged();
              FtGeoFencingHomeScene.this.singleSeletedPosi = this.val$position;
              FtGeoFencingHomeScene.this.singleSeletedName = this.val$vo.getName();
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
          label334: if ((localScene.getCondition() != null) && (localScene.getCondition().equals(FtGeoFencingHomeScene.this.getString(2131100045))))
          {
            localItemHolder.ic.setBackgroundResource(2130903088);
            break label181;
          }
          localItemHolder.ic.setBackgroundResource(2130903227);
          break label181;
          label388: localItemHolder.swich.setBackgroundResource(2130903784);
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

      class ViewHolder
      {
        private ImageView ic;
        private TextView name;
        private RelativeLayout relativeLayout;

        public ViewHolder(View arg2)
        {
          Object localObject;
          this.ic = ((ImageView)localObject.findViewById(2131558883));
          this.name = ((TextView)localObject.findViewById(2131559008));
          this.relativeLayout = ((RelativeLayout)localObject.findViewById(2131559190));
        }
      }
    };
    this.baseAdapter = local3;
    localListView.setAdapter(local3);
    for (int i = 0; i < this.business.getSmartScenes().smartScenes.size(); i++)
      ((Scene)this.business.getSmartScenes().smartScenes.get(i)).setSwich(false);
    int j = getRequest().getIntExtra("GEO_TOUCH_SCENE_POSI", 0);
    if ((j < this.business.getSmartScenes().smartScenes.size()) && (j != -1))
      ((Scene)this.business.getSmartScenes().smartScenes.get(j)).setSwich(true);
    this.baseAdapter.notifyDataSetChanged();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2130968761, null);
    ButterKnife.bind(this, this.view);
    init();
    initView();
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.GeoFencing.FtGeoFencingHomeScene
 * JD-Core Version:    0.6.0
 */