package com.practice.paymentassignment.repository;

import com.practice.paymentassignment.model.entity.Payment;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository {

    /**
     * 결제 정보를 저장합니다.
     * @param payment
     * @return Payment
     */
    Payment save(Payment payment);

    /**
     * 결제 정보를 조회합니다.
     * @param paymentId
     * @return Payment
     */
    Optional<Payment> findById(Long paymentId);

    /**
     * 성공한 결제가 존재하는지 확인합니다.
     * @param paymentClaim
     * @return Payment
     */
    Boolean existsPaymentByPaymentClaimAndSuccessYnIsTrue(PaymentClaim paymentClaim);

}
