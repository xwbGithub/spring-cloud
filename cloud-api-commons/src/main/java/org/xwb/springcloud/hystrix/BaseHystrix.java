package org.xwb.springcloud.hystrix;

/**
 * 本方法提供类hystrix的统一熔断方法集合<br/>
 * 需要其他controller继承，然后根据方法的定义返回统一的，或者是单独自定义的异常熔断信息返回即可
 */
public class BaseHystrix {
    //以下是全局fallback方法
    public String payment_global_fallbackMethod() {
        return "Global 异常异常处理信息，请稍后重试";
    }

    public String paymentTimeOutFallbackMethod(Integer id) {
        return "我是消费者80，调用hystrix8001系统繁忙，请稍后重试，或者运行就自己的出错检查";
    }

}
