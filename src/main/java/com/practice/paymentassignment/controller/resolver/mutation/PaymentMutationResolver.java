package com.practice.paymentassignment.controller.resolver.mutation;

import com.practice.paymentassignment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PaymentMutationResolver {

    private final PaymentService paymentService;

    @MutationMapping
    public Boolean pay(@Argument Long paymentClaimId){
        paymentService.create(paymentClaimId);
        return true;
    }

}
