package com.ex.ltech.onepiontfive.main.room.panel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.zcw.togglebutton.ToggleButton;
import java.util.List;

public class FtPanelAdapter extends BaseAdapter
{
  private Callback callback;
  private Context context;
  private List<Dvc> lights;

  public FtPanelAdapter(Activity paramActivity, List<Dvc> paramList, Callback paramCallback)
  {
    this.context = paramActivity;
    this.lights = paramList;
    this.callback = paramCallback;
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
      paramView = LayoutInflater.from(this.context).inflate(2130968756, null);
      localHolder.tblightswitch = ((ToggleButton)paramView.findViewById(2131559170));
      localHolder.btnEdit = ((Button)paramView.findViewById(2131558955));
      localHolder.ivEdit = ((ImageView)paramView.findViewById(2131559172));
      paramView.setTag(localHolder);
    }
    while (true)
    {
      localHolder.lampType.setText(((Dvc)this.lights.get(paramInt)).getName());
      localHolder.btnConnect.setOnClickListener(new View.OnClickListener(localHolder)
      {
        public void onClick(View paramView)
        {
          this.val$holder.ivRelationCircle.setVisibility(0);
          this.val$holder.tblightswitch.setVisibility(8);
          this.val$holder.ivConnect.setVisibility(8);
          this.val$holder.btnConnect.setVisibility(8);
        }
      });
      localHolder.btnEdit.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          FtPanelAdapter.this.callback.click(paramView);
        }
      });
      return paramView;
      localHolder = (Holder)paramView.getTag();
    }
  }

  public static abstract interface Callback
  {
    public abstract void click(View paramView);
  }

  class Holder
  {
    Button btnConnect;
    Button btnEdit;
    ImageView ivConnect;
    ImageView ivEdit;
    ImageView ivRelationCircle;
    TextView lampConnect;
    ImageView lampPic;
    TextView lampType;
    ToggleButton tblightswitch;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.panel.FtPanelAdapter
 * JD-Core Version:    0.6.0
 */