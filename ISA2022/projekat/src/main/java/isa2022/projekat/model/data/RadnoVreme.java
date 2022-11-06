package isa2022.projekat.model.data;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RadnoVreme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "bigserial", name = "id", updatable = false, unique=true)
	private long id;
	private LocalDateTime pocetak;
	private LocalDateTime kraj;
	
	private LocalTime pocetakPauze;
	private LocalTime krajPauze;
	
	public RadnoVreme(long id, LocalDateTime pocetak, LocalDateTime kraj, LocalTime pocetakPauze, LocalTime krajPauze) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.pocetakPauze = pocetakPauze;
		this.krajPauze = krajPauze;
	}
	public RadnoVreme() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
