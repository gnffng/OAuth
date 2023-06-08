package com.example.oauth.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@WritingConverter
public class AccessTokenWriteConverter implements Converter<OAuth2AccessToken, String> {
    @Override
    public String convert(OAuth2AccessToken source) {
        System.out.println(source.toString());

        return source.toString();
    }
}
