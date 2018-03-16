package com.windbise.css.service.impl;

import com.windbise.css.mapper.CartMapper;
import com.windbise.css.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
}
