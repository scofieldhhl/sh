package et.song.tool;

import android.content.Context;
import java.io.File;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ETFile
{
  public static final int ROOT = 0;
  public static final int TYPE_IMAGE = 1;
  public static final int TYPE_THUMBNAIL = 2;
  public static final int TYPE_VIDEO = 3;

  public static String createFileName(String paramString)
  {
    String str = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
    if (!paramString.startsWith("."))
      paramString = "." + paramString;
    return str + paramString;
  }

  public static boolean deleteSourceFile(String paramString, Context paramContext)
  {
    File localFile1 = new File(paramString);
    if (!localFile1.exists())
      return false;
    boolean bool = localFile1.delete();
    File localFile2 = new File(paramString.replace("Image", "Thumbnail"));
    if (!localFile2.exists())
      return bool;
    return localFile2.delete();
  }

  public static boolean deleteThumbFile(String paramString, Context paramContext)
  {
    File localFile1 = new File(paramString);
    if (!localFile1.exists())
      return false;
    boolean bool = localFile1.delete();
    File localFile2 = new File(paramString.replace("Thumbnail", "Image"));
    if (!localFile2.exists())
      return bool;
    return localFile2.delete();
  }

  public static String getFolderPath(Context paramContext, int paramInt, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(File.separator);
    switch (paramInt)
    {
    case 1:
    case 3:
    default:
    case 2:
    }
    while (true)
    {
      return localStringBuilder.toString();
      localStringBuilder.append("Thumbnail");
    }
  }

  public static List<File> listFiles(File paramFile, String paramString1, String paramString2)
  {
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.isDirectory()));
    File[] arrayOfFile;
    do
    {
      return null;
      arrayOfFile = paramFile.listFiles(new FilenameFilter(paramString2, paramString1)
      {
        public boolean accept(File paramFile, String paramString)
        {
          if ((ETFile.this == null) || (ETFile.this.equals("")))
            return paramString.endsWith(this.val$extension);
          return (paramString.contains(ETFile.this)) && (paramString.endsWith(this.val$extension));
        }
      });
    }
    while (arrayOfFile == null);
    ArrayList localArrayList = new ArrayList(Arrays.asList(arrayOfFile));
    sortList(localArrayList, false);
    return localArrayList;
  }

  public static List<File> listFiles(String paramString1, String paramString2)
  {
    return listFiles(new File(paramString1), paramString2, null);
  }

  public static List<File> listFiles(String paramString1, String paramString2, String paramString3)
  {
    return listFiles(new File(paramString1), paramString2, paramString3);
  }

  public static void sortList(List<File> paramList, boolean paramBoolean)
  {
    Collections.sort(paramList, new Comparator(paramBoolean)
    {
      public int compare(File paramFile1, File paramFile2)
      {
        if (paramFile1.lastModified() > paramFile2.lastModified())
          if (!this.val$asc);
        do
        {
          return 1;
          return -1;
          if (paramFile1.lastModified() == paramFile2.lastModified())
            return 0;
        }
        while (!this.val$asc);
        return -1;
      }
    });
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tool.ETFile
 * JD-Core Version:    0.6.0
 */