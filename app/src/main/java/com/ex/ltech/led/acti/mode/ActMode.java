package com.ex.ltech.led.acti.mode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.vo.ModeVo;
import com.indris.material.RippleView;

public class ActMode extends MyBaseActivity
        implements AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener {
    private RippleView btn_acti_mode_play;
    private ModeBusiness business;
    private GridView gridView;
    boolean isAllSeleted;
    boolean isLongClick;
    private boolean isMultiSeleted;
    private boolean isPlay;
    private boolean isSingleSeleted;
    ImageView iv_frag_sys_inside_all_seleted;
    private ModeGridViewAdapter madapter;
    private SeekBar sb_acti_mode;
    private int singleSeletedIndex = -1;
    private int speed = 2;
    int tempSpeed;

    private void findView() {
        this.iv_frag_sys_inside_all_seleted = ((ImageView) findViewById(R.id.iv_frag_sys_inside_all_seleted));
        this.gridView = ((GridView) findViewById(R.id.gv_act_mode));
        this.sb_acti_mode = ((SeekBar) findViewById(R.id.sb_acti_mode));
        this.btn_acti_mode_play = ((RippleView) findViewById(R.id.btn_acti_mode_play));
    }

    private void init() {
        this.business = new ModeBusiness(this);
        this.business.initModes();
        setAdt();
        this.business.prepareLink();
    }

    private void setAdt() {
        this.madapter = new ModeGridViewAdapter(this, this.business.modes, this.business.getNewCreateModeBitmaps());
        this.gridView.setAdapter(this.madapter);
        this.madapter.setMoreSeletedListener(new MoreSeletedListener() {
            public void onMoreSeleted(int paramInt) {
                ActMode.this.business.onMoreSeleted(paramInt);
                ActMode.this.madapter.notifyDataSetChanged();
                if (ActMode.this.business.isMultiSeleted()) {
                    ActMode.this.sb_acti_mode.setVisibility(View.INVISIBLE);
                    return;
                }
                ActMode.this.sb_acti_mode.setVisibility(View.VISIBLE);
            }
        });
        this.madapter.setSingleSeletedListener(new SingleSeletedListener() {
            public void onLongClick(int paramInt) {
                ActMode.this.isLongClick = true;
                ActMode.this.business.editMode(paramInt);
            }

            public void onSingleSeleted(int paramInt) {
                ModeVo localModeVo;
                if (ActMode.this.isLongClick)
                    ActMode.this.isLongClick = false;
                singleSeletedIndex = paramInt;
                business.onSingleSeleted(paramInt);
                madapter.notifyDataSetChanged();
                localModeVo = business.modes.get(paramInt);
                if (localModeVo.isAddBtn()) {
                    ActMode.this.sb_acti_mode.setProgress(12 * localModeVo.getSpeed());
                    ActMode.this.sb_acti_mode.setVisibility(View.VISIBLE);
                    ActMode.this.iv_frag_sys_inside_all_seleted.setBackgroundResource(R.mipmap.ic_no_all_seleted);
                    ActMode.this.isAllSeleted = false;
                    ActMode.this.sb_acti_mode.setVisibility(View.VISIBLE);
                }
                if (!isPlay) {
                    ActMode.this.btn_acti_mode_play.setBackgroundResource(R.mipmap.music_paly);
                    isPlay = false;
                }
            }
        });
    }

    private void setListener() {
        this.sb_acti_mode.setOnSeekBarChangeListener(this);
        this.btn_acti_mode_play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                if (!isPlay) {
                    isPlay = true;
                } else {
                    isPlay = false;
                }
                if (ActMode.this.business.isMultiSeleted()) {
                    if (!ActMode.this.isPlay) {
                        ActMode.this.btn_acti_mode_play.setBackgroundResource(R.mipmap.music_stop);
                        ActMode.this.business.sendModes();
                    } else {
                        ActMode.this.business.offModes();
                        ActMode.this.btn_acti_mode_play.setBackgroundResource(R.mipmap.music_paly);
                    }

                }
            }
        });
    }

    private void setMyTitle() {
        setViewTitle();
        setMenuBackgroundRes(R.mipmap.device_ic);
        setTiTleTextRes(R.string.mode);
        setDeviceTextRes(Main.deviceVo.getDeviceName());
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if (paramInt2 == 1000) {
            this.business.initModes();
            setAdt();
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.acti_mode);
        setMyTitle();
        findView();
        init();
        setListener();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
        this.business.onSingleSeleted(paramInt);
        this.madapter.notifyDataSetChanged();
    }

    protected void onMenu() {
        super.onMenu();
        finish();
    }

    public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean) {
        int i = 1;
        this.speed = (paramSeekBar.getProgress() / 12);
        int j = 1;
        ModeBusiness localModeBusiness1;
        if (this.tempSpeed != this.speed) {
            this.tempSpeed = this.speed;
            if (this.isSingleSeleted) {
                ModeBusiness localModeBusiness2 = this.business;
                if (this.speed != 0)
                    j = this.speed;
                localModeBusiness2.changeMoveSpeed(j);
                this.business.sendSingleMode(this.singleSeletedIndex);
            }
            if (this.isMultiSeleted) {
                localModeBusiness1 = this.business;
                if (this.speed != 0)
                    i = this.speed;
                localModeBusiness1.sendModesWithSameSpeed(i);
            }
        }
        System.out.println("isMultiSeleted            -----      " + this.isMultiSeleted);
        System.out.println("isSingleSeleted            -----      " + this.speed);
    }

    protected void onResume() {
        super.onResume();
        if (!Main.lastSendCmd.equals(Main.modeCmd)) {
            this.isPlay = false;
            this.btn_acti_mode_play.setBackgroundResource(R.mipmap.music_paly);
        }
    }

    public void onStartTrackingTouch(SeekBar paramSeekBar) {
        this.isMultiSeleted = this.business.isMultiSeleted();
        this.isSingleSeleted = this.business.isSingleSeleted();
    }

    public void onStopTrackingTouch(SeekBar paramSeekBar) {
        this.business.saveMoveSpeed2Sd();
    }

    public void seletedAll(View paramView) {
        this.singleSeletedIndex = -1;
        if (!this.isAllSeleted) {
            this.iv_frag_sys_inside_all_seleted.setBackgroundResource(R.mipmap.mode_all_seleted);
            this.isAllSeleted = true;
            this.sb_acti_mode.setVisibility(View.INVISIBLE);
        } else {
            this.business.seletedAll(this.isAllSeleted);
            this.madapter.notifyDataSetChanged();
            this.iv_frag_sys_inside_all_seleted.setBackgroundResource(R.mipmap.ic_no_all_seleted);
            this.isAllSeleted = false;
            this.sb_acti_mode.setVisibility(View.VISIBLE);
        }
    }

    public static abstract interface MoreSeletedListener {
        public abstract void onMoreSeleted(int paramInt);
    }

    public static abstract interface SingleSeletedListener {
        public abstract void onLongClick(int paramInt);

        public abstract void onSingleSeleted(int paramInt);
    }
}
