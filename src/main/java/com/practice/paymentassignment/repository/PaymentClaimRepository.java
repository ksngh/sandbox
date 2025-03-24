package com.practice.paymentassignment.repository;

import com.practice.paymentassignment.model.entity.PaymentClaim;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentClaimRepository {

    /**
     * PaymentClaim 엔티티를 저장합니다.
     * @param paymentClaim
     * @return PaymentClaim
     */
    PaymentClaim save(PaymentClaim paymentClaim);

}
