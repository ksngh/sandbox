package com.practice.paymentassignment.repository.jpa;

import com.practice.paymentassignment.model.entity.PaymentClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentClaimJpaRepository extends JpaRepository<PaymentClaim, Long> {
}
