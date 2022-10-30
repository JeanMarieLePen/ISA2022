package isa2022.projekat.model.korisnici;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
//Table ako zelimo da redefinisemo ime tabele
//@Table(name="korisnik_tbl")
//table_per_class strategija: sve podklase se mapiraju u posebne tabele
//sve podklase cuvaju SVA polja te klase plus ona koja su nasledjena od nadklase
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Korisnik {

	//ATRIBUTI
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	private String lozinka;
	private String korIme;
	private String email;
	
	private String ime;
	private String prezime;
	private String adresa;
	private LocalDate datumRodjenja;
	private LocalDate poslednjaDonacija;
	
	private TipKorisnika tipKorisnika;
	
	//KONSTRUKTORI
	
	
	public Korisnik() {
		super();
	}
	
	public Korisnik(Long id, String lozinka, String korIme, String email, String ime, String prezime, String adresa,
			LocalDate datumRodjenja, LocalDate poslednjaDonacija, TipKorisnika tipKorisnika) {
		super();
		this.id = id;
		this.lozinka = lozinka;
		this.korIme = korIme;
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.datumRodjenja = datumRodjenja;
		this.poslednjaDonacija = poslednjaDonacija;
		this.tipKorisnika = tipKorisnika;
	}

	//GETERI I SETERI
	
	public Long getId() {
		return id;
	}
	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getKorIme() {
		return korIme;
	}
	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public LocalDate getPoslednjaDonacija() {
		return poslednjaDonacija;
	}
	public void setPoslednjaDonacija(LocalDate poslednjaDonacija) {
		this.poslednjaDonacija = poslednjaDonacija;
	}
	
	
}
