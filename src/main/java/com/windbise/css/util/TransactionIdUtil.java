package com.windbise.css.util;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/28.
 */
public class TransactionIdUtil {

    public static long getTransactionId(int goodId, int buyerId) {
        int id = 0;
        int decades = DateTimeUtil.getDecades();
        int month = DateTimeUtil.getMonth();
        int day = DateTimeUtil.getDay();
        int hour = DateTimeUtil.getHour();
        int minute = DateTimeUtil.getMinute();
        int second = DateTimeUtil.getSeconds();
        int goodCode = goodId % 100;
        int buyerCode = buyerId % 100;
        List<Integer> timeList = new ArrayList<Integer>(){{add(decades);add(month);add(day);add(hour);add(minute);add(second);add(buyerCode);add(goodCode);}};
        for(Integer i : timeList) {
            id *= 100;
            id += i;
        }
        return id;
    }
}
