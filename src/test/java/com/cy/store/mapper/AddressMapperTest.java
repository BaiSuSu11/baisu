package com.cy.store.mapper;


import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(1);
        address.setName("白苏苏");
        address.setPhone("1572248716");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer count = addressMapper.countByUid(1);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(2);
        System.out.println(list);
    }
}
