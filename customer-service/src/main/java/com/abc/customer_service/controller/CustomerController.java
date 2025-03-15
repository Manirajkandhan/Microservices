package com.abc.customer_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.customer_service.entity.Customer;
import com.abc.customer_service.service.CustomerService;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	//@Autowired
	//private Environment env;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/save")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return new ResponseEntity<>(customer,HttpStatus.CREATED);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> fetchCustomerDetails(@PathVariable int customerId) {
		//System.out.println("Customer Service Running on Port: "+env.getProperty("local.server.port"));
		Customer customer = customerService.getCustomerById(customerId);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Customer>> fetchAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers,HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<Customer> fetchCustomerDetails(@RequestBody Customer customer) {
		customerService.updateCustomer(customer);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<>(customer,HttpStatus.OK);
		return responseEntity;
	}
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) {	
		
		customerService.deleteCustomer(customerId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);		
		return responseEntity;
	}
}
