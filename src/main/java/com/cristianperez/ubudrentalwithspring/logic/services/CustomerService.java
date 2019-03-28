package com.cristianperez.ubudrentalwithspring.logic.services;

import com.cristianperez.ubudrentalwithspring.logic.interfaces.CustomerRepository;
import com.cristianperez.ubudrentalwithspring.logic.models.Customer;
import com.cristianperez.ubudrentalwithspring.logic.models.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer obtainCustomerDetailsIfExists(String firstAndLastName) {
        return customerRepository.validateFirstAndLastName(firstAndLastName);
    }


}
