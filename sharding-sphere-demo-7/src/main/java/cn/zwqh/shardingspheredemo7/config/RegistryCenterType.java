package cn.zwqh.shardingspheredemo7.config;

/**
 * @description: 注册中心
 * @author: wangc
 * @create: 2021-07-20 15:28
 **/
public enum RegistryCenterType {
    ZOOKEEPER("zookeeper"),
    NACOS("NACOS"),
    ;
    private String name;

    RegistryCenterType(String name) {
        this.name = name;
    }
}
