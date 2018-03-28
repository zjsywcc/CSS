package com.windbise.css.service.impl;

import com.windbise.css.entity.Cart;
import com.windbise.css.entity.Good;
import com.windbise.css.entity.Purchase;
import com.windbise.css.entity.Purchase.PurchaseBuilder;
import com.windbise.css.mapper.CartMapper;
import com.windbise.css.mapper.GoodMapper;
import com.windbise.css.mapper.PurchaseMapper;
import com.windbise.css.service.PurchaseService;
import com.windbise.css.util.TransactionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GoodMapper goodMapper;

    @Override
    public List<Purchase> getPurchases(int buyerId) {
        return purchaseMapper.getPurchases(buyerId);
    }

    @Override
    public List<Purchase> getPurchasesByPage(int buyerId, int index, int pageSize) {
        return purchaseMapper.getPurchasesByPage(buyerId, index, pageSize);
    }

    @Override
    public int buy(int buyerId) {
        List<Cart> carts = cartMapper.getCart(buyerId);
        List<Purchase> purchases = new ArrayList<>();
        for(Cart cart : carts) {
            int goodId = cart.getGoodId();
            Good good = goodMapper.getGoodById(goodId);
            Purchase purchase = new PurchaseBuilder().setBuyerId(buyerId)
                    .setGoodId(goodId)
                    .setGoodTitle(good.getTitle())
                    .setGoodNum(cart.getGoodNum())
                    .setGoodCost(good.getCost())
                    .setGoodPhoto(good.getPhoto())
                    .setPurchaseTime(System.currentTimeMillis() / 1000)
                    .setTransactionId(TransactionIdUtil.getTransactionId(goodId, buyerId))
                    .build();
            purchases.add(purchase);
        }
        int rows = purchaseMapper.insertBatch(purchases);
        return rows;
    }
}
