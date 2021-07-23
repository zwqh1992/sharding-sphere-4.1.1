package cn.zwqh.shardingspheredemo3;

import cn.zwqh.shardingspheredemo3.entity.Orders;
import cn.zwqh.shardingspheredemo3.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class OrderTests {

    @Resource
    private OrdersMapper ordersMapper;

    @Test
    public void addOrder() {
        for (int i = 1; i <= 10; i++) {
            Orders orders = new Orders();
            orders.setOrderId(i);
            orders.setUserId(i);
            orders.setOrderType(i % 2);
            orders.setOrderAmount(1000.0 * i);
            ordersMapper.insertOrder(orders);
        }
    }

}