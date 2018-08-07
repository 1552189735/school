package cn.zh.Dome01.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by 浅笑 on 2018/4/4.
 */
public class UserInfo {
    private Integer   uid  ;
    @NotNull(message = "用户名不能为空")
    @Size(min =2,max = 20,message = "用户名长度必须在{min}-{max}之间")
    private String    uname  ;
    private String    unickname  ;
    @NotNull(message = "密码不能为空")
    private String    upwd  ;
    @NotNull(message = "类型不能为空")
    private Integer   uroleid  ;
    @NotNull(message = "邮箱不能为空")
    @Pattern(regexp = "^\\w+@\\w+\\.\\w+$",message = "邮箱格式不正确")
    private String    uemail  ;
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[3|5|7|8|9]\\d{9}$",message = "手机号格式不正确")
    private String    uphone  ;
    @NotEmpty(message = "身份证不能为空")
    @Pattern(regexp = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$",message = "身份证格式不正确")
    private String    uidentityid  ;
    @NotNull(message = "出生日期不能为空")
    private Date ubirhtday  ;  //出生日期
    @NotNull(message = "性别不能为空")
    private String        	 ugender ; //性别

    private String ugradeid;

    public String getUgradeid() {
        return ugradeid;
    }

    public void setUgradeid(String ugradeid) {
        this.ugradeid = ugradeid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUnickname() {
        return unickname;
    }

    public void setUnickname(String unickname) {
        this.unickname = unickname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public Integer getUroleid() {
        return uroleid;
    }

    public void setUroleid(Integer uroleid) {
        this.uroleid = uroleid;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUidentityid() {
        return uidentityid;
    }

    public void setUidentityid(String uidentityid) {
        this.uidentityid = uidentityid;
    }

    public Date getUbirhtday() {
        return ubirhtday;
    }

    public void setUbirhtday(Date ubirhtday) {
        this.ubirhtday = ubirhtday;
    }

    public String getUgender() {
        return ugender;
    }

    public void setUgender(String ugender) {
        this.ugender = ugender;
    }
}
