package com.ex.ltech.onepiontfive.main.room.mode;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.FileUtil;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.utils.UriUtil;
import com.ex.ltech.led.vo.DeviceVo;
import com.ex.ltech.led.vo.ModeVo;
import com.ex.ltech.onepiontfive.main.Constant;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import io.xlink.wifi.sdk.XDevice;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModeBusiness extends MyBusiness
{
  public static final int JianBian = 2;
  public static final int PingShan = 3;
  public static final int TiaoBian = 1;
  public static final int dataSaveOk = 1000;
  ModeVo addBtnVo = new ModeVo();
  private List<Bitmap> bms = new ArrayList();
  ArrayList<Integer> cmd = new ArrayList();
  private CmdDateBussiness cmdDateBussiness;
  int dvcNum;
  private UserFerences ferences;
  public ArrayList<String> gradViewDefultName;
  int groupNum;
  Gson gs;
  public ArrayList<Bitmap> imgList;
  boolean isGroup;
  int lastClickPosi;
  private List<ModeVo> localModesData = new ArrayList();
  private int maxItemCount;
  private String modeDataKey;
  private String modeSpeedKey;
  public List<ModeVo> modes = new ArrayList();
  public ArrayList<String> modesDefultName;
  public String modesNames;
  public Activity pct;
  public ArrayList<Integer> reses;
  private MyBusiness.MySendListener sendListener;
  private int singleSeletedPosi;
  private SocketManager socketManager;
  public List<ModeVo> synModes = new ArrayList();
  private byte[] uId = new byte[4];
  public String userIdHexString;
  XDevice x;

  public ModeBusiness(Activity paramActivity)
  {
    super(paramActivity);
    DeviceManage.getInstance();
    this.x = DeviceManage.getxDevice();
    this.modeDataKey = (DeviceListActivity.deviceMacAddress + "ModeDataKey");
    this.modeSpeedKey = (DeviceListActivity.deviceMacAddress + "ModeSpeedKey");
    this.gs = new Gson();
    this.maxItemCount = 170;
    this.imgList = new ArrayList();
    this.reses = new ArrayList();
    this.modesDefultName = new ArrayList();
    this.gradViewDefultName = new ArrayList();
    this.singleSeletedPosi = -1;
    this.lastClickPosi = -1;
    this.pct = paramActivity;
    this.ferences = UserFerences.getUserFerences(this.pct);
    this.addBtnVo.setType(2);
    this.userIdHexString = Integer.toHexString(SharedPreferencesUtil.queryIntValue("appId").intValue()).toUpperCase();
    for (int i = this.userIdHexString.length(); i < 8; i++)
      this.userIdHexString = ("0" + this.userIdHexString);
    this.uId[0] = (byte)Integer.parseInt(this.userIdHexString.substring(0, 2), 16);
    this.uId[1] = (byte)Integer.parseInt(this.userIdHexString.substring(2, 4), 16);
    this.uId[2] = (byte)Integer.parseInt(this.userIdHexString.substring(4, 6), 16);
    this.uId[3] = (byte)Integer.parseInt(this.userIdHexString.substring(6, 8), 16);
  }

  public void addModesDefultName(String paramString)
  {
    this.modesDefultName.add(paramString);
  }

  public void appendAddBtnVo()
  {
    this.modes.add(this.addBtnVo);
  }

  public void changeGalleryData(Bitmap paramBitmap)
  {
    this.imgList.add(this.imgList.size(), paramBitmap);
  }

  public void changeMoveSpeed(int paramInt)
  {
    if (this.singleSeletedPosi != -1)
    {
      ModeVo localModeVo = (ModeVo)this.modes.get(this.singleSeletedPosi);
      localModeVo.setSpeed(paramInt);
      this.modes.remove(this.singleSeletedPosi);
      this.modes.add(this.singleSeletedPosi, localModeVo);
    }
  }

  public List compareWithReturnInfo(String paramString)
  {
    int i = paramString.length();
    boolean bool1 = paramString.substring(18, 20).equalsIgnoreCase("a2");
    ArrayList localArrayList = null;
    if (bool1)
    {
      paramString.substring(0, i - 6);
      boolean bool2 = addCheckSumData(paramString);
      int j = Integer.parseInt(paramString.substring(20, 22), 16);
      localArrayList = new ArrayList();
      if ((bool2) && (j > 2))
      {
        String str = paramString.substring(26, -8 + paramString.length());
        for (int k = 0; k < j - 2; k++)
          localArrayList.add(Integer.valueOf(Integer.parseInt(str.substring(k * 2, 2 + k * 2), 16)));
      }
    }
    return localArrayList;
  }

  public void compressAndSave2SdCardBitmap(Uri paramUri)
  {
    String str = UriUtil.getRealFilePath(this.pct, paramUri);
    Bitmap localBitmap = BitmapUtils.getImageBit(str);
    FileUtil.saveMyBitmap(Environment.getExternalStorageDirectory() + "/ltech/led/image" + str.substring(str.lastIndexOf("/")), localBitmap, "/ltech/led/image");
  }

  public void deleteModes(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2, int paramInt3)
  {
    setMySendListener(paramMySendListener);
  }

  public void editMode(int paramInt)
  {
    if ((paramInt > 7) && (paramInt < this.modes.size()))
    {
      Intent localIntent = new Intent(this.pct, ActNewMode.class);
      localIntent.putExtra("modeCount", -1 + (-1 + this.modes.size()));
      localIntent.putExtra("modesNames", this.modesNames);
      localIntent.putExtra("modesPosi", paramInt);
      this.pct.startActivityForResult(localIntent, 1);
      ActMode.toSettingValue(-1 + (-1 + this.modes.size()), this.modesNames, paramInt);
    }
  }

  public byte[] getEditSceneCmds(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, List<Integer> paramList)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(7));
    this.cmd.add(Integer.valueOf(32));
    this.cmd.add(Integer.valueOf(paramInt4));
    this.cmd.add(Integer.valueOf(paramInt1));
    this.cmd.add(Integer.valueOf(8));
    for (int i = 0; i < paramList.size(); i++)
    {
      this.cmd.add(Integer.valueOf(Integer.toHexString(Color.red(((Integer)paramList.get(i)).intValue())), 16));
      this.cmd.add(Integer.valueOf(Integer.toHexString(Color.green(((Integer)paramList.get(i)).intValue())), 16));
      this.cmd.add(Integer.valueOf(Integer.toHexString(Color.blue(((Integer)paramList.get(i)).intValue())), 16));
    }
    this.cmd.add(Integer.valueOf(paramInt6));
    this.cmd.add(Integer.valueOf(paramInt5));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(paramInt3));
    ArrayList localArrayList = this.cmd;
    if (this.isGroup);
    for (int j = this.groupNum; ; j = this.dvcNum)
    {
      localArrayList.add(Integer.valueOf(j));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      return getCmdData(this.cmd);
    }
  }

  public List<ModeVo> getEditableModes()
  {
    String str = this.ferences.spFerences.getString(this.modeDataKey, "");
    try
    {
      List localList = (List)this.gs.fromJson(str, new TypeToken()
      {
      }
      .getType());
      return localList;
    }
    catch (JsonSyntaxException localJsonSyntaxException)
    {
    }
    return null;
  }

  public ArrayList<Bitmap> getGalleryData()
  {
    return this.imgList;
  }

  public ArrayList<String> getGradViewDefultName()
  {
    return this.gradViewDefultName;
  }

  public List<ModeVo> getItemVos()
  {
    return this.modes;
  }

  public ArrayList<String> getModesDefultName()
  {
    return this.modesDefultName;
  }

  public List<Bitmap> getNewCreateModeBitmaps()
  {
    this.bms.clear();
    int i = 0;
    if (i < this.modes.size())
    {
      if (((ModeVo)this.modes.get(i)).getNewCreateModeBitmapPath() != null)
        this.bms.add(BitmapFactory.decodeFile(((ModeVo)this.modes.get(i)).getNewCreateModeBitmapPath()));
      while (true)
      {
        i++;
        break;
        this.bms.add(BitmapFactory.decodeResource(this.pct.getResources(), ((ModeVo)this.modes.get(i)).getIvLeftRes()));
      }
    }
    return this.bms;
  }

  public byte[] getNewCreateSceneCmds(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, List<Integer> paramList)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(7));
    this.cmd.add(Integer.valueOf(32));
    this.cmd.add(Integer.valueOf(paramInt3));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(8));
    for (int i = 0; i < paramList.size(); i++)
    {
      this.cmd.add(Integer.valueOf(Integer.toHexString(Color.red(((Integer)paramList.get(i)).intValue())), 16));
      this.cmd.add(Integer.valueOf(Integer.toHexString(Color.green(((Integer)paramList.get(i)).intValue())), 16));
      this.cmd.add(Integer.valueOf(Integer.toHexString(Color.blue(((Integer)paramList.get(i)).intValue())), 16));
    }
    this.cmd.add(Integer.valueOf(paramInt5));
    this.cmd.add(Integer.valueOf(paramInt4 + 1));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(paramInt2));
    ArrayList localArrayList = this.cmd;
    if (this.isGroup);
    for (int j = this.groupNum; ; j = this.dvcNum)
    {
      localArrayList.add(Integer.valueOf(j));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      return getCmdData(this.cmd);
    }
  }

  public ArrayList<Integer> getReses()
  {
    return this.reses;
  }

  public void initDefaultModes()
  {
    this.modes.clear();
    StringBuilder localStringBuilder = new StringBuilder();
    ModeVo localModeVo1 = new ModeVo();
    localModeVo1.setTvName(this.pct.getString(R.string.mode_text_11));
    localModeVo1.setIvLeftRes(R.mipmap.mode_ic_6);
    localModeVo1.setSeleted(false);
    localModeVo1.setSpeed(this.ferences.spFerences.getInt(this.modeSpeedKey + 0, 6));
    localStringBuilder.append(localModeVo1.getTvName());
    this.modes.add(localModeVo1);
    ModeVo localModeVo2 = new ModeVo();
    localModeVo2.setTvName(this.pct.getString(R.string.mode_text_22));
    localModeVo2.setIvLeftRes(R.mipmap.mode_ic_5);
    localModeVo2.setSeleted(false);
    localModeVo2.setSpeed(this.ferences.spFerences.getInt(this.modeSpeedKey + 1, 6));
    localStringBuilder.append(localModeVo2.getTvName());
    this.modes.add(localModeVo2);
    ModeVo localModeVo3 = new ModeVo();
    localModeVo3.setTvName(this.pct.getString(R.string.mode_text_33));
    localModeVo3.setIvLeftRes(R.mipmap.mode_ic_7);
    localModeVo3.setSeleted(false);
    localModeVo3.setSpeed(this.ferences.spFerences.getInt(this.modeSpeedKey + 2, 6));
    localStringBuilder.append(localModeVo3.getTvName());
    this.modes.add(localModeVo3);
    ModeVo localModeVo4 = new ModeVo();
    localModeVo4.setTvName(this.pct.getString(R.string.mode_text_44));
    localModeVo4.setIvLeftRes(R.mipmap.mode_ic_8);
    localModeVo4.setSeleted(false);
    localModeVo4.setSpeed(this.ferences.spFerences.getInt(this.modeSpeedKey + 3, 6));
    localStringBuilder.append(localModeVo4.getTvName());
    this.modes.add(localModeVo4);
    ModeVo localModeVo5 = new ModeVo();
    localModeVo5.setTvName(this.pct.getString(R.string.mode_text_55));
    localModeVo5.setIvLeftRes(R.mipmap.mode_ic_1);
    localModeVo5.setSeleted(false);
    localStringBuilder.append(localModeVo5.getTvName());
    localModeVo5.setSpeed(this.ferences.spFerences.getInt(this.modeSpeedKey + 4, 6));
    this.modes.add(localModeVo5);
    ModeVo localModeVo6 = new ModeVo();
    localModeVo6.setTvName(this.pct.getString(R.string.mode_text_66));
    localModeVo6.setIvLeftRes(R.mipmap.mode_ic_4);
    localModeVo6.setSeleted(false);
    localStringBuilder.append(localModeVo6.getTvName());
    localModeVo6.setSpeed(this.ferences.spFerences.getInt(this.modeSpeedKey + 5, 6));
    this.modes.add(localModeVo6);
    ModeVo localModeVo7 = new ModeVo();
    localModeVo7.setTvName(this.pct.getString(R.string.mode_text_77));
    localModeVo7.setIvLeftRes(R.mipmap.mode_ic_2);
    localModeVo7.setSeleted(false);
    localStringBuilder.append(localModeVo7.getTvName());
    localModeVo7.setSpeed(this.ferences.spFerences.getInt(this.modeSpeedKey + 6, 6));
    this.modes.add(localModeVo7);
    ModeVo localModeVo8 = new ModeVo();
    localModeVo8.setTvName(this.pct.getString(R.string.mode_text_88));
    localModeVo8.setIvLeftRes(R.mipmap.mode_ic_3);
    localModeVo8.setSeleted(false);
    localStringBuilder.append(localModeVo8.getTvName());
    localModeVo8.setSpeed(this.ferences.spFerences.getInt(this.modeSpeedKey + 7, 6));
    this.modes.add(localModeVo8);
    this.modesNames = localStringBuilder.toString();
  }

  public void initGalleryData()
  {
    this.reses.add(Integer.valueOf(R.mipmap.mode_ic_1));
    this.reses.add(Integer.valueOf(R.mipmap.mode_ic_2));
    this.reses.add(Integer.valueOf(R.mipmap.mode_ic_3));
    this.reses.add(Integer.valueOf(R.mipmap.mode_ic_4));
    this.reses.add(Integer.valueOf(R.mipmap.mode_ic_5));
    this.reses.add(Integer.valueOf(R.mipmap.mode_ic_6));
    this.reses.add(Integer.valueOf(R.mipmap.mode_ic_7));
    this.reses.add(Integer.valueOf(R.mipmap.mode_ic_8));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), R.mipmap.mode_ic_1));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), R.mipmap.mode_ic_2));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), R.mipmap.mode_ic_3));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), R.mipmap.mode_ic_4));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), R.mipmap.mode_ic_5));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), R.mipmap.mode_ic_6));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), R.mipmap.mode_ic_7));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), R.mipmap.mode_ic_8));
    this.modesDefultName.add(this.pct.getString(R.string.mode_text_1));
    this.modesDefultName.add(this.pct.getString(R.string.mode_text_2));
    this.modesDefultName.add(this.pct.getString(2131100169));
    this.modesDefultName.add(this.pct.getString(2131100171));
    this.modesDefultName.add(this.pct.getString(2131100173));
    this.modesDefultName.add(this.pct.getString(2131100175));
    this.modesDefultName.add(this.pct.getString(2131100177));
    this.modesDefultName.add(this.pct.getString(2131100179));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_11));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_22));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_33));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_44));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_55));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_66));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_77));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_88));
  }

  public void initModes()
  {
    String str = this.ferences.spFerences.getString(this.modeDataKey, "");
    if (str.equals(""))
      initDefaultModes();
    while (true)
    {
      appendAddBtnVo();
      return;
      try
      {
        this.localModesData = ((List)this.gs.fromJson(str, new TypeToken()
        {
        }
        .getType()));
        StringBuilder localStringBuilder = new StringBuilder();
        for (int i = 0; i < this.localModesData.size(); i++)
          localStringBuilder.append(((ModeVo)this.localModesData.get(i)).getTvName());
        this.modesNames = localStringBuilder.toString();
        if (this.localModesData.size() == 0)
          continue;
        this.modes = this.localModesData;
      }
      catch (JsonSyntaxException localJsonSyntaxException)
      {
        initDefaultModes();
      }
    }
  }

  public void insertSynMode()
  {
    for (int i = 0; i < this.modes.size(); i++)
    {
      int k = 0;
      if (k >= this.synModes.size())
        continue;
      if (((ModeVo)this.modes.get(i)).getOrder() == ((ModeVo)this.synModes.get(k)).getOrder())
      {
        ((ModeVo)this.synModes.get(k)).setTvName(((ModeVo)this.modes.get(i)).getTvName());
        if (((ModeVo)this.modes.get(i)).getNewCreateModeBitmapPath() == null)
          break label159;
        ((ModeVo)this.synModes.get(k)).setNewCreateModeBitmapPath(((ModeVo)this.modes.get(i)).getNewCreateModeBitmapPath());
      }
      while (true)
      {
        k++;
        break;
        label159: ((ModeVo)this.synModes.get(k)).setIvLeftRes(((ModeVo)this.modes.get(i)).getIvLeftRes());
      }
    }
    for (int j = -1 + this.modes.size(); j > 7; j--)
      this.modes.remove(j);
    this.modes.addAll(this.synModes);
    appendAddBtnVo();
  }

  public boolean isMultiSeleted()
  {
    for (int i = 0; i < this.modes.size(); i++)
      if (((ModeVo)this.modes.get(i)).isSeleted())
        return true;
    return false;
  }

  public boolean isSingleSeleted()
  {
    for (int i = 0; i < this.modes.size(); i++)
      if (((ModeVo)this.modes.get(i)).isSingleSeleted())
        return true;
    return false;
  }

  public void offModes()
  {
    if (!isMultiSeleted())
      return;
    ArrayList localArrayList = new ArrayList();
    int i;
    int j;
    label44: byte b;
    if (this.modes.size() < 16)
    {
      i = -1 + this.modes.size();
      j = 0;
      if (j >= i)
        break label158;
      ModeVo localModeVo = (ModeVo)this.modes.get(j);
      b = 17;
      switch (localModeVo.getSpeed())
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      }
    }
    while (true)
    {
      localArrayList.add(Byte.valueOf(b));
      j++;
      break label44;
      i = this.modes.size();
      break;
      b = 24;
    }
    label158: String str = "";
    List localList = this.modes;
    int k = 15;
    if (k > -1)
    {
      if ((k > -1 + localList.size()) || (!((ModeVo)localList.get(k)).isSeleted()));
      for (str = str + "0"; ; str = str + "1")
      {
        k--;
        break;
      }
    }
    this.socketManager.postTask(this.cmdDateBussiness.getMoreSeletedModeCmd(str, localArrayList, 178));
  }

  public void onListViewItemClick(int paramInt)
  {
    ModeVo localModeVo = (ModeVo)this.modes.get(paramInt);
    if (!localModeVo.isSeleted());
    for (boolean bool = true; ; bool = false)
    {
      localModeVo.setSeleted(bool);
      this.modes.remove(paramInt);
      this.modes.add(paramInt, localModeVo);
      this.lastClickPosi = paramInt;
      return;
    }
  }

  public void onMoreSeleted(int paramInt)
  {
    this.singleSeletedPosi = -1;
    for (int i = 0; i < this.modes.size(); i++)
    {
      ModeVo localModeVo2 = (ModeVo)this.modes.get(i);
      localModeVo2.setSingleSeleted(false);
      this.modes.remove(i);
      this.modes.add(i, localModeVo2);
    }
    ModeVo localModeVo1 = (ModeVo)this.modes.get(paramInt);
    boolean bool1 = localModeVo1.isSeleted();
    boolean bool2 = false;
    if (!bool1)
      bool2 = true;
    localModeVo1.setSeleted(bool2);
    this.modes.remove(paramInt);
    this.modes.add(paramInt, localModeVo1);
    if ((this.modes.size() < this.maxItemCount) && ((-1 + this.modes.size() != paramInt) || (this.modes.size() == this.maxItemCount)))
      removeAddBtnVos();
  }

  public int onSingleSeleted(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 1;
    int k = this.modes.size();
    if ((k < this.maxItemCount) && (paramInt3 == k - 1))
    {
      ActMode.toSettingValue(-1 + (-1 + this.modes.size()), this.modesNames, 0);
      if (k == this.maxItemCount)
        removeAddBtnVos();
      return i;
    }
    if (this.singleSeletedPosi == paramInt3)
    {
      ModeVo localModeVo3 = (ModeVo)this.modes.get(paramInt3);
      if (!localModeVo3.isSingleSeleted())
      {
        int n = i;
        localModeVo3.setSingleSeleted(n);
        this.modes.remove(paramInt3);
        this.modes.add(paramInt3, localModeVo3);
        label133: this.singleSeletedPosi = paramInt3;
        if (paramInt3 != k - 1)
        {
          if (paramInt3 >= 8)
            break label318;
          if (!((ModeVo)this.modes.get(paramInt3)).isSingleSeleted())
            break label312;
        }
      }
    }
    label312: int j;
    while (true)
    {
      sendSingleMode(paramInt1, paramInt2, paramInt3, i);
      return 0;
      int i1 = 0;
      break;
      for (int m = 0; m < this.modes.size(); m++)
      {
        ModeVo localModeVo2 = (ModeVo)this.modes.get(m);
        localModeVo2.setSingleSeleted(false);
        localModeVo2.setSeleted(false);
        this.modes.remove(m);
        this.modes.add(m, localModeVo2);
      }
      ModeVo localModeVo1 = (ModeVo)this.modes.get(paramInt3);
      localModeVo1.setSingleSeleted(i);
      this.modes.remove(paramInt3);
      this.modes.add(paramInt3, localModeVo1);
      break label133;
      j = 2;
    }
    label318: if (((ModeVo)this.modes.get(paramInt3)).isSingleSeleted());
    while (true)
    {
      sendSingleMode(paramInt1, paramInt2, paramInt3, j);
      break;
      j = 2;
    }
  }

  public void prepareLink()
  {
    this.socketManager = SocketManager.instance();
    this.socketManager.ip = Main.deviceVo.getIp();
    this.cmdDateBussiness = new CmdDateBussiness(this.pct.getApplicationContext(), Main.deviceVo.getPwd());
  }

  public void queryModeNum(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(34));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void removeAddBtnVos()
  {
    this.modes.remove(-1 + this.modes.size());
  }

  public void responseMessage(MyBusiness.MySendListener paramMySendListener, String paramString1, String paramString2)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString2, 16)));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString1, 16)));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
  }

  public void responseMessage(String paramString1, String paramString2)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString2, 16)));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString1, 16)));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
  }

  public void saveCustomMode(MyBusiness.MySendListener paramMySendListener, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, List<Integer> paramList, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[24];
    for (int i = 0; i < 24; i++)
    {
      arrayOfByte[i] = 0;
      if (i >= paramArrayOfByte.length)
        continue;
      arrayOfByte[i] = paramArrayOfByte[i];
    }
    System.out.println("testmodename " + StringUtils.btye2Str(arrayOfByte));
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(7));
    this.cmd.add(Integer.valueOf(60));
    this.cmd.add(Integer.valueOf(17));
    ArrayList localArrayList = this.cmd;
    if (paramBoolean)
      paramInt1 = 255;
    localArrayList.add(Integer.valueOf(paramInt1));
    this.cmd.add(Integer.valueOf(8));
    for (int j = 0; j < paramList.size(); j++)
    {
      this.cmd.add(Integer.valueOf(Integer.toHexString(Color.red(((Integer)paramList.get(j)).intValue())), 16));
      this.cmd.add(Integer.valueOf(Integer.toHexString(Color.green(((Integer)paramList.get(j)).intValue())), 16));
      this.cmd.add(Integer.valueOf(Integer.toHexString(Color.blue(((Integer)paramList.get(j)).intValue())), 16));
    }
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(paramInt3));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(paramInt4));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(this.uId[0]));
    this.cmd.add(Integer.valueOf(this.uId[1]));
    this.cmd.add(Integer.valueOf(this.uId[2]));
    this.cmd.add(Integer.valueOf(this.uId[3]));
    for (int k = 0; k < arrayOfByte.length; k++)
      this.cmd.add(Integer.valueOf(arrayOfByte[k]));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public boolean saveModeData(String paramString)
  {
    paramString.length();
    Object localObject;
    boolean bool2;
    String str4;
    if (paramString.substring(18, 20).equalsIgnoreCase("a4"))
    {
      int i = Integer.parseInt(paramString.substring(20, 22), 16);
      (i - 36);
      if (i > 1)
      {
        String str1 = paramString.substring(22, -8 + paramString.length()).substring(0, 2);
        int j = 0;
        Iterator localIterator = getItemVos().iterator();
        ArrayList localArrayList;
        while (true)
        {
          boolean bool1 = localIterator.hasNext();
          localObject = null;
          if (bool1)
          {
            ModeVo localModeVo = (ModeVo)localIterator.next();
            if (Integer.parseInt(str1, 16) == localModeVo.getOrder())
              localObject = localModeVo;
          }
          else
          {
            if (j == getItemVos().size())
              localObject = new ModeVo();
            ((ModeVo)localObject).setOrder(Integer.parseInt(paramString.substring(24, 26), 16));
            ((ModeVo)localObject).setColorCount(Integer.parseInt(paramString.substring(26, 28), 16));
            localArrayList = new ArrayList(Constant.COLOR_ACCOUNT);
            for (int k = 0; k < Constant.COLOR_ACCOUNT; k++)
              localArrayList.add(Integer.valueOf(Color.rgb(Integer.parseInt(paramString.substring(28 + k * 6, 30 + k * 6), 16), Integer.parseInt(paramString.substring(30 + k * 6, 32 + k * 6), 16), Integer.parseInt(paramString.substring(32 + k * 6, 34 + k * 6), 16))));
          }
          j++;
        }
        ((ModeVo)localObject).setColors(localArrayList);
        ((ModeVo)localObject).setBrt(Integer.parseInt(paramString.substring(76, 78), 16));
        ((ModeVo)localObject).setSpeed(Integer.parseInt(paramString.substring(78, 80), 16));
        paramString.substring(80, 82);
        ((ModeVo)localObject).setTransformation(Integer.parseInt(paramString.substring(82, 84), 16));
        String str2 = paramString.substring(84, 92);
        ((ModeVo)localObject).setAppId(str2);
        String str3 = StringUtils.bytesStr2WordStr(paramString.substring(92, 140));
        System.out.println("testmodename " + paramString.substring(92, 140));
        if (!str2.equalsIgnoreCase(this.userIdHexString))
          break label736;
        bool2 = true;
        ((ModeVo)localObject).setOther(bool2);
        ((ModeVo)localObject).setTvName(str3);
        ((ModeVo)localObject).setIsNewCreateMode(true);
        ((ModeVo)localObject).setType(3);
        ((ModeVo)localObject).setIvLeftRes(R.mipmap.mode_ic_6);
        str4 = this.ferences.spFerences.getString(this.modeDataKey, "");
        if (str4.equals(""));
      }
    }
    while (true)
    {
      int m;
      try
      {
        Gson localGson = this.gs;
        3 local3 = new TypeToken()
        {
        };
        this.localModesData = ((List)localGson.fromJson(str4, local3.getType()));
        m = 0;
        if (m < this.localModesData.size())
        {
          boolean bool3 = ((ModeVo)localObject).getAppId().equals(((ModeVo)this.localModesData.get(m)).getAppId());
          if (((ModeVo)localObject).getOrder() != ((ModeVo)this.localModesData.get(m)).getOrder())
            break label742;
          bool4 = true;
          if (!(bool4 & bool3))
            break label730;
          if (((ModeVo)this.localModesData.get(m)).getNewCreateModeBitmapPath() == null)
            continue;
          ((ModeVo)localObject).setNewCreateModeBitmapPath(((ModeVo)this.localModesData.get(m)).getNewCreateModeBitmapPath());
          break label730;
          ((ModeVo)localObject).setIvLeftRes(((ModeVo)this.localModesData.get(m)).getIvLeftRes());
        }
      }
      catch (JsonSyntaxException localJsonSyntaxException)
      {
      }
      this.synModes.add(localObject);
      return true;
      return false;
      label730: m++;
      continue;
      label736: bool2 = false;
      break;
      label742: boolean bool4 = false;
    }
  }

  public void saveModesData2local(List<ModeVo> paramList)
  {
    String str = this.gs.toJson(paramList);
    this.ferences.putValue(this.modeDataKey, str);
  }

  public void saveMoveSpeed2Sd()
  {
    for (int i = 0; i < 8; i++)
      this.ferences.putValue(this.modeSpeedKey + i, Integer.valueOf(((ModeVo)this.modes.get(i)).getSpeed()));
  }

  public void seletedAll(boolean paramBoolean)
  {
    this.singleSeletedPosi = -1;
    for (int i = 0; i < this.modes.size(); i++)
    {
      ModeVo localModeVo = (ModeVo)this.modes.get(i);
      localModeVo.setSeleted(paramBoolean);
      localModeVo.setSingleSeleted(false);
      this.modes.remove(i);
      this.modes.add(i, localModeVo);
    }
  }

  public void sendColor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(8));
    this.cmd.add(Integer.valueOf(67));
    this.cmd.add(Integer.valueOf(paramInt1));
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(paramInt3));
    this.cmd.add(Integer.valueOf(paramInt4));
    this.cmd.add(Integer.valueOf(paramInt5));
    this.cmd.add(Integer.valueOf(paramInt6));
    this.cmd.add(Integer.valueOf(paramInt7));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void sendColorCmd(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.socketManager.postTask(this.cmdDateBussiness.getColorCmd(210, paramInt1, paramInt2, paramInt3, paramInt4, 0));
  }

  public void sendCustomMode(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, List<Integer> paramList)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(7));
    this.cmd.add(Integer.valueOf(60));
    int n;
    if (paramInt3 == 1)
    {
      ArrayList localArrayList2 = this.cmd;
      if (this.isGroup)
      {
        n = 34;
        localArrayList2.add(Integer.valueOf(n));
      }
    }
    while (true)
    {
      this.cmd.add(Integer.valueOf(17));
      this.cmd.add(Integer.valueOf(8));
      for (int i = 0; i < paramList.size(); i++)
      {
        this.cmd.add(Integer.valueOf(Integer.toHexString(Color.red(((Integer)paramList.get(i)).intValue())), 16));
        this.cmd.add(Integer.valueOf(Integer.toHexString(Color.green(((Integer)paramList.get(i)).intValue())), 16));
        this.cmd.add(Integer.valueOf(Integer.toHexString(Color.blue(((Integer)paramList.get(i)).intValue())), 16));
      }
      n = 33;
      break;
      this.cmd.add(Integer.valueOf(2));
    }
    this.cmd.add(Integer.valueOf(paramInt5));
    this.cmd.add(Integer.valueOf(paramInt4));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(paramInt2));
    ArrayList localArrayList1 = this.cmd;
    if (this.isGroup);
    byte[] arrayOfByte;
    for (int j = this.groupNum; ; j = this.dvcNum)
    {
      localArrayList1.add(Integer.valueOf(j));
      this.cmd.add(Integer.valueOf(this.uId[0]));
      this.cmd.add(Integer.valueOf(this.uId[1]));
      this.cmd.add(Integer.valueOf(this.uId[2]));
      this.cmd.add(Integer.valueOf(this.uId[3]));
      arrayOfByte = new byte[24];
      for (int k = 0; k < 24; k++)
      {
        arrayOfByte[k] = 0;
        if (k >= "呵呵呵呵".getBytes().length)
          continue;
        arrayOfByte[k] = "呵呵呵呵".getBytes()[k];
      }
    }
    for (int m = 0; m < arrayOfByte.length; m++)
      this.cmd.add(Integer.valueOf(arrayOfByte[m]));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdNoResponse(this.cmd);
  }

  public void sendModes(int paramInt1, int paramInt2, int paramInt3)
  {
    if (!isMultiSeleted())
      return;
    setMySendListener(this.sendListener);
    addNormalHeadData(this.cmd);
    ArrayList localArrayList1 = this.cmd;
    int i;
    int j;
    int k;
    if (this.isGroup)
    {
      i = 9;
      localArrayList1.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(paramInt1));
      ArrayList localArrayList2 = this.cmd;
      if (this.isGroup)
        paramInt2 = this.groupNum;
      localArrayList2.add(Integer.valueOf(paramInt2));
      this.cmd.add(Integer.valueOf(paramInt3));
      j = 0;
      List localList = this.modes;
      ((ModeVo)localList.get(-1 + localList.size()));
      k = 0;
      label136: if (k >= -1 + localList.size())
        break label237;
      if (((ModeVo)localList.get(k)).isSeleted())
      {
        if (k >= 8)
          break label206;
        this.cmd.add(Integer.valueOf(k + 1));
      }
    }
    while (true)
    {
      j++;
      k++;
      break label136;
      i = 8;
      break;
      label206: this.cmd.add(Integer.valueOf(((ModeVo)this.modes.get(k)).getOrder()));
    }
    label237: this.cmd.add(10, Integer.valueOf(j + 3));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void sendModesWithSameSpeed(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    int i;
    int j;
    label37: byte b;
    if (this.modes.size() < 16)
    {
      i = -1 + this.modes.size();
      j = 0;
      if (j >= i)
        break label134;
      b = 17;
      switch (paramInt)
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      }
    }
    while (true)
    {
      localArrayList.add(Byte.valueOf(b));
      j++;
      break label37;
      i = this.modes.size();
      break;
      b = 24;
    }
    label134: String str = "";
    List localList = this.modes;
    int k = 15;
    if (k > -1)
    {
      if ((k > -1 + localList.size()) || (!((ModeVo)localList.get(k)).isSeleted()));
      for (str = str + "0"; ; str = str + "1")
      {
        k--;
        break;
      }
    }
    this.socketManager.postTask(this.cmdDateBussiness.getMoreSeletedModeCmd(str, localArrayList, 177));
  }

  public void sendSingleMode(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    setMySendListener(this.sendListener);
    addNormalHeadData(this.cmd);
    ArrayList localArrayList1 = this.cmd;
    int i;
    if (this.isGroup)
    {
      i = 6;
      localArrayList1.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(5));
      this.cmd.add(Integer.valueOf(paramInt1));
      ArrayList localArrayList2 = this.cmd;
      if (this.isGroup)
        paramInt2 = this.groupNum;
      localArrayList2.add(Integer.valueOf(paramInt2));
      if (paramInt3 >= 8)
        break label204;
      this.cmd.add(Integer.valueOf(paramInt3 + 1));
    }
    while (true)
    {
      this.cmd.add(Integer.valueOf(paramInt4));
      this.cmd.add(Integer.valueOf(((ModeVo)this.modes.get(paramInt3)).getSpeed()));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmd(this.cmd);
      return;
      i = 5;
      break;
      label204: this.cmd.add(Integer.valueOf(((ModeVo)this.modes.get(paramInt3)).getOrder()));
    }
  }

  public void sendbrightCmd(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.socketManager.postTask(this.cmdDateBussiness.getColorCmd(209, paramInt1, paramInt2, paramInt3, paramInt4, 0));
  }

  public void setDvcNum(int paramInt)
  {
    this.dvcNum = paramInt;
  }

  public void setGroupNum(int paramInt)
  {
    this.groupNum = paramInt;
  }

  public void setIsGroup(boolean paramBoolean)
  {
    this.isGroup = paramBoolean;
  }

  public void setSendListener(MyBusiness.MySendListener paramMySendListener)
  {
    this.sendListener = paramMySendListener;
  }

  public void syncModeInfo(MyBusiness.MySendListener paramMySendListener, int paramInt)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(36));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(paramInt));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public static class InnerModeBusiness extends MyBaseFt
  {
    public static void goout()
    {
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      return super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.mode.ModeBusiness
 * JD-Core Version:    0.6.0
 */