package cn.zh.Dome01.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * �ʼ���֤��Ϣ
 * @author Happy
 *
 */
public class EmailAuthenticator extends Authenticator {
	private String username;   
	  
    private String userpass;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public EmailAuthenticator() {
	}

	public EmailAuthenticator(String username, String userpass) {
		this.username = username;
		this.userpass = userpass;
	}
	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(username,userpass);
	}
    
}
