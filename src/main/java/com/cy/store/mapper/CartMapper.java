package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CartMapper {

    Integer insert(Cart cart);

    Integer updateNumByCid(Integer cid,
                           Integer num,
                           String modifiedUser,
                           Date modifiedTime);
//根据商品id和用户id来查询购物车的数据
    Cart findByUidAndPid(@Param("uid") Integer uid, @Param("pid") Integer pid);

}


