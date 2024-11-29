package com.vrv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define the password encoder bean
    @Bean
    public Argon2PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

    // Configure HTTP security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Enable CSRF protection by default, or disable it if needed
            .csrf(csrf -> csrf.disable())  // Disable CSRF for API-style applications, or omit this for default behavior

            // Configure HTTP Basic Authentication manually, instead of using the deprecated httpBasic()
            .authorizeRequests(authz -> authz
                .requestMatchers("/login", "/signup").permitAll()  // Allow access to login and signup pages without authentication
                .anyRequest().authenticated()  // All other requests require authentication
            )

            // Enabling HTTP Basic Authentication manually
            .httpBasic(httpBasic -> httpBasic
                    .realmName("MyAppRealm")  // Specify the realm (optional)
                )

            // Enable form-based login with custom login page
            .formLogin(form -> form
                    .loginPage("/login")  // Custom login page URL
                    .permitAll()  // Allow unauthenticated access to the login page
                    .defaultSuccessUrl("/home", true)  // Redirect to home after successful login
                )

            // Enable logout functionality
            .logout(logout -> logout
                .permitAll()  // Allow unauthenticated access to logout functionality
            );

        return http.build();
    }
}
