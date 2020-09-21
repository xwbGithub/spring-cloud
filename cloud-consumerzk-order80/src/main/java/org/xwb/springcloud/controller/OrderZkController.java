package org.xwb.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZkController {
    /**
     * 此访问rest访问方式
     */
    @Resource
    private RestTemplate restTemplate;

    //public static final String PAYMENT_PATH = "http://localhost:8001";
    public static final String INVOKE_URL = "http://CLOUD-PROVIDER-PAYMENT";

    @GetMapping("/consumer/payment/zk")
    public String paymentInfo() {
        String forObject = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        return forObject;
    }
}

