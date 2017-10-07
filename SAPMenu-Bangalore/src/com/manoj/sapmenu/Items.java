package com.manoj.sapmenu;

public class Items {
	private String date;
	private String itemName;
	private String calories;
	private String day;
	private String when;
	public Items(String itemName, String calories, String day, String when,String date) {
		this.itemName = itemName;
		this.calories = calories;
		this.day = day;
		this.when = when;
		this.date = date;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getWhen() {
		return when;
	}
	public void setWhen(String when) {
		this.when = when;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
