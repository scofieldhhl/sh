package io.xlink.wifi.sdk;

import io.xlink.wifi.sdk.d.b;
import io.xlink.wifi.sdk.d.c;
import io.xlink.wifi.sdk.listener.ConnectDeviceListener;
import io.xlink.wifi.sdk.util.MyLog;
import java.net.InetAddress;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  public static a a;
  private final String A = "accesskey";
  private final String B = "subkey";
  private final String b = "pid";
  private final String c = "mac";
  private final String d = "dname";
  private final String e = "ip";
  private final String f = "did";
  private final String g = "port";
  private final String h = "version";
  private final String i = "mhv";
  private final String j = "msv";
  private final String k = "accesskey";
  private final String l = "subkey";
  private final String m = "init";
  private final String n = "protocol";
  private final String o = "device";
  private final String p = "macAddress";
  private final String q = "version";
  private final String r = "deviceID";
  private final String s = "deviceName";
  private final String t = "deviceIP";
  private final String u = "devicePort";
  private final String v = "deviceInit";
  private final String w = "mcuHardVersion";
  private final String x = "mucSoftVersion";
  private final String y = "mcuSoftVersion";
  private final String z = "productID";

  public static a a()
  {
    if (a == null)
      a = new a();
    return a;
  }

  public int a(XDevice paramXDevice, int paramInt1, b paramb, int paramInt2)
  {
    return XlinkAgent.getInstance().handshakeWithDevice(paramXDevice, paramInt1, paramb, paramInt2);
  }

  public int a(XDevice paramXDevice, io.xlink.wifi.sdk.d.a parama)
  {
    return XlinkAgent.getInstance().a(paramXDevice, parama);
  }

  public int a(XDevice paramXDevice, String paramString, b paramb, int paramInt)
  {
    return XlinkAgent.getInstance().a(paramXDevice, paramString, paramb, paramInt);
  }

  public XDevice a(int paramInt, XDevice paramXDevice)
  {
    paramXDevice.a(paramInt);
    return paramXDevice;
  }

  public XDevice a(XDevice paramXDevice, int paramInt)
  {
    paramXDevice.b(paramInt);
    return paramXDevice;
  }

  public XDevice a(XDevice paramXDevice, InetAddress paramInetAddress, byte[] paramArrayOfByte, String paramString, int paramInt)
  {
    if (paramInetAddress != null)
      paramXDevice.a(paramInetAddress);
    if (paramArrayOfByte != null)
      paramXDevice.a(paramArrayOfByte);
    if (paramString != null)
      paramXDevice.a(paramString);
    if (paramInt > 0)
      paramXDevice.d(paramInt);
    return paramXDevice;
  }

  public XDevice a(String paramString)
  {
    return new XDevice(paramString);
  }

  // ERROR //
  public XDevice a(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: new 163	io/xlink/wifi/sdk/XDevice
    //   3: dup
    //   4: aload_1
    //   5: ldc 44
    //   7: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   10: invokespecial 184	io/xlink/wifi/sdk/XDevice:<init>	(Ljava/lang/String;)V
    //   13: astore_2
    //   14: aload_2
    //   15: aload_1
    //   16: ldc 40
    //   18: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   21: invokevirtual 179	io/xlink/wifi/sdk/XDevice:a	(Ljava/lang/String;)V
    //   24: aload_1
    //   25: ldc 48
    //   27: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   30: ifne +132 -> 162
    //   33: aload_2
    //   34: aload_1
    //   35: ldc 48
    //   37: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   40: invokevirtual 202	io/xlink/wifi/sdk/XDevice:setDeviceName	(Ljava/lang/String;)V
    //   43: aload_1
    //   44: ldc 52
    //   46: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   49: ifeq +356 -> 405
    //   52: aload_2
    //   53: invokestatic 207	io/xlink/wifi/sdk/util/b:b	()Ljava/net/InetAddress;
    //   56: invokevirtual 173	io/xlink/wifi/sdk/XDevice:a	(Ljava/net/InetAddress;)V
    //   59: aload_2
    //   60: aload_1
    //   61: ldc 56
    //   63: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   66: invokevirtual 213	io/xlink/wifi/sdk/XDevice:e	(I)V
    //   69: aload_1
    //   70: ldc 60
    //   72: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   75: ifeq +346 -> 421
    //   78: aload_2
    //   79: getstatic 218	io/xlink/wifi/sdk/e/a:f	I
    //   82: invokevirtual 181	io/xlink/wifi/sdk/XDevice:d	(I)V
    //   85: aload_2
    //   86: aload_1
    //   87: ldc 64
    //   89: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   92: i2b
    //   93: invokevirtual 222	io/xlink/wifi/sdk/XDevice:setVersion	(B)V
    //   96: aload_1
    //   97: ldc 84
    //   99: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   102: ifeq +332 -> 434
    //   105: aload_2
    //   106: iconst_1
    //   107: invokevirtual 225	io/xlink/wifi/sdk/XDevice:a	(Z)V
    //   110: aload_1
    //   111: ldc 76
    //   113: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   116: ifeq +331 -> 447
    //   119: aload_2
    //   120: iconst_m1
    //   121: invokevirtual 228	io/xlink/wifi/sdk/XDevice:setAccessKey	(I)V
    //   124: aload_1
    //   125: ldc 80
    //   127: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   130: ifeq +330 -> 460
    //   133: aload_2
    //   134: iconst_m1
    //   135: invokevirtual 231	io/xlink/wifi/sdk/XDevice:setSubKey	(I)V
    //   138: aload_2
    //   139: aload_1
    //   140: ldc 68
    //   142: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   145: i2b
    //   146: invokevirtual 234	io/xlink/wifi/sdk/XDevice:setMcuHardVersion	(B)V
    //   149: aload_2
    //   150: aload_1
    //   151: ldc 72
    //   153: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   156: i2b
    //   157: invokevirtual 237	io/xlink/wifi/sdk/XDevice:setMcuSoftVersion	(I)V
    //   160: aload_2
    //   161: areturn
    //   162: aload_2
    //   163: ldc 239
    //   165: invokevirtual 202	io/xlink/wifi/sdk/XDevice:setDeviceName	(Ljava/lang/String;)V
    //   168: goto -125 -> 43
    //   171: astore_3
    //   172: aload_1
    //   173: ldc 88
    //   175: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   178: iconst_1
    //   179: if_icmpne +224 -> 403
    //   182: new 191	org/json/JSONObject
    //   185: dup
    //   186: aload_1
    //   187: ldc 92
    //   189: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   192: invokespecial 240	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   195: astore 5
    //   197: new 163	io/xlink/wifi/sdk/XDevice
    //   200: dup
    //   201: aload 5
    //   203: ldc 96
    //   205: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   208: invokespecial 184	io/xlink/wifi/sdk/XDevice:<init>	(Ljava/lang/String;)V
    //   211: astore 6
    //   213: aload 6
    //   215: aload 5
    //   217: ldc 64
    //   219: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   222: i2b
    //   223: invokevirtual 222	io/xlink/wifi/sdk/XDevice:setVersion	(B)V
    //   226: aload 6
    //   228: aload 5
    //   230: ldc 102
    //   232: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   235: invokevirtual 213	io/xlink/wifi/sdk/XDevice:e	(I)V
    //   238: aload 6
    //   240: aload 5
    //   242: ldc 134
    //   244: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   247: invokevirtual 179	io/xlink/wifi/sdk/XDevice:a	(Ljava/lang/String;)V
    //   250: aload 6
    //   252: aload 5
    //   254: ldc 122
    //   256: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   259: i2b
    //   260: invokevirtual 234	io/xlink/wifi/sdk/XDevice:setMcuHardVersion	(B)V
    //   263: aload 5
    //   265: ldc 106
    //   267: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   270: ifeq +203 -> 473
    //   273: aload 6
    //   275: ldc 239
    //   277: invokevirtual 202	io/xlink/wifi/sdk/XDevice:setDeviceName	(Ljava/lang/String;)V
    //   280: aload 5
    //   282: ldc 110
    //   284: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   287: ifeq +201 -> 488
    //   290: aload 6
    //   292: invokestatic 207	io/xlink/wifi/sdk/util/b:b	()Ljava/net/InetAddress;
    //   295: invokevirtual 173	io/xlink/wifi/sdk/XDevice:a	(Ljava/net/InetAddress;)V
    //   298: aload 5
    //   300: ldc 114
    //   302: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   305: ifeq +201 -> 506
    //   308: aload 6
    //   310: getstatic 218	io/xlink/wifi/sdk/e/a:f	I
    //   313: invokevirtual 181	io/xlink/wifi/sdk/XDevice:d	(I)V
    //   316: aload 5
    //   318: ldc 76
    //   320: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   323: ifeq +198 -> 521
    //   326: aload 6
    //   328: iconst_m1
    //   329: invokevirtual 228	io/xlink/wifi/sdk/XDevice:setAccessKey	(I)V
    //   332: aload 5
    //   334: ldc 80
    //   336: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   339: ifeq +197 -> 536
    //   342: aload 6
    //   344: iconst_m1
    //   345: invokevirtual 231	io/xlink/wifi/sdk/XDevice:setSubKey	(I)V
    //   348: aload 5
    //   350: ldc 84
    //   352: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   355: ifeq +196 -> 551
    //   358: aload 6
    //   360: aload 5
    //   362: ldc 118
    //   364: invokevirtual 243	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   367: invokevirtual 225	io/xlink/wifi/sdk/XDevice:a	(Z)V
    //   370: aload 5
    //   372: ldc 126
    //   374: invokevirtual 199	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   377: ifeq +200 -> 577
    //   380: aload 6
    //   382: aload 5
    //   384: ldc 130
    //   386: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   389: i2b
    //   390: invokevirtual 237	io/xlink/wifi/sdk/XDevice:setMcuSoftVersion	(I)V
    //   393: aload 6
    //   395: areturn
    //   396: astore 4
    //   398: aload 4
    //   400: invokevirtual 246	org/json/JSONException:printStackTrace	()V
    //   403: aconst_null
    //   404: areturn
    //   405: aload_2
    //   406: aload_1
    //   407: ldc 52
    //   409: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   412: invokestatic 249	io/xlink/wifi/sdk/util/b:e	(Ljava/lang/String;)Ljava/net/InetAddress;
    //   415: invokevirtual 173	io/xlink/wifi/sdk/XDevice:a	(Ljava/net/InetAddress;)V
    //   418: goto -359 -> 59
    //   421: aload_2
    //   422: aload_1
    //   423: ldc 60
    //   425: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   428: invokevirtual 181	io/xlink/wifi/sdk/XDevice:d	(I)V
    //   431: goto -346 -> 85
    //   434: aload_2
    //   435: aload_1
    //   436: ldc 84
    //   438: invokevirtual 243	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   441: invokevirtual 225	io/xlink/wifi/sdk/XDevice:a	(Z)V
    //   444: goto -334 -> 110
    //   447: aload_2
    //   448: aload_1
    //   449: ldc 76
    //   451: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   454: invokevirtual 228	io/xlink/wifi/sdk/XDevice:setAccessKey	(I)V
    //   457: goto -333 -> 124
    //   460: aload_2
    //   461: aload_1
    //   462: ldc 80
    //   464: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   467: invokevirtual 231	io/xlink/wifi/sdk/XDevice:setSubKey	(I)V
    //   470: goto -332 -> 138
    //   473: aload 6
    //   475: aload 5
    //   477: ldc 106
    //   479: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   482: invokevirtual 202	io/xlink/wifi/sdk/XDevice:setDeviceName	(Ljava/lang/String;)V
    //   485: goto -205 -> 280
    //   488: aload 6
    //   490: aload 5
    //   492: ldc 110
    //   494: invokevirtual 195	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   497: invokestatic 249	io/xlink/wifi/sdk/util/b:e	(Ljava/lang/String;)Ljava/net/InetAddress;
    //   500: invokevirtual 173	io/xlink/wifi/sdk/XDevice:a	(Ljava/net/InetAddress;)V
    //   503: goto -205 -> 298
    //   506: aload 6
    //   508: aload 5
    //   510: ldc 114
    //   512: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   515: invokevirtual 181	io/xlink/wifi/sdk/XDevice:d	(I)V
    //   518: goto -202 -> 316
    //   521: aload 6
    //   523: aload 5
    //   525: ldc 76
    //   527: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   530: invokevirtual 228	io/xlink/wifi/sdk/XDevice:setAccessKey	(I)V
    //   533: goto -201 -> 332
    //   536: aload 6
    //   538: aload 5
    //   540: ldc 80
    //   542: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   545: invokevirtual 231	io/xlink/wifi/sdk/XDevice:setSubKey	(I)V
    //   548: goto -200 -> 348
    //   551: aload 6
    //   553: aload 5
    //   555: ldc 84
    //   557: invokevirtual 243	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   560: invokevirtual 225	io/xlink/wifi/sdk/XDevice:a	(Z)V
    //   563: goto -193 -> 370
    //   566: astore 7
    //   568: aload 6
    //   570: iconst_1
    //   571: invokevirtual 225	io/xlink/wifi/sdk/XDevice:a	(Z)V
    //   574: goto -204 -> 370
    //   577: aload 6
    //   579: aload 5
    //   581: ldc 126
    //   583: invokevirtual 211	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   586: i2b
    //   587: invokevirtual 237	io/xlink/wifi/sdk/XDevice:setMcuSoftVersion	(I)V
    //   590: aload 6
    //   592: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	43	171	org/json/JSONException
    //   43	59	171	org/json/JSONException
    //   59	85	171	org/json/JSONException
    //   85	110	171	org/json/JSONException
    //   110	124	171	org/json/JSONException
    //   124	138	171	org/json/JSONException
    //   138	160	171	org/json/JSONException
    //   162	168	171	org/json/JSONException
    //   405	418	171	org/json/JSONException
    //   421	431	171	org/json/JSONException
    //   434	444	171	org/json/JSONException
    //   447	457	171	org/json/JSONException
    //   460	470	171	org/json/JSONException
    //   172	280	396	org/json/JSONException
    //   280	298	396	org/json/JSONException
    //   298	316	396	org/json/JSONException
    //   316	332	396	org/json/JSONException
    //   332	348	396	org/json/JSONException
    //   348	370	396	org/json/JSONException
    //   370	393	396	org/json/JSONException
    //   473	485	396	org/json/JSONException
    //   488	503	396	org/json/JSONException
    //   506	518	396	org/json/JSONException
    //   521	533	396	org/json/JSONException
    //   536	548	396	org/json/JSONException
    //   551	563	396	org/json/JSONException
    //   568	574	396	org/json/JSONException
    //   577	590	396	org/json/JSONException
    //   348	370	566	java/lang/Exception
    //   551	563	566	java/lang/Exception
  }

  public XDevice a(boolean paramBoolean, XDevice paramXDevice)
  {
    paramXDevice.a(paramBoolean);
    return paramXDevice;
  }

  public void a(XDevice paramXDevice)
  {
    MyLog.e("DeviceAgent", "reconnectDevice xDevice:" + paramXDevice);
    c.a(-1, paramXDevice);
    XlinkAgent.getInstance().connectDevice(paramXDevice, paramXDevice.getAccessKey(), new ConnectDeviceListener()
    {
      public void onConnectDevice(XDevice paramXDevice, int paramInt)
      {
        MyLog.e("Reconnect", "auth reconnectDevice:" + paramXDevice + " code:" + paramInt);
        if ((paramInt == 0) || (paramInt == 1))
        {
          c.a(-3, paramXDevice);
          return;
        }
        paramXDevice.b(0);
        c.a(-2, paramXDevice);
      }
    });
  }

  public void a(XDevice paramXDevice, String paramString)
  {
    paramXDevice.b(paramString);
  }

  public XDevice b(XDevice paramXDevice, int paramInt)
  {
    paramXDevice.e(paramInt);
    return paramXDevice;
  }

  public void b(XDevice paramXDevice)
  {
    paramXDevice.b();
  }

  public XDevice c(XDevice paramXDevice, int paramInt)
  {
    paramXDevice.c(paramInt);
    return paramXDevice;
  }

  public JSONObject c(XDevice paramXDevice)
    throws JSONException
  {
    JSONObject localJSONObject1 = new JSONObject();
    localJSONObject1.put("protocol", 1);
    JSONObject localJSONObject2 = new JSONObject();
    localJSONObject2.put("deviceName", paramXDevice.getDeviceName() + "");
    localJSONObject2.put("macAddress", paramXDevice.getMacAddress());
    localJSONObject2.put("deviceIP", paramXDevice.getAddress().getHostAddress());
    localJSONObject2.put("deviceID", paramXDevice.getDeviceId());
    localJSONObject2.put("productID", paramXDevice.getProductId());
    localJSONObject2.put("devicePort", paramXDevice.getPort());
    localJSONObject2.put("version", paramXDevice.getVersion());
    localJSONObject2.put("mcuHardVersion", paramXDevice.getMcuHardVersion());
    localJSONObject2.put("mcuSoftVersion", paramXDevice.getMcuSoftVersion());
    localJSONObject2.put("deviceInit", paramXDevice.isInit());
    localJSONObject2.put("accesskey", paramXDevice.getAccessKey());
    localJSONObject2.put("subkey", paramXDevice.getSubKey());
    localJSONObject1.put("device", localJSONObject2);
    return localJSONObject1;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.a
 * JD-Core Version:    0.6.0
 */