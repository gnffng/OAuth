package com.example.oauth.controller;

import com.example.oauth.dto.oauth.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/oauth2")
public class OAuthController {
    @Autowired
    RestTemplate restTemplate;

//    @PostMapping("/logout")
//    public Map logout(@RequestHeader Map<String, String> mapHeaders, HttpSession session){
//        HttpHeaders headers = new HttpHeaders();
//
//        for(String key : mapHeaders.keySet()){
//            headers.set(key, mapHeaders.get(key));
//        }
//
//        for(String attr : Collections.list(session.getAttributeNames())){
//            System.out.println(attr);
//            session.getAttribute(attr);
//        }
//
//        ResponseEntity<Map> resp = restTemplate.postForEntity("http://localhost:15000/logout",headers, Map.class);
//        return resp.getBody();
//    }

    @GetMapping("/csrf")
    public CsrfToken csrfToken(CsrfToken csrfToken){
        return csrfToken;
    }

    @GetMapping("/userData")
    public SessionUser getSession(HttpSession session){
        return (SessionUser)session.getAttribute("user");
    }

    @GetMapping("/authentication")
    public Authentication getSession2(HttpSession session){
        return ((SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication();
    }
}
