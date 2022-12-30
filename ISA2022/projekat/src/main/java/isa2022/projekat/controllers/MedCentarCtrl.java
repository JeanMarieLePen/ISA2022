package isa2022.projekat.controllers;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa2022.projekat.dtos.CustomRezDTO;
import isa2022.projekat.dtos.MedCentarDTO;
import isa2022.projekat.dtos.PorudzbinaDTO;
import isa2022.projekat.dtos.PretragaDTO;
import isa2022.projekat.dtos.TerminDTO;
import isa2022.projekat.dtos.TerminMiniDTO;
import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.data.Upitnik;
import isa2022.projekat.model.korisnici.RegKorisnik;
import isa2022.projekat.repositories.MedRadnikRep;
import isa2022.projekat.repositories.RegKorisnikRepository;
import isa2022.projekat.services.MedCentarService;

@RestController
@RequestMapping("/medcentar")
public class MedCentarCtrl {
	@Autowired  private MedRadnikRep mrRep;
	@Autowired	private MedCentarService medCentarService;
	@Autowired private RegKorisnikRepository rRep;
	@GetMapping("/getAll")
	public ResponseEntity<Collection<MedCentarDTO>> getAll(){
		return this.medCentarService.getAll().size() > 0 ? new ResponseEntity<Collection<MedCentarDTO>>(this.medCentarService.getAll(), HttpStatus.OK) 
				: new ResponseEntity<Collection<MedCentarDTO>>(HttpStatus.NO_CONTENT); 
	}
	
	@PostMapping("/pretraga")
	public ResponseEntity<Collection<MedCentarDTO>> pretraga(@RequestBody PretragaDTO searchEntity){
		System.out.println(searchEntity.toString());
		return new ResponseEntity<Collection<MedCentarDTO>>(medCentarService.pretraga(searchEntity), HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<MedCentarDTO> getCentarById(@PathVariable(value="id") Long id){
		return new ResponseEntity<MedCentarDTO> (medCentarService.getCentarById(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGISTROVANI_KORISNIK')")
	@GetMapping("/getTermineById/{id}")
	public ResponseEntity<Collection<TerminDTO>> getTermine(@PathVariable(value="id") Long id){
		return this.medCentarService.getTermineByIdCentra(id).size() > 0 ? new ResponseEntity<Collection<TerminDTO>>(this.medCentarService.getTermineByIdCentra(id), HttpStatus.OK) 
				: new ResponseEntity<Collection<TerminDTO>>(HttpStatus.NO_CONTENT);  
	}
	
	@PutMapping("/terminReserve/{userId}/{terminId}")
	public ResponseEntity<TerminDTO> terminReserve(@PathVariable(value="userId") Long userId, @PathVariable(value="terminId") Long terminId) throws Exception{
//		return this.medCentarService.terminReserve(userId, terminId)!= null ? new ResponseEntity<TerminDTO>(this.medCentarService.terminReserve(userId, terminId), HttpStatus.OK) : 
//			new ResponseEntity<TerminDTO>(HttpStatus.NO_CONTENT);
		return this.medCentarService.terminReserve(userId, terminId);
	}
	
	@PutMapping("/cancelTermin/{id}/{userId}")
	public ResponseEntity<TerminDTO> cancel(@PathVariable(value="id") Long id, @PathVariable(value="userId") Long userId){
		return this.medCentarService.cancelTermin(id, userId) != null ? new ResponseEntity<TerminDTO>(this.medCentarService.cancelTermin(id, userId), HttpStatus.OK) :
			new ResponseEntity<TerminDTO>(HttpStatus.NO_CONTENT);
	}
	@PutMapping("/{id}/updateTermin")
	public ResponseEntity<MedCentarDTO> updateTermina(@RequestBody MedCentarDTO mc){
		MedCentarDTO m=medCentarService.updateTermini(mc);
		return new ResponseEntity<MedCentarDTO>(m, HttpStatus.OK);
	}
	@GetMapping("/getByOsoblje/{id}")
	public ResponseEntity<MedCentarDTO> getByOsoblje(@PathVariable(value="id") Long id){
		Long mid= mrRep.getById(id).getMedCentar().getId();
		MedCentarDTO m=medCentarService.getByOsoblje(id);
		return m!=null ? new ResponseEntity<MedCentarDTO>(m,HttpStatus.OK) :new ResponseEntity<MedCentarDTO>(new MedCentarDTO(),HttpStatus.NO_CONTENT);
	}
	@PutMapping("/terminFilter")
	public ResponseEntity<List<MedCentarDTO>> getSlobodne(@RequestBody TerminMiniDTO tempTermin){
		List<MedCentarDTO> slobodni=medCentarService.getSlobodne(tempTermin);
		return slobodni.size()>0 ?   new ResponseEntity<List<MedCentarDTO>>(slobodni, HttpStatus.OK) : new ResponseEntity<List<MedCentarDTO>>(new ArrayList<MedCentarDTO>(), HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/customRezervacija")
	public ResponseEntity<Boolean> customRez(@RequestBody CustomRezDTO body){
		RegKorisnik r= rRep.getById(body.getUserId());
		long months=ChronoUnit.MONTHS.between(YearMonth.from(r.getPoslednjaDonacija()),
				YearMonth.from(LocalDateTime.now())
				);
		if(months<6) return new ResponseEntity<Boolean>(false, HttpStatus.MULTI_STATUS);
		
		if(null==r.getUpitnik())return new ResponseEntity<Boolean>(false, HttpStatus.ALREADY_REPORTED);
		Termin t= medCentarService.customRezervacija(body);
		return t!=null ? new ResponseEntity<Boolean>(true, HttpStatus.OK) : new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/makeNewOrder/{idOd}/{idDo}")
	public ResponseEntity<PorudzbinaDTO> makeNewOrder(@PathVariable(value="idOd") Long idOd, @PathVariable(value="idDo") Long idDo, @RequestBody PorudzbinaDTO porudzbina){
		PorudzbinaDTO dto = this.medCentarService.makeNewOrder(idOd, idDo, porudzbina);
		if(dto==null) {
			return new ResponseEntity<PorudzbinaDTO>(HttpStatus.ALREADY_REPORTED);
		}else {
			return new ResponseEntity<PorudzbinaDTO>(dto, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/getMedCentarByWorkerId/{id}")
	public ResponseEntity<MedCentarDTO> getMedCentarByWorkerId(@PathVariable(value="id") Long id){
		MedCentarDTO dto = this.medCentarService.findMedCentarByWorkerId(id);
		if(dto == null) {
			return new ResponseEntity<MedCentarDTO>(HttpStatus.IM_USED);
		}else {
			return new ResponseEntity<MedCentarDTO>(dto, HttpStatus.OK);
		}
	}
	@GetMapping("/getAllPorudzbineByWorkersId/{id}")
	public ResponseEntity<Collection<PorudzbinaDTO>> getAllPorudzbineByWorkersId(@PathVariable(value="id") Long id){
		Collection<PorudzbinaDTO> retList = this.medCentarService.getAllPorudzbineByWorkersId(id);
		if(retList.isEmpty()) {
			return new ResponseEntity<Collection<PorudzbinaDTO>>(HttpStatus.IM_USED);
		}else {
			return new ResponseEntity<Collection<PorudzbinaDTO>>(retList, HttpStatus.ACCEPTED);
		}
	}
	@GetMapping("/getPorudzbinaById/{id}")
	public ResponseEntity<PorudzbinaDTO> getPorudzbinaById(@PathVariable(value="id") Long id){
		PorudzbinaDTO retVal = this.medCentarService.getPorudzbinaById(id);
		if(retVal==null) {
			return new ResponseEntity<PorudzbinaDTO>(HttpStatus.IM_USED);
		}else {
			return new ResponseEntity<PorudzbinaDTO>(retVal, HttpStatus.ACCEPTED);
		}
	}
	@GetMapping("/getNarudzbineByWorkersId/{id}")
	public ResponseEntity<Collection<PorudzbinaDTO>> getNarudzbineByWorkersId(@PathVariable(value="id") Long id){
		Collection<PorudzbinaDTO> retList = this.medCentarService.getNarudzbineByWorkersId(id);
		if(retList.isEmpty()) {
			return new ResponseEntity<Collection<PorudzbinaDTO>>(HttpStatus.IM_USED);
		}else {
			return new ResponseEntity<Collection<PorudzbinaDTO>>(retList, HttpStatus.ACCEPTED);
		}
	}
}
