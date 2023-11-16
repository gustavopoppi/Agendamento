package com.api.agendamento.service;

import com.api.agendamento.dto.customer.ReadCustomerDto;
import com.api.agendamento.dto.customer.RecordCustomerDto;
import com.api.agendamento.dto.professional.RecordProfessionalDto;
import com.api.agendamento.model.Customer;
import com.api.agendamento.repository.CustomerRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class CustomerServiceTest {

    private CustomerService service;
    @Mock
    private CustomerRepository customerRepository;
    private Customer mockCustomer;
    private final Validator validor = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.service = new CustomerService(customerRepository);
        mockCustomer = Customer.builder()
                               .id(1L)
                               .nome("NOME TESTE")
                               .email("TESTE@GMAIL.COM")
                               .telefone("4432228888")
                               .build();
    }

    @Test
    void getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(mockCustomer);
        Mockito.when(customerRepository.findAll()).thenReturn(customers);

        List<ReadCustomerDto> readCustomersDto = service.getAllCustomers();
        Assertions.assertEquals(readCustomersDto.size(), 1);

        ReadCustomerDto firstCustomerDto = readCustomersDto.get(0);
        Assertions.assertEquals(firstCustomerDto.nome(), "NOME TESTE");
        Assertions.assertEquals(firstCustomerDto.email(), "TESTE@GMAIL.COM");
        Assertions.assertEquals(firstCustomerDto.telefone(), "4432228888");
    }

    @Test
    void getCustomerById() {
        Mockito.when(customerRepository.findById(mockCustomer.getId())).thenReturn(Optional.of(mockCustomer));

        ReadCustomerDto readCustomer = service.getCustomerById(mockCustomer.getId());
        Assertions.assertNotNull(readCustomer, "It must have at least one element.");

        Assertions.assertEquals(readCustomer.nome(), "NOME TESTE");
        Assertions.assertEquals(readCustomer.email(), "TESTE@GMAIL.COM");
        Assertions.assertEquals(readCustomer.telefone(), "4432228888");
    }

    @Test
    void recordCustomer() {
        mockCustomer.setId(null);
        RecordCustomerDto recordCustomerDto = new RecordCustomerDto(mockCustomer.getNome(), mockCustomer.getEmail(), mockCustomer.getTelefone());

        ReadCustomerDto readCustomer = service.recordCustomer(recordCustomerDto);

        Assertions.assertNotNull(readCustomer, "It must have at least one element.");

        Assertions.assertEquals(readCustomer.nome(), "NOME TESTE");
        Assertions.assertEquals(readCustomer.email(), "TESTE@GMAIL.COM");
        Assertions.assertEquals(readCustomer.telefone(), "4432228888");
        Mockito.verify(customerRepository).save(mockCustomer);
    }

    @Test
    void deleteCustomer() {
        Assertions.fail("Dev");
    }

    @Test
    void updateCustomer() {
        Assertions.fail("Dev");
    }
}