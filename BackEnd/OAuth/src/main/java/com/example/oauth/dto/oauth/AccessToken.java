package com.example.oauth.dto.oauth;

import com.example.oauth.dto.Registration;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@Document(collection="oauth_access_token")
@Getter
@Setter
public class AccessToken {
    @Id
    private String email;
    Registration registration;

    OAuth2AccessToken accessToken;

    public AccessToken(String email, Registration registration, OAuth2AccessToken accessToken) {
        this.email = email;
        this.registration = registration;
        this.accessToken = accessToken;
    }
}
