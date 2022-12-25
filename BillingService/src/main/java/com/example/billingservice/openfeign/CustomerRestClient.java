package com.example.billingservice.openfeign;

import com.example.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping(path="/api/customers/{id}")
    public Customer getCustomer(@PathVariable(name="id") String id);
    @GetMapping(path="/api/customers")
    public List<Customer> allCustomers();

}
