package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

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


    Integer updateInfoByUid(User user);

   //根据用户uid来修改用户的头像

    /**
     * @Param("sql映射文件中#{}占位符的变量名") :解决的问题是sql语句的占位符和映射的接口方法参数名不一致时，
     * 需要将某个参数强行注入到某个占位符变量时可以使用param这个注解来标注映射的关系
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
   Integer updateAvatarByUid(@Param("uid") Integer uid, String avatar, String modifiedUser, Date modifiedTime);



}
