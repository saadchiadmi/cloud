package com.example.cloud.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cloud.entities.Book;
import com.example.cloud.entities.Index;
import com.example.cloud.entities.Util;
import com.example.cloud.repository.BookRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/books")
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/books/{name}")
	public List<Book> getBooksByName(@PathVariable String name) {
		return bookRepository.findByIndex(name).stream().map(b-> new Util(b.getName(), b.getIndex().get(0).getOccurence())).sorted(Comparator.comparingLong(Util::getOcc).reversed()).map(u -> new Book(u.getName())).collect(Collectors.toList());
	}
	
	@PostMapping("/books")
	public Book createBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	@DeleteMapping("/books/{name}")
	public Book deleteBook(@PathVariable String name) {
		Book result = getBooksByName(name).get(0);
		bookRepository.deleteById(name);
		return result;
	}

}
