package isa2022.projekat.mappers;

import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.KategorijaDTO;
import isa2022.projekat.model.data.Kategorija;

@Component
public class KategorijaMapper {

	public Kategorija fromDTO(KategorijaDTO kdto) {
		Kategorija k = new Kategorija();
		k.setNaziv(kdto.getNaziv());
		k.setPoeni(kdto.getPoeni());
		return k;
	}

	public KategorijaDTO toDTO(Kategorija k) {
		KategorijaDTO kdto = new KategorijaDTO();
		kdto.setNaziv(k.getNaziv());
		kdto.setPoeni(k.getPoeni());
		return kdto;
	}
}
