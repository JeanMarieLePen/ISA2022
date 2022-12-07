package isa2022.projekat.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.korisnici.RegKorisnik;

public interface TerminRepository extends JpaRepository<Termin, Long> {
	
}
