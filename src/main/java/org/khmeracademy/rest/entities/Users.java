package org.khmeracademy.rest.entities;

import java.util.Date;
import java.util.List;

import org.khmeracademy.rest.form.RestaurantForm2;
import org.springframework.web.multipart.MultipartFile;


public class Users {
	
private String first_name;
private String last_name;
private String gender;

public String getFirst_name() {
	return first_name;
}

public void setFirst_name(String first_name) {
	this.first_name = first_name;
}

public String getLast_name() {
	return last_name;
}

public void setLast_name(String last_name) {
	this.last_name = last_name;
}

public Date getDob() {
	return dob;
}

public void setDob(Date dob) {
	this.dob = dob;
}

//	private String username;
//	private String email;
//	private String password;
private String salt;
public String getSalt() {
	return salt;
}

public void setSalt(String salt) {
	this.salt = salt;
}

public Date getJoined() {
	return joined;
}

public void setJoined(Date joined) {
	this.joined = joined;
}

	private Date dob;
	private Date joined;
	private String picture;
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}

	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	//@JsonProperty("ID")
	private int user_id;
	//@JsonProperty("USERNAME")
	private String username;
	//@JsonProperty("PASSWORD")
	private String password;
	//@JsonProperty("ROLE")
	private Roles role;
	//@JsonProperty("EMAIl")
	private String email;
	//@JsonProperty("ROLE_NAME")
	private String role_name;
	
	
	private List<Roles> roles;
	
	public Users() {
		username = "";
		password = "";
		role = new Roles();
	}
	
	public Users(String username, String password, Roles role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Roles getRole() {
		return role;
	}
	
	public void setRole(Roles role) {
		this.role = role;
	}
	
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", role=" + role + "]";
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	//===================== Upload User File ==========================
	 public static class Users2 extends Users{
		
		 private List<MultipartFile> user_file;

		public List<MultipartFile> getUser_file() {
			return user_file;
		}

		public void setUser_file(List<MultipartFile> user_file) {
			this.user_file = user_file;
		}
		
	 }
	
}
