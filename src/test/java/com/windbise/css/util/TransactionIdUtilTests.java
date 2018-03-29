package com.windbise.css.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wangchengcheng on 2018/3/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionIdUtilTests {

    @Test
    public void test() {
        System.out.println(TransactionIdUtil.getTransactionId(4, 5));
    }
}
