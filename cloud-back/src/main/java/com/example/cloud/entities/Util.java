package com.example.cloud.entities;

public class Util {
	
	String name;
	long occ;
	public Util(String name, long occ) {
		super();
		this.name = name;
		this.occ = occ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getOcc() {
		return occ;
	}
	public void setOcc(long occ) {
		this.occ = occ;
	}

}
