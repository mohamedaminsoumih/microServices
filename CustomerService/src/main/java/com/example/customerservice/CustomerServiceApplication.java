package com.example.customerservice;

import com.example.customerservice.dtos.CustomerRequestDTO;
import com.example.customerservice.service.Customerservice;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(Customerservice customerservice){
        return args -> {
            customerservice.save(new CustomerRequestDTO("C01", "Client1", "client1@mail.com"));
            customerservice.save(new CustomerRequestDTO("C02", "Client2", "client2@mail.com"));
            customerservice.save(new CustomerRequestDTO("C03", "Client3", "client3@mail.com"));
        };
    }
}
