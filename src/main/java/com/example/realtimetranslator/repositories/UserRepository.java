package com.example.realtimetranslator.repositories;

import com.example.realtimetranslator.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> getByEmail(String email);
}
