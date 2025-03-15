package com.abc.order_service.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
	
@Setter
@Getter
public class CustomerDTO {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int customerId;
		private String firstName;
		private String lastName;
		private String email;
		private String mobile;
		private String city;
}
