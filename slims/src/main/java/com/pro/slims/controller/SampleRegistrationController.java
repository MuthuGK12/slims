package com.pro.slims.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.slims.dto.SampleRegistrationDTO;
import com.pro.slims.service.SampleRegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/samples")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SampleRegistrationController {

    private final SampleRegistrationService sampleService;

    @PostMapping
    public SampleRegistrationDTO createSample(@Valid @RequestBody SampleRegistrationDTO dto) {
        return sampleService.createSample(dto);
    }

    @GetMapping("/{sampleId}")
    public SampleRegistrationDTO getSample(@PathVariable Long sampleId) {
        return sampleService.getSample(sampleId);
    }

    @GetMapping
    public List<SampleRegistrationDTO> getAllSamples() {
        return sampleService.getAllSamples();
    }

    @PutMapping("/{sampleId}")
    public SampleRegistrationDTO updateSample(@PathVariable Long sampleId,
            @Valid @RequestBody SampleRegistrationDTO dto) {
        return sampleService.updateSample(sampleId, dto);
    }

    @DeleteMapping("/{sampleId}")
    public void deleteSample(@PathVariable Long sampleId) {
        sampleService.deleteSample(sampleId);
    }
}