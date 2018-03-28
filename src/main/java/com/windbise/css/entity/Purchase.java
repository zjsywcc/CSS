package com.windbise.css.entity;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
public class Purchase {

    private int id;
    private int buyerId;
    private long transactionId;
    private int goodId;
    private String goodTitle;
    private int goodNum;
    private int goodCost;
    private String goodPhoto;
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

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodTitle() {
        return goodTitle;
    }

    public void setGoodTitle(String goodTitle) {
        this.goodTitle = goodTitle;
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

    public String getGoodPhoto() {
        return goodPhoto;
    }

    public void setGoodPhoto(String goodPhoto) {
        this.goodPhoto = goodPhoto;
    }

    public long getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(long purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public static class PurchaseBuilder {

        private static Purchase purchase;

        public PurchaseBuilder() {
            purchase = new Purchase();
        }

        public PurchaseBuilder setBuyerId(int buyerId) {
            purchase.setBuyerId(buyerId);
            return this;
        }

        public PurchaseBuilder setGoodId(int goodId) {
            purchase.setGoodId(goodId);
            return this;
        }

        public PurchaseBuilder setGoodTitle(String goodTitle) {
            purchase.setGoodTitle(goodTitle);
            return this;
        }

        public PurchaseBuilder setGoodNum(int goodNum) {
            purchase.setGoodNum(goodNum);
            return this;
        }

        public PurchaseBuilder setPurchaseTime(long purchaseTime) {
            purchase.setPurchaseTime(purchaseTime);
            return this;
        }

        public PurchaseBuilder setGoodCost(int goodCost) {
            purchase.setGoodCost(goodCost);
            return this;
        }

        public PurchaseBuilder setGoodPhoto(String goodPhoto) {
            purchase.setGoodPhoto(goodPhoto);
            return this;
        }

        public PurchaseBuilder setTransactionId(long transactionId) {
            purchase.setTransactionId(transactionId);
            return this;
        }

        public Purchase build() {
            return purchase;
        }
    }
}
