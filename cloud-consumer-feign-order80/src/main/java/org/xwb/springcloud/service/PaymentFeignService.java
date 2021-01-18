package org.xwb.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.xwb.springcloud.entities.CommonResult;
import org.xwb.springcloud.entities.Payment;

/**
 * 使用FeignClient注解，调用CLOUD-PAYMENT-SERVICE服务
 */
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE") //使用FeignClient注解，调用CLOUD-PAYMENT-SERVICE服务
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeOut();
}
