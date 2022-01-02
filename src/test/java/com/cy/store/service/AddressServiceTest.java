package com.cy.store.service;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {
    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setName("白苏");
        address.setPhone("1572248799");
        addressService.addNewAddress(1,"xu",address);
    }

    @Test
    public void getByUid() {
        Integer uid = 26;
        List<Address> list = addressService.getByUid(uid);
        System.out.println("count=" + list.size());
        for (Address item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void setDefault() {
            Integer aid = 13;
            Integer uid = 27;
            String username = "系统管理员";
            addressService.setDefault(aid, uid, username);
            System.out.println("OK.");

    }
}
