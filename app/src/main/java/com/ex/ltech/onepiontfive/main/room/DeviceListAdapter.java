package com.ex.ltech.onepiontfive.main.room;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.ex.ltech.led.my_view.DiscreteSeekBar;
import com.ex.ltech.led.my_view.DiscreteSeekBar.OnProgressChangeListener;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import java.util.List;

public class DeviceListAdapter extends BaseAdapter
{
  Bitmap bmBodySensor;
  Bitmap bmClodLight;
  Bitmap bmLed;
  Bitmap bmPlug;
  Bitmap bmRemote;
  Bitmap bmWarmLight;
  private CallBack callBack;
  private Context context;
  private LayoutInflater layoutInflater;
  private List<Dvc> objects;
  private ToggleButton tempSwitch;

  public DeviceListAdapter(Context paramContext, List<Dvc> paramList, CallBack paramCallBack)
  {
    this.context = paramContext;
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.objects = paramList;
    this.callBack = paramCallBack;
  }

  private void initializeViews(Dvc paramDvc, ViewHolder paramViewHolder, int paramInt)
  {
    int i = 1;
    this.tempSwitch = paramViewHolder.swich;
    paramViewHolder.swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView(paramInt)
    {
      public void onToggleInListView(boolean paramBoolean, int paramInt)
      {
        DeviceListAdapter.this.callBack.switchOnOff(this.val$posi, paramBoolean);
      }
    });
    paramViewHolder.rl_off_light_layer.setVisibility(8);
    int i3;
    label80: label222: int k;
    label208: label235: int m;
    label248: int i1;
    if ((paramDvc.isShowDeviceTitle() | paramDvc.isShowLightTitle()))
    {
      paramViewHolder.rl_title.setVisibility(0);
      TextView localTextView = paramViewHolder.title;
      if (paramDvc.isShowDeviceTitle())
      {
        i3 = 2131100030;
        localTextView.setText(i3);
        int j = paramDvc.getType();
        if (!paramDvc.isOnLine())
          break label664;
        if (!paramDvc.isOnOff())
          break label652;
        switch (j)
        {
        case 19:
        case 20:
        default:
          if (!paramDvc.isOnOff())
            break;
          paramViewHolder.swich.setToggleOn();
          if (paramDvc.getType() == 8)
          {
            k = i;
            if (paramDvc.getType() != 10)
              break label692;
            m = i;
            int n = m | k;
            if (paramDvc.getType() != 9)
              break label698;
            i1 = i;
            label268: int i2 = i1 | n;
            if (paramDvc.getType() != 11)
              break label704;
            label284: if ((i2 | i) == 0)
              break label710;
            paramViewHolder.sun_left.setVisibility(0);
            paramViewHolder.sun_right.setVisibility(0);
            paramViewHolder.sb.setVisibility(0);
            paramViewHolder.sb.setOnProgressChangeListener(null);
            paramViewHolder.sb.setProgress(paramDvc.getBrtProgress());
            paramViewHolder.sb.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener(paramInt, paramDvc)
            {
              public void onProgressChanged(DiscreteSeekBar paramDiscreteSeekBar, int paramInt, boolean paramBoolean)
              {
                DeviceListAdapter.this.callBack.onLvSbChange(paramDiscreteSeekBar.getProgress(), this.val$posi);
              }

              public void onStartTrackingTouch(DiscreteSeekBar paramDiscreteSeekBar)
              {
              }

              public void onStopTrackingTouch(DiscreteSeekBar paramDiscreteSeekBar)
              {
                this.val$vo.setBrtProgress(paramDiscreteSeekBar.getProgress());
                DeviceListAdapter.this.callBack.onLvSbChanged(this.val$posi);
              }
            });
            paramViewHolder.arrow.setVisibility(8);
            paramViewHolder.swich.setVisibility(0);
          }
        case 11:
        case 14:
        case 10:
        case 8:
        case 12:
        case 13:
        case 21:
        case 9:
        case 15:
        case 16:
        case 17:
        case 18:
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 22:
        }
      }
    }
    while (true)
    {
      paramViewHolder.name.setText(paramDvc.getName());
      return;
      i3 = 2131100145;
      break;
      paramViewHolder.rl_title.setVisibility(8);
      break label80;
      paramViewHolder.ic.setBackgroundResource(2130903085);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903731);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903142);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903700);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903701);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903621);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903679);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903805);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903607);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903780);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903800);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903600);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903790);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903401);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903792);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903228);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903254);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903636);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903055);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903406);
      break label208;
      paramViewHolder.ic.setBackgroundResource(2130903045);
      break label208;
      label652: paramViewHolder.ic.setBackgroundResource(2130903596);
      break label208;
      label664: paramViewHolder.ic.setBackgroundResource(2130903597);
      break label208;
      paramViewHolder.swich.setToggleOff();
      break label222;
      k = 0;
      break label235;
      label692: m = 0;
      break label248;
      label698: i1 = 0;
      break label268;
      label704: i = 0;
      break label284;
      label710: paramViewHolder.arrow.setVisibility(0);
      paramViewHolder.sun_left.setVisibility(8);
      paramViewHolder.sun_right.setVisibility(8);
      paramViewHolder.sb.setVisibility(8);
      paramViewHolder.name.setVisibility(0);
      paramViewHolder.name.setText(paramDvc.getName());
      paramViewHolder.swich.setVisibility(8);
    }
  }

  public int getCount()
  {
    return this.objects.size();
  }

  public Dvc getItem(int paramInt)
  {
    return (Dvc)this.objects.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.layoutInflater.inflate(2130968796, null);
      ViewHolder localViewHolder = new ViewHolder();
      ViewHolder.access$002(localViewHolder, (ImageView)paramView.findViewById(2131559297));
      ViewHolder.access$102(localViewHolder, (ImageView)paramView.findViewById(2131559294));
      ViewHolder.access$202(localViewHolder, (RelativeLayout)paramView.findViewById(2131558993));
      ViewHolder.access$302(localViewHolder, (TextView)paramView.findViewById(2131558469));
      ViewHolder.access$402(localViewHolder, (ImageView)paramView.findViewById(2131558883));
      ViewHolder.access$502(localViewHolder, (ImageView)paramView.findViewById(2131559296));
      ViewHolder.access$602(localViewHolder, (ImageView)paramView.findViewById(2131559295));
      ViewHolder.access$702(localViewHolder, (TextView)paramView.findViewById(2131559008));
      ViewHolder.access$802(localViewHolder, (DiscreteSeekBar)paramView.findViewById(2131558998));
      ViewHolder.access$902(localViewHolder, (ToggleButton)paramView.findViewById(2131559047));
      paramView.setTag(localViewHolder);
    }
    initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag(), paramInt);
    return paramView;
  }

  public void setCallBack(CallBack paramCallBack)
  {
    this.callBack = paramCallBack;
  }

  public static abstract interface CallBack
  {
    public abstract void onLvSbChange(int paramInt1, int paramInt2);

    public abstract void onLvSbChange(SeekBar paramSeekBar, int paramInt);

    public abstract void onLvSbChanged(int paramInt);

    public abstract void switchOnOff(int paramInt, boolean paramBoolean);
  }

  protected class ViewHolder
  {
    private ImageView arrow;
    private ImageView ic;
    private TextView name;
    private ImageView rl_off_light_layer;
    private RelativeLayout rl_title;
    private DiscreteSeekBar sb;
    private ImageView sun_left;
    private ImageView sun_right;
    private ToggleButton swich;
    private TextView title;

    protected ViewHolder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.DeviceListAdapter
 * JD-Core Version:    0.6.0
 */