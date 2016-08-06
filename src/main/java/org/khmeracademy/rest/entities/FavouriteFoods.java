package org.khmeracademy.rest.entities;

public class FavouriteFoods {
	
	private int fav_id;
	private Users user;
	private Foods food;
	
	public int getFav_id() {
		return fav_id;
	}
	public void setFav_id(int fav_id) {
		this.fav_id = fav_id;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Foods getFood() {
		return food;
	}
	public void setFood(Foods food) {
		this.food = food;
	}
	
}
