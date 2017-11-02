package et.song.tool;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

public final class ETCamera
{
  public static boolean CheckCameraHardware(Context paramContext)
  {
    return paramContext.getPackageManager().hasSystemFeature("android.hardware.camera");
  }

  public static Camera getCameraInstance()
  {
    try
    {
      Camera localCamera = Camera.open();
      return localCamera;
    }
    catch (Exception localException)
    {
    }
    return null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tool.ETCamera
 * JD-Core Version:    0.6.0
 */