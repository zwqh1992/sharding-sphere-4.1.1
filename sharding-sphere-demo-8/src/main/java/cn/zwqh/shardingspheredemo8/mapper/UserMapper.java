package cn.zwqh.shardingspheredemo8.mapper;

import cn.zwqh.shardingspheredemo8.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description: 用户
 * @author: wangc
 * @create: 2021-07-26 11:45
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into t_user(user_id,user_name,password) values(#{userId},#{userName},#{password})")
    void insertUser(UserEntity userEntity);

    @Select("select * from t_user where user_name=#{userName} and password=#{password}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "password", property = "password"),
            @Result(column = "password_assisted", property = "passwordAssisted")
    })
    List<UserEntity> getUserInfo(@Param("userName") String userName, @Param("password") String password);
}
