package cn.zwqh.shardingspheredemo8;

import cn.zwqh.shardingspheredemo8.entity.UserEntity;
import cn.zwqh.shardingspheredemo8.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest
class ShardingSphereDemo8ApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void insertUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(27);
        userEntity.setUserName("user27");
        userEntity.setPassword("123456");
        userMapper.insertUser(userEntity);
    }

    @Test
    void getUserInfo() {
        List<UserEntity> userEntityList = userMapper.getUserInfo("user27", "123456");
        log.info(userEntityList.toString());
    }

}
