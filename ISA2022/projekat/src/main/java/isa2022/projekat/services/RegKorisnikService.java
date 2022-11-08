package isa2022.projekat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa2022.projekat.dtos.UpitnikDTO;
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
	
	public UpitnikDTO submitUpitnik(UpitnikDTO u, Long id) {
		RegKorisnik rk = this.regKorisnikRepository.findById(id).orElse(null);
		if(rk == null) {
			return null;
		}
		if(rk.getUpitnik() != null) {
			return null;
		}
		Upitnik temp = this.upitnikMapper.fromDTO(u);
		rk.setUpitnik(temp);
		upitnikRepository.save(temp);
		return u;
	}
	
	public Boolean checkUpitnik(Long id) {
		RegKorisnik rk = this.regKorisnikRepository.findById(id).orElse(null);
		if(rk != null) {
			if(rk.getUpitnik() != null) {
				return true;
			}
		}
		return false;
	}
}
