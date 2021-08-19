package com.yh.interceptor;

import com.yh.utils.SystemConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断session是否为空
        if (request.getSession().getAttribute(SystemConstant.LOGINUSER) == null){
            //如果没有登录，就跳到登录页面
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return false; //验证失败 进行拦截
        }

        return true;

    }
}
