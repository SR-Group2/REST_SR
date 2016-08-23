package org.khmeracademy.rest.entities;

public class Addresses {
	
	private int address_id;
	private String street;
	private String district;
	private String communce;
	private String province;
	private String village;
	
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public String getStreet() {
		return street;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCommunce() {
		return communce;
	}
	public void setCommunce(String communce) {
		this.communce = communce;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
}
