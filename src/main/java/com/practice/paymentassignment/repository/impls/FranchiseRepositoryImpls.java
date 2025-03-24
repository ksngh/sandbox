package com.practice.paymentassignment.repository.impls;

import com.practice.paymentassignment.model.entity.Franchise;
import com.practice.paymentassignment.repository.FranchiseRepository;
import com.practice.paymentassignment.repository.jpa.FranchiseJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FranchiseRepositoryImpls implements FranchiseRepository {

    private final FranchiseJpaRepository franchiseJpaRepository;

    @Override
    public Optional<Franchise> findById(Long id) {
        return franchiseJpaRepository.findById(id);
    }
}
