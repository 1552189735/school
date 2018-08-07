package cn.zh.Dome01.Util;


import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * Created by 浅笑 on 2018/4/6.
 */
public class Hashmd5 {
    //MD5密码编码器
     private final static PasswordEncoder md5=new Md5PasswordEncoder();
    public static String getDigestHash(String str) {
        return md5.encodePassword(str,null);
    }

}
