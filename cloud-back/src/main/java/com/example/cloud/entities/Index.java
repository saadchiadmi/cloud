package com.example.cloud.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "index")
public class Index {
	
	@Id
	String word;
	
	String book;
	
	Long occurence;

	public Index() {
		super();
	}

	public Index(String word, String book, Long occurence) {
		super();
		this.word = word;
		this.book = book;
		this.occurence = occurence;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public Long getOccurence() {
		return occurence;
	}

	public void setOccurence(Long occurence) {
		this.occurence = occurence;
	}

}
