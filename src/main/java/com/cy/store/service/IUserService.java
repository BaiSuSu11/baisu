package com.cy.store.service;

import com.cy.store.entity.User;

//用户模块


public interface IUserService {
    /**
     *
     * @param user 用户的数据
     */
    void reg(User user);

    /**
     *
     * @param username ss
     * @param password
     * @return 当前匹配的用户数据，如果没有则返回null
     */
    User login(String username,String password);


    void changePassword(Integer uid,String username,String oldPassword,String newPassword);

    /**
     * 根据用户的uid查询用户的数据
     * @param uid 用户id
     * @return  用户的数据
     */
    User getByUid(Integer uid);

    /**
     *
     * @param uid 用户的id
     * @param username 用户的名称
     * @param user 用户对象的数据
     */
    void changeInfo(Integer uid,String username,User user);


    void changeAvatar(Integer uid, String username, String avatar);




}
