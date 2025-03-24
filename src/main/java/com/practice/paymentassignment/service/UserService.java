package com.practice.paymentassignment.service;

import com.practice.paymentassignment.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * ID로 유저를 조회합니다.
     * @param id
     * @return User
     */
    User getById(Long id);
}
