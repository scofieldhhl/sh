package com.ex.ltech.onepiontfive.main.newscene;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.remote.control.scene.CacheSceneData;
import com.ex.ltech.remote.control.vo.YkVo;
import com.ex.ltech.remote.control.yaokong.AtSaveYongkongList;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import java.util.List;

public class FtAddExecutiveTask extends MyBaseFt
  implements OnSwichOrItemClickListener
{
  AddExecutiveTaskListAdapter adapter;

  @Bind({2131558785})
  TextView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  ExpandableLvSceneBusiness business;
  int devicePosi;

  @Bind({2131559163})
  ExpandableListView expendlist;
  int roomPosi;
  int sceneRemoteType;

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
        FtAddExecutiveTask.this.finish();
      }
    });
    this.tvTitleViewTitle.setText(2131099853);
    this.btnTitleViewEdit.setVisibility(0);
    this.btnTitleViewEdit.setTextColor(-65536);
    this.btnTitleViewEdit.setText(2131100063);
    this.btnTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        new Thread()
        {
          public void run()
          {
          }
        }
        .start();
        CacheSceneData.ykVos.clear();
        FtAddExecutiveTask.this.business.saveStep();
        FtAddExecutiveTask.this.setResult(210);
        FtAddExecutiveTask.this.finish();
      }
    });
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 200)
    {
      List localList = CacheSceneData.ykVos;
      this.business.saveYK2SceneInnerDvc(this.roomPosi, this.devicePosi, localList);
      this.business.onDataResult(this.roomPosi, this.devicePosi, ((YkVo)localList.get(-1 + localList.size())).getType(), ((YkVo)localList.get(-1 + localList.size())).getName(), true);
      this.adapter.notifyDataSetChanged();
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968777, null);
      ButterKnife.bind(this, this.view);
      this.business = new ExpandableLvSceneBusiness(getActivity());
      this.adapter = new AddExecutiveTaskListAdapter(getActivity(), this, this.business.getAddTaskListData());
      this.expendlist.setAdapter(this.adapter);
      initTitle();
      this.expendlist.setDividerHeight(0);
      this.expendlist.setGroupIndicator(null);
    }
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if ((paramInt2 != -1) || (paramInt2 == 202))
      finish();
  }

  public void onItemClick(int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      if (this.business.getYK4SceneInnerDvc(paramInt1, paramInt2).size() == 0)
        CacheSceneData.ykVos.clear();
      while (true)
      {
        this.roomPosi = paramInt1;
        this.devicePosi = paramInt2;
        startActivityForResult(new Intent(getActivity(), AtSaveYongkongList.class), 0);
        return;
        CacheSceneData.ykVos.addAll(this.business.getYK4SceneInnerDvc(paramInt1, paramInt2));
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      while (true)
        localNullPointerException.printStackTrace();
    }
  }

  public void onSwich(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (!this.business.onDeviceSwich(paramInt1, paramInt2, paramBoolean))
      Toast.makeText(getActivity(), 2131099945, 0).show();
    this.adapter.notifyDataSetChanged();
  }

  public void onToggleSwich(String paramString)
  {
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.FtAddExecutiveTask
 * JD-Core Version:    0.6.0
 */