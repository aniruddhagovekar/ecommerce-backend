package com.tomtom.ecommerce.database.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tomtom.ecommerce.data.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
	@OneToMany(mappedBy="order")
	private Set<CartItem> items;
	
    @Column(name = "user_id")
    private String userId;
    
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    @Column(name = "is_active")
    private Short isActive;
    
    @Column(name = "status")
    private OrderStatus status;
    
    @Column(name = "grand_total")
    private long grandTotal;
    
    @Column(name = "checkout_comment")
    private String checkoutComment;
    
    @Column(name = "customer_email")
    private String customerEmail;
    
    @Column(name = "customer_prefix")
    private String customerPrefix;
    
    @Column(name = "customer_firstname")
    private String customerFirstname;
    
    @Column(name = "customer_middlename")
    private String customerMiddlename;
    
    @Column(name = "customer_lastname")
    private String customerLastname;
    
    @Column(name = "customer_suffix")
    private String customerSuffix;
}
