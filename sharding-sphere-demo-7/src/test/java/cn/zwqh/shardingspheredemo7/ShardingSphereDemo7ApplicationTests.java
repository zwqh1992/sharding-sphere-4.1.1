package cn.zwqh.shardingspheredemo7;

import cn.zwqh.shardingspheredemo7.entity.Address;
import cn.zwqh.shardingspheredemo7.mapper.AddressMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class ShardingSphereDemo7ApplicationTests {

    @Resource
    private AddressMapper addressMapper;

    @Test
    public void insertAddress() throws InterruptedException {
        Address address1 = new Address();
        address1.setAddressId(1L);
        address1.setAddressName("地址1");
        addressMapper.insertAddress(address1);
        Address address2 = new Address();
        address2.setAddressId(2L);
        address2.setAddressName("地址2");
        addressMapper.insertAddress(address2);
        log.info("这里开始睡眠20s——————————————>");
        // 睡眠20s用于修改配置
        Thread.sleep(20000);
        log.info("睡眠结束——————————————");
        Address address3 = new Address();
        address3.setAddressId(3L);
        address3.setAddressName("地址3");
        addressMapper.insertAddress(address3);
        Address address4 = new Address();
        address4.setAddressId(4L);
        address4.setAddressName("地址4");
        addressMapper.insertAddress(address4);

        addressMapper.deleteAllAddress();
    }

}
