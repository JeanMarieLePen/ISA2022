package isa2022.projekat.repositories;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import isa2022.projekat.model.data.Lokacija;

public interface LokacijaRepository extends JpaRepository<Lokacija, Long>{
	@Lock(LockModeType.READ)
	Optional<Lokacija> findById(Long id);
	
	@Lock(LockModeType.WRITE)
	Lokacija saveAndFlush(Lokacija l);
	
	@Lock(LockModeType.WRITE)
	Lokacija save(Lokacija l);
}
