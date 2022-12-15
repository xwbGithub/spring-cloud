package org.xwb.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xwb.springcloud.hystrix.BaseHystrix;
import org.xwb.springcloud.service.PaymentHystrixService;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_global_fallbackMethod")
public class OrderHystrixController extends BaseHystrix {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String getPaymentById(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_ok(id);
    }

    @RequestMapping("/consumer/payment/hystrix/ok/{id}")
    public String getPaymentByIdPostAndGet(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_ok(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String paymentFeignTimeOut(@PathVariable("id") Integer id) {
        int a = 10 / 0;
        return paymentHystrixService.paymentInfo_timeOut(id);
    }
}
