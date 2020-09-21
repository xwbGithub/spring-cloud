package org.xwb.springcloud.handler;

import org.springframework.web.bind.annotation.PathVariable;
import org.xwb.springcloud.entities.CommonResult;
import org.xwb.springcloud.entities.Payment;

public class MyHandler {
    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, null);
        return new CommonResult(444, "兜底异常handlerFallback，exception内容" + e.getMessage(), payment);
    }
}
