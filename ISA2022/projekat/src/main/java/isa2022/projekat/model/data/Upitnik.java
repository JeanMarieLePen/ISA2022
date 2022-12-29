package isa2022.projekat.model.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Version;

import isa2022.projekat.model.korisnici.RegKorisnik;

@Entity
public class Upitnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	//SVA POLJA MORAJU BITI INICIJALIZOVANA NA 0 KAKO BI SE MOGAO IZVRSITI ROLLBACK VERZIJE UKOLIKO JE POTREBAN
	//U suprotnom greska: Unable to perform beforeTransactionCompletion callback: null;
	private Integer version;
	private String ime;
	private String prezime;
	private String imeRoditelja;
	private String jmbg;
	private String datumRodjenja;
	private String pol;
	private String adresa;
	private String opstina;
	private String mesto;
	private String kucniTelefon;
	private String poslovniTelefon;
	private String mobTelefon;
	private String firma_Ili_Skola;
	private String zanimanje;
	private int brojPrethodnihDonacija;
	
//	private RegKorisnik korisnik;

	

	public Upitnik(Long id, String ime, String prezime, String imeRoditelja, String jmbg, String datumRodjenja,
			String pol, String adresa, String opstina, String mesto, String kucniTelefon, String poslovniTelefon,
			String mobTelefon, String firma_Ili_Skola, String zanimanje, int brojPrethodnihDonacija/*,
			RegKorisnik korisnik*/) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.imeRoditelja = imeRoditelja;
		this.jmbg = jmbg;
		this.datumRodjenja = datumRodjenja;
		this.pol = pol;
		this.adresa = adresa;
		this.opstina = opstina;
		this.mesto = mesto;
		this.kucniTelefon = kucniTelefon;
		this.poslovniTelefon = poslovniTelefon;
		this.mobTelefon = mobTelefon;
		this.firma_Ili_Skola = firma_Ili_Skola;
		this.zanimanje = zanimanje;
		this.brojPrethodnihDonacija = brojPrethodnihDonacija;
//		this.korisnik = korisnik;
	}


	public Upitnik() {
		super();
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getImeRoditelja() {
		return imeRoditelja;
	}


	public void setImeRoditelja(String imeRoditelja) {
		this.imeRoditelja = imeRoditelja;
	}


	public String getJmbg() {
		return jmbg;
	}


	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}


	public String getDatumRodjenja() {
		return datumRodjenja;
	}


	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}


	public String getPol() {
		return pol;
	}


	public void setPol(String pol) {
		this.pol = pol;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getOpstina() {
		return opstina;
	}


	public void setOpstina(String opstina) {
		this.opstina = opstina;
	}


	public String getMesto() {
		return mesto;
	}


	public void setMesto(String mesto) {
		this.mesto = mesto;
	}


	public String getKucniTelefon() {
		return kucniTelefon;
	}


	public void setKucniTelefon(String kucniTelefon) {
		this.kucniTelefon = kucniTelefon;
	}


	public String getPoslovniTelefon() {
		return poslovniTelefon;
	}


	public void setPoslovniTelefon(String poslovniTelefon) {
		this.poslovniTelefon = poslovniTelefon;
	}


	public String getMobTelefon() {
		return mobTelefon;
	}


	public void setMobTelefon(String mobTelefon) {
		this.mobTelefon = mobTelefon;
	}


	public String getFirma_Ili_Skola() {
		return firma_Ili_Skola;
	}


	public void setFirma_Ili_Skola(String firma_Ili_Skola) {
		this.firma_Ili_Skola = firma_Ili_Skola;
	}


	public String getZanimanje() {
		return zanimanje;
	}


	public void setZanimanje(String zanimanje) {
		this.zanimanje = zanimanje;
	}


	public int getBrojPrethodnihDonacija() {
		return brojPrethodnihDonacija;
	}


	public void setBrojPrethodnihDonacija(int brojPrethodnihDonacija) {
		this.brojPrethodnihDonacija = brojPrethodnihDonacija;
	}


//	public RegKorisnik getKorisnik() {
//		return korisnik;
//	}
//
//
//	public void setKorisnik(RegKorisnik korisnik) {
//		this.korisnik = korisnik;
//	}
	
	
}
