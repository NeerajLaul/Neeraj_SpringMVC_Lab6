package com.gl.StudentAuth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.StudentAuth.service.StudentServiceImpl;

@Configuration
public class WebSecurityConfig {

	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new UserDetailsServiceImpl();
	    }
	     
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	     
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	         
	        return authProvider;
	        
	    }
	 
	    //Authentication
//	    @Override
//	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.authenticationProvider(authenticationProvider());
//	    }
	    
	    //Authorization
//	    @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	    	        http.authorizeRequests()
//	    	            .antMatchers("/","/books/save","/books/showFormForAdd","/books/403").hasAnyAuthority("USER","ADMIN")
//	    	            .antMatchers("/books/showFormForUpdate","/books/delete").hasAuthority("ADMIN")
//	    	            .anyRequest().authenticated()
//	    	            .and()
//	    	            .formLogin().loginProcessingUrl("/login").successForwardUrl("/books/list").permitAll()
//	    	            .and()
//	    	            .logout().logoutSuccessUrl("/login").permitAll()
//	    	            .and()
//	    	            .exceptionHandling().accessDeniedPage("/books/403")
//	    	            .and()
//	    	            .cors().and().csrf().disable();
//	    }

	 
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    	http.authorizeRequests()
            .antMatchers("/","/books/save","/books/showFormForAdd","/books/403").hasAnyAuthority("USER","ADMIN")
            .antMatchers("/books/showFormForUpdate","/books/delete").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginProcessingUrl("/login").successForwardUrl("/books/list").permitAll()
            .and()
            .logout().logoutSuccessUrl("/login").permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/books/403")
            .and()
            .cors().and().csrf().disable();
	    	
	    	 http.authenticationProvider(authenticationProvider());
	    	 
	        return http.build();
	    }
	    

}
