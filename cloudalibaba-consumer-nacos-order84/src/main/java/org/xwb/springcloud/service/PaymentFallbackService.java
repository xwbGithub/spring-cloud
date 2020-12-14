package org.xwb.springcloud.service;

import org.springframework.stereotype.Component;
import org.xwb.springcloud.entities.CommonResult;
import org.xwb.springcloud.entities.Payment;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult paymentSQL(Long id) {
        return new CommonResult<Payment>(4444,"服务降级返回，----paymentFallbackService",new Payment(id,"errorSerial"));
    }
}
