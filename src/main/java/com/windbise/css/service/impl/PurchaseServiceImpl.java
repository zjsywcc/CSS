package com.windbise.css.service.impl;

import com.windbise.css.mapper.PurchaseMapper;
import com.windbise.css.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;
}
