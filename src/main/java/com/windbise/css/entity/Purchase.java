package com.windbise.css.entity;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
public class Purchase {

    private int id;
    private int buyerId;
    private int transactionId;
    private int goodId;
    private int goodNum;
    private int goodCost;
    private long purchaseTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public int getGoodCost() {
        return goodCost;
    }

    public void setGoodCost(int goodCost) {
        this.goodCost = goodCost;
    }

    public long getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(long purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}
