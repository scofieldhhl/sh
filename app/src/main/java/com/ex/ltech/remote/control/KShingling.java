package com.ex.ltech.remote.control;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KShingling
{
  private static final Pattern spaceReg = Pattern.compile("\\s+");
  protected int k;
  private final HashMap<String, Integer> shingles = new HashMap();

  public KShingling()
  {
    this.k = 5;
  }

  public KShingling(int paramInt)
  {
    if (paramInt <= 0)
      throw new InvalidParameterException("k should be positive!");
    this.k = paramInt;
  }

  private static int[] convertIntegers(List<Integer> paramList)
  {
    int[] arrayOfInt = new int[paramList.size()];
    Iterator localIterator = paramList.iterator();
    for (int i = 0; i < arrayOfInt.length; i++)
      arrayOfInt[i] = ((Integer)localIterator.next()).intValue();
    return arrayOfInt;
  }

  private HashMap<Integer, Integer> getHashProfile(String paramString)
  {
    HashMap localHashMap = new HashMap(paramString.length());
    String str1 = spaceReg.matcher(paramString).replaceAll(" ");
    int i = 0;
    if (i < 1 + (str1.length() - this.k))
    {
      String str2 = str1.substring(i, i + this.k);
      int j;
      if (this.shingles.containsKey(str2))
      {
        j = ((Integer)this.shingles.get(str2)).intValue();
        label88: if (!localHashMap.containsKey(Integer.valueOf(j)))
          break label168;
        localHashMap.put(Integer.valueOf(j), Integer.valueOf(1 + ((Integer)localHashMap.get(Integer.valueOf(j))).intValue()));
      }
      while (true)
      {
        i++;
        break;
        j = this.shingles.size();
        this.shingles.put(str2, Integer.valueOf(this.shingles.size()));
        break label88;
        label168: localHashMap.put(Integer.valueOf(j), Integer.valueOf(1));
      }
    }
    return localHashMap;
  }

  protected int[] getArrayProfile(String paramString)
  {
    ArrayList localArrayList = new ArrayList(this.shingles.size());
    for (int i = 0; i < this.shingles.size(); i++)
      localArrayList.add(Integer.valueOf(0));
    String str1 = spaceReg.matcher(paramString).replaceAll(" ");
    int j = 0;
    if (j < 1 + (str1.length() - this.k))
    {
      String str2 = str1.substring(j, j + this.k);
      if (this.shingles.containsKey(str2))
      {
        int m = ((Integer)this.shingles.get(str2)).intValue();
        localArrayList.set(m, Integer.valueOf(1 + ((Integer)localArrayList.get(m)).intValue()));
      }
      while (true)
      {
        j++;
        break;
        this.shingles.put(str2, Integer.valueOf(this.shingles.size()));
        localArrayList.add(Integer.valueOf(1));
      }
    }
    return convertIntegers(localArrayList);
  }

  public int getDimension()
  {
    return this.shingles.size();
  }

  public int getK()
  {
    return this.k;
  }

  String getNGram(int paramInt)
  {
    Iterator localIterator = this.shingles.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (((Integer)localEntry.getValue()).equals(Integer.valueOf(paramInt)))
        return (String)localEntry.getKey();
    }
    throw new InvalidParameterException("No ngram coresponds to key " + paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.KShingling
 * JD-Core Version:    0.6.0
 */