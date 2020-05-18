package com.data.science.net.oauth.gateway.database.mongo.repository;

import com.data.science.net.oauth.gateway.database.mongo.document.User;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

  Optional<User> findByUsername(String username);
}
