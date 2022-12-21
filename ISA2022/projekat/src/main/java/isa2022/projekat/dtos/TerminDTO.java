package isa2022.projekat.dtos;

import java.time.LocalDateTime;
import java.util.Collection;

public class TerminDTO {

	private Long id;
	private LocalDateTime pocetakTermina;
	private LocalDateTime krajTermina;
	private int brojMesta;
	private Long medCentarId ;
	private Collection<Long> prijavljeniId;
	
	private int brSlobodnihMesta;

	public TerminDTO(Long id, LocalDateTime pocetakTermina, LocalDateTime krajTermina, int brojMesta, Long medCentarId,
			Collection<Long> prijavljeniId, int brSlobodnihMesta) {
		super();
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.brojMesta = brojMesta;
		this.medCentarId = medCentarId;
		this.prijavljeniId = prijavljeniId;
		this.brSlobodnihMesta = brSlobodnihMesta;
	}


	public TerminDTO() {
		super();
	}
	

	public Long getMedCentarId() {
		return medCentarId;
	}


	public void setMedCentarId(Long medCentarId) {
		this.medCentarId = medCentarId;
	}


	public Collection<Long> getPrijavljeniId() {
		return prijavljeniId;
	}


	public void setPrijavljeniId(Collection<Long> prijavljeniId) {
		this.prijavljeniId = prijavljeniId;
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

	public int getBrSlobodnihMesta() {
		return brSlobodnihMesta;
	}

	public void setBrSlobodnihMesta(int brSlobodnihMesta) {
		this.brSlobodnihMesta = brSlobodnihMesta;
	}
	
	
}
