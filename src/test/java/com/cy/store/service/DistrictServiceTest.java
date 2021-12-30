package com.cy.store.service;


import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTest {
    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent(){
     //86表示中国，所有省的父代号都是86
     List<District> list = districtService.getByParent("86");
     for(District d : list)
     {
         System.err.println(d);
     }
    }

    @Test
    public void getNameByCode(){
        String code = "430000";
        String result = districtService.getNameByCode(code);
        System.out.println(result);
    }

//    @Test
//    public void getByParent2() {
//        try {
//            String parent = "86";
//            List<District> list = districtService.getByParent(parent);
//            System.out.println("count=" + list.size());
//            for (District item : list) {
//                System.out.println(item);
//            }
//        } catch (ServiceException e) {
//            System.out.println(e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
//    }

}
