package com.example.cloud.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "closeness")
public class Closeness {
	
	@Id
	String book;
	
	Long closeness;

	public Closeness() {
		super();
	}

	public Closeness(String book, Long closeness) {
		super();
		this.book = book;
		this.closeness = closeness;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public Long getCloseness() {
		return closeness;
	}

	public void setCloseness(Long closeness) {
		this.closeness = closeness;
	}

}
