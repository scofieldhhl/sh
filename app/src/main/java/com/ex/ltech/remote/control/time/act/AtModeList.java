package com.ex.ltech.remote.control.time.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ex.ltech.led.acti.MyBaseActivity;

public class AtModeList extends MyBaseActivity
{
  private ListView lv;
  int[] nameRes = { 2131099860, 2131099861, 2131099862, 2131099863, 2131099864 };

  private void findView()
  {
    this.lv = ((ListView)findViewById(R.id.lv_act_repeat_day));
  }

  private void init()
  {
    this.lv.setAdapter(new BaseAdapter()
    {
      int[] picRes = { 2130903724, 2130903724, 2130903724, 2130903724, 2130903724, 2130903724, 2130903724 };

      public int getCount()
      {
        return 5;
      }

      public Object getItem(int paramInt)
      {
        return Integer.valueOf(0);
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
          AtModeList.this.getLayoutInflater();
          paramView = LayoutInflater.from(AtModeList.this).inflate(2130968825, null);
          localHolder.name = ((TextView)paramView.findViewById(2131559008));
          localHolder.ic = ((ImageView)paramView.findViewById(2131558883));
          paramView.setTag(localHolder);
        }
        while (true)
        {
          localHolder.name.setText(AtModeList.this.getString(AtModeList.this.nameRes[paramInt]));
          localHolder.ic.setVisibility(View.GONE);
          return paramView;
          localHolder = (Holder)paramView.getTag();
        }
      }

      class Holder
      {
        ImageView ic;
        TextView name;

        Holder()
        {
        }
      }
    });
    this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        switch (paramInt)
        {
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        }
        String str = AtModeList.this.getString(AtModeList.this.nameRes[paramInt]);
        Intent localIntent = new Intent();
        localIntent.putExtra("mode", str);
        AtModeList.this.setResult(200, localIntent);
        AtModeList.this.finish();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.act_repeat_day);
    findView();
    setTitleView();
    init();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleTextRes(R.string.mode);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.time.act.AtModeList
 * JD-Core Version:    0.6.0
 */