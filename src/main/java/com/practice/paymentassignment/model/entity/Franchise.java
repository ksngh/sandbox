package com.practice.paymentassignment.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "franchises")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    private Franchise(String name){
        this.name = name;
    }

    public static Franchise of(String name){
        return new Franchise(name);
    }
}