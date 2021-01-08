package com.mindtree.ShoppingCartApp.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class StockQuantity implements Serializable, Comparable<StockQuantity> {

	/**
	 * serialVersionUID = 1L
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator = "stockQuantity_generator")
	@SequenceGenerator(name="stockQuantity_generator", sequenceName = "StockQuantity_seq")
	private int stockId;

	private int productQuantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cartId", nullable = false)
	@JsonBackReference
	private Cart cart;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productId", nullable = false)
	private Product product;

	private float productTotalPrice;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + productQuantity;
		result = prime * result + Float.floatToIntBits(productTotalPrice);
		result = prime * result + stockId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockQuantity other = (StockQuantity) obj;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (productQuantity != other.productQuantity)
			return false;
		if (Float.floatToIntBits(productTotalPrice) != Float.floatToIntBits(other.productTotalPrice))
			return false;
		if (stockId != other.stockId)
			return false;
		return true;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getProductTotalPrice() {
		return productTotalPrice;
	}

	public void setProductTotalPrice(float productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(StockQuantity stockQuantity) {
		return this.stockId -stockQuantity.stockId;
	}

	@Override
	public String toString() {
		return "StockQuantity [stockId=" + stockId + ", productQuantity=" + productQuantity + ", cart=" + cart
				+ ", product=" + product + ", productTotalPrice=" + productTotalPrice + "]";
	}

}
