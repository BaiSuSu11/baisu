package com.cy.store.service;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTest {
    @Autowired
    private ICartService cartService;


    @Autowired
    private CartMapper cartMapper;

    @Test
    public void addToCart() {



            Integer uid = 2;
            Integer pid = 10000021;
            Integer amount = 1;
            String username = "Tom";


            cartService.addToCart(uid, pid, amount, username);
            System.out.println("OK.");

    }

    @Test
    public void getVOByUid() {
        List<CartVO> list = cartService.getVOByUid(31);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void addNum() {
        try {
            Integer cid = 6;
            Integer uid = 31;
            String username = "管理员";
            Integer num = cartService.addNum(cid, uid, username);
            System.out.println("OK. New num=" + num);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}