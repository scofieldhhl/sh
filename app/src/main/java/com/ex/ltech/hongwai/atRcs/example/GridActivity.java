package com.ex.ltech.hongwai.atRcs.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import org.askerov.dynamicgrid.DynamicGridView;
import org.askerov.dynamicgrid.DynamicGridView.OnDragListener;

public class GridActivity extends Activity
{
  private static final String TAG = GridActivity.class.getName();
  private DynamicGridView gridView;

  public void onBackPressed()
  {
    if (this.gridView.isEditMode())
    {
      this.gridView.stopEditMode();
      return;
    }
    super.onBackPressed();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968646);
    this.gridView = ((DynamicGridView)findViewById(2131558793));
    this.gridView.setAdapter(new CheeseDynamicAdapter(this, new ArrayList(Arrays.asList(Cheeses.sCheeseStrings)), 3));
    this.gridView.setOnDragListener(new DynamicGridView.OnDragListener()
    {
      public void onDragPositionsChanged(int paramInt1, int paramInt2)
      {
        String str = GridActivity.TAG;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramInt1);
        arrayOfObject[1] = Integer.valueOf(paramInt2);
        Log.d(str, String.format("drag item position changed from %d to %d", arrayOfObject));
      }

      public void onDragStarted(int paramInt)
      {
        Log.d(GridActivity.TAG, "drag started at position " + paramInt);
      }
    });
    this.gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        GridActivity.this.gridView.startEditMode(paramInt);
        return true;
      }
    });
    this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        Toast.makeText(GridActivity.this, paramAdapterView.getAdapter().getItem(paramInt).toString(), 0).show();
      }
    });
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.example.GridActivity
 * JD-Core Version:    0.6.0
 */