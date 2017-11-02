package com.ex.ltech.onepiontfive.main.room.panel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.my_view.MLImageView;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.onepiontfive.main.vo.PanelLampVO;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import java.util.ArrayList;
import java.util.List;

public class PanelListAdt extends BaseAdapter
{
  Callback callback;
  private Context context;
  int itemCount;
  private LayoutInflater layoutInflater;
  private List<PanelLampVO> lights;
  private List<Bitmap> maps;
  PanelBussiness panelBussiness;

  public PanelListAdt(Context paramContext, List<PanelLampVO> paramList, Callback paramCallback, PanelBussiness paramPanelBussiness, int paramInt)
  {
    this.context = paramContext;
    this.callback = paramCallback;
    this.lights = paramList;
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.panelBussiness = paramPanelBussiness;
    this.itemCount = paramInt;
    this.maps = new ArrayList();
    for (int i = 0; i < paramList.size(); i++)
      this.maps.add(BitmapFactory.decodeFile(((PanelLampVO)paramList.get(i)).getImgPath()));
    this.layoutInflater = LayoutInflater.from(paramContext);
  }

  private void initializeViews(PanelLampVO paramPanelLampVO, ViewHolder paramViewHolder, View paramView, int paramInt)
  {
    if ((paramPanelLampVO.getType() == 10) || (paramPanelLampVO.getType() == 11) || (paramPanelLampVO.getType() == 9) || (paramPanelLampVO.getType() == 8) || (paramPanelLampVO.getType() == -1))
      paramViewHolder.onoff.setListViewItemPosi(paramInt);
    paramViewHolder.name.setText(paramPanelLampVO.getName());
    try
    {
      String str = this.panelBussiness.getPanelBranchImgPath(paramInt);
      if ((str != null) && (!str.equals("")) && (paramPanelLampVO.getType() == -1))
      {
        Bitmap localBitmap = BitmapUtils.zoomOutBM(BitmapFactory.decodeFile(str), BitmapUtils.dp2px(this.context, 220.0F));
        paramViewHolder.ic.setImageBitmap(localBitmap);
      }
      while (true)
      {
        paramViewHolder.name.setText(paramPanelLampVO.getName());
        if (!paramPanelLampVO.isShowRelationBtn())
          break label346;
        if (!paramPanelLampVO.isRealation())
          break;
        paramViewHolder.status.setText(2131100322);
        paramViewHolder.onoff.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
        {
          public void onToggleInListView(boolean paramBoolean, int paramInt)
          {
            PanelListAdt.this.callback.onOnoff(paramBoolean, paramInt);
          }
        });
        paramViewHolder.btnRelation.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            PanelListAdt.this.callback.onShowRelationControl(this.val$posi);
          }
        });
        paramViewHolder.btnEdit.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            PanelListAdt.this.callback.onEdit(this.val$posi);
          }
        });
        return;
        switch (paramPanelLampVO.getType())
        {
        case -1:
          paramViewHolder.ic.setBackgroundResource(2130903601);
        case 8:
        case 9:
        case 11:
        case 10:
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        continue;
        paramViewHolder.ic.setBackgroundResource(2130903700);
        continue;
        paramViewHolder.ic.setBackgroundResource(2130903805);
        continue;
        paramViewHolder.ic.setBackgroundResource(2130903085);
        continue;
        paramViewHolder.ic.setBackgroundResource(2130903142);
        continue;
        paramViewHolder.status.setText(2131100216);
        continue;
        label346: if (paramPanelLampVO.isRealation())
          paramViewHolder.status.setText(2131100322);
        while (true)
        {
          if (!paramPanelLampVO.isOn())
            break label391;
          paramViewHolder.onoff.setToggleOn();
          break;
          paramViewHolder.status.setText(2131100216);
        }
        label391: paramViewHolder.onoff.setToggleOff();
        continue;
      }
    }
  }

  public void changeDataList(List<PanelLampVO> paramList)
  {
    this.maps = new ArrayList();
    for (int i = 0; i < paramList.size(); i++)
      this.maps.add(BitmapFactory.decodeFile(((PanelLampVO)paramList.get(i)).getImgPath()));
  }

  public int getCount()
  {
    return this.itemCount;
  }

  public PanelLampVO getItem(int paramInt)
  {
    return (PanelLampVO)this.lights.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.layoutInflater.inflate(2130968756, null);
      ViewHolder localViewHolder = new ViewHolder();
      ViewHolder.access$002(localViewHolder, (MLImageView)paramView.findViewById(2131558883));
      ViewHolder.access$102(localViewHolder, (TextView)paramView.findViewById(2131559008));
      ViewHolder.access$202(localViewHolder, (TextView)paramView.findViewById(2131559169));
      ViewHolder.access$302(localViewHolder, (ToggleButton)paramView.findViewById(2131559170));
      ViewHolder.access$402(localViewHolder, (ImageView)paramView.findViewById(2131559172));
      ViewHolder.access$502(localViewHolder, (Button)paramView.findViewById(2131558955));
      ViewHolder.access$602(localViewHolder, (ImageView)paramView.findViewById(2131559173));
      ViewHolder.access$702(localViewHolder, (Button)paramView.findViewById(2131559174));
      paramView.setTag(localViewHolder);
    }
    initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag(), paramView, paramInt);
    return paramView;
  }

  public static abstract interface Callback
  {
    public abstract void onEdit(int paramInt);

    public abstract void onOnoff(boolean paramBoolean, int paramInt);

    public abstract void onRelation(int paramInt);

    public abstract void onShowRelationControl(int paramInt);
  }

  protected class ViewHolder
  {
    private Button btnEdit;
    private Button btnRelation;
    private MLImageView ic;
    private ImageView ivEdit;
    private ImageView ivRelation;
    private TextView name;
    private ToggleButton onoff;
    private TextView status;

    protected ViewHolder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.panel.PanelListAdt
 * JD-Core Version:    0.6.0
 */