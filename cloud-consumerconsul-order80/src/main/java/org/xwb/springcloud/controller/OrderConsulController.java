package org.xwb.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderConsulController {
    /**
     * 此访问rest访问方式
     */
    @Resource
    private RestTemplate restTemplate;

    public static final String INVOKE_URL = "http://consul-PROVIDER-PAYMENT";

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo() {
        String forObject = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return forObject;
    }
}

