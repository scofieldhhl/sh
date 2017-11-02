package com.ex.ltech.hongwai.yaokong;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.hongwai.YkAt;
import com.ex.ltech.hongwai.atRcs.AtAirConActivity;
import com.ex.ltech.hongwai.atRcs.AtFan;
import com.ex.ltech.hongwai.atRcs.AtNewBox;
import com.ex.ltech.hongwai.atRcs.AtNewStb;
import com.ex.ltech.hongwai.atRcs.AtNewTv;
import com.ex.ltech.hongwai.atRcs.AtProjecter;
import com.ex.ltech.hongwai.view.pickerview.OptionsPickerView;
import com.ex.ltech.hongwai.view.pickerview.OptionsPickerView.OnOptionsSelectListener;
import com.ex.ltech.hongwai.view.pickerview.model.IPickerViewData;
import com.ex.ltech.hongwai.vo.PickerViewData;
import com.ex.ltech.hongwai.vo.ProvinceBean;
import com.ex.ltech.hongwai.vo.city4json.A;
import com.ex.ltech.hongwai.vo.city4json.C;
import com.ex.ltech.hongwai.vo.city4json.Citylist;
import com.ex.ltech.hongwai.vo.city4json.Root;
import com.ex.ltech.led.acti.main.LocationAndWeatherBusiness;
import com.ex.ltech.led.my_view.SideBar;
import com.ex.ltech.led.utils.FileUtil;
import com.ex.ltech.led.vo.CityVo;
import com.ex.ltech.util.Logger;
import com.google.gson.Gson;
import com.hzy.tvmao.KookongSDK;
import com.hzy.tvmao.interf.IRequestResult;
import com.kookong.app.data.BrandList;
import com.kookong.app.data.BrandList.Brand;
import com.kookong.app.data.SpList;
import com.kookong.app.data.SpList.Sp;
import com.kookong.app.data.StbList;
import com.kookong.app.data.StbList.Stb;
import et.song.AdapterBrandList;
import et.song.AdapterPYinItem;
import et.song.PinyinComparator;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class AtYkBrandActivity extends YkAt
{
  private int areaId = 0;
  Root city4Json;
  ArrayList<ArrayList<IPickerViewData>> cityNullAreas = new ArrayList();
  ArrayList<AdapterPYinItem> commonItems = new ArrayList();
  private EditText etSearch;
  boolean iptvOrStb;
  boolean isGetLocalOk;

  @Bind({2131559061})
  TextView location;
  LocationAndWeatherBusiness locationAndWeatherBusiness;
  private ListView lvAtYkType;
  private AdapterBrandList mAdapter = null;
  private int mGroupIndex = 0;
  private int mType;
  ArrayList<AdapterPYinItem> nomalItems = new ArrayList();
  ArrayList<IPickerViewData> nullareas = new ArrayList();
  ArrayList<ProvinceBean> options1Items = new ArrayList();
  ArrayList<ArrayList<String>> options2Items = new ArrayList();
  ArrayList<ArrayList<ArrayList<IPickerViewData>>> options3Items = new ArrayList();
  OptionsPickerView pvOptions;

  @Bind({2131558756})
  RelativeLayout rl_search;
  ArrayList<AdapterPYinItem> showItems = new ArrayList();
  private SideBar sideBar;
  private int spId = 0;

  private void getCityStbList()
  {
    this.locationAndWeatherBusiness = new LocationAndWeatherBusiness(this);
    this.locationAndWeatherBusiness.setpHandler(new Handler()
    {
      public void handleMessage(Message paramMessage)
      {
        super.handleMessage(paramMessage);
        switch (paramMessage.what)
        {
        default:
          return;
        case 1:
        }
        AtYkBrandActivity.this.location.setVisibility(0);
        AtYkBrandActivity.this.location.setText(AtYkBrandActivity.this.locationAndWeatherBusiness.getCityVo().getProvince() + AtYkBrandActivity.this.locationAndWeatherBusiness.getCityVo().getCity() + AtYkBrandActivity.this.locationAndWeatherBusiness.getCityVo().getArea());
        KookongSDK.getAreaId(AtYkBrandActivity.this.locationAndWeatherBusiness.getCityVo().getProvince(), AtYkBrandActivity.this.locationAndWeatherBusiness.getCityVo().getCity(), "", new IRequestResult()
        {
          public void onFail(Integer paramInteger, String paramString)
          {
          }

          public void onSuccess(String paramString, Integer paramInteger)
          {
            if (!AtYkBrandActivity.this.isGetLocalOk)
            {
              AtYkBrandActivity.this.locationAndWeatherBusiness.stopLocat();
              AtYkBrandActivity.access$302(AtYkBrandActivity.this, paramInteger.intValue());
              KookongSDK.getOperaters(paramInteger.intValue(), new IRequestResult()
              {
                public void onFail(Integer paramInteger, String paramString)
                {
                }

                public void onSuccess(String paramString, SpList paramSpList)
                {
                  List localList = paramSpList.spList;
                  for (int i = 0; i < localList.size(); i++)
                    AtYkBrandActivity.this.showItems.add(i, new AdapterPYinItem(((SpList.Sp)localList.get(i)).spName, ((SpList.Sp)localList.get(i)).spName, ((SpList.Sp)localList.get(i)).spId, "A"));
                  if (localList.size() > 0)
                    ((AdapterPYinItem)AtYkBrandActivity.this.showItems.get(0)).setmPyinUpper(AtYkBrandActivity.this.getResources().getString(2131099967));
                  AtYkBrandActivity.this.mAdapter.notifyDataSetChanged();
                  AtYkBrandActivity.this.isGetLocalOk = true;
                }
              });
            }
          }
        });
      }
    });
    this.locationAndWeatherBusiness.startLocat();
  }

  private void test()
  {
    KookongSDK.getAreaId("北京市", "北京市", "海淀区", new IRequestResult()
    {
      public void onFail(Integer paramInteger, String paramString)
      {
      }

      public void onSuccess(String paramString, Integer paramInteger)
      {
        System.out.println("AreaId is : " + paramInteger);
      }
    });
    KookongSDK.getOperaters(110108, new IRequestResult()
    {
      public void onFail(Integer paramInteger, String paramString)
      {
      }

      public void onSuccess(String paramString, SpList paramSpList)
      {
        List localList = paramSpList.spList;
        for (int i = 0; i < localList.size(); i++)
        {
          System.out.println("The sp is " + ((SpList.Sp)localList.get(i)).spName);
          System.out.println("The sp is " + ((SpList.Sp)localList.get(i)).spId);
          System.out.println("The sp is " + ((SpList.Sp)localList.get(i)).type);
        }
      }
    });
  }

  protected void insertCommonBrandList()
  {
    Collections.sort(this.nomalItems, new PinyinComparator());
    ArrayList localArrayList = new ArrayList();
    int i;
    switch (this.mType)
    {
    case 1:
    case 4:
    case 7:
    default:
      i = localArrayList.size();
    case 2:
    case 5:
    case 8:
    case 3:
    case 6:
    }
    for (int j = 0; ; j++)
    {
      if (j >= localArrayList.size())
        break label507;
      int n = 0;
      while (true)
        if (n < this.showItems.size())
        {
          if (((AdapterPYinItem)this.showItems.get(n)).getName().equals(localArrayList.get(j)))
            this.commonItems.add(this.showItems.get(n));
          n++;
          continue;
          localArrayList.add("创维");
          localArrayList.add("海尔");
          localArrayList.add("三星");
          localArrayList.add("长虹");
          localArrayList.add("夏普");
          localArrayList.add("TCL");
          localArrayList.add("LG 乐金");
          localArrayList.add("康佳");
          localArrayList.add("索尼");
          break;
          localArrayList.add("格力");
          localArrayList.add("美的");
          localArrayList.add("海尔");
          localArrayList.add("海信");
          localArrayList.add("至高");
          localArrayList.add("大金");
          localArrayList.add("奥克斯");
          localArrayList.add("松下");
          localArrayList.add("格兰仕");
          localArrayList.add("春兰");
          break;
          localArrayList.add("艾美特");
          localArrayList.add("美的");
          localArrayList.add("格力");
          localArrayList.add("先锋");
          localArrayList.add("联创");
          localArrayList.add("华生");
          localArrayList.add("澳柯玛");
          localArrayList.add("荣事达");
          break;
          localArrayList.add("小米");
          localArrayList.add("乐视");
          localArrayList.add("天猫魔盒");
          localArrayList.add("海美迪");
          localArrayList.add("芒果嗨Q");
          localArrayList.add("英菲克");
          localArrayList.add("开博尔");
          localArrayList.add("美如画");
          localArrayList.add("开敏");
          localArrayList.add("迪优美特");
          break;
          localArrayList.add("爱普生");
          localArrayList.add("明基");
          localArrayList.add("日电");
          localArrayList.add("宏基");
          localArrayList.add("富可视");
          localArrayList.add("奥图码");
          localArrayList.add("索尼");
          localArrayList.add("松下");
          localArrayList.add("酷乐视");
          localArrayList.add("日立");
          break;
        }
    }
    label507: for (int k = 0; k < this.commonItems.size(); k++)
    {
      ((AdapterPYinItem)this.commonItems.get(k)).setmPyinUpper("1");
      ((AdapterPYinItem)this.commonItems.get(k)).setPyin("1");
    }
    this.showItems.clear();
    this.showItems.addAll(this.commonItems);
    this.showItems.addAll(this.nomalItems);
    if (this.commonItems.size() > 0)
    {
      ((AdapterPYinItem)this.showItems.get(0)).setIsShow(true);
      ((AdapterPYinItem)this.showItems.get(0)).setmPyinUpper(getResources().getString(2131099967));
    }
    Object localObject = "";
    for (int m = i; m < this.showItems.size(); m++)
    {
      String str = ((AdapterPYinItem)this.showItems.get(m)).getmPyinUpper().toUpperCase(Locale.getDefault());
      if (!((String)localObject).equals(str))
      {
        ((AdapterPYinItem)this.showItems.get(m)).setIsShow(true);
        ((AdapterPYinItem)this.showItems.get(m)).setmPyinUpper(str);
      }
      localObject = str;
    }
    System.out.println();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 10000)
    {
      setResult(10000);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968714);
    setTitleView();
    ButterKnife.bind(this);
    this.mType = getIntent().getIntExtra("D_TYPE_KEY", -1);
    this.mAdapter = new AdapterBrandList(this, this.showItems);
    this.etSearch = ((EditText)findViewById(2131558757));
    this.etSearch.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramEditable)
      {
      }

      public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
        if (paramCharSequence.length() != 0)
        {
          ArrayList localArrayList = new ArrayList();
          for (int i = 0; i < AtYkBrandActivity.this.nomalItems.size(); i++)
          {
            if (!((AdapterPYinItem)AtYkBrandActivity.this.nomalItems.get(i)).getName().contains(paramCharSequence))
              continue;
            localArrayList.add(AtYkBrandActivity.this.nomalItems.get(i));
          }
          AtYkBrandActivity.this.lvAtYkType.setAdapter(new AdapterBrandList(AtYkBrandActivity.this, localArrayList));
          return;
        }
        AtYkBrandActivity.this.lvAtYkType.setAdapter(AtYkBrandActivity.this.mAdapter);
      }
    });
    this.sideBar = ((SideBar)findViewById(2131558759));
    this.lvAtYkType = ((ListView)findViewById(2131559029));
    this.lvAtYkType.setAdapter(this.mAdapter);
    if (this.mType == 1)
    {
      setTiTleTextRes(2131100458);
      getCityStbList();
      this.rl_search.setVisibility(8);
      this.sideBar.setVisibility(8);
    }
    while (true)
    {
      this.sideBar.setListView(this.lvAtYkType);
      TextView localTextView = (TextView)findViewById(2131558761);
      this.sideBar.setTextView(localTextView);
      this.lvAtYkType.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          int i = AtYkBrandActivity.this.mType;
          Intent localIntent = null;
          switch (i)
          {
          case 4:
          case 7:
          default:
          case 2:
          case 1:
          case 5:
          case 8:
          case 3:
          case 6:
          }
          while (true)
          {
            localIntent.putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_CREATE");
            localIntent.putExtra("OP_AT_POSI_KEY", AtYkBrandActivity.this.getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
            localIntent.putExtra("RC_NAME_KEY", ((AdapterPYinItem)AtYkBrandActivity.this.showItems.get(paramInt)).getName());
            localIntent.putExtra("BRAND_ID", AtYkBrandActivity.this.mAdapter.getItem(paramInt).getPos());
            AtYkBrandActivity.this.startActivityForResult(localIntent, 0);
            return;
            localIntent = new Intent(AtYkBrandActivity.this, AtNewTv.class);
            continue;
            if (((AdapterPYinItem)AtYkBrandActivity.this.showItems.get(paramInt)).getName().indexOf("IPTV") == -1)
            {
              AtYkBrandActivity.this.iptvOrStb = false;
              localIntent = new Intent(AtYkBrandActivity.this, AtNewStb.class);
              localIntent.putExtra("AREA_Id_KEY", AtYkBrandActivity.this.areaId);
              continue;
            }
            AtYkBrandActivity.this.setTiTleTextRes(2131100108);
            AtYkBrandActivity.this.iptvOrStb = true;
            KookongSDK.getIPTV(((AdapterPYinItem)AtYkBrandActivity.this.showItems.get(paramInt)).getPos(), new IRequestResult()
            {
              public void onFail(Integer paramInteger, String paramString)
              {
              }

              public void onSuccess(String paramString, StbList paramStbList)
              {
                AtYkBrandActivity.this.nomalItems.clear();
                AtYkBrandActivity.this.showItems.clear();
                List localList = paramStbList.stbList;
                for (int i = 0; i < localList.size(); i++)
                  Logger.d("The Stb is " + ((StbList.Stb)localList.get(i)).bname);
                for (int j = 0; j < localList.size(); j++)
                {
                  AtYkBrandActivity.this.nomalItems.add(new AdapterPYinItem(((StbList.Stb)localList.get(j)).bname, "", ((StbList.Stb)localList.get(j)).bid, "A"));
                  AtYkBrandActivity.this.showItems.add(new AdapterPYinItem(((StbList.Stb)localList.get(j)).bname, "", ((StbList.Stb)localList.get(j)).bid, "A"));
                }
                AtYkBrandActivity.this.location.setVisibility(8);
                AtYkBrandActivity.this.mAdapter.notifyDataSetChanged();
              }
            });
            return;
            localIntent = new Intent(AtYkBrandActivity.this, AtAirConActivity.class);
            continue;
            localIntent = new Intent(AtYkBrandActivity.this, AtFan.class);
            continue;
            localIntent = new Intent(AtYkBrandActivity.this, AtNewBox.class);
            continue;
            localIntent = new Intent(AtYkBrandActivity.this, AtProjecter.class);
          }
        }
      });
      return;
      this.location.setVisibility(8);
      KookongSDK.getBrandListFromNet(this.mType, new IRequestResult()
      {
        public void onFail(Integer paramInteger, String paramString)
        {
          Toast.makeText(AtYkBrandActivity.this, "", 0).show();
        }

        public void onSuccess(String paramString, BrandList paramBrandList)
        {
          List localList = paramBrandList.brandList;
          for (int i = 0; i < localList.size(); i++)
          {
            AtYkBrandActivity.this.nomalItems.add(new AdapterPYinItem(((BrandList.Brand)localList.get(i)).cname, ((BrandList.Brand)localList.get(i)).ename, ((BrandList.Brand)localList.get(i)).brandId, ((BrandList.Brand)localList.get(i)).initial));
            AtYkBrandActivity.this.showItems.add(new AdapterPYinItem(((BrandList.Brand)localList.get(i)).cname, ((BrandList.Brand)localList.get(i)).ename, ((BrandList.Brand)localList.get(i)).brandId, ((BrandList.Brand)localList.get(i)).initial));
          }
          AtYkBrandActivity.this.insertCommonBrandList();
          AtYkBrandActivity.this.mAdapter.notifyDataSetChanged();
        }
      });
    }
  }

  protected void onMenu()
  {
    if (this.iptvOrStb)
    {
      this.nomalItems.clear();
      this.showItems.clear();
      setTiTleTextRes(2131100458);
      getCityStbList();
      this.location.setVisibility(0);
      this.iptvOrStb = false;
      return;
    }
    finish();
  }

  public void seletedLoaction(View paramView)
  {
    this.nullareas.add(new PickerViewData(""));
    this.cityNullAreas.add(this.nullareas);
    this.city4Json = ((Root)new Gson().fromJson(FileUtil.readAssets(this, "city.txt"), Root.class));
    this.pvOptions = new OptionsPickerView(this);
    for (int i = 0; i < this.city4Json.getCitylist().size(); i++)
    {
      this.options1Items.add(new ProvinceBean(i, ((Citylist)this.city4Json.getCitylist().get(i)).getP(), "", ""));
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      int j = 0;
      if (j < ((Citylist)this.city4Json.getCitylist().get(i)).getC().size())
      {
        localArrayList1.add(((C)((Citylist)this.city4Json.getCitylist().get(i)).getC().get(j)).getN());
        ArrayList localArrayList3 = new ArrayList();
        for (int k = 0; k < ((C)((Citylist)this.city4Json.getCitylist().get(i)).getC().get(j)).getA().size(); k++)
          localArrayList3.add(new PickerViewData(((A)((C)((Citylist)this.city4Json.getCitylist().get(i)).getC().get(j)).getA().get(k)).getS()));
        if (localArrayList3.size() > 0)
          localArrayList2.add(localArrayList3);
        while (true)
        {
          j++;
          break;
          localArrayList2.add(this.nullareas);
        }
      }
      this.options2Items.add(localArrayList1);
      this.options3Items.add(localArrayList2);
    }
    this.pvOptions.setPicker(this.options1Items, this.options2Items, this.options3Items, true);
    this.pvOptions.setTitle("选择城市");
    this.pvOptions.setCyclic(false, false, false);
    this.pvOptions.setSelectOptions(0, 0, 0);
    this.pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener()
    {
      public void onOptionsSelect(int paramInt1, int paramInt2, int paramInt3)
      {
        String str1 = ((ProvinceBean)AtYkBrandActivity.this.options1Items.get(paramInt1)).getPickerViewText();
        String str2 = (String)((ArrayList)AtYkBrandActivity.this.options2Items.get(paramInt1)).get(paramInt2);
        String str3 = ((IPickerViewData)((ArrayList)((ArrayList)AtYkBrandActivity.this.options3Items.get(paramInt1)).get(paramInt2)).get(paramInt3)).getPickerViewText();
        String str4;
        if ((str1.equals("北京市") | str1.equals("天津市") | str1.equals("上海市") | str1.equals("重庆市")))
        {
          str3 = str2;
          str4 = str1;
          AtYkBrandActivity.this.location.setText(str4 + str3);
        }
        while (true)
        {
          KookongSDK.getAreaId(str1, str4, str3, new IRequestResult()
          {
            public void onFail(Integer paramInteger, String paramString)
            {
            }

            public void onSuccess(String paramString, Integer paramInteger)
            {
              AtYkBrandActivity.this.locationAndWeatherBusiness.stopLocat();
              AtYkBrandActivity.access$302(AtYkBrandActivity.this, paramInteger.intValue());
              KookongSDK.getOperaters(paramInteger.intValue(), new IRequestResult()
              {
                public void onFail(Integer paramInteger, String paramString)
                {
                  if (paramInteger.intValue() == 6)
                    Toast.makeText(AtYkBrandActivity.this, "一部手机只能获取7个城市", 0).show();
                }

                public void onSuccess(String paramString, SpList paramSpList)
                {
                  AtYkBrandActivity.this.showItems.clear();
                  List localList = paramSpList.spList;
                  for (int i = 0; i < localList.size(); i++)
                    AtYkBrandActivity.this.showItems.add(i, new AdapterPYinItem(((SpList.Sp)localList.get(i)).spName, ((SpList.Sp)localList.get(i)).spName, ((SpList.Sp)localList.get(i)).spId, "A"));
                  if (localList.size() > 0)
                    ((AdapterPYinItem)AtYkBrandActivity.this.showItems.get(0)).setmPyinUpper(AtYkBrandActivity.this.getResources().getString(2131099967));
                  AtYkBrandActivity.this.mAdapter.notifyDataSetChanged();
                }
              });
            }
          });
          return;
          if (str1.indexOf("区") == -1)
            str1 = str1 + "省";
          str4 = str2 + "市";
          AtYkBrandActivity.this.location.setText(str1 + str4 + str3);
        }
      }
    });
    this.pvOptions.show();
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setTiTleTextRes(2131100535);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkBrandActivity
 * JD-Core Version:    0.6.0
 */