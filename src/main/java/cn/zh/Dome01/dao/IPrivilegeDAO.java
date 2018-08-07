package cn.zh.Dome01.dao;

import cn.zh.Dome01.entity.Role;
import cn.zh.Dome01.entity.privilege;

import java.util.List;

/**
 * Created by 浅笑 on 2018/4/7.
 */
public interface IPrivilegeDAO {
    //根据id获取这个类型能支配的全部权限
    public List<privilege> getprivilege(Integer uid) throws Exception;
    //获取全部权限
    public List<privilege> getallprivilege();
    //根据id保存角色的权限
    public List<privilege> getoneprivilege(Integer rid) throws Exception;
    //根据id删除角色的权限
    public int delroleprivilege(Integer rid) throws Exception;
    //根据id添加角色权限
    public int addprivilege(Integer rid,Integer pid) throws Exception;
}
