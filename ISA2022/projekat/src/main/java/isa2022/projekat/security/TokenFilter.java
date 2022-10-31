package isa2022.projekat.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import isa2022.projekat.services.MyUserDetailsService;

@Component
public class TokenFilter extends OncePerRequestFilter{
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private GenerateJwt generateJwt;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
		final String authorizationHeader = request.getHeader("Authorization");
		String email = null;
		String jwt = null;
		System.out.println("AUTHORIZATION HEADER: " + request.getHeader("Authorization"));
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			email = generateJwt.extractEmail(jwt);
		}
		
		if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(email);
			if(generateJwt.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken upwToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				upwToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upwToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
