package model;

import java.io.Serializable;

public class Question implements Serializable{
	private static final long serialVersionUID = 4142766782693370754L;
	
	private int id;

	private int taskId;
	
	private String question;
	
	private String description;

	private int mark;
	
	private int position;
	
	private boolean oneCorrectAnswer;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isOneCorrectAnswer() {
		return oneCorrectAnswer;
	}

	public void setOneCorrectAnswer(boolean oneCorrectAnswer) {
		this.oneCorrectAnswer = oneCorrectAnswer;
	}

}
