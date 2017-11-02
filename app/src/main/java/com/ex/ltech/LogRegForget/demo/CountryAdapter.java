package com.ex.ltech.LogRegForget.demo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class CountryAdapter extends GroupListView.GroupAdapter
{
  private Context context;
  private ArrayList<ArrayList<String[]>> countries;
  private HashMap<Character, ArrayList<String[]>> rawData;
  private SearchEngine sEngine;
  private ArrayList<String> titles;

  public CountryAdapter(Context paramContext, GroupListView paramGroupListView)
  {
    super(paramGroupListView);
    this.context = paramContext;
    this.rawData = CountryUtils.getGroupedCountryList(paramContext);
    initSearchEngine();
    search(null);
  }

  private int dipToPx(Context paramContext, int paramInt)
  {
    return (int)(0.5F + paramContext.getResources().getDisplayMetrics().density * paramInt);
  }

  private void initSearchEngine()
  {
    this.sEngine = new SearchEngine();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = this.rawData.entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((ArrayList)((Map.Entry)localIterator1.next()).getValue()).iterator();
      while (localIterator2.hasNext())
        localArrayList.add(((String[])localIterator2.next())[0]);
    }
    this.sEngine.setIndex(localArrayList);
  }

  public int getCount(int paramInt)
  {
    if (this.countries == null);
    ArrayList localArrayList;
    do
    {
      return 0;
      localArrayList = (ArrayList)this.countries.get(paramInt);
    }
    while (localArrayList == null);
    return localArrayList.size();
  }

  public int getGroupCount()
  {
    if (this.titles == null)
      return 0;
    return this.titles.size();
  }

  public String getGroupTitle(int paramInt)
  {
    if (this.titles.size() != 0)
      return ((String)this.titles.get(paramInt)).toString();
    return null;
  }

  public String[] getItem(int paramInt1, int paramInt2)
  {
    if (this.countries.size() != 0)
      try
      {
        arrayOfString = (String[])((ArrayList)this.countries.get(paramInt1)).get(paramInt2);
        return arrayOfString;
      }
      catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
      {
        while (true)
        {
          localArrayIndexOutOfBoundsException.printStackTrace();
          String[] arrayOfString = null;
        }
      }
    return null;
  }

  public View getTitleView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      LinearLayout localLinearLayout = new LinearLayout(paramViewGroup.getContext());
      localLinearLayout.setOrientation(1);
      localLinearLayout.setBackgroundColor(-1);
      paramView = localLinearLayout;
      TextView localTextView = new TextView(paramViewGroup.getContext());
      localTextView.setTextSize(1, 11.0F);
      localTextView.setTextColor(paramViewGroup.getContext().getResources().getColor(2131492988));
      int i = dipToPx(paramViewGroup.getContext(), 6);
      localTextView.setPadding(0, i, 0, i);
      localTextView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
      localLinearLayout.addView(localTextView);
      View localView = new View(paramViewGroup.getContext());
      localView.setBackgroundColor(-1842205);
      localLinearLayout.addView(localView, new LinearLayout.LayoutParams(-1, 1));
    }
    String str = getGroupTitle(paramInt);
    ((TextView)((LinearLayout)paramView).getChildAt(0)).setText(str);
    return paramView;
  }

  public View getView(int paramInt1, int paramInt2, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      LinearLayout localLinearLayout = new LinearLayout(paramViewGroup.getContext());
      localLinearLayout.setBackgroundColor(-1);
      paramView = localLinearLayout;
      TextView localTextView1 = new TextView(paramViewGroup.getContext());
      localTextView1.setTextSize(2, 16.0F);
      int i = dipToPx(paramViewGroup.getContext(), 16);
      localTextView1.setPadding(0, i, 0, i);
      localLinearLayout.addView(localTextView1, new LinearLayout.LayoutParams(-1, -2));
    }
    String[] arrayOfString = getItem(paramInt1, paramInt2);
    TextView localTextView2;
    if (arrayOfString != null)
    {
      localTextView2 = (TextView)((LinearLayout)paramView).getChildAt(0);
      localTextView2.setText(arrayOfString[0]);
      if (((String)this.titles.get(paramInt1)).equals(this.context.getString(2131099985)))
      {
        localTextView2.setTextColor(paramViewGroup.getContext().getResources().getColor(2131492938));
        Drawable localDrawable = this.context.getResources().getDrawable(2130903457);
        localDrawable.setBounds(0, 0, localDrawable.getMinimumWidth(), localDrawable.getMinimumHeight());
        localTextView2.setCompoundDrawables(localDrawable, null, null, null);
      }
    }
    else
    {
      return paramView;
    }
    localTextView2.setTextColor(paramViewGroup.getContext().getResources().getColor(2131492989));
    localTextView2.setCompoundDrawables(null, null, null, null);
    return paramView;
  }

  public void onGroupChange(View paramView, String paramString)
  {
    ((TextView)((LinearLayout)paramView).getChildAt(0)).setText(paramString);
  }

  public void search(String paramString)
  {
    ArrayList localArrayList1 = this.sEngine.match(paramString);
    int i;
    if (localArrayList1 != null)
    {
      int j = localArrayList1.size();
      i = 0;
      if (j > 0);
    }
    else
    {
      localArrayList1 = new ArrayList();
      i = 1;
    }
    HashMap localHashMap = new HashMap();
    Iterator localIterator1 = localArrayList1.iterator();
    while (localIterator1.hasNext())
    {
      String str = (String)localIterator1.next();
      localHashMap.put(str, str);
    }
    this.titles = new ArrayList();
    this.countries = new ArrayList();
    Iterator localIterator2 = this.rawData.entrySet().iterator();
    while (localIterator2.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator2.next();
      ArrayList localArrayList2 = (ArrayList)localEntry.getValue();
      ArrayList localArrayList3 = new ArrayList();
      Iterator localIterator3 = localArrayList2.iterator();
      while (localIterator3.hasNext())
      {
        String[] arrayOfString = (String[])localIterator3.next();
        if ((i == 0) && (!localHashMap.containsKey(arrayOfString[0])))
          continue;
        localArrayList3.add(arrayOfString);
      }
      if (localArrayList3.size() <= 0)
        continue;
      this.titles.add(String.valueOf(localEntry.getKey()));
      this.countries.add(localArrayList3);
    }
  }

  public void setlocalItem2Frist(String paramString)
  {
    ArrayList localArrayList1 = this.sEngine.match(paramString);
    int i;
    if (localArrayList1 != null)
    {
      int j = localArrayList1.size();
      i = 0;
      if (j > 0);
    }
    else
    {
      localArrayList1 = new ArrayList();
      i = 1;
    }
    HashMap localHashMap = new HashMap();
    Iterator localIterator1 = localArrayList1.iterator();
    while (localIterator1.hasNext())
    {
      String str = (String)localIterator1.next();
      localHashMap.put(str, str);
    }
    Iterator localIterator2 = this.rawData.entrySet().iterator();
    while (localIterator2.hasNext())
    {
      ArrayList localArrayList2 = (ArrayList)((Map.Entry)localIterator2.next()).getValue();
      ArrayList localArrayList3 = new ArrayList();
      Iterator localIterator3 = localArrayList2.iterator();
      while (localIterator3.hasNext())
      {
        String[] arrayOfString = (String[])localIterator3.next();
        if ((i == 0) && (!localHashMap.containsKey(arrayOfString[0])))
          continue;
        localArrayList3.add(arrayOfString);
      }
      if (localArrayList3.size() <= 0)
        continue;
      this.titles.add(0, this.context.getString(2131099985));
      this.countries.add(0, localArrayList3);
    }
    notifyDataSetChanged();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.demo.CountryAdapter
 * JD-Core Version:    0.6.0
 */