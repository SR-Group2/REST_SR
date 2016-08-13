package org.khmeracademy.rest.entities;

import java.util.Date;

public class Categories {
	private int category_id;
	private String category_name;
	private String other;
	private Date date_added;
	private Date date_modify;
	
	private Restaurants rest;
	
	public Restaurants getRest() {
		return rest;
	}
	public void setRest(Restaurants rest) {
		this.rest = rest;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public Date getDate_added() {
		return date_added;
	}
	public void setDate_added(Date date_added) {
		this.date_added = date_added;
	}
	public Date getDate_modify() {
		return date_modify;
	}
	public void setDate_modify(Date date_modify) {
		this.date_modify = date_modify;
	}
}
