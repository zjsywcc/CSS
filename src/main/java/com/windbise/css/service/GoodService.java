package com.windbise.css.service;

import com.windbise.css.entity.Good;

import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
public interface GoodService {

    public List<Good> getGoodsByPage(int index, int pageSize);

    public int addGood(Good good);
}
