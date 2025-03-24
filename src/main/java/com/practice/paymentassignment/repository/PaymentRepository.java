package com.practice.paymentassignment.repository;

import com.practice.paymentassignment.model.entity.Payment;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository {

    /**
     * 결제 정보를 저장합니다.
     * @param payment
     * @return Payment
     */
    Payment save(Payment payment);
}
