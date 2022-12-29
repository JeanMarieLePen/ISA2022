package isa2022.projekat.repositories;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import isa2022.projekat.model.data.MedCentar;
import isa2022.projekat.model.korisnici.RegKorisnik;

public interface RegKorisnikRepository extends JpaRepository<RegKorisnik, Long> {
	
	@Lock(LockModeType.READ)
//	@Lock(LockModeType.PESSIMISTIC_READ)
	public RegKorisnik getOneByEmail(String email);
	
	@Lock(LockModeType.READ)
//	@Lock(LockModeType.PESSIMISTIC_READ)
	public MedCentar findOneByTermini_medCentar_Id(Long id);
	
	@Lock(LockModeType.READ)
//	@Lock(LockModeType.PESSIMISTIC_READ)
	public MedCentar findOneById(Long id);
	
	@Lock(LockModeType.READ)
//	@Lock(LockModeType.PESSIMISTIC_READ)
	Optional<RegKorisnik> findById(Long id);
	
	
	
	@Lock(LockModeType.READ)
//	@Lock(LockModeType.PESSIMISTIC_READ)
	RegKorisnik getById(Long id);
	
	@Lock(LockModeType.WRITE)
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	RegKorisnik saveAndFlush(RegKorisnik rk);
	
	@Lock(LockModeType.WRITE)
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	RegKorisnik save(RegKorisnik rk);
}
