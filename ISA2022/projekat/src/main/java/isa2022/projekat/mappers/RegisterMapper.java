package isa2022.projekat.mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.RegisterDTO;
import isa2022.projekat.model.korisnici.Korisnik;
import isa2022.projekat.model.korisnici.RegKorisnik;

@Component
public class RegisterMapper {
	
	public RegisterDTO toDTO(Korisnik k) {
		RegisterDTO kdto =  new RegisterDTO();
		kdto.setAdresa(k.getAdresa());
		kdto.setDatumRodjenja(k.getDatumRodjenja());
		kdto.setEmail(k.getEmail());
		kdto.setIme(k.getIme());
		kdto.setKorIme(k.getKorIme());
		kdto.setLozinka(k.getLozinka());
		kdto.setPoslednjaDonacija(k.getPoslednjaDonacija());
		kdto.setPrezime(k.getPrezime());
		kdto.setTipKorisnika(k.getTipKorisnika());
		
		return kdto;
	}
//	public Korisnik fromDTO(RegisterDTO kdto) {
//		Korisnik k = new Korisnik();
//		k.setAdresa(kdto.getAdresa());
//		k.setDatumRodjenja(kdto.getDatumRodjenja());
//		k.setEmail(kdto.getEmail());
//		k.setIme(kdto.getIme());
//		k.setKorIme(kdto.getKorIme());
//		k.setLozinka(kdto.getLozinka());
//		k.setPoslednjaDonacija(kdto.getPoslednjaDonacija());
//		k.setPrezime(kdto.getPrezime());
//		k.setTipKorisnika(kdto.getTipKorisnika());
//		
//		return k;
//	}
	
	public RegKorisnik fromDTO(RegisterDTO kdto) {
		RegKorisnik k = new RegKorisnik();
		k.setAdresa(kdto.getAdresa());
		k.setDatumRodjenja(kdto.getDatumRodjenja());
		k.setEmail(kdto.getEmail());
		k.setIme(kdto.getIme());
		k.setKorIme(kdto.getKorIme());
		k.setLozinka(kdto.getLozinka());
		k.setPoslednjaDonacija(kdto.getPoslednjaDonacija());
		k.setPrezime(kdto.getPrezime());
		k.setTipKorisnika(kdto.getTipKorisnika());
		k.setPenali(0);
		k.setPoeni(0);
		k.setZahtevRezervacijaTermina(null);
		k.setTermini(null);
		k.setUpitnik(null);
		
		Collection<byte[]> tempSlike = new ArrayList<byte[]>();
		for(String s: kdto.getSlike()) {
			byte[] data = Base64.getDecoder().decode(s.split(",")[1]);
			tempSlike.add(data);
		}
		k.setSlike(tempSlike);
		return k;
	}
}
