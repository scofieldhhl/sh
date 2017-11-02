package com.ex.ltech.LogRegForget;

import java.util.Date;
import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

public class Mail extends Authenticator
{
  private boolean _auth = true;
  private String _body = "";
  private boolean _debuggable = false;
  private String _from = "";
  private String _host = "smtp.mxhichina.com";
  private Multipart _multipart = new MimeMultipart();
  private String _pass;
  private String _port = "465";
  private String _sport = "465";
  private String _subject = "";
  private String[] _to;
  private String _user = "";

  public Mail()
  {
    MailcapCommandMap localMailcapCommandMap = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
    localMailcapCommandMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
    localMailcapCommandMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
    localMailcapCommandMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
    localMailcapCommandMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
    localMailcapCommandMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
    CommandMap.setDefaultCommandMap(localMailcapCommandMap);
  }

  public Mail(String paramString1, String paramString2)
  {
    this();
    this._user = paramString1;
    this._pass = paramString2;
  }

  private Properties _setProperties()
  {
    Properties localProperties = new Properties();
    localProperties.put("mail.smtp.host", this._host);
    if (this._debuggable)
      localProperties.put("mail.debug", "true");
    if (this._auth)
      localProperties.put("mail.smtp.auth", "true");
    localProperties.put("mail.smtp.port", this._port);
    localProperties.put("mail.smtp.socketFactory.port", this._sport);
    localProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    localProperties.put("mail.smtp.socketFactory.fallback", "false");
    return localProperties;
  }

  public void addAttachment(String paramString)
    throws Exception
  {
    MimeBodyPart localMimeBodyPart = new MimeBodyPart();
    localMimeBodyPart.setDataHandler(new DataHandler(new FileDataSource(paramString)));
    localMimeBodyPart.setFileName(paramString);
    this._multipart.addBodyPart(localMimeBodyPart);
  }

  public String getBody()
  {
    return this._body;
  }

  public PasswordAuthentication getPasswordAuthentication()
  {
    return new PasswordAuthentication(this._user, this._pass);
  }

  public String get_body()
  {
    return this._body;
  }

  public String get_from()
  {
    return this._from;
  }

  public String get_host()
  {
    return this._host;
  }

  public Multipart get_multipart()
  {
    return this._multipart;
  }

  public String get_pass()
  {
    return this._pass;
  }

  public String get_port()
  {
    return this._port;
  }

  public String get_sport()
  {
    return this._sport;
  }

  public String get_subject()
  {
    return this._subject;
  }

  public String[] get_to()
  {
    return this._to;
  }

  public String get_user()
  {
    return this._user;
  }

  public boolean is_auth()
  {
    return this._auth;
  }

  public boolean is_debuggable()
  {
    return this._debuggable;
  }

  public boolean send()
    throws Exception
  {
    Properties localProperties = _setProperties();
    if ((!this._user.equals("")) && (!this._pass.equals("")) && (this._to.length > 0) && (!this._from.equals("")) && (!this._subject.equals("")) && (!this._body.equals("")))
    {
      MimeMessage localMimeMessage = new MimeMessage(Session.getInstance(localProperties, this));
      localMimeMessage.setFrom(new InternetAddress(this._from));
      InternetAddress[] arrayOfInternetAddress = new InternetAddress[this._to.length];
      for (int i = 0; i < this._to.length; i++)
        arrayOfInternetAddress[i] = new InternetAddress(this._to[i]);
      localMimeMessage.setRecipients(MimeMessage.RecipientType.TO, arrayOfInternetAddress);
      localMimeMessage.setSubject(this._subject);
      localMimeMessage.setSentDate(new Date());
      MimeBodyPart localMimeBodyPart = new MimeBodyPart();
      localMimeBodyPart.setText(this._body);
      this._multipart.addBodyPart(localMimeBodyPart);
      localMimeMessage.setContent(this._multipart);
      Transport.send(localMimeMessage);
      return true;
    }
    return false;
  }

  public void setBody(String paramString)
  {
    this._body = paramString;
  }

  public void set_auth(boolean paramBoolean)
  {
    this._auth = paramBoolean;
  }

  public void set_body(String paramString)
  {
    this._body = paramString;
  }

  public void set_debuggable(boolean paramBoolean)
  {
    this._debuggable = paramBoolean;
  }

  public void set_from(String paramString)
  {
    this._from = paramString;
  }

  public void set_host(String paramString)
  {
    this._host = paramString;
  }

  public void set_multipart(Multipart paramMultipart)
  {
    this._multipart = paramMultipart;
  }

  public void set_pass(String paramString)
  {
    this._pass = paramString;
  }

  public void set_port(String paramString)
  {
    this._port = paramString;
  }

  public void set_sport(String paramString)
  {
    this._sport = paramString;
  }

  public void set_subject(String paramString)
  {
    this._subject = paramString;
  }

  public void set_to(String[] paramArrayOfString)
  {
    this._to = paramArrayOfString;
  }

  public void set_user(String paramString)
  {
    this._user = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.Mail
 * JD-Core Version:    0.6.0
 */