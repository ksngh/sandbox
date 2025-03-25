package com.practice.paymentassignment.service;

import com.practice.paymentassignment.model.dto.payment.PaymentCreate;
import com.practice.paymentassignment.model.dto.payment.PaymentPay;
import com.practice.paymentassignment.model.entity.Payment;

public interface PaymentService {

    /**
     * 결제 시도 시 Payment를 생성합니다.
     * @param paymentClaimId
     * @return PaymentCreate.Response
     */
    PaymentCreate.Response create(Long paymentClaimId);

    /**
     * 외부 API에서 응답을 받으면 결제 성공 처리합니다.
     * @param paymentId
     * @return PaymentPay.Response
     */
    PaymentPay.Response paySuccessfully(Long paymentId);

    /**
     * ID로 결제를 조회합니다.
     * @param paymentId
     * @return Payment
     */
    Payment getById(Long paymentId);

}
