package com.practice.paymentassignment.controller.resolver.query;

import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimDetails;
import com.practice.paymentassignment.service.PaymentClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PaymentClaimQueryResolver {

    private final PaymentClaimService paymentClaimService;

    @QueryMapping
    public PaymentClaimDetails.Response getPaymentClaimDetails(@Argument Long paymentClaimId) {
        return paymentClaimService.getPaymentClaimDetails(paymentClaimId);
    }

}
