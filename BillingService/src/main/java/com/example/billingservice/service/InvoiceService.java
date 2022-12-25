package com.example.billingservice.service;

import com.example.billingservice.dtos.InvoiceRequestDTO;
import com.example.billingservice.dtos.InvoiceResponseDTO;

import java.util.List;

public interface InvoiceService {
    List<InvoiceResponseDTO> allInvoices();
    InvoiceResponseDTO save(InvoiceRequestDTO requestDTO);
    InvoiceResponseDTO getInvoice(String invoiceID);
    List<InvoiceResponseDTO> invoicesByCustomers(String customerID);

}
