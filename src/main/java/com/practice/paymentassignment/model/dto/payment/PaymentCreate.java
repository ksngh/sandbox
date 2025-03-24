package com.practice.paymentassignment.model.dto.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentCreate {

    @Getter
    public static class Request{
        Long userId;
        Long amount;

    }

    @Getter
    @NoArgsConstructor
    public static class Response{
        public Long id;
    }
}
