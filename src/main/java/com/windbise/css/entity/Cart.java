package com.windbise.css.entity;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
public class Cart {

    private int id;
    private int buyerId;
    private int goodId;
    private String goodTitle;
    private int goodNum;
    private int goodCost;
    private long orderTime;
    private boolean deleted;

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

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public int getGoodCost() {
        return goodCost;
    }

    public void setGoodCost(int goodCost) {
        this.goodCost = goodCost;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    public static class CartBuilder {
        
        private static Cart cart;

        public CartBuilder() {
            cart = new Cart();
        }
        
        public CartBuilder setBuyerId(int buyerId) {
            cart.setBuyerId(buyerId);
            return this;
        }

        public CartBuilder setGoodId(int goodId) {
            cart.setGoodId(goodId);
            return this;
        }

        public CartBuilder setGoodTitle(String goodTitle) {
            cart.setGoodTitle(goodTitle);
            return this;
        }

        public CartBuilder setGoodNum(int goodNum) {
            cart.setGoodNum(goodNum);
            return this;
        }

        public CartBuilder setOrderTime(long orderTime) {
            cart.setOrderTime(orderTime);
            return this;
        }

        public CartBuilder setGoodCost(int goodCost) {
            cart.setGoodCost(goodCost);
            return this;
        }

        public CartBuilder setDeleted(boolean deleted) {
            cart.setDeleted(deleted);
            return this;
        }
        
        public Cart build() {
            return cart;
        }
    }
}
