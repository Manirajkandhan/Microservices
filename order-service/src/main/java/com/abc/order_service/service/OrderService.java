package com.abc.order_service.service;

import java.util.List;


import com.abc.order_service.entity.Order;
import com.abc.order_service.entity.OrderItem;
import com.abc.order_service.model.InvoiceDTO;

public interface OrderService {
	
	Order placeOrder(int customerId,List<OrderItem> orderItems);
	
	Order getOrderDetails(int orderId);
	
	InvoiceDTO generateInvoice(int orderId);
	
	List<Order> getAllOrders();
	
	Order updateOrder(Order order);
	
	void deleteOrder(int orderId);

}