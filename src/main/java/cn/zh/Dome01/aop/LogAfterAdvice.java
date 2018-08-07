package cn.zh.Dome01.aop;


import cn.zh.Dome01.annotation.Myselfannotation;
import cn.zh.Dome01.entity.Log;
import cn.zh.Dome01.entity.UserInfo;
import cn.zh.Dome01.service.IlogService;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by 浅笑 on 2018/4/16.
 */
public class LogAfterAdvice implements AfterReturningAdvice,ApplicationContextAware{
    ApplicationContext ac;
    //log4j的日志
    private static final Logger logger=Logger.getLogger(LogAfterAdvice.class);
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        //注解，拿到类型的元数据
        Myselfannotation myselfannotation=method.getAnnotation(Myselfannotation.class);
      //构建Log实例
        Log log=new Log();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=simpleDateFormat.parse(simpleDateFormat.format(new Date()));

       // ApplicationContext ac=null;
        //通过实现一个接口注入进来ApplicationContext对象，进而获取到容器中悬浮的日志的service对象
        IlogService ilogService= (IlogService) ac.getBean("logService");
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        UserInfo info= (UserInfo) session.getAttribute("userinfo");
        if(info!=null){
            log.setUid(info.getUid());
            log.setUname(info.getUname());
            log.setOperatedatetime(date);
            if(myselfannotation!=null){
                log.setOperateinfo(myselfannotation.option());
                logger.info("编号"+info.getUid()+"\t用户名"+info.getUname()+"\t操作:"+myselfannotation.option()+"时间"+date);
                ilogService.addlog(log);
               // logger.info(myselfannotation.option());
            }


        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
          this.ac=applicationContext;
    }
}
