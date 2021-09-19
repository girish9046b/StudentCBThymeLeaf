package com.student.app.config;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 
@Configuration
//This class will provide the Basic authentication for the Spring Rest API
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http.cors().and()
         .csrf().disable()
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .httpBasic();
		
		
 }
   
  
	 @Value("${spring.security.user.name}") //Defined in application.properties file
	  String username;

	  @Value("${spring.security.user.password}") //Defined in application.properties file
	  String password;
	  
	  @Value("${spring.security.user.roles}") //Defined in application.properties file
	  String role;
	  
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
            throws Exception 
    {
    	auth.inMemoryAuthentication()
            .withUser(username)
            .password("{noop}"+password)
            .roles(role);
    }
  
  //To allow cross origin request access 
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("*"));
      configuration.setAllowedMethods(Arrays.asList("GET","POST"));
      configuration.setAllowCredentials(true);
      configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
    }
   
}