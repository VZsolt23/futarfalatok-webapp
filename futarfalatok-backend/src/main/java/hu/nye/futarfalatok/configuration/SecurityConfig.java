package hu.nye.futarfalatok.configuration;

import hu.nye.futarfalatok.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/v1/orders/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/v1/orders/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/dishes")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/restaurants/**")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/restaurants/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/restaurants/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/restaurants/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/reviews/**")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/reviews/**")
                .permitAll()
                .requestMatchers("/api/v1/reviews/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/restaurant-dish").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
