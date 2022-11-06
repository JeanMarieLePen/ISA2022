package isa2022.projekat.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.ZahtevRezervacijaTerminaDTO;
import isa2022.projekat.model.data.ZahtevRezervacijaTermina;

@Component
public class ZahtevRezervacijaTerminaMapper {
	
	@Autowired
	private RegKorisnikMapper regKorisnikMapper;
	
	public ZahtevRezervacijaTermina fromDTO(ZahtevRezervacijaTerminaDTO z) {
		ZahtevRezervacijaTermina temp = new ZahtevRezervacijaTermina();
		temp.setKorisnik(regKorisnikMapper.fromDTO(z.getKorisnik()));
		temp.setQrCode(z.getQrCode());
		temp.setStatus(z.getStatus());
		return temp;
	}
	public ZahtevRezervacijaTerminaDTO toDTO(ZahtevRezervacijaTermina z) {
		ZahtevRezervacijaTerminaDTO temp = new ZahtevRezervacijaTerminaDTO();
		temp.setStatus(z.getStatus());
		temp.setQrCode(z.getQrCode());
		temp.setKorisnik(regKorisnikMapper.toDTO(z.getKorisnik()));
		return temp;
	}

}
