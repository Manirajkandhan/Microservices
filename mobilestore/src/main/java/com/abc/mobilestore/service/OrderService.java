package com.abc.mobilestore.service;

import com.abc.mobilestore.entity.Order;

public interface OrderService {
	
	Order placeOrder(int customerId);
	
	Order getOrderDetails(int orderId);

}
