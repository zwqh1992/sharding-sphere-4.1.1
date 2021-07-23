package cn.zwqh.shardingspheredemo3.mapper;

import cn.zwqh.shardingspheredemo3.entity.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {

    @Insert("insert into orders(order_id,order_type,user_id,order_amount) values(#{orderId},#{orderType},#{userId},#{orderAmount})")
    void insertOrder(Orders orders);
}