package isa2022.projekat.dtos;

public class NewSifraDTO {
	private String staraSifra;
	private String novaSifra;
	
	
	public NewSifraDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NewSifraDTO(String staraSifra, String novaSifra) {
		super();
		this.staraSifra = staraSifra;
		this.novaSifra = novaSifra;
	}

	
	public String getStaraSifra() {
		return staraSifra;
	}
	public void setStaraSifra(String staraSifra) {
		this.staraSifra = staraSifra;
	}
	public String getNovaSifra() {
		return novaSifra;
	}
	public void setNovaSifra(String novaSifra) {
		this.novaSifra = novaSifra;
	}

	@Override
	public String toString() {
		String nova=	"NovaSifra	:	"+this.getNovaSifra()+"\t";
		String stara=	"StaraSifra	:	"+this.getStaraSifra()+"\t";
		return nova+stara;
	}
	
}
