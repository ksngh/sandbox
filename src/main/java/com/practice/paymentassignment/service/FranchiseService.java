package com.practice.paymentassignment.service;

import com.practice.paymentassignment.model.entity.Franchise;
import org.springframework.stereotype.Service;

@Service
public interface FranchiseService {
    /**
     * ID로 가맹점을 조회합니다.
     *
     * @param id
     * @return Optional<Franchise>
     */
    Franchise getById(Long id);

}
