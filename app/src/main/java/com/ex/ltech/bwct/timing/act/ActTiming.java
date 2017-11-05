package com.ex.ltech.bwct.timing.act;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.AbsListView;

import com.ex.ltech.bwct.timing.TimingBussines;
import com.ex.ltech.bwct.timing.TimingListAdapter;
import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.vo.TimingVo;

import java.util.ArrayList;
import java.util.List;

public class ActTiming extends MyBaseActivity
        implements TimingListAdapter.OnListVSwichChangeListener {
    private TimingListAdapter adt;
    private TimingBussines bussines;
    boolean checkJustOnce;
    ProgressDialog getTimeDialog;
    Handler handler = new Handler() {
    };
    private int itemPosition;
    List<TimingVo> justOnceVos = new ArrayList();
    Handler loopHandler = new Handler() {
    };
    private SwipeMenuListView lv_act_timing;
    Runnable runnable = new Runnable() {
        public void run() {
            ActTiming.this.bussines.getDeviceTiming();
        }
    };
    List<Integer> tempPosi = new ArrayList();
    private com.ex.ltech.bwct.timing.TimingData timingData;
    List<TimingVo> timingVos;
    private List<TimingVo> vos;

    private void findView() {
        this.lv_act_timing = ((SwipeMenuListView) findViewById(R.id.lv_act_timing));
        this.lv_act_timing.setMenuCreator(new SwipeMenuCreator() {
            public void create(SwipeMenu paramSwipeMenu) {
                SwipeMenuItem localSwipeMenuItem1 = new SwipeMenuItem(ActTiming.this.getApplicationContext());
                localSwipeMenuItem1.setBackground(new ColorDrawable(Color.rgb(201, 201, 206)));
                localSwipeMenuItem1.setWidth(ActTiming.this.bussines.dp2px(60));
                localSwipeMenuItem1.setIcon(R.mipmap.ic_edit_pressed);
                localSwipeMenuItem1.setTitleColor(-1);
                paramSwipeMenu.addMenuItem(localSwipeMenuItem1);
                SwipeMenuItem localSwipeMenuItem2 = new SwipeMenuItem(ActTiming.this.getApplicationContext());
                localSwipeMenuItem2.setBackground(new ColorDrawable(ActTiming.this.getResources().getColor(R.color.color5)));
                localSwipeMenuItem2.setWidth(ActTiming.this.bussines.dp2px(70));
                localSwipeMenuItem2.setIcon(R.mipmap.ci_del_btn);
                paramSwipeMenu.addMenuItem(localSwipeMenuItem2);
            }
        });
    }

    private void init() {
        this.getTimeDialog = ProgressDialog.show(this, "", getString(R.string.get_time_data), false);
        this.getTimeDialog.setCancelable(true);
        this.getTimeDialog.show();
        this.vos = new ArrayList();
        this.timingData = com.ex.ltech.bwct.timing.TimingData.getInstance(this);
        this.bussines = new TimingBussines(this);
        this.bussines.setActTiming(this);
        this.adt = new TimingListAdapter(this, this.vos);
        this.lv_act_timing.setAdapter(this.adt);
        setListTotgleBtnListenerDelay();
        this.bussines.synTime2Device();
        this.loopHandler.postDelayed(this.runnable, 2000L);
        this.lv_act_timing.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {
            }

            public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt) {
                if (paramInt != 0) {
                    ActTiming.this.adt.setOnListVSwichChangeListener(null);
                    return;
                }
                System.out.println("adt.isLvScroll = false;");
                ActTiming.this.adt.setOnListVSwichChangeListener(ActTiming.this);
            }
        });
    }

    private void openCheckJustOnce() {
        this.timingVos = com.ex.ltech.bwct.timing.TimingData.getInstance(this).getTimingVos4Sd();
        this.justOnceVos.clear();
        this.tempPosi.clear();
        this.checkJustOnce = true;
        for (int i = 0; i < this.timingVos.size(); i++) {
            if ((!((TimingVo) this.timingVos.get(i)).isJustOnce()) || (!((TimingVo) this.timingVos.get(i)).isSwich()))
                continue;
            this.justOnceVos.add(this.timingVos.get(i));
            this.tempPosi.add(Integer.valueOf(i));
        }
        if (this.justOnceVos.size() == 0)
            return;
        new Thread() {
            public void run() {
                for (int i = 0; i < ActTiming.this.justOnceVos.size(); i++) {
                    int j = ((Integer) ActTiming.this.tempPosi.get(i)).intValue();
                    if (System.currentTimeMillis() <= ((TimingVo) ActTiming.this.justOnceVos.get(i)).getJustOnceCurrentTime())
                        continue;
                    ActTiming.this.timingVos.remove(j);
                    TimingVo localTimingVo = (TimingVo) ActTiming.this.justOnceVos.get(i);
                    localTimingVo.setSwich(false);
                    ActTiming.this.timingVos.add(j, localTimingVo);
                    com.ex.ltech.bwct.timing.TimingData.getInstance(ActTiming.this).saveTimingVos2Sd(ActTiming.this.timingVos);
                    if (ActTiming.this.justOnceVos.size() == 1)
                        ActTiming.this.checkJustOnce = false;
                    ActTiming.this.justOnceVos.remove(i);
                }
                ActTiming.this.runOnUiThread(new Runnable() {
                    public void run() {
                        ActTiming.this.upDateData();
                        ActTiming.this.adt.notifyDataSetChanged();
                        ActTiming.this.setListTotgleBtnListenerDelay();
                    }
                });
            }
        }
                .start();
    }

    private void setListTotgleBtnListenerDelay() {
        this.adt.setOnListVSwichChangeListener(null);
        this.handler.postDelayed(new Runnable() {
                                     public void run() {
                                         ActTiming.this.adt.setOnListVSwichChangeListener(ActTiming.this);
                                     }
                                 }
                , 700L);
    }

    private void setListener() {
        this.lv_act_timing.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            public boolean onMenuItemClick(final int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2) {
                switch (paramInt2) {
                    default:
                        return false;
                    case 0:
                        if (((TimingVo) ActTiming.this.vos.get(paramInt1)).isOther()) {
                            ActTiming.this.toast(R.string.no_edit_other);
                            return false;
                        }
                        ActTiming.this.bussines.isCreateNewTime = false;
                        itemPosition = paramInt1;
                        ActTiming.this.handler.postDelayed(new Runnable() {
                                                               public void run() {
                                                                   Intent localIntent = new Intent(ActTiming.this, ActAddTiming.class);
                                                                   localIntent.putExtra("itemPosi", paramInt1);
                                                                   ActTiming.this.startActivityForResult(localIntent, 100);
                                                               }
                                                           }
                                , 200L);
                        return false;
                    case 1:
                }
                ActTiming.this.bussines.delTimingItem(ActTiming.this.vos, paramInt1);
                final ProgressDialog localProgressDialog = ProgressDialog.show(ActTiming.this, "", ActTiming.this.getString(R.string.get_d_info), false);
                localProgressDialog.setCancelable(true);
                localProgressDialog.show();
                ActTiming.this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener() {
                    public void onSendFailde() {
                        ActTiming.this.toast(R.string.add_time_no_ok);
                        localProgressDialog.dismiss();
                    }

                    public void onSendOk() {
                        ActTiming.this.vos.remove(paramInt1);
                        com.ex.ltech.led.acti.timing.TimingData.getInstance(ActTiming.this.getApplicationContext()).saveTimingVos2Sd(ActTiming.this.vos);
                        ActTiming.this.bussines.reSortTimingData(ActTiming.this.vos);
                        ActTiming.this.setListTotgleBtnListenerDelay();
                        ActTiming.this.adt.notifyDataSetChanged();
                        localProgressDialog.dismiss();
                        ActTiming.this.toast(R.string.time_ctrl_ok);
                        ActTiming.this.bussines.setSendCmdListener(null);
                    }
                });
                return false;
            }
        });
    }

    private void setTitleView() {
        setViewTitle();
        setMenuBackgroundRes(R.mipmap.device_ic);
        setTiTleTextRes(R.string.timing);
        setEditImageRes(R.mipmap.new_add);
        setDeviceTextRes(UserFerences.getUserFerences(this).spFerences.getString("dName" + DeviceListActivity.deviceMacAddress, ""));
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        /*if (paramInt2 == -1) {
            if (!this.bussines.isCreateNewTime)
                break label86;
            this.bussines.sendTiming(this.bussines.getCacheTimingVo4Sd(), this.itemPosition, true);
        }else {
            final ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(R.string.get_d_info), false);
            localProgressDialog.setCancelable(true);
            localProgressDialog.show();
            this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener() {
                public void onSendFailde() {
                    ActTiming.this.toast(R.string.add_time_no_ok);
                    localProgressDialog.dismiss();
                }

                public void onSendOk() {
                    if (ActTiming.this.bussines.isCreateNewTime)
                        ActTiming.this.vos.add(ActTiming.this.bussines.getCacheTimingVo4Sd());
                    else {
                        ActTiming.this.bussines.reSortTimingData(ActTiming.this.vos);
                        ActTiming.this.setListTotgleBtnListenerDelay();
                        ActTiming.this.adt.notifyDataSetChanged();
                        localProgressDialog.dismiss();
                        ActTiming.this.toast(R.string.time_ctrl_ok);
                        ActTiming.this.bussines.setSendCmdListener(null);
                        return;
                        ActTiming.this.vos.remove(ActTiming.this.itemPosition);
                        ActTiming.this.vos.add(ActTiming.this.itemPosition, ActTiming.this.bussines.getCacheTimingVo4Sd());
                    }
                }
            });
            return;
            label86:
            this.bussines.sendEditTiming((TimingVo) this.vos.get(this.itemPosition), this.itemPosition);
        }*/
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.act_timing);
        setTitleView();
        findView();
        init();
        setListener();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.bussines.closeReSend();
        this.loopHandler.removeCallbacks(this.runnable);
    }

    protected void onEdit() {
        super.onEdit();
        if (this.adt.getCount() < 10) {
            goAct4result(ActAddTiming.class, 100);
            this.itemPosition = this.adt.getCount();
            this.bussines.isCreateNewTime = true;
            return;
        }
        toast(R.string.ten_timing);
    }

    public void onListVSwichChange(final boolean paramBoolean, final int paramInt) {
        final ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(R.string.get_d_info), false);
        System.out.println("onListVSwichChange        " + paramInt);
        TimingVo localTimingVo = (TimingVo) this.vos.get(paramInt);
        localTimingVo.setSwich(paramBoolean);
        this.bussines.sendTiming(localTimingVo, paramInt, false);
        this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener() {
            public void onSendFailde() {
                /*TimingVo localTimingVo = (TimingVo) ActTiming.this.vos.get(paramInt);
                if (!paramBoolean) ;
                for (boolean bool = true; ; bool = false) {
                    localTimingVo.setSwich(bool);
                    ActTiming.this.timingData.saveTimingVos2Sd(ActTiming.this.vos);
                    ActTiming.this.setListTotgleBtnListenerDelay();
                    ActTiming.this.adt.notifyDataSetChanged();
                    ActTiming.this.toast(R.string.add_time_no_ok);
                    localProgressDialog.dismiss();
                    return;
                }*/
            }

            public void onSendOk() {
                ((TimingVo) ActTiming.this.vos.get(paramInt)).setSwich(paramBoolean);
                ActTiming.this.timingData.saveTimingVos2Sd(ActTiming.this.vos);
                localProgressDialog.dismiss();
                ActTiming.this.toast(R.string.time_ctrl_ok);
                ActTiming.this.bussines.setSendCmdListener(null);
            }
        });
    }

    protected void onMenu() {
        super.onMenu();
        finish();
    }

    protected void onPause() {
        super.onPause();
        this.bussines.removeXlinkListener();
    }

    protected void onResume() {
        super.onResume();
    }

    public void upDateData() {
        runOnUiThread(new Runnable() {
            public void run() {
                ActTiming.this.vos.clear();
                ActTiming.this.vos.addAll(ActTiming.this.timingData.getTimingVos4Sd());
                ActTiming.this.bussines.reSortTimingData(ActTiming.this.vos);
                ActTiming.this.setListTotgleBtnListenerDelay();
                ActTiming.this.adt.notifyDataSetChanged();
                if (ActTiming.this.getTimeDialog.isShowing())
                    ActTiming.this.getTimeDialog.dismiss();
            }
        });
    }
}