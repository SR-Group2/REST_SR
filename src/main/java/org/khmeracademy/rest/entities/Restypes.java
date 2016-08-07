package org.khmeracademy.rest.entities;

import java.util.Date;

public class Restypes {
	private int restype_id;
	private String restype_name;
	private String restype_name_kh;
	private String restype_picture;
	private Date date_added;
	private Date date_modify;
	private int parentid_restypeid;
	
	private Menus menus;
	private Restaurants restaurants;
	
	public Menus getMenus() {
		return menus;
	}
	public void setMenus(Menus menus) {
		this.menus = menus;
	}
	public Restaurants getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(Restaurants restaurants) {
		this.restaurants = restaurants;
	}
	public String getRestype_picture() {
		return restype_picture;
	}
	public void setRestype_picture(String restype_picture) {
		this.restype_picture = restype_picture;
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
	public String getRestype_name_kh() {
		return restype_name_kh;
	}
	public void setRestype_name_kh(String restype_name_kh) {
		this.restype_name_kh = restype_name_kh;
	}
	
	public int getRestype_id() {
		return restype_id;
	}
	public void setRestype_id(int restype_id) {
		this.restype_id = restype_id;
	}
	public String getRestype_name() {
		return restype_name;
	}
	public void setRestype_name(String restype_name) {
		this.restype_name = restype_name;
	}
	public int getParentid_restypeid() {
		return parentid_restypeid;
	}
	public void setParentid_restypeid(int parentid_restypeid) {
		this.parentid_restypeid = parentid_restypeid;
	}
}
