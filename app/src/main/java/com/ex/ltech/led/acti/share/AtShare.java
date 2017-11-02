package com.ex.ltech.led.acti.share;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.scene.MyBiz;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.hongwai.vo.SceneVos;
import com.ex.ltech.hongwai.vo.ShareIrData;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.google.gson.Gson;
import et.song.db.ETDB;
import et.song.etclass.ETGroup;
import et.song.etclass.ETPage;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AtShare extends MyBaseActivity
  implements UdpReceive.UdpRecListener, TcpServer.OnSendTcpDataListener, TcpClient.OnRecTcpDataListener
{

  @Bind({2131559030})
  Button cancel;
  boolean isImportData;
  boolean isShowPhoneList;

  @Bind({2131559031})
  ListView list;
  private List<Phone> phones = new ArrayList();
  ProgressDialog proDia = null;

  @Bind({2131559032})
  RelativeLayout rlExport;

  @Bind({2131559023})
  RelativeLayout rlPhones;
  private TcpClient tcpClient;
  private TcpServer tcpServer;

  @Bind({2131559034})
  TextView tvExportCancel;
  private UdpReceive udpReceive;
  private UdpSend udpSend;
  ProgressDialog waitDialog;

  private void init()
  {
    this.waitDialog = new ProgressDialog(this);
    this.waitDialog.setCancelable(false);
    this.waitDialog.setButton(-1, getString(2131099891), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        AtShare.this.isImportData = false;
        AtShare.this.rlExport.setVisibility(8);
        AtShare.this.tcpServer.closeTcpServer();
        AtShare.this.udpSend.loopSendStop();
      }
    });
    Toast.makeText(this, 2131100396, 1).show();
    this.udpSend = new UdpSend();
    this.udpReceive = new UdpReceive();
    this.tcpClient = new TcpClient();
    DeviceManage.getInstance();
    ETGroup localETGroup;
    if (DeviceManage.getxDevice().getProductId() == "5b05f623bdcf48d9b0fc1507a79bb847")
    {
      ETPage.getInstance(this).Load(ETDB.getInstance(this));
      localETGroup = (ETGroup)ETPage.getInstance(this).GetItem(0);
      localETGroup.LoadEnableDevice(ETDB.getInstance(this));
    }
    ShareIrData localShareIrData;
    for (this.tcpServer = new TcpServer(localETGroup, this.udpSend); ; this.tcpServer = new TcpServer(localShareIrData, this.udpSend))
    {
      this.udpReceive.setListener(this);
      this.tcpClient.setListener(this);
      this.tcpServer.setListener(this);
      this.list.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          AtShare.this.rlPhones.setVisibility(8);
          AtShare.this.waitDialog.setMessage(AtShare.this.getString(2131100506));
          AtShare.this.waitDialog.show();
          AtShare.this.isImportData = true;
          AtShare.this.isShowPhoneList = false;
          AtShare.this.udpReceive.stop();
          AtShare.this.tcpClient.setIp(((AtShare.Phone)AtShare.this.phones.get(paramInt)).ip);
          AtShare.this.phones.remove(paramInt);
          AtShare.this.tcpClient.start();
        }
      });
      this.cancel.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          AtShare.this.isShowPhoneList = false;
          AtShare.this.rlPhones.setVisibility(8);
        }
      });
      this.tvExportCancel.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          AtShare.this.rlExport.setVisibility(8);
          AtShare.this.tcpServer.closeTcpServer();
          AtShare.this.udpSend.loopSendStop();
        }
      });
      return;
      SceneVos localSceneVos = new MyBiz(this).getSceneVos();
      MyRcDevices localMyRcDevices = (MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class);
      localShareIrData = new ShareIrData();
      localShareIrData.rcDevices = localMyRcDevices;
      localShareIrData.scenes = localSceneVos;
    }
  }

  public void exportData(View paramView)
  {
    this.tcpServer.start();
    this.udpSend.loopSendMyInfo();
    this.udpReceive.stop();
    this.rlExport.setVisibility(0);
  }

  public void importData(View paramView)
  {
    this.isShowPhoneList = true;
    this.udpReceive.start();
    this.udpSend.loopSendStop();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968704);
    ButterKnife.bind(this);
    setViewTitle();
    setMenuBackgroundRes(2130903074);
    setTiTleTextRes(2131099932);
    init();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.tcpServer.closeTcpServer();
    this.udpSend.loopSendStop();
    this.udpReceive.stop();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onRecTcpDataOk(String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        AtShare.this.waitDialog.setMessage(AtShare.this.getString(2131100361));
      }
    });
    if (!this.isImportData)
      return;
    DeviceManage.getInstance();
    if (DeviceManage.getxDevice().getProductId() == "5b05f623bdcf48d9b0fc1507a79bb847")
    {
      ETPage.getInstance(this).Load(ETDB.getInstance(this));
      ((ETGroup)ETPage.getInstance(this).GetItem(0)).Delete(ETDB.getInstance(this));
      ETGroup localETGroup = (ETGroup)new Gson().fromJson(paramString, ETGroup.class);
      if (localETGroup.getmDeviceList() != null)
      {
        localETGroup.Inster(ETDB.getInstance(this));
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            AtShare.this.finish();
          }
        }
        , 500L);
      }
    }
    while (true)
    {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          AtShare.this.waitDialog.dismiss();
          AtShare.this.rlPhones.setVisibility(8);
          AtShare.this.longToast(2131100398);
          System.out.println("MessageGet         " + Build.MODEL + "      成功接收数据");
        }
      });
      return;
      ShareIrData localShareIrData = (ShareIrData)new Gson().fromJson(paramString, ShareIrData.class);
      MyRcDevices localMyRcDevices = localShareIrData.rcDevices;
      SceneVos localSceneVos = localShareIrData.scenes;
      if (localMyRcDevices != null)
        MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, localMyRcDevices);
      if (localSceneVos == null)
        continue;
      MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, localSceneVos);
    }
  }

  public void onSendTcpDataOk()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        AtShare.this.longToast(2131100399);
        AtShare.this.rlExport.setVisibility(8);
      }
    });
  }

  public void onUdpRec(String paramString1, String paramString2)
  {
    runOnUiThread(new Runnable(paramString2, paramString1)
    {
      public void run()
      {
        if (AtShare.this.isShowPhoneList)
          AtShare.this.rlPhones.setVisibility(0);
        Iterator localIterator = AtShare.this.phones.iterator();
        while (localIterator.hasNext())
          if (((AtShare.Phone)localIterator.next()).ip.equals(this.val$ip))
            return;
        AtShare.this.phones.add(new AtShare.Phone(AtShare.this, this.val$ip, this.val$phoneName));
        AtShare.this.list.setAdapter(new BaseAdapter()
        {
          private void initializeViews(AtShare.Phone paramPhone, ViewHolder paramViewHolder)
          {
            paramViewHolder.phoneName.setText(paramPhone.name);
          }

          public int getCount()
          {
            return AtShare.this.phones.size();
          }

          public AtShare.Phone getItem(int paramInt)
          {
            return (AtShare.Phone)AtShare.this.phones.get(paramInt);
          }

          public long getItemId(int paramInt)
          {
            return paramInt;
          }

          public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
          {
            if (paramView == null)
            {
              paramView = LayoutInflater.from(AtShare.this).inflate(2130968814, null);
              ViewHolder localViewHolder = new ViewHolder();
              ViewHolder.access$502(localViewHolder, (TextView)paramView.findViewById(2131559355));
              paramView.setTag(localViewHolder);
            }
            initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag());
            return paramView;
          }

          class ViewHolder
          {
            private TextView phoneName;

            ViewHolder()
            {
            }
          }
        });
      }
    });
  }

  class Phone
  {
    String ip;
    String name;

    public Phone(String paramString1, String arg3)
    {
      this.ip = paramString1;
      Object localObject;
      this.name = localObject;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.share.AtShare
 * JD-Core Version:    0.6.0
 */