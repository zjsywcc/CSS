package com.windbise.css.dao;

import com.windbise.css.entity.Good;
import com.windbise.css.mapper.GoodMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

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
        int id = goodMapper.insert(sellerId, buyerId, title, intro, content, photo, cost, createTime, soldNum, deleted);
        return id;
    }

    public Good findGood(int id) {
        return goodMapper.findById(id);
    }

    @Test
    @Rollback
    public void testGood() throws Exception {
        int id = addGood(3, -1, "testTitle", "testIntro", "testContent", "https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg",
                100, 1521210593, 0, false);
        Good good = findGood(id);
        Assert.assertEquals(1521210593, good.getCreateTime());
    }

}
