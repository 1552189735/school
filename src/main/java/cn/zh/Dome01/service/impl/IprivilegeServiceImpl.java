package cn.zh.Dome01.service.impl;

import cn.zh.Dome01.dao.IPrivilegeDAO;
import cn.zh.Dome01.entity.Role;
import cn.zh.Dome01.entity.privilege;
import cn.zh.Dome01.service.IprivilegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 浅笑 on 2018/4/7.
 */
@Service("privilegeService")
public class IprivilegeServiceImpl implements IprivilegeService{
    @Resource(name="IPrivilegeDAO")
    private IPrivilegeDAO iprivilegeDAO;

    public IPrivilegeDAO getIprivilegeDAO() {
        return iprivilegeDAO;
    }

    public void setIprivilegeDAO(IPrivilegeDAO iprivilegeDAO) {
        this.iprivilegeDAO = iprivilegeDAO;
    }
     //根据id获取用户的权限
    public List<privilege> getprivilege(Integer uid) throws Exception {

        return iprivilegeDAO.getprivilege(uid);
    }

     //获取全部权限
    public List<privilege> getallprivilege() {
        return iprivilegeDAO.getallprivilege();
    }


    //获取角色的全部权限
    public List<privilege> getoneprivilege(Integer rid) throws Exception {
        return iprivilegeDAO.getoneprivilege(rid);
    }
     //删除原有权限，并保存现有权限
    @Transactional
    public int addprivilege(Integer rid, Integer[] pid) throws Exception {
        //删除
        iprivilegeDAO.delroleprivilege(rid);
        //添加
        for (int i=0;i<pid.length;i++) {
            iprivilegeDAO.addprivilege(rid, pid[i]);
        }
        return 1;

    }

}
