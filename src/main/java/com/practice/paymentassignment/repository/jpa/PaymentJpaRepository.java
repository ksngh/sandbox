package com.practice.paymentassignment.repository.jpa;

import com.practice.paymentassignment.model.entity.Payment;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

    Boolean existsPaymentByPaymentClaimAndSuccessYnIsTrue(PaymentClaim paymentClaim);
}
