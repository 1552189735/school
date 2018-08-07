package cn.zh.Dome01.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 浅笑 on 2018/4/16.
 */
public class Log {
    private Integer lid;
    private Integer uid;
    private String uname;
    private String operateinfo;
    private Date operatedatetime;

    public String  getStroperatedatetime() {
        if (operatedatetime!=null){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=sdf.format(operatedatetime);
            return time;
        }
        return null;
    }


    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
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

    public String getOperateinfo() {
        return operateinfo;
    }

    public void setOperateinfo(String operateinfo) {
        this.operateinfo = operateinfo;
    }

    public Date getOperatedatetime() {
        return operatedatetime;
    }

    public void setOperatedatetime(Date operatedatetime) {
        this.operatedatetime = operatedatetime;
    }
}
