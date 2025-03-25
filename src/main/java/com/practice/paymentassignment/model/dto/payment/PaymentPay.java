package com.practice.paymentassignment.model.dto.payment;

import com.practice.paymentassignment.model.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentPay {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        public Long id;

        public static PaymentPay.Response fromEntity(Payment payment) {
            return new PaymentPay.Response(payment.getId());
        }
    }

}
