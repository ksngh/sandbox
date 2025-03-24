package com.practice.paymentassignment.repository;

import com.practice.paymentassignment.model.entity.Franchise;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FranchiseRepository {

    /**
     * ID로 가맹점을 조회합니다.
     * @param id
     * @return Optional<Franchise>
     */
    Optional<Franchise> findById(Long id);

    /**
     * 가맹점 정보를 저장합니다
     * @param franchise
     * @return Franchise
     */
    Franchise save(Franchise franchise);
}
