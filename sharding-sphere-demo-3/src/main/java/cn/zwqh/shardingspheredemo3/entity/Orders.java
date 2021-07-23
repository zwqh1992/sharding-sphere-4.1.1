package cn.zwqh.shardingspheredemo3.entity;

import lombok.Data;

@Data
public class Orders {

    private Integer orderId;
    private Integer orderType;
    private Integer userId;
    private Double orderAmount;

}