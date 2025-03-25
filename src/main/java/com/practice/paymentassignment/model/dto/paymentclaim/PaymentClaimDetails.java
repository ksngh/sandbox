package com.practice.paymentassignment.model.dto.paymentclaim;

import com.practice.paymentassignment.model.entity.Franchise;
import com.practice.paymentassignment.model.entity.Payment;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentClaimDetails {

    public static class Request{

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Response{
        private String franchiseName;
        private Long amount;

        public static Response fromEntity(Franchise franchise, PaymentClaim paymentClaim){
            return new Response(franchise.getName(), paymentClaim.getAmount());
        }
    }

}
