package com.cy.store.controller;


import com.cy.store.service.ICartService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid,
                                      Integer amount,
                                      HttpSession session) {


        // 调用业务对象执行添加到购物车
        cartService.addToCart(getUidFromSession(session),
                              pid,
                              amount,
                              getUsernameFromSession(session));
        // 返回成功
        return new JsonResult<Void>(OK);
    }
}
