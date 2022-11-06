package isa2022.projekat.mappers;

import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.RadnoVremeDTO;
import isa2022.projekat.model.data.RadnoVreme;

@Component
public class RadnoVremeMapper {

	public RadnoVreme fromDTO(RadnoVremeDTO rvdto) {
		RadnoVreme rv = new RadnoVreme();
		rv.setKraj(rvdto.getKraj());
		rv.setKrajPauze(rvdto.getKrajPauze());
		rv.setPocetak(rvdto.getPocetak());
		rv.setPocetakPauze(rvdto.getPocetakPauze());
		return rv;
	}
	public RadnoVremeDTO toDTO(RadnoVreme rv) {
		RadnoVremeDTO dto = new RadnoVremeDTO();
		dto.setKraj(rv.getKraj());
		dto.setKrajPauze(rv.getKrajPauze());
		dto.setPocetak(rv.getPocetak());
		dto.setPocetakPauze(rv.getPocetakPauze());
		return dto;
	}
	
}
