package com.ex.ltech.hongwai.atRcs.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import org.askerov.dynamicgrid.BaseDynamicGridAdapter;

public class CheeseDynamicAdapter extends BaseDynamicGridAdapter
{
  public CheeseDynamicAdapter(Context paramContext)
  {
    super(paramContext);
  }

  public CheeseDynamicAdapter(Context paramContext, List<?> paramList, int paramInt)
  {
    super(paramContext, paramList, paramInt);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    CheeseViewHolder localCheeseViewHolder;
    if (paramView == null)
    {
      paramView = LayoutInflater.from(getContext()).inflate(2130968820, null);
      localCheeseViewHolder = new CheeseViewHolder(paramView, null);
      paramView.setTag(localCheeseViewHolder);
    }
    while (true)
    {
      localCheeseViewHolder.build(getItem(paramInt).toString());
      return paramView;
      localCheeseViewHolder = (CheeseViewHolder)paramView.getTag();
    }
  }

  private class CheeseViewHolder
  {
    private ImageView image;
    private TextView titleText;

    private CheeseViewHolder(View arg2)
    {
      Object localObject;
      this.titleText = ((TextView)localObject.findViewById(2131559368));
      this.image = ((ImageView)localObject.findViewById(2131559367));
    }

    void build(String paramString)
    {
      this.titleText.setText(paramString);
      this.image.setImageResource(2130837627);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.example.CheeseDynamicAdapter
 * JD-Core Version:    0.6.0
 */