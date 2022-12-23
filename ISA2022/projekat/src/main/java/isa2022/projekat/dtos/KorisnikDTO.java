package isa2022.projekat.dtos;

import java.time.LocalDate;
import java.util.Collection;

import isa2022.projekat.model.korisnici.StatusNaloga;
import isa2022.projekat.model.korisnici.TipKorisnika;

public class KorisnikDTO {
	private String lozinka;
	private String korIme;
	private String email;
	
	private String ime;
	private String prezime;
	private String adresa;
	private LocalDate datumRodjenja;
	private LocalDate poslednjaDonacija;
	
	private StatusNaloga statusNaloga;
	private TipKorisnika tipKorisnika;
	private Collection<String> slike;
	
	public KorisnikDTO() {
		super();
	}
	public Collection<String> getSlike() {
		return slike;
	}
	public void setSlike(Collection<String> slike) {
		this.slike = slike;
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
	public StatusNaloga getStatusNaloga() {
		return statusNaloga;
	}
	public void setStatusNaloga(StatusNaloga statusNaloga) {
		this.statusNaloga = statusNaloga;
	}
	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}
	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}
	@Override
	public String toString() {
		String email=	"Email	:	"+this.getEmail()+"\n";
		String korIme=	"KorIme	:	"+this.getKorIme()+"\n";
		String lozinka=	"Lozinka:	"+this.getLozinka()+"\n";
		String ime=		"Ime	:	"+this.getIme()+"\n";
		String prezime=	"Prezime: 	"+this.getPrezime()+"\n";
		String adresa=	"Adresa	:	"+this.getAdresa()+"\n";
		String datRodj= "DatRodj:	"+this.getDatumRodjenja()+"\n";
		String poslDav=	"PoslDav:	"+this.getPoslednjaDonacija()+"\n";
		return email+korIme+lozinka+ime+prezime+adresa+datRodj+poslDav;
		//return super.toString();
	}
	
}
