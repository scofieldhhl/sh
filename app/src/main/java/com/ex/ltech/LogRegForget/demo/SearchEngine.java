package com.ex.ltech.LogRegForget.demo;

import android.content.Context;
import android.content.res.Resources;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import m.framework.utils.Hashon;

public class SearchEngine
{
  private static final String DB_FILE = "smssdk_pydb";
  private static HashMap<String, Object> hanzi2Pinyin;
  private boolean caseSensitive;
  private ArrayList<SearchIndex> index;

  public static void prepare(Context paramContext, Runnable paramRunnable)
  {
    1 local1 = new Runnable(paramContext, paramRunnable)
    {
      public void run()
      {
        monitorenter;
        try
        {
          if (SearchEngine.hanzi2Pinyin != null)
          {
            int j = SearchEngine.hanzi2Pinyin.size();
            if (j > 0)
              break label61;
          }
          try
          {
            int i = this.val$context.getResources().getIdentifier("smssdk_pydb", "raw", this.val$context.getPackageName());
            if (i <= 0)
              SearchEngine.access$002(new HashMap());
            while (true)
            {
              label61: if (this.val$afterPrepare != null)
                this.val$afterPrepare.run();
              monitorexit;
              return;
              BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(this.val$context.getResources().openRawResource(i))));
              String str = localBufferedReader.readLine();
              localBufferedReader.close();
              SearchEngine.access$002(new Hashon().fromJson(str));
            }
          }
          catch (Throwable localThrowable)
          {
            while (true)
            {
              localThrowable.printStackTrace();
              SearchEngine.access$002(new HashMap());
            }
          }
        }
        finally
        {
          monitorexit;
        }
        throw localObject;
      }
    };
    if (paramRunnable != null)
    {
      new Thread(local1).start();
      return;
    }
    local1.run();
  }

  public ArrayList<String> match(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (this.index == null);
    while (true)
    {
      return localArrayList;
      Iterator localIterator = this.index.iterator();
      while (localIterator.hasNext())
      {
        SearchIndex localSearchIndex = (SearchIndex)localIterator.next();
        if (!localSearchIndex.match(paramString, this.caseSensitive))
          continue;
        localArrayList.add(localSearchIndex.getText());
      }
    }
  }

  public void setCaseSensitive(boolean paramBoolean)
  {
    this.caseSensitive = paramBoolean;
  }

  public void setIndex(ArrayList<String> paramArrayList)
  {
    this.index = new ArrayList();
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      this.index.add(new SearchIndex(str, hanzi2Pinyin));
    }
  }

  private static class SearchIndex
  {
    private ArrayList<String> firstLatters;
    private ArrayList<String> pinyin;
    private String text;

    public SearchIndex(String paramString, HashMap<String, Object> paramHashMap)
    {
      this.text = paramString;
      this.pinyin = new ArrayList();
      this.firstLatters = new ArrayList();
      createPinyinList(paramHashMap);
    }

    private void createPinyinList(HashMap<String, Object> paramHashMap)
    {
      if ((paramHashMap != null) && (paramHashMap.size() > 0))
      {
        char[] arrayOfChar = this.text.toCharArray();
        ArrayList localArrayList1 = new ArrayList();
        int i = arrayOfChar.length;
        for (int j = 0; j < i; j++)
        {
          ArrayList localArrayList2 = (ArrayList)paramHashMap.get(String.valueOf(arrayOfChar[j]));
          if (localArrayList2 == null);
          String[] arrayOfString;
          for (int k = 0; ; k = localArrayList2.size())
          {
            arrayOfString = new String[k];
            for (int m = 0; m < k; m++)
            {
              String str = (String)((HashMap)localArrayList2.get(m)).get("yin");
              if ("none".equals(str))
                str = "";
              arrayOfString[m] = str;
            }
          }
          localArrayList1.add(arrayOfString);
        }
        HashSet localHashSet1 = new HashSet();
        HashSet localHashSet2 = new HashSet();
        toPinyinArray("", "", localHashSet1, localHashSet2, localArrayList1);
        this.pinyin.addAll(localHashSet1);
        this.firstLatters.addAll(localHashSet2);
      }
    }

    private boolean match(String paramString, boolean paramBoolean)
    {
      if ((paramString == null) || (paramString.trim().length() <= 0));
      String str;
      do
      {
        return true;
        str = paramString;
        if (paramBoolean)
          continue;
        str = paramString.toLowerCase();
      }
      while ((this.text != null) && (this.text.toLowerCase().contains(str)));
      Iterator localIterator1 = this.pinyin.iterator();
      while (localIterator1.hasNext())
        if (((String)localIterator1.next()).contains(str))
          return true;
      Iterator localIterator2 = this.firstLatters.iterator();
      while (localIterator2.hasNext())
        if (((String)localIterator2.next()).contains(str))
          return true;
      return false;
    }

    private void toPinyinArray(String paramString1, String paramString2, HashSet<String> paramHashSet1, HashSet<String> paramHashSet2, ArrayList<String[]> paramArrayList)
    {
      if (paramArrayList.size() > 0)
      {
        String[] arrayOfString = (String[])paramArrayList.get(0);
        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(paramArrayList);
        localArrayList.remove(0);
        int i = arrayOfString.length;
        int j = 0;
        if (j < i)
        {
          String str = arrayOfString[j];
          if (str.length() > 0)
            toPinyinArray(paramString1 + str, paramString2 + str.charAt(0), paramHashSet1, paramHashSet2, localArrayList);
          while (true)
          {
            j++;
            break;
            toPinyinArray(paramString1, paramString2, paramHashSet1, paramHashSet2, localArrayList);
          }
        }
      }
      else
      {
        paramHashSet1.add(paramString1);
        paramHashSet2.add(paramString2);
      }
    }

    public String getText()
    {
      return this.text;
    }

    public String toString()
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("text", this.text);
      localHashMap.put("pinyin", this.pinyin);
      localHashMap.put("firstLatters", this.firstLatters);
      return localHashMap.toString();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.demo.SearchEngine
 * JD-Core Version:    0.6.0
 */