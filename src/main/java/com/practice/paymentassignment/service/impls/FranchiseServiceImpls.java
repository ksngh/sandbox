package com.practice.paymentassignment.service.impls;

import com.practice.paymentassignment.model.entity.Franchise;
import com.practice.paymentassignment.repository.FranchiseRepository;
import com.practice.paymentassignment.service.FranchiseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FranchiseServiceImpls implements FranchiseService {

    private final FranchiseRepository franchiseRepository;

    @Override
    public Franchise getById(Long id) {
        return franchiseRepository.findById(id).orElseThrow(NullPointerException::new);
    }
}
