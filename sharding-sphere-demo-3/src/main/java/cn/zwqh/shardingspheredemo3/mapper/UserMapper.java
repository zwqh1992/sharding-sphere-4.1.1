package cn.zwqh.shardingspheredemo3.mapper;

import cn.zwqh.shardingspheredemo3.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("insert into users(user_id,user_name) values(#{userId},#{userName})")
    void insertUser(UserEntity userEntity);

}