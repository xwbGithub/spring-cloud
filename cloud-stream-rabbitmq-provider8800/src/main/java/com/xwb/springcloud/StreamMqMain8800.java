package com.xwb.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Administrator
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamMqMain8800 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMqMain8800.class, args);
    }
}
