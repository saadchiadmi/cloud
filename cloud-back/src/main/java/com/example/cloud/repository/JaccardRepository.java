package com.example.cloud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cloud.entities.Jaccard;

public interface JaccardRepository extends MongoRepository<Jaccard, Integer>{

}
