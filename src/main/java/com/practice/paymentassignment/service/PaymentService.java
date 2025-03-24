package com.practice.paymentassignment.service;

import com.practice.paymentassignment.model.dto.payment.PaymentCreate;

public interface PaymentService {

    /**
     * 결제 시도 시 Payment를 생성합니다.
     * @param paymentCreateRequest
     * @return PaymentCreate.Response
     */
    PaymentCreate.Response create(PaymentCreate.Request paymentCreateRequest);

}
