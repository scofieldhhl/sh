package com.ex.ltech.led.acti.device;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ex.ltech.led.Global;
import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.MyBaseActivity;

public class AtCfg1Activity extends MyBaseActivity {
    //智能灯泡
    public void bulb(View paramView) {
        Intent localIntent = new Intent(this, AtCfg2Activity.class);
        localIntent.putExtra("cfgType", "bulb");
        startActivityForResult(localIntent, 200);
    }
    //色温灯带
    public void ct(View paramView) {
        Intent localIntent = new Intent(this, AtCfg2Activity.class);
        localIntent.putExtra("cfgType", "ct");
        startActivityForResult(localIntent, 200);
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
        setContentView(R.layout.at_cfg_list);
        setViewTitle();
        setMenuBackgroundRes(R.mipmap.back_ic);
        setTiTleTextRes(R.string.choose_add_device);
    }

    protected void onMenu() {
        super.onMenu();
        finish();
    }
    //智能网关
    public void onePiontFive(View paramView) {
        Intent localIntent = new Intent(this, AtCfg2Activity.class);
        localIntent.putExtra("cfgType", "onePiontFive");
        startActivityForResult(localIntent, 200);
    }
    //智能插座
    public void plugs(View paramView) {
        Intent localIntent = new Intent(this, AtCfg2Activity.class);
        localIntent.putExtra("cfgType", "plugs");
        startActivityForResult(localIntent, 200);
    }
    //万能遥控器
    public void remote(View paramView) {
        Intent localIntent = new Intent(this, AtCfg2Activity.class);
        localIntent.putExtra("cfgType", "remote");
        startActivityForResult(localIntent, 200);
    }

    //幻彩灯带
    public void rgb(View paramView) {
        Intent localIntent = new Intent(this, AtCfg2Activity.class);
        localIntent.putExtra("cfgType", "rgb");
        startActivityForResult(localIntent, 200);
    }
    //幻彩灯带 HV
    public void rgbHV(View paramView) {
        Intent localIntent = new Intent(this, AtCfg2Activity.class);
        localIntent.putExtra("cfgType", "rgbhv");
        startActivityForResult(localIntent, 200);
    }
}
