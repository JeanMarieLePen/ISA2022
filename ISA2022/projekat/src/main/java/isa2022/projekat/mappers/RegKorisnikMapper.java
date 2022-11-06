package isa2022.projekat.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.RegKorisnikDTO;
import isa2022.projekat.dtos.ZahtevRezervacijaTerminaDTO;
import isa2022.projekat.model.data.ZahtevRezervacijaTermina;
import isa2022.projekat.model.korisnici.RegKorisnik;

@Component
public class RegKorisnikMapper {
	@Autowired
	private KategorijaMapper kategorijaMapper;
	@Autowired
	private TerminMapper terminMapper;
	@Autowired 
	private ZahtevRezervacijaTerminaMapper zrtMapper;

	public RegKorisnik fromDTO(RegKorisnikDTO rdto) {
		RegKorisnik rk = new RegKorisnik();
		rk.setAdresa(rdto.getAdresa());
		rk.setDatumRodjenja(rdto.getDatumRodjenja());
		rk.setEmail(rdto.getEmail());
		rk.setIme(rdto.getIme());
		
		rk.setKategorija(kategorijaMapper.fromDTO(rdto.getKategorija()));
		rk.setKorIme(rdto.getKorIme());
		rk.setLozinka(rdto.getLozinka());
		rk.setPenali(rdto.getPenali());
		rk.setPoeni(rdto.getPoeni());
		rk.setPoslednjaDonacija(rdto.getPoslednjaDonacija());
		rk.setPrezime(rdto.getPrezime());
		rk.setStatusNaloga(rdto.getStatusNaloga());
		rk.setTermin(terminMapper.fromDTO(rdto.getTermin()));
		rk.setTipKorisnika(rdto.getTipKorisnika());
		
		Collection<ZahtevRezervacijaTermina> tempLista = new ArrayList<ZahtevRezervacijaTermina>();
		for(ZahtevRezervacijaTerminaDTO z : rdto.getZahtevRezervacijaTermina()) {
			tempLista.add(zrtMapper.fromDTO(z));
		}
		
		rk.setZahtevRezervacijaTermina(tempLista);
		
		
		return rk;
	}
	
	public RegKorisnikDTO toDTO(RegKorisnik rk) {
		RegKorisnikDTO rdto = new RegKorisnikDTO();
		
		rdto.setAdresa(rk.getAdresa());
		rdto.setDatumRodjenja(rk.getDatumRodjenja());
		rdto.setEmail(rk.getEmail());
		rdto.setIme(rk.getIme());
		
		rdto.setKategorija(kategorijaMapper.toDTO(rk.getKategorija()));
		rdto.setKorIme(rk.getKorIme());
		rdto.setLozinka(rk.getLozinka());
		rdto.setPenali(rk.getPenali());
		rdto.setPoeni(rk.getPoeni());
		rdto.setPoslednjaDonacija(rk.getPoslednjaDonacija());
		rdto.setPrezime(rk.getPrezime());
		rdto.setStatusNaloga(rk.getStatusNaloga());
		rdto.setTermin(terminMapper.toDTO(rk.getTermin()));
		rdto.setTipKorisnika(rk.getTipKorisnika());
		
		Collection<ZahtevRezervacijaTerminaDTO> tempLista = new ArrayList<ZahtevRezervacijaTerminaDTO>();
		for(ZahtevRezervacijaTermina z : rk.getZahtevRezervacijaTermina()) {
			tempLista.add(zrtMapper.toDTO(z));
		}
		
		rdto.setZahtevRezervacijaTermina(tempLista);
		
		return rdto;
	}
	
}
