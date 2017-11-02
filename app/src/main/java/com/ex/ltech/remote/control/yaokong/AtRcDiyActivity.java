package com.ex.ltech.remote.control.yaokong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import com.ex.ltech.led.my_view.MyAlertDialog5;
import com.ex.ltech.led.my_view.MyAlertDialog5.MyOnClickListener;
import com.ex.ltech.remote.control.YkAt;
import et.song.etclass.ETDevice;
import et.song.etclass.ETKey;
import java.util.ArrayList;

public class AtRcDiyActivity extends YkAt
  implements View.OnLongClickListener
{
  private ItDiyAdapter adapter;
  private Button btAddDiy;
  private RcDiyBusiness business;
  ETKey diyEtKey;
  private GridView gvDiy;
  private boolean isShowEdit;
  private int keyIndex;

  public void addDiyBtn(View paramView)
  {
    goAct4result(AtAddDiyBtnActivity.class, 200);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 200)
    {
      this.business.seletedBtnRes.clear();
      this.business.seletedBtnRes.addAll(paramIntent.getIntegerArrayListExtra("seletedBtnList"));
      this.business.handleData();
      this.adapter.notifyDataSetChanged();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968695);
    this.gvDiy = ((GridView)findViewById(2131558819));
    this.btAddDiy = ((Button)findViewById(2131558851));
    this.business = new RcDiyBusiness(this);
    this.adapter = new ItDiyAdapter(this);
    this.business.initData(getIntent().getIntExtra("index", -1));
    this.gvDiy.setAdapter(this.adapter);
    setTitleView();
  }

  protected void onEdit()
  {
    super.onEdit();
    if (!this.isShowEdit);
    for (boolean bool = true; ; bool = false)
    {
      this.isShowEdit = bool;
      this.adapter.notifyDataSetChanged();
      return;
    }
  }

  public boolean onLongClick(View paramView)
  {
    showLearnDialog();
    return false;
  }

  protected void onMenu()
  {
    super.onMenu();
    this.business.onAtPressBack();
  }

  protected void onRecHongWaiCode(byte[] paramArrayOfByte)
  {
    super.onRecHongWaiCode(paramArrayOfByte);
    this.business.upDateEtKey(this.keyIndex, paramArrayOfByte);
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903830);
    setTiTleTextRes(2131100536);
    setEditImageRes(2130903831);
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
      int i;
      if (paramETKey.GetRes() != -1)
      {
        paramViewHolder.btDiy.setOnClickListener(new View.OnClickListener(paramETKey, paramInt)
        {
          public void onClick(View paramView)
          {
            if (this.val$etKey.GetState() == 4)
            {
              AtRcDiyActivity.access$402(AtRcDiyActivity.this, this.val$position);
              AtRcDiyActivity.this.showLearnDialog();
            }
            if (this.val$etKey.GetState() == 1)
            {
              AtRcDiyActivity.this.vibrate();
              AtRcDiyActivity.this.sendCmd(this.val$etKey.GetValue());
            }
          }
        });
        Button localButton = paramViewHolder.btEdit;
        if (AtRcDiyActivity.this.isShowEdit)
        {
          i = 0;
          localButton.setVisibility(i);
          paramViewHolder.btDiy.setVisibility(0);
          paramViewHolder.btDiy.setBackgroundResource(paramETKey.GetRes());
        }
      }
      while (true)
      {
        if (paramETKey.GetName().length() > 0)
          paramViewHolder.btDiy.setText(paramETKey.GetName());
        paramViewHolder.btEdit.setOnClickListener(new View.OnClickListener(paramInt, paramETKey)
        {
          public void onClick(View paramView)
          {
            MyAlertDialog5 localMyAlertDialog5 = new MyAlertDialog5(AtRcDiyActivity.this);
            localMyAlertDialog5.show();
            localMyAlertDialog5.tvRename.setText(2131100130);
            localMyAlertDialog5.tvEdit.setText(2131100053);
            localMyAlertDialog5.setMyOnClickListener(new MyAlertDialog5.MyOnClickListener()
            {
              public void onDel()
              {
                AtRcDiyActivity.this.business.delKey(AtRcDiyActivity.ItDiyAdapter.2.this.val$etKey);
                AtRcDiyActivity.ItDiyAdapter.this.notifyDataSetChanged();
              }

              public void onEdit()
              {
                AtRcDiyActivity.this.business.renameKey(AtRcDiyActivity.ItDiyAdapter.2.this.val$etKey);
              }

              public void onRename()
              {
                AtRcDiyActivity.access$402(AtRcDiyActivity.this, AtRcDiyActivity.ItDiyAdapter.2.this.val$position);
                AtRcDiyActivity.this.showLearnDialog();
              }
            });
          }
        });
        if (getCount() <= 6)
          break label184;
        if (paramInt <= 2)
          break label174;
        paramViewHolder.tv_under_line.setVisibility(0);
        return;
        i = 8;
        break;
        paramViewHolder.btEdit.setVisibility(8);
        paramViewHolder.btDiy.setOnLongClickListener(null);
        paramViewHolder.btDiy.setVisibility(4);
        paramViewHolder.btDiy.setOnClickListener(null);
      }
      label174: paramViewHolder.tv_under_line.setVisibility(8);
      return;
      label184: if (paramInt > 5)
      {
        paramViewHolder.tv_under_line.setVisibility(0);
        return;
      }
      paramViewHolder.tv_under_line.setVisibility(8);
    }

    public int getCount()
    {
      return AtRcDiyActivity.this.business.mDevice.GetCount();
    }

    public ETKey getItem(int paramInt)
    {
      return AtRcDiyActivity.this.business.mDevice.GetKeyByIndex(paramInt);
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
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.AtRcDiyActivity
 * JD-Core Version:    0.6.0
 */