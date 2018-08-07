package cn.zh.Dome01.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浅笑 on 2018/4/13.
 */
public class PageUtil<T> {
    //数据集合
      private List<T> lists=new ArrayList<T>();
    //总记录数
    private Integer totalRecords;
    //总页数
    private Integer totalpage;
    //当前页数
    private Integer indexpage;
    //每页记录数
    private Integer pagesize;

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }

    public Integer getIndexpage() {
        return indexpage;
    }

    public void setIndexpage(Integer indexpage) {
        this.indexpage = indexpage;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }
}
