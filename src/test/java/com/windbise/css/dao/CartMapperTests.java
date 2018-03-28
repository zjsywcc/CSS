package com.windbise.css.dao;

import com.windbise.css.entity.Cart;
import com.windbise.css.entity.Cart.CartBuilder;
import com.windbise.css.mapper.CartMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTests {

    @Autowired
    private CartMapper cartMapper;

    public int insertBatch(List<Cart> carts) {
        int rows = cartMapper.insertBatch(carts);
        return rows;
    }

    public int updateBatch(List<Cart> carts) {
        int rows = cartMapper.updateBatch(carts);
        return rows;
    }

    @Test
    @Rollback
    public void testCart() {
        Cart cart1 = new CartBuilder().setBuyerId(3)
                .setGoodId(4)
                .setGoodTitle("5")
                .setGoodNum(6)
                .setGoodCost(7)
                .setOrderTime(8)
                .setDeleted(false)
                .build();
        Cart cart2 = new CartBuilder().setBuyerId(8)
                .setGoodId(7)
                .setGoodTitle("6")
                .setGoodNum(5)
                .setGoodCost(4)
                .setOrderTime(3)
                .setDeleted(false)
                .build();
        List<Cart> carts = new ArrayList<Cart>(){{add(cart1);add(cart2);}};
        int rows = insertBatch(carts);
        Assert.assertEquals(2, rows);
    }

    @Test
    @Rollback
    public void testCartBatchUpdate() {
        List<Cart> carts = cartMapper.getCartAll(4);
        for(int i = 0; i < carts.size(); i++) {
            carts.get(i).setOrderTime(3000);
        }
        int rows = updateBatch(carts);
        //TODO 为什么批量更新影响行数并不是4而是1？
        Assert.assertEquals(4, rows);
    }
}
