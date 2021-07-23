package cn.zwqh.shardingspheredemo7.entity;

import lombok.Data;

@Data
public class OrderItem{
    
    private long orderItemId;
    
    private long orderId;
    
    private int userId;
    
    private String status;

}