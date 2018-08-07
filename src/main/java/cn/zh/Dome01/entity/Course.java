package cn.zh.Dome01.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浅笑 on 2018/4/27.
 */
public class Course {
    private Integer tcid;
    private String  tcimage;
    private String tcname;
    private Integer tcparent;
    private Integer tccomment;
    private Integer tctype;
    private Double tprice;
    private String tintroduction;
    private String ttarget;
    private String tcrowd;
    //植入父节点属性
    private  Integer parent;

    //植入子元素集合
    private List<Course> children=new ArrayList<Course>();

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public List<Course> getChildren() {
        return children;
    }

    public void setChildren(List<Course> children) {
        this.children = children;
    }

    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    public String getTcimage() {
        return tcimage;
    }

    public void setTcimage(String tcimage) {
        this.tcimage = tcimage;
    }

    public String getTcname() {
        return tcname;
    }

    public void setTcname(String tcname) {
        this.tcname = tcname;
    }

    public Integer getTcparent() {
        return tcparent;
    }

    public void setTcparent(Integer tcparent) {
        this.tcparent = tcparent;
    }

    public Integer getTccomment() {
        return tccomment;
    }

    public void setTccomment(Integer tccomment) {
        this.tccomment = tccomment;
    }

    public Integer getTctype() {
        return tctype;
    }

    public void setTctype(Integer tctype) {
        this.tctype = tctype;
    }

    public Double getTprice() {
        return tprice;
    }

    public void setTprice(Double tprice) {
        this.tprice = tprice;
    }

    public String getTintroduction() {
        return tintroduction;
    }

    public void setTintroduction(String tintroduction) {
        this.tintroduction = tintroduction;
    }

    public String getTtarget() {
        return ttarget;
    }

    public void setTtarget(String ttarget) {
        this.ttarget = ttarget;
    }

    public String getTcrowd() {
        return tcrowd;
    }

    public void setTcrowd(String tcrowd) {
        this.tcrowd = tcrowd;
    }
}
