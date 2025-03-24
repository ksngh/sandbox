package com.practice.paymentassignment.model.dto.payment;

import com.practice.paymentassignment.model.entity.Payment;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentCreate {

    @Getter
    public static class Request{
        PaymentClaim paymentClaim;

        public static Payment toEntity(Request request) {
            return Payment.of(request.paymentClaim);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        public Long id;

        public static Response fromEntity(Payment payment) {
            return new Response(payment.getId());
        }
    }
}
