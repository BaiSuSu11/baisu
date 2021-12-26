package com.cy.store.mapper;

import com.cy.store.entity.Address;

//收获地址持久层接口
public interface AddressMapper {



    /**插入用户收获地址数据
     *
     * @param address 收货地址数据
     * @return 受影响行数
     */
    Integer insert(Address address);

    /**据用户id统计收获地址条数
     *
     * @param uid 用户id
     * @return
     */
    Integer countByUid(Integer uid);
}
