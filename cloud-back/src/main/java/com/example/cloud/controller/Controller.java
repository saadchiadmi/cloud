package com.example.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cloud.entities.TimeExecution;
import com.example.cloud.repository.ClosenessRepository;
import com.example.cloud.repository.IndexRepository;
import com.example.cloud.repository.JaccardRepository;
import com.example.cloud.repository.WarshalRepository;
import com.example.cloud.util.UtilIndex;
import com.example.cloud.util.UtilJaccard;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class Controller {

	@Autowired
	IndexRepository indexRepository;
	
	@Autowired
	JaccardRepository jaccardRepository;
	
	@Autowired
	WarshalRepository warshalRepository;
	
	@Autowired
	ClosenessRepository closenessRepository;
	
	static String Docs = "docs/";
	
	@GetMapping("/start")
	public TimeExecution getTimeExecution() {
		TimeExecution timeExecution = new TimeExecution();
		
		long start = System.currentTimeMillis();
		UtilIndex.createFileIndexOfDirectory(Docs, indexRepository);
		timeExecution.setIndex(System.currentTimeMillis() - start);
		
		start = System.currentTimeMillis();
		//UtilJaccard.computeJaccard(indexRepository.findAll());
		timeExecution.setJaccard(System.currentTimeMillis() - start);
		return timeExecution;
	}
	
}
