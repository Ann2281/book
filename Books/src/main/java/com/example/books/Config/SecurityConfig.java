package com.example.books.Config;

import com.example.books.Models.Users;
import com.example.books.Repository.UsersRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
    @Bean
    public UserDetailsService userDetailsService(UsersRepository repository) {
        return username -> {
            Users users = repository.findByUsername(username);
            if (users != null) return users;
            throw new UsernameNotFoundException("Данный пользователь: не найден");
        };
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        return http.authorizeHttpRequests(auth->auth
                        .requestMatchers("/Author/**","/Publish/**","/Book/**").authenticated()
                        .requestMatchers("/Login", "/Registration").anonymous()
                        .requestMatchers("/","/style/**","/images/**").permitAll())
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/Login"))
                .build();
    }
}