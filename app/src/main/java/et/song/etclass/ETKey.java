package et.song.etclass;

import android.content.ContentValues;
import et.song.db.ETDB;
import et.song.face.IOp;
import et.song.tool.ETTool;

public class ETKey
  implements IOp
{
  public static final int ETKEY_STATE_DIY = 4;
  public static final int ETKEY_STATE_KNOWN = 2;
  public static final int ETKEY_STATE_NET = 5;
  public static final int ETKEY_STATE_NULL = 6;
  public static final int ETKEY_STATE_STUDY = 1;
  public static final int ETKEY_STATE_TYPE = 3;
  private int mBrandIndex;
  private int mBrandPos;
  private int mDID;
  private int mID;
  private int mKey;
  private byte[] mKeyValue;
  private String mName;
  private int mResId = 0;
  private int mRow;
  private int mState;
  private float mX;
  private float mY;

  public void Delete(ETDB paramETDB)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = String.valueOf(this.mID);
    paramETDB.deleteData("ETKEY", "id = ?", arrayOfString);
  }

  public int GetBrandIndex()
  {
    return this.mBrandIndex;
  }

  public int GetBrandPos()
  {
    return this.mBrandPos;
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

  public int GetRes()
  {
    return this.mResId;
  }

  public int GetRow()
  {
    return this.mRow;
  }

  public int GetState()
  {
    return this.mState;
  }

  public byte[] GetValue()
  {
    return this.mKeyValue;
  }

  public float GetX()
  {
    return this.mX;
  }

  public float GetY()
  {
    return this.mY;
  }

  public void Inster(ETDB paramETDB)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("did", Integer.valueOf(GetDID()));
      localContentValues.put("key_name", GetName());
      localContentValues.put("key_res", Integer.valueOf(GetRes()));
      localContentValues.put("key_x", Float.valueOf(GetX()));
      localContentValues.put("key_y", Float.valueOf(GetY()));
      localContentValues.put("key_value", ETTool.BytesToHexString(GetValue()));
      localContentValues.put("key_key", Integer.valueOf(GetKey()));
      localContentValues.put("key_brandindex", Integer.valueOf(GetBrandIndex()));
      localContentValues.put("key_brandpos", Integer.valueOf(GetBrandPos()));
      localContentValues.put("key_row", Integer.valueOf(GetRow()));
      localContentValues.put("key_state", Integer.valueOf(GetState()));
      paramETDB.insertData("ETKEY", localContentValues);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void Load(ETDB paramETDB)
  {
  }

  public void SetBrandIndex(int paramInt)
  {
    this.mBrandIndex = paramInt;
  }

  public void SetBrandPos(int paramInt)
  {
    this.mBrandPos = paramInt;
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

  public void SetPos(float paramFloat1, float paramFloat2)
  {
    this.mX = paramFloat1;
    this.mY = paramFloat2;
  }

  public void SetRes(int paramInt)
  {
    this.mResId = paramInt;
  }

  public void SetRow(int paramInt)
  {
    this.mRow = paramInt;
  }

  public void SetState(int paramInt)
  {
    this.mState = paramInt;
  }

  public void SetValue(byte[] paramArrayOfByte)
  {
    this.mKeyValue = paramArrayOfByte;
  }

  public void SetX(float paramFloat)
  {
    this.mX = paramFloat;
  }

  public void SetY(float paramFloat)
  {
    this.mY = paramFloat;
  }

  public void Update(ETDB paramETDB)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("key_name", GetName());
      localContentValues.put("key_state", Integer.valueOf(GetState()));
      localContentValues.put("key_value", ETTool.BytesToHexString(GetValue()));
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(this.mID);
      paramETDB.updataData("ETKEY", localContentValues, "id = ?", arrayOfString);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETKey
 * JD-Core Version:    0.6.0
 */