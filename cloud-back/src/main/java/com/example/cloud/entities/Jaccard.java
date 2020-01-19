package com.example.cloud.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jaccard")
public class Jaccard {
	
	String book1;
	
	String book2;
	
	Long distance;

	public Jaccard() {
		super();
	}

	public Jaccard(String book1, String book2, Long distance) {
		super();
		this.book1 = book1;
		this.book2 = book2;
		this.distance = distance;
	}

	public String getBook1() {
		return book1;
	}

	public void setBook1(String book1) {
		this.book1 = book1;
	}

	public String getBook2() {
		return book2;
	}

	public void setBook2(String book2) {
		this.book2 = book2;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

}
