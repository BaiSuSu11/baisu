package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

/**
 * 收货地址业务层接口
 */
public interface IAddressService {
    void addNewAddress(Integer uid,String username ,Address address);


    //根据用户的id来获取用户的地址信息
    List<Address> getByUid(Integer uid);

    /**
     * 设置用户默认收货地址
     * @param aid 收获地址id
     * @param uid 用户id
     * @param username 修改人
     */
    void setDefault(Integer aid , Integer uid , String username);

}
