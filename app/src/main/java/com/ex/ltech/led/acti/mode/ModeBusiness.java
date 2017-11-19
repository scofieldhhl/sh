package com.ex.ltech.led.acti.mode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.vo.ModeVo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ModeBusiness
{
  public static final int JianBian = 1;
  public static final int PingShan = 3;
  public static final int TiaoBian = 2;
  public static final int dataSaveOk = 1000;
  ModeVo addBtnVo = new ModeVo();
  private List<Bitmap> bms = new ArrayList();
  private CmdDateBussiness cmdDateBussiness;
  private UserFerences ferences;
  public ArrayList<String> gradViewDefultName = new ArrayList();
  Gson gs = new Gson();
  public ArrayList<Bitmap> imgList = new ArrayList();
  int lastClickPosi = -1;
  private List<ModeVo> localModesData = new ArrayList();
  private int maxItemCount = 17;
  private String modeDataKey = "ModeDataKey";
  private String modeSpeedKey = "ModeSpeedKey";
  public List<ModeVo> modes = new ArrayList();
  public ArrayList<String> modesDefultName = new ArrayList();
  private String modesNames;
  public Activity pct;
  public ArrayList<Integer> reses = new ArrayList();
  private int singleSeletedPosi = -1;
  private SocketManager socketManager;

  public ModeBusiness(Activity paramActivity)
  {
    this.pct = paramActivity;
    this.ferences = UserFerences.getUserFerences(this.pct);
    this.addBtnVo.setType(2);
    if (DeviceListActivity.deviceMacAddress != null)
    {
      this.modeDataKey = (DeviceListActivity.deviceMacAddress + "ModeDataKey");
      this.modeSpeedKey = (DeviceListActivity.deviceMacAddress + "ModeSpeedKey");
    }
  }

  private void initDefaultModes()
  {
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
      System.out.println();
    }
  }

  public void compressAndSave2SdCardBitmap(Uri paramUri)
  {
    /*String str = UriUtil.getRealFilePath(this.pct, paramUri);
    Bitmap localBitmap = BitmapUtils.getImageBit(str);
    FileUtil.saveMyBitmap(Environment.getExternalStorageDirectory() + "/ltech/led/image" + str.substring(str.lastIndexOf("/")), localBitmap, "/ltech/led/image");*/
  }

  public void editMode(int paramInt)
  {
    if ((paramInt > 7) && (paramInt < this.modes.size()))
    {
      Intent localIntent = new Intent(this.pct, ActNewMode.class);
      localIntent.putExtra("modeCount", -1 + (-1 + this.modes.size()));
      localIntent.putExtra("modesNames", this.modesNames);
      localIntent.putExtra("modesPosi", paramInt);
      localIntent.putExtra("newCreateModesPosi", -8 + (paramInt + 1));
      this.pct.startActivityForResult(localIntent, 1);
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
    for(int i = 0;i < this.modes.size(); i++)
    {
      if ((modes.get(i)).getNewCreateModeBitmapPath() != null)
        this.bms.add(BitmapFactory.decodeFile((modes.get(i)).getNewCreateModeBitmapPath()));
      else
        this.bms.add(BitmapFactory.decodeResource(this.pct.getResources(), (modes.get(i)).getIvLeftRes()));
    }
    return this.bms;
  }

  public ArrayList<Integer> getReses()
  {
    return this.reses;
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
    this.modesDefultName.add(this.pct.getString(R.string.mode_text_3));
    this.modesDefultName.add(this.pct.getString(R.string.mode_text_4));
    this.modesDefultName.add(this.pct.getString(R.string.mode_text_5));
    this.modesDefultName.add(this.pct.getString(R.string.mode_text_6));
    this.modesDefultName.add(this.pct.getString(R.string.mode_text_7));
    this.modesDefultName.add(this.pct.getString(R.string.mode_text_8));
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
    else {
      appendAddBtnVo();
      try {
        this.localModesData = (List<ModeVo>) this.gs.fromJson(str, ModeVo.class);
        StringBuilder localStringBuilder = new StringBuilder();
        for (int i = 0; i < this.localModesData.size(); i++)
          localStringBuilder.append(((ModeVo) this.localModesData.get(i)).getTvName());
        this.modesNames = localStringBuilder.toString();
        if (this.localModesData.size() == 0)
          this.modes = this.localModesData;
      } catch (JsonSyntaxException localJsonSyntaxException) {
        initDefaultModes();
      }
    }
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
    /*if (!isMultiSeleted())
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
    System.out.println("Test the fucking send    438       sendSingleMode " + str);
    this.socketManager.postTask(this.cmdDateBussiness.getMoreSeletedModeCmd(str, localArrayList, 178));*/
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

  public void onSingleSeleted(int paramInt)
  {
    int i = 1;
    int j = this.modes.size();
    if ((j < this.maxItemCount) && (paramInt == j - 1))
    {
      Intent localIntent = new Intent(this.pct, ActNewMode.class);
      localIntent.putExtra("modeCount", -1 + (-1 + this.modes.size()));
      localIntent.putExtra("modesNames", this.modesNames);
      localIntent.putExtra("newCreateModesPosi", -8 + this.modes.size());
      this.pct.startActivityForResult(localIntent, i);
      if (j == this.maxItemCount)
        removeAddBtnVos();
      return;
    }
    if (this.singleSeletedPosi == paramInt)
    {
      ModeVo localModeVo3 = modes.get(paramInt);
      if (!localModeVo3.isSingleSeleted())
      {
        localModeVo3.setSingleSeleted(true);
        this.modes.remove(paramInt);
        this.modes.add(paramInt, localModeVo3);
      }
    }
    this.singleSeletedPosi = paramInt;
    if (paramInt == j - 1)
      return;
    else {
      sendSingleMode(paramInt);
      for (int k = 0; k < this.modes.size(); k++)
      {
        ModeVo localModeVo2 = modes.get(k);
        localModeVo2.setSingleSeleted(false);
        localModeVo2.setSeleted(false);
        this.modes.remove(k);
        this.modes.add(k, localModeVo2);
      }
      ModeVo localModeVo1 = modes.get(paramInt);
      localModeVo1.setSingleSeleted(false);
      this.modes.remove(paramInt);
      this.modes.add(paramInt, localModeVo1);
    }
  }

  public void prepareLink()
  {
    this.socketManager = SocketManager.instance();
    this.socketManager.ip = Main.deviceVo.getIp();
    this.cmdDateBussiness = new CmdDateBussiness(this.pct, Main.deviceVo.getPwd());
  }

  public void removeAddBtnVos()
  {
    this.modes.remove(-1 + this.modes.size());
  }

  public void saveModeData2local(ModeVo paramModeVo)
  {
    String str = this.gs.toJson(paramModeVo);
    this.ferences.putValue(this.modeDataKey, str);
  }

  public void saveModesData2local(List<ModeVo> paramList)
  {
    String str = this.gs.toJson(paramList);
    this.ferences.putValue(this.modeDataKey, str);
  }

  public void saveMoveSpeed2Sd()
  {
    for (int i = 0; i < 8; i++)
    {
      this.ferences.putValue(this.modeSpeedKey + i, Integer.valueOf(((ModeVo)this.modes.get(i)).getSpeed()));
      System.out.println("很内疚啊飞机微软工会市场，吗           " + this.ferences.spFerences.getInt(new StringBuilder().append(this.modeSpeedKey).append(i).toString(), 6));
    }
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

  public void sendColorCmd(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.socketManager.postTask(this.cmdDateBussiness.getColorCmd(210, paramInt1, paramInt2, paramInt3, paramInt4, 0));
  }

  public void sendCustomMode(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, List<Integer> paramList)
  {
    byte b = 17;
    int i;
    switch (paramInt1)
    {
      default:
      case 1:
        i = 241;
        break;
      case 2:
        i = 242;
        break;
      case 3:
        i = 243;
        break;
      case 4:
        i = 244;
        break;
      case 5:
        i = 245;
        break;
      case 6:
        i = 246;
        break;
      case 7:
        i = 247;
        break;
      case 8:
        i = 248;
        break;
    }
    switch (paramInt4)
    {
    default:
    case 1:
      b = 17;
      break;
    case 2:
      b = 18;
      break;
    case 3:
      b = 19;
      break;
    case 4:
      b = 20;
      break;
    case 5:
      b = 21;
      break;
    case 6:
      b = 22;
      break;
    case 7:
      b = 23;
      break;
    case 8:
      b = 24;
      break;
    }
    System.out.println("speed       " + b + "      brt   " + paramInt5);
    System.out.println("mIndex%^&(*))*&^&%^           " + paramInt1);
    this.socketManager.postTask(this.cmdDateBussiness.getCustomModeCmd(i, paramInt2, paramInt3, b, paramInt5, paramInt6, paramList));
  }

  public void sendModes()
  {
    /*if (!isMultiSeleted())
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
        break label235;
      ModeVo localModeVo2 = (ModeVo)this.modes.get(j);
      b = 17;
      System.out.println("很内疚啊飞机微软工会市场，吗           " + localModeVo2.getSpeed());
      switch (localModeVo2.getSpeed())
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
      b = 17;
      continue;
      b = 18;
      continue;
      b = 19;
      continue;
      b = 20;
      continue;
      b = 21;
      continue;
      b = 22;
      continue;
      b = 23;
      continue;
      b = 24;
    }
    label235: String str = "";
    List localList = this.modes;
    ModeVo localModeVo1 = (ModeVo)localList.get(-1 + localList.size());
    localList.remove(-1 + localList.size());
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
    System.out.println("Test the fucking send       389    sendSingleMode " + str);
    this.socketManager.postTask(this.cmdDateBussiness.getMoreSeletedModeCmd(str, localArrayList, 177));
    localList.add(localModeVo1);*/
  }

  public void sendModesWithSameSpeed(int paramInt)
  {
    /*ArrayList localArrayList = new ArrayList();
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
    System.out.println("Test the fucking send      481     sendSingleMode " + str);
    this.socketManager.postTask(this.cmdDateBussiness.getMoreSeletedModeCmd(str, localArrayList, 177));*/
  }

  public void sendSingleMode(int paramInt)
  {
    /*ArrayList localArrayList = new ArrayList();
    int i;
    int j;
    byte b;
    if (this.modes.size() < 16)
    {
      i = -1 + this.modes.size();
      j = 0;
      if (j >= i)
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
      break label37;
      i = this.modes.size();
      break;
      b = 17;
      continue;
      b = 18;
      continue;
      b = 19;
      continue;
      b = 20;
      continue;
      b = 21;
      continue;
      b = 22;
      continue;
      b = 23;
      continue;
      b = 24;
    }
    String str = "";
    List localList = this.modes;
    int k = 15;
    if (k > -1)
    {
      if ((k > -1 + localList.size()) || (!((ModeVo)localList.get(k)).isSingleSeleted()));
      for (str = str + "0"; ; str = str + "1")
      {
        k--;
        break;
      }
    }
    System.out.println("Test the fucking send      538     sendSingleMode " + str);
    this.socketManager.postTask(this.cmdDateBussiness.getMoreSeletedModeCmd(str, localArrayList, 177));*/
  }

  public void sendbrightCmd(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.socketManager.postTask(this.cmdDateBussiness.getColorCmd(209, paramInt1, paramInt2, paramInt3, paramInt4, 0));
  }
}
