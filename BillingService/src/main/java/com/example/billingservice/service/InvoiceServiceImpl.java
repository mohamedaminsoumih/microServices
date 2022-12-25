package com.example.billingservice.service;

import com.example.billingservice.dtos.InvoiceRequestDTO;
import com.example.billingservice.dtos.InvoiceResponseDTO;
import com.example.billingservice.entities.Invoice;
import com.example.billingservice.exceptions.CustomerNotFoundException;
import com.example.billingservice.mappers.InvoiceMapper;
import com.example.billingservice.model.Customer;
import com.example.billingservice.openfeign.CustomerRestClient;
import com.example.billingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;


    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;

    }

    @Override
    public List<InvoiceResponseDTO> allInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice: invoices) {
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerID());
            invoice.setCustomer(customer);
        }
        return invoices.stream().map(invoice -> invoiceMapper.fromInvoice(invoice)).collect(Collectors.toList());
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO requestDTO) {
        Customer customer = null;
        try {
            customer = customerRestClient.getCustomer(requestDTO.getCustomerID());
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Customer Not found !!!");
        }

        Invoice invoice = invoiceMapper.fromInvoiceRequestDTO(requestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice save = invoiceRepository.save(invoice);
        save.setCustomer(customer);
        return invoiceMapper.fromInvoice(save);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceID) {
        Invoice invoice = invoiceRepository.findById(invoiceID).get();
        Customer customer = customerRestClient.getCustomer(invoice.getCustomerID());
        invoice.setCustomer(customer);
        invoice.setCustomerID(customer.getId());
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomers(String customerID) {
        List<Invoice> invoices = invoiceRepository.findByCustomerID(customerID);
        for (Invoice invoice: invoices) {
            Customer customer = customerRestClient.getCustomer(customerID);
            invoice.setCustomer(customer);
        }
        return invoices.stream().map(invoice -> invoiceMapper.fromInvoice(invoice)).collect(Collectors.toList());
    }
}
