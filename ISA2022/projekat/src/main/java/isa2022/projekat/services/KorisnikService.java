package isa2022.projekat.services;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import isa2022.projekat.dtos.RegisterDTO;
import isa2022.projekat.mappers.RegisterMapper;
import isa2022.projekat.model.korisnici.Korisnik;
import isa2022.projekat.model.korisnici.RegKorisnik;
import isa2022.projekat.model.korisnici.StatusNaloga;
import isa2022.projekat.model.korisnici.TipKorisnika;
import isa2022.projekat.repositories.KategorijaRepository;
import isa2022.projekat.repositories.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	@Autowired 
	private RegisterMapper registerMapper;
	@Autowired
	private EmailService emailService;
	@Autowired
	private KategorijaRepository katRep;
	
	public RegisterDTO registerNewUser(RegisterDTO user){
		Korisnik k = this.korisnikRepository.findByEmail(user.getEmail());
		if(k != null) {
			return null;
		}
//		Korisnik newUser = this.registerMapper.fromDTO(user);
		RegKorisnik newUser = this.registerMapper.fromDTO(user);
		if(newUser != null) {
			BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
			newUser.setTipKorisnika(TipKorisnika.REGISTROVANI_KORISNIK);
			newUser.setKategorija(this.katRep.findByNaziv("BRONZA"));
			newUser.setStatusNaloga(StatusNaloga.NA_CEKANJU);
			newUser.setLozinka(new String(enc.encode(user.getLozinka())));
			this.korisnikRepository.saveAndFlush(newUser);
			this.emailService.sendActivationEmail(newUser);
			return user;
		}
		return null;
		
	}
	
	public String activateAccount(String id, String pw, String exptime) {
		Long tempId = Long.parseLong(id);
		LocalDateTime temp = LocalDateTime.now();
		LocalDateTime exp = LocalDateTime.parse(exptime);
		Duration duration = Duration.between(exp, temp);
		long minutes = duration.toMinutes();
		if(minutes > 30) {
			return "<html><p>Aktivacioni link je istekao(period vazenja 30 minuta). Nije moguce aktiviranje naloga.</p></html>";
		}
		if(!this.korisnikRepository.existsById(tempId)) {
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
		
		return "<html><p>NALOG USPESNO AKTIVIRAN. ULOGUJTE SE.</p></html>";
	}
	
	
}
