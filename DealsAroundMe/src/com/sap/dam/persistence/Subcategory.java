package com.sap.dam.persistence;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: subcategory
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "AllSubcategory", query = "select u from Subcategory u"),
	@NamedQuery(name = "SubcategoryByCID", query = "SELECT p FROM Subcategory p WHERE p.categoryId = :categoryId") })
@IdClass(SubcategoryPK.class)
public class Subcategory implements Serializable {

	   
	@Id
	private int categoryId;   
	@Id
	private int subcategoryId;
	@Basic
	@Column(length=16)
	private String subcategory;
	@Basic
	@Column(length=16)
	private String type;
	private static final long serialVersionUID = 1L;

	public Subcategory() {
		super();
	}   
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
	public String getSubcategory() {
		return this.subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
   
}
