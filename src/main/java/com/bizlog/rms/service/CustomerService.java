package com.bizlog.rms.service;

import com.bizlog.rms.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<Customer> validateAndGetCustomer(Long id);

    Flux<Customer> getCustomers();

    Mono<Customer> saveCustomer(Customer customer);

    Mono<Void> deleteCustomer(Customer customer);
}
