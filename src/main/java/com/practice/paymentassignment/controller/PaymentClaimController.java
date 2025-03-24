package com.practice.paymentassignment.controller;

import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimCreate;
import com.practice.paymentassignment.model.entity.PaymentClaim;
import com.practice.paymentassignment.service.PaymentClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class PaymentClaimController {

    private final PaymentClaimService paymentClaimService;

    @PostMapping("/payment-claim")
    public ResponseEntity<PaymentClaimCreate.Response> createPaymentClaim(@RequestBody PaymentClaimCreate.Request paymentClaimCreateRequest) {
        PaymentClaimCreate.Response paymentClaimResponse = paymentClaimService.createPaymentClaim(paymentClaimCreateRequest);
        return ResponseEntity.ok(paymentClaimResponse);
    }

}
