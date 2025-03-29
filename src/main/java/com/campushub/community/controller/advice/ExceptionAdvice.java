package com.campushub.community.controller.advice;

import com.campushub.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: ExceptionAdvice
 * Package: com.campushub.community.controller.advice
 * Description:全局统一异常处理类
 *
 * @Author 欣欣欣
 * @Create 2025/3/3 19:30
 * @Version 1.0
 */
@ControllerAdvice(annotations = Controller.class)
//注解@ControllerAdvice修饰ExceptionAdvice类，表⽰该类是Controller的全局配置类
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    //注解@ExceptionHandler修饰⽅法，表⽰该⽅法handleException会在Controller出现异常后被调⽤，⽤于处理捕获到的异常
    //有异常controller会传过来Exception
    @ExceptionHandler({Exception.class})
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException { //定义异常处理函数
        logger.error("服务器发生异常: " + e.getMessage());  //记录⽇志：将异常写⼊⽇志
        //把异常所有栈的信息都记录下来
        for (StackTraceElement element : e.getStackTrace()) {
            logger.error(element.toString());
        }

        //给浏览器响应：要看是什么请求，想要服务器返回⽹⻚html/异步请求JSON(xml），从请求的消息头中获取
        String xRequestedWith = request.getHeader("x-requested-with");
        if ("XMLHttpRequest".equals(xRequestedWith)) { //返回json数据---捕获到异常后，如果是异步请求
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();  //输出流
            writer.write(CommunityUtil.getJSONString(1, "服务器异常!"));   //则返回code:1,msg:服务器异常！（输出JSON字符串）
        } else { //返回网页
            response.sendRedirect(request.getContextPath() + "/error");  //否则，重定向到error/500⻚⾯
        }
    }
}
