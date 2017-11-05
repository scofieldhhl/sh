package com.ex.ltech.onepiontfive.main.newscene;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.onepiontfive.main.vo.RoomLvChildVo;
import com.ex.ltech.onepiontfive.main.vo.SceneStep;
import com.ex.ltech.onepiontfive.main.vo.SceneSteps;
import com.ex.ltech.remote.control.vo.YkVo;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FinishAddSceneAdapter extends BaseAdapter
{
  private FtFinishAddScene ftFinishAddScene;
  private LayoutInflater layoutInflater;
  int[] picRes = { 2130903055, 2130903791, 2130903793, 2130903637, 2130903255, 2130903401, 2130903229 };
  SceneSteps sceneSteps;
  List<RoomLvChildVo> vos;

  public FinishAddSceneAdapter(FtFinishAddScene paramFtFinishAddScene, SceneSteps paramSceneSteps)
  {
    this.ftFinishAddScene = paramFtFinishAddScene;
    this.layoutInflater = LayoutInflater.from(paramFtFinishAddScene.getActivity());
    this.sceneSteps = paramSceneSteps;
  }

  public FinishAddSceneAdapter(FtFinishAddScene paramFtFinishAddScene, List<RoomLvChildVo> paramList)
  {
    this.ftFinishAddScene = paramFtFinishAddScene;
    this.layoutInflater = LayoutInflater.from(paramFtFinishAddScene.getActivity());
    this.vos = paramList;
  }

  public int getCount()
  {
    return this.sceneSteps.getSteps().size();
  }

  public RoomLvChildVo getItem(int paramInt)
  {
    return (RoomLvChildVo)this.vos.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.layoutInflater.inflate(2130968799, null);
      ViewHolder localViewHolder = new ViewHolder();
      ViewHolder.access$002(localViewHolder, (ImageView)paramView.findViewById(2131559285));
      ViewHolder.access$102(localViewHolder, (ImageView)paramView.findViewById(2131558468));
      ViewHolder.access$202(localViewHolder, (TextView)paramView.findViewById(2131558981));
      ViewHolder.access$302(localViewHolder, (TextView)paramView.findViewById(2131559286));
      ViewHolder.access$402(localViewHolder, (TextView)paramView.findViewById(2131559287));
      localViewHolder.del = ((Button)paramView.findViewById(2131559288));
      paramView.setTag(localViewHolder);
    }
    setView((ViewHolder)paramView.getTag(), paramInt);
    return paramView;
  }

  void setView(ViewHolder paramViewHolder, int paramInt)
  {
    SceneStep localSceneStep = (SceneStep)this.sceneSteps.getSteps().get(paramInt);
    paramViewHolder.yaoKongName.setText(localSceneStep.getRoomName());
    paramViewHolder.yaoKongStatus.setText(localSceneStep.getSeletedDvcNames());
    paramViewHolder.time.setText(localSceneStep.getSpaceTime() + this.ftFinishAddScene.getString(R.string.sec));
    String str;
    int i;
    if ((localSceneStep.seletedDvcs != null) && (localSceneStep.seletedDvcs.size() > 0) && (((RoomLvChildVo)localSceneStep.seletedDvcs.get(0)).getYkVo() != null))
    {
      str = ((RoomLvChildVo)localSceneStep.seletedDvcs.get(0)).getYkVo().getType();
      i = -1;
      switch (str.hashCode())
      {
      default:
        label274: switch (i)
        {
        default:
          label244: if ((getCount() > 1) && (-2 + getCount() == paramInt + 1))
          {
            paramViewHolder.time.setVisibility(View.GONE);
            paramViewHolder.time.setOnClickListener(new View.OnClickListener(localSceneStep, paramInt)
            {
              public void onClick(View paramView)
              {
                System.out.println("h.time.setOnClickListener = " + this.val$step.getSpaceTime());
                FinishAddSceneAdapter.this.ftFinishAddScene.onSpaceTimeClick(this.val$i, this.val$step.getSpaceTime());
              }
            });
            if (paramInt == -1 + getCount())
              break label801;
            paramViewHolder.down_vertical_line.setVisibility(View.VISIBLE);
            paramViewHolder.time.setVisibility(View.VISIBLE);
          }
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        }
      case 96586:
      case 3714:
      case 110741513:
      case -894830916:
      case 101139:
      case 3239401:
      case 99858:
      }
    }
    while (true)
    {
      paramViewHolder.del.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          FinishAddSceneAdapter.this.ftFinishAddScene.onDelStep(this.val$i);
        }
      });
      return;
      if (!str.equals("air"))
        break;
      i = 0;
      break;
      if (!str.equals("tv"))
        break;
      i = 1;
      break;
      if (!str.equals("tvbox"))
        break;
      i = 2;
      break;
      if (!str.equals("projector"))
        break;
      i = 3;
      break;
      if (!str.equals("fan"))
        break;
      i = 4;
      break;
      if (!str.equals("iptv"))
        break;
      i = 5;
      break;
      if (!str.equals("dvd"))
        break;
      i = 6;
      break;
      paramViewHolder.icon.setBackgroundResource(this.picRes[0]);
      break label244;
      paramViewHolder.icon.setBackgroundResource(this.picRes[1]);
      break label244;
      paramViewHolder.icon.setBackgroundResource(this.picRes[2]);
      break label244;
      paramViewHolder.icon.setBackgroundResource(this.picRes[3]);
      break label244;
      paramViewHolder.icon.setBackgroundResource(this.picRes[4]);
      break label244;
      paramViewHolder.icon.setBackgroundResource(this.picRes[5]);
      break label244;
      paramViewHolder.icon.setBackgroundResource(this.picRes[6]);
      break label244;
      paramViewHolder.icon.setBackgroundResource(2130903135);
      switch (((RoomLvChildVo)localSceneStep.seletedDvcs.get(0)).getInnerItemType())
      {
      default:
        break;
      case 8:
        paramViewHolder.icon.setBackgroundResource(2130903700);
        break;
      case 11:
        paramViewHolder.icon.setBackgroundResource(2130903085);
        break;
      case 14:
        paramViewHolder.icon.setBackgroundResource(2130903731);
        break;
      case 10:
        paramViewHolder.icon.setBackgroundResource(2130903142);
        break;
      case 12:
        paramViewHolder.icon.setBackgroundResource(2130903701);
        break;
      case 13:
        paramViewHolder.icon.setBackgroundResource(2130903621);
        break;
      case 15:
        paramViewHolder.icon.setBackgroundResource(2130903607);
        break;
      case 16:
        paramViewHolder.icon.setBackgroundResource(2130903780);
        break;
      case 17:
        paramViewHolder.icon.setBackgroundResource(2130903800);
        break;
      case 18:
        paramViewHolder.icon.setBackgroundResource(2130903600);
        break;
      case 9:
        paramViewHolder.icon.setBackgroundResource(2130903805);
        break;
        paramViewHolder.time.setVisibility(View.VISIBLE);
        break label274;
        label801: paramViewHolder.down_vertical_line.setVisibility(View.GONE);
        paramViewHolder.time.setVisibility(View.GONE);
      }
    }
  }

  protected class ViewHolder
  {
    Button del;
    private ImageView down_vertical_line;
    private ImageView icon;
    private TextView time;
    private TextView yaoKongName;
    private TextView yaoKongStatus;

    protected ViewHolder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.FinishAddSceneAdapter
 * JD-Core Version:    0.6.0
 */