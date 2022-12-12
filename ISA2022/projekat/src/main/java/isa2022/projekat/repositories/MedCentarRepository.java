package isa2022.projekat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa2022.projekat.model.data.MedCentar;

public interface MedCentarRepository extends JpaRepository<MedCentar, Long> {

	List<MedCentar> findAllByTermini_ListaPrijavljenih_Id(Long userId);

}
