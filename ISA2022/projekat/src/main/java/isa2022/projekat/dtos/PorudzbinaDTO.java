package isa2022.projekat.dtos;

import java.time.LocalDateTime;

import isa2022.projekat.model.data.Lokacija;

public class PorudzbinaDTO {
	
	private Long id;
	private MedCentarDTO medCentarZa;
	private MedCentarDTO medCentarOd;
	private int kolicinaAGrupa;
	private int kolicinaBGrupa;
	private int kolicinaABGrupa;
	private int kolicinaNultaGrupa;
	private LokacijaDTO lokacijaOd;
	private LokacijaDTO lokacijaDo;
	private LocalDateTime datumIVreme;
	
	public PorudzbinaDTO() {
		super();
	}
	
	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MedCentarDTO getMedCentarZa() {
		return medCentarZa;
	}
	public void setMedCentarZa(MedCentarDTO medCentarZa) {
		this.medCentarZa = medCentarZa;
	}
	public MedCentarDTO getMedCentarOd() {
		return medCentarOd;
	}
	public void setMedCentarOd(MedCentarDTO medCentarOd) {
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
	public LokacijaDTO getLokacijaOd() {
		return lokacijaOd;
	}
	public void setLokacijaOd(LokacijaDTO lokacijaOd) {
		this.lokacijaOd = lokacijaOd;
	}
	public LokacijaDTO getLokacijaDo() {
		return lokacijaDo;
	}
	public void setLokacijaDo(LokacijaDTO lokacijaDo) {
		this.lokacijaDo = lokacijaDo;
	}
	
	
}
