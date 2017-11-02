package et.song.etclass;

import android.content.ContentValues;
import android.database.Cursor;
import et.song.db.ETDB;
import et.song.remote.instance.AIR;

public class ETDeviceAIR extends ETDevice
{
  private AIR mAir = new AIR();

  public ETDeviceAIR()
  {
  }

  public ETDeviceAIR(int paramInt)
  {
    int i = 0;
    while (true)
      if (i < 7)
      {
        ETKey localETKey = new ETKey();
        localETKey.SetState(3);
        localETKey.SetKey(0xC000 | 1 + i * 2);
        localETKey.SetRow(paramInt);
        try
        {
          localETKey.SetValue(this.mAir.Search(paramInt, localETKey.GetKey()));
          SetKey(localETKey);
          i++;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
  }

  public ETDeviceAIR(int paramInt1, int paramInt2)
  {
    int i = 0;
    while (true)
      if (i < 7)
      {
        ETKey localETKey = new ETKey();
        localETKey.SetState(2);
        localETKey.SetKey(0xC000 | 1 + i * 2);
        localETKey.SetBrandIndex(paramInt1);
        localETKey.SetBrandPos(paramInt2);
        try
        {
          localETKey.SetValue(this.mAir.Search(paramInt1, paramInt2, localETKey.GetKey()));
          SetKey(localETKey);
          i++;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
  }

  public void Delete(ETDB paramETDB)
  {
    try
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(GetID());
      paramETDB.deleteData("ETAirDevice", "did = ?", arrayOfString);
      super.Delete(paramETDB);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public byte GetAutoWindDir()
  {
    return this.mAir.GetAutoWindDir();
  }

  public byte[] GetKeyValue(int paramInt)
    throws Exception
  {
    ETKey localETKey = GetKeyByValue(paramInt);
    if (localETKey.GetState() == 1)
      return super.GetKeyValue(paramInt);
    if (localETKey.GetState() == 3)
      return this.mAir.Search(localETKey.GetRow(), paramInt);
    if (localETKey.GetState() == 2)
      return this.mAir.Search(localETKey.GetBrandIndex(), localETKey.GetBrandPos(), paramInt);
    return null;
  }

  public byte GetMode()
  {
    return this.mAir.GetMode();
  }

  public byte GetPower()
  {
    return this.mAir.GetPower();
  }

  public byte GetTemp()
  {
    return this.mAir.GetTemp();
  }

  public byte GetWindDir()
  {
    return this.mAir.GetWindDir();
  }

  public byte GetWindRate()
  {
    return this.mAir.GetWindRate();
  }

  public void Inster(ETDB paramETDB)
  {
    super.Inster(paramETDB);
    try
    {
      Cursor localCursor1 = paramETDB.queryData2Cursor("select count(*) from ETDevice order by id desc", null);
      localCursor1.moveToFirst();
      long l = localCursor1.getLong(0);
      localCursor1.close();
      if (l == 0L)
        return;
      Cursor localCursor2 = paramETDB.queryData2Cursor("select * from ETDevice order by id desc", null);
      localCursor2.moveToFirst();
      int i = localCursor2.getInt(localCursor2.getColumnIndex("id"));
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("did", Integer.valueOf(i));
      localContentValues.put("air_temp", Byte.valueOf(GetTemp()));
      localContentValues.put("air_rate", Byte.valueOf(GetWindRate()));
      localContentValues.put("air_dir", Byte.valueOf(GetWindDir()));
      localContentValues.put("air_auto_dir", Byte.valueOf(GetAutoWindDir()));
      localContentValues.put("air_mode", Byte.valueOf(GetMode()));
      localContentValues.put("air_power", Byte.valueOf(GetPower()));
      paramETDB.insertData("ETAirDevice", localContentValues);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void Load(ETDB paramETDB)
  {
    super.Load(paramETDB);
    Cursor localCursor;
    try
    {
      localCursor = paramETDB.queryData2Cursor("select * from ETAirDevice where did = " + GetID(), null);
      localCursor.moveToFirst();
      while (!localCursor.isAfterLast())
      {
        SetTemp((byte)localCursor.getInt(localCursor.getColumnIndex("air_temp")));
        SetWindRate((byte)localCursor.getInt(localCursor.getColumnIndex("air_rate")));
        SetWindDir((byte)localCursor.getInt(localCursor.getColumnIndex("air_dir")));
        SetAutoWindDir((byte)localCursor.getInt(localCursor.getColumnIndex("air_auto_dir")));
        SetMode((byte)localCursor.getInt(localCursor.getColumnIndex("air_mode")));
        SetPower((byte)localCursor.getInt(localCursor.getColumnIndex("air_power")));
        localCursor.moveToNext();
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    localCursor.close();
  }

  public void SetAutoWindDir(byte paramByte)
  {
    this.mAir.SetAutoWindDir(paramByte);
  }

  public void SetMode(byte paramByte)
  {
    this.mAir.SetMode(paramByte);
  }

  public void SetPower(byte paramByte)
  {
    this.mAir.SetPower(paramByte);
  }

  public void SetTemp(byte paramByte)
  {
    this.mAir.SetTemp(paramByte);
  }

  public void SetWindDir(byte paramByte)
  {
    this.mAir.SetWindDir(paramByte);
  }

  public void SetWindRate(byte paramByte)
  {
    this.mAir.SetWindRate(paramByte);
  }

  public void Update(ETDB paramETDB)
  {
    super.Update(paramETDB);
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("did", Integer.valueOf(GetID()));
    localContentValues.put("air_temp", Byte.valueOf(GetTemp()));
    localContentValues.put("air_rate", Byte.valueOf(GetWindRate()));
    localContentValues.put("air_dir", Byte.valueOf(GetWindDir()));
    localContentValues.put("air_auto_dir", Byte.valueOf(GetAutoWindDir()));
    localContentValues.put("air_mode", Byte.valueOf(GetMode()));
    localContentValues.put("air_power", Byte.valueOf(GetPower()));
    try
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(GetID());
      paramETDB.updataData("ETAirDevice", localContentValues, "did = ?", arrayOfString);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETDeviceAIR
 * JD-Core Version:    0.6.0
 */