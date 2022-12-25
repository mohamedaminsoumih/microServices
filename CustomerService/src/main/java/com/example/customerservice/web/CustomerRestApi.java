package com.example.customerservice.web;

import com.example.customerservice.dtos.CustomerRequestDTO;
import com.example.customerservice.dtos.CustomerResponseDTO;
import com.example.customerservice.service.Customerservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CustomerRestApi {
    private Customerservice customerservice;

    public CustomerRestApi(Customerservice customerservice) {
        this.customerservice = customerservice;
    }

    @GetMapping(path="/customers")
    public List<CustomerResponseDTO> allCustomers(){
        return customerservice.listCustomers();
    }

    @PostMapping(path = "/customers")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
        customerRequestDTO.setId(UUID.randomUUID().toString());
        return customerservice.save(customerRequestDTO);
    }

    @GetMapping(path="/customers/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id){
        return customerservice.getCustomer(id);
    }
}
