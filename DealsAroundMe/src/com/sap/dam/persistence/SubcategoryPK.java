package com.sap.dam.persistence;

import java.io.Serializable;

/**
 * ID class for entity: subcategory
 *
 */ 
public class SubcategoryPK  implements Serializable {   
   
	         
	private int categoryId;         
	private int subcategoryId;
	private static final long serialVersionUID = 1L;

	public SubcategoryPK() {}

	

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	

	public int getSubcategoryId() {
		return this.subcategoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof SubcategoryPK)) {
			return false;
		}
		SubcategoryPK other = (SubcategoryPK) o;
		return true
			&& getCategoryId() == other.getCategoryId()
			&& getSubcategoryId() == other.getSubcategoryId();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getCategoryId();
		result = prime * result + getSubcategoryId();
		return result;
	}
   
   
}
