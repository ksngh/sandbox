package com.practice.paymentassignment.repository;

import com.practice.paymentassignment.model.entity.User;

import java.util.Optional;

public interface UserRepository {
    /**
     * ID로 사용자를 조회합니다.
     * @param id
     * return Optional<User>
     */
    Optional<User> findById(Long id);
}
