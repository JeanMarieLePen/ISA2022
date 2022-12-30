package isa2022.projekat.mappers;

import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.LokacijaDTO;
import isa2022.projekat.model.data.Lokacija;

@Component
public class LokacijaMapper {

	public Lokacija fromDTO(LokacijaDTO ldto) {
		Lokacija l = new Lokacija();
		l.setLat(ldto.getLat());
		l.setLng(ldto.getLng());
		return l;
	}
	public LokacijaDTO toDTO(Lokacija l) {
		LokacijaDTO ldto = new LokacijaDTO();
		ldto.setId(l.getId());
		ldto.setLat(l.getLat());
		ldto.setLng(l.getLng());
		return ldto;
	}
}
