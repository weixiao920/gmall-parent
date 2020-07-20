package com.wang.gmall.all;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 微笑
 * @date 2020/7/18 14:21
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.wang.gmall"})
@EnableDiscoveryClient
@ComponentScan("com.wang.gmall")
public class WebAllApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAllApplication.class,args);
    }
}
