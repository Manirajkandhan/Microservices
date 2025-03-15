package com.abc.order_service.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class InvoiceDTO {	
	private int orderId;
	private LocalDate orderDate;
	private double orderAmount;
	private String orderStatus;	
	private List<OrderItemDTO> orderItems;	
	private CustomerDTO customer;
}