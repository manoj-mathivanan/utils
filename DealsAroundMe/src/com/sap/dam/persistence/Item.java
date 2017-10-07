package com.sap.dam.persistence;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: item
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "AllItems", query = "select u from Item u"),
	@NamedQuery(name = "AllArea", query = "select distinct u.area from Item u"),
	@NamedQuery(name = "ItemByCID", query = "SELECT p FROM Item p WHERE p.categoryID = :categoryID"),
	@NamedQuery(name = "ItemByIID", query = "SELECT p FROM Item p WHERE p.itemId = :itemId"),
	@NamedQuery(name = "ItemByVID", query = "SELECT p FROM Item p WHERE p.vendorId = :vendorId")})
@IdClass(ItemPK.class)
public class Item implements Serializable {

	   
	@Id
	private int vendorId;   
	@Id
	private int categoryID; 
	@Id
	private int itemId;
	@Basic
	@Column(length=32)
	private String subCategory;
	@Basic
	@Column(length=16)
	private String category;
	@Basic
	private String vendorName;
	@Basic
	@Column(length=16)
	private double vendorLatitude;
	@Basic
	@Column(length=16)
	private double vendorLongitude;
	@Basic
	@Column(length=16)
	private String city;
	@Basic
	@Column(name= "area",length=128)
	private String area;
	@Basic
	@Column(length=32)
	private String item;
	@Basic
	@Column(length=15)
	private String phone;
	@Basic
	@Column(length=16)
	private String brand;
	@Basic
	@Column(length=128)
	private String description;
	@Basic
	@Column(length=16)
	private String price;
	@Basic
	@Column(length=16)
	private String discount;
	@Basic
	@Column(length=32)
	private String startDate;
	@Basic
	@Column(length=32)
	private String endDate;
	@Basic
	@Column(length=32)
	private String dealType;
	private static final long serialVersionUID = 1L;

	public Item() {
		super();
	}   
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
	public String getSubCategory() {
		return this.subCategory;
	}
	
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	} 

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}  
	public String getVendorName() {
		return this.vendorName;
	}

	public void setvendorName(String vendorName) {
		this.vendorName = vendorName;
	}  
	public String getItem() {
		return this.item;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setItem(String item) {
		this.item = item;
	}   
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}   
	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}   
	public String getDealType() {
		return this.dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	
	public String getstartDate() {
		return this.startDate;
	}

	public void setstartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getendDate() {
		return this.endDate;
	}

	public void setendDate(String endDate) {
		this.endDate = endDate;
	}
	
	public double getLatitude() {
		return this.vendorLatitude;
	}
	public void setLatitude(long vendorLatitude) {
		this.vendorLatitude = vendorLatitude;
	}   
	public double getLongitude() {
		return this.vendorLongitude;
	}

	public void setLongitude(long vendorLongitude) {
		this.vendorLongitude = vendorLongitude;
	}
   
}
