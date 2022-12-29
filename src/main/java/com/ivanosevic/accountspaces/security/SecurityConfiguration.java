package com.ivanosevic.accountspaces.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final RememberMeProperties rememberMeProperties;

    public SecurityConfiguration(RememberMeProperties rememberMeCookieProperties) {
        this.rememberMeProperties = rememberMeCookieProperties;
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager defaultAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                    .authorizeHttpRequests().antMatchers(HttpMethod.GET, "/static/**", "/webjars/**").permitAll()
                .and()
                    .authorizeHttpRequests().antMatchers(HttpMethod.GET, "/sign-in").permitAll()
                .and()
                    .authorizeHttpRequests().antMatchers(HttpMethod.POST, "/sign-in").permitAll()
                .and()
                    .authorizeHttpRequests().antMatchers(HttpMethod.POST, "/logout").permitAll()
                .and()
                    .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/sign-in")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .failureUrl("/sign-in?error")
                    .defaultSuccessUrl("/account-spaces/my-profile")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/sign-in?logout")
                .and()
                .rememberMe()
                    .key(rememberMeProperties.getKey())
                    .rememberMeParameter("remember-me")
                    .tokenValiditySeconds(rememberMeProperties.getDuration());
        return http.build();
    }
}
