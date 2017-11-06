package com.ex.ltech.onepiontfive.main.widget.pulltorefresh;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

public class PullToRefreshLayout extends RelativeLayout
{
  public static final int DONE = 5;
  public static final int FAIL = 1;
  public static final int INIT = 0;
  public static final int LOADING = 4;
  public static final int REFRESHING = 2;
  public static final int RELEASE_TO_LOAD = 3;
  public static final int RELEASE_TO_REFRESH = 1;
  public static final int SUCCEED = 0;
  public static final String TAG = "PullToRefreshLayout";
  public float MOVE_SPEED = 8.0F;
  private boolean canPullDown = true;
  private boolean canPullUp = true;
  private float downY;
  private boolean isLayout = false;
  boolean isRefresh = true;
  private boolean isTouch = false;
  private float lastY;
  private View loadStateImageView;
  private TextView loadStateTextView;
  private View loadingView;
  private float loadmoreDist = 200.0F;
  private View loadmoreView;
  private Context mContext;
  private int mEvents;
  private OnRefreshListener mListener;
  public float pullDownY = 0.0F;
  private View pullUpView;
  private float pullUpY = 0.0F;
  private View pullView;
  private View pullableView;
  private float radio = 2.0F;
  private float refreshDist = 200.0F;
  private View refreshStateImageView;
  private TextView refreshStateTextView;
  private View refreshView;
  private RotateAnimation refreshingAnimation;
  private View refreshingView;
  private RotateAnimation rotateAnimation;
  private int state = 0;
  private MyTimer timer;
  Handler updateHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      PullToRefreshLayout.this.MOVE_SPEED = (float)(8.0D + 5.0D * Math.tan(1.570796326794897D / PullToRefreshLayout.this.getMeasuredHeight() * (PullToRefreshLayout.this.pullDownY + Math.abs(PullToRefreshLayout.this.pullUpY))));
      if (!PullToRefreshLayout.this.isTouch)
      {
        if ((PullToRefreshLayout.this.state == 2) && (PullToRefreshLayout.this.pullDownY <= PullToRefreshLayout.this.refreshDist))
        {
          PullToRefreshLayout.this.pullDownY = PullToRefreshLayout.this.refreshDist;
          PullToRefreshLayout.this.timer.cancel();
        }
      }
      else
      {
        if (PullToRefreshLayout.this.pullDownY <= 0.0F)
          break label413;
        PullToRefreshLayout localPullToRefreshLayout = PullToRefreshLayout.this;
        localPullToRefreshLayout.pullDownY -= PullToRefreshLayout.this.MOVE_SPEED;
      }
      while (true)
      {
        if (PullToRefreshLayout.this.pullDownY < 0.0F)
        {
          PullToRefreshLayout.this.pullDownY = 0.0F;
          PullToRefreshLayout.this.pullView.clearAnimation();
          if ((PullToRefreshLayout.this.state != 2) && (PullToRefreshLayout.this.state != 4))
            PullToRefreshLayout.this.changeState(0);
          PullToRefreshLayout.this.timer.cancel();
          PullToRefreshLayout.this.requestLayout();
        }
        if (PullToRefreshLayout.this.pullUpY > 0.0F)
        {
          PullToRefreshLayout.access$002(PullToRefreshLayout.this, 0.0F);
          PullToRefreshLayout.this.pullUpView.clearAnimation();
          if ((PullToRefreshLayout.this.state != 2) && (PullToRefreshLayout.this.state != 4))
            PullToRefreshLayout.this.changeState(0);
          PullToRefreshLayout.this.timer.cancel();
          PullToRefreshLayout.this.requestLayout();
        }
        Log.d("handle", "handle");
        PullToRefreshLayout.this.requestLayout();
        if (PullToRefreshLayout.this.pullDownY + Math.abs(PullToRefreshLayout.this.pullUpY) == 0.0F)
          PullToRefreshLayout.this.timer.cancel();
        return;
        if ((PullToRefreshLayout.this.state != 4) || (-PullToRefreshLayout.this.pullUpY > PullToRefreshLayout.this.loadmoreDist))
          break;
        PullToRefreshLayout.access$002(PullToRefreshLayout.this, -PullToRefreshLayout.this.loadmoreDist);
        PullToRefreshLayout.this.timer.cancel();
        break;
        label413: if (PullToRefreshLayout.this.pullUpY >= 0.0F)
          continue;
        PullToRefreshLayout.access$002(PullToRefreshLayout.this, PullToRefreshLayout.this.pullUpY + PullToRefreshLayout.this.MOVE_SPEED);
      }
    }
  };

  public PullToRefreshLayout(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public PullToRefreshLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  public PullToRefreshLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initView(paramContext);
  }

  private void changeState(int paramInt)
  {
    this.state = paramInt;
    switch (this.state)
    {
    default:
      return;
    case 0:
      this.refreshStateImageView.setVisibility(View.GONE);
      this.refreshStateTextView.setText(2131100281);
      this.pullView.clearAnimation();
      this.pullView.setVisibility(View.VISIBLE);
      this.loadStateImageView.setVisibility(View.GONE);
      this.loadStateTextView.setText(2131100282);
      this.pullUpView.clearAnimation();
      this.pullUpView.setVisibility(View.VISIBLE);
      return;
    case 1:
      this.refreshStateTextView.setText(2131100325);
      this.pullView.startAnimation(this.rotateAnimation);
      return;
    case 2:
      this.pullView.clearAnimation();
      this.refreshingView.setVisibility(View.VISIBLE);
      this.pullView.setVisibility(4);
      this.refreshingView.startAnimation(this.refreshingAnimation);
      this.refreshStateTextView.setText(2131100319);
      return;
    case 3:
      this.loadStateTextView.setText(2131100324);
      this.pullUpView.startAnimation(this.rotateAnimation);
      return;
    case 4:
    }
    this.pullUpView.clearAnimation();
    this.loadingView.setVisibility(View.VISIBLE);
    this.pullUpView.setVisibility(4);
    this.loadingView.startAnimation(this.refreshingAnimation);
    this.loadStateTextView.setText(2131100152);
  }

  private void hide()
  {
    this.timer.schedule(5L);
  }

  private void initView()
  {
    this.pullView = this.refreshView.findViewById(2131559459);
    this.refreshStateTextView = ((TextView)this.refreshView.findViewById(2131559461));
    this.refreshingView = this.refreshView.findViewById(2131559460);
    this.refreshStateImageView = this.refreshView.findViewById(2131559462);
    this.pullUpView = this.loadmoreView.findViewById(2131559371);
    this.loadStateTextView = ((TextView)this.loadmoreView.findViewById(2131559373));
    this.loadingView = this.loadmoreView.findViewById(2131559372);
    this.loadStateImageView = this.loadmoreView.findViewById(2131559374);
  }

  private void initView(Context paramContext)
  {
    this.mContext = paramContext;
    this.timer = new MyTimer(this.updateHandler);
    this.rotateAnimation = ((RotateAnimation)AnimationUtils.loadAnimation(paramContext, 2131034126));
    this.refreshingAnimation = ((RotateAnimation)AnimationUtils.loadAnimation(paramContext, 2131034128));
    LinearInterpolator localLinearInterpolator = new LinearInterpolator();
    this.rotateAnimation.setInterpolator(localLinearInterpolator);
    this.refreshingAnimation.setInterpolator(localLinearInterpolator);
  }

  private void releasePull()
  {
    this.canPullDown = true;
    this.canPullUp = true;
  }

  public void autoLoad()
  {
    this.pullUpY = (-this.loadmoreDist);
    requestLayout();
    changeState(4);
    if (this.mListener != null)
      this.mListener.onLoadMore(this);
  }

  public void autoRefresh()
  {
    AutoRefreshAndLoadTask localAutoRefreshAndLoadTask = new AutoRefreshAndLoadTask(null);
    Integer[] arrayOfInteger = new Integer[1];
    arrayOfInteger[0] = Integer.valueOf(1);
    localAutoRefreshAndLoadTask.execute(arrayOfInteger);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.isRefresh);
    switch (paramMotionEvent.getActionMasked())
    {
    case 3:
    case 4:
    default:
    case 0:
    case 5:
    case 6:
      while (true)
      {
        super.dispatchTouchEvent(paramMotionEvent);
        return true;
        this.downY = paramMotionEvent.getY();
        this.lastY = this.downY;
        this.timer.cancel();
        this.mEvents = 0;
        releasePull();
        continue;
        this.mEvents = -1;
      }
    case 2:
      if (this.mEvents == 0)
        if ((this.pullDownY > 0.0F) || ((((Pullable)this.pullableView).canPullDown()) && (this.canPullDown) && (this.state != 4)))
        {
          this.pullDownY += (paramMotionEvent.getY() - this.lastY) / this.radio;
          System.out.println(this.pullDownY);
          if (this.pullDownY < 0.0F)
          {
            this.pullDownY = 0.0F;
            this.canPullDown = false;
            this.canPullUp = true;
          }
          if (this.pullDownY > getMeasuredHeight())
            this.pullDownY = getMeasuredHeight();
          if (this.state == 2)
            this.isTouch = true;
          label241: this.lastY = paramMotionEvent.getY();
          this.radio = (float)(2.0D + 2.0D * Math.tan(1.570796326794897D / getMeasuredHeight() * (this.pullDownY + Math.abs(this.pullUpY))));
          if ((this.pullDownY > 0.0F) || (this.pullUpY < 0.0F))
            requestLayout();
          if (this.pullDownY <= 0.0F)
            break label572;
          if ((this.pullDownY <= this.refreshDist) && ((this.state == 1) || (this.state == 5)))
            changeState(0);
          if ((this.pullDownY >= this.refreshDist) && (this.state == 0))
            changeState(1);
        }
      while (true)
      {
        System.out.println("pullDownY = " + this.pullDownY);
        if (this.pullDownY + Math.abs(this.pullUpY) <= 8.0F)
          break;
        paramMotionEvent.setAction(3);
        break;
        if ((this.pullUpY < 0.0F) || ((((Pullable)this.pullableView).canPullUp()) && (this.canPullUp) && (this.state != 2)))
        {
          this.pullUpY += (paramMotionEvent.getY() - this.lastY) / this.radio;
          if (this.pullUpY > 0.0F)
          {
            this.pullUpY = 0.0F;
            this.canPullDown = true;
            this.canPullUp = false;
          }
          if (this.pullUpY < -getMeasuredHeight())
            this.pullUpY = (-getMeasuredHeight());
          if (this.state != 4)
            break label241;
          this.isTouch = true;
          break label241;
        }
        releasePull();
        break label241;
        this.mEvents = 0;
        break label241;
        label572: if (this.pullUpY >= 0.0F)
          continue;
        if ((-this.pullUpY <= this.loadmoreDist) && ((this.state == 3) || (this.state == 5)))
          changeState(0);
        if ((-this.pullUpY < this.loadmoreDist) || (this.state != 0))
          continue;
        changeState(3);
      }
    case 1:
    }
    if ((this.pullDownY > this.refreshDist) || (-this.pullUpY > this.loadmoreDist))
      this.isTouch = false;
    if (this.state == 1)
    {
      changeState(2);
      if (this.mListener != null)
        this.mListener.onRefresh(this);
    }
    while (true)
    {
      hide();
      break;
      if (this.state != 3)
        continue;
      changeState(4);
      if (this.mListener == null)
        continue;
      this.mListener.onLoadMore(this);
    }
  }

  public void loadmoreFinish(int paramInt)
  {
    this.loadingView.clearAnimation();
    this.loadingView.setVisibility(View.GONE);
    switch (paramInt)
    {
    default:
      this.loadStateImageView.setVisibility(View.VISIBLE);
      this.loadStateTextView.setText(R.string.load_fail);
      this.loadStateImageView.setBackgroundResource(2130903206);
    case 0:
    }
    while (this.pullUpY < 0.0F)
    {
      new Handler()
      {
        public void handleMessage(Message paramMessage)
        {
          PullToRefreshLayout.this.changeState(5);
          PullToRefreshLayout.this.hide();
        }
      }
      .sendEmptyMessageDelayed(0, 1000L);
      return;
      this.loadStateImageView.setVisibility(View.VISIBLE);
      this.loadStateTextView.setText(2131100151);
      this.refreshStateTextView.setText(2131100318);
    }
    changeState(5);
    hide();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Log.d("Test", "Test");
    if (!this.isLayout)
    {
      this.refreshView = getChildAt(0);
      this.pullableView = getChildAt(1);
      this.loadmoreView = getChildAt(2);
      this.isLayout = true;
      initView();
      this.refreshDist = ((ViewGroup)this.refreshView).getChildAt(0).getMeasuredHeight();
      this.loadmoreDist = ((ViewGroup)this.loadmoreView).getChildAt(0).getMeasuredHeight();
    }
    this.refreshView.layout(0, (int)(this.pullDownY + this.pullUpY) - this.refreshView.getMeasuredHeight(), this.refreshView.getMeasuredWidth(), (int)(this.pullDownY + this.pullUpY));
    this.pullableView.layout(0, (int)(this.pullDownY + this.pullUpY), this.pullableView.getMeasuredWidth(), (int)(this.pullDownY + this.pullUpY) + this.pullableView.getMeasuredHeight());
    this.loadmoreView.layout(0, (int)(this.pullDownY + this.pullUpY) + this.pullableView.getMeasuredHeight(), this.loadmoreView.getMeasuredWidth(), (int)(this.pullDownY + this.pullUpY) + this.pullableView.getMeasuredHeight() + this.loadmoreView.getMeasuredHeight());
  }

  public void refreshFinish(int paramInt)
  {
    try
    {
      this.refreshingView.clearAnimation();
      this.refreshingView.setVisibility(View.GONE);
      switch (paramInt)
      {
      default:
        this.refreshStateImageView.setVisibility(View.VISIBLE);
        this.refreshStateTextView.setText(2131100316);
      case 0:
      }
      while (this.pullDownY > 0.0F)
      {
        new Handler()
        {
          public void handleMessage(Message paramMessage)
          {
            PullToRefreshLayout.this.changeState(5);
            PullToRefreshLayout.this.hide();
          }
        }
        .sendEmptyMessageDelayed(0, 1000L);
        return;
        this.refreshStateImageView.setVisibility(View.VISIBLE);
        this.refreshStateTextView.setText(2131100318);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    changeState(5);
    hide();
  }

  public void setCanRefresh(boolean paramBoolean)
  {
    this.isRefresh = paramBoolean;
  }

  public void setOnRefreshListener(OnRefreshListener paramOnRefreshListener)
  {
    this.mListener = paramOnRefreshListener;
  }

  private class AutoRefreshAndLoadTask extends AsyncTask<Integer, Float, String>
  {
    private AutoRefreshAndLoadTask()
    {
    }

    protected String doInBackground(Integer[] paramArrayOfInteger)
    {
      while (PullToRefreshLayout.this.pullDownY < 1.0F * PullToRefreshLayout.this.refreshDist)
      {
        PullToRefreshLayout localPullToRefreshLayout = PullToRefreshLayout.this;
        localPullToRefreshLayout.pullDownY += PullToRefreshLayout.this.MOVE_SPEED;
        Float[] arrayOfFloat = new Float[1];
        arrayOfFloat[0] = Float.valueOf(PullToRefreshLayout.this.pullDownY);
        publishProgress(arrayOfFloat);
      }
      return null;
    }

    protected void onPostExecute(String paramString)
    {
      PullToRefreshLayout.this.changeState(2);
      if (PullToRefreshLayout.this.mListener != null)
        PullToRefreshLayout.this.mListener.onRefresh(PullToRefreshLayout.this);
      PullToRefreshLayout.this.hide();
    }

    protected void onProgressUpdate(Float[] paramArrayOfFloat)
    {
      if (PullToRefreshLayout.this.pullDownY > PullToRefreshLayout.this.refreshDist)
        PullToRefreshLayout.this.changeState(1);
      PullToRefreshLayout.this.requestLayout();
    }
  }

  class MyTimer
  {
    private Handler handler;
    private MyTask mTask;
    private Timer timer;

    public MyTimer(Handler arg2)
    {
      Object localObject;
      this.handler = localObject;
      this.timer = new Timer();
    }

    public void cancel()
    {
      if (this.mTask != null)
      {
        this.mTask.cancel();
        this.mTask = null;
      }
    }

    public void schedule(long paramLong)
    {
      if (this.mTask != null)
      {
        this.mTask.cancel();
        this.mTask = null;
      }
      this.mTask = new MyTask(this.handler);
      this.timer.schedule(this.mTask, 0L, paramLong);
    }

    class MyTask extends TimerTask
    {
      private Handler handler;

      public MyTask(Handler arg2)
      {
        Object localObject;
        this.handler = localObject;
      }

      public void run()
      {
        this.handler.obtainMessage().sendToTarget();
      }
    }
  }

  public static abstract interface OnRefreshListener
  {
    public abstract void onLoadMore(PullToRefreshLayout paramPullToRefreshLayout);

    public abstract void onRefresh(PullToRefreshLayout paramPullToRefreshLayout);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.widget.pulltorefresh.PullToRefreshLayout
 * JD-Core Version:    0.6.0
 */