package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        User result = userMapper.findByUsername(user.getUsername());
        if (result !=null) {
            throw  new UsernameDuplicatedException("用户名被占用");

        }

        //密码加密 md5
        String oldPassword = user.getUsername();
        //获取盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全数据：盐值的记录
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密
        String md5Password = getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);



        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer insert = userMapper.insert(user);
        if (insert != 1) {
            throw  new InsertException("在用户注册过程中产生了未知的异常");

        }




    }
    private String getMD5Password(String password,String salt) {
        //md5加密算法的调用(三次）

        for (int i=0;i<3;i++) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();

        }
        return  password;


    }

}
