package com.campushub.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: LoginRequired
 * Package: com.campushub.community.annotation
 * Description:检查登陆状态，注解方式的拦截器
 *
 * @Author 欣欣欣
 * @Create 2025/2/27 12:06
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
}
