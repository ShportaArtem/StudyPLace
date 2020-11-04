package model;

import java.io.Serializable;

public class Subscription implements Serializable{

	private static final long serialVersionUID = 3342222199582022681L;

	private int userId;

	private int courseId;

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
}
