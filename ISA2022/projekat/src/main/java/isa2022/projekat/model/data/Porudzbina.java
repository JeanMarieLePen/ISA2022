package isa2022.projekat.model.data;

import java.time.LocalDateTime;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Porudzbina {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "bigserial", name="id", updatable = false, unique = true)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private MedCentar medCentarZa;
	@ManyToOne(fetch=FetchType.LAZY)
	private MedCentar medCentarOd;
	
	@Version
	private Integer version;
	
	private int kolicinaAGrupa;
	private int kolicinaBGrupa;
	private int kolicinaABGrupa;
	private int kolicinaNultaGrupa;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="lok_od", referencedColumnName="id")
	private Lokacija lokacijaOd;
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="lok_do", referencedColumnName="id")
	private Lokacija lokacijaDo;
	
	private LocalDateTime datumIVreme;
	
	
	public Porudzbina(Long id, MedCentar medCentarZa, MedCentar medCentarOd, int kolicinaAGrupa, int kolicinaBGrupa,
			int kolicinaABGrupa, int kolicinaNultaGrupa, Lokacija lokacijaOd, Lokacija lokacijaDo,
			LocalDateTime datumIVreme) {
		super();
		this.id = id;
		this.medCentarZa = medCentarZa;
		this.medCentarOd = medCentarOd;
		this.kolicinaAGrupa = kolicinaAGrupa;
		this.kolicinaBGrupa = kolicinaBGrupa;
		this.kolicinaABGrupa = kolicinaABGrupa;
		this.kolicinaNultaGrupa = kolicinaNultaGrupa;
		this.lokacijaOd = lokacijaOd;
		this.lokacijaDo = lokacijaDo;
		this.datumIVreme = datumIVreme;
	}

	public Porudzbina() {
		super();
	}
	
	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Lokacija getLokacijaOd() {
		return lokacijaOd;
	}
	public void setLokacijaOd(Lokacija lokacijaOd) {
		this.lokacijaOd = lokacijaOd;
	}
	public Lokacija getLokacijaDo() {
		return lokacijaDo;
	}
	public void setLokacijaDo(Lokacija lokacijaDo) {
		this.lokacijaDo = lokacijaDo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MedCentar getMedCentarZa() {
		return medCentarZa;
	}
	public void setMedCentarZa(MedCentar medCentarZa) {
		this.medCentarZa = medCentarZa;
	}
	public MedCentar getMedCentarOd() {
		return medCentarOd;
	}
	public void setMedCentarOd(MedCentar medCentarOd) {
		this.medCentarOd = medCentarOd;
	}
	public int getKolicinaAGrupa() {
		return kolicinaAGrupa;
	}
	public void setKolicinaAGrupa(int kolicinaAGrupa) {
		this.kolicinaAGrupa = kolicinaAGrupa;
	}
	public int getKolicinaBGrupa() {
		return kolicinaBGrupa;
	}
	public void setKolicinaBGrupa(int kolicinaBGrupa) {
		this.kolicinaBGrupa = kolicinaBGrupa;
	}
	public int getKolicinaABGrupa() {
		return kolicinaABGrupa;
	}
	public void setKolicinaABGrupa(int kolicinaABGrupa) {
		this.kolicinaABGrupa = kolicinaABGrupa;
	}
	public int getKolicinaNultaGrupa() {
		return kolicinaNultaGrupa;
	}
	public void setKolicinaNultaGrupa(int kolicinaNultaGrupa) {
		this.kolicinaNultaGrupa = kolicinaNultaGrupa;
	}
	
	
}
