package isa2022.projekat.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa2022.projekat.dtos.MedCentarDTO;
import isa2022.projekat.mappers.MedCentarMapper;
import isa2022.projekat.model.data.MedCentar;
import isa2022.projekat.repositories.MedCentarRepository;

@Service
public class MedCentarService {

	@Autowired
	private MedCentarRepository medCentarRepository;
	@Autowired
	private MedCentarMapper medCentarMapper;
	
	public Collection<MedCentarDTO> getAll(){
		Collection<MedCentar> lista = this.medCentarRepository.findAll();
		if(!lista.isEmpty()) {
			Collection<MedCentarDTO> retLista = new ArrayList<MedCentarDTO>();
			for(MedCentar md : lista) {
				retLista.add(medCentarMapper.toDTO(md));
			}
			return retLista;
		}
		return null;
	}
}
