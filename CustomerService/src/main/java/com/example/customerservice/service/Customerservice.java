package com.example.customerservice.service;

import com.example.customerservice.dtos.CustomerRequestDTO;
import com.example.customerservice.dtos.CustomerResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface Customerservice {
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO getCustomer(String id);
    List<CustomerResponseDTO> listCustomers();
}
