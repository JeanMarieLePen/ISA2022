package isa2022.projekat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import isa2022.projekat.model.data.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {
	Kategorija findByNaziv(String naziv);
}
