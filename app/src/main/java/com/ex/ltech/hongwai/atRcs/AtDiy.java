package com.ex.ltech.hongwai.atRcs;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.AtBaseYk;
import com.ex.ltech.hongwai.view.ImageTextBigButton;
import com.ex.ltech.hongwai.vo.DiyKey;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.MyAlertDialog12;
import com.ex.ltech.led.my_view.MyAlertDialog12.MyOnClickListener;
import com.ex.ltech.led.my_view.MyAlertDialog9;
import com.ex.ltech.led.my_view.MyAlertDialog9.MyOnClickListener;
import com.ex.ltech.led.utils.BitmapUtils;
import java.util.ArrayList;
import java.util.Map;

public class AtDiy extends AtBaseYk
{
  ItNDiyAdapter adapter;
  int editBtPosi;
  int existRcPosi;

  @Bind({2131558819})
  GridView gvDiy;
  boolean isDelDiyKeyOk = false;
  boolean isDiyReLearnBtn;
  boolean isEditDiyKeyOk = true;
  private String opRcType;
  private MyRcDevices rcDevices;

  @Bind({2131558934})
  TextView tips;

  public void back()
  {
    if (this.isDelDiyKeyOk)
    {
      for (int k = 0; k < ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.size(); k++)
      {
        ((DiyKey)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.get(k)).setEdit(false);
        ((DiyKey)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.get(k)).setShowDel(false);
      }
      MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
      setResult(10000);
      finish();
      return;
    }
    if (!this.isEditDiyKeyOk)
    {
      for (int j = 0; j < ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.size(); j++)
      {
        ((DiyKey)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.get(j)).setEdit(false);
        ((DiyKey)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.get(j)).setShowDel(false);
      }
      this.adapter.notifyDataSetChanged();
      showSettingBtn();
      setTitleText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName, -16777216);
      this.isEditDiyKeyOk = true;
    }
    if ((this.opRcType.equals("OP_AT_TYPE_CREATE") | this.opRcType.equals("OP_AT_TYPE_EXIST")))
    {
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = getTitleTest();
      for (int i = 0; i < ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.size(); i++)
        ((DiyKey)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.get(i)).setEdit(false);
      MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
    }
    if (!this.isDiyReLearnBtn)
    {
      setResult(10000);
      finish();
      return;
    }
    this.isDiyReLearnBtn = false;
  }

  public void changeRcCode()
  {
  }

  public void last(int paramInt)
  {
  }

  public void next(int paramInt)
  {
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 100000)
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = getTitleTest();
    if (paramInt2 == 130000)
    {
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.add(-1 + ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.size(), (DiyKey)paramIntent.getSerializableExtra(DiyKey.class.getName()));
      this.adapter.notifyDataSetChanged();
      MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
    }
    if (paramInt2 == 140000)
    {
      ((DiyKey)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.get(this.editBtPosi)).setName(paramIntent.getStringExtra("BT_NAME_KEY"));
      this.adapter.notifyDataSetChanged();
      MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
    }
    if (paramInt2 == 150000)
    {
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.remove(this.editBtPosi);
      this.adapter.notifyDataSetChanged();
      MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
    }
    if (paramInt2 == 160000)
    {
      this.isEditDiyKeyOk = false;
      setTitleText(getString(2131100050), Color.parseColor("#000000"));
      setSettingText(2131100358, Color.parseColor("#f82525"));
      for (int j = 0; j < ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.size(); j++)
        ((DiyKey)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.get(j)).setEdit(true);
      this.adapter.notifyDataSetChanged();
    }
    if (paramInt2 == 170000)
    {
      this.isEditDiyKeyOk = false;
      setTitleText(getString(2131100323), Color.parseColor("#000000"));
      setSettingText(2131100358, Color.parseColor("#f82525"));
      this.adapter.notifyDataSetChanged();
      this.isDiyReLearnBtn = true;
    }
    if (paramInt2 == 120000)
    {
      this.isDelDiyKeyOk = true;
      this.isEditDiyKeyOk = false;
      setTitleText(getString(2131100027), Color.parseColor("#000000"));
      setSettingText("");
      for (int i = 0; i < ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.size(); i++)
        ((DiyKey)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.get(i)).setShowDel(true);
      this.adapter.notifyDataSetChanged();
    }
  }

  public void onBottomViewShow()
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968683);
    ButterKnife.bind(this);
    setTitleView();
    this.opRcType = getIntent().getStringExtra("OP_AT_TYPE_KEY");
    this.existRcPosi = getIntent().getIntExtra("OP_AT_POSI_KEY", -1);
    this.mRcPosi = this.existRcPosi;
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
    String str = this.opRcType;
    int i;
    switch (str.hashCode())
    {
    default:
      i = -1;
      label138: switch (i)
      {
      default:
      case 0:
      case 1:
      }
    case 1740066227:
    case -357480640:
    case -1351163454:
    }
    while (true)
    {
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mType = 9;
      setBackIc(2130903275);
      setSettingIc(2130903571);
      setTitleText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName, -16777216);
      this.adapter = new ItNDiyAdapter(this);
      this.gvDiy.setAdapter(this.adapter);
      hideBottomView();
      return;
      if (!str.equals("OP_AT_TYPE_CREATE"))
        break;
      i = 0;
      break label138;
      if (!str.equals("OP_AT_TYPE_EXIST"))
        break;
      i = 1;
      break label138;
      if (!str.equals("OP_RC_TYPE_TIME"))
        break;
      i = 2;
      break label138;
      this.rcDevices.myRcDevices.add(new MyRcDevice());
      DiyKey localDiyKey = new DiyKey();
      localDiyKey.setTextColor(Color.parseColor("#FFFC2032"));
      localDiyKey.setName(getString(2131099812));
      localDiyKey.setRes(0);
      localDiyKey.setIcData(BitmapUtils.Bitmap2Bytes(BitmapUtils.zoomOutBM(BitmapFactory.decodeResource(getResources(), 2130903535), 120.0D)));
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.add(localDiyKey);
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName = getResources().getString(2131100285);
      hideSetting();
      continue;
      this.tips.setVisibility(8);
    }
  }

  public void onLearnOk(String paramString, byte[] paramArrayOfByte)
  {
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).learnMap.put(paramString, paramArrayOfByte);
    MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
  }

  public void seleted(int paramInt)
  {
  }

  public void setting()
  {
    if (!this.isEditDiyKeyOk)
    {
      for (int i = 0; i < ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.size(); i++)
        ((DiyKey)((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).diyKeys.get(i)).setEdit(false);
      this.adapter.notifyDataSetChanged();
      showSettingBtn();
      setTitleText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName, -16777216);
      this.isEditDiyKeyOk = true;
    }
  }

  public class ItNDiyAdapter extends BaseAdapter
  {
    private Context context;
    private LayoutInflater layoutInflater;

    public ItNDiyAdapter(Context arg2)
    {
      Context localContext;
      this.context = localContext;
      this.layoutInflater = LayoutInflater.from(localContext);
    }

    private void initializeViews(DiyKey paramDiyKey, ViewHolder paramViewHolder, int paramInt)
    {
      paramViewHolder.btn.setBtnText(paramDiyKey.getName());
      int i;
      label46: int j;
      if (paramDiyKey.getTextColor() != -1)
      {
        paramViewHolder.btn.setBtnTextColor(paramDiyKey.getTextColor());
        Button localButton1 = paramViewHolder.btEdit;
        if (!paramDiyKey.isEdit())
          break label253;
        i = 0;
        localButton1.setVisibility(i);
        Button localButton2 = paramViewHolder.delkey;
        boolean bool = paramDiyKey.isShowDel();
        j = 0;
        if (!bool)
          break label260;
        label73: localButton2.setVisibility(j);
        paramViewHolder.btEdit.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            MyAlertDialog9 localMyAlertDialog9 = new MyAlertDialog9(AtDiy.this);
            localMyAlertDialog9.show();
            localMyAlertDialog9.getWindow().clearFlags(131080);
            localMyAlertDialog9.getWindow().setSoftInputMode(4);
            localMyAlertDialog9.setMyOnClickListener(new MyAlertDialog9.MyOnClickListener()
            {
              public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
              {
                ((DiyKey)((MyRcDevice)AtDiy.this.rcDevices.myRcDevices.get(AtDiy.this.existRcPosi)).diyKeys.get(AtDiy.ItNDiyAdapter.1.this.val$posi)).setName(paramString);
              }
            });
          }
        });
        paramViewHolder.delkey.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            MyAlertDialog12 localMyAlertDialog12 = new MyAlertDialog12(AtDiy.this);
            localMyAlertDialog12.show();
            localMyAlertDialog12.setMsg(2131100027);
            localMyAlertDialog12.setMyOnClickListener(new MyAlertDialog12.MyOnClickListener()
            {
              public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
              {
                ((MyRcDevice)AtDiy.this.rcDevices.myRcDevices.get(AtDiy.this.existRcPosi)).diyKeys.remove(AtDiy.ItNDiyAdapter.2.this.val$posi);
                AtDiy.ItNDiyAdapter.this.notifyDataSetChanged();
              }
            });
          }
        });
        switch (paramDiyKey.getRes())
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
        case 8:
        case 9:
        case 10:
        case 11:
        case 12:
        case 13:
        case 14:
        case 15:
        case 16:
        case 17:
        }
      }
      while (true)
      {
        paramViewHolder.btn.setOnClickListener(new View.OnClickListener(paramDiyKey)
        {
          public void onClick(View paramView)
          {
            if ((!AtDiy.this.isEditDiyKeyOk) && (AtDiy.this.getTitleTest().equalsIgnoreCase(AtDiy.this.getString(2131100323))))
            {
              AtDiy.this.showLearn(this.val$vo.getName());
              return;
            }
            if (this.val$vo.getRes() != 0)
            {
              if (this.val$vo.getKeyCode() != null)
              {
                AtDiy.this.sendLearnedCmd(this.val$vo.getKeyCode());
                return;
              }
              AtDiy.this.showLearn(this.val$vo.getName());
              return;
            }
            AtDiy.this.startActivityForResult(new Intent(AtDiy.this, AtDiyAdd.class), 0);
          }
        });
        paramViewHolder.btn.setOnLongClickListener(new View.OnLongClickListener(paramInt, paramDiyKey)
        {
          public boolean onLongClick(View paramView)
          {
            AtDiy.this.editBtPosi = this.val$posi;
            if (this.val$vo.getRes() != 0)
              AtDiy.this.startActivityForResult(new Intent(AtDiy.this, AtEditDiyBtn.class).putExtra("BT_NAME_KEY", this.val$vo.getName()), 0);
            return false;
          }
        });
        return;
        paramViewHolder.btn.setBtnTextColor(Color.parseColor("#717171"));
        break;
        label253: i = 8;
        break label46;
        label260: j = 8;
        break label73;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903535));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903536));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903544));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903545));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903546));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903547));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903548));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903549));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903550));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903551));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903537));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903538));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903539));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903540));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903541));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903542));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903543));
        continue;
        paramViewHolder.btn.setBtnBitmap(BitmapFactory.decodeResource(AtDiy.this.getResources(), 2130903223));
      }
    }

    public int getCount()
    {
      if (AtDiy.this.isEditDiyKeyOk)
        return ((MyRcDevice)AtDiy.this.rcDevices.myRcDevices.get(AtDiy.this.existRcPosi)).diyKeys.size();
      return -1 + ((MyRcDevice)AtDiy.this.rcDevices.myRcDevices.get(AtDiy.this.existRcPosi)).diyKeys.size();
    }

    public DiyKey getItem(int paramInt)
    {
      return (DiyKey)((MyRcDevice)AtDiy.this.rcDevices.myRcDevices.get(AtDiy.this.existRcPosi)).diyKeys.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968806, null);
        paramView.setTag(new ViewHolder(paramView));
      }
      initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag(), paramInt);
      return paramView;
    }

    protected class ViewHolder
    {
      private Button btEdit;
      private ImageTextBigButton btn;
      private Button delkey;

      public ViewHolder(View arg2)
      {
        Object localObject;
        this.btn = ((ImageTextBigButton)localObject.findViewById(2131559313));
        this.btEdit = ((Button)localObject.findViewById(2131559298));
        this.delkey = ((Button)localObject.findViewById(2131559314));
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.AtDiy
 * JD-Core Version:    0.6.0
 */