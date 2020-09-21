package org.xwb.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.xwb.springcloud.entities.CommonResult;
import org.xwb.springcloud.entities.Payment;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;
    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1l, new Payment(1L, "210374208734KJEWFKW0U20sklfw0uor023u4"));
        hashMap.put(2l, new Payment(2L, "3498hofwsefhls93984rfhseohf2890343rwe"));
        hashMap.put(3l, new Payment(3L, "09snkjdfhjoiwhefdsnfjkhweo5235wefhwee"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200, "from mysql,serverPort" + serverPort, payment);
        return result;
    }
}

