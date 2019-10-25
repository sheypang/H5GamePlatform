package cn.pxy.ysframework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
/**
 * 自定义注解实现权限控制
 * ElementType.TYPE，ElementType.METHOD表示注解可以标记类和方法
 */
public @interface RequiredRole {
    String value();
}