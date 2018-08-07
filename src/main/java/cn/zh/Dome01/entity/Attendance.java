package cn.zh.Dome01.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 浅笑 on 2018/4/20.
 */
public class Attendance {
    private  String ugradeid;//班级编号
    private String uname;  //用户名
    private Integer aid;
    private Integer uid;
    private Integer attendance;  //考勤情况  1：出勤，2：请假，3：迟到，4：缺勤
    private Date times;//保存的出勤情况的时间

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Date getTimes() {
        return times;
    }
    public String getUgradeid() {
        return ugradeid;
    }

    public void setUgradeid(String ugradeid) {
        this.ugradeid = ugradeid;
    }
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    //日期转换
    public void setTimes(Date times) {
        this.times = times;
    }
    public String  getStroperatedatetime() {
        if (times!=null){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");  //只显示年-月-日
            String time=sdf.format(times);
            return time;
        }
        return null;
    }
}
