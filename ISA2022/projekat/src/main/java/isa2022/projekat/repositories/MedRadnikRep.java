package isa2022.projekat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import isa2022.projekat.model.korisnici.MedRadnik;

public interface MedRadnikRep extends JpaRepository<MedRadnik, Long>{
}

