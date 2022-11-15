package isa2022.projekat.dtos;

import java.time.LocalDateTime;

public class PretragaDTO {
	private String naziv;
	private String adresa;
	private String ocena;
	LocalDateTime odDatuma;
	LocalDateTime doDatuma;
	
	public PretragaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PretragaDTO(String naziv, String adresa, String ocena, LocalDateTime odDatuma, LocalDateTime doDatuma) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.ocena = ocena;
		this.odDatuma = odDatuma;
		this.doDatuma = doDatuma;
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

	public String getOcena() {
		return ocena;
	}

	public void setOcena(String ocena) {
		this.ocena = ocena;
	}

	public LocalDateTime getOdDatuma() {
		return odDatuma;
	}

	public void setOdDatuma(LocalDateTime odDatuma) {
		this.odDatuma = odDatuma;
	}

	public LocalDateTime getDoDatuma() {
		return doDatuma;
	}

	public void setDoDatuma(LocalDateTime doDatuma) {
		this.doDatuma = doDatuma;
	}

	@Override
	public String toString() {
		String pretraga= "Naziv:\t"+this.getNaziv()+"\nAdresa:\t"+this.getAdresa()+"\nOcena:\t"+this.getOcena()+"\nOdDatuma:\t"+this.getOdDatuma()+"\nDoDatuma:\t"+this.getDoDatuma()+"\n";
		return pretraga;
		//return super.toString();
	}
	
	
	
}
