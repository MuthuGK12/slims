package com.pro.slims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.slims.entity.SampleStatusHistory;

public interface SampleStatusHistoryRepository extends JpaRepository<SampleStatusHistory, Long> {

    List<SampleStatusHistory> findBySampleSampleIdOrderByChangedDateDesc(Long sampleId);
}