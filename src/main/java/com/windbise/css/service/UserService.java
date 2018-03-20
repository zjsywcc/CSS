package com.windbise.css.service;

import com.windbise.css.entity.User;

import javax.servlet.http.HttpSession;

/**
 * Created by wangchengcheng on 2018/3/2.
 */
public interface UserService {

    public User getUserByUsername(String username);

    public User getCurrentUser(HttpSession session);

}
