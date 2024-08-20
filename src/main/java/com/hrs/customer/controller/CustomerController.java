package com.hrs.customer.controller;

import com.hrs.customer.dto.CustomerDto;
import com.hrs.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/allCustomers")
    public List<CustomerDto> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable String id) {
        log.info("Get Customer By Id {} ", id);
        return customerService.getCustomerById(id);
    }

    @PostMapping("/saveCustomer")
    public CustomerDto saveCustomer(@RequestBody CustomerDto CustomerDto) {
        log.info("Req for Save Customer {} ", CustomerDto);
        return customerService.createCustomer(CustomerDto);
    }

    @PostMapping("/updateCustomer")
    public CustomerDto updateCustomer(@RequestBody CustomerDto CustomerDto) {
        log.info("Req for Update Customer {} ", CustomerDto);
        return customerService.updateCustomer(CustomerDto);
    }




}
