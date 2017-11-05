package com.ex.ltech.onepiontfive.main.room.panel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import java.util.List;

public class FtPanelEditAdapter extends BaseAdapter
{
  private Callback callback;
  private Context context;
  private List<Dvc> devices;

  public FtPanelEditAdapter(Activity paramActivity, List<Dvc> paramList, Callback paramCallback)
  {
    this.context = paramActivity;
    this.devices = paramList;
    this.callback = paramCallback;
  }

  private void initializeViews(Dvc paramDvc, Holder paramHolder, View paramView, int paramInt)
  {
    int i = 1;
    int j;
    int k;
    label29: int n;
    label49: label65: label78: int i2;
    if (paramDvc.getType() == 10)
    {
      j = i;
      if (paramDvc.getType() != 11)
        break label225;
      k = i;
      int m = k | j;
      if (paramDvc.getType() != 9)
        break label231;
      n = i;
      int i1 = n | m;
      if (paramDvc.getType() != 8)
        break label237;
      if ((i1 | i) == 0)
        break label243;
      paramView.setVisibility(View.VISIBLE);
      paramHolder.tvLampNameEdit.setText(paramDvc.getName());
      paramHolder.sb_detail_play_progress.setProgress(paramDvc.getBrtProgress());
      if (!paramDvc.isOnLine())
        break label307;
      paramHolder.ibLampChose.setVisibility(View.VISIBLE);
      ImageButton localImageButton = paramHolder.ibLampChose;
      if (!paramDvc.isSeletedRelation())
        break label252;
      i2 = 2130903786;
      label132: localImageButton.setBackgroundResource(i2);
      paramHolder.ib_lamp_offline.setVisibility(View.GONE);
      switch (paramDvc.getType())
      {
      default:
        label184: paramHolder.sb_detail_play_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(paramInt)
        {
          public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
          {
            FtPanelEditAdapter.this.callback.onProgressChanged(this.val$i, paramInt);
          }

          public void onStartTrackingTouch(SeekBar paramSeekBar)
          {
          }

          public void onStopTrackingTouch(SeekBar paramSeekBar)
          {
          }
        });
      case 8:
      case 9:
      case 11:
      case 10:
      }
    }
    while (true)
    {
      paramHolder.ibLampChose.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          FtPanelEditAdapter.this.callback.onRelationSeleted(this.val$i);
        }
      });
      return;
      j = 0;
      break;
      label225: k = 0;
      break label29;
      label231: n = 0;
      break label49;
      label237: i = 0;
      break label65;
      label243: paramView.setVisibility(View.GONE);
      break label78;
      label252: i2 = 2130903784;
      break label132;
      paramHolder.iv_icon.setBackgroundResource(2130903700);
      break label184;
      paramHolder.iv_icon.setBackgroundResource(2130903805);
      break label184;
      paramHolder.iv_icon.setBackgroundResource(2130903085);
      break label184;
      paramHolder.iv_icon.setBackgroundResource(2130903142);
      break label184;
      label307: paramHolder.ibLampChose.setVisibility(View.GONE);
      paramHolder.ib_lamp_offline.setVisibility(View.VISIBLE);
      paramHolder.iv_icon.setBackgroundResource(2130903597);
    }
  }

  public int getCount()
  {
    return this.devices.size();
  }

  public Object getItem(int paramInt)
  {
    return this.devices.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      Holder localHolder = new Holder();
      paramView = LayoutInflater.from(this.context).inflate(2130968755, null);
      localHolder.tvLampNameEdit = ((TextView)paramView.findViewById(2131559165));
      localHolder.ibLampChose = ((ImageButton)paramView.findViewById(2131559166));
      localHolder.iv_icon = ((ImageButton)paramView.findViewById(2131559095));
      localHolder.ib_lamp_offline = ((ImageButton)paramView.findViewById(2131559167));
      localHolder.sb_detail_play_progress = ((SeekBar)paramView.findViewById(2131559168));
      paramView.setTag(localHolder);
    }
    initializeViews((Dvc)getItem(paramInt), (Holder)paramView.getTag(), paramView, paramInt);
    return paramView;
  }

  public static abstract interface Callback
  {
    public abstract void onProgressChanged(int paramInt1, int paramInt2);

    public abstract void onRelationSeleted(int paramInt);
  }

  class Holder
  {
    ImageButton ibLampChose;
    ImageButton ib_lamp_offline;
    ImageButton iv_icon;
    SeekBar sb_detail_play_progress;
    TextView tvLampNameEdit;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.panel.FtPanelEditAdapter
 * JD-Core Version:    0.6.0
 */