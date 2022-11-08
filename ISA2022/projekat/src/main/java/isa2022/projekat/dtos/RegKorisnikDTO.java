package isa2022.projekat.dtos;

import java.util.Collection;

public class RegKorisnikDTO extends KorisnikDTO{
	private int poeni;
	private KategorijaDTO kategorija;
	private int penali;
	private Collection<ZahtevRezervacijaTerminaDTO> zahtevRezervacijaTermina;
	private Collection<TerminDTO> termini;
	public RegKorisnikDTO() {
		super();
	}
	

	public Collection<TerminDTO> getTermini() {
		return termini;
	}


	public void setTermini(Collection<TerminDTO> termini) {
		this.termini = termini;
	}


	public int getPoeni() {
		return poeni;
	}
	public void setPoeni(int poeni) {
		this.poeni = poeni;
	}
	public KategorijaDTO getKategorija() {
		return kategorija;
	}
	public void setKategorija(KategorijaDTO kategorija) {
		this.kategorija = kategorija;
	}
	public Collection<ZahtevRezervacijaTerminaDTO> getZahtevRezervacijaTermina() {
		return zahtevRezervacijaTermina;
	}
	public void setZahtevRezervacijaTermina(Collection<ZahtevRezervacijaTerminaDTO> zahtevRezervacijaTermina) {
		this.zahtevRezervacijaTermina = zahtevRezervacijaTermina;
	}
	public int getPenali() {
		return penali;
	}
	public void setPenali(int penali) {
		this.penali = penali;
	}

}
