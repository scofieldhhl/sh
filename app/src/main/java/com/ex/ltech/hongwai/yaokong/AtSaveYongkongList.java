package com.ex.ltech.hongwai.yaokong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.atRcs.AtAirConActivity;
import com.ex.ltech.hongwai.atRcs.AtFan;
import com.ex.ltech.hongwai.scene.AtAirRcSetActivity;
import com.ex.ltech.hongwai.scene.AtSimpleRcSetActivity;
import com.ex.ltech.hongwai.time.TimingBussines;
import com.ex.ltech.hongwai.vo.Ct1SceneVo;
import com.ex.ltech.hongwai.vo.Ct1ScenesVo;
import com.ex.ltech.hongwai.vo.CtTimingAction;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.MyAlertDialog17;
import com.ex.ltech.led.my_view.MyAlertDialog17.OnListener;
import com.ex.ltech.led.my_view.MyAlertDialog6;
import com.ex.ltech.led.my_view.MyAlertDialog6.MyOnClickListener;
import java.util.ArrayList;

public class AtSaveYongkongList extends MyBaseActivity
{
  private ItRcMainAdapter adapter;
  int clickLvPosi = 0;
  Intent in = null;
  private boolean isEdit;
  boolean isLongClickNow;
  private ListView lvActYk;
  int[] mDeviceImages = { 2130903636, 2130903572, 2130903574, 2130903526, 2130903228, 2130903523, 2130903564, 2130903564, 2130903555, 2130903534, 2130903369, 2130903372, 2130903295, 2130903564 };
  private MyRcDevices rcDevices;
  TextView title;

  public void back(View paramView)
  {
    finish();
  }

  protected void init()
  {
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
    this.title = ((TextView)findViewById(2131558469));
    this.adapter = new ItRcMainAdapter(this);
    this.lvActYk = ((ListView)findViewById(2131558952));
    this.lvActYk.setAdapter(this.adapter);
    this.lvActYk.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        AtSaveYongkongList.this.clickLvPosi = paramInt;
        if (AtSaveYongkongList.this.isLongClickNow)
        {
          AtSaveYongkongList.this.isLongClickNow = false;
          return;
        }
        int i = ((MyRcDevice)AtSaveYongkongList.this.rcDevices.myRcDevices.get(paramInt)).mType;
        if (AtSaveYongkongList.this.getIntent().getStringExtra("OP_AT_TYPE_KEY").equals("OP_RC_TYPE_TIME"))
        {
          if (i == 5)
          {
            Intent localIntent1 = new Intent(AtSaveYongkongList.this, AtAirConActivity.class);
            localIntent1.putExtra("OP_AT_TYPE_KEY", AtSaveYongkongList.this.getIntent().getStringExtra("OP_AT_TYPE_KEY"));
            localIntent1.putExtra("OP_AT_POSI_KEY", paramInt);
            AtSaveYongkongList.this.startActivityForResult(localIntent1, 0);
            return;
          }
          if (i == 8)
          {
            Intent localIntent2 = new Intent(AtSaveYongkongList.this, AtFan.class);
            localIntent2.putExtra("OP_AT_TYPE_KEY", AtSaveYongkongList.this.getIntent().getStringExtra("OP_AT_TYPE_KEY"));
            localIntent2.putExtra("OP_AT_POSI_KEY", paramInt);
            AtSaveYongkongList.this.startActivityForResult(localIntent2, 0);
            return;
          }
          if (i == 9)
          {
            Toast.makeText(AtSaveYongkongList.this, 2131099941, 0).show();
            return;
          }
          int j;
          int k;
          label233: int m;
          if (i == 10)
          {
            j = 1;
            if (i != 11)
              break label339;
            k = 1;
            m = j | k;
            if (i != 12)
              break label345;
          }
          label339: label345: for (int n = 1; ; n = 0)
          {
            if ((n | m) == 0)
              break label351;
            Intent localIntent3 = new Intent();
            localIntent3.putExtra("RC_NAME_KEY", ((MyRcDevice)AtSaveYongkongList.this.rcDevices.myRcDevices.get(paramInt)).mName);
            localIntent3.putExtra("RC_TYPE_KEY", i);
            localIntent3.putExtra("OP_RC_POSI_KEY", paramInt);
            AtSaveYongkongList.this.setResult(240000, localIntent3);
            AtSaveYongkongList.this.finish();
            return;
            j = 0;
            break;
            k = 0;
            break label233;
          }
          label351: Intent localIntent4 = new Intent();
          localIntent4.putExtra("RC_NAME_KEY", ((MyRcDevice)AtSaveYongkongList.this.rcDevices.myRcDevices.get(paramInt)).mName);
          localIntent4.putExtra("RC_TYPE_KEY", i);
          localIntent4.putExtra("OP_RC_POSI_KEY", paramInt);
          AtSaveYongkongList.this.setResult(70000, localIntent4);
          AtSaveYongkongList.this.finish();
          return;
        }
        if (AtSaveYongkongList.this.getIntent().getStringExtra("OP_AT_TYPE_KEY").equals("OP_AT_TYPE_BIND"))
        {
          if (i != 2)
          {
            Toast.makeText(AtSaveYongkongList.this, 2131099948, 0).show();
            return;
          }
          AtSaveYongkongList.this.setResult(120000, new Intent().putExtra("BIND_RC_POSI", paramInt));
          AtSaveYongkongList.this.finish();
          return;
        }
        if (i == 9)
        {
          Toast.makeText(AtSaveYongkongList.this, 2131099941, 0).show();
          return;
        }
        if ((i == 2) || (i == 1))
        {
          MyAlertDialog6 localMyAlertDialog6 = new MyAlertDialog6(AtSaveYongkongList.this);
          localMyAlertDialog6.show();
          localMyAlertDialog6.getWindow().setGravity(80);
          localMyAlertDialog6.setMyOnClickListener(new MyAlertDialog6.MyOnClickListener(i, paramInt)
          {
            public void onChanel()
            {
              AtSaveYongkongList.this.in = new Intent(AtSaveYongkongList.this, AtSimpleRcSetActivity.class);
              AtSaveYongkongList.this.in.putExtra("OP_SCENE_IN_TV_TYPE_KEY", "OP_SCENE_IN_TV_TYPE_CHANNEL");
              AtSaveYongkongList.this.in.putExtra("RC_TYPE_KEY", this.val$mType);
              AtSaveYongkongList.this.in.putExtra("OP_RC_POSI_KEY", this.val$i);
              AtSaveYongkongList.this.in.putExtra("RC_NAME_KEY", AtSaveYongkongList.this.adapter.getItem(this.val$i).mName);
              AtSaveYongkongList.this.in.putExtra("OP_AT_TYPE_KEY", AtSaveYongkongList.this.getIntent().getStringExtra("OP_AT_TYPE_KEY"));
              AtSaveYongkongList.this.in.putExtra("OP_SCENE_IN_RC_POSI_KEY", AtSaveYongkongList.this.getIntent().getIntExtra("OP_SCENE_IN_RC_POSI_KEY", 0));
              AtSaveYongkongList.this.startActivityForResult(AtSaveYongkongList.this.in, 0);
            }

            public void onOnOff()
            {
              AtSaveYongkongList.this.in = new Intent(AtSaveYongkongList.this, AtSimpleRcSetActivity.class);
              AtSaveYongkongList.this.in.putExtra("OP_SCENE_IN_TV_TYPE_KEY", "OP_SCENE_IN_TV_TYPE_ON_OFF");
              AtSaveYongkongList.this.in.putExtra("RC_TYPE_KEY", this.val$mType);
              AtSaveYongkongList.this.in.putExtra("OP_RC_POSI_KEY", this.val$i);
              AtSaveYongkongList.this.in.putExtra("RC_NAME_KEY", AtSaveYongkongList.this.adapter.getItem(this.val$i).mName);
              AtSaveYongkongList.this.in.putExtra("OP_AT_TYPE_KEY", AtSaveYongkongList.this.getIntent().getStringExtra("OP_AT_TYPE_KEY"));
              AtSaveYongkongList.this.in.putExtra("OP_SCENE_IN_RC_POSI_KEY", AtSaveYongkongList.this.getIntent().getIntExtra("OP_SCENE_IN_RC_POSI_KEY", 0));
              AtSaveYongkongList.this.startActivityForResult(AtSaveYongkongList.this.in, 0);
            }
          });
          return;
        }
        switch (i)
        {
        case 6:
        case 7:
        case 8:
        case 9:
        default:
          AtSaveYongkongList.this.in = new Intent(AtSaveYongkongList.this, AtSimpleRcSetActivity.class);
        case 5:
        case 10:
        case 11:
          while (true)
          {
            AtSaveYongkongList.this.in.putExtra("RC_TYPE_KEY", i);
            AtSaveYongkongList.this.in.putExtra("OP_RC_POSI_KEY", paramInt);
            AtSaveYongkongList.this.in.putExtra("RC_NAME_KEY", AtSaveYongkongList.this.adapter.getItem(paramInt).mName);
            AtSaveYongkongList.this.in.putExtra("OP_AT_TYPE_KEY", AtSaveYongkongList.this.getIntent().getStringExtra("OP_AT_TYPE_KEY"));
            AtSaveYongkongList.this.in.putExtra("OP_SCENE_IN_RC_POSI_KEY", AtSaveYongkongList.this.getIntent().getIntExtra("OP_SCENE_IN_RC_POSI_KEY", 0));
            AtSaveYongkongList.this.startActivityForResult(AtSaveYongkongList.this.in, 0);
            return;
            AtSaveYongkongList.this.in = new Intent(AtSaveYongkongList.this, AtAirRcSetActivity.class);
            continue;
            AtSaveYongkongList.this.in = new Intent(AtSaveYongkongList.this, AtYkSwitchSelectActivity.class);
          }
        case 12:
        }
        InnerRcVo localInnerRcVo = new InnerRcVo();
        localInnerRcVo.setName(((MyRcDevice)AtSaveYongkongList.this.rcDevices.myRcDevices.get(AtSaveYongkongList.this.clickLvPosi)).mName);
        localInnerRcVo.setTypeStr(AtSaveYongkongList.this.getString(2131100037));
        localInnerRcVo.setmType(12);
        localInnerRcVo.setmSaveRcListPosi(AtSaveYongkongList.this.clickLvPosi);
        localInnerRcVo.nonIrDevice = ((MyRcDevice)AtSaveYongkongList.this.rcDevices.myRcDevices.get(AtSaveYongkongList.this.clickLvPosi)).nonIrDevice;
        MyAlertDialog17 localMyAlertDialog17 = new MyAlertDialog17(AtSaveYongkongList.this);
        localMyAlertDialog17.setOnListener(new MyAlertDialog17.OnListener(localInnerRcVo)
        {
          public void cancel()
          {
          }

          public void effect()
          {
            AtSaveYongkongList.this.goAct4result(AtYkLightMode.class, 0);
          }

          public void off()
          {
            this.val$sceneInnerRcVo.nonIrDevice.irCt1Onoff = false;
            this.val$sceneInnerRcVo.setStatus(AtSaveYongkongList.this.getString(2131100226));
            Intent localIntent = new Intent();
            localIntent.putExtra(InnerRcVo.class.getName(), this.val$sceneInnerRcVo);
            AtSaveYongkongList.this.setResult(210000, localIntent);
            AtSaveYongkongList.this.finish();
          }

          public void on()
          {
            this.val$sceneInnerRcVo.nonIrDevice.irCt1Onoff = true;
            this.val$sceneInnerRcVo.setStatus(AtSaveYongkongList.this.getString(R.string.on));
            Intent localIntent = new Intent();
            localIntent.putExtra(InnerRcVo.class.getName(), this.val$sceneInnerRcVo);
            AtSaveYongkongList.this.setResult(210000, localIntent);
            AtSaveYongkongList.this.finish();
          }
        });
        localMyAlertDialog17.show();
      }
    });
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 20000)
    {
      setResult(20000, paramIntent);
      finish();
    }
    if (paramInt2 == 40000)
    {
      setResult(40000, paramIntent);
      finish();
    }
    if (paramInt2 == 50000)
    {
      setResult(50000, paramIntent);
      finish();
    }
    if (paramInt2 == 100000)
    {
      setResult(100000, paramIntent);
      finish();
    }
    if (paramInt2 == 220000)
    {
      setResult(220000, paramIntent);
      finish();
    }
    InnerRcVo localInnerRcVo;
    CtTimingAction localCtTimingAction;
    if (paramInt2 == 230000)
    {
      localInnerRcVo = new InnerRcVo();
      localInnerRcVo.nonIrDevice = ((MyRcDevice)this.rcDevices.myRcDevices.get(this.clickLvPosi)).nonIrDevice;
      localInnerRcVo.setName(((MyRcDevice)this.rcDevices.myRcDevices.get(this.clickLvPosi)).mName);
      localInnerRcVo.setTypeStr(getString(2131100037));
      localInnerRcVo.setmType(12);
      localInnerRcVo.setmSaveRcListPosi(this.clickLvPosi);
      localInnerRcVo.nonIrDevice.irCt1Onoff = true;
      localInnerRcVo.setStatus(getString(2131100054));
      localCtTimingAction = (CtTimingAction)paramIntent.getSerializableExtra(CtTimingAction.class.getName());
      switch (localCtTimingAction.seletedType)
      {
      default:
      case 1:
      case 2:
      }
    }
    while (true)
    {
      Intent localIntent = new Intent();
      localIntent.putExtra(InnerRcVo.class.getName(), localInnerRcVo);
      setResult(210000, localIntent);
      finish();
      return;
      Ct1ScenesVo localCt1ScenesVo = new TimingBussines(this).getCt1SceneVos();
      localInnerRcVo.nonIrDevice.irCt1Brt = ((Ct1SceneVo)localCt1ScenesVo.ct1SceneVos.get(localCtTimingAction.scenePosi)).irCt1Brt;
      localInnerRcVo.nonIrDevice.irCt1C = ((Ct1SceneVo)localCt1ScenesVo.ct1SceneVos.get(localCtTimingAction.scenePosi)).irCt1C;
      localInnerRcVo.nonIrDevice.irCt1W = ((Ct1SceneVo)localCt1ScenesVo.ct1SceneVos.get(localCtTimingAction.scenePosi)).irCt1W;
      continue;
      localInnerRcVo.nonIrDevice.irCt1Brt = localCtTimingAction.brt;
      localInnerRcVo.nonIrDevice.irCt1C = localCtTimingAction.c;
      localInnerRcVo.nonIrDevice.irCt1W = localCtTimingAction.w;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968685);
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setBgWhite();
    setTiTleTextRes(2131099936);
    init();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onResume()
  {
    super.onResume();
  }

  public class ItRcMainAdapter extends BaseAdapter
  {
    private Context context;
    private LayoutInflater layoutInflater;

    public ItRcMainAdapter(Context arg2)
    {
      Context localContext;
      this.context = localContext;
      this.layoutInflater = LayoutInflater.from(localContext);
    }

    private void initializeViews(MyRcDevice paramMyRcDevice, ViewHolder paramViewHolder, int paramInt)
    {
      paramViewHolder.ic.setImageResource(AtSaveYongkongList.this.mDeviceImages[paramMyRcDevice.mType]);
      paramViewHolder.name.setText(paramMyRcDevice.mName);
    }

    public int getCount()
    {
      return AtSaveYongkongList.this.rcDevices.myRcDevices.size();
    }

    public MyRcDevice getItem(int paramInt)
    {
      return (MyRcDevice)AtSaveYongkongList.this.rcDevices.myRcDevices.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968821, null);
        ViewHolder localViewHolder = new ViewHolder();
        ViewHolder.access$302(localViewHolder, (ImageView)paramView.findViewById(2131558883));
        ViewHolder.access$402(localViewHolder, (TextView)paramView.findViewById(2131559008));
        ViewHolder.access$502(localViewHolder, (ImageView)paramView.findViewById(2131559352));
        paramView.setTag(localViewHolder);
      }
      initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag(), paramInt);
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
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtSaveYongkongList
 * JD-Core Version:    0.6.0
 */