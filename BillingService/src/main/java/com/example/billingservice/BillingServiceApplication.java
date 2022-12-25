package com.example.billingservice;

import com.example.billingservice.dtos.InvoiceRequestDTO;
import com.example.billingservice.service.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InvoiceService service){
        return args -> {
            service.save(new InvoiceRequestDTO(12000, "C01"));
            service.save(new InvoiceRequestDTO(12500, "C01"));
            service.save(new InvoiceRequestDTO(188800, "C02"));
            service.save(new InvoiceRequestDTO(190, "C02"));
            service.save(new InvoiceRequestDTO(100, "C03"));
        };
    }
}
