package model;

import java.io.Serializable;
import java.sql.Date;

public class Publication implements Serializable{

	private static final long serialVersionUID = -3282062958545104162L;
	
	private int id;

	private String name;
	
	private String description;
	
	private int courseID;

	private int pisition;
	
	private Date dateTime;

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

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getPisition() {
		return pisition;
	}

	public void setPisition(int pisition) {
		this.pisition = pisition;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
