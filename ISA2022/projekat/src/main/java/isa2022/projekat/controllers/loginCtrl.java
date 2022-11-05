package isa2022.projekat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa2022.projekat.dtos.AuthenticationRequestDTO;
import isa2022.projekat.dtos.AuthenticationResponseDTO;
import isa2022.projekat.dtos.RegisterDTO;
import isa2022.projekat.security.GenerateJwt;
import isa2022.projekat.services.KorisnikService;
import isa2022.projekat.services.MyUserDetailsService;

@RestController
@RequestMapping("")
public class loginCtrl {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private GenerateJwt generateJwt;
	
	@Autowired 
	private KorisnikService korisnikService;
	
	
	@GetMapping("/hello")
	public String testEndpoint() {
		return "TEST ENDPOINT:SUCCESS";
	}
	
	@GetMapping("/activateAccount")
	public String activateAccount(@RequestParam(name="id") String id, @RequestParam(name="secret")String pw, @RequestParam(name="exptime") String exptime) {
		return this.korisnikService.activateAccount(id, pw, exptime);
	}
	
	@PostMapping("/registerUser")
	public ResponseEntity<RegisterDTO> register(@RequestBody RegisterDTO user){
		RegisterDTO temp = this.korisnikService.registerNewUser(user);
		return (temp != null) ? new ResponseEntity<RegisterDTO>(temp, HttpStatus.OK) : new ResponseEntity<RegisterDTO>(HttpStatus.NOT_MODIFIED);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO rqst) throws Exception{
		
		try {
			System.out.println("Email adresa korisnika: " + rqst.getEmail() + ", PASSWORD KORISNIKA: " + rqst.getPassword());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(rqst.getEmail(), rqst.getPassword()));
		}catch(BadCredentialsException e) {
			throw new Exception("Netacna email adresa ili lozinka", e);
		}
		//zavrsena autentifikacija
		//dalje se izvrsava autorizacija
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(rqst.getEmail());
		final String jwt = generateJwt.generateToken(userDetails);
		System.out.println("TOKEN KOJI SE SALJE U ODGOVORU NA ZAHTEV ZA LOGIN: " + jwt);
		return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
	}
}
