package org.khmeracademy.rest.entities;

public class Votes {
	private int vote_id;
	private double vote_number;
	private Restaurants rest;
	private Foods food;
	private Users user;
	public int getVote_id() {
		return vote_id;
	}
	public void setVote_id(int vote_id) {
		this.vote_id = vote_id;
	}
	public double getVote_number() {
		return vote_number;
	}
	public void setVote_number(double vote_number) {
		this.vote_number = vote_number;
	}
	public Restaurants getRest() {
		return rest;
	}
	public void setRest(Restaurants rest) {
		this.rest = rest;
	}
	public Foods getFood() {
		return food;
	}
	public void setFood(Foods food) {
		this.food = food;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	
}
