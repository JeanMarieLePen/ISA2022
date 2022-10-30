package isa2022.projekat.model.korisnici;

import java.time.LocalDate;
import java.util.Collection;

import isa2022.projekat.model.data.MedCentar;


public class MedRadnik extends Korisnik{

	//ATRIBUTI
	private MedCentar medCentar;
	private Collection<Korisnik> korisnik;
	
	//KONSTRUKTORI
	public MedRadnik() {
		super();
	}
	public MedRadnik(MedCentar medCentar, Collection<Korisnik> korisnik) {
		super();
		this.medCentar = medCentar;
		this.korisnik = korisnik;
	}
	
	//GETERI I SETERI
	public MedCentar getMedCentar() {
		return medCentar;
	}
	
	public void setMedCentar(MedCentar medCentar) {
		this.medCentar = medCentar;
	}
	public Collection<Korisnik> getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Collection<Korisnik> korisnik) {
		this.korisnik = korisnik;
	}
	
	
}
