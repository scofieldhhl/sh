package com.ex.ltech.onepiontfive.main;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import com.ex.ltech.led.MyApp;
import com.ex.ltech.led.acti.device.ActDeleteManager;
import com.ex.ltech.led.musci_service.ServicePlayer;
import com.ex.ltech.led.musci_service.ServicePlayer.MyBinder;
import com.ex.ltech.onepiontfive.main.config.FtAddDeviceList;
import com.ex.ltech.onepiontfive.main.more.FtAddShortCuts;
import com.ex.ltech.onepiontfive.main.more.FtMore;
import com.ex.ltech.onepiontfive.main.more.GeoFencing.FtGeoFencing;
import com.ex.ltech.onepiontfive.main.more.SmsLog.FtSmsLog;
import com.ex.ltech.onepiontfive.main.newscene.FtFinishAddScene;
import com.ex.ltech.onepiontfive.main.room.ModeColorEdit;
import com.ex.ltech.onepiontfive.main.room.MusicFragment;
import com.ex.ltech.onepiontfive.main.room.mode.ActMode;
import com.ex.ltech.onepiontfive.main.room.panel.FtPanel;
import com.ex.ltech.onepiontfive.main.room.sensor.FtSensor;
import com.ex.ltech.onepiontfive.main.time.FtTime;
import com.fragmentmaster.app.FragmentMaster;
import com.fragmentmaster.app.Request;
import java.io.PrintStream;

public class AtFragmentMaster extends MyBaseAt
{
  public static ServicePlayer myService = null;
  boolean isOpenMusic;
  private ServiceConnection mServiceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      AtFragmentMaster.myService = ((ServicePlayer.MyBinder)paramIBinder).getService();
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      System.out.println("fuckingSSSS         onServiceDisconnected");
    }
  };

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onBackPressed()
  {
    new Intent("MusicFragmentOnPressBack");
    super.onBackPressed();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968678);
    FragmentMaster localFragmentMaster = getFragmentMaster();
    String str = getIntent().getStringExtra("AtTypeKey");
    label176: int i;
    switch (str.hashCode())
    {
    default:
      i = -1;
    case -406351113:
    case 353292895:
    case -1930701753:
    case 214511774:
    case 350471127:
    case 288324386:
    case -1898417961:
    case 721588477:
    case 1705480746:
    case -1923202120:
    case -1832237760:
    case -596800700:
    case 348301304:
    case 288323952:
    case -1958943350:
    case -1038895103:
    case -1189057259:
    }
    while (true)
      switch (i)
      {
      case 7:
      default:
        return;
        if (!str.equals("AtTypeCfg"))
          break label176;
        i = 0;
        continue;
        if (!str.equals("AtTypeScene"))
          break label176;
        i = 1;
        continue;
        if (!str.equals("AtTypeSensor"))
          break label176;
        i = 2;
        continue;
        if (!str.equals("AtTypeAddTiming"))
          break label176;
        i = 3;
        continue;
        if (!str.equals("AtTypePanel"))
          break label176;
        i = 4;
        continue;
        if (!str.equals("AtTypeMore"))
          break label176;
        i = 5;
        continue;
        if (!str.equals("AtTypeTiming"))
          break label176;
        i = 6;
        continue;
        if (!str.equals("AtTypeOnKeyOnOff"))
          break label176;
        i = 7;
        continue;
        if (!str.equals("AtTypeGeoFencing"))
          break label176;
        i = 8;
        continue;
        if (!str.equals("AtTypeSmsLog"))
          break label176;
        i = 9;
        continue;
        if (!str.equals("AtTypeShortcuts"))
          break label176;
        i = 10;
        continue;
        if (!str.equals("AtAddDevice"))
          break label176;
        i = 11;
        continue;
        if (!str.equals("AtTypeMusic"))
          break label176;
        i = 12;
        continue;
        if (!str.equals("AtTypeMode"))
          break label176;
        i = 13;
        continue;
        if (!str.equals("ModeColorEdit"))
          break label176;
        i = 14;
        continue;
        if (!str.equals("DeleteDevice"))
          break label176;
        i = 15;
        continue;
        if (!str.equals("AddLight"))
          break label176;
        i = 16;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      }
    localFragmentMaster.install(2131558918, new Request(FtAddDeviceList.class).putExtra("mPosition", getIntent().getIntExtra("mPosition", -1)), true);
    return;
    localFragmentMaster.install(2131558918, new Request(FtFinishAddScene.class).putExtra("NewSceneCountKey", getIntent().getIntExtra("NewSceneCountKey", 0)).putExtra("SceneEditPosiKey", getIntent().getIntExtra("SceneEditPosiKey", 0)).putExtra("SceneNameExtraKey ", getIntent().getStringExtra("SceneNameExtraKey ")).putExtra("SceneNumExtraKey ", getIntent().getIntExtra("SceneNumExtraKey ", -1)), true);
    return;
    localFragmentMaster.install(2131558918, new Request(FtSensor.class).putExtra("DIndexKey", getIntent().getIntExtra("DIndexKey", 0)).putExtra("DRoomNumKey", getIntent().getIntExtra("DRoomNumKey", 0)).putExtra("DClickPosiKey", getIntent().getIntExtra("DClickPosiKey", 0)), true);
    return;
    localFragmentMaster.install(2131558918, new Request(FtTime.class), true);
    return;
    localFragmentMaster.install(2131558918, new Request(FtPanel.class).putExtra("AtTypeKey", getIntent().getStringExtra("AtTypeKey")).putExtra("DIndexKey", getIntent().getIntExtra("DIndexKey", 0)).putExtra("DRoomNumKey", getIntent().getIntExtra("DRoomNumKey", 0)).putExtra("DClickPosiKey", getIntent().getIntExtra("DClickPosiKey", 0)), true);
    return;
    localFragmentMaster.install(2131558918, new Request(FtMore.class), true);
    return;
    localFragmentMaster.install(2131558918, new Request(FtTime.class), true);
    return;
    localFragmentMaster.install(2131558918, new Request(FtGeoFencing.class), true);
    return;
    localFragmentMaster.install(2131558918, new Request(FtSmsLog.class), true);
    return;
    localFragmentMaster.install(2131558918, new Request(FtAddShortCuts.class), true);
    return;
    localFragmentMaster.install(2131558918, new Request(FtAddDeviceList.class).putExtra("mPosition", getIntent().getIntExtra("mPosition", -1)), true);
    return;
    localFragmentMaster.install(2131558918, new Request(MusicFragment.class).putExtra("DTypeKey", 67).putExtra("DRoomNumKey", getIntent().getIntExtra("DRoomNumKey", 10)).putExtra("MusicIsGroupKey", getIntent().getBooleanExtra("MusicIsGroupKey", false)).putExtra("DIndexKey", getIntent().getIntExtra("DIndexKey", 10)), true);
    this.isOpenMusic = true;
    return;
    localFragmentMaster.install(2131558918, new Request(ActMode.class).putExtra("DIndexKey", getIntent().getIntExtra("DIndexKey", 0)).putExtra("DRoomNumKey", getIntent().getIntExtra("DRoomNumKey", 0)).putExtra("ModeIsGroupKey", getIntent().getBooleanExtra("ModeIsGroupKey", false)).putExtra("ModeGroupIdKey", getIntent().getIntExtra("ModeGroupIdKey", 0)).putExtra("ModeDvcOderIdKey", getIntent().getIntExtra("ModeDvcOderIdKey", 0)), true);
    return;
    localFragmentMaster.install(2131558918, new Request(ModeColorEdit.class).putExtra("ctScenePosi", getIntent().getIntExtra("ctScenePosi", -1)).putExtra("ctSceneName", getIntent().getStringExtra("ctSceneName")).putExtra("sceneDataStr", getIntent().getStringExtra("sceneDataStr")), true);
    return;
    localFragmentMaster.install(2131558918, new Request(ActDeleteManager.class), true);
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      Intent localIntent = new Intent("MusicFragmentOnPressBack");
      LocalBroadcastManager.getInstance(MyApp.getApp()).sendBroadcast(localIntent);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.AtFragmentMaster
 * JD-Core Version:    0.6.0
 */