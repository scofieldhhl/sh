package com.ex.ltech.led;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.ex.ltech.led.acti.colors.Business;
import com.ex.ltech.led.my_view.ClipImageLayout;
import com.ex.ltech.led.my_view.ClipZoomImageView;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.FileUtil;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class AtClip extends Activity
{
  private ClipImageLayout mClipImageLayout;

  public void back(View paramView)
  {
    finish();
  }

  public void clip(View paramView)
  {
    Bitmap localBitmap = this.mClipImageLayout.clip();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
    byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
    new Intent().putExtra("CLIP_IC_DATA_KEY", arrayOfByte);
    if (getIntent().getStringExtra("OP_CLIP_TYPE").equals("OP_CLIP_TYPE_CAMARE"))
    {
      FileUtil.saveMyBitmap(getIntent().getStringExtra("OP_CLIP_IC_FILE_PATH_KEY"), localBitmap, "/ltech/led/image");
      setResult(300);
    }
    if (getIntent().getStringExtra("OP_CLIP_TYPE").equals("OP_CLIP_TYPE_GRALLRY"))
    {
      FileUtil.saveMyBitmap(getIntent().getStringExtra("OP_CLIP_IC_FILE_PATH_KEY"), localBitmap, "/ltech/led/image");
      setResult(400);
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968669);
    this.mClipImageLayout = ((ClipImageLayout)findViewById(2131558892));
    if (getIntent().getStringExtra("OP_CLIP_TYPE").equals("OP_CLIP_TYPE_CAMARE"))
      this.mClipImageLayout.mZoomImageView.setImageBitmap(BitmapFactory.decodeFile(getIntent().getStringExtra("OP_CLIP_IC_FILE_PATH_KEY")));
    ContentResolver localContentResolver;
    if (getIntent().getStringExtra("OP_CLIP_TYPE").equals("OP_CLIP_TYPE_GRALLRY"))
      localContentResolver = getContentResolver();
    try
    {
      Bitmap localBitmap = new Business(this).autoZoomInBM(BitmapUtils.ImageCenterCrop(BitmapFactory.decodeStream(localContentResolver.openInputStream(Uri.parse(getIntent().getStringExtra("OP_CLIP_IC_URI_KEY"))))), getIntent().getIntExtra("OP_CLIP_VIEW_MAX_WIDTH_KEY", 1000), getIntent().getIntExtra("OP_CLIP_VIEW_MAX_WIDTH_KEY", 1000));
      this.mClipImageLayout.mZoomImageView.setImageBitmap(localBitmap);
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.AtClip
 * JD-Core Version:    0.6.0
 */