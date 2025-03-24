package com.practice.paymentassignment.service;

import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimCreate;
import org.springframework.stereotype.Service;

@Service
public interface PaymentClaimService {


    /**
     * 결제 요청을 생성합니다.
     * @param paymentClaimCreateRequest
     * @return PaymentClaimCreate.Response
     */
    PaymentClaimCreate.Response createPaymentClaim(PaymentClaimCreate.Request paymentClaimCreateRequest);


}
