package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimCreate;
import com.practice.paymentassignment.model.entity.Franchise;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import com.practice.paymentassignment.model.entity.User;
import com.practice.paymentassignment.repository.PaymentClaimRepository;
import com.practice.paymentassignment.service.FranchiseService;
import com.practice.paymentassignment.service.PaymentClaimService;
import com.practice.paymentassignment.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentClaimServiceImpls implements PaymentClaimService {

    private final UserService userService;
    private final FranchiseService franchiseService;
    private final PaymentClaimRepository paymentClaimRepository;

    @Override
    @Transactional
    public PaymentClaimCreate.Response createPaymentClaim(PaymentClaimCreate.Request paymentClaimCreateRequest) {
        User user = userService.getById(paymentClaimCreateRequest.getUserId());
        Franchise franchise = franchiseService.getById(paymentClaimCreateRequest.getFranchiseId());
        PaymentClaim paymentClaim = paymentClaimCreateRequest.toEntity(
                user,
                franchise,
                paymentClaimCreateRequest.getAmount()
        );
        return PaymentClaimCreate.Response.fromEntity(paymentClaim);
    }

    @Override
    public PaymentClaim getPaymentClaim(Long paymentClaimId) {
        return paymentClaimRepository.findById(paymentClaimId).orElseThrow(NullPointerException::new);
    }
}
