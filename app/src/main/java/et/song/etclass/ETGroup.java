package et.song.etclass;

import android.content.ContentValues;
import android.database.Cursor;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import et.song.db.ETDB;
import et.song.face.IOp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ETGroup
  implements IOp
{
  public List<ETDevice> mDeviceList = new ArrayList();
  private int mID;
  private String mName;
  private int mResId;
  private int mType;

  public void Delete(ETDB paramETDB)
  {
    try
    {
      Iterator localIterator = this.mDeviceList.iterator();
      while (localIterator.hasNext())
        ((ETDevice)localIterator.next()).Delete(paramETDB);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    String[] arrayOfString = new String[1];
    arrayOfString[0] = String.valueOf(this.mID);
    paramETDB.deleteData("ETGroup", "id = ?", arrayOfString);
  }

  public int GetCount()
  {
    return this.mDeviceList.size();
  }

  public int GetID()
  {
    return this.mID;
  }

  public Object GetItem(int paramInt)
  {
    return this.mDeviceList.get(paramInt);
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
    try
    {
      ContentValues localContentValues1 = new ContentValues();
      localContentValues1.put("group_name", GetName());
      localContentValues1.put("group_res", Integer.valueOf(GetRes()));
      localContentValues1.put("group_type", Integer.valueOf(GetType()));
      paramETDB.insertData("ETGroup", localContentValues1);
      Cursor localCursor1 = paramETDB.queryData2Cursor("select count(*) from ETGroup order by id desc", null);
      localCursor1.moveToFirst();
      long l = localCursor1.getLong(0);
      localCursor1.close();
      if (l == 0L)
        return;
      Cursor localCursor2 = paramETDB.queryData2Cursor("select * from ETGroup order by id desc", null);
      localCursor2.moveToFirst();
      int i = localCursor2.getInt(localCursor2.getColumnIndex("id"));
      for (int j = 0; j < this.mDeviceList.size(); j++)
      {
        ETDevice localETDevice = (ETDevice)this.mDeviceList.get(j);
        localETDevice.SetGID(i);
        ContentValues localContentValues2 = new ContentValues();
        localContentValues2.put("gid", Integer.valueOf(localETDevice.GetGID()));
        localContentValues2.put("device_name", localETDevice.GetName());
        localContentValues2.put("device_res", Integer.valueOf(localETDevice.GetRes()));
        localContentValues2.put("device_type", Integer.valueOf(localETDevice.GetType()));
        paramETDB.insertData("ETKEY", localContentValues2);
        localETDevice.Inster(paramETDB);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void Load(ETDB paramETDB)
  {
    this.mDeviceList.clear();
    while (true)
    {
      Cursor localCursor2;
      try
      {
        Cursor localCursor1 = paramETDB.queryData2Cursor("select count(*) from ETGroup", null);
        localCursor1.moveToFirst();
        long l = localCursor1.getLong(0);
        localCursor1.close();
        if (l != 0L)
          continue;
        ETDevice localETDevice1 = new ETDevice();
        localETDevice1.SetID(0);
        localETDevice1.SetName("");
        localETDevice1.SetType(-16777216);
        localETDevice1.SetRes(2130903040);
        this.mDeviceList.add(localETDevice1);
        return;
        localCursor2 = paramETDB.queryData2Cursor("select * from ETDevice where mac_id = '" + DeviceListActivity.deviceMacAddress + "'", null);
        localCursor2.moveToFirst();
        if (!localCursor2.isAfterLast())
        {
          int i = localCursor2.getInt(localCursor2.getColumnIndex("id"));
          int j = localCursor2.getInt(localCursor2.getColumnIndex("gid"));
          String str = localCursor2.getString(localCursor2.getColumnIndex("device_name"));
          int k = localCursor2.getInt(localCursor2.getColumnIndex("device_type"));
          int m = localCursor2.getInt(localCursor2.getColumnIndex("device_res"));
          ETDevice localETDevice2 = ETDevice.Builder(k);
          localETDevice2.SetID(i);
          localETDevice2.SetGID(j);
          localETDevice2.SetName(str);
          localETDevice2.SetType(k);
          localETDevice2.SetRes(m);
          localETDevice2.Load(paramETDB);
          this.mDeviceList.add(localETDevice2);
          localCursor2.moveToNext();
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      localCursor2.close();
    }
  }

  public void LoadEnableDevice(ETDB paramETDB)
  {
    this.mDeviceList.clear();
    Cursor localCursor2;
    try
    {
      Cursor localCursor1 = paramETDB.queryData2Cursor("select count(*) from ETGroup", null);
      localCursor1.moveToFirst();
      long l = localCursor1.getLong(0);
      localCursor1.close();
      if (l == 0L)
        return;
      localCursor2 = paramETDB.queryData2Cursor("select * from ETDevice where mac_id = '" + DeviceListActivity.deviceMacAddress + "'", null);
      localCursor2.moveToFirst();
      while (!localCursor2.isAfterLast())
      {
        int i = localCursor2.getInt(localCursor2.getColumnIndex("id"));
        int j = localCursor2.getInt(localCursor2.getColumnIndex("gid"));
        String str = localCursor2.getString(localCursor2.getColumnIndex("device_name"));
        int k = localCursor2.getInt(localCursor2.getColumnIndex("device_type"));
        int m = localCursor2.getInt(localCursor2.getColumnIndex("device_res"));
        ETDevice localETDevice = ETDevice.Builder(k);
        localETDevice.SetID(i);
        localETDevice.SetGID(j);
        localETDevice.SetName(str);
        localETDevice.SetType(k);
        localETDevice.SetRes(m);
        localETDevice.Load(paramETDB);
        this.mDeviceList.add(localETDevice);
        localCursor2.moveToNext();
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    localCursor2.close();
  }

  public void SetID(int paramInt)
  {
    this.mID = paramInt;
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

  public void Update(ETDB paramETDB)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("group_name", GetName());
    try
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(this.mID);
      paramETDB.updataData("ETGroup", localContentValues, "id = ?", arrayOfString);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void addAllDevices(List<ETDevice> paramList)
  {
    this.mDeviceList.clear();
    this.mDeviceList.addAll(paramList);
  }

  public void addDevice(ETDevice paramETDevice)
  {
    this.mDeviceList.add(paramETDevice);
  }

  public List<ETDevice> getmDeviceList()
  {
    return this.mDeviceList;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETGroup
 * JD-Core Version:    0.6.0
 */