package cn.zwqh.shardingspheredemo6.entity;

import lombok.Data;

@Data
public class OrderItem{
    
    private long orderItemId;
    
    private long orderId;
    
    private int userId;
    
    private String status;

}