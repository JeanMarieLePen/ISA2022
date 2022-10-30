package isa2022.projekat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import isa2022.projekat.model.korisnici.Korisnik;
import isa2022.projekat.repositories.KorisnikRepository;
import isa2022.projekat.security.MyUserDetails;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Korisnik tempKorisnik = korisnikRepository.findByEmail(username);
		if(tempKorisnik == null) {
			throw new UsernameNotFoundException("Nema korisnika sa tim emailom.");
		}
		//na osnovu pronadjenog korisnika kreira se myUserDetails klasa koja implementira
		//interfejs UserDetails(getPassword, getUsername, getAuthorities su kljucne metode) 
		MyUserDetails myUserDetails = new MyUserDetails(tempKorisnik);
		System.out.println("Korisnik: " + myUserDetails.getUsername() + ", ULOGA:" + myUserDetails.getAuthorities());
		return myUserDetails;
	}

}
