package com.windbise.css.service;

import com.windbise.css.entity.Good;

import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
public interface GoodService {

    public List<Good> getGoodsByPage(int index, int pageSize);

    public Good getGoodById(int goodId);

    public int addGood(Good good);

    public int editGood(Good good);

    public int deleteGood(int id);
}
