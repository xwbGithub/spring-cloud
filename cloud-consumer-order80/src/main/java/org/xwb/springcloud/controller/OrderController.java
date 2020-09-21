package org.xwb.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.xwb.springcloud.entities.CommonResult;
import org.xwb.springcloud.entities.Payment;
import org.xwb.springcloud.lb.impl.MyLB;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * 最老的版本使用httpclient<br/>
 * 第二次使用restTemplate
 */
@SuppressWarnings("rawtypes")
@RestController
@Slf4j
public class OrderController {
    /**
     * 此访问rest访问方式
     */
    @Resource
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoverClient;
    @Autowired
    private MyLB myLB;

    //public static final String PAYMENT_PATH = "http://localhost:8001";
    public static final String PAYMENT_PATH = "http://CLOUD-PAYMENT-SERVICE";

    /**
     * 80调用8001的接口
     *
     * @param payment 实体类
     * @return
     */
    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        CommonResult<Payment> commonResult = restTemplate.postForObject(PAYMENT_PATH + "/payment/create", payment, CommonResult.class);
        return commonResult;
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        CommonResult<Payment> forObject = restTemplate.getForObject(PAYMENT_PATH + "/payment/get/" + id, CommonResult.class);
        return forObject;
    }

    @GetMapping("/consumer/payment2/get/{id}")
    public CommonResult getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_PATH + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败!");
        }
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoverClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance instances1 = myLB.instances(instances);
        URI uri = instances1.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String getPayment2() {
//        String result = restTemplate.getForObject(PAYMENT_PATH + "/payment/zipkin", String.class);
        String result = restTemplate.getForObject("http://localhost:8001/payment/zipkin", String.class);
        return result;
    }
}
