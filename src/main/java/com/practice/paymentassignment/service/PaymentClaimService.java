package com.practice.paymentassignment.service;

import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimCreate;
import com.practice.paymentassignment.model.entity.PaymentClaim;

public interface PaymentClaimService {

    /**
     * 결제 요청을 생성합니다.
     * @param paymentClaimCreateRequest
     * @return PaymentClaimCreate.Response
     */
    PaymentClaimCreate.Response createPaymentClaim(PaymentClaimCreate.Request paymentClaimCreateRequest);

    PaymentClaim getPaymentClaim(Long paymentClaimId);

}
