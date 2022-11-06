package isa2022.projekat.model.korisnici;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import isa2022.projekat.model.data.MedCentar;

@Entity
public class MedRadnik extends Korisnik{

	//ATRIBUTI
	@ManyToOne(fetch=FetchType.LAZY)
	private MedCentar medCentar;
	
	
//	@OneToMany
//	private Collection<Korisnik> korisnik;
	
	//KONSTRUKTORI
	public MedRadnik() {
		super();
	}
	public MedRadnik(MedCentar medCentar) {
		super();
		this.medCentar = medCentar;
	}
	//GETERI I SETERI
	public MedCentar getMedCentar() {
		return medCentar;
	}
	
	public void setMedCentar(MedCentar medCentar) {
		this.medCentar = medCentar;
	}
}
