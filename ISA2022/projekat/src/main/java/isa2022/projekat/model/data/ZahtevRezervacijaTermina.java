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
import javax.persistence.Version;

import isa2022.projekat.model.korisnici.RegKorisnik;

@Entity
public class ZahtevRezervacijaTermina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	//SVA POLJA MORAJU BITI INICIJALIZOVANA NA 0 KAKO BI SE MOGAO IZVRSITI ROLLBACK VERZIJE UKOLIKO JE POTREBAN
	//U suprotnom greska: Unable to perform beforeTransactionCompletion callback: null;
	private Integer version;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
