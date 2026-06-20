package com.pro.slims.dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long customerId;

    private String customerCode;

    private String customerName;

    private String contactPerson;

    private String email;

    private String mobile;

    private String address;

    private String status;
}