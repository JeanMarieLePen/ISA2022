package isa2022.projekat.model.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.springframework.lang.Nullable;

import isa2022.projekat.model.korisnici.MedRadnik;
import isa2022.projekat.model.korisnici.RegKorisnik;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Termin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "bigserial", name = "id", updatable = false, unique=true)
	private Long id;
	
	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	//SVA POLJA MORAJU BITI INICIJALIZOVANA NA 0 KAKO BI SE MOGAO IZVRSITI ROLLBACK VERZIJE UKOLIKO JE POTREBAN
	//U suprotnom greska: Unable to perform beforeTransactionCompletion callback: null;
	private Integer version;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private MedCentar medCentar;
	
	private LocalDateTime pocetakTermina;
	private LocalDateTime krajTermina;
	private int brojMesta;
	
//	@OneToMany(mappedBy="termin", cascade = CascadeType.ALL, orphanRemoval = true)
	
	@ManyToMany(mappedBy="termini")
	private Collection<RegKorisnik> listaPrijavljenih;
	
	private int brSlobodnihMesta;
	//private Collection<MedRadnik> listaRadnika;
	
	
	public Termin() {
		super();
	}
	public Termin(Long id, MedCentar medCentar, LocalDateTime pocetakTermina, LocalDateTime krajTermina, int brojMesta,
			Collection<RegKorisnik> listaPrijavljenih, int brSlobodnihMesta) {
		super();
		this.id = id;
		this.medCentar = medCentar;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.brojMesta = brojMesta;
		this.listaPrijavljenih = listaPrijavljenih;
		this.brSlobodnihMesta = brSlobodnihMesta;
	}
	public Termin( MedCentar medCentar, LocalDateTime pocetakTermina, LocalDateTime krajTermina, int brojMesta,
			 int brSlobodnihMesta) {
		super();
		this.medCentar = medCentar;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.brojMesta = brojMesta;
		this.listaPrijavljenih = new ArrayList<RegKorisnik>();
		this.brSlobodnihMesta = brSlobodnihMesta;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public MedCentar getMedCentar() {
		return medCentar;
	}
	public void setMedCentar(MedCentar medCentar) {
		this.medCentar = medCentar;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getPocetakTermina() {
		return pocetakTermina;
	}
	public void setPocetakTermina(LocalDateTime pocetakTermina) {
		this.pocetakTermina = pocetakTermina;
	}
	public LocalDateTime getKrajTermina() {
		return krajTermina;
	}
	public void setKrajTermina(LocalDateTime krajTermina) {
		this.krajTermina = krajTermina;
	}
	public int getBrojMesta() {
		return brojMesta;
	}
	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}
	public Collection<RegKorisnik> getListaPrijavljenih() {
		return listaPrijavljenih;
	}
	public void setListaPrijavljenih(Collection<RegKorisnik> listaPrijavljenih) {
		this.listaPrijavljenih = listaPrijavljenih;
	}
	public int getBrSlobodnihMesta() {
		return this.brSlobodnihMesta;
	}
	public void setBrSlobodnihMesta(int brSlobodnihMesta) {
		this.brSlobodnihMesta = brSlobodnihMesta;
	}
	
	
}
