package cn.zh.Dome01.service;

import cn.zh.Dome01.Util.PageUtil;
import cn.zh.Dome01.entity.Log;
import cn.zh.Dome01.entity.UserInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 浅笑 on 2018/4/16.
 */
public interface IlogService {
    //添加日志
    public int addlog(Log log) throws Exception;
    //查询日志
    public List<Log> alllog() throws Exception;
    //获取每页的数据集合
    public PageUtil<Log> getpage(int indexpage, int pagesize, String uname, Date startime, Date endtime) throws Exception;


}
