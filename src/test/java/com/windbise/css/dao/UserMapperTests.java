package com.windbise.css.dao;

import com.windbise.css.entity.User;
import com.windbise.css.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wangchengcheng on 2018/3/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;


    public User addUser(String username, String nickname, String password, boolean type) throws Exception {
        User user = userMapper.findByName(username);
        if(user != null) {
            userMapper.delete(username);
        }
        userMapper.insert(username, nickname, password, type);
        return userMapper.findByName(username);
    }

    @Test
    @Rollback
    public void addSeller() throws Exception {
        User user = addUser("seller", "卖家", "relles", false);
        Assert.assertEquals("seller", user.getUsername());
    }

    @Test
    @Rollback
    public void addBuyer() throws Exception {
        User user = addUser("buyer", "买家", "reyub", true);
        Assert.assertEquals("buyer", user.getUsername());
    }
}
