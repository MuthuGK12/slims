package com.pro.slims.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.pro.slims.enums.CustomerStatus;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_code", unique = true)
    private String customerCode;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}