package com.cy.store.service;

public interface ICartService {

    //将商品添加到购物车中
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

}
