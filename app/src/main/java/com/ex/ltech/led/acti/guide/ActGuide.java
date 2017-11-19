package com.ex.ltech.led.acti.guide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ex.ltech.led.R;

public class ActGuide extends AppCompatActivity
{
  static final int NUM_PAGES = 6;
  LinearLayout circles;
  boolean isOpaque = true;
  ViewPager pager;
  PagerAdapter pagerAdapter;

  private void buildCircles()
  {
    this.circles = ((LinearLayout)LinearLayout.class.cast(findViewById(R.id.circles)));
    int i = (int)(0.5F + 5.0F * getResources().getDisplayMetrics().density);
    for (int j = 0; j < 5; j++)
    {
      ImageView localImageView = new ImageView(this);
      /*localImageView.setImageResource(R.drawable.ic_swipe_indicator_white_18dp);*/
      localImageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
      localImageView.setAdjustViewBounds(true);
      localImageView.setPadding(i, 0, i, 0);
      this.circles.addView(localImageView);
    }
    setIndicator(0);
  }

  private void endTutorial()
  {
    goMain(null);
    finish();
    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
  }

  private void setIndicator(int paramInt)
  {
    if (paramInt < 6)
    {
      int i = 0;
      if (i < 5)
      {
        ImageView localImageView = (ImageView)this.circles.getChildAt(i);
        if (i == paramInt)
          localImageView.setColorFilter(getResources().getColor(R.color.text_selected));
        while (true)
        {
          i++;
          break;
          /*localImageView.setColorFilter(getResources().getColor(17170445));*/
        }
      }
    }
  }

  public void goMain(View paramView)
  {
    /*if (UserFerences.getUserFerences(this).spFerences.getInt("isFirstOpen", -1) == -1)
    {
      UserFerences.getUserFerences(this).putValue("isFirstOpen", Integer.valueOf(0));
      startActivity(new Intent(this, ActLoginActivity.class));
    }
    finish();*/
  }

  public void onBackPressed()
  {
    if (this.pager.getCurrentItem() == 0)
    {
      super.onBackPressed();
      return;
    }
    this.pager.setCurrentItem(-1 + this.pager.getCurrentItem());
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
//    getWindow().setFlags(67108864, 67108864);
    setContentView(R.layout.activity_tutorial);
    this.pager = ((ViewPager)findViewById(R.id.pager));
    this.pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
    this.pager.setAdapter(this.pagerAdapter);
    this.pager.setPageTransformer(true, new CrossfadePageTransformer());
    this.pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramInt)
      {
      }

      public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
      {
        if ((paramInt1 == 4) && (paramFloat > 0.0F))
          if (ActGuide.this.isOpaque)
          {
            ActGuide.this.pager.setBackgroundColor(0);
            ActGuide.this.isOpaque = false;
          }
        do {
          ActGuide.this.pager.setBackgroundColor(ActGuide.this.getResources().getColor(R.color.primary_material_light));
          ActGuide.this.isOpaque = true;
        }while (ActGuide.this.isOpaque);

      }

      public void onPageSelected(int paramInt)
      {
        ActGuide.this.setIndicator(paramInt);
        if (paramInt == 5)
          ActGuide.this.endTutorial();
      }
    });
    buildCircles();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (this.pager != null);
  }

  public class CrossfadePageTransformer
    implements ViewPager.PageTransformer
  {
    public CrossfadePageTransformer()
    {
    }

    public void transformPage(View paramView, float paramFloat)
    {
      paramView.getWidth();
    }
  }

  private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
  {
    public ScreenSlidePagerAdapter(FragmentManager arg2)
    {
      super(arg2);
    }

    public int getCount()
    {
      return 6;
    }

    public Fragment getItem(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return null;
      case 0:
        return ProductTourFragment.newInstance(2130968895);
      case 1:
        return ProductTourFragment.newInstance(2130968896);
      case 2:
        return ProductTourFragment.newInstance(2130968897);
      case 3:
        return ProductTourFragment.newInstance(2130968898);
      case 4:
        return ProductTourFragment.newInstance(2130968899);
      case 5:
      }
      return ProductTourFragment.newInstance(2130968900);
    }
  }
}
