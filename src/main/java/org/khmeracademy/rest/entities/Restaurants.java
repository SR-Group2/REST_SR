package org.khmeracademy.rest.entities;

import java.util.List;

public class Restaurants {
	private int rest_id;
	private String rest_name;
	private String contact;
	private String about;
	private String open_close;
	private String latitude;
	private String longitude;
	private String rest_name_kh;
	private int total_favorite;
	
	
	private Addresses address;
	private Users user;
	
	public String getRest_name_kh() {
		return rest_name_kh;
	}
	public void setRest_name_kh(String rest_name_kh) {
		this.rest_name_kh = rest_name_kh;
	}
	private List<Restpictures> restpictures;
	
	
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
	
	public List<Restpictures> getRestpictures() {
		return restpictures;
	}
	public void setRestpictures(List<Restpictures> restpictures) {
		this.restpictures = restpictures;
	}
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
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getTotal_favorite() {
		return total_favorite;
	}
	public void setTotal_favorite(int total_favorite) {
		this.total_favorite = total_favorite;
	}	
}
