package com.practice.paymentassignment.model.entity;

import com.practice.paymentassignment.model.enums.PaymentClaimStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "payment_claims")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", nullable = false)
    private Franchise franchise;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private PaymentClaimStatus status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();

    private PaymentClaim(User user, Franchise franchise, Long amount) {
        this.user = user;
        this.franchise = franchise;
        this.amount = amount;
        this.status = PaymentClaimStatus.PENDING;
    }

    public static PaymentClaim of(User user, Franchise franchise, Long amount) {
        return new PaymentClaim(user, franchise, amount);
    }

    public void payCompleted(){
        this.status = PaymentClaimStatus.COMPLETED;
    }

}
