package isa2022.projekat.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.MedCentarDTO;
import isa2022.projekat.dtos.MedRadnikDTO;
import isa2022.projekat.dtos.TerminDTO;
import isa2022.projekat.model.data.MedCentar;
import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.korisnici.MedRadnik;
@Component
public class MedCentarMapper {
	
	@Autowired
	private MedRadnikMapper medRadnikMapper;
	@Autowired
	private RadnoVremeMapper radnoVremeMapper;
	@Autowired TerminMapper terminMapper;

	public MedCentarDTO toDTO(MedCentar mc) {
		MedCentarDTO temp = new MedCentarDTO();
		temp.setAbGrupa(mc.getAbGrupa());
		temp.setAdresa(mc.getAdresa());
		temp.setaGrupa(mc.getaGrupa());
		temp.setbGrupa(mc.getbGrupa());
		
		Collection<MedRadnikDTO> tempLista = new ArrayList<MedRadnikDTO>();
		
		for(MedRadnik md : mc.getListaZaposlenih()) {
			tempLista.add(medRadnikMapper.toDTO(md));
		}
		temp.setListaZaposlenih(tempLista);
		temp.setNaziv(mc.getNaziv());
		temp.setNultaGrupa(mc.getNultaGrupa());
		temp.setOcena(mc.getOcena());
		temp.setOpis(mc.getOpis());
		
		
		temp.setRadnoVreme(radnoVremeMapper.toDTO(mc.getRadnoVreme()));
		
		Collection<TerminDTO> tempTermini = new ArrayList<TerminDTO>();
		for(Termin t : mc.getTermini()) {
			tempTermini.add(terminMapper.toDTOLite(t));
		}
		
		temp.setTermini(tempTermini);
		return temp;
	}
	
	public MedCentar fromDTO(MedCentarDTO mdto) {
		MedCentar temp = new MedCentar();
		temp.setAbGrupa(mdto.getAbGrupa());
		temp.setAdresa(mdto.getAdresa());
		temp.setaGrupa(mdto.getaGrupa());
		temp.setbGrupa(mdto.getbGrupa());
		
		Collection<MedRadnik> tempLista = new ArrayList<MedRadnik>();
		
		for(MedRadnikDTO md : mdto.getListaZaposlenih()) {
			tempLista.add(medRadnikMapper.fromDTO(md));
		}
		temp.setListaZaposlenih(tempLista);
		temp.setNaziv(mdto.getNaziv());
		temp.setNultaGrupa(mdto.getNultaGrupa());
		temp.setOcena(mdto.getOcena());
		temp.setOpis(mdto.getOpis());
		
		
		temp.setRadnoVreme(radnoVremeMapper.fromDTO(mdto.getRadnoVreme()));
		
		Collection<Termin> tempTermini = new ArrayList<Termin>();
		for(TerminDTO t : mdto.getTermini()) {
			tempTermini.add(terminMapper.fromDTO(t));
		}
		
		temp.setTermini(tempTermini);
		return temp;
	}
}
