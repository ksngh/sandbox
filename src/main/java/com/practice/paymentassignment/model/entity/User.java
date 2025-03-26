package com.practice.paymentassignment.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30,name = "name", unique = false)
    private String name;

    @Column(nullable = false, name = "balance")
    private Long balance;

    private User(String name) {
        this.name = name;
        this.balance = 0L;
    }

    public static User of(String name) {
        return new User(name);
    }

    public void charge(int amount) {
        this.balance += amount;
    }

    public void pay(int amount) {
        this.balance -= amount;
    }

}
