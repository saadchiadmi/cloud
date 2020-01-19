package com.example.cloud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.cloud.entities.Warshall;

public interface WarshalRepository extends MongoRepository<Warshall, Integer>{

}
