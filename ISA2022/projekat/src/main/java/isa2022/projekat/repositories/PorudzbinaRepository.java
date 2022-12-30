package isa2022.projekat.repositories;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import isa2022.projekat.model.data.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long>{

	@Lock(LockModeType.READ)
	Optional<Porudzbina> findById(Long id);
	
	@Lock(LockModeType.READ)
	Optional<Porudzbina> findByMedCentarOd_Id(Long idOd);
	
	@Lock(LockModeType.WRITE)
	Porudzbina saveAndFlush(Porudzbina p);
	
	@Lock(LockModeType.WRITE)
	Porudzbina save(Porudzbina p);
}
