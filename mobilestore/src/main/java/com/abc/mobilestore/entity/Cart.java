package com.abc.mobilestore.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name="cart_tbl")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private double cartTotal;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.DETACH)
	private List<CartItem> cartItems;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
}
