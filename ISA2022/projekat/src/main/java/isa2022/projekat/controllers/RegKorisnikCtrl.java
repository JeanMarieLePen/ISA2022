package isa2022.projekat.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa2022.projekat.dtos.RegKorisnikDTO;
import isa2022.projekat.dtos.UpdateNalogDTO;
import isa2022.projekat.dtos.UpitnikDTO;
import isa2022.projekat.services.RegKorisnikService;

@RestController
@RequestMapping("/regkorisnik")
public class RegKorisnikCtrl {

	
	@Autowired
	private RegKorisnikService regKorisnikService;
	
	@PostMapping("/submitUpitnik/{id}")
	public ResponseEntity<UpitnikDTO> submitUpitnik(@PathVariable(value="id")Long id, @RequestBody UpitnikDTO u){
		UpitnikDTO retVal = regKorisnikService.submitUpitnik(u, id);
		return  (retVal != null) ? new ResponseEntity<UpitnikDTO>(retVal, HttpStatus.OK) : new ResponseEntity<UpitnikDTO>(HttpStatus.ALREADY_REPORTED);	
	}
	@GetMapping("/checkUpitnik/{id}")
	public ResponseEntity<Boolean> checkUpitnik(@PathVariable(value="id") Long id) {
		Boolean retVal = regKorisnikService.checkUpitnik(id);
		return new ResponseEntity<Boolean>(retVal, HttpStatus.OK);
	}
	
	@GetMapping("/registrovaniKorisnik/{id}")
	public ResponseEntity<RegKorisnikDTO> getRegKorisnik(@PathVariable(value="id") Long id){
		return new ResponseEntity<RegKorisnikDTO>(regKorisnikService.getRegKorisnik(id), HttpStatus.OK);
	}
	
	@PutMapping(value="")
	public ResponseEntity<RegKorisnikDTO> update(@RequestBody UpdateNalogDTO dto){
		RegKorisnikDTO osvezeni= regKorisnikService.update(dto);
		return new ResponseEntity<RegKorisnikDTO>(osvezeni, HttpStatus.OK);
	}
	
	@DeleteMapping(value="deleteRegistrovaniKorisnik/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable(value="id") Long id){
		Boolean obrisan=regKorisnikService.deleteRegKor(id);
		HttpStatus status=  obrisan.equals(true) ? HttpStatus.OK : HttpStatus.OK;
		return new ResponseEntity<Boolean>(obrisan, HttpStatus.OK);
	}
}
