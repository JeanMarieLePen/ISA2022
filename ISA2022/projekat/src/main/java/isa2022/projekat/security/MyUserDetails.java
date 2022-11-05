package isa2022.projekat.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import isa2022.projekat.model.korisnici.Korisnik;
import isa2022.projekat.model.korisnici.StatusNaloga;

public class MyUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6567733831163310188L;

	private Korisnik korisnik;
	
	public MyUserDetails() {
		super();
	}
	public MyUserDetails(Korisnik korisnik) {
		super();
		this.korisnik = korisnik;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		String tempRole = this.korisnik.getTipKorisnika().toString();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(tempRole));
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.korisnik.getLozinka();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.korisnik.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		if(this.korisnik.getStatusNaloga().equals(StatusNaloga.AKTIVAN)) {
			return true;
		}
		return false;
	}

}
