package isa2022.projekat.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa2022.projekat.dtos.MedCentarDTO;
import isa2022.projekat.dtos.PretragaDTO;
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
}
