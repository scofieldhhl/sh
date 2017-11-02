package et.song.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ETLoadDialog extends Dialog
{
  Context mContext;

  public ETLoadDialog(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  public ETLoadDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.mContext = paramContext;
    init();
  }

  private void init()
  {
    LinearLayout localLinearLayout = new LinearLayout(this.mContext);
    localLinearLayout.setMinimumHeight(48);
    localLinearLayout.setGravity(17);
    localLinearLayout.setOrientation(0);
    ImageView localImageView = new ImageView(this.mContext);
    localImageView.setImageResource(17301629);
    Animation localAnimation = AnimationUtils.loadAnimation(this.mContext, 2131034127);
    localAnimation.setInterpolator(new LinearInterpolator());
    localImageView.setAnimation(localAnimation);
    localLinearLayout.addView(localImageView);
    setContentView(localLinearLayout);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4);
    return true;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.dialog.ETLoadDialog
 * JD-Core Version:    0.6.0
 */