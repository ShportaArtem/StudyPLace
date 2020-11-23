package modelView;

import java.io.Serializable;
import java.sql.Date;

public class CommentView implements Serializable{
	private static final long serialVersionUID = 1593054485251702976L;
	
	private int id;
	
	private Date dateTime;

	private String userName;
	
	private String text;
	
	private int courseID;

	private int publicationID;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getPublicationID() {
		return publicationID;
	}

	public void setPublicationID(int publicationID) {
		this.publicationID = publicationID;
	}
	
}
