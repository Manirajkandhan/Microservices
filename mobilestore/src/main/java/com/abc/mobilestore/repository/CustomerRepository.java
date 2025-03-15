package com.abc.mobilestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.mobilestore.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

}
