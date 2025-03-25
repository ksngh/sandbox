package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.dto.payment.PaymentCreate;
import com.practice.paymentassignment.model.dto.payment.PaymentPay;
import com.practice.paymentassignment.model.entity.Payment;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import com.practice.paymentassignment.model.enums.PaymentStatus;
import com.practice.paymentassignment.repository.PaymentClaimRepository;
import com.practice.paymentassignment.repository.PaymentRepository;
import com.practice.paymentassignment.service.PaymentClaimService;
import com.practice.paymentassignment.service.PaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpls implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentClaimService paymentClaimService;

    @Override
//    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Transactional
    public PaymentCreate.Response create(Long paymentClaimId) {
        PaymentClaim paymentClaim = paymentClaimService.getPaymentClaim(paymentClaimId);
        validateUniqueness(paymentClaim);
        Payment payment = Payment.of(paymentClaim);
        return PaymentCreate.Response.fromEntity(paymentRepository.save(payment));
    }

    @Override
    @Transactional
    public PaymentPay.Response paySuccessfully(Long paymentId) {
        Payment payment = getById(paymentId);
        payment.paySuccessfully();
        payment.getPaymentClaim().payCompleted();
        return PaymentPay.Response.fromEntity(payment);
    }

    @Override
    public Payment getById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(NullPointerException::new);
    }

    private void validateUniqueness(PaymentClaim paymentClaim) {
        if (paymentRepository.existsPaymentByPaymentClaimAndPaymentStatusIs(paymentClaim, PaymentStatus.PENDING)) {
            throw new IllegalStateException("결제가 이미 진행중입니다.");
        }
    }

}
