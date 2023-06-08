package com.example.oauth.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import org.springframework.security.oauth2.core.OAuth2AccessToken;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@ReadingConverter
public class AccessTokenReadConverter implements Converter<Map, OAuth2AccessToken> {
    @Override
    public OAuth2AccessToken convert(Map source) {
        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                source.get("tokenValue").toString(),
                ((Date)source.get("issuedAt")).toInstant(),
                ((Date)source.get("expiresAt")).toInstant(),
                Set.copyOf((ArrayList)source.get("scopes"))
        );

        return oAuth2AccessToken;
    }
}
