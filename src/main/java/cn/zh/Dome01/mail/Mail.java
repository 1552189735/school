package cn.zh.Dome01.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
  private String mailServer,from,to,mailSubject,mailContent;
  private String username,password;
  public Mail(){
	  //设置邮件信息
	  //进行认证登录的用户名
	  username="1539224793@qq.com";
	  //认证密码
	  password="oqeojrlcewbyhifh";
	  //认证的邮箱对应的邮件服务器
	  mailServer="smtp.qq.com";
	  //发件人信息
	  from="1539224793@qq.com";
	  //收件人信息
	  to="1552189735@qq.com";
	  //邮件标题
	/*  mailSubject="今晚8点天桥下见 2017年5月9日09:58:11";*/
	  mailSubject="Springmvc可以了 2018年6月7日16:04:19";
	  //邮件内容
	  mailContent="Springmvc了，感谢，正文，2018年6月7日16:04:25";
  }
	//设置邮件服务器
  @SuppressWarnings("static-access")
public  void send(){
	  Properties prop=System.getProperties();
	  //指定邮件server
	  prop.put("mail.smtp.host", mailServer);
	  //是否开启认证
	  prop.put("mail.smtp.auth", "true");
	  //smtp协议的
	  prop.put("mail.smtp.port", "587");
	  //产生Session服务
	  EmailAuthenticator mailauth=new EmailAuthenticator(username, password);
	  Session mailSession=Session.getInstance(prop,(Authenticator)mailauth);
	   try {
		   //封装Message对象
		   Message message=new MimeMessage(mailSession);
		   message.setFrom(new InternetAddress(from)); //发件人
		   message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//收件人
		   message.setSubject(mailSubject);
		   //设置内容(设置字符集处理乱码问题)
		   message.setContent(mailContent,"text/html;charset=gbk");
		   message.setSentDate(new Date());
		   //创建Transport实例，发送邮件
		   Transport tran=mailSession.getTransport("smtp");
		   tran.send(message,message.getAllRecipients());
		   tran.close();
		   //http://lib.csdn.net/article/java/65778
		} catch (Exception e) {
			e.printStackTrace();
		}
  }
}