package isa2022.projekat.services;

import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import isa2022.projekat.dtos.RegKorisnikDTO;
import isa2022.projekat.dtos.UpdateNalogDTO;
import isa2022.projekat.dtos.UpitnikDTO;
import isa2022.projekat.mappers.RegKorisnikMapper;
import isa2022.projekat.mappers.UpitnikMapper;
import isa2022.projekat.model.data.Upitnik;
import isa2022.projekat.model.korisnici.RegKorisnik;
import isa2022.projekat.repositories.RegKorisnikRepository;
import isa2022.projekat.repositories.UpitnikRepository;

@Service
public class RegKorisnikService {

	@Autowired
	private RegKorisnikRepository regKorisnikRepository;
	@Autowired
	private UpitnikMapper upitnikMapper;
	@Autowired
	private UpitnikRepository upitnikRepository;
	@Autowired
	RegKorisnikMapper regMap;

	public UpitnikDTO submitUpitnik(UpitnikDTO u, Long id) {
		RegKorisnik rk = this.regKorisnikRepository.findById(id).orElse(null);
		if (rk == null) {
			return null;
		}
		if (rk.getUpitnik() != null) {
			return null;
		}
		Upitnik temp = this.upitnikMapper.fromDTO(u);
		rk.setUpitnik(temp);
		upitnikRepository.save(temp);
		return u;
	}

	public Boolean checkUpitnik(Long id) {
		RegKorisnik rk = this.regKorisnikRepository.findById(id).orElse(null);
		if (rk != null) {
			if (rk.getUpitnik() != null) {
				return true;
			}
		}
		return false;
	}

	public RegKorisnikDTO getRegKorisnik(Long id) {
		return regMap.toDTO(regKorisnikRepository.getById(id));
	}

	public RegKorisnikDTO update(UpdateNalogDTO dto) {
		RegKorisnik k = regKorisnikRepository.getOneByEmail(dto.getKorDTO().getEmail());
		k.setIme(dto.getKorDTO().getIme());
		k.setPrezime(dto.getKorDTO().getPrezime());
		k.setAdresa(dto.getKorDTO().getAdresa());
		k.setDatumRodjenja(dto.getKorDTO().getDatumRodjenja());

		// ako je menjao sifru, ovaj deo koda se izvrsava
		if (dto.getNovaSifraDTO() != null && !dto.getNovaSifraDTO().getNovaSifra().equals("")) {
			System.out.println("menjanaSifra");
			BCryptPasswordEncoder t = new BCryptPasswordEncoder();
			if (BCrypt.checkpw(dto.getNovaSifraDTO().getStaraSifra(), k.getLozinka())) {
				String pw = t.encode(dto.getNovaSifraDTO().getNovaSifra());
				k.setLozinka(pw);
			} else {
				return null;
			}
		}
		RegKorisnik osvezeni=regKorisnikRepository.saveAndFlush(k);
		return regMap.toDTO(osvezeni);
	}

	public Boolean deleteRegKor(Long id) {
		RegKorisnik k = regKorisnikRepository.getById(id);
		Long aktivneRezervacije=k.getZahtevRezervacijaTermina().stream().filter(x->x.getStatus().equals("rezervisan")).count();
		if (aktivneRezervacije.equals(0L)) {
			//? sta ukloniti od podataka?
			return true;
		}else {
			return false;
		}
	}
}
