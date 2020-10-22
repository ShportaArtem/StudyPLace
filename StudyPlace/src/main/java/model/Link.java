package model;

import java.io.Serializable;

public class Link implements Serializable{

	private static final long serialVersionUID = -5435916284762500327L;
	
	private int id;

	private String value;
	
	private int publicationId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}
}
