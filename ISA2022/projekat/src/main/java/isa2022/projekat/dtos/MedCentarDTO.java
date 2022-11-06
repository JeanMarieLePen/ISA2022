package isa2022.projekat.dtos;

import java.util.Collection;

public class MedCentarDTO {

	private String naziv;
	private String adresa;
	private String opis;
	private int ocena;
	private int aGrupa;
	private int bGrupa;
	private int abGrupa;
	private int nultaGrupa;
	
	private RadnoVremeDTO radnoVreme;
	private Collection<TerminDTO> termini;
	private Collection<MedRadnikDTO> listaZaposlenih;
	public MedCentarDTO() {
		super();
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
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public int getaGrupa() {
		return aGrupa;
	}
	public void setaGrupa(int aGrupa) {
		this.aGrupa = aGrupa;
	}
	public int getbGrupa() {
		return bGrupa;
	}
	public void setbGrupa(int bGrupa) {
		this.bGrupa = bGrupa;
	}
	public int getAbGrupa() {
		return abGrupa;
	}
	public void setAbGrupa(int abGrupa) {
		this.abGrupa = abGrupa;
	}
	public int getNultaGrupa() {
		return nultaGrupa;
	}
	public void setNultaGrupa(int nultaGrupa) {
		this.nultaGrupa = nultaGrupa;
	}
	public RadnoVremeDTO getRadnoVreme() {
		return radnoVreme;
	}
	public void setRadnoVreme(RadnoVremeDTO radnoVreme) {
		this.radnoVreme = radnoVreme;
	}
	public Collection<TerminDTO> getTermini() {
		return termini;
	}
	public void setTermini(Collection<TerminDTO> termini) {
		this.termini = termini;
	}
	public Collection<MedRadnikDTO> getListaZaposlenih() {
		return listaZaposlenih;
	}
	public void setListaZaposlenih(Collection<MedRadnikDTO> listaZaposlenih) {
		this.listaZaposlenih = listaZaposlenih;
	}
	
	
	
}
