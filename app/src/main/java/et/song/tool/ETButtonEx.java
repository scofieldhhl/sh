package et.song.tool;

import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;

public final class ETButtonEx
{
  public static final float[] ETBUTTON_NOT_SELECTED;
  public static final float[] ETBUTTON_SELECTED = { 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9F, 0.0F };
  public static final OnFocusChangeListener buttonOnFocusChangeListener;
  public static final OnTouchListener buttonOnTouchListener;

  static
  {
    ETBUTTON_NOT_SELECTED = new float[] { 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F };
    buttonOnFocusChangeListener = new OnFocusChangeListener()
    {
      public void onFocusChange(View paramView, boolean paramBoolean)
      {
        if (paramBoolean)
        {
          paramView.getBackground().setColorFilter(new ColorMatrixColorFilter(ETButtonEx.ETBUTTON_SELECTED));
          paramView.setBackgroundDrawable(paramView.getBackground());
          return;
        }
        paramView.getBackground().setColorFilter(new ColorMatrixColorFilter(ETButtonEx.ETBUTTON_NOT_SELECTED));
        paramView.setBackgroundDrawable(paramView.getBackground());
      }
    };
    buttonOnTouchListener = new OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        if (paramMotionEvent.getAction() == 0)
        {
          paramView.getBackground().setColorFilter(new ColorMatrixColorFilter(ETButtonEx.ETBUTTON_SELECTED));
          paramView.setBackgroundDrawable(paramView.getBackground());
        }
        while (true)
        {
          return false;
          if (paramMotionEvent.getAction() != 1)
            continue;
          paramView.getBackground().setColorFilter(new ColorMatrixColorFilter(ETButtonEx.ETBUTTON_NOT_SELECTED));
          paramView.setBackgroundDrawable(paramView.getBackground());
        }
      }
    };
  }

  public static final void setButton(View paramView)
  {
    paramView.getBackground().setColorFilter(new ColorMatrixColorFilter(ETBUTTON_NOT_SELECTED));
    paramView.setBackgroundDrawable(paramView.getBackground());
    paramView.setOnTouchListener(buttonOnTouchListener);
    paramView.setOnFocusChangeListener(buttonOnFocusChangeListener);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tool.ETButtonEx
 * JD-Core Version:    0.6.0
 */