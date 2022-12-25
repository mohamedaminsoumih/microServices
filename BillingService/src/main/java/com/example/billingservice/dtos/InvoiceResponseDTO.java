package com.example.billingservice.dtos;

import com.example.billingservice.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponseDTO {
    private String id;
    private Date date;
    private double amount;
    private Customer customer;
}
