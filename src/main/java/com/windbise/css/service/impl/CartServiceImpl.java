package com.windbise.css.service.impl;

import com.windbise.css.entity.Cart;
import com.windbise.css.mapper.CartMapper;
import com.windbise.css.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public int addToCart(Cart cart) {
        List<Cart> carts = getCart(cart.getBuyerId());
        boolean merged = false;
        for(int index = 0; index < carts.size(); index++) {
            Cart oldCart = carts.get(index);
            if(oldCart.getGoodId() == cart.getGoodId()) {
                oldCart.setGoodNum(oldCart.getGoodNum() + cart.getGoodNum());
                oldCart.setOrderTime(cart.getOrderTime());
            }
            merged = true;
        }
        if(!merged) {
            return cartMapper.addCart(cart);
        } else {
            return cartMapper.updateBatch(carts);
        }
    }

    @Override
    public int addToCart(List<Cart> carts) {
        return cartMapper.insertBatch(carts);
    }

    @Override
    public int updateCart(List<Cart> carts) {
        return cartMapper.updateBatch(carts);
    }

    @Override
    public int emptyCart(int buyerId) {
        return cartMapper.emptyCart(buyerId);
    }

    @Override
    public List<Cart> getCart(int buyerId) {
        return cartMapper.getCart(buyerId);
    }

    @Override
    public List<Cart> getCartByPage(int buyerId, int index, int pageSize) {
        return cartMapper.getCartsByPage(buyerId, index, pageSize);
    }

}
