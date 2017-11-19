package com.ex.ltech.led.acti.main;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ex.ltech.led.acti.mode.ColorSeletedView;
import com.ex.ltech.led.my_view.MLImageView;
import com.ex.ltech.led.vo.CtSceneVo;
import com.ex.ltech.led.vo.ModeVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class DeviceListAdapter extends BaseAdapter
{
  Bitmap bwBm;
  ArrayList<Integer> cols = new ArrayList();
  Bitmap ctBm;
  Bitmap defaultBm;
//  private List<Device> devices;
  Bitmap hongwaiBm;
  RelativeLayout.LayoutParams layoutParams;
  Bitmap ledBm;
  Bitmap lightOffline;
  private Activity mContext;
  OnFreshDevice onFreshDevice;
  Bitmap oneZeroFiveBm;
  Bitmap oneZeroFiveOff;
  Bitmap plugBm;
  Bitmap plugOffline;
  Bitmap remoteOffline;

  /*public DeviceListAdapter(Activity paramActivity, ArrayList<Device> paramArrayList)
  {
    this.mContext = paramActivity;
    this.devices = paramArrayList;
    this.ledBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.rgb);
    this.plugBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.device_plug);
    this.hongwaiBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.d_remote);
    this.ctBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.ct);
    this.bwBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.bw_d_ic);
    this.defaultBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.cfg_cfg_no);
    this.oneZeroFiveBm = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.icon_105);
    this.oneZeroFiveOff = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.icon_105_off);
    this.lightOffline = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.light_offline);
    this.plugOffline = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.plug_offline);
    this.remoteOffline = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.remote_offline);
    this.layoutParams = new RelativeLayout.LayoutParams(-2, -2);
  }*/

  private boolean isZh()
  {
    Locale localLocale = this.mContext.getResources().getConfiguration().locale;
    String str1 = localLocale.getCountry();
    String str2 = localLocale.getLanguage();
    return (str2 + "_" + str1).equals("zh_CN");
  }

  /*public int getCount()
  {
    return this.devices.size();
  }

  public Object getItem(int paramInt)
  {
    return this.devices.get(paramInt);
  }*/

  @Override
  public int getCount() {
    return 0;
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  // ERROR //
  public View getView(int paramInt, View paramView, android.view.ViewGroup paramViewGroup)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +1830 -> 1831
    //   4: new 157	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 160	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:<init>	(Lcom/ex/ltech/led/acti/main/DeviceListAdapter;)V
    //   12: astore 4
    //   14: aload_0
    //   15: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   18: invokestatic 166	android/view/LayoutInflater:from	(Landroid/content/Context;)Landroid/view/LayoutInflater;
    //   21: ldc 167
    //   23: aconst_null
    //   24: invokevirtual 171	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;)Landroid/view/View;
    //   27: astore_2
    //   28: aload 4
    //   30: aload_2
    //   31: ldc 172
    //   33: invokevirtual 178	android/view/View:findViewById	(I)Landroid/view/View;
    //   36: checkcast 180	com/ex/ltech/led/my_view/MLImageView
    //   39: putfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   42: aload 4
    //   44: aload_2
    //   45: ldc 185
    //   47: invokevirtual 178	android/view/View:findViewById	(I)Landroid/view/View;
    //   50: checkcast 187	android/widget/TextView
    //   53: putfield 191	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_device_name	Landroid/widget/TextView;
    //   56: aload 4
    //   58: aload_2
    //   59: ldc 192
    //   61: invokevirtual 178	android/view/View:findViewById	(I)Landroid/view/View;
    //   64: checkcast 187	android/widget/TextView
    //   67: putfield 195	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_online_status	Landroid/widget/TextView;
    //   70: aload 4
    //   72: aload_2
    //   73: ldc 196
    //   75: invokevirtual 178	android/view/View:findViewById	(I)Landroid/view/View;
    //   78: checkcast 187	android/widget/TextView
    //   81: putfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   84: aload 4
    //   86: aload_2
    //   87: ldc 200
    //   89: invokevirtual 178	android/view/View:findViewById	(I)Landroid/view/View;
    //   92: checkcast 202	com/ex/ltech/led/my_view/ColorSeletedView
    //   95: putfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   98: aload 4
    //   100: aload_2
    //   101: ldc 207
    //   103: invokevirtual 178	android/view/View:findViewById	(I)Landroid/view/View;
    //   106: checkcast 209	android/widget/ImageView
    //   109: putfield 213	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:space	Landroid/widget/ImageView;
    //   112: aload 4
    //   114: aload_2
    //   115: ldc 214
    //   117: invokevirtual 178	android/view/View:findViewById	(I)Landroid/view/View;
    //   120: checkcast 209	android/widget/ImageView
    //   123: putfield 217	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:off_line_lay	Landroid/widget/ImageView;
    //   126: aload_2
    //   127: aload 4
    //   129: invokevirtual 221	android/view/View:setTag	(Ljava/lang/Object;)V
    //   132: aload 4
    //   134: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   137: astore 5
    //   139: new 223	com/ex/ltech/led/acti/main/DeviceListAdapter$1
    //   142: dup
    //   143: aload_0
    //   144: iload_1
    //   145: invokespecial 226	com/ex/ltech/led/acti/main/DeviceListAdapter$1:<init>	(Lcom/ex/ltech/led/acti/main/DeviceListAdapter;I)V
    //   148: astore 6
    //   150: aload 5
    //   152: aload 6
    //   154: invokevirtual 230	com/ex/ltech/led/my_view/MLImageView:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   157: aload_0
    //   158: getfield 42	com/ex/ltech/led/acti/main/DeviceListAdapter:devices	Ljava/util/List;
    //   161: iload_1
    //   162: invokeinterface 149 2 0
    //   167: checkcast 232	io/xlink/wifi/js/bean/Device
    //   170: astore 7
    //   172: aload 7
    //   174: invokevirtual 235	io/xlink/wifi/js/bean/Device:isShowOnline	()Z
    //   177: ifne +11 -> 188
    //   180: aload 7
    //   182: invokevirtual 238	io/xlink/wifi/js/bean/Device:isShowOffline	()Z
    //   185: ifeq +1666 -> 1851
    //   188: aload 4
    //   190: getfield 195	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_online_status	Landroid/widget/TextView;
    //   193: iconst_0
    //   194: invokevirtual 242	android/widget/TextView:setVisibility	(I)V
    //   197: aload 4
    //   199: getfield 195	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_online_status	Landroid/widget/TextView;
    //   202: astore 8
    //   204: aload 7
    //   206: invokevirtual 235	io/xlink/wifi/js/bean/Device:isShowOnline	()Z
    //   209: ifeq +1634 -> 1843
    //   212: ldc 243
    //   214: istore 9
    //   216: aload 8
    //   218: iload 9
    //   220: invokevirtual 246	android/widget/TextView:setText	(I)V
    //   223: aload_0
    //   224: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   227: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   230: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   233: new 119	java/lang/StringBuilder
    //   236: dup
    //   237: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   240: aload 7
    //   242: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   245: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: ldc_w 261
    //   251: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   257: ldc_w 263
    //   260: invokeinterface 269 3 0
    //   265: astore 10
    //   267: aload_0
    //   268: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   271: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   274: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   277: new 119	java/lang/StringBuilder
    //   280: dup
    //   281: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   284: ldc_w 271
    //   287: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: aload 7
    //   292: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   295: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   301: ldc_w 263
    //   304: invokeinterface 269 3 0
    //   309: astore 11
    //   311: aload_0
    //   312: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   315: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   318: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   321: new 119	java/lang/StringBuilder
    //   324: dup
    //   325: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   328: ldc_w 273
    //   331: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: aload 7
    //   336: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   339: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   345: ldc_w 263
    //   348: invokeinterface 269 3 0
    //   353: astore 12
    //   355: aload_0
    //   356: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   359: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   362: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   365: new 119	java/lang/StringBuilder
    //   368: dup
    //   369: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   372: ldc_w 275
    //   375: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   378: aload 7
    //   380: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   383: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   389: ldc_w 263
    //   392: invokeinterface 269 3 0
    //   397: astore 13
    //   399: aload 4
    //   401: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   404: iconst_0
    //   405: invokevirtual 242	android/widget/TextView:setVisibility	(I)V
    //   408: aload 4
    //   410: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   413: bipush 8
    //   415: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   418: aload_0
    //   419: getfield 94	com/ex/ltech/led/acti/main/DeviceListAdapter:layoutParams	Landroid/widget/RelativeLayout$LayoutParams;
    //   422: aload_0
    //   423: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   426: ldc_w 277
    //   429: invokestatic 283	com/ex/ltech/led/utils/BitmapUtils:dp2px	(Landroid/content/Context;F)I
    //   432: aload_0
    //   433: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   436: fconst_0
    //   437: invokestatic 283	com/ex/ltech/led/utils/BitmapUtils:dp2px	(Landroid/content/Context;F)I
    //   440: iconst_0
    //   441: iconst_0
    //   442: invokevirtual 287	android/widget/RelativeLayout$LayoutParams:setMargins	(IIII)V
    //   445: aload 4
    //   447: getfield 191	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_device_name	Landroid/widget/TextView;
    //   450: aload_0
    //   451: getfield 94	com/ex/ltech/led/acti/main/DeviceListAdapter:layoutParams	Landroid/widget/RelativeLayout$LayoutParams;
    //   454: invokevirtual 291	android/widget/TextView:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   457: aload 7
    //   459: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   462: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   465: pop
    //   466: aload 7
    //   468: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   471: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   474: astore 15
    //   476: iconst_m1
    //   477: istore 16
    //   479: aload 15
    //   481: invokevirtual 303	java/lang/String:hashCode	()I
    //   484: lookupswitch	default:+84->568, -438963134:+1397->1881, -395867606:+1431->1915, -269836529:+1465->1949, 57183619:+1483->1967, 595978335:+1380->1864, 1117596001:+1519->2003, 1369599384:+1501->1985, 1531098377:+1448->1932, 1753509423:+1414->1898
    //   569: bipush 170
    //   571: nop
    //   572: nop
    //   573: nop
    //   574: nop
    //   575: iaload
    //   576: nop
    //   577: nop
    //   578: nop
    //   579: iconst_m1
    //   580: nop
    //   581: nop
    //   582: nop
    //   583: lconst_0
    //   584: nop
    //   585: nop
    //   586: iconst_3
    //   587: monitorexit
    //   588: nop
    //   589: nop
    //   590: iconst_4
    //   591: ldiv
    //   592: nop
    //   593: nop
    //   594: iconst_4
    //   595: <illegal opcode>
    //   596: nop
    //   597: nop
    //   598: iconst_4
    //   599: <illegal opcode>
    //   600: nop
    //   601: nop
    //   602: iconst_5
    //   603: lshr
    //   604: nop
    //   605: nop
    //   606: iconst_5
    //   607: <illegal opcode>
    //   608: nop
    //   609: nop
    //   610: lconst_0
    //   611: lload_3
    //   612: nop
    //   613: nop
    //   614: lconst_0
    //   615: land
    //   616: aload 7
    //   618: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   621: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   624: ldc_w 305
    //   627: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   630: ifne +20 -> 650
    //   633: aload 7
    //   635: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   638: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   641: ldc_w 307
    //   644: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   647: ifeq +714 -> 1361
    //   650: aload 10
    //   652: invokevirtual 310	java/lang/String:length	()I
    //   655: pop
    //   656: aload 12
    //   658: ldc_w 312
    //   661: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   664: ifeq +287 -> 951
    //   667: aload 4
    //   669: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   672: iconst_0
    //   673: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   676: aload 13
    //   678: ldc_w 314
    //   681: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   684: ifeq +252 -> 936
    //   687: aload_0
    //   688: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   691: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   694: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   697: new 119	java/lang/StringBuilder
    //   700: dup
    //   701: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   704: ldc_w 316
    //   707: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   710: aload 7
    //   712: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   715: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   718: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   721: ldc_w 263
    //   724: invokeinterface 269 3 0
    //   729: astore 60
    //   731: aload_0
    //   732: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   735: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   738: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   741: new 119	java/lang/StringBuilder
    //   744: dup
    //   745: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   748: ldc_w 318
    //   751: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   754: aload 7
    //   756: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   759: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   762: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   765: ldc_w 263
    //   768: invokeinterface 269 3 0
    //   773: astore 61
    //   775: aload_0
    //   776: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   779: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   782: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   785: new 119	java/lang/StringBuilder
    //   788: dup
    //   789: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   792: ldc_w 320
    //   795: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   798: aload 7
    //   800: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   803: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   806: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   809: ldc_w 263
    //   812: invokeinterface 269 3 0
    //   817: astore 62
    //   819: aload 60
    //   821: bipush 16
    //   823: invokestatic 326	java/lang/Integer:valueOf	(Ljava/lang/String;I)Ljava/lang/Integer;
    //   826: invokevirtual 329	java/lang/Integer:intValue	()I
    //   829: istore 63
    //   831: aload 61
    //   833: bipush 16
    //   835: invokestatic 326	java/lang/Integer:valueOf	(Ljava/lang/String;I)Ljava/lang/Integer;
    //   838: invokevirtual 329	java/lang/Integer:intValue	()I
    //   841: istore 64
    //   843: aload 62
    //   845: bipush 16
    //   847: invokestatic 326	java/lang/Integer:valueOf	(Ljava/lang/String;I)Ljava/lang/Integer;
    //   850: invokevirtual 329	java/lang/Integer:intValue	()I
    //   853: istore 65
    //   855: aload 62
    //   857: bipush 16
    //   859: invokestatic 326	java/lang/Integer:valueOf	(Ljava/lang/String;I)Ljava/lang/Integer;
    //   862: invokevirtual 329	java/lang/Integer:intValue	()I
    //   865: pop
    //   866: aload_0
    //   867: getfield 38	com/ex/ltech/led/acti/main/DeviceListAdapter:cols	Ljava/util/ArrayList;
    //   870: invokevirtual 332	java/util/ArrayList:clear	()V
    //   873: aload_0
    //   874: getfield 38	com/ex/ltech/led/acti/main/DeviceListAdapter:cols	Ljava/util/ArrayList;
    //   877: sipush 255
    //   880: iload 63
    //   882: iload 64
    //   884: iload 65
    //   886: invokestatic 338	android/graphics/Color:argb	(IIII)I
    //   889: invokestatic 341	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   892: invokevirtual 344	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   895: pop
    //   896: aload 4
    //   898: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   901: ldc_w 263
    //   904: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   907: aload 4
    //   909: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   912: sipush 255
    //   915: iload 63
    //   917: iload 64
    //   919: iload 65
    //   921: invokestatic 338	android/graphics/Color:argb	(IIII)I
    //   924: invokevirtual 350	com/ex/ltech/led/my_view/ColorSeletedView:setColor	(I)V
    //   927: aload 4
    //   929: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   932: aconst_null
    //   933: invokevirtual 354	com/ex/ltech/led/my_view/ColorSeletedView:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   936: aload 13
    //   938: ldc_w 356
    //   941: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   944: istore 59
    //   946: iload 59
    //   948: ifeq +3 -> 951
    //   951: aload 12
    //   953: ldc_w 358
    //   956: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   959: ifeq +326 -> 1285
    //   962: aload_0
    //   963: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   966: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   969: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   972: new 119	java/lang/StringBuilder
    //   975: dup
    //   976: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   979: ldc_w 360
    //   982: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   985: aload 7
    //   987: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   990: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   993: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   996: ldc_w 263
    //   999: invokeinterface 269 3 0
    //   1004: astore 50
    //   1006: iconst_0
    //   1007: istore 51
    //   1009: aload_0
    //   1010: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   1013: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   1016: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   1019: new 119	java/lang/StringBuilder
    //   1022: dup
    //   1023: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   1026: ldc_w 362
    //   1029: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1032: aload 7
    //   1034: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   1037: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1040: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1043: ldc_w 263
    //   1046: invokeinterface 269 3 0
    //   1051: invokestatic 366	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1054: istore 51
    //   1056: aload_0
    //   1057: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   1060: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   1063: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   1066: new 119	java/lang/StringBuilder
    //   1069: dup
    //   1070: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   1073: ldc_w 368
    //   1076: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1079: aload 7
    //   1081: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   1084: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1087: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1090: ldc_w 263
    //   1093: invokeinterface 269 3 0
    //   1098: invokestatic 366	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1101: istore 57
    //   1103: iload 57
    //   1105: istore 53
    //   1107: aload 50
    //   1109: ldc_w 370
    //   1112: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1115: ifeq +1107 -> 2222
    //   1118: aload 4
    //   1120: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   1123: iconst_0
    //   1124: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   1127: aload 4
    //   1129: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   1132: ldc_w 371
    //   1135: invokevirtual 374	com/ex/ltech/led/my_view/ColorSeletedView:setBackgroundResource	(I)V
    //   1138: aload 4
    //   1140: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   1143: invokevirtual 377	com/ex/ltech/led/my_view/ColorSeletedView:clearCanvas	()V
    //   1146: aload 4
    //   1148: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   1151: ldc_w 263
    //   1154: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   1157: iload 51
    //   1159: bipush 10
    //   1161: if_icmpne +21 -> 1182
    //   1164: aload 4
    //   1166: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   1169: aload_0
    //   1170: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   1173: ldc_w 378
    //   1176: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   1179: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   1182: iload 51
    //   1184: tableswitch	default:+48 -> 1232, 1:+860->2044, 2:+881->2065, 3:+902->2086, 4:+923->2107, 5:+944->2128, 6:+965->2149, 7:+986->2170, 8:+1007->2191
    //   1233: saload
    //   1234: ifle +51 -> 1285
    //   1237: ldc_w 263
    //   1240: astore 54
    //   1242: aload_0
    //   1243: aload 7
    //   1245: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   1248: invokevirtual 385	com/ex/ltech/led/acti/main/DeviceListAdapter:initCustomModes	(Ljava/lang/String;)Ljava/util/List;
    //   1251: iconst_m1
    //   1252: iload 53
    //   1254: bipush 8
    //   1256: iadd
    //   1257: iadd
    //   1258: invokeinterface 149 2 0
    //   1263: checkcast 387	com/ex/ltech/led/vo/ModeVo
    //   1266: invokevirtual 390	com/ex/ltech/led/vo/ModeVo:getTvName	()Ljava/lang/String;
    //   1269: astore 56
    //   1271: aload 56
    //   1273: astore 54
    //   1275: aload 4
    //   1277: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   1280: aload 54
    //   1282: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   1285: aload 12
    //   1287: ldc_w 392
    //   1290: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1293: ifeq +31 -> 1324
    //   1296: aload 4
    //   1298: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   1301: aload_0
    //   1302: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   1305: ldc_w 393
    //   1308: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   1311: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   1314: aload 4
    //   1316: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   1319: bipush 8
    //   1321: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   1324: aload 7
    //   1326: invokevirtual 396	io/xlink/wifi/js/bean/Device:isOnline	()Z
    //   1329: ifeq +936 -> 2265
    //   1332: aload 4
    //   1334: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   1337: astore 48
    //   1339: aload 10
    //   1341: invokevirtual 310	java/lang/String:length	()I
    //   1344: ifle +912 -> 2256
    //   1347: aload 10
    //   1349: invokestatic 400	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   1352: astore 49
    //   1354: aload 48
    //   1356: aload 49
    //   1358: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   1361: aload 7
    //   1363: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   1366: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   1369: ldc_w 406
    //   1372: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1375: ifeq +3 -> 1378
    //   1378: aload 7
    //   1380: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   1383: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   1386: ldc_w 408
    //   1389: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1392: ifeq +3 -> 1395
    //   1395: aload 7
    //   1397: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   1400: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   1403: ldc_w 410
    //   1406: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1409: aload 7
    //   1411: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   1414: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   1417: ldc_w 412
    //   1420: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1423: ior
    //   1424: istore 22
    //   1426: aload 7
    //   1428: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   1431: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   1434: ldc_w 414
    //   1437: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1440: istore 23
    //   1442: iload 22
    //   1444: iload 23
    //   1446: ior
    //   1447: ifeq +109 -> 1556
    //   1450: aload_0
    //   1451: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   1454: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   1457: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   1460: new 119	java/lang/StringBuilder
    //   1463: dup
    //   1464: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   1467: ldc_w 416
    //   1470: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1473: aload 7
    //   1475: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   1478: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1481: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1484: ldc_w 263
    //   1487: invokeinterface 269 3 0
    //   1492: invokestatic 366	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1495: istore 29
    //   1497: aload 4
    //   1499: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   1502: aload_0
    //   1503: aload 7
    //   1505: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   1508: invokevirtual 419	com/ex/ltech/led/acti/main/DeviceListAdapter:initCtSceneVos	(Ljava/lang/String;)Ljava/util/List;
    //   1511: iload 29
    //   1513: invokeinterface 149 2 0
    //   1518: checkcast 421	com/ex/ltech/led/vo/CtSceneVo
    //   1521: invokevirtual 424	com/ex/ltech/led/vo/CtSceneVo:getName	()Ljava/lang/String;
    //   1524: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   1527: aload 12
    //   1529: ldc_w 392
    //   1532: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1535: ifeq +21 -> 1556
    //   1538: aload 4
    //   1540: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   1543: aload_0
    //   1544: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   1547: ldc_w 393
    //   1550: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   1553: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   1556: aload 7
    //   1558: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   1561: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   1564: ldc_w 426
    //   1567: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1570: istore 24
    //   1572: iload 24
    //   1574: ifeq +3 -> 1577
    //   1577: aload 7
    //   1579: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   1582: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   1585: ldc_w 305
    //   1588: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1591: ifeq +3 -> 1594
    //   1594: aload 7
    //   1596: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   1599: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   1602: ldc_w 305
    //   1605: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1608: ifeq +3 -> 1611
    //   1611: aload 7
    //   1613: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   1616: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   1619: ldc_w 408
    //   1622: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1625: ifeq +3 -> 1628
    //   1628: aload 4
    //   1630: getfield 191	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_device_name	Landroid/widget/TextView;
    //   1633: astore 18
    //   1635: aload 11
    //   1637: invokevirtual 310	java/lang/String:length	()I
    //   1640: ifle +1680 -> 3320
    //   1643: aload 11
    //   1645: astore 19
    //   1647: aload 18
    //   1649: aload 19
    //   1651: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   1654: aload_0
    //   1655: invokespecial 428	com/ex/ltech/led/acti/main/DeviceListAdapter:isZh	()Z
    //   1658: ifne +91 -> 1749
    //   1661: aload 11
    //   1663: ldc_w 430
    //   1666: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1669: ifeq +14 -> 1683
    //   1672: aload 4
    //   1674: getfield 191	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_device_name	Landroid/widget/TextView;
    //   1677: ldc_w 431
    //   1680: invokevirtual 246	android/widget/TextView:setText	(I)V
    //   1683: aload 11
    //   1685: ldc_w 433
    //   1688: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1691: ifeq +14 -> 1705
    //   1694: aload 4
    //   1696: getfield 191	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_device_name	Landroid/widget/TextView;
    //   1699: ldc_w 434
    //   1702: invokevirtual 246	android/widget/TextView:setText	(I)V
    //   1705: aload 11
    //   1707: ldc_w 436
    //   1710: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1713: ifeq +14 -> 1727
    //   1716: aload 4
    //   1718: getfield 191	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_device_name	Landroid/widget/TextView;
    //   1721: ldc_w 437
    //   1724: invokevirtual 246	android/widget/TextView:setText	(I)V
    //   1727: aload 11
    //   1729: ldc_w 439
    //   1732: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1735: ifeq +14 -> 1749
    //   1738: aload 4
    //   1740: getfield 191	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_device_name	Landroid/widget/TextView;
    //   1743: ldc_w 440
    //   1746: invokevirtual 246	android/widget/TextView:setText	(I)V
    //   1749: aload 7
    //   1751: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   1754: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   1757: ldc_w 410
    //   1760: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1763: aload 7
    //   1765: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   1768: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   1771: ldc_w 414
    //   1774: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1777: ior
    //   1778: ifeq +26 -> 1804
    //   1781: aload 11
    //   1783: ldc_w 442
    //   1786: invokevirtual 445	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   1789: iconst_m1
    //   1790: if_icmpeq +14 -> 1804
    //   1793: aload 4
    //   1795: getfield 191	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_device_name	Landroid/widget/TextView;
    //   1798: ldc_w 440
    //   1801: invokevirtual 246	android/widget/TextView:setText	(I)V
    //   1804: aload 4
    //   1806: getfield 217	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:off_line_lay	Landroid/widget/ImageView;
    //   1809: astore 20
    //   1811: aload 7
    //   1813: invokevirtual 238	io/xlink/wifi/js/bean/Device:isShowOffline	()Z
    //   1816: ifeq +1514 -> 3330
    //   1819: iconst_0
    //   1820: istore 21
    //   1822: aload 20
    //   1824: iload 21
    //   1826: invokevirtual 446	android/widget/ImageView:setVisibility	(I)V
    //   1829: aload_2
    //   1830: areturn
    //   1831: aload_2
    //   1832: invokevirtual 450	android/view/View:getTag	()Ljava/lang/Object;
    //   1835: checkcast 157	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder
    //   1838: astore 4
    //   1840: goto -1708 -> 132
    //   1843: ldc_w 451
    //   1846: istore 9
    //   1848: goto -1632 -> 216
    //   1851: aload 4
    //   1853: getfield 195	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_online_status	Landroid/widget/TextView;
    //   1856: bipush 8
    //   1858: invokevirtual 242	android/widget/TextView:setVisibility	(I)V
    //   1861: goto -1638 -> 223
    //   1864: aload 15
    //   1866: ldc_w 307
    //   1869: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1872: ifeq -1304 -> 568
    //   1875: iconst_0
    //   1876: istore 16
    //   1878: goto -1310 -> 568
    //   1881: aload 15
    //   1883: ldc_w 406
    //   1886: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1889: ifeq -1321 -> 568
    //   1892: iconst_2
    //   1893: istore 16
    //   1895: goto -1327 -> 568
    //   1898: aload 15
    //   1900: ldc_w 408
    //   1903: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1906: ifeq -1338 -> 568
    //   1909: iconst_3
    //   1910: istore 16
    //   1912: goto -1344 -> 568
    //   1915: aload 15
    //   1917: ldc_w 453
    //   1920: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1923: ifeq -1355 -> 568
    //   1926: iconst_4
    //   1927: istore 16
    //   1929: goto -1361 -> 568
    //   1932: aload 15
    //   1934: ldc_w 455
    //   1937: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1940: ifeq -1372 -> 568
    //   1943: iconst_5
    //   1944: istore 16
    //   1946: goto -1378 -> 568
    //   1949: aload 15
    //   1951: ldc_w 414
    //   1954: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1957: ifeq -1389 -> 568
    //   1960: bipush 6
    //   1962: istore 16
    //   1964: goto -1396 -> 568
    //   1967: aload 15
    //   1969: ldc_w 410
    //   1972: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1975: ifeq -1407 -> 568
    //   1978: bipush 7
    //   1980: istore 16
    //   1982: goto -1414 -> 568
    //   1985: aload 15
    //   1987: ldc_w 426
    //   1990: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1993: ifeq -1425 -> 568
    //   1996: bipush 8
    //   1998: istore 16
    //   2000: goto -1432 -> 568
    //   2003: aload 15
    //   2005: ldc_w 412
    //   2008: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2011: ifeq -1443 -> 568
    //   2014: bipush 9
    //   2016: istore 16
    //   2018: goto -1450 -> 568
    //   2021: astore 58
    //   2023: aload 58
    //   2025: invokevirtual 458	java/lang/Exception:printStackTrace	()V
    //   2028: goto -1077 -> 951
    //   2031: astore 52
    //   2033: aload 52
    //   2035: invokevirtual 458	java/lang/Exception:printStackTrace	()V
    //   2038: iconst_0
    //   2039: istore 53
    //   2041: goto -934 -> 1107
    //   2044: aload 4
    //   2046: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2049: aload_0
    //   2050: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2053: ldc_w 459
    //   2056: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   2059: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2062: goto -830 -> 1232
    //   2065: aload 4
    //   2067: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2070: aload_0
    //   2071: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2074: ldc_w 460
    //   2077: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   2080: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2083: goto -851 -> 1232
    //   2086: aload 4
    //   2088: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2091: aload_0
    //   2092: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2095: ldc_w 461
    //   2098: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   2101: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2104: goto -872 -> 1232
    //   2107: aload 4
    //   2109: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2112: aload_0
    //   2113: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2116: ldc_w 462
    //   2119: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   2122: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2125: goto -893 -> 1232
    //   2128: aload 4
    //   2130: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2133: aload_0
    //   2134: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2137: ldc_w 463
    //   2140: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   2143: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2146: goto -914 -> 1232
    //   2149: aload 4
    //   2151: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2154: aload_0
    //   2155: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2158: ldc_w 464
    //   2161: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   2164: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2167: goto -935 -> 1232
    //   2170: aload 4
    //   2172: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2175: aload_0
    //   2176: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2179: ldc_w 465
    //   2182: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   2185: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2188: goto -956 -> 1232
    //   2191: aload 4
    //   2193: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2196: aload_0
    //   2197: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2200: ldc_w 466
    //   2203: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   2206: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2209: goto -977 -> 1232
    //   2212: astore 55
    //   2214: aload 55
    //   2216: invokevirtual 458	java/lang/Exception:printStackTrace	()V
    //   2219: goto -944 -> 1275
    //   2222: aload 13
    //   2224: ldc_w 356
    //   2227: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2230: ifne -945 -> 1285
    //   2233: aload 4
    //   2235: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2238: ldc_w 263
    //   2241: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2244: aload 4
    //   2246: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   2249: iconst_0
    //   2250: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   2253: goto -968 -> 1285
    //   2256: aload_0
    //   2257: getfield 57	com/ex/ltech/led/acti/main/DeviceListAdapter:ledBm	Landroid/graphics/Bitmap;
    //   2260: astore 49
    //   2262: goto -908 -> 1354
    //   2265: aload 4
    //   2267: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2270: aload_0
    //   2271: getfield 81	com/ex/ltech/led/acti/main/DeviceListAdapter:lightOffline	Landroid/graphics/Bitmap;
    //   2274: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2277: aload 4
    //   2279: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   2282: bipush 8
    //   2284: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   2287: aload 4
    //   2289: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2292: ldc_w 263
    //   2295: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2298: goto -937 -> 1361
    //   2301: aload 7
    //   2303: invokevirtual 396	io/xlink/wifi/js/bean/Device:isOnline	()Z
    //   2306: ifeq +135 -> 2441
    //   2309: aload 4
    //   2311: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2314: astore 45
    //   2316: aload 10
    //   2318: invokevirtual 310	java/lang/String:length	()I
    //   2321: ifle +111 -> 2432
    //   2324: aload 10
    //   2326: invokestatic 400	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   2329: astore 46
    //   2331: aload 45
    //   2333: aload 46
    //   2335: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2338: aload_0
    //   2339: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2342: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   2345: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   2348: new 119	java/lang/StringBuilder
    //   2351: dup
    //   2352: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   2355: ldc_w 468
    //   2358: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2361: aload 7
    //   2363: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   2366: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2369: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2372: ldc_w 263
    //   2375: invokeinterface 269 3 0
    //   2380: astore 42
    //   2382: aload 4
    //   2384: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2387: astore 43
    //   2389: aload 42
    //   2391: ldc_w 370
    //   2394: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2397: ifeq +59 -> 2456
    //   2400: aload_0
    //   2401: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2404: ldc_w 469
    //   2407: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   2410: astore 44
    //   2412: aload 43
    //   2414: aload 44
    //   2416: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2419: aload 4
    //   2421: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   2424: bipush 8
    //   2426: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   2429: goto -1068 -> 1361
    //   2432: aload_0
    //   2433: getfield 60	com/ex/ltech/led/acti/main/DeviceListAdapter:plugBm	Landroid/graphics/Bitmap;
    //   2436: astore 46
    //   2438: goto -107 -> 2331
    //   2441: aload 4
    //   2443: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2446: aload_0
    //   2447: getfield 84	com/ex/ltech/led/acti/main/DeviceListAdapter:plugOffline	Landroid/graphics/Bitmap;
    //   2450: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2453: goto -115 -> 2338
    //   2456: aload_0
    //   2457: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2460: ldc_w 470
    //   2463: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   2466: astore 44
    //   2468: goto -56 -> 2412
    //   2471: aload 4
    //   2473: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2476: bipush 8
    //   2478: invokevirtual 242	android/widget/TextView:setVisibility	(I)V
    //   2481: aload 7
    //   2483: invokevirtual 396	io/xlink/wifi/js/bean/Device:isOnline	()Z
    //   2486: ifeq +105 -> 2591
    //   2489: aload 4
    //   2491: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2494: astore 40
    //   2496: aload 10
    //   2498: invokevirtual 310	java/lang/String:length	()I
    //   2501: ifle +81 -> 2582
    //   2504: aload 10
    //   2506: invokestatic 400	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   2509: astore 41
    //   2511: aload 40
    //   2513: aload 41
    //   2515: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2518: aload 4
    //   2520: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2523: bipush 8
    //   2525: invokevirtual 242	android/widget/TextView:setVisibility	(I)V
    //   2528: aload 4
    //   2530: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   2533: bipush 8
    //   2535: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   2538: aload_0
    //   2539: getfield 94	com/ex/ltech/led/acti/main/DeviceListAdapter:layoutParams	Landroid/widget/RelativeLayout$LayoutParams;
    //   2542: aload_0
    //   2543: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2546: ldc_w 277
    //   2549: invokestatic 283	com/ex/ltech/led/utils/BitmapUtils:dp2px	(Landroid/content/Context;F)I
    //   2552: aload_0
    //   2553: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2556: ldc_w 471
    //   2559: invokestatic 283	com/ex/ltech/led/utils/BitmapUtils:dp2px	(Landroid/content/Context;F)I
    //   2562: iconst_0
    //   2563: iconst_0
    //   2564: invokevirtual 287	android/widget/RelativeLayout$LayoutParams:setMargins	(IIII)V
    //   2567: aload 4
    //   2569: getfield 191	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_device_name	Landroid/widget/TextView;
    //   2572: aload_0
    //   2573: getfield 94	com/ex/ltech/led/acti/main/DeviceListAdapter:layoutParams	Landroid/widget/RelativeLayout$LayoutParams;
    //   2576: invokevirtual 291	android/widget/TextView:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   2579: goto -1218 -> 1361
    //   2582: aload_0
    //   2583: getfield 63	com/ex/ltech/led/acti/main/DeviceListAdapter:hongwaiBm	Landroid/graphics/Bitmap;
    //   2586: astore 41
    //   2588: goto -77 -> 2511
    //   2591: aload 4
    //   2593: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2596: aload_0
    //   2597: getfield 87	com/ex/ltech/led/acti/main/DeviceListAdapter:remoteOffline	Landroid/graphics/Bitmap;
    //   2600: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2603: goto -85 -> 2518
    //   2606: aload 4
    //   2608: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2611: bipush 8
    //   2613: invokevirtual 242	android/widget/TextView:setVisibility	(I)V
    //   2616: aload 7
    //   2618: invokevirtual 396	io/xlink/wifi/js/bean/Device:isOnline	()Z
    //   2621: ifeq +105 -> 2726
    //   2624: aload 4
    //   2626: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2629: astore 38
    //   2631: aload 10
    //   2633: invokevirtual 310	java/lang/String:length	()I
    //   2636: ifle +81 -> 2717
    //   2639: aload 10
    //   2641: invokestatic 400	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   2644: astore 39
    //   2646: aload 38
    //   2648: aload 39
    //   2650: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2653: aload 4
    //   2655: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2658: bipush 8
    //   2660: invokevirtual 242	android/widget/TextView:setVisibility	(I)V
    //   2663: aload 4
    //   2665: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   2668: bipush 8
    //   2670: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   2673: aload_0
    //   2674: getfield 94	com/ex/ltech/led/acti/main/DeviceListAdapter:layoutParams	Landroid/widget/RelativeLayout$LayoutParams;
    //   2677: aload_0
    //   2678: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2681: ldc_w 277
    //   2684: invokestatic 283	com/ex/ltech/led/utils/BitmapUtils:dp2px	(Landroid/content/Context;F)I
    //   2687: aload_0
    //   2688: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   2691: ldc_w 471
    //   2694: invokestatic 283	com/ex/ltech/led/utils/BitmapUtils:dp2px	(Landroid/content/Context;F)I
    //   2697: iconst_0
    //   2698: iconst_0
    //   2699: invokevirtual 287	android/widget/RelativeLayout$LayoutParams:setMargins	(IIII)V
    //   2702: aload 4
    //   2704: getfield 191	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:tv_device_name	Landroid/widget/TextView;
    //   2707: aload_0
    //   2708: getfield 94	com/ex/ltech/led/acti/main/DeviceListAdapter:layoutParams	Landroid/widget/RelativeLayout$LayoutParams;
    //   2711: invokevirtual 291	android/widget/TextView:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   2714: goto -1353 -> 1361
    //   2717: aload_0
    //   2718: getfield 63	com/ex/ltech/led/acti/main/DeviceListAdapter:hongwaiBm	Landroid/graphics/Bitmap;
    //   2721: astore 39
    //   2723: goto -77 -> 2646
    //   2726: aload 4
    //   2728: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2731: aload_0
    //   2732: getfield 87	com/ex/ltech/led/acti/main/DeviceListAdapter:remoteOffline	Landroid/graphics/Bitmap;
    //   2735: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2738: goto -85 -> 2653
    //   2741: aload 7
    //   2743: invokevirtual 396	io/xlink/wifi/js/bean/Device:isOnline	()Z
    //   2746: ifeq +63 -> 2809
    //   2749: aload 4
    //   2751: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2754: astore 36
    //   2756: aload 10
    //   2758: invokevirtual 310	java/lang/String:length	()I
    //   2761: ifle +39 -> 2800
    //   2764: aload 10
    //   2766: invokestatic 400	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   2769: astore 37
    //   2771: aload 36
    //   2773: aload 37
    //   2775: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2778: aload 4
    //   2780: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2783: iconst_0
    //   2784: invokevirtual 242	android/widget/TextView:setVisibility	(I)V
    //   2787: aload 4
    //   2789: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   2792: bipush 8
    //   2794: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   2797: goto -1436 -> 1361
    //   2800: aload_0
    //   2801: getfield 66	com/ex/ltech/led/acti/main/DeviceListAdapter:ctBm	Landroid/graphics/Bitmap;
    //   2804: astore 37
    //   2806: goto -35 -> 2771
    //   2809: aload 4
    //   2811: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2814: aload_0
    //   2815: getfield 81	com/ex/ltech/led/acti/main/DeviceListAdapter:lightOffline	Landroid/graphics/Bitmap;
    //   2818: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2821: goto -43 -> 2778
    //   2824: aload 7
    //   2826: invokevirtual 396	io/xlink/wifi/js/bean/Device:isOnline	()Z
    //   2829: ifeq +63 -> 2892
    //   2832: aload 4
    //   2834: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2837: astore 34
    //   2839: aload 10
    //   2841: invokevirtual 310	java/lang/String:length	()I
    //   2844: ifle +39 -> 2883
    //   2847: aload 10
    //   2849: invokestatic 400	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   2852: astore 35
    //   2854: aload 34
    //   2856: aload 35
    //   2858: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2861: aload 4
    //   2863: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2866: iconst_0
    //   2867: invokevirtual 242	android/widget/TextView:setVisibility	(I)V
    //   2870: aload 4
    //   2872: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   2875: bipush 8
    //   2877: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   2880: goto -1519 -> 1361
    //   2883: aload_0
    //   2884: getfield 66	com/ex/ltech/led/acti/main/DeviceListAdapter:ctBm	Landroid/graphics/Bitmap;
    //   2887: astore 35
    //   2889: goto -35 -> 2854
    //   2892: aload 4
    //   2894: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2897: aload_0
    //   2898: getfield 81	com/ex/ltech/led/acti/main/DeviceListAdapter:lightOffline	Landroid/graphics/Bitmap;
    //   2901: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2904: goto -43 -> 2861
    //   2907: aload 7
    //   2909: invokevirtual 396	io/xlink/wifi/js/bean/Device:isOnline	()Z
    //   2912: ifeq +74 -> 2986
    //   2915: aload 4
    //   2917: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2920: astore 32
    //   2922: aload 10
    //   2924: invokevirtual 310	java/lang/String:length	()I
    //   2927: ifle +50 -> 2977
    //   2930: aload 10
    //   2932: invokestatic 400	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   2935: astore 33
    //   2937: aload 32
    //   2939: aload 33
    //   2941: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2944: aload 4
    //   2946: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2949: iconst_0
    //   2950: invokevirtual 242	android/widget/TextView:setVisibility	(I)V
    //   2953: aload 4
    //   2955: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   2958: ldc_w 263
    //   2961: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   2964: aload 4
    //   2966: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   2969: bipush 8
    //   2971: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   2974: goto -1613 -> 1361
    //   2977: aload_0
    //   2978: getfield 75	com/ex/ltech/led/acti/main/DeviceListAdapter:oneZeroFiveBm	Landroid/graphics/Bitmap;
    //   2981: astore 33
    //   2983: goto -46 -> 2937
    //   2986: aload 4
    //   2988: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   2991: aload_0
    //   2992: getfield 78	com/ex/ltech/led/acti/main/DeviceListAdapter:oneZeroFiveOff	Landroid/graphics/Bitmap;
    //   2995: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   2998: goto -54 -> 2944
    //   3001: aload 7
    //   3003: invokevirtual 295	io/xlink/wifi/js/bean/Device:getXDevice	()Lio/xlink/wifi/sdk/XDevice;
    //   3006: invokevirtual 300	io/xlink/wifi/sdk/XDevice:getProductId	()Ljava/lang/String;
    //   3009: ldc_w 412
    //   3012: invokevirtual 137	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3015: ifeq +40 -> 3055
    //   3018: aload 7
    //   3020: invokevirtual 396	io/xlink/wifi/js/bean/Device:isOnline	()Z
    //   3023: ifeq +64 -> 3087
    //   3026: aload 4
    //   3028: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   3031: astore 30
    //   3033: aload 10
    //   3035: invokevirtual 310	java/lang/String:length	()I
    //   3038: ifle +40 -> 3078
    //   3041: aload 10
    //   3043: invokestatic 400	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   3046: astore 31
    //   3048: aload 30
    //   3050: aload 31
    //   3052: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   3055: aload 4
    //   3057: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   3060: bipush 8
    //   3062: invokevirtual 242	android/widget/TextView:setVisibility	(I)V
    //   3065: aload 4
    //   3067: getfield 206	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbColor	Lcom/ex/ltech/led/my_view/ColorSeletedView;
    //   3070: bipush 8
    //   3072: invokevirtual 276	com/ex/ltech/led/my_view/ColorSeletedView:setVisibility	(I)V
    //   3075: goto -1714 -> 1361
    //   3078: aload_0
    //   3079: getfield 69	com/ex/ltech/led/acti/main/DeviceListAdapter:bwBm	Landroid/graphics/Bitmap;
    //   3082: astore 31
    //   3084: goto -36 -> 3048
    //   3087: aload 4
    //   3089: getfield 184	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:iv_device	Lcom/ex/ltech/led/my_view/MLImageView;
    //   3092: aload_0
    //   3093: getfield 81	com/ex/ltech/led/acti/main/DeviceListAdapter:lightOffline	Landroid/graphics/Bitmap;
    //   3096: invokevirtual 404	com/ex/ltech/led/my_view/MLImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   3099: goto -44 -> 3055
    //   3102: astore 25
    //   3104: aload_0
    //   3105: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   3108: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   3111: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   3114: new 119	java/lang/StringBuilder
    //   3117: dup
    //   3118: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   3121: ldc_w 473
    //   3124: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3127: aload 7
    //   3129: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   3132: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3135: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3138: ldc_w 263
    //   3141: invokeinterface 269 3 0
    //   3146: bipush 16
    //   3148: invokestatic 326	java/lang/Integer:valueOf	(Ljava/lang/String;I)Ljava/lang/Integer;
    //   3151: invokevirtual 329	java/lang/Integer:intValue	()I
    //   3154: istore 26
    //   3156: aload_0
    //   3157: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   3160: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   3163: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   3166: new 119	java/lang/StringBuilder
    //   3169: dup
    //   3170: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   3173: ldc_w 475
    //   3176: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3179: aload 7
    //   3181: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   3184: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3187: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3190: ldc_w 263
    //   3193: invokeinterface 269 3 0
    //   3198: bipush 16
    //   3200: invokestatic 326	java/lang/Integer:valueOf	(Ljava/lang/String;I)Ljava/lang/Integer;
    //   3203: invokevirtual 329	java/lang/Integer:intValue	()I
    //   3206: pop
    //   3207: aload_0
    //   3208: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   3211: invokestatic 252	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   3214: getfield 256	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   3217: new 119	java/lang/StringBuilder
    //   3220: dup
    //   3221: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   3224: ldc_w 477
    //   3227: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3230: aload 7
    //   3232: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   3235: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3238: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3241: ldc_w 263
    //   3244: invokeinterface 269 3 0
    //   3249: bipush 16
    //   3251: invokestatic 326	java/lang/Integer:valueOf	(Ljava/lang/String;I)Ljava/lang/Integer;
    //   3254: invokevirtual 329	java/lang/Integer:intValue	()I
    //   3257: pop
    //   3258: aload 4
    //   3260: getfield 199	com/ex/ltech/led/acti/main/DeviceListAdapter$Holder:rgbStatus	Landroid/widget/TextView;
    //   3263: new 119	java/lang/StringBuilder
    //   3266: dup
    //   3267: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   3270: aload_0
    //   3271: getfield 40	com/ex/ltech/led/acti/main/DeviceListAdapter:mContext	Landroid/app/Activity;
    //   3274: ldc_w 478
    //   3277: invokevirtual 381	android/app/Activity:getString	(I)Ljava/lang/String;
    //   3280: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3283: iload 26
    //   3285: bipush 100
    //   3287: imul
    //   3288: sipush 255
    //   3291: idiv
    //   3292: invokevirtual 481	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   3295: ldc_w 483
    //   3298: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3301: invokevirtual 129	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3304: invokevirtual 347	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   3307: aload 25
    //   3309: invokevirtual 458	java/lang/Exception:printStackTrace	()V
    //   3312: goto -1785 -> 1527
    //   3315: astore 17
    //   3317: goto -1740 -> 1577
    //   3320: aload 7
    //   3322: invokevirtual 259	io/xlink/wifi/js/bean/Device:getMacAddress	()Ljava/lang/String;
    //   3325: astore 19
    //   3327: goto -1680 -> 1647
    //   3330: bipush 8
    //   3332: istore 21
    //   3334: goto -1512 -> 1822
    //
    // Exception table:
    //   from	to	target	type
    //   676	936	2021	java/lang/Exception
    //   936	946	2021	java/lang/Exception
    //   1009	1103	2031	java/lang/Exception
    //   1242	1271	2212	java/lang/Exception
    //   1450	1527	3102	java/lang/Exception
    //   1361	1378	3315	java/lang/Exception
    //   1378	1395	3315	java/lang/Exception
    //   1395	1442	3315	java/lang/Exception
    //   1527	1556	3315	java/lang/Exception
    //   1556	1572	3315	java/lang/Exception
    //   3104	3312	3315	java/lang/Exception
    return null;
  }

  public List<CtSceneVo> initCtSceneVos(String paramString)
  {
    /*String str = UserFerences.getUserFerences(this.mContext).spFerences.getString(paramString + "CtSceneVos", "");
    try
    {
      List localList = (List)new Gson().fromJson(str, new TypeToken()
      {
      }
      .getType());
      return localList;
    }
    catch (JsonSyntaxException localJsonSyntaxException)
    {
    }*/
    return null;
  }

  public List<ModeVo> initCustomModes(String paramString)
  {
    /*String str = UserFerences.getUserFerences(this.mContext).spFerences.getString(paramString + "ModeDataKey", "");
    try
    {
      List localList = (List)new Gson().fromJson(str, new TypeToken()
      {
      }
      .getType());
      return localList;
    }
    catch (JsonSyntaxException localJsonSyntaxException)
    {
    }*/
    return null;
  }

  public void setOnFreshDevice(OnFreshDevice paramOnFreshDevice)
  {
    this.onFreshDevice = paramOnFreshDevice;
  }

  class Holder
  {
    MLImageView iv_device;
    ImageView off_line_lay;
    ColorSeletedView rgbColor;
    TextView rgbStatus;
    ImageView space;
    TextView tv_device_name;
    TextView tv_online_status;

    Holder()
    {
    }
  }

  class MyBitmap
  {
    Bitmap bitmap;
    boolean isBitmap;

    MyBitmap()
    {
    }

    public Bitmap getBitmap()
    {
      return this.bitmap;
    }

    public boolean isBitmap()
    {
      return this.isBitmap;
    }

    public void setBitmap(Bitmap paramBitmap)
    {
      this.bitmap = paramBitmap;
    }

    public void setIsBitmap(boolean paramBoolean)
    {
      this.isBitmap = paramBoolean;
    }
  }

  static abstract interface OnFreshDevice
  {
    public abstract void OnClick(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.main.DeviceListAdapter
 * JD-Core Version:    0.6.0
 */