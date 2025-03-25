package com.practice.paymentassignment.model.entity;

import com.practice.paymentassignment.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id", nullable = false)
    private PaymentClaim paymentClaim;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public void paySuccessfully() {
        this.paymentStatus = PaymentStatus.SUCCESS;
    }

    private Payment(PaymentClaim paymentClaim) {
        this.paymentClaim = paymentClaim;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public static Payment of(PaymentClaim paymentClaim) {
        Payment payment = new Payment(paymentClaim);
        paymentClaim.getPayments().add(payment);
        return payment;
    }
}