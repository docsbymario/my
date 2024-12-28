package com.docsbymario.my.repository;

import com.docsbymario.my.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByUsername(String username);
    List<User> findByEmail(String email);
}
