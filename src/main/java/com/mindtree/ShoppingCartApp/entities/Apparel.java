package com.mindtree.ShoppingCartApp.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@DiscriminatorValue("Apparel")
public class Apparel extends Product {

	/**
	 * serialVersionUID = 1L
	 */
	private static final long serialVersionUID = 1L;

	private String apparelType;
	private String apparelBrand;
	private String apparelDesign;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((apparelBrand == null) ? 0 : apparelBrand.hashCode());
		result = prime * result + ((apparelDesign == null) ? 0 : apparelDesign.hashCode());
		result = prime * result + ((apparelType == null) ? 0 : apparelType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apparel other = (Apparel) obj;
		if (apparelBrand == null) {
			if (other.apparelBrand != null)
				return false;
		} else if (!apparelBrand.equals(other.apparelBrand))
			return false;
		if (apparelDesign == null) {
			if (other.apparelDesign != null)
				return false;
		} else if (!apparelDesign.equals(other.apparelDesign))
			return false;
		if (apparelType == null) {
			if (other.apparelType != null)
				return false;
		} else if (!apparelType.equals(other.apparelType))
			return false;
		return true;
	}

	public String getApparelType() {
		return apparelType;
	}

	public void setApparelType(String apparelType) {
		this.apparelType = apparelType;
	}

	public String getApparelBrand() {
		return apparelBrand;
	}

	public void setApparelBrand(String apparelBrand) {
		this.apparelBrand = apparelBrand;
	}

	public String getApparelDesign() {
		return apparelDesign;
	}

	public void setApparelDesign(String apparelDesign) {
		this.apparelDesign = apparelDesign;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Apparel [apparelType=" + apparelType + ", apparelBrand=" + apparelBrand + ", apparelDesign="
				+ apparelDesign + "]";
	}

}

