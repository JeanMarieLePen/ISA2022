package isa2022.projekat.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.RegKorisnikDTO;
import isa2022.projekat.dtos.TerminDTO;
import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.korisnici.RegKorisnik;

@Component
public class TerminMapper {

	@Autowired 
	private RegKorisnikMapper regKorisnikMapper;
	@Autowired
	private MedCentarMapper medCentarMapper;
	public Termin fromDTO(TerminDTO dto) {
		Termin t = new Termin();
		t.setBrojMesta(dto.getBrojMesta());
		t.setBrSlobodnihMesta(dto.getBrSlobodnihMesta());
		t.setKrajTermina(dto.getKrajTermina());
		
		Collection<RegKorisnik> tempLista = new ArrayList<RegKorisnik>();
		for(RegKorisnikDTO rk : dto.getListaPrijavljenih()) {
			tempLista.add(regKorisnikMapper.fromDTO(rk));
		}
		t.setListaPrijavljenih(tempLista);
		t.setMedCentar(medCentarMapper.fromDTO(dto.getMedCentar()));
		t.setPocetakTermina(dto.getPocetakTermina());
		return t;
	}
	
	public TerminDTO toDTO(Termin t) {
		TerminDTO tdto = new TerminDTO();
		tdto.setBrojMesta(t.getBrojMesta());
		tdto.setBrSlobodnihMesta(t.getBrSlobodnihMesta());
		tdto.setKrajTermina(t.getKrajTermina());
		tdto.setPocetakTermina(tdto.getPocetakTermina());
		tdto.setMedCentar(medCentarMapper.toDTO(t.getMedCentar()));
		
		Collection<RegKorisnikDTO> tempLista = new ArrayList<RegKorisnikDTO>();
		for(RegKorisnik rk : t.getListaPrijavljenih()) {
			tempLista.add(regKorisnikMapper.toDTO(rk));
		}
		
		tdto.setListaPrijavljenih(tempLista);
		return tdto;
	}
	public TerminDTO toDTOLite(Termin t) {
		TerminDTO tdto = new TerminDTO();
		tdto.setBrojMesta(t.getBrojMesta());
		tdto.setBrSlobodnihMesta(t.getBrSlobodnihMesta());
		tdto.setKrajTermina(t.getKrajTermina());
		tdto.setPocetakTermina(tdto.getPocetakTermina());
		//tdto.setMedCentar(medCentarMapper.toDTO(t.getMedCentar()));
		
//		Collection<RegKorisnikDTO> tempLista = new ArrayList<RegKorisnikDTO>();
//		for(RegKorisnik rk : t.getListaPrijavljenih()) {
//			tempLista.add(regKorisnikMapper.toDTO(rk));
//		}
//		
//		tdto.setListaPrijavljenih(tempLista);
		return tdto;
	}
}
