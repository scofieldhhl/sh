package com.ex.ltech.onepiontfive.main.room.mode;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.my_view.CircleColorView;
import com.ex.ltech.led.my_view.CircleColorView.OnCilcleColorSeletedListner;
import com.ex.ltech.led.my_view.SceneColorPickerView;
import com.ex.ltech.led.my_view.SceneColorPickerView.OnColorChangedListener;
import com.ex.ltech.led.my_view.SceneColorPickerView.OnXYChangedListener;
import com.ex.ltech.led.vo.ModeVo;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.room.FtRooms;
import com.ex.ltech.onepiontfive.main.room.RoomBusiness;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.fragmentmaster.app.Request;
import com.google.gson.Gson;
import com.indris.material.RippleView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ActNewMode extends MyBaseFt
  implements SceneColorPickerView.OnColorChangedListener, CircleColorView.OnCilcleColorSeletedListner, View.OnClickListener, SeekBar.OnSeekBarChangeListener, SceneColorPickerView.OnXYChangedListener
{
  private Intent _intent = new Intent();
  int b;
  TextWatcher bTextWatcher = new TextWatcher()
  {
    public void afterTextChanged(Editable paramEditable)
    {
      System.out.println("afterTextChanged");
      ActNewMode.this.onInputColor();
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
      System.out.println("beforeTextChanged");
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
      System.out.println("onTextChanged");
      try
      {
        ActNewMode.this.b = Integer.parseInt(paramCharSequence.toString());
        if (ActNewMode.this.b > 255)
        {
          EditText localEditText = ActNewMode.this.etActNewCustomB;
          StringBuilder localStringBuilder = new StringBuilder();
          ActNewMode.this.b = 255;
          localEditText.setText(255 + "");
        }
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localNumberFormatException.printStackTrace();
        ActNewMode.this.b = 0;
      }
    }
  };
  int brt = 255;

  @Bind({2131558567})
  Button btActiNewModeBringness;

  @Bind({2131558566})
  Button btActiNewModePing;

  @Bind({2131558564})
  RippleView btActiNewModePlay;

  @Bind({2131558563})
  Button btActiNewModeTiao;

  @Bind({2131558565})
  Button btActiNewModeZan;

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  private ModeBusiness business;
  CircleColorView ccvActiNewMode;
  private int changeMode = 2;
  private int cilcleBallPosi = -1;
  int colorCount = 1;
  private int currentColor;
  int dIndex;
  Runnable delayAddTextWatchThread = new Runnable()
  {
    public void run()
    {
      ActNewMode.this.addMyTextwatcher();
    }
  };

  @Bind({2131558574})
  EditText etActNewCustomB;

  @Bind({2131558571})
  EditText etActNewCustomG;

  @Bind({2131558572})
  EditText etActNewCustomR;
  int g;
  TextWatcher gTextWatcher = new TextWatcher()
  {
    public void afterTextChanged(Editable paramEditable)
    {
      ActNewMode.this.onInputColor();
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
      try
      {
        ActNewMode.this.g = Integer.parseInt(paramCharSequence.toString());
        if (ActNewMode.this.g > 255)
        {
          EditText localEditText = ActNewMode.this.etActNewCustomG;
          StringBuilder localStringBuilder = new StringBuilder();
          ActNewMode.this.g = 255;
          localEditText.setText(255 + "");
        }
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localNumberFormatException.printStackTrace();
        ActNewMode.this.g = 0;
      }
    }
  };
  Handler handler = new Handler();
  boolean isNewCreate;
  private boolean isPlay;
  boolean isSeletedColor = false;
  private boolean isThisBG;
  private int modeCount;
  String modeDataStr = null;
  private int modeOrder;
  private String modesNames;
  private int modesPosi;
  int r;
  TextWatcher rTextWatcher = new TextWatcher()
  {
    public void afterTextChanged(Editable paramEditable)
    {
      ActNewMode.this.onInputColor();
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
      try
      {
        ActNewMode.this.r = Integer.parseInt(paramCharSequence.toString());
        if (ActNewMode.this.r > 255)
        {
          EditText localEditText = ActNewMode.this.etActNewCustomR;
          StringBuilder localStringBuilder = new StringBuilder();
          ActNewMode.this.r = 255;
          localEditText.setText(255 + "");
        }
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localNumberFormatException.printStackTrace();
        ActNewMode.this.r = 0;
      }
    }
  };
  RelativeLayout rlNewCustomColorBar;
  private RelativeLayout rl_new_custom_color_bar;
  RoomBusiness roomBusiness;
  int roomNum;
  private int runStatus;

  @Bind({2131558568})
  SeekBar sbActiNewMode1;

  @Bind({2131558569})
  SeekBar sbActiNewMode2;

  @Bind({2131558560})
  SceneColorPickerView scpvActiNewMode;
  int speed = 8;
  private RelativeLayout thisRL;
  private TextView tv1;
  private TextView tv2;
  private TextView tv3;
  private TextView tv4;
  private TextView tv5;
  private TextView tv6;
  private TextView tv7;
  private TextView tv8;

  @Bind({2131558561})
  TextView tvActiNewModeCircleColorPosi;
  private List<TextView> tvList = new ArrayList();

  @Bind({2131558573})
  TextView tvNewCustomB;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;
  private ModeVo vo = new ModeVo();
  float xPrecent;
  float yPrecent;

  private void addMyTextwatcher()
  {
    this.etActNewCustomR.addTextChangedListener(this.rTextWatcher);
    this.etActNewCustomG.addTextChangedListener(this.gTextWatcher);
    this.etActNewCustomB.addTextChangedListener(this.bTextWatcher);
  }

  private void findView()
  {
    this.thisRL = ((RelativeLayout)this.view.findViewById(2131558558));
    addMyTextwatcher();
  }

  private void init()
  {
    this.business.prepareLink();
    this.rlNewCustomColorBar = ((RelativeLayout)this.view.findViewById(2131558570));
    this.btActiNewModeZan.setBackgroundResource(R.mipmap.ic_new_custom_zan_press);
    this.modeCount = (2 + getRequest().getIntExtra("modeCount", 1));
    this.modesNames = getRequest().getStringExtra("modesNames");
    this.modesPosi = getRequest().getIntExtra("modesPosi", -1);
    this.modeOrder = getRequest().getIntExtra("modeOrder", -1);
    this.isNewCreate = getRequest().getBooleanExtra("isNewCreate", false);
    if (!this.isNewCreate)
    {
      String str = getRequest().getStringExtra("modeDataStr");
      this.vo = ((ModeVo)new Gson().fromJson(str, ModeVo.class));
    }
    this.vo.setIsNewCreateMode(true);
    this.roomNum = getRequest().getIntExtra("DRoomNumKey", 0);
    this.dIndex = getRequest().getIntExtra("DIndexKey", 0);
    RelativeLayout localRelativeLayout = this.rlNewCustomColorBar;
    int i = Color.argb(255, 155, 254, 255);
    this.currentColor = i;
    localRelativeLayout.setBackgroundColor(i);
    this.btActiNewModePlay.setOnClickListener(this);
    setupView();
    this.speed = (this.sbActiNewMode1.getProgress() / 12);
    this.brt = (255 * this.sbActiNewMode2.getProgress() / 100);
    if (this.brt < 12)
      this.brt = 12;
  }

  private void initTitle()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(R.string.mode);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ActNewMode.this.finish();
      }
    });
    this.tvTitleViewEdit.setVisibility(View.VISIBLE);
    this.tvTitleViewEdit.setText(R.string.finish);
    this.tvTitleViewEdit.setTextColor(getResources().getColor(2131492897));
    this.tvTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        int i = 1;
        for (int j = -1 + ActNewMode.this.ccvActiNewMode.colors.size(); ; j--)
        {
          if (j > 1)
          {
            if (((Integer)ActNewMode.this.ccvActiNewMode.colors.get(j)).intValue() == -16777216)
              continue;
            i = j + 1;
          }
          ActNewMode.this.vo.setColorCount(i);
          ActNewMode.this.vo.setColors(ActNewMode.this.ccvActiNewMode.colors);
          ActNewMode.this.vo.setSpeed(ActNewMode.this.speed);
          ActNewMode.this.vo.setBrt(ActNewMode.this.brt);
          ActNewMode.this.vo.setOrder(ActNewMode.this.modeOrder);
          ActNewMode.this.vo.setTransformation(ActNewMode.this.changeMode);
          ActNewMode.this.vo.setIsNewCreateMode(true);
          ActNewMode.this.vo.setType(3);
          ActNewMode.this.modeDataStr = ActNewMode.this.business.gs.toJson(ActNewMode.this.vo);
          Intent localIntent = new Intent(ActNewMode.this.getActivity(), FtNamingMode.class);
          localIntent.putExtra("modeDataStr", ActNewMode.this.modeDataStr);
          localIntent.putExtra("modesPosi", ActNewMode.this.modesPosi);
          localIntent.putExtra("modesNames", ActNewMode.this.modesNames);
          ActNewMode.this.startActivityForResult(localIntent, 1);
          return;
        }
      }
    });
  }

  private void onInputColor()
  {
    try
    {
      int i = Color.argb(0, this.r, this.g, this.b);
      RelativeLayout localRelativeLayout = this.rlNewCustomColorBar;
      int j = Color.argb(255, this.r, this.g, this.b);
      this.currentColor = j;
      localRelativeLayout.setBackgroundColor(j);
      this.roomBusiness.sendColor(FtRooms.seletedDvc.isGroup(), FtRooms.seletedDvc.getGroupId(), FtRooms.seletedDvc.getType(), FtRooms.seletedDvc.getRoomIndex(), FtRooms.seletedDvc.getmIndex(), this.r, this.g, this.b, 255, 255, this.xPrecent, this.yPrecent);
      System.out.println("onInputColor = " + i);
      return;
    }
    catch (Exception localException)
    {
      System.out.println("Exception = ");
      localException.printStackTrace();
    }
  }

  private void setListener()
  {
    this.business.setSendListener(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
      }

      public void onTimeOut()
      {
      }
    });
    this.ccvActiNewMode.setMyListener(this);
    this.scpvActiNewMode.setListener(this);
    this.scpvActiNewMode.setXYChangedListener(this);
    this.btActiNewModeTiao.setOnClickListener(this);
    this.btActiNewModeZan.setOnClickListener(this);
    this.btActiNewModePing.setOnClickListener(this);
    this.sbActiNewMode1.setOnSeekBarChangeListener(this);
    this.sbActiNewMode2.setOnSeekBarChangeListener(this);
  }

  private void setTitleView()
  {
  }

  private void setupView()
  {
    this.business.getEditableModes();
    if (this.modesPosi != -1)
    {
      List localList = this.business.getEditableModes();
      if (localList != null)
      {
        ModeVo localModeVo = (ModeVo)localList.get(this.modesPosi);
        this.ccvActiNewMode.setColors(localModeVo.getColors());
        this.ccvActiNewMode.invalidate();
        this.ccvActiNewMode.setVisibility(View.VISIBLE);
        this.sbActiNewMode2.setProgress(100 * localModeVo.getBrt() / 255);
        this.sbActiNewMode1.setProgress(12 * localModeVo.getSpeed());
        this.speed = localModeVo.getSpeed();
        this.brt = localModeVo.getBrt();
        if (this.brt < 12)
          this.brt = 12;
        this.btActiNewModePing.setBackgroundResource(R.mipmap.ic_new_custom_ping);
        this.btActiNewModeZan.setBackgroundResource(R.mipmap.ic_new_custom_zan);
        this.btActiNewModeTiao.setBackgroundResource(R.mipmap.ic_new_custom_tiao);
        this.changeMode = 2;
        if (localModeVo.getTransformation() == 1)
        {
          this.btActiNewModeTiao.setBackgroundResource(R.mipmap.ic_new_custom_tiao_press);
          this.changeMode = 1;
        }
        if (localModeVo.getTransformation() == 2)
        {
          this.btActiNewModeZan.setBackgroundResource(R.mipmap.ic_new_custom_zan_press);
          this.changeMode = 2;
        }
        if (localModeVo.getTransformation() == 3)
        {
          this.btActiNewModePing.setBackgroundResource(R.mipmap.ic_new_custom_ping_press);
          this.changeMode = 3;
        }
      }
    }
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 1000)
    {
      setResult(1000, getRequest());
      finish();
    }
  }

  public void onCilcleBgSeleted(int paramInt)
  {
    this.cilcleBallPosi = paramInt;
    this.ccvActiNewMode.saveColor(this.currentColor, paramInt);
  }

  public void onCilcleBgTouchUp()
  {
    this.tvActiNewModeCircleColorPosi.setVisibility(View.GONE);
    this.tvActiNewModeCircleColorPosi.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_down_out_fast));
  }

  public void onClick(View paramView)
  {
    int m;
    int i1;
    if (paramView == this.btActiNewModePlay)
    {
      m = -1 + this.ccvActiNewMode.colors.size();
      if (m > 1)
      {
        if (((Integer)this.ccvActiNewMode.colors.get(m)).intValue() != -16777216)
          this.colorCount = (m + 1);
      }
      else
      {
        if (!this.isPlay)
          break label149;
        ModeBusiness localModeBusiness5 = this.business;
        if (!this.isNewCreate)
          break label140;
        i1 = 255;
        label89: localModeBusiness5.sendCustomMode(null, i1, this.changeMode, 2, this.speed, this.brt, this.colorCount, this.ccvActiNewMode.colors);
        this.isPlay = false;
        paramView.setBackgroundResource(R.mipmap.music_paly);
      }
    }
    label140: label149: int k;
    label330: int j;
    label378: 
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            m--;
            break;
            i1 = this.modeOrder;
            break label89;
            ModeBusiness localModeBusiness4 = this.business;
            if (this.isNewCreate);
            for (int n = 255; ; n = this.modeOrder)
            {
              localModeBusiness4.sendCustomMode(null, n, this.changeMode, 1, this.speed, this.brt, this.colorCount, this.ccvActiNewMode.colors);
              this.isPlay = true;
              paramView.setBackgroundResource(R.mipmap.music_stop);
              return;
            }
            this.btActiNewModePing.setBackgroundResource(R.mipmap.ic_new_custom_ping);
            this.btActiNewModeZan.setBackgroundResource(R.mipmap.ic_new_custom_zan);
            this.btActiNewModeTiao.setBackgroundResource(R.mipmap.ic_new_custom_tiao);
            if (paramView != this.btActiNewModeZan)
              break label330;
            this.btActiNewModeZan.setBackgroundResource(R.mipmap.ic_new_custom_zan_press);
            this.changeMode = 2;
          }
          while (!this.isPlay);
          ModeBusiness localModeBusiness3 = this.business;
          if (!this.isNewCreate)
            break label487;
          k = 255;
          localModeBusiness3.sendCustomMode(null, k, this.changeMode, 1, this.speed, this.brt, this.colorCount, this.ccvActiNewMode.colors);
          if (paramView != this.btActiNewModePing)
            break label410;
          this.btActiNewModePing.setBackgroundResource(R.mipmap.ic_new_custom_ping_press);
          this.changeMode = 3;
        }
        while (!this.isPlay);
        ModeBusiness localModeBusiness2 = this.business;
        if (!this.isNewCreate)
          break label496;
        j = 255;
        localModeBusiness2.sendCustomMode(null, j, this.changeMode, 1, this.speed, this.brt, this.colorCount, this.ccvActiNewMode.colors);
      }
      while (paramView != this.btActiNewModeTiao);
      this.btActiNewModeTiao.setBackgroundResource(R.mipmap.ic_new_custom_tiao_press);
      this.changeMode = 1;
    }
    while (!this.isPlay);
    label410: ModeBusiness localModeBusiness1 = this.business;
    if (this.isNewCreate);
    for (int i = 255; ; i = this.modeOrder)
    {
      localModeBusiness1.sendCustomMode(null, i, this.changeMode, 1, this.speed, this.brt, this.colorCount, this.ccvActiNewMode.colors);
      return;
      label487: k = this.modeOrder;
      break;
      label496: j = this.modeOrder;
      break label378;
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2130968609, null);
    ButterKnife.bind(this, this.view);
    setSlideable(false);
    this.ccvActiNewMode = ((CircleColorView)this.view.findViewById(2131558562));
    this.business = new ModeBusiness(getActivity());
    this.roomBusiness = new RoomBusiness(getActivity());
    this.business.setIsGroup(getRequest().getBooleanExtra("ModeIsGroupKey", false));
    this.business.setGroupNum(getRequest().getIntExtra("ModeGroupIdKey", 0));
    this.business.setDvcNum(getRequest().getIntExtra("ModeDvcOderIdKey", 0));
    initTitle();
    findView();
    setListener();
    init();
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    this.scpvActiNewMode.setPactHeight(localDisplayMetrics.heightPixels);
    return this.view;
  }

  public void onDestroy()
  {
    super.onDestroy();
    this.business.setSendListener(null);
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    System.out.println("ftNewMOde destroyView");
    if (this.business != null)
      this.business.setMySendListener(null);
    ButterKnife.unbind(this);
  }

  protected void onEdit()
  {
    for (int i = 0; ; i++)
    {
      int j;
      int k;
      ModeBusiness localModeBusiness;
      if (i < this.ccvActiNewMode.colors.size())
      {
        if (((Integer)this.ccvActiNewMode.colors.get(i)).intValue() == -16777216)
          continue;
        j = 1;
        k = -1 + this.ccvActiNewMode.colors.size();
        if (k > 1)
        {
          if (((Integer)this.ccvActiNewMode.colors.get(k)).intValue() == -16777216)
            break label235;
          j = k + 1;
        }
        localModeBusiness = this.business;
        if (this.modesPosi >= 0)
          break label241;
      }
      label235: label241: for (int m = -1 + this.modeCount; ; m = this.modesPosi)
      {
        localModeBusiness.sendCustomMode(null, m, this.changeMode, 17, this.speed, this.brt, j, this.ccvActiNewMode.colors);
        this.vo.setColorCount(j);
        this.vo.setColors(this.ccvActiNewMode.colors);
        this.vo.setSpeed(this.speed);
        this.vo.setBrt(this.brt);
        this.vo.setTransformation(this.changeMode);
        this.vo.setIsNewCreateMode(true);
        this.vo.setType(3);
        this.modeDataStr = this.business.gs.toJson(this.vo);
        return;
        k--;
        break;
      }
    }
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 1000)
    {
      setResult(1000, paramRequest);
      finish();
    }
  }

  public void onLongClick(int paramInt)
  {
    this.ccvActiNewMode.colors.remove(paramInt);
    this.ccvActiNewMode.colors.add(paramInt, Integer.valueOf(-16777216));
    this.ccvActiNewMode.invalidate();
  }

  public void onPikerTouchUp(int paramInt)
  {
  }

  public void onPikerXYChange(int paramInt)
  {
    this.currentColor = paramInt;
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    if (this.sbActiNewMode2 == paramSeekBar)
    {
      if (paramSeekBar.getProgress() > 4)
      {
        this.brt = (255 * paramSeekBar.getProgress() / 100);
        if (this.brt < 12)
          this.brt = 12;
      }
      if (this.isPlay)
        break label68;
    }
    label68: 
    do
    {
      return;
      this.speed = (paramSeekBar.getProgress() / 12);
      break;
    }
    while (!this.isSeletedColor);
    ModeBusiness localModeBusiness = this.business;
    if (this.isNewCreate);
    for (int i = 255; ; i = this.modeOrder)
    {
      localModeBusiness.sendCustomMode(null, i, this.changeMode, 1, this.speed, this.brt, this.colorCount, this.ccvActiNewMode.colors);
      return;
    }
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onRgbChange(int paramInt1, int paramInt2, int paramInt3)
  {
    this.etActNewCustomR.removeTextChangedListener(this.rTextWatcher);
    this.etActNewCustomG.removeTextChangedListener(this.gTextWatcher);
    this.etActNewCustomB.removeTextChangedListener(this.bTextWatcher);
    this.r = paramInt1;
    this.g = paramInt2;
    this.b = paramInt3;
    this.handler.removeCallbacks(this.delayAddTextWatchThread);
    this.handler.postDelayed(this.delayAddTextWatchThread, 1000L);
    this.etActNewCustomR.setText(paramInt1 + "");
    this.etActNewCustomG.setText(paramInt2 + "");
    this.etActNewCustomB.setText(paramInt3 + "");
    this.roomBusiness.sendColor(FtRooms.seletedDvc.isGroup(), FtRooms.seletedDvc.getGroupId(), FtRooms.seletedDvc.getType(), FtRooms.seletedDvc.getRoomIndex(), FtRooms.seletedDvc.getmIndex(), paramInt1, paramInt2, paramInt3, 255, 255, this.xPrecent, this.yPrecent);
    try
    {
      Color.argb(0, paramInt1, paramInt2, paramInt3);
      RelativeLayout localRelativeLayout = this.rlNewCustomColorBar;
      int i = Color.argb(255, paramInt1, paramInt2, paramInt3);
      this.currentColor = i;
      localRelativeLayout.setBackgroundColor(i);
      System.out.println(this.currentColor);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    for (int i = 0; ; i++)
    {
      if (i < this.ccvActiNewMode.colors.size())
      {
        if (((Integer)this.ccvActiNewMode.colors.get(i)).intValue() == -16777216)
          continue;
        this.isSeletedColor = true;
      }
      return;
    }
  }

  public void onStop()
  {
    super.onStop();
    ModeBusiness localModeBusiness;
    if (this.isPlay)
    {
      localModeBusiness = this.business;
      if (!this.isNewCreate)
        break label81;
    }
    label81: for (int i = 255; ; i = this.modeOrder)
    {
      localModeBusiness.sendCustomMode(null, i, this.changeMode, 2, this.speed, this.brt, this.ccvActiNewMode.colors.size(), this.ccvActiNewMode.colors);
      this.isPlay = false;
      this.btActiNewModePlay.setBackgroundResource(R.mipmap.music_paly);
      return;
    }
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    if (paramSeekBar == this.sbActiNewMode1)
      this.speed = (paramSeekBar.getProgress() / 12);
    this.isSeletedColor = false;
  }

  public void onXYChange(float paramFloat1, float paramFloat2)
  {
    this.xPrecent = paramFloat1;
    this.yPrecent = paramFloat2;
  }

  public void play(View paramView)
  {
    int i = 255;
    if (this.isPlay)
    {
      ModeBusiness localModeBusiness2 = this.business;
      if (this.isNewCreate);
      while (true)
      {
        localModeBusiness2.sendCustomMode(null, i, this.changeMode, 2, this.speed, this.brt, this.ccvActiNewMode.colors.size(), this.ccvActiNewMode.colors);
        this.isPlay = false;
        paramView.setBackgroundResource(R.mipmap.music_paly);
        return;
        i = this.modeOrder;
      }
    }
    int j = -1 + this.ccvActiNewMode.colors.size();
    ModeBusiness localModeBusiness1;
    if (j > 1)
    {
      if (((Integer)this.ccvActiNewMode.colors.get(j)).intValue() != -16777216)
        this.colorCount = (j + 1);
    }
    else
    {
      localModeBusiness1 = this.business;
      if (!this.isNewCreate)
        break label199;
    }
    while (true)
    {
      localModeBusiness1.sendCustomMode(null, i, this.changeMode, 1, this.speed, this.brt, this.colorCount, this.ccvActiNewMode.colors);
      this.isPlay = true;
      paramView.setBackgroundResource(R.mipmap.music_stop);
      return;
      j--;
      break;
      label199: i = this.modeOrder;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.mode.ActNewMode
 * JD-Core Version:    0.6.0
 */