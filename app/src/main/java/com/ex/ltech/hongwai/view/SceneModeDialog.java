package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ex.ltech.hongwai.vo.Ct1SceneVo;
import com.ex.ltech.led.my_view.MLImageView;
import java.util.ArrayList;
import java.util.Iterator;

public class SceneModeDialog extends BaseTipsDialog
{
  ArrayList<Ct1SceneVo> ct1SceneVos;

  @Bind({2131559098})
  GridView mGridViewScene;
  private int mSelectPosition = -1;
  OnListener onListener;
  private final int[] reses = { 2130903158, 2130903159, 2130903160, 2130903161, 2130903163, 2130903162, 2130903164, 2130903165 };

  public SceneModeDialog(Context paramContext, ArrayList<Ct1SceneVo> paramArrayList)
  {
    super(paramContext, 1.0F, (int)(0.5F + 290.0F * paramContext.getResources().getDisplayMetrics().density), 80);
    this.ct1SceneVos = paramArrayList;
  }

  @OnClick({2131559094})
  public void cancel()
  {
    dismiss();
  }

  protected int getLayoutId()
  {
    return 2130968726;
  }

  protected void initView()
  {
  }

  public SceneModeDialog setOnListener(OnListener paramOnListener)
  {
    this.onListener = paramOnListener;
    return this;
  }

  public void show()
  {
    super.show();
    this.mGridViewScene.setAdapter(new GridViewAdapter(this.mContext, this.ct1SceneVos));
  }

  class GridViewAdapter extends BaseAdapter
  {
    private Context context;
    ArrayList<Ct1SceneVo> ct1SceneVos;

    public GridViewAdapter(ArrayList<Ct1SceneVo> arg2)
    {
      Object localObject1;
      this.context = localObject1;
      Object localObject2;
      this.ct1SceneVos = localObject2;
    }

    public int getCount()
    {
      return this.ct1SceneVos.size();
    }

    public Object getItem(int paramInt)
    {
      return this.ct1SceneVos.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      ViewHolder localViewHolder;
      Ct1SceneVo localCt1SceneVo;
      ImageView localImageView;
      if (paramView == null)
      {
        paramView = LayoutInflater.from(this.context).inflate(2130968804, null);
        localViewHolder = new ViewHolder(paramView);
        paramView.setTag(localViewHolder);
        localCt1SceneVo = (Ct1SceneVo)this.ct1SceneVos.get(paramInt);
        localViewHolder.edit.setBackgroundResource(2130903709);
        localImageView = localViewHolder.edit;
        if (!localCt1SceneVo.isEdit)
          break label224;
      }
      label224: for (int i = 0; ; i = 4)
      {
        localImageView.setVisibility(i);
        if ((!localCt1SceneVo.isEdit) && (SceneModeDialog.this.mSelectPosition == paramInt))
        {
          localViewHolder.edit.setBackgroundResource(2130903398);
          localViewHolder.edit.setVisibility(0);
        }
        localViewHolder.name.setText(localCt1SceneVo.name);
        localViewHolder.customIc.setBackgroundResource(SceneModeDialog.this.reses[(paramInt % SceneModeDialog.this.reses.length)]);
        localViewHolder.customIc.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            if (SceneModeDialog.this.onListener != null)
              SceneModeDialog.this.onListener.onItemClick(this.val$position);
            SceneModeDialog.access$002(SceneModeDialog.this, this.val$position);
            SceneModeDialog.GridViewAdapter.this.notifyDataSetChanged();
          }
        });
        localViewHolder.customIc.setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramView)
          {
            Iterator localIterator = SceneModeDialog.GridViewAdapter.this.ct1SceneVos.iterator();
            if (localIterator.hasNext())
            {
              Ct1SceneVo localCt1SceneVo = (Ct1SceneVo)localIterator.next();
              if (!localCt1SceneVo.isEdit);
              for (boolean bool = true; ; bool = false)
              {
                localCt1SceneVo.isEdit = bool;
                break;
              }
            }
            SceneModeDialog.GridViewAdapter.this.notifyDataSetChanged();
            return true;
          }
        });
        localViewHolder.edit.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            if (SceneModeDialog.this.onListener != null)
              SceneModeDialog.this.onListener.onItemEditClick(this.val$position);
            SceneModeDialog.this.dismiss();
          }
        });
        return paramView;
        localViewHolder = (ViewHolder)paramView.getTag();
        break;
      }
    }

    class ViewHolder
    {

      @Bind({2131559310})
      MLImageView customIc;

      @Bind({2131559311})
      ImageView edit;

      @Bind({2131559312})
      TextView name;

      ViewHolder(View arg2)
      {
        View localView;
        ButterKnife.bind(this, localView);
      }
    }
  }

  public static abstract interface OnListener
  {
    public abstract void onItemClick(int paramInt);

    public abstract void onItemEditClick(int paramInt);
  }

  public static class TestData
  {
    public boolean isEdit;
    public String name;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.SceneModeDialog
 * JD-Core Version:    0.6.0
 */