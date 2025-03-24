package com.practice.paymentassignment.repository.jpa;

import com.practice.paymentassignment.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
