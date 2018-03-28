package com.windbise.css.mapper;

import com.windbise.css.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Mapper
public interface CartMapper {

    @Select("SELECT * FROM CART WHERE ID = #{id}")
    Cart findById(@Param("id") int id);

    @Select("SELECT * FROM CART WHERE BUYER_ID = #{buyerId} ")
    List<Cart> getCartAll(@Param("buyerId") int buyerId);

    @Select("SELECT * FROM CART WHERE DELETED = 0 and BUYER_ID = #{buyerId} ")
    List<Cart> getCart(@Param("buyerId") int buyerId);

    @Select("SELECT * FROM CART WHERE DELETED = 0 and BUYER_ID = #{buyerId} LIMIT #{index}, #{pageSize} ")
    List<Cart> getCartsByPage(@Param("buyerId") int buyerId, @Param("index") int index, @Param("pageSize") int pageSize);

    @Insert("INSERT INTO CART(BUYER_ID, GOOD_ID, GOOD_TITLE, GOOD_NUM, GOOD_COST, ORDER_TIME, DELETED) " +
            "VALUES(#{buyerId}, #{goodId}, #{goodTitle}, #{goodNum}, #{goodCost}, #{orderTime}, #{deleted})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addCart(Cart cart);

    @Insert("<script>" +
            "INSERT INTO CART(BUYER_ID, GOOD_ID, GOOD_TITLE, GOOD_NUM, GOOD_COST, ORDER_TIME, DELETED) VALUES" +
            "<foreach collection=\"list\" item=\"item1\" index=\"index\" separator=\",\">" +
            "(#{item1.buyerId}, #{item1.goodId}, #{item1.goodTitle}, #{item1.goodNum}, #{item1.goodCost}, #{item1.orderTime}, #{item1.deleted})" +
            "</foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBatch(@Param("list") List<Cart> carts);

    @Update("<script>" +
            "<foreach item=\"item1\" collection=\"list\" index=\"index\" open='' close='' separator=\";\">" +
            " UPDATE CART " +
            "SET BUYER_ID = #{item1.buyerId}, " +
            "GOOD_ID = #{item1.goodId}, " +
            "GOOD_TITLE = #{item1.goodTitle}, " +
            "GOOD_NUM = #{item1.goodNum}, " +
            "GOOD_COST = #{item1.goodCost}, " +
            "ORDER_TIME = #{item1.orderTime}, " +
            "DELETED = #{item1.deleted} " +
            "WHERE ID = #{item1.id}" +
            "</foreach>" +
            "</script>")
    int updateBatch(@Param("list") List<Cart> carts);

    @Update("UPDATE CART SET DELETED = 1 WHERE BUYER_ID = #{buyerId}")
    int emptyCart(@Param("buyerId") int buyerId);
}
