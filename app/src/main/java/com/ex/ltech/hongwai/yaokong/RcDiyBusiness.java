package com.ex.ltech.hongwai.yaokong;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.ex.ltech.led.BaseBusiness;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.my_view.MyEditAlertDialog2;
import com.ex.ltech.led.my_view.MyEditAlertDialog2.MyOnClickListener;
import et.song.db.ETDB;
import et.song.etclass.ETDevice;
import et.song.etclass.ETDeviceCustom;
import et.song.etclass.ETGroup;
import et.song.etclass.ETKey;
import et.song.etclass.ETPage;
import et.song.tool.ETTool;
import java.util.ArrayList;
import java.util.List;

public class RcDiyBusiness extends BaseBusiness
{
  public int[] butRes = { 2130903179, 2130903186, 2130903180, 2130903178, 2130903182, 2130903185, 2130903184, 2130903225, 2130903181, 2130903259, 2130903642, 2130903224, 2130903233, 2130903230, 2130903226, 2130903236, 2130903235, 2130903238, 2130903219, 2130903220, 2130903221 };
  CmdDateBussiness cmd;
  Context ctx;
  boolean isCancelLearn;
  public ETDevice mDevice = null;
  private int mDeviceIndex = -1;
  private ETGroup mGroup = null;
  public final int noSeletecBtStatus = -1;
  public final int seletecBtStatus = 0;
  public ArrayList<Integer> seletedBtnRes = new ArrayList();

  public RcDiyBusiness(Context paramContext)
  {
    super(paramContext);
    this.ctx = paramContext;
    this.cmd = new CmdDateBussiness(paramContext, "0000");
    ETPage.getInstance(paramContext).Load(ETDB.getInstance(paramContext));
    this.mGroup = ((ETGroup)ETPage.getInstance(paramContext).GetItem(0));
    this.mGroup.Load(ETDB.getInstance(this.ctx));
    this.mGroup.SetType(et.song.global.ETGlobal.mGroupTypes[0]);
    this.mGroup.SetRes(0);
    this.mGroup.SetID(1);
  }

  private void addEtKey2Device()
  {
  }

  public void delKey(ETKey paramETKey)
  {
    paramETKey.Delete(ETDB.getInstance(this.ctx));
    this.mDevice.Load(ETDB.getInstance(this.ctx));
  }

  public void handleData()
  {
    int i = this.mDevice.GetCount();
    this.mDevice.getmKeyList();
    if (this.mDevice.GetKeyByIndex(i - 1).GetState() == 6)
      this.mDevice.reomveKeyListItem(-1 + this.mDevice.GetCount());
    if (this.mDevice.GetKeyByIndex(i - 2).GetState() == 6)
      this.mDevice.reomveKeyListItem(-1 + this.mDevice.GetCount());
    int j = 0;
    while (true)
      if (j < this.seletedBtnRes.size())
      {
        ETKey localETKey1 = new ETKey();
        localETKey1.SetKey(j + 6);
        localETKey1.SetPos(5.0F, 5.0F);
        localETKey1.SetRes(((Integer)this.seletedBtnRes.get(j)).intValue());
        if ((localETKey1.GetRes() == 2130903219) || (localETKey1.GetRes() == 2130903220) || (localETKey1.GetRes() == 2130903221))
        {
          localETKey1.SetName(this.ctx.getString(2131099887));
          localETKey1.SetBrandIndex(-1);
          localETKey1.SetBrandPos(-1);
          localETKey1.SetRow(-1);
          localETKey1.SetState(4);
        }
        try
        {
          localETKey1.SetValue(ETTool.HexStringToBytes(""));
          this.mDevice.SetKey(localETKey1);
          j++;
          continue;
          localETKey1.SetName("");
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    int k = this.mDevice.GetCount();
    if (k % 3 == 1)
    {
      ETKey localETKey2 = new ETKey();
      localETKey2.SetKey(-1);
      localETKey2.SetPos(5.0F, 5.0F);
      localETKey2.SetRes(-1);
      localETKey2.SetName("");
      localETKey2.SetBrandIndex(-1);
      localETKey2.SetBrandPos(-1);
      localETKey2.SetRow(-1);
      localETKey2.SetState(6);
      this.mDevice.SetKey(localETKey2);
      this.mDevice.SetKey(localETKey2);
    }
    if (k % 3 == 2)
    {
      ETKey localETKey3 = new ETKey();
      localETKey3.SetKey(-1);
      localETKey3.SetPos(5.0F, 5.0F);
      localETKey3.SetRes(-1);
      localETKey3.SetName("");
      localETKey3.SetBrandIndex(-1);
      localETKey3.SetBrandPos(-1);
      localETKey3.SetRow(-1);
      localETKey3.SetState(6);
      this.mDevice.SetKey(localETKey3);
    }
  }

  public void initAtAddBtnData()
  {
    this.mDevice = new ETDeviceCustom();
    this.mDevice.SetGID(1);
    this.mDevice.SetType(-33554432);
    this.mDevice.SetRes(9);
    this.mDevice.SetName("DIY");
    int i = 6;
    while (true)
      if (i < this.butRes.length)
      {
        ETKey localETKey = new ETKey();
        localETKey.SetKey(i);
        localETKey.SetPos(5.0F, 5.0F);
        localETKey.SetRes(this.butRes[i]);
        localETKey.SetId(i);
        localETKey.SetName("");
        if (i > 17)
          localETKey.SetName(this.ctx.getString(2131099887));
        localETKey.SetBrandIndex(-1);
        localETKey.SetBrandPos(-1);
        localETKey.SetRow(-1);
        localETKey.SetState(4);
        try
        {
          localETKey.SetValue(ETTool.HexStringToBytes(""));
          this.mDevice.SetKey(localETKey);
          i++;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
  }

  public void initData(int paramInt)
  {
    this.mDeviceIndex = paramInt;
    if (paramInt == -1)
    {
      this.mDevice = new ETDeviceCustom();
      this.mDevice.SetGID(this.mGroup.GetID());
      this.mDevice.SetType(-33554432);
      this.mDevice.SetRes(9);
      this.mDevice.SetName("DIY" + this.mGroup.GetCount());
      int i = 0;
      while (true)
        if (i < 6)
        {
          ETKey localETKey = new ETKey();
          localETKey.SetKey(i);
          localETKey.SetPos(5.0F, 5.0F);
          localETKey.SetRes(this.butRes[i]);
          localETKey.SetName("");
          localETKey.SetBrandIndex(-1);
          localETKey.SetBrandPos(-1);
          localETKey.SetRow(-1);
          localETKey.SetState(4);
          try
          {
            localETKey.SetValue(ETTool.HexStringToBytes(""));
            this.mDevice.SetKey(localETKey);
            i++;
          }
          catch (Exception localException)
          {
            while (true)
              localException.printStackTrace();
          }
        }
      this.mDevice.Inster(ETDB.getInstance(this.ctx));
    }
    while (true)
    {
      if (this.mDevice.GetCount() == 0);
      return;
      this.mDevice = ((ETDeviceCustom)this.mGroup.GetItem(paramInt));
    }
  }

  public void onAtPressBack()
  {
    ArrayList localArrayList = new ArrayList();
    this.mGroup.Load(ETDB.getInstance(this.ctx));
    localArrayList.addAll(this.mGroup.getmDeviceList());
    localArrayList.remove(-1 + localArrayList.size());
    this.mGroup.Delete(ETDB.getInstance(this.ctx));
    this.mGroup.Load(ETDB.getInstance(this.ctx));
    int i;
    if (this.mDeviceIndex == -1)
    {
      i = -1 + localArrayList.size();
      if (i == -1)
        break label214;
      localArrayList.remove(i);
      localArrayList.add(i, this.mDevice);
    }
    while (true)
    {
      this.mGroup.addAllDevices(localArrayList);
      this.mGroup.Inster(ETDB.getInstance(this.ctx));
      int j = this.mGroup.GetCount();
      this.mGroup.Load(ETDB.getInstance(this.ctx));
      if (this.mGroup.GetCount() - j == 2)
        ((ETDevice)this.mGroup.GetItem(0)).Delete(ETDB.getInstance(this.ctx));
      return;
      i = this.mDeviceIndex;
      break;
      label214: localArrayList.add(this.mDevice);
    }
  }

  public void renameKey(ETKey paramETKey)
  {
    int i = 1;
    int j;
    int k;
    label25: int m;
    if (paramETKey.GetRes() == 2130903219)
    {
      j = i;
      if (paramETKey.GetRes() != 2130903220)
        break label114;
      k = i;
      m = j | k;
      if (paramETKey.GetRes() != 2130903221)
        break label120;
    }
    while (true)
    {
      if ((m | i) == 0)
        break label125;
      MyEditAlertDialog2 localMyEditAlertDialog2 = new MyEditAlertDialog2(this.ctx);
      localMyEditAlertDialog2.show();
      localMyEditAlertDialog2.setHintContent(paramETKey.GetName());
      localMyEditAlertDialog2.getWindow().clearFlags(131080);
      localMyEditAlertDialog2.getWindow().setSoftInputMode(4);
      localMyEditAlertDialog2.setMyOnClickListener(new MyEditAlertDialog2.MyOnClickListener(paramETKey)
      {
        public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
        {
          this.val$k.SetName(paramString);
          this.val$k.Update(ETDB.getInstance(RcDiyBusiness.this.ctx));
        }
      });
      return;
      j = 0;
      break;
      label114: k = 0;
      break label25;
      label120: i = 0;
    }
    label125: Toast.makeText(this.ctx, 2131100217, 0).show();
  }

  public void upDateEtKey(int paramInt, byte[] paramArrayOfByte)
  {
    ETKey localETKey = this.mDevice.GetKeyByIndex(paramInt);
    if (localETKey != null)
    {
      localETKey.SetState(1);
      localETKey.SetValue(paramArrayOfByte);
      localETKey.Update(ETDB.getInstance(this.ctx));
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.RcDiyBusiness
 * JD-Core Version:    0.6.0
 */