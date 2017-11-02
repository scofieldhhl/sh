package et.song.etclass;

import android.content.ContentValues;
import et.song.db.ETDB;
import et.song.face.IOp;
import java.util.ArrayList;
import java.util.List;

public class ETWifiDevice
  implements IOp
{
  private int mID;
  private String mIp;
  private int mIsWan;
  private String mName;
  private String mPWD;
  private int mPort;
  private int mResId;
  private String mSSID;
  private int mType;
  private String mUId;
  public List<ETWifiSub> mWifiSubList = new ArrayList();

  public void Delete(ETDB paramETDB)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = String.valueOf(this.mID);
    paramETDB.deleteData("ETWifiDevice", "id=?", arrayOfString);
  }

  public int GetCount()
  {
    return this.mWifiSubList.size();
  }

  public int GetID()
  {
    return this.mID;
  }

  public String GetIP()
  {
    return this.mIp;
  }

  public Object GetItem(int paramInt)
  {
    return this.mWifiSubList.get(paramInt);
  }

  public String GetName()
  {
    return this.mName;
  }

  public String GetPWD()
  {
    return this.mPWD;
  }

  public int GetPort()
  {
    return this.mPort;
  }

  public int GetRes()
  {
    return this.mResId;
  }

  public String GetSSID()
  {
    return this.mSSID;
  }

  public int GetType()
  {
    return this.mType;
  }

  public String GetUID()
  {
    return this.mUId;
  }

  public int GetWan()
  {
    return this.mIsWan;
  }

  public void Inster(ETDB paramETDB)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("wifidevice_name", this.mName);
    localContentValues.put("wifidevice_res", Integer.valueOf(this.mResId));
    localContentValues.put("wifidevice_type", Integer.valueOf(this.mType));
    localContentValues.put("wifidevice_uid", this.mUId);
    localContentValues.put("wifidevice_ssid", this.mSSID);
    localContentValues.put("wifidevice_pwd", this.mPWD);
    localContentValues.put("wifidevice_wan", Integer.valueOf(this.mIsWan));
    localContentValues.put("wifidevice_port", Integer.valueOf(this.mPort));
    localContentValues.put("wifidevice_ip", this.mIp);
    paramETDB.insertData("ETWifiDevice", localContentValues);
  }

  public void Load(ETDB paramETDB)
  {
    this.mWifiSubList.clear();
  }

  public void SetID(int paramInt)
  {
    this.mID = paramInt;
  }

  public void SetIP(String paramString)
  {
    this.mIp = paramString;
  }

  public void SetName(String paramString)
  {
    this.mName = paramString;
  }

  public void SetPWD(String paramString)
  {
    this.mPWD = paramString;
  }

  public void SetPort(int paramInt)
  {
    this.mPort = paramInt;
  }

  public void SetRes(int paramInt)
  {
    this.mResId = paramInt;
  }

  public void SetSSID(String paramString)
  {
    this.mSSID = paramString;
  }

  public void SetType(int paramInt)
  {
    this.mType = paramInt;
  }

  public void SetUID(String paramString)
  {
    this.mUId = paramString;
  }

  public void SetWan(int paramInt)
  {
    this.mIsWan = paramInt;
  }

  public void Update(ETDB paramETDB)
  {
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETWifiDevice
 * JD-Core Version:    0.6.0
 */