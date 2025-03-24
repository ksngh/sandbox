package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.dto.user.UserCreate;
import com.practice.paymentassignment.model.entity.User;
import com.practice.paymentassignment.repository.UserRepository;
import com.practice.paymentassignment.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpls implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    @Transactional
    public UserCreate.Response create(UserCreate.Request userCreateRequest) {
        User user = userCreateRequest.toEntity(userCreateRequest);
        return UserCreate.Response.fromEntity(userRepository.save(user));
    }

}
