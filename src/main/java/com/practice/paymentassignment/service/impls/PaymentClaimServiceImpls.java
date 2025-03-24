package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimCreate;
import com.practice.paymentassignment.model.entity.Franchise;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import com.practice.paymentassignment.model.entity.User;
import com.practice.paymentassignment.repository.PaymentClaimRepository;
import com.practice.paymentassignment.service.FranchiseService;
import com.practice.paymentassignment.service.PaymentClaimService;
import com.practice.paymentassignment.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentClaimServiceImpls implements PaymentClaimService {

    private final UserService userService;
    private final FranchiseService franchiseService;


    @Override
    public PaymentClaimCreate.Response createPaymentClaim(PaymentClaimCreate.Request paymentClaimCreateRequest) {
        User user = userService.getById(paymentClaimCreateRequest.getUserId());
        Franchise franchise = franchiseService.getById(paymentClaimCreateRequest.getFranchiseId());
        PaymentClaim paymentClaim = paymentClaimCreateRequest.toEntity(
                user,
                franchise,
                paymentClaimCreateRequest.getAmount(),
                paymentClaimCreateRequest.getStatus()
        );
        return PaymentClaimCreate.Response.fromEntity(paymentClaim);
    }
}
