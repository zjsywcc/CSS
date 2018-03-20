package com.windbise.css.service.impl;

import com.windbise.css.entity.User;
import com.windbise.css.mapper.UserMapper;
import com.windbise.css.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by wangchengcheng on 2018/3/5.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public User getCurrentUser(HttpSession session) {
        User currentUser = (User)session.getAttribute("userModel");
        return currentUser;
    }
}
