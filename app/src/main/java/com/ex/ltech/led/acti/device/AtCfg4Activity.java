package com.ex.ltech.led.acti.device;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ex.ltech.led.Global;
import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.hiflying.smartlink.ISmartLinker;
import com.hiflying.smartlink.OnSmartLinkListener;
import com.hiflying.smartlink.SmartLinkedModule;
import com.hiflying.smartlink.v7.MulticastSmartLinker;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AtCfg4Activity extends MyBaseActivity
        implements View.OnClickListener {
    String cfgState = "cfging";

    @BindView(R.id.device_connet)
    Button deviceConnet;

    @BindView(R.id.info)
    TextView info;
    private boolean isconncting = false;

    @BindView(R.id.iv_bottom_left)
    ImageView ivBottomLeft;

    @BindView(R.id.iv_bottom_right)
    ImageView ivBottomRight;

    @BindView(R.id.iv_cfg_no_ok)
    ImageView ivCfgNoOk;

    @BindView(R.id.iv_cfg_ok)
    ImageView ivCfgOk;
    protected ISmartLinker mSnifferSmartLinker;
    String psd;
    Runnable runnable = new Runnable() {
        public void run() {
            if (AtCfg4Activity.this.timerTime > 1) {
                AtCfg4Activity localAtCfg4Activity = AtCfg4Activity.this;
                localAtCfg4Activity.timerTime = (-1 + localAtCfg4Activity.timerTime);
                AtCfg4Activity.this.timeHandler.postDelayed(AtCfg4Activity.this.runnable, 1000L);
                AtCfg4Activity.this.second.setText(AtCfg4Activity.this.timerTime + "");
            }else {
                AtCfg4Activity.this.timerTime = 30;
                AtCfg4Activity.this.second.setText(AtCfg4Activity.this.timerTime + "");
                AtCfg4Activity.this.info.setText(R.string.cfging_time_failde);
                AtCfg4Activity.this.cfgState = "timeout";
                AtCfg4Activity.this.deviceConnet.setVisibility(View.GONE);
                AtCfg4Activity.this.ivBottomLeft.setVisibility(View.VISIBLE);
                AtCfg4Activity.this.ivBottomRight.setVisibility(View.VISIBLE);
                isconncting = false;
                AtCfg4Activity.this.timeHandler.removeCallbacks(AtCfg4Activity.this.runnable);
//                AtCfg4Activity.this.sbActOutletLed.setProgressSweep(0);
                AtCfg4Activity.this.ivCfgOk.setVisibility(View.GONE);
                AtCfg4Activity.this.smallSsecond.setVisibility(View.GONE);
                AtCfg4Activity.this.second.setVisibility(View.GONE);
                AtCfg4Activity.this.ivCfgNoOk.setVisibility(View.VISIBLE);
            }
//            AtCfg4Activity.this.sbActOutletLed.setProgressSweep(360 * (30 - AtCfg4Activity.this.timerTime) / 30);
        }
    };
//    private SecondArc sbActOutletLed;
    private TextView second;
    private TextView smallSsecond;
    Handler timeHandler = new Handler() {
    };
    int timerTime = 30;
    String wifiName;

    private void timer() {
        this.timerTime = 30;
        this.timeHandler.removeCallbacks(this.runnable);
        this.timeHandler.postDelayed(this.runnable, 1000L);
    }

    public void onClick(View paramView) {
        switch (paramView.getId()) {
            default:
            case R.id.device_connet:
                if (this.cfgState.equals("ok")) {
                    setResult(Global.net_config_ok);
                    finish();
                    return;
                }
                if (this.cfgState.equals("cfging")) {
                    this.mSnifferSmartLinker.stop();
                    finish();
                }
                if (!this.cfgState.equals("timeout")){
                    startSmartLink();
                    startNewSL();
                    this.cfgState = "cfging";
                    this.deviceConnet.setBackgroundResource(R.mipmap.cgf_cancel2);
                    this.ivCfgOk.setVisibility(View.GONE);
                    this.smallSsecond.setVisibility(View.VISIBLE);
                    this.second.setVisibility(View.VISIBLE);
                    this.ivCfgNoOk.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_bottom_left:
                finish();
                break;
            case R.id.iv_bottom_right:
        }
        this.smallSsecond.setVisibility(View.VISIBLE);
        this.second.setVisibility(View.VISIBLE);
        this.ivCfgNoOk.setVisibility(View.GONE);
        startSmartLink();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.at_cfg4);
        ButterKnife.bind(this);
//        this.sbActOutletLed = ((SecondArc) findViewById(R.id.sb_act_outlet_led));
        this.second = ((TextView) findViewById(R.id.second));
        this.smallSsecond = ((TextView) findViewById(R.id.small_second));
        findViewById(R.id.device_connet).setOnClickListener(this);
        findViewById(R.id.iv_bottom_left).setOnClickListener(this);
        findViewById(R.id.iv_bottom_right).setOnClickListener(this);
        this.wifiName = getIntent().getStringExtra("wifi");
        this.psd = getIntent().getStringExtra("psd");
        startSmartLink();
        setViewTitle();
        setMenuBackgroundRes(R.mipmap.h_remote_back);
        setTiTleText("");
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mSnifferSmartLinker.setOnSmartLinkListener(null);
    }

    protected void onMenu() {
        super.onMenu();
        finish();
    }

    protected void onPause() {
        super.onPause();
        this.timeHandler.removeCallbacks(this.runnable);
    }

    public void startNewSL() {
        this.mSnifferSmartLinker = MulticastSmartLinker.getInstance();
        this.mSnifferSmartLinker.setOnSmartLinkListener(new OnSmartLinkListener() {
            public void onCompleted() {
                AtCfg4Activity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        AtCfg4Activity.this.info.setText(R.string.cfging_ok);
                        AtCfg4Activity.this.cfgState = "ok";
                        AtCfg4Activity.this.deviceConnet.setBackgroundResource(R.mipmap.network_finish);
                        isconncting = false;
                        AtCfg4Activity.this.timeHandler.removeCallbacks(AtCfg4Activity.this.runnable);
//                        AtCfg4Activity.this.sbActOutletLed.setProgressSweep(0);
                        AtCfg4Activity.this.ivCfgOk.setVisibility(View.VISIBLE);
                        AtCfg4Activity.this.ivCfgNoOk.setVisibility(View.GONE);
                        AtCfg4Activity.this.smallSsecond.setVisibility(View.GONE);
                        AtCfg4Activity.this.second.setVisibility(View.GONE);
                    }
                });
            }

            public void onLinked(SmartLinkedModule paramSmartLinkedModule) {
            }

            public void onTimeOut() {
                AtCfg4Activity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        AtCfg4Activity.this.info.setText(R.string.cfging_time_out);
                        AtCfg4Activity.this.cfgState = "timeout";
                        AtCfg4Activity.this.deviceConnet.setVisibility(View.GONE);
                        AtCfg4Activity.this.ivBottomLeft.setVisibility(View.VISIBLE);
                        AtCfg4Activity.this.ivBottomRight.setVisibility(View.VISIBLE);
                        isconncting = false;
                        AtCfg4Activity.this.timeHandler.removeCallbacks(AtCfg4Activity.this.runnable);
//                        AtCfg4Activity.this.sbActOutletLed.setProgressSweep(0);
                        AtCfg4Activity.this.ivCfgOk.setVisibility(View.GONE);
                        AtCfg4Activity.this.smallSsecond.setVisibility(View.GONE);
                        AtCfg4Activity.this.second.setVisibility(View.GONE);
                        AtCfg4Activity.this.ivCfgNoOk.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        try {
            ISmartLinker localISmartLinker = this.mSnifferSmartLinker;
            String str = this.psd;
            String[] arrayOfString = new String[1];
            arrayOfString[0] = this.wifiName;
            localISmartLinker.start(this, str, arrayOfString);
            return;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    public void startSmartLink() {
        this.info.setText(R.string.cfging);
        timer();
        if (!this.isconncting) {
            this.isconncting = true;
            startNewSL();
            return;
        }
        this.mSnifferSmartLinker.stop();
        this.isconncting = false;
    }
}
