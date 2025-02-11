package com.itheima.bigevent.interceptors;

import com.itheima.bigevent.pojo.Result;
import com.itheima.bigevent.utils.JwtUtil;
import com.itheima.bigevent.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
/*
拦截器类，由mvc自动调用
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /*
    preHandle()：
    这是拦截器的核心方法，在请求到达 Controller 之前执行。
    返回 true 表示请求继续执行（放行）；返回 false 表示请求被拦截，后续处理（Controller）不会被执行。
    */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token，令牌验证,Authorization这个是前端写好键值对，后端通过get获取
        String token = request.getHeader("Authorization");
        //验证token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);

            //把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);

            //放行
            return true;
        } catch (Exception e) {
            //http响应状态码为401
            response.setStatus(401);
            //不放行
            return false;
        }
    }

    /*
    清空ThreadLocal中的数据
    afterCompletion 表示请求处理完成后所执行的操作，最后阶段自动调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
