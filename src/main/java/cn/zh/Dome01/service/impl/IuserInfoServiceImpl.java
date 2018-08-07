package cn.zh.Dome01.service.impl;

import cn.zh.Dome01.Util.PageUtil;
import cn.zh.Dome01.dao.IUserInfoDAO;
import cn.zh.Dome01.entity.*;
import cn.zh.Dome01.service.IuserInfoService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;

/**
 * Created by 浅笑 on 2018/4/4.
 */
@Service("userInfoService")
public class IuserInfoServiceImpl implements IuserInfoService {
    public IUserInfoDAO getIuserInfoDao() {
        return iuserInfoDao;
    }

    public void setIuserInfoDao(IUserInfoDAO iuserInfoDao) {
        this.iuserInfoDao = iuserInfoDao;
    }

    @Resource(name = "IUserInfoDAO")
       private IUserInfoDAO iuserInfoDao;


        //登录
    public UserInfo login(UserInfo iuserInfo) throws Exception {
        return iuserInfoDao.login(iuserInfo);
    }
        //查询所有用户
    public List<UserInfo> userlist() {
        return iuserInfoDao.userlist();
    }
      //根据id查新单个用户
    public UserInfo oneuser(int uid) {
        return iuserInfoDao.oneuser(uid);
    }
    //修改用户
    public int updateuser(UserInfo userInfo) {

        return iuserInfoDao.updateuser(userInfo);
    }
        //添加用户
    public int add(UserInfo userInfo) {
        return iuserInfoDao.add(userInfo);
    }
     //删除用户
    public int deluser(int uid) {
        return iuserInfoDao.deluser(uid);
    }

    public int updatepwd(UserInfo userInfo) {
        return iuserInfoDao.updatepwd(userInfo);
    }

    //单页数据集合
    public PageUtil<UserInfo> getpage(int indexpage,int pagesize) throws Exception {
        PageUtil<UserInfo> page=new PageUtil<UserInfo>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("indexpage",(indexpage)*pagesize);
        map.put("pagesize",pagesize);
       //list集合
        List<UserInfo> list=iuserInfoDao.getonepagecount(map);
        page.setLists(list);
        page.setPagesize(pagesize);//单页记录数
        page.setIndexpage(indexpage);//当前页面
        //总记录数
        int count=iuserInfoDao.getcount(new UserInfo());
        page.setTotalRecords(count);
        //总页数
        int pagecount=page.getTotalRecords()%page.getPagesize()==0?page.getTotalRecords()/page.getPagesize():page.getTotalRecords()/page.getPagesize()+1;
        page.setTotalpage(pagecount);
        return page;
    }

    public PageUtil<UserInfo> getpage(int indexpage,int pagesize,UserInfo info) throws Exception {
        PageUtil<UserInfo> page=new PageUtil<UserInfo>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("indexpage",(indexpage)*pagesize);
        map.put("pagesize",pagesize);
        map.put("uname",info.getUname());
        //list集合
        List<UserInfo> list=iuserInfoDao.getonepagecount(map);
        page.setLists(list);
        page.setPagesize(pagesize);//单页记录数
        page.setIndexpage(indexpage);//当前页面
        //总记录数
        int count=iuserInfoDao.getcount(info);
        page.setTotalRecords(count);
        //总页数
        int pagecount=page.getTotalRecords()%page.getPagesize()==0?page.getTotalRecords()/page.getPagesize():page.getTotalRecords()/page.getPagesize()+1;
        page.setTotalpage(pagecount);
        return page;
    }


    //查询所有年级和班级
    public List<Classes> allclass() throws Exception {
        return iuserInfoDao.allclass();
    }
    //根据id查询在班级的学员
    public List<UserInfo> classuser(String ugradeid) throws Exception {
        return iuserInfoDao.classuser(ugradeid);
    }
    //添加班级考勤
    public void addattendance(Integer[] uidarr,Integer[] attendance) {
        Date date = new Date();
        for (int i=0;i<uidarr.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("uid",uidarr[i]);
            map.put("attendance",attendance[i]);
            map.put("times",date);
            iuserInfoDao.addattendance(map);
        }

    }
    //判断数据库今天的考勤有几条数据
    public int countattendance(String ugradeid) throws Exception {
        return iuserInfoDao.countattendance(ugradeid);
    }
    //根据姓名查询该学员的出勤情况
    public List<Attendance> oneattendance(String uname, Date startime,Date endtime) throws Exception {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("uname",uname);
        map.put("startime",startime);
        map.put("endtime",endtime);
        return iuserInfoDao.oneattendance(map);
    }
    //根据班级id查询班级学员的出勤情况
    public List<Attendance> classattendance(String ugradeid, Date startime,Date endtime) throws Exception {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("ugradeid",ugradeid);
        map.put("startime",startime);
        map.put("endtime",endtime);
        return iuserInfoDao.classattendance(map);
    }

    public int addinto(UserInfo userInfo) throws Exception {

        return 0;
    }

    public List<Course> selectAllTypeThreeSys_course() throws Exception {
        return iuserInfoDao.selectAllTypeThreeSys_course();
    }

    public Integer insertVideoItemForm(Video video) throws Exception {
        return iuserInfoDao.insertVideoItemForm(video);
    }

    public List<Achievement> alltitle(String ugradeid) throws Exception {
        return iuserInfoDao.alltitle(ugradeid);
    }

    public List<Achievement> allres(String ugradeid, String title) throws Exception {
        return iuserInfoDao.allres(ugradeid,title);
    }


}
