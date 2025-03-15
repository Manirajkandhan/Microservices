package com.abc.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.customer_service.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

}