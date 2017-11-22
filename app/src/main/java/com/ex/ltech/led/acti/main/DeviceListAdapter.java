package com.ex.ltech.led.acti.main;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ex.ltech.led.R;
import com.ex.ltech.led.my_view.MLImageView;
import com.ex.ltech.led.vo.CtSceneVo;
import com.ex.ltech.led.vo.ModeVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.xlink.wifi.js.bean.Device;


public class DeviceListAdapter extends BaseAdapter
{
  Bitmap bwBm;
  ArrayList<Integer> cols = new ArrayList();
  Bitmap ctBm;
  Bitmap defaultBm;
  private List<Device> devices;
  Bitmap hongwaiBm;
  RelativeLayout.LayoutParams layoutParams;
  Bitmap ledBm;
  Bitmap lightOffline;
  private Activity mContext;
  OnFreshDevice onFreshDevice;
  Bitmap oneZeroFiveBm;
  Bitmap oneZeroFiveOff;
  Bitmap plugBm;
  Bitmap plugOffline;
  Bitmap remoteOffline;

  public DeviceListAdapter(Activity paramActivity, ArrayList<Device> paramArrayList)
  {
    this.mContext = paramActivity;
    this.devices = paramArrayList;
    this.ledBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.rgb);
    this.plugBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.device_plug);
    this.hongwaiBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.d_remote);
    this.ctBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.ct);
    this.bwBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.bw_d_ic);
    this.defaultBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.cfg_cfg_no);
    this.oneZeroFiveBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.icon_105);
    this.oneZeroFiveOff = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.icon_105_off);
    this.lightOffline = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.light_offline);
    this.plugOffline = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.plug_offline);
    this.remoteOffline = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.remote_offline);
    this.layoutParams = new RelativeLayout.LayoutParams(-2, -2);
  }

  private boolean isZh()
  {
    Locale localLocale = this.mContext.getResources().getConfiguration().locale;
    String str1 = localLocale.getCountry();
    String str2 = localLocale.getLanguage();
    return (str2 + "_" + str1).equals("zh_CN");
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

  // ERROR //
  public View getView(int paramInt, View convertView, android.view.ViewGroup paramViewGroup)
  {

    Holder holder = null;
        if (convertView == null) {
            holder=new Holder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_item_act_new_main, null);
//            holder.iv_device = (MLImageView)convertView.findViewById(R.id.iv_device);
          holder.off_line_lay = (ImageView)convertView.findViewById(R.id.off_line_lay);
          holder.space = (ImageView)convertView.findViewById(R.id.space);
            holder.rgbStatus = (TextView)convertView.findViewById(R.id.tv_status);
            holder.tv_device_name = (TextView)convertView.findViewById(R.id.tv_device_name);
          holder.tv_online_status = (TextView)convertView.findViewById(R.id.tv_online_status);
            convertView.setTag(holder);

        }else {

            holder = (Holder)convertView.getTag();
        }

    Device device = devices.get(paramInt);
    holder.off_line_lay = (ImageView)convertView.findViewById(R.id.off_line_lay);
    holder.space = (ImageView)convertView.findViewById(R.id.space);
//    holder.rgbStatus.setText(device.getState());
    holder.tv_device_name.setText(device.getName());
    holder.tv_online_status.setText(String.valueOf(device.isOnline()));

        return convertView;

  }

  public List<CtSceneVo> initCtSceneVos(String paramString)
  {
    /*String str = UserFerences.getUserFerences(this.mContext).spFerences.getString(paramString + "CtSceneVos", "");
    try
    {
      List localList = (List)new Gson().fromJson(str, new TypeToken()
      {
      }
      .getType());
      return localList;
    }
    catch (JsonSyntaxException localJsonSyntaxException)
    {
    }*/
    return null;
  }

  public List<ModeVo> initCustomModes(String paramString)
  {
    /*String str = UserFerences.getUserFerences(this.mContext).spFerences.getString(paramString + "ModeDataKey", "");
    try
    {
      List localList = (List)new Gson().fromJson(str, new TypeToken()
      {
      }
      .getType());
      return localList;
    }
    catch (JsonSyntaxException localJsonSyntaxException)
    {
    }*/
    return null;
  }

  public void setOnFreshDevice(OnFreshDevice paramOnFreshDevice)
  {
    this.onFreshDevice = paramOnFreshDevice;
  }

  class Holder
  {
    MLImageView iv_device;
    ImageView off_line_lay;
//    ColorSeletedView rgbColor;
View rgbColor;
    TextView rgbStatus;
    ImageView space;
    TextView tv_device_name;
    TextView tv_online_status;

  }

  class MyBitmap
  {
    Bitmap bitmap;
    boolean isBitmap;

    MyBitmap()
    {
    }

    public Bitmap getBitmap()
    {
      return this.bitmap;
    }

    public boolean isBitmap()
    {
      return this.isBitmap;
    }

    public void setBitmap(Bitmap paramBitmap)
    {
      this.bitmap = paramBitmap;
    }

    public void setIsBitmap(boolean paramBoolean)
    {
      this.isBitmap = paramBoolean;
    }
  }

  static abstract interface OnFreshDevice
  {
    public abstract void OnClick(int paramInt);
  }
}
