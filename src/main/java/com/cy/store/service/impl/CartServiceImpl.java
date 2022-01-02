package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartServiceImpl implements ICartService {
    //购物车的业务层依赖于购物车的持久层
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid,
                          Integer amount, String username) {
        //查询当前要添加的这个购物是否在表中已存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if(result==null) { //从未加入购物车，新增
            //创建一个cart对象
            Cart cart = new Cart();
            //补全数据，参数传递的数据

            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 补全价格：来自于商品中的数据
            Product product=productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            //补全4日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);

            //执行数据的插入操作
            Integer rows=cartMapper.insert(cart);
            if (rows!=1){
                throw  new InsertException("插入数据时产生异常");
            }

        }else{//当前已存在，更新num值
            Integer num = result.getNum()+amount;
            Integer rows=cartMapper.updateNumByCid(result.getCid(), num, username, date);
            if(rows !=1){
                throw new UpdateException("更新数据时发生未知的异常");
            }
        }
    }
}
