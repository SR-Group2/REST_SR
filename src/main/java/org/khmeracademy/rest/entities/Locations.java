package org.khmeracademy.rest.entities;

public class Locations {
	
	private int id;		
	private String type;	
	private String type_code;	
	private String code;	
	private String khmer_name;	
	private String english_name;	
	private String reference;	
	private String official_note;	
	private String checker_note	;
	private int  parent_id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getKhmer_name() {
		return khmer_name;
	}
	public void setKhmer_name(String khmer_name) {
		this.khmer_name = khmer_name;
	}
	public String getEnglish_name() {
		return english_name;
	}
	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getOfficial_note() {
		return official_note;
	}
	public void setOfficial_note(String official_note) {
		this.official_note = official_note;
	}
	public String getChecker_note() {
		return checker_note;
	}
	public void setChecker_note(String checker_note) {
		this.checker_note = checker_note;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	
	
}
