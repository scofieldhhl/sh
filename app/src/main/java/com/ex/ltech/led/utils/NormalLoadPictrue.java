package com.ex.ltech.led.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NormalLoadPictrue
{
  Handler handle = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      if ((paramMessage.what == 1) && (NormalLoadPictrue.this.picByte != null))
      {
        Bitmap localBitmap = BitmapFactory.decodeByteArray(NormalLoadPictrue.this.picByte, 0, NormalLoadPictrue.this.picByte.length);
        NormalLoadPictrue.this.imageView.setImageBitmap(localBitmap);
      }
    }
  };
  private ImageView imageView;
  private byte[] picByte;
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      InputStream localInputStream;
      ByteArrayOutputStream localByteArrayOutputStream;
      try
      {
        HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(NormalLoadPictrue.this.uri).openConnection();
        localHttpURLConnection.setRequestMethod("GET");
        localHttpURLConnection.setReadTimeout(10000);
        if (localHttpURLConnection.getResponseCode() == 200)
        {
          localInputStream = localHttpURLConnection.getInputStream();
          localByteArrayOutputStream = new ByteArrayOutputStream();
          byte[] arrayOfByte = new byte[1024];
          while (true)
          {
            int i = localInputStream.read(arrayOfByte);
            if (i == -1)
              break;
            localByteArrayOutputStream.write(arrayOfByte, 0, i);
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return;
      NormalLoadPictrue.access$002(NormalLoadPictrue.this, localByteArrayOutputStream.toByteArray());
      localByteArrayOutputStream.close();
      localInputStream.close();
      Message localMessage = new Message();
      localMessage.what = 1;
      NormalLoadPictrue.this.handle.sendMessage(localMessage);
    }
  };
  private String uri;

  public void getPicture(String paramString, ImageView paramImageView)
  {
    this.uri = paramString;
    this.imageView = paramImageView;
    new Thread(this.runnable).start();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.NormalLoadPictrue
 * JD-Core Version:    0.6.0
 */