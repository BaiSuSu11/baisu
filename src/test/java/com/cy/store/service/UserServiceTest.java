package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
//表示当前的类是一个测试类，不会随同项目一块打包
@RunWith(SpringRunner.class)
//@RunWith: 表示启动这个单元测试类(不写的话，单元测试类是不能运行的）

public class UserServiceTest {
    //idea检测的功能，接口是不能创建bean的（动态代理来解决）
    @Autowired
    private IUserService userService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("jacob");
            user.setPassword("522315");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }



}