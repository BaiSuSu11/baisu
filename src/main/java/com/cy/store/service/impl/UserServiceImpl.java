package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
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
        if (result != null) {
            throw new UsernameDuplicatedException("用户名被占用");

        }

        //获取盐值
        String salt = UUID.randomUUID().toString().toUpperCase();

        //将密码和盐值作为一个整体进行加密
        String md5Password = getMd5Password(user.getPassword(),salt);

        user.setPassword(md5Password);

        //补全数据：盐值的记录
        user.setSalt(salt);
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer insert = userMapper.insert(user);
        if (insert != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");

        }


    }

    @Override
    public User login(String username, String password) {
        // 调用userMapper的findByUsername()方法，根据参数username查询用户数据
        User result = userMapper.findByUsername(username);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在的错误");
        }

        // 判断查询结果中的isDelete是否为1
        if (result.getIsDelete() == 1) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在的错误");
        }


        // 从查询结果中获取盐值
        String salt = result.getSalt();
        // 调用getMd5Password()方法，将参数password和salt结合起来进行加密
        String md5Password = getMd5Password(password,salt);
        // 判断查询结果中的密码，与以上加密得到的密码是否不一致
        if (!result.getPassword().equals(md5Password)) {
            // 是：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("密码验证失败的错误");
        }

        // 创建新的User对象
        User user = new User();
        // 将查询结果中的uid、username、avatar封装到新的user对象中
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        // 返回新的user对象
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result ==null || result.getIsDelete() == 1) {
            throw  new  UserNotFoundException("用户数据不存在");
        }
        String oldMd5Password = getMd5Password(oldPassword, result.getSalt());

        if (!result.getPassword().equals(oldMd5Password)) {
            throw  new  PasswordNotMatchException("密码错误");

        }
        //将新的密码设置到数据库中，将新的密码进行加密再去更新
        String newMd5Password = getMd5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if ( rows != 1) {
            throw  new UpdateException("更新数据时产生未知的异常");

        }


    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if ( result == null || result.getIsDelete() == 1) {
            throw  new UserNotFoundException("用户数据不存在");
        }

        //要的数据封装到user
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }


    /**
     *
     * @param uid 用户的id
     * @param username 用户的名称
     * @param user 用户对象的数据
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if ( result == null || result.getIsDelete() == 1) {
            throw  new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer integer = userMapper.updateInfoByUid(user);

        if ( integer != 1) {
            throw new  UpdateException("更新数据产生未知的异常");
        }

    }

    private String getMd5Password(String password, String salt) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;

    }
}



