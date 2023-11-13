package com.api.agendamento.service;

import com.api.agendamento.dto.RecordCustomerDto;
import com.api.agendamento.model.Customer;
import com.api.agendamento.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerByIdThrowIFIsEmpty(id);
    }

    public Customer recordCustomer(RecordCustomerDto recordCustomerDto) {
        return addAndSaveCustomer(new Customer(), recordCustomerDto);
    }

    public void deleteCustomer(Long id) {
        customerRepository.delete(customerByIdThrowIFIsEmpty(id));
    }

    public Customer updateCustomer(Long id, RecordCustomerDto recordCustomerDto) {
        return addAndSaveCustomer(customerByIdThrowIFIsEmpty(id), recordCustomerDto);
    }

    private Customer customerByIdThrowIFIsEmpty(Long id) {
        Optional<Customer> customerById = customerRepository.findById(id);
        if (customerById.isEmpty())
            throw new RuntimeException("Customer no found.");

        return customerById.get();
    }

    private Customer addAndSaveCustomer(Customer customer, RecordCustomerDto recordCustomerDto) {
        BeanUtils.copyProperties(recordCustomerDto, customer);
        return customerRepository.save(customer);
    }
}