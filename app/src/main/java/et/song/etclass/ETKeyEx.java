package et.song.etclass;

import android.content.ContentValues;
import et.song.db.ETDB;
import et.song.face.IOp;
import et.song.tool.ETTool;

public class ETKeyEx
  implements IOp
{
  private int mDID;
  private int mID;
  private int mKey;
  private byte[] mKeyValue;
  private String mName;

  public void Delete(ETDB paramETDB)
  {
  }

  public int GetCount()
  {
    return 0;
  }

  public int GetDID()
  {
    return this.mDID;
  }

  public int GetId()
  {
    return this.mID;
  }

  public Object GetItem(int paramInt)
  {
    return null;
  }

  public int GetKey()
  {
    return this.mKey;
  }

  public String GetName()
  {
    return this.mName;
  }

  public byte[] GetValue()
  {
    return this.mKeyValue;
  }

  public void Inster(ETDB paramETDB)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("did", Integer.valueOf(GetDID()));
      localContentValues.put("key_name", GetName());
      localContentValues.put("key_value", ETTool.BytesToHexString(GetValue()));
      localContentValues.put("key_key", Integer.valueOf(GetKey()));
      paramETDB.insertData("ETKEYEX", localContentValues);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void Load(ETDB paramETDB)
  {
  }

  public void SetDID(int paramInt)
  {
    this.mDID = paramInt;
  }

  public void SetId(int paramInt)
  {
    this.mID = paramInt;
  }

  public void SetKey(int paramInt)
  {
    this.mKey = paramInt;
  }

  public void SetName(String paramString)
  {
    this.mName = paramString;
  }

  public void SetValue(byte[] paramArrayOfByte)
  {
    this.mKeyValue = paramArrayOfByte;
  }

  public void Update(ETDB paramETDB)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("key_name", GetName());
      localContentValues.put("key_value", ETTool.BytesToHexString(GetValue()));
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(this.mID);
      paramETDB.updataData("ETKEYEX", localContentValues, "id = ?", arrayOfString);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETKeyEx
 * JD-Core Version:    0.6.0
 */