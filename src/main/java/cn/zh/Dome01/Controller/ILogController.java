package cn.zh.Dome01.Controller;

import cn.zh.Dome01.Util.PageUtil;
import cn.zh.Dome01.annotation.Myselfannotation;
import cn.zh.Dome01.entity.Log;
import cn.zh.Dome01.entity.UserInfo;
import cn.zh.Dome01.service.IlogService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 浅笑 on 2018/4/17.
 */
@Controller
public class ILogController {
    //注入service
    @Resource(name="logService")
    private IlogService ilogService;

    public IlogService getIlogService() {
        return ilogService;
    }

    public void setIlogService(IlogService ilogService) {
        this.ilogService = ilogService;
    }

    /*转换日期*/
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(sdf,true));
    }

    //查询所有日志
    @RequestMapping("/findlog")
    public String  findlog(Model model) throws Exception {
        List<Log> loglist=new ArrayList<Log>();
           loglist=ilogService.alllog();
           model.addAttribute("loglists",loglist);
           return "loglist";
    }


    @RequestMapping("/getpages")
    public String getpage(){
        return "loglist";//返回的是页面
    }
    @RequestMapping("/getpagesJson")
    @ResponseBody
    @Myselfannotation(modulename = "系统管理",option = "日志列表分页")
    public Object getpages(@RequestParam(defaultValue = "1") int indexpage, @RequestParam(defaultValue = "2") int pagesize, String uname, Date startime, Date endtime) throws Exception {
        PageUtil<Log> pageUtil = ilogService.getpage(indexpage, pagesize, uname, startime, endtime);
        return pageUtil;
    }

}
