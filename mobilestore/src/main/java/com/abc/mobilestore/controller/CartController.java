package com.abc.mobilestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.mobilestore.entity.Cart;
import com.abc.mobilestore.model.CartItemRequest;
import com.abc.mobilestore.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/addItem")
	public ResponseEntity<Cart> addToCart(@RequestBody CartItemRequest cartItemReq) {
		
		Cart cart = cartService.addMobileToCart(cartItemReq.getCartId(), cartItemReq.getMobileId(), cartItemReq.getQty());
		
		return new ResponseEntity<>(cart,HttpStatus.ACCEPTED);
	}
	
}
