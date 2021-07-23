package cn.zwqh.shardingspheredemo2.entity;

import lombok.Data;

/**
 * @description: 订单
 * @author: wangc
 * @create: 2021-07-15 10:42
 **/
@Data
public class Orders {

    private Integer orderId;
    private Integer orderType;
    private Integer userId;
    private Double orderAmount;
}
