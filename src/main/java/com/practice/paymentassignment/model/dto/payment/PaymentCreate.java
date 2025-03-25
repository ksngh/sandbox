package com.practice.paymentassignment.model.dto.payment;

import com.practice.paymentassignment.model.entity.Payment;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentCreate {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        Long paymentClaimId;

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
