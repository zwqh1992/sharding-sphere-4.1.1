package cn.zwqh.shardingspheredemo5;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class ShardingSphereDemo5Application {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereDemo5Application.class, args);
    }

}
