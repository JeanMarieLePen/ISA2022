package isa2022.projekat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import isa2022.projekat.model.korisnici.Korisnik;

@SpringBootTest
public class ModelKorisniciUnitTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Mock
	private Korisnik korisnik;
	
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
	
}
