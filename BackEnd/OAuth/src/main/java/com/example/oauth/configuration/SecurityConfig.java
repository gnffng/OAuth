package com.example.oauth.configuration;

import com.example.oauth.handler.OAuthLogoutHandler;
import com.example.oauth.repository.AccessTokenRepository;
import com.example.oauth.repository.UserRepository;
import com.example.oauth.service.OAuth2UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OAuth2UserServiceImpl oAuth2UserService;
    private final AccessTokenRepository accessTokenRepository;
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> {
            web.ignoring()
                    .requestMatchers("/js/**", "/image/**")
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return
            http
                .authorizeHttpRequests()
                    .requestMatchers("/test", "/oauth2/*")
                    .permitAll()
                    .anyRequest()	// 모든 요청에 대해서 인증처리를 해라.
                    .authenticated()
                .and()
                .logout()
                    .logoutUrl("/oauth2/logout")
                    .addLogoutHandler(new OAuthLogoutHandler(userRepository,accessTokenRepository, restTemplate))
                    .logoutSuccessUrl("http://localhost:3001")	// 로그아웃에 대해서 성공하면 "/"로 이동
                .and()
                .oauth2Login()
                    .defaultSuccessUrl("http://localhost:3001/signUp")
                    .userInfoEndpoint()
                    .userService(oAuth2UserService)
                .and()
                    .and().build();
    }
}
