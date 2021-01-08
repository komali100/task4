package com.mindtree.ShoppingCartApp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "productCategory", discriminatorType = DiscriminatorType.STRING)
public class Product implements Serializable, Comparable<Product> {

	/**
	 * serialVersionUID = 1L
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int productId;
	private String productName;
	private float productPrice;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<StockQuantity> stockQuantityInProduct;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + Float.floatToIntBits(productPrice);
		result = prime * result + ((stockQuantityInProduct == null) ? 0 : stockQuantityInProduct.hashCode());
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
		Product other = (Product) obj;
		if (productId != other.productId)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (Float.floatToIntBits(productPrice) != Float.floatToIntBits(other.productPrice))
			return false;
		if (stockQuantityInProduct == null) {
			if (other.stockQuantityInProduct != null)
				return false;
		} else if (!stockQuantityInProduct.equals(other.stockQuantityInProduct))
			return false;
		return true;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public List<StockQuantity> getStockQuantityInProduct() {
		return stockQuantityInProduct;
	}

	public void setStockQuantityInProduct(List<StockQuantity> stockQuantityInProduct) {
		this.stockQuantityInProduct = stockQuantityInProduct;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(Product product) {
		return this.productId -product.productId;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", stockQuantityInProduct=" + stockQuantityInProduct + "]";
	}

}

