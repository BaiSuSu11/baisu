package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.management.snmp.jvminstr.JvmOSImpl;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
       /* JsonResult<Void> result = new JsonResult<>();

        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");

        } catch (InsertException e) {
            result.setState(5000);
            result.setMessage("注册产生未知的异常");
        }
        return  result;*/
        userService.reg(user);
        return  new JsonResult<>(OK);




    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession httpSession) {
        User data= userService.login(username, password);
        //向session对象中完成数据的绑定（session全局的）
        httpSession.setAttribute("uid",data.getUid());
        httpSession.setAttribute("username",data.getUsername());

        //获取session中绑定的数据
        System.out.println(getUidFromSession(httpSession));
        System.out.println(getUsernameFromSession(httpSession));

        return  new JsonResult<User>(OK,data);

    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session) {
        Integer uidFromSession = getUidFromSession(session);
        String usernameFromSession = getUsernameFromSession(session);
        userService.changePassword(uidFromSession,usernameFromSession,oldPassword,newPassword);
        return new  JsonResult<Void>(OK);

    }

    @RequestMapping("get_by_uid")
    public  JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getUidFromSession(session));
        return  new JsonResult<User>(OK,data);


    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return  new  JsonResult<>(OK);


    }




}
