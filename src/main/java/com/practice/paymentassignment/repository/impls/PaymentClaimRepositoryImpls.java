package com.practice.paymentassignment.repository.impls;

import com.practice.paymentassignment.model.entity.PaymentClaim;
import com.practice.paymentassignment.repository.PaymentClaimRepository;
import com.practice.paymentassignment.repository.jpa.PaymentClaimJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PaymentClaimRepositoryImpls implements PaymentClaimRepository {

    private final PaymentClaimJpaRepository paymentClaimJpaRepository;

    @Override
    public PaymentClaim save(PaymentClaim paymentClaim) {
        return paymentClaimJpaRepository.save(paymentClaim);
    }

    @Override
    public Optional<PaymentClaim> findById(Long paymentClaimId) {
        return paymentClaimJpaRepository.findById(paymentClaimId);
    }

    @Override
    public void deleteAll() {
        paymentClaimJpaRepository.deleteAll();
    }

}
