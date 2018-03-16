package com.windbise.css.mapper;

import com.windbise.css.entity.Good;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by wangchengcheng on 2018/3/16.
 */
@Mapper
public interface GoodMapper {

    @Select("SELECT * FROM GOOD WHERE ID = #{id}")
    Good findById(@Param("id") int id);

    @Select("SELECT * FROM GOOD LIMIT #{index}, #{pageSize}")
    List<Good> getGoodListByPage(@Param("index") int index, @Param("pageSize") int pageSize);

    @Insert("INSERT INTO GOOD(SELLER_ID, BUYER_ID, TITLE, INTRO, CONTENT, PHOTO, COST, CREATE_TIME, SOLD_NUM, DELETED) " +
            "VALUES(#{sellerId}, #{buyerId}, #{title}, #{intro}, #{content}, #{photo}, #{cost}, #{createTime}, #{soldNum}, #{deleted})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("sellerId") int sellerId, @Param("buyerId") int buyerId, @Param("title") String title, @Param("intro") String intro,
               @Param("content") String content, @Param("photo") String photo, @Param("cost") int cost, @Param("createTime") long createTime,
               @Param("soldNum") int soldNum, @Param("deleted") boolean deleted);

    @Delete("DELETE FROM GOOD WHERE ID = #{id}")
    int delete(@Param("id") int id);
}
