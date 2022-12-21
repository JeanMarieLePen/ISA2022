package isa2022.projekat.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.MedCentarDTO;
import isa2022.projekat.dtos.RegKorisnikDTO;
import isa2022.projekat.dtos.TerminDTO;
import isa2022.projekat.model.data.MedCentar;
import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.korisnici.RegKorisnik;
import isa2022.projekat.repositories.MedCentarRepository;
import isa2022.projekat.repositories.RegKorisnikRepository;

@Component
public class TerminMapper {
	@Autowired RegKorisnikRepository regRep;
	@Autowired 
	private RegKorisnikMapper regKorisnikMapper;
	@Autowired
	private MedCentarRepository medRep;
	public Termin fromDTO(TerminDTO dto) {
		Termin t = new Termin();
		t.setBrojMesta(dto.getBrojMesta());
		t.setBrSlobodnihMesta(dto.getBrSlobodnihMesta());
		t.setKrajTermina(dto.getKrajTermina());
		
		Collection<RegKorisnik> tempLista = new ArrayList<RegKorisnik>();
		if(dto.getPrijavljeniId().size()>0) {
		for(Long rk : dto.getPrijavljeniId()) {
			tempLista.add(regRep.getById(rk));
		}
		}
		t.setListaPrijavljenih(tempLista);
		t.setMedCentar(new MedCentar());
		t.setPocetakTermina(dto.getPocetakTermina());
		return t;
	}
	
	public TerminDTO toDTO(Termin t) {
		TerminDTO tdto = new TerminDTO();
		tdto.setId(t.getId());
		tdto.setBrojMesta(t.getBrojMesta());
		tdto.setBrSlobodnihMesta(t.getBrSlobodnihMesta());
		tdto.setKrajTermina(t.getKrajTermina());
		tdto.setPocetakTermina(t.getPocetakTermina());
		tdto.setMedCentarId(t.getMedCentar().getId());		
		tdto.setPrijavljeniId(new ArrayList<Long>());
		for(RegKorisnik r: t.getListaPrijavljenih())
			tdto.getPrijavljeniId().add(r.getId());
		return tdto;
	}
//	public TerminDTO toDTOLite(Termin t) {
//		TerminDTO tdto = new TerminDTO();
//		tdto.setId(t.getId());
//		tdto.setBrojMesta(t.getBrojMesta());
//		tdto.setBrSlobodnihMesta(t.getBrSlobodnihMesta());
//		tdto.setKrajTermina(t.getKrajTermina());
//		tdto.setPocetakTermina(t.getPocetakTermina());
//		tdto.setMedCentarId(t.getMedCentar().getId());		
//		
////		Collection<RegKorisnikDTO> tempLista = new ArrayList<RegKorisnikDTO>();
////		for(RegKorisnik rk : t.getListaPrijavljenih()) {
////			tempLista.add(regKorisnikMapper.toDTO(rk));
////		}
////		
////		tdto.setListaPrijavljenih(tempLista);
//		return tdto;
//	}
}
