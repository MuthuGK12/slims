package com.pro.slims.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.pro.slims.enums.Priority;
import com.pro.slims.enums.SampleStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SampleRegistrationDTO {

    private Long sampleId;

    private String sampleNo;

    private String jobNo;

    @NotNull(message = "Customer Id is required")
    private Long customerId;

    @NotBlank(message = "Sample Name is required")
    @Size(max = 200, message = "Sample Name cannot exceed 200 characters")
    private String sampleName;

    @Size(max = 1000, message = "Sample Description cannot exceed 1000 characters")
    private String sampleDescription;

    @NotBlank(message = "Sample Type is required")
    @Size(max = 100, message = "Sample Type cannot exceed 100 characters")
    private String sampleType;

    @Size(max = 100, message = "Sample Category cannot exceed 100 characters")
    private String sampleCategory;

    @Size(max = 200, message = "Sampling Point cannot exceed 200 characters")
    private String samplingPoint;

    @Size(max = 100, message = "Batch No cannot exceed 100 characters")
    private String batchNo;

    @Size(max = 100, message = "Lot No cannot exceed 100 characters")
    private String lotNo;

    @NotNull(message = "Received Date is required")
    private LocalDateTime receivedDate;

    @NotBlank(message = "Received By is required")
    @Size(max = 100, message = "Received By cannot exceed 100 characters")
    private String receivedBy;

    private LocalDateTime collectedDate;

    private String collectedBy;

    @Positive(message = "Quantity must be greater than zero")
    private BigDecimal quantity;

    private String quantityUnit;

    private String conditionOnReceipt;

    @NotNull(message = "Priority is required")
    private Priority priority;

    private SampleStatus status;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String remarks;
    
    private String createdBy;

    private LocalDateTime createdDate;

    private String modifiedBy;

    private LocalDateTime modifiedDate;
}