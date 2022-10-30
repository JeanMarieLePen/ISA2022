package isa2022.projekat.model.data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import isa2022.projekat.model.korisnici.RegKorisnik;

@Entity
public class ZahtevRezervacijaTermina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private RegKorisnik korisnik;
	
	private String qrCode;
	private String status;
	
//	private Upitnik upitnik;
	
	
	
	public ZahtevRezervacijaTermina() {
		super();
	}

	public ZahtevRezervacijaTermina(Long id, RegKorisnik korisnik, String qrCode, String status) {
	super();
	this.id = id;
	this.korisnik = korisnik;
	this.qrCode = qrCode;
	this.status = status;
}

	public Long getId() {
		return id;
	}
	
	public RegKorisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(RegKorisnik korisnik) {
		this.korisnik = korisnik;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
