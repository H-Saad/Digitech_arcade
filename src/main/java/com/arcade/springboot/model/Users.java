package com.arcade.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="fullname")
	private String fullname;
	@Column(name="major")
	private String major;
	@Column(name="phone")
	private String phone;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="points")
	private int points;
	@Column(name="banned")
	private boolean banned;
	
	public Users() {
		this.banned = false;
	}

	public Users(int id, String fullname, String major, String phone, String email, String password, int points,
			boolean banned) {
		this.id = id;
		this.fullname = fullname;
		this.major = major;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.points = points;
		this.banned = banned;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}
}
