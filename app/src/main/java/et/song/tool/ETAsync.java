package et.song.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class ETAsync
{
  private static ImageCallback mCallBack;
  static final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      ETAsync.mCallBack.imageLoaded((byte[])paramMessage.obj);
    }
  };
  private HashMap<String, SoftReference<Bitmap>> imageCache;
  private InputStream mInputStream;
  private String mTag;

  public ETAsync()
  {
    mCallBack = null;
    this.imageCache = new HashMap();
  }

  public static byte[] readInputStream(InputStream paramInputStream)
    throws Exception
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte1 = new byte[1024];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte1);
      if (i == -1)
      {
        localByteArrayOutputStream.flush();
        byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
        paramInputStream.close();
        localByteArrayOutputStream.close();
        return arrayOfByte2;
      }
      localByteArrayOutputStream.write(arrayOfByte1, 0, i);
    }
  }

  public Bitmap loadDrawable(InputStream paramInputStream, String paramString, ImageCallback paramImageCallback)
  {
    mCallBack = paramImageCallback;
    this.mInputStream = paramInputStream;
    this.mTag = paramString;
    if (this.imageCache.containsKey(this.mTag))
    {
      Bitmap localBitmap = (Bitmap)((SoftReference)this.imageCache.get(this.mTag)).get();
      if (localBitmap != null)
        return localBitmap;
    }
    new Thread()
    {
      public void run()
      {
        try
        {
          byte[] arrayOfByte = ETAsync.readInputStream(ETAsync.this.mInputStream);
          Bitmap localBitmap = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length);
          ETAsync.this.imageCache.put(ETAsync.this.mTag, new SoftReference(localBitmap));
          Message localMessage = ETAsync.mHandler.obtainMessage(0, arrayOfByte);
          ETAsync.mHandler.sendMessage(localMessage);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
    .start();
    return null;
  }

  public static abstract interface ImageCallback
  {
    public abstract void imageLoaded(byte[] paramArrayOfByte);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tool.ETAsync
 * JD-Core Version:    0.6.0
 */