package com.windbise.css.entity;


/**
 * Created by wangchengcheng on 2018/3/16.
 */
public class Good {

    private int id;
    private int sellerId;
    private int buyerId;
    private String title;
    private String intro;
    private String content;
    private String photo;
    private int cost;
    private long createTime;
    private int soldNum;
    private boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }


    public int getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(int soldNum) {
        this.soldNum = soldNum;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public static class GoodBuilder {

        private static Good good;

        public GoodBuilder() {
            good = new Good();
        }

        public GoodBuilder setId(int id) {
            good.setId(id);
            return this;
        }

        public GoodBuilder setSellerId(int sellerId) {
            good.setSellerId(sellerId);
            return this;
        }

        public GoodBuilder setBuyerId(int buyerId) {
            good.setBuyerId(buyerId);
            return this;
        }

        public GoodBuilder setTitle(String title) {
            good.setTitle(title);
            return this;
        }

        public GoodBuilder setIntro(String intro) {
            good.setIntro(intro);
            return this;
        }

        public GoodBuilder setPhoto(String photo) {
            good.setPhoto(photo);
            return this;
        }

        public GoodBuilder setContent(String content) {
            good.setContent(content);
            return this;
        }

        public GoodBuilder setCost(int cost) {
            good.setCost(cost);
            return this;
        }

        public GoodBuilder setCreateTime(long createTime) {
            good.setCreateTime(createTime);
            return this;
        }

        public GoodBuilder setSoldNum(int soldNum) {
            good.setSoldNum(soldNum);
            return this;
        }

        public GoodBuilder setDeleted(boolean deleted) {
            good.setDeleted(deleted);
            return this;
        }

        public Good build() {
            return good;
        }

    }
}
