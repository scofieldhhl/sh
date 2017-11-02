package com.ex.ltech.remote.control.yaokong;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.T;
import com.ex.ltech.led.my_view.MyAlertDialog4;
import com.ex.ltech.led.my_view.MyAlertDialog4.MyOnClickListener;
import com.ex.ltech.led.my_view.MyEditAlertDialog2;
import com.ex.ltech.led.my_view.MyEditAlertDialog2.MyOnClickListener;
import com.ex.ltech.remote.control.YkAt;
import com.ex.ltech.remote.control.atYaoKongs.AtAirConActivity;
import com.ex.ltech.remote.control.atYaoKongs.AtDvdActivity;
import com.ex.ltech.remote.control.atYaoKongs.AtFanActivity;
import com.ex.ltech.remote.control.atYaoKongs.AtNewTvActivity;
import com.ex.ltech.remote.control.atYaoKongs.AtProjectionActivity;
import com.google.gson.Gson;
import et.song.db.ETDB;
import et.song.etclass.ETDevice;
import et.song.etclass.ETGroup;
import et.song.etclass.ETPage;
import java.util.ArrayList;
import java.util.List;

public class AtYaokongActivity extends YkAt
{
  private ItRcMainAdapter adapter;
  ETGroup group;
  private GridView gvActYk;
  private boolean isEdit;
  boolean isLongClickNow;
  int[] mDeviceImages = { 2130903790, 2130903401, 2130903792, 2130903228, 2130903254, 2130903636, 2130903636, 2130903055, 2130903406, 2130903406, 2130903636, 2130903636 };
  TextView title;

  public void add(View paramView)
  {
    Intent localIntent = new Intent(this, AtYkTypeList.class);
    localIntent.putExtra("yaoKongAllCount", this.adapter.getCount());
    startActivityForResult(localIntent, 0);
  }

  public void back(View paramView)
  {
    finish();
  }

  public void edit(View paramView)
  {
    if (!this.isEdit);
    for (boolean bool = true; ; bool = false)
    {
      this.isEdit = bool;
      this.adapter.notifyDataSetChanged();
      return;
    }
  }

  protected void init()
  {
    this.title = ((TextView)findViewById(2131558469));
    this.adapter = new ItRcMainAdapter(this);
    this.gvActYk = ((GridView)findViewById(2131558962));
    this.gvActYk.setAdapter(this.adapter);
    this.group = ((ETGroup)ETPage.getInstance(this).GetItem(0));
    this.gvActYk.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        AtYaokongActivity.this.isLongClickNow = true;
        new AlertDialog.Builder(AtYaokongActivity.this).setMessage(2131100016).setPositiveButton(2131100400, new DialogInterface.OnClickListener(paramInt)
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            ((ETDevice)AtYaokongActivity.this.group.GetItem(this.val$i)).Delete(ETDB.getInstance(AtYaokongActivity.this.getApplicationContext()));
            AtYaokongActivity.this.group.Load(ETDB.getInstance(AtYaokongActivity.this));
            AtYaokongActivity.this.adapter.notifyDataSetChanged();
          }
        }).setNegativeButton(2131100208, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            paramDialogInterface.dismiss();
          }
        }).create().show();
        return false;
      }
    });
    this.gvActYk.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        if (AtYaokongActivity.this.isLongClickNow)
        {
          AtYaokongActivity.this.isLongClickNow = false;
          return;
        }
        switch (((ETDevice)AtYaokongActivity.this.group.GetItem(paramInt)).GetRes())
        {
        case 6:
        case 8:
        default:
          return;
        case 0:
          Intent localIntent8 = new Intent(AtYaokongActivity.this, AtNewTvActivity.class);
          localIntent8.putExtra("index", paramInt);
          localIntent8.putExtra("tvType", "tv");
          AtYaokongActivity.this.startActivity(localIntent8);
          return;
        case 1:
          Intent localIntent7 = new Intent(AtYaokongActivity.this, AtNewTvActivity.class);
          localIntent7.putExtra("index", paramInt);
          localIntent7.putExtra("tvType", "iptv");
          AtYaokongActivity.this.startActivity(localIntent7);
          return;
        case 2:
          Intent localIntent6 = new Intent(AtYaokongActivity.this, AtNewTvActivity.class);
          localIntent6.putExtra("index", paramInt);
          localIntent6.putExtra("tvType", "tvbox");
          AtYaokongActivity.this.startActivity(localIntent6);
          return;
        case 3:
          Intent localIntent5 = new Intent(AtYaokongActivity.this, AtDvdActivity.class);
          localIntent5.putExtra("index", paramInt);
          AtYaokongActivity.this.startActivity(localIntent5);
          return;
        case 4:
          Intent localIntent4 = new Intent(AtYaokongActivity.this, AtFanActivity.class);
          localIntent4.putExtra("index", paramInt);
          AtYaokongActivity.this.startActivity(localIntent4);
          return;
        case 5:
          Intent localIntent3 = new Intent(AtYaokongActivity.this, AtProjectionActivity.class);
          localIntent3.putExtra("index", paramInt);
          AtYaokongActivity.this.startActivity(localIntent3);
          return;
        case 7:
          Intent localIntent2 = new Intent(AtYaokongActivity.this, AtAirConActivity.class);
          localIntent2.putExtra("index", paramInt);
          AtYaokongActivity.this.startActivity(localIntent2);
          return;
        case 9:
        }
        Intent localIntent1 = new Intent(AtYaokongActivity.this, AtRcDiyActivity.class);
        localIntent1.putExtra("index", paramInt);
        AtYaokongActivity.this.startActivity(localIntent1);
      }
    });
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == this.saveYkOk);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968710);
    init();
  }

  protected void onResume()
  {
    super.onResume();
    this.group.Load(ETDB.getInstance(this));
    ETPage localETPage = ETPage.getInstance(this);
    localETPage.Load(ETDB.getInstance(this));
    new Gson().toJson(localETPage.GetItem(0)).getBytes();
    this.adapter.notifyDataSetChanged();
  }

  public class ItRcMainAdapter extends BaseAdapter
  {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<T> objects = new ArrayList();

    public ItRcMainAdapter(Context arg2)
    {
      Context localContext;
      this.context = localContext;
      ETPage.getInstance(this.context).Load(ETDB.getInstance(this.context));
      this.layoutInflater = LayoutInflater.from(localContext);
    }

    private void initializeViews(ETDevice paramETDevice, ViewHolder paramViewHolder, int paramInt)
    {
      paramViewHolder.ic.setImageResource(AtYaokongActivity.this.mDeviceImages[paramETDevice.GetRes()]);
      paramViewHolder.name.setText(paramETDevice.GetName());
      ImageView localImageView = paramViewHolder.ed;
      if (AtYaokongActivity.this.isEdit);
      for (int i = 0; ; i = 8)
      {
        localImageView.setVisibility(i);
        paramViewHolder.ed.setOnClickListener(new View.OnClickListener(paramETDevice, paramInt)
        {
          public void onClick(View paramView)
          {
            MyAlertDialog4 localMyAlertDialog4 = new MyAlertDialog4(AtYaokongActivity.this);
            localMyAlertDialog4.show();
            localMyAlertDialog4.setMyOnClickListener(new MyAlertDialog4.MyOnClickListener()
            {
              public void onDel()
              {
                ((ETDevice)AtYaokongActivity.this.group.GetItem(AtYaokongActivity.ItRcMainAdapter.1.this.val$position)).Delete(ETDB.getInstance(AtYaokongActivity.this.getApplicationContext()));
                AtYaokongActivity.this.group.Load(ETDB.getInstance(AtYaokongActivity.this));
                AtYaokongActivity.this.adapter.notifyDataSetChanged();
              }

              public void onRename()
              {
                MyEditAlertDialog2 localMyEditAlertDialog2 = new MyEditAlertDialog2(AtYaokongActivity.this);
                localMyEditAlertDialog2.show();
                localMyEditAlertDialog2.setHintContent(AtYaokongActivity.ItRcMainAdapter.1.this.val$device.GetName());
                localMyEditAlertDialog2.getWindow().clearFlags(131080);
                localMyEditAlertDialog2.getWindow().setSoftInputMode(4);
                localMyEditAlertDialog2.setMyOnClickListener(new MyEditAlertDialog2.MyOnClickListener()
                {
                  public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
                  {
                    AtYaokongActivity.ItRcMainAdapter.1.this.val$device.SetName(paramString);
                    AtYaokongActivity.ItRcMainAdapter.1.this.val$device.Update(ETDB.getInstance(AtYaokongActivity.ItRcMainAdapter.this.context));
                    AtYaokongActivity.this.group.Load(ETDB.getInstance(AtYaokongActivity.this));
                    AtYaokongActivity.ItRcMainAdapter.this.notifyDataSetChanged();
                    AtYaokongActivity.this.edit(null);
                  }
                });
              }
            });
          }
        });
        return;
      }
    }

    public int getCount()
    {
      try
      {
        int i = AtYaokongActivity.this.group.GetCount();
        return i - 1;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return 0;
    }

    public Object getItem(int paramInt)
    {
      return AtYaokongActivity.this.group.GetItem(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968811, null);
        ViewHolder localViewHolder = new ViewHolder();
        ViewHolder.access$102(localViewHolder, (ImageView)paramView.findViewById(2131558883));
        ViewHolder.access$202(localViewHolder, (TextView)paramView.findViewById(2131559008));
        ViewHolder.access$302(localViewHolder, (ImageView)paramView.findViewById(2131559352));
        paramView.setTag(localViewHolder);
      }
      initializeViews((ETDevice)getItem(paramInt), (ViewHolder)paramView.getTag(), paramInt);
      return paramView;
    }

    protected class ViewHolder
    {
      private ImageView ed;
      private ImageView ic;
      private TextView name;

      protected ViewHolder()
      {
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.AtYaokongActivity
 * JD-Core Version:    0.6.0
 */