package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.dto.payment.PaymentCreate;
import com.practice.paymentassignment.model.entity.Payment;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import com.practice.paymentassignment.model.enums.PaymentStatus;
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
    public synchronized PaymentCreate.Response create(Long paymentClaimId) {
        PaymentClaim paymentClaim = paymentClaimService.getPaymentClaim(paymentClaimId);
        validatePaymentClaim(paymentClaim);
        Payment payment = Payment.of(paymentClaim);
        payment.paySuccessfully();
        payment.getPaymentClaim().payCompleted();
        paymentClaim.getUser().pay(Math.toIntExact(paymentClaim.getAmount()));
        return PaymentCreate.Response.fromEntity(paymentRepository.save(payment));
    }

    @Override
    public Payment getById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(NullPointerException::new);
    }

    private void validatePaymentClaim(PaymentClaim paymentClaim) {
        validateUniqueness(paymentClaim);
        validateBalance(paymentClaim);
    }

    private void validateUniqueness(PaymentClaim paymentClaim) {
        if (paymentRepository.existsPaymentByPaymentClaimAndPaymentStatusIs(paymentClaim, PaymentStatus.PENDING)) {
            throw new IllegalStateException("결제가 이미 진행중입니다.");
        } else if (paymentRepository.existsPaymentByPaymentClaimAndPaymentStatusIs(paymentClaim, PaymentStatus.SUCCESS)) {
            throw new IllegalStateException("결제가 이미 완료되었습니다.");
        }
    }

    private void validateBalance(PaymentClaim paymentClaim) {
        if (paymentClaim.getUser().getBalance() < paymentClaim.getAmount()) {
            throw new IllegalStateException("잔액이 부족합니다.");
        }
    }

}
