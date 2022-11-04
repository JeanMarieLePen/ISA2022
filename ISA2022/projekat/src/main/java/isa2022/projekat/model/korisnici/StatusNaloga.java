package isa2022.projekat.model.korisnici;

public enum StatusNaloga {

	AKTIVAN(0),
	NA_CEKANJU(1),
	OBRISAN(2),
	BANOVAN(3);
	
	private final int status;
	private StatusNaloga(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
}
