package isa2022.projekat.model.korisnici;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import isa2022.projekat.model.data.Kategorija;
import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.data.Upitnik;
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
	
	
//	//JEDAN KORISNIK, JEDAN TERMIN
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "termin_id")
//	@Nullable
//	@ManyToOne(optional = true, fetch = FetchType.LAZY)
//	private Termin termin;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name="regkorisnik_termini", joinColumns = @JoinColumn(name="termin_id"),
	inverseJoinColumns = @JoinColumn(name="regkorisnik_id"))
	private Collection<Termin> termini;
	@Nullable
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="upitnik_id", referencedColumnName = "id")
	private Upitnik upitnik;
	
	//KONSTRUKTORI
	
	
	public RegKorisnik() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegKorisnik(int poeni, Kategorija kategorija, int penali,
			Collection<ZahtevRezervacijaTermina> zahtevRezervacijaTermina, Collection<Termin> termini,
			Upitnik upitnik) {
		super();
		this.poeni = poeni;
		this.kategorija = kategorija;
		this.penali = penali;
		this.zahtevRezervacijaTermina = zahtevRezervacijaTermina;
		this.termini = termini;
		this.upitnik = upitnik;
	}


	public Collection<Termin> getTermini() {
		return termini;
	}



	public void setTermini(Collection<Termin> termini) {
		this.termini = termini;
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
	public Upitnik getUpitnik() {
		return upitnik;
	}
	public void setUpitnik(Upitnik upitnik) {
		this.upitnik = upitnik;
	}
	
	
	
}
