package isa2022.projekat.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa2022.projekat.dtos.MedCentarDTO;
import isa2022.projekat.dtos.PretragaDTO;
import isa2022.projekat.mappers.MedCentarMapper;
import isa2022.projekat.model.data.MedCentar;
import isa2022.projekat.model.data.Termin;
import isa2022.projekat.repositories.MedCentarRepository;

@Service
public class MedCentarService {

	@Autowired
	private MedCentarRepository medCentarRepository;
	@Autowired
	private MedCentarMapper medCentarMapper;
	
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
}
