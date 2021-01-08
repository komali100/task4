package com.mindtree.ShoppingCartApp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart implements Serializable, Comparable<Cart> {

	/**
	 * serialVersionUID = 1L
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int cartId;

	@OneToOne
	private User user;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<StockQuantity> stockQuantityInCart;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
		result = prime * result + ((stockQuantityInCart == null) ? 0 : stockQuantityInCart.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Cart other = (Cart) obj;
		if (cartId != other.cartId)
			return false;
		if (stockQuantityInCart == null) {
			if (other.stockQuantityInCart != null)
				return false;
		} else if (!stockQuantityInCart.equals(other.stockQuantityInCart))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<StockQuantity> getStockQuantityInCart() {
		return stockQuantityInCart;
	}

	public void setStockQuantityInCart(List<StockQuantity> stockQuantityInCart) {
		this.stockQuantityInCart = stockQuantityInCart;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(Cart cart) {
      return this.cartId-cart.cartId;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", user=" + user + ", stockQuantityInCart=" + stockQuantityInCart + "]";
	}

}

