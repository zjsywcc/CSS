package com.windbise.css.mapper;

import com.windbise.css.entity.Cart;
import com.windbise.css.entity.Good;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Mapper
public interface GoodMapper {

    @Select("SELECT * FROM GOOD WHERE ID = #{id}")
    Good getGoodById(@Param("id") int id);

    @Select("SELECT * FROM GOOD WHERE DELETED = 0 ")
    List<Good> getGoods();

    @Select("SELECT * FROM GOOD WHERE DELETED = 0 and SOLD_NUM = 0")
    List<Good> getGoodsUnbought();

    @Select("SELECT * FROM GOOD WHERE DELETED = 0 LIMIT #{index}, #{pageSize} ")
    List<Good> getGoodsByPage(@Param("index") int index, @Param("pageSize") int pageSize);


    @Insert("INSERT INTO GOOD(SELLER_ID, BUYER_ID, TITLE, INTRO, CONTENT, PHOTO, COST, CREATE_TIME, SOLD_NUM, DELETED) " +
            "VALUES(#{sellerId}, #{buyerId}, #{title}, #{intro}, #{content}, #{photo}, #{cost}, #{createTime}, #{soldNum}, #{deleted})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addGood(Good good);

    @Update("UPDATE GOOD SET TITLE = #{title}, SELLER_ID = #{sellerId}, BUYER_ID = #{buyerId}, INTRO = #{intro}, CONTENT = #{content}, " +
            "PHOTO = #{photo}, COST = #{cost}, CREATE_TIME = #{createTime}, SOLD_NUM = #{soldNum}, DELETED = #{deleted} " +
            "WHERE ID = #{id}")
    int editGood(Good good);

    @Update("UPDATE GOOD SET DELETED = 1 WHERE ID = #{id}")
    int deleteGoodById(Good good);

}
