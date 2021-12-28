package org.xwb.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamMqMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMqMain8802.class, args);
    }
}
