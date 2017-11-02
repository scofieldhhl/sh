package com.ex.ltech.onepiontfive.main.room.panel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.LinearLayout;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.FileUtil;
import com.ex.ltech.led.utils.UtilMath;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.PanelLampVO;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.soundcloud.android.crop.Crop;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PanelBussiness extends MyBusiness
{
  private static final int PHOTO_REQ_CODE = 1;
  private File bgFile;
  ArrayList<Integer> cmd = new ArrayList();
  public Context context;
  int dIndex;
  public List<Dvc> dvcs;
  public Home home;
  private String lastReturnData = "";
  public List<Dvc> lights;
  private String mac;
  public List<PanelLampVO> panelLampVos;
  int panelPosi;
  private int panelWays;
  private String roomName = "";
  int roomPosi;

  PanelBussiness(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramContext);
    this.roomPosi = paramInt1;
    this.panelPosi = paramInt2;
    this.dIndex = paramInt3;
    this.context = paramContext;
    this.mac = UserFerences.getUserFerences(paramContext).getValue("GateWayMacIdKey");
  }

  public static String hexString2binaryString(String paramString)
  {
    String str1;
    if ((paramString == null) || (paramString.length() % 2 != 0))
      str1 = null;
    while (true)
    {
      return str1;
      str1 = "";
      for (int i = 0; i < paramString.length(); i++)
      {
        String str2 = "0000" + Integer.toBinaryString(Integer.parseInt(paramString.substring(i, i + 1), 16));
        str1 = str1 + str2.substring(-4 + str2.length());
      }
    }
  }

  public void beginCrop(Fragment paramFragment, Activity paramActivity, Uri paramUri)
  {
    Crop.of(paramUri, Uri.fromFile(new File(paramActivity.getCacheDir(), "cropped"))).asSquare().start(paramFragment, paramActivity);
  }

  public void changeImg(int paramInt1, int paramInt2)
  {
    ((Dvc)this.lights.get(paramInt1)).setType(paramInt2);
  }

  public void ctrlBulb(Dvc paramDvc)
  {
    this.cmd.clear();
    this.cmd.add(Integer.valueOf(90));
    this.cmd.add(Integer.valueOf(165));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(1));
    switch (paramDvc.getType())
    {
    default:
    case 11:
    case 9:
    case 10:
    case 8:
    }
    int i;
    while (true)
    {
      addUserId2Cmd(this.cmd);
      this.cmd.add(Integer.valueOf(16));
      this.cmd.add(Integer.valueOf(7));
      this.cmd.add(Integer.valueOf(paramDvc.getRoomIndex()));
      this.cmd.add(Integer.valueOf(paramDvc.getmIndex()));
      this.cmd.add(Integer.valueOf(paramDvc.getRoomIndex()));
      this.cmd.add(Integer.valueOf(paramDvc.getR()));
      this.cmd.add(Integer.valueOf(paramDvc.getG()));
      this.cmd.add(Integer.valueOf(paramDvc.getB()));
      this.cmd.add(Integer.valueOf(paramDvc.getW()));
      this.cmd.add(Integer.valueOf(paramDvc.getBrt()));
      this.cmd.add(Integer.valueOf(1));
      i = 0;
      for (int j = 0; j < this.cmd.size(); j++)
        i += ((Integer)this.cmd.get(j)).intValue();
      this.cmd.add(Integer.valueOf(69));
      continue;
      this.cmd.add(Integer.valueOf(70));
      continue;
      this.cmd.add(Integer.valueOf(70));
      continue;
      this.cmd.add(Integer.valueOf(71));
    }
    String str1 = Integer.toHexString(i);
    String str2 = str1.substring(-2 + str1.length(), str1.length());
    String str3 = "";
    if (str1.length() == 3)
      str3 = str1.substring(0, 1);
    if (str1.length() == 4)
      str3 = str1.substring(0, 2);
    this.cmd.add(Integer.valueOf(str3));
    this.cmd.add(Integer.valueOf(str2));
    this.cmd.add(Integer.valueOf(90));
  }

  public void ctrlSwitch(MyBusiness.MySendListener paramMySendListener, PanelLampVO paramPanelLampVO, int paramInt1, String[] paramArrayOfString, int paramInt2)
  {
    setMySendListener(paramMySendListener);
    int i = 0;
    for (int j = 0; j < 4; j++)
    {
      if (paramArrayOfString[j].equals("0"))
        continue;
      i += Integer.parseInt(new DecimalFormat("0").format(Math.pow(2.0D, j)));
    }
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(21));
    this.cmd.add(Integer.valueOf(5));
    switch (((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).getType())
    {
    default:
    case 15:
    case 16:
    case 17:
    case 18:
    }
    while (true)
    {
      this.cmd.add(Integer.valueOf(paramInt2 + 1));
      this.cmd.add(Integer.valueOf(this.dIndex));
      this.cmd.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmd(this.cmd);
      return;
      this.cmd.add(Integer.valueOf(34));
      continue;
      this.cmd.add(Integer.valueOf(35));
      continue;
      this.cmd.add(Integer.valueOf(36));
      continue;
      this.cmd.add(Integer.valueOf(37));
    }
  }

  public String getPanelBranchImgPath(int paramInt)
  {
    return SharedPreferencesUtil.queryValue(this.mac + "panel" + ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).getmIndex() + "branch" + paramInt);
  }

  public int getPanelWays()
  {
    return this.panelWays;
  }

  public void goCarmare(Fragment paramFragment, int paramInt)
  {
    File localFile = new File(Environment.getExternalStorageDirectory(), "/ltech/led/mainTitle");
    if (!localFile.exists())
      localFile.mkdirs();
    this.bgFile = new File(localFile, System.currentTimeMillis() + ".jpg");
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", Uri.fromFile(this.bgFile));
    paramFragment.startActivityForResult(localIntent, 1);
  }

  public void handleCrop(Activity paramActivity, Intent paramIntent, LinearLayout paramLinearLayout, OnPictrueListener paramOnPictrueListener)
  {
    String str = Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg";
    Bitmap localBitmap = BitmapUtils.squareCropRectangle(BitmapUtils.getBitmapFromUri(paramActivity.getApplicationContext(), Crop.getOutput(paramIntent)), paramLinearLayout.getWidth(), paramLinearLayout.getHeight());
    this.setter.putValue(this.roomName, str);
    FileUtil.saveMyBitmap(str, localBitmap, "/ltech/led/image");
    paramActivity.runOnUiThread(new Runnable(paramOnPictrueListener, str)
    {
      public void run()
      {
        this.val$listener.onPictrueOk(this.val$bitmapPath);
      }
    });
  }

  public void initData()
  {
    try
    {
      this.home = ((Home)getBean4ClassName(this.mac, Home.class));
      this.lights = ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).getInnerDvcVos();
      if (this.lights.size() == 0)
        this.lights = ((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos();
      this.dvcs = ((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos();
      switch (((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).getType())
      {
      case 15:
        setPanelWays(4);
        return;
      case 16:
      case 17:
      case 18:
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    setPanelWays(3);
    return;
    setPanelWays(2);
    return;
    setPanelWays(1);
  }

  public void initData4Cache()
  {
    this.home = ((Home)getCacheBean(Home.class));
    this.lights = ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).getInnerDvcVos();
    if (this.lights.size() == 0)
      this.lights = ((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos();
  }

  public void initData4Result()
  {
    this.home = ((Home)getCacheBean(Home.class));
    this.lights = ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).getInnerDvcVos();
    if (this.lights.size() == 0)
      this.lights = ((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos();
  }

  public List<PanelLampVO> initDataTemp()
  {
    this.home = ((Home)getBean4ClassName(this.mac, Home.class));
    this.panelLampVos = ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).getPanelLampVO();
    if ((this.panelLampVos == null) || (this.panelLampVos.size() == 0))
    {
      this.panelLampVos = new ArrayList();
      for (int i = 0; i < 4; i++)
      {
        PanelLampVO localPanelLampVO = new PanelLampVO();
        localPanelLampVO.setType(-1);
        localPanelLampVO.setOn(false);
        localPanelLampVO.setRealation(false);
        localPanelLampVO.setShowRelationBtn(false);
        localPanelLampVO.setChosenIndex(-1);
        this.panelLampVos.add(localPanelLampVO);
      }
      ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).setPanelLampVO(this.panelLampVos);
    }
    return this.panelLampVos;
  }

  public List<PanelLampVO> initPanelLampVos()
  {
    if ((this.panelLampVos == null) || (this.panelLampVos.size() == 0))
      this.panelLampVos = new ArrayList();
    while (true)
    {
      for (int i = 0; i < 4; i++)
      {
        PanelLampVO localPanelLampVO = new PanelLampVO();
        localPanelLampVO.setType(-1);
        localPanelLampVO.setOn(false);
        localPanelLampVO.setRealation(false);
        localPanelLampVO.setShowRelationBtn(false);
        localPanelLampVO.setChosenIndex(-1);
        this.panelLampVos.add(localPanelLampVO);
      }
      this.panelLampVos.clear();
    }
    return this.panelLampVos;
  }

  public boolean onChangeNameOk(int paramInt, String paramString)
  {
    if ((!paramString.equals("")) && (((PanelLampVO)this.panelLampVos.get(paramInt)).getType() == -1))
      ((PanelLampVO)this.panelLampVos.get(paramInt)).setName(paramString);
    return true;
  }

  public void onHideRelationControl()
  {
    for (int i = 0; i < this.panelLampVos.size(); i++)
      ((PanelLampVO)this.panelLampVos.get(i)).setShowRelationBtn(false);
  }

  public void onProgressChanged(int paramInt1, int paramInt2)
  {
    ((Dvc)this.lights.get(paramInt1)).setBrtProgress(paramInt2);
  }

  public void onRelation(int paramInt)
  {
    PanelLampVO localPanelLampVO = (PanelLampVO)this.panelLampVos.get(paramInt);
    if (!((PanelLampVO)this.panelLampVos.get(paramInt)).isRealation());
    for (boolean bool = true; ; bool = false)
    {
      localPanelLampVO.setRealation(bool);
      return;
    }
  }

  public void onRelationSeleted(int paramInt)
  {
    Dvc localDvc = (Dvc)this.lights.get(paramInt);
    if (!((Dvc)this.lights.get(paramInt)).isSeletedRelation());
    for (boolean bool = true; ; bool = false)
    {
      localDvc.setIsSeletedRelation(bool);
      return;
    }
  }

  public void onSeletedAll(boolean paramBoolean)
  {
    for (int i = 0; i < this.lights.size(); i++)
      ((Dvc)this.lights.get(i)).setIsSeletedRelation(paramBoolean);
  }

  public void onShowRelationControl()
  {
    for (int i = 0; i < this.panelLampVos.size(); i++)
      ((PanelLampVO)this.panelLampVos.get(i)).setShowRelationBtn(true);
  }

  public boolean onSwitch(int paramInt)
  {
    Dvc localDvc = (Dvc)this.lights.get(paramInt);
    if (!((Dvc)this.lights.get(paramInt)).isOnOff());
    for (boolean bool = true; ; bool = false)
    {
      localDvc.setOnOff(bool);
      return true;
    }
  }

  public int operatingData(String paramString)
  {
    boolean bool = addCheckSumData(paramString);
    String str = paramString.substring(18, 20);
    int i = Integer.parseInt(paramString.substring(20, 22), 16);
    if ((str.equalsIgnoreCase("A1")) && (bool) && (i == 9))
    {
      responseMessage(paramString.substring(4, 6), "21");
      if (!this.lastReturnData.equalsIgnoreCase(paramString))
      {
        this.lastReturnData = paramString;
        Integer.parseInt(paramString.substring(22, 24), 16);
        Integer.parseInt(paramString.substring(24, 26), 16);
        Integer.parseInt(paramString.substring(26, 28), 16);
        Integer.parseInt(paramString.substring(28, 30), 16);
        char[] arrayOfChar = hexString2binaryString(paramString.substring(28, 30)).substring(4).toCharArray();
        int j = 3;
        if (j >= 0)
        {
          if (arrayOfChar[j] == '1')
            ((PanelLampVO)this.panelLampVos.get(3 - j)).setOn(true);
          while (true)
          {
            j--;
            break;
            ((PanelLampVO)this.panelLampVos.get(3 - j)).setOn(false);
          }
        }
        saveData(this.panelLampVos);
      }
      return 1;
    }
    return -1;
  }

  public void putCacheData()
  {
    ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).setPanelLampVO(this.panelLampVos);
    putCacheData(this.home);
  }

  public void relateDeviceInfo(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(41));
    this.cmd.add(Integer.valueOf(6));
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(paramInt3 + 1));
    this.cmd.add(Integer.valueOf(paramInt4));
    double d = Math.pow(2.0D, paramInt5);
    this.cmd.add(Integer.valueOf((int)d));
    this.cmd.add(Integer.valueOf(paramInt6));
    ArrayList localArrayList = this.cmd;
    if (paramBoolean);
    for (int i = 1; ; i = 0)
    {
      localArrayList.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmd(this.cmd);
      return;
    }
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

  public void saveBug(int paramInt1, int paramInt2)
  {
    this.setter.putValue("row" + paramInt1, Integer.valueOf(paramInt2));
  }

  public void saveData(List<PanelLampVO> paramList)
  {
    ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).setPanelLampVO(paramList);
  }

  public Dvc saveDvcData(String paramString)
  {
    Dvc localDvc = new Dvc();
    ArrayList localArrayList = new ArrayList(4);
    String str1 = paramString.substring(18, 20);
    boolean bool1 = addCheckSumData(paramString);
    if ((!str1.equalsIgnoreCase("A3")) || ((str1.equalsIgnoreCase("A3")) && (Integer.parseInt(paramString.substring(22, 24), 16) == 255)))
      return null;
    if ((str1.equalsIgnoreCase("A3")) && ((!bool1) || (Integer.parseInt(paramString.substring(20, 22), 16) != 38)))
      return null;
    if (this.lastReturnData.equalsIgnoreCase(paramString))
      return null;
    String str2 = paramString.substring(20, 22);
    paramString.substring(22, 24);
    int i = -1 + Integer.parseInt(paramString.substring(24, 26), 16);
    int j = Integer.parseInt(paramString.substring(26, 28), 16);
    Integer.parseInt(paramString.substring(28, 30), 16);
    if (str2.equalsIgnoreCase("26"))
    {
      String[] arrayOfString1 = UtilMath.hexString2binaryString(paramString.substring(38, 40)).substring(4).trim().split("");
      String[] arrayOfString2 = new String[4];
      for (int k = 0; k < arrayOfString1.length; k++)
      {
        if (arrayOfString1[k].isEmpty())
          continue;
        arrayOfString2[(4 - k)] = arrayOfString1[k];
      }
      int m = 0;
      int n = arrayOfString2.length;
      int i1 = 0;
      if (i1 < n)
      {
        String str3 = arrayOfString2[i1];
        int i2;
        int i3;
        String str4;
        PanelLampVO localPanelLampVO;
        boolean bool2;
        if (!str3.isEmpty())
        {
          i2 = Integer.parseInt(paramString.substring(m + 40, m + 42), 16);
          i3 = 0;
          str4 = "";
          for (int i4 = 0; i4 < this.dvcs.size(); i4++)
          {
            int i5;
            if ((((Dvc)this.dvcs.get(i4)).isGroup()) && (((Dvc)this.dvcs.get(i4)).innerDvcVos.size() > 0))
              i5 = 0;
            while (i5 < ((Dvc)this.dvcs.get(i4)).innerDvcVos.size())
            {
              if (((Dvc)((Dvc)this.dvcs.get(i4)).innerDvcVos.get(i5)).getmIndex() == i2)
              {
                i3 = ((Dvc)((Dvc)this.dvcs.get(i4)).innerDvcVos.get(i5)).getType();
                str4 = ((Dvc)((Dvc)this.dvcs.get(i4)).innerDvcVos.get(i5)).getName();
              }
              i5++;
              continue;
              if ((i2 == 0) || (((Dvc)this.dvcs.get(i4)).getmIndex() != i2))
                break;
              i3 = ((Dvc)this.dvcs.get(i4)).getType();
              str4 = ((Dvc)this.dvcs.get(i4)).getName();
            }
          }
          localPanelLampVO = new PanelLampVO();
          if (i2 <= 0)
            break label687;
          bool2 = true;
          label609: localPanelLampVO.setRealation(bool2);
          localPanelLampVO.setIsSeleted(false);
          if (i2 <= 0)
            break label693;
          label627: localPanelLampVO.setChosenIndex(i2);
          if (!str3.equals("1"))
            break label699;
        }
        label687: label693: label699: for (boolean bool3 = true; ; bool3 = false)
        {
          localPanelLampVO.setOn(bool3);
          localPanelLampVO.setType(i3);
          localPanelLampVO.setName(str4);
          m += 2;
          localArrayList.add(localPanelLampVO);
          i1++;
          break;
          bool2 = false;
          break label609;
          i2 = -1;
          break label627;
        }
      }
    }
    localDvc.setmIndex(j);
    localDvc.setRoomIndex(i);
    localDvc.setType(15);
    localDvc.setName(this.ct.getString(2131100407));
    localDvc.setPanelLampVO(localArrayList);
    if (1 == 0)
      localDvc.setOnOff(true);
    while (true)
    {
      this.lastReturnData = paramString;
      return localDvc;
      localDvc.setOnOff(false);
    }
  }

  public void savePanelBranchImgPath(int paramInt, String paramString)
  {
    SharedPreferencesUtil.keepShared(this.mac + "panel" + ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).getmIndex() + "branch" + paramInt, paramString);
  }

  public void scanDevices(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(26));
    this.cmd.add(Integer.valueOf(12));
    this.cmd.add(Integer.valueOf(21));
    this.cmd.add(Integer.valueOf(97));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(10));
    this.cmd.add(Integer.valueOf(10));
    this.cmd.add(Integer.valueOf(30));
    this.cmd.add(Integer.valueOf(10));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void setBgView(Activity paramActivity, LinearLayout paramLinearLayout, int paramInt, OnPictrueListener paramOnPictrueListener)
  {
    new Thread(paramActivity, paramOnPictrueListener)
    {
      public void run()
      {
        super.run();
        Bitmap localBitmap1 = BitmapUtils.zoomOutBM(BitmapFactory.decodeFile(PanelBussiness.this.bgFile.getPath()), BitmapUtils.dp2px(PanelBussiness.this.ct, 220.0F));
        int i = BitmapUtils.getExifOrientation(PanelBussiness.this.bgFile.getPath());
        PanelBussiness.this.setter.putValue(PanelBussiness.this.roomName, PanelBussiness.this.bgFile.getPath());
        if ((i == 90) || (i == 180) || (i == 270) || (i == 0))
        {
          Matrix localMatrix = new Matrix();
          localMatrix.postRotate(i);
          Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, localBitmap1.getWidth(), localBitmap1.getHeight(), localMatrix, false);
          FileUtil.saveMyBitmap(PanelBussiness.this.bgFile.getPath(), localBitmap2, "/ltech/led/image");
          this.val$activity.runOnUiThread(new Runnable()
          {
            public void run()
            {
              PanelBussiness.1.this.val$listener.onPictrueOk(PanelBussiness.this.bgFile.getPath());
            }
          });
        }
      }
    }
    .start();
  }

  public void setPanelWays(int paramInt)
  {
    this.panelWays = paramInt;
  }

  public void startListener(MyBusiness.MySendListener paramMySendListener, String paramString, Home paramHome)
  {
    setMySendListener(paramMySendListener);
    listenerCmd(this.panelLampVos, paramString, this.panelPosi);
    Log.i("", "");
  }

  public void syncDeviceInfo(int paramInt1, int paramInt2, int paramInt3)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(35));
    this.cmd.add(Integer.valueOf(3));
    this.cmd.add(Integer.valueOf(paramInt1));
    this.cmd.add(Integer.valueOf(paramInt2 + 1));
    this.cmd.add(Integer.valueOf(paramInt3));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void syncDeviceInfo(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2, int paramInt3)
  {
    setMySendListener(paramMySendListener);
    syncDeviceInfo(paramInt1, paramInt2, paramInt3);
  }

  static abstract interface OnPictrueListener
  {
    public abstract void onPictrueOk(String paramString);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.panel.PanelBussiness
 * JD-Core Version:    0.6.0
 */