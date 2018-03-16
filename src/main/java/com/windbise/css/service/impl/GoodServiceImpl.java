package com.windbise.css.service.impl;

import com.windbise.css.entity.Good;
import com.windbise.css.mapper.GoodMapper;
import com.windbise.css.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Service("goodService")
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    @Override
    public List<Good> getGoodListByPage(int index, int pageSize) {
        return goodMapper.getGoodListByPage(index, pageSize);
    }

}
