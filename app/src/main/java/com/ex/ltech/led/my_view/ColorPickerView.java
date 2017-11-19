package com.ex.ltech.led.my_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ex.ltech.led.R;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.vo.TouchArea;

import java.util.ArrayList;
import java.util.List;

public class ColorPickerView extends View
{
  private static final int FACTOR = 2;
  private int actionMoveTime;
  private Bitmap bigGroupBM;
  private Paint bitmapPaint;
  BitmapFactory.Options bmOptions;
  private int brightness;
  private Bitmap colorBM;
  private Bitmap colorTitleBM;
  private Context context;
  private int curPikerColor;
  private Bitmap currentBM = null;
  private int distance;
  private int doubleMoveX;
  private int doubleMoveY;
  int drawTime = 0;
  private int flag = 1;
  boolean frishMeasure;
  private Paint generalP;
  private int glassCenterX;
  private int glassCenterY;
  private Paint glassPaint;
  private Bitmap glassPikerBM;
  private int glassRadius;
  private List<TouchArea> groupAreaList = new ArrayList();
  private Bitmap groupBM;
  private int groupCircleColor = getResources().getColor(R.color.group_color_gone);
  private Point groupPiont = new Point();
  public int height;
  int i = 0;
  private int in = 0;
  boolean isActionDown;
  private boolean isActionMove = true;
  public boolean isFirshShotBm = true;
  private boolean isGlassShow;
  private boolean isGroupSeleted = false;
  public boolean isRainbowBg = true;
  private float k;
  private int lastX;
  private int lastY;
  List<String> lightNums = new ArrayList();
  private OnColorChangedListener listener;
  private int mCurrentX;
  private int mCurrentY;
  private Matrix mMatrix = new Matrix();
  private Path mPath = new Path();
  private OnNoPickerSeletedListener onNoPickerSeletedListener = null;
  TouchArea originalTouchArea;
  private int pActHeight;
  private Point pickerPonit1;
  private Point pickerPonit2;
  private Point pickerPonit3;
  private Point pickerPonit4;
  public int pickerX1;
  public int pickerX2;
  public int pickerX3;
  public int pickerX4;
  public int pikerAraeHight;
  private Bitmap pikerBM;
  private int pikerBMHeight;
  private int pikerBMWidth;
  public List<Integer> pikerOfGroup = new ArrayList();
  private int pikerOfGroupCount = 0;
  private Bitmap pikerSeletedBM;
  public Bitmap rainbowBm;
  private int sacleBmX;
  private int sacleBmY;
  private int seletedPickerIndex = -1;
  private Bitmap sellPhoneBm = null;
  public Bitmap shotBM;
  public Bitmap shotBm4SellPhone;
  private boolean show;
  private final int step = 40;
  private int targetX;
  private int targetY;
  private int tempPikerOfGroupCount = 0;
  public Bitmap tempShotBM;
  private Paint textPaint;
  private Paint textPaint1;
  private Paint textPaint2;
  private Paint textPaint3;
  private Paint textPaint4;
  private int thumbX;
  private int thumbY;
  int top;
  private int touchActionDownX;
  private int touchActionDownY;
  private List<TouchArea> touchAreaList = new ArrayList();
  public int width;

  public ColorPickerView(Context paramContext)
  {
    super(paramContext);
  }

  public ColorPickerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public ColorPickerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.context = paramContext;
  }

  private void blowUp(int paramInt1, int paramInt2)
  {
    this.mCurrentX = paramInt1;
    this.mCurrentY = paramInt2;
  }

  private void changePaintColor(int paramInt1, int paramInt2)
  {
    /*int j = -16777216;
    Paint localPaint1 = this.glassPaint;
    if (paramInt1 > 128);
    for (int m = j; ; m = -1)
    {
      localPaint1.setColor(m);
      switch (paramInt2 + 1)
      {
      default:
        return;
      case 1:
      case 2:
      case 3:
      case 4:
      }
    }
    Paint localPaint5 = this.textPaint1;
    if (paramInt1 > 128);
    while (true)
    {
      localPaint5.setColor(j);
      return;
      j = -1;
    }
    Paint localPaint4 = this.textPaint2;
    if (paramInt1 > 128);
    while (true)
    {
      localPaint4.setColor(j);
      return;
      j = -1;
    }
    Paint localPaint3 = this.textPaint3;
    if (paramInt1 > 128);
    while (true)
    {
      localPaint3.setColor(j);
      return;
      j = -1;
    }
    Paint localPaint2 = this.textPaint4;
    if (paramInt1 > 128);
    while (true)
    {
      localPaint2.setColor(j);
      return;
      j = -1;
    }*/
  }

  private void cleanShot()
  {
    this.isGlassShow = false;
    this.shotBM = null;
    invalidate();
  }

  private void closeGroup()
  {
  }

  private int getBrightness(int paramInt1, int paramInt2)
  {
    return 64;
  }

  private void handleActionDown(int paramInt1, int paramInt2)
  {
    this.isActionDown = true;
    hidePikerOnGlassOpen(paramInt1 - this.pikerBMWidth / 2 + this.width, paramInt2 - this.pikerBMHeight / 2, this.seletedPickerIndex);
    shot();
    this.tempPikerOfGroupCount = this.pikerOfGroupCount;
    this.touchActionDownX = paramInt1;
    this.touchActionDownY = paramInt2;
    this.seletedPickerIndex = 1;
    this.actionMoveTime = 0;
    this.isActionMove = false;
    handleActionMove(paramInt1, paramInt2);
    onTouchPoint(paramInt1, paramInt2);
    if (this.listener != null)
      this.listener.onSeletedStart(paramInt1, paramInt2, this.seletedPickerIndex);
  }

  private void handleActionMove(int paramInt1, int paramInt2)
  {
    /*int j;
    int m;
    int n;
    int i1;
    if (this.seletedPickerIndex != -1)
    {
      hidePikerOnGlassOpen(paramInt1 - this.pikerBMWidth / 2 + this.width, paramInt2 - this.pikerBMHeight / 2, this.seletedPickerIndex);
      this.mCurrentX = paramInt1;
      this.mCurrentY = paramInt2;
      if (paramInt2 < this.glassRadius / 2)
        break label255;
      this.curPikerColor = this.currentBM.getPixel(paramInt1, paramInt2 - this.glassRadius / 2);
      this.brightness = getBrightness(paramInt1, paramInt2 - this.glassRadius / 2);
      j = Color.red(this.curPikerColor);
      m = Color.green(this.curPikerColor);
      n = Color.blue(this.curPikerColor);
      changePaintColor(this.brightness, this.seletedPickerIndex);
      this.i = (1 + this.i);
      i1 = this.i;
      if (!Main.myService.getIsPlay())
        break label281;
    }
    label281: for (int i2 = 20; ; i2 = 3)
    {
      if (i1 % i2 == 0)
      {
        this.listener.onColorChange(this.curPikerColor);
        this.listener.onColorChange(j, m, n);
        this.listener.onColorChange(j, m, n, this.brightness);
        this.listener.onBrightnessChange(this.brightness);
      }
      this.actionMoveTime = (1 + this.actionMoveTime);
      if (this.actionMoveTime > 2)
        this.isActionMove = true;
      invalidate();
      return;
      label255: this.curPikerColor = this.currentBM.getPixel(paramInt1, paramInt2);
      this.brightness = getBrightness(paramInt1, paramInt2);
      break;
    }*/
  }

  private void handleActionUp(int paramInt1, int paramInt2)
  {
    /* if (this.seletedPickerIndex != -1)
    {
      this.actionMoveTime = 0;
      showPikerOnGlassClose(paramInt1 - this.pikerBMWidth / 2, paramInt2 - this.pikerBMHeight / 2, this.seletedPickerIndex);
      onPikerUp(paramInt1, paramInt2, this.seletedPickerIndex);
      invalidate();
      updatePikerAndGroupArea(paramInt1 - this.pikerBMWidth / 2, paramInt2 - this.pikerBMHeight / 2, this.seletedPickerIndex);
      changePaintColor(getBrightness(paramInt1 + this.pikerBMWidth / 2, paramInt2 + this.pikerBMHeight / 2), this.seletedPickerIndex);
      if (paramInt2 < this.glassRadius / 2)
        break label247;
      this.curPikerColor = this.currentBM.getPixel(paramInt1, paramInt2 - this.glassRadius / 2);
    }
    for (this.brightness = getBrightness(paramInt1, paramInt2 - this.glassRadius / 2); ; this.brightness = getBrightness(paramInt1, paramInt2))
    {
      int j = Color.red(this.curPikerColor);
      int m = Color.green(this.curPikerColor);
      int n = Color.blue(this.curPikerColor);
      this.listener.onColorChange(this.curPikerColor);
      this.listener.onColorChange(j, m, n);
      this.listener.onColorChange(j, m, n, this.brightness);
      this.listener.onBrightnessChange(this.brightness);
      onTouchPointUp(paramInt1, paramInt2);
      invalidate();
      return;
      label247: this.curPikerColor = this.currentBM.getPixel(paramInt1, paramInt2);
    }*/
  }

  private void hidePikerOnGlassOpen(int paramInt1, int paramInt2, int paramInt3)
  {
    switch (paramInt3)
    {
    default:
    case 0:
      this.pickerPonit1.set(paramInt1 + this.width, paramInt2);
      break;
    case 1:
      this.pickerPonit2.set(paramInt1 + this.width, paramInt2);
      break;
    case 2:
      this.pickerPonit3.set(paramInt1 + this.width, paramInt2);
      break;
    case 3:
      this.pickerPonit4.set(paramInt1 + this.width, paramInt2);
      break;
    }
      invalidate();
  }

  private void init()
  {
    if (!this.show)
      return;
    this.bmOptions = new BitmapFactory.Options();
    this.generalP = new Paint();
    this.generalP.setAntiAlias(true);
    this.colorBM = BitmapUtils.readBitMap(getResources(), R.mipmap.piccolor);
    this.bigGroupBM = BitmapUtils.readBitMap(getResources(), R.mipmap.groud_area_big);
    this.colorTitleBM = BitmapUtils.readBitMap(getResources(), R.mipmap.piccolor_title);
    this.pikerBM = BitmapUtils.readBitMap(getResources(), R.mipmap.area_2);
    this.glassPikerBM = BitmapUtils.readBitMap(getResources(), R.mipmap.area_big);
    this.pikerSeletedBM = BitmapUtils.readBitMap(getResources(), R.mipmap.area_1);
    this.groupBM = BitmapUtils.readBitMap(getResources(), R.mipmap.groud_area_2);
    this.pikerBMWidth = this.pikerBM.getWidth();
    this.pikerBMHeight = this.pikerBM.getHeight();
    this.bitmapPaint = new Paint();
    this.bitmapPaint.setAntiAlias(true);
    this.textPaint = new Paint();
    this.textPaint.setAntiAlias(true);
    this.textPaint.setTextSize(25.0F);
    this.textPaint1 = new Paint();
    this.textPaint1.setAntiAlias(true);
    this.textPaint1.setTextSize(25.0F);
    this.textPaint2 = new Paint();
    this.textPaint2.setAntiAlias(true);
    this.textPaint2.setTextSize(25.0F);
    this.textPaint3 = new Paint();
    this.textPaint3.setAntiAlias(true);
    this.textPaint3.setTextSize(25.0F);
    this.textPaint4 = new Paint();
    this.textPaint4.setAntiAlias(true);
    this.textPaint4.setTextSize(25.0F);
    this.glassPaint = new Paint();
    this.glassPaint.setAntiAlias(true);
    this.glassPaint.setTextSize(50.0F);
    sacaleColorBM();
    this.distance = this.groupBM.getHeight();
    initTouchArea();
    initBitmapShader();
    this.currentBM = this.colorBM;
    this.groupPiont.set(-500, 0);
    if (Build.VERSION.SDK_INT <= 17)
      setLayerType(1, null);
    setDrawingCacheEnabled(true);
  }

  private void initBitmapShader()
  {
    this.mPath.addCircle(this.glassRadius, this.glassRadius, this.glassRadius, Direction.CW);
    this.mMatrix.setScale(2.0F, 2.0F);
  }

  private void initTouchArea()
  {
    this.originalTouchArea = new TouchArea();
    this.originalTouchArea.setMinX(-1000);
    this.originalTouchArea.setMaxX(-1000);
    this.originalTouchArea.setMinY(-1000);
    this.originalTouchArea.setMaxX(-1000);
    this.touchAreaList.add(this.originalTouchArea);
    this.touchAreaList.add(this.originalTouchArea);
    this.touchAreaList.add(this.originalTouchArea);
    this.touchAreaList.add(this.originalTouchArea);
    this.groupAreaList.add(this.originalTouchArea);
    this.groupAreaList.add(this.originalTouchArea);
    this.groupAreaList.add(this.originalTouchArea);
    this.groupAreaList.add(this.originalTouchArea);
    this.pikerOfGroup.add(Integer.valueOf(-1));
    this.pikerOfGroup.add(Integer.valueOf(-1));
    this.pikerOfGroup.add(Integer.valueOf(-1));
    this.pikerOfGroup.add(Integer.valueOf(-1));
    resetLightNums();
  }

  private void moveThumb(Canvas paramCanvas, float paramFloat1, float paramFloat2)
  {
    float f = this.targetX - this.thumbX;
    if (Math.abs(f) < 40.0F)
    {
      paramCanvas.drawBitmap(this.pikerBM, this.targetX, this.targetY, this.bitmapPaint);
      if (this.in >= 2)
      {
        this.in = 0;
        return;
      }
      this.in = (1 + this.in);
      invalidate();
      hidePikerOnGlassOpen((int)paramFloat1 - this.pikerBMWidth / 2 + this.width, (int)paramFloat2 - this.pikerBMHeight / 2, this.seletedPickerIndex);
      System.out.println("我是上");
      return;
    }
    if (f > 0.0F);
    for (this.flag = 1; ; this.flag = -1)
    {
      this.thumbX = (int)(this.thumbX + 40 * this.flag / Math.floor(1.0F + this.k * this.k));
      this.thumbY = ((int)(this.k * (this.thumbX - this.lastX)) + this.lastY);
      paramCanvas.drawBitmap(this.pikerBM, this.thumbX, this.thumbY, this.bitmapPaint);
      System.out.println("我是下");
      invalidate();
      return;
    }
  }

  private void onPikerOutArea(int paramInt1, int paramInt2, int paramInt3)
  {
    this.isGlassShow = false;
    this.shotBM = null;
    if (paramInt2 >= this.pikerAraeHight)
      switch (paramInt3)
      {
      default:
      case 0:
        this.pickerPonit1.set(paramInt1, this.pikerAraeHight - this.pikerBMHeight);
        break;
      case 1:
        this.pickerPonit2.set(paramInt1, this.pikerAraeHight - this.pikerBMHeight);
        break;
      case 2:
        this.pickerPonit3.set(paramInt1, this.pikerAraeHight - this.pikerBMHeight);
        break;
      case 3:
        this.pickerPonit4.set(paramInt1, this.pikerAraeHight - this.pikerBMHeight);
        break;
      }
      invalidate();
  }

  private void onPikerUp(int paramInt1, int paramInt2, int paramInt3)
  {
    switch (paramInt3)
    {
    default:
      return;
    case 0:
      this.pickerPonit1.set(paramInt1 - this.pikerBMWidth / 2, paramInt2 - (int)(2.5D * this.pikerBMHeight));
      return;
    case 1:
      this.pickerPonit2.set(paramInt1 - this.pikerBMWidth / 2, paramInt2 - (int)(2.5D * this.pikerBMHeight));
      return;
    case 2:
      this.pickerPonit3.set(paramInt1 - this.pikerBMWidth / 2, paramInt2 - (int)(2.5D * this.pikerBMHeight));
      return;
    case 3:
    }
    this.pickerPonit4.set(paramInt1 - this.pikerBMWidth / 2, paramInt2 - (int)(2.5D * this.pikerBMHeight));
  }

  private void openGroup()
  {
  }

  private void resetLightNums()
  {
    this.lightNums.clear();
    this.lightNums.add("1");
    this.lightNums.add("2");
    this.lightNums.add("3");
    this.lightNums.add("4");
  }

  private void sacaleColorBM()
  {
    /*Matrix localMatrix = new Matrix();
    WindowManager localWindowManager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
    this.width = localWindowManager.getDefaultDisplay().getWidth();
    this.height = localWindowManager.getDefaultDisplay().getHeight();
    this.glassRadius = (this.width / 6);
    this.glassRadius = (3 * this.pikerBMHeight);
    float f1;
    float f2;
    if (this.width > this.colorBM.getWidth())
    {
      f1 = this.width / this.colorBM.getWidth();
      if (this.height <= this.pActHeight)
        f2 = (this.pActHeight - getTop()) / this.colorBM.getHeight();
    }else{
      localMatrix.postScale(f1, f2);
      this.colorTitleBM = Bitmap.createBitmap(this.colorTitleBM, 0, 0, this.colorTitleBM.getWidth(), this.colorTitleBM.getHeight(), localMatrix, true);
      this.colorBM = Bitmap.createBitmap(this.colorBM, 0, 0, this.colorBM.getWidth(), this.colorBM.getHeight(), localMatrix, true);
      return;
      f1 = this.width / this.colorBM.getWidth();
      break;
      label216: f2 = this.colorBM.getHeight() / (this.pActHeight - getTop());
    }*/
  }

  private void saveShotBm4SellPhone()
  {
    if (this.pickerPonit1 != null)
      this.pickerPonit1.set(this.pickerPonit1.x + this.width, this.pickerPonit1.y);
    if (this.pickerPonit2 != null)
      this.pickerPonit2.set(this.pickerPonit2.x + this.width, this.pickerPonit2.y);
    if (this.pickerPonit3 != null)
      this.pickerPonit3.set(this.pickerPonit3.x + this.width, this.pickerPonit3.y);
    if (this.pickerPonit4 != null)
      this.pickerPonit4.set(this.pickerPonit4.x + this.width, this.pickerPonit4.y);
    invalidate();
    this.shotBm4SellPhone = getDrawingCache();
    if (this.pickerPonit1 != null)
      this.pickerPonit1.set(this.pickerPonit1.x - this.width, this.pickerPonit1.y);
    if (this.pickerPonit2 != null)
      this.pickerPonit2.set(this.pickerPonit2.x - this.width, this.pickerPonit2.y);
    if (this.pickerPonit3 != null)
      this.pickerPonit3.set(this.pickerPonit3.x - this.width, this.pickerPonit3.y);
    if (this.pickerPonit4 != null)
      this.pickerPonit4.set(this.pickerPonit4.x - this.width, this.pickerPonit4.y);
    invalidate();
  }

  private void shot()
  {
    if (!this.isGlassShow)
    {
      this.isGlassShow = true;
      this.shotBM = getDrawingCache();
    }
  }

  private void showPikerOnGlassClose(int paramInt1, int paramInt2, int paramInt3)
  {
    switch (paramInt3)
    {
    default:
      return;
    case 0:
      this.pickerPonit1.set(paramInt1, paramInt2);
      return;
    case 1:
      this.pickerPonit2.set(paramInt1, paramInt2);
      return;
    case 2:
      this.pickerPonit3.set(paramInt1, paramInt2);
      return;
    case 3:
    }
    this.pickerPonit4.set(paramInt1, paramInt2);
  }

  private void updatePikerAndGroupArea(int paramInt1, int paramInt2, int paramInt3)
  {
    TouchArea localTouchArea = new TouchArea();
    localTouchArea.setMinX(paramInt1);
    localTouchArea.setMaxX(paramInt1 + this.pikerBMWidth);
    localTouchArea.setMinY(paramInt2);
    localTouchArea.setMaxY(paramInt2 + this.pikerBMHeight);
    this.touchAreaList.remove(paramInt3);
    this.touchAreaList.add(paramInt3, localTouchArea);
  }

  private void updatePikerArea(int paramInt1, int paramInt2, int paramInt3)
  {
    TouchArea localTouchArea = new TouchArea();
    localTouchArea.setMinX(paramInt1);
    localTouchArea.setMaxX(paramInt1 + this.pikerBMWidth);
    localTouchArea.setMinY(paramInt2);
    localTouchArea.setMaxY(paramInt2 + this.pikerBMHeight);
    this.touchAreaList.remove(paramInt3);
    this.touchAreaList.add(paramInt3, localTouchArea);
  }

  public int getCurPikerColor()
  {
    if (this.currentBM == null)
      return 0;
    if((this.pickerPonit2.y > this.currentBM.getHeight()) || (this.pickerPonit2.x > this.currentBM.getWidth()))
      return this.currentBM.getPixel(this.currentBM.getWidth(), this.currentBM.getHeight());
    else
      return this.currentBM.getPixel(this.pickerPonit2.x, this.pickerPonit2.y);
  }

  public void hidePiker(int paramInt)
  {
    this.touchAreaList.remove(paramInt - 1);
    this.touchAreaList.add(paramInt - 1, this.originalTouchArea);
    this.groupAreaList.remove(paramInt - 1);
    this.groupAreaList.add(paramInt - 1, this.originalTouchArea);
    switch (paramInt)
    {
    default:
    case 1:
      this.pickerPonit1 = null;
      break;
    case 2:
      this.pickerPonit2 = null;
      break;
    case 3:
      this.pickerPonit3 = null;
      break;
    case 4:
      this.pickerPonit4 = null;
      break;
    }
      invalidate();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (isInEditMode())
      return;
    if (this.sellPhoneBm != null)
    {
      this.currentBM = this.shotBm4SellPhone;
      paramCanvas.drawBitmap(this.colorTitleBM, 0.0F, 0.0F, this.bitmapPaint);
      paramCanvas.drawBitmap(this.sellPhoneBm, 0.0F, this.colorTitleBM.getHeight(), this.bitmapPaint);
      if (this.tempShotBM != null)
        this.tempShotBM = getDrawingCache();
      if (this.isFirshShotBm)
        this.rainbowBm = this.tempShotBM;
      if (this.tempShotBM != null)
      {
        invalidate();
        this.isFirshShotBm = false;
      }
      if (this.shotBM == null){
        this.doubleMoveX = (2 * this.mCurrentX);
        this.doubleMoveY = (2 * this.mCurrentY);
        this.glassCenterX = (this.mCurrentX - this.glassRadius);
        this.glassCenterY = (this.mCurrentY - 2 * this.glassRadius);
        this.sacleBmX = (this.glassRadius - this.doubleMoveX);
        this.sacleBmY = (2 * this.glassRadius - this.doubleMoveY);
      }
      paramCanvas.translate(this.glassCenterX, this.glassCenterY);
      paramCanvas.clipPath(this.mPath);
      paramCanvas.translate(this.sacleBmX, this.sacleBmY);
      paramCanvas.drawBitmap(this.shotBM, this.mMatrix, this.bitmapPaint);
      /*if (this.sacleBmX >= 0)
        break label538;
      if (this.sacleBmY >= 0)
        break label489;*/
      paramCanvas.translate(Math.abs(this.sacleBmX) + this.glassRadius - this.glassPikerBM.getWidth() / 2, Math.abs(this.sacleBmY) - (this.glassPikerBM.getHeight() - this.glassRadius));
    }else {
      /*if (!this.isGroupSeleted)
        paramCanvas.drawBitmap(this.bigGroupBM, 0.0F, 0.0F, this.bitmapPaint);
      else*/
        paramCanvas.drawBitmap(this.currentBM, 0.0F, 0.0F, this.bitmapPaint);
      this.currentBM = this.colorBM;
      if (this.isActionDown)
      {
        this.isActionDown = false;
        paramCanvas.drawBitmap(this.pikerBM, this.pickerPonit2.x, this.pickerPonit2.y, this.bitmapPaint);
        return;
      }
      if (this.isActionMove)
      {
        paramCanvas.drawBitmap(this.pikerBM, this.pickerPonit2.x, this.pickerPonit2.y, this.bitmapPaint);
        onTouchPointUp(this.pickerPonit2.x, this.pickerPonit2.y);
        System.out.println("简单画");
      }
      moveThumb(paramCanvas, this.targetX, this.targetY);
      System.out.println("动画画");
      paramCanvas.translate(Math.abs(this.sacleBmX) + this.glassRadius - this.glassPikerBM.getWidth() / 2, -this.sacleBmY - (this.glassPikerBM.getHeight() - this.glassRadius));
      if (this.sacleBmY < 0)
      {
        paramCanvas.translate(this.glassRadius - this.glassPikerBM.getWidth() / 2 - this.sacleBmX, Math.abs(this.sacleBmY) - (this.glassPikerBM.getHeight() - this.glassRadius));
      }
      paramCanvas.translate(this.glassRadius - this.glassPikerBM.getWidth() / 2 - this.sacleBmX, -this.sacleBmY - (this.glassPikerBM.getHeight() - this.glassRadius));
    }
    paramCanvas.drawBitmap(this.glassPikerBM, 0.0F, 0.0F, this.bitmapPaint);
    paramCanvas.translate(this.glassPikerBM.getWidth() / 2, 9 * this.glassPikerBM.getHeight() / 23);
    this.textPaint.setColor(this.curPikerColor);
    paramCanvas.drawCircle(0.0F, 0.0F, 2 + this.glassPikerBM.getWidth() / 3, this.textPaint);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (!this.frishMeasure)
    {
      this.frishMeasure = true;
      this.top = getTop();
    }
    setMeasuredDimension(this.width, this.pActHeight - this.top);
    System.out.println("onMeasure = " + (this.pActHeight - this.top));
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int j = (int)paramMotionEvent.getX();
    int m = (int)paramMotionEvent.getY();
    if ((j >= 0) && (m >= 0) && (m <= this.pActHeight) && (j < this.width))
      switch (paramMotionEvent.getAction())
      {
      default:
      case 0:
        handleActionDown(j, m);
        break;
      case 2:
        handleActionMove(j, m);
        break;
      case 1:
        cleanShot();
        handleActionUp(j, m);
        break;
      }
    if (paramMotionEvent.getAction() == 1)
      onPikerOutArea(j, m, this.seletedPickerIndex);
    return true;
  }

  protected void onTouchPoint(float paramFloat1, float paramFloat2)
  {
    this.targetX = (int)paramFloat1;
    this.targetY = (int)paramFloat2;
    this.lastX = this.thumbX;
    this.lastY = this.thumbY;
    float f = this.targetX - this.thumbX;
    if (f == 0.0F)
    {
      this.k = 1.0F;
      return;
    }
    this.k = ((this.targetY - this.lastY) / f);
  }

  protected void onTouchPointUp(float paramFloat1, float paramFloat2)
  {
    this.thumbX = (int)paramFloat1;
    this.thumbY = (int)paramFloat2;
    this.lastX = this.thumbX;
    this.lastY = this.thumbY;
  }

  public void setOnColorChangedListener(OnColorChangedListener paramOnColorChangedListener)
  {
    this.listener = paramOnColorChangedListener;
  }

  public void setOnNoPickerSeletedListener(OnNoPickerSeletedListener paramOnNoPickerSeletedListener)
  {
    this.onNoPickerSeletedListener = paramOnNoPickerSeletedListener;
  }

  public void setPikersX(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.pickerX1 = paramInt1;
    this.pickerX2 = paramInt2;
    this.pickerX3 = paramInt3;
    this.pickerX4 = paramInt4;
  }

  public void setSellPhoneBm(Bitmap paramBitmap)
  {
    this.sellPhoneBm = paramBitmap;
    if (this.pickerPonit1 != null)
      this.pickerPonit1.set(this.pickerPonit1.x + this.width, this.pickerPonit1.y);
    if (this.pickerPonit2 != null)
      this.pickerPonit2.set(this.pickerPonit2.x + this.width, this.pickerPonit2.y);
    if (this.pickerPonit3 != null)
      this.pickerPonit3.set(this.pickerPonit3.x + this.width, this.pickerPonit3.y);
    if (this.pickerPonit4 != null)
      this.pickerPonit4.set(this.pickerPonit4.x + this.width, this.pickerPonit4.y);
    invalidate();
    this.shotBm4SellPhone = getDrawingCache();
    if (this.pickerPonit1 != null)
      this.pickerPonit1.set(this.pickerPonit1.x - this.width, this.pickerPonit1.y);
    if (this.pickerPonit2 != null)
      this.pickerPonit2.set(this.pickerPonit2.x - this.width, this.pickerPonit2.y);
    if (this.pickerPonit3 != null)
      this.pickerPonit3.set(this.pickerPonit3.x - this.width, this.pickerPonit3.y);
    if (this.pickerPonit4 != null)
      this.pickerPonit4.set(this.pickerPonit4.x - this.width, this.pickerPonit4.y);
    invalidate();
  }

  public void setpActHeight(int paramInt)
  {
    this.pActHeight = paramInt;
    this.show = true;
    init();
    this.pikerAraeHight = paramInt;
    invalidate();
  }

  public void showPiker(int paramInt)
  {
    Point localPoint = new Point();
    TouchArea localTouchArea1 = new TouchArea();
    switch (paramInt)
    {
    default:
    case 1:
      localTouchArea1.setMinX(this.width / 2);
      localTouchArea1.setMaxX(this.width / 2 + this.pikerBMWidth);
      localTouchArea1.setMinY(-100 + this.pikerAraeHight / 2);
      localTouchArea1.setMaxY(-100 + this.pikerAraeHight / 2 + this.pikerBMHeight);
      this.touchAreaList.remove(paramInt - 1);
      this.touchAreaList.add(paramInt - 1, localTouchArea1);
      localPoint.set(this.width / 2, -100 + this.pikerAraeHight / 2);
      this.pickerPonit1 = localPoint;
      break;
    case 2:
      TouchArea localTouchArea4 = new TouchArea();
      localTouchArea4.setMinX(this.width / 2);
      localTouchArea4.setMaxX(this.width / 2 + this.pikerBMWidth);
      localTouchArea4.setMinY(-100 + this.pikerAraeHight / 2);
      localTouchArea4.setMaxY(-100 + this.pikerAraeHight / 2 + this.pikerBMHeight);
      this.touchAreaList.remove(paramInt - 1);
      this.touchAreaList.add(paramInt - 1, localTouchArea4);
      localPoint.set(this.width / 2, -100 + this.pikerAraeHight / 2);
      this.pickerPonit2 = localPoint;
      break;
    case 3:
      TouchArea localTouchArea3 = new TouchArea();
      localTouchArea3.setMinX(this.width / 2);
      localTouchArea3.setMaxX(this.width / 2 + this.pikerBMWidth);
      localTouchArea3.setMinY(-100 + this.pikerAraeHight / 2);
      localTouchArea3.setMaxY(-100 + this.pikerAraeHight / 2 + this.pikerBMHeight);
      this.touchAreaList.remove(paramInt - 1);
      this.touchAreaList.add(paramInt - 1, localTouchArea3);
      localPoint.set(this.width / 2, -100 + this.pikerAraeHight / 2);
      this.pickerPonit3 = localPoint;
      break;
    case 4:
      TouchArea localTouchArea2 = new TouchArea();
      localTouchArea2.setMinX(this.width / 2);
      localTouchArea2.setMaxX(this.width / 2 + this.pikerBMWidth);
      localTouchArea2.setMinY(-100 + this.pikerAraeHight / 2);
      localTouchArea2.setMaxY(-100 + this.pikerAraeHight / 2 + this.pikerBMHeight);
      this.touchAreaList.remove(paramInt - 1);
      this.touchAreaList.add(paramInt - 1, localTouchArea2);
      localPoint.set(this.width / 2, -100 + this.pikerAraeHight / 2);
      this.pickerPonit4 = localPoint;
      break;
    }
    invalidate();
  }

  public void showPiker(int paramInt1, int paramInt2, int paramInt3)
  {
    Point localPoint = new Point();
    TouchArea localTouchArea1 = new TouchArea();
    switch (paramInt1)
    {
    default:
    case 1:
      localTouchArea1.setMinX(paramInt2);
      localTouchArea1.setMaxX(paramInt2 + this.pikerBMWidth);
      localTouchArea1.setMinY(paramInt3);
      localTouchArea1.setMaxY(paramInt3 + this.pikerBMHeight);
      this.touchAreaList.remove(paramInt1 - 1);
      this.touchAreaList.add(paramInt1 - 1, localTouchArea1);
      localPoint.set(paramInt2, paramInt3);
      this.pickerPonit1 = localPoint;
      break;
    case 2:
      TouchArea localTouchArea4 = new TouchArea();
      localTouchArea4.setMinX(paramInt2);
      localTouchArea4.setMaxX(paramInt2 + this.pikerBMWidth);
      localTouchArea4.setMinY(paramInt3);
      localTouchArea4.setMaxY(paramInt3 + this.pikerBMHeight);
      this.touchAreaList.remove(paramInt1 - 1);
      this.touchAreaList.add(paramInt1 - 1, localTouchArea4);
      localPoint.set(paramInt2, paramInt3);
      this.pickerPonit2 = localPoint;
      break;
    case 3:
      TouchArea localTouchArea3 = new TouchArea();
      localTouchArea3.setMinX(paramInt2);
      localTouchArea3.setMaxX(paramInt2 + this.pikerBMWidth);
      localTouchArea3.setMinY(paramInt3);
      localTouchArea3.setMaxY(paramInt3 + this.pikerBMHeight);
      this.touchAreaList.remove(paramInt1 - 1);
      this.touchAreaList.add(paramInt1 - 1, localTouchArea3);
      localPoint.set(paramInt2, paramInt3);
      this.pickerPonit3 = localPoint;
      break;
    case 4:
      TouchArea localTouchArea2 = new TouchArea();
      localTouchArea2.setMinX(paramInt2);
      localTouchArea2.setMaxX(paramInt2 + this.pikerBMWidth);
      localTouchArea2.setMinY(paramInt3);
      localTouchArea2.setMaxY(paramInt3 + this.pikerBMHeight);
      this.touchAreaList.remove(paramInt1 - 1);
      this.touchAreaList.add(paramInt1 - 1, localTouchArea2);
      localPoint.set(paramInt2, paramInt3);
      this.pickerPonit4 = localPoint;
      break;
    }
      invalidate();
  }

  public void updatePikerPosi(Point paramPoint, int paramInt)
  {
    switch (paramInt + 1)
    {
    default:
      return;
    case 1:
      this.pickerPonit1 = paramPoint;
      return;
    case 2:
      this.pickerPonit2 = paramPoint;
      return;
    case 3:
      this.pickerPonit3 = paramPoint;
      return;
    case 4:
    }
    this.pickerPonit4 = paramPoint;
  }

  public static abstract interface OnColorChangedListener
  {
    public abstract void onBrightnessChange(int paramInt);

    public abstract void onColorChange(int paramInt);

    public abstract void onColorChange(int paramInt1, int paramInt2, int paramInt3);

    public abstract void onColorChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

    public abstract void onSeletedStart(int paramInt1, int paramInt2, int paramInt3);
  }

  public static abstract interface OnNoPickerSeletedListener
  {
    public abstract void onNoPickerSeleted();
  }
}
