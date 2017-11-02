package com.ex.ltech.onepiontfive.main.newscene;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.vo.SceneSteps;
import com.fragmentmaster.app.MasterFragment;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;

public class FtAddScene extends MasterFragment
  implements View.OnClickListener
{

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;

  @Bind({2131559111})
  TextView condition;

  @Bind({2131559147})
  ImageView go1;

  @Bind({2131559150})
  ImageView go2;
  public String mac;

  @Bind({2131559151})
  TextView result;

  @Bind({2131559145})
  RelativeLayout rl1;

  @Bind({2131559148})
  RelativeLayout rl2;

  @Bind({2131558993})
  RelativeLayout rlTitle;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;

  public void initTitleView()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131099850);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtAddScene.this.finish();
        new MyBusiness(FtAddScene.this.getActivity()).putData4ClassName(FtAddScene.this.mac, new SceneSteps());
      }
    });
    this.btnTitleViewEdit.setVisibility(8);
  }

  public void onClick(View paramView)
  {
    if (paramView == this.rl1)
      startFragmentForResult(new Request(FtInitCondition.class).putExtra("NewSceneCountKey", getRequest().getIntExtra("NewSceneCountKey", 0)), 200);
    if (paramView == this.rl2)
      startFragmentForResult(new Request(FtAddExecutiveTask.class), 200);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130968743, null);
    ButterKnife.bind(this, localView);
    this.mac = UserFerences.getUserFerences(getActivity().getApplicationContext()).getValue("GateWayMacIdKey");
    initTitleView();
    return localView;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 202)
    {
      new MyBusiness(getActivity()).putData4ClassName(this.mac, new SceneSteps());
      finish();
    }
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.rl1.setOnClickListener(this);
    this.rl2.setOnClickListener(this);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.FtAddScene
 * JD-Core Version:    0.6.0
 */