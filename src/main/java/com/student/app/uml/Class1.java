package com.student.app.uml;

public class Class1 {

	private String id;
	private int name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void getName() {
		throw new UnsupportedOperationException();
	}

	public void setName(int name) {
		this.name = name;
	}

	public void getAttribute() {
		throw new UnsupportedOperationException();
	}

	public void setAttribute(int attribute) {
		throw new UnsupportedOperationException();
	}
}
