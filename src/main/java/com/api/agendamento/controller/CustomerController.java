package com.api.agendamento.controller;

import com.api.agendamento.dto.customer.ReadCustomerDto;
import com.api.agendamento.dto.customer.RecordCustomerDto;
import com.api.agendamento.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<ReadCustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadCustomerDto> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<ReadCustomerDto> createCustomer(@RequestBody @Valid RecordCustomerDto recordCustomerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.recordCustomer(recordCustomerDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ReadCustomerDto> fazerDepois(@PathVariable @Valid Long id, RecordCustomerDto recordCustomerDto){
        return ResponseEntity.ok((customerService.updateCustomer(id, recordCustomerDto)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Removed data.");
    }
}