package com.practice.paymentassignment.repository.impls;

import com.practice.paymentassignment.model.entity.Payment;
import com.practice.paymentassignment.repository.PaymentRepository;
import com.practice.paymentassignment.repository.jpa.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpls implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentJpaRepository.save(payment);
    }

}
