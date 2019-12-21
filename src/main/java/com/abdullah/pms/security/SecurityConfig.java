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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
		
//		.antMatchers("/rest/**")
//		.access("hasRole('ROLE_USER')")
		.antMatchers("/give-attendance")
		.permitAll()
		.antMatchers("/registration")
		.access("hasRole('ROLE_ADMIN')")
		.antMatchers("/leavereq")
		.access("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ACCOUNTANT') ")
		.antMatchers("/loanreq")
		.access("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ACCOUNTANT') ")
		.antMatchers("/ad_leave")
		.access("hasRole('ROLE_ADMIN')")
		.antMatchers("/ad_loan")
		.access("hasRole('ROLE_ADMIN')")
		.antMatchers("/payment_permission")
		.access("hasRole('ROLE_ADMIN')")
		.antMatchers("/give_salary")
		.access("hasRole('ROLE_ACCOUNTANT')")
		//.access("hasRole('READER')")
		.antMatchers("/**").permitAll().and()
				.formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/", true)
				.permitAll()
				.and()
//		          .logout()
//		            .logoutSuccessUrl("/")
				.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll()
                
                .and().csrf().disable()
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
