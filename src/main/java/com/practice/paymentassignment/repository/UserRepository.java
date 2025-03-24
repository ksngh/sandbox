package com.practice.paymentassignment.repository;

import com.practice.paymentassignment.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    /**
     * ID로 사용자를 조회합니다.
     * @param id
     * return Optional<User>
     */
    Optional<User> findById(Long id);

    /**
     * 사용자 정보를 저장합니다.
     * @param user
     * return Optional<User>
     */
    User save(User user);
}
