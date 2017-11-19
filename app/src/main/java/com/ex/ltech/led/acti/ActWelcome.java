package com.ex.ltech.led.acti;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.ex.ltech.LogRegForget.ActLoginActivity;
import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;

public class ActWelcome extends MyBaseActivity {
    private Button btAddDiy;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        UserFerences localUserFerences = UserFerences.getUserFerences(this);
        if (isZh())
            localUserFerences.putValue("isZh", Boolean.valueOf(true));
        /*while (localUserFerences.spFerences.getInt("isFirstOpen", -1) == -1) {
            goAct(ActGuide.class);
            finish();
            return;
            localUserFerences.putValue("isZh", Boolean.valueOf(false));
        }*/
        if (!localUserFerences.spFerences.getBoolean("isLog", false)) {
            goAct(ActLoginActivity.class);
            finish();
            return;
        }
        setContentView(R.layout.act_welcome);
        new Handler().postDelayed(new Runnable() {
                                 public void run() {
                                     ActWelcome.this.goAct(DeviceListActivity.class);
                                     ActWelcome.this.finish();
                                 }
                             }
                        , 1000L);
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}