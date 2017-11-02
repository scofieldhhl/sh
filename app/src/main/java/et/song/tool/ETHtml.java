package et.song.tool;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;

public final class ETHtml
{
  public static Spanned GetSpanned(Context paramContext, String paramString)
  {
    return Html.fromHtml(paramString, new ImageGetter(paramContext)
    {
      public Drawable getDrawable(String paramString)
      {
        int i = Integer.parseInt(paramString);
        Drawable localDrawable = ETHtml.this.getResources().getDrawable(i);
        localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
        return localDrawable;
      }
    }
    , null);
  }

  public static String encodeHTML(String paramString)
  {
    return paramString.replaceAll("://", "/").replaceAll("admin:", "").replaceAll("#", "%23").replaceAll("%", "%25").replaceAll(" ", "%20").replaceAll("/", "%2F").replaceAll("@", "%40");
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tool.ETHtml
 * JD-Core Version:    0.6.0
 */