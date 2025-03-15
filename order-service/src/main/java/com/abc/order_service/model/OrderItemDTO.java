package com.abc.order_service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {


	private int itemId;
    private MobileDTO mobile;
    private int qty;
    private double itemTotal;
    
}
