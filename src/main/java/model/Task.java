package model;

import java.io.Serializable;

public class Task implements Serializable{
	private static final long serialVersionUID = -5521467769307252473L;
	
	private int id;
	
	private String name;
	
	private String description;
	
	private int publicationId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int courseId) {
		this.publicationId = courseId;
	}

	
	
	
}
