package cn.zh.Dome01.dao;

import cn.zh.Dome01.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 浅笑 on 2018/4/4.
 */
public interface IUserInfoDAO {
    //登录的查询，根据用户名，密码登录
    public UserInfo login(UserInfo iuserInfo) throws Exception;
    //查询所有用户
    public List<UserInfo> userlist();
    //根据id查询单个用户
    public UserInfo oneuser(int uid);
    //修改用户信息
    public int updateuser(UserInfo userInfo);
    //添加用户
    public int add(UserInfo userInfo) ;
    //删除用户
    public int deluser(int uid);
    //修改密码
    public int updatepwd(UserInfo userInfo);
    //获取每页的记录
    public List<UserInfo> getonepagecount(Map<String,Object> map) throws Exception;
    //获取总记录数
    public int getcount(UserInfo info) throws Exception;
    //查询所有年级和班级
    public List<Classes> allclass() throws Exception;
    //根据班级id查询所在班级学员列表
    public List<UserInfo> classuser(String ugradeid) throws Exception;
    //添加班级出勤情况
    public int addattendance(Map<String,Object> map);
    //判断数据库今天的考勤是否有数据
    public int countattendance(String ugradeid) throws Exception;
    //根据姓名查询该学员的出勤情况
    public List<Attendance> oneattendance(Map<String,Object> map) throws Exception;
    //根据班级id查询班级学员的出勤情况
    public List<Attendance> classattendance(Map<String,Object> map)throws Exception;
    //导入的添加用户
    public int addinto(UserInfo userInfo) throws Exception;
    /*查询所有三级分类*/
    public List<Course> selectAllTypeThreeSys_course() throws Exception;
    /*新增视频到数据库*/
    public Integer insertVideoItemForm(Video video) throws Exception;
    /*根据id查询所有考试标题*/
    public List<Achievement> alltitle(String ugradeid) throws Exception;
    /*根据班级id和试卷的标题查询所有的班级学员姓名和成绩*/
    public List<Achievement> allres(String ugradeid,String title) throws Exception;
}
