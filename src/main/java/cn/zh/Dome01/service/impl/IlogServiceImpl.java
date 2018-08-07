package cn.zh.Dome01.service.impl;

import cn.zh.Dome01.Util.PageUtil;
import cn.zh.Dome01.dao.ILogDAO;
import cn.zh.Dome01.entity.Log;
import cn.zh.Dome01.entity.UserInfo;
import cn.zh.Dome01.service.IlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 浅笑 on 2018/4/16.
 */
@Service("logService")
public class IlogServiceImpl implements IlogService{

    public ILogDAO getIlogDAO() {
        return ilogDAO;
    }

    public void setIlogDAO(ILogDAO ilogDAO) {
        this.ilogDAO = ilogDAO;
    }
    @Resource(name="ILogDAO")
    private ILogDAO ilogDAO;
    public int addlog(Log log) throws Exception {
        return ilogDAO.addlog(log);
    }

    public List<Log> alllog() throws Exception {
        return ilogDAO.alllog();
    }


    public PageUtil<Log> getpage(int indexpage, int pagesize, String uname, Date startime, Date endtime) throws Exception {
        PageUtil<Log> page=new PageUtil<Log>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("indexpage",(indexpage)*pagesize);
        map.put("pagesize",pagesize);
        map.put("startime",startime);
        map.put("endtime",endtime);
        map.put("uname",uname);
        //list集合
        List<Log> list=ilogDAO.getonepagecount(map);
        page.setLists(list);
        page.setPagesize(pagesize);//单页记录数
        page.setIndexpage(indexpage);//当前页面
        //总记录数
        int count=ilogDAO.getcount(map);
        page.setTotalRecords(count);
        //总页数
        int pagecount=page.getTotalRecords()%page.getPagesize()==0?page.getTotalRecords()/page.getPagesize():page.getTotalRecords()/page.getPagesize()+1;
        page.setTotalpage(pagecount);
        return page;
    }
}
