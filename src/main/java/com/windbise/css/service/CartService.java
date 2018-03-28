package com.windbise.css.service;

import com.windbise.css.entity.Cart;

import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
public interface CartService {

    public int addToCart(Cart cart);

    public int addToCart(List<Cart> carts);

    public int updateCart(List<Cart> carts);

    public int emptyCart(int buyerId);

    public List<Cart> getCart(int buyerId);

    public List<Cart> getCartByPage(int buyerId, int index, int pageSize);

}
