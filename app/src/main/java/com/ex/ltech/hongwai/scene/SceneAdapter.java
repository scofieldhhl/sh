package com.ex.ltech.hongwai.scene;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.T;
import java.util.ArrayList;
import java.util.List;

public class SceneAdapter extends BaseAdapter
{
  private Context context;
  private LayoutInflater layoutInflater;
  private List<T> objects = new ArrayList();

  public SceneAdapter(Context paramContext)
  {
    this.context = paramContext;
    this.layoutInflater = LayoutInflater.from(paramContext);
  }

  private void initializeViews(T paramT, ViewHolder paramViewHolder)
  {
  }

  public int getCount()
  {
    return this.objects.size();
  }

  public T getItem(int paramInt)
  {
    return (T)this.objects.get(paramInt);
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
      ViewHolder.access$002(localViewHolder, (ImageView)paramView.findViewById(2131559290));
      ViewHolder.access$102(localViewHolder, (TextView)paramView.findViewById(2131558662));
      paramView.setTag(localViewHolder);
    }
    initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag());
    return paramView;
  }

  protected class ViewHolder
  {
    private ImageView ivIc;
    private TextView tvName;

    protected ViewHolder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.scene.SceneAdapter
 * JD-Core Version:    0.6.0
 */