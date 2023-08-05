/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.security;

import com.envisionnepal.gunasho.service.GunashoUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
                import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author User
 */
@Configuration
@EnableWebSecurity
public class WebConfig {
     @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private GunashoUserDetailService userDetailsService;

      private static final String[] SECURED_URLs = {"/gunasho/admin/**"};
      private static final String[] SECURED_USERURLs = {"/gunasho/user/**"};
      private static final String[] SECURED_USERADMINURLs = {"/gunasho/editgunasho/{id}","/gunasho/editgunasho","/gunasho/getallgunasho",};
     private static final String[] SECURED_ADMINMODERATORURLs = {"/gunasho/editgunashocompletedate/{id}","/gunasho/getallgunasho"};

    private static final String[] UN_SECURED_URLs = {
            
            
        "/gunasho/authenticate/**",
        "/gunasho/files/{fileName}",
            "/gunasho/adminNotification/{gunashoId}",
            "/gunasho/adminNotification/user/{userId}",
            "/gunasho/user/{userId}/seenStatus",
            "/gunasho/user/{userId}/unseennumber",
            "/gunasho/getByEmail/{email}"
    };
  @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
     @Bean
    public AuthenticationProvider authenticationProvider(){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService( userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
       return authenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
//               http.csrf(csrf-> csrf.disable()).formLogin().disable()
//                .authorizeHttpRequests()
//                .requestMatchers(UN_SECURED_URLs).permitAll().and()
//                .authorizeHttpRequests().requestMatchers(SECURED_URLs)
//                .hasAuthority("ADMIN").anyRequest().authenticated()
//                .and().authorizeHttpRequests().requestMatchers(SECURED_USERURLs).hasAuthority("USER").anyRequest().authenticated()
//                .and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore( authenticationFilter, UsernamePasswordAuthenticationFilter.class);
                http.securityMatcher("/**")
				.authorizeHttpRequests(user->user.requestMatchers(UN_SECURED_URLs).permitAll()
                        .requestMatchers(SECURED_USERURLs).
                                hasAuthority("User").requestMatchers(SECURED_USERADMINURLs).
                                     hasAnyAuthority("Admin","User").
                                        requestMatchers(SECURED_ADMINMODERATORURLs).
                                         hasAnyAuthority("Admin","Moderator")
                                .
                                requestMatchers(SECURED_URLs).hasAuthority("Admin").anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable()).formLogin(form->form.disable())
                        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider()).addFilterBefore( authenticationFilter, UsernamePasswordAuthenticationFilter.class);
				
				
                
        
        return http.build(); 
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
}
