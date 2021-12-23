package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest
//表示当前的类是一个测试类，不会随同项目一块打包
@RunWith(SpringRunner.class)
//@RunWith: 表示启动这个单元测试类(不写的话，单元测试类是不能运行的）

public class UserMapperTest {
    //idea检测的功能，接口是不能创建bean的（动态代理来解决）
    @Autowired
    private UserMapper userMapper;

    @Test
    public void  insert(){
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);

    }
    @Test
    public void findByUsername() {
        User tim = userMapper.findByUsername("tim");
        System.out.println(tim);

    }




}
