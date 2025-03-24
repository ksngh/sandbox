package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.dto.payment.PaymentCreate;
import com.practice.paymentassignment.model.entity.Payment;
import com.practice.paymentassignment.repository.PaymentRepository;
import com.practice.paymentassignment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpls implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentCreate.Response create(PaymentCreate.Request paymentCreateRequest) {
        Payment payment = PaymentCreate.Request.toEntity(paymentCreateRequest);
        return PaymentCreate.Response.fromEntity(paymentRepository.save(payment));
    }

}
