package cn.zwqh.shardingspheredemo5.mapper;

import cn.zwqh.shardingspheredemo5.entity.UserEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into t_user(user_id,user_name) values(#{userId},#{userName})")
    void insertUserInfo(UserEntity userEntity);

    @Select("select * from t_user where user_id=#{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name")
    })
    UserEntity getUserInfo(Integer userId);

}