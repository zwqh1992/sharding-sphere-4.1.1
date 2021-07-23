package cn.zwqh.shardingspheredemo4.mapper;

import cn.zwqh.shardingspheredemo4.entity.OrderType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderTypeMapper {

    @Insert("insert into t_order_type(type_id,type_name) values(#{typeId},#{typeName})")
    void insertOrderType(OrderType orderType);

}