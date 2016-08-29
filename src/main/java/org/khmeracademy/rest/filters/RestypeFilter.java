package org.khmeracademy.rest.filters;

public class RestypeFilter {
	private String keyword;
	private int categoryId;
	
	public RestypeFilter(){
		keyword = "";
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
