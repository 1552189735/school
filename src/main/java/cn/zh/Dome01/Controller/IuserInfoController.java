package cn.zh.Dome01.Controller;


import cn.zh.Dome01.Util.Hashmd5;
import cn.zh.Dome01.Util.POIExcelUtil;
import cn.zh.Dome01.Util.PageUtil;
import cn.zh.Dome01.annotation.Myselfannotation;
import cn.zh.Dome01.entity.*;
import cn.zh.Dome01.service.IprivilegeService;
import cn.zh.Dome01.service.IuserInfoService;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 浅笑 on 2018/4/4.
 */
@Controller
public class IuserInfoController {
    //注入用户service
    @Resource(name = "userInfoService" )
    private IuserInfoService iuserInfoService;

    public IuserInfoService getIuserInfoService() {
        return iuserInfoService;
    }

    public void setIuserInfoService(IuserInfoService iuserInfoService) {
        this.iuserInfoService = iuserInfoService;
    }


    //注入权限的service
    @Resource(name="privilegeService")
    private IprivilegeService iprivilegeService;

    public IprivilegeService getIprivilegeService() {
        return iprivilegeService;
    }

    public void setIprivilegeService(IprivilegeService iprivilegeService) {
        this.iprivilegeService = iprivilegeService;
    }



    //规定日期转换的格式
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(sdf,true));
    }



    // 登录
    @RequestMapping("/userlogin")
    @Myselfannotation(modulename = "系统管理",option = "登录")
    public String login(UserInfo iuserInfo,HttpSession session) throws Exception{
        //MD5加密
         iuserInfo.setUpwd(Hashmd5.getDigestHash(iuserInfo.getUpwd()));
          UserInfo user= iuserInfoService.login(iuserInfo);
            if(user!=null&&user.getUname()!=null){
            session.setAttribute("userinfo",user);
            List<privilege> privileges=getAllPrivileges(user.getUid());
           // System.out.println("000000000000000000000000000000000"+privileges.size());
            session.setAttribute("allprivileges",privileges);
            return "main";
        }else{
            //System.out.println("密码错误");
            JOptionPane.showMessageDialog(null, "登录失败，用户名或密码错误！");
            return "login";
        }
    }

  //注销用户
    @RequestMapping("/xiao")
    public String deluserone(HttpSession session){
         session.removeAttribute("userinfo");
         return "login";
    }





    //查询所有用户信息
    @RequestMapping("/findall")
    @Myselfannotation(modulename = "系统管理",option = "用户列表")
    public  String finduserall(Model model){
        List<UserInfo> list=new ArrayList<UserInfo>();
       list=iuserInfoService.userlist();
        model.addAttribute("userlist",list);
       return "right";
    }



    //根据id查询单个用户信息
    @RequestMapping("/findone")

    public String findone(Model model,int uid) throws Exception{
        UserInfo onelist=iuserInfoService.oneuser(uid);
          model.addAttribute("onelist",onelist);
        return "form";
    }



    //修改，添加用户信息
    @RequestMapping("/updateone")
    @Myselfannotation(modulename = "系统管理",option = "添加或修改用户")
    public String updateuser(UserInfo userInfo){
       // System.out.println(userInfo);
        if(userInfo == null||userInfo.getUid()==null||userInfo.getUid()<1){
            //添加
            userInfo.setUpwd(Hashmd5.getDigestHash(userInfo.getUpwd()));  //MD5加密
            iuserInfoService.add(userInfo);
            return "forward:/findall";
        }else{
            //修改
            iuserInfoService.updateuser(userInfo);
            return "forward:/findall";
        }
    }

//修改密码
    @RequestMapping("/updatepwd")
    public String newpwd(UserInfo userInfo){
        iuserInfoService.updatepwd(userInfo);
        return "forward:/findall";
    }

   //根据id删除单个用户信息
   @RequestMapping("/delone")
   @Myselfannotation(modulename = "系统管理",option = "删除用户")
    public String deluser(int uid){
        //JOptionPane.showInternalConfirmDialog(frame,"确认删除吗","删除");
     //  JOptionPane.showMessageDialog(null, "导出成功!");
       int res=JOptionPane.showConfirmDialog(null, "确认删除吗？", "删除", JOptionPane.YES_NO_OPTION);
                   if(res==JOptionPane.YES_OPTION){
                         // System.out.println("选择是后执行的代码");    //点击“是”后执行这个代码块
                       iuserInfoService.deluser(uid);
                       return "forward:/findall";
                        }else{
                           // System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块

                            return "forward:/findall";


                        }


    }


     //数据校验
    @RequestMapping("/res")
    public String validator(UserInfo userInfo, BindingResult br,Model model){
         if (br.getErrorCount()>0){
             FieldError uname=br.getFieldError("uname");
             FieldError upwd=br.getFieldError("upwd");
           //  FieldError uroleid=br.getFieldError("uroleid");
             FieldError uemail=br.getFieldError("uemail");
             FieldError uphone=br.getFieldError("uphone");
             FieldError uidentityid=br.getFieldError("uidentityid");
             FieldError ubirhtday=br.getFieldError("ubirhtday");
             FieldError ugender=br.getFieldError("ugender");
             if (uname!=null){
                 String unamemsg=uname.getDefaultMessage();
                 model.addAttribute("unamemsg",unamemsg);
             }
             if (upwd!=null){
                 String upwdmsg=upwd.getDefaultMessage();
                 model.addAttribute("upwdmsg",upwdmsg);
             }
           /*  if (uroleid!=null){
                 String uroleidmsg=uroleid.getDefaultMessage();
                 model.addAttribute("uroleidmsg",uroleidmsg);
             }*/
             if (uemail!=null){
                 String uemailmsg=uemail.getDefaultMessage();
                 model.addAttribute("uemailmsg",uemailmsg);
             }
             if (uphone!=null){
                 String uphonemsg=uphone.getDefaultMessage();
                 model.addAttribute("uphonemsg",uphonemsg);
             }
             if (uidentityid!=null){
                 String uidentityidmsg=uidentityid.getDefaultMessage();
                 model.addAttribute("uidentityidmsg",uidentityidmsg);
             }
             if (ubirhtday!=null){
                 String ubirhtdaymsg=ubirhtday.getDefaultMessage();
                 model.addAttribute("ubirhtdaymsg",ubirhtdaymsg);
             }
             if (ugender!=null){
                 String ugendermsg=ugender.getDefaultMessage();
                 model.addAttribute("ugendermsg",ugendermsg);
             }
             return "form";
         }
         return "updateone";
    }



    //权限
    //获取指定用户的所有权限集合 并且将权限集合  变成  有层级关系的集合
    @RequestMapping("/doMain")
    public List<privilege> getAllPrivileges(int uid) throws Exception {
        //新的容器 保存有父子关系的权限
        List<privilege> rootMenus=new ArrayList<privilege>();
        try {
            //get  Data from db  , this list is not layer list这个集合是平级的，没有任何的对象植入
            List<privilege> privilegeList = iprivilegeService.getprivilege(uid);
            for (privilege item:privilegeList){//原始平级容器
                privilege childMenu=item;//接收每一项
                int pid = childMenu.getParent(); //当前项目的pid  父分类编号  1
                if (pid==0){  //如果是0，证明是顶级分类
                    rootMenus.add(item); //作为单列集合的直接对象
                }else{
                    for (privilege innerMenu:privilegeList){  //扫描整个内存中的数据
                        Integer id = innerMenu.getId(); //1
                        if (id==pid){
                            privilege parentMenu=innerMenu;
                            parentMenu.getChildren().add(childMenu);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println("==========================="+rootMenus.size());
        return rootMenus;
    }


    //获取全部权限并转换成JSON格式
    @RequestMapping("/getjson")
    @ResponseBody
    @Myselfannotation(modulename = "系统管理",option = "获取角色权限")
    public Object getJSON(String rid) throws  Exception{
        //新的容器 保存有父子关系的权限
        List<privilege> rootMenus=new ArrayList<privilege>();
        try {
            List<privilege> privilegeList =iprivilegeService.getallprivilege();
              List<privilege> rolejson=getroleprivilege(Integer.parseInt(rid));
            for (privilege item:privilegeList){//原始平级容器
                privilege childMenu=item;//接收每一项
                for (privilege chooses:rolejson){
                    if (item.getId().equals(chooses.getId())){
                        item.setChecked(true);
                    }
                }
                int pid = childMenu.getParent(); //当前项目的pid  父分类编号  1
                if (pid==0){  //如果是0，证明是顶级分类
                    rootMenus.add(item); //作为单列集合的直接对象
                }else{
                    for (privilege innerMenu:privilegeList){  //扫描整个内存中的数据
                        Integer id = innerMenu.getId(); //1
                        if (id==pid){
                            privilege parentMenu=innerMenu;
                            parentMenu.getChildren().add(childMenu);
                            break;
                        }
                       // System.out.println("--------------------------------------"+innerMenu);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

           return rootMenus;
    }


    //获取角色的全部权限
    public  List<privilege> getroleprivilege(int rid) throws Exception {
         List<privilege> roleprivileges=iprivilegeService.getoneprivilege(rid);
         return  roleprivileges;
    }

    /*//保存重新分配的权限
    @RequestMapping("saveprivilege")
    @ResponseBody
    public Object savaroleprivilege(int rid){

        //把新分配的权限保存下来
     return "";
    }*/
      @RequestMapping("/getpage")
    public String getpage(){
        return "right";//返回的是页面
      }
      @RequestMapping("/getpageJson")
      @ResponseBody
      @Myselfannotation(modulename = "系统管理",option = "用户列表分页")
    public Object getpages(@RequestParam(defaultValue = "1") int indexpage, @RequestParam(defaultValue = "2") int pagesize,UserInfo info) throws Exception {
          PageUtil<UserInfo> pageUtil;
        if (info!=null&&info.getUname()!=null&&!info.getUname().equals("")) {
             pageUtil = iuserInfoService.getpage(indexpage, pagesize,info);
           // System.out.println("------------------------------------");
        }else {
            pageUtil = iuserInfoService.getpage(indexpage, pagesize);
           // System.out.println("+++++++++++++++++++++++++");
        }
          return pageUtil;
      }

 /*   @RequestMapping("/getpageJson")
    @ResponseBody
    public Object getpages(@RequestParam(defaultValue = "1") int indexpage, @RequestParam(defaultValue = "2") int pagesize,UserInfo info) throws Exception {
        PageUtil<UserInfo> pageUtil=iuserInfoService.getpage(indexpage,pagesize);
        return pageUtil;
    }*/

 //导出excel
    @RequestMapping("/partExport")
    @ResponseBody
    @Myselfannotation(modulename = "系统管理",option = "导出用户列表")
        public String downUserInfoList(HttpServletResponse response, HttpSession session){
            List<UserInfo> allUserList=new ArrayList<UserInfo>();
            try {
                allUserList = iuserInfoService.userlist();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(allUserList);
            try {

                UserInfo loginuser = (UserInfo) session.getAttribute("userinfo");
                String path = session.getServletContext().getRealPath("/background/down");
                System.out.println(path);

                System.out.println(loginuser);
                POIExcelUtil.downGo(allUserList, loginuser.getUname(), path, response);

            }catch (Exception ex){
                ex.printStackTrace();
            }
            return "forward:getpage";
        }

    //查询所有年级和班级
    public List<Classes> all() throws Exception {
        List<Classes> classlist=new ArrayList<Classes>();
        classlist=iuserInfoService.allclass();
        return classlist;
    }


    //学生考勤页面的班级下拉框
    @RequestMapping("/findclass")
    @Myselfannotation(modulename = "系统管理",option = "学生出勤")
    public String findclass(Model model) throws Exception {
            List<Classes> all=all();

            model.addAttribute("classlists",all);
            return "Attendance";
    }

    //班级成绩页面的班级下拉框
     @RequestMapping("/allclasses")
     public String allclasses(Model model) throws Exception {
         List<Classes> all=all();
         model.addAttribute("classes",all);
         return "result";
     }

    //根据id查询在班级的学员
    @RequestMapping("/alluser")
   @ResponseBody
    public Object alluser(Model model,String ugradeid) throws Exception {
            List<UserInfo> clist=new ArrayList<UserInfo>();
            clist=iuserInfoService.classuser(ugradeid);
            //model.addAttribute("clist",clist);
            return clist;
     }

     //添加班级出勤情况
    @RequestMapping("/addattendance")
    @ResponseBody
    public Object addattendance(Integer[] uidarr,Integer[] attendance){
            //获取保存出勤情况时间
                List<Attendance> list=new ArrayList<Attendance>();
                iuserInfoService.addattendance(uidarr,attendance);
                return 1;
    }
    //判断今天的出勤情况是否已保存
    @RequestMapping("/countattendance")
    @ResponseBody
    public Object countattendance(String ugradeid) throws Exception {
         int count= iuserInfoService.countattendance(ugradeid);
          return count;
    }



    //根据学员姓名获取学员的出勤情况
    @RequestMapping("/oneattendances")
    public Object getoneatt(String uname, Date startime,Date endtime) throws Exception {
        List<Attendance> list=new ArrayList<Attendance>();
        list=iuserInfoService.oneattendance(uname,startime,endtime);
        return list;

    }

    @RequestMapping("/findsclass")
    public String findsclass(Model model) throws Exception {
        List<Classes> classlist=iuserInfoService.allclass();
        model.addAttribute("classlists",classlist);
        return "classattendance";
    }

    //班级出勤查询
    @RequestMapping("/classattendances")
    @ResponseBody
    public Object getclassatt(String ugradeid, Date startime,Date endtime) throws Exception {
        List<Attendance> list=new ArrayList<Attendance>();
        list=iuserInfoService.classattendance(ugradeid,startime,endtime);
        return list;
    }

    //根据id查询班级考试标题
    @RequestMapping("/titlebyid")
    public String titles(Model model,String ugradeid) throws Exception {
          List<Achievement> list=iuserInfoService.alltitle(ugradeid);
          model.addAttribute("alltitles",list);
          return "result";
    }

    /*根据班级id和试卷标题查询班级学员姓名和成绩*/
    @RequestMapping("/allres")
    public String allreses(String ugradeid,String title,Model model) throws Exception {
        List<Achievement> list=iuserInfoService.allres(ugradeid,title);
        model.addAttribute("allreses",list);
        return "achievement";
    }
    }
