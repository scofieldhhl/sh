package et.song.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import java.lang.reflect.Method;

public class ETDB
{
  public static ETDB instance = null;
  private DBHelper dbHelper;
  private SQLiteDatabase sqliteDatabase;

  private ETDB(Context paramContext)
  {
    this.dbHelper = new DBHelper(paramContext);
    this.sqliteDatabase = this.dbHelper.getReadableDatabase();
  }

  public static final ETDB getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new ETDB(paramContext);
    return instance;
  }

  public void close()
  {
    if (this.sqliteDatabase.isOpen())
      this.sqliteDatabase.close();
    if (this.dbHelper != null)
      this.dbHelper.close();
    if (instance != null)
      instance = null;
  }

  public int deleteData(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    boolean bool = this.sqliteDatabase.isOpen();
    int i = 0;
    if (bool)
      i = this.sqliteDatabase.delete(paramString1, paramString2, paramArrayOfString);
    return i;
  }

  public void deleteDataBySql(String paramString, String[] paramArrayOfString)
    throws Exception
  {
    if (this.sqliteDatabase.isOpen())
    {
      SQLiteStatement localSQLiteStatement = this.sqliteDatabase.compileStatement(paramString);
      if (paramArrayOfString != null)
      {
        int i = paramArrayOfString.length;
        for (int j = 0; j < i; j++)
          localSQLiteStatement.bindString(j + 1, paramArrayOfString[j]);
        Method[] arrayOfMethod = localSQLiteStatement.getClass().getDeclaredMethods();
        int k = arrayOfMethod.length;
        for (int m = 0; m < k; m++)
          arrayOfMethod[m];
        localSQLiteStatement.execute();
        localSQLiteStatement.close();
      }
    }
  }

  public void exe(String paramString)
  {
    this.sqliteDatabase.execSQL(paramString);
  }

  public Long insertData(String paramString, ContentValues paramContentValues)
  {
    long l = 0L;
    if (this.sqliteDatabase.isOpen())
      l = this.sqliteDatabase.insert(paramString, null, paramContentValues);
    return Long.valueOf(l);
  }

  public Long insertDataBySql(String paramString, String[] paramArrayOfString)
    throws Exception
  {
    long l = 0L;
    if (this.sqliteDatabase.isOpen())
    {
      SQLiteStatement localSQLiteStatement = this.sqliteDatabase.compileStatement(paramString);
      if (paramArrayOfString != null)
      {
        int i = paramArrayOfString.length;
        for (int j = 0; j < i; j++)
          localSQLiteStatement.bindString(j + 1, paramArrayOfString[j]);
        l = localSQLiteStatement.executeInsert();
        localSQLiteStatement.close();
      }
    }
    return Long.valueOf(l);
  }

  public Cursor queryData2Cursor(String paramString, String[] paramArrayOfString)
    throws Exception
  {
    if (this.sqliteDatabase.isOpen())
    {
      Cursor localCursor = this.sqliteDatabase.rawQuery(paramString, paramArrayOfString);
      if (localCursor != null)
        return localCursor;
    }
    return null;
  }

  public int updataData(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString)
  {
    boolean bool = this.sqliteDatabase.isOpen();
    int i = 0;
    if (bool)
      i = this.sqliteDatabase.update(paramString1, paramContentValues, paramString2, paramArrayOfString);
    return i;
  }

  public void updateDataBySql(String paramString, String[] paramArrayOfString)
    throws Exception
  {
    if (this.sqliteDatabase.isOpen())
    {
      SQLiteStatement localSQLiteStatement = this.sqliteDatabase.compileStatement(paramString);
      if (paramArrayOfString != null)
      {
        int i = paramArrayOfString.length;
        for (int j = 0; j < i; j++)
          localSQLiteStatement.bindString(j + 1, paramArrayOfString[j]);
        localSQLiteStatement.execute();
        localSQLiteStatement.close();
      }
    }
  }

  public class DBHelper extends SQLiteOpenHelper
  {
    private static final int DATABASE_VERSION = 7;
    private static final String TABLE_AIRDEVICE_CREATE = "CREATE TABLE IF NOT EXISTS ETAirDevice (id INTEGER PRIMARY KEY AUTOINCREMENT,did INTEGER,air_temp INTEGER,air_rate INTEGER,air_dir INTEGER,air_auto_dir INTEGER,air_mode INTEGER,air_power INTEGER);";
    private static final String TABLE_DEVICE_CREATE = "CREATE TABLE IF NOT EXISTS ETDevice (id INTEGER PRIMARY KEY AUTOINCREMENT,gid INTEGER,device_name TEXT,mac_id TEXT,device_type INTEGER,device_res INTEGER);";
    private static final String TABLE_GROUP_CREATE = "CREATE TABLE IF NOT EXISTS ETGroup (id INTEGER PRIMARY KEY AUTOINCREMENT,group_name TEXT,group_type INTEGER,group_res INTEGER);";
    private static final String TABLE_KEYEX_CREATE = "CREATE TABLE IF NOT EXISTS ETKEYEX (id INTEGER PRIMARY KEY AUTOINCREMENT,did INTEGER,key_name TEXT,key_value TEXT,key_key INTEGER);";
    private static final String TABLE_KEY_CREATE = "CREATE TABLE IF NOT EXISTS ETKEY (id INTEGER PRIMARY KEY AUTOINCREMENT,did INTEGER,key_name TEXT,key_res INTEGER,key_x FLOAT,key_y FLOAT,key_value TEXT,key_key INTEGER,key_brandindex INTEGER,key_brandpos INTEGER,key_row INTEGER,key_state INTEGER);";
    private static final String TABLE_WATCHTV_CREATE = "CREATE TABLE IF NOT EXISTS WATCHTV (id INTEGER PRIMARY KEY AUTOINCREMENT,did INTEGER,watchtv_name TEXT,watchtv_context TEXT,watchtv_res INTEGER,watchtv_value INTEGER,watchtv_ok INTEGER);";
    private static final String TABLE_WATCHTV_DELETE = "DROP TABLE IF EXISTS WATCHTV;";
    private static final String TABLE_WATCHTV_UPDATE = "ALTER TABLE WATCHTV ADD COLUMN watchtv_select INTEGER;";
    private static final String TABLE_WATCHTV_UPDATE_1 = "ALTER TABLE WATCHTV ADD COLUMN watchtv_value_ex TEXT;";
    private static final String TABLE_WIFIDEVICE_CREATE = "CREATE TABLE IF NOT EXISTS ETWifiDevice (id INTEGER PRIMARY KEY AUTOINCREMENT,wifidevice_name TEXT,wifidevice_uid TEXT,wifidevice_ssid TEXT,wifidevice_pwd TEXT,wifidevice_wan INTEGER,wifidevice_ip TEXT,wifidevice_port INTEGER,wifidevice_type INTEGER,wifidevice_res INTEGER);";
    private static final String TABLE_WIFIDIRECT_CREATE = "CREATE TABLE IF NOT EXISTS ETWifiDirect (id INTEGER PRIMARY KEY AUTOINCREMENT,wifidirect_ip TEXT,wifidirect_port INTEGER,wifidirect_res INTEGER);";

    DBHelper(Context arg2)
    {
      super("com.hxd.remotestat.db", null, 7);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ETGroup (id INTEGER PRIMARY KEY AUTOINCREMENT,group_name TEXT,group_type INTEGER,group_res INTEGER);");
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ETDevice (id INTEGER PRIMARY KEY AUTOINCREMENT,gid INTEGER,device_name TEXT,mac_id TEXT,device_type INTEGER,device_res INTEGER);");
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ETKEY (id INTEGER PRIMARY KEY AUTOINCREMENT,did INTEGER,key_name TEXT,key_res INTEGER,key_x FLOAT,key_y FLOAT,key_value TEXT,key_key INTEGER,key_brandindex INTEGER,key_brandpos INTEGER,key_row INTEGER,key_state INTEGER);");
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ETKEYEX (id INTEGER PRIMARY KEY AUTOINCREMENT,did INTEGER,key_name TEXT,key_value TEXT,key_key INTEGER);");
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ETWifiDevice (id INTEGER PRIMARY KEY AUTOINCREMENT,wifidevice_name TEXT,wifidevice_uid TEXT,wifidevice_ssid TEXT,wifidevice_pwd TEXT,wifidevice_wan INTEGER,wifidevice_ip TEXT,wifidevice_port INTEGER,wifidevice_type INTEGER,wifidevice_res INTEGER);");
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ETWifiDirect (id INTEGER PRIMARY KEY AUTOINCREMENT,wifidirect_ip TEXT,wifidirect_port INTEGER,wifidirect_res INTEGER);");
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ETAirDevice (id INTEGER PRIMARY KEY AUTOINCREMENT,did INTEGER,air_temp INTEGER,air_rate INTEGER,air_dir INTEGER,air_auto_dir INTEGER,air_mode INTEGER,air_power INTEGER);");
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS WATCHTV (id INTEGER PRIMARY KEY AUTOINCREMENT,did INTEGER,watchtv_name TEXT,watchtv_context TEXT,watchtv_res INTEGER,watchtv_value INTEGER,watchtv_ok INTEGER);");
      paramSQLiteDatabase.execSQL("ALTER TABLE WATCHTV ADD COLUMN watchtv_select INTEGER;");
      paramSQLiteDatabase.execSQL("ALTER TABLE WATCHTV ADD COLUMN watchtv_value_ex TEXT;");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (paramInt1 < 5)
      {
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS WATCHTV;");
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS WATCHTV (id INTEGER PRIMARY KEY AUTOINCREMENT,did INTEGER,watchtv_name TEXT,watchtv_context TEXT,watchtv_res INTEGER,watchtv_value INTEGER,watchtv_ok INTEGER);");
        paramSQLiteDatabase.execSQL("ALTER TABLE WATCHTV ADD COLUMN watchtv_select INTEGER;");
        paramSQLiteDatabase.execSQL("ALTER TABLE WATCHTV ADD COLUMN watchtv_value_ex TEXT;");
        paramInt1 = 5;
      }
      if (paramInt1 < 6)
      {
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ETWifiDirect (id INTEGER PRIMARY KEY AUTOINCREMENT,wifidirect_ip TEXT,wifidirect_port INTEGER,wifidirect_res INTEGER);");
        paramInt1 = 6;
      }
      if (paramInt1 < 7)
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ETKEYEX (id INTEGER PRIMARY KEY AUTOINCREMENT,did INTEGER,key_name TEXT,key_value TEXT,key_key INTEGER);");
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.db.ETDB
 * JD-Core Version:    0.6.0
 */