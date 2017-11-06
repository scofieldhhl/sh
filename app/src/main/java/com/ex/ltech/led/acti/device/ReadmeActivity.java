package com.ex.ltech.led.acti.device;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.ex.ltech.led.acti.MyBaseActivity;

public class ReadmeActivity extends MyBaseActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968617);
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleTextRes(R.string.guide);
    findViewById(2131558612).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(ReadmeActivity.this.getApplicationContext(), ActSimpleReadmeInfoActivity.class);
        localIntent.putExtra("page", 1);
        ReadmeActivity.this.startActivity(localIntent);
      }
    });
    findViewById(2131558613).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(ReadmeActivity.this.getApplicationContext(), ActSimpleReadmeInfoActivity.class);
        localIntent.putExtra("page", 2);
        ReadmeActivity.this.startActivity(localIntent);
      }
    });
    findViewById(2131558614).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(ReadmeActivity.this.getApplicationContext(), ActSimpleReadmeInfoActivity.class);
        localIntent.putExtra("page", 3);
        ReadmeActivity.this.startActivity(localIntent);
      }
    });
    findViewById(2131558615).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(ReadmeActivity.this.getApplicationContext(), ActSimpleReadmeInfoActivity.class);
        localIntent.putExtra("page", 4);
        ReadmeActivity.this.startActivity(localIntent);
      }
    });
    findViewById(2131558616).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(ReadmeActivity.this.getApplicationContext(), ActSimpleReadmeInfoActivity.class);
        localIntent.putExtra("page", 5);
        ReadmeActivity.this.startActivity(localIntent);
      }
    });
    findViewById(2131558617).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(ReadmeActivity.this.getApplicationContext(), ActSimpleReadmeInfoActivity.class);
        localIntent.putExtra("page", 6);
        ReadmeActivity.this.startActivity(localIntent);
      }
    });
    findViewById(2131558618).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(ReadmeActivity.this.getApplicationContext(), ActSimpleReadmeInfoActivity.class);
        localIntent.putExtra("page", 7);
        ReadmeActivity.this.startActivity(localIntent);
      }
    });
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void readme1(View paramView)
  {
    Intent localIntent = new Intent(this, ActSimpleReadmeInfoActivity.class);
    localIntent.putExtra("page", 1);
    startActivity(localIntent);
  }

  public void readme2(View paramView)
  {
    Intent localIntent = new Intent(this, ActSimpleReadmeInfoActivity.class);
    localIntent.putExtra("page", 2);
    startActivity(localIntent);
  }

  public void readme3(View paramView)
  {
    Intent localIntent = new Intent(this, ActSimpleReadmeInfoActivity.class);
    localIntent.putExtra("page", 3);
    startActivity(localIntent);
  }

  public void readme4(View paramView)
  {
    Intent localIntent = new Intent(this, ActSimpleReadmeInfoActivity.class);
    localIntent.putExtra("page", 4);
    startActivity(localIntent);
  }

  public void readme5(View paramView)
  {
    Intent localIntent = new Intent(this, ActSimpleReadmeInfoActivity.class);
    localIntent.putExtra("page", 5);
    startActivity(localIntent);
  }

  public void readme6(View paramView)
  {
    Intent localIntent = new Intent(this, ActSimpleReadmeInfoActivity.class);
    localIntent.putExtra("page", 6);
    startActivity(localIntent);
  }

  public void readme7(View paramView)
  {
    Intent localIntent = new Intent(this, ActSimpleReadmeInfoActivity.class);
    localIntent.putExtra("page", 7);
    startActivity(localIntent);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.ReadmeActivity
 * JD-Core Version:    0.6.0
 */