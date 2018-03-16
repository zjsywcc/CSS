package com.windbise.css.service;

import com.windbise.css.entity.Good;

import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
public interface GoodService {

    public List<Good> getGoodListByPage(int index, int pageSize);
}
