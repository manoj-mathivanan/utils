package com.sap.dam.persistence;

import java.io.Serializable;

/**
 * ID class for entity: Vendor
 *
 */ 
public class VendorPK  implements Serializable {   
   
	         
	private int vendorId;         
	private String vendorName;
	private String city;
	private String area;
	private double latitude;
	private double longitude;
	
	private static final long serialVersionUID = 1L;

	public VendorPK() {}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof VendorPK)) {
			return false;
		}
		VendorPK other = (VendorPK) o;
		return true
			&& getVendorId() == other.getVendorId();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getVendorId();
		return result;
	}
   
   
}
