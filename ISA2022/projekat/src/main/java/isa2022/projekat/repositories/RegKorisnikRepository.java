package isa2022.projekat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import isa2022.projekat.model.korisnici.RegKorisnik;

public interface RegKorisnikRepository extends JpaRepository<RegKorisnik, Long> {

}
