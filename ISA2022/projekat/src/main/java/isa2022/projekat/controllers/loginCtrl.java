package isa2022.projekat.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa2022.projekat.dtos.AuthenticationRequestDTO;

@RestController
@RequestMapping("")
public class loginCtrl {

	@GetMapping("/testEndpoint")
	public String testEndpoint() {
		return "TEST ENDPOINT:SUCCESS";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequest) throws Exception{
		try {
			System.out.println("Email adresa korisnika: " + authenticationRequest.getEmail() + ", PASSWORD KORISNIKA: " + authenticationRequest.getPassword());
		}catch(BadCredentialsException e) {
			throw new Exception("Netacna email adresa ili lozinka", e);
		}
		//zavrsena autentifikacija
		//dalje se izvrsava autorizacija
		
		return null;
	}
}
