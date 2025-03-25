package com.practice.paymentassignment.controller;

import com.practice.paymentassignment.model.dto.payment.PaymentCreate;
import com.practice.paymentassignment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment-claim/{paymentClaimId}/payment")
    public ResponseEntity<PaymentCreate.Response> pay(@PathVariable Long paymentClaimId) {
        PaymentCreate.Response response = paymentService.create(paymentClaimId);
        return ResponseEntity.ok(response);
    }

}
