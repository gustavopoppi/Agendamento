package com.api.agendamento.service;

import com.api.agendamento.dto.customer.ReadCustomerDto;
import com.api.agendamento.dto.customer.RecordCustomerDto;
import com.api.agendamento.model.Customer;
import com.api.agendamento.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<ReadCustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::mapCustomerToReadCustomerDto).toList();
    }

    @Transactional(readOnly = true)
    public ReadCustomerDto getCustomerById(Long id) {
        return mapCustomerToReadCustomerDto(customerByIdThrowIFIsEmpty(id));
    }

    @Transactional
    public ReadCustomerDto recordCustomer(RecordCustomerDto recordCustomerDto) {
        return addAndSaveCustomer(new Customer(), recordCustomerDto);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional
    public ReadCustomerDto updateCustomer(Long id, RecordCustomerDto recordCustomerDto) {
        return addAndSaveCustomer(customerByIdThrowIFIsEmpty(id), recordCustomerDto);
    }

    private Customer customerByIdThrowIFIsEmpty(Long id) {
        Optional<Customer> customerById = customerRepository.findById(id);
        if (customerById.isEmpty())
            throw new RuntimeException("Customer not found.");

        return customerById.get();
    }

    private ReadCustomerDto addAndSaveCustomer(Customer customer, RecordCustomerDto recordCustomerDto) {
        BeanUtils.copyProperties(recordCustomerDto, customer);
        customerRepository.save(customer);
        return mapCustomerToReadCustomerDto(customer);
    }

    private ReadCustomerDto mapCustomerToReadCustomerDto(Customer customer) {
        return new ReadCustomerDto(customer.getId(), customer.getNome(), customer.getEmail(), customer.getTelefone());
    }
}