package com.ex.ltech.hongwai.view.dslv;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;

public class SimpleFloatViewManager
  implements DragSortListView.FloatViewManager
{
  private int mFloatBGColor = -1;
  private Bitmap mFloatBitmap;
  private ImageView mImageView;
  private ListView mListView;

  public SimpleFloatViewManager(ListView paramListView)
  {
    this.mListView = paramListView;
  }

  public View onCreateFloatView(int paramInt)
  {
    View localView = this.mListView.getChildAt(paramInt + this.mListView.getHeaderViewsCount() - this.mListView.getFirstVisiblePosition());
    if (localView == null)
      return null;
    localView.setPressed(false);
    localView.setDrawingCacheEnabled(true);
    this.mFloatBitmap = Bitmap.createBitmap(localView.getDrawingCache());
    localView.setDrawingCacheEnabled(false);
    if (this.mImageView == null)
      this.mImageView = new ImageView(this.mListView.getContext());
    this.mImageView.setPadding(0, 0, 0, 0);
    this.mImageView.setImageBitmap(this.mFloatBitmap);
    this.mImageView.setLayoutParams(new ViewGroup.LayoutParams(localView.getWidth(), localView.getHeight()));
    return this.mImageView;
  }

  public void onDestroyFloatView(View paramView)
  {
    ((ImageView)paramView).setImageDrawable(null);
    this.mFloatBitmap.recycle();
    this.mFloatBitmap = null;
  }

  public void onDragFloatView(View paramView, Point paramPoint1, Point paramPoint2)
  {
  }

  public void setBackgroundColor(int paramInt)
  {
    this.mFloatBGColor = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.dslv.SimpleFloatViewManager
 * JD-Core Version:    0.6.0
 */