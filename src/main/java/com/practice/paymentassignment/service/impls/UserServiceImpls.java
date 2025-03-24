package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.entity.User;
import com.practice.paymentassignment.repository.UserRepository;
import com.practice.paymentassignment.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpls implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(NullPointerException::new);
    }
}
