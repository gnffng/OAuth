package com.example.oauth.handler;

import com.example.oauth.dto.Registration;
import com.example.oauth.dto.oauth.AccessToken;
import com.example.oauth.repository.AccessTokenRepository;
import com.example.oauth.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

public class OAuthLogoutHandler implements LogoutHandler {

    private UserRepository userRepository;
    private AccessTokenRepository accessTokenRepository;

    private RestTemplate restTemplate;

    public OAuthLogoutHandler(UserRepository userRepository, AccessTokenRepository accessTokenRepository, RestTemplate restTemplate) {
        this.accessTokenRepository = accessTokenRepository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String email = "";
        AccessToken accessToken;
        Registration registration = Registration.valueOf(((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId());

        switch(registration){
            case google :
                break;
            case kakao :
                email = (String)((LinkedHashMap)((DefaultOAuth2User)authentication.getPrincipal()).getAttribute("kakao_account")).get("email");
                accessToken = accessTokenRepository.findByEmail(email);
                System.out.println(accessToken.getAccessToken().toString());

                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", "application/x-www-form-urlencoded");
                headers.set("Authorization", accessToken.getAccessToken().getTokenType().getValue()+" "+accessToken.getAccessToken().getTokenValue());

                HttpEntity httpRequest = new HttpEntity(headers);
                ResponseEntity<Map> res = restTemplate.exchange("https://kapi.kakao.com/v1/user/logout", HttpMethod.POST,httpRequest, Map.class);
                System.out.println(((Map)res.getBody()).get("id"));
                break;
            default :
                break;
        }
    }
}
