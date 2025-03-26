package com.practice.paymentassignment.repository;

import com.practice.paymentassignment.model.entity.PaymentClaim;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentClaimRepository {

    /**
     * PaymentClaim 엔티티를 저장합니다.
     *
     * @param paymentClaim
     * @return PaymentClaim
     */
    PaymentClaim save(PaymentClaim paymentClaim);

    /**
     * PaymentClaim 엔티티를 조회합니다.
     *
     * @param paymentClaimId
     * @return Optional<PaymentClaim>
     */
    Optional<PaymentClaim> findById(Long paymentClaimId);

    /**
     * 모든 PaymentClaim 엔티티를 삭제합니다.
     */
    void deleteAll();
}
