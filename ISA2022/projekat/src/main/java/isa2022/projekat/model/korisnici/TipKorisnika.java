package isa2022.projekat.model.korisnici;

public enum TipKorisnika {

	ADMINISTRATOR(0),
	REGISTROVANI_KORISNIK(1),
	MEDICINSKO_OSOBLJE(2),
	ADMINISTRATOR_USTANOVE(3);
	
	private final int tip;
	private TipKorisnika(int tip) {
		this.tip = tip;
	}
	public int getTip() {
		return tip;
	}
}
