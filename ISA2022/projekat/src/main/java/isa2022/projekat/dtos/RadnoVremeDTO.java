package isa2022.projekat.dtos;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RadnoVremeDTO {

	private LocalDateTime pocetak;
	private LocalDateTime kraj;
	
	private LocalTime pocetakPauze;
	private LocalTime krajPauze;
	
	public RadnoVremeDTO() {
		super();
	}
	public LocalDateTime getPocetak() {
		return pocetak;
	}
	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}
	public LocalDateTime getKraj() {
		return kraj;
	}
	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}
	public LocalTime getPocetakPauze() {
		return pocetakPauze;
	}
	public void setPocetakPauze(LocalTime pocetakPauze) {
		this.pocetakPauze = pocetakPauze;
	}
	public LocalTime getKrajPauze() {
		return krajPauze;
	}
	public void setKrajPauze(LocalTime krajPauze) {
		this.krajPauze = krajPauze;
	}
	
	
}
