package isa2022.projekat.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import isa2022.projekat.model.korisnici.Korisnik;
import isa2022.projekat.repositories.KorisnikRepository;

@Service
public class GenerateJwt {

	@Autowired
	private KorisnikRepository kRep;
	
	private String SECRET_KEY = "ISA2022";
	
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
	 }
	 private Claims extractAllClaims(String token) {
	    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	 }
	 private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
	 }
	 
	 public String generateToken(UserDetails userDetails) {
		 Map<String, Object> claims = new HashMap<>();
		 List<SimpleGrantedAuthority> tempLista = (List<SimpleGrantedAuthority>) userDetails.getAuthorities();
		 System.out.println("authorities: " + userDetails.getAuthorities());
		 try {
			 Korisnik k = this.kRep.findByEmail(userDetails.getUsername());
			 if(k!=null) {
				 claims.put("userid", k.getId());
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		try {
	        if(tempLista.size() > 0) {
	        	claims.put("authority", tempLista.get(0).getAuthority());
	        }
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
		
		return createToken(claims, userDetails.getUsername());
	 }
	 
	 private String createToken(Map<String, Object> claims, String subject) {
		 System.out.println("KREIRAN TOKEN");
		 //postavljanje vremena isteka i odabir algoritma potpisa
		 return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				 .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	 }
	 
	 
	 public Boolean validateToken(String token, UserDetails userDetails) {
		 final String email = this.extractEmail(token);
		 return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	 }
	 
}
