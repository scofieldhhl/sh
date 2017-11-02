package et.song.etclass;

import android.content.ContentValues;
import et.song.db.ETDB;
import et.song.face.IOp;
import java.util.ArrayList;
import java.util.List;

public class ETWifiDirect
  implements IOp
{
  private int mID;
  private String mIp;
  private int mPort;
  private int mResId;
  public List<ETWifiSub> mWifiSubList = new ArrayList();

  public void Delete(ETDB paramETDB)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = String.valueOf(this.mID);
    paramETDB.deleteData("ETWifiDirect", "id=?", arrayOfString);
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

  public int GetPort()
  {
    return this.mPort;
  }

  public int GetRes()
  {
    return this.mResId;
  }

  public void Inster(ETDB paramETDB)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("wifidirect_res", Integer.valueOf(this.mResId));
    localContentValues.put("wifidirect_port", Integer.valueOf(this.mPort));
    localContentValues.put("wifidirect_ip", this.mIp);
    paramETDB.insertData("ETWifiDirect", localContentValues);
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

  public void SetPort(int paramInt)
  {
    this.mPort = paramInt;
  }

  public void SetRes(int paramInt)
  {
    this.mResId = paramInt;
  }

  public void Update(ETDB paramETDB)
  {
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETWifiDirect
 * JD-Core Version:    0.6.0
 */