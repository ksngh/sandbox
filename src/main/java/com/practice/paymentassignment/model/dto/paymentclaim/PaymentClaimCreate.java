package com.practice.paymentassignment.model.dto.paymentclaim;

import com.practice.paymentassignment.model.entity.Franchise;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import com.practice.paymentassignment.model.entity.User;
import com.practice.paymentassignment.model.enums.PaymentClaimStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentClaimCreate {

    @Getter
    @NoArgsConstructor
    public static class Request{
        private Long userId;
        private Long amount;
        private Long franchiseId;
        private PaymentClaimStatus status;

        public PaymentClaim toEntity(User user, Franchise franchise, Long amount,PaymentClaimStatus status){
            return PaymentClaim.of(user,franchise,amount,status);
        }

    }

    @AllArgsConstructor
    public static class Response{
        private Long id;

        public static Response fromEntity(PaymentClaim paymentClaim){
            return new Response(paymentClaim.getId());
        }
    }

}
