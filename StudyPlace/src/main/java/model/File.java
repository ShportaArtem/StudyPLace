package model;

import java.io.Serializable;
import java.sql.Blob;

public class File implements Serializable {
	private static final long serialVersionUID = -7723410987381496076L;
	
	private int id;
	
	private Blob value;
	
	private int publicationId;
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Blob getValue() {
		return value;
	}

	public void setValue(Blob value) {
		this.value = value;
	}

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}
}
