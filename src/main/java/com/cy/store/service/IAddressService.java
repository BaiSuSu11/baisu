package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

/**
 * 收货地址业务层接口
 */
public interface IAddressService {
    void addNewAddress(Integer uid,String username ,Address address);

    List<Address> getByUid(Integer uid);

    /**
     * 设置默认收货地址
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @param username 当前登录的用户名
     */
    void setDefault(Integer aid, Integer uid, String username);

}
