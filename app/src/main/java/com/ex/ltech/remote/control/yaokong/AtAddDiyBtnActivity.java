package com.ex.ltech.remote.control.yaokong;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import com.ex.ltech.remote.control.YkAt;
import et.song.etclass.ETDevice;
import et.song.etclass.ETKey;
import java.util.ArrayList;

public class AtAddDiyBtnActivity extends YkAt
{
  private ItDiyAdapter adapter;
  private RcDiyBusiness business;
  private GridView gvDiy;
  private boolean isShowEdit;
  public final int noSeletecBtStatus = -1;
  public final int seletecBtStatus = 0;
  private ArrayList<Integer> seleted = new ArrayList();
  private ArrayList<Integer> seletedBtnRes = new ArrayList();

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968654);
    this.business = new RcDiyBusiness(this);
    this.gvDiy = ((GridView)findViewById(2131558819));
    this.adapter = new ItDiyAdapter(this);
    this.business.initAtAddBtnData();
    this.gvDiy.setAdapter(this.adapter);
    setTitleView();
    for (int i = 0; i < 16; i++)
      this.seleted.add(Integer.valueOf(-1));
  }

  protected void onEdit()
  {
    super.onEdit();
    Intent localIntent = new Intent();
    for (int i = 0; i < this.seleted.size(); i++)
    {
      if (((Integer)this.seleted.get(i)).intValue() != 0)
        continue;
      this.seletedBtnRes.add(Integer.valueOf(this.business.butRes[(i + 6)]));
    }
    if (this.seletedBtnRes.size() > 0)
    {
      localIntent.putIntegerArrayListExtra("seletedBtnList", this.seletedBtnRes);
      setResult(200, localIntent);
    }
    while (true)
    {
      finish();
      return;
      setResult(-1, localIntent);
    }
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.yk_back);
    setTiTleTextRes(2131099813);
    setEditTextRes(R.string.finish, getResources().getColor(2131492897));
  }

  class ItDiyAdapter extends BaseAdapter
  {
    private Context context;
    private LayoutInflater layoutInflater;

    public ItDiyAdapter(Context arg2)
    {
      Context localContext;
      this.context = localContext;
      this.layoutInflater = LayoutInflater.from(localContext);
    }

    private void initializeViews(ETKey paramETKey, ViewHolder paramViewHolder, int paramInt)
    {
      if (paramETKey.GetRes() != -1)
        paramViewHolder.btDiy.setBackgroundResource(paramETKey.GetRes());
      if (paramETKey.GetName().length() > 0)
        paramViewHolder.btDiy.setText(paramETKey.GetName());
      paramViewHolder.btEdit.setVisibility(View.VISIBLE);
      if (((Integer)AtAddDiyBtnActivity.this.seleted.get(paramInt)).intValue() == 0)
        paramViewHolder.btEdit.setBackgroundResource(2130903276);
      while (true)
      {
        paramViewHolder.btDiy.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            int i = -1;
            if (((Integer)AtAddDiyBtnActivity.this.seleted.get(this.val$position)).intValue() == i)
              i = 0;
            AtAddDiyBtnActivity.this.seleted.remove(this.val$position);
            AtAddDiyBtnActivity.this.seleted.add(this.val$position, Integer.valueOf(i));
            AtAddDiyBtnActivity.ItDiyAdapter.this.notifyDataSetChanged();
          }
        });
        paramViewHolder.tv_under_line.setVisibility(View.VISIBLE);
        return;
        paramViewHolder.btEdit.setBackgroundResource(2130903277);
      }
    }

    public int getCount()
    {
      return AtAddDiyBtnActivity.this.business.mDevice.GetCount();
    }

    public ETKey getItem(int paramInt)
    {
      return AtAddDiyBtnActivity.this.business.mDevice.GetKeyByIndex(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130968797, null);
        ViewHolder localViewHolder = new ViewHolder();
        ViewHolder.access$102(localViewHolder, (Button)paramView.findViewById(2131559016));
        ViewHolder.access$202(localViewHolder, (Button)paramView.findViewById(2131559298));
        ViewHolder.access$302(localViewHolder, (TextView)paramView.findViewById(2131559299));
        paramView.setTag(localViewHolder);
      }
      initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag(), paramInt);
      return paramView;
    }

    protected class ViewHolder
    {
      private Button btDiy;
      private Button btEdit;
      private TextView tv_under_line;

      protected ViewHolder()
      {
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.AtAddDiyBtnActivity
 * JD-Core Version:    0.6.0
 */