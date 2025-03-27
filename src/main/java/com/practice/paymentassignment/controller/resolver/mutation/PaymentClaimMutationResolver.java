package com.practice.paymentassignment.controller.resolver.mutation;

import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimCreate;
import com.practice.paymentassignment.service.PaymentClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PaymentClaimMutationResolver {

    private final PaymentClaimService paymentClaimService;

    @MutationMapping
    public Boolean createPaymentClaim(@Argument PaymentClaimCreate.Request paymentClaimCreateRequest) {
        paymentClaimService.createPaymentClaim(paymentClaimCreateRequest);
        return true;
    }

}
