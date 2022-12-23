package isa2022.projekat.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.lang.Collections;
import isa2022.projekat.dtos.CustomRezDTO;
import isa2022.projekat.dtos.MedCentarDTO;
import isa2022.projekat.dtos.PretragaDTO;
import isa2022.projekat.dtos.TerminDTO;
import isa2022.projekat.dtos.TerminMiniDTO;
import isa2022.projekat.mappers.MedCentarMapper;
import isa2022.projekat.mappers.TerminMapper;
import isa2022.projekat.model.data.MedCentar;
import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.korisnici.MedRadnik;
import isa2022.projekat.model.korisnici.RegKorisnik;
import isa2022.projekat.repositories.MedCentarRepository;
import isa2022.projekat.repositories.MedRadnikRep;
import isa2022.projekat.repositories.RegKorisnikRepository;
import isa2022.projekat.repositories.TerminRepository;

@Service
public class MedCentarService {

	@Autowired
	private MedCentarRepository medCentarRepository;
	@Autowired
	private MedCentarMapper medCentarMapper;
	
	@Autowired 
	private TerminMapper terminMapper;
	@Autowired 
	private TerminRepository terminRepository;
	@Autowired
	private RegKorisnikRepository regKorisnikRepository;
	@Autowired
	private EmailService emailService;
	@Autowired private MedRadnikRep mrRep;
	public Collection<MedCentarDTO> getAll(){
		Collection<MedCentar> lista = this.medCentarRepository.findAll();
		if(!lista.isEmpty()) {
			Collection<MedCentarDTO> retLista = new ArrayList<MedCentarDTO>();
			for(MedCentar md : lista) {
				retLista.add(medCentarMapper.toDTO(md));
			}
			return retLista;
		}
		return null;
	}
	@PersistenceContext private EntityManager entityManager;
	public Collection<MedCentarDTO> pretraga(PretragaDTO dto) {
		CriteriaBuilder cb= entityManager.getCriteriaBuilder();
		CriteriaQuery<MedCentar> query= cb.createQuery(MedCentar.class);
		Root<MedCentar> centar = query.from(MedCentar.class);
		List<Predicate> predicates= new ArrayList<Predicate>();//lista uslova

		if(!dto.getNaziv().equals(null) && !dto.getNaziv().trim().equals("")) predicates.add(cb.like(cb.lower(centar.get("naziv")), "%"+dto.getNaziv().trim().toLowerCase()+"%"));
		if(!dto.getAdresa().equals(null) && !dto.getAdresa().trim().equals("")) predicates.add( cb.like(cb.lower(centar.get("adresa")), "%"+dto.getAdresa().trim().toLowerCase()+"%"));

//		if(!dto.getAdresa().getDrzava().equals(null) && !dto.getAdresa().getDrzava().equals("")) {
//			predicates.add(cb.equal(cb.lower(avantura.get("adresa").get("drzava")), dto.getAdresa().getDrzava().toLowerCase()));
//		}
//		if(!dto.getAdresa().getMesto().equals(null) && !dto.getAdresa().getMesto().equals(""))  {
//			predicates.add(cb.equal(cb.lower(avantura.get("adresa").get("mesto")), dto.getAdresa().getMesto().toLowerCase()));
//		}
//		if(!dto.getAdresa().getUlica().equals(null) && !dto.getAdresa().getUlica().trim().equals("")){
//			predicates.add(cb.equal(cb.lower(avantura.get("adresa").get("ulica")), "%"+dto.getAdresa().getUlica().toLowerCase()+"%"));
//		}
		if(Long.parseLong(dto.getOcena())!=-1L) predicates.add(cb.ge(centar.get("ocena"),Long.parseLong(dto.getOcena())));
		//datumi
		LocalDateTime danas=LocalDateTime.now();
		danas=zaokruzi(danas);
		LocalDateTime sutra= danas.plusDays(1);
		
		Subquery<Termin> sub=query.subquery(Termin.class);
		Root<Termin> termin= sub.from(Termin.class);
		List<Predicate> predTermini= new ArrayList<>();
		
		if(dto.getOdDatuma()!=null) { 
			predicates.add(cb.greaterThanOrEqualTo(centar.join("termini").get("pocetakTermina"), zaokruzi(dto.getOdDatuma())));
			//predTermini.add(cb.greaterThanOrEqualTo(termin.get("pocetakTermina"), zaokruzi(dto.getOdDatuma())));
			//sub.select(termin).distinct(true).where(cb.and(predTermini.toArray(new Predicate[predTermini.size()])));
			//predicates.add(cb.equal(centar.join("termini").get("pocetakTermina"), sub));
		}
		
		if(dto.getDoDatuma()!=null) { 
			predicates.add(cb.lessThanOrEqualTo(centar.join("termini").get("krajTermina"), zaokruzi(dto.getDoDatuma().plusDays(1))));
//			predTermini.removeAll(predTermini);
//			predTermini.add(cb.lessThanOrEqualTo(termin.get("krajTermina"), zaokruzi(dto.getDoDatuma().plusDays(1))));
//			sub.select(termin).distinct(true).where(cb.and(predTermini.toArray(new Predicate[predTermini.size()])));
//			predicates.add(cb.equal(centar.join("termini").get("krajTermina"), sub));
		}
		
		
		if(predicates.size()>0)
			query.select(centar).distinct(true).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		else 		
			query.select(centar);
		List<MedCentar> rez=entityManager.createQuery(query).getResultList();
		return rez.stream().map(x->medCentarMapper.toDTO(x)).collect(Collectors.toList());
	}
	
	public LocalDateTime zaokruzi(LocalDateTime t) {
		LocalDateTime z=t.withHour(0).withMinute(0).withSecond(0).withNano(0);
		return z;
	}

	public MedCentarDTO getCentarById(Long id) {
		// TODO Auto-generated method stub
		MedCentar mc = this.medCentarRepository.findById(id).orElse(null);
		if(mc == null) {
			return null;
		}
		return this.medCentarMapper.toDTO(mc);
	}

	public Collection<TerminDTO> getTermineByIdCentra(Long id) {
		// TODO Auto-generated method stub
		MedCentar medCentar = this.medCentarRepository.findById(id).orElse(null);
		if(medCentar == null) {
			return null;
		}
		Collection<Termin> listaTermina = medCentar.getTermini();
		Collection<TerminDTO> retList = new ArrayList<TerminDTO>();
		for(Termin t:listaTermina) {
			retList.add(this.terminMapper.toDTO(t));
		}
		return retList;
	}

	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	public ResponseEntity<TerminDTO> terminReserve(Long userId, Long terminId) {
		// TODO Auto-generated method stub
		Termin t = this.terminRepository.findById(terminId).orElse(null);
		RegKorisnik kor = this.regKorisnikRepository.findById(userId).orElse(null);
		if(t == null) {
			return new ResponseEntity<TerminDTO>(HttpStatus.NO_CONTENT);
		}
		if(kor == null) {
			return new ResponseEntity<TerminDTO>(HttpStatus.NO_CONTENT);
		}
		if(kor.getUpitnik() == null) {
			System.out.println("Nije popunjen upitnik!");
			return new ResponseEntity<TerminDTO>(HttpStatus.ALREADY_REPORTED);
		}
		//dobavlja sve termine koji u listi prijavljenih imaju korisnika sa id-om userId
		List<MedCentar> listaCentara = this.medCentarRepository.findAllByTermini_ListaPrijavljenih_Id(userId);
		for(MedCentar tmpMd : listaCentara) {
			if(tmpMd.getId() == t.getMedCentar().getId()) {
				System.out.println("Vec imate rezervisan termin u ovom centru.");
				return new ResponseEntity<TerminDTO>(HttpStatus.IM_USED);
			}
		}
		
		LocalDate now = LocalDate.now();
		LocalDate lastDonation = null;
		long days = 0;
		if(kor.getPoslednjaDonacija() == null) {
			days = 1000;
		}else {
			lastDonation = kor.getPoslednjaDonacija();
			days = ChronoUnit.DAYS.between(lastDonation, now);
		}
		if(days<=(30*6)) {
			System.out.println("Vec ste donirali krv u prethodnih 6 meseci.");
			return new ResponseEntity<TerminDTO>(HttpStatus.MULTI_STATUS);
		}
//		varijanta u kojoj se ne dozvoljava zakazivanje ukoliko korisnik prethodno ima zakazan termin u bilo kom od centara
//		if(listaCentara.size() != 0) {
//			System.out.println("Vec imate rezervisan termin u nekom od centara.");
//			return new ResponseEntity<TerminDTO>(HttpStatus.IM_USED);
//		}
		
		if(t.getBrSlobodnihMesta() > 0) {
			
			
			int tmp = t.getListaPrijavljenih().size();
			for(RegKorisnik rk : t.getListaPrijavljenih()) {
				if(rk.getId() == userId) {
					return null;
				}
			}
			
			t.getListaPrijavljenih().add(kor);
			t.setBrSlobodnihMesta(t.getBrojMesta() - t.getListaPrijavljenih().size());
			kor.getTermini().add(t);
			
			this.terminRepository.save(t);
			this.regKorisnikRepository.save(kor);
			
			this.emailService.sendReservationNotificationMail(kor, t.getMedCentar(), t);
//			this.emailService.sendReservationNotificationMail(kor, t.getMedCentar(), t);
		}
		TerminDTO retVal = this.terminMapper.toDTO(t);
		return new ResponseEntity<TerminDTO>(retVal, HttpStatus.OK);
	}

	public TerminDTO cancelTermin(Long id, Long userId) {
		// TODO Auto-generated method stub
		Termin t = this.terminRepository.findById(id).orElse(null);
		if(t == null) {
			return null;
		}
		for(RegKorisnik rk : t.getListaPrijavljenih()) {
			if(rk.getId().equals(userId)) {
				long hours = Duration.between(t.getPocetakTermina(), LocalDateTime.now()).toHours();
				if(hours > 24) {
					t.getListaPrijavljenih().remove(rk);
					rk.getTermini().remove(t);
					t.setBrSlobodnihMesta(t.getBrSlobodnihMesta() + 1);
					this.terminRepository.save(t);
					this.regKorisnikRepository.save(rk);
					this.emailService.sendReservationCancelationNotificationMail(rk, t.getMedCentar(), t);
					break;
				}else {
					return null;
				}
				
			}
		}
		TerminDTO retVal = this.terminMapper.toDTO(t);
		return retVal;
	}

	public MedCentarDTO updateTermini(MedCentarDTO mc) {
		MedCentar stari= medCentarRepository.getById(mc.getId());
		List<Long> idsBaza=stari.getTermini().stream().map(x->x.getId()).collect(Collectors.toList());
		List<Long> idsDTO=mc.getTermini().stream().map(x->x.getId()).collect(Collectors.toList());
		for(Long id: idsBaza) {
			if(!idsDTO.contains(id)) {
				Termin temp= new Termin();
				for(Termin t : stari.getTermini()) {
					if(t.getId().equals(id)) {
						temp=t;
						break;
					}
				}
				if(temp.getListaPrijavljenih().size()==0)
					stari.getTermini().remove(temp);
			}
			
		}
		
		List<Termin> novi= new ArrayList<Termin>();
		for(TerminDTO t: mc.getTermini()) {
			if(t.getId().equals(-1L))
				stari.getTermini().add(new Termin(stari, t.getPocetakTermina(), t.getKrajTermina(),t.getBrojMesta(),t.getBrojMesta()));
			else {
				//Termin tBaza= terminRepository.getById(t.getId());
				//novi.add(tBaza);
			}
		}
		
		stari=medCentarRepository.saveAndFlush(stari);

		return medCentarMapper.toDTO(stari);
	}
	
	public MedCentarDTO getByOsoblje(Long id) {
		MedRadnik r= mrRep.getById(id);
		return medCentarMapper.toDTO(r.getMedCentar());
	}

	public List<MedCentarDTO> getSlobodne(TerminMiniDTO tempTermin) {
		LocalDateTime pocetak=tempTermin.getPocetakTermina();
		LocalDateTime kraj=tempTermin.getKrajTermina();
		List<MedCentar> slobodni= new ArrayList<MedCentar>();
		for(MedCentar m : medCentarRepository.findAll()) {
			boolean slobodan=true;
			//treba proveriti radno vreme i pauzu!
			for(Termin t: m.getTermini()) {
				if(t.getPocetakTermina().isAfter(kraj) || t.getKrajTermina().isBefore(pocetak)) {continue;}
				else {
					slobodan=false;
					break;
				}
			}
			if(slobodan==true) slobodni.add(m); 
		}
		
		return slobodni.stream().map(x->medCentarMapper.toDTO(x)).collect(Collectors.toList());
	}
	//provere
	public Termin customRezervacija(CustomRezDTO body) {
		MedCentar mc= medCentarRepository.getById(body.getId());
		RegKorisnik rk= regKorisnikRepository.getById(body.getUserId());
		Termin t= new Termin(mc, body.getTerminTemp().getPocetakTermina(), body.getTerminTemp().getKrajTermina(), 1, 0);
		t.getListaPrijavljenih().add(rk);
		
		t= terminRepository.saveAndFlush(t);
		rk.getTermini().add(t);
		rk=regKorisnikRepository.saveAndFlush(rk);
		emailService.sendReservationNotificationMail(rk,mc,t);
		return t;
	}
	
	
}
