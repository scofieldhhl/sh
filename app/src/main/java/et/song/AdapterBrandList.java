package et.song;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import java.util.List;
import java.util.Locale;

public class AdapterBrandList extends BaseAdapter
  implements SectionIndexer
{
  private LayoutInflater mInflater;
  private List<AdapterPYinItem> mItems;

  public AdapterBrandList(Context paramContext, List<AdapterPYinItem> paramList)
  {
    this.mInflater = LayoutInflater.from(paramContext);
    this.mItems = paramList;
  }

  public int getCount()
  {
    return this.mItems.size();
  }

  public AdapterPYinItem getItem(int paramInt)
  {
    return (AdapterPYinItem)this.mItems.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  @SuppressLint({"DefaultLocale"})
  public int getPositionForSection(int paramInt)
  {
    for (int i = 0; i < this.mItems.size(); i++)
      if (((AdapterPYinItem)this.mItems.get(i)).getPyin().substring(0, 1).toUpperCase(Locale.getDefault()).charAt(0) == paramInt)
        return i;
    return -1;
  }

  public int getSectionForPosition(int paramInt)
  {
    return 0;
  }

  public Object[] getSections()
  {
    return null;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ViewHolder localViewHolder;
    int i;
    label131: int j;
    label167: LinearLayout localLinearLayout;
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(2130968736, null);
      localViewHolder = new ViewHolder(null);
      localViewHolder.file_title = ((TextView)paramView.findViewById(2131559120));
      localViewHolder.top_line = ((TextView)paramView.findViewById(2131559118));
      localViewHolder.ll_tip = ((LinearLayout)paramView.findViewById(2131559119));
      localViewHolder.file_name = ((TextView)paramView.findViewById(2131559121));
      localViewHolder.file_ename = ((TextView)paramView.findViewById(2131559122));
      paramView.setTag(localViewHolder);
      TextView localTextView1 = localViewHolder.top_line;
      if (!((AdapterPYinItem)this.mItems.get(paramInt)).isShow())
        break label330;
      i = 0;
      localTextView1.setVisibility(i);
      TextView localTextView2 = localViewHolder.file_title;
      if (!((AdapterPYinItem)this.mItems.get(paramInt)).isShow())
        break label337;
      j = 0;
      localTextView2.setVisibility(j);
      localLinearLayout = localViewHolder.ll_tip;
      if (!((AdapterPYinItem)this.mItems.get(paramInt)).isShow())
        break label344;
    }
    label330: label337: label344: for (int k = 0; ; k = 8)
    {
      localLinearLayout.setVisibility(k);
      localViewHolder.file_title.setText(((AdapterPYinItem)this.mItems.get(paramInt)).getmPyinUpper());
      localViewHolder.file_name.setText(((AdapterPYinItem)this.mItems.get(paramInt)).getName());
      localViewHolder.file_ename.setText(((AdapterPYinItem)this.mItems.get(paramInt)).ename);
      if (!((AdapterPYinItem)this.mItems.get(paramInt)).ename.equals(""))
        break label351;
      localViewHolder.file_ename.setVisibility(8);
      return paramView;
      localViewHolder = (ViewHolder)paramView.getTag();
      break;
      i = 8;
      break label131;
      j = 8;
      break label167;
    }
    label351: localViewHolder.file_ename.setVisibility(0);
    return paramView;
  }

  private class ViewHolder
  {
    TextView file_ename;
    TextView file_name;
    TextView file_title;
    LinearLayout ll_tip;
    TextView top_line;

    private ViewHolder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.AdapterBrandList
 * JD-Core Version:    0.6.0
 */