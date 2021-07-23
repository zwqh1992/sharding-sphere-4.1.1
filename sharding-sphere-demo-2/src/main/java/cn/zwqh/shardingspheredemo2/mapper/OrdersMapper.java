package cn.zwqh.shardingspheredemo2.mapper;

import cn.zwqh.shardingspheredemo2.entity.Orders;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrdersMapper {

    @Insert("insert into t_orders(order_id,order_type,user_id,order_amount) values(#{orderId},#{orderType},#{userId},#{orderAmount})")
    void insert(Orders orders);


    @Select("select * from t_orders where order_id = #{orderId} and user_id=#{userId}")
    @Results({
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderType", column = "order_type"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "orderAmount", column = "order_amount")
    })
    Orders selectOneDB(Orders orders);
}