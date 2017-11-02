package com.ex.ltech.remote.control.scene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.MyAlertDialog5;
import com.ex.ltech.led.my_view.MyAlertDialog5.MyOnClickListener;
import com.ex.ltech.led.my_view.MyEditAlertDialog2;
import com.ex.ltech.led.my_view.MyEditAlertDialog2.MyOnClickListener;
import com.ex.ltech.outlet.ActOutLetLed;
import com.ex.ltech.remote.control.vo.YkSceneVo;
import java.io.PrintStream;
import java.util.List;

public class AtSceneActivity extends Activity
{
  private ItAtYkSceneAdapter adt;
  private SceneBusiness business;
  private GridView gvActScene;
  private int gvHeight;
  Handler handler = new Handler()
  {
  };
  boolean isShowEditBtn;
  TextView title;

  public void add(View paramView)
  {
    this.business.onAdd(this.adt.getCount());
  }

  public void back(View paramView)
  {
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968702);
    this.title = ((TextView)findViewById(2131558469));
    try
    {
      String str = UserFerences.getUserFerences(this).spFerences.getString("dName" + DeviceListActivity.deviceMacAddress, "");
      this.title.setText(str);
      ButterKnife.bind(this);
      this.business = new SceneBusiness(this);
      this.gvActScene = ((GridView)findViewById(2131558954));
      this.adt = new ItAtYkSceneAdapter(this);
      this.gvActScene.setAdapter(this.adt);
      this.gvActScene.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          if (!AtSceneActivity.this.isShowEditBtn)
            AtSceneActivity.this.business.onItemClick(paramInt);
        }
      });
      this.gvActScene.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
      {
        public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          AtSceneActivity localAtSceneActivity = AtSceneActivity.this;
          if (!AtSceneActivity.this.isShowEditBtn);
          for (boolean bool = true; ; bool = false)
          {
            localAtSceneActivity.isShowEditBtn = bool;
            AtSceneActivity.this.business.showEdit();
            AtSceneActivity.this.adt.notifyDataSetChanged();
            return true;
          }
        }
      });
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  protected void onPause()
  {
    super.onPause();
    this.isShowEditBtn = false;
    this.business.hideEdit();
    this.adt.notifyDataSetChanged();
  }

  protected void onResume()
  {
    super.onResume();
    this.business.resetData();
    this.adt = new ItAtYkSceneAdapter(this);
    this.gvActScene.setAdapter(this.adt);
  }

  public void showLight(View paramView)
  {
    startActivity(new Intent(this, ActOutLetLed.class));
  }

  class ItAtYkSceneAdapter extends BaseAdapter
  {
    private Context context;
    private LayoutInflater layoutInflater;

    public ItAtYkSceneAdapter(Context arg2)
    {
      Context localContext;
      this.context = localContext;
      this.layoutInflater = LayoutInflater.from(localContext);
    }

    private void initializeViews(YkSceneVo paramYkSceneVo, ViewHolder paramViewHolder, int paramInt)
    {
      if (paramYkSceneVo.getPicPath().length() > 0);
      while (true)
      {
        try
        {
          Bitmap localBitmap = BitmapFactory.decodeFile(paramYkSceneVo.getPicPath());
          paramViewHolder.ivIc.setImageBitmap(localBitmap);
          ImageView localImageView1 = paramViewHolder.iv_edit;
          if (!paramYkSceneVo.isEdit())
            break label171;
          i = 0;
          localImageView1.setVisibility(i);
          ImageView localImageView2 = paramViewHolder.iv_room_name_bg;
          int j = paramYkSceneVo.getPicPath().length();
          k = 0;
          if (j <= 0)
            break label178;
          localImageView2.setVisibility(k);
          paramViewHolder.tvName.setText(paramYkSceneVo.getName());
          paramViewHolder.iv_edit.setOnClickListener(new View.OnClickListener(paramYkSceneVo, paramInt)
          {
            public void onClick(View paramView)
            {
              MyAlertDialog5 localMyAlertDialog5 = new MyAlertDialog5(AtSceneActivity.this);
              localMyAlertDialog5.show();
              localMyAlertDialog5.getWindow().setGravity(80);
              localMyAlertDialog5.setMyOnClickListener(new MyAlertDialog5.MyOnClickListener()
              {
                public void onDel()
                {
                  if (AtSceneActivity.ItAtYkSceneAdapter.1.this.val$position > 3)
                  {
                    AtSceneActivity.this.business.onItemLongClick(AtSceneActivity.ItAtYkSceneAdapter.1.this.val$position);
                    AtSceneActivity.this.adt.notifyDataSetChanged();
                    return;
                  }
                  Toast.makeText(AtSceneActivity.this, 2131100011, 0).show();
                }

                public void onEdit()
                {
                  AtSceneActivity.this.business.onEdit(AtSceneActivity.ItAtYkSceneAdapter.1.this.val$position, AtSceneActivity.ItAtYkSceneAdapter.1.this.val$vo.getName());
                }

                public void onRename()
                {
                  MyEditAlertDialog2 localMyEditAlertDialog2 = new MyEditAlertDialog2(AtSceneActivity.this);
                  localMyEditAlertDialog2.show();
                  localMyEditAlertDialog2.setHintContent(AtSceneActivity.ItAtYkSceneAdapter.1.this.val$vo.getName());
                  localMyEditAlertDialog2.getWindow().clearFlags(131080);
                  localMyEditAlertDialog2.getWindow().setSoftInputMode(4);
                  localMyEditAlertDialog2.setMyOnClickListener(new MyEditAlertDialog2.MyOnClickListener()
                  {
                    public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
                    {
                      AtSceneActivity.this.business.onRename(AtSceneActivity.ItAtYkSceneAdapter.1.this.val$position, paramString);
                      AtSceneActivity.ItAtYkSceneAdapter.this.notifyDataSetChanged();
                    }
                  });
                }
              });
            }
          });
          if (paramInt <= 3)
            break;
          paramViewHolder.rlBg.setBackgroundColor(Color.argb(255, 248, 248, 248));
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          continue;
        }
        paramViewHolder.ivIc.setImageBitmap(BitmapFactory.decodeResource(AtSceneActivity.this.getResources(), paramYkSceneVo.getPicRes()));
        continue;
        label171: int i = 8;
        continue;
        label178: int k = 8;
      }
      paramViewHolder.rlBg.setBackgroundColor(Color.argb(255, 255, 255, 255));
    }

    public int getCount()
    {
      return AtSceneActivity.this.business.getData().size();
    }

    public YkSceneVo getItem(int paramInt)
    {
      return (YkSceneVo)AtSceneActivity.this.business.getData().get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968794, null);
        ViewHolder localViewHolder = new ViewHolder();
        ViewHolder.access$202(localViewHolder, (ImageView)paramView.findViewById(2131559290));
        ViewHolder.access$302(localViewHolder, (ImageView)paramView.findViewById(2131559291));
        ViewHolder.access$402(localViewHolder, (ImageView)paramView.findViewById(2131559172));
        ViewHolder.access$502(localViewHolder, (TextView)paramView.findViewById(2131558662));
        ViewHolder.access$602(localViewHolder, (RelativeLayout)paramView.findViewById(2131559289));
        paramView.setTag(localViewHolder);
      }
      if (paramView.getMeasuredHeight() > 0)
      {
        AtSceneActivity.this.business.saveGvItWidthHeight(paramView.getMeasuredWidth(), paramView.getMeasuredHeight());
        System.out.println(paramView.getMeasuredWidth() + "$%^&*()*&()              " + paramInt + "           " + paramView.getMeasuredHeight());
      }
      initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag(), paramInt);
      return paramView;
    }

    protected class ViewHolder
    {
      private ImageView ivIc;
      private ImageView iv_edit;
      private ImageView iv_room_name_bg;
      private RelativeLayout rlBg;
      private TextView tvName;

      protected ViewHolder()
      {
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.scene.AtSceneActivity
 * JD-Core Version:    0.6.0
 */