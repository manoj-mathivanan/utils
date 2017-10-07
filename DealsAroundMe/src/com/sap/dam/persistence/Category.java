package com.sap.dam.persistence;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: category
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "AllCategory", query = "select u from Category u"),
	@NamedQuery(name = "CategoryByVID", query = "SELECT p FROM Category p WHERE p.vendorId = :vendorId") })
@IdClass(CategoryPK.class)
public class Category implements Serializable {

	   
	@Id
	private int vendorId;   
	@Id
	private int categoryId;
	@Basic
	@Column(length=16)
	private String category;
	private static final long serialVersionUID = 1L;

	public Category() {
		super();
	}   
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
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
   
}
