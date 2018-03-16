package com.windbise.css.service.impl;

import com.windbise.css.entity.User;
import com.windbise.css.mapper.UserMapper;
import com.windbise.css.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangchengcheng on 2018/3/5.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfoByUsername(String username) {
        return userMapper.findByName(username);
    }

}
