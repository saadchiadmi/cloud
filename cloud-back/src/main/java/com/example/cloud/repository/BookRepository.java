package com.example.cloud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cloud.entities.Book;

public interface BookRepository extends MongoRepository<Book, String>{

}
