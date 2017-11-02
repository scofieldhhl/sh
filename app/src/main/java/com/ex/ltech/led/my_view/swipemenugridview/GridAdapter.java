package com.ex.ltech.led.my_view.swipemenugridview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter
{
  private Context mContext;
  private ArrayList<Drawable> mDrawableList = new ArrayList();
  private LayoutInflater mInflater;
  private ArrayList<String> mNameList = new ArrayList();
  LinearLayout.LayoutParams params;

  public GridAdapter(Context paramContext, ArrayList<String> paramArrayList, ArrayList<Drawable> paramArrayList1)
  {
    this.mNameList = paramArrayList;
    this.mDrawableList = paramArrayList1;
    this.mContext = paramContext;
    this.mInflater = LayoutInflater.from(paramContext);
    this.params = new LinearLayout.LayoutParams(-2, -2);
    this.params.gravity = 17;
  }

  public int getCount()
  {
    return this.mNameList.size();
  }

  public Object getItem(int paramInt)
  {
    return this.mNameList.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ItemViewTag localItemViewTag;
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(2130968786, null);
      localItemViewTag = new ItemViewTag((ImageView)paramView.findViewById(2131559272), (TextView)paramView.findViewById(2131559273));
      paramView.setTag(localItemViewTag);
    }
    while (true)
    {
      localItemViewTag.mName.setText((CharSequence)this.mNameList.get(paramInt));
      localItemViewTag.mIcon.setBackgroundDrawable((Drawable)this.mDrawableList.get(paramInt));
      localItemViewTag.mIcon.setLayoutParams(this.params);
      return paramView;
      localItemViewTag = (ItemViewTag)paramView.getTag();
    }
  }

  class ItemViewTag
  {
    protected ImageView mIcon;
    protected TextView mName;

    public ItemViewTag(ImageView paramTextView, TextView arg3)
    {
      Object localObject;
      this.mName = localObject;
      this.mIcon = paramTextView;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.swipemenugridview.GridAdapter
 * JD-Core Version:    0.6.0
 */