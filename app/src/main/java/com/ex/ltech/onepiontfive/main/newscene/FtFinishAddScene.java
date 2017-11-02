package com.ex.ltech.onepiontfive.main.newscene;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.my_view.MyTimePickerView.onSelectListener;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.vo.Scene;
import com.ex.ltech.onepiontfive.main.vo.SceneStep;
import com.ex.ltech.onepiontfive.main.vo.SceneSteps;
import com.ex.ltech.onepiontfive.main.vo.SceneTouchSensor;
import com.ex.ltech.remote.control.scene.CacheSceneData;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.List;

public class FtFinishAddScene extends MyBaseFt
{
  Runnable RegetStepRunnable = new Runnable()
  {
    public void run()
    {
      if (FtFinishAddScene.this.sceneNumlist.size() > FtFinishAddScene.this.regetStepsIndexsPosi)
      {
        if (FtFinishAddScene.this.regetStepsTime >= 4)
          break label78;
        FtFinishAddScene localFtFinishAddScene2 = FtFinishAddScene.this;
        localFtFinishAddScene2.loopGetSceneTime = (-1 + localFtFinishAddScene2.loopGetSceneTime);
      }
      while (true)
      {
        FtFinishAddScene.this.loopGetStep(FtFinishAddScene.this.regetStepsIndexsPosi);
        FtFinishAddScene localFtFinishAddScene1 = FtFinishAddScene.this;
        localFtFinishAddScene1.regetStepsTime = (1 + localFtFinishAddScene1.regetStepsTime);
        return;
        label78: FtFinishAddScene.this.regetStepsTime = 0;
      }
    }
  };

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  ExpandableLvSceneBusiness business;
  int clickTimePosi;

  @Bind({2131559111})
  TextView condition;

  @Bind({2131559149})
  ImageView conditionIc;
  String conditionStr = "";
  Runnable dataRequestTimeoutRunnable = new Runnable()
  {
    public void run()
    {
      FtFinishAddScene.this.hideDataRequestDialog();
      FtFinishAddScene.this.business.setMySendListener(null);
      FtFinishAddScene.this.handler.removeCallbacks(FtFinishAddScene.this.dataRequestTimeoutRunnable);
      FtFinishAddScene.this.handler.removeCallbacks(FtFinishAddScene.this.RegetStepRunnable);
      FtFinishAddScene.this.shortToast(2131100336);
    }
  };

  @Bind({2131559147})
  ImageView delName;
  AlertDialog dialog;

  @Bind({2131559157})
  TextView etName;
  List<Integer> exitsStepIndexList = new ArrayList();
  FinishAddSceneAdapter finishAddSceneAdapter;
  View footer;
  MyBusiness.MySendListener getStepListener = new MyBusiness.MySendListener()
  {
    public void onFail()
    {
    }

    public void onOk(byte[] paramArrayOfByte)
    {
      String str1 = StringUtils.btye2Str(paramArrayOfByte);
      int j;
      int k;
      int m;
      int n;
      String str2;
      if ((str1.length() >= 50) && (str1.substring(18, 20).equalsIgnoreCase("a5")))
      {
        int i = Integer.parseInt(str1.substring(20, 22), 16);
        Integer.parseInt(str1.substring(24, 26), 16);
        j = Integer.parseInt(str1.substring(26, 28), 16);
        k = Integer.parseInt(str1.substring(28, 30), 16);
        m = Integer.parseInt(str1.substring(30, 32), 16);
        n = Integer.parseInt(str1.substring(32, 34), 16);
        str2 = str1.substring(34, 42);
        int i1;
        try
        {
          str1.substring(42, 50);
          StringUtils.bytesStr2WordStr(StringUtils.subEndZero(str1.substring(50, 98)));
          FtFinishAddScene.this.responseMessage(str1.substring(4, 6), "25");
          i1 = 0;
          for (int i2 = 0; i2 < FtFinishAddScene.this.exitsStepIndexList.size(); i2++)
          {
            if (!((Integer)FtFinishAddScene.this.exitsStepIndexList.get(i2)).equals(Integer.valueOf(k)))
              continue;
            i1 = 1;
          }
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
        if (i1 == 0)
        {
          FtFinishAddScene.this.exitsStepIndexList.add(Integer.valueOf(k));
          if (i != 38)
            break label364;
        }
      }
      label364: for (SceneStep localSceneStep = FtFinishAddScene.this.business.createSceneStep(n, str2); ; localSceneStep = FtFinishAddScene.this.business.createSceneStep(n, str2))
      {
        if (localSceneStep != null)
        {
          FtFinishAddScene.this.responseMessage(str1.substring(4, 6), "25");
          localSceneStep.setSpaceTime(m);
          FtFinishAddScene.this.business.sceneSteps.steps.add(localSceneStep);
          FragmentActivity localFragmentActivity = FtFinishAddScene.this.getActivity();
          1 local1 = new Runnable()
          {
            public void run()
            {
              FtFinishAddScene.this.finishAddSceneAdapter.notifyDataSetChanged();
              FtFinishAddScene.this.autoSetLvPaddingAndMargins();
            }
          };
          localFragmentActivity.runOnUiThread(local1);
          if (k == j)
            break;
        }
        return;
      }
      if (FtFinishAddScene.this.loopGetSceneTime < FtFinishAddScene.this.sceneNumlist.size())
      {
        FtFinishAddScene.this.loopGetStep(FtFinishAddScene.this.loopGetSceneTime);
        return;
      }
      FtFinishAddScene.this.handler.removeCallbacks(FtFinishAddScene.this.RegetStepRunnable);
      FtFinishAddScene.this.handler.removeCallbacks(FtFinishAddScene.this.dataRequestTimeoutRunnable);
      if ((FtFinishAddScene.this.dialog != null) && (FtFinishAddScene.this.dialog.isShowing()))
        FtFinishAddScene.this.dialog.dismiss();
      FtFinishAddScene.this.sceneNumlist.clear();
      FtFinishAddScene.this.business.setMySendListener(null);
    }

    public void onTimeOut()
    {
    }
  };

  @Bind({2131559160})
  ImageView go3;
  Handler handler = new Handler();

  @Bind({2131559158})
  ListView listview;
  int loopGetSceneTime = 0;
  private String mac;
  String name;
  int regetStepsIndexsPosi;
  int regetStepsTime;

  @Bind({2131559145})
  RelativeLayout rl1;

  @Bind({2131559148})
  RelativeLayout rl2;

  @Bind({2131559159})
  RelativeLayout rl3;

  @Bind({2131559162})
  RelativeLayout rlTime;
  List<Integer> sceneNumlist = new ArrayList();
  String second = "5";

  @Bind({2131559058})
  MyTimePickerView tpSecond;

  @Bind({2131559161})
  TextView tvSo;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;

  private void hideDataRequestDialog()
  {
    if ((this.dialog != null) && (this.dialog.isShowing()))
      this.dialog.dismiss();
  }

  private void initTitle()
  {
    this.rlTime.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtFinishAddScene.this.rlTime.setVisibility(8);
        FtFinishAddScene.this.business.onSpaceTimeClick(FtFinishAddScene.this.clickTimePosi, FtFinishAddScene.this.second);
        FtFinishAddScene.this.finishAddSceneAdapter.notifyDataSetChanged();
        FtFinishAddScene.this.autoSetLvPaddingAndMargins();
      }
    });
    ArrayList localArrayList = new ArrayList();
    for (int i = 5; i < 60; i++)
      localArrayList.add(i + "");
    this.tpSecond.setData(localArrayList);
    this.tpSecond.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        FtFinishAddScene.this.second = paramString;
      }
    });
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtFinishAddScene.this.business.putData4ClassName(FtFinishAddScene.this.mac, new SceneSteps());
        FtFinishAddScene.this.finish();
      }
    });
    this.tvTitleViewTitle.setText(2131099850);
    this.btnTitleViewEdit.setBackgroundResource(2130903706);
    this.btnTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtFinishAddScene.this.name = FtFinishAddScene.this.etName.getText().toString();
        if (FtFinishAddScene.this.name.getBytes().length > 24)
          Toast.makeText(FtFinishAddScene.this.getActivity(), 2131100328, 0).show();
        int i;
        Object localObject;
        while (true)
        {
          return;
          if (FtFinishAddScene.this.name.length() == 0)
            FtFinishAddScene.this.name = FtFinishAddScene.this.getString(2131100367);
          FtFinishAddScene.this.dialog = ProgressDialog.show(FtFinishAddScene.this.getActivity(), "", FtFinishAddScene.this.getString(2131100002), false);
          if (!FtFinishAddScene.this.dialog.isShowing())
            FtFinishAddScene.this.dialog.show();
          FtFinishAddScene.this.dialog.setCancelable(true);
          i = FtFinishAddScene.this.getRequest().getIntExtra("NewSceneCountKey", 0);
          if (FtFinishAddScene.this.getRequest().getIntExtra("SceneEditPosiKey", -1) != -1)
            localObject = FtFinishAddScene.this.business.getScene(FtFinishAddScene.this.name, i);
          while (true)
          {
            ((Scene)localObject).setCondition(FtFinishAddScene.this.conditionStr);
            if ((((Scene)localObject).getCondition() == null) || (((Scene)localObject).getCondition().equals("")))
            {
              Toast.makeText(FtFinishAddScene.this.getActivity(), 2131099934, 0).show();
              if (!FtFinishAddScene.this.dialog.isShowing())
                break;
              FtFinishAddScene.this.dialog.dismiss();
              return;
              try
              {
                Scene localScene = FtFinishAddScene.this.business.getScene(FtFinishAddScene.this.name, i);
                localObject = localScene;
              }
              catch (Exception localException1)
              {
                localException1.printStackTrace();
                if (FtFinishAddScene.this.dialog.isShowing())
                  FtFinishAddScene.this.dialog.dismiss();
                Toast.makeText(FtFinishAddScene.this.getActivity(), 2131099938, 0).show();
                return;
              }
            }
          }
          if (FtFinishAddScene.this.finishAddSceneAdapter.getCount() != 0)
            break;
          Toast.makeText(FtFinishAddScene.this.getActivity(), 2131099938, 0).show();
          if (!FtFinishAddScene.this.dialog.isShowing())
            continue;
          FtFinishAddScene.this.dialog.dismiss();
          return;
        }
        try
        {
          SceneTouchSensor localSceneTouchSensor = (SceneTouchSensor)FtFinishAddScene.this.business.getBean4ClassName(FtFinishAddScene.this.mac, SceneTouchSensor.class);
          int j;
          if (localSceneTouchSensor != null)
          {
            j = 1;
            if (localSceneTouchSensor.getSensors() == null)
              break label545;
          }
          label545: for (int k = 1; ; k = 0)
          {
            if ((k & j) != 0)
              ((Scene)localObject).setTouchSensors(localSceneTouchSensor);
            FtFinishAddScene.this.business.isCreateNewScene = true;
            if (FtFinishAddScene.this.getRequest().getIntExtra("SceneEditPosiKey", -1) != -1)
              FtFinishAddScene.this.business.isCreateNewScene = false;
            FtFinishAddScene.this.business.setSceneNameByte(FtFinishAddScene.this.name.getBytes());
            FtFinishAddScene.this.business.sendPreprea(i, (Scene)localObject);
            FtFinishAddScene.this.business.startLoopCreateScene((Scene)localObject);
            return;
            j = 0;
            break;
          }
        }
        catch (Exception localException2)
        {
          while (true)
            localException2.printStackTrace();
        }
      }
    });
  }

  private void loopGetStep(int paramInt)
  {
    this.regetStepsIndexsPosi = paramInt;
    synSceneStep(((Integer)this.sceneNumlist.get(paramInt)).intValue());
    this.loopGetSceneTime = (1 + this.loopGetSceneTime);
    this.handler.removeCallbacks(this.RegetStepRunnable);
    this.handler.postDelayed(this.RegetStepRunnable, 10000L);
  }

  private void responseMessage(String paramString1, String paramString2)
  {
    this.business.responseMessage(paramString1, paramString2);
  }

  private void setConditionView(String paramString)
  {
    this.condition.setText(paramString);
    if ((paramString.equals(getString(2131100083)) | paramString.equals(getString(2131100045))))
    {
      if (paramString.equals(getString(2131100083)))
        this.conditionIc.setBackgroundResource(2130903762);
      if (paramString.equals(getString(2131100045)))
        this.conditionIc.setBackgroundResource(2130903227);
    }
    while (true)
    {
      this.business.setSceneCondition(paramString);
      return;
      this.conditionIc.setBackgroundResource(2130903088);
    }
  }

  private void synSceneStep(int paramInt)
  {
    this.exitsStepIndexList.clear();
    this.business.syncSceneStep(this.getStepListener, paramInt);
  }

  public void autoSetLvPaddingAndMargins()
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams;
    FragmentActivity localFragmentActivity;
    float f;
    if ((this.listview.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
    {
      localMarginLayoutParams = (ViewGroup.MarginLayoutParams)this.listview.getLayoutParams();
      localFragmentActivity = getActivity();
      if (this.finishAddSceneAdapter.getCount() <= 0)
        break label100;
      f = 15.0F;
    }
    while (true)
    {
      localMarginLayoutParams.setMargins(0, BitmapUtils.dp2px(localFragmentActivity, f), 0, 0);
      this.listview.requestLayout();
      if (this.finishAddSceneAdapter.getCount() <= 0)
        break;
      this.listview.setPadding(0, BitmapUtils.dp2px(getActivity(), 10.0F), 0, BitmapUtils.dp2px(getActivity(), 0.0F));
      return;
      label100: f = 0.0F;
    }
    this.listview.setPadding(0, 0, 0, 0);
  }

  public void onCreateSceneFailed()
  {
    hideDataRequestDialog();
    shortToast(2131100336);
  }

  public void onCreateSceneOk(int paramInt)
  {
    if (paramInt > 8)
    {
      Toast.makeText(getActivity(), 2131100365, 0).show();
      finish();
      return;
    }
    CacheSceneData.ykVos.clear();
    this.business.saveSmartScene(this.name, paramInt, getRequest().getIntExtra("SceneEditPosiKey", -1));
    setResult(202);
    this.business.setFtFinishAddScene(null);
    if (this.dialog.isShowing())
      this.dialog.dismiss();
    finish();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968751, null);
      ButterKnife.bind(this, this.view);
      initTitle();
      this.mac = UserFerences.getUserFerences(getActivity().getApplicationContext()).getValue("GateWayMacIdKey");
      this.name = getRequest().getStringExtra("SceneNameExtraKey ");
      if (this.name.length() > 0)
        this.etName.setText(this.name);
      this.rl1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
        }
      });
      this.rl3.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (FtFinishAddScene.this.finishAddSceneAdapter.getCount() > 7)
          {
            Toast.makeText(FtFinishAddScene.this.getActivity(), 2131100414, 0).show();
            return;
          }
          FtFinishAddScene.this.startFragmentForResult(new Request(FtAddExecutiveTask.class), 200);
          FtFinishAddScene.this.business.saveStep(FtFinishAddScene.this.business.sceneSteps);
        }
      });
      this.delName.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          FtFinishAddScene.this.etName.setText("");
        }
      });
      this.business = new ExpandableLvSceneBusiness(getActivity());
      this.business.setFtFinishAddScene(this);
      if (getRequest().getIntExtra("SceneEditPosiKey", -1) != -1);
      int i = getRequest().getIntExtra("SceneNumExtraKey ", -1);
      if (i != -1)
      {
        this.loopGetSceneTime = 0;
        this.sceneNumlist.clear();
        this.sceneNumlist.add(Integer.valueOf(i));
        this.handler.removeCallbacks(this.dataRequestTimeoutRunnable);
        this.handler.postDelayed(this.dataRequestTimeoutRunnable, 1000 * (10 * this.sceneNumlist.size()));
        this.business.sceneSteps.steps.clear();
        loopGetStep(this.loopGetSceneTime);
        this.dialog = ProgressDialog.show(getActivity(), "", getString(2131100002), false);
        if (!this.dialog.isShowing())
          this.dialog.show();
        this.dialog.setCancelable(true);
      }
      this.finishAddSceneAdapter = new FinishAddSceneAdapter(this, this.business.sceneSteps);
      String str = getString(2131100083);
      this.conditionStr = str;
      setConditionView(str);
      this.listview.setAdapter(this.finishAddSceneAdapter);
      autoSetLvPaddingAndMargins();
      this.etName.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramEditable)
        {
        }

        public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
        {
        }

        public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
        {
          ImageView localImageView = FtFinishAddScene.this.delName;
          if (paramCharSequence.length() < 1);
          for (int i = 8; ; i = 0)
          {
            localImageView.setVisibility(i);
            return;
          }
        }
      });
      ImageView localImageView = this.delName;
      int j = this.etName.getText().length();
      int k = 0;
      if (j < 1)
        k = 8;
      localImageView.setVisibility(k);
      this.footer = LayoutInflater.from(getActivity()).inflate(2130968888, null);
      this.listview.addFooterView(this.footer);
      this.footer.findViewById(2131559159).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (FtFinishAddScene.this.finishAddSceneAdapter.getCount() > 7)
          {
            Toast.makeText(FtFinishAddScene.this.getActivity(), 2131100414, 0).show();
            return;
          }
          FtFinishAddScene.this.startFragmentForResult(new Request(FtAddExecutiveTask.class), 200);
          FtFinishAddScene.this.business.saveStep(FtFinishAddScene.this.business.sceneSteps);
        }
      });
    }
    ButterKnife.bind(this, this.view);
    return this.view;
  }

  void onDelStep(int paramInt)
  {
    this.business.sceneSteps.steps.remove(paramInt);
    this.finishAddSceneAdapter.notifyDataSetChanged();
    autoSetLvPaddingAndMargins();
    this.business.saveStep(this.business.sceneSteps);
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    if (this.business != null)
    {
      this.business.putData4ClassName(this.mac, new SceneSteps());
      this.business.setMySendListener(null);
      this.business.pFragmentDestroy();
    }
    this.handler.removeCallbacks(this.RegetStepRunnable);
    this.handler.removeCallbacks(this.dataRequestTimeoutRunnable);
    ButterKnife.unbind(this);
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    int i = 1;
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 210)
    {
      this.business = new ExpandableLvSceneBusiness(getActivity());
      this.business.setFtFinishAddScene(this);
      this.business.setSceneCondition(this.conditionStr);
      this.finishAddSceneAdapter = new FinishAddSceneAdapter(this, this.business.getSceneSteps());
      this.listview.setAdapter(this.finishAddSceneAdapter);
      autoSetLvPaddingAndMargins();
      this.listview.post(new Runnable()
      {
        public void run()
        {
          FtFinishAddScene.this.listview.smoothScrollToPosition(FtFinishAddScene.this.finishAddSceneAdapter.getCount());
        }
      });
    }
    int j;
    if (paramInt2 == 211)
    {
      j = i;
      if (paramInt2 != 212)
        break label153;
    }
    while (true)
    {
      if ((i | j) != 0)
      {
        this.conditionStr = paramRequest.getStringExtra("ConditionExtraKey");
        setConditionView(this.conditionStr);
      }
      return;
      j = 0;
      break;
      label153: i = 0;
    }
  }

  public void onSpaceTimeClick(int paramInt1, int paramInt2)
  {
    this.tpSecond.setSelected(paramInt2 + "");
    this.rlTime.setVisibility(0);
    this.clickTimePosi = paramInt1;
    this.second = (paramInt2 + "");
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }

  public void showSecond(int paramInt)
  {
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.FtFinishAddScene
 * JD-Core Version:    0.6.0
 */