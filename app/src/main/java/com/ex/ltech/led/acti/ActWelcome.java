package com.ex.ltech.led.acti;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ex.ltech.LogRegForget.ActLoginActivity;
import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.guide.ActGuide;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.remote.control.yaokong.RcDiyBusiness;

import et.song.etclass.ETKey;

public class ActWelcome extends MyBaseActivity {
    private ItDiyAdapter adapter;
    private Button btAddDiy;
    private RcDiyBusiness business;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        UserFerences localUserFerences = UserFerences.getUserFerences(this);
        if (isZh())
            localUserFerences.putValue("isZh", Boolean.valueOf(true));
        while (localUserFerences.spFerences.getInt("isFirstOpen", -1) == -1) {
            goAct(ActGuide.class);
            finish();
            return;
            /*localUserFerences.putValue("isZh", Boolean.valueOf(false));*/
        }
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

    class ItDiyAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater layoutInflater;

        public ItDiyAdapter(Context localContext) {
            this.context = localContext;
            this.layoutInflater = LayoutInflater.from(localContext);
        }

        private void initializeViews(ETKey paramETKey, ViewHolder paramViewHolder, int paramInt) {
            if (paramETKey.GetRes() != -1) {
                paramViewHolder.btDiy.setVisibility(View.VISIBLE);
                paramViewHolder.btDiy.setBackgroundResource(paramETKey.GetRes());
            }
            while (true) {
                if (paramETKey.GetName().length() > 0)
                    paramViewHolder.btDiy.setText(paramETKey.GetName());
                paramViewHolder.btEdit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramView) {
                    }
                });
                if (getCount() <= 6)
                    break;
                if (paramInt > 2) {
                    paramViewHolder.tv_under_line.setVisibility(View.VISIBLE);
                    return;
                    /*paramViewHolder.btEdit.setVisibility(View.GONE);
                    paramViewHolder.btDiy.setVisibility(View.INVISIBLE);
                    paramViewHolder.btDiy.setOnClickListener(null);
                    continue;*/
                }
                paramViewHolder.tv_under_line.setVisibility(View.GONE);
                return;
            }
            if (paramInt > 5) {
                paramViewHolder.tv_under_line.setVisibility(View.VISIBLE);
                return;
            }
            paramViewHolder.tv_under_line.setVisibility(View.GONE);
        }

        public int getCount() {
            return ActWelcome.this.business.mDevice.GetCount();
        }

        public ETKey getItem(int paramInt) {
            return ActWelcome.this.business.mDevice.GetKeyByIndex(paramInt);
        }

        public long getItemId(int paramInt) {
            return paramInt;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            if (paramView == null) {
                paramView = this.layoutInflater.inflate(R.layout.it_diy, null);
                ViewHolder localViewHolder = new ViewHolder();
                localViewHolder.btDiy = (TextView) paramView.findViewById(R.id.bt_diy);
                localViewHolder.btEdit =  (TextView) paramView.findViewById(R.id.bt_edit);
                localViewHolder.tv_under_line = (TextView) paramView.findViewById(R.id.tv_under_line);
                paramView.setTag(localViewHolder);
            }
            initializeViews(getItem(paramInt), (ViewHolder) paramView.getTag(), paramInt);
            return paramView;
        }

        protected class ViewHolder {
            private TextView btDiy;
            private TextView btEdit;
            private TextView tv_under_line;

            protected ViewHolder() {
            }
        }
    }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.ActWelcome
 * JD-Core Version:    0.6.0
 */