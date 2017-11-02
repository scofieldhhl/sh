package com.ex.ltech.LogRegForget.demo;

import android.content.Context;
import android.content.res.Resources;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CountryUtils
{
  private static ArrayList<String[]> getArray(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramContext.getResources().getIdentifier(paramString, "array", paramContext.getPackageName());
    if (i > 0)
    {
      String[] arrayOfString = paramContext.getResources().getStringArray(i);
      int j = arrayOfString.length;
      for (int k = 0; k < j; k++)
        localArrayList.add(arrayOfString[k].split(","));
    }
    return localArrayList;
  }

  public static HashMap<Character, ArrayList<String[]>> getGroupedCountryList(Context paramContext)
  {
    int i = 0;
    Character[] arrayOfCharacter = new Character[26];
    arrayOfCharacter[0] = Character.valueOf('A');
    arrayOfCharacter[1] = Character.valueOf('B');
    arrayOfCharacter[2] = Character.valueOf('C');
    arrayOfCharacter[3] = Character.valueOf('D');
    arrayOfCharacter[4] = Character.valueOf('E');
    arrayOfCharacter[5] = Character.valueOf('F');
    arrayOfCharacter[6] = Character.valueOf('G');
    arrayOfCharacter[7] = Character.valueOf('H');
    arrayOfCharacter[8] = Character.valueOf('I');
    arrayOfCharacter[9] = Character.valueOf('J');
    arrayOfCharacter[10] = Character.valueOf('K');
    arrayOfCharacter[11] = Character.valueOf('L');
    arrayOfCharacter[12] = Character.valueOf('M');
    arrayOfCharacter[13] = Character.valueOf('N');
    arrayOfCharacter[14] = Character.valueOf('O');
    arrayOfCharacter[15] = Character.valueOf('P');
    arrayOfCharacter[16] = Character.valueOf('Q');
    arrayOfCharacter[17] = Character.valueOf('R');
    arrayOfCharacter[18] = Character.valueOf('S');
    arrayOfCharacter[19] = Character.valueOf('T');
    arrayOfCharacter[20] = Character.valueOf('U');
    arrayOfCharacter[21] = Character.valueOf('V');
    arrayOfCharacter[22] = Character.valueOf('W');
    arrayOfCharacter[23] = Character.valueOf('X');
    arrayOfCharacter[24] = Character.valueOf('Y');
    arrayOfCharacter[25] = Character.valueOf('Z');
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    int j = arrayOfCharacter.length;
    while (i < j)
    {
      Character localCharacter = arrayOfCharacter[i];
      ArrayList localArrayList = getArray(paramContext, "smssdk_country_group_" + String.valueOf(localCharacter).toLowerCase());
      if (!localArrayList.isEmpty())
        localLinkedHashMap.put(localCharacter, localArrayList);
      i++;
    }
    return localLinkedHashMap;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.demo.CountryUtils
 * JD-Core Version:    0.6.0
 */