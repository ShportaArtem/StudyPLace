package model;

import java.io.Serializable;

public class UserAnswer implements Serializable{

	private static final long serialVersionUID = -2459465012551648772L;
	
	private int id;
	
	private int userId;
	
	private int answerForQuestionId;

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

	public int getAnswerForQuestionId() {
		return answerForQuestionId;
	}

	public void setAnswerForQuestionId(int answerForQuestionId) {
		this.answerForQuestionId = answerForQuestionId;
	}
}
