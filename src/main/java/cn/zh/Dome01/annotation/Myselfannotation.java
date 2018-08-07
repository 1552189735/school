package cn.zh.Dome01.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 浅笑 on 2018/4/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
//业务注解
public @interface Myselfannotation {
     //模块名字
    String modulename();
    //操作内容
    String option();
}
