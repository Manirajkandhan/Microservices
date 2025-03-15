package com.abc.order_service.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class MobileDTO {
	
	private int mobileId;
	private String mobileName;
	private double price;
	private LocalDate mfd;
	private String companyName;
}