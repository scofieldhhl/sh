package io.xlink.wifi.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.IBinder;
import io.xlink.wifi.sdk.c.e;
import io.xlink.wifi.sdk.c.f;
import io.xlink.wifi.sdk.c.g;
import io.xlink.wifi.sdk.util.MyLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class XlinkTcpService extends Service
{
  public static int a;
  public static String b;
  public static boolean d = false;
  public static boolean h;
  public static int k;
  private static XlinkTcpService m;
  private static String q = io.xlink.wifi.sdk.util.b.a.getPackageName() + "-tcp-keep";
  private static String r = io.xlink.wifi.sdk.util.b.a.getPackageName() + "-tcp-reconnect";
  private static String x;
  private static String y;
  public io.xlink.wifi.sdk.g.b c;
  public int e = 0;
  public long f;
  public final Timer g = new Timer();
  int i = 0;
  boolean j = false;
  private final String l = "TCPService";
  private Socket n;
  private boolean o;
  private boolean p;
  private boolean s = false;
  private BroadcastReceiver t = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      boolean bool1 = false;
      String str = paramIntent.getAction();
      MyLog.e("TCPService", "TCP mReceiver " + new Date() + "  " + System.currentTimeMillis());
      if (str.equals(XlinkTcpService.e()))
        if (!XlinkTcpService.c())
        {
          XlinkTcpService.a(XlinkTcpService.this);
          XlinkTcpService.a(XlinkTcpService.this, "tcp !isConnected stopKeepAlive ");
        }
      do
      {
        long l2;
        do
        {
          return;
          long l1 = (System.currentTimeMillis() - XlinkTcpService.this.f) / 1000L;
          if (XlinkTcpService.this.e > 3)
          {
            XlinkTcpService.a(XlinkTcpService.this, "tcp 3次心跳超时" + l1 + " cloud service 3 times not response ping app logout ");
            XlinkTcpService.a(XlinkTcpService.this);
            XlinkTcpService.this.a(true, -2, true);
            return;
          }
          l2 = System.currentTimeMillis() - XlinkTcpService.this.f;
        }
        while ((XlinkTcpService.this.e == 0) && (l2 < 1000 * (-3 + io.xlink.wifi.sdk.e.a.e / 2)));
        XlinkTcpService localXlinkTcpService = XlinkTcpService.this;
        localXlinkTcpService.e = (1 + localXlinkTcpService.e);
        io.xlink.wifi.sdk.g.b.a = 0;
        g localg = io.xlink.wifi.sdk.g.c.a().b();
        XlinkTcpService.this.a(localg);
        XlinkTcpService.a(XlinkTcpService.this, "tcp send keep alive packet ");
        XlinkTcpService.this.f = System.currentTimeMillis();
        return;
        if (!str.equals(XlinkTcpService.f()))
          continue;
        XlinkTcpService.b(XlinkTcpService.this);
        return;
      }
      while (!paramIntent.getAction().equals("android.intent.action.TIME_TICK"));
      Iterator localIterator = ((ActivityManager)io.xlink.wifi.sdk.util.b.a.getSystemService("activity")).getRunningServices(2147483647).iterator();
      label317: if (localIterator.hasNext())
        if (!"io.xlink.wifi.sdk.XlinkTcpService".equals(((RunningServiceInfo)localIterator.next()).service.getClassName()))
          break label415;
      label415: for (boolean bool2 = true; ; bool2 = bool1)
      {
        bool1 = bool2;
        break label317;
        XlinkTcpService.a(XlinkTcpService.this, "tcp isServiceRunning:" + bool1);
        if (bool1)
          break;
        paramContext.startService(new Intent(paramContext, XlinkTcpService.class));
        XlinkTcpService.a(XlinkTcpService.this, "tcp RestartService...");
        return;
      }
    }
  };
  private TimerTask u;
  private InputStream v;
  private OutputStream w;
  private InetAddress z;

  static
  {
    h = false;
    x = "123456";
    y = "xlink_tclient.bks";
  }

  public static XlinkTcpService a()
  {
    return m;
  }

  public static void a(String paramString)
  {
    y = paramString;
  }

  private void a(Socket paramSocket)
    throws IOException
  {
    int i1 = 1;
    if (this.c == null);
    while (true)
    {
      try
      {
        this.v = paramSocket.getInputStream();
        this.w = paramSocket.getOutputStream();
        if (i1 == 0)
          continue;
        this.c = new io.xlink.wifi.sdk.g.b(this, this.v);
        if (io.xlink.wifi.sdk.g.a.a().c())
          continue;
        io.xlink.wifi.sdk.g.a.a().b();
        io.xlink.wifi.sdk.g.a.a().a(this, this.w);
        this.c.a();
        this.o = true;
        if ((io.xlink.wifi.sdk.e.a.j == 3) || (k == 3))
        {
          c("send http prot head");
          7 local7 = new io.xlink.wifi.sdk.d.a()
          {
            public void onResponse(e parame)
            {
              if (parame.b == 0)
              {
                XlinkTcpService.a(XlinkTcpService.this, "http prot head succeed  send login packet");
                XlinkTcpService.g(XlinkTcpService.this);
                return;
              }
              XlinkTcpService.a(XlinkTcpService.this, "http prot head timeout  connect tcp error");
              XlinkTcpService.b(XlinkTcpService.this, false);
              io.xlink.wifi.sdk.d.c.a(5, -1);
              XlinkTcpService.this.a(false, -1, true);
            }
          };
          g localg = new g(f.a().a(local7), local7, 3);
          io.xlink.wifi.sdk.g.a.a().a(localg);
          this.i = 0;
          return;
          this.c.a(this.v);
          continue;
        }
      }
      catch (IOException localIOException)
      {
        if (this.c == null);
      }
      try
      {
        this.c.b();
        label176: this.c = null;
        if (this.v != null);
        try
        {
          this.v.close();
          label195: this.v = null;
          if (this.w != null);
          try
          {
            this.w.close();
            label214: this.w = null;
            if (paramSocket != null);
            try
            {
              paramSocket.close();
              label227: this.o = false;
              throw localIOException;
              n();
            }
            catch (Exception localException)
            {
              break label227;
            }
          }
          catch (Throwable localThrowable1)
          {
            break label214;
          }
        }
        catch (Throwable localThrowable2)
        {
          break label195;
        }
      }
      catch (Throwable localThrowable3)
      {
        break label176;
      }
      i1 = 0;
    }
  }

  public static void b(String paramString)
  {
    x = paramString;
  }

  private void c(String paramString)
  {
    MyLog.e("TCPService", paramString);
  }

  public static boolean c()
  {
    if (a() == null)
      return false;
    return a().b();
  }

  private void i()
  {
    if (h)
      return;
    this.i = (1 + this.i);
    if (this.i > 2)
    {
      this.i = 0;
      return;
    }
    c("start reconnect tcp server");
    this.j = false;
    this.g.schedule(new TimerTask()
    {
      public void run()
      {
        if (XlinkTcpService.this.j)
          return;
        XlinkTcpService.b(XlinkTcpService.this);
      }
    }
    , 10000L);
  }

  private void j()
  {
    if (this.u != null)
    {
      this.u.cancel();
      this.g.purge();
      this.u = null;
    }
    this.s = false;
  }

  private void k()
  {
    this.j = true;
  }

  private void l()
  {
    if (d)
    {
      c("connectInSSL ，return...connecting is true");
      return;
    }
    if (c())
    {
      c("connectInSSL---return isConnected is true");
      return;
    }
    new Thread()
    {
      public void run()
      {
        super.run();
        if (XlinkTcpService.c())
        {
          XlinkTcpService.a(XlinkTcpService.this, "isConnected is true  --return connectInSSL()");
          return;
        }
        try
        {
          if (XlinkTcpService.c(XlinkTcpService.this) == null)
            XlinkTcpService.a(XlinkTcpService.this, InetAddress.getByName(io.xlink.wifi.sdk.e.a.a));
          SSLContext localSSLContext = SSLContext.getInstance("SSL");
          KeyStore localKeyStore1 = KeyStore.getInstance("BKS");
          InputStream localInputStream = XlinkTcpService.this.getResources().getAssets().open(XlinkTcpService.g());
          KeyStore localKeyStore2 = KeyStore.getInstance("BKS");
          localKeyStore2.load(localInputStream, XlinkTcpService.h().toCharArray());
          KeyManagerFactory localKeyManagerFactory = KeyManagerFactory.getInstance("X509");
          localKeyManagerFactory.init(localKeyStore1, XlinkTcpService.h().toCharArray());
          TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance("X509");
          localTrustManagerFactory.init(localKeyStore2);
          localSSLContext.init(localKeyManagerFactory.getKeyManagers(), localTrustManagerFactory.getTrustManagers(), null);
          XlinkTcpService.a(XlinkTcpService.this, "SSLContext initialize succeed");
          SSLSocketFactory localSSLSocketFactory = localSSLContext.getSocketFactory();
          XlinkTcpService.a(XlinkTcpService.this, (SSLSocket)localSSLSocketFactory.createSocket(XlinkTcpService.c(XlinkTcpService.this), io.xlink.wifi.sdk.e.a.b));
          XlinkTcpService.b(XlinkTcpService.this, XlinkTcpService.d(XlinkTcpService.this));
          return;
        }
        catch (UnknownHostException localUnknownHostException)
        {
          XlinkTcpService.e(XlinkTcpService.this);
          XlinkTcpService.d = false;
          XlinkTcpService.a(XlinkTcpService.this, "UnknownHostException connect SSL tcp error...");
          io.xlink.wifi.sdk.d.c.a(5, -1);
          return;
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
          XlinkTcpService.e(XlinkTcpService.this);
          XlinkTcpService.d = false;
          XlinkTcpService.a(XlinkTcpService.this, "connect SSL tcp IOException...");
          io.xlink.wifi.sdk.d.c.a(5, -1);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          XlinkTcpService.e(XlinkTcpService.this);
          XlinkTcpService.d = false;
          XlinkTcpService.a(XlinkTcpService.this, "connect SSL tcp Exception...");
          io.xlink.wifi.sdk.d.c.a(5, -1);
          return;
        }
        finally
        {
          XlinkTcpService.d = false;
        }
        throw localObject;
      }
    }
    .start();
    d = true;
    c("cloud connectSSL main thread done");
  }

  private void m()
  {
    if (d)
    {
      c("connecting is true  intercept connect() method");
      return;
    }
    if (c())
    {
      c("isConnected is true  intercept connect() method");
      return;
    }
    if (!io.xlink.wifi.sdk.f.a.a().d())
    {
      c("connect tcp  network is not available");
      io.xlink.wifi.sdk.d.c.a(5, -2);
      return;
    }
    k();
    d = true;
    new Thread()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: invokespecial 28	java/lang/Thread:run	()V
        //   4: invokestatic 32	io/xlink/wifi/sdk/XlinkTcpService:c	()Z
        //   7: ifeq +13 -> 20
        //   10: aload_0
        //   11: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   14: ldc 34
        //   16: invokestatic 37	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/lang/String;)V
        //   19: return
        //   20: aload_0
        //   21: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   24: invokestatic 41	io/xlink/wifi/sdk/XlinkTcpService:f	(Lio/xlink/wifi/sdk/XlinkTcpService;)Z
        //   27: ifeq +26 -> 53
        //   30: getstatic 44	io/xlink/wifi/sdk/XlinkTcpService:a	I
        //   33: ifeq +20 -> 53
        //   36: aload_0
        //   37: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   40: ldc 46
        //   42: invokestatic 37	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/lang/String;)V
        //   45: aload_0
        //   46: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   49: invokestatic 49	io/xlink/wifi/sdk/XlinkTcpService:g	(Lio/xlink/wifi/sdk/XlinkTcpService;)V
        //   52: return
        //   53: aload_0
        //   54: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   57: new 51	java/lang/StringBuilder
        //   60: dup
        //   61: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   64: ldc 54
        //   66: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   69: getstatic 63	io/xlink/wifi/sdk/e/a:a	Ljava/lang/String;
        //   72: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   75: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   78: invokestatic 37	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/lang/String;)V
        //   81: aload_0
        //   82: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   85: invokestatic 70	io/xlink/wifi/sdk/XlinkTcpService:c	(Lio/xlink/wifi/sdk/XlinkTcpService;)Ljava/net/InetAddress;
        //   88: ifnonnull +38 -> 126
        //   91: new 72	io/xlink/wifi/sdk/util/a
        //   94: dup
        //   95: getstatic 63	io/xlink/wifi/sdk/e/a:a	Ljava/lang/String;
        //   98: invokespecial 75	io/xlink/wifi/sdk/util/a:<init>	(Ljava/lang/String;)V
        //   101: astore_3
        //   102: aload_3
        //   103: invokevirtual 78	io/xlink/wifi/sdk/util/a:start	()V
        //   106: aload_3
        //   107: getstatic 80	io/xlink/wifi/sdk/e/a:g	I
        //   110: i2l
        //   111: invokevirtual 84	io/xlink/wifi/sdk/util/a:join	(J)V
        //   114: aload_0
        //   115: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   118: aload_3
        //   119: invokevirtual 87	io/xlink/wifi/sdk/util/a:a	()Ljava/net/InetAddress;
        //   122: invokestatic 90	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/net/InetAddress;)Ljava/net/InetAddress;
        //   125: pop
        //   126: aload_0
        //   127: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   130: invokestatic 70	io/xlink/wifi/sdk/XlinkTcpService:c	(Lio/xlink/wifi/sdk/XlinkTcpService;)Ljava/net/InetAddress;
        //   133: ifnonnull +123 -> 256
        //   136: new 20	java/net/UnknownHostException
        //   139: dup
        //   140: new 51	java/lang/StringBuilder
        //   143: dup
        //   144: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   147: ldc 92
        //   149: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   152: getstatic 63	io/xlink/wifi/sdk/e/a:a	Ljava/lang/String;
        //   155: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   158: ldc 94
        //   160: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   163: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   166: invokespecial 95	java/net/UnknownHostException:<init>	(Ljava/lang/String;)V
        //   169: athrow
        //   170: astore_2
        //   171: iconst_0
        //   172: putstatic 99	io/xlink/wifi/sdk/XlinkTcpService:d	Z
        //   175: aload_0
        //   176: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   179: ldc 101
        //   181: invokestatic 37	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/lang/String;)V
        //   184: iconst_5
        //   185: iconst_m1
        //   186: invokestatic 106	io/xlink/wifi/sdk/d/c:a	(II)V
        //   189: aload_0
        //   190: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   193: invokestatic 109	io/xlink/wifi/sdk/XlinkTcpService:e	(Lio/xlink/wifi/sdk/XlinkTcpService;)V
        //   196: return
        //   197: astore 4
        //   199: aload 4
        //   201: invokevirtual 112	java/lang/InterruptedException:printStackTrace	()V
        //   204: goto -90 -> 114
        //   207: astore_1
        //   208: aload_0
        //   209: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   212: ldc 114
        //   214: invokestatic 37	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/lang/String;)V
        //   217: getstatic 117	io/xlink/wifi/sdk/e/a:j	I
        //   220: iconst_1
        //   221: if_icmpne +318 -> 539
        //   224: iconst_0
        //   225: putstatic 99	io/xlink/wifi/sdk/XlinkTcpService:d	Z
        //   228: getstatic 120	io/xlink/wifi/sdk/XlinkTcpService:k	I
        //   231: iconst_1
        //   232: if_icmpne +253 -> 485
        //   235: aload_0
        //   236: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   239: ldc 122
        //   241: invokestatic 37	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/lang/String;)V
        //   244: iconst_3
        //   245: putstatic 120	io/xlink/wifi/sdk/XlinkTcpService:k	I
        //   248: aload_0
        //   249: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   252: invokestatic 125	io/xlink/wifi/sdk/XlinkTcpService:b	(Lio/xlink/wifi/sdk/XlinkTcpService;)V
        //   255: return
        //   256: aload_0
        //   257: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   260: invokestatic 128	io/xlink/wifi/sdk/XlinkTcpService:d	(Lio/xlink/wifi/sdk/XlinkTcpService;)Ljava/net/Socket;
        //   263: astore 6
        //   265: aload 6
        //   267: ifnull +22 -> 289
        //   270: aload_0
        //   271: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   274: invokestatic 128	io/xlink/wifi/sdk/XlinkTcpService:d	(Lio/xlink/wifi/sdk/XlinkTcpService;)Ljava/net/Socket;
        //   277: invokevirtual 133	java/net/Socket:close	()V
        //   280: aload_0
        //   281: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   284: aconst_null
        //   285: invokestatic 136	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/net/Socket;)Ljava/net/Socket;
        //   288: pop
        //   289: aload_0
        //   290: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   293: new 130	java/net/Socket
        //   296: dup
        //   297: invokespecial 137	java/net/Socket:<init>	()V
        //   300: invokestatic 136	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/net/Socket;)Ljava/net/Socket;
        //   303: pop
        //   304: getstatic 117	io/xlink/wifi/sdk/e/a:j	I
        //   307: iconst_3
        //   308: if_icmpeq +10 -> 318
        //   311: getstatic 120	io/xlink/wifi/sdk/XlinkTcpService:k	I
        //   314: iconst_3
        //   315: if_icmpne +97 -> 412
        //   318: aload_0
        //   319: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   322: new 51	java/lang/StringBuilder
        //   325: dup
        //   326: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   329: ldc 139
        //   331: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   334: aload_0
        //   335: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   338: invokestatic 70	io/xlink/wifi/sdk/XlinkTcpService:c	(Lio/xlink/wifi/sdk/XlinkTcpService;)Ljava/net/InetAddress;
        //   341: invokevirtual 142	java/net/InetAddress:toString	()Ljava/lang/String;
        //   344: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   347: ldc 144
        //   349: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   352: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   355: invokestatic 37	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/lang/String;)V
        //   358: aload_0
        //   359: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   362: invokestatic 128	io/xlink/wifi/sdk/XlinkTcpService:d	(Lio/xlink/wifi/sdk/XlinkTcpService;)Ljava/net/Socket;
        //   365: new 146	java/net/InetSocketAddress
        //   368: dup
        //   369: aload_0
        //   370: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   373: invokestatic 70	io/xlink/wifi/sdk/XlinkTcpService:c	(Lio/xlink/wifi/sdk/XlinkTcpService;)Ljava/net/InetAddress;
        //   376: getstatic 148	io/xlink/wifi/sdk/e/a:d	I
        //   379: invokespecial 151	java/net/InetSocketAddress:<init>	(Ljava/net/InetAddress;I)V
        //   382: getstatic 80	io/xlink/wifi/sdk/e/a:g	I
        //   385: invokevirtual 155	java/net/Socket:connect	(Ljava/net/SocketAddress;I)V
        //   388: aload_0
        //   389: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   392: aload_0
        //   393: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   396: invokestatic 128	io/xlink/wifi/sdk/XlinkTcpService:d	(Lio/xlink/wifi/sdk/XlinkTcpService;)Ljava/net/Socket;
        //   399: invokestatic 158	io/xlink/wifi/sdk/XlinkTcpService:b	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/net/Socket;)V
        //   402: aload_0
        //   403: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   406: ldc 160
        //   408: invokestatic 37	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/lang/String;)V
        //   411: return
        //   412: aload_0
        //   413: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   416: new 51	java/lang/StringBuilder
        //   419: dup
        //   420: invokespecial 52	java/lang/StringBuilder:<init>	()V
        //   423: ldc 139
        //   425: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   428: aload_0
        //   429: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   432: invokestatic 70	io/xlink/wifi/sdk/XlinkTcpService:c	(Lio/xlink/wifi/sdk/XlinkTcpService;)Ljava/net/InetAddress;
        //   435: invokevirtual 142	java/net/InetAddress:toString	()Ljava/lang/String;
        //   438: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   441: ldc 162
        //   443: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   446: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   449: invokestatic 37	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/lang/String;)V
        //   452: aload_0
        //   453: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   456: invokestatic 128	io/xlink/wifi/sdk/XlinkTcpService:d	(Lio/xlink/wifi/sdk/XlinkTcpService;)Ljava/net/Socket;
        //   459: new 146	java/net/InetSocketAddress
        //   462: dup
        //   463: aload_0
        //   464: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   467: invokestatic 70	io/xlink/wifi/sdk/XlinkTcpService:c	(Lio/xlink/wifi/sdk/XlinkTcpService;)Ljava/net/InetAddress;
        //   470: getstatic 164	io/xlink/wifi/sdk/e/a:b	I
        //   473: invokespecial 151	java/net/InetSocketAddress:<init>	(Ljava/net/InetAddress;I)V
        //   476: getstatic 80	io/xlink/wifi/sdk/e/a:g	I
        //   479: invokevirtual 155	java/net/Socket:connect	(Ljava/net/SocketAddress;I)V
        //   482: goto -94 -> 388
        //   485: getstatic 120	io/xlink/wifi/sdk/XlinkTcpService:k	I
        //   488: iconst_3
        //   489: if_icmpne +24 -> 513
        //   492: aload_0
        //   493: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   496: ldc 166
        //   498: invokestatic 37	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/lang/String;)V
        //   501: iconst_4
        //   502: putstatic 120	io/xlink/wifi/sdk/XlinkTcpService:k	I
        //   505: aload_0
        //   506: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   509: invokestatic 169	io/xlink/wifi/sdk/XlinkTcpService:h	(Lio/xlink/wifi/sdk/XlinkTcpService;)V
        //   512: return
        //   513: aload_0
        //   514: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   517: invokestatic 109	io/xlink/wifi/sdk/XlinkTcpService:e	(Lio/xlink/wifi/sdk/XlinkTcpService;)V
        //   520: iconst_0
        //   521: putstatic 99	io/xlink/wifi/sdk/XlinkTcpService:d	Z
        //   524: aload_0
        //   525: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   528: ldc 114
        //   530: invokestatic 37	io/xlink/wifi/sdk/XlinkTcpService:a	(Lio/xlink/wifi/sdk/XlinkTcpService;Ljava/lang/String;)V
        //   533: iconst_5
        //   534: iconst_m1
        //   535: invokestatic 106	io/xlink/wifi/sdk/d/c:a	(II)V
        //   538: return
        //   539: iconst_0
        //   540: putstatic 99	io/xlink/wifi/sdk/XlinkTcpService:d	Z
        //   543: aload_0
        //   544: getfield 15	io/xlink/wifi/sdk/XlinkTcpService$5:a	Lio/xlink/wifi/sdk/XlinkTcpService;
        //   547: invokestatic 109	io/xlink/wifi/sdk/XlinkTcpService:e	(Lio/xlink/wifi/sdk/XlinkTcpService;)V
        //   550: iconst_5
        //   551: iconst_m1
        //   552: invokestatic 106	io/xlink/wifi/sdk/d/c:a	(II)V
        //   555: return
        //   556: astore 8
        //   558: goto -278 -> 280
        //
        // Exception table:
        //   from	to	target	type
        //   53	106	170	java/net/UnknownHostException
        //   106	114	170	java/net/UnknownHostException
        //   114	126	170	java/net/UnknownHostException
        //   126	170	170	java/net/UnknownHostException
        //   199	204	170	java/net/UnknownHostException
        //   256	265	170	java/net/UnknownHostException
        //   270	280	170	java/net/UnknownHostException
        //   280	289	170	java/net/UnknownHostException
        //   289	318	170	java/net/UnknownHostException
        //   318	388	170	java/net/UnknownHostException
        //   388	411	170	java/net/UnknownHostException
        //   412	482	170	java/net/UnknownHostException
        //   106	114	197	java/lang/InterruptedException
        //   53	106	207	java/io/IOException
        //   106	114	207	java/io/IOException
        //   114	126	207	java/io/IOException
        //   126	170	207	java/io/IOException
        //   199	204	207	java/io/IOException
        //   256	265	207	java/io/IOException
        //   270	280	207	java/io/IOException
        //   280	289	207	java/io/IOException
        //   289	318	207	java/io/IOException
        //   318	388	207	java/io/IOException
        //   388	411	207	java/io/IOException
        //   412	482	207	java/io/IOException
        //   270	280	556	java/lang/Exception
      }
    }
    .start();
    c("connect tcp main thread done.");
  }

  private void n()
  {
    io.xlink.wifi.sdk.g.c.a().a(a, b, new io.xlink.wifi.sdk.d.a()
    {
      public void onResponse(e parame)
      {
        XlinkTcpService.d = false;
        switch (parame.b)
        {
        default:
          XlinkTcpService.a(XlinkTcpService.this, "login fail code ::" + parame.b);
          io.xlink.wifi.sdk.d.c.a(5, parame.b);
          return;
        case 0:
          XlinkTcpService.a(XlinkTcpService.this, "login - - connect tcp succeed");
          XlinkTcpService.a(XlinkTcpService.this, true);
          io.xlink.wifi.sdk.d.c.a(4, 0);
          XlinkTcpService.this.d();
          return;
        case -100:
        }
        XlinkTcpService.a(XlinkTcpService.this, "login fail service not response packet timeout ! ");
        XlinkTcpService.e(XlinkTcpService.this);
        io.xlink.wifi.sdk.d.c.a(5, -100);
      }
    }
    , 6);
  }

  public void a(g paramg)
  {
    io.xlink.wifi.sdk.g.a.a().b(paramg);
  }

  public void a(boolean paramBoolean1, int paramInt, boolean paramBoolean2)
  {
    this.e = 0;
    if (!this.p)
      paramBoolean1 = false;
    this.p = false;
    if (this.o)
    {
      this.o = false;
      c("disconnect shutdown isDispatch :" + paramBoolean1 + " code :" + paramInt);
      if (this.c != null)
        this.c.b();
      if (this.v == null);
    }
    try
    {
      this.v.close();
      label93: this.v = null;
      if (this.w != null);
      try
      {
        this.w.close();
        label112: this.w = null;
        try
        {
          this.n.close();
          label124: if (paramBoolean1)
          {
            if (paramBoolean2)
              i();
            c("dispatch NetEvent cloud disconnect");
            io.xlink.wifi.sdk.d.c.a(6, paramInt);
          }
          return;
        }
        catch (Exception localException)
        {
          break label124;
        }
      }
      catch (Throwable localThrowable1)
      {
        break label112;
      }
    }
    catch (Throwable localThrowable2)
    {
      break label93;
    }
  }

  public boolean b()
  {
    return (this.o) && (this.p) && (a != 0);
  }

  public void d()
  {
    MyLog.e("TCPService", "cloud start KeepAlive ");
    if (System.currentTimeMillis() - this.f > 1000 * io.xlink.wifi.sdk.e.a.e)
      this.s = false;
    if (this.s)
      return;
    this.f = System.currentTimeMillis();
    int i1 = -2 + io.xlink.wifi.sdk.e.a.e / 3;
    if (this.u != null)
    {
      this.u.cancel();
      this.u = null;
    }
    this.u = new TimerTask()
    {
      public void run()
      {
        if (!XlinkTcpService.c())
        {
          XlinkTcpService.a(XlinkTcpService.this);
          XlinkTcpService.a(XlinkTcpService.this, "tcp !isConnected stopKeepAlive ");
          return;
        }
        long l = (System.currentTimeMillis() - XlinkTcpService.this.f) / 1000L;
        if (XlinkTcpService.this.e > 3)
        {
          XlinkTcpService.a(XlinkTcpService.this, "tcp 3次心跳超时" + l + " cloud service 3 times not response ping app logout ");
          XlinkTcpService.a(XlinkTcpService.this);
          XlinkTcpService.this.a(true, -2, true);
          return;
        }
        (System.currentTimeMillis() - XlinkTcpService.this.f);
        XlinkTcpService localXlinkTcpService = XlinkTcpService.this;
        localXlinkTcpService.e = (1 + localXlinkTcpService.e);
        io.xlink.wifi.sdk.g.b.a = 0;
        g localg = io.xlink.wifi.sdk.g.c.a().b();
        XlinkTcpService.this.a(localg);
        XlinkTcpService.a(XlinkTcpService.this, "tcp send keep alive packet ");
        XlinkTcpService.this.f = System.currentTimeMillis();
      }
    };
    this.g.schedule(this.u, i1 * 1000, i1 * 1000);
    this.s = true;
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    c("Xlink Tcp Service onCreate! ");
    m = this;
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction(q);
    localIntentFilter.addAction(r);
    localIntentFilter.addAction("android.intent.action.TIME_TICK");
    registerReceiver(this.t, localIntentFilter);
  }

  public void onDestroy()
  {
    unregisterReceiver(this.t);
    c("XlinkTcpService onDestroy! ");
    io.xlink.wifi.sdk.d.c.a("XTService");
    a(true, -3, false);
    k();
    if (!h)
    {
      startService(new Intent(this, XlinkTcpService.class));
      c("tcp RestartService... on onDestroy");
    }
    super.onDestroy();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    c("onStart service statue:" + c());
    if ((a == 0) || (b == null))
    {
      c("appid ==0 ||keys==null return onStartCommand");
      return 2;
    }
    if (!c())
    {
      k = io.xlink.wifi.sdk.e.a.j;
      switch (io.xlink.wifi.sdk.e.a.j)
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      }
    }
    while (true)
    {
      return 1;
      m();
      continue;
      m();
      continue;
      m();
      continue;
      l();
      continue;
      c("onStart do login succeed ");
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.XlinkTcpService
 * JD-Core Version:    0.6.0
 */