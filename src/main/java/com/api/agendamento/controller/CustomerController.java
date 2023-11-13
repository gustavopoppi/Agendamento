package com.api.agendamento.controller;

import com.api.agendamento.dto.RecordCustomerDto;
import com.api.agendamento.model.Customer;
import com.api.agendamento.service.CustomerService;
import jakarta.transaction.Transactional;
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
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid RecordCustomerDto recordCustomerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.recordCustomer(recordCustomerDto));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Customer> fazerDepois(@PathVariable @Valid Long id, RecordCustomerDto recordCustomerDto){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, recordCustomerDto));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removed data.");
    }
}