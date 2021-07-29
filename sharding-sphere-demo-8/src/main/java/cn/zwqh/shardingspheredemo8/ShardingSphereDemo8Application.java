package cn.zwqh.shardingspheredemo8;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class ShardingSphereDemo8Application {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereDemo8Application.class, args);
    }

}
