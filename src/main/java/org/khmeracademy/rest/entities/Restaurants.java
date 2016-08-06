package org.khmeracademy.rest.entities;

import java.util.Date;

public class Restaurants {
	private int rest_id;
	private String rest_name;
	private String contact;
	private String about;
	private Date open_close;
	private String location;
	private Restypes restype;
	private Addresses address;
	private Users user;
	
	public Restypes getRestype() {
		return restype;
	}
	public void setRestype(Restypes restype) {
		this.restype = restype;
	}
	public Addresses getAddress() {
		return address;
	}
	public void setAddress(Addresses address) {
		this.address = address;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public int getRest_id() {
		return rest_id;
	}
	public void setRest_id(int rest_id) {
		this.rest_id = rest_id;
	}
	public String getRest_name() {
		return rest_name;
	}
	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Date getOpen_close() {
		return open_close;
	}
	public void setOpen_close(Date open_close) {
		this.open_close = open_close;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}	
}
