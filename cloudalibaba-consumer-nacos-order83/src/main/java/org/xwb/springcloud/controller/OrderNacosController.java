package org.xwb.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;
    @Value("${service-url.nacos-user-service}")
    private String nacosUserService;

    @GetMapping("/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id) {
        return restTemplate.getForObject(nacosUserService + "/payment/nacos/" + id, String.class);
    }

    @PostMapping("/consumer/{id}")
    public String paymentInfoPost(@PathVariable("id") Long id) {
        return restTemplate.getForObject(nacosUserService + "/paymentInfoPost/" + id, String.class);
    }

    @RequestMapping("/consumer/payment/{id}")
    public String paymentInfoRequest(@PathVariable("id") Long id) {
        return restTemplate.getForObject(nacosUserService + "/payment/" + id, String.class);
    }
}
