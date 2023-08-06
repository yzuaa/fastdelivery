package com.laioffer.fastdelivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private final DataSource dataSource;

   public SecurityConfig(DataSource dataSource) {
      this.dataSource = dataSource;
   }
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/register/*").permitAll()
            .antMatchers(HttpMethod.POST, "/authenticate/*").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf()
            .disable();
   }
   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication().dataSource(dataSource)
            .passwordEncoder(passwordEncoder())
            .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
            .authoritiesByUsernameQuery("SELECT username, authority FROM authority WHERE username = ?");
   }

   @Override
   @Bean
   public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
   }
}
