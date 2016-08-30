package org.khmeracademy.rest.filters;

public class RestypeFilter {
	private String keyword;
	private int category_id;
	
	public RestypeFilter(){
		keyword = "";
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	
}
