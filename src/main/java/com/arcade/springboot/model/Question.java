package com.arcade.springboot.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="body")
	private String body;
	@Column(name="type")
	private String type;
	@Column(name="choices")
	private ArrayList<String> choices;
	@Column(name="answer")
	private String answer;
	@Column(name="original_pts")
	private int original_pts;
	@Column(name="points")
	private int points;
	@Column(name="min_pts")
	private int min_pts;
	@Column(name="dec_value")
	private int dec_value;
	@Column(name="archived")
	private boolean archived;
	
	public Question() {
		this.choices = new ArrayList<String>();
		this.archived = false;
	}

	public Question(int id, String title, String body, String type, ArrayList<String> choices, String answer,
			int original_pts, int points, int min_pts, int dec_value, boolean archived) {
		this();
		this.id = id;
		this.title = title;
		this.body = body;
		this.type = type;
		this.choices = choices;
		this.answer = answer;
		this.points = points;
		this.original_pts = original_pts;
		this.min_pts = min_pts;
		this.dec_value = dec_value;
		this.archived = archived;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<String> getChoices() {
		return choices;
	}

	public void setChoices(ArrayList<String> choices) {
		this.choices = choices;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getMin_pts() {
		return min_pts;
	}

	public void setMin_pts(int min_pts) {
		this.min_pts = min_pts;
	}

	public int getDec_value() {
		return dec_value;
	}

	public void setDec_value(int dec_value) {
		this.dec_value = dec_value;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public int getOriginal_pts() {
		return original_pts;
	}

	public void setOriginal_pts(int original_pts) {
		this.original_pts = original_pts;
	}
	
	
}
