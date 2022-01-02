package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> findHotList();
    //根据商品的id查找商品
    Product findById(Integer id);
    //test pushing
}
