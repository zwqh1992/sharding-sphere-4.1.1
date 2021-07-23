package cn.zwqh.shardingspheredemo2;

import cn.zwqh.shardingspheredemo2.entity.Orders;
import cn.zwqh.shardingspheredemo2.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class OrderTests {

    @Resource
    private OrdersMapper ordersMapper;

   @Test
    public void addOrders2() {
        for (int i = 1; i <= 100; i++) {
            Orders orders = new Orders();
            orders.setOrderId(i);
            orders.setUserId(i % 5);
            orders.setOrderType(i % 2);
            orders.setOrderAmount(1000.0 * i);
            ordersMapper.insert(orders);
        }
    }

    @Test
    public void selectOneDB(){
        Orders orders=new Orders();
        orders.setUserId(4);
        orders.setOrderId(11);
        System.out.println(ordersMapper.selectOneDB(orders));
    }

}