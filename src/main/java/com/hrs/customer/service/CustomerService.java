package com.hrs.customer.service;

import com.hrs.customer.dto.CustomerDto;
import com.hrs.customer.exception.ValidationException;
import com.hrs.customer.model.Customer;
import com.hrs.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public List<CustomerDto> getAllCustomers() {
		return customerRepository.findAll()
				.stream()
				.map(customer -> modelMapper.map(customer, CustomerDto.class))
				.toList();
	}

	public CustomerDto getCustomerById(String customerId) {
		return customerRepository.findById(customerId)
				.map(customer -> modelMapper.map(customer, CustomerDto.class))
				.orElseThrow(() -> new ValidationException("Customer not found"));
	}

	public CustomerDto createCustomer(CustomerDto customerDto) {

		if (customerRepository.existsById(customerDto.getCustomerId())) {
			throw new ValidationException("Customer Id Already Present");
		}

		Customer customer = modelMapper.map(customerDto, Customer.class);
		customer = customerRepository.save(customer);
		log.info("Customer created with id {} ", customer.getCustomerId());
		return modelMapper.map(customer, CustomerDto.class);
	}

	public CustomerDto updateCustomer(CustomerDto customerDto) {
		var existingCustomerOpt = customerRepository.findById(customerDto.getCustomerId());

		if (existingCustomerOpt.isEmpty()) {
			throw new ValidationException("Customer is not Present");
		}

		var customer = existingCustomerOpt.get();
		modelMapper.map(customerDto, customer);
		customer = customerRepository.save(customer);
		log.info("Customer updated with id {} ", customer.getCustomerId());
		return modelMapper.map(customer, CustomerDto.class);
	}


	

}