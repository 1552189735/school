package cn.zh.Dome01.Controller;

import cn.zh.Dome01.annotation.Myselfannotation;
import cn.zh.Dome01.entity.Role;
import cn.zh.Dome01.entity.privilege;
import cn.zh.Dome01.service.IprivilegeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浅笑 on 2018/4/9.
 */
@Controller
public class IPrivilegeController {

    //注入权限的service
    @Resource(name="privilegeService")
    private IprivilegeService iprivilegeService;

    public IprivilegeService getIprivilegeService() {
        return iprivilegeService;
    }

    public void setIprivilegeService(IprivilegeService iprivilegeService) {
        this.iprivilegeService = iprivilegeService;
    }


    //添加权限
    @RequestMapping("/addrolep")
    @ResponseBody
    @Myselfannotation(modulename = "系统管理",option = "添加权限")
    public Object add(Integer rid,Integer[] pid){
        try {
            iprivilegeService.addprivilege(rid,pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("权限添加成功");
        return 1;
    }

}
