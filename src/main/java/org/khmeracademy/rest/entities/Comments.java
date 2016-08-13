package org.khmeracademy.rest.entities;

public class Comments {
	private int comment_id;
	private String comment;
	private Users user;
	private Restaurants rest;
	
	public Restaurants getRest() {
		return rest;
	}
	public void setRest(Restaurants rest) {
		this.rest = rest;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
}
