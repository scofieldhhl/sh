package com.ex.ltech.led.acti.device;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ex.ltech.led.Global;
import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.MyBaseActivity;

/**
 * @Description 链接幻彩灯带
 * @time 2017/11/3
 */
public class AtCfg2Activity extends MyBaseActivity {
    private ImageView center;
    private boolean changeBg = true;
    Handler handler = new Handler();
    private TextView indicatori_light_status;
    private TextView info1;
    private TextView info2;
    private TextView info3;
    boolean isLampBlinkSeleted;
    private ImageView ivDevice;
    ImageView ivDeviceTop;
    private int res1;
    private int res2;
    Runnable runnable = new Runnable() {
        public void run() {
            AtCfg2Activity.this.loopPic();
        }
    };
    Runnable runnable2 = new Runnable() {
        public void run() {
            AtCfg2Activity.this.loopRcPic();
        }
    };
    private TextView tv_lamp_blink;
    private TextView tv_lamp_blink_seleted;

    @TargetApi(17)
    private double getScreenSizeOfDevice2() {
        Point localPoint = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(localPoint);
        DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
        double d = Math.sqrt(Math.pow(localPoint.x / localDisplayMetrics.xdpi, 2.0D) + Math.pow(localPoint.y / localDisplayMetrics.ydpi, 2.0D));
        Log.d("ContentValues", "Screen inches : " + d);
        return d;
    }

    private void loopPic() {
        /*if ((getIntent().getStringExtra("cfgType").equals("rgbhv") | getIntent().getStringExtra("cfgType").equals("rgb") | getIntent().getStringExtra("cfgType").equals("ct")))
            if (this.changeBg) {
                this.ivDeviceTop.setBackgroundResource(this.res1);
                if (this.changeBg)
                    break label144;
            }
        label144:
        for (boolean bool = true; ; bool = false) {
            this.changeBg = bool;
            this.handler.postDelayed(this.runnable, 1000L);
            return;
            this.ivDeviceTop.setBackgroundResource(this.res2);
            break;
            if (this.changeBg) {
                this.ivDevice.setBackgroundResource(this.res1);
                break;
            }
            this.ivDevice.setBackgroundResource(this.res2);
            break;
        }*/
    }

    private void loopRcPic() {
        if (this.changeBg) {
            findViewById(R.id.ic).setBackgroundResource(this.res1);
        }else {
            findViewById(R.id.ic).setBackgroundResource(this.res2);
        }
        changeBg = !changeBg;
        this.handler.postDelayed(this.runnable2, 1000L);
    }

    public void cancel(View paramView) {
        finish();
    }

    public void goReset(View paramView) {
        startActivityForResult(new Intent(this, AtCfg2Activity.class).putExtra("cfgType",
                getIntent().getStringExtra("cfgType")).putExtra("isReset", true), 0);
    }

    public void lampBlinkSeleted(View paramView) {
        if (!this.isLampBlinkSeleted) ;
        ImageView localImageView;
        for (boolean bool = true; ; bool = false) {
            this.isLampBlinkSeleted = bool;
            localImageView = (ImageView) findViewById(R.id.iv_bottom_right);
            if (!this.isLampBlinkSeleted)
                break;
            localImageView.setBackgroundResource(R.mipmap.next_red_1);
            this.tv_lamp_blink_seleted.setBackgroundResource(R.mipmap.time_seleted);
            return;
        }
        localImageView.setBackgroundResource(R.mipmap.next_gray_1);
        this.tv_lamp_blink_seleted.setBackgroundResource(R.mipmap.time_no_seleted);
    }

    public void next(View paramView) {
        if (!this.isLampBlinkSeleted)
            return;
        startActivityForResult(new Intent(this, AtCfg3Activity.class).putExtra("cfgType", getIntent().getStringExtra("cfgType")), 200);
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        if (paramInt2 == Global.net_config_ok) {
            setResult(Global.net_config_ok);
            finish();
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        ImageView localImageView2;
        TextView localTextView2;
        String str3;
        int j;
        if (getIntent().getBooleanExtra("isReset", false)) {
            setContentView(R.layout.at_cfg_2_help);
            setViewTitle();
            setMenuBackgroundRes(R.mipmap.back_ic);
            setTiTleTextRes(R.string.title_indicatori_light_status);
            localImageView2 = (ImageView) findViewById(R.id.ic);
            localTextView2 = (TextView) findViewById(R.id.restored_textView);
            this.tv_lamp_blink_seleted = ((TextView) findViewById(R.id.tv_lamp_blink_seleted));
            this.tv_lamp_blink = ((TextView) findViewById(R.id.tv_lamp_blink));
            this.tv_lamp_blink.setText(R.string.blue_lamp_blink);
            str3 = getIntent().getStringExtra("cfgType");
            j = -1;
            switch (str3.hashCode()) {
                default:
                    break;
                case 108447387:
                    if (!str3.equals("rgbhv"))
                        break;
                    j = 0;
                    break;
                case 112845:
                    if (!str3.equals("rgb"))
                        break;
                    j = 1;
                    break;
                case 3185:
                    if (!str3.equals("ct"))
                        break;
                    j = 2;
                    break;
                case 106767525:
                    if (!str3.equals("plugs"))
                        break;
                    j = 3;
                    break;
                case -934610874:
                    if (!str3.equals("remote"))
                        break;
                    j = 4;
                    break;
                case 1410339560:
                    if (!str3.equals("onePiontFive"))
                        break;
                    j = 5;
                    break;
            }

            switch (j) {
                default:
                case 0:
                    localImageView2.setBackgroundResource(R.mipmap.lamp_reset);
                    localTextView2.setText(R.string.d_reset_info);
                    break;
                case 1:
                    localImageView2.setBackgroundResource(R.mipmap.lamp_reset);
                    localTextView2.setText(R.string.d_reset_info);
                    break;
                case 2:
                    localImageView2.setBackgroundResource(R.mipmap.lamp_reset);
                    localTextView2.setText(R.string.d_reset_info);
                    break;
                case 3:
                    this.res1 = R.mipmap.plugs_reset;
                    this.res2 = R.mipmap.plugs_reset2;
                    loopRcPic();
                    localTextView2.setText(R.string.d_reset_info2);
                    break;
                case 4:
                    localTextView2.setText(R.string.d_reset_info3);
                    this.res1 = R.mipmap.rc_reset;
                    this.res2 = R.mipmap.rc_reset2;
                    loopRcPic();
                    break;
                case 5:
                    ((TextView) (TextView) findViewById(R.id.tv_head)).setText(R.string.reset_network);
                    localTextView2.setText(R.string.d_reset_info1);
                    localImageView2.setBackgroundResource(R.mipmap.cfg_105_device);
                    break;
            }

        }else if (getIntent().getBooleanExtra("isReset2", false)) {
                setContentView(R.layout.at_cfg_2_help2);
                TextView localTextView1 = (TextView) findViewById(R.id.textView);
                setViewTitle();
                setMenuBackgroundRes(R.mipmap.back_ic);
                setTiTleTextRes(R.string.title_con_help);
                ImageView localImageView1 = (ImageView) findViewById(R.id.ic);
                String str2 = getIntent().getStringExtra("cfgType");
                int i = -1;
                switch (str2.hashCode()) {
                    default:
                    case 108447387:
                        if (!str2.equals("rgbhv"))
                            break;
                        i = 0;
                        break;
                    case 112845:
                        if (!str2.equals("rgb"))
                            break;
                        i = 1;
                        break;
                    case 3185:
                        if (!str2.equals("ct"))
                            break;
                        i = 2;
                        break;
                    case 106767525:
                        if (!str2.equals("plugs"))
                            break;
                        i = 3;
                        break;
                    case -934610874:
                        if (!str2.equals("remote"))
                            break;
                        i = 4;
                        break;
                }
                switch (i) {
                    default:
                        break;
                    case 0:
                        localImageView1.setBackgroundResource(R.mipmap.lamp_reset);
                        localTextView1.setText(R.string.d_reset_info);
                        break;
                    case 1:
                        localImageView1.setBackgroundResource(R.mipmap.lamp_reset);
                        localTextView1.setText(R.string.d_reset_info);
                        break;
                    case 2:
                        localImageView1.setBackgroundResource(R.mipmap.lamp_reset);
                        localTextView1.setText(R.string.d_reset_info);
                        break;
                    case 3:
                        this.res1 = R.mipmap.plugs_reset;
                        this.res2 = R.mipmap.plugs_reset2;
                        loopRcPic();
                        localTextView1.setText(R.string.d_reset_info2);
                        break;
                    case 4:
                        this.res1 = R.mipmap.rc_reset;
                        this.res2 = R.mipmap.rc_reset2;
                        loopRcPic();
                        localTextView1.setText(R.string.d_reset_info3);
                        break;
                }

            }else {
            setContentView(R.layout.at_cfg_2);
            setViewTitle();
            setMenuBackgroundRes(R.mipmap.back_ic);
            this.ivDeviceTop = ((ImageView) findViewById(R.id.iv_device_top));
            this.ivDevice = ((ImageView) findViewById(R.id.iv_device));
            this.info1 = ((TextView) findViewById(R.id.info1));
            this.info2 = ((TextView) findViewById(R.id.info2));
            this.info3 = ((TextView) findViewById(R.id.info3));
            this.indicatori_light_status = ((TextView) findViewById(R.id.indicatori_light_status));
            this.tv_lamp_blink_seleted = ((TextView) findViewById(R.id.tv_lamp_blink_seleted));
            this.tv_lamp_blink = ((TextView) findViewById(R.id.tv_lamp_blink));
            this.center = ((ImageView) findViewById(R.id.center));
            String str1 = getIntent().getStringExtra("cfgType");
            if (str1.equals("ct")) {
                this.res1 = R.mipmap.cfg_ct_1;
                this.res2 = R.mipmap.cfg_ct_2;
                this.ivDeviceTop.setVisibility(View.VISIBLE);
                this.info3.setVisibility(View.VISIBLE);
                setTiTleTextRes(R.string.cfg_i_1);
                this.info2.setText(R.string.cfg_5);
                this.tv_lamp_blink.setText(R.string.lamp_blink);
                this.indicatori_light_status.setVisibility(View.VISIBLE);
            }
            if (str1.equals("plugs")) {
                this.res1 = R.mipmap.cfg_plug_1;
                this.res2 = R.mipmap.cfg_plug_2;
                setTiTleTextRes(R.string.cfg_i_2);
                this.info2.setText(R.string.cfg_2);
                this.tv_lamp_blink.setText(R.string.blue_lamp_blink);
                this.indicatori_light_status.setVisibility(View.VISIBLE);
                this.ivDeviceTop.setVisibility(View.VISIBLE);
                this.info3.setVisibility(View.VISIBLE);
            }
            if (str1.equals("bulb")) {
                this.res1 = R.mipmap.cfg_ct_1;
                this.res2 = R.mipmap.cfg_ct_2;
                setTiTleTextRes(R.string.cfg_i_3);
                this.info2.setText(R.string.cfg_3);
            }
            if (str1.equals("remote")) {
                this.res1 = R.mipmap.cgf_rc_1;
                this.res2 = R.mipmap.cgf_rc_2;
                setTiTleTextRes(R.string.cfg_i_4);
                this.info2.setText(R.string.n_rc_cfg_1);
                this.tv_lamp_blink.setText(R.string.blue_lamp_blink);
                this.indicatori_light_status.setVisibility(View.VISIBLE);
                this.ivDeviceTop.setVisibility(View.VISIBLE);
                this.info3.setVisibility(View.VISIBLE);
            }
            if (str1.equals("rgb")) {
                this.res1 = R.mipmap.cfg_rgb_1;
                this.res2 = R.mipmap.cfg_rgb_2;
                this.ivDeviceTop.setVisibility(View.VISIBLE);
                setTiTleTextRes(R.string.cfg_i_5);
                this.info2.setText(R.string.cfg_5);
                this.info3.setVisibility(View.VISIBLE);
                this.tv_lamp_blink.setText(R.string.lamp_blink);
                this.indicatori_light_status.setVisibility(View.VISIBLE);
            }
            if (str1.equals("rgbhv")) {
                this.res1 = R.mipmap.cfg_rgb_1;
                this.res2 = R.mipmap.cfg_rgb_2;
                this.ivDeviceTop.setVisibility(View.VISIBLE);
                setTiTleTextRes(R.string.cfg_i_HV);
                this.info2.setText(R.string.cfg_5);
                this.info3.setVisibility(View.VISIBLE);
                this.tv_lamp_blink.setText(R.string.lamp_blink);
                this.indicatori_light_status.setVisibility(View.VISIBLE);
            }
            if (str1.equals("onePiontFive")) {
                this.res1 = R.mipmap.cfg_105_1;
                this.res2 = R.mipmap.cfg_105_2;
                this.ivDeviceTop.setVisibility(View.VISIBLE);
                setTiTleTextRes(R.string.cfg_l_6);
                this.tv_lamp_blink.setText(R.string.blue_lamp_blink);
                this.info2.setText(R.string.cfg_6);
            }
            loopPic();
        }
        /*while (getScreenSizeOfDevice2() >= 4.0D);
        this.indicatori_light_status.setVisibility(View.GONE);*/
    }

    protected void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.runnable);
        this.handler.removeCallbacks(this.runnable2);
    }

    protected void onMenu() {
        super.onMenu();
        finish();
    }

    protected void onPause() {
        super.onPause();
    }
}
