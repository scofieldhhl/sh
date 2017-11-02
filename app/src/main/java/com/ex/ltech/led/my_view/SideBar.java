package com.ex.ltech.led.my_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

@SuppressLint({"DrawAllocation"})
public class SideBar extends View
{
  private char[] l;
  private ListView list;
  private TextView mDialogText;
  Paint paint = new Paint();
  private SectionIndexer sectionIndexter = null;

  public SideBar(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public SideBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public SideBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  private void init()
  {
    this.l = new char[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 };
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = getHeight();
    int j = getWidth();
    int k = i / this.l.length;
    for (int m = 0; m < this.l.length; m++)
    {
      this.paint.setColor(-16777216);
      this.paint.setTypeface(Typeface.DEFAULT_BOLD);
      this.paint.setAntiAlias(true);
      this.paint.setTextSize(30.0F);
      float f1 = j / 2 - this.paint.measureText(String.valueOf(this.l[m])) / 2.0F;
      float f2 = k + k * m;
      paramCanvas.drawText(String.valueOf(this.l[m]), f1, f2, this.paint);
      this.paint.reset();
    }
  }

  @SuppressLint({"ClickableViewAccessibility"})
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    super.onTouchEvent(paramMotionEvent);
    int i = (int)(paramMotionEvent.getY() / getHeight() * this.l.length);
    if (i >= this.l.length)
    {
      i = -1 + this.l.length;
      if ((paramMotionEvent.getAction() != 0) && (paramMotionEvent.getAction() != 2))
        break label169;
      this.mDialogText.setVisibility(0);
      this.mDialogText.setText("" + this.l[i]);
      if (this.sectionIndexter == null)
        this.sectionIndexter = ((SectionIndexer)this.list.getAdapter());
      if (this.sectionIndexter != null)
        break label135;
    }
    label135: int j;
    do
    {
      return true;
      if (i >= 0)
        break;
      i = 0;
      break;
      j = this.sectionIndexter.getPositionForSection(this.l[i]);
    }
    while (j == -1);
    this.list.setSelection(j);
    return true;
    label169: this.mDialogText.setVisibility(4);
    return true;
  }

  public void setListView(ListView paramListView)
  {
    this.list = paramListView;
    this.sectionIndexter = ((SectionIndexer)paramListView.getAdapter());
  }

  public void setTextView(TextView paramTextView)
  {
    this.mDialogText = paramTextView;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.SideBar
 * JD-Core Version:    0.6.0
 */