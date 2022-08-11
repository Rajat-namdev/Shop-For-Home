package com.c2_g4_discount_ms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
	
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column
	private String coupon;
	
	@Column(name="status")
	private Long status;
	
	@Column(name="user_email")
	private String email;
	

}
