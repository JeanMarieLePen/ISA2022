package isa2022.projekat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.data.Upitnik;
import isa2022.projekat.model.korisnici.Korisnik;
import isa2022.projekat.model.korisnici.RegKorisnik;
import isa2022.projekat.model.korisnici.StatusNaloga;
import isa2022.projekat.model.korisnici.TipKorisnika;
import isa2022.projekat.repositories.RegKorisnikRepository;
import isa2022.projekat.repositories.TerminRepository;
import isa2022.projekat.repositories.UpitnikRepository;
import isa2022.projekat.services.MedCentarService;

import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
public class ModelKorisniciUnitTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Mock
	private Korisnik korisnik;
	
	@InjectMocks
	private MedCentarService medCentarService;
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testKorisnik() {
		this.korisnik = new Korisnik();
		assertEquals(null, korisnik.getIme());
	}
	@Test
	public void testGetSetEmail() {
		Korisnik k = new Korisnik();
		k.setEmail("testmail@gmail.com");
		assertEquals("testmail@gmail.com", k.getEmail());
	}
	

//	@Test
//	@Transactional
//	@Rollback(true)
//	void dodavanjeKorisnika() {
//		Korisnik k = new Korisnik("1234", "bemax", "bemax@gmail.com", "milo", "djukanovic", "Titograd",
//				LocalDate.of(2020, 5, 12), LocalDate.of(2020, 5, 12), StatusNaloga.AKTIVAN, TipKorisnika.REGISTROVANI_KORISNIK,
//				null);
//		RegKorisnik rk = new RegKorisnik();
//		repository.save(rk);
//		repository.flush();
//		assertEquals(1, repository.count());
//	}
	
//	@Mock
//	RegKorisnikRepository regKorisnikRepository;
//	@Mock 
//	TerminRepository terminRepository;
//	@Mock
//	UpitnikRepository upitnikRepository;
//	@Test
//	@WithMockUser(roles="REGISTROVANI_KORISNIK")
//	public void zakaziTermin() {
//		
//		RegKorisnik rk = new RegKorisnik();
//		Upitnik u = new Upitnik();
//		u.setAdresa("dwadw");
//		u.setId(31L);
//		rk.setUpitnik(u);
//		rk.setId(21L);
//		
//		Optional<RegKorisnik> rkOpt = Optional.of(rk);
//		Optional<Upitnik> uOpt = Optional.of(u);
//		when(regKorisnikRepository.findById(anyLong())).thenReturn(rkOpt);
//		when(upitnikRepository.findById(anyLong())).thenReturn(uOpt);
//		
////		MvcResult res1 = mockMvc.perform(put("/medCentar/terminReserve/" + anyLong() + "/" + anyLong()).and
////		when(terminRepository.findById(anyLong())).thenReturn(t);
//		
////		MvcResult res = mockMvc.perform(put("/medCentar/terminReserve/"))
//	}
	
	
	
}
