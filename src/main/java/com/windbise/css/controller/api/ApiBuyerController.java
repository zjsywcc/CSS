package com.windbise.css.controller.api;

import com.alibaba.fastjson.JSON;
import com.windbise.css.entity.Cart;
import com.windbise.css.entity.Cart.CartBuilder;
import com.windbise.css.entity.Good;
import com.windbise.css.entity.Purchase;
import com.windbise.css.entity.User;
import com.windbise.css.service.CartService;
import com.windbise.css.service.GoodService;
import com.windbise.css.service.PurchaseService;
import com.windbise.css.service.UserService;
import com.windbise.css.util.ReturnData;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/20.
 */
@Controller
@RequestMapping("/api")
public class ApiBuyerController {

    Logger logger = LoggerFactory.getLogger(ApiBuyerController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private GoodService goodService;

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "/v1/buyer/purchases", method = RequestMethod.POST)
    @ResponseBody
    public String purchases(HttpSession session) {
        User currentUser = userService.getCurrentUser(session);
        if(currentUser == null) {
            return ReturnData.result(-2, "未登录!", null);
        }
        if(!currentUser.isType()) {
            return ReturnData.result(-3, "非法用户!", null);
        }
        int buyerId = currentUser.getId();
        List<Purchase> purchases = purchaseService.getPurchases(buyerId);
        return ReturnData.result(0, "获取买家购买记录成功!", JSON.toJSONString(purchases));
    }

    @RequestMapping(value = "/v1/buyer/listCart", method = RequestMethod.POST)
    @ResponseBody
    public String listCart(HttpSession session) {
        User currentUser = userService.getCurrentUser(session);
        if(currentUser == null) {
            return ReturnData.result(-2, "未登录!", null);
        }
        if(!currentUser.isType()) {
            return ReturnData.result(-3, "非法用户!", null);
        }
        int buyerId = currentUser.getId();
        List<Cart> carts = cartService.getCart(buyerId);
        return ReturnData.result(0, "获取购物车内容成功!", JSON.toJSONString(carts));
    }

    @RequestMapping(value = "/v1/buyer/addToCart", method = RequestMethod.POST)
    @ResponseBody
    public String addToCart(@Param("goodId") int goodId,
                            @Param("goodNum") int goodNum,
                            HttpSession session) {
        User currentUser = userService.getCurrentUser(session);
        if(currentUser == null) {
            return ReturnData.result(-2, "未登录!", null);
        }
        if(!currentUser.isType()) {
            return ReturnData.result(-3, "非法用户!", null);
        }
        int buyerId = currentUser.getId();
        Good good = goodService.getGoodById(goodId);
        Cart cart = new CartBuilder().setBuyerId(buyerId)
                .setGoodId(goodId)
                .setGoodTitle(good.getTitle())
                .setGoodCost(good.getCost())
                .setGoodNum(goodNum)
                .setOrderTime(System.currentTimeMillis() / 1000)
                .setDeleted(false)
                .build();
        cartService.addToCart(cart);
        return ReturnData.result(0, "添加到购物车成功!", null);
    }

    @RequestMapping(value = "/v1/buyer/buy", method = RequestMethod.POST)
    @ResponseBody
    public String buy(@Param("cartList") String cartList,
                      HttpSession session) {
        User currentUser = userService.getCurrentUser(session);
        if(currentUser == null) {
            return ReturnData.result(-2, "未登录!", null);
        }
        if(!currentUser.isType()) {
            return ReturnData.result(-3, "非法用户!", null);
        }
        List<Cart> carts = JSON.parseArray(cartList, Cart.class);
        int buyerId = currentUser.getId();
        cartService.updateCart(carts);
        purchaseService.buy(buyerId);
        goodService.soldGood(carts);
        cartService.emptyCart(buyerId);
        return ReturnData.result(0, "购买成功!", null);
    }

}
