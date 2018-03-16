package com.windbise.css.mapper;

import com.windbise.css.entity.Cart;
import org.apache.ibatis.annotations.*;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Mapper
public interface CartMapper {

    @Select("SELECT * FROM CART WHERE ID = #{id}")
    Cart findById(@Param("id") int id);

    @Insert("INSERT INTO CART(BUYER_ID, GOOD_ID, GOOD_NUM, COST, ORDER_TIME, DELETED) " +
            "VALUES(#{buyerId}, #{goodId}, #{goodNum}, #{cost}, #{orderTime}, #{deleted})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("buyerId") int buyerId, @Param("goodId") int goodId, @Param("goodNum") int goodNum, @Param("cost") int cost,
               @Param("orderTime") long orderTime, @Param("deleted") boolean deleted);

    @Delete("DELETE FROM CART WHERE ID = #{id}")
    int delete(@Param("id") int id);
}
