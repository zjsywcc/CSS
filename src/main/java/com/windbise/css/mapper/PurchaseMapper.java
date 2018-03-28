package com.windbise.css.mapper;

import com.windbise.css.entity.Purchase;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Mapper
public interface PurchaseMapper {

    @Select("SELECT * FROM PURCHASE WHERE ID = #{id}")
    Purchase findById(@Param("id") int id);

    @Select("SELECT * FROM PURCHASE WHERE BUYER_ID = #{buyerId} ")
    List<Purchase> getPurchases(@Param("buyerId") int buyerId);

    @Select("SELECT * FROM PURCHASE WHERE BUYER_ID = #{buyerId} LIMIT #{index}, #{pageSize} ")
    List<Purchase> getPurchasesByPage(@Param("buyerId") int buyerId, @Param("index") int index, @Param("pageSize") int pageSize);

    @Insert("INSERT INTO PURCHASE(BUYER_ID, GOOD_ID, GOOD_NUM, GOOD_TITLE, GOOD_PHOTO, GOOD_COST, PURCHASE_TIME, TRANSACTION_ID) " +
            "VALUES(#{buyerId}, #{goodId}, #{goodNum}, #{goodTitle}, #{goodPhoto}, #{goodCost}, #{purchaseTime}, #{transactionId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("buyerId") int buyerId, @Param("goodId") int goodId, @Param("goodNum") int goodNum, @Param("goodTitle") String goodTitle, @Param("goodPhoto") String goodPhoto, @Param("goodCost") int goodCost,
               @Param("purchaseTime") long purchaseTime, @Param("transactionId") long transactionId);

    @Insert("<script>" +
            "INSERT INTO PURCHASE(BUYER_ID, GOOD_ID, GOOD_TITLE, GOOD_NUM, GOOD_PHOTO, GOOD_COST, PURCHASE_TIME, TRANSACTION_ID) VALUES" +
            "<foreach collection=\"list\" item=\"item1\" index=\"index\" separator=\",\">" +
            "(#{item1.buyerId}, #{item1.goodId}, #{item1.goodTitle}, #{item1.goodNum}, #{item1.goodPhoto}, #{item1.goodCost}, #{item1.purchaseTime}, #{item1.transactionId})" +
            "</foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(@Param("list") List<Purchase> purchases);

}
