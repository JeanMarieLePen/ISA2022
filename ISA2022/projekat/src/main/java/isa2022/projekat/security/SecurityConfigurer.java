package isa2022.projekat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import isa2022.projekat.services.MyUserDetailsService;

public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//koristi myUserDetailsService za povlacenje korisnika iz baze na osnovu emaila
		auth.userDetailsService(myUserDetailsService);
	}
	
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManager();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		//za enkripciju passworda biramo BCrypt
		return new BCryptPasswordEncoder();
	}

}
