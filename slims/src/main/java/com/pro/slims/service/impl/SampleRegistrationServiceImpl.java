package com.pro.slims.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.slims.dto.SampleRegistrationDTO;
import com.pro.slims.entity.Customer;
import com.pro.slims.entity.SampleRegistration;
import com.pro.slims.entity.SampleStatusHistory;
import com.pro.slims.enums.SampleStatus;
import com.pro.slims.exception.ResourceNotFoundException;
import com.pro.slims.repository.CustomerRepository;
import com.pro.slims.repository.SampleRegistrationRepository;
import com.pro.slims.repository.SampleStatusHistoryRepository;
import com.pro.slims.service.SampleRegistrationService;
import com.pro.slims.util.NumberGeneratorUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleRegistrationServiceImpl implements SampleRegistrationService {

    private final SampleRegistrationRepository sampleRepository;
    private final CustomerRepository customerRepository;
    private final SampleStatusHistoryRepository historyRepository;

    @Override
    @Transactional
    public SampleRegistrationDTO createSample(SampleRegistrationDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));

        SampleRegistration sample = SampleRegistration.builder()
                .customer(customer)
                .sampleName(dto.getSampleName())
                .sampleDescription(dto.getSampleDescription())
                .sampleType(dto.getSampleType())
                .sampleCategory(dto.getSampleCategory())
                .samplingPoint(dto.getSamplingPoint())
                .batchNo(dto.getBatchNo())
                .lotNo(dto.getLotNo())
                .receivedDate(dto.getReceivedDate())
                .receivedBy(dto.getReceivedBy())
                .collectedDate(dto.getCollectedDate())
                .collectedBy(dto.getCollectedBy())
                .quantity(dto.getQuantity())
                .quantityUnit(dto.getQuantityUnit())
                .conditionOnReceipt(dto.getConditionOnReceipt())
                .priority(dto.getPriority())
                .status(SampleStatus.REGISTERED)
                .remarks(dto.getRemarks())
                .createdBy(dto.getCreatedBy())
                .createdDate(LocalDateTime.now())
                .build();

        sample = sampleRepository.save(sample);

        sample.setSampleNo(
                NumberGeneratorUtil.generateSampleNumber(
                        sample.getSampleId()));

        sample.setJobNo(
                NumberGeneratorUtil.generateJobNumber(
                        sample.getSampleId()));

        sample = sampleRepository.save(sample);

        SampleStatusHistory history = SampleStatusHistory.builder()
                .sample(sample)
                .oldStatus(null)
                .newStatus(SampleStatus.REGISTERED.name())
                .changedBy(dto.getCreatedBy())
                .remarks("Sample Registered")
                .changedDate(LocalDateTime.now())
                .build();

        historyRepository.save(history);

        return convertToDTO(sample);
    }

    @Override
    public SampleRegistrationDTO getSample(Long sampleId) {

        SampleRegistration sample = sampleRepository.findById(sampleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sample not found"));

        return convertToDTO(sample);
    }

    @Override
    public List<SampleRegistrationDTO> getAllSamples() {

        return sampleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SampleRegistrationDTO updateSample(
            Long sampleId,
            SampleRegistrationDTO dto) {

        SampleRegistration sample = sampleRepository.findById(sampleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sample not found"));

        sample.setSampleName(dto.getSampleName());
        sample.setSampleDescription(dto.getSampleDescription());
        sample.setSampleType(dto.getSampleType());
        sample.setSampleCategory(dto.getSampleCategory());
        sample.setSamplingPoint(dto.getSamplingPoint());
        sample.setBatchNo(dto.getBatchNo());
        sample.setLotNo(dto.getLotNo());
        sample.setReceivedDate(dto.getReceivedDate());
        sample.setReceivedBy(dto.getReceivedBy());
        sample.setCollectedDate(dto.getCollectedDate());
        sample.setCollectedBy(dto.getCollectedBy());
        sample.setQuantity(dto.getQuantity());
        sample.setQuantityUnit(dto.getQuantityUnit());
        sample.setConditionOnReceipt(dto.getConditionOnReceipt());
        sample.setPriority(dto.getPriority());
        sample.setRemarks(dto.getRemarks());

        sample.setModifiedBy(dto.getModifiedBy());
        sample.setModifiedDate(LocalDateTime.now());

        sample = sampleRepository.save(sample);

        return convertToDTO(sample);
    }

    @Override
    public void deleteSample(Long sampleId) {

        SampleRegistration sample = sampleRepository.findById(sampleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sample not found"));

        sampleRepository.delete(sample);
    }

    private SampleRegistrationDTO convertToDTO(
            SampleRegistration sample) {

        SampleRegistrationDTO dto = new SampleRegistrationDTO();

        dto.setSampleId(sample.getSampleId());
        dto.setSampleNo(sample.getSampleNo());
        dto.setJobNo(sample.getJobNo());

        if (sample.getCustomer() != null) {
            dto.setCustomerId(
                    sample.getCustomer().getCustomerId());
        }

        dto.setSampleName(sample.getSampleName());
        dto.setSampleDescription(sample.getSampleDescription());
        dto.setSampleType(sample.getSampleType());
        dto.setSampleCategory(sample.getSampleCategory());
        dto.setSamplingPoint(sample.getSamplingPoint());
        dto.setBatchNo(sample.getBatchNo());
        dto.setLotNo(sample.getLotNo());

        dto.setReceivedDate(sample.getReceivedDate());
        dto.setReceivedBy(sample.getReceivedBy());

        dto.setCollectedDate(sample.getCollectedDate());
        dto.setCollectedBy(sample.getCollectedBy());

        dto.setQuantity(sample.getQuantity());
        dto.setQuantityUnit(sample.getQuantityUnit());

        dto.setConditionOnReceipt(
                sample.getConditionOnReceipt());

        dto.setPriority(sample.getPriority());
        dto.setStatus(sample.getStatus());

        dto.setRemarks(sample.getRemarks());

        dto.setCreatedBy(sample.getCreatedBy());
        dto.setCreatedDate(sample.getCreatedDate());

        dto.setModifiedBy(sample.getModifiedBy());
        dto.setModifiedDate(sample.getModifiedDate());

        return dto;
    }
}