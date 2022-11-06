package isa2022.projekat.dtos;

import java.time.LocalDateTime;
import java.util.Collection;

public class TerminDTO {

	private MedCentarDTO medCentar;
	private LocalDateTime pocetakTermina;
	private LocalDateTime krajTermina;
	private int brojMesta;
	
	private Collection<RegKorisnikDTO> listaPrijavljenih;
	
	private int brSlobodnihMesta;

	public TerminDTO() {
		super();
	}

	public MedCentarDTO getMedCentar() {
		return medCentar;
	}

	public void setMedCentar(MedCentarDTO medCentar) {
		this.medCentar = medCentar;
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

	public Collection<RegKorisnikDTO> getListaPrijavljenih() {
		return listaPrijavljenih;
	}

	public void setListaPrijavljenih(Collection<RegKorisnikDTO> listaPrijavljenih) {
		this.listaPrijavljenih = listaPrijavljenih;
	}

	public int getBrSlobodnihMesta() {
		return brSlobodnihMesta;
	}

	public void setBrSlobodnihMesta(int brSlobodnihMesta) {
		this.brSlobodnihMesta = brSlobodnihMesta;
	}
	
	
}
