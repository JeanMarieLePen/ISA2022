package isa2022.projekat.dtos;

public class AuthenticationResponseDTO {
	private final String odgovor;

	public AuthenticationResponseDTO(String odgovor) {
		super();
		this.odgovor = odgovor;
	}

	public String getOdgovor() {
		return odgovor;
	}
	
	
}
