package cn.zh.Dome01.entity;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 浅笑 on 2018/6/9.
 */
public class Achievement {
    private Integer id;
    private Integer uid;
    private Integer tcid;
    private Integer achievement;
    private Date time;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    public Integer getAchievement() {
        return achievement;
    }

    public void setAchievement(Integer achievement) {
        this.achievement = achievement;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String  getStroperatedatetime() {
        if (time!=null){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");
            String times=sdf.format(time);
            return times;
        }
        return null;
    }
}
