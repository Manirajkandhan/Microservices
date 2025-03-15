package com.abc.order_service.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abc.order_service.entity.Order;
import com.abc.order_service.entity.OrderItem;
import com.abc.order_service.exception.ResourceNotFoundException;
import com.abc.order_service.model.CustomerDTO;
import com.abc.order_service.model.InvoiceDTO;
import com.abc.order_service.model.MobileDTO;
import com.abc.order_service.model.OrderItemDTO;
import com.abc.order_service.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Order placeOrder(int customerId, List<OrderItem> orderItems) {
		
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setOrderStatus("Pending");
		order.setCustomerId(customerId);
		
		double orderTotal = 0;

		for (OrderItem item : orderItems) {

			int mobileId = item.getMobileId();
			int qty = item.getQty();

			ResponseEntity<MobileDTO> responseEntity = restTemplate.
					getForEntity("http://localhost:8080/mobile/" + mobileId, MobileDTO.class);
			MobileDTO mobile = responseEntity.getBody();
			double mobilePrice = mobile.getPrice();

			// calculate orderItem total
			double itemTotal = mobilePrice * qty;
			item.setItemTotal(itemTotal);
			
			orderTotal = orderTotal+itemTotal;
			
			item.setOrder(order);
		}

		order.setOrderAmount(orderTotal); 		
		order.setOrderItems(orderItems);

		orderRepository.save(order);

		return order;
	}

	@Override
	public Order getOrderDetails(int orderId) {
		
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new ResourceNotFoundException("Order not found");
		}

		Order order = optionalOrder.get();
		return order;
	}

	@Override
	public InvoiceDTO generateInvoice(int orderId) {
		
		Order order = getOrderDetails(orderId);
		
		InvoiceDTO invoice = new InvoiceDTO();
		invoice.setOrderId(order.getOrderId());
		invoice.setOrderDate(order.getOrderDate());
		invoice.setOrderAmount(order.getOrderAmount());
		invoice.setOrderStatus(order.getOrderStatus());
		
		int customerId = order.getCustomerId();
		
		ResponseEntity<CustomerDTO> responseEntity = restTemplate.
				getForEntity("http://localhost:8081/customer/"+customerId, CustomerDTO.class);
		
		CustomerDTO customer = responseEntity.getBody();
		
		invoice.setCustomer(customer);
		
		List<OrderItem> orderEntityItems = order.getOrderItems();
		
		List<OrderItemDTO> orderItems = new ArrayList<>();
		
		for(OrderItem orderItem:orderEntityItems) {
			
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			orderItemDTO.setItemId(orderItem.getOrderItemId());
			orderItemDTO.setItemTotal(orderItem.getItemTotal());
			orderItemDTO.setQty(orderItem.getQty());
			
			int mobileId = orderItem.getMobileId();
			
			ResponseEntity<MobileDTO> mobileResponseEntity = 
					restTemplate.getForEntity("http://localhost:8080/mobile/" + mobileId, MobileDTO.class);
			
			MobileDTO mobile = mobileResponseEntity.getBody();
					
			orderItemDTO.setMobile(mobile);
			
			orderItems.add(orderItemDTO);			
		}	
		
		invoice.setOrderItems(orderItems);
		
		return invoice;
	}
	@Override
	public List<Order> getAllOrders() {
	    return orderRepository.findAll();
	}
	
	@Override
	public Order updateOrder(Order order) {
		Optional<Order> optionalOrder = orderRepository.findById(order.getOrderId());
		if(optionalOrder.isEmpty()) {
			throw new ResourceNotFoundException("Order not existing with id: " + order.getOrderId());
		}		
		orderRepository.save(order);		
		return order;
	}

	@Override
	public void deleteOrder (int orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if (optionalOrder.isEmpty()) {
			throw new ResourceNotFoundException("Order not existing with id: " +orderId);
		}		
		Order order = optionalOrder.get();		
		orderRepository.delete(order);
	}
}