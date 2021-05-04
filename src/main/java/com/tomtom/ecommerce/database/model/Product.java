package com.tomtom.ecommerce.database.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="product")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "price")
    private double price;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "overview")
    private String overview;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "status")
    private int status;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

}
