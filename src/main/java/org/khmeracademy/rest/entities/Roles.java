package org.khmeracademy.rest.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Roles {
	
	@JsonProperty("ID")
	private int id;
	@JsonProperty("KEY")
	private String key;
	@JsonProperty("NAME")
	private String name;
	
	public Roles() {
		key = "";
		name = "";
	}
	/**
	 * @param key
	 * @param name
	 */
	public Roles(String key, String name) {
		super();
		this.key = key;
		this.name = name;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
