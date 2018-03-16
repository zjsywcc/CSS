package com.windbise.css.mapper;

import com.windbise.css.entity.Purchase;
import org.apache.ibatis.annotations.*;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Mapper
public interface PurchaseMapper {

    @Select("SELECT * FROM PURCHASE WHERE ID = #{id}")
    Purchase findById(@Param("id") int id);

    @Insert("INSERT INTO PURCHASE(BUYER_ID, GOOD_ID, GOOD_NUM, COST, PURCHASE_TIME, TRANSACTION_ID) " +
            "VALUES(#{buyerId}, #{goodId}, #{goodNum}, #{cost}, #{purchaseTime}, #{transactionId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("buyerId") int buyerId, @Param("goodId") int goodId, @Param("goodNum") int goodNum, @Param("cost") int cost,
               @Param("purchaseTime") long purchaseTime, @Param("transactionId") long transactionId);

    @Delete("DELETE FROM PURCHASE WHERE ID = #{id}")
    int delete(@Param("id") int id);
}
