package com.cy.store.mapper;

import com.cy.store.entity.User;

import java.util.Date;

/*
*用户模块持久层接口 */

public interface UserMapper {
    Integer insert(User user);

    //根据用户名查询数据
    User findByUsername(String username);

    //根据用户的uid来修改用户密码

    /**
     *
     * @param uid
     * @param password 用户输入的新密码
     * @param modifiedUser 修改的执行者
     * @param modifiedTime 修改的时间
     * @return
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);


    User findByUid(Integer uid);



}
