package com.practice.paymentassignment.model.dto.paymentclaim;

import com.practice.paymentassignment.model.entity.Franchise;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import com.practice.paymentassignment.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentClaimCreate {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long userId;
        private Long amount;
        private Long franchiseId;

        public PaymentClaim toEntity(User user, Franchise franchise, Long amount) {
            return PaymentClaim.of(user, franchise, amount);
        }

    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long id;

        public static Response fromEntity(PaymentClaim paymentClaim) {
            return new Response(paymentClaim.getId());
        }
    }

}
