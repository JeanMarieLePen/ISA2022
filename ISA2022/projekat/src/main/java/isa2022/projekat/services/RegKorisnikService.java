package isa2022.projekat.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import isa2022.projekat.dtos.RegKorisnikDTO;
import isa2022.projekat.dtos.TerminDTO;
import isa2022.projekat.dtos.UpdateNalogDTO;
import isa2022.projekat.dtos.UpitnikDTO;
import isa2022.projekat.mappers.RegKorisnikMapper;
import isa2022.projekat.mappers.TerminMapper;
import isa2022.projekat.mappers.UpitnikMapper;
import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.data.Upitnik;
import isa2022.projekat.model.korisnici.RegKorisnik;
import isa2022.projekat.repositories.RegKorisnikRepository;
import isa2022.projekat.repositories.TerminRepository;
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
	@Autowired
	private TerminRepository terminRepository;
	@Autowired 
	private TerminMapper terminMapper;
	@Autowired
	private EntityManager entityManager;
	
	//obavezna anotacija @Transactional
	@Transactional 
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
	
	//obavezna anotacija @Transactional
	@Transactional 
	public UpitnikDTO editUpitnik(UpitnikDTO u, Long id) throws Exception  {
		
//		RegKorisnik rk = entityManager.find(RegKorisnik.class, id);
//		entityManager.lock(rk, LockModeType.OPTIMISTIC);
		RegKorisnik rk = this.regKorisnikRepository.findById(id).orElse(null);
		if(rk == null) {
			return null;
		}
		if(rk.getUpitnik() != null) {
			Upitnik temp = upitnikRepository.findById(rk.getUpitnik().getId()).orElse(null);
//			Upitnik temp = entityManager.find(Upitnik.class, rk.getUpitnik().getId());
//			entityManager.lock(temp, LockModeType.OPTIMISTIC);
			temp.setAdresa(u.getAdresa());
			temp.setBrojPrethodnihDonacija(u.getBrojPrethodnihDonacija());
			temp.setDatumRodjenja(u.getDatumRodjenja());
			temp.setFirma_Ili_Skola(u.getFirma_Ili_Skola());
			temp.setIme(u.getIme());
			temp.setImeRoditelja(u.getImeRoditelja());
			temp.setJmbg(u.getJmbg());
			temp.setKucniTelefon(u.getKucniTelefon());
			temp.setMesto(u.getMesto());
			temp.setMobTelefon(u.getMobTelefon());
			temp.setOpstina(u.getOpstina());
			temp.setPol(u.getPol());
			temp.setPoslovniTelefon(u.getPoslovniTelefon());
			temp.setPrezime(u.getPrezime());
			temp.setZanimanje(u.getZanimanje());
			System.out.println("VERZIJA JE SAD: " + temp.getVersion());
			Thread.sleep(10000);
			upitnikRepository.saveAndFlush(temp);
			return u;
		}
		return null;
	}
//	public Boolean checkUpitnik(Long id) {
//		RegKorisnik rk = this.regKorisnikRepository.findById(id).orElse(null);
//		if (rk != null) {
//			if (rk.getUpitnik() != null) {
//				return true;
//			}
//		}
//		return false;
//	}
	public UpitnikDTO checkUpitnik(Long id) {
		RegKorisnik rk = this.regKorisnikRepository.findById(id).orElse(null);
		if(rk != null) {
			if(rk.getUpitnik() != null) {
				Upitnik u = rk.getUpitnik();
				UpitnikDTO retVal = this.upitnikMapper.toDTO(u);
				
				return retVal;
			}
		}
		return null;
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
	public Collection<TerminDTO> getAllTerminiByKorisnik(Long id) {
		// TODO Auto-generated method stub
		RegKorisnik rk = this.regKorisnikRepository.findById(id).orElse(null);
		if(rk==null) {
			return null;
		}
		
		Collection<TerminDTO> retList = new ArrayList<TerminDTO>();
		for(Termin t:rk.getTermini()) {
			retList.add(this.terminMapper.toDTO(t));
		}
		return retList;
	}
}
