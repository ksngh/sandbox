package com.practice.paymentassignment.service;

import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimCreate;
import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimDetails;
import com.practice.paymentassignment.model.entity.PaymentClaim;

public interface PaymentClaimService {

    /**
     * 결제 요청을 생성합니다.
     * @param paymentClaimCreateRequest
     * @return PaymentClaimCreate.Response
     */
    PaymentClaimCreate.Response createPaymentClaim(PaymentClaimCreate.Request paymentClaimCreateRequest);

    /**
     * 결제 요청 정보를 조회합니다.
     * @param paymentClaimId
     * @return PaymentClaim
     */
    PaymentClaim getPaymentClaim(Long paymentClaimId);

    /**
     * 결제 요청 상세정보를 조회합니다.
     * @param paymentClaimId
     * @return PaymentClaimDetails.Response
     */
    PaymentClaimDetails.Response getPaymentClaimDetails(Long paymentClaimId);

}
