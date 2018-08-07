package cn.zh.Dome01.Controller;


import cn.zh.Dome01.annotation.Myselfannotation;
import cn.zh.Dome01.entity.Role;
import cn.zh.Dome01.service.IRoleService;
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
public class IRoleController {
    public IRoleService getiRoleService() {
        return iRoleService;
    }

    public void setiRoleService(IRoleService iRoleService) {
        this.iRoleService = iRoleService;
    }

    //注入权限的service
    @Resource(name="roleService")
    private IRoleService iRoleService;
    //获取所有角色
    @RequestMapping("/findrole")
    @Myselfannotation(modulename = "系统管理",option = "角色列表")
    public String getroses(Model model){
        List<Role> roleList=new ArrayList<Role>();
        roleList=iRoleService.getrole();
        model.addAttribute("rolelists",roleList);
        return "rolelist";
    }
    //根据id删除角色
    @RequestMapping("/delonerole")
    @Myselfannotation(modulename = "系统管理",option = "删除角色")
    public String delonerole(int rid){
        iRoleService.delrole(rid);
        return "forward:/findrole";
    }
}
