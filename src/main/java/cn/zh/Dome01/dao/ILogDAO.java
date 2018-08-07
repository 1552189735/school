package cn.zh.Dome01.dao;

import cn.zh.Dome01.entity.Log;
import cn.zh.Dome01.entity.Role;
import cn.zh.Dome01.entity.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by 浅笑 on 2018/4/9.
 */
public interface ILogDAO {
   //添加日志
    public int addlog(Log log) throws Exception;
    //查询日志
    public List<Log> alllog() throws Exception;
  //获取每页的记录
    public List<Log> getonepagecount(Map<String,Object> map) throws Exception;
    //获取总记录数
    public int getcount(Map<String,Object> map) throws Exception;

}
