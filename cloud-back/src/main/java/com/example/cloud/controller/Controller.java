package com.example.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cloud.entities.Book;
import com.example.cloud.entities.Closeness;
import com.example.cloud.entities.Graphe;
import com.example.cloud.entities.TimeExecution;
import com.example.cloud.repository.BookRepository;
import com.example.cloud.repository.ClosenessRepository;
import com.example.cloud.repository.IndexRepository;
import com.example.cloud.repository.GrapheRepository;
import com.example.cloud.util.UtilIndex;
import com.example.cloud.util.UtilCloseness;
import com.example.cloud.util.UtilGraphe;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class Controller {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	IndexRepository indexRepository;
	
	@Autowired
	GrapheRepository grapheRepository;
	
	@Autowired
	ClosenessRepository closenessRepository;
	
	static String Docs = "docs/";
	
	@GetMapping("/start")
	public TimeExecution getTimeExecution() {
		TimeExecution timeExecution = new TimeExecution();
		grapheRepository.deleteAll();
		closenessRepository.deleteAll();
		
		long start = System.currentTimeMillis();
		List<Book> books = UtilIndex.createFileIndexOfDirectory(Docs);
		bookRepository.saveAll(books);
		timeExecution.setIndex(System.currentTimeMillis() - start);
		
		start = System.currentTimeMillis();
		List<Graphe> graphe = UtilGraphe.computeJaccard(bookRepository.findAll());
		grapheRepository.saveAll(graphe);
		timeExecution.setGraphe(System.currentTimeMillis() - start);
		
		start = System.currentTimeMillis();
		List<Closeness> closenesses = UtilCloseness.computeClosenessFiles(grapheRepository.findAll());
		closenessRepository.saveAll(closenesses);
		timeExecution.setCloseness(System.currentTimeMillis() - start);
		return timeExecution;
	}
	
}
