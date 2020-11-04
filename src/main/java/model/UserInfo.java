package model;

import java.io.Serializable;
import java.sql.Blob;

public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1231468054964602727L;
	
	private int id;
	
	private boolean isLocked;
	
	private String messanger;
	
	private String website;
	
	private String email;
	
	private Blob picture;
	
	private String about;

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public String getMessanger() {
		return messanger;
	}

	public void setMessanger(String messanger) {
		this.messanger = messanger;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob blob) {
		this.picture = blob;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
