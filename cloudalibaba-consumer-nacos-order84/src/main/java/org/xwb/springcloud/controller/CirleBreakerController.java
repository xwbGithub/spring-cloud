package org.xwb.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.xwb.springcloud.entities.CommonResult;
import org.xwb.springcloud.entities.Payment;
import org.xwb.springcloud.service.PaymentService;

import javax.annotation.Resource;

@Slf4j
@RestController
public class CirleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")//什么都不配置
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")//只负责业务异常
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler")//blockHandler只负责sentinel控制台的配置
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})//blockHandler只负责sentinel控制台的配置
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该id没有对应的记录，空指针异常");
        }
        return result;
    }

    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException blockException) {
        Payment payment = new Payment(id, null);
        return new CommonResult(445, "blockHandler-sentinel限流，无此流水，blockException:" + blockException.getMessage(), payment);

    }

    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, null);
        return new CommonResult(444, "兜底异常handlerFallback，exception内容" + e.getMessage(), payment);
    }
//    public CommonResult<Payment> fallback(BlockException blockException) {
//        return new CommonResult<Payment>(444, "", null);
//    }


    //====================================openFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }
}

