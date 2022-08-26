package com.example.dboperationproject.managers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dboperationproject.exception.ResourceNotFoundException;
import com.example.dboperationproject.managers.Interfaces.IDbOperations;
import com.example.dboperationproject.model.Customer;
import com.example.dboperationproject.repository.CustomerRepository;

public class CustomerManager //implements IDbOperations<Customer> 
{

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	public Optional<Customer> getById(Long id) {
		return customerRepository.findById(id);
	}

	public Customer create(Customer entity) {
		return customerRepository.save(entity);
	}

	public Customer update(Long id, Customer entity) {
		return customerRepository.findById(id).map(customer -> {
			customer.setBirthDate(entity.getBirthDate());
			customer.setBirthPlace(entity.getBirthPlace());
			customer.setCustomerName(entity.getCustomerName());
			customer.setEmail(entity.getEmail());
			return customerRepository.save(customer);
		}).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
	}

	public String delete(Long id) {
		try {
			Optional<Customer> customer =  customerRepository.findById(id);
			customerRepository.delete(customer.get());
			return "Silme işlemi başarılı";
		} catch (Exception e) {
			return "Silme işlemi başarısız";
		}
	}

}
