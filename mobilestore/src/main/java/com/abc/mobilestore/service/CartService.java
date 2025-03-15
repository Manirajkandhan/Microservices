package com.abc.mobilestore.service;

import com.abc.mobilestore.entity.Cart;

public interface CartService {

	Cart addMobileToCart(int cartId,int mobileId,int qty);
	
	//Cart removeMobileFromCart(int cartId,int mobileId);
	
	//Cart updateQtyInCart(int cartId,int mobileId,int qty);
	
}
