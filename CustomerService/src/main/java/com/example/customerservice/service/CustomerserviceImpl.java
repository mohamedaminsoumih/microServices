package com.example.customerservice.service;

import com.example.customerservice.dtos.CustomerRequestDTO;
import com.example.customerservice.dtos.CustomerResponseDTO;
import com.example.customerservice.entities.Customer;
import com.example.customerservice.mappers.CustomerMapper;
import com.example.customerservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerserviceImpl implements Customerservice {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerserviceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.CustomerRequestDTOToCustomer(customerRequestDTO);
        Customer save = customerRepository.save(customer);
        CustomerResponseDTO response = customerMapper.CustomerToCustomerResponseDTO(save);
        return response;
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.CustomerRequestDTOToCustomer(customerRequestDTO);
        Customer updated = customerRepository.save(customer);
        return customerMapper.CustomerToCustomerResponseDTO(updated);
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {
        Customer customer = customerRepository.findById(id).get();
        return customerMapper.CustomerToCustomerResponseDTO(customer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOS = customers.stream()
                .map(customer -> customerMapper.CustomerToCustomerResponseDTO(customer))
                .collect(Collectors.toList());
        return customerResponseDTOS;
    }
}
