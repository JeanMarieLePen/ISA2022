package isa2022.projekat.model.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Kategorija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	//SVA POLJA MORAJU BITI INICIJALIZOVANA NA 0 KAKO BI SE MOGAO IZVRSITI ROLLBACK VERZIJE UKOLIKO JE POTREBAN
	//U suprotnom greska: Unable to perform beforeTransactionCompletion callback: null;
	private Integer version;
	private String naziv;
	private int poeni;
	
	public Kategorija() {
		super();
	}
	public Kategorija(Long id, String naziv, int poeni) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.poeni = poeni;
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
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getPoeni() {
		return poeni;
	}
	public void setPoeni(int poeni) {
		this.poeni = poeni;
	}
	
	
}
