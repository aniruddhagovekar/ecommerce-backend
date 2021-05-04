package com.tomtom.ecommerce.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tomtom.ecommerce.data.model.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="payment_id")
	private long id;
	
	@Column(name="transaction_id")
	private String transactionId;
	
	@Column(name="status")
	private PaymentStatus status;
}
