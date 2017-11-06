package com.ex.ltech.onepiontfive.main.room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ex.ltech.ct.ActCtSceneLast;
import com.ex.ltech.ct.ColorBussiness;
import com.ex.ltech.led.R;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.my_view.SimpleColorPickerView;
import com.ex.ltech.led.vo.CtSceneVo;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.indris.material.RippleView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.listener.SendPipeListener;

public class ModeColorEdit extends MyBaseFt
{
  int b;
  int brt;
  private int brtType = 209;

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  private RoomsBusiness business;
  private ColorBussiness bussiness;
  int c;
  private CmdDateBussiness cmdDateBussiness;

  @Bind({R.id.color})
  SimpleColorPickerView color;
  private String ctSceneName;
  private int ctScenePosi;
  int g;
  private int groupIndex;
  private Home home;
  int r;
  private Room room;
  private RoomBusiness roomBusiness;
  private int roomIndex;

  @Bind({R.id.sb})
  SeekBar sb;
  SendPipeListener sendPipeListener = new SendPipeListener()
  {
    public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
    {
    }
  };

  @Bind({2131558829})
  ImageView sub;
  private Dvc tempDvc;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  private int type;
  private View view;
  private CtSceneVo vo;
  int w;
  private int warmWhiteType = 210;

  private void init()
  {
    this.bussiness = new ColorBussiness(getActivity());
    this.cmdDateBussiness = new CmdDateBussiness("0000");
    this.bussiness.loadCtSceneVos();
    this.ctScenePosi = getRequest().getIntExtra("ctScenePosi", -1);
    this.ctSceneName = getRequest().getStringExtra("ctSceneName");
    this.type = getRequest().getIntExtra("type", -1);
    this.roomIndex = getRequest().getIntExtra("roomIndex", -1);
    this.groupIndex = getRequest().getIntExtra("groupIndex", -1);
    this.vo = ((CtSceneVo)this.bussiness.vos.get(this.ctScenePosi));
    this.c = this.vo.getC();
    this.w = this.vo.getW();
    this.brt = this.vo.getBrt();
    this.business = new RoomsBusiness(getActivity());
    this.home = this.business.getHome();
    this.room = ((Room)this.home.getRooms().get(this.ctScenePosi));
    this.roomBusiness = new RoomBusiness(getActivity(), this.room.getDvcVos());
    this.tempDvc = new Dvc();
    this.tempDvc.setIsGroup(false);
    this.tempDvc.setType(8);
    this.tempDvc.setRoomIndex(1 + this.roomIndex);
    this.sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
      {
        if (paramSeekBar.getProgress() > 4)
        {
          ModeColorEdit.this.brt = (255 * paramSeekBar.getProgress() / 100);
          ModeColorEdit.this.roomBusiness.sendCT(new MyBusiness.MySendListener()
          {
            public void onFail()
            {
            }

            public void onOk(byte[] paramArrayOfByte)
            {
              Log.i("", "");
            }

            public void onTimeOut()
            {
            }
          }
          , ModeColorEdit.this.tempDvc, ModeColorEdit.this.c, ModeColorEdit.this.w, ModeColorEdit.this.brt);
          System.out.println(ModeColorEdit.this.brt);
        }
      }

      public void onStartTrackingTouch(SeekBar paramSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramSeekBar)
      {
      }
    });
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        ModeColorEdit.this.sb.setProgress(100 * ModeColorEdit.this.vo.getBrt() / 255);
        ModeColorEdit.this.color.setPikerXy(100 * ModeColorEdit.this.vo.getW() / 255);
      }
    }
    , 1000L);
    this.color.setListener(new SimpleColorPickerView.OnColorChangedListener()
    {
      public void onPikerTouchUp(int paramInt)
      {
      }

      public void onPikerXYChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
      {
        ModeColorEdit.this.r = paramInt2;
        ModeColorEdit.this.g = paramInt3;
        ModeColorEdit.this.b = paramInt4;
        if (ModeColorEdit.this.r == 255)
          ModeColorEdit.this.c = (255 - ModeColorEdit.this.b / 2);
        if (ModeColorEdit.this.b > 247)
          ModeColorEdit.this.c = (ModeColorEdit.this.r / 2);
      }

      public void onProgressPercent(float paramFloat)
      {
        ModeColorEdit.this.w = (int)(255.0F * paramFloat);
        if (ModeColorEdit.this.w < 1)
          ModeColorEdit.this.w = 0;
        if (ModeColorEdit.this.w > 254)
          ModeColorEdit.this.w = 255;
        ModeColorEdit.this.c = (255 - ModeColorEdit.this.w);
        ModeColorEdit.this.roomBusiness.sendCT(new MyBusiness.MySendListener()
        {
          public void onFail()
          {
          }

          public void onOk(byte[] paramArrayOfByte)
          {
            Log.i("", "");
          }

          public void onTimeOut()
          {
          }
        }
        , ModeColorEdit.this.tempDvc, ModeColorEdit.this.c, ModeColorEdit.this.w, ModeColorEdit.this.brt);
      }
    });
    this.color.setViewBgRes(2130903149, true);
  }

  private void initTitle()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(this.ctSceneName);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ModeColorEdit.access$202(ModeColorEdit.this, (CtSceneVo)ModeColorEdit.this.bussiness.vos.get(ModeColorEdit.this.ctScenePosi));
        ModeColorEdit.this.roomBusiness.sendCT(new MyBusiness.MySendListener()
        {
          public void onFail()
          {
          }

          public void onOk(byte[] paramArrayOfByte)
          {
            ModeColorEdit.this.sb.setProgress(100 * ModeColorEdit.this.vo.getBrt() / 255);
            ModeColorEdit.this.color.setPikerXy(100 * ModeColorEdit.this.vo.getW() / 255);
          }

          public void onTimeOut()
          {
          }
        }
        , ModeColorEdit.this.tempDvc, ModeColorEdit.this.vo.getC(), ModeColorEdit.this.vo.getW(), ModeColorEdit.this.vo.getBrt());
        ModeColorEdit.this.finish();
      }
    });
    this.tvTitleViewEdit.setVisibility(View.VISIBLE);
    this.tvTitleViewEdit.setText(R.string.finish);
    this.tvTitleViewEdit.setTextColor(getResources().getColor(R.color.color1));
    this.tvTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ModeColorEdit.this.bussiness.saveCtSceneVo(ModeColorEdit.this.vo, ModeColorEdit.this.ctScenePosi);
        Intent localIntent = new Intent(ModeColorEdit.this.getActivity(), ActCtSceneLast.class);
        localIntent.putExtra("ctScenePosi", ModeColorEdit.this.ctScenePosi);
        localIntent.putExtra("sceneDataStr", ModeColorEdit.this.bussiness.gs.toJson(ModeColorEdit.this.vo));
        localIntent.putExtra("ctSceneName", ModeColorEdit.this.ctSceneName);
        localIntent.putExtra("cValue", ModeColorEdit.this.c);
        localIntent.putExtra("wValue", ModeColorEdit.this.w);
        localIntent.putExtra("brtValue", ModeColorEdit.this.brt);
        ModeColorEdit.this.startActivityForResult(localIntent, 0);
      }
    });
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.bussiness = new ColorBussiness(getActivity());
    this.bussiness.loadCtSceneVos();
    this.vo = ((CtSceneVo)this.bussiness.vos.get(this.ctScenePosi));
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
      this.view = paramLayoutInflater.inflate(2130968672, null);
    ButterKnife.bind(this, this.view);
    init();
    initTitle();
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.ModeColorEdit
 * JD-Core Version:    0.6.0
 */