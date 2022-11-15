package isa2022.projekat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import isa2022.projekat.services.MyUserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private TokenFilter tokenFilter;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//koristi myUserDetailsService za povlacenje korisnika iz baze na osnovu emaila
		auth.userDetailsService(myUserDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeRequests().antMatchers("/authenticate")
		.permitAll()
		.antMatchers("/registerUser").permitAll()
		.antMatchers("/activateAccount").permitAll()
		.antMatchers("/medcentar/**").permitAll()
//		.antMatchers("/regkorisnik/submitUpitnik/**").permitAll()
		.antMatchers("/regkorisnik/**").permitAll()
		.anyRequest().authenticated();
		//.anonymous().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManager();
	}

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		//za enkripciju passworda biramo BCrypt
		return new BCryptPasswordEncoder();
	}

}
