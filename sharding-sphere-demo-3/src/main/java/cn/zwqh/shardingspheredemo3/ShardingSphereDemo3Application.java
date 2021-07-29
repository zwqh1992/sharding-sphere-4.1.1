package cn.zwqh.shardingspheredemo3;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class ShardingSphereDemo3Application {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereDemo3Application.class, args);
    }

}
