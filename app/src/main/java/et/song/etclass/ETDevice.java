package et.song.etclass;

import android.content.ContentValues;
import android.database.Cursor;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import et.song.db.ETDB;
import et.song.face.IOp;
import et.song.jni.ir.ETIR;
import et.song.tool.ETTool;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ETDevice
  implements IOp
{
  private int m08 = 255;
  private int m10 = 255;
  private int m20 = 255;
  private int mGID;
  private int mID;
  private List<ETKeyEx> mKeyExList = new ArrayList();
  private List<ETKey> mKeyList = new ArrayList();
  private String mName;
  private int mResId;
  private int mType;

  public static ETDevice Builder(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 8192:
      return new ETDeviceTV();
    case 8448:
      return new ETDeviceIPTV();
    case 16384:
      return new ETDeviceSTB();
    case 24576:
      return new ETDeviceDVD();
    case 32768:
      return new ETDeviceFANS();
    case 40960:
      return new ETDevicePJT();
    case 57344:
      return new ETDeviceLIGHT();
    case 49152:
      return new ETDeviceAIR();
    case 8960:
      return new ETDeviceDC();
    case -33554432:
      return new ETDeviceCustom();
    case 11008:
    }
    return new ETDevicePower();
  }

  private int Get08()
  {
    return this.m08;
  }

  private int Get10()
  {
    return this.m10;
  }

  private int Get20()
  {
    return this.m20;
  }

  private void Set08(int paramInt)
  {
    this.m08 = paramInt;
  }

  private void Set10(int paramInt)
  {
    this.m10 = paramInt;
  }

  private void Set20(int paramInt)
  {
    this.m20 = paramInt;
  }

  public void Delete(ETDB paramETDB)
  {
    try
    {
      String[] arrayOfString1 = new String[1];
      arrayOfString1[0] = String.valueOf(this.mID);
      paramETDB.deleteData("WATCHTV", "did = ?", arrayOfString1);
      String[] arrayOfString2 = new String[1];
      arrayOfString2[0] = String.valueOf(this.mID);
      paramETDB.deleteData("ETKEYEX", "did = ?", arrayOfString2);
      String[] arrayOfString3 = new String[1];
      arrayOfString3[0] = String.valueOf(this.mID);
      paramETDB.deleteData("ETKEY", "did = ?", arrayOfString3);
      String[] arrayOfString4 = new String[1];
      arrayOfString4[0] = String.valueOf(this.mID);
      paramETDB.deleteData("ETDevice", "id = ?", arrayOfString4);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public int GetCount()
  {
    return this.mKeyList.size();
  }

  public int GetGID()
  {
    return this.mGID;
  }

  public int GetID()
  {
    return this.mID;
  }

  public Object GetItem(int paramInt)
  {
    return this.mKeyList.get(paramInt);
  }

  public ETKey GetKeyByIndex(int paramInt)
  {
    return (ETKey)this.mKeyList.get(paramInt);
  }

  public ETKeyEx GetKeyByIndexEx(int paramInt)
  {
    return (ETKeyEx)this.mKeyExList.get(paramInt);
  }

  public ETKey GetKeyByValue(int paramInt)
  {
    Iterator localIterator = this.mKeyList.iterator();
    while (localIterator.hasNext())
    {
      ETKey localETKey = (ETKey)localIterator.next();
      if (localETKey.GetKey() == paramInt)
        return localETKey;
    }
    return null;
  }

  public ETKeyEx GetKeyByValueEx(int paramInt)
  {
    Iterator localIterator = this.mKeyExList.iterator();
    while (localIterator.hasNext())
    {
      ETKeyEx localETKeyEx = (ETKeyEx)localIterator.next();
      if (localETKeyEx.GetKey() == paramInt)
        return localETKeyEx;
    }
    return null;
  }

  public byte[] GetKeyValue(int paramInt)
    throws Exception
  {
    ETKey localETKey = GetKeyByValue(paramInt);
    if (localETKey == null);
    do
    {
      do
        return null;
      while (localETKey.GetValue() == null);
      if (localETKey.GetState() == 1)
        return localETKey.GetValue();
      if (localETKey.GetState() == 3)
        return Work(localETKey.GetValue());
    }
    while (localETKey.GetState() != 2);
    return Work(localETKey.GetValue());
  }

  public byte[] GetKeyValueEx(int paramInt)
    throws Exception
  {
    ETKeyEx localETKeyEx = GetKeyByValueEx(paramInt);
    if (localETKeyEx == null);
    do
      return null;
    while (localETKeyEx.GetValue() == null);
    return Study(localETKeyEx.GetValue());
  }

  public String GetName()
  {
    return this.mName;
  }

  public int GetRes()
  {
    return this.mResId;
  }

  public int GetType()
  {
    return this.mType;
  }

  public void Inster(ETDB paramETDB)
  {
    if (GetName().equals(""))
      return;
    while (true)
    {
      try
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("gid", Integer.valueOf(GetGID()));
        localContentValues.put("device_name", GetName());
        localContentValues.put("device_res", Integer.valueOf(GetRes()));
        localContentValues.put("device_type", Integer.valueOf(GetType()));
        localContentValues.put("mac_id", DeviceListActivity.deviceMacAddress);
        paramETDB.insertData("ETDevice", localContentValues);
        Cursor localCursor1 = paramETDB.queryData2Cursor("SELECT count(*) FROM ETDevice order by id desc ", null);
        localCursor1.moveToFirst();
        long l = localCursor1.getLong(0);
        localCursor1.close();
        if (l == 0L)
          break;
        Cursor localCursor2 = paramETDB.queryData2Cursor("SELECT * FROM ETDevice order by id desc ", null);
        localCursor2.moveToFirst();
        int i = localCursor2.getInt(localCursor2.getColumnIndex("id"));
        int j = 0;
        if (j < this.mKeyList.size())
        {
          ETKey localETKey = (ETKey)this.mKeyList.get(j);
          localETKey.SetDID(i);
          localETKey.Inster(paramETDB);
          j++;
          continue;
          if (k >= this.mKeyExList.size())
            break;
          ETKeyEx localETKeyEx = (ETKeyEx)this.mKeyExList.get(k);
          localETKeyEx.SetDID(i);
          localETKeyEx.Inster(paramETDB);
          k++;
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      int k = 0;
    }
  }

  public void Load(ETDB paramETDB)
  {
    this.mKeyList.clear();
    try
    {
      localCursor2 = paramETDB.queryData2Cursor("select * from ETKEY where did = " + this.mID, null);
      localCursor2.moveToFirst();
      while (!localCursor2.isAfterLast())
      {
        ETKey localETKey = new ETKey();
        localETKey.SetId(localCursor2.getInt(localCursor2.getColumnIndex("id")));
        localETKey.SetDID(localCursor2.getInt(localCursor2.getColumnIndex("did")));
        localETKey.SetName(localCursor2.getString(localCursor2.getColumnIndex("key_name")));
        localETKey.SetState(localCursor2.getInt(localCursor2.getColumnIndex("key_state")));
        localETKey.SetRes(localCursor2.getInt(localCursor2.getColumnIndex("key_res")));
        localETKey.SetRow(localCursor2.getInt(localCursor2.getColumnIndex("key_row")));
        localETKey.SetBrandIndex(localCursor2.getInt(localCursor2.getColumnIndex("key_brandindex")));
        localETKey.SetBrandPos(localCursor2.getInt(localCursor2.getColumnIndex("key_brandpos")));
        localETKey.SetKey(localCursor2.getInt(localCursor2.getColumnIndex("key_key")));
        localETKey.SetValue(ETTool.HexStringToBytes(localCursor2.getString(localCursor2.getColumnIndex("key_value"))));
        localETKey.SetX(localCursor2.getFloat(localCursor2.getColumnIndex("key_x")));
        localETKey.SetY(localCursor2.getFloat(localCursor2.getColumnIndex("key_y")));
        this.mKeyList.add(localETKey);
        localCursor2.moveToNext();
      }
    }
    catch (Exception localException1)
    {
      Cursor localCursor2;
      localException1.printStackTrace();
      Cursor localCursor1;
      while (true)
      {
        this.mKeyExList.clear();
        try
        {
          localCursor1 = paramETDB.queryData2Cursor("select * from ETKEYEX where did = " + this.mID, null);
          localCursor1.moveToFirst();
          while (!localCursor1.isAfterLast())
          {
            ETKeyEx localETKeyEx = new ETKeyEx();
            localETKeyEx.SetId(localCursor1.getInt(localCursor1.getColumnIndex("id")));
            localETKeyEx.SetDID(localCursor1.getInt(localCursor1.getColumnIndex("did")));
            localETKeyEx.SetName(localCursor1.getString(localCursor1.getColumnIndex("key_name")));
            localETKeyEx.SetKey(localCursor1.getInt(localCursor1.getColumnIndex("key_key")));
            localETKeyEx.SetValue(ETTool.HexStringToBytes(localCursor1.getString(localCursor1.getColumnIndex("key_value"))));
            this.mKeyExList.add(localETKeyEx);
            localCursor1.moveToNext();
          }
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          return;
        }
        localCursor2.close();
      }
      localCursor1.close();
    }
  }

  public void SetGID(int paramInt)
  {
    this.mGID = paramInt;
  }

  public void SetID(int paramInt)
  {
    this.mID = paramInt;
  }

  public void SetKey(ETKey paramETKey)
  {
    this.mKeyList.add(paramETKey);
  }

  public void SetKeyEx(ETKeyEx paramETKeyEx)
  {
    this.mKeyExList.add(paramETKeyEx);
  }

  public void SetName(String paramString)
  {
    this.mName = paramString;
  }

  public void SetRes(int paramInt)
  {
    this.mResId = paramInt;
  }

  public void SetType(int paramInt)
  {
    this.mType = paramInt;
  }

  protected byte[] Study(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[2 + paramArrayOfByte.length];
    for (int i = 0; i < paramArrayOfByte.length; i++)
      arrayOfByte[i] = paramArrayOfByte[i];
    return ETIR.StudyCode(arrayOfByte, arrayOfByte.length);
  }

  public void Update(ETDB paramETDB)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("device_name", GetName());
    try
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(this.mID);
      paramETDB.updataData("ETDevice", localContentValues, "id = ?", arrayOfString);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  protected byte[] Work(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte[2] == 4)
      if (Get20() == 255)
        Set20(paramArrayOfByte[5]);
    while (true)
    {
      paramArrayOfByte[9] = (byte)(paramArrayOfByte[0] + paramArrayOfByte[1] + paramArrayOfByte[2] + paramArrayOfByte[3] + paramArrayOfByte[4] + paramArrayOfByte[5] + paramArrayOfByte[6] + paramArrayOfByte[7] + paramArrayOfByte[8]);
      Set20(255);
      Set08(255);
      Set10(255);
      return paramArrayOfByte;
      Set20(0x20 ^ Get20());
      paramArrayOfByte[5] = (byte)Get20();
      continue;
      if (paramArrayOfByte[2] == 10)
      {
        if (Get08() == 255)
        {
          Set08(paramArrayOfByte[5]);
          continue;
        }
        Set08(0x8 ^ Get08());
        paramArrayOfByte[5] = (byte)Get08();
        continue;
      }
      if (paramArrayOfByte[2] != 33)
        continue;
      if (Get10() == 255)
      {
        Set10(paramArrayOfByte[5]);
        continue;
      }
      Set10(0x10 ^ Get10());
      paramArrayOfByte[5] = (byte)Get10();
    }
  }

  public void addAllEtKeyList(List<ETKey> paramList)
  {
    this.mKeyList.addAll(paramList);
  }

  public void clearEtKeyList()
  {
    this.mKeyList.clear();
  }

  public List<ETKey> getmKeyList()
  {
    return this.mKeyList;
  }

  public void reomveKeyListItem(int paramInt)
  {
    this.mKeyList.remove(paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETDevice
 * JD-Core Version:    0.6.0
 */