package cn.zh.Dome01.service.impl;

import cn.zh.Dome01.dao.IRoleDAO;
import cn.zh.Dome01.entity.Role;
import cn.zh.Dome01.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 浅笑 on 2018/4/9.
 */
@Service("roleService")
public class IRoleServiceImpl implements IRoleService {
    public IRoleDAO getiRoleDAO() {
        return iRoleDAO;
    }

    public void setiRoleDAO(IRoleDAO iRoleDAO) {
        this.iRoleDAO = iRoleDAO;
    }

    @Resource(name="IRoleDAO")
    private IRoleDAO iRoleDAO;
    public List<Role> getrole() {
        return iRoleDAO.getrole();
    }
     //根据id删除角色
    public int delrole(Integer rid) {
        return iRoleDAO.delrole(rid);
    }
}
