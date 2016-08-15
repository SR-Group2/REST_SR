package org.khmeracademy.rest.entities;

import java.util.List;

public class Restaurants {
	private int rest_id;
	private String rest_name;
	private String contact;
	private String about;
	private String open_close;
	private String location;
	private Addresses address;
	private Users user;
	private List<Menus> restype;//menus
	
	//private Restypes catagories;//restypes
	private List<Restypes> categories;//restypesList
	
	public Restypes getRestypes() {
		return restypes;
	}
	public void setRestypes(Restypes restypes) {
		this.restypes = restypes;
	}
	private Restypes restypes;
	
	
	/*public List<Restypes> getRestypes() {
		return restypes;
	}*/
	
	public List<Menus> getRestype() {
		return restype;
	}
	public List<Restypes> getCategories() {
		return categories;
	}
	public void setCategories(List<Restypes> categories) {
		this.categories = categories;
	}
	public String getOpen_close() {
		return open_close;
	}
	public void setRestype(List<Menus> restype) {
		this.restype = restype;
	}

	public void setOpen_close(String open_close) {
		this.open_close = open_close;
	}

//	public void setRestypes(List<Restypes> restypes) {
//		this.restypes = restypes;
//	}
	
	
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}	
}
