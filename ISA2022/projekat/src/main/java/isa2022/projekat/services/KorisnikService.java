package isa2022.projekat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import isa2022.projekat.dtos.RegisterDTO;
import isa2022.projekat.mappers.RegisterMapper;
import isa2022.projekat.model.korisnici.Korisnik;
import isa2022.projekat.model.korisnici.StatusNaloga;
import isa2022.projekat.repositories.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	@Autowired 
	private RegisterMapper registerMapper;
	@Autowired
	private EmailService emailService;
	
	public RegisterDTO registerNewUser(RegisterDTO user){
		Korisnik k = this.korisnikRepository.findByEmail(user.getEmail());
		if(k != null) {
			return null;
		}
		Korisnik newUser = this.registerMapper.fromDTO(user);
		if(newUser != null) {
			BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
			newUser.setStatusNaloga(StatusNaloga.NA_CEKANJU);
			newUser.setLozinka(new String(enc.encode(user.getLozinka())));
			this.korisnikRepository.saveAndFlush(newUser);
			this.emailService.sendActivationEmail(newUser);
			return user;
		}
		return null;
		
	}
	
	public boolean activateAccount(String id, String pw) {
		Long tempId = Long.parseLong(id);
		if(this.korisnikRepository.existsById(tempId)) {
			throw new DataIntegrityViolationException("Neispravan ID!");
		}
		Korisnik k = korisnikRepository.getById(tempId);
		if(k.getStatusNaloga().equals(StatusNaloga.NA_CEKANJU)) {
			k.setStatusNaloga(StatusNaloga.AKTIVAN);
		}
		try {
			this.korisnikRepository.save(k);
		}catch(Exception e) {
			throw new DataIntegrityViolationException("Greska pri cuvanju!");
		}
		return true;
	}
	
	
}
