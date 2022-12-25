package com.example.billingservice.web;

import com.example.billingservice.dtos.InvoiceRequestDTO;
import com.example.billingservice.dtos.InvoiceResponseDTO;
import com.example.billingservice.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InvoiceRestController {
    private InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path="/invoices")
    public List<InvoiceResponseDTO> allInvoices(){
        return invoiceService.allInvoices();
    }

    @GetMapping(path="/invoices/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable(name = "id") String invoiceId){
        return invoiceService.getInvoice(invoiceId);
    }

    @GetMapping(path="/invoicesByCustomer/{customerid}")
    public List<InvoiceResponseDTO> invoicesByCustomer(@PathVariable String customerid){
        return invoiceService.invoicesByCustomers(customerid);
    }

    @PostMapping(path="/invoices")
    public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO requestDTO){
        return invoiceService.save(requestDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
