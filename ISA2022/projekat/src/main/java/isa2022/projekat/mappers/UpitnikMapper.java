package isa2022.projekat.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import isa2022.projekat.dtos.UpitnikDTO;
import isa2022.projekat.model.data.Upitnik;

@Component
public class UpitnikMapper {
	@Autowired
	private RegKorisnikMapper regKorisnikMapper;
	
	public Upitnik fromDTO(UpitnikDTO u) {
		Upitnik ret = new Upitnik();
		ret.setAdresa(u.getAdresa());
		ret.setBrojPrethodnihDonacija(u.getBrojPrethodnihDonacija());
		ret.setDatumRodjenja(u.getDatumRodjenja());
		ret.setFirma_Ili_Skola(u.getFirma_Ili_Skola());
		ret.setId(u.getId());
		ret.setIme(u.getIme());
		ret.setImeRoditelja(u.getImeRoditelja());
		ret.setJmbg(u.getJmbg());
		ret.setVersion(u.getVersion());
//		ret.setKorisnik(regKorisnikMapper.fromDTO(u.getKorisnik()));
		ret.setKucniTelefon(u.getKucniTelefon());
		ret.setMesto(u.getMesto());
		ret.setMobTelefon(u.getMobTelefon());
		ret.setOpstina(u.getOpstina());
		ret.setPol(u.getPol());
		ret.setPoslovniTelefon(u.getPoslovniTelefon());
		ret.setPrezime(u.getPrezime());
		ret.setZanimanje(u.getZanimanje());
		return ret;
	}
	
	public UpitnikDTO toDTO(Upitnik u) {
		UpitnikDTO ret = new UpitnikDTO();
		ret.setAdresa(u.getAdresa());
		ret.setBrojPrethodnihDonacija(u.getBrojPrethodnihDonacija());
		ret.setDatumRodjenja(u.getDatumRodjenja());
		ret.setFirma_Ili_Skola(u.getFirma_Ili_Skola());
		ret.setId(u.getId());
		ret.setIme(u.getIme());
		ret.setImeRoditelja(u.getImeRoditelja());
		ret.setJmbg(u.getJmbg());
		ret.setVersion(u.getVersion());
//		ret.setKorisnik(regKorisnikMapper.toDTO(u.getKorisnik()));
		ret.setKucniTelefon(u.getKucniTelefon());
		ret.setMesto(u.getMesto());
		ret.setMobTelefon(u.getMobTelefon());
		ret.setOpstina(u.getOpstina());
		ret.setPol(u.getPol());
		ret.setPoslovniTelefon(u.getPoslovniTelefon());
		ret.setPrezime(u.getPrezime());
		ret.setZanimanje(u.getZanimanje());
		return ret;
	}
}
