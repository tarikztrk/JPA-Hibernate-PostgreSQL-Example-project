package com.example.dboperationproject.controller;

import com.example.dboperationproject.exception.ResourceNotFoundException;
import com.example.dboperationproject.model.Customer;
import com.example.dboperationproject.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
    
    @GetMapping("/customers/{id}")
    public Optional<Customer> getCustomers(Long id) {
        return customerRepository.findById(id);
    }


    @PostMapping("/addCustomer")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/customer/{customerId}")
    public Customer updateCustomer(@PathVariable Long customerId,
                                   @Valid @RequestBody Customer customerReq) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                	customer.setBirthDate(customerReq.getBirthDate());
                	customer.setBirthPlace(customerReq.getBirthPlace());
                	customer.setCustomerName(customerReq.getCustomerName());
                	customer.setEmail(customerReq.getEmail());
                    return customerRepository.save(customer);
                }).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + customerId));
    }
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("customer not found with id " + customerId));
    }
}
