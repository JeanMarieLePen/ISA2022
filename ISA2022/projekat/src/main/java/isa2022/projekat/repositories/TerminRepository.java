package isa2022.projekat.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.korisnici.RegKorisnik;

public interface TerminRepository extends JpaRepository<Termin, Long> {
	
	@Lock(LockModeType.READ)
//	@Lock(LockModeType.PESSIMISTIC_READ)
	Optional<Termin> findById(Long id);
	
	@Lock(LockModeType.WRITE)
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Termin saveAndFlush(Termin t);
	
	@Lock(LockModeType.WRITE)
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Termin save(Termin t);
}
