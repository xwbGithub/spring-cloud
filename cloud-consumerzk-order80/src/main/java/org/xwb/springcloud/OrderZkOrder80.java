package org.xwb.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderZkOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderZkOrder80.class, args);
    }
}
