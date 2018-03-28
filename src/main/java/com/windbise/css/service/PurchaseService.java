package com.windbise.css.service;

import com.windbise.css.entity.Purchase;

import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
public interface PurchaseService {

    public List<Purchase> getPurchases(int buyerId);

    public List<Purchase> getPurchasesByPage(int buyerId, int index, int pageSize);

    public int buy(int buyerId);

}
