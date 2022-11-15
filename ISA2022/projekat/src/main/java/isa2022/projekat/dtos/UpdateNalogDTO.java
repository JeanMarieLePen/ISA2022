package isa2022.projekat.dtos;

public class UpdateNalogDTO    {
	private KorisnikDTO  korDTO;
	private NewSifraDTO novaSifraDTO;
	
	public UpdateNalogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateNalogDTO(KorisnikDTO korDTO, NewSifraDTO novaSifraDTO) {
		super();
		this.korDTO = korDTO;
		this.novaSifraDTO = novaSifraDTO;
	}

	public KorisnikDTO getKorDTO() {
		return korDTO;
	}

	public void setKorDTO(KorisnikDTO korDTO) {
		this.korDTO = korDTO;
	}

	public NewSifraDTO getNovaSifraDTO() {
		return novaSifraDTO;
	}

	public void setNovaSifraDTO(NewSifraDTO novaSifraDTO) {
		this.novaSifraDTO = novaSifraDTO;
	}

	@Override
	public String toString() {
		return korDTO.toString()+novaSifraDTO.toString();
	}
	
}
