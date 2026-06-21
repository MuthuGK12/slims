package com.pro.slims.service;

import java.util.List;

import com.pro.slims.dto.SampleRegistrationDTO;

public interface SampleRegistrationService {

    SampleRegistrationDTO createSample(SampleRegistrationDTO dto);

    SampleRegistrationDTO getSample(Long sampleId);

    List<SampleRegistrationDTO> getAllSamples();

    SampleRegistrationDTO updateSample(Long sampleId, SampleRegistrationDTO dto);

    void deleteSample(Long sampleId);
}