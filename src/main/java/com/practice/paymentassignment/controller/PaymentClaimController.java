package com.practice.paymentassignment.controller;

import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimCreate;
import com.practice.paymentassignment.model.dto.paymentclaim.PaymentClaimDetails;
import com.practice.paymentassignment.service.PaymentClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/v1/api/payment-claim/{paymentClaimId}")
    public ResponseEntity<PaymentClaimDetails.Response> getPaymentClaimDetails(@PathVariable Long paymentClaimId) {
        return ResponseEntity.ok(paymentClaimService.getPaymentClaimDetails(paymentClaimId));
    }

}
