package isa2022.projekat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import isa2022.projekat.model.korisnici.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	Korisnik findByEmail(String email);	
}
