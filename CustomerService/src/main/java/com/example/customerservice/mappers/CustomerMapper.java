package com.example.customerservice.mappers;

import com.example.customerservice.dtos.CustomerRequestDTO;
import com.example.customerservice.dtos.CustomerResponseDTO;
import com.example.customerservice.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO CustomerToCustomerResponseDTO(Customer customer);
    Customer CustomerRequestDTOToCustomer(CustomerRequestDTO customer);

}
