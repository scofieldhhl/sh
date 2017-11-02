package com.ex.ltech.onepiontfive.main.room.panel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import java.util.List;

public class FtPanelAssociatedAdapter extends BaseAdapter
{
  private Callback callback;
  private int chosenIndex = 0;
  private Context context;
  private int history = -1;
  private int index = -1;
  private List<Dvc> lights;

  public FtPanelAssociatedAdapter(Activity paramActivity, List<Dvc> paramList, Callback paramCallback, int paramInt)
  {
    this.context = paramActivity;
    this.lights = paramList;
    this.callback = paramCallback;
    this.chosenIndex = paramInt;
  }

  private void initCheck(Holder paramHolder)
  {
  }

  private void initializeViews(Dvc paramDvc, Holder paramHolder, View paramView, int paramInt)
  {
    switch (paramDvc.getType())
    {
    default:
    case 8:
    case 12:
    case 9:
    case 11:
    case 10:
    }
    while (true)
    {
      paramHolder.tv_item_description.setText(paramDvc.getName());
      return;
      paramHolder.iv_item_icon.setBackgroundResource(2130903700);
      continue;
      paramHolder.iv_item_icon.setBackgroundResource(2130903701);
      continue;
      paramHolder.iv_item_icon.setBackgroundResource(2130903805);
      continue;
      paramHolder.iv_item_icon.setBackgroundResource(2130903085);
      continue;
      paramHolder.iv_item_icon.setBackgroundResource(2130903142);
    }
  }

  public int getCount()
  {
    return this.lights.size();
  }

  public Object getItem(int paramInt)
  {
    return this.lights.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Holder localHolder;
    if (paramView == null)
    {
      localHolder = new Holder();
      paramView = LayoutInflater.from(this.context).inflate(2130968771, null);
      localHolder.ll_panel_associated = ((LinearLayout)paramView.findViewById(2131559218));
      localHolder.rb_item_association = ((TextView)paramView.findViewById(2131559221));
      localHolder.iv_item_icon = ((ImageView)paramView.findViewById(2131559219));
      localHolder.tv_item_description = ((TextView)paramView.findViewById(2131559220));
      paramView.setTag(localHolder);
    }
    while (true)
    {
      int i = ((Dvc)this.lights.get(paramInt)).getType();
      if ((i != 8) && (i != 12) && (i != 9) && (i != 10) && (i != 11))
        paramView.setVisibility(4);
      initializeViews((Dvc)getItem(paramInt), (Holder)paramView.getTag(), paramView, paramInt);
      if (((Dvc)this.lights.get(paramInt)).getmIndex() == this.chosenIndex)
      {
        this.index = ((Dvc)this.lights.get(paramInt)).getmIndex();
        initCheck((Holder)paramView.getTag());
      }
      localHolder.ll_panel_associated.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          FtPanelAssociatedAdapter.access$002(FtPanelAssociatedAdapter.this, ((Dvc)FtPanelAssociatedAdapter.this.lights.get(this.val$i)).getmIndex());
          FtPanelAssociatedAdapter.this.callback.click((Dvc)FtPanelAssociatedAdapter.this.lights.get(this.val$i), this.val$i);
          FtPanelAssociatedAdapter.access$302(FtPanelAssociatedAdapter.this, ((Dvc)FtPanelAssociatedAdapter.this.lights.get(this.val$i)).getmIndex());
        }
      });
      localHolder.rb_item_association.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          FtPanelAssociatedAdapter.access$002(FtPanelAssociatedAdapter.this, ((Dvc)FtPanelAssociatedAdapter.this.lights.get(this.val$i)).getmIndex());
          FtPanelAssociatedAdapter.this.callback.click((Dvc)FtPanelAssociatedAdapter.this.lights.get(this.val$i), this.val$i);
          FtPanelAssociatedAdapter.access$302(FtPanelAssociatedAdapter.this, ((Dvc)FtPanelAssociatedAdapter.this.lights.get(this.val$i)).getmIndex());
        }
      });
      if (!((Dvc)this.lights.get(paramInt)).isRelation())
        break;
      localHolder.rb_item_association.setBackgroundResource(2130903786);
      return paramView;
      localHolder = (Holder)paramView.getTag();
    }
    localHolder.rb_item_association.setBackgroundResource(2130903784);
    return paramView;
  }

  public static abstract interface Callback
  {
    public abstract void click(View paramView, Dvc paramDvc, int paramInt);

    public abstract void click(Dvc paramDvc, int paramInt);
  }

  class Holder
  {
    ImageView iv_item_icon;
    LinearLayout ll_panel_associated;
    TextView rb_item_association;
    TextView tv_item_description;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.panel.FtPanelAssociatedAdapter
 * JD-Core Version:    0.6.0
 */