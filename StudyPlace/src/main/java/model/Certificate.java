package model;

import java.io.Serializable;


public class Certificate implements Serializable{

	private static final long serialVersionUID = -9175517728830926345L;

	private int id;
	
	private int userId;
		
	private int courseId;

	private Double mark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Double getMark() {
		return mark;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}
}
