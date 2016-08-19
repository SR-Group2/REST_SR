package org.khmeracademy.rest.entities;

public class FavouriteRestaurants {
	private int favrest_id;
	private Users user;
	private Restaurants rest;
	public int getFavrest_id() {
		return favrest_id;
	}
	public void setFavrest_id(int favrest_id) {
		this.favrest_id = favrest_id;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Restaurants getRest() {
		return rest;
	}
	public void setRest(Restaurants rest) {
		this.rest = rest;
	}
	
}
