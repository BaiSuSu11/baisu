package com.cy.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
* 定义一个拦截器*/
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象是否有uid数据，如果有则放行，如果没有重定向到登录页面
     * @param request  请求对象
     * @param response  响应对象
     * @param handler   处理器
     * @return 如果返回值为true表示放行当前的请求，如果是false则表示拦截当前的请求
     * @throws Exception
     */
    @Override
    //在调用所有处理请求的方法之前被自动调用执行的方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //request来获取全局的session对象
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null) {
            //用户没有登录过系统，则重定向到login.html
            response.sendRedirect("/web/login.html");
             //结束后续的调用
            return false;

        }
        //请求放行
        return  true;




    }

    @Override
    //在ModelAndView对象返回之后被调用的方法
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    //在整个请求所有关联的资源被执行完毕最后执行的方法
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
