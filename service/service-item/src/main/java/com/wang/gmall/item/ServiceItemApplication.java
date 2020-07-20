package com.wang.gmall.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 微笑
 * @date 2020/7/18 14:09
 */
@SpringBootApplication
@ComponentScan({"com.wang.gmall"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"com.wang.gmall"})
public class ServiceItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceItemApplication.class,args);
    }
}
