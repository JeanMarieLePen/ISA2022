package isa2022.projekat.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.PorudzbinaDTO;
import isa2022.projekat.model.data.Porudzbina;

@Component
public class PorudzbinaMapper {

	@Autowired
	private MedCentarMapper medCentarMapper;
	@Autowired
	private LokacijaMapper lokacijaMapper;
	public PorudzbinaDTO toDTO(Porudzbina p) {
		PorudzbinaDTO temp = new PorudzbinaDTO();
		temp.setId(p.getId());
		temp.setKolicinaABGrupa(p.getKolicinaABGrupa());
		temp.setKolicinaAGrupa(p.getKolicinaAGrupa());
		temp.setKolicinaBGrupa(p.getKolicinaBGrupa());
		temp.setKolicinaNultaGrupa(p.getKolicinaNultaGrupa());
		temp.setLokacijaDo(this.lokacijaMapper.toDTO(p.getLokacijaDo()));
		temp.setLokacijaOd(this.lokacijaMapper.toDTO(p.getLokacijaOd()));
		temp.setMedCentarOd(this.medCentarMapper.toDTO(p.getMedCentarOd()));
		temp.setMedCentarZa(this.medCentarMapper.toDTO(p.getMedCentarZa()));
		temp.setDatumIVreme(p.getDatumIVreme());
		return temp;
	}
	
	public Porudzbina fromDTO(PorudzbinaDTO dto) {
		Porudzbina p = new Porudzbina();
		p.setDatumIVreme(dto.getDatumIVreme());
		p.setKolicinaABGrupa(dto.getKolicinaABGrupa());
		p.setKolicinaAGrupa(dto.getKolicinaAGrupa());
		p.setKolicinaBGrupa(dto.getKolicinaBGrupa());
		p.setKolicinaNultaGrupa(dto.getKolicinaNultaGrupa());
		p.setLokacijaDo(this.lokacijaMapper.fromDTO(dto.getLokacijaDo()));
		p.setLokacijaOd(this.lokacijaMapper.fromDTO(dto.getLokacijaOd()));
//		p.setMedCentarOd(this.medCentarMapper.fromDTO(dto.getMedCentarOd()));
//		p.setMedCentarZa(this.medCentarMapper.fromDTO(dto.getMedCentarZa()));
		return p;
	}
}
