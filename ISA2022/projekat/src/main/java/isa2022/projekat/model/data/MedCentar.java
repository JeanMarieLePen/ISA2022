package isa2022.projekat.model.data;

import java.time.LocalDateTime;
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import isa2022.projekat.model.korisnici.MedRadnik;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MedCentar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "bigserial", name = "id", updatable = false, unique=true)
	private Long id;
	
	private String naziv;
	private String adresa;
	private String opis;
	private int ocena;
	private int aGrupa;
	private int bGrupa;
	private int abGrupa;
	private int nultaGrupa;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="radnovreme_id", referencedColumnName = "id")
	private RadnoVreme radnoVreme;
	@OneToMany(mappedBy="medCentar", cascade=CascadeType.ALL, orphanRemoval=true)
	private Collection<Termin> termini;
	@OneToMany(mappedBy="medCentar", cascade=CascadeType.ALL, orphanRemoval = true)
	private Collection<MedRadnik> listaZaposlenih;
	
	
	public MedCentar() {
		super();
	}


	public MedCentar(Long id, String naziv, String adresa, String opis, int ocena, int aGrupa, int bGrupa, int abGrupa,
			int nultaGrupa, RadnoVreme radnoVreme, Collection<Termin> termini, Collection<MedRadnik> listaZaposlenih) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.ocena = ocena;
		this.aGrupa = aGrupa;
		this.bGrupa = bGrupa;
		this.abGrupa = abGrupa;
		this.nultaGrupa = nultaGrupa;
		this.radnoVreme = radnoVreme;
		this.termini = termini;
		this.listaZaposlenih = listaZaposlenih;
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


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}


	public int getOcena() {
		return ocena;
	}


	public void setOcena(int ocena) {
		this.ocena = ocena;
	}


	public int getaGrupa() {
		return aGrupa;
	}


	public void setaGrupa(int aGrupa) {
		this.aGrupa = aGrupa;
	}


	public int getbGrupa() {
		return bGrupa;
	}


	public void setbGrupa(int bGrupa) {
		this.bGrupa = bGrupa;
	}


	public int getAbGrupa() {
		return abGrupa;
	}


	public void setAbGrupa(int abGrupa) {
		this.abGrupa = abGrupa;
	}


	public int getNultaGrupa() {
		return nultaGrupa;
	}


	public void setNultaGrupa(int nultaGrupa) {
		this.nultaGrupa = nultaGrupa;
	}


	public RadnoVreme getRadnoVreme() {
		return radnoVreme;
	}


	public void setRadnoVreme(RadnoVreme radnoVreme) {
		this.radnoVreme = radnoVreme;
	}


	public Collection<Termin> getTermini() {
		return termini;
	}


	public void setTermini(Collection<Termin> termini) {
		this.termini = termini;
	}


	public Collection<MedRadnik> getListaZaposlenih() {
		return listaZaposlenih;
	}


	public void setListaZaposlenih(Collection<MedRadnik> listaZaposlenih) {
		this.listaZaposlenih = listaZaposlenih;
	}
	
	
	
}
