package isa2022.projekat.dtos;

import isa2022.projekat.model.korisnici.RegKorisnik;

public class UpitnikDTO {
	private Long id;
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
	
	private RegKorisnikDTO korisnik;
	
	public UpitnikDTO() {
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

	public RegKorisnikDTO getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(RegKorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}
	
	
}
