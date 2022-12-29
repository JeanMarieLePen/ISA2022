package isa2022.projekat.model.korisnici;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

import com.sun.istack.NotNull;

@Entity
//Table ako zelimo da redefinisemo ime tabele
//@Table(name="korisnik_tbl")
//table_per_class strategija: sve podklase se mapiraju u posebne tabele
//sve podklase cuvaju SVA polja te klase plus ona koja su nasledjena od nadklase
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
public class Korisnik {

	//ATRIBUTI
	@Id
	//tip generisanja mora se postaviti na identity da bi se korisniku koji se 
	//dodaje naknadno dodelio ID koji uzima u obzir prethodno uvezene podatke iz import.sql fajla
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//dodavanjem parametra columnDefinition i postavljanjem na serial/bigserial dobija se isto sto i u MySql-u koriscenjem AUTO_INCREMENT
	//odnosno dobija se da se podacima uvezenim iz import.sql fajla generise ID 
    @Column(columnDefinition = "bigserial", name = "id", updatable = false, unique=true)
	private Long id;
	
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	//SVA POLJA MORAJU BITI INICIJALIZOVANA NA 0 KAKO BI SE MOGAO IZVRSITI ROLLBACK VERZIJE UKOLIKO JE POTREBAN
	//U suprotnom greska: Unable to perform beforeTransactionCompletion callback: null;
	private Integer version;
	
	private String lozinka;
	private String korIme;
	private String email;
	
	private String ime;
	private String prezime;
	private String adresa;
	private LocalDate datumRodjenja;
	private LocalDate poslednjaDonacija;
	
	@Enumerated
	private StatusNaloga statusNaloga;
	@Enumerated
	private TipKorisnika tipKorisnika;
	
	@ElementCollection
	private Collection<byte[]> slike;
	
	//KONSTRUKTORI
	
	
	public Korisnik() {
		super();
	}
	public Korisnik(String lozinka, String korIme, String email, String ime, String prezime, String adresa,
			LocalDate datumRodjenja, LocalDate poslednjaDonacija, StatusNaloga statusNaloga, TipKorisnika tipKorisnika,
			Collection<byte[]> slike) {
		super();
		this.lozinka = lozinka;
		this.korIme = korIme;
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.datumRodjenja = datumRodjenja;
		this.poslednjaDonacija = poslednjaDonacija;
		this.statusNaloga = statusNaloga;
		this.tipKorisnika = tipKorisnika;
		this.slike = slike;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Collection<byte[]> getSlike() {
		return slike;
	}
	public void setSlike(Collection<byte[]> slike) {
		this.slike = slike;
	}
	public StatusNaloga getStatusNaloga() {
		return statusNaloga;
	}

	public void setStatusNaloga(StatusNaloga statusNaloga) {
		this.statusNaloga = statusNaloga;
	}
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
