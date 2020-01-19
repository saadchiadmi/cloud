package com.example.cloud.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cloud.entities.Book;
import com.example.cloud.repository.BookRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/books")
	public List<Book> getAllBooks() throws InterruptedException, ExecutionException {
		return bookRepository.findAll();
	}
	
	@GetMapping("/books/{name}")
	public Book getBooksByName(@PathVariable String name) throws InterruptedException, ExecutionException {
		return bookRepository.findById(name).orElse(null);
	}
	
	@PostMapping("/books")
	public Book createBook(@RequestBody Book book) throws InterruptedException, ExecutionException {
		return bookRepository.save(book);
	}
	
	@PutMapping("/books")
	public Book updateBook(@RequestBody Book book) {
		return updateBook(book);
	}
	
	@DeleteMapping("/books/{name}")
	public Book deleteBook(@PathVariable String name) throws InterruptedException, ExecutionException {
		Book result = getBooksByName(name);
		bookRepository.deleteById(name);
		return result;
	}

}
