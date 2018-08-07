package cn.zh.Dome01.dao;

import cn.zh.Dome01.entity.Role;

import java.util.List;

/**
 * Created by 浅笑 on 2018/4/9.
 */
public interface IRoleDAO {
    //获取全部的角色
    public List<Role> getrole();
    //删除角色
    public int delrole(Integer rid);
}
