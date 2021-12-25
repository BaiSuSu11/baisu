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





}
