package cn.zwqh.shardingspheredemo3;

import cn.zwqh.shardingspheredemo3.entity.UserEntity;
import cn.zwqh.shardingspheredemo3.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void insertUser() {
        for (int i = 1; i <= 10; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(i);
            userEntity.setUserName("user" + i);
            userMapper.insertUser(userEntity);
        }
    }
}