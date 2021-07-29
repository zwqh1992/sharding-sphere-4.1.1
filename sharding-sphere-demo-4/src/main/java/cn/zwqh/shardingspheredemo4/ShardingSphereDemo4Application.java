package cn.zwqh.shardingspheredemo4;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class ShardingSphereDemo4Application {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereDemo4Application.class, args);
    }

}
