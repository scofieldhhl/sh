package com.ex.ltech.onepiontfive.main.more.GeoFencing;

import android.content.Context;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.newscene.ExpandableLvSceneBusiness;
import com.ex.ltech.onepiontfive.main.vo.GeoSpaceVo;
import com.ex.ltech.onepiontfive.main.vo.Scene;
import com.ex.ltech.onepiontfive.main.vo.Scenes;
import java.util.ArrayList;

public class GeoFencingBusiness extends MyBusiness
{
  Context c;
  ArrayList<Integer> cmd = new ArrayList();

  public GeoFencingBusiness(Context paramContext)
  {
    super(paramContext);
    this.c = paramContext;
  }

  public GeoSpaceVo compareWithReturnInfo(String paramString)
  {
    int i = paramString.length();
    if (paramString.substring(18, 20).equalsIgnoreCase("a7"))
    {
      paramString.substring(0, i - 6);
      int j = Integer.parseInt(paramString.substring(20, 22), 16);
      new ArrayList();
      if (1 != 0)
      {
        GeoSpaceVo localGeoSpaceVo = new GeoSpaceVo();
        ArrayList localArrayList;
        StringBuffer localStringBuffer;
        int k;
        int m;
        int n;
        int i2;
        label528: String str5;
        if (Integer.parseInt(paramString.substring(24, 26), 16) == 1)
        {
          localGeoSpaceVo.setIsGoHome(true);
          localGeoSpaceVo.setIsOutHome(false);
          localGeoSpaceVo.setTouchSceneIndex(-1 + Integer.parseInt(paramString.substring(26, 28), 16));
          ExpandableLvSceneBusiness localExpandableLvSceneBusiness = new ExpandableLvSceneBusiness(this.c);
          if (localExpandableLvSceneBusiness.getSmartScenes().smartScenes.size() > localGeoSpaceVo.getTouchSceneIndex())
            localGeoSpaceVo.setSceneName(((Scene)localExpandableLvSceneBusiness.getSmartScenes().smartScenes.get(localGeoSpaceVo.getTouchSceneIndex())).getName());
          localArrayList = new ArrayList();
          RepeatDayVo localRepeatDayVo1 = new RepeatDayVo();
          localRepeatDayVo1.setDay(this.ct.getString(2131100055));
          localRepeatDayVo1.setSeleted(false);
          localArrayList.add(localRepeatDayVo1);
          RepeatDayVo localRepeatDayVo2 = new RepeatDayVo();
          localRepeatDayVo2.setDay(this.ct.getString(2131100003));
          localRepeatDayVo2.setSeleted(false);
          localArrayList.add(localRepeatDayVo2);
          RepeatDayVo localRepeatDayVo3 = new RepeatDayVo();
          localRepeatDayVo3.setDay(this.ct.getString(2131100004));
          localRepeatDayVo3.setSeleted(false);
          localArrayList.add(localRepeatDayVo3);
          RepeatDayVo localRepeatDayVo4 = new RepeatDayVo();
          localRepeatDayVo4.setDay(this.ct.getString(2131100005));
          localRepeatDayVo4.setSeleted(false);
          localArrayList.add(localRepeatDayVo4);
          RepeatDayVo localRepeatDayVo5 = new RepeatDayVo();
          localRepeatDayVo5.setDay(this.ct.getString(2131100006));
          localRepeatDayVo5.setSeleted(false);
          localArrayList.add(localRepeatDayVo5);
          RepeatDayVo localRepeatDayVo6 = new RepeatDayVo();
          localRepeatDayVo6.setDay(this.ct.getString(2131100007));
          localRepeatDayVo6.setSeleted(false);
          localArrayList.add(localRepeatDayVo6);
          RepeatDayVo localRepeatDayVo7 = new RepeatDayVo();
          localRepeatDayVo7.setDay(this.ct.getString(2131100008));
          localRepeatDayVo7.setSeleted(false);
          localArrayList.add(localRepeatDayVo7);
          RepeatDayVo localRepeatDayVo8 = new RepeatDayVo();
          localRepeatDayVo8.setDay(this.ct.getString(2131100009));
          localRepeatDayVo8.setSeleted(false);
          localArrayList.add(localRepeatDayVo8);
          localStringBuffer = new StringBuffer();
          String str1 = StringUtils.hexString2binaryString(paramString.substring(28, 30));
          k = 0;
          m = 0;
          n = 0;
          String[] arrayOfString = str1.split("");
          int i1 = arrayOfString.length;
          i2 = 0;
          if (i2 >= i1)
            break label1030;
          str5 = arrayOfString[i2];
          if (!str5.equals(""))
            if (str5.equals("1"))
              switch (n)
              {
              default:
              case 0:
              case 1:
              case 2:
              case 3:
              case 4:
              case 5:
              case 6:
              case 7:
              }
        }
        while (true)
        {
          n++;
          i2++;
          break label528;
          localGeoSpaceVo.setIsGoHome(false);
          localGeoSpaceVo.setIsOutHome(true);
          break;
          if (str5.equals("0"))
            continue;
          ((RepeatDayVo)localArrayList.get(0)).setSeleted(true);
          localStringBuffer.append(this.ct.getString(2131100055)).append("\t\t");
          k = 1;
          continue;
          if (str5.equals("0"))
            continue;
          ((RepeatDayVo)localArrayList.get(7)).setSeleted(true);
          localStringBuffer.append(this.ct.getString(2131100394)).append("\t\t");
          m++;
          continue;
          if (str5.equals("0"))
            continue;
          ((RepeatDayVo)localArrayList.get(6)).setSeleted(true);
          localStringBuffer.append(this.ct.getString(2131100404)).append("\t\t");
          m++;
          continue;
          if (str5.equals("0"))
            continue;
          ((RepeatDayVo)localArrayList.get(5)).setSeleted(true);
          localStringBuffer.append(this.ct.getString(2131100066)).append("\t\t");
          m++;
          continue;
          if (str5.equals("0"))
            continue;
          ((RepeatDayVo)localArrayList.get(4)).setSeleted(true);
          localStringBuffer.append(this.ct.getString(2131100071)).append("\t\t");
          m++;
          continue;
          if (str5.equals("0"))
            continue;
          ((RepeatDayVo)localArrayList.get(3)).setSeleted(true);
          localStringBuffer.append(this.ct.getString(2131100430)).append("\t\t");
          m++;
          continue;
          if (str5.equals("0"))
            continue;
          ((RepeatDayVo)localArrayList.get(2)).setSeleted(true);
          localStringBuffer.append(this.ct.getString(2131100467)).append("\t\t");
          m++;
          continue;
          if (str5.equals("0"))
            continue;
          ((RepeatDayVo)localArrayList.get(1)).setSeleted(true);
          localStringBuffer.append(this.ct.getString(2131100240)).append("\t\t");
          m++;
        }
        label1030: String str2;
        if (k == 0)
          str2 = StringUtils.reverse(localStringBuffer.toString());
        while (true)
        {
          localGeoSpaceVo.setRepeatDay(str2.trim());
          if (m != 7)
            break;
          localGeoSpaceVo.setRepeatDay(this.ct.getString(2131100055));
          int i3 = 0;
          while (true)
            if (i3 < localArrayList.size())
            {
              ((RepeatDayVo)localArrayList.get(i3)).setSeleted(false);
              i3++;
              continue;
              str2 = localStringBuffer.toString();
              break;
            }
          ((RepeatDayVo)localArrayList.get(0)).setSeleted(true);
        }
        localGeoSpaceVo.setRepeatDayVos(localArrayList);
        String str3;
        String str4;
        if (j > 4)
        {
          str3 = StringUtils.bytesStr2WordStr(paramString.substring(30, 62));
          str4 = StringUtils.bytesStr2WordStr(paramString.substring(62, 94));
        }
        try
        {
          localGeoSpaceVo.setLat(Double.valueOf(str3).doubleValue());
          localGeoSpaceVo.setLng(Double.valueOf(str4).doubleValue());
          return localGeoSpaceVo;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          localNumberFormatException.printStackTrace();
          return localGeoSpaceVo;
        }
      }
    }
    return null;
  }

  public void responseMessage(MyBusiness.MySendListener paramMySendListener, String paramString1, String paramString2)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString2, 16)));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString1, 16)));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
  }

  public void setGeoFencingInfo(MyBusiness.MySendListener paramMySendListener, GeoSpaceVo paramGeoSpaceVo)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(29));
    this.cmd.add(Integer.valueOf(35));
    ArrayList localArrayList1 = this.cmd;
    int i;
    if (paramGeoSpaceVo.getName().equalsIgnoreCase(this.ct.getString(2131099877)))
      i = 1;
    String str2;
    String str3;
    while (true)
    {
      localArrayList1.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(1 + paramGeoSpaceVo.getTouchSceneIndex()));
      ArrayList localArrayList2 = paramGeoSpaceVo.getRepeatDayVos();
      String str1 = "";
      if (((RepeatDayVo)localArrayList2.get(0)).isSeleted())
      {
        str1 = "01111111";
        this.cmd.add(Integer.valueOf(Integer.parseInt(str1, 2)));
        str2 = paramGeoSpaceVo.getLat() + "";
        str3 = paramGeoSpaceVo.getLng() + "";
        for (int k = 0; k < 16; k++)
        {
          if (k <= -1 + str2.length())
            continue;
          str2 = str2 + "0";
        }
        i = 2;
        continue;
      }
      int j = 1;
      label248: if (j < localArrayList2.size())
        if (!((RepeatDayVo)localArrayList2.get(j)).isSeleted())
          break label301;
      label301: for (str1 = 1 + str1; ; str1 = 0 + str1)
      {
        j++;
        break label248;
        break;
      }
    }
    for (int m = 0; m < 16; m++)
    {
      if (m <= -1 + str3.length())
        continue;
      str3 = str3 + "0";
    }
    for (int n = 0; n < str2.getBytes().length; n++)
      this.cmd.add(Integer.valueOf(str2.getBytes()[n]));
    for (int i1 = 0; i1 < str3.getBytes().length; i1++)
      this.cmd.add(Integer.valueOf(str3.getBytes()[i1]));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void syncGeoFencingInfo(MyBusiness.MySendListener paramMySendListener, int paramInt)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(39));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(5));
    this.cmd.add(Integer.valueOf(paramInt));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.GeoFencing.GeoFencingBusiness
 * JD-Core Version:    0.6.0
 */