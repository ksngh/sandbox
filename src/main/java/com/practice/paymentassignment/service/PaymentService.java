package com.practice.paymentassignment.service;

import com.practice.paymentassignment.model.dto.payment.PaymentCreate;
import com.practice.paymentassignment.model.entity.Payment;

public interface PaymentService {

    /**
     * 결제 시도 시 Payment를 생성합니다.
     * @param paymentClaimId
     * @return PaymentCreate.Response
     */
    PaymentCreate.Response create(Long paymentClaimId);

    /**
     * ID로 결제를 조회합니다.
     * @param paymentId
     * @return Payment
     */
    Payment getById(Long paymentId);

}
