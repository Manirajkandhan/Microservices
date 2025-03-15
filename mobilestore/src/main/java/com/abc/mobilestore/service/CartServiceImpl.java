package com.abc.mobilestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.mobilestore.entity.Cart;
import com.abc.mobilestore.entity.CartItem;
import com.abc.mobilestore.entity.Mobile;
import com.abc.mobilestore.exception.ResourceNotFoundException;
import com.abc.mobilestore.repository.CartItemRepository;
import com.abc.mobilestore.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private MobileService mobileService;
	
	@Override
	public Cart addMobileToCart(int cartId, int mobileId, int qty) {
		
		Mobile mobile = mobileService.getMobileById(mobileId);		
		double price = mobile.getPrice();		
		double itemTotal = qty*price;
		
		Optional<Cart> optionalCart = cartRepository.findById(cartId);
		
		if(optionalCart.isEmpty()) {
			throw new ResourceNotFoundException("Cart not found with id: "+cartId);
		}
		
		Cart cart = optionalCart.get();
		
		CartItem cartItem = new CartItem();
//		cartItem.setCartItemId(0); //auto-incremented		
		cartItem.setMobileId(mobileId);
		cartItem.setQty(qty);
		cartItem.setItemTotal(itemTotal);
		cartItem.setCart(cart);
		
		cartItemRepository.save(cartItem);
		
		//update cart total
		
		double cartTotal = 0;
		
		List<CartItem> cartItems = cart.getCartItems();
		
		for(CartItem item:cartItems) {			
			double total = item.getItemTotal();			
			cartTotal = cartTotal+total;
		}		
		cart.setCartTotal(cartTotal);	
		
		cartRepository.save(cart);
		
		return cart;
	}
}
