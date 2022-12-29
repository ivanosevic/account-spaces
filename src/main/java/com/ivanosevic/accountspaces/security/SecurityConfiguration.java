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
        http.cors();

        http.authorizeHttpRequests().antMatchers(HttpMethod.GET, "/static/**", "/webjars/**").permitAll();
        http.authorizeHttpRequests().antMatchers(HttpMethod.GET, "/sign-in").permitAll();
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/sign-in").permitAll();
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/logout").permitAll();
        http.authorizeHttpRequests().antMatchers(HttpMethod.GET, "/account-spaces/my-profile").authenticated();
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/account-spaces/my-profile/basic-information").authenticated();
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/account-spaces/change-password").authenticated();
        http.authorizeHttpRequests().anyRequest().denyAll();

        http.formLogin()
                .loginPage("/sign-in")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/sign-in?error")
                .defaultSuccessUrl("/account-spaces/my-profile");

        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .deleteCookies("remember-me")
                .logoutSuccessUrl("/sign-in?logout");

        http.rememberMe()
                .key(rememberMeProperties.getKey())
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(rememberMeProperties.getDuration());

        return http.build();
    }
}
