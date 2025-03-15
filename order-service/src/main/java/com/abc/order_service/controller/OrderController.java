package com.abc.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.order_service.entity.Order;
import com.abc.order_service.entity.OrderItem;
import com.abc.order_service.model.InvoiceDTO;
import com.abc.order_service.service.OrderService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create/{customerId}")
	public ResponseEntity<Order> createOrder(@PathVariable int customerId,@RequestBody List<OrderItem> orderItems) {
		
		Order order = orderService.placeOrder(customerId, orderItems);
		
		return new ResponseEntity<>(order,HttpStatus.CREATED);
	}
	@GetMapping("/all")
	public ResponseEntity<List<Order>> fetchAllOrders() {
		List<Order> orders = orderService.getAllOrders();
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderDetails(@PathVariable int orderId) {
		
		Order order = orderService.getOrderDetails(orderId);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@GetMapping("/invoice")
	public ResponseEntity<InvoiceDTO> createInvoice(@RequestParam("orderId") int orderId) {
		
		InvoiceDTO invoiceDto = orderService.generateInvoice(orderId);
		
		return new ResponseEntity<>(invoiceDto,HttpStatus.OK);

	}
	@PutMapping("/update")
	public ResponseEntity<Order> fetchOrderDetails(@RequestBody Order order) {
	 orderService.updateOrder(order);
		ResponseEntity<Order> responseEntity = new ResponseEntity<>(order,HttpStatus.OK);
		return responseEntity;
	}
	@DeleteMapping("/{orderId}")
	public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {	
		
		orderService.deleteOrder(orderId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);		
		return responseEntity;
	}
}