package cn.zwqh.shardingspheredemo7.entity;

import lombok.Data;

@Data
public class Order {

    private long orderId;

    private int userId;

    private long addressId;

    private String status;
}