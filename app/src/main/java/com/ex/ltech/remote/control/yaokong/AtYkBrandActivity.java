package com.ex.ltech.remote.control.yaokong;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.my_view.SideBar;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.remote.control.YkAt;
import et.song.AdapterBrandList;
import et.song.AdapterPYinItem;
import et.song.PinyinComparator;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

public class AtYkBrandActivity extends YkAt
{
  ArrayList<AdapterPYinItem> commonItems = new ArrayList();
  ArrayList<AdapterPYinItem> items = new ArrayList();
  private ListView lvAtYkType;
  private AdapterBrandList mAdapter = null;
  private int mGroupIndex = 0;
  private int mType;

  @Bind({2131558756})
  RelativeLayout rlSearch;
  private SideBar sideBar;
  ArrayList<AdapterPYinItem> spareItems = new ArrayList();

  protected Void doInBackground()
  {
    HanyuPinyinOutputFormat localHanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
    localHanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    localHanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    switch (this.mType)
    {
    default:
      return null;
    case 8192:
      String[] arrayOfString9 = getResources().getStringArray(2131165218);
      int i19 = 0;
      int i20 = arrayOfString9.length;
      int i21 = 0;
      while (i21 < i20)
      {
        String str17 = arrayOfString9[i21];
        try
        {
          String str18 = StringUtils.getPinYin(str17);
          Locale localLocale7 = Locale.getDefault();
          if (str18.toLowerCase(localLocale7).contains("zhanghong"))
            str18 = "changhong";
          ArrayList localArrayList16 = this.items;
          AdapterPYinItem localAdapterPYinItem16 = new AdapterPYinItem(str17, str18, i19);
          localArrayList16.add(localAdapterPYinItem16);
          ArrayList localArrayList17 = this.spareItems;
          AdapterPYinItem localAdapterPYinItem17 = new AdapterPYinItem(str17, str18, i19);
          localArrayList17.add(localAdapterPYinItem17);
          i19++;
          i21++;
        }
        catch (Exception localException9)
        {
          while (true)
            localException9.printStackTrace();
        }
      }
    case 16384:
      String[] arrayOfString8 = getResources().getStringArray(2131165217);
      int i16 = 0;
      int i17 = arrayOfString8.length;
      int i18 = 0;
      while (i18 < i17)
      {
        String str15 = arrayOfString8[i18];
        try
        {
          String str16 = StringUtils.getPinYin(str15);
          Locale localLocale6 = Locale.getDefault();
          if (str16.toLowerCase(localLocale6).contains("zhanghong"))
            str16 = "changhong";
          ArrayList localArrayList14 = this.items;
          AdapterPYinItem localAdapterPYinItem14 = new AdapterPYinItem(str15, str16, i16);
          localArrayList14.add(localAdapterPYinItem14);
          ArrayList localArrayList15 = this.spareItems;
          AdapterPYinItem localAdapterPYinItem15 = new AdapterPYinItem(str15, str16, i16);
          localArrayList15.add(localAdapterPYinItem15);
          i16++;
          i18++;
        }
        catch (Exception localException8)
        {
          while (true)
            localException8.printStackTrace();
        }
      }
    case 24576:
      String[] arrayOfString7 = getResources().getStringArray(2131165212);
      int i13 = 0;
      int i14 = arrayOfString7.length;
      int i15 = 0;
      while (i15 < i14)
      {
        String str13 = arrayOfString7[i15];
        try
        {
          String str14 = StringUtils.getPinYin(str13);
          Locale localLocale5 = Locale.getDefault();
          if (str14.toLowerCase(localLocale5).contains("zhanghong"))
            str14 = "changhong";
          ArrayList localArrayList12 = this.items;
          AdapterPYinItem localAdapterPYinItem12 = new AdapterPYinItem(str13, str14, i13);
          localArrayList12.add(localAdapterPYinItem12);
          ArrayList localArrayList13 = this.spareItems;
          AdapterPYinItem localAdapterPYinItem13 = new AdapterPYinItem(str13, str14, i13);
          localArrayList13.add(localAdapterPYinItem13);
          i13++;
          i15++;
        }
        catch (Exception localException7)
        {
          while (true)
            localException7.printStackTrace();
        }
      }
    case 40960:
      String[] arrayOfString6 = getResources().getStringArray(2131165216);
      int i10 = 0;
      int i11 = arrayOfString6.length;
      int i12 = 0;
      while (i12 < i11)
      {
        String str11 = arrayOfString6[i12];
        try
        {
          String str12 = StringUtils.getPinYin(str11);
          Locale localLocale4 = Locale.getDefault();
          if (str12.toLowerCase(localLocale4).contains("zhanghong"))
            str12 = "changhong";
          ArrayList localArrayList10 = this.items;
          AdapterPYinItem localAdapterPYinItem10 = new AdapterPYinItem(str11, str12, i10);
          localArrayList10.add(localAdapterPYinItem10);
          ArrayList localArrayList11 = this.spareItems;
          AdapterPYinItem localAdapterPYinItem11 = new AdapterPYinItem(str11, str12, i10);
          localArrayList11.add(localAdapterPYinItem11);
          i10++;
          i12++;
        }
        catch (Exception localException6)
        {
          while (true)
            localException6.printStackTrace();
        }
      }
    case 32768:
      String[] arrayOfString5 = getResources().getStringArray(2131165213);
      int i7 = 0;
      int i8 = arrayOfString5.length;
      int i9 = 0;
      while (i9 < i8)
      {
        String str9 = arrayOfString5[i9];
        try
        {
          String str10 = StringUtils.getPinYin(str9);
          Locale localLocale3 = Locale.getDefault();
          if (str10.toLowerCase(localLocale3).contains("zhanghong"))
            str10 = "changhong";
          ArrayList localArrayList8 = this.items;
          AdapterPYinItem localAdapterPYinItem8 = new AdapterPYinItem(str9, str10, i7);
          localArrayList8.add(localAdapterPYinItem8);
          ArrayList localArrayList9 = this.spareItems;
          AdapterPYinItem localAdapterPYinItem9 = new AdapterPYinItem(str9, str10, i7);
          localArrayList9.add(localAdapterPYinItem9);
          i7++;
          i9++;
        }
        catch (Exception localException5)
        {
          while (true)
            localException5.printStackTrace();
        }
      }
    case 8448:
      String[] arrayOfString4 = getResources().getStringArray(2131165214);
      int i4 = 0;
      int i5 = arrayOfString4.length;
      int i6 = 0;
      while (i6 < i5)
      {
        String str7 = arrayOfString4[i6];
        try
        {
          String str8 = StringUtils.getPinYin(str7);
          Locale localLocale2 = Locale.getDefault();
          if (str8.toLowerCase(localLocale2).contains("zhanghong"))
            str8 = "changhong";
          ArrayList localArrayList6 = this.items;
          AdapterPYinItem localAdapterPYinItem6 = new AdapterPYinItem(str7, str8, i4);
          localArrayList6.add(localAdapterPYinItem6);
          ArrayList localArrayList7 = this.spareItems;
          AdapterPYinItem localAdapterPYinItem7 = new AdapterPYinItem(str7, str8, i4);
          localArrayList7.add(localAdapterPYinItem7);
          i4++;
          i6++;
        }
        catch (Exception localException4)
        {
          while (true)
            localException4.printStackTrace();
        }
      }
    case 49152:
      String[] arrayOfString3 = getResources().getStringArray(2131165210);
      int i2 = 0;
      int i3 = 0;
      while (i3 < arrayOfString3.length)
      {
        System.out.println("DeviceType.DEVICE_REMOTE_AIR      " + i3);
        String str5 = arrayOfString3[i3];
        try
        {
          String str6 = StringUtils.getPinYin(str5);
          Locale localLocale1 = Locale.getDefault();
          if (str6.toLowerCase(localLocale1).contains("zhanghong"))
            str6 = "changhong";
          System.out.println("DeviceType.DEVICE_REMOTE_AIR         pyin  " + str6);
          ArrayList localArrayList4 = this.items;
          AdapterPYinItem localAdapterPYinItem4 = new AdapterPYinItem(str5, str6, i2);
          localArrayList4.add(localAdapterPYinItem4);
          ArrayList localArrayList5 = this.spareItems;
          AdapterPYinItem localAdapterPYinItem5 = new AdapterPYinItem(str5, str6, i2);
          localArrayList5.add(localAdapterPYinItem5);
          System.out.println("DeviceType.DEVICE_REMOTE_AIR" + i2);
          i2++;
          i3++;
        }
        catch (Exception localException3)
        {
          while (true)
          {
            localException3.printStackTrace();
            System.out.println("DeviceType.DEVICE_REMOTE_AIR              Exception");
          }
        }
      }
    case 57344:
      String[] arrayOfString2 = getResources().getStringArray(2131165215);
      int m = 0;
      int n = arrayOfString2.length;
      int i1 = 0;
      while (i1 < n)
      {
        String str3 = arrayOfString2[i1];
        try
        {
          String str4 = StringUtils.getPinYin(str3);
          ArrayList localArrayList2 = this.items;
          AdapterPYinItem localAdapterPYinItem2 = new AdapterPYinItem(str3, str4, m);
          localArrayList2.add(localAdapterPYinItem2);
          ArrayList localArrayList3 = this.spareItems;
          AdapterPYinItem localAdapterPYinItem3 = new AdapterPYinItem(str3, str4, m);
          localArrayList3.add(localAdapterPYinItem3);
          m++;
          i1++;
        }
        catch (Exception localException2)
        {
          while (true)
            localException2.printStackTrace();
        }
      }
    case 8960:
    }
    String[] arrayOfString1 = getResources().getStringArray(2131165211);
    int i = 0;
    int j = arrayOfString1.length;
    int k = 0;
    while (k < j)
    {
      String str1 = arrayOfString1[k];
      try
      {
        String str2 = StringUtils.getPinYin(str1);
        ArrayList localArrayList1 = this.items;
        AdapterPYinItem localAdapterPYinItem1 = new AdapterPYinItem(str1, str2, i);
        localArrayList1.add(localAdapterPYinItem1);
        i++;
        k++;
      }
      catch (Exception localException1)
      {
        while (true)
          localException1.printStackTrace();
      }
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == this.saveYkOk)
    {
      setResult(this.saveYkOk);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968714);
    ButterKnife.bind(this);
    this.rlSearch.setVisibility(View.GONE);
    this.mType = getIntent().getIntExtra("type", -1);
    setTitleView();
    this.lvAtYkType = ((ListView)findViewById(2131559029));
    this.sideBar = ((SideBar)findViewById(2131558759));
    this.sideBar.setListView(this.lvAtYkType);
    TextView localTextView = (TextView)findViewById(2131558761);
    this.sideBar.setTextView(localTextView);
    doInBackground();
    onPostExecute();
    this.lvAtYkType.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        Intent localIntent = new Intent(AtYkBrandActivity.this, AtLearnActivity.class);
        localIntent.putExtra("index", ((AdapterPYinItem)AtYkBrandActivity.this.spareItems.get(paramInt)).getPos());
        localIntent.putExtra("type", AtYkBrandActivity.this.mType);
        localIntent.putExtra("yaoKongAllCount", AtYkBrandActivity.this.getIntent().getIntExtra("yaoKongAllCount", -1));
        localIntent.putExtra("group", AtYkBrandActivity.this.mGroupIndex);
        localIntent.putExtra("name", ((AdapterPYinItem)AtYkBrandActivity.this.spareItems.get(paramInt)).getName());
        AtYkBrandActivity.this.startActivityForResult(localIntent, 0);
      }
    });
  }

  protected void onPostExecute()
  {
    if (!this.items.isEmpty())
    {
      Collections.sort(this.spareItems, new PinyinComparator());
      Collections.sort(this.items, new PinyinComparator());
      switch (this.mType)
      {
      case 8448:
      case 16384:
      case 24576:
      case 40960:
      case 57344:
      default:
      case 8192:
      case 32768:
      case 49152:
      }
      while (true)
      {
        Object localObject = "";
        for (int j = this.commonItems.size(); j < this.items.size(); j++)
        {
          String str = ((AdapterPYinItem)this.spareItems.get(j)).getPyin().substring(0, 1).toUpperCase(Locale.getDefault());
          System.out.println(((AdapterPYinItem)this.items.get(j)).toString());
          if (!((String)localObject).equals(str))
          {
            ((AdapterPYinItem)this.spareItems.get(j)).setIsShow(true);
            ((AdapterPYinItem)this.spareItems.get(j)).setmPyinUpper(str);
          }
          localObject = str;
        }
        this.commonItems.add(this.items.get(5));
        this.commonItems.add(this.items.get(11));
        this.commonItems.add(this.items.get(25));
        this.commonItems.add(this.items.get(4));
        this.commonItems.add(this.items.get(32));
        this.commonItems.add(this.items.get(31));
        this.commonItems.add(this.items.get(19));
        this.commonItems.add(this.items.get(11));
        this.commonItems.add(this.items.get(16));
        this.commonItems.add(this.items.get(30));
        this.spareItems.addAll(0, this.commonItems);
        ((AdapterPYinItem)this.spareItems.get(0)).setIsShow(true);
        for (int m = 0; m < this.commonItems.size(); m++)
          ((AdapterPYinItem)this.spareItems.get(0)).setmPyinUpper(getString(2131099967));
        this.commonItems.add(this.items.get(0));
        this.commonItems.add(this.items.get(8));
        this.commonItems.add(this.items.get(3));
        this.commonItems.add(this.items.get(14));
        this.commonItems.add(this.items.get(6));
        this.commonItems.add(this.items.get(4));
        this.commonItems.add(this.items.get(1));
        this.commonItems.add(this.items.get(10));
        this.spareItems.addAll(0, this.commonItems);
        ((AdapterPYinItem)this.spareItems.get(0)).setIsShow(true);
        for (int k = 0; k < this.commonItems.size(); k++)
          ((AdapterPYinItem)this.spareItems.get(0)).setmPyinUpper(getString(2131099967));
        this.commonItems.add(this.items.get(14));
        this.commonItems.add(this.items.get(30));
        this.commonItems.add(this.items.get(16));
        this.commonItems.add(this.items.get(17));
        this.commonItems.add(this.items.get(66));
        this.commonItems.add(this.items.get(6));
        this.commonItems.add(this.items.get(1));
        this.commonItems.add(this.items.get(46));
        this.commonItems.add(this.items.get(13));
        this.commonItems.add(this.items.get(5));
        this.spareItems.addAll(0, this.commonItems);
        ((AdapterPYinItem)this.spareItems.get(0)).setIsShow(true);
        for (int i = 0; i < this.commonItems.size(); i++)
          ((AdapterPYinItem)this.spareItems.get(0)).setmPyinUpper(getString(2131099967));
      }
      this.mAdapter = new AdapterBrandList(this, this.spareItems);
      this.lvAtYkType.setAdapter(this.mAdapter);
    }
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleTextRes(2131100535);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.AtYkBrandActivity
 * JD-Core Version:    0.6.0
 */