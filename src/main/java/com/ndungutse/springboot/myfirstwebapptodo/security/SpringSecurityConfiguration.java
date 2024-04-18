package com.ndungutse.springboot.myfirstwebapptodo.security;

import java.util.function.Function;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager createUserDetails() {

        UserDetails userDetails1 = createNewUser("eric", "test");
        UserDetails userDetails2 = createNewUser("james", "test");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    @Bean
    // Used during creating user and comapring passwords in login
    // Decoding and Encoding
    // When Signing in, spring encode the password with this method and
    // Verify with the above
    // Since the above is plain not hashed/encoded user cannot login
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserDetails createNewUser(String username, String password) {
        Function<String, String> passwordEncoder = input -> passwordEncoder()
                .encode(input);

        return User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();

    }

    // *** SPRING SECURITY DEFAULT
    // 1) All Urls are protected
    // 2) A login is shown for unauthorized request

    // For H2 in spring security configured, CSRF prevents it, we need to desable it
    // to access console
    // H2 uses Frames, not configured in spring security

    @Bean
    // An interface (Deefault support above 2, need to implement the last 2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> auth.anyRequest().authenticated());
        // SInce we overrode default behavioir securityFilteChain, we need to define
        // entire chain
        http.formLogin(withDefaults());
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();

    }

}
