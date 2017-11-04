package com.ex.ltech.ct;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ex.ltech.ct.timing.act.ActTiming;
import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.colors.AtPanelLearnActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.my_view.MLImageView;
import com.ex.ltech.led.my_view.SimpleColorPickerView;
import com.ex.ltech.led.vo.CtSceneVo;

import java.util.List;

import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;

public class AtColor extends Activity {
    private ModeGridViewAdapter adapter;
    int b;
    int brt = 255;
    private int brtType = 209;
    private ColorBussiness bussiness;
    int c;
    boolean changedColor;
    private CmdDateBussiness cmdDateBussiness;
    int g;
    private GridView gridView;
    boolean isResume;
    boolean on;
    SimpleColorPickerView.OnColorChangedListener onColorChangedListener;
    OnOffListener onOffListener = new OnOffListener();
    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;
    int r;
    int sbTime;
    SeekBar seekBar;
    SendPipeListener sendPipeListener = new SendPipeListener() {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2) {
        }
    };
    boolean showedMenu;
    SimpleColorPickerView simpleColorPickerView;
    TextView tv_ct_back;
    int w;
    private int warmWhiteType = 210;

    private void getOnOff() {
        new Handler().postDelayed(new Runnable() {
                                      public void run() {
                                          XlinkAgent.getInstance().addXlinkListener(AtColor.this.onOffListener);
                                          XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                                          DeviceManage.getInstance();
                                          localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), AtColor.this.cmdDateBussiness.getDeviceOnOffInfoCmd(), AtColor.this.sendPipeListener);
                                      }
                                  }
                , 2000L);
    }

    private void setGridView() {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        float f = localDisplayMetrics.density;
        int i = (int) (f * 832);
        int j = (int) (f * 100);
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(i, -1);
        this.gridView.setLayoutParams(localLayoutParams);
        this.gridView.setColumnWidth(j);
        this.gridView.setHorizontalSpacing(5);
        this.gridView.setStretchMode(GridView.NO_STRETCH);
        this.gridView.setNumColumns(8);
    }

    public void add(View paramView) {
    /*if (this.showedMenu)
      findViewById(R.id.rl_pop).setVisibility(View.GONE);
    while (true)
    {
      boolean bool1 = this.showedMenu;
      boolean bool2 = false;
      if (!bool1)
        bool2 = true;
      this.showedMenu = bool2;
      return;
      findViewById(R.id.rl_pop).setVisibility(View.VISIBLE);
    }*/
    }

    public void addPanel(View paramView) {
        findViewById(R.id.rl_pop).setVisibility(View.GONE);
        findViewById(R.id.rl_add_panel).setVisibility(View.VISIBLE);
    }

    public void addPanel1(View paramView) {
        findViewById(R.id.rl_add_panel).setVisibility(View.GONE);
        startActivity(new Intent(this, AtPanelLearnActivity.class).putExtra("type", "panel5"));
    }

    public void addPanel2(View paramView) {
        findViewById(R.id.rl_add_panel).setVisibility(View.GONE);
        startActivity(new Intent(this, AtPanelLearnActivity.class).putExtra("type", "panel6"));
    }

    public void addRc(View paramView) {
        findViewById(R.id.rl_pop).setVisibility(View.GONE);
        findViewById(R.id.rl_add_rc).setVisibility(View.VISIBLE);
    }

    public void addRc1(View paramView) {
        findViewById(R.id.rl_add_rc).setVisibility(View.GONE);
        startActivity(new Intent(this, AtPanelLearnActivity.class).putExtra("type", "rc3"));
    }

    public void addRc2(View paramView) {
        findViewById(R.id.rl_add_rc).setVisibility(View.GONE);
        startActivity(new Intent(this, AtPanelLearnActivity.class).putExtra("type", "rc4"));
    }

    public void back(View paramView) {
        finish();
    }

    public void changeCol(View paramView) {
        int i = 1;
        findViewById(R.id.rl_pop).setVisibility(View.GONE);
    /*if (this.changedColor)
    {
      this.simpleColorPickerView.setViewBgRes(R.mipmap.ct_col_bg_1, false);
      UserFerences.getUserFerences(this).putValue("CtColorBg", Integer.valueOf(i));
      if (this.changedColor)
        break label82;
    }
    while (true)
    {
      this.changedColor = i;
      return;
      this.simpleColorPickerView.setViewBgRes(R.mipmap.ct_col_bg_2, false);
      UserFerences.getUserFerences(this).putValue("CtColorBg", Integer.valueOf(2));
      break;
      label82: i = 0;
    }*/
    }

    public int dip2px(int paramInt) {
        return (int) (0.5F + getResources().getDisplayMetrics().density * paramInt);
    }

    public void edit(View paramView) {
    }

    public void goTime(View paramView) {
        findViewById(R.id.rl_pop).setVisibility(View.GONE);
        startActivity(new Intent(this, ActTiming.class));
    }

    public void hideAddPanelDialog(View paramView) {
        findViewById(R.id.rl_add_panel).setVisibility(View.GONE);
    }

    public void hideAddRcDialog(View paramView) {
        findViewById(R.id.rl_add_rc).setVisibility(View.GONE);
    }

    public void hidePopWindow(View paramView) {
        paramView.setVisibility(View.GONE);
    }

    public void off(View paramView) {
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getAllOnOffCmd(160), this.sendPipeListener);
        this.on = false;
        findViewById(R.id.act_gray_layer).setVisibility(View.VISIBLE);
    }

    public void on(View paramView) {
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getAllOnOffCmd(161), this.sendPipeListener);
        this.on = true;
        findViewById(R.id.act_gray_layer).setVisibility(View.GONE);
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if (paramInt2 == 1000) {
            this.bussiness.updateData();
            this.adapter.notifyDataSetChanged();
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.at_ct_color);
        this.seekBar = ((SeekBar) findViewById(R.id.sb));
        this.gridView = ((GridView) findViewById(R.id.grid));
        this.tv_ct_back = ((TextView) findViewById(R.id.tv_ct_back));
        this.cmdDateBussiness = new CmdDateBussiness("0000");
        setTextPrecent(this.seekBar.getProgress());
        SeekBar localSeekBar = this.seekBar;
        this.onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean) {
                AtColor localAtColor = AtColor.this;
                localAtColor.sbTime = (1 + localAtColor.sbTime);
                AtColor.this.brt = (255 * paramSeekBar.getProgress() / 100);
                if (AtColor.this.sbTime % 10 == 0) {
                    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                    DeviceManage.getInstance();
                    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), AtColor.this.cmdDateBussiness.getCtColorCmd(AtColor.this.brtType, AtColor.this.brt, AtColor.this.c, AtColor.this.w), AtColor.this.sendPipeListener);
                }
                AtColor.this.setTextPrecent(paramSeekBar.getProgress());
            }

            public void onStartTrackingTouch(SeekBar paramSeekBar) {
            }

            public void onStopTrackingTouch(SeekBar paramSeekBar) {
                XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                DeviceManage.getInstance();
                localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), AtColor.this.cmdDateBussiness.getCtColorCmd(AtColor.this.brtType, AtColor.this.brt, AtColor.this.c, AtColor.this.w), AtColor.this.sendPipeListener);
            }
        };
        localSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        this.simpleColorPickerView = ((SimpleColorPickerView) findViewById(R.id.color));
        SimpleColorPickerView localSimpleColorPickerView = this.simpleColorPickerView;
        this.onColorChangedListener = new SimpleColorPickerView.OnColorChangedListener() {
            public void onPikerTouchUp(int paramInt) {
            }

            public void onPikerXYChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
                AtColor.this.r = paramInt2;
                AtColor.this.g = paramInt3;
                AtColor.this.b = paramInt4;
                if (AtColor.this.r == 255)
                    AtColor.this.c = (255 - AtColor.this.b / 2);
                if (AtColor.this.b > 247)
                    AtColor.this.c = (AtColor.this.r / 2);
            }

            public void onProgressPercent(float paramFloat) {
                AtColor.this.w = (int) (255.0F * paramFloat);
                if (AtColor.this.w < 1)
                    AtColor.this.w = 0;
                if (AtColor.this.w > 254)
                    AtColor.this.w = 255;
                AtColor.this.c = (255 - AtColor.this.w);
                System.out.println(" c = " + AtColor.this.c + " w = " + AtColor.this.w);
                XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                DeviceManage.getInstance();
                localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), AtColor.this.cmdDateBussiness.getCtColorCmd(AtColor.this.warmWhiteType, AtColor.this.brt, AtColor.this.c, AtColor.this.w), AtColor.this.sendPipeListener);
            }
        };
        localSimpleColorPickerView.setListener(onColorChangedListener);
        this.simpleColorPickerView.setViewBgRes(R.mipmap.ct_col_bg_1, false);
        this.bussiness = new ColorBussiness(this);
        this.bussiness.loadCtSceneVos();
        this.adapter = new ModeGridViewAdapter(this, this.bussiness.vos);
        setGridView();
        this.gridView.setAdapter(this.adapter);
        this.tv_ct_back.setText(UserFerences.getUserFerences(this).spFerences.getString("dName" + DeviceListActivity.deviceMacAddress, ""));
    /*if (UserFerences.getUserFerences(this).spFerences.getInt("CtColorBg", 1) == 1)
    {
      this.changedColor = false;
      this.simpleColorPickerView.setViewBgRes(R.mipmap.ct_col_bg_1, false);
    }
    while (true)
    {
      if (DeviceListActivity.devicePid == "160fa2b3051a03e9160fa2b3051ac601")
        findViewById(R.id.add_bt).setVisibility(View.VISIBLE);
      findViewById(R.id.add_bt).setVisibility(View.VISIBLE);
      return;
      this.changedColor = true;
      this.simpleColorPickerView.setViewBgRes(R.mipmap.ct_col_bg_2, false);
    }*/
    }

    protected void onPause() {
        super.onPause();
        this.isResume = false;
        XlinkAgent.getInstance().removeListener(this.onOffListener);
    }

    protected void onResume() {
        super.onResume();
        this.isResume = true;
        getOnOff();
    }

    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
        if (!this.isResume) ;
    }

    void setTextPrecent(int paramInt) {
        ((TextView) findViewById(R.id.precent)).setText(paramInt + "%");
    }

    class ModeGridViewAdapter extends BaseAdapter {
        private List<CtSceneVo> itemVos;
        private Activity pct;
        int[] reses = {R.mipmap.ct_scene1, R.mipmap.ct_scene2, R.mipmap.ct_scene3, R.mipmap.ct_scene4,
                R.mipmap.ct_scene5, R.mipmap.ct_scene6, R.mipmap.ct_scene7, R.mipmap.ct_scene8};

        public ModeGridViewAdapter(Activity act, List<CtSceneVo> arg2) {
            this.pct = act;
            this.itemVos = arg2;
        }

        public int getCount() {
            return this.itemVos.size();
        }

        public Object getItem(int paramInt) {
            return this.itemVos.get(paramInt);
        }

        public long getItemId(int paramInt) {
            return paramInt;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            Holder localHolder;
            CtSceneVo localCtSceneVo = null;
            final int i = 0;
            if (paramView == null) {
        /*localHolder = new Holder();
        this.pct.getLayoutInflater();
        paramView = LayoutInflater.from(this.pct).inflate(R.layout.it_gv_ct, null);
        localHolder.edit = ((ImageView)paramView.findViewById(R.id.iv_acti_scene_list_item_4));
        localHolder.name = ((TextView)paramView.findViewById(R.id.tv_acti_scene_list_item_2));
        localHolder.customIc = ((MLImageView)paramView.findViewById(R.id.iv_acti_scene_list_item_5));
        paramView.setTag(localHolder);
        localCtSceneVo = (CtSceneVo)this.itemVos.get(paramInt);
        ImageView localImageView = localHolder.edit;
        if (!localCtSceneVo.isEdit())
          break label236;
        localImageView.setVisibility(View.VISIBLE);
        localHolder.name.setText(localCtSceneVo.getName());
        if (!localCtSceneVo.getIcPath().equals(""))
          break label243;
        localHolder.customIc.setBackgroundResource(this.reses[localCtSceneVo.getIcResPosi()]);*/
            }
        /*localHolder.customIc.setOnClickListener(new View.OnClickListener(paramInt, localCtSceneVo)
        {
          public void onClick(View paramView)
          {
            AtColor.this.bussiness.hideEditBtn();
            AtColor.ModeGridViewAdapter.this.notifyDataSetChanged();
            XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
            DeviceManage.getInstance();
            localXlinkAgent.sendPipeData(DeviceManage.getxDevice(),
                    cmdDateBussiness.getCtSceneColorCmd(1 + i, localCtSceneVo.getName(), localCtSceneVo.getBrt(),
                            localCtSceneVo.getC(), localCtSceneVo.getW()), AtColor.this.sendPipeListener);
            AtColor.this.seekBar.setOnSeekBarChangeListener(null);
            AtColor.this.seekBar.setProgress(100 * localCtSceneVo.getBrt() / 255);
            AtColor.this.seekBar.setOnSeekBarChangeListener(AtColor.this.onSeekBarChangeListener);
            AtColor.this.simpleColorPickerView.setListener(null);
            AtColor.this.setTextPrecent(AtColor.this.seekBar.getProgress());
            AtColor.this.simpleColorPickerView.setPikerXy2(100 * localCtSceneVo.getW() / 255);
            AtColor.this.simpleColorPickerView.setListener(AtColor.this.onColorChangedListener);
          }
        });
        localHolder.customIc.setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramView)
          {
            AtColor.this.bussiness.showEditBtn();
            AtColor.ModeGridViewAdapter.this.notifyDataSetChanged();
            return false;
          }
        });
        localHolder.edit.setOnClickListener(new View.OnClickListener(paramInt, localCtSceneVo)
        {
          public void onClick(View paramView)
          {
            AtColor.this.bussiness.hideEditBtn();
            AtColor.ModeGridViewAdapter.this.notifyDataSetChanged();
            Intent localIntent = new Intent(AtColor.this, AtSceneEdit.class);
            localIntent.putExtra("ctScenePosi", i);
            localIntent.putExtra("ctSceneName", localCtSceneVo.getName());
            localIntent.putExtra("sceneDataStr", AtColor.this.bussiness.gs.toJson(localCtSceneVo));
            AtColor.this.startActivityForResult(localIntent, 0);
          }
        });*/
            localHolder = (Holder) paramView.getTag();
//      i = 8;
            localHolder.customIc.setImageBitmap(BitmapFactory.decodeFile(localCtSceneVo.getIcPath()));
            return paramView;

        }

        class Holder {
            MLImageView customIc;
            ImageView edit;
            TextView name;

            Holder() {
            }
        }
    }

    class OnOffListener
            implements XlinkNetListener {
        OnOffListener() {
        }

        public void onDataPointUpdate(XDevice paramXDevice, List<DataPoint> paramList, int paramInt) {
        }

        public void onDeviceStateChanged(XDevice paramXDevice, int paramInt) {
        }

        public void onDisconnect(int paramInt) {
        }

        public void onEventNotify(EventNotify paramEventNotify) {
        }

        public void onLocalDisconnect(int paramInt) {
        }

        public void onLogin(int paramInt) {
        }

        public void onRecvPipeData(short paramShort, XDevice paramXDevice, final byte[] paramArrayOfByte) {
      /*AtColor.this.runOnUiThread(new Runnable(paramArrayOfByte)
      {
        public void run()
        {
          String str1 = StringUtils.btye2Str(paramArrayOfByte);
          String str2 = StringUtils.btye2Str(paramArrayOfByte);
          if ((str2.length() == 18) && (str2.indexOf("AAEB") != -1))
          {
            if (str2.substring(12, 14).equals("01"))
              AtColor.this.findViewById(R.id.act_gray_layer).setVisibility(View.GONE);
            if (str2.substring(12, 14).equals("00"))
              AtColor.this.findViewById(R.id.act_gray_layer).setVisibility(View.VISIBLE);
            if (str2.substring(14, 16).equalsIgnoreCase("AA"));
          }
          do
            return;
          while ((str1.indexOf("66BB") == -1) || (str1.indexOf("EB") == -1) || (paramArrayOfByte.length < 92));
          if (!AtColor.this.bussiness.isFristParseData)
            AtColor.this.adapter.notifyDataSetChanged();
          ColorBussiness localColorBussiness = AtColor.this.bussiness;
          boolean bool1 = AtColor.this.bussiness.isFristParseData;
          boolean bool2 = false;
          if (!bool1)
            bool2 = true;
          localColorBussiness.isFristParseData = bool2;
        }
      });*/
        }

        public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte) {
      /*String str1 = StringUtils.btye2Str(paramArrayOfByte);
      String str2 = StringUtils.btye2Str(paramArrayOfByte);
      if ((str1.length() == 18) && (str1.indexOf("AAEB") != -1))
      {
        if (str1.substring(12, 14).equals("01"))
          AtColor.this.findViewById(R.id.act_gray_layer).setVisibility(View.GONE);
        if (str1.substring(12, 14).equals("00"))
          AtColor.this.findViewById(R.id.act_gray_layer).setVisibility(View.VISIBLE);
      }
      do
        return;
      while ((str2.indexOf("66BB") == -1) || (str2.indexOf("EB") == -1) || (paramArrayOfByte.length < 92));
      if (!AtColor.this.bussiness.isFristParseData)
        AtColor.this.adapter.notifyDataSetChanged();
      ColorBussiness localColorBussiness = AtColor.this.bussiness;
      boolean bool1 = AtColor.this.bussiness.isFristParseData;
      boolean bool2 = false;
      if (!bool1)
        bool2 = true;
      localColorBussiness.isFristParseData = bool2;
      System.out.println(" main onRecvPipeSyncData      " + StringUtils.btye2Str(paramArrayOfByte));*/
        }

        public void onStart(int paramInt) {
        }
    }
}
