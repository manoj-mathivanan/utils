package com.sap.dam.persistence;

import java.io.Serializable;

/**
 * ID class for entity: category
 *
 */ 
public class CategoryPK  implements Serializable {   
   
	         
	private int vendorId;         
	private int categoryId;
	private static final long serialVersionUID = 1L;

	public CategoryPK() {}

	

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof CategoryPK)) {
			return false;
		}
		CategoryPK other = (CategoryPK) o;
		return true
			&& getVendorId() == other.getVendorId()
			&& getCategoryId() == other.getCategoryId();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getVendorId();
		result = prime * result + getCategoryId();
		return result;
	}
   
   
}
