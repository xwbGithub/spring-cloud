package org.xwb.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.xwb.springcloud.entities.CommonResult;
import org.xwb.springcloud.entities.Payment;
import org.xwb.springcloud.service.PaymentFeignService;

import javax.annotation.Resource;

/**
 * 最老的版本使用httpclient<br/>
 * 第二次使用restTemplate
 */
@SuppressWarnings("rawtypes")
@RestController
@Slf4j
public class OrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public String paymentFeignTimeOut() {
        return paymentFeignService.paymentFeignTimeOut();
    }
}
