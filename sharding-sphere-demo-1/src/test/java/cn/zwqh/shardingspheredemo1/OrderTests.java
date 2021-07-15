package cn.zwqh.shardingspheredemo1;

import cn.zwqh.shardingspheredemo1.entity.Orders;
import cn.zwqh.shardingspheredemo1.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @description:
 * @author: wangc
 * @create: 2021-07-15 10:29
 **/
@SpringBootTest
public class OrderTests {

    @Resource
    private OrdersMapper ordersMapper;

    @Test
    public void addOrders() {
        for (int i = 1; i <= 10; i++) {
            Orders orders = new Orders();
            orders.setOrderId(i);
            orders.setUserId(i % 3);
            orders.setOrderType(i % 2);
            orders.setOrderAmount(1000.0 * i);
            ordersMapper.insert(orders);
        }
    }

    @Test
    public void queryOrders() {
        Orders orders = ordersMapper.selectOne(1);
        System.out.println(orders);
    }
}
