package com.pro.slims.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.pro.slims.enums.Priority;
import com.pro.slims.enums.SampleStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sample_registration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SampleRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sample_id")
    private Long sampleId;

    @Column(name = "sample_no", nullable = false, unique = true)
    private String sampleNo;

    @Column(name = "job_no")
    private String jobNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "sample_name", nullable = false)
    private String sampleName;

    @Column(name = "sample_description")
    private String sampleDescription;

    @Column(name = "sample_type")
    private String sampleType;

    @Column(name = "sample_category")
    private String sampleCategory;

    @Column(name = "sampling_point")
    private String samplingPoint;

    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "lot_no")
    private String lotNo;

    @Column(name = "received_date")
    private LocalDateTime receivedDate;

    @Column(name = "received_by")
    private String receivedBy;

    @Column(name = "collected_date")
    private LocalDateTime collectedDate;

    @Column(name = "collected_by")
    private String collectedBy;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "quantity_unit")
    private String quantityUnit;

    @Column(name = "condition_on_receipt")
    private String conditionOnReceipt;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SampleStatus status;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
}