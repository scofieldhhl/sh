package com.ex.ltech.onepiontfive.main.newscene;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;

public class FtInitCondition extends MyBaseFt
  implements OnSwichOrItemClickListener
{
  InitConditionAdapter adapter;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;

  public void initTitleView()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131100392);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtInitCondition.this.finish();
      }
    });
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2130968752, null);
    ButterKnife.bind(this, this.view);
    initTitleView();
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
    if (paramInt2 == 212)
    {
      setResult(212, paramRequest.putExtra("ConditionExtraKey", getString(2131099885)));
      finish();
    }
  }

  public void onItemClick(int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onSwich(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    startFragmentForResult(new Request(FtFinishAddScene.class).putExtra("ConditionExtraKey", this.adapter.getBodySensorCondition(paramInt2)), 200);
  }

  public void onToggleSwich(String paramString)
  {
    startFragmentForResult(new Request(FtFinishAddScene.class).putExtra("ConditionExtraKey", paramString), 200);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    ExpandableListView localExpandableListView = (ExpandableListView)paramView.findViewById(2131559163);
    localExpandableListView.setDividerHeight(0);
    InitConditionAdapter localInitConditionAdapter = new InitConditionAdapter(getActivity(), this);
    this.adapter = localInitConditionAdapter;
    localExpandableListView.setAdapter(localInitConditionAdapter);
    localExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
    {
      public boolean onGroupClick(ExpandableListView paramExpandableListView, View paramView, int paramInt, long paramLong)
      {
        if (paramInt != 1)
        {
          FtInitCondition.this.setResult(211, FtInitCondition.this.getRequest().putExtra("ConditionExtraKey", ((TextView)paramView.findViewById(2131559008)).getText().toString()).putExtra("NewSceneCountKey", FtInitCondition.this.getRequest().getIntExtra("NewSceneCountKey", 0)));
          FtInitCondition.this.finish();
          return false;
        }
        FtInitCondition.this.startFragmentForResult(new Request(FtChooseSensor.class).putExtra("NewSceneCountKey", FtInitCondition.this.getRequest().getIntExtra("NewSceneCountKey", 0)), 0);
        return false;
      }
    });
    localExpandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
      }
    });
    localExpandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        if (paramInt == 1);
        return false;
      }
    });
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.FtInitCondition
 * JD-Core Version:    0.6.0
 */