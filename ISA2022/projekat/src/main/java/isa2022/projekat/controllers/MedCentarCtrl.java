package isa2022.projekat.controllers;

import java.util.Collection;

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

import isa2022.projekat.dtos.MedCentarDTO;
import isa2022.projekat.dtos.PretragaDTO;
import isa2022.projekat.dtos.TerminDTO;
import isa2022.projekat.services.MedCentarService;

@RestController
@RequestMapping("/medcentar")
public class MedCentarCtrl {

	@Autowired
	private MedCentarService medCentarService;
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
	public ResponseEntity<TerminDTO> terminReserve(@PathVariable(value="userId") Long userId, @PathVariable(value="terminId") Long terminId){
		return this.medCentarService.terminReserve(userId, terminId)!= null ? new ResponseEntity<TerminDTO>(this.medCentarService.terminReserve(userId, terminId), HttpStatus.OK) : 
			new ResponseEntity<TerminDTO>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/cancelTermin/{id}/{userId}")
	public ResponseEntity<TerminDTO> cancel(@PathVariable(value="id") Long id, @PathVariable(value="userId") Long userId){
		return this.medCentarService.cancelTermin(id, userId) != null ? new ResponseEntity<TerminDTO>(this.medCentarService.cancelTermin(id, userId), HttpStatus.OK) :
			new ResponseEntity<TerminDTO>(HttpStatus.NO_CONTENT);
	}
}
