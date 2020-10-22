package model;

import java.io.Serializable;
/**
 * Role entity.
 * 
 * @author A.Shporta
 * 
 */
public class Role implements Serializable{

	private static final long serialVersionUID = -4935408337811905947L;
	
	private int id;
	
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
}
