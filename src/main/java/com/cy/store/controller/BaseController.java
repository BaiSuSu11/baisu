package com.cy.store.controller;

import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

//表示控制层的基类
public class BaseController {
    //操作成功的状态码
    public static final int OK =200;
    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //自动异常对象传递给此方法的参数列表上
    //当前项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给前端

    @ExceptionHandler({ServiceException.class}) //用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if ( e instanceof UsernameDuplicatedException) {
            result.setState(5000);
            result.setMessage("用户名已经被占用的异常");
        } else if(e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("用户数据不存在的异常");
        } else if(e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("用户名的密码错误的异常");
        } else if(e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("用户收货地址超出上限异常");
        }else if(e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessage("商品未找到的异常");
        }
        else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMessage("购物车未找到的异常");
        }
        else if(e instanceof UpdateException) {
            result.setState(4001);
            result.setMessage("更新数据时产生未知的异常");
        }



        return  result;

    }

    /**
     * 获取session对象中的uid
     * @param session
     * @return 当前用户登录的uid
     */
    protected  final Integer getUidFromSession(HttpSession session) {

       return Integer.valueOf(session.getAttribute("uid").toString());

    }

    /**
     * 获取当前登录用户的username
     * @param session
     * @return
     */

    protected  final String getUsernameFromSession(HttpSession session) {

        return session.getAttribute("username").toString();

    }



}
