package com.ex.ltech.onepiontfive.main.room;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.VisualizerView;
import com.indris.material.RippleView;

public class MusicFragment$$ViewBinder<T extends MusicFragment>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleDeviceName = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558782, "field 'tvTitleDeviceName'"), 2131558782, "field 'tvTitleDeviceName'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.btnTitleViewEdit = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558784, "field 'btnTitleViewEdit'"), 2131558784, "field 'btnTitleViewEdit'"));
    paramT.tvTitleViewEdit = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558785, "field 'tvTitleViewEdit'"), 2131558785, "field 'tvTitleViewEdit'"));
    paramT.ivActiMusicDisc = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558717, "field 'ivActiMusicDisc'"), 2131558717, "field 'ivActiMusicDisc'"));
    paramT.ivActiMusicLoader = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558718, "field 'ivActiMusicLoader'"), 2131558718, "field 'ivActiMusicLoader'"));
    paramT.ivActiNullLoader = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558719, "field 'ivActiNullLoader'"), 2131558719, "field 'ivActiNullLoader'"));
    paramT.tvActiMusicSongName = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558720, "field 'tvActiMusicSongName'"), 2131558720, "field 'tvActiMusicSongName'"));
    paramT.sbMusicActi = ((SeekBar)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558721, "field 'sbMusicActi'"), 2131558721, "field 'sbMusicActi'"));
    paramT.btMusicActGoList = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558722, "field 'btMusicActGoList'"), 2131558722, "field 'btMusicActGoList'"));
    paramT.tvActiMusicSongCurrentTime = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558723, "field 'tvActiMusicSongCurrentTime'"), 2131558723, "field 'tvActiMusicSongCurrentTime'"));
    paramT.tvActiMusicSongAllTime = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558724, "field 'tvActiMusicSongAllTime'"), 2131558724, "field 'tvActiMusicSongAllTime'"));
    paramT.ivActiMusicBack = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558725, "field 'ivActiMusicBack'"), 2131558725, "field 'ivActiMusicBack'"));
    paramT.ivActiMusicPlay = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558726, "field 'ivActiMusicPlay'"), 2131558726, "field 'ivActiMusicPlay'"));
    paramT.ivActiMusicMic = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558727, "field 'ivActiMusicMic'"), 2131558727, "field 'ivActiMusicMic'"));
    paramT.ivActiMusicNext = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558728, "field 'ivActiMusicNext'"), 2131558728, "field 'ivActiMusicNext'"));
    paramT.vvActMusic = ((VisualizerView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558729, "field 'vvActMusic'"), 2131558729, "field 'vvActMusic'"));
    paramT.btActiMusList = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558730, "field 'btActiMusList'"), 2131558730, "field 'btActiMusList'"));
    paramT.btActiMusicMic = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558731, "field 'btActiMusicMic'"), 2131558731, "field 'btActiMusicMic'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleDeviceName = null;
    paramT.tvTitleViewTitle = null;
    paramT.btnTitleViewEdit = null;
    paramT.tvTitleViewEdit = null;
    paramT.ivActiMusicDisc = null;
    paramT.ivActiMusicLoader = null;
    paramT.ivActiNullLoader = null;
    paramT.tvActiMusicSongName = null;
    paramT.sbMusicActi = null;
    paramT.btMusicActGoList = null;
    paramT.tvActiMusicSongCurrentTime = null;
    paramT.tvActiMusicSongAllTime = null;
    paramT.ivActiMusicBack = null;
    paramT.ivActiMusicPlay = null;
    paramT.ivActiMusicMic = null;
    paramT.ivActiMusicNext = null;
    paramT.vvActMusic = null;
    paramT.btActiMusList = null;
    paramT.btActiMusicMic = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.MusicFragment..ViewBinder
 * JD-Core Version:    0.6.0
 */