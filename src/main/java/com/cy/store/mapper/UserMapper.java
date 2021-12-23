package com.cy.store.mapper;

import com.cy.store.entity.User;

/*
*用户模块持久层接口 */

public interface UserMapper {
    Integer insert(User user);

    //根据用户名查询数据
    User findByUsername(String username);



}
