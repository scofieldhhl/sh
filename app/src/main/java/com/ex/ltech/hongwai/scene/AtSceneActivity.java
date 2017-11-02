package com.ex.ltech.hongwai.scene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.ex.ltech.hongwai.vo.SceneVo;
import com.ex.ltech.hongwai.vo.SceneVos;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.MyAlertDialog12;
import com.ex.ltech.led.my_view.MyAlertDialog12.MyOnClickListener;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import java.io.PrintStream;
import java.util.ArrayList;

public class AtSceneActivity extends Activity
{
  private ItAtYkSceneAdapter adt;
  private MyBiz business;
  private SwipeMenuListView gvActScene;
  private int gvHeight;
  Handler handler = new Handler()
  {
  };
  boolean isShowEditBtn;
  SceneVos sceneVos;
  TextView title;

  public void add(View paramView)
  {
    Intent localIntent = new Intent(this, AtRcNewSceneActivity.class);
    localIntent.putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_CREATE");
    localIntent.putExtra("OP_AT_POSI_KEY", this.adt.getCount());
    startActivityForResult(localIntent, 0);
  }

  public void back(View paramView)
  {
    finish();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 20000)
    {
      this.sceneVos = this.business.getSceneVos();
      this.adt.notifyDataSetChanged();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968686);
    this.title = ((TextView)findViewById(2131558469));
    try
    {
      String str = UserFerences.getUserFerences(this).spFerences.getString("dName" + DeviceListActivity.deviceMacAddress, "");
      this.title.setText(str);
      this.business = new MyBiz(this);
      this.sceneVos = this.business.getSceneVos();
      ButterKnife.bind(this);
      this.gvActScene = ((SwipeMenuListView)findViewById(2131558954));
      this.gvActScene.setMenuCreator(new SwipeMenuCreator()
      {
        public void create(SwipeMenu paramSwipeMenu)
        {
          SwipeMenuItem localSwipeMenuItem1 = new SwipeMenuItem(AtSceneActivity.this.getApplicationContext());
          localSwipeMenuItem1.setBackground(new ColorDrawable(Color.rgb(201, 201, 206)));
          localSwipeMenuItem1.setWidth(AtSceneActivity.this.business.dp2px(60));
          localSwipeMenuItem1.setIcon(2130903553);
          localSwipeMenuItem1.setTitleColor(-1);
          paramSwipeMenu.addMenuItem(localSwipeMenuItem1);
          SwipeMenuItem localSwipeMenuItem2 = new SwipeMenuItem(AtSceneActivity.this.getApplicationContext());
          localSwipeMenuItem2.setBackground(new ColorDrawable(Color.rgb(252, 82, 89)));
          localSwipeMenuItem2.setWidth(AtSceneActivity.this.business.dp2px(60));
          localSwipeMenuItem2.setIcon(2130903532);
          paramSwipeMenu.addMenuItem(localSwipeMenuItem2);
        }
      });
      this.gvActScene.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
      {
        public boolean onMenuItemClick(int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2)
        {
          switch (paramInt2)
          {
          default:
            return false;
          case 0:
            AtSceneActivity.this.handler.postDelayed(new Runnable(paramInt1)
            {
              public void run()
              {
                Intent localIntent = new Intent(AtSceneActivity.this, AtRcNewSceneActivity.class);
                localIntent.putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_EXIST");
                localIntent.putExtra("OP_AT_POSI_KEY", this.val$position);
                AtSceneActivity.this.startActivityForResult(localIntent, 0);
              }
            }
            , 200L);
            return false;
          case 1:
          }
          if (paramInt1 < 4)
          {
            Toast.makeText(AtSceneActivity.this, 2131100011, 0).show();
            return false;
          }
          MyAlertDialog12 localMyAlertDialog12 = new MyAlertDialog12(AtSceneActivity.this);
          localMyAlertDialog12.show();
          localMyAlertDialog12.setMsg(2131100044);
          localMyAlertDialog12.setMyOnClickListener(new MyAlertDialog12.MyOnClickListener(paramInt1)
          {
            public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
            {
              AtSceneActivity.this.sceneVos.sceneVos.remove(this.val$position);
              AtSceneActivity.this.adt.notifyDataSetChanged();
              AtSceneActivity.this.business.saveSceneVos(AtSceneActivity.this.sceneVos);
            }
          });
          return false;
        }
      });
      this.adt = new ItAtYkSceneAdapter(this);
      this.gvActScene.setAdapter(this.adt);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
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

    private void initializeViews(SceneVo paramSceneVo, ViewHolder paramViewHolder, int paramInt)
    {
      if (paramSceneVo.getSenceIcType().equals("outHome"))
      {
        paramViewHolder.ivIc.setImageBitmap(BitmapFactory.decodeResource(AtSceneActivity.this.getResources(), 2130903647));
        if (!(paramSceneVo.getSenceIcType().equals("outHome") | paramSceneVo.getSenceIcType().equals("goHome")))
          break label311;
        paramViewHolder.ivIcBottom.setVisibility(0);
        if (paramSceneVo.getSenceIcType().equals("outHome"))
          paramViewHolder.ivIcBottom.setImageBitmap(BitmapFactory.decodeResource(AtSceneActivity.this.getResources(), 2130903567));
        if (paramSceneVo.getSenceIcType().equals("goHome"))
          paramViewHolder.ivIcBottom.setImageBitmap(BitmapFactory.decodeResource(AtSceneActivity.this.getResources(), 2130903566));
      }
      while (true)
      {
        while (true)
        {
          paramViewHolder.tvName.setText(paramSceneVo.getName());
          paramViewHolder.btn_hand_touch_run.setOnClickListener(new View.OnClickListener(paramInt)
          {
            public void onClick(View paramView)
            {
              AtSceneActivity.this.business.send(AtSceneActivity.this.sceneVos, this.val$position);
            }
          });
          return;
          if (paramSceneVo.getSenceIcType().equals("goHome"))
          {
            paramViewHolder.ivIc.setImageBitmap(BitmapFactory.decodeResource(AtSceneActivity.this.getResources(), 2130903560));
            break;
          }
          if (paramSceneVo.getSenceIcType().equals("sleep"))
          {
            paramViewHolder.ivIc.setImageBitmap(BitmapFactory.decodeResource(AtSceneActivity.this.getResources(), 2130903649));
            break;
          }
          if (paramSceneVo.getSenceIcType().equals("wakeup"))
          {
            paramViewHolder.ivIc.setImageBitmap(BitmapFactory.decodeResource(AtSceneActivity.this.getResources(), 2130903648));
            break;
          }
          try
          {
            Bitmap localBitmap = BitmapFactory.decodeFile(paramSceneVo.getPicPath());
            if (localBitmap == null)
              break label289;
            paramViewHolder.ivIc.setImageBitmap(localBitmap);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
        break;
        label289: paramViewHolder.ivIc.setImageBitmap(BitmapFactory.decodeResource(AtSceneActivity.this.getResources(), 2130903560));
        break;
        label311: paramViewHolder.ivIcBottom.setVisibility(8);
      }
    }

    public int getCount()
    {
      return AtSceneActivity.this.sceneVos.sceneVos.size();
    }

    public SceneVo getItem(int paramInt)
    {
      return (SceneVo)AtSceneActivity.this.sceneVos.sceneVos.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968812, null);
        ViewHolder localViewHolder = new ViewHolder();
        ViewHolder.access$202(localViewHolder, (ImageView)paramView.findViewById(2131559290));
        ViewHolder.access$302(localViewHolder, (ImageView)paramView.findViewById(2131559353));
        ViewHolder.access$402(localViewHolder, (TextView)paramView.findViewById(2131558662));
        ViewHolder.access$502(localViewHolder, (Button)paramView.findViewById(2131559354));
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
      private Button btn_hand_touch_run;
      private ImageView ivIc;
      private ImageView ivIcBottom;
      private TextView tvName;

      protected ViewHolder()
      {
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.scene.AtSceneActivity
 * JD-Core Version:    0.6.0
 */