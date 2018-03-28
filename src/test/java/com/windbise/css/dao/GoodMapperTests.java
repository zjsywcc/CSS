package com.windbise.css.dao;

import com.windbise.css.entity.Good;
import com.windbise.css.entity.Good.GoodBuilder;
import com.windbise.css.mapper.GoodMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * Created by wangchengcheng on 2018/3/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodMapperTests {

    @Autowired
    private GoodMapper goodMapper;


    public int addGood(int sellerId, int buyerId, String title, String intro, String content, String photo,
                        int cost, long createTime, int soldNum, boolean deleted) throws Exception {
        Good good = new GoodBuilder().setSellerId(sellerId)
                .setBuyerId(buyerId)
                .setTitle(title)
                .setIntro(intro)
                .setContent(content)
                .setCost(cost)
                .setCreateTime(createTime)
                .setDeleted(deleted)
                .setPhoto(photo)
                .setSoldNum(soldNum)
                .build();
        goodMapper.addGood(good);
        return good.getId();
    }

    public int editGood(Good good) {
        return goodMapper.editGood(good);
    }

    public int deleteGood(Good good) {
        return goodMapper.deleteGoodById(good);
    }

    public Good findGood(int id) {
        return goodMapper.getGoodById(id);
    }

    public List<Good> findGoods() {
        return goodMapper.getGoods();
    }

    @Test
    @Rollback
    public void testGood() throws Exception {
        int id = addGood(3, -1, "testTitle11", "testIntro", "testContent", "https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg",
                100, 1521210593, 0, false);
        Good good = findGood(id);
        System.out.println(id);
        good.setContent("edit");
        int row = editGood(good);
        Assert.assertEquals(1, row);
//        Assert.assertEquals(2, findGoods().size());
    }

}
