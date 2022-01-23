package com.antunes.youpost.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.antunes.youpost.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	

}
