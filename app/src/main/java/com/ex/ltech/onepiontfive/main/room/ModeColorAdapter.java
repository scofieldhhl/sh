package com.ex.ltech.onepiontfive.main.room;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.ct.ColorBussiness;
import com.ex.ltech.led.my_view.MLImageView;
import com.ex.ltech.led.vo.CtSceneVo;
import com.google.gson.Gson;
import java.util.List;

class ModeColorAdapter extends BaseAdapter
{
  private ColorBussiness bussiness;
  private CallBack callBack;
  private List<CtSceneVo> itemVos;
  private Activity pct;
  int[] reses = { 2130903158, 2130903159, 2130903160, 2130903161, 2130903162, 2130903163, 2130903164, 2130903165 };

  public ModeColorAdapter(Activity paramActivity, List<CtSceneVo> paramList, CallBack paramCallBack)
  {
    this.pct = paramActivity;
    this.itemVos = paramList;
    this.callBack = paramCallBack;
    this.bussiness = new ColorBussiness(this.pct);
    this.bussiness.loadCtSceneVos();
  }

  public int getCount()
  {
    return this.itemVos.size();
  }

  public Object getItem(int paramInt)
  {
    return this.itemVos.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Holder localHolder;
    CtSceneVo localCtSceneVo;
    int i;
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(2130968803, null);
      localHolder.edit = ((ImageView)paramView.findViewById(2131559311));
      localHolder.name = ((TextView)paramView.findViewById(2131559312));
      localHolder.customIc = ((MLImageView)paramView.findViewById(2131559310));
      paramView.setTag(localHolder);
      localCtSceneVo = (CtSceneVo)this.itemVos.get(paramInt);
      ImageView localImageView = localHolder.edit;
      if (!localCtSceneVo.isEdit())
        break label233;
      i = 0;
      label117: localImageView.setVisibility(i);
      localHolder.name.setText(localCtSceneVo.getName());
      if (!localCtSceneVo.getIcPath().equals(""))
        break label240;
      localHolder.customIc.setBackgroundResource(this.reses[localCtSceneVo.getIcResPosi()]);
    }
    while (true)
    {
      localHolder.customIc.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          ModeColorAdapter.this.bussiness.hideEditBtn();
          ModeColorAdapter.this.notifyDataSetChanged();
          ModeColorAdapter.this.callBack.seekBar();
        }
      });
      localHolder.customIc.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramView)
        {
          return false;
        }
      });
      localHolder.edit.setOnClickListener(new View.OnClickListener(paramInt, localCtSceneVo)
      {
        public void onClick(View paramView)
        {
          ModeColorAdapter.this.bussiness.hideEditBtn();
          ModeColorAdapter.this.notifyDataSetChanged();
          ModeColorAdapter.this.callBack.goToCtModeColorEdit(this.val$i, this.val$vo.getName(), ModeColorAdapter.this.bussiness.gs.toJson(this.val$vo));
        }
      });
      return paramView;
      localHolder = (Holder)paramView.getTag();
      break;
      label233: i = 8;
      break label117;
      label240: localHolder.customIc.setImageBitmap(BitmapFactory.decodeFile(localCtSceneVo.getIcPath()));
    }
  }

  public static abstract interface CallBack
  {
    public abstract void goToCtModeColorEdit(int paramInt, String paramString1, String paramString2);

    public abstract void seekBar();
  }

  class Holder
  {
    MLImageView customIc;
    ImageView edit;
    TextView name;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.ModeColorAdapter
 * JD-Core Version:    0.6.0
 */