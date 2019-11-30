package com.abdullah.pms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private ReaderRepository readerRepository;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		.antMatchers("/rest/**")
		.access("hasRole('ROLE_USER')")
		.antMatchers("/registration")
		.access("hasRole('ROLE_ADMIN')")
		.antMatchers("/leavereq")
		.access("hasRole('ROLE_EMPLOYEE')")
		.antMatchers("/loanreq")
		.access("hasRole('ROLE_EMPLOYEE')")
		//.access("hasRole('READER')")
		.antMatchers("/**").permitAll().and()
				.formLogin()
				
				//.loginPage("/login").failureUrl("/login?error=true")
				
				;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
	}
	
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
}
