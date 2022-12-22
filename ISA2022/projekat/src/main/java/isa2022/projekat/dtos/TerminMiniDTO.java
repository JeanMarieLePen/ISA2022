package isa2022.projekat.dtos;

import java.time.LocalDateTime;

public class TerminMiniDTO {
	private LocalDateTime pocetakTermina;
	private LocalDateTime krajTermina;
	
	
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
	public TerminMiniDTO(LocalDateTime pocetakTermina, LocalDateTime krajTermina) {
		super();
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
	}
	
	

}
