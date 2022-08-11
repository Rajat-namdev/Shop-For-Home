package com.wipro.springboot.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "order_main")
@DynamicUpdate
public class Order implements Serializable {
	private static final long serialVersionUID = -3819883511505235030L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
	private Set<ProductInOrder> products = new HashSet<>();

	private String buyerEmail;

	private String buyerName;

	private String buyerPhone;

	private String buyerAddress;

	private BigDecimal orderAmount;

	@ColumnDefault("0")
	private Integer orderStatus;

	@CreationTimestamp
	private LocalDateTime createTime;

	@UpdateTimestamp
	private LocalDateTime updateTime;

	public Order(User buyer) {
		this.buyerEmail = buyer.getEmail();
		this.buyerName = buyer.getName();
		this.buyerPhone = buyer.getPhone();
		this.buyerAddress = buyer.getAddress();
		this.orderAmount = buyer.getCart().getProducts().stream()
				.map(item -> item.getProductPrice().multiply(new BigDecimal(item.getCount()))).reduce(BigDecimal::add)
				.orElse(new BigDecimal(0));
		this.orderStatus = 0;

	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Set<ProductInOrder> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductInOrder> products) {
		this.products = products;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Order() {

	}
}
