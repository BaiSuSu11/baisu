package com.cy.store.mapper;


import com.cy.store.entity.Address;
import com.cy.store.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTest {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart=new Cart();
        cart.setUid(22);
        cart.setPid(10000011);
        cart.setNum(2);
        cart.setPrice(1000L);
        cartMapper.insert(cart);
    }
    @Test
    public void updateNumByCid(){
        cartMapper.updateNumByCid(3,4,"张三",new Date());
    }
    @Test
    public void findByUidAndPid() { Integer uid = 22; Integer pid = 10000011;
    Cart result = cartMapper.findByUidAndPid(uid, pid);
    System.out.println(result);
    }

}


