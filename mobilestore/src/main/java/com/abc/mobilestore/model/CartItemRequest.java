package com.abc.mobilestore.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CartItemRequest {

	private int cartId;
	private int mobileId;
	private int qty;
}
