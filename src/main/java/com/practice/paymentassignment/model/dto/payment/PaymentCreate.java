package com.practice.paymentassignment.model.dto.payment;

import com.practice.paymentassignment.model.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentCreate {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;

        public static Response fromEntity(Payment payment) {
            return new Response(payment.getId());
        }
    }

}
