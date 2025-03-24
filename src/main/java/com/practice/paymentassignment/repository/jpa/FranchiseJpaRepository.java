package com.practice.paymentassignment.repository.jpa;

import com.practice.paymentassignment.model.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseJpaRepository extends JpaRepository<Franchise, Long> {
}
