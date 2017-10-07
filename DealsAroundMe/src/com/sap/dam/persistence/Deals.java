package com.sap.dam.persistence;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Deals
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "AllDeals", query = "select u from Deals u"),
	@NamedQuery(name = "DealsByVID", 
			query = "SELECT p FROM Deals p WHERE p.vendorId = :vendorId")
			})
@IdClass(DealsPK.class)
public class Deals implements Serializable {

	   
	@Id
	private int vendorId;   
	@Id
	private int dealId;
	@Basic
	@Column(length=64)
	private String dealInfo;
	@Basic
	@Column(length=128)
	private String dealDescription;
	@Basic
	@Column(length=32)
	private String type;
	private static final long serialVersionUID = 1L;

	public Deals() {
		super();
	}   
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
	public String getDealInfo() {
		return this.dealInfo;
	}

	public void setDealInfo(String dealInfo) {
		this.dealInfo = dealInfo;
	}   
	public String getDealDescription() {
		return this.dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
   
}
