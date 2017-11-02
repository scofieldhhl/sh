package et.song.tool;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;

public final class ETVideo
{
  public static Bitmap getVideoThumbnail(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    return ThumbnailUtils.extractThumbnail(ThumbnailUtils.createVideoThumbnail(paramString, paramInt3), paramInt1, paramInt2, 2);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tool.ETVideo
 * JD-Core Version:    0.6.0
 */