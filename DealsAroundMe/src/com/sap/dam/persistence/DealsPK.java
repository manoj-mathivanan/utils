package com.sap.dam.persistence;

import java.io.Serializable;

/**
 * ID class for entity: Deals
 *
 */ 
public class DealsPK  implements Serializable {   
   
	         
	private int vendorId;         
	private int dealId;
	private static final long serialVersionUID = 1L;

	public DealsPK() {}

	

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	

	public int getDealId() {
		return this.dealId;
	}

	public void setDealId(int dealId) {
		this.dealId = dealId;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof DealsPK)) {
			return false;
		}
		DealsPK other = (DealsPK) o;
		return true
			&& getVendorId() == other.getVendorId()
			&& getDealId() == other.getDealId();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getVendorId();
		result = prime * result + getDealId();
		return result;
	}
   
   
}
