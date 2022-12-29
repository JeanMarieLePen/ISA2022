package isa2022.projekat.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.RegKorisnikDTO;
import isa2022.projekat.dtos.TerminDTO;
import isa2022.projekat.dtos.ZahtevRezervacijaTerminaDTO;
import isa2022.projekat.model.data.Termin;
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
		rk.setVersion(rdto.getVersion());
		rk.setKategorija(kategorijaMapper.fromDTO(rdto.getKategorija()));
		rk.setKorIme(rdto.getKorIme());
		rk.setLozinka(rdto.getLozinka());
		rk.setPenali(rdto.getPenali());
		rk.setPoeni(rdto.getPoeni());
		rk.setPoslednjaDonacija(rdto.getPoslednjaDonacija());
		rk.setPrezime(rdto.getPrezime());
		rk.setStatusNaloga(rdto.getStatusNaloga());
		
		Collection<Termin> terminiList = new ArrayList<Termin>();
		for(TerminDTO t : rdto.getTermini()) {
			terminiList.add(terminMapper.fromDTO(t));
		}
		
		rk.setTermini(terminiList);
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
		rdto.setVersion(rk.getVersion());
		rdto.setKategorija(kategorijaMapper.toDTO(rk.getKategorija()));
		rdto.setKorIme(rk.getKorIme());
		rdto.setLozinka(rk.getLozinka());
		rdto.setPenali(rk.getPenali());
		rdto.setPoeni(rk.getPoeni());
		rdto.setPoslednjaDonacija(rk.getPoslednjaDonacija());
		rdto.setPrezime(rk.getPrezime());
		rdto.setStatusNaloga(rk.getStatusNaloga());
		
		Collection<TerminDTO> listaTermina = new ArrayList<TerminDTO>();
		for(Termin t : rk.getTermini()) {
			listaTermina.add(terminMapper.toDTO(t));
		}
		rdto.setTermini(listaTermina);
		rdto.setTipKorisnika(rk.getTipKorisnika());
		
		Collection<ZahtevRezervacijaTerminaDTO> tempLista = new ArrayList<ZahtevRezervacijaTerminaDTO>();
		for(ZahtevRezervacijaTermina z : rk.getZahtevRezervacijaTermina()) {
			tempLista.add(zrtMapper.toDTO(z));
		}
		
		Collection<String> tempSlike = new ArrayList<String>();
		for(byte[] s : rk.getSlike()) {
			String tempSlika = Base64.getEncoder().encodeToString(s);
			tempSlike.add(tempSlika);
		}
		rdto.setSlike(tempSlike);
		
		rdto.setZahtevRezervacijaTermina(tempLista);
		
		return rdto;
	}
	
}
