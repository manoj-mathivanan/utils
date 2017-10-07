package com.sap.dam.persistence;

import java.io.Serializable;

/**
 * ID class for entity: item
 *
 */ 
public class ItemPK  implements Serializable {   
   
	         
	private int vendorId;         
	private int categoryID;         
	private int itemId;
	private static final long serialVersionUID = 1L;

	public ItemPK() {}

	

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	

	public int getCategoryID() {
		return this.categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof ItemPK)) {
			return false;
		}
		ItemPK other = (ItemPK) o;
		return true
			&& getVendorId() == other.getVendorId()
			&& getCategoryID() == other.getCategoryID()
			&& getItemId() == other.getItemId();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getVendorId();
		result = prime * result + getCategoryID();
		result = prime * result + getItemId();
		return result;
	}
   
   
}
