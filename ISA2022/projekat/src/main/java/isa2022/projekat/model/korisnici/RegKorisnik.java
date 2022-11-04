package isa2022.projekat.model.korisnici;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import isa2022.projekat.model.data.Kategorija;
import isa2022.projekat.model.data.ZahtevRezervacijaTermina;
;

@Entity
@Table(name="REGKORISNIK")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class RegKorisnik extends Korisnik{
	
	//ATRIBUTI
	private int poeni;
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="kategorija_id", referencedColumnName="id")
	private Kategorija kategorija;
	private int penali;
	@OneToMany(mappedBy="korisnik", cascade=CascadeType.ALL, orphanRemoval = true)
	private Collection<ZahtevRezervacijaTermina> zahtevRezervacijaTermina;
	
	//KONSTRUKTORI
	
	
	public RegKorisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public RegKorisnik(int poeni, Kategorija kategorija, int penali,
			Collection<ZahtevRezervacijaTermina> zahtevRezervacijaTermina) {
		super();
		this.poeni = poeni;
		this.kategorija = kategorija;
		this.penali = penali;
		this.zahtevRezervacijaTermina = zahtevRezervacijaTermina;
	}

	//GETERI I SETERI
	public int getPoeni() {
		return poeni;
	}
	
	public void setPoeni(int poeni) {
		this.poeni = poeni;
	}
	public Kategorija getKategorija() {
		return kategorija;
	}
	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	public int getPenali() {
		return penali;
	}
	public void setPenali(int penali) {
		this.penali = penali;
	}
	public Collection<ZahtevRezervacijaTermina> getZahtevRezervacijaTermina() {
		return zahtevRezervacijaTermina;
	}
	public void setZahtevRezervacijaTermina(Collection<ZahtevRezervacijaTermina> zahtevRezervacijaTermina) {
		this.zahtevRezervacijaTermina = zahtevRezervacijaTermina;
	}
	
	
	
}
