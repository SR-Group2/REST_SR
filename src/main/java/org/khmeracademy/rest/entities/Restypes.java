package org.khmeracademy.rest.entities;

public class Restypes {
	private int restype_id;
	private String restype_name;
	private int parentid_restypeid;
	
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
