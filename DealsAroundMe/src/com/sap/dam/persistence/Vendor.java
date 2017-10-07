package com.sap.dam.persistence;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vendor
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "AllVendors", query = "select u from Vendor u"),
	@NamedQuery(name = "VendorByLatitude", 
	query = "SELECT p FROM Vendor p WHERE p.latitude > :latitudeLOW AND p.latitude < :latitudeHIGH "
			+ "AND p.longitude >:longitudeLOW AND p.longitude <:longitudeHIGH"),
	@NamedQuery(name = "VendorByArea", 
			query = "SELECT p FROM Vendor p WHERE p.area = :area")
			})

public class Vendor implements Serializable {

	@Id
	private int vendorId;
	@Basic
	@Column(length=32)
	private String vendorName;
	@Basic
	@Column(length=16)
	private String city;
	@Basic
	@Column(length=128)
	private String area;
	@Basic
	@Column(length=16)	
	private double latitude;
	@Basic
	@Column(length=16)
	private double longitude;
	private static final long serialVersionUID = 1L;
	
    
	@Basic
	@Column(length=15)
	private String phone;
	
	@Basic
	@Column(length=50)
	private String username;
	
	@Basic
	@Column(length=50)
	private String password;

	public Vendor() {
		super();
	}   
	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}   
	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}   
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}   
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}   
	public double getLatitude() {
		return this.latitude;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}   
	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
   
}
