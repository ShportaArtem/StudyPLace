package model;

import java.io.Serializable;
import java.sql.Blob;

public class UserFTAnswer implements Serializable {

	private static final long serialVersionUID = -5451369644546410123L;
	
	private int id;
	
	private int userId;
	
	private int questionId;
	
	private Blob file;
	
	private String FreeText;

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

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public Blob getFile() {
		return file;
	}

	public void setFile(Blob file) {
		this.file = file;
	}

	public String getFreeText() {
		return FreeText;
	}

	public void setFreeText(String freeText) {
		FreeText = freeText;
	}
	
}
