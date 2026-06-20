package com.pro.slims.service.impl;

import com.pro.slims.dto.CustomerDTO;
import com.pro.slims.entity.Customer;
import com.pro.slims.enums.CustomerStatus;
import com.pro.slims.exception.ResourceNotFoundException;
import com.pro.slims.repository.CustomerRepository;
import com.pro.slims.service.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {

        Customer customer = Customer.builder()
                .customerCode(dto.getCustomerCode())
                .customerName(dto.getCustomerName())
                .contactPerson(dto.getContactPerson())
                .email(dto.getEmail())
                .mobile(dto.getMobile())
                .address(dto.getAddress())
                .status(CustomerStatus.ACTIVE)
                .createdDate(LocalDateTime.now())
                .build();

        return convertToDTO(repository.save(customer));
    }

    @Override
    public CustomerDTO getCustomer(Long id) {

        Customer customer =
                repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return convertToDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {

        Customer customer =
                repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customer.setCustomerName(dto.getCustomerName());
        customer.setContactPerson(dto.getContactPerson());
        customer.setEmail(dto.getEmail());
        customer.setMobile(dto.getMobile());
        customer.setAddress(dto.getAddress());

        return convertToDTO(repository.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {

        Customer customer =
                repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        repository.delete(customer);
    }

    private CustomerDTO convertToDTO(Customer customer) {

        CustomerDTO dto = new CustomerDTO();

        dto.setCustomerId(customer.getCustomerId());
        dto.setCustomerCode(customer.getCustomerCode());
        dto.setCustomerName(customer.getCustomerName());
        dto.setContactPerson(customer.getContactPerson());
        dto.setEmail(customer.getEmail());
        dto.setMobile(customer.getMobile());
        dto.setAddress(customer.getAddress());
        dto.setStatus(customer.getStatus().name());

        return dto;
    }
}