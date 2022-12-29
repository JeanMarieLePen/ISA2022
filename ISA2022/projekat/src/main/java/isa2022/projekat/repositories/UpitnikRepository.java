package isa2022.projekat.repositories;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import isa2022.projekat.model.data.Upitnik;

public interface UpitnikRepository extends JpaRepository<Upitnik, Long> {
	@Lock(LockModeType.READ)
//	@Lock(LockModeType.PESSIMISTIC_READ)
	Optional<Upitnik> findById(Long id);
	@Lock(LockModeType.READ)
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
//	@Override
//	<S extends Upitnik> S saveAndFlush(S u);
	Upitnik saveAndFlush(Upitnik u);
	@Lock(LockModeType.WRITE)
//	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Upitnik save(Upitnik u);
	
}
