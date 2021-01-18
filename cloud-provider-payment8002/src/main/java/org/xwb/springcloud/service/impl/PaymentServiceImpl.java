package org.xwb.springcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xwb.springcloud.dao.PaymentDao;
import org.xwb.springcloud.entities.Payment;
import org.xwb.springcloud.service.PaymentService;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
