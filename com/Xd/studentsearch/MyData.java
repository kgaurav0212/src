package com.Xd.studentsearch;

public class MyData {
	int id;
	String roll, name, dept, batch, filename, prog, gender, add, room, hall,
			email, bgroup;

	public void Mydata() {

	}

	public MyData(String name, String roll, String batch, String dept,
			String prog, String gender, String room, String hall, String email,
			String bgroup) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.dept = dept;
		this.roll = roll;
		this.batch = batch;
		this.prog = prog;
		this.gender = gender;
		this.room = room;
		this.hall = hall;
		this.email = email;
		this.bgroup = bgroup;
	}

	public String getBgroup() {
		return this.bgroup;
	}

	public String getEmail() {
		return this.email;
	}

	public String getHall() {
		return this.hall;
	}

	public String getRoom() {
		return this.room;
	}

	public String getAdd() {
		return this.add;
	}

	public String getFilename() {
		return this.filename;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getdept() {
		return this.dept;
	}

	public String getBatch() {
		return this.batch;
	}

	public String getProg() {
		return this.prog;
	}

	public String getRoll() {
		return this.roll;
	}
}