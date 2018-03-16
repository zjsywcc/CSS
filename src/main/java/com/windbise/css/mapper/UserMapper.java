package com.windbise.css.mapper;

import com.windbise.css.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by wangchengcheng on 2018/3/5.
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE USERNAME = #{username}")
    User findByName(@Param("username") String username);

    @Insert("INSERT INTO USER(USERNAME, NICKNAME, PASSWORD, TYPE) VALUES(#{username}, #{nickname}, #{password}, #{type})")
    int insert(@Param("username") String username, @Param("nickname") String nickname, @Param("password") String password, @Param("type") boolean type);

    @Delete("DELETE FROM USER WHERE USERNAME = #{username}")
    int delete(@Param("username") String username);

}
