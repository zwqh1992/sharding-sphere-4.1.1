package cn.zwqh.shardingspheredemo8.entity;

import lombok.Data;

/**
 * @description: 用户
 * @author: wangc
 * @create: 2021-07-26 11:44
 **/
@Data
public class UserEntity {

    private Integer userId;

    private String userName;

    private String password;

    private String passwordEncrypt;

    private String passwordAssisted;
}
