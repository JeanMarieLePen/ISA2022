package isa2022.projekat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa2022.projekat.dtos.UpitnikDTO;
import isa2022.projekat.mappers.UpitnikMapper;
import isa2022.projekat.model.data.Upitnik;
import isa2022.projekat.model.korisnici.Korisnik;
import isa2022.projekat.model.korisnici.RegKorisnik;
import isa2022.projekat.repositories.KorisnikRepository;
import isa2022.projekat.repositories.RegKorisnikRepository;
import isa2022.projekat.repositories.UpitnikRepository;

@Service
public class RegKorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;
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
	
	public UpitnikDTO editUpitnik(UpitnikDTO u, Long id) {
		
		RegKorisnik rk = this.regKorisnikRepository.findById(id).orElse(null);
		if(rk == null) {
			return null;
		}
		if(rk.getUpitnik() != null) {
			Upitnik temp = rk.getUpitnik();
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
					
			upitnikRepository.saveAndFlush(temp);
			return u;
		}
		return null;
	}
	
//	public Boolean checkUpitnik(Long id) {
//		RegKorisnik rk = this.regKorisnikRepository.findById(id).orElse(null);
//		if(rk != null) {
//			if(rk.getUpitnik() != null) {
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
}
