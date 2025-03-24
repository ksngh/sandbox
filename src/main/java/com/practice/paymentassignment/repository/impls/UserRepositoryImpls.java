package com.practice.paymentassignment.repository.impls;

import com.practice.paymentassignment.model.entity.User;
import com.practice.paymentassignment.repository.UserRepository;
import com.practice.paymentassignment.repository.jpa.UserJpaRepository;
import com.practice.paymentassignment.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryImpls implements UserRepository {

    private final UserJpaRepository userJpaRepository;


    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id);
    }
}
