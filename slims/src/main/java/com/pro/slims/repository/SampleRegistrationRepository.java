package com.pro.slims.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.slims.entity.SampleRegistration;

public interface SampleRegistrationRepository
        extends JpaRepository<SampleRegistration, Long> {

    Optional<SampleRegistration> findBySampleNo(String sampleNo);

    boolean existsBySampleNo(String sampleNo);

    SampleRegistration findTopByOrderBySampleIdDesc();
}