package cn.zh.Dome01.entity;

import java.util.Date;

/**
 * Created by 浅笑 on 2018/4/28.
 */
public class Video {
       private Integer tvid;
       private String tvname;
       private String  timage;
       private String tvvideourl;
       private Integer tcid;
       private Integer tvparent;
       private Integer tcomment;
       private Double  tvprice;
       private Date tdate;
      private String turl;

    public String getTurl() {

        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }

    public Integer getTvid() {
        return tvid;
    }

    public void setTvid(Integer tvid) {
        this.tvid = tvid;
    }

    public String getTvname() {
        return tvname;
    }

    public void setTvname(String tvname) {
        this.tvname = tvname;
    }

    public String getTimage() {
        return timage;
    }

    public void setTimage(String timage) {
        this.timage = timage;
    }

    public String getTvvideourl() {
        return tvvideourl;
    }

    public void setTvvideourl(String tvvideourl) {
        this.tvvideourl = tvvideourl;
    }

    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    public Integer getTvparent() {
        return tvparent;
    }

    public void setTvparent(Integer tvparent) {
        this.tvparent = tvparent;
    }

    public Integer getTcomment() {
        return tcomment;
    }

    public void setTcomment(Integer tcomment) {
        this.tcomment = tcomment;
    }

    public Double getTvprice() {
        return tvprice;
    }

    public void setTvprice(Double tvprice) {
        this.tvprice = tvprice;
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }
}

