package com.example.oauth.repository;

import com.example.oauth.dto.oauth.AccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends MongoRepository<AccessToken, String> {
    AccessToken findByEmail(String email);
}
