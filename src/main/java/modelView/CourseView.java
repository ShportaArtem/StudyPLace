package modelView;

import java.io.Serializable;
import java.sql.Blob;

public class CourseView implements Serializable{

	private static final long serialVersionUID = -1842141320487532709L;
	
	private int id;
	
	private String description;
	
	private String name;
	
	private String password;
	
	private int teacherId;	
	
	private String teacherName;
	
	private int countFollowers;
	
	private Blob picture;
	
	private Double price;

	
	public String getTeacherName() {
		return teacherName;
	}
	
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public int getCountFollowers() {
		return countFollowers;
	}
	
	public void setCountFollowers(int countFollowers) {
		this.countFollowers = countFollowers;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public CharSequence  getShortDescription() {
		int len = description.length();
		CharSequence  shortD;
		if(len<60) {
			shortD = description.subSequence(0,len);
		}
		else {
			shortD = description.subSequence(0,49);
		}
		return shortD;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
